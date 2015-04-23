package com.example.mattyice.ultibook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Matty Ice on 3/26/2015.
 */
public class Stage implements Serializable{
    private UUID mId;
    private ArrayList<Dot> mOffensiveDots;
    private ArrayList<Dot> mDefensiveDots;
    private ArrayList<Dot> mConeDots;
    private Dot mDisc;
    private String mName;
    private String mPlay;
    private boolean mViewing;

    public Stage () {
        mOffensiveDots = new ArrayList<>();
        mDefensiveDots = new ArrayList<>();
        mConeDots = new ArrayList<>();
        mId = UUID.randomUUID();
    }

    public Stage (String name){
        mOffensiveDots = new ArrayList<>();
        mDefensiveDots = new ArrayList<>();
        mConeDots = new ArrayList<>();
        mName = name;
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public ArrayList<Dot> getOffensiveDots() {
        return mOffensiveDots;
    }

    public void setOffensiveDots(ArrayList<Dot> offensiveDots) {
        mOffensiveDots = offensiveDots;
    }

    public ArrayList<Dot> getDefensiveDots() {
        return mDefensiveDots;
    }

    public void setDefensiveDots(ArrayList<Dot> defensiveDots) {
        mDefensiveDots = defensiveDots;
    }

    public ArrayList<Dot> getConeDots() {
        return mConeDots;
    }

    public void setConeDots(ArrayList<Dot> coneDots) {
        mConeDots = coneDots;
    }

    public Dot getDisc() {
        return mDisc;
    }

    public void setDisc(Dot disc) {
        mDisc = disc;
    }

    public String getName() {
        return mName;
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

    public boolean isViewing() {
        return mViewing;
    }

    public void setViewing(boolean viewing) {
        mViewing = viewing;
    }
}
