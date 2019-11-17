package com.example.wordsbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private  DBHelper dbHelper;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true; }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_book:
            {
                Intent i = new Intent(this,BookActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_setting:
            {
                Intent i = new Intent(this,SettingActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_update:
            {
                Intent i = new Intent(this,UpdateActivity.class);
                startActivity(i);
                break;
            }

        }

        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this,"Word.db",null,1);
        dbHelper.getWritableDatabase();
    }
}
