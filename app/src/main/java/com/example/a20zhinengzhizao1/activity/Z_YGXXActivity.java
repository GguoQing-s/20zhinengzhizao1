package com.example.a20zhinengzhizao1.activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20zhinengzhizao1.ExcelUtil;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.adapter.EmployerAdapter;
import com.example.a20zhinengzhizao1.entity.ContactInfor;
import com.example.a20zhinengzhizao1.user.IndexView;

import org.litepal.LitePal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class Z_YGXXActivity extends AppCompatActivity {

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.tv_words)
    IndexView tvWords;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.search)
    Button search;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.setting)
    Button setting;
    @BindView(R.id.deleteAll)
    Button deleteAll;
    @BindView(R.id.selectAll)
    Button selectAll;
    @BindView(R.id.layout2)
    LinearLayout layout2;
    @BindView(R.id.daoru)
    Button daoru;
    @BindView(R.id.export)
    Button export;
    private EmployerAdapter adapter;
    private String filePath = "/sdcard/AndroidExcelDemo";
    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private int REQUEST_PERMISSION_CODE = 1000;
    private Handler handler = new Handler();
    private List<ContactInfor> mlist;
    private List<ContactInfor> search_List;
    private boolean flag = false;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_ygxxactivity);
        ButterKnife.bind(this);
        LitePal.getDatabase();
        requestPermission();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        daoru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readExcel();
            }
        });

        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportExcel(Z_YGXXActivity.this);
            }
        });

        tvWords.setListener(new IndexView.OnIndexChangeListener() {
            @Override
            public void onIndexChange(String word) {
                upDateWord(word);
                upDateListView(word);
            }
        });
        initData();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_List = new ArrayList<>();
                String Name = etSearch.getText().toString();
                for (int i = 0; i < mlist.size(); i++) {
                    ContactInfor infor = mlist.get(i);
                    if (infor.getName().equals(Name)) {
                        search_List.add(infor);
                    }
                }
                lv.setAdapter(new EmployerAdapter(Z_YGXXActivity.this, 0, search_List));
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Z_YGXXActivity.this, AddEmployerActivity.class);

                startActivity(intent);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Z_YGXXActivity.this, AddEmployerActivity.class);
                intent.putExtra("name", mlist.get(position).getName());
                intent.putExtra("address", mlist.get(position).getAddress());
                intent.putExtra("birthday", mlist.get(position).getBirthDay());
                intent.putExtra("email", mlist.get(position).getEmail());
                intent.putExtra("zw", mlist.get(position).getPost());
                intent.putExtra("scx", mlist.get(position).getProduce());
                intent.putExtra("sex", mlist.get(position).getSex());
                intent.putExtra("tel", mlist.get(position).getTel());
                startActivity(intent);
                finish();
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mlist.size(); i++) {
                    mlist.get(i).setVisiable(false);
                }

                if (flag == false) {
                    layout2.setVisibility(View.VISIBLE);
                    flag = true;

                    for (int i = 0; i < mlist.size(); i++) {
                        mlist.get(i).setVisiable(true);

                    }
                    adapter = new EmployerAdapter(Z_YGXXActivity.this, 0, mlist);
                    lv.setAdapter(adapter);
                    adapter.setListener(new EmployerAdapter.CheckListener() {
                        @Override
                        public void checkChange(int position) {
                            mlist.get(position).setCheck("true");
                            ContentValues values = new ContentValues();

                            for (int i = 0; i < mlist.size(); i++) {
                                ContactInfor infor = mlist.get(i);
                                if (infor.isCheck().equals("true")) {
                                    values.put("isCheck", "true");
                                    LitePal.updateAll(ContactInfor.class, values);
                                }
                            }

                        }
                    });

                } else {
                    layout2.setVisibility(View.GONE);
                    flag = false;

                    for (int i = 0; i < mlist.size(); i++) {
                        mlist.get(i).setVisiable(false);
                        Log.d("11111111111111", mlist.get(i).isVisiable() + "");
                    }
                    adapter = new EmployerAdapter(Z_YGXXActivity.this, 0, mlist);
                    lv.setAdapter(adapter);
                    adapter.setListener(new EmployerAdapter.CheckListener() {
                        @Override
                        public void checkChange(int position) {
                            mlist.get(position).setCheck("true");
                            ContentValues values = new ContentValues();

                            for (int i = 0; i < mlist.size(); i++) {
                                ContactInfor infor = mlist.get(i);
                                if (infor.isCheck().equals("true")) {
                                    values.put("isCheck", "true");
                                    LitePal.updateAll(ContactInfor.class, values);
                                }
                            }
                        }
                    });
                }

            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i = LitePal.deleteAll(ContactInfor.class, "isCheck=?", "true");
                Log.d("rrrrrrrrrrrrrr", "onClick: " + i);
                mlist = LitePal.findAll(ContactInfor.class);
                adapter = new EmployerAdapter(Z_YGXXActivity.this, 0, mlist);
                lv.setAdapter(adapter);


            }
        });

        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mlist.size(); i++) {
                    mlist.get(i).setCheck("true");
                }
                ReflashData();
            }
        });

    }


    private void initData() {
        mlist = LitePal.findAll(ContactInfor.class);
        adapter = new EmployerAdapter(this, 0, mlist);
        if (mlist.size() > 0) {

            lv.setAdapter(adapter);
            adapter.setListener(new EmployerAdapter.CheckListener() {
                @Override
                public void checkChange(int position) {
                    mlist.get(position).setCheck("true");
                    ContentValues values = new ContentValues();

                    for (int i = 0; i < mlist.size(); i++) {
                        ContactInfor infor = mlist.get(i);
                        if (infor.isCheck().equals("true")) {
                            values.put("isCheck", "true");
                            LitePal.updateAll(ContactInfor.class, values);
                        }
                    }
                }
            });

            ReflashData();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.improt:
                readExcel();
                break;

            case R.id.export:
                exportExcel(this);
                break;
        }
        return true;
    }


    private void exportExcel(Context context) {

        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        String excelFileName = "/contacts.xls";

        String[] title = {"姓名", "性别", "出生日期", "电话", "生产线", "职务", "邮箱", "家庭住址"};
        String sheetName = "demoSheetName";


        filePath = filePath + excelFileName;


        ExcelUtil.initExcel(filePath, sheetName, title);


        ExcelUtil.writeObjListToExcel(mlist, filePath, context);

        Toast.makeText(context, "excel已导出至：" + filePath, Toast.LENGTH_SHORT).show();

    }

    private void upDateWord(String word) {
        tvWord.setVisibility(View.VISIBLE);
        tvWord.setText(word);
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvWord.setVisibility(View.GONE);
            }
        }, 500);
    }

    private void upDateListView(String word) {
        for (int i = 0; i < mlist.size(); i++) {
            String list_word = mlist.get(i).getPy().substring(0, 1);
            if (word.equals(list_word)) {
                lv.setSelection(i);
                return;
            }
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT > 23) {
            if (ContextCompat.checkSelfPermission(Z_YGXXActivity.this,
                    permissions[0])
                    == PackageManager.PERMISSION_GRANTED) {
                //授予权限
                Toast.makeText(this, "用户之前已经授予了权限！", Toast.LENGTH_SHORT).show();
            } else {
                //未获得权限
                Toast.makeText(this, "未获得权限，现在申请！", Toast.LENGTH_SHORT).show();
                requestPermissions(permissions
                        , REQUEST_PERMISSION_CODE);
            }
        }

    }

    private void ReflashData() {
        mlist = LitePal.findAll(ContactInfor.class);
        adapter.notifyDataSetChanged();
    }

    public void readExcel() {
        try {
            /**
             * 后续考虑问题,比如Excel里面的图片以及其他数据类型的读取
             **/
//            InputStream is = new FileInputStream("mnt/sdcard/test.xls");
            Workbook book = Workbook
                    .getWorkbook(new File(filePath));
            book.getNumberOfSheets();
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            int Cols = sheet.getColumns();
            Log.d("aaaaaaaaaaaa", "readExcel: " + "当前工作表的名字:" + sheet.getName());
            Log.d("bbbbbbbbbbb", "readExcel: " + "总行数:" + Rows);
            Log.d("ccccccccccc", "readExcel: " + "总列数:" + Cols);
            List<String> list_name = new ArrayList<>();
            List<String> list_address = new ArrayList<>();
            List<String> list_sex = new ArrayList<>();
            List<String> list_scx = new ArrayList<>();
            List<String> list_zw = new ArrayList<>();
            List<String> list_email = new ArrayList<>();
            List<String> list_birthday = new ArrayList<>();
            List<String> list_tel = new ArrayList<>();
            List<String>[] array = new List[]{list_name, list_sex, list_birthday, list_tel, list_scx, list_zw, list_email, list_address};

            for (int i = 0; i < Cols; ++i) {
                List<String> list = array[i];
                for (int j = 1; j < Rows; ++j) {
                    list.add((sheet.getCell(i, j)).getContents());

                    Log.d("11111111111111111111", "获得单元格的值: " + (sheet.getCell(i, j)).getContents() + "\t" + i + "----" + j);
                }
                System.out.print("\n");
            }
            // 得到第一列第一行的单元格
            Cell cell1 = sheet.getCell(0, 0);
            String result = cell1.getContents();
            Log.d("2222222222222222", "第一列第一行: " + result + "\t");
            book.close();

            for (int i = 0; i < list_name.size(); i++) {
                Log.d("jjjjjjjjjjj", "readExcel: " + list_name.get(i));
                String name = list_name.get(i);
                String address = list_address.get(i);
                String sex = list_sex.get(i);
                String tel = list_tel.get(i);
                String scx = list_scx.get(i);
                String birthday = list_birthday.get(i);
                String zw = list_zw.get(i);
                String email = list_email.get(i);
                ContactInfor infor = new ContactInfor(name, sex, birthday, tel, scx, zw, email, address);
                infor.save();
            }

            mlist = LitePal.findAll(ContactInfor.class);
            adapter.notifyDataSetChanged();

        } catch (Exception e) {
            System.out.println(e);
        }


    }

}
