package com.example.e_poketra;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class addExpence extends AppCompatActivity {
    EditText date,desc,nominal;
    DatePickerDialog datePickerDialog;
    DatabaseHelper db;
    Button save,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expence);

        date= (EditText)findViewById(R.id.date);
        desc=(EditText)findViewById(R.id.desc);
        nominal=(EditText)findViewById(R.id.nominal);
        save=(Button)findViewById(R.id.save);
        back=(Button)findViewById(R.id.back);
        db= new DatabaseHelper(this);

        date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c= Calendar.getInstance();
                int m_year= c.get(Calendar.YEAR);
                int m_month= c.get(Calendar.MONTH);
                int m_day=c.get(Calendar.DAY_OF_MONTH);
                // date picker dialogue
                datePickerDialog = new DatePickerDialog(addExpence.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, m_year, m_month, m_day);
                datePickerDialog.show();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = desc.getText().toString();
                String dt=date.getText().toString();
                int n = Integer.parseInt(String.valueOf(nominal.getText()));
                String t = "Expence";

                    boolean checkInserttrans = db.addTransaction(dt, d, n, t);
                    clearText();
                    if (checkInserttrans == true) {
                        Toast.makeText(addExpence.this, "transaction Successfully Recorded", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(addExpence.this, "transaction record Failed", Toast.LENGTH_SHORT).show();
                    }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(addExpence.this,Myhome.class));
            }
        });

    }
    public void clearText(){
        date.setText("");
        desc.setText("");
        nominal.setText("");
    }
}