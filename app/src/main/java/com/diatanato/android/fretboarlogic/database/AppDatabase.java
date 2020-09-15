package com.diatanato.android.fretboarlogic.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.diatanato.android.fretboarlogic.Note;
import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.database.converter.StringsConverter;
import com.diatanato.android.fretboarlogic.database.dao.TuningDao;
import com.diatanato.android.fretboarlogic.database.entities.Tuning;

import java.util.concurrent.Executors;

@Database(entities = { Tuning.class}, exportSchema = false, version = 1)
@TypeConverters({StringsConverter.class})
public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase INSTANCE;

    private final static Tuning[] TUNINGS =
    {
        new Tuning(
            Octave.getInstance().getNoteCode(Note.E, Octave.OCTAVE_4),
            Octave.getInstance().getNoteCode(Note.B, Octave.OCTAVE_3),
            Octave.getInstance().getNoteCode(Note.G, Octave.OCTAVE_3),
            Octave.getInstance().getNoteCode(Note.D, Octave.OCTAVE_3),
            Octave.getInstance().getNoteCode(Note.A, Octave.OCTAVE_2),
            Octave.getInstance().getNoteCode(Note.E, Octave.OCTAVE_2)
        ),
        new Tuning(
            Octave.getInstance().getNoteCode(Note.E, Octave.OCTAVE_4),
            Octave.getInstance().getNoteCode(Note.B, Octave.OCTAVE_3),
            Octave.getInstance().getNoteCode(Note.G, Octave.OCTAVE_3),
            Octave.getInstance().getNoteCode(Note.D, Octave.OCTAVE_3),
            Octave.getInstance().getNoteCode(Note.A, Octave.OCTAVE_2),
            Octave.getInstance().getNoteCode(Note.D, Octave.OCTAVE_2)
        ),
        new Tuning(
            Octave.getInstance().getNoteCode(Note.D, Octave.OCTAVE_4),
            Octave.getInstance().getNoteCode(Note.A, Octave.OCTAVE_3),
            Octave.getInstance().getNoteCode(Note.F, Octave.OCTAVE_3),
            Octave.getInstance().getNoteCode(Note.C, Octave.OCTAVE_3),
            Octave.getInstance().getNoteCode(Note.G, Octave.OCTAVE_2),
            Octave.getInstance().getNoteCode(Note.C, Octave.OCTAVE_2)
        ),
        new Tuning(
            Octave.getInstance().getNoteCode(Note.E, Octave.OCTAVE_4),
            Octave.getInstance().getNoteCode(Note.B, Octave.OCTAVE_3),
            Octave.getInstance().getNoteCode(Note.G, Octave.OCTAVE_3),
            Octave.getInstance().getNoteCode(Note.D, Octave.OCTAVE_3),
            Octave.getInstance().getNoteCode(Note.A, Octave.OCTAVE_2),
            Octave.getInstance().getNoteCode(Note.E, Octave.OCTAVE_2),
            Octave.getInstance().getNoteCode(Note.B, Octave.OCTAVE_1),
            Octave.getInstance().getNoteCode(Note.G, Octave.OCTAVE_1)
        )
    };

    public abstract TuningDao getTuningDao();

    public synchronized static AppDatabase getInstance(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE =
                Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "tuning-database.db")
                    .allowMainThreadQueries()
                    .addCallback(new Callback()
                    {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db)
                        {
                            super.onCreate(db);
                            Executors.newSingleThreadExecutor().execute(() ->
                            {
                                getInstance(context).getTuningDao().insert(TUNINGS);
                            });
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }
}
