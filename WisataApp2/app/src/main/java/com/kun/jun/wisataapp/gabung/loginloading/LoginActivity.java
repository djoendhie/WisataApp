package com.kun.jun.wisataapp.gabung.loginloading;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kun.jun.wisataapp.MainActivity;
import com.kun.jun.wisataapp.R;
import com.kun.jun.wisataapp.gabung.ntah.SessionManager;

public class LoginActivity extends SessionManager {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button =  findViewById(R.id.btnSubmit);
        final EditText editText = findViewById(R.id.edtInput);
        final preference preference = new preference(LoginActivity.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = editText.getText().toString();
                if (TextUtils.isEmpty(nama)) {
                    Toast.makeText(LoginActivity.this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();

                }else {
                    preference.setNama(nama);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    sessionManager.setIdUser(nama);
                    finish();

                }
            }
        });

    }
    public static class  preference {

        String nama;
        String KEY_NAME = "NAMA";
        String PREF_NAME = "SIMPAN";
        SharedPreferences preferences;
        SharedPreferences.Editor editor;

        Context context;
        public preference(Context context) {


            preferences =context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }

        public String getNama() {
            return preferences.getString(KEY_NAME, null);
        }

        public void setNama(String nama) {
            this.nama = nama;
            editor = preferences.edit();
            editor.putString("NAMA", nama);
            editor.apply();

        }
        public  void logout(){
            editor = preferences.edit();
            editor.clear();
            editor.commit();
        }

    }

}
