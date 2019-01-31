package com.diatanato.android.fretboarlogic.instruments;

import com.diatanato.android.fretboarlogic.Octave;

import java.util.Arrays;

public class Guitar extends Instrument
{
    public Guitar()
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
    }
}
