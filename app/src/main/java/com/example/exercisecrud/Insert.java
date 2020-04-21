package com.example.exercisecrud;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Insert extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper mydb;
    EditText ETalamat;
    EditText ETemail;
    EditText ETnama;
    EditText ETtlp;
    int id_To_Update = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        ETnama = findViewById(R.id.ETnama);
        ETtlp = findViewById(R.id.ETtlp);
        ETemail = findViewById(R.id.ETemail);
        ETalamat = findViewById(R.id.ETalamat);
        mydb = new DBHelper(this);
    }

    public void run(View v) {
        if (ETnama.getText().toString().equals("") || ETtlp.getText().toString().equals("") || ETemail.getText().toString().equals("") || ETalamat.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Data Harus Lengkap", Toast.LENGTH_LONG).show();
        } else {
            if (!ETemail.getText().toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                Toast.makeText(getApplicationContext(), "Format Email Salah", Toast.LENGTH_LONG).show();
            } else {
                mydb.insertContact(ETnama.getText().toString(), ETtlp.getText().toString(), ETemail.getText().toString(), ETalamat.getText().toString());
                Toast.makeText(getApplicationContext(), "Insert Data Berhasil", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        }
    }
}
