package com.example.a20zhinengzhizao1.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.activity.ScjlActivity;
import com.example.a20zhinengzhizao1.adapter.WdjlAdapter;
import com.example.a20zhinengzhizao1.bean.WdjlInfo;
import com.example.a20zhinengzhizao1.sql.Mysql;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment_jllb extends Fragment {
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.lv)
    ListView lv;
    Unbinder unbinder;
    private Mysql mysql;
    private SQLiteDatabase database;
    private List<WdjlInfo> mlist;
    private WdjlAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jllb, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mysql=new Mysql(getContext(),"jbxx.db",null,1);
        database=mysql.getReadableDatabase();
        mlist=new ArrayList<>();
        initData();
        setListener();
    }

    private void initData() {


        Cursor cursor = database.query("jllb", null, null, null, null, null, null);
        do {
            if (cursor.moveToFirst()){
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String file=cursor.getString(cursor.getColumnIndex("file"));
                String time=cursor.getString(cursor.getColumnIndex("time"));
                String bz=cursor.getString(cursor.getColumnIndex("bz"));
                mlist.add(new WdjlInfo(name,bz,time,file));
            }

        }while (cursor.moveToNext());
        adapter=new WdjlAdapter(getContext(),0,mlist);
        lv.setAdapter(adapter);
    }

    private void setListener() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("1111111111111111", "onClick: ");
                startActivity(new Intent(getContext(), ScjlActivity.class));
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fileUrl=mlist.get(position).getPath();
                if (fileUrl!=null){
                    File file = new File(fileUrl);
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.addCategory("android.intent.category.DEFAULT");
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Uri uri = Uri.fromFile(file);
                    //application/msword
                    //application/vnd.ms-excel
                    if (fileUrl.contains(".docx")){
                        intent2.setDataAndType(uri, "application/msword");
                    }else if (fileUrl.contains(".xlsx")){
                        intent2.setDataAndType(uri, "application/vnd.ms-excel");
                    }else {
                        intent2.setDataAndType(uri, "text/plain");
                    }
                    startActivity(intent2);
                }else {
                    Toast.makeText(getContext(), "路径不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
      initData();
    }
}
