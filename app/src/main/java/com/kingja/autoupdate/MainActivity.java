package com.kingja.autoupdate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lib.king.kupdate.UpdateManager;
import lib.king.kupdate.strategy.WebServiceStrategy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UpdateManager.Builder builder = new UpdateManager.Builder(this);
        builder
                .setUpdateCancleable(false)
                .setShowDownloadDialog(true)
                .setLoadStrategy(new WebServiceStrategy())
                .setUpdateContent("V1.2.5:\n1.界面的美化\n" +
                        "2.登录模块的简略化\n" +
                        "3.取消多次点赞功能，并加入了二次元点赞动画，非常绚丽\n" +
                        "4.修补了一些小Bug\n" +
                        "5.加入收藏功能")
                .build()
                .checkUpdate();
    }
}
