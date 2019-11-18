package com.example.wordsbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private EditText per,chinese,english,example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new DBHelper(this, "Word.db", null, 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        per = findViewById(R.id.inputUE);
        chinese = findViewById(R.id.inputUC);
        english = findViewById(R.id.inputUE);
        example = findViewById(R.id.input_example);
        Button bt = findViewById(R.id.button_inputU);
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("english",english.getText().toString());
                values.put("chinese",chinese.getText().toString());
                values.put("example",example.getText().toString());
                db.update("words",values,"english = ?",new String[]{ per.getText().toString()});
                Toast.makeText(UpdateActivity.this,"修改完成",Toast.LENGTH_SHORT);
            }
        });


    }
}
