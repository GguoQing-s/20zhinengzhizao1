package com.example.a20zhinengzhizao1.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.sql.HistoryInfor;
import com.example.a20zhinengzhizao1.sql.YlInfor;

import org.litepal.LitePal;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class KcActivity extends Fragment {
    @BindView(R.id.lv1)
    ListView lv1;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LitePal.getDatabase();
        add();
        List<YlInfor> infors = LitePal.findAll(YlInfor.class);
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < infors.size(); i++) {
            list.add(infors.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), KcDataActivity.class);
                intent.putExtra("name", list.get(position));
                startActivity(intent);
            }
        });
    }

    private void add() {
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(R.drawable.itachi);
        YlInfor infor = new YlInfor("hah", "adada", 1, "ww", 20, toByteArray(imageView));
        HistoryInfor historyInfor = new HistoryInfor("2020-20-20", "hahha", 20, 20.21, 32.10, "哈哈", "po", 30);
        historyInfor.save();
        infor.save();
    }


    private byte[] toByteArray(ImageView photo) {
        Bitmap bm = ((BitmapDrawable) photo.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] datas = baos.toByteArray();
        return datas;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
