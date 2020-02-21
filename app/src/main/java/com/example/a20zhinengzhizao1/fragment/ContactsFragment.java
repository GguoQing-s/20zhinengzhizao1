package com.example.a20zhinengzhizao1.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.sql.ProviderInfor;
import com.example.a20zhinengzhizao1.user.IndexView;
import com.example.a20zhinengzhizao1.utils.IndexViewAdapter;
import com.example.a20zhinengzhizao1.utils.IndexViewAdapter2;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class ContactsFragment extends Fragment {
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.tv_words)
    IndexView tvWords;
    Unbinder unbinder;
    private int flag;


    public ContactsFragment(int flag) {
        this.flag = flag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<ProviderInfor> infors = LitePal.findAll(ProviderInfor.class);
        Log.d("2222222222222222", "onActivityCreated: "+infors.size());
        if (flag==1){
            lv.setAdapter(new IndexViewAdapter(getContext(),0,infors));
        }else {
            lv.setAdapter(new IndexViewAdapter2(getContext(),0,infors));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
