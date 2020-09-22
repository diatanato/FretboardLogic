package com.diatanato.android.fretboarlogic.instruments.guitar;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.fretboard.FretboardNote;

public class GuitarPlayer
{
    private Context       mContext;
    private MediaPlayer   mPlayer;
    private FretboardNote mNote;

    public GuitarPlayer(Context context) {
        mContext = context;
    }

    /** Проигрыает указанную ноту на гитаре. */

    public void play(FretboardNote note)
    {
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
        }
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            mPlayer.seekTo(0);
        }
        if (!note.equals(mNote))
        {
            try
            {
                mNote = note;

                int id = mContext.getResources().getIdentifier(
                    Octave.getNoteName(
                        mNote.getNoteIndex(),
                        mNote.getOctave(),
                        Octave.ALTERATION_NONE)
                    .toLowerCase() +
                    mNote.getString(),
                    "raw",
                    mContext.getPackageName());

                AssetFileDescriptor file = mContext.getResources().openRawResourceFd(id);

                mPlayer.reset();
                mPlayer.setDataSource(
                    file.getFileDescriptor(),
                    file.getStartOffset(),
                    file.getLength());
                mPlayer.prepare();

                file.close();
            }
            catch (Exception ignore) {}
        }
        mPlayer.start();
    }

    /** Освобождаем медиаплейер. */

    public void release()
    {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
        mPlayer = null;
    }
}