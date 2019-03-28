package com.diatanato.android.fretboarlogic;

import com.diatanato.android.fretboarlogic.Octave.NoteIndex;

public class Note
{
    private int mNote;
    private int mOctave;

    public Note(@NoteIndex int note, int octave)
    {
        mNote   = note;
        mOctave = octave;
    }

    /** Возвращает индекс ноты. */

    @NoteIndex
    public int getNoteIndex()
    {
        return mNote;
    }

    /** Возвращает индекс октавы. */

    public int getOctave()
    {
        return mOctave;
    }
}
