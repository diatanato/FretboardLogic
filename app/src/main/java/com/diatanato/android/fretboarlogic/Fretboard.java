package com.diatanato.android.fretboarlogic;

import android.graphics.drawable.Drawable;

/** Спецификации и изображение грифа для расчера положения точки. */

public class Fretboard
{
    private final Drawable mImage;

    private final int mMinFret;
    private final int mMaxFret;

    public Fretboard(Drawable image, int min, int max)
    {
        mImage   = image;
        mMinFret = min;
        mMaxFret = max;
    }

    /** Изображение грифа. */

    public Drawable getImage()
    {
        return mImage;
    }

    /** Минимальный лад. */

    public int getMinFret()
    {
        return mMinFret;
    }

    /** Максимальный лад. */

    public int getMaxFret()
    {
        return mMaxFret;
    }
}
