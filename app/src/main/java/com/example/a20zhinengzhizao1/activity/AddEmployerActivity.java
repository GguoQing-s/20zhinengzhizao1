package com.example.a20zhinengzhizao1.activity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.entity.ContactInfor;

import org.litepal.LitePal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddEmployerActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.man)
    RadioButton man;
    @BindView(R.id.woman)
    RadioButton woman;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.select)
    Button select;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.spinner_scx)
    Spinner spinnerScx;
    @BindView(R.id.spinner_zw)
    Spinner spinnerZw;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.delete)
    Button delete;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addinfor);
        ButterKnife.bind(this);
        more.setOnClickListener(this);
        add.setOnClickListener(this);
        select.setOnClickListener(this);
        delete.setOnClickListener(this);

        LitePal.getDatabase();
       String name = getIntent().getStringExtra("name");
        if (name != null) {
            initData();
        }
    }

    private void initData() {
        etAddress.setText(getIntent().getStringExtra("address"));
        etEmail.setText(getIntent().getStringExtra("email"));
        etPhone.setText(getIntent().getStringExtra("tel"));
        etName.setText(getIntent().getStringExtra("name"));
        if (getIntent().getStringExtra("sex").equals("男")) {
            man.setChecked(true);
        } else {
            woman.setChecked(true);
        }
        tvBirthday.setText(getIntent().getStringExtra("birthday"));
    }

    @Override
    public void onClick(View v) {
        String address = null;
        String name = etName.getText().toString();
        String birthday = tvBirthday.getText().toString();
        String tel = etPhone.getText().toString();
        String scx = spinnerScx.getSelectedItem().toString();
        String zw = spinnerZw.getSelectedItem().toString();
        String email = null;
        String sex = null;
        switch (v.getId()) {
            case R.id.more:
                layout.setVisibility(View.VISIBLE);
                break;
            case R.id.add:

                if (man.isChecked()) {
                    sex = "男";
                } else {
                    sex = "女";
                }
                if (!TextUtils.isEmpty(etEmail.getText().toString())) {
                    email = etEmail.getText().toString();
                } else {
                    email = "";
                }
                if (!TextUtils.isEmpty(etAddress.getText().toString())) {
                    address = etAddress.getText().toString();
                } else {
                    address = "";
                }
                if (name != null) {
                    ContactInfor infor = new ContactInfor(name, sex, birthday, tel, scx, zw, email, address);
                    infor.save();
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put("Name", name);
                    values.put("Address", address);
                    values.put("Email", email);
                    values.put("Birthday", birthday);
                    values.put("Post", zw);
                    values.put("Tel", tel);
                    values.put("Sex", sex);
                    values.put("Produce", scx);

                    LitePal.updateAll(ContactInfor.class, values);
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.select:
                DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvBirthday.setText(year + "年" + month + "月" + dayOfMonth + "日");
                    }
                }, 2020, 2, 5);
                dialog.show();
                break;
            case R.id.delete:
                if (name != null) {
                    LitePal.deleteAll(ContactInfor.class, "name=?", name);
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void onBackPressed() {
      startActivity(new Intent(this, Z_YGXXActivity.class));
    }
}
