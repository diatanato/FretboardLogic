package com.diatanato.android.fretboarlogic.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.view.Menu;
import android.view.MenuInflater;

import com.diatanato.android.fretboarlogic.Note;
import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.R;
import com.diatanato.android.fretboarlogic.settings.preferences.StringPreference;

public class SettingsInstrumentFragment extends PreferenceFragment
{
    PreferenceCategory mCategory;

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_instrument);

        loadTuning();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        //TODO: список предсохраненных строев

        menu.add(Menu.NONE, 101, Menu.NONE, "EADGBE");
    }

    /** Возвращает строй в виде строки. */

    public String getTuningString()
    {
        //TODO: берем ALTERATION из настроек

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < mCategory.getPreferenceCount(); i++)
        {
            StringPreference preference = (StringPreference)mCategory.getPreference(i);
            builder.append(Octave.getNoteName(preference.getNote().getNoteIndex(), Octave.ALTERATION_FLAT));
        }
        return builder.toString();
    }

    /** Загружает настройки указанного строя. */

    private void loadTuning(/* TODO: строй или индекс строя */)
    {
        mCategory = (PreferenceCategory)findPreference("category_strings");

        //TODO: берем количество струн строя
        //TODO: устанавливаем ноту

        for (int i = 0; i < 6; i++)
        {
            String key = Settings.KEY_PREFERENCE_STRING + i;

            StringPreference preference = new StringPreference(getContext());
            preference.setKey(key);
            preference.setNote(Note.A, 1);
            preference.setIndex(i + 1);

            mCategory.addPreference(preference);
        }
    }
}
