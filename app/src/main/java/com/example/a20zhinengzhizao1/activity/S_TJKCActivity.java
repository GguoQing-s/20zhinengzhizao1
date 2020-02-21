package com.example.a20zhinengzhizao1.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.net.VolleyLo;
import com.example.a20zhinengzhizao1.net.VolleyTo;

import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class S_TJKCActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.mc)
    EditText mc;
    @BindView(R.id.xh)
    EditText xh;
    @BindView(R.id.jb)
    EditText jb;
    @BindView(R.id.cs)
    EditText cs;
    @BindView(R.id.hbbz)
    EditText hbbz;
    @BindView(R.id.sssj)
    EditText sssj;
    @BindView(R.id.jg)
    EditText jg;
    @BindView(R.id.video)
    Button video;
    @BindView(R.id.video1)
    EditText video1;
    @BindView(R.id.image)
    Button image;
    @BindView(R.id.image1)
    EditText image1;
    @BindView(R.id.sl)
    EditText sl;
    @BindView(R.id.sms)
    EditText sms;
    @BindView(R.id.cspz)
    EditText cspz;
    @BindView(R.id.tj)
    Button tj;
    @BindView(R.id.spinner)
    Spinner spinner;
    private String clmc1, clxh1, jb1, cs1, lx1, hbbz1, sssj1, jg1, sl1, video2, image2, sms1, cppz1;
    private static final int FILE_SELECT_CODE = 1;
    private final int CHOOSE_PHOTO = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_tjkcactivity);
        ButterKnife.bind(this);
        title.setText("添加库存");
        if (ContextCompat.checkSelfPermission(S_TJKCActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(S_TJKCActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);//判断你是否授权
        } else {
            inintVideoPath();
        }
    }

    private void inintVideoPath() {
        ////打开软件直接播放的视频名字是movie.mp4
        File file = new File(Environment.getExternalStorageDirectory(), "movie.mp4");
    }

    private void huoqu() {
        VolleyTo volleyTo = new VolleyTo();

        volleyTo.setUrl("set_vehiclea")
                .setJsonObject("name",mc.getText().toString())
                .setJsonObject("clxh",xh.getText().toString())
                .setJsonObject("jb",jb.getText().toString())
                .setJsonObject("cs",cs.getText().toString())
                .setJsonObject("lx",spinner.getSelectedItem().toString())
                .setJsonObject("hbbz",hbbz.getText().toString())
                .setJsonObject("sssj",sssj.getText().toString())
                .setJsonObject("jg",jg.getText().toString())
                .setJsonObject("sl",sl.getText().toString())
                .setJsonObject("video",video1.getText().toString())
                .setJsonObject("image",image1.getText().toString())
                .setJsonObject("sms",sms.getText().toString())
                .setJsonObject("pz",cspz.getText().toString())

                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    @OnClick({R.id.change, R.id.tj, R.id.video, R.id.image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                finish();
                break;
            case R.id.tj:
                huoqu();
                break;
            case R.id.video:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");//设置类型，这是任意类型
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
                break;
            case R.id.image:
                if (ContextCompat.checkSelfPermission(S_TJKCActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(S_TJKCActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    Log.d("lllllllllllllllll", "onClick: ");
                    openAlbum();
                }
                break;
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

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
            default:
                Uri uri = data.getData();
                video2 = uri + "";
                video1.setText(video2);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(S_TJKCActivity.this, "权限被禁止", Toast.LENGTH_SHORT).show();
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
        if (DocumentsContract.isDocumentUri(S_TJKCActivity.this, uri)) {
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
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
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
            image2 = imagePath;
            image1.setText(image2);
            Log.d("+++++++", "displayImage: -------" + imagePath);
        } else {
            Toast.makeText(S_TJKCActivity.this, "图片获取失败", Toast.LENGTH_SHORT).show();
        }
    }

}
