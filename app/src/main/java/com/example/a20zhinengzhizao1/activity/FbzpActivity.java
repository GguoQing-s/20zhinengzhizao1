package com.example.a20zhinengzhizao1.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;
import com.example.a20zhinengzhizao1.sql.Mysql_Zpxx;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FbzpActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content2)
    TextView content2;
    @BindView(R.id.content3)
    TextView content3;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.comm_address)
    EditText commAddress;
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.gw)
    EditText gw;
    @BindView(R.id.gz)
    EditText gz;
    @BindView(R.id.zyyq)
    EditText zyyq;
    @BindView(R.id.gzjlyz)
    EditText gzjlyz;
    @BindView(R.id.gzyq)
    EditText gzyq;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.xl)
    Spinner xl;
    @BindView(R.id.hy)
    EditText hy;
    private int i=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fbzp);
        ButterKnife.bind(this);
        Mysql_Zpxx dbhelper = new Mysql_Zpxx(this, "zpxx.db", null, 1);
        final SQLiteDatabase db = dbhelper.getWritableDatabase();
        title.setText("发布招聘信息");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
                Date date = new Date(System.currentTimeMillis());
                Date date1 =new Date(System.currentTimeMillis());
                String t1 = format1.format(date1);
                String t = format.format(date);
                Log.d("=====", "onClick: ----" + t);
                if (AppClient.getTime()==Integer.parseInt(t1))
                {
                    AppClient.setZhi(AppClient.getZhi()+1);
                }else {
                    AppClient.setTime(Integer.parseInt(t1));
                    AppClient.setZhi(1);
                }

                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("set_factory_fbzp")
                        .setJsonObject("bh","202002211")
                        .setJsonObject("zt","1")
                        .setJsonObject("name",name.getText().toString())
                        .setJsonObject("xl",xl.getSelectedItem().toString())
                        .setJsonObject("hy",hy.getText().toString())
                        .setJsonObject("szd",address.getText().toString())
                        .setJsonObject("gzdz", commAddress.getText().toString())
                        .setJsonObject("tel", tel.getText().toString())
                        .setJsonObject("email", email.getText().toString())
                        .setJsonObject("gw", gw.getText().toString()+"-"+AppClient.getTime()+AppClient.getZhi()+"")
                        .setJsonObject("xz", gz.getText().toString())
                        .setJsonObject("zyyq", zyyq.getText().toString())
                        .setJsonObject("gzjlyq", gzjlyz.getText().toString())
                        .setJsonObject("gwyq", gzyq.getText().toString())
                        .setJsonObject("shr", "无")
                        .setJsonObject("shsj", "无")
                        .setJsonObject("time", t)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Toast.makeText(FbzpActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
/*     int i=1;
                Date date=new Date(System.currentTimeMillis());
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
                Cursor cursor = db.query("fbzp", null, null, null, null, null, null);
                while (cursor.moveToNext()){
                    i++;
                }
                String number=i+"";
                ContentValues values=new ContentValues();
                if (i<10){
                    number="0"+i;
                }
                values.put("id",dateFormat.format(date)+number);
                values.put("address",);
                values.put("tel",tel.getText().toString());
                values.put("email",email.getText().toString());
                values.put("gw",gw.getText().toString());
                values.put("xz",gz.getText().toString());
                values.put("zyyq",zyyq.getText().toString());
                values.put("jlyz",gzjlyz.getText().toString());
                values.put("gwyz",gzyq.getText().toString());
                values.put("com_address",commAddress.getText().toString());
                values.put("name",);
                db.insert("fbzp",number,values);*/