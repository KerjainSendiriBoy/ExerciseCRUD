package com.example.exercisecrud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    protected Cursor cursor;
    DBHelper mydb;
    TextView TVnama;
    TextView TVtlp;
    TextView TVemail;
    TextView TValamat;
    int id_To_Update = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mydb = new DBHelper(this);
        TVnama = findViewById(R.id.TVnama);
        TVtlp = findViewById(R.id.TVtlp);
        TVemail = findViewById(R.id.TVemail);
        TValamat = findViewById(R.id.TValamat);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                //means this is the view part not the add contact part.
                cursor = mydb.getData(Value);
                id_To_Update = Value;
                cursor.moveToFirst();
                String nama = cursor.getString(cursor.getColumnIndex(DBHelper.MHS_COLUMN_NAMA));
                String phone = cursor.getString(cursor.getColumnIndex(DBHelper.MHS_COLUMN_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(DBHelper.MHS_COLUMN_EMAIL));
                String alamat = cursor.getString(cursor.getColumnIndex(DBHelper.MHS_COLUMN_ALAMAT));
                if (!cursor.isClosed()) {
                    cursor.close();
                }
                TVnama.setText(nama);
                TVnama.setFocusable(false);
                TVnama.setClickable(false);
                TVtlp.setText(phone);
                TVtlp.setFocusable(false);
                TVtlp.setClickable(false);
                TVemail.setText(email);
                TVemail.setFocusable(false);
                TVemail.setClickable(false);
                TValamat.setText(alamat);
                TValamat.setFocusable(false);
                TValamat.setClickable(false);
            }
        }
    }
}
