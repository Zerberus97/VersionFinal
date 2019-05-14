package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.model.EmpresaDBContract;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.myapplication.FirmaActivity.firmaPNG;
import static com.example.myapplication.FirmaActivity2.firmaPNG2;

//import android.media.Image;


public class Formulario extends AppCompatActivity {


    //Variables

    private EditText txtNombreEmpresa, txtDireccionEmpresa, txtRBDEmpresa, txtObservaciones;
    private Button btnGuardar, btnEnviar;
    private RadioGroup RGroup;
    private RadioButton RadioServicio, RadioControl;
    private CheckBox CheckRBpe, CheckRBpi, CheckRBb, CheckRbext, CheckRbint, CheckRbbo, CheckRbsha, CheckRbshc, CheckRbcamF, CheckRbbroma, CheckRbtrampa, CheckRbnotoxico, CheckRbcipermetrina, CheckRbdelta, CheckRbaguatrin, CheckRbagita, CheckRbsanicitrex;
    private CheckBox CBdeslindes, CBpec, CBbmp, CBbpt, CBo, CBe, CBc, CBa, CBsdm, CBcya, CBlya, CBpp, CBg; //Nuevos del primer tier
    private CheckBox CBdp, CBpe2, CBbmp2, CBbpt2, CBsh, CBc2, CBad, CBg2, CBo2, CBsdm2, CBcya2, CBpp2;
    private CheckBox CBcgp; //microorganismos
    private CheckBox CBtb, CBdb, CBdc,CBrb ,CBrp,CBdm,CBdb2, CB50p1, CB50p2, CBta, CBcnt;
    private CheckBox CBanasec, CBdemon, CBko, CBcyper, CBcyperP, CBquimico10, CBquimico20, CBtrampa;  //desinsectacion
    private CheckBox CB05agua, CB01agua; //Dosis y tipo de producto utilizados

    private String TipoServicio = "";
    private String Cell = "CEL: 9 8370 1407 – 2 2966 3828";
    private String web = "www.antimouse.cl";
    private String[]header={"Control de Roedores","Control de Insectos","Control de Microorganismos"};
    private String[]header2={"Desratizacion","Desinsectacion","Sanitizacion"};


    //VARIABLES QUE RECIBEN DE FIRMA PARA MANTENER FORMULARIO
    private String nombreVuelta, direccionVuelta, RBDVuelta, observacionesVuelta;
    //RADIO BUTTON
    private String radioVuelta;
    //CHECKBOX CONTROL DE RODEDORES
    private String perimetroExtVuelta,perimetroIntVuelta,bodegasVuelta, deslindesV, perimetroExternoCercanoV, bodegasMateriasPrimasV, bodegasProductosTerminadosV, oficinaV, entretechosV, cerchasV, alcantarilladosV, salaDeMaquinasV, casinosYAnexosV, produccionProcesosV, laboratoriosYAnexosV, guardiaV;
    //CHECKBOX CONTROL DE INSECTOS
    private String exteriorVuelta,interiorVuelta,bodegas2Vuelta, CBdpV, CBpe2V, CBbmp2V, CBbpt2V, CBshV, CBc2V, CBadV, CBg2V, CBo2V, CBsdm2V, CBcya2V, CBpp2V;
    //CHECKBOX CONTROL DE MICROORGANISMOS
    private String admVuelta,camarinesVuelta,camFVuelta, casinoGerenciaV;
    //CHECKBOX DESRATIZACION
    private String bromaVuelta,trampaVuelta,toxVuelta,CBtbV, CBdbV, CBdcV, CBrbV , CBrpV , CBdmV , CBdb2V, CB50p1V, CB50p2V, CBtaV, CBcntV;
    //CHECKBOX DESINSECTACION
    private String cipeVuelta,deltaVuelta,aquaVuelta,agitaVuelta, anasecV, demonV, koV, cyperkillV, cyperkillPV, quimico10V, quimico20V, trampaV;
    //CKECKBOX SANITIZACION
    private String saniVuelta, usoQuimico05V, usoQuimico01V;




    String correlativo;

    //----
    String perimetroExt = " ";
    String perimetroInt = " ";
    String bodegas = "  ";
    String deslindes = "   ";
    String PerimetroExternoCercano = "  ";
    String bodegasMateriasPrimas = "    ";
    String bodegasProductosTerminados = " ";
    String oficinas = " ";
    String entretechos = "  ";
    String cerchas = "  ";
    String alcantarillados = "  ";
    String salasDeMquinas = "   ";
    String casinosYAnexos = "   ";
    String laboratoriosYAnexos = "  ";
    String produccionProcesos = "   ";
    String guardia = "  ";

    //----
    String Exterior = " ";
    String Interior = " ";
    String bodegas2 = "  ";
    String deslindesP = "   ";
    String perimetroExterno = "   ";
    String bodegasMateriasPrimas2 = "   ";
    String bodegasProductosTerminados2 = "   ";
    String serviciosHigienicos = "   ";
    String camarines = "   ";
    String alcantarillados2 = "   ";
    String guardia2 = "   ";
    String oficinas2 = "   ";
    String salasDeMquinas2 = "   ";
    String casinosYAnexos2 = "   ";
    String produccionProcesos2 = "      ";
    //----
    String ServHig = "  ";
    String ServHigCamarines = " ";
    String CamaraFrio = "   ";
    String CasinoGerenciaPersonal = "   ";
    //----
    String Bromadiolona = " ";
    String Trampacapturaviva = "    ";
    String Notoxicas = "    ";
    String termixanbloque = "    ";
    String detiabloque = "    ";
    String detiacebo = "    ";
    String rastopbloque = "    ";
    String rastoppellet = "    ";
    String deablinemolienda = "    ";
    String deablinebloque = "    ";
    String dosis50p = "    ";
    String dosis50p2 = "    ";
    String Trampaadhesivas = "    ";
    String cebonotoxico = "    ";
    //----
    String Cipermetrina = "    ";
    String Deltametrina = "    ";
    String Aquatrin = "    ";
    String Agita = "    ";
    String Anasec = "    ";
    String Demon = "    ";
    String KO = "    ";
    String Cyperkill = "    ";
    String CyperkillP = "    ";
    String dosisUso10 = "    ";
    String dosisUso20 = "    ";
    String TrampaA = "    ";



    //----
    String Sanicitrex = "   ";
    String dosisUso05agua = "   ";
    String dosisUso01agua = "   ";

    String EspacioBlanco = "   ";

    String pago;

    String firmado="";

    String usoQuimico = "DOSIS USO QUIMICO";
    String noQuimico = "NO QUIMICO";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal_salir, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_salir:

