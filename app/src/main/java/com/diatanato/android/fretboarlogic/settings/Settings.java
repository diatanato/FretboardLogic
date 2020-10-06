package com.diatanato.android.fretboarlogic.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import static com.diatanato.android.fretboarlogic.Octave.*;

public class Settings implements OnSharedPreferenceChangeListener
{
    public static final String KEY_PREFERENCE_ALTERATION = "alteration";
    public static final String KEY_PREFERENCE_INSTRUMENT = "instrument";
    public static final String KEY_PREFERENCE_MAX_FRET   = "max";
    public static final String KEY_PREFERENCE_MIN_FRET   = "min";
    public static final String KEY_PREFERENCE_STRING     = "string";
    public static final String KEY_PREFERENCE_REVERSE    = "reverse";
    public static final String KEY_PREFERENCE_SOUND      = "sound";
    public static final String KEY_PREFERENCE_TUNING     = "tuning";
    public static final String KEY_PREFERENCE_ZOOM       = "zoom";

    public Settings(Context context)
    {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);

        preference.registerOnSharedPreferenceChangeListener(this);

        //загружаем настройки
        mStrings = new ArrayList<Integer>(10);

        onSharedPreferenceChanged(preference, KEY_PREFERENCE_ZOOM);
        onSharedPreferenceChanged(preference, KEY_PREFERENCE_SOUND);
        onSharedPreferenceChanged(preference, KEY_PREFERENCE_STRING);
        onSharedPreferenceChanged(preference, KEY_PREFERENCE_TUNING);
        onSharedPreferenceChanged(preference, KEY_PREFERENCE_REVERSE);
        onSharedPreferenceChanged(preference, KEY_PREFERENCE_MIN_FRET);
        onSharedPreferenceChanged(preference, KEY_PREFERENCE_MAX_FRET);
    }

    private int mInstrument;
    private int mTuning;
    private int mMin;
    private int mMax;

    private boolean mReverse;
    private boolean mSound;
    private boolean mZoom;

    private List<Integer> mStrings;

    /** Индекс текущего инструмента. */

    public int instrument() {
        return mInstrument;
    }

    /** Индекс текущего строя. */

    public int tuning() {
        return mTuning;
    }

    /** Минимальный лад для генерации случайных нот. */

    public int minFret() {
        return mMin;
    }

    /** Максимальный лад для генерации случайных нот. */

    public int maxFret() {
        return mMax;
    }

    /** Используемые знаки альтерации. */

    @NoteAlteration
    public int alteration() {
        return ALTERATION_NONE;
    }

    /** Ориентация грифа под левую/правую руку. */

    public boolean reverse() {
        return mReverse;
    }

    /** Масштабирование грифа. */

    public boolean zoom() {
        return mZoom;
    }

    /** Проигрывание нот. */

    public boolean sound() {
        return mSound;
    }

    /** Список активных струн. */

    public List<Integer> strings()
    {
        return mStrings;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences preferences, String key)
    {
        switch (key)
        {
            case KEY_PREFERENCE_REVERSE:
                mReverse = preferences.getBoolean(key, false);
                return;
            case KEY_PREFERENCE_TUNING:
                mTuning = preferences.getInt(key, 1);
                return;
            case KEY_PREFERENCE_MIN_FRET:
                mMin = preferences.getInt(key, 0);
                return;
            case KEY_PREFERENCE_MAX_FRET:
                mMax = preferences.getInt(key, 3);
                return;
            case KEY_PREFERENCE_ZOOM:
                mZoom = preferences.getBoolean(key, false);
                return;
            case KEY_PREFERENCE_SOUND:
                mSound = preferences.getBoolean(key, true);
                return;
        }
        if (key.contains(KEY_PREFERENCE_STRING))
        {
            mStrings.clear();

            //TODO: количество струн
            for (int i = 0; i < 6; i++)
            {
                if (preferences.getBoolean(Settings.KEY_PREFERENCE_STRING + i, true)) {
                    mStrings.add(i);
                }
            }
        }
    }
}
