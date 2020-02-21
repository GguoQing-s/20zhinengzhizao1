package com.example.a20zhinengzhizao1.server;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;

import java.util.Random;

public class SQLserver extends Service {
    public SQLserver() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Uri uri  =Uri.parse("content://com.example.databasetest.provider/User");
        ContentValues values  =new ContentValues();
        for (int i=1;i<6;i++)
        {
            Random random = new Random();
            values.put("username","user"+i);
            values.put("password","123456");
            values.put("email","1571771239@qq.com");
            values.put("ida",i+"");
            values.put("jine",random.nextInt(10000)+"");
            getContentResolver().insert(uri,values);
        }
        Uri uri1  =Uri.parse("content://com.example.databasetest.provider/Zhopin");
        ContentValues values1  =new ContentValues();
        values1.put("name","公司A");
        values1.put("type","电子");
        values1.put("name1","销售");
        values1.put("yaoqiu","熟练掌握专业知识");
        values1.put("chengshi","德州");
        values1.put("xiuli","专科");
        values1.put("xinzi","3000-5000");
        values1.put("email","aa@qq.com");
        values1.put("time","2018-10-10 10:20:30");
        getContentResolver().insert(uri1,values1);
        values1.clear();
        values1.put("name","公司B");
        values1.put("type","食品");
        values1.put("name1","生产");
        values1.put("yaoqiu","掌握专业知识");
        values1.put("chengshi","济南");
        values1.put("xiuli","专科");
        values1.put("xinzi","3500-6000");
        values1.put("email","bb@qq.com");
        values1.put("time","2019-11-10 18:25:20");
        getContentResolver().insert(uri1,values1);
        values1.clear();
        values1.put("name","公司c");
        values1.put("type","电子");
        values1.put("name1","质检");
        values1.put("yaoqiu","掌握专业知识");
        values1.put("chengshi","聊城");
        values1.put("xiuli","专科");
        values1.put("xinzi","5000-7000");
        values1.put("email","cc@qq.com");
        values1.put("time","2015-01-30 10:05:10");
        getContentResolver().insert(uri1,values1);
        return super.onStartCommand(intent, flags, startId);

    }
}
