package com.kun.jun.wisataapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kun.jun.wisataapp.R;
import com.kun.jun.wisataapp.client.FirebaseClient;
import com.kun.jun.wisataapp.client.MyHolder;
import com.kun.jun.wisataapp.client.PicassoClient;
import com.kun.jun.wisataapp.model.Hewan;

import java.util.ArrayList;

/**
 * Created by Blackswan on 7/19/2017.
 */

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<Hewan> hewanArrayList;
    LayoutInflater inflater;
    DatabaseReference ref;
    public ImageView img1;
    FirebaseClient firebaseClient;
    Firebase firebase;
    private int viewss;


    public CustomAdapter(Context c, ArrayList<Hewan> hewanArrayList, DatabaseReference ref, Firebase firebase) {
        this.ref = ref;
        this.c = c;
        this.hewanArrayList = hewanArrayList;
        this.firebase = firebase;

    }


    @Override
    public int getCount() {
        return hewanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return hewanArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_layout, parent, false);
        }
        final MyHolder holder = new MyHolder(convertView);
        holder.nameText.setText(hewanArrayList.get(position).getName());
        holder.infoText.setText(hewanArrayList.get(position).getInfo());
        PicassoClient.downloading(c, hewanArrayList.get(position).getUrl(), holder.img);


        holder.infoText.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Dialog dialog = new Dialog(c);
                dialog.setTitle("I recomended this one for Your Holidays");
                //final Dialog dialog = new Dialog(c);
                LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.pentetdetail, null);
                dialog.setContentView(view);


                final TextView nameEditext = (TextView) dialog.findViewById(R.id.nameEditText);
                final ImageView urlEditext = (ImageView) dialog.findViewById(R.id.urlEditText);
                final TextView infoEditext = (TextView) dialog.findViewById(R.id.infoEditText);

                final String name = hewanArrayList.get(position).getName();
                final String info = hewanArrayList.get(position).getInfo();
//                final String url = hewanArrqayList.get(position).getUrl();

                final String id = hewanArrayList.get(position).getId_hewan();

                nameEditext.setText(name);
                PicassoClient.downloading(c,hewanArrayList.get(position).getUrl(),holder.img = urlEditext);
//                urlEditext.setImageURI(c, hewanArrayList.get(position).getUrl(), holder.img = url);
                infoEditext.setText(info);

                dialog.show();
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialogs = (Dialog) new Dialog(c);
                dialogs.setTitle("I recomended this one for Your Holidays");
                //final Dialog dialog = new Dialog(c);
                LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.pentetdetail, null);
                dialogs.setContentView(view);


                final TextView nameEditext = (TextView) dialogs.findViewById(R.id.nameEditText);
                final ImageView urlEditext = (ImageView) dialogs.findViewById(R.id.urlEditText);
                final TextView infoEditext = (TextView) dialogs.findViewById(R.id.infoEditText);

                Button btnupdate = (Button) dialogs.findViewById(R.id.updateBtn);
                Button btndelete = (Button) dialogs.findViewById(R.id.deleteBtn);

                final String name = hewanArrayList.get(position).getName();
                final String info = hewanArrayList.get(position).getInfo();
                final String url = hewanArrayList.get(position).getUrl();

                final String id = hewanArrayList.get(position).getId_hewan();
                nameEditext.setText(name);
                PicassoClient.downloading(c,hewanArrayList.get(position).getUrl(),holder.img = urlEditext);
                infoEditext.setText(info);

//                btnupdate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Toast.makeText(c, "Updated Successfully", Toast.LENGTH_SHORT).show();
//                     }
//                });
//                btndelete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Toast.makeText(c, "deleted successfully", Toast.LENGTH_SHORT).show();
//                     }
//                });
                dialogs.show();

            }
        });

        return convertView;
    }
 }
