package com.example.larasati.registrasimahasiswa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Larasati on 26/04/2018.
 */

public class DataMhswAdapter extends RecyclerView.Adapter<DataMhswAdapter.DataViewHolder> {

    List<DataMhsw> dataMhsw;
    Context context;
    BtnClickListener btnClickListener;

    private int lastSelect = -1;

    public interface BtnClickListener {
        void onItemClicked(int position);
    }

    public DataMhswAdapter(BtnClickListener btnClickListener) {
        this.btnClickListener = btnClickListener;
    }

    public void setDataMhsw(List<DataMhsw> dataMhsw, Context context) {
        this.dataMhsw = dataMhsw;
        this.context = context;
    }

    public DataMhswAdapter(List<DataMhsw> dataMhsw, Context context) {
        this.dataMhsw = dataMhsw;
        this.context = context;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lihat, parent, false);

        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        DataMhsw d = dataMhsw.get(position);

        holder.txtNama.setText(d.getNama());
        holder.btnSelect.setChecked(lastSelect == position);
    }

    @Override
    public int getItemCount() {
        return dataMhsw.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama;
        RadioButton btnSelect;

        public DataViewHolder(View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.item_nama);
            btnSelect = itemView.findViewById(R.id.radioButton);

//            View.OnClickListener clickListener = new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    lastSelect = getAdapterPosition();
//                    notifyDataSetChanged();
//                }
//            };
//
//            itemView.setOnClickListener(clickListener);
//            btnSelect.OnClickListener(clickListener);

            btnSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelect = getAdapterPosition();
                    notifyDataSetChanged();
                    btnClickListener.onItemClicked(lastSelect);

                    Toast.makeText(DataMhswAdapter.this.context, "Data yang dipilih : " + txtNama.getText(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}

