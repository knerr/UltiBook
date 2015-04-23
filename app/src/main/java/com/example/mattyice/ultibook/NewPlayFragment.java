package com.example.mattyice.ultibook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matty Ice on 3/25/2015.
 */
public class NewPlayFragment extends DialogFragment {

    public static final String EXTRA_PLAY = "com.example.knerr.criminalintent.play-name";

    private Play mPlay;
    private String mPlaybook;

    public static NewPlayFragment newInstance(){
        Bundle args = new Bundle();

        NewPlayFragment fragment = new NewPlayFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public static NewPlayFragment newInstance(String playbook){
        Bundle args = new Bundle();
        args.putString("playbook", playbook);

        NewPlayFragment fragment = new NewPlayFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState){

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_newplay, null);
        mPlaybook = getArguments().getString("playbook");

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

        //Playbook Spinner
        final Spinner playbookSpinner = (Spinner)v.findViewById(R.id.newplay_playbook_spinner);
        ArrayList<Playbook> PlaybookArrayList = PlaybookLab.get(getActivity()).getPlaybooks();
        List<String> playbookNames = new ArrayList<>();
        playbookNames.add("None");
        for(int i = 0; i < PlaybookArrayList.size(); i++){
            playbookNames.add(PlaybookArrayList.get(i).getName());
        }
        ArrayAdapter<String> playbookAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, playbookNames);
        playbookSpinner.setAdapter(playbookAdapter);
        if (mPlaybook != null){
            playbookSpinner.setSelection(playbookAdapter.getPosition(mPlaybook));
            playbookSpinner.setEnabled(false);
        }


        return new AlertDialog.Builder(getActivity()).setView(v)
                .setPositiveButton(R.string.newplay_done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<Play> playArrayList = PlayLab.get(getActivity()).getPlays();
                        if (!playName.getText().toString().equals("")){
                            mPlay = new Play(playName.getText().toString(),
                                    PlayTypes.valueOf(typeSpinner.getSelectedItem().toString()),
                                    Integer.parseInt(offensiveSpinner.getSelectedItem().toString()),
                                    Integer.parseInt(defensiveSpinner.getSelectedItem().toString()),
                                    Integer.parseInt(conesSpinner.getSelectedItem().toString()),
                                    playbookSpinner.getSelectedItem().toString());
                            playArrayList.add(mPlay);
                        }
                        else {
                            //Say to insert a play name
                        }
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .setNegativeButton(R.string.newplay_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_CANCELED);
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
