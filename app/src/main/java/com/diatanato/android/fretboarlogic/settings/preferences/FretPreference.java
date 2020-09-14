package com.diatanato.android.fretboarlogic.settings.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;


public class FretPreference extends DialogPreference
{
    private int mMin = 0;
    private int mMax = 24;

    private int mNumber = 0;

    private NumberPicker mPicker;

    public FretPreference(Context context)
    {
        this(context, null, 0);
    }

    public FretPreference(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public FretPreference(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);
    }

    @Override
    protected void onBindView(View view)
    {
        super.onBindView(view);

        TextView title = view.findViewById(android.R.id.title);

        if (title != null)
        {
            title.setSingleLine(true);
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        }
    }

    @Override
    protected View onCreateDialogView()
    {
        mPicker = new NumberPicker(getContext());

        mPicker.setMinValue(mMin);
        mPicker.setMaxValue(mMax);
        mPicker.setValue(mNumber);
        mPicker.setWrapSelectorWheel(false);

        return mPicker;
    }

    @Override
    protected void onDialogClosed(boolean positiveResult)
    {
        if (positiveResult)
        {
            mPicker.clearFocus();
            setValue(mPicker.getValue());
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index)
    {
        return a.getInt(index, 0);
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue)
    {
        setValue(restoreValue ? getPersistedInt(mNumber) : (int)defaultValue);
    }

    public void setValue(int value)
    {
        if (shouldPersist())
        {
            persistInt(value);
        }
        if (value != mNumber)
        {
            mNumber = value;
            notifyChanged();
        }
        setSummary(Integer.toString(mNumber));
    }

    /** Устанавливает минимальное значение. */

    public void setMin(int value)
    {
        mMin = Math.min(value, mMax);
    }

    /** Устанавливает максимальное значение. */

    public void setMax(int value)
    {
        mMax = Math.max(value, mMin);
    }
}