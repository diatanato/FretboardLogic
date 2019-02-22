package com.diatanato.android.fretboarlogic;

import static com.diatanato.android.fretboarlogic.Octave.NoteAlteration;
import static com.diatanato.android.fretboarlogic.Octave.NoteIndex;

public class FretboardNote
{
    private final int mNote;
    private final int mAlteration;
    private final int mFret;
    private final int mString;

    public FretboardNote(@NoteIndex int note, @NoteAlteration int alteration, int fret, int string)
    {
        mNote       = note;
        mAlteration = alteration;
        mFret       = fret;
        mString     = string;
    }

    /** Возвращает индекс ноты. */

    @NoteIndex
    public int getNote()
    {
        return mNote;
    }

    /** Возвращает альтерацию ноты. */

    @NoteAlteration
    public int getAlteration()
    {
        return mAlteration;
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
