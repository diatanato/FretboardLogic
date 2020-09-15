package com.diatanato.android.fretboarlogic.settings.preferences;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.preference.TwoStatePreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;

import com.diatanato.android.fretboarlogic.Note;
import com.diatanato.android.fretboarlogic.Note.NoteIndex;
import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.R;

public class StringPreference extends TwoStatePreference
{
    private static MediaPlayer mPlayer = new MediaPlayer();

    private Note mNote;
    private int  mIndex;

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

        view.findViewById(R.id.string).getLayoutParams().height = 2 * mIndex;
        view.findViewById(R.id.play).setOnClickListener(v ->
        {
            if (mPlayer.isPlaying()) {
                mPlayer.pause();
                mPlayer.seekTo(0);
            }
            if (mPlayer.getAudioSessionId() != mIndex){
                try {
                    int id = getContext().getResources().getIdentifier(
                        Octave.getNoteName(
                            mNote.getNoteIndex(),
                            mNote.getOctave(),
                            Octave.ALTERATION_NONE)
                        .toLowerCase() +
                        mIndex,
                        "raw",
                        getContext().getPackageName());

                    AssetFileDescriptor file = getContext().getResources().openRawResourceFd(id);

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
        });
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

    /** Устанавливает выбранную ноту */

    public void setNote(Note note)
    {
        mNote = note;
        setSummary(Octave.getNoteName(mNote.getNoteIndex(), mNote.getOctave(), Octave.ALTERATION_NONE));
    }

    /** Устанавливает номер струны */

    public void setIndex(int index)
    {
        mIndex = index;
        setTitle(Integer.toString(index));
    }
}