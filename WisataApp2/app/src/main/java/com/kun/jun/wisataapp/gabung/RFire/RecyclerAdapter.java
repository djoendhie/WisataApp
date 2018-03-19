package com.kun.jun.wisataapp.gabung.RFire;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kun.jun.wisataapp.R;
import com.kun.jun.wisataapp.client.MyHolder;
import com.kun.jun.wisataapp.client.PicassoClient;
import com.kun.jun.wisataapp.model.Hewan;

import java.util.ArrayList;
import java.util.List;

import static com.kun.jun.wisataapp.gabung.ntah.MyFuction.c;

/**
 * Created by Jun on 3/17/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder>{

    List<Hewan> list;
    Context context;
    private LayoutInflater inflater;
    private View convertView;

    public RecyclerAdapter(List<Hewan> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.cardview,parent,false);
        MyHolder MyHolder = new MyHolder(view);

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cardview, parent, false);
        }

        return MyHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        Hewan mylist = list.get(position);
        holder.name.setText(list.get(position).getName());
        holder.email.setText(list.get(position).getInfo());
        PicassoClient.downloading(context,list.get(position).getUrl(),holder.addresse);

        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ViewGroup parent = null;
//                View view = LayoutInflater.from(context).inflate(R.layout.pentetdetail,parent,false);
//                MyHolder MyHolder = new MyHolder(view);

                Dialog dialog = new Dialog(context);
                dialog.setTitle("I recomended this one for Your Holidays");

                //final Dialog dialog = new Dialog(c);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View x = inflater.inflate(R.layout.activity_card, null);
                dialog.setContentView(x);



                final TextView nameEditext = (TextView) dialog.findViewById(R.id.nameEditText);
                final ImageView urlEditext = (ImageView) dialog.findViewById(R.id.urlEditText);
                final TextView infoEditext = (TextView) dialog.findViewById(R.id.infoEditText);

                final String name = list.get(position).getName();
                final String info = list.get(position).getInfo();
//                final String url = hewanArrqayList.get(position).getUrl();

                final String id = list.get(position).getId_hewan();

                nameEditext.setText(name);
                PicassoClient.downloading(context,list.get(position).getUrl(),holder.addresse = urlEditext);
//                urlEditext.setImageURI(c, hewanArrayList.get(position).getUrl(), holder.img = url);
                infoEditext.setText(info);

                dialog.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        int arr = 0;
        try{
            if(list.size()==0){
                arr = 0;
            }
            else{
                arr=list.size();
            }
        }catch (Exception e){
        }
        return arr;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView name,email;
        ImageView addresse;
        public MyHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.vname);
            email= (TextView) itemView.findViewById(R.id.vemail);
            addresse = (ImageView)itemView.findViewById(R.id.vaddress);
//            address= (TextView) itemView.findViewById(R.id.vaddress);

        }
    }

}
