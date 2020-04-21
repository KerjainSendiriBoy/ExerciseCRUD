package com.example.exercisecrud;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import static android.R.layout.simple_list_item_1;

public class MainActivity extends AppCompatActivity {
    DBHelper mydb = new DBHelper(this);
    Intent intent;
    ListView obj;
    String[] daftar;
    protected Cursor cursor;
    public static MainActivity ma;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), Insert.class);
                startActivity(intent);
            }
        });
        mydb = new DBHelper(this);
        ArrayList array_list = mydb.getAllCotacts();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, simple_list_item_1, array_list);
        obj = findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
//                ArrayList<String> arrayList = mydb.getAllCotacts();
//                final CharSequence[] dialogitem = {"Lihat Kontak", "Update Kontak", "Hapus Kontak"};
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Pilihan");
//                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int item) {
//                        switch (item) {
//                            case 0:
//                                Intent i = new Intent(getApplicationContext(), Profile.class);
//                                i.putExtra("nama", arrayList);
//                                startActivity(i);
//                                break;
//                            case 1:
//                                Intent in = new Intent(getApplicationContext(), Update.class);
//                                in.putExtra("nama", arrayList);
//                                startActivity(in);
//                                break;
//                            case 2:
//                                SQLiteDatabase db = mydb.getWritableDatabase();
//                                db.execSQL("delete from contact where nama = '" + arrayList + "'");
//                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                                break;
//                        }
//                    }
//                });
//                builder.create().show();
            }
        });
        ((ArrayAdapter) obj.getAdapter()).notifyDataSetInvalidated();
    }

//    public void RefreshList() {
//        SQLiteDatabase db = mydb.getReadableDatabase();
//        cursor = db.rawQuery("SELECT * FROM contact", null);
//        daftar = new String[cursor.getCount()];
//        cursor.moveToFirst();
//        for (int cc = 0; cc < cursor.getCount(); cc++) {
//            cursor.moveToPosition(cc);
//            daftar[cc] = cursor.getString(1);
//        }
//        obj = findViewById(R.id.list);
//        obj.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
//        obj.setSelected(true);
//        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//
//            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
//                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
//                final CharSequence[] dialogitem = {"Edit", "Hapus"};
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int item) {
//                        switch (item) {
////                            case 0:
////                                Intent i = new Intent(getApplicationContext(), LihatBiodataActivity.class);
////                                i.putExtra("nama", selection);
////                                startActivity(i);
////                                break;
//                            case 1:
//                                intent = new Intent(getApplicationContext(), Update.class);
//                                intent.putExtra("nama", selection);
//                                startActivity(intent);
//                                break;
//                            case 2:
//                                SQLiteDatabase db = mydb.getWritableDatabase();
//                                db.execSQL("delete from contact where nama = '" + selection + "'");
//                                RefreshList();
//                                break;
//                        }
//                    }
//                });
//                builder.create().show();
//            }
//        });
//        ((ArrayAdapter) obj.getAdapter()).notifyDataSetInvalidated();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.MnCari) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
}
