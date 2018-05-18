package com.pklaus.infosim.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.widget.Toast;

import com.pklaus.infosim.CategoryActivity;
import com.pklaus.infosim.data.QuizContract.QuestionEntry;

import java.util.ArrayList;
import java.util.List;

import static com.pklaus.infosim.data.QuizContract.QuestionEntry.COLUMN_ANSWER;
import static com.pklaus.infosim.data.QuizContract.QuestionEntry.COLUMN_CATEGORY;
import static com.pklaus.infosim.data.QuizContract.QuestionEntry.COLUMN_ID;
import static com.pklaus.infosim.data.QuizContract.QuestionEntry.COLUMN_OPTA;
import static com.pklaus.infosim.data.QuizContract.QuestionEntry.COLUMN_OPTB;
import static com.pklaus.infosim.data.QuizContract.QuestionEntry.COLUMN_OPTC;
import static com.pklaus.infosim.data.QuizContract.QuestionEntry.COLUMN_PHOTO;
import static com.pklaus.infosim.data.QuizContract.QuestionEntry.COLUMN_QUES;
import static com.pklaus.infosim.data.QuizContract.QuestionEntry.TABLE_NAME;

public class DbHelper extends SQLiteOpenHelper {

    // DB version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "infoQuiz";

    // Table name
    private SQLiteDatabase dbase;
    Context context;

    public DbHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;

        String scoreTable = "CREATE TABLE IF NOT EXISTS score" + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +"category TEXT,"+ "score" + " INTEGER)";
        dbase.execSQL(scoreTable);

