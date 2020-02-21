package com.example.a20zhinengzhizao1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Msg;
import com.example.a20zhinengzhizao1.bean.TzItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RcscActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.jl)
    TextView jl;
    @BindView(R.id.ckzp)
    TextView ckzp;
    @BindView(R.id.fbzp)
    TextView fbzp;
    @BindView(R.id.names)
    TextView names;
    @BindView(R.id.notify)
    TextView notify;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.count)
    TextView count;

    private AppClient appclient;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (appclient.getMsgCount() != 0) {
                count.setVisibility(View.VISIBLE);
                count.setText(appclient.getMsgCount() + "");
            } else {
                count.setVisibility(View.INVISIBLE);
            }

            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rcsc);
        ButterKnife.bind(this);
        jl.setOnClickListener(this);
        ckzp.setOnClickListener(this);
        fbzp.setOnClickListener(this);
        names.setOnClickListener(this);
        notify.setOnClickListener(this);
        back.setOnClickListener(this);

        appclient = (AppClient) getApplication();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    do {
                        boolean flag = false;
                        Random random1 = new Random();
                        int id = random1.nextInt(4);
                        String[] infos = new String[]{"aaa", "bbb", "ccc", "ddd"};
                        List<TzItem> mlist = appclient.getTzItems();
                        if (mlist.size() > 0) {
                            for (int i = 0; i < mlist.size(); i++) {
                                TzItem item = mlist.get(i);
                                if (item.getId() == id) {
                                    item.getMessages().add(new Msg(infos[id], 0));
                                    flag = true;
                                }
                            }
                            if (flag == false) {
                                Date date = new Date();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
                                List<Msg> list = new ArrayList<>();
                                list.add(new Msg(infos[id], 0));
                                mlist.add(new TzItem(id, list, dateFormat.format(date)));
                            }
                        } else {
                            Date date = new Date();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
                            List<Msg> list = new ArrayList<>();
                            list.add(new Msg(infos[id], 0));
                            mlist.add(new TzItem(id, list, dateFormat.format(date)));
                        }
                        Log.d("aaaaaaaaaaaaaaa", "run: " + mlist.size());
                        handler.sendEmptyMessage(0);
                        Thread.sleep(5000);
                    } while (true);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jl:
                startActivity(new Intent(this, WdjlActivity.class));
                break;
            case R.id.ckzp:
                startActivity(new Intent(this, S_ZHAOPINActivity.class));
                break;
            case R.id.fbzp:
                startActivity(new Intent(this, FbzpActivity.class));
                break;
            case R.id.names:
                startActivity(new Intent(this, S_YPRYActivity.class));
                break;
            case R.id.notify:
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
