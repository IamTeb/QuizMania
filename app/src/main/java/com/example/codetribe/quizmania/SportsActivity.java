package com.example.codetribe.quizmania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SportsActivity extends AppCompatActivity {

    RadioButton rdb1;
    RadioButton rdb2;
    RadioButton rdb3;
    RadioButton rdb4;
    RadioGroup rdg;
    TextView Questions;
    int pos = 0;
    int point=0;
    public static String questions[] = {"How many Fights did Mohamed Ali lose in his career?","Which Snooker player is nicknamed 'The Rocket'?",
            "Who is the current NBA MVP of the year 2016", "Which Played played the EURO finals at an age of 19?",
            "Which player graduated from high school 'with full marks' and the enrolled at a faculty of Business and Economics in Turin?" };
    String Answer[]= {"Only One", "Ronnie O'Sullivan","Stephen Curry","Cristiano Ronaldo","Giorgio Chiellini"};
    String posAnswers[] = {"Two","Only One","Three","None",
            "Stephen Henry","Efrem Reyes","Ronnie O'Sullivan","Jayson Shaw",
            "Kobe Bryant","Lebron James","Dwight Howard","Stephen Curry",
            "Renato Sanchez","Cristiano Ronaldo","Pedro","Martin Odegaard",
            "Giorgio Chiellini","Paul Pogba","Alvaro Morata","Mario Mandzukic"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        Questions = (TextView) findViewById(R.id.txtQuestion);
        rdb1 = (RadioButton) findViewById(R.id.rdbOne);
        rdb2 = (RadioButton) findViewById(R.id.rdbTwo);
        rdb3 = (RadioButton) findViewById(R.id.rdbThree);
        rdb4 = (RadioButton) findViewById(R.id.rdbFour);
        rdg = (RadioGroup) findViewById(R.id.rdgQuestions) ;
        Questions.setText(questions[pos]);

        rdb1.setText(posAnswers[pos]);
        rdb2.setText(posAnswers[pos + 1]);
        rdb3.setText(posAnswers[pos + 2]);
        rdb4.setText(posAnswers[pos + 3]);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void saveAnswers(View view)
    {

        int id = rdg.getCheckedRadioButtonId();

        if (id > 0)
        {
            RadioButton selectedItem = (RadioButton)findViewById(id);

            if(selectedItem.getText().toString() == Answer[pos])
            {
                point++;
            }
            pos++;
            rdg.clearCheck();

            if (pos < questions.length)
            {
                Questions.setText(questions[pos]);

                rdb1.setText(posAnswers[pos * 4]);
                rdb2.setText(posAnswers[pos * 4 + 1]);
                rdb3.setText(posAnswers[pos * 4 + 2]);
                rdb4.setText(posAnswers[pos * 4 + 3]);
            }
            else
            {
                Toast.makeText(this, "End of the questions" , Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,ResultsActivity.class);
                intent.putExtra("Score",String.valueOf(point));
                intent.putExtra("Qu", String.valueOf(questions.length));
                startActivity(intent);
            }
        }
        else
        {
            Toast.makeText(this, "Please select an option ", Toast.LENGTH_SHORT).show();
        }



    }
}
