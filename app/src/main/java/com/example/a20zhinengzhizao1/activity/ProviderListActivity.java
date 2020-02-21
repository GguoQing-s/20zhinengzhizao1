package com.example.a20zhinengzhizao1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.ProviderAdapter;
import com.example.a20zhinengzhizao1.entity.ProviderStoreInfo;
import com.example.a20zhinengzhizao1.sql.ProviderInfor;
import com.example.a20zhinengzhizao1.sql.YlInfo;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProviderListActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.lv)
    ListView lv;
    private List<ProviderStoreInfo> mlist;
    private ProviderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.providerlist);
        ButterKnife.bind(this);
        image2.setImageResource(R.drawable.add2);
        initData();
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(ProviderListActivity.this,AddCommodityActivity.class));
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Intent intent=new Intent(ProviderListActivity.this,GysXqActicity.class);
               intent.putExtra("number", mlist.get(position).getNumber());
               startActivity(intent);
               finish();
            }
        });
    }

    private void initData() {
        List<ProviderInfor> list= LitePal.findAll(ProviderInfor.class);
        for (ProviderInfor info :list){
            Log.d("aaaaaa", "name------"+info.getName());
            Log.d("aaaaaa", "author------"+info.getNumber());
            Log.d("aaaaaa", "pages------"+info.getCity());
            Log.d("aaaaaa", "price------"+info.getContacts());
            Log.d("aaaaaa", "press------"+info.getLegal_person());
        }
        List<YlInfo> list2= LitePal.findAll(YlInfo.class);
        for (YlInfo info :list2){
            Log.d("aaaaaa", "name------"+info.getYlName());
            Log.d("aaaaaa", "author------"+info.getPrice());
            Log.d("aaaaaa", "pages------"+info.getProviderNumber());
            Log.d("aaaaaa", "price------"+info.getYlnumber());
        }

        mlist=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ProviderInfor info = list.get(i);
            for (int x=0;x<list2.size();x++){
                YlInfo ylInfo = list2.get(x);
                if (info.getNumber()==ylInfo.getProviderNumber()){
                    mlist.add(new ProviderStoreInfo(info.getNumber(),info.getName(),info.getAddress(),ylInfo.getYlName()));
                }
            }
        }
        adapter=new ProviderAdapter(this,0,mlist);
        lv.setAdapter(adapter);

    }
}
