package com.example.mattyice.ultibook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Matty Ice on 4/16/2015.
 */
public class PlayDisplayFragment extends Fragment{
    public final static String SELECTED_STAGE = "com.example.mattyice.ultibook.stage";

    private Button mbackButton;
    private Button mplayButton;
    private Button mstopButton;
    private Button mforwardButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_play_display, parent, false);

        mbackButton = (Button)v.findViewById(R.id.playeditor_backButton);
        mbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mplayButton = (Button)v.findViewById(R.id.playeditor_playButton);
        mplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mstopButton = (Button)v.findViewById(R.id.playeditor_stopButton);
        mstopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mforwardButton = (Button)v.findViewById(R.id.playeditor_forwardButton);
        mforwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }

    public static PlayDisplayFragment newInstance(Stage stage){
        Bundle args = new Bundle();
        args.putSerializable(SELECTED_STAGE, stage);

        PlayDisplayFragment fragment = new PlayDisplayFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
