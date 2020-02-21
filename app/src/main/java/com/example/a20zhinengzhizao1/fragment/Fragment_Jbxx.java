package com.example.a20zhinengzhizao1.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.AppClient;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;
import com.example.a20zhinengzhizao1.sql.Mysql;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class Fragment_Jbxx extends Fragment {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.man)
    RadioButton man;
    @BindView(R.id.woman)
    RadioButton woman;
    @BindView(R.id.zy)
    EditText zy;
    @BindView(R.id.school)
    EditText school;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.birthday)
    EditText birthday;
    @BindView(R.id.jg)
    EditText jg;
    @BindView(R.id.experience)
    EditText experience;
    @BindView(R.id.yx)
    EditText yx;
    @BindView(R.id.hj)
    EditText hj;
    @BindView(R.id.select)
    Button select;
    @BindView(R.id.image)
    ImageView image;
    Unbinder unbinder;
    @BindView(R.id.upload)
    Button upload;
    private final int CHOOSE_PHOTO = 2;
    @BindView(R.id.xl)
    Spinner xl;
    @BindView(R.id.edit)
    Button edit;
    private Bitmap bitmap;
    private Mysql mysql;
    private SQLiteDatabase database;
    private String path, sex;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jbxx, null);
        unbinder = ButterKnife.bind(this, view);
        mysql = new Mysql(getContext(), "jbxx.db", null, 1);
        database = mysql.getWritableDatabase();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListener();

    }

    private void update() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("update_factory_information")
                .setJsonObject("name",name.getText().toString())
                .setJsonObject("sex",sex)
                .setJsonObject("zy",zy.getText().toString())
                .setJsonObject("xx",school.getText().toString())
                .setJsonObject("email",email.getText().toString())
                .setJsonObject("tel",tel.getText().toString())
                .setJsonObject("csrq",birthday.getText().toString())
                .setJsonObject("jg",jg.getText().toString())
                .setJsonObject("xl",xl.getSelectedItem().toString())
                .setJsonObject("gzjl",experience.getText().toString())
                .setJsonObject("jyxx",yx.getText().toString())
                .setJsonObject("hj",hj.getText().toString())
                .setJsonObject("path",path)
                .setJsonObject("user",AppClient.getName())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S"))
                        {
                            Toast.makeText(getContext(), "上传成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void add() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("set_factory_information")
                .setJsonObject("name",name.getText().toString())
                .setJsonObject("sex",sex)
                .setJsonObject("zy",zy.getText().toString())
                .setJsonObject("xx",school.getText().toString())
                .setJsonObject("email",email.getText().toString())
                .setJsonObject("tel",tel.getText().toString())
                .setJsonObject("csrq",birthday.getText().toString())
                .setJsonObject("jg",jg.getText().toString())
                .setJsonObject("xl",xl.getSelectedItem().toString())
                .setJsonObject("gzjl",experience.getText().toString())
                .setJsonObject("jyxx",yx.getText().toString())
                .setJsonObject("hj",hj.getText().toString())
                .setJsonObject("path",path)
                .setJsonObject("user",AppClient.getName())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S"))
                        {
                            Toast.makeText(getContext(), "上传成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void huoqu() {
        VolleyTo volleyTo  =new VolleyTo();
        volleyTo.setUrl("get_factory_information")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        if (jsonArray.length()==0)
                        {
                            add();
                        }

                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            if (jsonObject1.optString("user").equals(AppClient.getName()))
                            {
                                update();
                            }else {
                                add();
                            }
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setListener() {
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    Log.d("lllllllllllllllll", "onClick: ");
                    openAlbum();
                }
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set(false);

                if (man.isChecked()) {
                    sex = man.getText().toString();
                } else {
                    sex = woman.getText().toString();
                }
                huoqu();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set(true);
            }
        });

    }

    private void set( boolean flag) {
        name.setEnabled(flag);
        woman.setEnabled(flag);
        man.setEnabled(flag);
        school.setEnabled(flag);
        xl.setEnabled(flag);
        experience.setEnabled(flag);
        yx.setEnabled(flag);
        hj.setEnabled(flag);
        email.setEnabled(flag);
        tel.setEnabled(flag);
        zy.setEnabled(flag);
        birthday.setEnabled(flag);
        jg.setEnabled(flag);
        image.setEnabled(flag);
    }

    private byte[] picTobyte(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(getContext(), "权限被禁止", Toast.LENGTH_SHORT).show();
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
        if (DocumentsContract.isDocumentUri(getContext(), uri)) {
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
            path=imagePath;
            Log.d("00000000000000", "displayImage: -------"+imagePath);
            image.setImageBitmap(bitmap);
        } else {
            Toast.makeText(getContext(), "图片获取失败", Toast.LENGTH_SHORT).show();
        }
    }
}
