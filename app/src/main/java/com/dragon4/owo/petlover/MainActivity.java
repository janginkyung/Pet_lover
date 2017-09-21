package com.dragon4.owo.petlover;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(MainActivity.this,GoogleSignActivity.class);
                startActivity(intent);
            }
        }, 2000);// 0.5초 정도 딜레이를 준 후 시작




    }
}
