package com.diatanato.android.fretboarlogic.dagger;

import com.diatanato.android.fretboarlogic.MainActivity;

import dagger.Subcomponent;

@Subcomponent()
public interface ActivityComponent
{
    void inject(MainActivity activity);
}
