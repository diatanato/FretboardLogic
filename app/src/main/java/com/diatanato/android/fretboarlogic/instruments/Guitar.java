package com.diatanato.android.fretboarlogic.instruments;

import android.content.Context;
import androidx.core.content.ContextCompat;

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
            new Note(Note.E, Octave.OCTAVE_4),
            new Note(Note.B, Octave.OCTAVE_3),
            new Note(Note.G, Octave.OCTAVE_3),
            new Note(Note.D, Octave.OCTAVE_3),
            new Note(Note.A, Octave.OCTAVE_2),
            new Note(Note.E, Octave.OCTAVE_2)
        );
        mFretboard = new Fretboard(
            ContextCompat.getDrawable(context, R.drawable.fretboard_guitar),
            new FretboardSpecs(),
            0,
            24);
    }
}