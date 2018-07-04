package com.manager.jfdeng.creditmanagersystem.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yf on 18-7-4.
 */

public class DataBaseUtils extends SQLiteOpenHelper {

    final String CREATE_STU = "create table if not exists stu(" +
            "id integer primary key autoincrement ," +
            "sno text," +
            "name text," +
            "class text)";

    final String CREATE_SCORE = "create table if not exists score(" +
            "id integer primary key autoincrement ," +
            "sno text," +
            "kaoqin integer," +
            "zuoye integer," +
            "shangji integer)";

//    final String CREATE_STU = "create table if not exists stu(" +
//            "id integer primary key autoincrement ," +
//            "sno text," +
//            "name text," +
//            "class text)";


    Context mContext;

    public DataBaseUtils(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_STU);
        database.execSQL(CREATE_SCORE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

    }
}