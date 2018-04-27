package com.example.larasati.registrasimahasiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView namaMhsw, nimMhsw, prodiMhsw, emailMhsw;
    String nama, nim, prodi, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        namaMhsw = findViewById(R.id.data_nama);
        nimMhsw = findViewById(R.id.data_nim);
        prodiMhsw = findViewById(R.id.data_prodi);
        emailMhsw = findViewById(R.id.data_email);

        Intent receiveIntent = getIntent();
        nama = receiveIntent.getStringExtra("nama_mhsw");
        nim = receiveIntent.getStringExtra("nim_mhsw");
        prodi = receiveIntent.getStringExtra("prodi_mhsw");
        email = receiveIntent.getStringExtra("email_mhsw");

        namaMhsw.setText(nama);
        nimMhsw.setText(nim);
        prodiMhsw.setText(prodi);
        emailMhsw.setText(email);
    }

    public void kembaliLihat(View view){
        Intent intent6 = new Intent(this, LihatActivity.class);
        startActivity(intent6);
    }
}
