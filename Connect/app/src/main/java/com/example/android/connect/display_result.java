package com.example.android.connect;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//To show the result
public class display_result extends AppCompatActivity {
    Button end,start;
    TextView result;
    int colour;
    MediaPlayer mmedia1;
     @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disp);
         mmedia1=MediaPlayer.create(this,R.raw.win);
        start=(Button)findViewById(R.id.newgame);
        end=(Button)findViewById(R.id.exitgame);
        result=(TextView)findViewById(R.id.result);
        colour=SecondActivity.result;
        if(colour==1)
        {     mmedia1.start();
         result.setTextColor(Color.RED);
            result.setText("PLAYER 1 WINS !!");
            }
        if(colour==2)
        {    result.setTextColor(Color.YELLOW);
            result.setText("PLAYER 2 WINS !!");
            mmedia1.start();
        }
        if(colour==3)
        {result.setTextColor(Color.WHITE);
            result.setText("WANT TO START A NEW GAME ?");
        }

        }
public void new_game(View view)
{
Intent intent=new Intent(display_result.this,SecondActivity.class);

startActivity(intent);

}
public void out(View view)
{finishAffinity();}




}
