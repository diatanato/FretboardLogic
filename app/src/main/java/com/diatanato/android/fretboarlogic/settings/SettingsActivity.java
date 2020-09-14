package com.diatanato.android.fretboarlogic.settings;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.diatanato.android.fretboarlogic.R;

public class SettingsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction()
            .replace(R.id.container, new SettingsFragment())
            .commit();
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}
