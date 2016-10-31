package com.example.codetribe.quizmania;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class RetrieveActivity extends AppCompatActivity {

    TextView nam,cont,mess,view;
    SQLiteDatabase PointsBD = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nam = (TextView) findViewById(R.id.rtvName);
        cont = (TextView) findViewById(R.id.rtvContact);
        mess = (TextView) findViewById(R.id.rtvMessage);
        view = (TextView) findViewById(R.id.rtvView);

        createDB();
        getContent();

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
    public String getContent()
    {
        Cursor cursor = PointsBD.rawQuery("SELECT * FROM Suggestion",null);
        int idColumn = cursor.getColumnIndex("id");
        int nameColumn = cursor.getColumnIndex("name");
        int contactColumn = cursor.getColumnIndex("contact");
        int messageColumn = cursor.getColumnIndex("message");

        cursor.moveToFirst();

        String resultsList = "";

        if (cursor != null && (cursor.getCount() > 0))
        {
            do {
                String id = cursor.getString(idColumn);
                String name = cursor.getString(nameColumn);
                String contact = cursor.getString(contactColumn);
                String message = cursor.getString(messageColumn);

                //nam.setText(name);
                //cont.setText(contact);
                //mess.setText(message);

                view.setText(resultsList);
                resultsList = resultsList + "\nName: \t" + name + "\nContact:\t" + contact + "\nMessage:\t" + message + "\n";
            }while (cursor.moveToNext());
            return resultsList;
        }
        else
        {
            Toast.makeText(this, "No Results to Show", Toast.LENGTH_SHORT).show();
        }
        return resultsList;
    }
    public void createDB()
    {
        try {
            PointsBD = this.openOrCreateDatabase("MySuggestion",MODE_PRIVATE,null);
            PointsBD.execSQL("CREATE TABLE IF NOT EXISTS Suggestion" +
                    "(id integer primary key,name VARCHAR,contact VARCHAR,message VARCHAR);");
            File database = getApplicationContext().getDatabasePath("MySuggestion.db");
            if (!database.exists())
            {
                Toast.makeText(this, "Enter details", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, "Database doesn't exist", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e)
        {
            Log.e("Contact Error","Error creating Database");
        }

    }
}
