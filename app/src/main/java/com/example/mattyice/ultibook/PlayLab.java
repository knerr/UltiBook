package com.example.mattyice.ultibook;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Matty Ice on 4/8/2015.
 */
public class PlayLab {
    private ArrayList<Play> mPlayArrayList;
    private static PlayLab sOurInstance;
    private Context mAppContext;

    private PlayLab(Context appContext){
        mAppContext = appContext;
        mPlayArrayList = new ArrayList<>();
        int i;
        Play p;
        for (i = 0; i < 5; i++){
            p = new Play("Play #" + i, PlayTypes.Offensive);
            p.setPlaybook("Playbook #0");
            mPlayArrayList.add(p);
        }
        for (i = 5; i < 10; i++){
            p = new Play("Play #" + i, PlayTypes.Drill);
            p.setPlaybook("Playbook #1");
            mPlayArrayList.add(p);
        }
    }

    public static PlayLab get(Context c) {
        if (sOurInstance == null) {
            sOurInstance = new PlayLab(c.getApplicationContext());
        }
        return sOurInstance;
    }

    public ArrayList<Play> getPlays() {
        return mPlayArrayList;
    }

    public ArrayList<Play> getPlaybookPlays(String playbook) {
        ArrayList<Play> tempPlayList = new ArrayList<>();
        for (Play p : mPlayArrayList){
            if (p.getPlaybook().equals(playbook)){
                tempPlayList.add(p);
            }
        }
        return tempPlayList;
    }

    public Play getPlay(UUID id){
        for (Play p : mPlayArrayList){
            if (p.getID().equals(id)){
                return p;
            }
        }
        return null;
    }

    public void deletePlay(Play p){
        mPlayArrayList.remove(p);
    }
}
