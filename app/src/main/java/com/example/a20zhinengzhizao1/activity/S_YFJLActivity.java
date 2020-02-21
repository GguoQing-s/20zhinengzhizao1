package com.example.a20zhinengzhizao1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.Yfadapter;
import com.example.a20zhinengzhizao1.bean.Yf;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class S_YFJLActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.change1)
    ImageView change1;
    @BindView(R.id.listView)
    ListView listView;
    private AppClient mApp;
    private List<Yf> myf;
    private Yfadapter myfadapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_yfyxactivity);
        ButterKnife.bind(this);
        inview();
        huoqu();
        setadapter();
    }

    private void huoqu() {
        VolleyTo volleyTo  =new VolleyTo();
        volleyTo.setUrl("get_factory_application")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setadapter() {
        myfadapter = new Yfadapter(this,myf);
        listView.setAdapter(myfadapter);
    }

    private void inview() {
        title.setText("已发邮件");
        change.setVisibility(View.GONE);
        change1.setVisibility(View.GONE);
        mApp = (AppClient) getApplication();
        myf = mApp.getMyf();
    }
}
