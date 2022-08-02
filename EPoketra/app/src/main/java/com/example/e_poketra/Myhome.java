package com.example.e_poketra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Myhome extends AppCompatActivity {
    DatabaseHelper dbhelp;
    TextView expence,income;
    int total_exp;
//    ArrayList<String>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myhome);
        dbhelp= new DatabaseHelper(this);

        final ImageView inc= (ImageView) findViewById(R.id.imgInc);
        final ImageView exp= (ImageView) findViewById(R.id.imgExp);
        final ImageView listTrsn= (ImageView) findViewById(R.id.imglist);
        final ImageView sett= (ImageView) findViewById(R.id.imgSet);
        expence=(TextView)findViewById(R.id.exp);
        income=(TextView)findViewById(R.id.inc);

        int fexp= dbhelp.getExpence();
//        System.out.println(fexp);
        expence.setText("Expence RP. "+String.valueOf(fexp));

        int finc=dbhelp.getIncome();
        income.setText("Income RP. "+String.valueOf(finc));
        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Myhome.this,addIncome.class));
            }
        });
        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Myhome.this, addExpence.class));
            }
        });
        listTrsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Myhome.this,listTrans.class));
            }
        });
        sett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Myhome.this,setting.class));

            }
        });
//
    }
}