package com.dunghn.toeictest.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dunghn.toeictest.Database.DatabaseHelper;
import com.dunghn.toeictest.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;


public class NguoiDungDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE NguoiDung (ID integer primary key autoincrement,username text,password text, fullname text, email text);";
    public static final String COL_ID = "ID";
    public static final String COL_USERN = "username";
    public static final String COL_PASS = "password";
    public static final String COL_FULLN = "fullname";
    public static final String COL_EMAIL = "email";
    public static final String TAG = "NguoiDungDAO";

    public NguoiDungDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int insertNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUsername());
        values.put("password", nd.getPassword());
        values.put("fullname", nd.getFullname());
        values.put("email", nd.getEmail());
        try {
            if (db.insert(TABLE_NAME, null, values) < 0) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;

    }

    //get All
    public List<NguoiDung> getAllNguoiDung() {
        List<NguoiDung> dsNguoiDung = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            NguoiDung ee = new NguoiDung();
            ee.setID(c.getInt(0));
            ee.setUsername(c.getString(1));
            ee.setPassword(c.getString(2));
            ee.setFullname(c.getString(3));
            ee.setEmail(c.getString(4));
            dsNguoiDung.add(ee);
            Log.d("//====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsNguoiDung;
    }

    public int getIdFromUserName(String name) {
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ID+" as ID",COL_USERN}, COL_USERN+"=?",new String[]{name}, null, null, null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex("ID"));
    }
    public Cursor getDataFromId(int id) {
        Cursor res1 =  db.rawQuery( "select fullname, email from NguoiDung where id="+id+"", null );
        return res1;
    }


    public int changePasswordNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("password", nd.getPassword());
        int result = db.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(nd.getID())});
        if (result == 0) {
            return -1;
        }
        return 1;
    }


    public boolean checkUser(NguoiDung nd) {
        String[] columns = {COL_ID};
        String Selection = COL_USERN + "=?" + " and " + COL_PASS + "=?";
        String[] SelectionArgs = {nd.getUsername(), nd.getPassword()};
        Cursor cursor = db.query(TABLE_NAME, columns, Selection, SelectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0)
            return true;
        else
            return false;
    }
}

