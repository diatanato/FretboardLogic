package com.diatanato.android.fretboarlogic.dagger.modules;

import android.content.Context;

import com.diatanato.android.fretboarlogic.dagger.qualifiers.ApplicationContext;
import com.diatanato.android.fretboarlogic.settings.Settings;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsModule
{
    private final Settings mSettings;

    public SettingsModule(@ApplicationContext Context context){
        mSettings = new Settings(context);
    }

    @Provides
    @Singleton
    Settings provideSettings() {
        return mSettings;
    }
}
