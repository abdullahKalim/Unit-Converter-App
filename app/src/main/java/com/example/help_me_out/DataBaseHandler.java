package com.example.help_me_out;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    public static final String tableCalc="CALC_TABLE";
    public static final String tablePostPre="PosTPre_TABLE";
    public static final String tableDictionary="DICTIONARY_TABLE";
    public static final String tableCurrency="CURRENCY_TABLE";
    public static final String tableCrypto="CRYPTO_TABLE";
    public static final String column_id="column_id";
    public static final String expression="expression";
    public static final String result="result";
    public DataBaseHandler(@Nullable Context context) {
        super(context, "database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableCalc="CREATE TABLE IF NOT EXISTS "+tableCalc+" ("+column_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+expression+" TEXT,"+result+" TEXT)";
        String createTablePostPre="CREATE TABLE IF NOT EXISTS "+tablePostPre+" ("+column_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+expression+" TEXT,"+result+" TEXT)";
        String createTableDictionary="CREATE TABLE IF NOT EXISTS "+tableDictionary+" ("+column_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+expression+" TEXT,"+result+" TEXT)";
        String createTableCurrency="CREATE TABLE IF NOT EXISTS "+tableCurrency+" ("+column_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+expression+" TEXT,"+result+" TEXT)";
        String createTableCrypto="CREATE TABLE IF NOT EXISTS "+tableCrypto+" ("+column_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+expression+" TEXT,"+result+" TEXT)";
        db.execSQL(createTableCalc);
        db.execSQL(createTablePostPre);
        db.execSQL(createTableDictionary);
        db.execSQL(createTableCurrency);
        db.execSQL(createTableCrypto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void add(DataModule dataModule,String tabel)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(expression,dataModule.getExpression());
        cv.put(result,dataModule.getResult());
        db.insert(tabel,null,cv);
        db.close();
    }

    public List<DataModule> getAll(String Table)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+Table;
        Cursor cursor=db.rawQuery(query,null);
        List<DataModule> dm=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do
            {
                int id=cursor.getInt(0);
                String exp=cursor.getString(1);
                String res=cursor.getString(2);
                DataModule dataModule=new DataModule(exp,res,-1);
                dm.add(dataModule);
            }while(cursor.moveToNext());
        }
        else
        {
            DataModule dataModule=new DataModule("","-1",-1);
            dm.add(dataModule);
        }
        db.close();
        cursor.close();
        return dm;
    }
 public void deleteAll(String tableDel)
 {
     SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
     String deleteQuery="DELETE FROM "+tableDel;
     sqLiteDatabase.execSQL(deleteQuery);
 }

}
