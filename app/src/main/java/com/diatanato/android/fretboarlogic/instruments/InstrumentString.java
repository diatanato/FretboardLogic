package com.diatanato.android.fretboarlogic.instruments;

import com.diatanato.android.fretboarlogic.Octave.NoteIndex;

public class InstrumentString
{
    private int     mNote;
    private boolean mActivated;

    public InstrumentString(@NoteIndex int note, boolean activated)
    {
        mNote      = note;
        mActivated = activated;
    }

    /** Нота открытой струны. */

    @NoteIndex
    public int getNote()
    {
        return mNote;
    }

    /** Возможность отображения нот на струне. */

    public boolean isActivated()
    {
        return mActivated;
    }
}
