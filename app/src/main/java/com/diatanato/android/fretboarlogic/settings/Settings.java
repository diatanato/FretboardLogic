package com.diatanato.android.fretboarlogic.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

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

        onSharedPreferenceChanged(preference, KEY_PREFERENCE_REVERSE);
        onSharedPreferenceChanged(preference, KEY_PREFERENCE_MIN_FRET);
        onSharedPreferenceChanged(preference, KEY_PREFERENCE_MAX_FRET);
    }

    /** Индекс текущего инструмента. */

    public int instrument()
    {
        return 0;
    }

    /** Минимальный лад для генерации случайных нот. */

    public int minFret()
    {
        return mMin;
    }
    private int mMin;

    /** Максимальный лад для генерации случайных нот. */

    public int maxFret()
    {
        return mMax;
    }
    private int mMax;

    /** Используемые знаки альтерации. */

    @NoteAlteration
    public int alteration()
    {
        return ALTERATION_NONE;
    }

    /** Возможность отображения нот на струне. */

    public boolean isStringActivated(int string)
    {
        return true;
    }

    /** Ориентация грифа под левую/правую руку. */

    public boolean reverse()
    {
        return mReverse;
    }
    private boolean mReverse;

    /** Масштабирование грифа. */

    public boolean zoom()
    {
        return mZoom;
    }
    private boolean mZoom;

    /** Проигрывание нот. */

    public boolean sound()
    {
        return false;
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences preferences, String key)
    {
        if (key.equals(KEY_PREFERENCE_REVERSE))
        {
            mReverse = preferences.getBoolean(key, false);
        }
        if (key.equals(KEY_PREFERENCE_MIN_FRET))
        {
            mMin = preferences.getInt(key, 0);
        }
        if (key.equals(KEY_PREFERENCE_MAX_FRET))
        {
            mMax = preferences.getInt(key, 3);
        }
        if (key.equals(KEY_PREFERENCE_ZOOM))
        {
            mZoom = preferences.getBoolean(key, false);
        }
    }
}
