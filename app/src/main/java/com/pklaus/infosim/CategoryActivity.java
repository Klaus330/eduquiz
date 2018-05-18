package com.pklaus.infosim;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.pklaus.infosim.data.DbHelper;
import com.pklaus.infosim.data.Question;
import com.pklaus.infosim.data.QuizContract;

import java.util.List;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {


    private Button grafuriBotton;
    private Button hardwareButton;
    private Button softwareButton;
    private DbHelper mDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.category_activity);

        grafuriBotton = (Button) findViewById(R.id.grafButton);
        hardwareButton = (Button) findViewById(R.id.hardwareButton);
        softwareButton =  (Button) findViewById(R.id.softwareButton);

        mDBHelper = new DbHelper(this);
        mDBHelper.getReadableDatabase();


        if(mDBHelper.hasHighScore("Grafuri")) {
            changeScore(grafuriBotton, "Grafuri");
        }
        if(mDBHelper.hasHighScore("Hardware"))
        {
            changeScore(hardwareButton,"Hardware");
        }

        if(mDBHelper.hasHighScore("Software"))
        {
            changeScore(softwareButton,"Software");
        }



        grafuriBotton.setOnClickListener(this);
        hardwareButton.setOnClickListener(this);
        softwareButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        String category = clickedButton.getText().toString();

        changeScore(clickedButton,category);
        Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
        intent.putExtra("category",category);
        startActivity(intent);
    }


    public void changeScore(Button Button,String category){
        if(mDBHelper.hasHighScore(category))
        {
            int score = mDBHelper.getLastScoreByCategory(category);
             List<Question> quesList = mDBHelper.getAllQuestionsByCategory(category);

            if(score < quesList.size() && score >= 1)
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if(category == "Grafuri") {
                        Button.setCompoundDrawablesWithIntrinsicBounds(null,
                                getBaseContext().getDrawable(R.drawable.ic_business_affiliate_network),
                                null, getBaseContext().getDrawable(R.drawable.ic_star_half));
                    }
                    else if(category == "Hardware") {
                        Button.setCompoundDrawablesWithIntrinsicBounds(null,
                                getBaseContext().getDrawable(R.drawable.ic_hardware),
                                null, getBaseContext().getDrawable(R.drawable.ic_star_half));
                    }
                    else if(category == "Software") {
                        Button.setCompoundDrawablesWithIntrinsicBounds(null,
                                getBaseContext().getDrawable(R.drawable.ic_view_quilt_black_24dp),
                                null, getBaseContext().getDrawable(R.drawable.ic_star_half));
                    }
                }
            }
            else if(score == quesList.size()){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if(category == "Grafuri") {
                        Button.setCompoundDrawablesWithIntrinsicBounds(null,
                                getBaseContext().getDrawable(R.drawable.ic_business_affiliate_network),
                                null, getBaseContext().getDrawable(R.drawable.ic_star_black_24dp));
                    }
                      else if(category == "Hardware") {
                        Button.setCompoundDrawablesWithIntrinsicBounds(null,
                                getBaseContext().getDrawable(R.drawable.ic_hardware),
                                null, getBaseContext().getDrawable(R.drawable.ic_star_black_24dp));
                    }
                    else if(category == "Software") {
                        Button.setCompoundDrawablesWithIntrinsicBounds(null,
                                getBaseContext().getDrawable(R.drawable.ic_view_quilt_black_24dp),
                                null, getBaseContext().getDrawable(R.drawable.ic_star_black_24dp));
                    }
                }
            }
        }
    }
}