                final AlertDialog.Builder alerta=new AlertDialog.Builder(this);
                alerta.setMessage("Realmente desea cerrar sesion?");
                alerta.setTitle("Cerrar sesion");
                alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences sesiones = getSharedPreferences(EmpresaDBContract.Sesion.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sesiones.edit();

                        editor.putBoolean(EmpresaDBContract.Sesion.FIELD_SESSION, false);
                        editor.putString(EmpresaDBContract.Sesion.FIELD_USERNAME, "");
                        editor.commit();

                        Intent i = new Intent(Formulario.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
                alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog=alerta.create();
                dialog.show();




                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);




        final String url1 = "http://cybertechnology.online/api/empresa/3";

        final RequestQueue queue1 = Volley.newRequestQueue(this);


        final JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url1, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        String array = response.toString();
                        try {
                            JSONArray json = new JSONArray(array);
                            for (int i = 0; i < json.length(); i++) {

                                JSONObject o = json.getJSONObject(i);

                                String correlativo1 = o.getString("correlativo");

                                Integer  correlativo2 = Integer.valueOf(correlativo1.replaceFirst("0000", ""));

                                correlativo2++;

                                correlativo = String.format("%05d", correlativo2);  // 0009

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
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
        queue1.add(getRequest);

        /*
        *   CAMPOS!
        * */
        txtNombreEmpresa = findViewById(R.id.EditTextNombreEmpresa);
        txtDireccionEmpresa = findViewById(R.id.EditTextDireccionEmpresa);
        txtRBDEmpresa=findViewById(R.id.EditTextRBD);
        btnGuardar =findViewById(R.id.button_save);
        btnEnviar =findViewById(R.id.button_send);
        RGroup = findViewById(R.id.RGroup);
        RadioServicio = findViewById(R.id.RServicio);
        RadioControl =  findViewById(R.id.RControl);
        //Checkboxs
        //---Primer----
        CheckRBpe =findViewById(R.id.RBpe);
        CheckRBpi =findViewById(R.id.RBpi);
        CheckRBb =  findViewById(R.id.RBb);
        CBdeslindes = findViewById(R.id.CBd);
        CBpec = findViewById(R.id.Cbpec);
        CBbmp = findViewById(R.id.CBbmp);
        CBbpt = findViewById(R.id.CBbpt);
        CBo = findViewById(R.id.CBo);
        CBe = findViewById(R.id.CBe);
        CBc = findViewById(R.id.CBc);
        CBa = findViewById(R.id.CBa);
        CBsdm = findViewById(R.id.CBsdm);
        CBcya = findViewById(R.id.CBcya);
        CBlya = findViewById(R.id.CBlya);
        CBpp = findViewById(R.id.CBpp);
        CBg  = findViewById(R.id.CBg);
        //----Segundo----
        CheckRbext = findViewById(R.id.RBext);
        CheckRbint =  findViewById(R.id.RBint);
        CheckRbbo = findViewById(R.id.RBbo);
        CBdp = findViewById(R.id.CBdp);
        CBpe2 = findViewById(R.id.CBpe2);
        CBbmp2 = findViewById(R.id.CBbmp2);
        CBbpt2 = findViewById(R.id.CBbpt2);
        CBsh = findViewById(R.id.CBsh);
        CBc2 = findViewById(R.id.CBc2);
        CBad = findViewById(R.id.CBad);
        CBg2 = findViewById(R.id.CBg2);
        CBo2 = findViewById(R.id.CBo2);
        CBsdm2 = findViewById(R.id.CBsdm2);
        CBcya2 = findViewById(R.id.CBcya2);
        CBpp2 = findViewById(R.id.CBpp2);
        //----tercero-----
        CheckRbsha =  findViewById(R.id.RBsha);
        CheckRbshc =  findViewById(R.id.RBshc);
        CheckRbcamF =  findViewById(R.id.RBcamF);
        CBcgp = findViewById(R.id.CBcgp);
        //-----Cuarto-----
        CheckRbbroma =  findViewById(R.id.RBbroma);
        CheckRbtrampa =  findViewById(R.id.RBtrampa);
        CheckRbnotoxico =  findViewById(R.id.RBnotoxico);
        CBtb = findViewById(R.id.CBtb);
        CBdb = findViewById(R.id.CBdb);
        CBdc = findViewById(R.id.CBdc);
        CBrb = findViewById(R.id.CBrb);
        CBrp = findViewById(R.id.CBrp);
        CBdm = findViewById(R.id.CBdm);
        CBdb2 = findViewById(R.id.CBdb2);
        CB50p1 = findViewById(R.id.CB50p1);
        CB50p2 = findViewById(R.id.CB50p2);
        CBta = findViewById(R.id.CBta);
        CBcnt = findViewById(R.id.CBcnt);
        //---------Quinto---------
        CheckRbcipermetrina = findViewById(R.id.RBCipermetrina);
        CheckRbdelta =  findViewById(R.id.RBDeltametrina);
        CheckRbaguatrin = findViewById(R.id.RBAquatrin);
        CheckRbagita =  findViewById(R.id.RBAgita);
        CBanasec =  findViewById(R.id.CBanasec);
        CBdemon =  findViewById(R.id.CBdemon);
        CBko =  findViewById(R.id.CBko);
        CBcyper =  findViewById(R.id.CBcyper);
        CBcyperP =  findViewById(R.id.CBcyperP);
        CBquimico10 =  findViewById(R.id.CBquimico10);
        CBquimico20 =  findViewById(R.id.CBquimico20);
        CBtrampa =  findViewById(R.id.CBtrampa);

        //-----sexto-----
        CheckRbsanicitrex = findViewById(R.id.RBSanicitrex);
        CB05agua = findViewById(R.id.CB05agua);
        CB01agua = findViewById(R.id.CB01agua);

        txtObservaciones =  findViewById(R.id.EditTextObservacion);


        Bundle extras = this.getIntent().getExtras();

        if (extras!=null){

            nombreVuelta=extras.getString("KEY_NOMBRE");
            direccionVuelta=extras.getString("KEY_DIRECCION");
            RBDVuelta=extras.getString("KEY_RBD");
            observacionesVuelta=extras.getString("KEY_OBSERVACIONES");

            radioVuelta=extras.getString("KEY_RADIO");

            perimetroExtVuelta=extras.getString("KEY_PERIMETROEX");
            perimetroIntVuelta=extras.getString("KEY_PERIMETROIN");
            bodegasVuelta=extras.getString("KEY_BODEGAS");
            deslindesV=extras.getString("KEY_DESLINDES");
            perimetroExternoCercanoV=extras.getString("KEY_PERIMETROEXTC");
            bodegasMateriasPrimasV=extras.getString("KEY_BODEGASMATERIASP");
            bodegasProductosTerminadosV=extras.getString("KEY_BODEGASPRODUCT");
            oficinaV=extras.getString("KEY_OFICINAS");
            entretechosV=extras.getString("KEY_ENTRETECHOS");
            cerchasV=extras.getString("KEY_CERCHAS");
            alcantarilladosV=extras.getString("KEY_ALCANTARILLADOS");
            salaDeMaquinasV=extras.getString("KEY_SALADEMAQUINAS");
            casinosYAnexosV=extras.getString("KEY_CASINOSYANEXOS");
            laboratoriosYAnexosV=extras.getString("KEY_LABORATORIOS");
            produccionProcesosV=extras.getString("KEY_PRODUCCIONPROCESOS");
            guardiaV=extras.getString("KEY_GUARDIA");

            exteriorVuelta=extras.getString("KEY_EXTERIOR");
            interiorVuelta=extras.getString("KEY_INTERIOR");
            bodegas2Vuelta=extras.getString("KEY_BODEGAS2");
            CBdpV=extras.getString("KEY_DESLINDESPATIO"); //de aqui para abajo
            CBpe2V=extras.getString("KEY_PERIMETROEXTERNO");
            CBbmp2V=extras.getString("KEY_BODEGAMATERIASPRIMAS");
            CBbpt2V=extras.getString("KEY_BODEGAPRODUCTOTERMINADO");
            CBshV=extras.getString("KEY_SERVICIOSHIGIENICOS");
            CBc2V=extras.getString("KEY_CAMARINES");
            CBadV=extras.getString("KEY_ALCANTARILLADOS2");
            CBg2V=extras.getString("KEY_GUARDIA2");
            CBo2V=extras.getString("KEY_OFICINAS2");
            CBsdm2V=extras.getString("KEY_SALADEMAQUINAS2");
            CBcya2V=extras.getString("KEY_CASINOSYANEXOS2");
            CBpp2V=extras.getString("KEY_PRODUCCIONPROCESOS2");


            admVuelta=extras.getString("KEY_ADM");
            camarinesVuelta=extras.getString("KEY_CAMARINES");
            camFVuelta=extras.getString("KEY_CAMF");
            casinoGerenciaV=extras.getString("KEY_CASINOG");


            bromaVuelta=extras.getString("KEY_BROMA");
            trampaVuelta=extras.getString("KEY_TRAMPA");
            toxVuelta=extras.getString("KEY_TOX");
            CBtbV=extras.getString("KEY_TERMIXANBLOQUE");// AQUI
            CBdbV=extras.getString("KEY_DETIABLOQUE");
            CBdcV =extras.getString("KEY_DETIACEBO");
            CBrbV=extras.getString("KEY_RASTOPBLOQUE");
            CBrpV=extras.getString("KEY_RASTOPPELLET");
            CBdmV=extras.getString("KEY_DEABLINEMOLIENDA");
            CBdb2V=extras.getString("KEY_DEABLINEBLOQUE");
            CB50p1V=extras.getString("KEY_QUIMICO50P1");
            CB50p2V=extras.getString("KEY_QUIMICO50P2");
            CBtaV=extras.getString("KEY_TRAMPAADHESIVAS");
            CBcntV=extras.getString("KEY_CEBONOTOXICO");


            cipeVuelta=extras.getString("KEY_CIPE");
            deltaVuelta=extras.getString("KEY_DELTA");
            aquaVuelta=extras.getString("KEY_AQUA");
            agitaVuelta=extras.getString("KEY_AGITA");
            anasecV=extras.getString("KEY_ANASEC");
            demonV=extras.getString("KEY_DEMON");
            koV=extras.getString("KEY_KO");
            cyperkillV=extras.getString("KEY_CYPERKILL");
            cyperkillPV=extras.getString("KEY_CYPERKILLP");
            quimico10V=extras.getString("KEY_QUIMICO10");
            quimico20V=extras.getString("KEY_QUIMICO20");
            trampaV=extras.getString("KEY_TRAMPA2");

            saniVuelta=extras.getString("KEY_SANI");
            usoQuimico05V=extras.getString("KEY_QUIMICO05");
            usoQuimico01V=extras.getString("KEY_QUIMICO01");

            correlativo=extras.getString("KEY_CORRELATIVO");

            firmado=extras.getString("KEY_FIRMADO");

            txtNombreEmpresa.setText(nombreVuelta);
            txtDireccionEmpresa.setText(direccionVuelta);
            txtRBDEmpresa.setText(RBDVuelta);
            txtObservaciones.setText(observacionesVuelta);

            if (firmado.equals("firmado")){
                btnGuardar.setFocusable(true);
                btnGuardar.setFocusableInTouchMode(true);
                btnGuardar.requestFocus();
            }

            if (radioVuelta.equals("radio1")){ RadioServicio.setChecked(true); }
            if (radioVuelta.equals("radio2")){ RadioControl.setChecked(true); }

            if (perimetroExtVuelta.equals("checked")){CheckRBpe.setChecked(true); }
            if (perimetroIntVuelta.equals("checked")){CheckRBpi.setChecked(true); }
            if (bodegasVuelta.equals("checked")){CheckRBb.setChecked(true); }
            if (deslindesV.equals("checked")){CBdeslindes.setChecked(true);}
            if (perimetroExternoCercanoV.equals("checked")){CBpec.setChecked(true);}
            if (bodegasMateriasPrimasV.equals("checked")){CBbmp.setChecked(true);}
            if (bodegasProductosTerminadosV.equals("checked")){CBbpt.setChecked(true);}
            if (oficinaV.equals("checked")){CBo.setChecked(true);}
            if (entretechosV.equals("checked")){CBe.setChecked(true);}
            if (cerchasV.equals("checked")){CBc.setChecked(true);}
            if (alcantarilladosV.equals("checked")){CBa.setChecked(true);}
            if (salaDeMaquinasV.equals("checked")){CBsdm.setChecked(true);}
            if (casinosYAnexosV.equals("checked")){CBcya.setChecked(true);}
            if (laboratoriosYAnexosV.equals("checked")){CBlya.setChecked(true);}
            if (produccionProcesosV.equals("checked")){CBpp.setChecked(true);}
            if (guardiaV.equals("checked")){CBg.setChecked(true);}

            if (exteriorVuelta.equals("checked")){CheckRbext.setChecked(true); }
            if (interiorVuelta.equals("checked")){CheckRbint.setChecked(true); }
            if (bodegas2Vuelta.equals("checked")){CheckRbbo.setChecked(true); }
            if (CBdpV.equals("checked")){CBdp.setChecked(true); }
            if (CBpe2V.equals("checked")){CBpe2.setChecked(true); }
            if (CBbmp2V.equals("checked")){CBbmp2.setChecked(true); }
            if (CBbpt2V.equals("checked")){CBbpt2.setChecked(true); }
            if (CBshV.equals("checked")){CBsh.setChecked(true); }
            if (CBc2V.equals("checked")){CBc2.setChecked(true); }
            if (CBadV.equals("checked")){CBad.setChecked(true); }
            if (CBg2V.equals("checked")){CBg2.setChecked(true); }
            if (CBo2V.equals("checked")){CBo2.setChecked(true); }
            if (CBsdm2V.equals("checked")){CBsdm2.setChecked(true); }
            if (CBcya2V.equals("checked")){CBcya2.setChecked(true); }
            if (CBpp2V.equals("checked")){CBpp2.setChecked(true); }


            if (admVuelta.equals("checked")){CheckRbsha.setChecked(true); }
            if (camarinesVuelta.equals("checked")){CheckRbshc.setChecked(true); }
            if (camFVuelta.equals("checked")){CheckRbcamF.setChecked(true); }
            if (casinoGerenciaV.equals("checked")){CBcgp.setChecked(true); }

            if (bromaVuelta.equals("checked")){CheckRbbroma.setChecked(true); }
            if (trampaVuelta.equals("checked")){CheckRbtrampa.setChecked(true); }
            if (toxVuelta.equals("checked")){CheckRbnotoxico.setChecked(true); }
            //aqui
            if (CBtbV.equals("checked")){CBtb.setChecked(true); }
            if (CBdbV.equals("checked")){CBdb.setChecked(true); }
            if (CBdcV.equals("checked")){CBdc.setChecked(true); }
            if (CBrbV.equals("checked")){CBrb.setChecked(true); }
            if (CBrpV.equals("checked")){CBrp.setChecked(true); }
            if (CBdmV.equals("checked")){CBdm.setChecked(true); }
            if (CBdb2V.equals("checked")){CBdb2.setChecked(true); }
            if (CB50p1V.equals("checked")){CB50p1.setChecked(true); }
            if (CB50p2V.equals("checked")){CB50p2.setChecked(true); }
            if (CBtaV.equals("checked")){CBta.setChecked(true); }
            if (CBcntV.equals("checked")){CBcnt.setChecked(true); }

            if (cipeVuelta.equals("checked")){CheckRbcipermetrina.setChecked(true); }
            if (deltaVuelta.equals("checked")){CheckRbdelta.setChecked(true); }
            if (aquaVuelta.equals("checked")){CheckRbaguatrin.setChecked(true); }
            if (agitaVuelta.equals("checked")){CheckRbagita.setChecked(true); }
            if (anasecV.equals("checked")){CBanasec.setChecked(true); }
            if (demonV.equals("checked")){CBdemon.setChecked(true); }
            if (koV.equals("checked")){CBko.setChecked(true); }
            if (cyperkillV.equals("checked")){CBcyper.setChecked(true); }
            if (cyperkillPV.equals("checked")){CBcyperP.setChecked(true); }
            if (quimico10V.equals("checked")){CBquimico10.setChecked(true); }
            if (quimico20V.equals("checked")){CBquimico20.setChecked(true); }
            if (trampaV.equals("checked")){CBtrampa.setChecked(true); }

            if (saniVuelta.equals("checked")){CheckRbsanicitrex.setChecked(true); }
            if (usoQuimico05V.equals("checked")){CB05agua.setChecked(true); }
            if (usoQuimico01V.equals("checked")){CB01agua.setChecked(true); }

        }
    }//cerrar onCreate



    public void pdfApp(View view) throws IOException, DocumentException {

        //----------------Comprobacion---------------------

        //Comprobacion de campos


        if (txtNombreEmpresa.getText().toString().isEmpty()){
            txtNombreEmpresa.setError("El Campo esta vacio");
            txtNombreEmpresa.requestFocus();
            return;
        }
        if (txtDireccionEmpresa.getText().toString().isEmpty()){
            txtDireccionEmpresa.setError("El Campo esta vacio");
            txtDireccionEmpresa.requestFocus();
            return;
        }

        //Comprobacion de los RadioButton
        if (RadioControl.isChecked() == true) {
            TipoServicio = "Control";
        } else if (RadioServicio.isChecked() == true) {
            TipoServicio = "Servicio";
        } if (RGroup.getCheckedRadioButtonId() == -1){

            Toast.makeText(this ,"Debe marcar un tipo de servicio",Toast.LENGTH_LONG).show();

            RGroup.requestFocus();
            return;
        }

        //----------------------------------------Comprobacion Checkbox-----------------------------------------------------
        //Control de Roedores
        if (CheckRBpe.isChecked()){
            perimetroExt = "Perimetro Exterior";
        }
        if (CheckRBpi.isChecked()){
            perimetroInt = "Perimetro Interior";
        }
        if(CheckRBb.isChecked()){
            bodegas = "Bodegas";
        }
        if (CBdeslindes.isChecked()){
            deslindes = "Deslindes";
        }
        if(CBpec.isChecked()){
            PerimetroExternoCercano = "Perimetro Externo Cercano";
        }
        if(CBbmp.isChecked()){
            bodegasMateriasPrimas = "Bod. Mat. Primas";
        }
        if (CBbmp.isChecked()){
            bodegasProductosTerminados = "Bod. Prod. Terminados";
        }
        if (CBo.isChecked()){
            oficinas = "Oficinas";
        }
        if (CBe.isChecked()){
            entretechos = "Entretechos";
        }
        if (CBc.isChecked()){
            cerchas = "Cerchas";
        }
        if (CBa.isChecked()){
            alcantarillados = "Alcantarillados";
        }
        if (CBsdm.isChecked()){
            salasDeMquinas = "Salas de Maquinas";
        }
        if (CBcya.isChecked()){
            casinosYAnexos = "Casinos y Anexos";
        }
        if (CBlya.isChecked()){
            laboratoriosYAnexos = "Laboratorios y Anexos";
        }
        if (CBpp.isChecked()) {
            produccionProcesos = "Produccion / Procesos";
        }
        if (CBg.isChecked()){
            guardia = "Guardia";
        }


        //      ---------------         Control insectos           -------------
        if (CheckRbext.isChecked()){
            Exterior = "Exterior";
        }
        if (CheckRbint.isChecked()){
            Interior = "Interior";
        }
        if(CheckRbbo.isChecked()){
            bodegas2 = "Bodegas";
        }
        if(CBdp.isChecked()){
            deslindesP = "Deslindes patios";
        }
        if(CBpe2.isChecked()){
            perimetroExterno = "Perímetro externo";
        }
        if(CBbmp2.isChecked()){
            bodegasMateriasPrimas2 = "Bod.materias primas";
        }
        if(CBbpt2.isChecked()){
            bodegasProductosTerminados2 = "Bod.prod.terminados";
        }
        if(CBsh.isChecked()){
            serviciosHigienicos = "Servicios Higiénicos";
        }
        if(CBc2.isChecked()){
            camarines = "Camarines";
        }
        if(CBad.isChecked()){
            alcantarillados2 = "Alcantarillados/Desagues";
        }
        if(CBg2.isChecked()){
            guardia2 = "Guardia";
        }
        if(CBo2.isChecked()){
            oficinas2 = "Oficinas";
        }
        if(CBsdm2.isChecked()){
            salasDeMquinas2 = "Sala de máquinas";
        }
        if(CBcya2.isChecked()){
            casinosYAnexos2 = "Casinos y anexos";
        }
        if(CBpp2.isChecked()){
            produccionProcesos2 = "Producción/Procesos";
        }

        //---- Control de Microorganismos
        if (CheckRbsha.isChecked()){
            ServHig = "Ser. Hig. Adm.";
        }
        if (CheckRbshc.isChecked()){
            ServHigCamarines = "Ser. Hig. Camarines y personal";
        }
        if(CheckRbcamF.isChecked()){
            CamaraFrio = "Camara de Frio";
        }
        if(CBcgp.isChecked()){
            CasinoGerenciaPersonal = "Casino Gerencia / Personal";
        }


        //---- Desratizacion
        if (CheckRbbroma.isChecked()){
            Bromadiolona = "Bromadiolona";
        }
        if (CheckRbtrampa.isChecked()){
            Trampacapturaviva = "Trampa captura viva";
        }
        if(CheckRbnotoxico.isChecked()){
            Notoxicas = "No toxico";
        }
        if(CBtb.isChecked()){
            termixanbloque = "Termixan bloque";
        }
        if(CBdb.isChecked()){
            detiabloque = "Detia Bloque(Bromadiolona)";
        }
        if(CBdc.isChecked()){
            detiacebo = "Detia Cebo(Bromadiolona)";
        }
        if(CBrb.isChecked()){
            rastopbloque = "Rastop Bloque(Bromadiolona)";
        }
        if(CBrp.isChecked()){
            rastoppellet = "Rastop Pellet(Bromadiolona)";
        }
        if(CBdm.isChecked()){
            deablinemolienda = "Deabline Molienda";
        }
        if(CBdb2.isChecked()){
            deablinebloque = "Deabline Bloque";
        }
        if(CB50p1.isChecked()){
            dosis50p = "50ppm/10 a 20gr x cebo";
        }
        if(CB50p2.isChecked()){
            dosis50p2 = "50ppm/10 a 20ml x cebo";
        }
        if(CBta.isChecked()){
            Trampaadhesivas = "Trampas Adhesivas";
        }
        if(CBcnt.isChecked()){
            cebonotoxico = "Cebo no tóxico";
        }

        //---- Desinsectacion
        if (CheckRbcipermetrina.isChecked()){
            Cipermetrina = "Cipermetrina 25%";
        }
        if (CheckRbdelta.isChecked()){
            Deltametrina = "Deltametrina";
        }
        if(CheckRbaguatrin.isChecked()){
            Aquatrin = "Aquatrin";
        }
        if(CheckRbagita.isChecked()){
            Agita = "Agita";
        }
        if(CBanasec.isChecked()){
            Anasec = "Anasec (Praletrina 20%)";
        }
        if(CBdemon.isChecked()){
            Demon = "Demon (Cipermetria 25ec)";
        }
        if(CBko.isChecked()){
            KO = "K - Othrine 2.5 m e";
        }
        if(CBcyper.isChecked()){
            Cyperkill = "Cyperkill (Cipermetrina 25%)";
        }
        if(CBcyperP.isChecked()){
            CyperkillP = "Cyperkill Plus";
        }
        if(CBquimico10.isChecked()){
            dosisUso10 = "10% - 100cc / 10lts. agua";
        }
        if(CBquimico20.isChecked()){
            dosisUso20 = "20% - 20gr / 100cc agua";
        }
        if(CBtrampa.isChecked()){
            TrampaA = "Trampas Adhesivas";
        }
        //----              Sanitizacion
        if(CheckRbsanicitrex.isChecked()){
            Sanicitrex = "Sanicitrex D19/C8";
        }
        if(CB05agua.isChecked()){
            dosisUso05agua = "0,5% - 50cc/ 10lts. agua";
        }
        if(CB01agua.isChecked()){
            dosisUso01agua = "0,1% - 10cc/ 10lts. agua";
        }

        //------ Observaciones --------


        //------------------------Fin de comprobacion----------------------------

        final String url1 = "http://cybertechnology.online/api/empresa/3";

        final RequestQueue queue1 = Volley.newRequestQueue(this);


        final JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url1, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        String array = response.toString();
                        try {
                            JSONArray json = new JSONArray(array);
                            for (int i = 0; i < json.length(); i++) {

                                JSONObject o = json.getJSONObject(i);

                                String correlativo1 = o.getString("correlativo");

                                Integer  correlativo2 = Integer.valueOf(correlativo1.replaceFirst("0000", ""));

                                correlativo2++;

                                correlativo = String.format("%05d", correlativo2);  // 0009

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
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
        queue1.add(getRequest);


        final RequestQueue queue = Volley.newRequestQueue(this);


        final String url = "http://cybertechnology.online/api/empresau/3";
        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {


                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("correlativo", correlativo);


                return params;
            }

        };

        queue.add(putRequest);

        if (FirmaActivity.firmaPNG==null | FirmaActivity2.firmaPNG2==null){






            final AlertDialog.Builder alerta=new AlertDialog.Builder(this);
            alerta.setMessage("Crear documento sin firma?");
            alerta.setTitle("Guardar documento");
            alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {



                    comprobarPagoPDF();

                    //Capturando Fecha y Hora
                    Date date = new Date() ;
                    String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
                    final TemplatePDF templatePDF = new TemplatePDF(getApplicationContext());




                    //Añadir imagen
                    AssetManager mngr = getAssets();

                    InputStream is = null;
                    try {
                        is = mngr.open("antimouse.png");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Bitmap bmp = BitmapFactory.decodeStream(is);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);

                    Image image = null;
                    try {
                        image = Image.getInstance(stream.toByteArray());
                    } catch (BadElementException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    templatePDF.crearPDF(txtNombreEmpresa.getText().toString(),txtDireccionEmpresa.getText().toString());

                    templatePDF.abrirDocumento();
                    templatePDF.addMetaData("OrdenDeTrabajo","OrdenDeTrabajo","Antimouse");

                    templatePDF.addImage(image);






                    templatePDF.addTitles("ORDEN DE TRABAJO","N° " + correlativo);
                    templatePDF.Servicio(TipoServicio);
                    templatePDF.addParagraph("Nombre empresa: " + txtNombreEmpresa.getText().toString());
                    templatePDF.addParagraph("Direccion empresa: " + txtDireccionEmpresa.getText().toString());



                    templatePDF.addParagraph("Fecha: "+ timeStamp);
                    templatePDF.addParagraph("RBD: "+ txtRBDEmpresa.getText().toString());


                    templatePDF.createTable(header,getFila1());
                    templatePDF.createTable(header2,getFila2());

                    templatePDF.addParagraph("\n");
                    templatePDF.addParagraph("Observaciones");
                    templatePDF.addParagraph(txtObservaciones.getText().toString());
                    templatePDF.closeDocument();




                    //Ver PDF
                    if (TemplatePDF.archivoPDF.exists()) {
                        Uri uri = FileProvider.getUriForFile(Formulario.this,BuildConfig.APPLICATION_ID+".provider",TemplatePDF.archivoPDF);
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.setDataAndType(uri, "application/pdf");
                        try {


                            Limpiar();
                            startActivity(intent);


                            BorrarFirma();
                            firmaPNG=null;
                            btnGuardar.setFocusable(false);
                            btnGuardar.setFocusableInTouchMode(false);

                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.adobe.reader")));
                            Toast.makeText(getApplicationContext(), "No cuentas con una aplicacion para visualizar pdf", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "No se encontro el archivo", Toast.LENGTH_LONG).show();
                    }


                }
            });
            alerta.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog=alerta.create();
            dialog.show();

            //Capturando Fecha y Hora




        }else{




            comprobarPagoPDF();

            Date date = new Date() ;
            String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
            final TemplatePDF templatePDF = new TemplatePDF(getApplicationContext());


            //Añadir imagen LOGO
            AssetManager mngr = getAssets();
            InputStream is = mngr.open("antimouse.png");
            Bitmap bmp = BitmapFactory.decodeStream(is);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image = Image.getInstance(stream.toByteArray());

            /*
            AssetManager mngr1 = getAssets();
            InputStream is1 = mngr1.open("firmaRoberto.png");
            Bitmap bmp1 = BitmapFactory.decodeStream(is1);
            ByteArrayOutputStream stream12 = new ByteArrayOutputStream();
            bmp1.compress(Bitmap.CompressFormat.PNG, 100, stream12);
            Image firmaR = Image.getInstance(stream12.toByteArray());
            */


            //Añadir imagen FIRMA
            Bitmap bitmap = BitmapFactory.decodeFile(firmaPNG.getAbsolutePath());
            ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream1);
            Image firma = Image.getInstance(stream1.toByteArray());

            Bitmap bitmap2 = BitmapFactory.decodeFile(firmaPNG2.getAbsolutePath());
            ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
            bitmap2.compress(Bitmap.CompressFormat.PNG, 50, stream2);
            Image firma2 = Image.getInstance(stream2.toByteArray());


            templatePDF.crearPDF(txtNombreEmpresa.getText().toString(),txtDireccionEmpresa.getText().toString());

            templatePDF.abrirDocumento();
            templatePDF.addMetaData("OrdenDeTrabajo","OrdenDeTrabajo","Antimouse");



            templatePDF.addImage(image);
            templatePDF.addContact(Cell,web,"ORDEN DE TRABAJO", "N° " + correlativo);
            templatePDF.Servicio(TipoServicio);
            templatePDF.addParagraph("Nombre empresa: " + txtNombreEmpresa.getText().toString());
            templatePDF.addParagraph("Direccion empresa: " + txtDireccionEmpresa.getText().toString());

            templatePDF.addParagraph("Fecha: "+ timeStamp);
            templatePDF.addParagraph("RBD: "+ txtRBDEmpresa.getText().toString());


            templatePDF.createTable(header,getFila1());
            templatePDF.createTable(header2,getFila2());

            templatePDF.addParagraph("\n");
            templatePDF.addParagraph("Observaciones");
            templatePDF.addParagraph(txtObservaciones.getText().toString());
            templatePDF.addFirmas(firma, firma2);
            templatePDF.closeDocument();



            //Ver PDF
            if (TemplatePDF.archivoPDF.exists()) {
                Uri uri = FileProvider.getUriForFile(Formulario.this,BuildConfig.APPLICATION_ID+".provider",TemplatePDF.archivoPDF);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(uri, "application/pdf");
                try {

                    Limpiar();
                    startActivity(intent);
                    BorrarFirma();

                    firmaPNG=null;
                    btnGuardar.setFocusable(false);
                    btnGuardar.setFocusableInTouchMode(false);


                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.adobe.reader")));
                    Toast.makeText(getApplicationContext(), "No cuentas con una aplicacion para visualizar pdf", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "No se encontro el archivo", Toast.LENGTH_LONG).show();
            }

        }


    }





    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    public void comprobarPagoPDF(){



        final String url1 = "http://cybertechnology.online/api/empresa/3";

        final RequestQueue queue1 = Volley.newRequestQueue(this);


        final JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url1, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        String array = response.toString();
                        try {
                            JSONArray json = new JSONArray(array);
                            for (int i = 0; i < json.length(); i++) {

                                JSONObject o = json.getJSONObject(i);

                                pago = o.getString("pago");


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                        SharedPreferences sesiones=getSharedPreferences(EmpresaDBContract.Sesion.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sesiones.edit();

                        if (pago.equals("0")) {

                            Intent intent=new Intent(Formulario.this,PagoCreadoActivity.class);

                            editor.putString(EmpresaDBContract.Sesion.FIELD_PAGO, String.valueOf(pago));
                            editor.putBoolean(EmpresaDBContract.Sesion.FIELD_SESSION, false);
                            editor.putString(EmpresaDBContract.Sesion.FIELD_USERNAME, "");
                            editor.apply();

                            startActivity(intent);




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
        queue1.add(getRequest);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    public void sendEmail(View view) {



        if (TemplatePDF.archivoPDF!=null) {
            String[] correo = {""};
            String[] CC = {""};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            Uri uri2 = FileProvider.getUriForFile(Formulario.this, BuildConfig.APPLICATION_ID + ".provider", TemplatePDF.archivoPDF);


            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, correo);
            emailIntent.putExtra(Intent.EXTRA_CC, CC);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Orden de Trabajo");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "");
            emailIntent.putExtra(Intent.EXTRA_STREAM, uri2);

            try {
                Limpiar();
                startActivity(Intent.createChooser(emailIntent, "Enviar Orden de Trabajo"));

            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(Formulario.this,
                        "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Snackbar.make(view, "Debe crear el archivo primero", Snackbar.LENGTH_SHORT).show();
        }

    }

    public void pdfFirma(View view){


        Intent intent = new Intent(Formulario.this, FirmaActivity.class);

        String rbstring = "";
        String perimetroExt="",perimetroInt="",bodegas="",deslindes="",pec="",bmp="",bpt="",o="",e="",c="",a="",sdm="",cya="",lya="",pp="",g="";
        String exterior="",interior="",bodegas2="",CBdp2="", CBpe22="", CBbmp22="", CBbpt22="", CBsh2="", CBc22="", CBad2="", CBg22="", CBo22="", CBsdm22="", CBcya22="", CBpp22="";
        String adm="",camarines="",camF="", casino="";
        String broma="",trampa="",tox="",CBtb2="", CBdb22="", CBdc2="",CBrb2="",CBrp2="",CBdm2="",CBdb222="", CB50p12="", CB50p22="", CBta2="", CBcnt2="";
        String cipe="",delta="",aqua="",agita="",anasec="" ,demon="" ,ko="" ,cyper="" ,cyperP="" ,quimico10="" ,quimico20="" ,trampa2="";
        String sani="",quimico05="",quimico01="";

        firmado="firmado";

         if (RadioServicio.isChecked()){ rbstring = "radio1";}
         if (RadioControl.isChecked()){ rbstring="radio2";}

         if (CheckRBpe.isChecked()){ perimetroExt="checked"; }
         if (CheckRBpi.isChecked()){ perimetroInt="checked"; }
         if (CheckRBb.isChecked()){ bodegas="checked"; }
         if (CBdeslindes.isChecked()){ deslindes="checked"; }
         if (CBpec.isChecked()){ pec="checked"; }
         if (CBbmp.isChecked()){ bmp="checked"; }
        if (CBbpt.isChecked()){bpt="checked";}
         if (CBo.isChecked()){ o="checked"; }
         if (CBe.isChecked()){ e="checked"; }
         if (CBc.isChecked()){ c="checked"; }
         if (CBa.isChecked()){ a="checked"; }
         if (CBsdm.isChecked()){ sdm="checked"; }
         if (CBcya.isChecked()){ cya="checked"; }
         if (CBlya.isChecked()){ lya="checked"; }
         if (CBpp.isChecked()){ pp="checked"; }
         if (CBg.isChecked()){ g="checked"; }

        if (CheckRbext.isChecked()){ exterior="checked"; }
        if (CheckRbint.isChecked()){ interior="checked"; }
        if (CheckRbbo.isChecked()){ bodegas2="checked"; }
        if (CBdp.isChecked()){ CBdp2="checked"; }
        if (CBpe2.isChecked()){ CBpe22="checked"; }
        if (CBbmp2.isChecked()){ CBbmp22="checked"; }
        if (CBbpt2.isChecked()){ CBbpt22="checked"; }
        if (CBsh.isChecked()){ CBsh2="checked"; }
        if (CBc2.isChecked()){ CBc22="checked"; }
        if (CBad.isChecked()){ CBad2="checked"; }
        if (CBg2.isChecked()){ CBg22="checked"; }
        if (CBo2.isChecked()){ CBo22="checked"; }
        if (CBsdm2.isChecked()){ CBsdm22="checked"; }
        if (CBcya2.isChecked()){ CBcya22="checked"; }
        if (CBpp2.isChecked()){ CBpp22="checked"; }



        if (CheckRbsha.isChecked()){ adm="checked"; }
        if (CheckRbshc.isChecked()){ camarines="checked"; }
        if (CheckRbcamF.isChecked()){ camF="checked"; }
        if (CBcgp.isChecked()){ casino="checked"; }

        if (CheckRbbroma.isChecked()){ broma="checked"; }
        if (CheckRbtrampa.isChecked()){ trampa="checked"; }
        if (CheckRbnotoxico.isChecked()){ tox="checked"; }
        //abajo
        if (CBtb.isChecked()){ CBtb2="checked"; }
        if (CBdb2.isChecked()){ CBdb22="checked"; }
        if (CBdc.isChecked()){ CBdc2="checked"; }
        if (CBrb.isChecked()){ CBrb2="checked"; }
        if (CBrp.isChecked()){ CBrp2="checked"; }
        if (CBdm.isChecked()){ CBdm2="checked"; }
        if (CBdb2.isChecked()){ CBdb222="checked"; }
        if (CB50p1.isChecked()){ CB50p12="checked"; }
        if (CB50p2.isChecked()){ CB50p22="checked"; }
        if (CBta.isChecked()){ CBta2="checked"; }
        if (CBcnt.isChecked()){ CBcnt2="checked"; }



        if (CheckRbcipermetrina.isChecked()){ cipe="checked"; }
        if (CheckRbdelta.isChecked()){ delta="checked"; }
        if (CheckRbaguatrin.isChecked()){ aqua="checked"; }
        if (CheckRbagita.isChecked()){ agita="checked"; }
        if (CBanasec.isChecked()){ anasec="checked"; }
        if (CBdemon.isChecked()){ demon="checked"; }
        if (CBko.isChecked()){ ko="checked"; }
        if (CBcyper.isChecked()){ cyper="checked"; }
        if (CBcyperP.isChecked()){ cyperP="checked"; }
        if (CBquimico10.isChecked()){ quimico10="checked"; }
        if (CBquimico20.isChecked()){ quimico20="checked"; }
        if (CBtrampa.isChecked()){ trampa2="checked"; }


        if (CheckRbsanicitrex.isChecked()){ sani="checked"; }
        if (CB05agua.isChecked()){ quimico05="checked"; }
        if (CB01agua.isChecked()){ quimico01="checked"; }



        Bundle bundle =new Bundle();
        bundle.putString("KEY_NOMBRE",txtNombreEmpresa.getText().toString());
        bundle.putString("KEY_DIRECCION",txtDireccionEmpresa.getText().toString());
        bundle.putString("KEY_RBD",txtRBDEmpresa.getText().toString());
        bundle.putString("KEY_OBSERVACIONES",txtObservaciones.getText().toString());

        bundle.putString("KEY_RADIO",rbstring);

        bundle.putString("KEY_PERIMETROEX",perimetroExt);
        bundle.putString("KEY_PERIMETROIN",perimetroInt);
        bundle.putString("KEY_BODEGAS",bodegas);
        bundle.putString("KEY_DESLINDES",deslindes);
        bundle.putString("KEY_PERIMETROEXTC",pec);
        bundle.putString("KEY_BODEGASMATERIASP", bmp);
        bundle.putString("KEY_BODEGASPRODUCT",bpt);
        bundle.putString("KEY_OFICINAS",o);
        bundle.putString("KEY_ENTRETECHOS",e);
        bundle.putString("KEY_CERCHAS",c);
        bundle.putString("KEY_ALCANTARILLADOS",a);
        bundle.putString("KEY_SALADEMAQUINAS",sdm);
        bundle.putString("KEY_CASINOSYANEXOS",cya);
        bundle.putString("KEY_LABORATORIOS",lya);
        bundle.putString("KEY_PRODUCCIONPROCESOS",pp);
        bundle.putString("KEY_GUARDIA",g);


        bundle.putString("KEY_EXTERIOR",exterior);
        bundle.putString("KEY_INTERIOR",interior);
        bundle.putString("KEY_BODEGAS2",bodegas2);
        bundle.putString("KEY_DESLINDESPATIO", CBdp2); //de aqui para abajo
        bundle.putString("KEY_PERIMETROEXTERNO", CBpe22);
        bundle.putString("KEY_BODEGAMATERIASPRIMAS",CBbmp22);
        bundle.putString("KEY_BODEGAPRODUCTOTERMINADO",CBbpt22);
        bundle.putString("KEY_SERVICIOSHIGIENICOS",CBsh2);
        bundle.putString("KEY_CAMARINES",CBc22);
        bundle.putString("KEY_ALCANTARILLADOS2",CBad2);
        bundle.putString("KEY_GUARDIA2",CBg22);
        bundle.putString("KEY_OFICINAS2",CBo22);
        bundle.putString("KEY_SALADEMAQUINAS2",CBsdm22);
        bundle.putString("KEY_CASINOSYANEXOS2",CBcya22);
        bundle.putString("KEY_PRODUCCIONPROCESOS2",CBpp22);


        bundle.putString("KEY_ADM",adm);
        bundle.putString("KEY_CAMARINES",camarines);
        bundle.putString("KEY_CAMF",camF);
        bundle.putString("KEY_CASINOG",casino);


        bundle.putString("KEY_BROMA",broma);
        bundle.putString("KEY_TRAMPA",trampa);
        bundle.putString("KEY_TOX",tox);
        bundle.putString("KEY_TERMIXANBLOQUE",CBtb2);// AQUI
        bundle.putString("KEY_DETIABLOQUE",CBdb22);
        bundle.putString("KEY_DETIACEBO",CBdc2);
        bundle.putString("KEY_RASTOPBLOQUE",CBrb2);
        bundle.putString("KEY_RASTOPPELLET",CBrp2);
        bundle.putString("KEY_DEABLINEMOLIENDA",CBdm2);
        bundle.putString("KEY_DEABLINEBLOQUE",CBdb222);
        bundle.putString("KEY_QUIMICO50P1",CB50p12);
        bundle.putString("KEY_QUIMICO50P2",CB50p22);
        bundle.putString("KEY_TRAMPAADHESIVAS",CBta2);
        bundle.putString("KEY_CEBONOTOXICO",CBcnt2);

        bundle.putString("KEY_CIPE",cipe);
        bundle.putString("KEY_DELTA",delta);
        bundle.putString("KEY_AQUA",aqua);
        bundle.putString("KEY_AGITA",agita);
        //---
        bundle.putString("KEY_ANASEC",anasec);
        bundle.putString("KEY_DEMON",demon);
        bundle.putString("KEY_KO",ko);
        bundle.putString("KEY_CYPERKILL",cyper);
        bundle.putString("KEY_CYPERKILLP",cyperP);
        bundle.putString("KEY_QUIMICO10",quimico10);
        bundle.putString("KEY_QUIMICO20",quimico20);
        bundle.putString("KEY_TRAMPA2",trampa2);


        bundle.putString("KEY_SANI",sani);
        bundle.putString("KEY_QUIMICO05",quimico05);
        bundle.putString("KEY_QUIMICO01",quimico01);

        bundle.putString("KEY_CORRELATIVO",correlativo);

        bundle.putString("KEY_FIRMADO",firmado);




        intent.putExtras(bundle);

        startActivity(intent);


    }

    public void pdfFirma2(View view){


        Intent intent = new Intent(Formulario.this, FirmaActivity2.class);

        String rbstring = "";
        String perimetroExt="",perimetroInt="",bodegas="",deslindes="",pec="",bmp="",bpt="",o="",e="",c="",a="",sdm="",cya="",lya="",pp="",g="";
        String exterior="",interior="",bodegas2="",CBdp2="", CBpe22="", CBbmp22="", CBbpt22="", CBsh2="", CBc22="", CBad2="", CBg22="", CBo22="", CBsdm22="", CBcya22="", CBpp22="";
        String adm="",camarines="",camF="", casino="";
        String broma="",trampa="",tox="",CBtb2="", CBdb22="", CBdc2="",CBrb2="",CBrp2="",CBdm2="",CBdb222="", CB50p12="", CB50p22="", CBta2="", CBcnt2="";
        String cipe="",delta="",aqua="",agita="",anasec="" ,demon="" ,ko="" ,cyper="" ,cyperP="" ,quimico10="" ,quimico20="" ,trampa2="";
        String sani="",quimico05="",quimico01="";

        firmado="firmado";

        if (RadioServicio.isChecked()){ rbstring = "radio1";}
        if (RadioControl.isChecked()){ rbstring="radio2";}

        if (CheckRBpe.isChecked()){ perimetroExt="checked"; }
        if (CheckRBpi.isChecked()){ perimetroInt="checked"; }
        if (CheckRBb.isChecked()){ bodegas="checked"; }
        if (CBdeslindes.isChecked()){ deslindes="checked"; }
        if (CBpec.isChecked()){ pec="checked"; }
        if (CBbmp.isChecked()){ bmp="checked"; }
        if (CBbpt.isChecked()){bpt="checked";}
        if (CBo.isChecked()){ o="checked"; }
        if (CBe.isChecked()){ e="checked"; }
        if (CBc.isChecked()){ c="checked"; }
        if (CBa.isChecked()){ a="checked"; }
        if (CBsdm.isChecked()){ sdm="checked"; }
        if (CBcya.isChecked()){ cya="checked"; }
        if (CBlya.isChecked()){ lya="checked"; }
        if (CBpp.isChecked()){ pp="checked"; }
        if (CBg.isChecked()){ g="checked"; }

        if (CheckRbext.isChecked()){ exterior="checked"; }
        if (CheckRbint.isChecked()){ interior="checked"; }
        if (CheckRbbo.isChecked()){ bodegas2="checked"; }
        if (CBdp.isChecked()){ CBdp2="checked"; }
        if (CBpe2.isChecked()){ CBpe22="checked"; }
        if (CBbmp2.isChecked()){ CBbmp22="checked"; }
        if (CBbpt2.isChecked()){ CBbpt22="checked"; }
        if (CBsh.isChecked()){ CBsh2="checked"; }
        if (CBc2.isChecked()){ CBc22="checked"; }
        if (CBad.isChecked()){ CBad2="checked"; }
        if (CBg2.isChecked()){ CBg22="checked"; }
        if (CBo2.isChecked()){ CBo22="checked"; }
        if (CBsdm2.isChecked()){ CBsdm22="checked"; }
        if (CBcya2.isChecked()){ CBcya22="checked"; }
        if (CBpp2.isChecked()){ CBpp22="checked"; }



        if (CheckRbsha.isChecked()){ adm="checked"; }
        if (CheckRbshc.isChecked()){ camarines="checked"; }
        if (CheckRbcamF.isChecked()){ camF="checked"; }
        if (CBcgp.isChecked()){ casino="checked"; }

        if (CheckRbbroma.isChecked()){ broma="checked"; }
        if (CheckRbtrampa.isChecked()){ trampa="checked"; }
        if (CheckRbnotoxico.isChecked()){ tox="checked"; }
        //abajo
        if (CBtb.isChecked()){ CBtb2="checked"; }
        if (CBdb2.isChecked()){ CBdb22="checked"; }
        if (CBdc.isChecked()){ CBdc2="checked"; }
        if (CBrb.isChecked()){ CBrb2="checked"; }
        if (CBrp.isChecked()){ CBrp2="checked"; }
        if (CBdm.isChecked()){ CBdm2="checked"; }
        if (CBdb2.isChecked()){ CBdb222="checked"; }
        if (CB50p1.isChecked()){ CB50p12="checked"; }
        if (CB50p2.isChecked()){ CB50p22="checked"; }
        if (CBta.isChecked()){ CBta2="checked"; }
        if (CBcnt.isChecked()){ CBcnt2="checked"; }



        if (CheckRbcipermetrina.isChecked()){ cipe="checked"; }
        if (CheckRbdelta.isChecked()){ delta="checked"; }
        if (CheckRbaguatrin.isChecked()){ aqua="checked"; }
        if (CheckRbagita.isChecked()){ agita="checked"; }
        if (CBanasec.isChecked()){ anasec="checked"; }
        if (CBdemon.isChecked()){ demon="checked"; }
        if (CBko.isChecked()){ ko="checked"; }
        if (CBcyper.isChecked()){ cyper="checked"; }
        if (CBcyperP.isChecked()){ cyperP="checked"; }
        if (CBquimico10.isChecked()){ quimico10="checked"; }
        if (CBquimico20.isChecked()){ quimico20="checked"; }
        if (CBtrampa.isChecked()){ trampa2="checked"; }


        if (CheckRbsanicitrex.isChecked()){ sani="checked"; }
        if (CB05agua.isChecked()){ quimico05="checked"; }
        if (CB01agua.isChecked()){ quimico01="checked"; }



        Bundle bundle =new Bundle();
        bundle.putString("KEY_NOMBRE",txtNombreEmpresa.getText().toString());
        bundle.putString("KEY_DIRECCION",txtDireccionEmpresa.getText().toString());
        bundle.putString("KEY_RBD",txtRBDEmpresa.getText().toString());
        bundle.putString("KEY_OBSERVACIONES",txtObservaciones.getText().toString());

        bundle.putString("KEY_RADIO",rbstring);

        bundle.putString("KEY_PERIMETROEX",perimetroExt);
        bundle.putString("KEY_PERIMETROIN",perimetroInt);
        bundle.putString("KEY_BODEGAS",bodegas);
        bundle.putString("KEY_DESLINDES",deslindes);
        bundle.putString("KEY_PERIMETROEXTC",pec);
        bundle.putString("KEY_BODEGASMATERIASP", bmp);
        bundle.putString("KEY_BODEGASPRODUCT",bpt);
        bundle.putString("KEY_OFICINAS",o);
        bundle.putString("KEY_ENTRETECHOS",e);
        bundle.putString("KEY_CERCHAS",c);
        bundle.putString("KEY_ALCANTARILLADOS",a);
        bundle.putString("KEY_SALADEMAQUINAS",sdm);
        bundle.putString("KEY_CASINOSYANEXOS",cya);
        bundle.putString("KEY_LABORATORIOS",lya);
        bundle.putString("KEY_PRODUCCIONPROCESOS",pp);
        bundle.putString("KEY_GUARDIA",g);


        bundle.putString("KEY_EXTERIOR",exterior);
        bundle.putString("KEY_INTERIOR",interior);
        bundle.putString("KEY_BODEGAS2",bodegas2);
        bundle.putString("KEY_DESLINDESPATIO", CBdp2); //de aqui para abajo
        bundle.putString("KEY_PERIMETROEXTERNO", CBpe22);
        bundle.putString("KEY_BODEGAMATERIASPRIMAS",CBbmp22);
        bundle.putString("KEY_BODEGAPRODUCTOTERMINADO",CBbpt22);
        bundle.putString("KEY_SERVICIOSHIGIENICOS",CBsh2);
        bundle.putString("KEY_CAMARINES",CBc22);
        bundle.putString("KEY_ALCANTARILLADOS2",CBad2);
        bundle.putString("KEY_GUARDIA2",CBg22);
        bundle.putString("KEY_OFICINAS2",CBo22);
        bundle.putString("KEY_SALADEMAQUINAS2",CBsdm22);
        bundle.putString("KEY_CASINOSYANEXOS2",CBcya22);
        bundle.putString("KEY_PRODUCCIONPROCESOS2",CBpp22);


        bundle.putString("KEY_ADM",adm);
        bundle.putString("KEY_CAMARINES",camarines);
        bundle.putString("KEY_CAMF",camF);
        bundle.putString("KEY_CASINOG",casino);


        bundle.putString("KEY_BROMA",broma);
        bundle.putString("KEY_TRAMPA",trampa);
        bundle.putString("KEY_TOX",tox);
        bundle.putString("KEY_TERMIXANBLOQUE",CBtb2);// AQUI
        bundle.putString("KEY_DETIABLOQUE",CBdb22);
        bundle.putString("KEY_DETIACEBO",CBdc2);
        bundle.putString("KEY_RASTOPBLOQUE",CBrb2);
        bundle.putString("KEY_RASTOPPELLET",CBrp2);
        bundle.putString("KEY_DEABLINEMOLIENDA",CBdm2);
        bundle.putString("KEY_DEABLINEBLOQUE",CBdb222);
        bundle.putString("KEY_QUIMICO50P1",CB50p12);
        bundle.putString("KEY_QUIMICO50P2",CB50p22);
        bundle.putString("KEY_TRAMPAADHESIVAS",CBta2);
        bundle.putString("KEY_CEBONOTOXICO",CBcnt2);

        bundle.putString("KEY_CIPE",cipe);
        bundle.putString("KEY_DELTA",delta);
        bundle.putString("KEY_AQUA",aqua);
        bundle.putString("KEY_AGITA",agita);
        //---
        bundle.putString("KEY_ANASEC",anasec);
        bundle.putString("KEY_DEMON",demon);
        bundle.putString("KEY_KO",ko);
        bundle.putString("KEY_CYPERKILL",cyper);
        bundle.putString("KEY_CYPERKILLP",cyperP);
        bundle.putString("KEY_QUIMICO10",quimico10);
        bundle.putString("KEY_QUIMICO20",quimico20);
        bundle.putString("KEY_TRAMPA2",trampa2);


        bundle.putString("KEY_SANI",sani);
        bundle.putString("KEY_QUIMICO05",quimico05);
        bundle.putString("KEY_QUIMICO01",quimico01);

        bundle.putString("KEY_CORRELATIVO",correlativo);

        bundle.putString("KEY_FIRMADO",firmado);




        intent.putExtras(bundle);

        startActivity(intent);


    }

    private ArrayList<String[]> getFila1(){
        ArrayList<String[]>rows=new ArrayList<>();

        rows.add(new String[]{perimetroExt +"\n" + perimetroInt + "\n" + bodegas
                ,Exterior + "\n" + Interior + "\n" + bodegas2 ,
                ServHig + "\n" + ServHigCamarines + "\n" + CamaraFrio});

        rows.add(new String[]{perimetroExt +"\n" + perimetroInt + "\n" + bodegas + "\n" + deslindes
                ,Exterior + "\n" + Interior + "\n" + bodegas2 ,
                ServHig + "\n" + ServHigCamarines + "\n" + CamaraFrio});

        rows.add(new String[]{perimetroExt +"\n" + perimetroInt + "\n" + bodegas + "\n" + deslindes + "\n" + PerimetroExternoCercano + "\n" + bodegasMateriasPrimas + "\n" + bodegasProductosTerminados + "\n" + oficinas + "\n" + entretechos + "\n" + cerchas + "\n" + alcantarillados + "\n" + salasDeMquinas + "\n" + casinosYAnexos + "\n" + laboratoriosYAnexos + "\n" + produccionProcesos + "\n" + guardia
                ,Exterior + "\n" + Interior + "\n" + bodegas2 + "\n" + perimetroExterno + "\n" + bodegasMateriasPrimas2 + "\n" + bodegasProductosTerminados2 + "\n" + serviciosHigienicos + "\n" + camarines + "\n" + alcantarillados2 + "\n" + guardia2 + "\n" + oficinas2 + "\n" + salasDeMquinas2 + "\n" +casinosYAnexos2 + "\n" + produccionProcesos2,
                ServHig + "\n" + ServHigCamarines + "\n" + CamaraFrio + "\n" + CasinoGerenciaPersonal});


        return rows;
    }
    private ArrayList<String[]> getFila2(){
        ArrayList<String[]>rows=new ArrayList<>();

        rows.add(new String[]{Bromadiolona +"\n" + Trampacapturaviva + "\n" + Notoxicas,
                Cipermetrina +"\n"+ Deltametrina +"\n" + Aquatrin + "\n" + Agita,
                Sanicitrex});
        rows.add(new String[]{Bromadiolona +"\n" + Trampacapturaviva + "\n" + Notoxicas + "\n" + EspacioBlanco
                ,Cipermetrina +"\n"+ Deltametrina +"\n" + Aquatrin +"\n" + Agita,
                EspacioBlanco + "\n" + Sanicitrex + EspacioBlanco + "\n" + EspacioBlanco});
        rows.add(new String[]{Bromadiolona +"\n" + Trampacapturaviva + "\n" + Notoxicas + "\n" + termixanbloque + "\n" + detiabloque + "\n" + detiacebo + "\n" +rastopbloque + "\n" + rastoppellet + "\n" + deablinemolienda + "\n" + deablinebloque + "\n" + EspacioBlanco + "\n" + usoQuimico + "\n" + dosis50p + "\n" + dosis50p2 + "\n" + EspacioBlanco + "\n" + noQuimico + "\n" + Trampaadhesivas + "\n" + cebonotoxico,
                Cipermetrina +"\n"+ Deltametrina +"\n" + Aquatrin +"\n" + Agita + "\n" + Anasec + "\n" + Demon + "\n" + KO + "\n" + Cyperkill + "\n" + CyperkillP + "\n" + EspacioBlanco + "\n" + usoQuimico + "\n" + dosisUso10 + "\n" + dosisUso20 + "\n" + EspacioBlanco + "\n" + noQuimico + "\n" + TrampaA,
                EspacioBlanco + "\n" + Sanicitrex + EspacioBlanco + "\n" +  EspacioBlanco + "\n" + usoQuimico + "\n" + dosisUso05agua + "\n" + dosisUso01agua });


        return rows;
    }

    private ArrayList<String[]> Fila1(){


        ArrayList<String[]>rows=new ArrayList<>();

        rows.add(new String[]{Bromadiolona +"\n" + Trampacapturaviva + "\n" + Notoxicas,
                Cipermetrina +"\n"+ Deltametrina +"\n" + Aquatrin + "\n" + Agita,
                Sanicitrex});

        return rows;
    }

    private void Limpiar(){

        perimetroExt = " ";
        perimetroInt = " ";
        bodegas = "  ";
        deslindes = "   ";
         PerimetroExternoCercano = "  ";
         bodegasMateriasPrimas = "    ";
         bodegasProductosTerminados = " ";
         oficinas = " ";
         cerchas = "  ";
         alcantarillados = "  ";
         salasDeMquinas = "   ";
         casinosYAnexos = "   ";
         laboratoriosYAnexos = "  ";
         produccionProcesos = "   ";
         guardia = "  ";
        //----
        Exterior = " ";
        Interior = " ";
        bodegas2 = "  ";
         deslindesP = "   ";
         perimetroExterno = "   ";
         bodegasMateriasPrimas2 = "   ";
         bodegasProductosTerminados2 = "   ";
         serviciosHigienicos = "   ";
         camarines = "   ";
         alcantarillados2 = "   ";
         guardia2 = "   ";
         oficinas2 = "   ";
         salasDeMquinas2 = "   ";
         casinosYAnexos2 = "   ";
         produccionProcesos2 = "      ";
        //----
        ServHig = "  ";
        ServHigCamarines = " ";
        CamaraFrio = "   ";
        CasinoGerenciaPersonal = "  ";
        //----
        Bromadiolona = " ";
        Trampacapturaviva = "    ";
        Notoxicas = "    ";
         termixanbloque = "    ";
         detiabloque = "    ";
         detiacebo = "    ";
         rastopbloque = "    ";
         rastoppellet = "    ";
         deablinemolienda = "    ";
         deablinebloque = "    ";
         dosis50p = "    ";
         dosis50p2 = "    ";
         Trampaadhesivas = "    ";
         cebonotoxico = "    ";
        //----
        Cipermetrina = "    ";
        Deltametrina = "    ";
        Aquatrin = "    ";
        Agita = "    ";
         Cipermetrina = "    ";
         Deltametrina = "    ";
         Aquatrin = "    ";
         Agita = "    ";
         Anasec = "    ";
         Demon = "    ";
         KO = "    ";
         Cyperkill = "    ";
         CyperkillP = "    ";
         dosisUso10 = "    ";
         dosisUso20 = "    ";
         TrampaA = "    ";
        //----
        Sanicitrex = "   ";
        dosisUso05agua = "   ";
        dosisUso01agua = "   ";
        EspacioBlanco = "    ";

        txtNombreEmpresa.setText("");
        txtDireccionEmpresa.setText("");
        txtRBDEmpresa.setText("");



        RGroup.clearCheck();

        //---Primer----
        CheckRBpe.setChecked(false);
        CheckRBpi.setChecked(false);
        CheckRBb.setChecked(false);
        CBdeslindes.setChecked(false);
        CBpec.setChecked(false);
        CBbmp.setChecked(false);
        CBbpt.setChecked(false);
        CBo.setChecked(false);
        CBe.setChecked(false);
        CBc.setChecked(false);
        CBa.setChecked(false);
        CBsdm.setChecked(false);
        CBcya.setChecked(false);
        CBlya.setChecked(false);
        CBpp.setChecked(false);
        CBg.setChecked(false);
        //----Segundo----
        CheckRbext.setChecked(false);
        CheckRbint.setChecked(false);
        CheckRbbo.setChecked(false);
        CBdp.setChecked(false);
                CBpe2.setChecked(false);
        CBbmp2.setChecked(false);
                CBbpt2.setChecked(false);
        CBsh.setChecked(false);
                CBc2.setChecked(false);
        CBad.setChecked(false);
                CBg2.setChecked(false);
        CBo2.setChecked(false);
                CBsdm2.setChecked(false);
        CBcya2.setChecked(false);
                CBpp2.setChecked(false);
        //----tercero-----
        CheckRbsha.setChecked(false);
        CheckRbshc.setChecked(false);
        CheckRbcamF .setChecked(false);
        CBcgp.setChecked(false);
        //-----Cuarto-----
        CheckRbbroma.setChecked(false);
        CheckRbtrampa.setChecked(false);
        CheckRbnotoxico.setChecked(false);
        CBtb.setChecked(false);
                CBdb.setChecked(false);
        CBdc.setChecked(false);
                CBrb.setChecked(false);
        CBrp.setChecked(false);
                CBdm.setChecked(false);
        CBdb2.setChecked(false);
                CB50p1.setChecked(false);
        CB50p2.setChecked(false);
                CBta.setChecked(false);
        CBcnt.setChecked(false);
        //---------Quinto---------
        CheckRbcipermetrina.setChecked(false);
        CheckRbdelta.setChecked(false);
        CheckRbaguatrin.setChecked(false);
        CheckRbagita.setChecked(false);
        CBanasec.setChecked(false);
        CBdemon.setChecked(false);
        CBko.setChecked(false);
        CBcyper.setChecked(false);
        CBcyperP.setChecked(false);
        CBquimico10.setChecked(false);
        CBquimico20.setChecked(false);
        CBtrampa.setChecked(false);
        //-----sexto-----
        CheckRbsanicitrex.setChecked(false);
        CB05agua.setChecked(false);
        CB01agua.setChecked(false);

        txtObservaciones.setText("");

    }


    private void BorrarFirma()  {

        File dir = new File(Environment.getExternalStorageDirectory()+"/Android/data/signature");


        try {
            FileUtils.deleteDirectory(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
