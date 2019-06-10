package com.diatanato.android.fretboarlogic.settings.preferences;

import android.content.Context;
import android.media.MediaPlayer;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;

import com.diatanato.android.fretboarlogic.Note;
import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.Octave.NoteIndex;
import com.diatanato.android.fretboarlogic.R;


public class StringPreference extends DialogPreference
{
    private Note mNote;
    private MediaPlayer mPlayer;

    public StringPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        setNote(Octave.A, 1);
    }

    public StringPreference(Context context, AttributeSet attrs, int defStyleAttr)
    {
        this(context, attrs, defStyleAttr, 0);
    }

    public StringPreference(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
        setLayoutResource(R.layout.string_preference);
    }

    @Override
    protected void onBindView(View view)
    {
        super.onBindView(view);
        view.findViewById(R.id.string).setOnClickListener(v -> mPlayer.start());
    }

    public Note getNote()
    {
        return mNote;
    }

    /** Устанавливаем выбранную ноту */

    private void setNote(@NoteIndex int note, int octave)
    {
        Context context = getContext();

        //TODO: получаем ресурс по ноте и октаве
        //context.getResources().getIdentifier("A1", "raw", context.getPackageName());

        mNote   = new Note(note, octave);
        mPlayer = MediaPlayer.create(context, R.raw.a1);
    }
}