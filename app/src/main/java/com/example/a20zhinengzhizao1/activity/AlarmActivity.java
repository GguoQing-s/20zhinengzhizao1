package com.example.a20zhinengzhizao1.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.AlarmAdapter;
import com.example.a20zhinengzhizao1.entity.AlarmInfor;
import com.example.a20zhinengzhizao1.sql.YlInfor;
import com.example.a20zhinengzhizao1.user.ReflashListView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AlarmActivity extends Fragment {
    @BindView(R.id.lv)
    ReflashListView lv;
    Unbinder unbinder;
    private List<AlarmInfor> mlist;
    private List<YlInfor> infors;
    private boolean flag=false;
    private AlarmAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alarm, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(getActivity());

        LitePal.getDatabase();
        infors = LitePal.findAll(YlInfor.class);
        mlist = new ArrayList<>();
        for (int i = 0; i < infors.size(); i++) {
            if (infors.get(i).getCount() <= AppClient.yz) {
                mlist.add(new AlarmInfor(infors.get(i).getName(), infors.get(i).getXh(), infors.get(i).getCount()));
            }
        }

        adapter = new AlarmAdapter(getActivity(), 0, mlist);
        lv.setAdapter(adapter);
        lv.setiReflashListerner(new ReflashListView.IReflashListerner() {
            @Override
            public void onReflash() {
                mlist.clear();
                for (int i = 0; i < infors.size(); i++) {
                    if (infors.get(i).getCount() <= AppClient.yz) {
                        mlist.add(new AlarmInfor(infors.get(i).getName(), infors.get(i).getXh(), infors.get(i).getCount()));
                    }
                }
                if (flag == false) {
                    lv.reflashComplete();
                    flag = true;
                } else {
                    lv.Isfinish();
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
