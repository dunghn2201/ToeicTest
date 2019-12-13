package com.dunghn.toeictest.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dunghn.toeictest.database.DatabaseHelper;
import com.dunghn.toeictest.model.User;

import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "User";
    public static final String SQL_USER = "CREATE TABLE User (ID integer primary key autoincrement,username text unique,password text, fullname text, email text);";
    public static final String COL_ID = "ID";
    public static final String COL_USERN = "username";
    public static final String COL_PASS = "password";
    public static final String COL_FULLN = "fullname";
    public static final String COL_EMAIL = "email";
    public static final String TAG = "UserDAO";

    public UserDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int insertUser(User u) {
        ContentValues values = new ContentValues();
        values.put("username", u.getUsername());
        values.put("password", u.getPassword());
        values.put("fullname", u.getFullname());
        values.put("email", u.getEmail());
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
    public List<User> getAllUser() {
        List<User> dsUser = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            User u = new User();
            u.setID(c.getInt(0));
            u.setUsername(c.getString(1));
            u.setPassword(c.getString(2));
            u.setFullname(c.getString(3));
            u.setEmail(c.getString(4));
            dsUser.add(u);
//            Log.d("//====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsUser;
    }

    public int getIdFromUserName(String name) {
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ID+" as ID",COL_USERN}, COL_USERN+"=?",new String[]{name}, null, null, null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex("ID"));
    }
    public Cursor getDataFromId(int id) {
        Cursor res1 =  db.rawQuery( "select fullname, email from User where id="+id+"", null );
        return res1;
    }


    public int changePasswordUser(User u) {
        ContentValues values = new ContentValues();
        values.put("password", u.getPassword());
        int result = db.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(u.getID())});
        if (result == 0) {
            return -1;
        }
        return 1;
    }


    public boolean checkUser(User u) {
        String[] columns = {COL_ID};
        String Selection = COL_USERN + "=?" + " and " + COL_PASS + "=?";
        String[] SelectionArgs = {u.getUsername(), u.getPassword()};
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

