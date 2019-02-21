package com.diatanato.android.fretboarlogic;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

public class FretboardPoint extends ViewGroup
{
    private final FretboardNote mNote;
    private       TextView      mText;

    public FretboardPoint(Context context, FretboardNote note)
    {
        super(context);

        mNote = note;
        mText = new TextView(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        if (mText != null)
        {
            //TODO: позиция текста
        }
    }
}
