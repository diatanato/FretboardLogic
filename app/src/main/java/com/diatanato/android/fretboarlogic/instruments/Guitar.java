package com.diatanato.android.fretboarlogic.instruments;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.diatanato.android.fretboarlogic.Fretboard;
import com.diatanato.android.fretboarlogic.FretboardSpecs;
import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.R;

import java.util.Arrays;

public class Guitar extends Instrument
{
    public Guitar(Context context)
    {
        mTuning = Arrays.asList
        (
            new InstrumentString(Octave.E, true),
            new InstrumentString(Octave.B, true),
            new InstrumentString(Octave.G, true),
            new InstrumentString(Octave.D, true),
            new InstrumentString(Octave.A, true),
            new InstrumentString(Octave.E, true)
        );
        mFretboard = new Fretboard(
            ContextCompat.getDrawable(context, R.drawable.fretboard_guitar),
            new FretboardSpecs(),
            0,
            24);
    }
}
