package com.example.android.connect;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                // This method will be executed once the timer is over
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
                finish();

    }
}
