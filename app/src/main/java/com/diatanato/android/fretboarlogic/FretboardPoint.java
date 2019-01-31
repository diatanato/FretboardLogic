package com.diatanato.android.fretboarlogic;

import java.util.List;

public class FretboardPoint
{
    private final FretboardNote mNote;
    private final List<Integer> mColors;

    public FretboardPoint(FretboardNote note, List<Integer> colors)
    {
        mNote   = note;
        mColors = colors;
    }
}
