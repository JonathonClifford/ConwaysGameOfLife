package com.jonathonclifford.conwaysgameoflife.Classes;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class SimCell extends View {
    private int x;
    private int y;
    private boolean alive;


    public SimCell(Context context) {
        super(context);
    }

    public SimCell(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isAlive() { return alive; }

    public void switchAlive() { alive = !alive; }

    public void makeAlive() {
        alive = true;
        this.setBackgroundColor(Color.WHITE);
    }

    public void makeDead() {
        alive = false;
        this.setBackgroundColor(Color.BLACK);
    }
}
