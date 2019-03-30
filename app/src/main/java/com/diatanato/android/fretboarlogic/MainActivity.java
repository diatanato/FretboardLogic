package com.diatanato.android.fretboarlogic;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.diatanato.android.fretboarlogic.settings.SettingsActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    private FretboardPoint mPoint;
    private FretboardView  mFretboard;

    private TextView       mCorrect;
    private TextView       mIncorrect;
    private TextView       mSpeed;

    private long           mTime;
    private int            mCorrectCount;
    private int            mIncorrectCount;

    private AppSettings    mSettings = new AppSettings();

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
            zoom();
            setPoint();
        });
        findViewById(R.id.settings).setOnClickListener(view ->
        {
            startActivity(new Intent(this, SettingsActivity.class));
        });
    }

    public void onClick(View param)
    {
        if (mPoint.getNote().getNote() == Integer.parseInt(param.getTag().toString()))
        {
            //TODO: нужна блокировака кнопки на время обработки правильного ответа

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

    /** Масштабирует гриф с учетом используемых ладов. */

    private void zoom()
    {
        if (mSettings.zoom())
        {
            //TODO: масштабирование грифа
        }
    }

    /** Устанавливает точку на грифе. */

    private void setPoint()
    {
        mFretboard.removeView(mPoint);
        mPoint = mFretboard.addRandomPoint(mSettings, FretboardView.POINT_BLUE);
        mPoint.setTextVisibility(View.INVISIBLE);

        mTime = getCurrentTime();
    }

    /** Возвращает текущее время в миллисекундах. */

    private long getCurrentTime()
    {
        return Calendar.getInstance().getTime().getTime();
    }
}
