package com.dunghn.toeictest.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.dunghn.toeictest.database.DatabaseHelper;
import com.dunghn.toeictest.model.Question;
import com.dunghn.toeictest.model.QuestionPart1;

import java.util.ArrayList;
import java.util.List;

public class QuestionPart1DAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String SQL_QUESTIONPART1 = "CREATE TABLE " + QuestionPart1.TABLE_NAME + " ( " + QuestionPart1.ID + " INTEGER PRIMARY KEY, " + QuestionPart1.IMAGENAME + " TEXT, " + Question.CORRECTANSWER + " TEXT);";

    public QuestionPart1DAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static ContentValues putQuestionPart1(int id, String imgName, int correctAnwser) {
        ContentValues values = new ContentValues();
        values.put(QuestionPart1.ID, id);
        values.put(QuestionPart1.IMAGENAME, imgName);
        values.put(Question.CORRECTANSWER, correctAnwser);
        return values;
    }

    public List<QuestionPart1> getQuestion_Part1() {
        List<QuestionPart1> arrayPart1 = new ArrayList<>();
        String sql = "SELECT * FROM " + QuestionPart1.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        QuestionPart1 questionPart1;
        if (cursor.moveToFirst()) {
            do {
                questionPart1 = new QuestionPart1();
                questionPart1.setId(cursor.getInt(cursor.getColumnIndex(QuestionPart1.ID)));
                questionPart1.setImageName(cursor.getString(cursor.getColumnIndex(QuestionPart1.IMAGENAME)));
                questionPart1.setCorrectAnswer(cursor.getInt(cursor.getColumnIndex(QuestionPart1.CORRECTANSWER)));
                arrayPart1.add(questionPart1);
                if (questionPart1.getId() == 10)
                    return arrayPart1;
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return arrayPart1;
    }
}