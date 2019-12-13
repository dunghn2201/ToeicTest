package com.dunghn.toeictest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dunghn.toeictest.DAO.QuestionPart1DAO;
import com.dunghn.toeictest.DAO.QuestionPart2DAO;
import com.dunghn.toeictest.DAO.QuestionPart3_4DAO;
import com.dunghn.toeictest.DAO.ResultDAO;
import com.dunghn.toeictest.DAO.UserDAO;
import com.dunghn.toeictest.model.Question;
import com.dunghn.toeictest.model.QuestionPart1;
import com.dunghn.toeictest.model.QuestionPart2;
import com.dunghn.toeictest.model.QuestionPart3_4;
import com.dunghn.toeictest.model.Result;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbPracticeToeic";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDAO.SQL_USER);
        ContentValues values;
        db.execSQL(QuestionPart1DAO.SQL_QUESTIONPART1);

        int[] dapAnP1 = {2, 3, 2, 3, 3, 1, 1, 3, 4, 1};
        String[] imgNames = {"part1_1", "part1_2", "part1_3", "part1_4", "part1_5", "part1_6", "part1_7", "part1_8", "part1_9", "part1_10"};
        for (int i = 0; i <= 9; i++) {
            values = QuestionPart1DAO.putQuestionPart1(i + 1, imgNames[i], dapAnP1[i]);
            db.insert(QuestionPart1.TABLE_NAME, null, values);
        }
        db.execSQL(QuestionPart2DAO.SQL_QUESTIONPART2);

        int[] dapAnP2 = {2, 3, 1, 1, 2, 1, 2, 3, 1, 2, 3, 1, 1, 3, 2, 3, 2, 2, 3, 1, 2, 1, 3, 2, 3, 1, 3, 2, 3, 2};
        for (int i = 10; i <= 39; i++) {
            values = QuestionPart2DAO.putQuestionPart2(i+1, dapAnP2[i-10]);
            db.insert(QuestionPart2.TABLE_NAME, null, values);
        }
        //

        db.execSQL(QuestionPart3_4DAO.SQL_QUESTIONPART3_4);

        values = QuestionPart3_4DAO.putQuestionPart3_4(41, "Why has the man not purchased his ticket yes?",
                " He's waiting to schedule a meeting.",
                " The trains are under repair.",
                " The tickets are sold out.",
                " Some routes have been cancelled.", 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(QuestionPart3_4.ID, 42);
        values.put(Question.CONTENT, "When does the woman suggest the man go?");
        values.put(QuestionPart3_4.ANSWER_A, " Sunday");
        values.put(QuestionPart3_4.ANSWER_B, " Monday");
        values.put(QuestionPart3_4.ANSWER_C, " Tuesday ");
        values.put(QuestionPart3_4.ANSWER_D, " Wednesday ");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);

        values.put(QuestionPart3_4.ID, 43);
        values.put(Question.CONTENT, "What is the man planning to do on Monday?");
        values.put(QuestionPart3_4.ANSWER_A, " Fly to Denver ");
        values.put(QuestionPart3_4.ANSWER_B, " Attend a training program ");
        values.put(QuestionPart3_4.ANSWER_C, " Go on a holiday");
        values.put(QuestionPart3_4.ANSWER_D, " Have a meeting ");
        values.put(Question.CORRECTANSWER, 4);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);

        values.put(QuestionPart3_4.ID, 44);
        values.put(Question.CONTENT, "What are the speakers talking about?");
        values.put(QuestionPart3_4.ANSWER_A, " Making a presentation  ");
        values.put(QuestionPart3_4.ANSWER_B, " Going out socially  ");
        values.put(QuestionPart3_4.ANSWER_C, " Getting a discount at the caleteria ");
        values.put(QuestionPart3_4.ANSWER_D, " Preparing appetizers ");
        values.put(Question.CORRECTANSWER, 2);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        values.put(QuestionPart3_4.ID, 45);
        values.put(Question.CONTENT, " What did the man do yesterday afternoon?");
        values.put(QuestionPart3_4.ANSWER_A, " Worked on a presentation ");
        values.put(QuestionPart3_4.ANSWER_B, " Had a meeting with some clients ");
        values.put(QuestionPart3_4.ANSWER_C, " Went out with some coworkers ");
        values.put(QuestionPart3_4.ANSWER_D, "  Attended a company luncheon");
        values.put(Question.CORRECTANSWER, 1);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        values.put(QuestionPart3_4.ID, 46);
        values.put(Question.CONTENT, "When will the speakers meet? ");
        values.put(QuestionPart3_4.ANSWER_A, " 6:00");
        values.put(QuestionPart3_4.ANSWER_B, " 6:30");
        values.put(QuestionPart3_4.ANSWER_C, " 7:00");
        values.put(QuestionPart3_4.ANSWER_D, " 7:30");
        values.put(Question.CORRECTANSWER, 2);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        values.put(QuestionPart3_4.ID, 47);
        values.put(Question.CONTENT, " Where are the speakers?");
        values.put(QuestionPart3_4.ANSWER_A, "  At a presentation ");
        values.put(QuestionPart3_4.ANSWER_B, " In an office building ");
        values.put(QuestionPart3_4.ANSWER_C, " At a financial institution ");
        values.put(QuestionPart3_4.ANSWER_D, " In a post office");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 48);
        values.put(Question.CONTENT, "What does the woman suggest they do?");
        values.put(QuestionPart3_4.ANSWER_A, " Stay and wait in line ");
        values.put(QuestionPart3_4.ANSWER_B, " Postpone the presentation ");
        values.put(QuestionPart3_4.ANSWER_C, " Call the director ");
        values.put(QuestionPart3_4.ANSWER_D, " Return again later ");
        values.put(Question.CORRECTANSWER, 4);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 49);
        values.put(Question.CONTENT, " What is the woman concerned about?");
        values.put(QuestionPart3_4.ANSWER_A, "If a report she asked for is ready ");
        values.put(QuestionPart3_4.ANSWER_B, "Where the meeting will be held ");
        values.put(QuestionPart3_4.ANSWER_C, "If the meeting room is too small ");
        values.put(QuestionPart3_4.ANSWER_D, " What time the presentation will begin ");
        values.put(Question.CORRECTANSWER, 4);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 50);
        values.put(Question.CONTENT, "What will most likely happen on Wednesday? ");
        values.put(QuestionPart3_4.ANSWER_A, " The man will go home early");
        values.put(QuestionPart3_4.ANSWER_B, " The man will be interviewed by Mr Franklin ");
        values.put(QuestionPart3_4.ANSWER_C, " The man will read an accounting book");
        values.put(QuestionPart3_4.ANSWER_D, " The man will change departments");
        values.put(Question.CORRECTANSWER, 2);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 51);
        values.put(Question.CONTENT, "What does the woman suggest they do?");
        values.put(QuestionPart3_4.ANSWER_A, " Go home early to prepare for a meeting ");
        values.put(QuestionPart3_4.ANSWER_B, " Visit the accounting department");
        values.put(QuestionPart3_4.ANSWER_C, " Buy Mr. Franklin breakfast ");
        values.put(QuestionPart3_4.ANSWER_D, " Attend a training session ");
        values.put(Question.CORRECTANSWER, 1);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 52);
        values.put(Question.CONTENT, "What will the woman probably do next?");
        values.put(QuestionPart3_4.ANSWER_A, "  Interview an applicant ");
        values.put(QuestionPart3_4.ANSWER_B, "Speak with Mr Franklin ");
        values.put(QuestionPart3_4.ANSWER_C, "Visit the accounting department ");
        values.put(QuestionPart3_4.ANSWER_D, "Give the man a book for reference ");
        values.put(Question.CORRECTANSWER, 4);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 53);
        values.put(Question.CONTENT, "What does the woman want to do?");
        values.put(QuestionPart3_4.ANSWER_A, "  Go to Chicago ");
        values.put(QuestionPart3_4.ANSWER_B, " Leave early ");
        values.put(QuestionPart3_4.ANSWER_C, " Work late ");
        values.put(QuestionPart3_4.ANSWER_D, " Arrive early tomorrow ");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 54);
        values.put(Question.CONTENT, " What does the man say about Trevor?");
        values.put(QuestionPart3_4.ANSWER_A, " He can help will, the report");
        values.put(QuestionPart3_4.ANSWER_B, " He will contact the client in Chicago");
        values.put(QuestionPart3_4.ANSWER_C, " He will arrive late tomorrow morning");
        values.put(QuestionPart3_4.ANSWER_D, " He can answer the phone");
        values.put(Question.CORRECTANSWER, 1);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 55);
        values.put(Question.CONTENT, "What did the man do today?");
        values.put(QuestionPart3_4.ANSWER_A, " Finished the report ");
        values.put(QuestionPart3_4.ANSWER_B, " Spoke with his client");
        values.put(QuestionPart3_4.ANSWER_C, " Contacted Trevor ");
        values.put(QuestionPart3_4.ANSWER_D, " visited Chicago ");
        values.put(Question.CORRECTANSWER, 2);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 56);
        values.put(Question.CONTENT, "Where did Adam stay in Las Vegas?");
        values.put(QuestionPart3_4.ANSWER_A, " In a hotel ");
        values.put(QuestionPart3_4.ANSWER_B, " His cousin's house");
        values.put(QuestionPart3_4.ANSWER_C, " In an express bus ");
        values.put(QuestionPart3_4.ANSWER_D, " In a small inn ");
        values.put(Question.CORRECTANSWER, 1);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 57);
        values.put(Question.CONTENT, " When did the woman go to Las Vegas?");
        values.put(QuestionPart3_4.ANSWER_A, "Last week");
        values.put(QuestionPart3_4.ANSWER_B, "Last month");
        values.put(QuestionPart3_4.ANSWER_C, "Six months ago");
        values.put(QuestionPart3_4.ANSWER_D, "Two years ago");
        values.put(Question.CORRECTANSWER, 4);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 58);
        values.put(Question.CONTENT, "How did Adam mostly spend his vacation?");
        values.put(QuestionPart3_4.ANSWER_A, " Fishing");
        values.put(QuestionPart3_4.ANSWER_B, " Gambling");
        values.put(QuestionPart3_4.ANSWER_C, " Visiting his parents ");
        values.put(QuestionPart3_4.ANSWER_D, " Relaxing at home ");
        values.put(Question.CORRECTANSWER, 2);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 59);
        values.put(Question.CONTENT, " Why are drinks not included with lunch anymore?");
        values.put(QuestionPart3_4.ANSWER_A, " They lost one of their main suppliers");
        values.put(QuestionPart3_4.ANSWER_B, " There is a coffee shop nearby");
        values.put(QuestionPart3_4.ANSWER_C, " Not many people drink coffee ");
        values.put(QuestionPart3_4.ANSWER_D, " the company is trying to save money ");
        values.put(Question.CORRECTANSWER, 4);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 60);
        values.put(Question.CONTENT, "What is the man's concern? ");
        values.put(QuestionPart3_4.ANSWER_A, " Going to a nearby cafe ");
        values.put(QuestionPart3_4.ANSWER_B, " Looking for a new Job ");
        values.put(QuestionPart3_4.ANSWER_C, " Paying for a drink ");
        values.put(QuestionPart3_4.ANSWER_D, " Sending in a complaint ");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 61);
        values.put(Question.CONTENT, "Why does the woman recommend the coffee shop?");
        values.put(QuestionPart3_4.ANSWER_A, " Her friend owns the shop ");
        values.put(QuestionPart3_4.ANSWER_B, " The company pays for the bill ");
        values.put(QuestionPart3_4.ANSWER_C, " The coffee tastes fresh ");
        values.put(QuestionPart3_4.ANSWER_D, " They offer discount cards");
        values.put(Question.CORRECTANSWER, 4);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 62);
        values.put(Question.CONTENT, "What are the speakers talking about?");
        values.put(QuestionPart3_4.ANSWER_A, " A shipment");
        values.put(QuestionPart3_4.ANSWER_B, " A document ");
        values.put(QuestionPart3_4.ANSWER_C, " Home furnishings");
        values.put(QuestionPart3_4.ANSWER_D, " A colleague ");
        values.put(Question.CORRECTANSWER, 1);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 63);
        values.put(Question.CONTENT, "Why is Grace Cameron unavailable?");
        values.put(QuestionPart3_4.ANSWER_A, "She isn't in the office today");
        values.put(QuestionPart3_4.ANSWER_B, "She is on vacation");
        values.put(QuestionPart3_4.ANSWER_C, " She is in a meeting");
        values.put(QuestionPart3_4.ANSWER_D, "She is away at lunch ");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 64);
        values.put(Question.CONTENT, "What does the woman offer to do?");
        values.put(QuestionPart3_4.ANSWER_A, " E-mail it to Grace ");
        values.put(QuestionPart3_4.ANSWER_B, " Fax it to her boss ");
        values.put(QuestionPart3_4.ANSWER_C, " Sign the form ");
        values.put(QuestionPart3_4.ANSWER_D, " Make a copy ");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 65);
        values.put(Question.CONTENT, "How did the man learn about the positions? ");
        values.put(QuestionPart3_4.ANSWER_A, "His friend is in the HR department ");
        values.put(QuestionPart3_4.ANSWER_B, " He saw an ad in the paper ");
        values.put(QuestionPart3_4.ANSWER_C, " He was searching their website");
        values.put(QuestionPart3_4.ANSWER_D, "He contacted the marketing division");
        values.put(Question.CORRECTANSWER, 2);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 66);
        values.put(Question.CONTENT, " Why would the man like to work in marketing");
        values.put(QuestionPart3_4.ANSWER_A, " He can make a lot of money. ");
        values.put(QuestionPart3_4.ANSWER_B, " He has a friend in that division");
        values.put(QuestionPart3_4.ANSWER_C, " It involves a lot of training");
        values.put(QuestionPart3_4.ANSWER_D, " He has experience in that field");
        values.put(Question.CORRECTANSWER, 4);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 67);
        values.put(Question.CONTENT, "What will the man probably do next? ");
        values.put(QuestionPart3_4.ANSWER_A, " Visit another company");
        values.put(QuestionPart3_4.ANSWER_B, " Write out his resume ");
        values.put(QuestionPart3_4.ANSWER_C, " Wait for an interview");
        values.put(QuestionPart3_4.ANSWER_D, " Contact the marketing division");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 68);
        values.put(Question.CONTENT, "Who most likely is lhe man?");
        values.put(QuestionPart3_4.ANSWER_A, "  A park supervisor ");
        values.put(QuestionPart3_4.ANSWER_B, " A real estate agent ");
        values.put(QuestionPart3_4.ANSWER_C, "  A financial analyst ");
        values.put(QuestionPart3_4.ANSWER_D, " An office receptionist ");
        values.put(Question.CORRECTANSWER, 2);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 69);
        values.put(Question.CONTENT, "Why would the woman like to rush the purchase ?");
        values.put(QuestionPart3_4.ANSWER_A, " She is interested in joining the team ");
        values.put(QuestionPart3_4.ANSWER_B, " Her office building is expensive");
        values.put(QuestionPart3_4.ANSWER_C, " Her company is launching an advertising campaign soon ");
        values.put(QuestionPart3_4.ANSWER_D, " She would like to be closer to home");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 70);
        values.put(Question.CONTENT, "What does the woman suggest the man do? ");
        values.put(QuestionPart3_4.ANSWER_A, "Begin the review of operations");
        values.put(QuestionPart3_4.ANSWER_B, " Finish his meeting earlier ");
        values.put(QuestionPart3_4.ANSWER_C, " Stay late to complete the reports ");
        values.put(QuestionPart3_4.ANSWER_D, " Make an arrangement with his clients ");
        values.put(Question.CORRECTANSWER, 1);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 71);
        values.put(Question.CONTENT, "What is the purpose of the announcement?");
        values.put(QuestionPart3_4.ANSWER_A, " To report a schedule change ");
        values.put(QuestionPart3_4.ANSWER_B, " To announce a new performance date ");
        values.put(QuestionPart3_4.ANSWER_C, "To promote a new dancer ");
        values.put(QuestionPart3_4.ANSWER_D, " To talk about sealing arrangements ");
        values.put(Question.CORRECTANSWER, 1);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);//-------
        values.put(QuestionPart3_4.ID, 72);
        values.put(Question.CONTENT, "Where most likely is the announcement being made?");
        values.put(QuestionPart3_4.ANSWER_A, " At a museum ");
        values.put(QuestionPart3_4.ANSWER_B, " At an advertising firm ");
        values.put(QuestionPart3_4.ANSWER_C, " At a dance school ");
        values.put(QuestionPart3_4.ANSWER_D, " At a theater ");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 73);
        values.put(Question.CONTENT, "What time will he performance begin?");
        values.put(QuestionPart3_4.ANSWER_A, "6:30pm");
        values.put(QuestionPart3_4.ANSWER_B, "7:00pm");
        values.put(QuestionPart3_4.ANSWER_C, "7:30pm");
        values.put(QuestionPart3_4.ANSWER_D, "8:00pm");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 74);
        values.put(Question.CONTENT, "Why was the price of Ihe ticket discounted?");
        values.put(QuestionPart3_4.ANSWER_A, " The arrangements were made in advance");
        values.put(QuestionPart3_4.ANSWER_B, " MI. Parnell has some air miles");
        values.put(QuestionPart3_4.ANSWER_C, "May has a partnership with Ihe airline ");
        values.put(QuestionPart3_4.ANSWER_D, " Mr. Parnell booked a hotel as well");
        values.put(Question.CORRECTANSWER, 1);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 75);
        values.put(Question.CONTENT, "What will the travelers receive upon arrival in Barcelona?");
        values.put(QuestionPart3_4.ANSWER_A, "A complimentary lunch ");
        values.put(QuestionPart3_4.ANSWER_B, "Taxi service ");
        values.put(QuestionPart3_4.ANSWER_C, "A city tour ");
        values.put(QuestionPart3_4.ANSWER_D, "A spa package ");
        values.put(Question.CORRECTANSWER, 1);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 76);
        values.put(Question.CONTENT, "What restriction applies to the travel package?");
        values.put(QuestionPart3_4.ANSWER_A, "The package is non-refundable");
        values.put(QuestionPart3_4.ANSWER_B, "The discount applies only to Mr Parnell ");
        values.put(QuestionPart3_4.ANSWER_C, "There is an additional charge lor a taxi service ");
        values.put(QuestionPart3_4.ANSWER_D, "The amount of baggage is limited ");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 77);
        values.put(Question.CONTENT, "Why should people share the workbooks?");
        values.put(QuestionPart3_4.ANSWER_A, " To help each other learn ");
        values.put(QuestionPart3_4.ANSWER_B, "To complete the assignments quicker ");
        values.put(QuestionPart3_4.ANSWER_C, "There aren't enough copies for everyone");
        values.put(QuestionPart3_4.ANSWER_D, "Not enough people have attended");
        values.put(Question.CORRECTANSWER, 1);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 78);
        values.put(Question.CONTENT, " What is the lecture about?");
        values.put(QuestionPart3_4.ANSWER_A, " How to conduct an instructional workshop ");
        values.put(QuestionPart3_4.ANSWER_B, "How to use a new type of software ");
        values.put(QuestionPart3_4.ANSWER_C, " How to sell computer software ");
        values.put(QuestionPart3_4.ANSWER_D, " How to download software from the internet");
        values.put(Question.CORRECTANSWER, 2);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 79);
        values.put(Question.CONTENT, "What will everyone do after they finish reading page 54?");
        values.put(QuestionPart3_4.ANSWER_A, "Discuss the problem will their partner ");
        values.put(QuestionPart3_4.ANSWER_B, "Practice a common computer situation ");
        values.put(QuestionPart3_4.ANSWER_C, "Close their books ");
        values.put(QuestionPart3_4.ANSWER_D, "Raise some questions ");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 80);
        values.put(Question.CONTENT, "Who is Richard Sakes?");
        values.put(QuestionPart3_4.ANSWER_A, " A guitarist");
        values.put(QuestionPart3_4.ANSWER_B, " A festival planner");
        values.put(QuestionPart3_4.ANSWER_C, "A radio show host");
        values.put(QuestionPart3_4.ANSWER_D, "A poet");
        values.put(Question.CORRECTANSWER, 4);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 81);
        values.put(Question.CONTENT, " Why Is Myra Jones returning to New York?");
        values.put(QuestionPart3_4.ANSWER_A, "To play al a rock festival ");
        values.put(QuestionPart3_4.ANSWER_B, "To visit the radio station");
        values.put(QuestionPart3_4.ANSWER_C, "To record a new album");
        values.put(QuestionPart3_4.ANSWER_D, "To meet some guests");
        values.put(Question.CORRECTANSWER, 2);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 82);
        values.put(Question.CONTENT, "How can the listeners ask questions?");
        values.put(QuestionPart3_4.ANSWER_A, "Call the studio");
        values.put(QuestionPart3_4.ANSWER_B, "Attend the New York Rock Festival");
        values.put(QuestionPart3_4.ANSWER_C, " Buy Myra's new album ");
        values.put(QuestionPart3_4.ANSWER_D, "Send the station an e-mail ");
        values.put(Question.CORRECTANSWER, 4);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 83);
        values.put(Question.CONTENT, "What mosl likely is Bill Buchanan's profession?");
        values.put(QuestionPart3_4.ANSWER_A, " ConstructIon manager ");
        values.put(QuestionPart3_4.ANSWER_B, " City inspector");
        values.put(QuestionPart3_4.ANSWER_C, " Designer ");
        values.put(QuestionPart3_4.ANSWER_D, " Office receptionist ");
        values.put(Question.CORRECTANSWER, 2);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 84);
        values.put(Question.CONTENT, "Where will the two men meet?");
        values.put(QuestionPart3_4.ANSWER_A, "At Miller's office");
        values.put(QuestionPart3_4.ANSWER_B, "At the Kent Street site");
        values.put(QuestionPart3_4.ANSWER_C, "At the inspector's office");
        values.put(QuestionPart3_4.ANSWER_D, "At Buchanan's office");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 85);
        values.put(Question.CONTENT, " What is the reason for the message?");
        values.put(QuestionPart3_4.ANSWER_A, "To make a change in the cost of materials");
        values.put(QuestionPart3_4.ANSWER_B, " To discuss Ihe construction plan ");
        values.put(QuestionPart3_4.ANSWER_C, "To alter the number of workers needed");
        values.put(QuestionPart3_4.ANSWER_D, "To discuss a problem with the building's structure");
        values.put(Question.CORRECTANSWER, 2);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 86);
        values.put(Question.CONTENT, "What is the reason for this talk? ");
        values.put(QuestionPart3_4.ANSWER_A, " To thank people for an award ");
        values.put(QuestionPart3_4.ANSWER_B, "To introduce a new franchise ");
        values.put(QuestionPart3_4.ANSWER_C, "To promote a new project");
        values.put(QuestionPart3_4.ANSWER_D, "To celebrate a promotion");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 87);
        values.put(Question.CONTENT, "Who is the speaker?");
        values.put(QuestionPart3_4.ANSWER_A, "A motivational speaker ");
        values.put(QuestionPart3_4.ANSWER_B, " A franchise developer ");
        values.put(QuestionPart3_4.ANSWER_C, "A sales associate ");
        values.put(QuestionPart3_4.ANSWER_D, "The owner of the company ");
        values.put(Question.CORRECTANSWER, 4);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 88);
        values.put(Question.CONTENT, "How many stores does Klein Grocers have? ");
        values.put(QuestionPart3_4.ANSWER_A, "One");
        values.put(QuestionPart3_4.ANSWER_B, "Forty");
        values.put(QuestionPart3_4.ANSWER_C, "Seventy-five ");
        values.put(QuestionPart3_4.ANSWER_D, "Two hundred ");
        values.put(Question.CORRECTANSWER, 2);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 89);
        values.put(Question.CONTENT, "What should employees do when they first arrive for their shift? ");
        values.put(QuestionPart3_4.ANSWER_A, " Answer the phone ");
        values.put(QuestionPart3_4.ANSWER_B, " Go to their work station ");
        values.put(QuestionPart3_4.ANSWER_C, "Speak to their trainer");
        values.put(QuestionPart3_4.ANSWER_D, " Sign in with their employee ID ");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 90);
        values.put(Question.CONTENT, "How long will the employees be trained?");
        values.put(QuestionPart3_4.ANSWER_A, "A week");
        values.put(QuestionPart3_4.ANSWER_B, "Three weeks");
        values.put(QuestionPart3_4.ANSWER_C, "A month");
        values.put(QuestionPart3_4.ANSWER_D, "Two months");
        values.put(Question.CORRECTANSWER, 1);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 91);
        values.put(Question.CONTENT, " What is in the training manual?");
        values.put(QuestionPart3_4.ANSWER_A, "Answers to common questions");
        values.put(QuestionPart3_4.ANSWER_B, "Rules and regulations ");
        values.put(QuestionPart3_4.ANSWER_C, " Employee ID numbers ");
        values.put(QuestionPart3_4.ANSWER_D, " Client contact numbers");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 92);
        values.put(Question.CONTENT, "Who most likely is the speaker?");
        values.put(QuestionPart3_4.ANSWER_A, "A museum tour guide");
        values.put(QuestionPart3_4.ANSWER_B, " A university student");
        values.put(QuestionPart3_4.ANSWER_C, " An art collector ");
        values.put(QuestionPart3_4.ANSWER_D, "An antiques dealer ");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 93);
        values.put(Question.CONTENT, "Who does the museum benefit?");
        values.put(QuestionPart3_4.ANSWER_A, " The art gallery owners ");
        values.put(QuestionPart3_4.ANSWER_B, "The gift shop ");
        values.put(QuestionPart3_4.ANSWER_C, "The community");
        values.put(QuestionPart3_4.ANSWER_D, "The tour guides ");
        values.put(Question.CORRECTANSWER, 4);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 94);
        values.put(Question.CONTENT, "Where will the visitors go after the tour?");
        values.put(QuestionPart3_4.ANSWER_A, "To see tile artwork ");
        values.put(QuestionPart3_4.ANSWER_B, "To the gift shop ");
        values.put(QuestionPart3_4.ANSWER_C, "To the museum restaurant");
        values.put(QuestionPart3_4.ANSWER_D, "To the research center");
        values.put(Question.CORRECTANSWER, 1);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 95);
        values.put(Question.CONTENT, "What is the main subject of this report? ");
        values.put(QuestionPart3_4.ANSWER_A, "Constructing a new city park ");
        values.put(QuestionPart3_4.ANSWER_B, " Building a production facility ");
        values.put(QuestionPart3_4.ANSWER_C, " Requesting local government support ");
        values.put(QuestionPart3_4.ANSWER_D, "Hiring a new chairman for Appleton ");
        values.put(Question.CORRECTANSWER, 2);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 96);
        values.put(Question.CONTENT, "According to the report. what is important about Houston ?");
        values.put(QuestionPart3_4.ANSWER_A, "It is localed close to the border ");
        values.put(QuestionPart3_4.ANSWER_B, " It has favorable weather conditions ");
        values.put(QuestionPart3_4.ANSWER_C, " It has a large population");
        values.put(QuestionPart3_4.ANSWER_D, "It has favorable tax laws ");
        values.put(Question.CORRECTANSWER, 4);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 97);
        values.put(Question.CONTENT, "What did Mr. Becket announce?");
        values.put(QuestionPart3_4.ANSWER_A, "He wants to stay in Houston for ten years ");
        values.put(QuestionPart3_4.ANSWER_B, "He thinks that the local government has been helpful");
        values.put(QuestionPart3_4.ANSWER_C, "He doesn't want to leave New York");
        values.put(QuestionPart3_4.ANSWER_D, "He will hire many local residents");
        values.put(Question.CORRECTANSWER, 1);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 98);
        values.put(Question.CONTENT, "Who is this advertisement most likely lor?");
        values.put(QuestionPart3_4.ANSWER_A, " Exercise trainers ");
        values.put(QuestionPart3_4.ANSWER_B, "Telephone operators ");
        values.put(QuestionPart3_4.ANSWER_C, "Busy exercisers ");
        values.put(QuestionPart3_4.ANSWER_D, "Gym owners ");
        values.put(Question.CORRECTANSWER, 2);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 99);
        values.put(Question.CONTENT, "What do the DVDs do?");
        values.put(QuestionPart3_4.ANSWER_A, " Make a workout more effective ");
        values.put(QuestionPart3_4.ANSWER_B, "Reduce work·related stress ");
        values.put(QuestionPart3_4.ANSWER_C, " Reduce costs ");
        values.put(QuestionPart3_4.ANSWER_D, " Make exercising boring ");
        values.put(Question.CORRECTANSWER, 3);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //-------
        values.put(QuestionPart3_4.ID, 100);
        values.put(Question.CONTENT, " What should the listeners do to receive a greater discount ?");
        values.put(QuestionPart3_4.ANSWER_A, " Ask one of the instructors ");
        values.put(QuestionPart3_4.ANSWER_B, " Refer a friend to the company ");
        values.put(QuestionPart3_4.ANSWER_C, "Order two copIes of the program");
        values.put(QuestionPart3_4.ANSWER_D, "Call within half an hour ");
        values.put(Question.CORRECTANSWER, 1);
        values.put(Question.NUMBERANSWER, 4);
        db.insert(QuestionPart3_4.TABLE_NAME, null, values);
        //
        db.execSQL(ResultDAO.SQL_RESULT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + UserDAO.TABLE_NAME);
        db.execSQL("Drop table if exists " + QuestionPart1.TABLE_NAME);
        db.execSQL("Drop table if exists " + QuestionPart2.TABLE_NAME);
        db.execSQL("Drop table if exists " + QuestionPart3_4.TABLE_NAME);
        db.execSQL("Drop table if exists " + Result.TABLE_NAME);
        onCreate(db);
    }
}
