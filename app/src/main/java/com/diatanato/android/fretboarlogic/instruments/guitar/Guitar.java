package com.diatanato.android.fretboarlogic.instruments.guitar;

import android.content.Context;
import androidx.core.content.ContextCompat;

import com.diatanato.android.fretboarlogic.fretboard.Fretboard;
import com.diatanato.android.fretboarlogic.fretboard.FretboardSpecs;
import com.diatanato.android.fretboarlogic.instruments.Instrument;
import com.diatanato.android.fretboarlogic.Note;
import com.diatanato.android.fretboarlogic.R;

public class Guitar extends Instrument
{
    public Guitar(Context context, Note[] tuning)
    {
        super(tuning);
        mFretboard = new Fretboard(
            ContextCompat.getDrawable(context, R.drawable.fretboard_guitar),
            new FretboardSpecs(),
            0,
            24);
    }
}