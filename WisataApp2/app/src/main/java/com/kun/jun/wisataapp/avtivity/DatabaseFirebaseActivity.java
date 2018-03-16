package com.kun.jun.wisataapp.avtivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kun.jun.wisataapp.R;
import com.kun.jun.wisataapp.client.FirebaseClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DatabaseFirebaseActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView listview;
    final static String DB_URL ="https://bebas-121f0.firebaseio.com/";

    EditText nameEditext,urlEditext,infoEditext;
    Button btnsave,btncancel;

    FirebaseClient firebaseClient;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_firebase);
        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        ref = FirebaseDatabase.getInstance().getReference("hewan");

        firebaseClient = new FirebaseClient(this,DB_URL,listview,ref);
        firebaseClient.refreshdata();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent n = new Intent(DatabaseFirebaseActivity.this, PencetActivity.class);
//                startActivity(n);
                Toast.makeText(DatabaseFirebaseActivity.this, "Map OTW", Toast.LENGTH_SHORT).show();
              // isipencet();
                //bikin peta
                // displaydialog();
            }
        });
    }


    private void displaydialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("save data");
        dialog.setContentView(R.layout.customdialog_layout);
        nameEditext = (EditText)dialog.findViewById(R.id.nameEditText);
        urlEditext = (EditText)dialog.findViewById(R.id.urlEditText);
        infoEditext = (EditText)dialog.findViewById(R.id.infoEditText);
        btnsave = (Button)dialog.findViewById(R.id.saveBtn);
        btncancel= (Button)dialog.findViewById(R.id.cancelBtn);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseClient.savedata(nameEditext.getText().toString(),infoEditext.getText().toString(),urlEditext.getText().toString());
                nameEditext.setText("");
                infoEditext.setText("");
                urlEditext.setText("");
                dialog.dismiss();
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
