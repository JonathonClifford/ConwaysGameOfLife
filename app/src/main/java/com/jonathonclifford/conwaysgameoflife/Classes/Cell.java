package com.jonathonclifford.conwaysgameoflife.Classes;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class Cell extends AppCompatButton {
    private int x;
    private int y;
    private boolean alive;

    public Cell(Context context, AttributeSet attrs, int x, int y) {
        super(context, attrs);
        this.x = x;
        this.y = y;
        alive=false;
    }

    public int getXPos() {
        return x;
    }

    public void setXPos(int x) {
        this.x = x;
    }

    public int getYPos() {
        return y;
    }

    public void setYPos(int y) {
        this.y = y;
    }

    public void switchAlive() {
        alive = !alive;
    }

    public boolean isAlive() {
        return alive;
    }



}
