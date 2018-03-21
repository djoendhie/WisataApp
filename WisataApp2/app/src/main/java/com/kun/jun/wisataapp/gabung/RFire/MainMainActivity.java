package com.kun.jun.wisataapp.gabung.RFire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kun.jun.wisataapp.MainActivity;
import com.kun.jun.wisataapp.R;
import com.kun.jun.wisataapp.gabung.ntah.MyFuction;
import com.kun.jun.wisataapp.gabung.ntah.SessionManager;
import com.kun.jun.wisataapp.model.Hewan;

import java.util.ArrayList;
import java.util.List;

public class MainMainActivity extends SessionManager {

    /// actyvity ini kagak di pake
    // jangan di hapus tapi
    // buat referensi

    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<Hewan> list;
    RecyclerView recycle;
    Button view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
         recycle = (RecyclerView) findViewById(R.id.recycle);



        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("hewan");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list = new ArrayList<Hewan>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    Hewan value = dataSnapshot1.getValue(Hewan.class);
                    Hewan fire = new Hewan();
                    String name = value.getName();
                    String address = value.getUrl();
                    String email = value.getInfo();
                    fire.setName(name);
                    fire.setInfo(email);
                    fire.setUrl(address);
                    list.add(fire);
                }

                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,MainMainActivity.this);
//                RecyclerView.LayoutManager recyce = new GridLayoutManager(MainMainActivity.this,2);
                 RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainMainActivity.this);
                // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                recycle.setLayoutManager(recyce);
                recycle.setItemAnimator( new DefaultItemAnimator());
                recycle.setAdapter(recyclerAdapter);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });


    }
    public void myIntent(Class kelastujuan){
        startActivity(new Intent(c,kelastujuan));
    }

}

//    FirebaseDatabase database;
//    DatabaseReference myRef;
//    List<Hewan> list;
//    RecyclerView recycle;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_main);
//
//
//        recycle = (RecyclerView) findViewById(R.id.recycle);
//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("message");
//
//        myRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                list = new ArrayList<Hewan>();
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//
//                    Hewan value = dataSnapshot1.getValue(Hewan.class);
//                    Hewan fire = new Hewan();
//                    String name = value.getName();
//                    String address = value.getUrl();
//                    String email = value.getInfo();
//                    fire.setName(name);
//                    fire.setInfo(email);
//                    fire.setUrl(address);
//                    list.add(fire);
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("Hello", "Failed to read value.", error.toException());
//            }
//        });
//
//        RecyclerAdapter adapter=new RecyclerAdapter(list, this);
//        //membuat adapter baru untuk reyclerview
//        recycle.setAdapter(adapter);
//        //menset nilai dari adapter
//        recycle.setHasFixedSize(true);
//        //menset setukuran
//        recycle.setLayoutManager(new LinearLayoutManager(this));
//
//    }
//}