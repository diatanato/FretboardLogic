package com.diatanato.android.fretboarlogic;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diatanato.android.fretboarlogic.fretboard.FretboardPoint;
import com.diatanato.android.fretboarlogic.fretboard.FretboardView;
import com.diatanato.android.fretboarlogic.settings.Settings;
import com.diatanato.android.fretboarlogic.settings.SettingsActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    private FretboardPoint mPoint;
    private FretboardView  mFretboard;

    private LinearLayout   mBottomPanel;

    private TextView       mCorrect;
    private TextView       mIncorrect;
    private TextView       mSpeed;

    private long           mTime;
    private int            mCorrectCount;
    private int            mIncorrectCount;

    private Settings       mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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
        mSettings    = new Settings(this);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        update();
    }

    public synchronized void onClick(View param)
    {
        param.setEnabled(false);

        if (mPoint.getNote().getNote() == Integer.parseInt(param.getTag().toString()))
        {
            mCorrectCount += 1;
            mCorrect.setText(String.valueOf(mCorrectCount));
            animation(mCorrect);

            long time = getCurrentTime() - mTime;
            float speed = (float)time / 1000.0F;
            mSpeed.setText(String.format("%.2f", speed));

            if (mSettings.sound())
            {

            }
            mPoint.setTextVisibility(View.VISIBLE);
            animation(mPoint, new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    setPoint();
                }
            });
        }
        else
        {
            mIncorrectCount += 1;
            mIncorrect.setText(String.valueOf(mIncorrectCount));
            animation(mIncorrect);

            //TODO: меняем цвет кнопки на красный
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
        if (mPoint != null)
        {
            setPoint();
        }
        mFretboard.setScaleX(mSettings.reverse() ? -1 : 1);
    }

    /** Устанавливает точку на грифе. */

    private void setPoint()
    {
        mTime = getCurrentTime();

        mFretboard.removeView(mPoint);
        mPoint = mFretboard.addRandomPoint(mSettings, FretboardView.POINT_BLUE);
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
