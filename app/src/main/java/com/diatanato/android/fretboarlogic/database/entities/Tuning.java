package com.diatanato.android.fretboarlogic.database.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Arrays;
import java.util.List;

@Entity
public class Tuning
{
    public Tuning()
    {
    }

    @Ignore
    public Tuning(Integer... string)
    {
        this.strings = Arrays.asList(string);
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    public List<Integer> strings;
}
