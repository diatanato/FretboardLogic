package com.diatanato.android.fretboarlogic;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.diatanato.android.fretboarlogic.dagger.ActivityComponent;

public abstract class InjectionActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        onInject(((FretboardLogicApplication)getApplication()).component.push(/*new ActivityModule(this)*/));
    }

    protected void onInject(ActivityComponent component) {
    }
}

