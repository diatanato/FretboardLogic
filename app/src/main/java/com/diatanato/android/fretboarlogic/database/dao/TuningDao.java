package com.diatanato.android.fretboarlogic.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.diatanato.android.fretboarlogic.database.entities.Tuning;

import java.util.List;

@Dao
public abstract class TuningDao implements BaseDao<Tuning>
{
    @Query
    (
        "SELECT * " +
        "FROM Tuning"
    )
    public abstract List<Tuning> getAll();

    @Query
    (
        "SELECT * " +
        "FROM Tuning " +
        "WHERE Tuning.id = :id"
    )
    public abstract Tuning getTuning(int id);
}
