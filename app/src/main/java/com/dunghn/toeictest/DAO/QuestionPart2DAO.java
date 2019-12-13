package com.dunghn.toeictest.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.dunghn.toeictest.database.DatabaseHelper;
import com.dunghn.toeictest.model.Question;
import com.dunghn.toeictest.model.QuestionPart1;
import com.dunghn.toeictest.model.QuestionPart2;

import java.util.ArrayList;
import java.util.List;

public class QuestionPart2DAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String SQL_QUESTIONPART2 = "CREATE TABLE "+ QuestionPart2.TABLE_NAME +" ( "+QuestionPart2.ID+" INTEGER PRIMARY KEY, "+ Question.CORRECTANSWER +" TEXT);";

    public QuestionPart2DAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public static ContentValues putQuestionPart2(int id, int correctAnswer)
    {
        ContentValues values=new ContentValues();
        values.put(QuestionPart1.ID,id);
        values.put(Question.CORRECTANSWER,correctAnswer);
        return values;
    }
    public List<QuestionPart2> getQuestion_Part2(){
        ArrayList<QuestionPart2> arrayPart2=new ArrayList<QuestionPart2>();
        String sql="SELECT * FROM "+ QuestionPart2.TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        QuestionPart2 questionPart2;
        if (cursor.moveToFirst())
        {
            do{
                questionPart2=new QuestionPart2();
                questionPart2.setId(cursor.getInt(cursor.getColumnIndex(QuestionPart2.ID)));
                questionPart2.setCorrectAnswer(cursor.getInt(cursor.getColumnIndex(QuestionPart2.CORRECTANSWER)));
                arrayPart2.add(questionPart2);
            }while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return arrayPart2;
    }
}
