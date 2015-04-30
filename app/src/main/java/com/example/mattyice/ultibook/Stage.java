package com.example.mattyice.ultibook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Matty Ice on 3/26/2015.
 */
public class Stage implements Serializable{
    private UUID mId;
    private ArrayList<Dot> mDots;
    private String mName;
    private String mPlay;

    public Stage () {
        mDots = new ArrayList<>();
        mId = UUID.randomUUID();
    }

    public Stage (String name){
        mDots = new ArrayList<>();
        mName = name;
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<Dot> getDots() {
        return mDots;
    }

    public void setDots(ArrayList<Dot> dots) {
        mDots = dots;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPlay() {
        return mPlay;
    }

    public void setPlay(String play) {
        mPlay = play;
    }
}
