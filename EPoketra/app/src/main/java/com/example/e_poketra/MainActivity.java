package com.example.e_poketra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView username=(TextView) findViewById(R.id.username);
        final TextView password=(TextView) findViewById(R.id.password);
        Button login= (Button) findViewById(R.id.loginbtn);
        DatabaseHelper db= new DatabaseHelper(this);
        String p= db.getPassword();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("user") && password.getText().toString().equals(p)){
                    startActivity(new Intent(MainActivity.this,Myhome.class));
//                    String usr= username.getText().toString();
//                    String psw= password.getText().toString();
//                    db.insertUser(usr,psw);
                }else if(username.getText().toString().equals("user") && password.getText().toString()!=p){

                    Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}