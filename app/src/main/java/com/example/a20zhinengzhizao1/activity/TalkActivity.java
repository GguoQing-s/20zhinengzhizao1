package com.example.a20zhinengzhizao1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.MsgAdapter;
import com.example.a20zhinengzhizao1.bean.Msg;
import com.example.a20zhinengzhizao1.bean.TzItem;

import java.util.List;

public class TalkActivity extends AppCompatActivity {

    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private MsgAdapter adapter;
    private AppClient appclient;
    private List<Msg> msgList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talk);
        msgListView =  findViewById(R.id.msg_list_view);
        send =  findViewById(R.id.send);
        appclient= (AppClient) getApplication();

        List<TzItem> items = appclient.getTzItems();
        msgList=items.get(getIntent().getIntExtra("id",0)).getMessages();
        adapter = new MsgAdapter(this, R.layout.msg_item, msgList);
        inputText =  findViewById(R.id.input_text);

        Log.d("wwwwwwwwwww", "onCreate: ");
        msgListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();
                    msgListView.setSelection(msgList.size());
                    inputText.setText("");
                }
            }
        });
    }



}
