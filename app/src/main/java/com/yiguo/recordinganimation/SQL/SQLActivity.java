package com.yiguo.recordinganimation.SQL;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yiguo.recordinganimation.R;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import static com.yiguo.recordinganimation.SQL.StuDBHelper.AGE;
import static com.yiguo.recordinganimation.SQL.StuDBHelper.ID;
import static com.yiguo.recordinganimation.SQL.StuDBHelper.NAME;
import static com.yiguo.recordinganimation.SQL.StuDBHelper.NUM;
import static com.yiguo.recordinganimation.SQL.StuDBHelper.SEX;
import static com.yiguo.recordinganimation.SQL.StuDBHelper.TABLE_NAME;
import static com.yiguo.recordinganimation.SQL.StuDBHelper.VERSION;

public class SQLActivity extends AppCompatActivity {

    private Button createBtn;
    private Button updateBtn;
    private Button insertBtn;
    private Button ModifyBtn;
    private Button queryBtn;
    private Button deleteBtn;
    private StuDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        //创建一个表 包含 id name age sex 字段
        //  dbHelper = new StuDBHelper(SQLActivity.this, TABLE_NAME, null, 1);
        //版本号为2 表中增加一个num字段
        //  dbHelper = new StuDBHelper(SQLActivity.this, TABLE_NAME, null, 2);
        //版本号为3,表中删除name和num字段
        dbHelper = new StuDBHelper(SQLActivity.this, TABLE_NAME, null, 3);
        Toast.makeText(this, "当前数据库版本为" + VERSION, Toast.LENGTH_SHORT).show();
        creatView();
        setListener();
    }

    //通过findViewById获得Button对象的方法
    private void creatView() {
        createBtn = (Button) findViewById(R.id.createDatabase);
        updateBtn = (Button) findViewById(R.id.updateDatabase);
        insertBtn = (Button) findViewById(R.id.insert);
        ModifyBtn = (Button) findViewById(R.id.modify);
        queryBtn = (Button) findViewById(R.id.query);
        deleteBtn = (Button) findViewById(R.id.delete);
    }

    //为按钮注册监听的方法
    private void setListener() {
        createBtn.setOnClickListener(new CreateListener());
        updateBtn.setOnClickListener(new UpdateListener());
        insertBtn.setOnClickListener(new InsertListener());
        ModifyBtn.setOnClickListener(new ModifyListener());
        queryBtn.setOnClickListener(new QueryListener());
        deleteBtn.setOnClickListener(new DeleteListener());
    }

    //创建数据库的方法
    class CreateListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //创建StuDBHelper对象
            //得到一个可读的SQLiteDatabase对象
            SQLiteDatabase db = dbHelper.getReadableDatabase();
        }
    }

    //升级数据库的方法
    class UpdateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 数据库版本的更新,由原来的1变为2
            SQLiteDatabase db = dbHelper.getWritableDatabase();

        }
    }

    //插入数据的方法
    class InsertListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //得到一个可写的数据库
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            for (int i = 0; i < 10; i++) {
                //生成ContentValues对象 //key:列名，value:想插入的值
                ContentValues cv = new ContentValues();
                //往ContentValues对象存放数据，键-值对模式
                cv.put(ID, i);
                cv.put(NAME, "xiaoming_" + i);
                cv.put(AGE, 20 + i);
                cv.put(SEX, "男");
                //调用insert方法，将数据插入数据库
                db.insert(TABLE_NAME, null, cv);
            }
            //关闭数据库
            db.close();
        }
    }

    //查询数据的方法
    class QueryListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //得到一个可写的数据库
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            //参数1：表名
            //参数2：要想显示的列
            //参数3：where子句 查询的字段
            //参数4：where子句 对应的条件值
            //参数5：分组方式
            //参数6：having条件
            //参数7：排序方式
            // Cursor cursor = db.query(TABLE_NAME, new String[]{ID, NAME, AGE, SEX}, "name=?", new String[]{"xiaoming"}, null, null, null);
            Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ID));
                // String name = cursor.getString(cursor.getColumnIndex(NAME));
                String age = cursor.getString(cursor.getColumnIndex(AGE));
                String sex = cursor.getString(cursor.getColumnIndex(SEX));
                if (VERSION == 2) {
                    String num = cursor.getString(cursor.getColumnIndex(NUM));
                    System.out.println("query------->" + id + ":姓名：" + " " + "年龄：" + age + " " + "性别：" + sex + " " + "分数：" + num);
                } else {
                    System.out.println("query------->" + "主键:" + id + ":姓名:" + " " + "年龄：" + age + " " + "性别：" + sex + " ");
                }
            }
            //关闭数据库
            db.close();
        }
    }

    //修改数据的方法
    class ModifyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //得到一个可写的数据库
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(SEX, "男");
            //where 子句 "?"是占位符号，对应后面的"1",
            String whereClause = "name=?";
            String[] whereArgs = {"xiaoming"};
//参数1 是要更新的表名
//参数2 是一个ContentValeus对象,需要更新的值
//参数3 4  是where子句和条件
            if (VERSION == 2) {
                ContentValues cv1 = new ContentValues();
                cv1.put(NUM, 11);
                db.update(TABLE_NAME, cv1, null, null);
            } else {
                db.update(TABLE_NAME, cv, whereClause, whereArgs);
            }
            db.close();
        }
    }

    //删除数据的方法
    class DeleteListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //得到一个可写的数据库
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String whereClauses = "name=?";
            String[] whereArgs = {"xiaoming"};
            //调用delete方法，删除数据
            db.delete(TABLE_NAME, whereClauses, whereArgs);
            db.close();
        }
    }
}
