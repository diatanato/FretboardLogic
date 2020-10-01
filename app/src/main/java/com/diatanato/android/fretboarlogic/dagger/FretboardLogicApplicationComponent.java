package com.diatanato.android.fretboarlogic.dagger;

import com.diatanato.android.fretboarlogic.dagger.modules.AppModule;
import com.diatanato.android.fretboarlogic.dagger.modules.PlayerModule;
import com.diatanato.android.fretboarlogic.dagger.modules.RoomModule;
import com.diatanato.android.fretboarlogic.dagger.modules.SettingsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules =
{
    AppModule.class,
    PlayerModule.class,
    RoomModule.class,
    SettingsModule.class
})
public interface FretboardLogicApplicationComponent
{
    ActivityComponent push(/*ActivityModule module*/);
}
