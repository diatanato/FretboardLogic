package com.diatanato.android.fretboarlogic;

import static com.diatanato.android.fretboarlogic.Octave.NoteIndex;

public class FretboardNote
{
    private final int mString;
    private final int mFret;
    private final int mNote;

    public FretboardNote(int string, int fret, @NoteIndex int note)
    {
        mString = string;
        mFret   = fret;
        mNote   = note;
    }

    public int getString()
    {
        return mString;
    }

    public int getFret()
    {
        return mFret;
    }

    @NoteIndex
    public int getNote()
    {
        return mNote;
    }
}
