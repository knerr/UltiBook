package com.example.mattyice.ultibook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Matty Ice on 3/26/2015.
 */
public class Play implements Serializable {

    private UUID mID;
    private String mName;
    private PlayTypes mType;
    private int mOffensivePlayers;
    private int mDefensivePlayers;
    private int mCones;
    private ArrayList<Stage> mStages;
    private String mPlaybook;
    private Date mDateCreated;

    public Play () {
        mID = UUID.randomUUID();
    }
    public Play (String name, PlayTypes type){
        mID = UUID.randomUUID();
        mName = name;
        mType = type;
    }
    public Play (String name, PlayTypes type, int OffensivePlayers, int DefensivePlayers, int Cones,
                 String playbook) {
        mID = UUID.randomUUID();
        this.mName = name;
        mType = type;
        mOffensivePlayers = OffensivePlayers;
        mDefensivePlayers = DefensivePlayers;
        mCones = Cones;
        mPlaybook = playbook;
    }

    public UUID getID() {
        return mID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public PlayTypes getType() {
        return mType;
    }

    public void setType(PlayTypes type) {
        mType = type;
    }

    public int getOffensivePlayers() {
        return mOffensivePlayers;
    }

    public void setOffensivePlayers(int offensivePlayers) {
        mOffensivePlayers = offensivePlayers;
    }

    public int getDefensivePlayers() {
        return mDefensivePlayers;
    }

    public void setDefensivePlayers(int defensivePlayers) {
        mDefensivePlayers = defensivePlayers;
    }

    public int getCones() {
        return mCones;
    }

    public void setCones(int cones) {
        mCones = cones;
    }

    public ArrayList<Stage> getStages() {
        return mStages;
    }

    public void setPlaybook(String Playbook) { mPlaybook = Playbook;}

    public String getPlaybook() {return mPlaybook;}

    public Date getDateCreated() {
        return mDateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        mDateCreated = dateCreated;
    }

}
