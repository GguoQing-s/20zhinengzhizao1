package com.example.a20zhinengzhizao1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.CommodityAdapter;
import com.example.a20zhinengzhizao1.dialog.AddCommodityDialog;
import com.example.a20zhinengzhizao1.sql.ProviderInfor;
import com.example.a20zhinengzhizao1.sql.YlInfo;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCommodityActivity extends AppCompatActivity {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.add)
    Button add;
    private CommodityAdapter adapter;
    private AddCommodityDialog dialog;
    private List<ProviderInfor> list;
    private List<YlInfo> mlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_commodity);
        ButterKnife.bind(this);

        LitePal.getDatabase();

        mlist = LitePal.findAll(YlInfo.class);
        adapter=new CommodityAdapter(AddCommodityActivity.this, 0, mlist);
        lv.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new AddCommodityDialog();
                dialog.show(getSupportFragmentManager(),"");
                dialog.setListener(new AddCommodityDialog.ReflashListener() {
                    @Override
                    public void reflash() {
                        mlist = LitePal.findAll(YlInfo.class);
                        adapter=new CommodityAdapter(AddCommodityActivity.this, 0, mlist);
                        lv.setAdapter(adapter);
                    }
                });
            }
        });
    }
}
