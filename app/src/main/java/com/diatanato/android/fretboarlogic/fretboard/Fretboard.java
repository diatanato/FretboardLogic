package com.diatanato.android.fretboarlogic.fretboard;

import android.graphics.drawable.Drawable;

/** Спецификации и изображение грифа для расчера положения точки. */

public class Fretboard
{
    private final Drawable       mImage;
    private final FretboardSpecs mSpecs;

    private final int mMinFret;
    private final int mMaxFret;

    public Fretboard(Drawable image, FretboardSpecs specs, int min, int max)
    {
        mImage   = image;
        mSpecs   = specs;

        mMinFret = min;
        mMaxFret = max;
    }

    /** Корректирует спецификации с учетом текущих размеров компонента. */

    public void measure(int width, int height)
    {
        //TODO: в идеале - генерировать на основании изображения и корректировать под размеры
    }

    /** Изображение грифа. */

    public Drawable getImage()
    {
        return mImage;
    }

    /** Спецификации грифа. */

    public FretboardSpecs getSpecs()
    {
        return mSpecs;
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
