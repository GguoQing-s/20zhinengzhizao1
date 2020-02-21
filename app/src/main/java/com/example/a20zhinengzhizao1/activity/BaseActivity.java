package com.example.a20zhinengzhizao1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.S_MyService;

import java.io.IOException;

public class BaseActivity extends AppCompatActivity {
    private S_MyService myWebServer;
    private AppClient mApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = (AppClient) getApplication();
        myWebServer  = new S_MyService(3333,mApp);
        try {
            myWebServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("------------------", "onCreate: "+getPackageCodePath());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myWebServer!=null){
            myWebServer.stop();
        }
        Log.d("------------------", "onDestroy: "+getPackageCodePath());
    }
}
