package com.example.mattyice.ultibook;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Matty Ice on 4/16/2015.
 */
public class PlayDisplayFragment extends Fragment{
    public final static String SELECTED_STAGE = "com.example.mattyice.ultibook.stage";
    public final static String ALL_STAGES = "com.example.mattyice.ultibook.stages";

    private Button mbackButton;
    private Button mplayButton;
    private Button mstopButton;
    private Button mforwardButton;

    private Stage mPrevStage;
    private Stage mNextStage;
    private Stage mSelectedStage;
    private ArrayList<Stage> mStages;

    private Stage mStagePrev;
    private Stage mStageCurrent;
    private Stage mStageNext;

    private Callbacks mCallbacks;

    /**
     * Required interface for hosting activities
     */
    public interface Callbacks {
        void onStageBack(Stage stage);
        void onStageForward(Stage stage);
        void onStagePlay(Stage stage);
        void onStageStop();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallbacks = (Callbacks)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mSelectedStage = (Stage)getArguments().getSerializable(SELECTED_STAGE);
        mStages = (ArrayList<Stage>)getArguments().getSerializable(ALL_STAGES);

        //If we're working with one object
        if (mStages.size() == 1){
            mNextStage = null;
            mPrevStage = null;
        }
        //If we're working with the first object.
        else if (mStages.indexOf(mSelectedStage) == 0){
            mNextStage = mStages.get(mStages.indexOf(mSelectedStage) + 1);
            mPrevStage = null;
        }
        //Last object
        else if (mStages.indexOf(mSelectedStage) >= mStages.size()-1){
            mNextStage = null;
            mPrevStage = mStages.get(mStages.indexOf(mSelectedStage) - 1);
        }
        //Has both a previous and a next
        else {
            mNextStage = mStages.get(mStages.indexOf(mSelectedStage) + 1);
            mPrevStage = mStages.get(mStages.indexOf(mSelectedStage) - 1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
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

    public static PlayDisplayFragment newInstance(Stage stage, ArrayList<Stage> stages){
        Bundle args = new Bundle();
        args.putSerializable(SELECTED_STAGE, stage);
        args.putSerializable(ALL_STAGES, stages);

        PlayDisplayFragment fragment = new PlayDisplayFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
