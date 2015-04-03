package com.example.mattyice.ultibook;

import java.util.ArrayList;

/**
 * Created by Matty Ice on 3/26/2015.
 */
public class Stage {
    private ArrayList<Dot> mOffensiveDots;
    private ArrayList<Dot> mDefensiveDots;
    private ArrayList<Dot> mConeDots;
    private Dot mDisc;

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
}
