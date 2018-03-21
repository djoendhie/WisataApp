package com.kun.jun.wisataapp.gabung.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kun.jun.wisataapp.R;
import com.kun.jun.wisataapp.gabung.RFire.MainMainActivity;
import com.kun.jun.wisataapp.gabung.RFire.RecyclerAdapter;
import com.kun.jun.wisataapp.model.Hewan;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<Hewan> list;
    RecyclerView recycle;
    private Context homeFragment;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_main_main, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycle = (RecyclerView)view.findViewById(R.id.recycle);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("hewan");

        ValueEventListener valueEventListener = myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list = new ArrayList<Hewan>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
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

                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,getContext());
//                RecyclerView.LayoutManager recyce = new GridLayoutManager(MainMainActivity.this,2);
                RecyclerView.LayoutManager recyce = new LinearLayoutManager(getContext());
                // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                recycle.setLayoutManager(recyce);
                recycle.setItemAnimator(new DefaultItemAnimator());
                recycle.setAdapter(recyclerAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Hello", "Failed to read value.", databaseError.toException());
            }
        });
    }
}