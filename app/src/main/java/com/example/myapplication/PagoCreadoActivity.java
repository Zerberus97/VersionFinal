package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.model.EmpresaDBContract;

public class PagoCreadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagopdf);


    }

    @Override
    public void onBackPressed() {

        Toast.makeText(getApplicationContext(), "Vuelva al inicio y page su suscripción", Toast.LENGTH_SHORT).show();
    }




    public void volverInicioPDF(View view){

        Intent intent=new Intent(PagoCreadoActivity.this,MainActivity.class);


        startActivity(intent);
    }



}
