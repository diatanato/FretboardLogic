package com.diatanato.android.fretboarlogic.fretboard;

import static com.diatanato.android.fretboarlogic.Note.NoteIndex;
import static com.diatanato.android.fretboarlogic.Octave.NoteAlteration;

public class FretboardNote
{
    private final int mNote;
    private final int mOctave;
    private final int mAlteration;
    private final int mFret;
    private final int mString;

    public FretboardNote(@NoteIndex int note, int octave, @NoteAlteration int alteration, int string, int fret)
    {
        mNote       = note;
        mOctave     = octave;
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

    /** Возвращае индекс октавы. */

    public int getOctave()
    {
        return mOctave;
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
