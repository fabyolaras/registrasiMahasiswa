package com.example.larasati.registrasimahasiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Registrasi extends AppCompatActivity {

    EditText namaMhsw, nimMhsw, prodiMhsw, emailMhsw;
    Button btn_simpan, btn_kosong, btn_kembali;

    public DataMhswAdapter mhswAdapter;
    private List<DataMhsw> dataMhswList = new ArrayList<>();
    public RecyclerView recyclerView;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        db = new DatabaseHelper(this);

        namaMhsw = findViewById(R.id.edit_nama);
        nimMhsw = findViewById(R.id.edit_nim);
        prodiMhsw = findViewById(R.id.edit_prodi);
        emailMhsw = findViewById(R.id.edit_email);
        btn_kosong = findViewById(R.id.button_kosong);
        btn_simpan = findViewById(R.id.button_simpan);
        btn_kembali = findViewById(R.id.button_kembali);
    }

    public void createData(View view) {
        String nama = namaMhsw.getText().toString();
        String noinduk = nimMhsw.getText().toString();
        String departemen = prodiMhsw.getText().toString();
        String emails = emailMhsw.getText().toString();

        long id = db.insertData(nama, noinduk, departemen, emails);

        //get the newly inserted data from db
        DataMhsw d = db.getDataMhsw(id);

        if (d != null) {
            //adding new note to array list at 0 position
            dataMhswList.add(0, d);

            //refreshing the list
            //mhswAdapter.notifyDataSetChanged();
        }

        Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
    }

    public void kosongData(View view) {
        namaMhsw.setText("");
        nimMhsw.setText("");
        prodiMhsw.setText("");
        emailMhsw.setText("");
    }

    public void backMain(View view) {
        Intent intent5 = new Intent(this, MainActivity.class);
        startActivity(intent5);
    }
}
