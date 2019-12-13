package com.dunghn.toeictest.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.dunghn.toeictest.database.DatabaseHelper;
import com.dunghn.toeictest.model.Question;
import com.dunghn.toeictest.model.QuestionPart3_4;

import java.util.ArrayList;
import java.util.List;

public class QuestionPart3_4DAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String SQL_QUESTIONPART3_4 = "CREATE TABLE "+ QuestionPart3_4.TABLE_NAME +" ( "+QuestionPart3_4.ID+" INTEGER PRIMARY KEY, "+ Question.CONTENT +" TEXT, "+QuestionPart3_4.ANSWER_A+" TEXT, "+QuestionPart3_4.ANSWER_B+" TEXT, "+QuestionPart3_4.ANSWER_C+" TEXT, "+QuestionPart3_4.ANSWER_D+" TEXT, "+Question.CORRECTANSWER+" INTEGER, "+Question.NUMBERANSWER+" INTEGER);";

    public QuestionPart3_4DAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public static ContentValues putQuestionPart3_4(int id, String content, String a, String b, String c, String d, int correctAnswer)
    {
        ContentValues values=new ContentValues();
        values.put(QuestionPart3_4.ID,id);
        values.put(Question.CONTENT,content);
        values.put(QuestionPart3_4.ANSWER_A,a);
        values.put(QuestionPart3_4.ANSWER_B,b);
        values.put(QuestionPart3_4.ANSWER_C,c);
        values.put(QuestionPart3_4.ANSWER_D,d);
        values.put(Question.CORRECTANSWER,correctAnswer);
        return values;
    }
    public List<QuestionPart3_4> getQuestion_Part3(){
        List<QuestionPart3_4> arraylistp3=new ArrayList<QuestionPart3_4>();
        String sql="SELECT * FROM " +QuestionPart3_4.TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        QuestionPart3_4 questionPart3;
        if(cursor.moveToFirst())
        {
            do{
                questionPart3=new QuestionPart3_4();
                questionPart3.setId(cursor.getInt(cursor.getColumnIndex(QuestionPart3_4.ID)));
                questionPart3.setAnswerA(cursor.getString(cursor.getColumnIndex(QuestionPart3_4.ANSWER_A)));
                questionPart3.setAnswerB(cursor.getString(cursor.getColumnIndex(QuestionPart3_4.ANSWER_B)));
                questionPart3.setAnswerC(cursor.getString(cursor.getColumnIndex(QuestionPart3_4.ANSWER_C)));
                questionPart3.setAnswerD(cursor.getString(cursor.getColumnIndex(QuestionPart3_4.ANSWER_D)));
                questionPart3.setContent(cursor.getString(cursor.getColumnIndex(QuestionPart3_4.CONTENT)));
                questionPart3.setNumberAnswer(cursor.getShort(cursor.getColumnIndex(QuestionPart3_4.NUMBERANSWER)));
                questionPart3.setCorrectAnswer(cursor.getShort(cursor.getColumnIndex(QuestionPart3_4.CORRECTANSWER)));

                arraylistp3.add(questionPart3);
                if(questionPart3.getId()==70){
                    return arraylistp3;
                }
            }while (cursor.moveToNext());
            cursor.close();
            db.close();
        }

        return arraylistp3;
    }
    public List<QuestionPart3_4> getQuestion_Part4(){
        List<QuestionPart3_4> arrayPart4=new ArrayList<QuestionPart3_4>();
        String sql="SELECT * FROM " +QuestionPart3_4.TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        QuestionPart3_4 questionPart4;

        if(cursor.moveToPosition(30)){
            do{
                questionPart4=new QuestionPart3_4();
                questionPart4.setId(cursor.getInt(cursor.getColumnIndex(QuestionPart3_4.ID)));
                questionPart4.setAnswerA(cursor.getString(cursor.getColumnIndex(QuestionPart3_4.ANSWER_A)));
                questionPart4.setAnswerB(cursor.getString(cursor.getColumnIndex(QuestionPart3_4.ANSWER_B)));
                questionPart4.setAnswerC(cursor.getString(cursor.getColumnIndex(QuestionPart3_4.ANSWER_C)));
                questionPart4.setAnswerD(cursor.getString(cursor.getColumnIndex(QuestionPart3_4.ANSWER_D)));
                questionPart4.setContent(cursor.getString(cursor.getColumnIndex(QuestionPart3_4.CONTENT)));
                questionPart4.setNumberAnswer(cursor.getShort(cursor.getColumnIndex(QuestionPart3_4.NUMBERANSWER)));
                questionPart4.setCorrectAnswer(cursor.getShort(cursor.getColumnIndex(QuestionPart3_4.CORRECTANSWER)));

                arrayPart4.add(questionPart4);

            }while (cursor.moveToNext());
            cursor.close();
            db.close();

        }

        return arrayPart4;
    }
}
