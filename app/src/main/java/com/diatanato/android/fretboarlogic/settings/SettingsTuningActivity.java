package com.diatanato.android.fretboarlogic.settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diatanato.android.fretboarlogic.R;

public class SettingsTuningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_tuning);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction()
            .replace(R.id.container, new SettingsTuningFragment())
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
        SettingsTuningFragment tuning = (SettingsTuningFragment) getFragmentManager().findFragmentById(R.id.container);

        Intent intent = new Intent();
        intent.putExtra("tuning", tuning.GetTuningString());
        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }
}


