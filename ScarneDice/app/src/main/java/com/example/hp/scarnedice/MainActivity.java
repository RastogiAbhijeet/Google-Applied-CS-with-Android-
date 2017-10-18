package com.example.hp.scarnedice;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;


import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    public static int USEROVERALLSCORE=0, USERTURNSCORE=0;
    public static int COMPOVERALLSCORE=0, COMPTURNSCCORE=0;
    public static int flag = 0;
    public ImageView img;
    public Random random;
    public TextView userScore ;
    public TextView compScore ;
    public Button roll ;
    public Button hold;
    public Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll = (Button) findViewById(R.id.Roll);
        hold = (Button) findViewById(R.id.Hold);
        reset = (Button) findViewById(R.id.Reset);
        userScore = (TextView) findViewById(R.id.userScore);
        compScore = (TextView) findViewById(R.id.compScore);
        img = (ImageView) findViewById(R.id.imageView);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int numberOnDice = handleRoll();

                if (numberOnDice != 1) {
                    USERTURNSCORE += numberOnDice;
//                    Toast toasty = Toast.makeText(getApplicationContext(), "Turn Score : " + Integer.toString(USERTURNSCORE), Toast.LENGTH_SHORT);
//                    toasty.show();
                    userScore.setText("Your Score : " + Integer.toString(USEROVERALLSCORE+=numberOnDice));
                } else {
                    userScore.setText("Your Score <>: " + Integer.toString(numberOnDice)+"  "+ Integer.toString(USEROVERALLSCORE-=USERTURNSCORE));
                    USERTURNSCORE = 0;
                    final Handler time = new Handler();
                    Runnable timeRunnable = new Runnable() {
                        @Override
                        public void run() {


                            time.postDelayed(this, 500);
                        }
                    };

                    holdHandler();

                }
//
//
//
            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdHandler();

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USERTURNSCORE = 0;
                COMPTURNSCCORE = 0;
                COMPOVERALLSCORE = 0;
                USEROVERALLSCORE = 0;
                userScore.setText("Your Score : 0");
                compScore.setText("Computer Score : 0");
            }
        });
    }

    public int handleRoll(){
        random = new Random();
        int i = random.nextInt(6);
        img.setImageDrawable(getResources().getDrawable(R.drawable.dice1+i));
        return i+1;
    }

    public void compTurn() {
        int score = handleRoll();

        if (COMPTURNSCCORE < 20){
            if (score != 1) {
                COMPTURNSCCORE += score;
            } else {
                COMPTURNSCCORE = 0;
                COMPOVERALLSCORE += COMPTURNSCCORE;
            }
            compTurn();
        }
    }

    public void holdHandler(){
        Toast toasty = Toast.makeText(getApplicationContext(), "Computer's Turn", Toast.LENGTH_SHORT);
        toasty.show();
        USEROVERALLSCORE += USERTURNSCORE;
        USERTURNSCORE = 0;
        userScore.setText("Your Score : " + Integer.toString(USEROVERALLSCORE));
        roll.setBackgroundColor(Color.BLACK);
        roll.setEnabled(false);

        compTurn();
        compScore.setText("Computer Score : " + Integer.toString(COMPOVERALLSCORE += COMPTURNSCCORE));
        COMPTURNSCCORE = 0;
        roll.setBackgroundColor(Color.LTGRAY);
        roll.setEnabled(true);

    }

}
