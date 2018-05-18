package com.pklaus.infosim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    RatingBar ratingBar;
    TextView scoreInterpretationTextView;
    int score;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);

        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setNumStars(5);
        ratingBar.setStepSize(0.5f);

        scoreInterpretationTextView = findViewById(R.id.scoreInterpretation);

        Bundle extras = getIntent().getExtras();
        score = extras.getInt("score");
        int questionListSize = extras.getInt("questionListSize");

        ratingBar.setNumStars(questionListSize);
        ratingBar.setRating(score);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,CategoryActivity.class);
                startActivity(intent);
            }
        });

        switch (score)
        {
            case 0:
                scoreInterpretationTextView.setText(" \n Nu te descuraja. Mai incearca!" +
                        "\n :)");
                break;
            case 1:
                scoreInterpretationTextView.setText("\nTrebuie sa înveți mai mult!" +
                        "\n :)");
                break;
            case 2:
                scoreInterpretationTextView.setText("\nTrebuie sa înveți mai mult!" +
                        "\n :)");
                break;
            case 3:
                scoreInterpretationTextView.setText("\nTrebuie sa înveți mai mult! " +
                        "\n:)");
                break;
            case 4:
                scoreInterpretationTextView.setText("\n Ai fost extrem de aproape!" +
                        "\n :)");
                break;
            case 5:
                scoreInterpretationTextView.setText(
                        "Ai terminat testul!" +
                                "\n Felicitari!" +
                        " :D");
                break;
        }

    }


}
