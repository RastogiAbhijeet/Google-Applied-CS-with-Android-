package com.example.hp.scarnedice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    public static int USEROVERALLSCORE, USERTURNSCORE;
    public static int COMPOVERALLSCORE, COMPTURNSCCORE;
    public static int flag = 0;
    public ImageView img;
    public Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button roll = (Button)findViewById(R.id.Roll);
        img = (ImageView)findViewById(R.id.imageView);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "Game starts",Toast.LENGTH_LONG);
                    toast.show();
                    flag+=1;
                }
                int numberOnDice = handleRoll();


            }
        });
    }

    public int handleRoll(){
        random = new Random();
        int i = random.nextInt(6);
        img.setImageDrawable(getResources().getDrawable(R.drawable.dice1+i));
        return i+1;
    }
}
