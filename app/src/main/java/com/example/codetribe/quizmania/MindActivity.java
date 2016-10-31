package com.example.codetribe.quizmania;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MindActivity extends AppCompatActivity {

    RadioButton rdb1;
    RadioButton rdb2;
    RadioButton rdb3;
    RadioButton rdb4;
    RadioGroup rdg;
    TextView Questions;
    int pos = 0;
    int point =0;

    String questions[] = {"Feed me i live, yet give me a drink and i die, Who am I?","Imagine you are in a dark room.How do you get out?",
            "What is at the end of the rainbow", "Johnny's mother had three children. The first child was named April.The second was named May." +
            " What is the Third child's name?","A man ran through a stop sign. Two police officers saw him and just sat there.Why? " };
    String answers[] = {"Fire","Just stop imagining","Letter w","Johnny","He was running"};

    String posAnswers[] = {"Men","Barman","Fire","Life",
            "Back Door","Through the Window","Reach for the light","Just stop imagining",
            "Rainfall","Letter w","Clouds","The ground",
            "June","July","April","Johnny",
            "He was speeding","He was instructed","He was running","He was walking"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mind);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
            if(selectedItem.getText().toString() == answers[pos])
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
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
        }
    }
}
