package com.example.a20zhinengzhizao1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.YlAdapter;
import com.example.a20zhinengzhizao1.sql.ProviderInfor;
import com.example.a20zhinengzhizao1.sql.YlInfo;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GysXqActicity extends AppCompatActivity {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.bh)
    TextView bh;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.legal_person)
    TextView legalPerson;
    @BindView(R.id.contact)
    TextView contact;
    @BindView(R.id.business)
    TextView business;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.ylbh)
    TextView ylbh;
    private ProviderInfor infor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gysxq);
        ButterKnife.bind(this);

        LitePal.getDatabase();
        List<ProviderInfor> mlist = LitePal.findAll(ProviderInfor.class);
        List<YlInfo> ylInfos = LitePal.findAll(YlInfo.class);
        List<YlInfo> mlist2 = new ArrayList<>();
        int number = getIntent().getIntExtra("number", 0);
        Log.d("1111111111111", "onCreate: " + number + "----------" + mlist.size());
        for (int i = 0; i < mlist.size(); i++) {
            if (mlist.get(i).getNumber() == number) {
                infor = mlist.get(i);
            }
        }
        for (int i = 0; i < ylInfos.size(); i++) {
            if (ylInfos.get(i).getProviderNumber() == number) {
                mlist2.add(ylInfos.get(i));
            }
        }
        bh.setText("" + infor.getNumber());
        name.setText(infor.getName());
        city.setText(infor.getCity());
        address.setText(infor.getAddress());
        legalPerson.setText(infor.getLegal_person());
        contact.setText(infor.getContacts());
        business.setText(infor.getBusiness());
        ylbh.setText(infor.getNumber());

        lv.setAdapter(new YlAdapter(this, 0, mlist2));


    }
}
