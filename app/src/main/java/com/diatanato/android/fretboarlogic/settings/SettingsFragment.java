package com.diatanato.android.fretboarlogic.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;

import com.diatanato.android.fretboarlogic.R;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
{
    public static final String KEY_PREFERENCE_ALTERATION = "alteration";
    public static final String KEY_PREFERENCE_INSTRUMENT = "instrument";


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        SharedPreferences preferences = getPreferenceScreen().getSharedPreferences();

        onSharedPreferenceChanged(preferences, KEY_PREFERENCE_ALTERATION);
        onSharedPreferenceChanged(preferences, KEY_PREFERENCE_INSTRUMENT);
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
        if (key.equals(KEY_PREFERENCE_ALTERATION))
        {
            ListPreference preference = (ListPreference)findPreference(key);
            preference.setSummary(preference.getEntry());
        }
        if (key.equals(KEY_PREFERENCE_INSTRUMENT))
        {
            for (int i = 6; i < 10; i++)
            {
                //findPreference(key).;
            }
        }
    }
}
