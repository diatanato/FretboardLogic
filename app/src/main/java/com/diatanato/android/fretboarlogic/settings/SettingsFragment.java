package com.diatanato.android.fretboarlogic.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.diatanato.android.fretboarlogic.R;

public class SettingsFragment extends PreferenceFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
