package com.example.a20zhinengzhizao1.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.ShxxAdapter;
import com.example.a20zhinengzhizao1.bean.HistoryInfo;
import com.example.a20zhinengzhizao1.bean.ShxxInfo;
import com.example.a20zhinengzhizao1.sql.Mysql_Zpxx;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content2)
    TextView content2;
    @BindView(R.id.content3)
    TextView content3;
    private List<ShxxInfo> mlist;
    @BindView(R.id.lv)
    ListView lv;
    private ShxxAdapter adapter;
    private AppClient appclient;
    private  List<HistoryInfo> historyInfos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shxx);
        ButterKnife.bind(this);
        appclient= (AppClient) getApplication();
        content3.setText("审核");
        content2.setText("全选");
      historyInfos = appclient.getHistoryInfos();
        mlist = new ArrayList<>();
        Mysql_Zpxx dbhelper = new Mysql_Zpxx(this, "zpxx.db", null, 1);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.query("fbzp", null, null, null, null, null, null);
       while (cursor.moveToNext()){
           String bh = cursor.getString(cursor.getColumnIndex("name"));
           String com_address = cursor.getString(cursor.getColumnIndex("address"));
           String gw = cursor.getString(cursor.getColumnIndex("xz"));
           mlist.add(new ShxxInfo(bh, com_address, gw, false));
       }
        adapter = new ShxxAdapter(this, 0, mlist);
        lv.setAdapter(adapter);
       adapter.setListener(new ShxxAdapter.OnCheckChangeListener() {
           @Override
           public void setData(int lx, int position, boolean check) {
               if (lx==1){
                   mlist.get(position).setCheck(true);
                   addInfo();
               }else {
                   mlist.get(position).setCheck(check);
               }
           }
       });

        content3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInfo();
            }
        });
        content2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<mlist.size();i++){
                    mlist.get(i).setCheck(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void addInfo() {
        boolean flag=false;
        Date date=new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd");
        for (int i=0;i<mlist.size();i++){
            ShxxInfo info = mlist.get(i);
            if (info.isCheck()){
                flag=true;
                mlist.remove(i);
                historyInfos.add(new HistoryInfo(info.getBh(),info.getCom_address(),info.getGw(),"admin",dateFormat.format(date)));
            }
        }

        if (flag==false){
            Toast.makeText(this, "请选择审核信息", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "审核成功", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
    }
}
