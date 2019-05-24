package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FirmaActivity2 extends AppCompatActivity {

    private Button btnClear, btnSave;
    private File file;
    private LinearLayout canvasLL;
    private View view;
    private signature mSignature;
    private Bitmap bitmap;

    public static File firmaPNG2;


    // Creating Separate Directory for saving Generated Images
    String DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/Android/data/signature/";
    String pic_name = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    String StoredPath = DIRECTORY + pic_name + ".png";

    //VARIABLES PASADAS POR LA ACTIVIDAD FORMULARIO PARA SER DEVUELTA
    private String formularioNombre, formularioDireccion, formularioRBD, formularioObservaciones, formularioRadio;

    //CHECKBOX
    private String formularioPE,formularioPI,formularioBO, formularioDeslindes, formularioPEC, formularioBMP, formularioBPT, formularioO, formularioE, formularioC, formularioA, formularioSDM, formularioCYA, formularioLYA, formularioPP, formularioG;

    private String formularioEX,formularioIN,formularioBO2, formularioCBdpV , formularioCBdpVCBpe2V,formularioCBdpVCBbmp2, formularioCBdpVCBbpt2, formularioCBdpVCBsh, formularioCBdpVCBc2, formularioCBdpVCBad, formularioCBdpVCBg2, formularioCBdpVCBo2, formularioCBdpVCBsdm2, formularioCBdpVCBcya2, formularioCBdpVCBpp2;

    private String formularioADM,formularioCAMARINES,formularioCAMF, formularioCasinoG, formularioSeda;

    private String formularioBroma,formularioTrampa,formularioTox,formularioCBtb, formularioCBdb, formularioCBdc, formularioCBrb, formularioCBrp, formularioCBdm, formularioCBdb2, formularioCB50p1, formularioCB50p2, formularioCBta, formularioCBcnt;

    private String formularioCIPE,formularioDELTA,formularioAQUA,formularioAGITA ,formularioAnasec, formularioDemon, formularioKO, formularioCyper, formularioCyperP, formularioQuimico10, formularioQuimico20, formularioTrampa2;

    private String formularioSANI,formulario05, formulario01;;

    private String formularioCORR;

    private String formularioFirmado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma2);

        Bundle extras = this.getIntent().getExtras();

        if (extras!=null){


            formularioNombre=extras.getString("KEY_NOMBRE");
            formularioDireccion=extras.getString("KEY_DIRECCION");
            formularioRBD=extras.getString("KEY_RBD");
            formularioObservaciones=extras.getString("KEY_OBSERVACIONES");
            formularioRadio=extras.getString("KEY_RADIO");

            formularioPE=extras.getString("KEY_PERIMETROEX");
            formularioPI=extras.getString("KEY_PERIMETROIN");
            formularioBO=extras.getString("KEY_BODEGAS");
            formularioDeslindes=extras.getString("KEY_DESLINDES");
            formularioPEC=extras.getString("KEY_PERIMETROEXTC");
            formularioBMP=extras.getString("KEY_BODEGASMATERIASP");
            formularioBPT=extras.getString("KEY_BODEGASPRODUCT");
            formularioO=extras.getString("KEY_OFICINAS");
            formularioE=extras.getString("KEY_ENTRETECHOS");
            formularioC=extras.getString("KEY_CERCHAS");
            formularioA=extras.getString("KEY_ALCANTARILLADOS");
            formularioSDM=extras.getString("KEY_SALADEMAQUINAS");
            formularioCYA=extras.getString("KEY_CASINOSYANEXOS");
            formularioLYA=extras.getString("KEY_LABORATORIOS");
            formularioPP=extras.getString("KEY_PRODUCCIONPROCESOS");
            formularioG=extras.getString("KEY_GUARDIA");

            formularioEX=extras.getString("KEY_EXTERIOR");
            formularioIN=extras.getString("KEY_INTERIOR");
            formularioBO2=extras.getString("KEY_BODEGAS2");
            formularioCBdpV=extras.getString("KEY_DESLINDESPATIO"); //de aqui para abajo
            formularioCBdpVCBpe2V=extras.getString("KEY_PERIMETROEXTERNO");
            formularioCBdpVCBbmp2=extras.getString("KEY_BODEGAMATERIASPRIMAS");
            formularioCBdpVCBbpt2=extras.getString("KEY_BODEGAPRODUCTOTERMINADO");
            formularioCBdpVCBsh=extras.getString("KEY_SERVICIOSHIGIENICOS");
            formularioCBdpVCBc2=extras.getString("KEY_CAMARINES");
            formularioCBdpVCBad=extras.getString("KEY_ALCANTARILLADOS2");
            formularioCBdpVCBg2=extras.getString("KEY_GUARDIA2");
            formularioCBdpVCBo2=extras.getString("KEY_OFICINAS2");
            formularioCBdpVCBsdm2=extras.getString("KEY_SALADEMAQUINAS2");
            formularioCBdpVCBcya2=extras.getString("KEY_CASINOSYANEXOS2");
            formularioCBdpVCBpp2=extras.getString("KEY_PRODUCCIONPROCESOS2");

            formularioADM=extras.getString("KEY_ADM");
            formularioCAMARINES=extras.getString("KEY_CAMARINES");
            formularioCAMF=extras.getString("KEY_CAMF");
            formularioCasinoG=extras.getString("KEY_CASINOG");
            formularioSeda=extras.getString("KEY_SEDA");

            formularioBroma=extras.getString("KEY_BROMA");
            formularioTrampa=extras.getString("KEY_TRAMPA");
            formularioTox=extras.getString("KEY_TOX");
            formularioCBtb=extras.getString("KEY_TERMIXANBLOQUE");// AQUI
            formularioCBdb=extras.getString("KEY_DETIABLOQUE");
            formularioCBdc=extras.getString("KEY_DETIACEBO");
            formularioCBrb=extras.getString("KEY_RASTOPBLOQUE");
            formularioCBrp=extras.getString("KEY_RASTOPPELLET");
            formularioCBdm=extras.getString("KEY_DEABLINEMOLIENDA");
            formularioCBdb2=extras.getString("KEY_DEABLINEBLOQUE");
            formularioCB50p1=extras.getString("KEY_QUIMICO50P1");
            formularioCB50p2=extras.getString("KEY_QUIMICO50P2");
            formularioCBta=extras.getString("KEY_TRAMPAADHESIVAS");
            formularioCBcnt=extras.getString("KEY_CEBONOTOXICO");

            formularioCIPE=extras.getString("KEY_CIPE");
            formularioDELTA=extras.getString("KEY_DELTA");
            formularioAQUA=extras.getString("KEY_AQUA");
            formularioAGITA=extras.getString("KEY_AGITA");
            formularioAnasec=extras.getString("KEY_ANASEC");
            formularioDemon=extras.getString("KEY_DEMON");
            formularioKO=extras.getString("KEY_KO");
            formularioCyper=extras.getString("KEY_CYPERKILL");
            formularioCyperP=extras.getString("KEY_CYPERKILLP");
            formularioQuimico10=extras.getString("KEY_QUIMICO10");
            formularioQuimico20=extras.getString("KEY_QUIMICO20");
            formularioTrampa2=extras.getString("KEY_TRAMPA2");

            formularioSANI=extras.getString("KEY_SANI");
            formulario05=extras.getString("KEY_QUIMICO05");
            formulario01=extras.getString("KEY_QUIMICO01");

            formularioCORR=extras.getString("KEY_CORRELATIVO");

            formularioFirmado=extras.getString("KEY_FIRMADO");

        }


        canvasLL = (LinearLayout) findViewById(R.id.canvasLL);
        mSignature = new signature(getApplicationContext(), null);
        mSignature.setBackgroundColor(Color.WHITE);
        // Dynamically generating Layout through java code
        canvasLL.addView(mSignature, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        btnClear = (Button) findViewById(R.id.btnclear);
        btnSave = (Button) findViewById(R.id.btnsave);

        view = canvasLL;

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignature.clear();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                view.setDrawingCacheEnabled(true);
                mSignature.save(view,StoredPath);


                Toast.makeText(getApplicationContext(), "Firma Guardada", Toast.LENGTH_SHORT).show();


                Bitmap signature = mSignature.getBitmap();
                Intent intent = new Intent(FirmaActivity2.this, Formulario.class);


                firmaPNG2 = new File(DIRECTORY,pic_name+".png");


                Bundle bundle =new Bundle();
                bundle.putString("KEY_NOMBRE",formularioNombre);
                bundle.putString("KEY_DIRECCION",formularioDireccion);
                bundle.putString("KEY_RBD",formularioRBD);
                bundle.putString("KEY_OBSERVACIONES",formularioObservaciones);

                bundle.putString("KEY_RADIO",formularioRadio);

                bundle.putString("KEY_PERIMETROEX",formularioPE);
                bundle.putString("KEY_PERIMETROIN",formularioPI);
                bundle.putString("KEY_BODEGAS",formularioBO);
                bundle.putString("KEY_DESLINDES",formularioDeslindes);
                bundle.putString("KEY_PERIMETROEXTC",formularioPEC);
                bundle.putString("KEY_BODEGASMATERIASP",formularioBMP);
                bundle.putString("KEY_BODEGASPRODUCT",formularioBPT);
                bundle.putString("KEY_OFICINAS",formularioO);
                bundle.putString("KEY_ENTRETECHOS",formularioE);
                bundle.putString("KEY_CERCHAS",formularioC);
                bundle.putString("KEY_ALCANTARILLADOS",formularioA);
                bundle.putString("KEY_SALADEMAQUINAS",formularioSDM);
                bundle.putString("KEY_CASINOSYANEXOS",formularioCYA);
                bundle.putString("KEY_LABORATORIOS",formularioLYA);
                bundle.putString("KEY_PRODUCCIONPROCESOS",formularioPP);
                bundle.putString("KEY_GUARDIA",formularioG);

                bundle.putString("KEY_EXTERIOR",formularioEX);
                bundle.putString("KEY_INTERIOR",formularioIN);
                bundle.putString("KEY_BODEGAS2",formularioBO2);
                bundle.putString("KEY_DESLINDESPATIO", formularioCBdpV ); //de aqui para abajo
                bundle.putString("KEY_PERIMETROEXTERNO", formularioCBdpVCBpe2V);
                bundle.putString("KEY_BODEGAMATERIASPRIMAS",formularioCBdpVCBbmp2);
                bundle.putString("KEY_BODEGAPRODUCTOTERMINADO",formularioCBdpVCBbpt2);
                bundle.putString("KEY_SERVICIOSHIGIENICOS",formularioCBdpVCBsh);
                bundle.putString("KEY_CAMARINES",formularioCBdpVCBc2);
                bundle.putString("KEY_ALCANTARILLADOS2",formularioCBdpVCBad);
                bundle.putString("KEY_GUARDIA2",formularioCBdpVCBg2);
                bundle.putString("KEY_OFICINAS2",formularioCBdpVCBo2);
                bundle.putString("KEY_SALADEMAQUINAS2",formularioCBdpVCBsdm2);
                bundle.putString("KEY_CASINOSYANEXOS2",formularioCBdpVCBcya2);
                bundle.putString("KEY_PRODUCCIONPROCESOS2",formularioCBdpVCBpp2);

                bundle.putString("KEY_ADM",formularioADM);
                bundle.putString("KEY_CAMARINES",formularioCAMARINES);
                bundle.putString("KEY_CAMF",formularioCAMF);
                bundle.putString("KEY_CASINOG",formularioCasinoG);
                bundle.putString("KEY_SEDA",formularioSeda);

                bundle.putString("KEY_BROMA",formularioBroma);
                bundle.putString("KEY_TRAMPA",formularioTrampa);
                bundle.putString("KEY_TOX",formularioTox);
                bundle.putString("KEY_TERMIXANBLOQUE",formularioCBtb);// AQUI
                bundle.putString("KEY_DETIABLOQUE",formularioCBdb);
                bundle.putString("KEY_DETIACEBO",formularioCBdc);
                bundle.putString("KEY_RASTOPBLOQUE",formularioCBrb);
                bundle.putString("KEY_RASTOPPELLET",formularioCBrp);
                bundle.putString("KEY_DEABLINEMOLIENDA",formularioCBdm);
                bundle.putString("KEY_DEABLINEBLOQUE",formularioCBdb2);
                bundle.putString("KEY_QUIMICO50P1",formularioCB50p1);
                bundle.putString("KEY_QUIMICO50P2",formularioCB50p2);
                bundle.putString("KEY_TRAMPAADHESIVAS",formularioCBta);
                bundle.putString("KEY_CEBONOTOXICO",formularioCBcnt);

                bundle.putString("KEY_CIPE",formularioCIPE);
                bundle.putString("KEY_DELTA",formularioDELTA);
                bundle.putString("KEY_AQUA",formularioAQUA);
                bundle.putString("KEY_AGITA",formularioAGITA);
                bundle.putString("KEY_ANASEC",formularioAnasec);
                bundle.putString("KEY_DEMON",formularioDemon);
                bundle.putString("KEY_KO",formularioKO);
                bundle.putString("KEY_CYPERKILL",formularioCyper);
                bundle.putString("KEY_CYPERKILLP",formularioCyperP);
                bundle.putString("KEY_QUIMICO10",formularioQuimico10);
                bundle.putString("KEY_QUIMICO20",formularioQuimico20);
                bundle.putString("KEY_TRAMPA2",formularioTrampa2);

                bundle.putString("KEY_SANI",formularioSANI);
                bundle.putString("KEY_QUIMICO05",formulario05);
                bundle.putString("KEY_QUIMICO01",formulario01);

                bundle.putString("KEY_CORRELATIVO",formularioCORR);

                bundle.putString("KEY_FIRMADO",formularioFirmado);

                intent.putExtras(bundle);

                startActivity(intent);





            }
        });

        // Method to create Directory, if the Directory doesn't exists
        file = new File(DIRECTORY);
        if (!file.exists()) {
            file.mkdir();
        }

    }



    public class signature extends View {

        private static final float STROKE_WIDTH = 5f;
        private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
        private Paint paint = new Paint();
        private Path path = new Path();
        private float lastTouchX;
        private float lastTouchY;
        private final RectF dirtyRect = new RectF();

        public signature(Context context, AttributeSet attrs) {
            super(context, attrs);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(STROKE_WIDTH);
        }

        public Bitmap getBitmap() {
            View v = (View) this.getParent();
            Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
            v.draw(c);

            return b;
        }

        public FileOutputStream save(View v, String StoredPath) {
            Log.v("log_tag", "Width: " + v.getWidth());
            Log.v("log_tag", "Height: " + v.getHeight());
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(canvasLL.getWidth(), canvasLL.getHeight(), Bitmap.Config.RGB_565);
            }
            Canvas canvas = new Canvas(bitmap);
            try {
                // Output the file
                FileOutputStream mFileOutStream = new FileOutputStream(StoredPath);
                v.draw(canvas);

                // Convert the output file to Image such as .png
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, mFileOutStream);






                mFileOutStream.flush();
                mFileOutStream.close();




            } catch (Exception e) {
                Log.v("log_tag", e.toString());
            }


            return null;
        }

        public void clear() {
            path.reset();
            invalidate();

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    lastTouchX = eventX;
                    lastTouchY = eventY;
                    return true;

                case MotionEvent.ACTION_MOVE:

                case MotionEvent.ACTION_UP:

                    resetDirtyRect(eventX, eventY);
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++) {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);
                        expandDirtyRect(historicalX, historicalY);
                        path.lineTo(historicalX, historicalY);
                    }
                    path.lineTo(eventX, eventY);
                    break;

                default:
                    debug("Ignored touch event: " + event.toString());
                    return false;
            }

            invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.top - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.right + HALF_STROKE_WIDTH),
                    (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

            lastTouchX = eventX;
            lastTouchY = eventY;

            return true;
        }

        private void debug(String string) {

            Log.v("log_tag", string);

        }

        private void expandDirtyRect(float historicalX, float historicalY) {
            if (historicalX < dirtyRect.left) {
                dirtyRect.left = historicalX;
            } else if (historicalX > dirtyRect.right) {
                dirtyRect.right = historicalX;
            }

            if (historicalY < dirtyRect.top) {
                dirtyRect.top = historicalY;
            } else if (historicalY > dirtyRect.bottom) {
                dirtyRect.bottom = historicalY;
            }
        }

        private void resetDirtyRect(float eventX, float eventY) {
            dirtyRect.left = Math.min(lastTouchX, eventX);
            dirtyRect.right = Math.max(lastTouchX, eventX);
            dirtyRect.top = Math.min(lastTouchY, eventY);
            dirtyRect.bottom = Math.max(lastTouchY, eventY);
        }
    }
}

