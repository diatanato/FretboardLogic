package com.diatanato.android.fretboarlogic;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    private Date           mDate;
    private FretboardPoint mPoint;
    private FretboardView  mFretboard;

    private AppSettings    mSettings = new AppSettings();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFretboard = findViewById(R.id.fretboard);
        mFretboard.setOnClickListener(view -> setPoint());
    }

    public void onClick(View param)
    {
        if (mPoint.getNote().getNote() == Integer.parseInt(param.getTag().toString()))
        {
            //TODO: нужна блокировака кнопки на время обработки правильного ответа

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
    }

    /** Анимация масштабирования. */

    public void animation(View view)
    {
        animation(view, null);
    }

    /** Анимация масштабирования с событиями. */

    public void animation(View view, AnimatorListener listener)
    {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.scale);
        animator.setTarget(view);
        animator.addListener(listener);
        animator.start();
    }

    /** Устанавливает точку на грифе. */

    private void setPoint()
    {
        mFretboard.removeView(mPoint);
        mPoint = mFretboard.addRandomPoint(mSettings, FretboardView.POINT_BLUE);
        mPoint.setTextVisibility(View.INVISIBLE);

        mDate = Calendar.getInstance().getTime();
    }
}
