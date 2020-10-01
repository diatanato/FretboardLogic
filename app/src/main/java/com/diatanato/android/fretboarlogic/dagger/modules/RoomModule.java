package com.diatanato.android.fretboarlogic.dagger.modules;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.diatanato.android.fretboarlogic.Note;
import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.dagger.qualifiers.ApplicationContext;
import com.diatanato.android.fretboarlogic.database.AppDatabase;
import com.diatanato.android.fretboarlogic.database.dao.TuningDao;
import com.diatanato.android.fretboarlogic.database.entities.Tuning;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule
{
    private AppDatabase mDatabase;

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

    public RoomModule(@ApplicationContext Context context)
    {
        mDatabase =
            Room.databaseBuilder(context, AppDatabase.class, "tuning-database.db")
                .allowMainThreadQueries()
                .addCallback(new RoomDatabase.Callback()
                {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db)
                    {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(() ->
                        {
                            mDatabase.getTuningDao().insert(TUNINGS);
                        });
                    }
                })
                .build();
    }

    @Provides
    @Singleton
    AppDatabase getDatabase() {
        return mDatabase;
    }

    @Provides
    @Singleton
    TuningDao provideThuningDao(AppDatabase database) {
        return database.getTuningDao();
    }
}
