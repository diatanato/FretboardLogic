package com.diatanato.android.fretboarlogic.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.R;
import com.diatanato.android.fretboarlogic.settings.preferences.StringPreference;


public class SettingsTuningFragment extends PreferenceFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_tuning);
    }

    /** Возвращает строй в виде строки. */

    public String GetTuningString()
    {
        //TODO: берем ALTERATION из настроек
        //TODO: берем количество струн из инструмента

        StringBuilder builder = new StringBuilder(6);

        for (int i = 0; i < 6; i++)
        {
            StringPreference preference = (StringPreference)findPreference(Settings.KEY_PREFERENCE_STRING + i);
            builder.append(Octave.getInstance().getNoteName(preference.getNote().getNoteIndex(), Octave.ALTERATION_FLAT));
        }
        return builder.toString();
    }
}
