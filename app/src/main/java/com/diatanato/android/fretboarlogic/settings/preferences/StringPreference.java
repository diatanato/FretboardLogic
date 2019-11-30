package com.diatanato.android.fretboarlogic.settings.preferences;

import android.content.Context;
import android.media.MediaPlayer;
import android.preference.TwoStatePreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;

import com.diatanato.android.fretboarlogic.Note;
import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.Octave.NoteIndex;
import com.diatanato.android.fretboarlogic.R;


public class StringPreference extends TwoStatePreference
{
    private Note        mNote;
    private MediaPlayer mPlayer;

    public StringPreference(Context context)
    {
        this(context, null);
    }

    public StringPreference(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
        setLayoutResource(R.layout.string_preference);
    }

    public StringPreference(Context context, AttributeSet attrs, int defStyleAttr)
    {
        this(context, attrs, defStyleAttr, 0);
    }

    public StringPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void notifyChanged()
    {
        //Чинит анимацию переключателя.
    }

    @Override
    protected void onBindView(View view)
    {
        super.onBindView(view);

        view.findViewById(R.id.play).setOnClickListener(v -> mPlayer.start());
        view.findViewById(R.id.string).getLayoutParams().height = 2 * Integer.parseInt(getTitle().toString());

        Switch button = view.findViewById(R.id.button);

        button.setChecked(isChecked());
        button.setOnCheckedChangeListener((v, checked) ->
        {
            setChecked(checked);
        });
    }

    public Note getNote()
    {
        return mNote;
    }

    /** Устанавливаем выбранную ноту */

    public void setNote(@NoteIndex int note, int octave)
    {
        Context context = getContext();

        //TODO: получаем ресурс по ноте и октаве
        //context.getResources().getIdentifier("A1", "raw", context.getPackageName());

        mNote   = new Note(note, octave);
        mPlayer = MediaPlayer.create(context, R.raw.a1);

        setSummary(Octave.getInstance().getNoteName(mNote.getNoteIndex(), mNote.getOctave(), Octave.ALTERATION_NONE));
    }
}