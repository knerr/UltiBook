package com.example.mattyice.ultibook;

import android.graphics.Color;

/**
 * Created by Matty Ice on 3/26/2015.
 */
public class Dot {
    private int mLocationX;
    private int mLocationY;
    private String mType;
    private Color mColor;
    private int mRadius;

    public Dot(String type){
        mType = type;
        if (type == "Offense"){

        } else if (type == "Defense"){

        } else if (type == "Cone") {

        } else if (type == "Disc") {

        } else {

        }
    }

    public int getLocationX() {
        return mLocationX;
    }

    public void setLocationX(int locationX) {
        mLocationX = locationX;
    }

    public int getLocationY() {
        return mLocationY;
    }

    public void setLocationY(int locationY) {
        mLocationY = locationY;
    }

    public Color getColor() {
        return mColor;
    }

    public void setColor(Color color) {
        mColor = color;
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int radius) {
        mRadius = radius;
    }
}
