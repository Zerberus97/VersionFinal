package com.example.myapplication;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.controller.EmpresaController;
import com.example.myapplication.model.EmpresaDBContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {




    private EditText txtRut, txtPassword;




    protected void onCreate(Bundle savedInstanceState) {





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INICIAR SESION AUTOMATICAMENTE SI YA INICIÓ ANTERIORMENTE
        SharedPreferences sesion = getSharedPreferences(EmpresaDBContract.Sesion.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        boolean loggedIn = sesion.getBoolean(EmpresaDBContract.Sesion.FIELD_SESSION, false);
        if (loggedIn) {
            Intent i = new Intent(MainActivity.this, Formulario.class);
            startActivity(i);
            finish();
        }


        //pregunto por los permisos.
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

            }
        }





        EmpresaController controller = new EmpresaController(getApplicationContext());

        try {
            controller.eliminarRegistros();

            Log.d("BORRADO ", null);

        } catch (Exception e) {

            String mensaje = e.getMessage();
            Log.d("NO BORRADO", mensaje);

        }





    }


    public void formulario(View view){




        final String url = "http://cybertechnology.online/api/empresa/3";

        final RequestQueue queue = Volley.newRequestQueue(this);



        txtRut= findViewById(R.id.input_rut);
        txtPassword= findViewById(R.id.input_contraseña);

        final String rut = txtRut.getText().toString();
        final String password = txtPassword.getText().toString();



        if (rut.isEmpty()) {

            txtRut.setError("El Campo esta vacio");
            txtRut.requestFocus();

            return;
        }else if (password.isEmpty()){

            txtPassword.setError("El Campo esta vacio");
            txtPassword.requestFocus();

            return;

        }

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();



        if(!hayInternet()){
            progressDialog.dismiss();

            Toast.makeText(getApplicationContext(), "No hay conexión con el servidor", Toast.LENGTH_SHORT).show();
        }else if (hayInternet()){

            final JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {

                            String array = response.toString();
                            try {
                                JSONArray json = new JSONArray(array);
                                for (int i = 0; i < json.length(); i++) {

                                    JSONObject o = json.getJSONObject(i);

                                    String idEmpresa = o.getString("id");
                                    String rutA = o.getString("rut");
                                    String nombre = o.getString("nombre");
                                    String clave = o.getString("password");
                                    String pago = o.getString("pago");
                                    String correlativo = o.getString("correlativo");


                                    try {
                                        EmpresaController controller = new EmpresaController(getApplicationContext());
                                        controller.crearEmpresa(Integer.parseInt(idEmpresa), rutA, nombre, clave, pago, correlativo);
                                        Log.d("CREADO USUARIO", null);
                                    } catch (Exception e) {
                                        String mensaje = e.getMessage();
                                        Log.d("NO CREADO", mensaje);
                                    }


                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            String rut = txtRut.getText().toString();
                            String password = txtPassword.getText().toString();

                            EmpresaController controller = new EmpresaController(getApplicationContext());

                            if (controller.obtenerPAGOusuarioString(rut).equals("0")) {

                                controller.eliminarRegistros();

                                progressDialog.dismiss();

                                Toast.makeText(getApplicationContext(), "Debe realizar su pago mensual", Toast.LENGTH_LONG).show();

                            }else if (controller.usuarioLogin(rut, password)) {


                                Intent intent = new Intent(MainActivity.this, Formulario.class);



                                SharedPreferences sesion = getSharedPreferences(EmpresaDBContract.Sesion.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sesion.edit();

                                editor.putBoolean(EmpresaDBContract.Sesion.FIELD_SESSION, true);
                                editor.putString(EmpresaDBContract.Sesion.FIELD_USERNAME, rut);

                                editor.putString(EmpresaDBContract.Sesion.FIELD_ID, controller.obtenerIDusuario(rut));

                                editor.putString(EmpresaDBContract.Sesion.FIELD_PAGO, controller.obtenerPAGOusuarioString(rut));
                                editor.putString(EmpresaDBContract.Sesion.FIELD_CORRELATIVO, controller.obtenerCORRELATIVOusuario(rut));
                                editor.putString(EmpresaDBContract.Sesion.FIELD_RUT, rut);

                                editor.apply();

                                finish();

                                progressDialog.dismiss();
                                startActivity(intent);

                            } else {

                                controller.eliminarRegistros();
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
                            }

                            Log.d("Response", response.toString());

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error.Response", error.toString());

                        }
                    }
            );
            queue.add(getRequest);
        }






    }


    private boolean hayInternet(){
        boolean hayWIFI=false;
        boolean hayDATOS=false;

        ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos=connectivityManager.getAllNetworkInfo();

        for (NetworkInfo info:networkInfos)
        {
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected())
                hayWIFI=true;
            if (info.getTypeName().equalsIgnoreCase("MOBILE"))
                if (info.isConnected())
                hayDATOS=true;
        }
        return hayDATOS || hayWIFI;
    }


    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1 ;


}


