package com.diatanato.android.fretboarlogic.instruments;

import com.diatanato.android.fretboarlogic.AppSettings;
import com.diatanato.android.fretboarlogic.FretboardNote;
import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.Octave.NoteIndex;

import java.util.List;
import java.util.Random;

public abstract class Instrument
{
    private static Random mRandom = new Random();
    private static Octave mOctave = Octave.getInstance();

    protected List<InstrumentString> mTuning;

    //TODO: изображение грифа
    //TODO: опорные точки изображения, для правильного расчета позиции FretboarPoint

    /** Генератор случайных чисел в указанных пределах. */

    private static int getRandom(int min, int max)
    {
        return mRandom.nextInt(max - min) + min;
    }

    /** Получаем индекс ноты на струне с учетом настройки инструмента. */

    @NoteIndex
    public int getNote(int string, int fret)
    {
        if (string < mTuning.size())
        {
            return mOctave.getIntervalNote(mTuning.get(string).getNote(), fret) ;
        }
        throw new IllegalArgumentException();
    }

    /** Получаем случайную ноту на грифе. */

    public FretboardNote getRandomNote(AppSettings settings)
    {
        int string;
        int fret;
        int note;

        //TODO: игнорировать отключенные струны снизу и сверху. в идеале игнорировать все отключенные струны

        while (true)
        {
            //случайным образом выбираем струну
            string = getRandom(0, mTuning.size());

            //проверяем, что струна включена в настройках
            if (mTuning.get(string).isActivated())
            {
                fret = getRandom(
                    Math.max(getMinFret(), settings.minFret()),
                    Math.min(getMaxFret(), settings.maxFret()) + 1);

                note = getNote(string, fret);

                //проверяем, что выбранная нота доступна
                if (mOctave.getNotes(settings.alteration()).contains(note))
                {
                    return new FretboardNote(note, fret, string);
                }
            }
        }
    }

    public abstract int getMinFret();

    public abstract int getMaxFret();
}
