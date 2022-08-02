package com.example.e_poketra;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "money_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TRANS_TABLE = "trans";
    private static final String USER_TABLE = "users";



    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    public static DatabaseHelper instanceOfDatabase(Context context)
//    {
//        if(dbhelper == null)
//            dbhelper = new DatabaseHelper(context);
//
//        return dbhelper;
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRANS="CREATE TABLE " + TRANS_TABLE+"("
                +"id INTEGER PRIMARY KEY,date TEXT,des TEXT,nominal INTEGER,type TEXT)";
        String CREATE_LOGIN = "CREATE TABLE "+ USER_TABLE+"(username TEXT primary key, password TEXT)";
        db.execSQL(CREATE_LOGIN);
        db.execSQL(CREATE_TRANS);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " +TRANS_TABLE);
    }

    public boolean insertUser(String username, String password) {
        SQLiteDatabase db= this.getReadableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = db.insert(USER_TABLE, null, contentValues);
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }
    public String getPassword(){
        String result="";
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select password from "+USER_TABLE+" where username='user'",null);
        if (cursor.moveToFirst()) {
            result=cursor.getString(0);
        } else {
            result="";
        }
        return result;
    }

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");



    public boolean addTransaction(String date, String description, Integer Nominal, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding user name in users table
        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("des", description);
        values.put("nominal", Nominal);
        values.put("type", type);
        // db.insert(TABLE_USER, null, values);
        long id = db.insert(TRANS_TABLE, null, values);
        if(id==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }

    }
    public Cursor getData(){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from "+TRANS_TABLE, null);
        return cursor;
    }
    public int getExpence(){
        int total=0;
        String expRes = "";
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select sum(nominal) from "+TRANS_TABLE+" where type='Expence'",null);
        if (cursor.moveToFirst()) {
            total=cursor.getInt(0);
        } else {
            total=-1;
        }
        return total;
    }
    public int getIncome(){
        int total=0;
        String expRes = "";
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select sum(nominal) from "+TRANS_TABLE+" where type='Income'",null);
        if (cursor.moveToFirst()) {
            total=cursor.getInt(0);
        } else {
            total=-1;
        }
        return total;
    }
    public void update_user (String old_PASS , String new_PASS )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("UPDATE "+USER_TABLE+" SET password='"+new_PASS+ "' WHERE password= '"+old_PASS+"'" );
    }
}
