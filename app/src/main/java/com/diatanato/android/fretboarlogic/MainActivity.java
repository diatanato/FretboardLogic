package com.diatanato.android.fretboarlogic;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.diatanato.android.fretboarlogic.dagger.ActivityComponent;
import com.diatanato.android.fretboarlogic.fretboard.FretboardPoint;
import com.diatanato.android.fretboarlogic.fretboard.FretboardView;
import com.diatanato.android.fretboarlogic.instruments.guitar.GuitarPlayer;
import com.diatanato.android.fretboarlogic.settings.Settings;
import com.diatanato.android.fretboarlogic.settings.SettingsActivity;

import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

public class MainActivity extends InjectionActivity
{
    @Inject
    Settings mSettings;

    @Inject
    GuitarPlayer mPlayer;

    private FretboardPoint mPoint;
    private FretboardView  mFretboard;
    private LinearLayout   mBottomPanel;

    private TextView       mCorrect;
    private TextView       mIncorrect;
    private TextView       mSpeed;

    private long           mTime;
    private int            mCorrectCount;
    private int            mIncorrectCount;

    @Override
    protected void onCreate(@Nullable Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        mSpeed     = findViewById(R.id.speed);
        mCorrect   = findViewById(R.id.correct);
        mIncorrect = findViewById(R.id.incorrect);
        mFretboard = findViewById(R.id.fretboard);

        mFretboard.setOnClickListener(view ->
        {
            setPoint();
        });
        findViewById(R.id.settings).setOnClickListener(view ->
        {
            startActivity(new Intent(this, SettingsActivity.class));
        });
        mBottomPanel = findViewById(R.id.bottompanel);
    }

    @Override
    protected void onInject(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mPlayer.release();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        update();
    }

    public synchronized void onClick(View button)
    {
        //TODO: меняем цвет кнопки на красный селектором
        button.setEnabled(false);

        if (mPoint.getNote().getNoteIndex() == Integer.parseInt(button.getTag().toString()))
        {
            mCorrectCount += 1;
            mCorrect.setText(String.valueOf(mCorrectCount));
            animation(mCorrect);

            long time = getCurrentTime() - mTime;
            float speed = (float)time / 1000.0F;
            mSpeed.setText(String.format(Locale.US, "%.1f", speed));

            if (mSettings.sound()) {
                mPlayer.play(mPoint.getNote());
            }
            mPoint.setTextVisibility(View.VISIBLE);
            animation(mPoint, new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation) {
                    setPoint();
                }
            });
        }
        else
        {
            mIncorrectCount += 1;
            mIncorrect.setText(String.valueOf(mIncorrectCount));
            animation(mIncorrect);
        }
    }

    /** Анимация масштабирования. */

    private void animation(View view)
    {
        animation(view, null);
    }

    /** Анимация масштабирования с событиями. */

    private void animation(View view, AnimatorListener listener)
    {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.scale);

        if (listener != null)
        {
            animator.addListener(listener);
        }
        animator.setTarget(view);
        animator.start();
    }

    /** Устанавливает гриф с учетом настроек. */

    private void update()
    {
        if (mSettings.zoom())
        {
            //TODO: масштабирование грифа
        }
        if (mSettings.reverse())
        {
            if (mFretboard.getX() > 0)
                mFretboard.setX(mFretboard.getX() * -1);
            if (mFretboard.getScaleX() > 0)
                mFretboard.setScaleX(mFretboard.getScaleX() * -1);
        }
        else
        {
            if (mFretboard.getX() < 0)
                mFretboard.setX(mFretboard.getX() * -1);
            if (mFretboard.getScaleX() < 0)
                mFretboard.setScaleX(mFretboard.getScaleX() * -1);
        }
        if (mPoint != null)
        {
            setPoint();
        }
    }

    /** Устанавливает точку на грифе. */

    private void setPoint()
    {
        mFretboard.removeView(mPoint);

        mTime  = getCurrentTime();
        mPoint = mFretboard.addRandomPoint(mSettings, FretboardView.POINT_BLUE);

        mPoint.setReverse(mSettings.reverse());
        mPoint.setTextVisibility(View.INVISIBLE);

        for (int i = 0; i < mBottomPanel.getChildCount(); i++)
        {
            mBottomPanel.getChildAt(i).setEnabled(true);
        }
    }

    /** Возвращает текущее время в миллисекундах. */

    private long getCurrentTime()
    {
        return Calendar.getInstance().getTime().getTime();
    }
}
