package com.example.a20zhinengzhizao1.dialog;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioButton;


import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Jbxx;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by Administrator on 2019/10/8 0008.
 */

@SuppressLint("ValidFragment")
public class TsDialog extends DialogFragment {


    @BindView(R.id.tongzhi)
    RadioButton tongzhi;
    @BindView(R.id.duanxin)
    RadioButton duanxin;
    @BindView(R.id.dianhua)
    RadioButton dianhua;
    private AppClient mApp;
    private List<Jbxx> jbxxes;
    private Unbinder mUnbinder;
    private String tel;
    private List<String> numbersArrayList;
    private int index=0;
    public TsDialog(String tel)
    {
        this.tel=tel;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.tsdialog, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inview();

        tongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=0;i<jbxxes.size();i++)
                {
                    Jbxx jbxx = jbxxes.get(i);
                    if (jbxx.isXz())
                    {
                        tz(i,jbxx.getName());
                    }
                }
                getDialog().dismiss();
            }
        });
        duanxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Uri smsToUri = Uri.parse("smsto:13563521293");
                Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
                intent.putExtra("sms_body", "88888888888");
                startActivity(intent);*/

                numbersArrayList = new ArrayList<>();
                for (int i=0;i<jbxxes.size();i++)
                {
                    Jbxx jbxx = jbxxes.get(i);
                    if (jbxx.isXz())
                    {
                        dx(jbxx.getTel());
                    }
                }




            }
        });
        if (index>1)
        {
            dianhua.setEnabled(false);
        }else {
            dianhua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + tel);
                    intent.setData(data);
                    startActivity(intent);
                    getDialog().dismiss();
                }
            });
        }
    }

    private void dx(String tel) {

        numbersArrayList.add(tel);
        String phoneNumber = "";
        for (String response : numbersArrayList) {
            phoneNumber = phoneNumber + response + ";";
        }
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        startActivity(intent);
    }

    private void tz(int id,String name) {
        Log.d("9999999999", "tz: ------"+id);
        NotificationManager manager  = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(getActivity())
                .setContentTitle("通知")
                .setContentText(name+"已同意您的应聘")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setAutoCancel(false)
                .build();
        manager.notify(id,notification);
    }

    private void inview() {
        mApp = (AppClient) getActivity().getApplication();
        jbxxes = mApp.getMjbxx();
        for (int i=0;i<jbxxes.size();i++)
        {
            Jbxx jbxx = jbxxes.get(i);
            if (jbxx.isXz())
            {
                index++;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
