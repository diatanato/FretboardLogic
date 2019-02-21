package com.diatanato.android.fretboarlogic;

import static com.diatanato.android.fretboarlogic.Octave.*;

public class AppSettings
{
    /** Индекс текущего инструмента. */

    public int instrument()
    {
        return 0;
    }

    /** Минимальный лад для генерации случайных нот. */

    public int minFret()
    {
        return 0;
    }

    /** Максимальный лад для генерации случайных нот. */

    public int maxFret()
    {
        return 24;
    }

    /** Используемые знаки альтерации. */

    @NoteAlteration
    public  int alteration()
    {
        return ALTERATION_NONE;
    }

    /** Ориентация грифа под левую/правую руку. */

    public boolean reverse()
    {
        return true;
    }
}
