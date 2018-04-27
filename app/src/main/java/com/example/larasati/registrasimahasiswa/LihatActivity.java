package com.example.larasati.registrasimahasiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LihatActivity extends AppCompatActivity implements DataMhswAdapter.BtnClickListener{

    List<DataMhsw> dataMhswsList = new ArrayList<>();
    RecyclerView recyclerView;
    DataMhswAdapter dataMhswAdapter;

    private DatabaseHelper db;

    int positions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat);

        db = new DatabaseHelper(this);

        dataMhswsList.addAll(db.getAllData());

        recyclerView = findViewById(R.id.data_mhsw_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        dataMhswAdapter = new DataMhswAdapter(this);
        dataMhswAdapter.setDataMhsw(dataMhswsList, getApplicationContext());
        recyclerView.setAdapter(dataMhswAdapter);
    }

    @Override
    public void onItemClicked(int positions) { positions = positions; }

    public void detailData (View view) {
        try {
            int position = positions ;

            Intent intent3 = new Intent(this, DetailActivity.class);

            intent3.putExtra("nama_mhsw", dataMhswsList.get(position).getNama());
            intent3.putExtra("nim_mhsw", dataMhswsList.get(position).getNim());
            intent3.putExtra("prodi_mhsw", dataMhswsList.get(position).getProdi());
            intent3.putExtra("email_mhsw", dataMhswsList.get(position).getEmail());

            startActivity(intent3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteData(View view) {
        try {
            int position = positions;

            //deleting the note from db
            db.deleteData(dataMhswsList.get(position));

            //removing the note from the list
            dataMhswsList.remove(position);
            //dataMhswAdapter.notifyItemRemoved(position);
            Toast.makeText(this, "Data telah dihapus!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void kembaliMain (View view) {
        Intent intent4 = new Intent(this, MainActivity.class);
        startActivity(intent4);
    }
}
