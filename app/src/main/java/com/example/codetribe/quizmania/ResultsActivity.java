package com.example.codetribe.quizmania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView txt1, txt2,tym;
    int point = 0, Qu = 0;
    RatingBar ratingBar;
    long start = System.nanoTime();
    long end = System.nanoTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        txt1 = (TextView) findViewById(R.id.total);
        txt2 = (TextView) findViewById(R.id.QuesRating);
        tym = (TextView) findViewById(R.id.Time);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        point = Integer.parseInt(getIntent().getExtras().getString("Score"));
        Qu = Integer.parseInt(getIntent().getExtras().getString("Qu"));

        double percent = ((double) point / Qu) * 100;

        long milli = end - start;

        int seconds =(int) ((milli / 1000) % 60);
        txt1.setText("You have : " + percent + "%");

        txt2.setText("Your Score is: " + point + "/" + Qu);
        tym.setText("You took " + seconds + " seconds");
        ratingBar.setRating(point);
    }

    public void goToHome(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
