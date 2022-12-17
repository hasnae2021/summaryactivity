package com.example.recapessai3;

import static com.example.recapessai3.DBmain.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayData extends AppCompatActivity {
    DBmain db;
    SQLiteDatabase sq;
    ListView listView;
    String[] name, choice, nature, sauce, toppings;
    int[] id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        fndid();
        displayData();
    }

    private void displayData() {
        db = new DBmain(this);
        sq = db.getReadableDatabase();
        Cursor cursor = sq.rawQuery("select *from " + TABLE_NAME + "", null);
        if (cursor.getCount() > 0) {
            id = new int[cursor.getCount()];
            name = new String[cursor.getCount()];
            choice = new String[cursor.getCount()];
            nature = new String[cursor.getCount()];
            sauce = new String[cursor.getCount()];
            toppings = new String[cursor.getCount()];

            int i = 0;
            while (cursor.moveToNext()) {
                id[i] = cursor.getInt(0);
                name[i] = cursor.getString(1);
                choice[i] = cursor.getString(2);
                nature[i] = cursor.getString(3);
                sauce[i] = cursor.getString(4);
                toppings[i] = cursor.getString(5);
                i++;
            }
            //now create inner class adapter
            Custom custom = new Custom();
            listView.setAdapter(custom);

        }
    }
    private void fndid() {
        listView = (ListView) findViewById(R.id.lv);
    }

    private class Custom extends BaseAdapter {
        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView1, textView2, textView3, textView4, textView5;
Button delete;
            convertView = LayoutInflater.from(DisplayData.this).inflate(R.layout.singledata, parent, false);
            textView1 = (TextView) convertView.findViewById(R.id.txtName);
            textView2 = (TextView) convertView.findViewById(R.id.txtChoice);
            textView3 = (TextView) convertView.findViewById(R.id.txtNature);
            textView4 = (TextView) convertView.findViewById(R.id.txtSauce);
            textView5 = (TextView) convertView.findViewById(R.id.txtToppings);
delete=(Button)convertView.findViewById(R.id.delete);
            //set value
            textView1.setText(name[position]);
            textView2.setText(choice[position]);
            textView3.setText(nature[position]);
            textView4.setText(sauce[position]);
            textView5.setText(toppings[position]);
delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        sq=db.getReadableDatabase();
        long recdelete=sq.delete(TABLE_NAME,"id="+id[position],null);
        if(recdelete!=-1){
            Toast.makeText(DisplayData.this, "Data deleted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DisplayData.this,MainActivity.class));
            displayData();
        }
    }
});
            return convertView;
        }

    }
}