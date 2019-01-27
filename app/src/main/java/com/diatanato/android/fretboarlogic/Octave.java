package com.diatanato.android.fretboarlogic;

import java.util.Arrays;
import java.util.List;

public class Octave
{
    private static Octave INSTANCE;

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

    public final List<Integer> getNotes()
    {
        return Arrays.asList(C, CD, D, DE, E, F, FG, G, GA, A, AB, B);
    }

    public final List<Integer> getRootNotes()
    {
        return Arrays.asList(C, D, E, F, G, A, B);
    }

    public final String getNoteNameSharp(int note)
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

    public final String getNoteNameFlat(int note)
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

    public final String getRootNoteName(int note)
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
        return "?";
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
