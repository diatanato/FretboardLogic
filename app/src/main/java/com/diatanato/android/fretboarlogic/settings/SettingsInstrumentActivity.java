package com.diatanato.android.fretboarlogic.settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diatanato.android.fretboarlogic.R;

public class SettingsInstrumentActivity extends AppCompatActivity
{
    public static final String EXTRA_TUNING = "tuning";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_instrument);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction()
            .replace(R.id.container, new SettingsInstrumentFragment())
            .commit();
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed()
    {
        SettingsInstrumentFragment tuning = (SettingsInstrumentFragment) getFragmentManager().findFragmentById(R.id.container);

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TUNING, tuning.getTuningString());
        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }
}


