package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    int[][] winningState = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int getState[] = {2,2,2,2,2,2,2,2,2};
    boolean gameActive = true;
    int activePlayer = 0;


    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

            int tappedCounter = Integer.parseInt(counter.getTag().toString());
            if(getState[tappedCounter] ==2){

            getState[tappedCounter] = activePlayer;
            if (getState[tappedCounter] != 2 && gameActive) {
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.shinchan);
                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.amikky);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);


            for (int winningPosition[] : winningState) {

                if (getState[winningPosition[0]] == getState[winningPosition[1]] &&
                        getState[winningPosition[1]] == getState[winningPosition[2]] && getState[winningPosition[0]] != 2) {
                    String msg = "";
                    if (activePlayer == 0) {
                        msg = "Miccky";
                    } else {
                        msg = "Shinchan";
                    }

                   // Toast.makeText(this, msg + "has Won!", Toast.LENGTH_SHORT).show();
                    Button playButton = (Button) findViewById(R.id.button);
                    TextView winnerText = (TextView) findViewById(R.id.textView);
                    winnerText.setText(msg + " has Won!!!");
                    playButton.setVisibility(view.VISIBLE);
                    winnerText.setVisibility(view.VISIBLE);

                    gameActive = false;


                }
            }

        }
    }
    }

    public void playAgain(View view){
        Log.i("ggg", "tappp");
        Button playButton = (Button) findViewById(R.id.button);
        TextView winnerText = (TextView) findViewById(R.id.textView);
        playButton.setVisibility(view.INVISIBLE);
        winnerText.setVisibility(view.INVISIBLE);
        GridLayout gridLay = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i<gridLay.getChildCount(); i++){
            ImageView counter = (ImageView) gridLay.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<getState.length;i++){
            getState[i]= 2;
        }
        activePlayer =0;
        gameActive = true;

    }



}