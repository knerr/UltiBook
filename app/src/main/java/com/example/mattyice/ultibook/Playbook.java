package com.example.mattyice.ultibook;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Matty Ice on 3/26/2015.
 */
public class Playbook {
    private String mName;
    private ArrayList<Play> mPlays;
    private UUID mUUID;

    public Playbook () {
    }

    public Playbook(String name){
        mName = name;
        mUUID = UUID.randomUUID();
        mPlays = new ArrayList<Play>();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public ArrayList<Play> getPlays() {
        return mPlays;
    }

    public UUID getID() {
        return mUUID;
    }

    public void addPlay(Play p){
        mPlays.add(p);
    }
}
