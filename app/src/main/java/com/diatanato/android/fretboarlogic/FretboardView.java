package com.diatanato.android.fretboarlogic;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.diatanato.android.fretboarlogic.instruments.Guitar;
import com.diatanato.android.fretboarlogic.instruments.Instrument;

public class FretboardView extends RelativeLayout
{
    private final Instrument mInstrument = new Guitar();

    public FretboardView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public FretboardView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        ((ConstraintLayout.LayoutParams)getLayoutParams()).dimensionRatio = "1927:310"; //TODO: Instrument.getDrawableWidth : Instrument.getDrawableHeight
    }

}
