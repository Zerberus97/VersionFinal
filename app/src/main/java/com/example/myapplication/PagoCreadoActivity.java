package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.myapplication.model.EmpresaDBContract;

public class PagoCreadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagopdf);


    }
    public void volverInicioPDF(View view){

        Intent intent=new Intent(PagoCreadoActivity.this,MainActivity.class);

        SharedPreferences sesiones = getSharedPreferences(EmpresaDBContract.Sesion.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sesiones.edit();

        editor.putBoolean(EmpresaDBContract.Sesion.FIELD_SESSION, false);
        editor.putString(EmpresaDBContract.Sesion.FIELD_USERNAME, "");
        editor.commit();

        startActivity(intent);
    }

}
