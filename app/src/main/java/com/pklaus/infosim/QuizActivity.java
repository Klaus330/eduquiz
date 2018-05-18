package com.pklaus.infosim;

import android.content.Intent;
import android.os.BaseBundle;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pklaus.infosim.data.DbHelper;
import com.pklaus.infosim.data.Question;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import static android.content.Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    List<Question> quesList = null;
    int score = 0;
    int questionId = 0;
    String currentQText = null;
    Question currentQ;
    TextView questionTextView,currentQTextView;
    Button optA,optB,optC;
    ProgressBar progressBar;
    ImageView questionImage;

    DbHelper mDBHelper = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz);

        Bundle extras = getIntent().getExtras();
        String category = extras.getString("category");
        DbHelper db = new DbHelper(this);
        quesList = db.getAllQuestionsByCategory(category);


        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(quesList.size());
        questionTextView = findViewById(R.id.questionTV);
        currentQTextView = findViewById(R.id.currentQuesTV);
        questionImage = findViewById(R.id.imageView);

        optA = findViewById(R.id.buttonOptA);
        optB = findViewById(R.id.buttonOptB);
        optC = findViewById(R.id.buttonOptC);



        if(quesList.size() != 0) {
            currentQ = quesList.get(questionId);
                if(!currentQ.hasPicture()) {
                    questionImage.setVisibility(View.GONE);
                }
            setQuestionView();
        }else{
            Toast.makeText(this, "Nu sunt intrebari disponibile!", Toast.LENGTH_SHORT).show();
            finish();
        }



        optA.setOnClickListener(this);
        optB.setOnClickListener(this);
        optC.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Button option = (Button) v;
        checkAnswer(option);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setQuestionView()
    {
        questionTextView.setText(currentQ.getQuestion());
        optA.setText(currentQ.getOptA());
        optB.setText(currentQ.getOptB());
        optC.setText(currentQ.getOptC());
        if(currentQ.hasPicture())
        {
            questionImage.setVisibility(View.VISIBLE);
            String photoUrl = currentQ.getPhotoURL();
            Picasso.get().load(photoUrl).resize(400, 400).into(questionImage);
        }
        questionId++;
        currentQText = (questionId) + " of " +  quesList.size();
        currentQTextView.setText(currentQText);
    }

    private void loadResultsActivity(){

        mDBHelper.getWritableDatabase();
        mDBHelper.setLastScoreByCategory(currentQ.getCategory(),score);

        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);

        Bundle extras = new Bundle();
        extras.putInt("score", score);
        extras.putInt("questionListSize", quesList.size());
        intent.putExtras(extras);

        startActivity(intent);
        finish();
    }


    private void checkAnswer(final Button option){
        final Handler handler = new Handler();
        if(currentQ.getAnswer().equals(option.getText().toString())){
            progressBar.setProgress(questionId);
            changeColorInGreen(option);
            disableButtons();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    score++;
                    changeColorInWhite(option);
                    enableButtons();
                    if(questionId < quesList.size()){
                        questionImage.setVisibility(View.GONE);
                        currentQ = quesList.get(questionId);
                        setQuestionView();
                    }else{
                        loadResultsActivity();
                    }
                }
            }, 1000);
        }else
        {
            changeColorInRed(option);
            disableButtons();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressBar.setProgress(questionId);
                    changeColorInWhite(option);
                    enableButtons();
                    if(questionId < quesList.size()){
                        questionImage.setVisibility(View.GONE);
                        currentQ = quesList.get(questionId);
                        setQuestionView();
                    }else{
                        loadResultsActivity();
                    }
                }
            }, 1000);
        }
    }

    private void changeColorInWhite(Button button){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button.setBackground(getBaseContext().getDrawable(R.drawable.answer_button_bg));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                button.setTextColor(getBaseContext().getColor(R.color.gray));
            }
        }
    }

    private void changeColorInGreen(Button button){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button.setBackground(getBaseContext().getDrawable(R.drawable.btn_bg_green));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                button.setTextColor(getBaseContext().getColor(R.color.green));
            }
        }
    }

    private void changeColorInRed(Button button){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button.setBackground(getBaseContext().getDrawable(R.drawable.btn_bg_red));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                button.setTextColor(getBaseContext().getColor(R.color.red));
            }
        }
    }

    private void disableButtons(){
        optA.setEnabled(false);
        optB.setEnabled(false);
        optC.setEnabled(false);
    }

    private void enableButtons(){
        optA.setEnabled(true);
        optB.setEnabled(true);
        optC.setEnabled(true);
    }

}
