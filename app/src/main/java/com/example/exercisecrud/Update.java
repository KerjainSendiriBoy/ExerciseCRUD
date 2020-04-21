package com.example.exercisecrud;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Update extends AppCompatActivity {
    protected Cursor cursor;
    Button BtnBtl;
    Button BtnUbah;
    DBHelper mydb;
    EditText ETalamatU;
    EditText ETemailU;
    EditText ETnamaU;
    EditText ETtlpU;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        updatedata();
    }

    public void updatedata() {
        mydb = new DBHelper(this);
        ETnamaU = findViewById(R.id.ETnamaU);
        ETtlpU = findViewById(R.id.ETtlpU);
        ETemailU = findViewById(R.id.ETemailU);
        ETalamatU = findViewById(R.id.ETalamatU);
        BtnUbah = findViewById(R.id.BtnUbah);
        BtnBtl = findViewById(R.id.BtnBtl);

        SQLiteDatabase db = mydb.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM contact WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            ETnamaU.setText(cursor.getString(0).toString());
            ETtlpU.setText(cursor.getString(1).toString());
            ETemailU.setText(cursor.getString(2).toString());
            ETalamatU.setText(cursor.getString(3).toString());
        }

        BtnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = mydb.getWritableDatabase();
                db.execSQL("update contact set nama='" +
                        ETnamaU.getText().toString() + "', tgl='" +
                        ETtlpU.getText().toString() + "', jk='" +
                        ETemailU.getText().toString() + "', alamat='" +
                        ETalamatU.getText().toString() + "' where id'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        BtnBtl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}