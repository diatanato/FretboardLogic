package com.diatanato.android.fretboarlogic;

import static com.diatanato.android.fretboarlogic.Octave.NoteIndex;

public class FretboardNote
{
    private final int mNote;
    private final int mFret;
    private final int mString;

    public FretboardNote(@NoteIndex int note, int fret, int string)
    {
        mNote   = note;
        mFret   = fret;
        mString = string;
    }

    /** Возвращает индекс ноты. */

    @NoteIndex
    public int getNote()
    {
        return mNote;
    }

    /** Возвращает индекс лада. */

    public int getFret()
    {
        return mFret;
    }

    /** Возвращает индекс струны. */

    public int getString()
    {
        return mString;
    }
}
