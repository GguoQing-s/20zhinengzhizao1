package com.example.a20zhinengzhizao1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.ProviderSelectAdapter;
import com.example.a20zhinengzhizao1.entity.KeyItem;
import com.example.a20zhinengzhizao1.entity.SelectInfor;
import com.example.a20zhinengzhizao1.fragment.ContactsFragment;
import com.example.a20zhinengzhizao1.sql.ProviderInfor;
import com.example.a20zhinengzhizao1.sql.YlInfo;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProviderSelectActivity extends AppCompatActivity {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.lv_key)
    ListView lvKey;
    @BindView(R.id.lv_value)
    ListView lvValue;
    @BindView(R.id.up)
    ImageView up;
    @BindView(R.id.down)
    ImageView down;
    @BindView(R.id.fragment)
    FrameLayout fragment;
    private List<String> list_Address, list_Bussiness, list_ylName, list_Name, list_Price, list_Contacts;
    private ProviderSelectAdapter adapter;
    private List<KeyItem> items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.providerselect);
        ButterKnife.bind(this);
        final int[] index = {0};
        list_Address = new ArrayList<>();
        list_Bussiness = new ArrayList<>();
        list_ylName = new ArrayList<>();
        list_Name = new ArrayList<>();
        list_Price = new ArrayList<>();
        list_Contacts = new ArrayList<>();
        List<SelectInfor> mlist = new ArrayList<>();
        LitePal.getDatabase();
        List<ProviderInfor> infors = LitePal.findAll(ProviderInfor.class);
        List<YlInfo> ylInfos = LitePal.findAll(YlInfo.class);

        items = new ArrayList<>();
        items.add(new KeyItem("地区", true));
        items.add(new KeyItem("业务范围", false));
        items.add(new KeyItem("原料名称", false));
        items.add(new KeyItem("价格", false));
        items.add(new KeyItem("名称", false));
        items.add(new KeyItem("联系人", false));
        adapter = new ProviderSelectAdapter(this, 0, items);
        lvKey.setAdapter(adapter);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index[0]--;
                if (index[0] <= 0) {
                    index[0] = 0;
                } else if (index[0] > items.size() - 1) {
                    index[0] = items.size() - 1;
                }
                for (int i = 0; i < items.size(); i++) {
                    items.get(i).setCheck(false);
                }
                lvKey.setSelection(index[0]);
                items.get(index[0]).setCheck(true);
                adapter.notifyDataSetChanged();
                select(index[0]);
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index[0]++;
                if (index[0] <= 0) {
                    index[0] = 0;
                } else if (index[0] > items.size() - 1) {
                    index[0] = items.size() - 1;
                }

                lvKey.setSelection(index[0]);
                for (int i = 0; i < items.size(); i++) {
                    items.get(i).setCheck(false);
                }
                items.get(index[0]).setCheck(true);

                adapter.notifyDataSetChanged();
                select(index[0]);
            }

        });

        lvKey.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (items.get(position).getName()) {
                    case "地区":
                        for (int i = 0; i < items.size(); i++) {
                            items.get(i).setCheck(false);
                        }
                        fragment.setVisibility(View.GONE);
                        lvValue.setAdapter(new ArrayAdapter<String>(ProviderSelectActivity.this, android.R.layout.simple_list_item_1, list_Address));
                        break;
                    case "业务范围":
                        for (int i = 0; i < items.size(); i++) {
                            items.get(i).setCheck(false);
                        }
                        fragment.setVisibility(View.GONE);
                        lvValue.setAdapter(new ArrayAdapter<String>(ProviderSelectActivity.this, android.R.layout.simple_list_item_1, list_Bussiness));
                        break;
                    case "原料名称":
                        for (int i = 0; i < items.size(); i++) {
                            items.get(i).setCheck(false);
                        }
                        fragment.setVisibility(View.GONE);
                        lvValue.setAdapter(new ArrayAdapter<String>(ProviderSelectActivity.this, android.R.layout.simple_list_item_1, list_ylName));
                        break;
                    case "价格":
                        for (int i = 0; i < items.size(); i++) {
                            items.get(i).setCheck(false);
                        }
                        fragment.setVisibility(View.GONE);
                        lvValue.setAdapter(new ArrayAdapter<String>(ProviderSelectActivity.this, android.R.layout.simple_list_item_1, list_Price));
                        break;
                    case "名称":
                        for (int i = 0; i < items.size(); i++) {
                            items.get(i).setCheck(false);
                        }
                        fragment.setVisibility(View.VISIBLE);
                        replace(new ContactsFragment(2));
                        lvValue.setAdapter(new ArrayAdapter<String>(ProviderSelectActivity.this, android.R.layout.simple_list_item_1, list_Name));
                        break;
                    case "联系人":
                        for (int i = 0; i < items.size(); i++) {
                            items.get(i).setCheck(false);
                        }
                        fragment.setVisibility(View.VISIBLE);
                        replace(new ContactsFragment(2));
                        break;
                }
            }
        });


        for (int i = 0; i < infors.size(); i++) {
            ProviderInfor infor = infors.get(i);
            for (int x = 0; x < ylInfos.size(); x++) {
                YlInfo ylInfo = ylInfos.get(x);
                if (infor.getNumber() == ylInfo.getProviderNumber()) {
                    mlist.add(new SelectInfor(infor.getAddress(), infor.getBusiness(), ylInfo.getPrice() + "", ylInfo.getYlName(), infor.getName(), infor.getPrice() + ""));
                }
            }
        }


        for (int i = 0; i < mlist.size(); i++) {
            boolean flag = false;
            SelectInfor infor = mlist.get(i);
            if (list_Address.size() != 0) {
                for (int x = 0; x < list_Address.size(); x++) {
                    if (!list_Address.get(x).equals(infor.getAddress())) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    list_Address.add(infor.getAddress());
                }

            } else {
                list_Address.add(infor.getAddress());
            }


            if (list_Bussiness.size() != 0) {
                for (int x = 0; x < list_Bussiness.size(); x++) {
                    if (!list_Bussiness.get(x).equals(infor.getBussiness())) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    list_Bussiness.add(infor.getBussiness());
                }

            } else {
                list_Bussiness.add(infor.getBussiness());
            }

            if (list_Contacts.size() != 0) {
                for (int x = 0; x < list_Contacts.size(); x++) {
                    if (!list_Contacts.get(x).equals(infor.getContact())) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    list_Contacts.add(infor.getContact());
                }

            } else {
                list_Contacts.add(infor.getContact());
            }

            if (list_Name.size() != 0) {
                for (int x = 0; x < list_Name.size(); x++) {
                    if (!list_Name.get(x).equals(infor.getName())) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    list_Name.add(infor.getName());
                }

            } else {
                list_Name.add(infor.getName());
            }

            if (list_Price.size() != 0) {
                for (int x = 0; x < list_Price.size(); x++) {
                    if (!list_Price.get(x).equals(infor.getPrice() + "")) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    list_Price.add(infor.getPrice() + "");
                }

            } else {
                list_Price.add(infor.getPrice() + "");
            }

            if (list_ylName.size() != 0) {
                for (int x = 0; x < list_ylName.size(); x++) {
                    if (!list_ylName.get(x).equals(infor.getYlName() + "")) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    list_ylName.add(infor.getPrice() + "");
                }

            } else {
                list_ylName.add(infor.getPrice() + "");
            }


        }
        lvValue.setAdapter(new ArrayAdapter<String>(ProviderSelectActivity.this, android.R.layout.simple_list_item_1, list_Address));
    }

    public void select(int position) {
        switch (items.get(position).getName()) {
            case "地区":
                fragment.setVisibility(View.GONE);
                lvValue.setAdapter(new ArrayAdapter<String>(ProviderSelectActivity.this, android.R.layout.simple_list_item_1, list_Address));
                break;
            case "业务范围":
                fragment.setVisibility(View.GONE);
                lvValue.setAdapter(new ArrayAdapter<String>(ProviderSelectActivity.this, android.R.layout.simple_list_item_1, list_Bussiness));
                break;
            case "原料名称":
                fragment.setVisibility(View.GONE);
                lvValue.setAdapter(new ArrayAdapter<String>(ProviderSelectActivity.this, android.R.layout.simple_list_item_1, list_ylName));
                break;
            case "价格":
                fragment.setVisibility(View.GONE);
                lvValue.setAdapter(new ArrayAdapter<String>(ProviderSelectActivity.this, android.R.layout.simple_list_item_1, list_Price));
                break;
            case "名称":
                fragment.setVisibility(View.VISIBLE);
                replace(new ContactsFragment(1));
                break;
            case "联系人":
                fragment.setVisibility(View.VISIBLE);
                replace(new ContactsFragment(2));
                break;
        }
    }

    public void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

}
