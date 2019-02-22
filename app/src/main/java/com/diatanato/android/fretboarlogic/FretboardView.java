package com.diatanato.android.fretboarlogic;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.diatanato.android.fretboarlogic.instruments.Guitar;
import com.diatanato.android.fretboarlogic.instruments.Instrument;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FretboardView extends RelativeLayout
{
    @IntDef(
    {
        POINT_CROSS,
        POINT_RED,
        POINT_GREEN,
        POINT_BLUE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface PointBackground { }

    public final static int POINT_CROSS = 0;
    public final static int POINT_RED   = 1;
    public final static int POINT_GREEN = 2;
    public final static int POINT_BLUE  = 3;

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

        setScaleX(-1); //TODO: проверяем settings.reverse()

        ((ConstraintLayout.LayoutParams)getLayoutParams()).dimensionRatio =  w + ":" + h;
    }

    /** Добавляет случайную ноту на гриф. */

    public FretboardPoint addRandomPoint(AppSettings settings, @PointBackground int background)
    {
        return addPoint(settings, mInstrument.getRandomNote(settings), background);
    }

    /** Добавляет ноту на гриф. */

    public FretboardPoint addPoint(AppSettings settings, FretboardNote note, @PointBackground int background)
    {
        Rect position = getPointPosition(note.getFret(), note.getString());

        FretboardPoint point = new FretboardPoint(getContext(), note);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(position.width(), position.height());
        params.topMargin  = position.top;
        params.leftMargin = position.left;

        point.setLayoutParams(params);
        point.setBackground(getPointBackground(background));

        if (settings.reverse())
        {
            point.setScaleX(-1);
        }
        addView(point);
        return point;
    }

    /** Расчитывает положение точки на грифе. */

    private Rect getPointPosition(int fret, int string)
    {
        int pointheight = 38;

        int left = 110 - pointheight / 2;
        int top  =  62 - pointheight / 2;

        return new Rect(left, top, left + pointheight, top + pointheight);
    }

    /** Возвращает оформление фона для точки на грифе. */

    @NonNull
    private Drawable getPointBackground(@PointBackground int background)
    {
        switch (background)
        {
            case POINT_CROSS:
                return ContextCompat.getDrawable(getContext(), R.drawable.point_cross);
            case POINT_RED:
                return ContextCompat.getDrawable(getContext(), R.drawable.point_red);
            case POINT_GREEN:
                return ContextCompat.getDrawable(getContext(), R.drawable.point_green);
            case POINT_BLUE:
                return ContextCompat.getDrawable(getContext(), R.drawable.point_blue);
        }
        throw new IllegalArgumentException();
    }
}
