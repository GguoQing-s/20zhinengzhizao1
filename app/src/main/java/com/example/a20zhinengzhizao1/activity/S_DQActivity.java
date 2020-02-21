package com.example.a20zhinengzhizao1.activity;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20zhinengzhizao1.DemoBean;
import com.example.a20zhinengzhizao1.ExcelUtil;
import com.example.a20zhinengzhizao1.R;
import com.example.a20zhinengzhizao1.bean.Ygxx;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class S_DQActivity extends AppCompatActivity {
    @BindView(R.id.change)
    ImageView change;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.sz)
    TextView sz;
    private List<Ygxx> mygxx;
    private String filePath = "/sdcard";
    private String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_dqactivity);
        ButterKnife.bind(this);
        mygxx = new ArrayList<>();
       // createExcel();
         readExcel();//读取表中的数据
        // writeExcel("mnt/sdcard/test5.xls");
        //  addExcel();
        // writeToExcel();
        //exportExcel(this);
        // writeToExcel("李四","男");//向已有数据的数据表添加数据
    }
    public void writeToExcel(String name, String gender) {
        try {


            //每次插入数据,都要取原来的表,然后新建一个表,然后将原来的表的内容添加到新表上.但只要两个路径相同的话,效果相当于在原来的表添加.
            Workbook oldWwb = Workbook.getWorkbook(new File("mnt/sdcard/test.xls"));
            WritableWorkbook  wwb = Workbook.createWorkbook(new File("mnt/sdcard/test.xls"), oldWwb);
            //获取指定索引的表格
            WritableSheet ws = wwb.getSheet(0);
            // 获取该表格现有的行数
            int row = ws.getRows();
            Label lbl1 = new Label(0, row, name);
            Label bll2 = new Label(1, row, gender);
            ws.addCell(lbl1);
            ws.addCell(bll2);
            // 从内存中写入文件中,只能刷一次.
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //存入数据库

    private void exportExcel(Context context) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String excelFileName = "/test12.xls";
        String[] title = {"姓名", "年龄", "男孩"};
        String sheetName = "demoSheetName";
        List<DemoBean> demoBeanList = new ArrayList<>();
        DemoBean demoBean1 = new DemoBean("张三1", 10, true);
        DemoBean demoBean2 = new DemoBean("小红1", 12, false);
        DemoBean demoBean3 = new DemoBean("李四1", 18, true);
        DemoBean demoBean4 = new DemoBean("王香1", 13, false);
        demoBeanList.add(demoBean1);
        demoBeanList.add(demoBean2);
        demoBeanList.add(demoBean3);
        demoBeanList.add(demoBean4);
        filePath = filePath + excelFileName;
        ExcelUtil.initExcel(filePath, sheetName, title);
       // ExcelUtil.writeObjListToExcel(demoBeanList, filePath, context);
    }


    public void writeToExcel() {
        try {

            //每次插入数据,都要取原来的表,然后新建一个表,然后将原来的表的内容添加到新表上.但只要两个路径相同的话,效果相当于在原来的表添加.
            Workbook oldWwb = Workbook.getWorkbook(new File("mnt/sdcard/test1.xls"));
            WritableWorkbook wwb = Workbook.createWorkbook(new File("mnt/sdcard/test1.xls"), oldWwb);
            //获取指定索引的表格
            WritableSheet ws = wwb.getSheet(0);
            // 获取该表格现有的行数
            int row = ws.getRows();
            Label lbl1 = new Label(0, 0, "张三");
            Label bll2 = new Label(1, 0, "李四");
            ws.addCell(lbl1);
            ws.addCell(bll2);
            // 从内存中写入文件中,只能刷一次.
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addExcel() {
    }

    public void readExcel() {
        Log.d("8888888888888888888888", "readExcel: ----"+path);
        try {
            /**
             * 后续考虑问题,比如Excel里面的图片以及其他数据类型的读取
             **/
            InputStream is = new FileInputStream("mnt/sdcard/test10.xls");

            Workbook book = Workbook.getWorkbook(new File(path));
            book.getNumberOfSheets();
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            int Cols = sheet.getColumns();
            System.out.println("当前工作表的名字:" + sheet.getName());
            System.out.println("总行数Rows:" + Rows);
            System.out.println("总列数Cols:" + Cols);

         /*   for (int i=1;i<Rows;i++)
            {
                String name =(sheet.getCell(0, i)).getContents();
                String sex =(sheet.getCell(1, i)).getContents();
                String csrq =(sheet.getCell(2, i)).getContents();
                String tel =(sheet.getCell(3, i)).getContents();
                String scx =(sheet.getCell(4, i)).getContents();
                String zw =(sheet.getCell(5, i)).getContents();
                String email =(sheet.getCell(6, i)).getContents();
                String jtzz =(sheet.getCell(7, i)).getContents();
                mygxx.add(new Ygxx(name,sex,csrq,tel,scx,zw,email,jtzz));
            }
            Log.d("0000000000000000000000", "readExcel: --------"+mygxx);*/

            for (int j = 0; j < Rows; ++j) {
                for (int i = 0; i < Cols; ++i) {
                    // getCell(Col,Row)获得单元格的值
                    System.out.print("-----" + (sheet.getCell(i, j)).getContents() + "\t");
                }
                System.out.print("\n");
            }
            /*  for (int i = 0; i < Cols; ++i) {
                for (int j = 0; j < Rows; ++j){
                    // getCell(Col,Row)获得单元格的值
                    System.out.print("-----"+(sheet.getCell(i, j)).getContents() + "\t");
                }
                System.out.print("\n");
            }*/

            /*
            // 得到第一列第一行的单元格
            Cell cell1 = sheet.getCell(2, 0);//(列数值，行数值)
            String result = cell1.getContents();
            System.out.println("数据："+result);*/
            book.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createExcel() {
        try {
            // 创建或打开Excel文件
            WritableWorkbook book = Workbook.createWorkbook(new File("mnt/sdcard/test6.xls"));

            // 生成名为“第一页”的工作表,参数0表示这是第一页
            WritableSheet sheet1 = book.createSheet("第一页", 0);
            WritableSheet sheet2 = book.createSheet("第三页", 2);

            // 在Label对象的构造函数中,元格位置是第一列第一行(0,0)以及单元格内容为test
            Label label = new Label(0, 1, "test");

            // 将定义好的单元格添加到工作表中
            sheet1.addCell(label);

            /*
             * 生成一个保存数字的单元格.必须使用Number的完整包路径,否则有语法歧义
             */
            jxl.write.Number number = new jxl.write.Number(1, 0, 555.12541);
            sheet2.addCell(number);

            // 写入数据并关闭文件
            book.write();
            book.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * jxl暂时不提供修改已经存在的数据表,这里通过一个小办法来达到这个目的,不适合大型数据更新! 这里是通过覆盖原文件来更新的.
     *
     * @param filePath
     */
    public void updateExcel(String filePath) {
        try {
            Workbook rwb = Workbook.getWorkbook(new File(filePath));
            WritableWorkbook wwb = Workbook.createWorkbook(new File(
                    "d:/new.xls"), rwb);// copy
            WritableSheet ws = wwb.getSheet(0);
            WritableCell wc = ws.getWritableCell(0, 0);
            // 判断单元格的类型,做出相应的转换
            Label label = (Label) wc;
            label.setString("The value has been modified");
            wwb.write();
            wwb.close();
            rwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeExcel(String filePath) {
        try {

            // 创建工作薄
            WritableWorkbook wwb = Workbook.createWorkbook(new File(filePath));
            // 创建工作表
            WritableSheet ws = wwb.createSheet("Sheet1", 0);
            // 添加标签文本
            Random rnd = new Random((new Date()).getTime());
            int forNumber = rnd.nextInt(100);
            Label label = new Label(0, 0, "test");
            for (int i = 0; i < 3; i++) {
                ws.addCell(label);
                ws.addCell(new Number(rnd.nextInt(50), rnd
                        .nextInt(50), rnd.nextInt(1000)));
            }
            // 添加图片(注意此处jxl暂时只支持png格式的图片)
            // 0,1分别代表x,y 2,5代表宽和高占的单元格数
          //  ws.addImage(new WritableImage(5, 5, 2, 5, new File(
                //    "mnt/sdcard/nb.png")));
            wwb.write();
            wwb.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @OnClick(R.id.sz)
    public void onViewClicked() {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(intent,1000);
        }catch (Exception e){
            Toast.makeText(S_DQActivity.this, "请安装资源管理器", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if ("file".equalsIgnoreCase(uri.getScheme())){//使用第三方应用打开
                path = uri.getPath();
             //   file.setText(path);
                readExcel();
                Toast.makeText(this,path+"11111", Toast.LENGTH_SHORT).show();
                return;
            }
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
                path = getPath(this, uri);
               // file.setText(path);
                readExcel();
                Toast.makeText(this,path, Toast.LENGTH_SHORT).show();
            } else {//4.4以下下系统调用方法
                path = getRealPathFromURI(uri);
               // file.setText(path);
                readExcel();
                Toast.makeText(S_DQActivity.this, path+"222222", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(null!=cursor&&cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
            cursor.close();
        }
        return res;
    }

    /**
     * 专为Android4.4设计的从Uri获取文件绝对路径，以前的方法已不好使
     */
    @SuppressLint("NewApi")
    public String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public String getDataColumn(Context context, Uri uri, String selection,
                                String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }




}
