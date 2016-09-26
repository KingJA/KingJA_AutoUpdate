package com.kingja.autoupdate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lib.king.kupdate.Constants;
import lib.king.kupdate.UpdateAsyncTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new UpdateAsyncTask(this).execute(Constants.APK_NAME);
    }
}
