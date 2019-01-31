package com.diatanato.android.fretboarlogic;

import android.annotation.SuppressLint;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;

public class Octave
{
    private static Octave INSTANCE;

    @IntDef({C, CD, D, DE, E, F, FG, G, GA, A, AB, B})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NoteIndex { }

    public final static int C  =  0;
    public final static int CD =  1;
    public final static int D  =  2;
    public final static int DE =  3;
    public final static int E  =  4;
    public final static int F  =  5;
    public final static int FG =  6;
    public final static int G  =  7;
    public final static int GA =  8;
    public final static int A  =  9;
    public final static int AB = 10;
    public final static int B  = 11;

    @IntDef({ALTERATION_NONE, ALTERATION_SHARP, ALTERATION_FLAT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NoteAlteration { }

    public final static int ALTERATION_NONE  = 0;
    public final static int ALTERATION_SHARP = 1;
    public final static int ALTERATION_FLAT  = 2;

    /** Узнаем ноту через указанный интервал (смещение). */

    @NoteIndex
    public final int getIntervalNote(@NoteIndex int note, int interval)
    {
        return (note + interval) % 12;
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
                return getInstance().getNotes();
            case ALTERATION_NONE:
                return getInstance().getRootNotes();
        }
        throw new IllegalArgumentException();
    }

    /** Основные ноты и полутона. */

    @NonNull
    @NoteAlteration
    public final List<Integer> getNotes()
    {
        return Arrays.asList(C, CD, D, DE, E, F, FG, G, GA, A, AB, B);
    }

    /** Основные ноты. */

    @NonNull
    @NoteAlteration
    public final List<Integer> getRootNotes()
    {
        return Arrays.asList(C, D, E, F, G, A, B);
    }

    /** Текстовое написание основных нот и полутонов в диез. */

    @NonNull
    @SuppressLint("SwitchIntDef")
    public final String getNoteNameSharp(@NoteIndex int note)
    {
        switch (note)
        {
            case CD: return "C#";
            case DE: return "D#";
            case FG: return "F#";
            case GA: return "G#";
            case AB: return "A#";
        }
        return getRootNoteName(note);
    }

    /** Текстовое написание основных нот и полутонов в бемоль. */

    @NonNull
    @SuppressLint("SwitchIntDef")
    public final String getNoteNameFlat(@NoteIndex int note)
    {
        switch (note)
        {
            case CD: return "Db";
            case DE: return "Eb";
            case FG: return "Gb";
            case GA: return "Ab";
            case AB: return "Bb";
        }
        return getRootNoteName(note);
    }

    /** Текстовое написание основных нот. */

    @NonNull
    @SuppressLint("SwitchIntDef")
    public final String getRootNoteName(@NoteIndex int note)
    {
        switch (note)
        {
            case  C: return "C";
            case  D: return "D";
            case  E: return "E";
            case  F: return "F";
            case  G: return "G";
            case  A: return "A";
            case  B: return "B";
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
