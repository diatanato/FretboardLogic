package com.diatanato.android.fretboarlogic.instruments;

import androidx.annotation.NonNull;

import com.diatanato.android.fretboarlogic.fretboard.FretboardNote;
import com.diatanato.android.fretboarlogic.fretboard.Fretboard;
import com.diatanato.android.fretboarlogic.Note;
import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.settings.Settings;

import java.util.Random;

public abstract class Instrument
{
    private final static Random mRandom = new Random();
    private final static Octave mOctave = Octave.getInstance();

    protected String     mName;
    protected Note[]     mTuning;
    protected Fretboard  mFretboard;

    public Instrument(Note[] tuning) {
        mTuning = tuning;
    }

    /** Возвращает название инструмента. */

    public String getName() {
        return mName;
    }

    /** Описание графи инструмента. */

    public Fretboard getFretboard() {
        return mFretboard;
    }

    /** Возвращает ноту на струне с учетом настройки инструмента. */

    @NonNull
    public Note getNote(int string, int fret)
    {
        if (string < mTuning.length)
        {
            Note note = mTuning[string];

            return new Note(
                mOctave.getIntervalNote(note.getNoteIndex(), fret),
                mOctave.getIntervalOctave(note.getNoteIndex(), note.getOctave(), fret)
            );
        }
        throw new IllegalArgumentException();
    }

    /** Возвращает случайную ноту на грифе. */

    public FretboardNote getRandomNote(Settings settings)
    {
        //случайным образом выбираем струну из списка доступных
        int string = settings.strings().get(getRandom(0, settings.strings().size()));

        while (true)
        {
            int fret = getRandom(
                Math.max(mFretboard.getMinFret(), settings.minFret()),
                Math.min(mFretboard.getMaxFret(), settings.maxFret()) + 1);

            Note note = getNote(string, fret);

            //проверяем, что выбранная нота доступна
            if (mOctave.getNotes(settings.alteration()).contains(note.getNoteIndex()))
            {
                return new FretboardNote(note.getNoteIndex(), note.getOctave(), settings.alteration(), string, fret);
            }
        }
    }

    /** Генератор случайных чисел в указанных пределах. */

    private static int getRandom(int min, int max) {
        return mRandom.nextInt(max - min) + min;
    }
}
