package com.diatanato.android.fretboarlogic.fretboard;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.widget.RelativeLayout;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import androidx.core.content.ContextCompat;

import com.diatanato.android.fretboarlogic.R;
import com.diatanato.android.fretboarlogic.instruments.Guitar;
import com.diatanato.android.fretboarlogic.instruments.Instrument;
import com.diatanato.android.fretboarlogic.settings.Settings;

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

        ((ConstraintLayout.LayoutParams)getLayoutParams()).dimensionRatio =  w + ":" + h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int w = getMeasuredWidth();
        int h = getMeasuredHeight();

        mInstrument.getFretboard().measure(w, h);
    }

    /** Добавляет случайную ноту на гриф. */

    public FretboardPoint addRandomPoint(Settings settings, @PointBackground int background)
    {
        return addPoint(mInstrument.getRandomNote(settings), background);
    }

    /** Добавляет ноту на гриф. */

    public FretboardPoint addPoint(FretboardNote note, @PointBackground int background)
    {
        Rect position = getPointPosition(note.getFret(), note.getString());

        FretboardPoint point = new FretboardPoint(getContext(), note);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(position.width(), position.height());
        params.topMargin  = position.top;
        params.leftMargin = position.left;

        point.setLayoutParams(params);
        point.setBackground(getPointBackground(background));

        addView(point);
        return point;
    }

    /** Расчитывает положение точки на грифе. */

    private Rect getPointPosition(int fret, int string)
    {
        //TODO: пересчет пропорций с учетом текущих width и height только в момент изменения размеров коптонета
        FretboardSpecs specs = mInstrument.getFretboard().getSpecs();

        int pointsize = 32;

        float left = getMeasuredWidth()  * specs.getFretPadding();
        float top  = getMeasuredHeight() * specs.getStringStep();

        float lastFretSize    = getMeasuredWidth() * specs.getFretSize();
        float currentFretSize = 0;

        for (int i = 0; i < string; i++)
        {
            top += getMeasuredHeight() * specs.getStringStep();
        }
        for (int i = 0; i < fret; i++)
        {
            left += currentFretSize;

            currentFretSize = lastFretSize * specs.getFretStep();
            lastFretSize    = currentFretSize;
        }
        left += currentFretSize / 2;

        left -= pointsize / 2;
        top  -= pointsize / 2;

        return new Rect((int)left, (int)top, (int)left + pointsize, (int)top + pointsize);
    }

    /** Возвращает оформление фона для точки на грифе. */

    @NonNull
    private Drawable getPointBackground(@PointBackground int background)
    {
        ContextThemeWrapper wrapper;

        switch (background)
        {
            case POINT_RED:
                wrapper = new ContextThemeWrapper(getContext(), R.style.PointRed);
                break;
            case POINT_GREEN:
                wrapper = new ContextThemeWrapper(getContext(), R.style.PointGreen);
                break;
            case POINT_BLUE:
                wrapper = new ContextThemeWrapper(getContext(), R.style.PointBlue);
                break;
            case POINT_CROSS:
                return ContextCompat.getDrawable(getContext(), R.drawable.point_cross);
            default:
                throw new IllegalArgumentException();
        }
        return VectorDrawableCompat.create(getResources(), R.drawable.fretboard_point, wrapper.getTheme());
    }
}
