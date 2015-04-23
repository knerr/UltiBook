package com.example.mattyice.ultibook;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Matty Ice on 4/16/2015.
 */
public class StageLab {
    private ArrayList<Stage> mStageArrayList;
    private static StageLab sOurInstance;
    private Context mAppContext;

    private StageLab(Context appContext){
        mAppContext = appContext;
        mStageArrayList = new ArrayList<>();
        int i;
        Stage s;
        for (i = 0; i < 5; i++){
            s = new Stage("Stage #" + i);
            s.setPlay("Play #1");
            mStageArrayList.add(s);
        }
        for (i = 5; i < 10; i++){
            s = new Stage("Stage #" + i);
            s.setPlay("Play #2");
            mStageArrayList.add(s);
        }
        for (i = 10; i < 15; i++){
            s = new Stage("Stage #" + i);
            s.setPlay("Play #6");
            mStageArrayList.add(s);
        }
    }

    public static StageLab get(Context c) {
        if (sOurInstance == null) {
            sOurInstance = new StageLab(c.getApplicationContext());
        }
        return sOurInstance;
    }

    public ArrayList<Stage> getPlays() {
        return mStageArrayList;
    }

    public ArrayList<Stage> getPlayStages(String play) {
        ArrayList<Stage> tempStageList = new ArrayList<>();
        for (Stage s : mStageArrayList){
            if (s.getPlay().equals(play)){
                tempStageList.add(s);
            }
        }
        return tempStageList;
    }

    public void addStage (Stage stage){
        mStageArrayList.add(stage);
    }

    public void deleteStage (Stage stage){
        mStageArrayList.remove(stage);
    }

    public Stage getStage(UUID id){
        for (Stage s : mStageArrayList){
            if (s.getId().equals(id)){
                return s;
            }
        }
        return null;
    }
}
