package com.example.e_poketra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class setting extends AppCompatActivity {

    DatabaseHelper dbhelp;
    TextView oldp,newp;
    Button save,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        dbhelp=new DatabaseHelper(this);
        oldp= (TextView)findViewById(R.id.oldpsw);
        newp= (TextView)findViewById(R.id.newpsw);
        save=(Button)findViewById(R.id.saveNew);
        back=(Button)findViewById(R.id.backS);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldP= dbhelp.getPassword();
                String oldP1=oldp.getText().toString();
                String newP= newp.getText().toString();
                if (oldP.equals(oldP1) && newP !=""){
                    dbhelp.update_user(oldP1,newP);
                    Toast.makeText(setting.this,"Password Changed Successfully",Toast.LENGTH_SHORT);
                    clearText();
                }else if(oldP != oldP1 && newP !="") {
                    Toast.makeText(setting.this,"Wrong Old Password ",Toast.LENGTH_SHORT);
                }else {
                    Toast.makeText(setting.this,"Please, Fill out all Field ",Toast.LENGTH_SHORT);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(setting.this,Myhome.class));
            }
        });

        }
            public void clearText(){
                oldp.setText("");
                newp.setText("");
            }
}