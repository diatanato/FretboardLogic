package com.diatanato.android.fretboarlogic;

import com.diatanato.android.fretboarlogic.Octave.*;

public class Note
{
    private int mNote;
    private int mOctave;

    /** Инициализирует ноту по строковому значению. */

    public Note(String value)
    {

    }

    /** Инициализирует ноту по индексу и октаве. */

    public Note(@NoteIndex int note, @OctaveIndex int octave)
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

    @OctaveIndex
    public int getOctave()
    {
        return mOctave;
    }
}
