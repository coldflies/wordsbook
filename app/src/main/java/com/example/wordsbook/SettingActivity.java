package com.example.wordsbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    private DBHelper dbHelper;
    private TextView textview;
    private EditText inpute,inputc,inputd,inputf,ed_iexmaple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new DBHelper(this, "Word.db", null, 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

       Button btI = findViewById(R.id.button_inputI);
       Button btD = findViewById(R.id.button_inputD);
       Button btF = findViewById(R.id.button_inputF);
        //EditText  ed_ie = findViewById(R.id.inputE);
        //EditText ed_ic = findViewById(R.id.inputC);
       ed_iexmaple = findViewById(R.id.input_example);
        //textview = findViewById(R.id.textview);
       inpute =  findViewById(R.id.inputE);
       inputc = findViewById(R.id.inputC);
       inputd = findViewById(R.id.inputD);
       inputf = findViewById(R.id.inputF);
       textview =findViewById(R.id.find);

        //btI.setOnClickListener(this);
        //btD.setOnClickListener(this);
        //btF.setOnClickListener(this);
        btI.setOnClickListener(this);
        btF.setOnClickListener(this);
        btD.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view){

        switch (view.getId()){
            case R.id.button_inputI:

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String inputE = inpute.getText().toString();
                String inputC = inputc.getText().toString();
                String inputEx = ed_iexmaple.getText().toString();




                //创建存放数据的ContentValues对象
               ContentValues values = new ContentValues();
               values.put("english",inputE);
                values.put("chinese",inputC);
                values.put("example",inputEx);
                //数据库执行插入命令
                db.insert("words", null, values);
                break;
            case R.id.button_inputD:
                db = dbHelper.getWritableDatabase();
                String st =inputd.getText().toString();

                db.delete("words","english=?",new String[]{st});
                break;
            case R.id.button_inputF:
                 db = dbHelper.getWritableDatabase();
                //Cursor cursor = db.query("words",null,null,null,null,null,null);
                String find = inputf.getText().toString();
                Cursor cursor = db.rawQuery("select * from words where english like ? ",new String[]{ "%"+find+"%"});
                String textview_data = "";
                if (cursor.moveToFirst()){
                    do{
                        //遍历，取出数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("english"));
                        String author = cursor.getString(cursor.getColumnIndex("chinese"));

                        Log.d("MainActivity","英文为："+name);
                        Log.d("MainActivity","中文为："+author);
                        textview_data = textview_data + "\n" + name + "  " + author;
                    }while (cursor.moveToNext());
                }
                String str = db.rawQuery("select * from words",null).toString();
                textview.setText(textview_data);
                Log.d("MainActivity","全部内容为：\n"+str);
                break;

        }
    }
}
