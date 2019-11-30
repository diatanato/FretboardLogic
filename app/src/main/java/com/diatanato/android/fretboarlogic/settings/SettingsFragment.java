package com.diatanato.android.fretboarlogic.settings;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.diatanato.android.fretboarlogic.R;
import com.diatanato.android.fretboarlogic.settings.preferences.FretPreference;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
{
    public static final int SETTINGS_TUNING_REQUEST = 1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        findPreference(Settings.KEY_PREFERENCE_INSTRUMENT).setOnPreferenceClickListener(preference ->
        {
            Intent intent = new Intent(getActivity(), SettingsInstrumentActivity.class);
            startActivityForResult(intent, SETTINGS_TUNING_REQUEST);
            return true;
        });
        SharedPreferences preferences = getPreferenceScreen().getSharedPreferences();

        onSharedPreferenceChanged(preferences, Settings.KEY_PREFERENCE_MIN_FRET);
        onSharedPreferenceChanged(preferences, Settings.KEY_PREFERENCE_MAX_FRET);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == SETTINGS_TUNING_REQUEST)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                findPreference(Settings.KEY_PREFERENCE_INSTRUMENT).setSummary(data.getStringExtra(SettingsInstrumentActivity.EXTRA_TUNING));
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences preferences, String key)
    {
        if (key.equals(Settings.KEY_PREFERENCE_MIN_FRET))
        {
            ((FretPreference)findPreference(Settings.KEY_PREFERENCE_MAX_FRET)).setMin(preferences.getInt(key, 0));
        }
        if (key.equals(Settings.KEY_PREFERENCE_MAX_FRET))
        {
            ((FretPreference)findPreference(Settings.KEY_PREFERENCE_MIN_FRET)).setMax(preferences.getInt(key, 0));
        }
    }
}
