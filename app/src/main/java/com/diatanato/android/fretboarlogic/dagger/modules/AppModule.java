package com.diatanato.android.fretboarlogic.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.diatanato.android.fretboarlogic.dagger.qualifiers.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule
{
    private final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    static Resources provideResources(@ApplicationContext Context context) {
        return context.getResources();
    }
}
