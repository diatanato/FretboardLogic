package com.diatanato.android.fretboarlogic;

import androidx.multidex.MultiDexApplication;

import com.diatanato.android.fretboarlogic.dagger.DaggerFretboardLogicApplicationComponent;
import com.diatanato.android.fretboarlogic.dagger.FretboardLogicApplicationComponent;
import com.diatanato.android.fretboarlogic.dagger.modules.AppModule;
import com.diatanato.android.fretboarlogic.dagger.modules.PlayerModule;
import com.diatanato.android.fretboarlogic.dagger.modules.RoomModule;
import com.diatanato.android.fretboarlogic.dagger.modules.SettingsModule;

public class FretboardLogicApplication extends MultiDexApplication
{
    public FretboardLogicApplicationComponent component;

    @Override
    public void onCreate()
    {
        super.onCreate();

        component =
            DaggerFretboardLogicApplicationComponent
                .builder()
                .appModule(new AppModule(this))
                .playerModule(new PlayerModule(this))
                .roomModule(new RoomModule(this))
                .settingsModule(new SettingsModule(this))
                .build();
    }
}
