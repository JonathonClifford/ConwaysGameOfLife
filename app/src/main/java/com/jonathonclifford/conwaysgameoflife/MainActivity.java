package com.jonathonclifford.conwaysgameoflife;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jonathonclifford.conwaysgameoflife.Classes.MyButton;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.jonathonclifford.conwaysgameoflife.MESSAGE";

    public static final int height = 10;
    public static final int width = 10;

    //private MyButton button;
    //private MyButton cell2;
    //private MyButton myButton3;

    private MyButton cells[][] = new MyButton[width][height];

    private View.OnClickListener myButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v instanceof MyButton)
                myButtonClicked((MyButton)v);
        }
    };

    private void myButtonClicked(MyButton b) {
        b.switchAlive();
        if (b.isAlive()) {
            b.setBackgroundColor(Color.WHITE);
        } else {
            b.setBackgroundColor(Color.BLACK);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button = new MyButton(this);
        //cell2 = new MyButton(this);
        //myButton3 = new MyButton(this);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                String cellID = "cell_" + x + y;
                int resID = getResources().getIdentifier(cellID, "id", getPackageName());
                cells[x][y] = findViewById(resID);
                cells[x][y].setOnClickListener(myButtonOnClickListener);
            }
        }

    }

    public void StartSimulation(View view) {
        Intent intent = new Intent(this, SimulationActivity.class);
        //String message = new String("Mrs Robinson");
        boolean message[] = new boolean[width*height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                message[x+y*width] = cells[x][y].isAlive();
            }
        }
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
