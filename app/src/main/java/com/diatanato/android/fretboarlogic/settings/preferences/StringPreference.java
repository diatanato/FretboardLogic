package com.diatanato.android.fretboarlogic.settings.preferences;

import android.content.Context;
import android.preference.TwoStatePreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;

import com.diatanato.android.fretboarlogic.Octave;
import com.diatanato.android.fretboarlogic.R;
import com.diatanato.android.fretboarlogic.fretboard.FretboardNote;
import com.diatanato.android.fretboarlogic.instruments.guitar.GuitarPlayer;

public class StringPreference extends TwoStatePreference
{
    private GuitarPlayer mPlayer;

    private FretboardNote mNote;

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
        //Чинит анимацию переключателя
    }

    @Override
    protected void onBindView(View view)
    {
        super.onBindView(view);

        view.findViewById(R.id.string).getLayoutParams().height = 2 * mNote.getString();
        view.findViewById(R.id.play).setOnClickListener(v -> {
            mPlayer.play(mNote);
        });

        Switch button = view.findViewById(R.id.button);

        button.setChecked(isChecked());
        button.setOnCheckedChangeListener((v, checked) -> {
            setChecked(checked);
        });
    }

    public FretboardNote getNote() {
        return mNote;
    }

    /** Устанавливает выбранную ноту */

    public void setNote(FretboardNote note)
    {
        mNote = note;

        setTitle(Integer.toString(mNote.getString()));
        setSummary(Octave.getNoteName(mNote.getNoteIndex(), mNote.getOctave(), Octave.ALTERATION_NONE));
    }

    /** Устанавливает проигрыватель нот */

    public void setPlayer(GuitarPlayer player){
        mPlayer = player;
    }
}