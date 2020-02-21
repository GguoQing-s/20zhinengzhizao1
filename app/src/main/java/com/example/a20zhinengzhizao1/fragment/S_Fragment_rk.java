package com.example.a20zhinengzhizao1.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.activity.S_CKLSActivity;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class S_Fragment_rk extends Fragment {

    @BindView(R.id.ylmc)
    EditText ylmc;
    @BindView(R.id.xh)
    EditText xh;
    @BindView(R.id.gys)
    EditText gys;
    @BindView(R.id.shuliang)
    EditText shuliang;
    @BindView(R.id.dj)
    EditText dj;
    @BindView(R.id.weizhi)
    EditText weizhi;
    @BindView(R.id.cgy)
    EditText cgy;
    @BindView(R.id.lxr)
    EditText lxr;
    @BindView(R.id.dfzh)
    EditText dfzh;
    @BindView(R.id.ren)
    EditText ren;
    @BindView(R.id.rksj)
    EditText rksj;
    @BindView(R.id.shangchuan)
    Button shangchuan;
    @BindView(R.id.im)
    ImageView im;
    @BindView(R.id.qd)
    Button qd;
    Unbinder unbinder;
    @BindView(R.id.ckls)
    Button ckls;
    private Bitmap bitmap;
    private String path="";
    private static final int CHOOSE_PHOTO = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.s_fragment_rk, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @OnClick({R.id.shangchuan, R.id.qd, R.id.ckls})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shangchuan:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
                break;
            case R.id.qd:
                if (ylmc.getText().toString().equals("")||xh.getText().toString().equals("")||
                        gys.getText().toString().equals("")||
                        shuliang.getText().toString().equals("")||
                        dj.getText().toString().equals("")||
                        weizhi.getText().toString().equals("")||
                        cgy.getText().toString().equals("")||
                        lxr.getText().toString().equals("")||
                        dfzh.getText().toString().equals("")||
                        ren.getText().toString().equals("")||
                        rksj.getText().toString().equals("")||
                        path.equals("")
                )
                {
                    Toast.makeText(getActivity(),"内容不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                VolleyTo volleyTo  =new VolleyTo();
                volleyTo.setUrl("set_stock_warehousing")
                        .setJsonObject("name",ylmc.getText().toString())
                        .setJsonObject("xh",xh.getText().toString())
                        .setJsonObject("gys",gys.getText().toString())
                        .setJsonObject("shuliang",shuliang.getText().toString())
                        .setJsonObject("dj",dj.getText().toString())
                        .setJsonObject("weizhi",weizhi.getText().toString())
                        .setJsonObject("caigoyuan",cgy.getText().toString())
                        .setJsonObject("lianxiren",lxr.getText().toString())
                        .setJsonObject("zhanghao",dfzh.getText().toString())
                        .setJsonObject("ren",ren.getText().toString())
                        .setJsonObject("time",rksj.getText().toString())
                        .setJsonObject("path",path)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Toast.makeText(getActivity(),"添加成功",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();
                break;
            case R.id.ckls:
                startActivity(new Intent(getActivity(), S_CKLSActivity.class));
                break;
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(getActivity(), "权限被禁止", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
        }
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(getActivity(), uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                imagePath = getImagePath(uri, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                imagePath = uri.getPath();
            }
            displayImage(imagePath);
        }
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getActivity().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }

        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            bitmap = BitmapFactory.decodeFile(imagePath);
            path = imagePath;
            im.setImageBitmap(bitmap);
        } else {
            Toast.makeText(getActivity(), "图片获取失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
