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
    private int mNumPlays;

    public Playbook () {
        mUUID = UUID.randomUUID();
        mNumPlays = 0;
        mPlays = new ArrayList<>();
    }

    public Playbook(String name){
        mName = name;
        mUUID = UUID.randomUUID();
        mPlays = new ArrayList<>();
        mNumPlays = 0;
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

    public void addPlay(Play p){
        mPlays.add(p);
        mNumPlays++;
    }

    public void removePlay(Play p){
        mPlays.remove(p);
        mNumPlays--;
    }

    public UUID getID() {
        return mUUID;
    }


    public int getNumPlays() {
        return mNumPlays;
    }

    public void setNumPlays(int numPlays) {
        mNumPlays = numPlays;
    }
}
