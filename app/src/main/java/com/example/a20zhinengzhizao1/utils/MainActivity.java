package com.example.a20zhinengzhizao1.utils;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import com.example.a20zhinengzhizao1.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tv_word;
    private IndexView indexView;
    private ListView lv;
    private List<Person> persons;
    private IndexViewAdapter adapter;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);

        initView();
        initData();
        indexView.setListener(new IndexView.OnIndexChangeListener() {
            @Override
            public void onIndexChange(String word) {
                upDateWord(word);
                upDateListView(word);
            }
        });
    }

    private void upDateListView(String word) {
        for (int i=0;i<persons.size();i++){
            String list_word=persons.get(i).getPy().substring(0,1);
            if (word.equals(list_word)){
                lv.setSelection(i);
                return;
            }
        }
    }

    private void initData() {
        persons.add(new Person("佩恩"));
        persons.add(new Person("阿飞"));
        persons.add(new Person("阿斯玛"));
        persons.add(new Person("千手柱间"));
        persons.add(new Person("千手扉间"));
        persons.add(new Person("猿飞日斩"));
        persons.add(new Person("小南"));
        persons.add(new Person("蝎"));
        persons.add(new Person("大蛇丸"));
        persons.add(new Person("宇智波鼬"));
        persons.add(new Person("迪达拉"));
        persons.add(new Person("鬼鲛"));
        persons.add(new Person("飞段"));
        persons.add(new Person("角都"));
        persons.add(new Person("卡卡西"));
        persons.add(new Person("鸣人"));
        persons.add(new Person("宇智波佐助"));
        persons.add(new Person("凯"));


        persons.add(new Person("黄药师"));
        persons.add(new Person("欧阳锋"));
        persons.add(new Person("段誉"));
        persons.add(new Person("洪七公"));
        persons.add(new Person("王重阳"));
        persons.add(new Person("周伯通"));
        persons.add(new Person("张家宝"));
        persons.add(new Person("张文龙"));
        persons.add(new Person("张子千"));
        persons.add(new Person("栗延童"));
        persons.add(new Person("毕雨博"));
        persons.add(new Person("李小龙"));
        persons.add(new Person("郭靖"));
        persons.add(new Person("杨康"));
        persons.add(new Person("梅超风"));



        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getPy().compareTo(o2.getPy());
            }
        });
    }

    private void initView() {

        tv_word=findViewById(R.id.tv_word);
        indexView=findViewById(R.id.tv_words);
        persons=new ArrayList<>();
        lv=findViewById(R.id.lv);
        adapter=new IndexViewAdapter(this,0,persons);
        lv.setAdapter(adapter);

    }

    private void upDateWord(String word) {
        tv_word.setVisibility(View.VISIBLE);
        tv_word.setText(word);
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_word.setVisibility(View.GONE);
            }
        },500);
    }
}
