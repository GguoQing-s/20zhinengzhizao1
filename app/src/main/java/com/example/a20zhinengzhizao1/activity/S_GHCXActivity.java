package com.example.a20zhinengzhizao1.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_qiye;
import com.example.a20zhinengzhizao1.fragment.S_Fragment_yuanliao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_GHCXActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.yuanliao)
    TextView yuanliao;
    @BindView(R.id.qiye)
    TextView qiye;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_gshcactivity);
        ButterKnife.bind(this);
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.lin,new S_Fragment_yuanliao());
        ft.commit();
        inview();
    }

    private void inview() {
        title.setText("供应商-供货查询");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.change, R.id.yuanliao, R.id.qiye})
    public void onViewClicked(View view) {
        ft = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.change:
               finish();
                break;
            case R.id.yuanliao:
                ft.replace(R.id.lin,new S_Fragment_yuanliao());
                break;
            case R.id.qiye:
                ft.replace(R.id.lin,new S_Fragment_qiye());
                break;
        }
        ft.commit();
    }
}
