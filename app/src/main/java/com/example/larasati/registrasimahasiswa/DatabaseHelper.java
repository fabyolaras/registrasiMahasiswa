package com.example.larasati.registrasimahasiswa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Larasati on 26/04/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "datamhsw_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create data table
        db.execSQL(DataMhsw.CREATE_TABLE);
    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DataMhsw.TABLE_NAME);

        //Create tables again
        onCreate(db);
    }

    public long insertData(String nama, String nim, String prodi, String email){
        //get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DataMhsw.COLUMN_NAMA, nama);
        values.put(DataMhsw.COLUMN_NIM, nim);
        values.put(DataMhsw.COLUMN_PRODI, prodi);
        values.put(DataMhsw.COLUMN_EMAIL, email);

        //insert row
        long id = db.insert(DataMhsw.TABLE_NAME, null, values);

        //close db connection
        db.close();

        //return newly inserted row id
        return id;
    }

    public DataMhsw getDataMhsw (long id) {
        //get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DataMhsw.TABLE_NAME,
                new String[]{DataMhsw.COLUMN_ID, DataMhsw.COLUMN_NAMA, DataMhsw.COLUMN_NIM, DataMhsw.COLUMN_PRODI, DataMhsw.COLUMN_EMAIL},
                DataMhsw.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        //prepare data mahasiswa object
        DataMhsw dataMhsw = new DataMhsw(
                cursor.getInt(cursor.getColumnIndex(DataMhsw.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(DataMhsw.COLUMN_NAMA)),
                cursor.getString(cursor.getColumnIndex(DataMhsw.COLUMN_NIM)),
                cursor.getString(cursor.getColumnIndex(DataMhsw.COLUMN_PRODI)),
                cursor.getString(cursor.getColumnIndex(DataMhsw.COLUMN_EMAIL)));

        //close the db connection
        db.close();

        return dataMhsw;
    }

    public List<DataMhsw> getAllData() {
        List<DataMhsw> data = new ArrayList<>();

        //Select All Query
        String selectQuery = "SELECT * FROM " + DataMhsw.TABLE_NAME + " ORDER BY " +
                DataMhsw.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DataMhsw dataMhsw = new DataMhsw();
                dataMhsw.setId(cursor.getInt(cursor.getColumnIndex(DataMhsw.COLUMN_ID)));
                dataMhsw.setNama(cursor.getString(cursor.getColumnIndex(DataMhsw.COLUMN_NAMA)));
                dataMhsw.setNim(cursor.getString(cursor.getColumnIndex(DataMhsw.COLUMN_NIM)));
                dataMhsw.setProdi(cursor.getString(cursor.getColumnIndex(DataMhsw.COLUMN_PRODI)));
                dataMhsw.setEmail(cursor.getString(cursor.getColumnIndex(DataMhsw.COLUMN_EMAIL)));

                data.add(dataMhsw);
            } while (cursor.moveToNext());
        }

        //close db connection
        db.close();

        //return data list
        return data;
    }

    public int getDataCount() {
        String countQuery = "SELECT * FROM " + DataMhsw.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        //return count
        return count;
    }

    public int updateData(DataMhsw dataMhsw) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataMhsw.COLUMN_NAMA, dataMhsw.getNama());
        values.put(DataMhsw.COLUMN_NIM, dataMhsw.getNim());
        values.put(DataMhsw.COLUMN_PRODI, dataMhsw.getProdi());
        values.put(DataMhsw.COLUMN_EMAIL, dataMhsw.getEmail());

        //updating row
        return db.update(dataMhsw.TABLE_NAME,values, DataMhsw.COLUMN_ID + "=?",
                new String[]{String.valueOf(dataMhsw.getId())});
    }

    public void deleteData(DataMhsw dataMhsw) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataMhsw.TABLE_NAME, DataMhsw.COLUMN_ID + "=?",
                new String[]{String.valueOf(dataMhsw.getId())});
        db.close();
    }
}
