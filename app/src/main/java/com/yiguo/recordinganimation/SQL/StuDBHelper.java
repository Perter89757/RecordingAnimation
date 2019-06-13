package com.yiguo.recordinganimation.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StuDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "TestSQLite";
    //默认版本号
    public static  int VERSION = 1;
    //表名字
    public static final String TABLE_NAME = "stu_table";
    //字段名字
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String SEX = "sex";

    //新增字段
    public static final String NUM = "num";

    private String CREATE_BOOK = "create table stu_table(" +
            "id integer primary key autoincrement," +
            "name varchar(20)," +
            "age int," +
            "sex varchar(10)" +
            ")";

    //1.原来的数据库表重命名一下转换成临时表
    private String CREATE_TEMP_BOOK = "alter table stu_table rename to _temp_stu_table";
    //2.创建一张新表,名字是原来表,添加新的字段在后面
    private String CREATE_NEW_BOOK = "create table stu_table(id integer primary key autoincrement,name varchar(20),age int,sex varchar(10),num int)";
    //3.备份表中的数据copy到新创建的数据库表中
    private String INSERT_DATA = "insert into stu_table select *,'' from _temp_stu_table";
    //4.把备份表干掉就行啦
    private String DROP_BOOK = "drop table _temp_stu_table";


    //必须要有构造函数
    public StuDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                       int version) {
        super(context, name, factory, version);
        VERSION = version;
    }

    // 当第一次创建数据库的时候，调用该方法
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "create Database------------->");
        db.execSQL(CREATE_BOOK);
    }

    //当更新数据库的时候执行该方法
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //输出更新数据库的日志信息
        switch (newVersion) {
            case 2:
                db.execSQL(CREATE_TEMP_BOOK);
                db.execSQL(CREATE_NEW_BOOK);
                db.execSQL(INSERT_DATA);
                db.execSQL(DROP_BOOK);
                Log.i(TAG, "update Database v2.0------------->");
                break;
            case 3:
                db.execSQL("BEGIN TRANSACTION;");
                db.execSQL("create TEMPORARY TABLE stu_table_backup(id,name,age,sex);");
                db.execSQL("INSERT INTO stu_table_backup SELECT id,name,age,sex FROM stu_table;");
                db.execSQL("DROP TABLE stu_table;");
                db.execSQL("CREATE TABLE stu_table(id,name,age,sex);");
                db.execSQL("INSERT INTO stu_table SELECT id,name,age,sex FROM stu_table_backup;");
                db.execSQL("DROP TABLE stu_table_backup;");
                db.execSQL("COMMIT;");
                Log.i(TAG, "update Database v3.0------------->");
                break;
        }
    }
}