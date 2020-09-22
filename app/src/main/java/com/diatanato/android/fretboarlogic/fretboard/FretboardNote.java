package com.diatanato.android.fretboarlogic.fretboard;

import androidx.annotation.Nullable;

import com.diatanato.android.fretboarlogic.Note;
import com.diatanato.android.fretboarlogic.Note.NoteIndex;
import com.diatanato.android.fretboarlogic.Octave.NoteAlteration;
import com.diatanato.android.fretboarlogic.Octave.OctaveIndex;

public class FretboardNote extends Note
{
    private final int mAlteration;
    private final int mFret;
    private final int mString;

    public FretboardNote(@NoteIndex int note, @OctaveIndex int octave, @NoteAlteration int alteration, int string, int fret)
    {
        super(note, octave);

        mAlteration = alteration;
        mFret       = fret;
        mString     = string;
    }

    /** Возвращает альтерацию ноты. */

    @NoteAlteration
    public int getAlteration() {
        return mAlteration;
    }

    /** Возвращает индекс лада. */

    public int getFret() {
        return mFret;
    }

    /** Возвращает номер струны. */

    public int getString() {
        return mString + 1;
    }

    /** Возвращает индекс струны. */

    public int getStringIndex() {
        return mString;
    }

    @Override
    public boolean equals(@Nullable Object obj)
    {
        if (this != obj) {
            if (obj instanceof FretboardNote) {
                //лад и альтерация не имеют значения
                return
                    super.equals(obj) &&
                    mString == ((FretboardNote)obj).mString;
            }
            return false;
        }
        return true;
    }
}
