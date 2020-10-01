package com.diatanato.android.fretboarlogic.dagger.modules;

import android.content.Context;

import com.diatanato.android.fretboarlogic.dagger.qualifiers.ApplicationContext;
import com.diatanato.android.fretboarlogic.instruments.guitar.GuitarPlayer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerModule
{
    private GuitarPlayer mGuitar;

    public PlayerModule(@ApplicationContext Context context){
        mGuitar = new GuitarPlayer(context);
    }

    @Provides
    @Singleton
    GuitarPlayer provideGuitarPlayer() {
        return mGuitar;
    }
}
