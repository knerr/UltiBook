package com.example.mattyice.ultibook;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Matty Ice on 3/26/2015.
 */
public class PlaybookLab {
    private ArrayList<Playbook> mPlaybooks;
    private static PlaybookLab sOurInstance;
    private Context mAppContext;

    private PlaybookLab(Context appContext) {
        mAppContext = appContext;
        mPlaybooks = new ArrayList<Playbook>();
        for (int i = 0; i < 10; i++){
            Playbook p = new Playbook("Playbook #" + i);
            mPlaybooks.add(p);
        }
    }

    public static PlaybookLab get(Context c) {
        if (sOurInstance == null) {
            sOurInstance = new PlaybookLab(c.getApplicationContext());
        }
        return sOurInstance;
    }

    public ArrayList<Playbook> getPlaybooks() {
        return mPlaybooks;
    }

    public Playbook getPlay(UUID id){
        for (Playbook p : mPlaybooks){
            if (p.getID().equals(id)){
                return p;
            }
        }
        return null;
    }
}