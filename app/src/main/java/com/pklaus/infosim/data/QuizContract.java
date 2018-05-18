package com.pklaus.infosim.data;

import android.provider.BaseColumns;

public class QuizContract {

    public static class QuestionEntry implements BaseColumns {

        public static final String TABLE_NAME = "quest";

        // tasks Table Columns names
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_QUES = "question";
        public static final String COLUMN_PHOTO = "photoURL";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_OPTA= "opta"; //option a
        public static final String COLUMN_OPTB= "optb"; //option b
        public static final String COLUMN_OPTC= "optc"; //option c
        public static final String COLUMN_ANSWER = "answer"; //correct option
    }

}
