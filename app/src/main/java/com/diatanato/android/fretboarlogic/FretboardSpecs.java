package com.diatanato.android.fretboarlogic;

/** Пропорции элементов изображения грифа. */

public class FretboardSpecs
{
    private final float mFretStep;
    private final float mFretSize;
    private final float mFretPadding;
    private final float mStringStep;
    private final float mStringPadding;

    public FretboardSpecs()
    {
        //TODO: куда-то вынести инициализацию

        mFretStep      = 0.943F;
        mFretSize      = 0.0762F;
        mFretPadding   = 0.0170F;

        mStringStep    = 0.129F;
        mStringPadding = 0.1838F;
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

    /** Возвращает смещение до первой струны. */

    public float getStringPadding()
    {
        return mStringPadding; //TODO: одинаковое смещение сверху и снизу
    }
}
