package com.diatanato.android.fretboarlogic.instruments;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.diatanato.android.fretboarlogic.fretboard.Fretboard;
import com.diatanato.android.fretboarlogic.fretboard.FretboardSpecs;
import com.diatanato.android.fretboarlogic.Note;
import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.R;

import java.util.Arrays;

public class Guitar extends Instrument
{
    public Guitar(Context context)
    {
        mTuning = Arrays.asList
        (
            new Note(Octave.E, 4),
            new Note(Octave.B, 3),
            new Note(Octave.G, 3),
            new Note(Octave.D, 3),
            new Note(Octave.A, 2),
            new Note(Octave.E, 2)
        );
        mFretboard = new Fretboard(
            ContextCompat.getDrawable(context, R.drawable.fretboard_guitar),
            new FretboardSpecs(),
            0,
            24);
    }
}
