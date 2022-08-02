package com.example.e_poketra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class listTrans extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> date,desc,nom,typ;
    DatabaseHelper dbhelp;
    MyAdapter adapter;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trans);
        dbhelp= new DatabaseHelper(this);
        date= new ArrayList<>();
        desc=new ArrayList<>();
        nom= new ArrayList<>();
        typ=new ArrayList<>();
        recyclerView= findViewById(R.id.recyclerview);
        adapter= new MyAdapter(this,date,desc,nom,typ);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(listTrans.this,Myhome.class));
            }
        });

    }

    private void displayData() {
        Cursor cursor=dbhelp.getData();
        if (cursor.getCount()==0){
            Toast.makeText(listTrans.this,"No recorded transaction",Toast.LENGTH_SHORT).show();
            return;
        }else {
            while (cursor.moveToNext()){
                date.add(cursor.getString(1));
                desc.add(cursor.getString(2));
                nom.add(cursor.getString(3));
                typ.add(cursor.getString(4));
            }
        }
    }
}