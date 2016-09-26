package com.kingja.autoupdate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lib.king.kupdate.UpdateManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UpdateManager.Builder builder = new UpdateManager.Builder(this);
        builder.setCancleable(false).build().checkUpdate();
    }
}
