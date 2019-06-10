package com.diatanato.android.fretboarlogic.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;

import com.diatanato.android.fretboarlogic.AppSettings;
import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.R;
import com.diatanato.android.fretboarlogic.settings.preferences.StringPreference;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        SharedPreferences preferences = getPreferenceScreen().getSharedPreferences();

        onSharedPreferenceChanged(preferences, AppSettings.KEY_PREFERENCE_ALTERATION);
        onSharedPreferenceChanged(preferences, AppSettings.KEY_PREFERENCE_TUNINGS);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause()
    {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences preferences, String key)
    {
        if (key.equals(AppSettings.KEY_PREFERENCE_ALTERATION))
        {
            ListPreference preference = (ListPreference)findPreference(key);
            preference.setSummary(preference.getEntry());
        }
        if (key.equals(AppSettings.KEY_PREFERENCE_TUNINGS))
        {
            //TODO: берем ALTERATION из настроек
            //TODO: берем количество струн из инструмента

            StringBuilder builder = new StringBuilder(6);

            for (int i = 0; i < 6; i++)
            {
                StringPreference preference = (StringPreference)findPreference("string" + i);
                builder.append(Octave.getInstance().getNoteName(preference.getNote().getNoteIndex(), Octave.ALTERATION_FLAT));
            }
            findPreference(key).setSummary(builder.toString());
        }
    }
}
