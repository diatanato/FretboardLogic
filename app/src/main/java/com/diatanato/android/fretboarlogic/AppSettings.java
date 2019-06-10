package com.diatanato.android.fretboarlogic;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

import static com.diatanato.android.fretboarlogic.Octave.*;

public class AppSettings implements OnSharedPreferenceChangeListener
{
    public static final String KEY_PREFERENCE_ALTERATION = "alteration";
    public static final String KEY_PREFERENCE_INSTRUMENT = "instrument";
    public static final String KEY_PREFERENCE_MAX_FRET   = "max_fret";
    public static final String KEY_PREFERENCE_MIN_FRET   = "min_fret";
    public static final String KEY_PREFERENCE_REVERSE    = "reverse";
    public static final String KEY_PREFERENCE_SOUND      = "sound";
    public static final String KEY_PREFERENCE_TUNINGS    = "tunings";
    public static final String KEY_PREFERENCE_ZOOM       = "zoom";

    public AppSettings(Context context)
    {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);

        preference.registerOnSharedPreferenceChangeListener(this);

        //загружаем настройки

        onSharedPreferenceChanged(preference, KEY_PREFERENCE_ALTERATION);
        onSharedPreferenceChanged(preference, KEY_PREFERENCE_TUNINGS);
    }

    /** Индекс текущего инструмента. */

    public int instrument()
    {
        return 0;
    }

    /** Минимальный лад для генерации случайных нот. */

    public int minFret()
    {
        return 0;
    }

    /** Максимальный лад для генерации случайных нот. */

    public int maxFret()
    {
        return 24;
    }

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
        return true;
    }

    /** Масштабирование грифа. */

    public boolean zoom()
    {
        return true;
    }

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
            //reverse = preferences.getBoolean(key, false);
        }
        if (key.equals(KEY_PREFERENCE_ZOOM))
        {
            //zoom = preferences.getBoolean(key, false);
        }
    }
}
