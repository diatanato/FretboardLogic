package com.diatanato.android.fretboarlogic;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;

import com.diatanato.android.fretboarlogic.Note.NoteIndex;

import static com.diatanato.android.fretboarlogic.Note.*;

public class Octave
{
    private static Octave INSTANCE;

    private final List<Integer> mNotes;
    private final List<Integer> mRootNotes;

    @IntDef({OCTAVE_1, OCTAVE_2, OCTAVE_3, OCTAVE_4})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OctaveIndex { }

    public final static int OCTAVE_1 = 1;
    public final static int OCTAVE_2 = 2;
    public final static int OCTAVE_3 = 3;
    public final static int OCTAVE_4 = 4;

    @IntDef({ALTERATION_NONE, ALTERATION_SHARP, ALTERATION_FLAT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NoteAlteration { }

    public final static int ALTERATION_NONE  = 0;
    public final static int ALTERATION_SHARP = 1;
    public final static int ALTERATION_FLAT  = 2;

    public Octave()
    {
        mNotes     = Arrays.asList(C, CD, D, DE, E, F, FG, G, GA, A, AB, B);
        mRootNotes = Arrays.asList(C, D, E, F, G, A, B);
    }

    /** Узнаем ноту по ее коду. */

    @NoteIndex
    public final int getNote(int code)
    {
        return code % mNotes.size();
    }

    /** Узнаем ноту через указанный интервал (смещение). */

    @NoteIndex
    public final int getIntervalNote(@NoteIndex int note, int interval)
    {
        return getNote(note + interval);
    }

    /** Узнаем октаву по коду ноты. */

    @OctaveIndex
    public final int getOctave(int code)
    {
        return code / mNotes.size();
    }

    /** Узнаем октаву через указанный интервал (смещение). */

    @OctaveIndex
    public final int getIntervalOctave(@NoteIndex int note, @OctaveIndex int octave, int interval)
    {
        return octave + getOctave(note + interval);
    }

    /** Возвращает код ноты */

    public final int getNoteCode(@NoteIndex int note, @OctaveIndex int octave)
    {
        return note + octave * mNotes.size();
    }

    /** Основные ноты и полутона с указанными знаками альтерации. */

    @NonNull
    @NoteAlteration
    public final List<Integer> getNotes(@NoteAlteration int alteration)
    {
        switch (alteration)
        {
            case ALTERATION_SHARP:
            case ALTERATION_FLAT:
                return getNotes();
            case ALTERATION_NONE:
                return getRootNotes();
        }
        throw new IllegalArgumentException();
    }

    /** Основные ноты и полутона. */

    @NonNull
    @NoteAlteration
    public final List<Integer> getNotes()
    {
        return mNotes;
    }

    /** Основные ноты. */

    @NonNull
    @NoteAlteration
    public final List<Integer> getRootNotes()
    {
        return mRootNotes;
    }

    /** Текстовое написание ноты с учетом альтерации. */

    @NonNull
    public static String getNoteName(@NoteIndex int note, @NoteAlteration int alteration)
    {
        switch (alteration)
        {
            case ALTERATION_SHARP:
                return Note.getNoteNameSharp(note);
            case ALTERATION_FLAT:
                return Note.getNoteNameFlat(note);
            case ALTERATION_NONE:
                return Note.getRootNoteName(note);
        }
        throw new IllegalArgumentException();
    }

    /** Текстовое написание ноты с учетом альтерации и октавой. */

    @NonNull
    public static String getNoteName(@NoteIndex int note, @OctaveIndex int octave, @NoteAlteration int alteration)
    {
        String name = getNoteName(note, alteration);

        switch (octave)
        {
            case OCTAVE_1: return name + "1";
            case OCTAVE_2: return name + "2";
            case OCTAVE_3: return name + "3";
            case OCTAVE_4: return name + "4";
        }
        throw new IllegalArgumentException();
    }

    public synchronized static Octave getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new Octave();
        }
        return INSTANCE;
    }
}
