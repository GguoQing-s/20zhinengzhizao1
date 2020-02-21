package com.example.a20zhinengzhizao1.dialog;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.sql.ProviderInfor;
import com.example.a20zhinengzhizao1.sql.YlInfo;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class AddCommodityDialog extends DialogFragment {
    public static final int CHOOSE_PHOTO = 2;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_city)
    EditText etCity;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_leagelperson)
    EditText etLeagelperson;
    @BindView(R.id.et_contacts)
    EditText etContacts;
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.open)
    Button open;
    @BindView(R.id.photo)
    ImageView photo;
    @BindView(R.id.submit)
    Button submit;
    Unbinder unbinder;
    @BindView(R.id.et_business)
    EditText etBusiness;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_gysbh)
    EditText etGysbh;
    @BindView(R.id.et_ylbh)
    EditText etYlbh;
    @BindView(R.id.et_ylname)
    EditText etYlname;

    private ReflashListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addcommodity_dialog, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void setListener(ReflashListener listener) {
        this.listener = listener;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProviderInfor infor = new ProviderInfor();
                YlInfo ylInfo = new YlInfo();
                infor.setNumber(Integer.parseInt(etGysbh.getText().toString()));
                infor.setAddress(etAddress.getText().toString());
                infor.setBusiness(etBusiness.getText().toString());
                infor.setCity(etCity.getText().toString());
                infor.setContacts(etContacts.getText().toString());
                infor.setLegal_person(etLeagelperson.getText().toString());
                infor.setName(etName.getText().toString());
                infor.setTel(etTel.getText().toString());
                byte[] bytes = toByteArray(photo);
                ylInfo.setPrice(Double.parseDouble(etPrice.getText().toString()));
                ylInfo.setProviderNumber(Integer.parseInt(etYlbh.getText().toString()));
                ylInfo.setYlName(etYlname.getText().toString());
                ylInfo.setYlPhoto(bytes);

                ylInfo.save();
                infor.save();
                Log.d("ccccccccccccccc", "onClick: "+infor.getNumber());
                listener.reflash();
                dismiss();
            }
        });
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });
    }

    private byte[] toByteArray(ImageView photo) {
        Bitmap bm = ((BitmapDrawable) photo.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] datas = baos.toByteArray();
        return datas;
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
                Log.d("pppppppp", "onRequestPermissionsResult: " + requestCode);
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(getContext(), "权限访问失败", Toast.LENGTH_SHORT).show();
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
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            photo.setImageBitmap(bitmap);
        } else {
            Toast.makeText(getContext(), "获取图片失败", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface ReflashListener {
        void reflash();
    }
}
