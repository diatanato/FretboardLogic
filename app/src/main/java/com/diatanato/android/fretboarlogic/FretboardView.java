package com.diatanato.android.fretboarlogic;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.diatanato.android.fretboarlogic.instruments.Guitar;
import com.diatanato.android.fretboarlogic.instruments.Instrument;

public class FretboardView extends RelativeLayout
{
    //TODO: выбираем инструмент в соответствии с настройками
    private final Instrument mInstrument = new Guitar(getContext());

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

        Drawable fretboard = mInstrument.getFretboard().getImage();

        setBackground(fretboard);

        int w = fretboard.getIntrinsicWidth();
        int h = fretboard.getIntrinsicHeight();

        //setScaleX(-1);

        ((ConstraintLayout.LayoutParams)getLayoutParams()).dimensionRatio =  w + ":" + h;
    }

    /** Добавляет случайную ноту на гриф. */

    public FretboardPoint addRandomPoint(AppSettings settings)
    {
        return addPoint(settings, mInstrument.getRandomNote(settings));
    }

    /** Добавляет ноту на гриф. */

    public FretboardPoint addPoint(AppSettings settings, FretboardNote note)
    {
        Rect position = getPointPosition(note.getFret(), note.getString(), settings.reverse());

        FretboardPoint point = new FretboardPoint(getContext(), note);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(position.width(), position.height());
        params.topMargin  = position.top;
        params.leftMargin = position.left;

        point.setLayoutParams(params);
        point.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.point_red));

        addView(point);
        return point;
    }

    /** Расчитывает положение точки на грифе. */

    private Rect getPointPosition(int fret, int string, boolean reverse)
    {
        int pointheight = 38;

        int left = 110 - pointheight / 2;
        int top  =  62 - pointheight / 2;

        //под левую руку
        if (reverse)
        {
            //left = getMeasuredWidth() - left - pointheight;  //TODO: если делать scale -1, то нужно что-то придумать с текстом
        }
        return new Rect(left, top, left + pointheight, top + pointheight);
    }
}
