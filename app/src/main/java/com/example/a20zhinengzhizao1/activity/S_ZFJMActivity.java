package com.example.a20zhinengzhizao1.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Wddd;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_ZFJMActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.bt)
    TextView bt;
    @BindView(R.id.ewm)
    ImageView ewm;
    private String url,url1;
    private boolean is=true;
    private AppClient mApp;
    private List<Wddd> myzf;
    private String xh="",sl="",name="",je="";
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Random random= new Random();
            url1=url;
            url1+=url1+random.nextInt(10);
            Crean(url1);
            return false;
        }
    });
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_zfjmactivity);
        ButterKnife.bind(this);
        xh = getIntent().getStringExtra("xh");
        sl = getIntent().getStringExtra("sl");
        name=getIntent().getStringExtra("name");
        je=getIntent().getStringExtra("jg");
     //   Log.d("1111111111", "onCreate: -----"+xh+"   "+sl);
        url="车辆名称："+getIntent().getStringExtra("name")+"      付款金额："+getIntent().getStringExtra("jg");
        inview();
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                   try {
                       handler.sendEmptyMessage(0);
                       Thread.sleep(5000);
                   }catch (Exception e)
                   {
                       e.printStackTrace();
                   }
                }while (is);
            }
        }).start();
        setdianji();


    }

    private void addData(String name,String je,String time) {
        VolleyTo volleyTo  =new VolleyTo();
        volleyTo.setUrl("set_order")
                .setJsonObject("name",name)
                .setJsonObject("jine",je)
                .setJsonObject("time",time)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void updata(final String name, String xh, String sl) {
        VolleyTo volleyTo  =new VolleyTo();
        volleyTo.setUrl("update_vehicle")
                .setJsonObject("name",name)
                .setJsonObject("clxh",xh)
                .setJsonObject("sl",sl)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setdianji() {
        ewm.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SimpleDateFormat format  =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                String t= format.format(date);
                bt.setText(url);
                Toast.makeText(S_ZFJMActivity.this,"支付成功！",Toast.LENGTH_LONG).show();
                String[] body1= name.split(",");
                String[] body2= xh.split(",");
                String[] body3= sl.split(",");
                addData(name,je,t);
                for (int i=0;i<body1.length;i++)
                {
                    updata(body1[i],body2[i],body3[i]);
                }
                return true;
            }
        });
    }

    private void Crean(String url) {
        Hashtable<EncodeHintType,String> hashtable  =new Hashtable<>();
        hashtable.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        try {
            BitMatrix bitMatrix  =new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE,300,300,hashtable);
            int[] arr = new int[300*300];
            for (int i=0;i<300;i++)
            {
                for (int j=0;j<300;j++)
                {
                    if (bitMatrix.get(i,j))
                    {
                        arr[j*300+i]=0xff000000;
                    }else {
                        arr[j*300+i]=0xffffffff;
                    }
                }
            }
            Bitmap bitmap  =Bitmap.createBitmap(300,300,Bitmap.Config.ARGB_8888);
            bitmap.setPixels(arr,0,300,0,0,300,300);
            ewm.setImageBitmap(bitmap);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void inview() {
        mApp = (AppClient) getApplication();
        myzf = mApp.getMyzf();
        title.setText("支付界面");
    }

    @OnClick(R.id.change)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        is=false;
    }
}
