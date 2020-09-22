package com.diatanato.android.fretboarlogic.settings;

import android.os.Bundle;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.R;
import com.diatanato.android.fretboarlogic.database.AppDatabase;
import com.diatanato.android.fretboarlogic.database.entities.Tuning;
import com.diatanato.android.fretboarlogic.fretboard.FretboardNote;
import com.diatanato.android.fretboarlogic.instruments.guitar.GuitarPlayer;
import com.diatanato.android.fretboarlogic.settings.preferences.StringPreference;

public class SettingsInstrumentFragment extends PreferenceFragment
{
    GuitarPlayer       mPlayer;
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

        mPlayer    = new GuitarPlayer(getContext());
        mCategory = (PreferenceCategory)findPreference("category_strings");

        //TODO: индекс строя из настроек */
        loadTuning(1);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mPlayer.release();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        for (Tuning tuning : AppDatabase.getInstance(getContext()).getTuningDao().getAll())
        {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < tuning.strings.size(); i++)
            {
                builder.append(Octave.getNoteName(Octave.getInstance().getNote(tuning.strings.get(i)), Octave.ALTERATION_FLAT));
            }
            menu.add(Menu.NONE, tuning.id, Menu.NONE, builder.toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() < 100)
        {
            loadTuning(item.getItemId());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Возвращает строй в виде строки. */

    public String getTuningString()
    {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < mCategory.getPreferenceCount(); i++)
        {
            StringPreference preference = (StringPreference)mCategory.getPreference(i);
            FretboardNote    note       = preference.getNote();

            builder.append(Octave.getNoteName(note.getNoteIndex(), note.getAlteration()));
        }
        return builder.toString();
    }

    /** Загружает настройки указанного строя. */

    private void loadTuning(int index)
    {
        mCategory.removeAll();

        Tuning tuning = AppDatabase.getInstance(getContext()).getTuningDao().getTuning(index);

        for (int i = 0; i < tuning.strings.size(); i++)
        {
            Integer code = tuning.strings.get(i);
            String  key  = Settings.KEY_PREFERENCE_STRING + i;

            //TODO: берем ALTERATION из настроек
            FretboardNote note = new FretboardNote(
                Octave.getInstance().getNote(code),
                Octave.getInstance().getOctave(code),
                Octave.ALTERATION_NONE,
                i,
                0
            );
            StringPreference preference = new StringPreference(getContext());

            preference.setKey(key);
            preference.setNote(note);
            preference.setPlayer(mPlayer);

            mCategory.addPreference(preference);
        }
    }
}
