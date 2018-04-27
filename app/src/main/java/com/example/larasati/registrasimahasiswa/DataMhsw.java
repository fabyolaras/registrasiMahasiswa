package com.example.larasati.registrasimahasiswa;

/**
 * Created by Larasati on 26/04/2018.
 */

public class DataMhsw {
    public static final String TABLE_NAME = "dataMahasiswa";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_NIM = "nim";
    public static final String COLUMN_PRODI = "prodi";
    public static final String COLUMN_EMAIL = "email";

    int id;
    String nama, nim, prodi, email;

    //Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE_TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAMA + " TEXT,"
                    + COLUMN_NIM + " TEXT,"
                    + COLUMN_PRODI + " TEXT,"
                    + COLUMN_EMAIL + " TEXT"
                    + ")";

    public DataMhsw(){
    }

    public DataMhsw (int id, String nama, String nim, String prodi, String email) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.prodi = prodi;
        this.email = email;
    }

    public int getId() {return id;}

    public int setId(int id) {
        this.id = id;
        return id;}

    public String getNama() {return nama;}

    public String setNama(String nama) {
        this.nama = nama;
        return nama;}

    public String getNim() {return nim;}

    public String setNim(String nim) {
        this.nim = nim;
        return nim;}

    public String getProdi() {return prodi;}

    public String setProdi(String prodi) {
        this.prodi = prodi;
        return prodi;}

    public String getEmail() {return email;}

    public String setEmail(String email) {
        this.email = email;
        return email;}
}
