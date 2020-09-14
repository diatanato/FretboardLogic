package com.diatanato.android.fretboarlogic;

import android.annotation.SuppressLint;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.UnsupportedOperationException;

import com.diatanato.android.fretboarlogic.Octave.OctaveIndex;

public class Note
{
    private int mNote;
    private int mOctave;

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

    /** Текстовое написание основных нот и полутонов с диез. */

    @NonNull
    @SuppressLint("SwitchIntDef")
    public static String getNoteNameSharp(@NoteIndex int note)
    {
        switch (note)
        {
            case Note.CD: return "C#";
            case Note.DE: return "D#";
            case Note.FG: return "F#";
            case Note.GA: return "G#";
            case Note.AB: return "A#";
        }
        return getRootNoteName(note);
    }

    /** Текстовое написание основных нот и полутонов с бемоль. */

    @NonNull
    @SuppressLint("SwitchIntDef")
    public static String getNoteNameFlat(@NoteIndex int note)
    {
        switch (note)
        {
            case Note.CD: return "Db";
            case Note.DE: return "Eb";
            case Note.FG: return "Gb";
            case Note.GA: return "Ab";
            case Note.AB: return "Bb";
        }
        return getRootNoteName(note);
    }

    /** Текстовое написание основных нот. */

    @NonNull
    @SuppressLint("SwitchIntDef")
    public static String getRootNoteName(@NoteIndex int note)
    {
        switch (note)
        {
            case  Note.C: return "C";
            case  Note.D: return "D";
            case  Note.E: return "E";
            case  Note.F: return "F";
            case  Note.G: return "G";
            case  Note.A: return "A";
            case  Note.B: return "B";
        }
        throw new IllegalArgumentException();
    }

    /** Инициализирует ноту по строковому значению. */

    public Note(String value)
    {
        throw new UnsupportedOperationException();
    }

    /** Инициализирует ноту по ее коду. */

    public Note(int code)
    {
        this(Octave.getInstance().getNote(code), Octave.getInstance().getOctave(code));
    }

    /** Инициализирует ноту по индексу и октаве. */

    public Note(@NoteIndex int note, @OctaveIndex int octave)
    {
        mNote   = note;
        mOctave = octave;
    }

    /** Возвращает индекс ноты. */

    @NoteIndex
    public int getNoteIndex()
    {
        return mNote;
    }

    /** Возвращает индекс октавы. */

    @OctaveIndex
    public int getOctave()
    {
        return mOctave;
    }
}