        String quesTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_QUES
                + " TEXT, " + COLUMN_CATEGORY + " TEXT," + COLUMN_PHOTO+ " TEXT," + COLUMN_OPTA + " TEXT, " + COLUMN_OPTB + " TEXT, "
                + COLUMN_OPTC + " TEXT, " + COLUMN_ANSWER + " TEXT)";
        dbase.execSQL(quesTable);
        addQuestions(db);


    }


    private void addQuestions(SQLiteDatabase db){

        Question q1 = new Question("Cate grafuri neorientate,distincte,cu 4 varfuri se pot construi?",
                                    "Grafuri",
                                    "24", "4",
                                    "2 la puterea a 6-a",
                                    "2 la puterea a 6-a");
        this.addQuestion(q1,db);
        Question q2 = new Question("Care din urmatoarele propietati este adevarata pentru un graf orientat cu n varfuri si n arce(n>3) care are un circuit de lungime n?",
                                    "Grafuri",
                                    "exista un varf cu gradul intern n-1",
                                    "graful nu are drumuri de lungime strict mai mare decat 2",
                                    "pentru orice varf gradul intern si gradul extern sunt egale",
                                    "pentru orice varf gradul intern si gradul extern sunt egale");
        this.addQuestion(q2,db);
        Question q3 = new Question("Daca G este un graf neorientat cu 8 noduri si 2 componente conexe, atunci graful are cel mult:",
                                    "Grafuri",
                                    "28 de muchii",
                                    "12 muchii",
                                    "21 de muchii",
                                    "12 muchii" );
        this.addQuestion(q3,db);
        Question q4 = new Question("Se considera un graf neorientat complet cu 10 varfuri. Cate lanturi elementare distincte" +
                                    "de lungime 3 exista intre varful 2 si 4? Doua lanturi sunt distincte daca difera prin cel putin o muchie.",
                                    "Grafuri",
                                    "90",
                                    "28",
                                    "45",
                                    "45");
        this.addQuestion(q4,db);
        Question q5 = new Question("Fie graful orientat G cu 5 varfuri si arcele (1,2),(1,3),(1,4),(2,3),(4,2),(4,5)" +
                ",(5,2) si (2,4). Care dintre urmatoarele varfuri au gradul extern egal cu gradul intern",
                                    "Grafuri",
                                    "4 si 5",
                                    "1 si 2",
                                    " 3 si 4",
                                    "4 si 5");
        this.addQuestion(q5,db);

        Question q6 = new Question("Care este acronimul microprocesorului?",
                                    "Hardware","RAM",
                                    "CPU","HDD",
                                    "CPU");
        this.addQuestion(q6,db);

        Question q7= new Question("Ce reprezinta obiectul din imagine?",
                "https://kainos-img.dgn.lt/photos2_25_1622313/samsung-ssd-850-evo-1tb-sata-iii-mz-75e1t0b-eu.jpg",
                "Hardware","Un SSD (Solid Drive Disk)",
                "Un HDD (Hard Disk)","Un mouse",
                "Un SSD (Solid Drive Disk)");
        this.addQuestion(q7,db);

        Question q8= new Question("\t\n" +
                "Unde sunt incarcate fisierele cand copiati un program?",
                "Hardware","Placa Video",
                "DVD-RW","Hard Disk",
                "Hard Disk");
        this.addQuestion(q8,db);

        Question q9= new Question("\t\n" +
                "Care din urmatoarele este un sistem de operare?",
                "Hardware","Mac OS",
                "Windows Internet Explorer","Ubisoft",
                "Mac OS");
        this.addQuestion(q9,db);

        Question q10= new Question("\t\n" +
                "Ce tip de memorie este cea din imagine?",
                "https://images-eu.ssl-images-amazon.com/images/I/419SRJu4kHL._SL500_AC_SS350_.jpg",
                "Hardware","ROM",
                "RAM","Nu este un tip de memorie",
                "RAM");
        this.addQuestion(q10,db);
    }

    public void addQuestion(Question quest,SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUES, quest.getQuestion());
        values.put(COLUMN_CATEGORY, quest.getCategory());
        values.put(COLUMN_PHOTO, quest.getPhotoURL());
        values.put(COLUMN_ANSWER, quest.getAnswer());
        values.put(COLUMN_OPTA, quest.getOptA());
        values.put(COLUMN_OPTB, quest.getOptB());
        values.put(COLUMN_OPTC, quest.getOptC());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }


    public List<Question> getAllQuestionsByCategory(String category) {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + COLUMN_CATEGORY + " = '" + category.trim() + "'";


        dbase=this.getReadableDatabase();
        if(dbase == null)
        {
            Toast.makeText(context,"Something went wrong with DB.",Toast.LENGTH_LONG).show();
            return null;
        }else {

            Cursor cursor = dbase.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String ques = cursor.getString(1);
                    String qcategory = cursor.getString(2);
                    String optA = cursor.getString(4);
                    String optB = cursor.getString(5);
                    String optC = cursor.getString(6);
                    String answer = cursor.getString(7);

                    if(!TextUtils.isEmpty(cursor.getString(3))){
                        String photoUrl = cursor.getString(3);
                        Question quest = new Question(id, ques,photoUrl, qcategory, optA, optB, optC, answer);
                        quesList.add(quest);
                    }else {
                        Question quest = new Question(id, ques, qcategory, optA, optB, optC, answer);
                        quesList.add(quest);
                    }
                } while (cursor.moveToNext());
            }
            // return quest list
            return quesList;
        }
    }


    public int getLastScoreByCategory(String category){

        String selectQuery = "SELECT  * FROM " + "score" + " WHERE " + COLUMN_CATEGORY + " = '" + category.trim() + "'";
        int score = 0;
        Cursor cursor;

        cursor = dbase.rawQuery(selectQuery, null);
        if(cursor.moveToLast()) {
            cursor.moveToLast();
             int i = cursor.getColumnCount();
            score = cursor.getInt(2);
        }
        return score;
    }

    public void setLastScoreByCategory(String category,int score){
        ContentValues values = new ContentValues();

        values.put("category",category);
        values.put("score",score);

        dbase = this.getWritableDatabase();
        dbase.insert("score", null, values);
    }


    public boolean hasHighScore(String c){
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + COLUMN_CATEGORY + " = '" + c.trim() + "'";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        if(cursor == null)
            return  false;
        else
            return true;
    }
}
