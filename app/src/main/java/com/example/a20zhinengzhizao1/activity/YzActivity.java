package com.example.a20zhinengzhizao1.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.sql.YlInfor;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YzActivity extends AppCompatActivity {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.photo)
    ImageView photo;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.yz)
    TextView yz;
    @BindView(R.id.jian)
    ImageView jian;
    @BindView(R.id.set)
    Button set;
    private int count=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yz_activity);
        ButterKnife.bind(this);
        yz.setText(AppClient.yz+"");
        count=AppClient.yz;
        String Ylname=getIntent().getStringExtra("name");
        List<YlInfor> infors = LitePal.findAll(YlInfor.class);
        name.setText(Ylname);
        for (int i = 0; i < infors.size(); i++) {
            YlInfor infor = infors.get(i);
            if (Ylname.equals(infor.getName())){
                byte[] bytes = infor.getPhoto();
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                photo.setImageBitmap(bitmap);
            }
            break;
        }
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppClient.yz=count;
                Toast.makeText(YzActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
            }
        });
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count>0){
                    count--;
                }else {
                    count=0;
                }

            }
        });
    }
}
