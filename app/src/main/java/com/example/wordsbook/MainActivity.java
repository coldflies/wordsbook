package com.example.wordsbook;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<wordsList> wordslist;
    private   DBHelper dbHelper;
    SQLiteDatabase db;
    MyAdapter myAdapter;
    private TextView tv_chinese,tv_english,tv_example;
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
            case R.id.menu_help:
                Intent i = new Intent(this,HelpActivity.class);
                startActivity(i);
                break;


        }

        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this,"Word.db",null,1);
        dbHelper.getWritableDatabase();
        ListView lv = findViewById(R.id.listView);
        wordslist = new ArrayList<wordsList>();
        db = dbHelper.getWritableDatabase();
        Query();
        myAdapter = new MyAdapter(this);
        tv_english = findViewById(R.id.words);
        tv_chinese = findViewById(R.id.chinese);
        tv_example = findViewById(R.id.example);
        lv.setAdapter(myAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                wordsList word = wordslist.get(i);
               tv_english.setText(word.getEnglish());
                tv_chinese.setText(word.getChinese());
                tv_example.setText(word.getExample());


            }

        });


    }
    public class MyAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;

        public MyAdapter(Context context){
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount(){
            return wordslist.size();
        }
        @Override
        public Object getItem(int position){
            return null;
        }
        @Override
        public long getItemId(int position){
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            wordsList w = wordslist.get(position);
            ViewHolder viewHolder = null;
            if(convertView == null){
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.listitem_layout,null);
                viewHolder.txt_english = (TextView) convertView
                        .findViewById(R.id.TV_english);
                viewHolder.txt_chinese = (TextView) convertView
                        .findViewById(R.id.TV_chinese);
                viewHolder.txt_example = (TextView) convertView
                        .findViewById(R.id.tv_exmaple);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //向textview中插入数据
            viewHolder.txt_chinese.setText(w.getChinese());
            viewHolder.txt_english.setText(w.getEnglish());
            viewHolder.txt_example.setText(w.getExample());
            return convertView;
        }
    }
    public class ViewHolder {
        private TextView txt_english;
        private TextView txt_chinese;
        private TextView txt_example;
    }

    // 插入数据
    /*public void Insert() {
        for (int i = 0; i < 100; i++) {
            ContentValues values = new ContentValues();
            values.put("name", "张三" + i);
            values.put("salary", "123" + i + i);
            values.put("phone", "151" + i + i);
            db.insert("person", null, values);
        }
    }*/

    // 查询数据
    public void Query() {
        Cursor cursor = db.query("words", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                //遍历，取出数据并打印
                String english = cursor.getString(cursor.getColumnIndex("english"));
                String chinese = cursor.getString(cursor.getColumnIndex("chinese"));
                String example = cursor.getString(cursor.getColumnIndex("example"));
                // int page = cursor.getInt(cursor.getColumnIndex("page"));
                //double price = cursor.getDouble(cursor.getColumnIndex("price"));


                wordsList words = new wordsList(english,chinese,example);
                wordslist.add(words);
                Log.d("MainActivity","成功取出单词："+english);
            }while (cursor.moveToNext());
        }

    }


}
