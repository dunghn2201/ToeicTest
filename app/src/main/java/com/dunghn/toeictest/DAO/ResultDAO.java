package com.dunghn.toeictest.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.dunghn.toeictest.database.DatabaseHelper;
import com.dunghn.toeictest.model.Result;

import java.util.ArrayList;
import java.util.List;

public class ResultDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String SQL_RESULT = "CREATE TABLE "+ Result.TABLE_NAME+" ( "+ Result.KQ_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+ Result.KQ_NAME +" TEXT, "+ Result.KQ_SCORE +" INTEGER, "+ Result.KQ_TIME +" TEXT);";

    public ResultDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public void InsertKQ(String name,String time,int score){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Result.KQ_TIME,time);
        contentValues.put(Result.KQ_SCORE,score);
        contentValues.put(Result.KQ_NAME,name);
        db.insert(Result.TABLE_NAME,null,contentValues);
        db.close();
    }
    public List<Result> getKQ()  {
        List<Result> arrayKQ=new ArrayList<>();
        String sql="SELECT * FROM " + Result.TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        Result ketQua;

        if(cursor.moveToFirst()){
            do{
                ketQua=new Result();
                ketQua.setId(cursor.getInt(cursor.getColumnIndex(Result.KQ_ID)));
                ketQua.setName(cursor.getString(cursor.getColumnIndex(Result.KQ_NAME)));
                ketQua.setScore(cursor.getInt(cursor.getColumnIndex(Result.KQ_SCORE)));
                String time=cursor.getString(cursor.getColumnIndex(Result.KQ_TIME));
                ketQua.setTime(time);
                arrayKQ.add(ketQua);
            }while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return arrayKQ;
    }
}
