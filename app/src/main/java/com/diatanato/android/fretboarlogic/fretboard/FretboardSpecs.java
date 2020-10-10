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
        //TODO: вынести инициализацию

        mFretStep      = 0.9430F;
        mFretSize      = 0.0720F;
        mFretPadding   = 0.0162F;
        mStringStep    = 0.1440F;
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
