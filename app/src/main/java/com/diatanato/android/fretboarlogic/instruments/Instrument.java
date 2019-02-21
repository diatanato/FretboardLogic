package com.diatanato.android.fretboarlogic.instruments;

import com.diatanato.android.fretboarlogic.AppSettings;
import com.diatanato.android.fretboarlogic.FretboardNote;
import com.diatanato.android.fretboarlogic.Fretboard;
import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.Octave.NoteIndex;

import java.util.List;
import java.util.Random;

public abstract class Instrument
{
    private final static Random mRandom = new Random();
    private final static Octave mOctave = Octave.getInstance();

    protected Fretboard mFretboard;
    protected List<InstrumentString> mTuning;

    /** Генератор случайных чисел в указанных пределах. */

    private static int getRandom(int min, int max)
    {
        return mRandom.nextInt(max - min) + min;
    }

    /** Описание графи инструмента. */

    public Fretboard getFretboard()
    {
        return mFretboard;
    }

    /** Получаем индекс ноты на струне с учетом настройки инструмента. */

    @NoteIndex
    public int getNote(int string, int fret)
    {
        if (string < mTuning.size())
        {
            return mOctave.getIntervalNote(mTuning.get(string).getNote(), fret);
        }
        throw new IllegalArgumentException();
    }

    /** Получаем случайную ноту на грифе. */

    public FretboardNote getRandomNote(AppSettings settings)
    {
        int note;
        int fret;
        int string;

        //TODO: игнорировать отключенные струны снизу и сверху. в идеале игнорировать все отключенные струны

        while (true)
        {
            //случайным образом выбираем струну
            string = getRandom(0, mTuning.size());

            //проверяем, что струна включена в настройках
            if (mTuning.get(string).isActivated())
            {
                fret = getRandom(
                    Math.max(mFretboard.getMinFret(), settings.minFret()),
                    Math.min(mFretboard.getMaxFret(), settings.maxFret()) + 1);

                note = getNote(string, fret);

                //проверяем, что выбранная нота доступна
                if (mOctave.getNotes(settings.alteration()).contains(note))
                {
                    return new FretboardNote(note, fret, string);
                }
            }
        }
    }
}
