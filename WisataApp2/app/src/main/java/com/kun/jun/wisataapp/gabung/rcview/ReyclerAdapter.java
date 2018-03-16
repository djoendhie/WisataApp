package com.kun.jun.wisataapp.gabung.rcview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kun.jun.wisataapp.R;

public class ReyclerAdapter extends RecyclerView.Adapter<ReyclerViewHolder> {


    private final Context context;

    String [] name={"Padang","Pikumbuah","Bukik","Pasaman",
            "Lintau","Sangka","Manggilang","Mudiak", "Ngalau", "Warnet", "Labuah Baru", "Nunang"};
    // menampilkan list item dalam bentuk text dengan tipe data string dengan variable name

    LayoutInflater inflater;
    public ReyclerAdapter(IsiActivity context) {
        this.context=context;
        inflater=LayoutInflater.from(context);
    }


    @Override
    public ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v=inflater.inflate(R.layout.item_list, parent, false);

        ReyclerViewHolder viewHolder=new ReyclerViewHolder(v);
        return viewHolder;    }

    @Override
    public void onBindViewHolder(ReyclerViewHolder holder, int position) {

        holder.tv1.setText(name[position]);
        holder.tv1.setOnClickListener(clickListener);
        holder.imageView.setOnClickListener(clickListener);
        holder.tv1.setTag(holder);
        holder.imageView.setTag(holder);
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//member aksi saat cardview diklik berdasarkan posisi tertentu
            ReyclerViewHolder vholder = (ReyclerViewHolder) v.getTag();

            int position = vholder.getPosition();


            Toast.makeText(context, "Kampuang Saya Di" + name, Toast.LENGTH_LONG).show();

// biar bisa pindah posisi ganti Toat yg atas ama yg bawah ni tapi cara ne pie to bg ....????
//            if (position == 0 && position < getItemCount())            {                Intent intent = new Intent(context , MainActivity2.class);
//                context.startActivity(intent);
//            }
        }
    };

    @Override
    public int getItemCount() {
        return name.length;
    }
}
