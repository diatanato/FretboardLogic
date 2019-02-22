package com.diatanato.android.fretboarlogic;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FretboardPoint extends ViewGroup
{
    private final FretboardNote mNote;
    private final TextView      mText;

    public FretboardPoint(Context context, FretboardNote note)
    {
        super(context);

        mNote = note;

        mText = new TextView(context);
        mText.setGravity(Gravity.CENTER);
        mText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7.5f);
        mText.setTextColor(Color.BLACK);
        mText.setText(Octave.getInstance().getNoteName(note.getNote(), note.getAlteration()));

        addView(mText);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        mText.layout(0, 0, r - l, b - t);
    }

    @Override
    protected void onMeasure(int width, int height)
    {
        super.onMeasure(width, height);

        mText.measure(width, height);
    }

    /** Возвращает описание ноты, связанной с точкой. */

    public FretboardNote getNote()
    {
        return mNote;
    }

    /** Устанавливаем видимость текста в точке. */

    public void setTextVisibility(int visibility)
    {
        mText.setVisibility(visibility);
    }
}
