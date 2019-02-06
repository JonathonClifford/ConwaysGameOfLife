package com.jonathonclifford.conwaysgameoflife.Classes;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import com.jonathonclifford.conwaysgameoflife.R;

public class MyButton extends Button {
    private int x;
    private int y;
    private boolean alive = false;

    public MyButton(Context context) {
        this(context, null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        alive = false;

        /*TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MyButton,
                0, 0);

        try {
            x = a.getInteger(R.styleable.MyButton_xPos, 0);
            y = a.getInteger(R.styleable.MyButton_yPos, 0);
        } finally {
            a.recycle();
        }*/
    }

    public boolean isAlive() {
        return alive;
    }

    public void switchAlive() {
        alive = !alive;
    }
}
