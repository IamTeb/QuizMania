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

public class PuzzleActivity extends AppCompatActivity {

    RadioButton rdb1;
    RadioButton rdb2;
    RadioButton rdb3;
    RadioButton rdb4;
    RadioGroup rdg;
    TextView Questions;
    int pos = 0;
    int point =0;

    String questions[] = {"What day comes three days after the day which comes two days after the day which comes immediately after the day " +
            "which comes two days after Monday?","Gordon is twice as old as Tony was when Gordon was as old as Tony is now. The combined age of " +
            "Gordon and Tony is 112 years. How old are Gordon and Tony now?",
            "A bag of potatos weighs 50kgs divided by half of its weight.How much does the bag of potatoes weigh?",
            "What letter comes two to the right of the letter which is immediately to the left of the letter that comes three to the right of the letter" +
                    " thar comes midway between the letter two to the left of the letter C and the letter to the right of the letter F",
            "How many bits is a byte"};
    String Answer[] = {"Tuesday","Gordon 64 and Tony 48","10kg","H","8"};
    String posAnswers[] = {"Wednesday","Thursday","Tuesday","Friday",
            "Gordon 64 and Tony 48","Gordon 48 and Tony 64","Gordon 54 and Tony 58","Gordon 58 and Tony 54",
            "25kg","20kgs","12.5kg","10kg",
            "G","H","I","J",
            "4","8","12","16"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Questions = (TextView) findViewById(R.id.txtQuestion);
        rdb1 = (RadioButton) findViewById(R.id.rdbOne);
        rdb2 = (RadioButton) findViewById(R.id.rdbTwo);
        rdb3 = (RadioButton) findViewById(R.id.rdbThree);
        rdb4 = (RadioButton) findViewById(R.id.rdbFour);
        rdg = (RadioGroup) findViewById(R.id.rdgQuestions);

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
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
        }
    }
}
