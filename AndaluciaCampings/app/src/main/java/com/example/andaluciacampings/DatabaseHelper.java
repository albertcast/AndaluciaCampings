package com.example.andaluciacampings;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USER";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "NOMBRE";
    public static final String COL_5 = "APELLIDO";
    public static final String COL_6 = "NIVEL";
    public static final String COL_7 = "EXPERIENCIA";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String admin = "admin";
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USER TEXT, PASSWORD TEXT, NOMBRE TEXT, APELLIDO TEXT, NIVEL INTEGER, EXPERIENCIA INTEGER)");
        db.execSQL("INSERT INTO "+ TABLE_NAME+ " (" +
                COL_2 + "," +
                COL_3 + "," +
                COL_4 + "," +
                COL_5 + "," +
                COL_6 + "," +
                COL_7 + ")" +
                " VALUES ('admin','admin','admin','admin',99,0);");
        db.execSQL("INSERT INTO "+ TABLE_NAME+ " (" +
                COL_2 + "," +
                COL_3 + "," +
                COL_4 + "," +
                COL_5 + "," +
                COL_6 + "," +
                COL_7 + ")" +
                " VALUES ('usuario1','usuario1','usuario1','usuario1',1,0);");
        db.execSQL("INSERT INTO "+ TABLE_NAME+ " (" +
                COL_2 + "," +
                COL_3 + "," +
                COL_4 + "," +
                COL_5 + "," +
                COL_6 + "," +
                COL_7 + ")" +
                " VALUES ('usuario2','usuario2','usuario2','usuario2',6,0);");
        db.execSQL("INSERT INTO "+ TABLE_NAME+ " (" +
                COL_2 + "," +
                COL_3 + "," +
                COL_4 + "," +
                COL_5 + "," +
                COL_6 + "," +
                COL_7 + ")" +
                " VALUES ('usuario3','usuario3','usuario3','usuario3',4,0);");
        db.execSQL("INSERT INTO "+ TABLE_NAME+ " (" +
                COL_2 + "," +
                COL_3 + "," +
                COL_4 + "," +
                COL_5 + "," +
                COL_6 + "," +
                COL_7 + ")" +
                " VALUES ('usuario4','usuario4','usuario4','usuario4',12,0);");
        db.execSQL("INSERT INTO "+ TABLE_NAME+ " (" +
                COL_2 + "," +
                COL_3 + "," +
                COL_4 + "," +
                COL_5 + "," +
                COL_6 + "," +
                COL_7 + ")" +
                " VALUES ('usuario5','usuario5','usuario5','usuario5',20,0);");
        db.execSQL("INSERT INTO "+ TABLE_NAME+ " (" +
                COL_2 + "," +
                COL_3 + "," +
                COL_4 + "," +
                COL_5 + "," +
                COL_6 + "," +
                COL_7 + ")" +
                " VALUES ('usuario6','usuario6','usuario6','usuario6',12,0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String user, String password, String nombre, String apellido){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, user);
        contentValues.put(COL_3, password);
        contentValues.put(COL_4, nombre);
        contentValues.put(COL_5, apellido);
        contentValues.put(COL_6, 1);
        contentValues.put(COL_7, 0);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getAllData(int offset){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " ORDER BY " + COL_6 + " DESC LIMIT 6 OFFSET " + offset , null);
        return res;
    }

    public Cursor getUsername(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where USER = '" + user+"'", null);
        return res;
    }

    public boolean updateData(String id, String nombre, String apellido){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_4, nombre);
        contentValues.put(COL_5, apellido);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] { id });
        return true;
    }

    public boolean updateExperiencia(String user, int exp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor res = getUsername(user);
        if (res.moveToNext()){
            int experiencia, nivel;
            String id;
            id = res.getString(1);
            nivel = res.getInt(6);
            experiencia = res.getInt(7);
            if (experiencia + exp >= 100){
                nivel++;
                experiencia = experiencia + exp - 100;
            } else {
                experiencia += exp;
            }
            contentValues.put(COL_1, id);
            contentValues.put(COL_6, nivel);
            contentValues.put(COL_7, experiencia);
            db.update(TABLE_NAME, contentValues, "ID = ?", new String[] { id });
        }
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

}
