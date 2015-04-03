package com.example.mattyice.ultibook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Matty Ice on 3/25/2015.
 */
public class NewPlayFragment extends DialogFragment {

    public static final String EXTRA_PLAY = "com.example.knerr.criminalintent.play-name";

    private Play mPlay;

    public static NewPlayFragment newInstance(){
        Bundle args = new Bundle();

        NewPlayFragment fragment = new NewPlayFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState){

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_newplay, null);

        Resources res = getResources();
        //Play Name Text
        final EditText playName = (EditText)v.findViewById(R.id.newplay_play_name);

        //Play Type Spinner
        final Spinner typeSpinner = (Spinner)v.findViewById(R.id.newplay_play_type_spinner);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, res.getStringArray(R.array.newplay_play_types));
        typeSpinner.setAdapter(typeAdapter);

        //Offensive Players Spinner
        final Spinner offensiveSpinner = (Spinner)v.findViewById(R.id.newplay_offensive_numbers_spinner);
        ArrayAdapter<String> numbersAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, res.getStringArray(R.array.newplay_number_options));
        offensiveSpinner.setAdapter(numbersAdapter);

        //Defensive Players Spinner
        final Spinner defensiveSpinner = (Spinner)v.findViewById(R.id.newplay_defensive_numbers_spinner);
        defensiveSpinner.setAdapter(numbersAdapter);

        //Cones Spinner
        final Spinner conesSpinner = (Spinner)v.findViewById(R.id.newplay_cones_number_spinner);
        ArrayAdapter<String> conesAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, res.getStringArray(R.array.newplay_cones_options));
        conesSpinner.setAdapter(conesAdapter);

        return new AlertDialog.Builder(getActivity()).setView(v)
                .setPositiveButton(R.string.newplay_done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mPlay = new Play(playName.getText().toString(),
                                PlayTypes.valueOf(typeSpinner.getSelectedItem().toString()),
                                Integer.parseInt(offensiveSpinner.getSelectedItem().toString()),
                                Integer.parseInt(defensiveSpinner.getSelectedItem().toString()),
                                Integer.parseInt(conesSpinner.getSelectedItem().toString()));
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create();
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null)
            return;

        Intent i = new Intent();
        i.putExtra(EXTRA_PLAY, mPlay);


        //i.putExtra(EXTRA_DATE, mDate);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }
}
