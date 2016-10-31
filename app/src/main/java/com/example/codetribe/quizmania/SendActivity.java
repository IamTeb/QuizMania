package com.example.codetribe.quizmania;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class SendActivity extends AppCompatActivity {

    Button send,reset;
    EditText nam,cont,mess;
    SQLiteDatabase PointsBD = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        send = (Button) findViewById(R.id.btnSend);
        reset = (Button) findViewById(R.id.btnReset);
        nam = (EditText) findViewById(R.id.edtName);
        cont = (EditText) findViewById(R.id.edtContact);
        mess = (EditText) findViewById(R.id.edtMessage);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createDB();

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
                Toast.makeText(this, "Enter Details", Toast.LENGTH_LONG).show();
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
        Cursor cursor = PointsBD.rawQuery("SELECT * from Suggestion",null);
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

                resultsList = resultsList + "Name: \t" + name + "\nContact:\t" + contact + "\nMessage:\t" + message;
            }while (cursor.moveToNext());
            return resultsList;
        }
        else
        {
            Toast.makeText(this, "No Results to Show", Toast.LENGTH_SHORT).show();
        }
        return resultsList;
    }
    public void addContent()
    {
        String n = nam.getText().toString();
        String con = cont.getText().toString();
        String mes = mess.getText().toString();

        String name = String.valueOf(n);
        String contact = String.valueOf(con);
        String message = String.valueOf(mes);

       PointsBD.execSQL("INSERT INTO Suggestion(name,contact,message) VALUES('" + name + "','" + contact + "','" + message + "')");

    }

    public void sendData(View view)
    {
        addContent();

        Toast.makeText(this, "Message Sent, visit platform", Toast.LENGTH_LONG).show();

        nam.setText("");
        cont.setText("");
        mess.setText("");


    }
    public void resetData(View view)
    {
        Intent intent = new Intent(this,RetrieveActivity.class);
        startActivity(intent);
    }
}
