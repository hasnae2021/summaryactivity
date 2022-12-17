package com.example.recapessai3;


import static com.example.recapessai3.DBmain.KEY_CHOICE;
import static com.example.recapessai3.DBmain.KEY_NAME;
import static com.example.recapessai3.DBmain.KEY_NATURE;


import static com.example.recapessai3.DBmain.KEY_SAUCE;
import static com.example.recapessai3.DBmain.KEY_TOPPINGS;
import static com.example.recapessai3.DBmain.TABLE_NAME;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DBmain db;
    SQLiteDatabase sq;
    //EditText editText;
    TextView textView;

    Spinner spinner;
    RadioButton vegetarienRadioButton,SpicyRadioButton;
    CheckBox addToppings,addSauce;
    Button submit,display;
    String[]choice={"emporté","Livraison à domicile"};//for spinner array



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create method
        findid();
        insertData();
    }

    private void insertData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=new DBmain(MainActivity.this);
                sq=db.getWritableDatabase();
                ContentValues cv =new ContentValues();
                //get name from edit textbox
                cv.put(KEY_NAME,textView.getText().toString().trim());



                //get value from spinner array
                cv.put(KEY_CHOICE,spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString().trim());
                //get the value from radiobutton
                if(vegetarienRadioButton.isChecked()){
                    cv.put(KEY_NATURE,vegetarienRadioButton.getText().toString().trim());

                }else{
                    cv.put(KEY_NATURE,SpicyRadioButton.getText().toString().trim());
                }
                //get value of checkbox
                if(addSauce.isChecked()){
                    cv.put(KEY_SAUCE,addSauce.getText().toString());

                }
                if(addToppings.isChecked()){
                    cv.put(KEY_TOPPINGS,addToppings.getText().toString());
                }



//now all value store into database table
                Long rec=sq.insert(TABLE_NAME,null,cv);
                if (rec !=null){
                    Toast.makeText(MainActivity.this, "Data insered successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();

                }


            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, DisplayData.class);
                startActivity(intent);
            }
        });
    }

    private void findid() {
        textView=(TextView) findViewById(R.id.sushiNameInfo);
        //textViews=(TextView) findViewById(R.id.txtPrice);
        spinner=(Spinner) findViewById(R.id.edtspinner);
        spinner.setOnItemSelectedListener( this);
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,choice);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        vegetarienRadioButton=(RadioButton)findViewById(R.id.vegetarienRadioButton);
        SpicyRadioButton=(RadioButton)findViewById(R.id.SpicyRadioButton);
        addSauce=(CheckBox) findViewById(R.id.addSauce);
        addToppings=(CheckBox)findViewById(R.id.addToppings);
        submit=(Button) findViewById(R.id.submit);
        display=(Button) findViewById(R.id.button);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}