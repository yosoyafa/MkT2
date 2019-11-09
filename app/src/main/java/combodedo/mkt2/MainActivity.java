package combodedo.mkt2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import model.Local;
import utils.ConexionHTTP;
import utils.DataBase;
import utils.LogicDataBase;

public class MainActivity extends AppCompatActivity {

    private CardView cardDownload, cardCCs, cardSync, cardHistorial;
    private Toolbar toolbar;
    private TextView tvWelcome;
    private ProgressDialog progress;
    private static LogicDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new LogicDataBase(getApplicationContext());
        setContentView(R.layout.activity_main);
        setWidgets();
        setOnClick();
    }

    private void setOnClick() {
        cardCCs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToListaCCs();
            }
        });

        cardDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = ProgressDialog.show(MainActivity.this, "Descargando información de centros comerciales",
                        "Se están descagando los datos de tus centros comerciales", true);

                new Thread(new Runnable() {
                    @Override
                    public void run()
                    {
                        try {
                            int a = downloadCCs();
                            if(a==1){
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Descarga completa",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                            }else if(a==2){
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "ERROR JSON",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                            }else if(a==3){
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "No se pudo realizar la descarga, revisa tu conexión a internet",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {
                                progress.dismiss();
                            }
                        });
                    }
                }).start();
            }
        });

    }

    private void goToListaCCs() {
        ArrayList<String> ccs = db.getCCs();
        System.out.println("----------------------------------------------\nsize: "+ccs.size());
        if(ccs != null && !ccs.isEmpty()){
            Intent listaCCsActivity = new Intent(getApplicationContext(), ListaCCsActivity.class);
            for (int a = 0; a < ccs.size(); a++) {
                listaCCsActivity.putExtra("cc" + a, ccs.get(a));
                System.out.println(ccs.get(a));
            }
            System.out.println("----------------------------------------------");
            listaCCsActivity.putExtra("num_ccs", ccs.size());
            startActivity(listaCCsActivity);
        }else{
            Toast.makeText(MainActivity.this, "No hay centros comerciales disponibles, intenta descargandolos antes.",
                    Toast.LENGTH_LONG).show();

        }
    }

    private int downloadCCs() {
        ConexionHTTP conexion = new ConexionHTTP();
        //ConexionHTTP conexion = new ConexionHTTP(sharedPreferences.getString("sede","-"));
        conexion.downloadCCs("");
        while (!conexion.isFinishProcess()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        JSONArray respuesta = conexion.getResponseArray();
        if (respuesta != null) {
            db.resetLocalesComerciales();
            try {
                for (int a = 0; a < respuesta.length(); a++) {
                    JSONObject row = respuesta.getJSONObject(a);

                    String centroComercial = row.getString("centroComercial");
                    String numeroLocal = row.getString("local");
                    String area = row.getString("area");
                    String nombre = row.getString("comerciante");
                    String codigoCategoria = row.getString("codigoCategoria");
                    String codigoSubcategoria = row.getString("codigoSubcategoria");
                    String codigoBien = row.getString("tipoBienServicio");
                    String idLocal = row.getString("idLocal");

                    Local local = new Local(idLocal,nombre,numeroLocal,area,codigoCategoria,codigoSubcategoria,codigoBien,centroComercial);
                    db.addLocal(local);
                }

                //Toast.makeText(this, "Descarga completa",
                //        Toast.LENGTH_LONG).show();
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
                //Toast.makeText(this, "ERROR JSON",
                //        Toast.LENGTH_LONG).show();
                return 2;
            }
        } else {
            //Toast.makeText(this, "No se pudo realizar la descarga, revisa tu conexión a internet",
            //        Toast.LENGTH_LONG).show();
            return 3;
        }
    }

    private void setWidgets() {
        cardDownload = findViewById(R.id.cardDownload);
        cardCCs = findViewById(R.id.cardCCs);
        cardSync = findViewById(R.id.cardSync);
        cardHistorial = findViewById(R.id.cardHistory);
        toolbar = findViewById(R.id.toolbar_main);
        tvWelcome = findViewById(R.id.tv_welcome);
        tvWelcome.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
    }

    public static LogicDataBase getDb() {
        return db;
    }
}
