package com.diatanato.android.fretboarlogic.settings.preferences;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;

import com.diatanato.android.fretboarlogic.R;

public class StringPreference extends DialogPreference
{
    public StringPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public StringPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public StringPreference(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
        setLayoutResource(R.layout.string_preference);
    }
}