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

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import model.Local;
import utils.ConexionHTTP;
import utils.DataBase;
import utils.Links;
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
        AndroidNetworking.initialize(getApplicationContext());
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
                        "Se están descargando los datos de tus centros comerciales", true);

                String linkDescarga = new Links().getDownloadAllCCs();
                AndroidNetworking.get(linkDescarga)
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONArray(new JSONArrayRequestListener() {
                            @Override
                            public void onResponse(JSONArray response) {
                                //System.out.println(response);
                                //Toast.makeText(MainActivity.this, "AN",Toast.LENGTH_LONG).show();

                                if (response != null) {
                                    db.resetLocalesComerciales();
                                    try {
                                        for (int a = 0; a < response.length(); a++) {
                                            JSONObject row = response.getJSONObject(a);

                                            String centroComercial = row.getString("centroComercial");
                                            String numeroLocal = row.getString("local");
                                            String area = row.getString("area");
                                            String nombre = row.getString("comerciante");
                                            String codigoCategoria = row.getString("codigoCategoria");
                                            String codigoSubcategoria = row.getString("codigoSubcategoria");
                                            String codigoBien = row.getString("tipoBienServicio");
                                            String idLocal = row.getString("idLocal");
                                            String gestionado = row.getString("gestionado");

                                            Local local = new Local(idLocal,nombre,numeroLocal,area,codigoCategoria,codigoSubcategoria,codigoBien,centroComercial,gestionado);
                                            db.addLocal(local);
                                        }

                                        Toast.makeText(MainActivity.this,
                                                "Descarga completa",Toast.LENGTH_LONG).show();
                                        //return 1;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Toast.makeText(MainActivity.this,
                                                "Hubo un error procesando la información", Toast.LENGTH_LONG).show();
                                        //return 2;
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this,
                                            "No se pudo realizar la descarga, revisa tu conexión a internet",Toast.LENGTH_LONG).show();
                                    //return 3;
                                }

                                progress.dismiss();
                            }
                            @Override
                            public void onError(ANError error) {
                                // handle error
                            }
                        });
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
