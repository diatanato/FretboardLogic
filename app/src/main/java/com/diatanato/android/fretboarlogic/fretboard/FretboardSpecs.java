package com.diatanato.android.fretboarlogic.fretboard;

/** Пропорции элементов изображения грифа. */

public class FretboardSpecs
{
    private final float mFretStep;
    private final float mFretSize;
    private final float mFretPadding;
    private final float mStringStep;

    public FretboardSpecs()
    {
        //TODO: куда-то вынести инициализацию

        mFretStep      = 0.943F;
        mFretSize      = 0.0762F;
        mFretPadding   = 0.0170F;
        mStringStep    = 0.144F;
    }

    /** Возвращает коэффициент уменьшения ладов. */

    public float getFretStep()
    {
        return mFretStep;
    }

    /** Возвращает размер нулевого лада. */

    public float getFretSize()
    {
        return mFretSize;
    }

    /** Возвращает смещение до нулевого лада. */

    public float getFretPadding()
    {
        return mFretPadding;
    }

    /** Возвращает шаг между струнами. */

    public float getStringStep()
    {
        return mStringStep;
    }
}
