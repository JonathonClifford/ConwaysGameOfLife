package com.jonathonclifford.conwaysgameoflife;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jonathonclifford.conwaysgameoflife.Classes.SimCell;

import java.util.Timer;
import java.util.TimerTask;

public class SimulationActivity extends AppCompatActivity {

    //private View views[][] = new View[MainActivity.width][MainActivity.height];
    private SimCell simCells[][] = new SimCell[MainActivity.width][MainActivity.height];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);

        Intent intent = getIntent();
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        boolean message2[] = intent.getBooleanArrayExtra(MainActivity.EXTRA_MESSAGE);
        //String message = "Did it work?";
        //message += "  No, it didn't.  ";
        String message = "";
        int count = 0;
        for (int x = 0; x < MainActivity.width; x++) {
            if (message2[x]) {
                message += "1";
            } else {
                message += "0";
            }
            //message += "X";
            count++;
        }

        for (boolean value:message2) {
            if (value) {
                System.out.println("I am pregnant. I just thought you should know.");
            }
        }


        //TextView textView = findViewById(R.id.textView);
        //textView.setText(message);


        for (int i = 0; i < MainActivity.width; i++) {
            for (int j = 0; j < MainActivity.height; j++) {
                String sim_cellID = "sim_cell_" + i + j;
                int resID = getResources().getIdentifier(sim_cellID, "id", getPackageName());
                simCells[i][j] = findViewById(resID);

                if (message2[i+MainActivity.width*j])
                    simCells[i][j].makeAlive();
                else
                    simCells[i][j].makeDead();
            }
        }

        runSimulation();
    }

    private void runSimulation() {
        final Handler handler = new Handler();
        Timer timer = new Timer(false);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        updateSimBoard();
                    }
                });
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    private void updateSimBoard() {
        for (int x = 0; x < MainActivity.width; x++) {
            for (int y = 0; y < MainActivity.height; y++) {
                int adjacentAlive = countAdjacentAlive(x, y);
                Log.d("isAlive", String.format("%b", simCells[x][y].isAlive()));
                // Update view depending on the value of adjacentAlive
                if (!simCells[x][y].isAlive() && adjacentAlive == 3) {
                    simCells[x][y].makeAlive();
                } else if (simCells[x][y].isAlive() && adjacentAlive <= 1) {
                    simCells[x][y].makeDead();
                } else if (simCells[x][y].isAlive() && adjacentAlive >= 4) {
                    simCells[x][y].makeDead();
                }
                if (simCells[x][y].isAlive()) {
                    simCells[x][y].setBackgroundColor(Color.WHITE);
                } else {
                    simCells[x][y].setBackgroundColor(Color.BLACK);
                }
            }
        }
    }

    private int countAdjacentAlive(int x, int y) {
        int aliveCount = 0;
        for (int i = x - 1; i < x+2; i++) {
            for (int j = y - 1; j < y+2; j++) {
                // Handle overlaps of the grid
                int locI = i;
                int locJ = j;

                if (locI == -1)
                    locI = 9;
                else if (locI == 10)
                    locI = 0;

                if (locJ == -1)
                    locJ = 9;
                else if (locJ == 10){
                    locJ = 0;
                }

                if (simCells[locI][locJ].isAlive()) {
                    aliveCount++;
                }
            }
        }
        Log.d("Jono", String.format("%d", aliveCount));
        return aliveCount;
    }
}
