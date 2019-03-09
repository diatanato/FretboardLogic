package com.diatanato.android.fretboarlogic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    private FretboardPoint mPoint;
    private FretboardView  mFretboard;

    private AppSettings    mSettings = new AppSettings();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFretboard = findViewById(R.id.fretboard);
        mFretboard.setOnClickListener(view -> updatePoint());
    }

    public void onClick(View param)
    {
        if (mPoint.getNote().getNote() == Integer.parseInt(param.getTag().toString()))
        {
            updatePoint();
        }
    }

    private void updatePoint()
    {
        mFretboard.removeView(mPoint);
        mPoint = mFretboard.addRandomPoint(mSettings, FretboardView.POINT_RED);
        mPoint.setTextVisibility(View.INVISIBLE);
    }
}
