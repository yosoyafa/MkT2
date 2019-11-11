package combodedo.mkt2;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import model.Gestion;
import model.Local;
import utils.ConexionHTTP;
import utils.LogicDataBase;

public class EditLocalActivity extends AppCompatActivity implements GestionDialog.ExampleDialogListener {

    private Local local;
    private Toolbar toolbar;
    private TextView tvNombre, tvCategoria, tvSubcategoria, tvBien, tvNumLocal, tvArea, tvSpCategoria, tvSpSubcategoria, tvSpBien;
    private RadioButton rbIgual, rbCambio, rbVacio;
    private EditText etNombre, etArea, etObservacion;
    private Spinner spinnerCategoria, spinnerSubcategoria, spinnerBien;
    private Button btnGuardar;
    private LogicDataBase db;
    private String catNuevo, subcatNuevo, bienNuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_local);
        db = MainActivity.getDb();
        setLocal();
        setWidgets();
        configSpinners();
    }

    private void setLocal() {
        String[] loc = getIntent().getStringExtra("local").split(",");
        local = new Local(loc[0], Integer.parseInt(loc[1]), loc[2], loc[3], loc[4], loc[5], loc[6], loc[7], loc[8], loc[9]);
        System.out.println(local.toStringRaw());
        catNuevo = "";
        subcatNuevo = "";
        bienNuevo = "";
    }

    private void setWidgets() {
        tvNombre = findViewById(R.id.tv_edit_nombre);
        tvNombre.setText(local.getNombre());
        tvNombre.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        tvNombre.setTypeface(null, Typeface.BOLD);

        tvCategoria = findViewById(R.id.tv_edit_categoria);
        tvCategoria.setText("Categoría: " + local.getCategoria());

        tvSubcategoria = findViewById(R.id.tv_edit_subcategoria);
        tvSubcategoria.setText("Subcategoría: " + local.getSubcategoria());

        tvBien = findViewById(R.id.tv_edit_tipo_bien_servicio);
        tvBien.setText("Tipo de bien/servicio: " + local.getTipoBien());

        tvNumLocal = findViewById(R.id.tv_edit_numero_local);
        tvNumLocal.setText("Local: " + local.getNumero());

        tvArea = findViewById(R.id.tv_edit_area);
        tvArea.setText("Área: " + local.getArea() + " m²");

        rbIgual = findViewById(R.id.rb_igual);
        rbCambio = findViewById(R.id.rb_cambio);
        rbVacio = findViewById(R.id.rb_vacio);

        etNombre = findViewById(R.id.et_nombre_gestion_local);
        etArea = findViewById(R.id.et_area_gestion);
        etObservacion = findViewById(R.id.et_observacion_gestion_local);

        spinnerBien = findViewById(R.id.spinner_tipobienservicio);
        spinnerCategoria = findViewById(R.id.spinner_categoria);
        spinnerSubcategoria = findViewById(R.id.spinner_subcategoria);

        tvSpCategoria = findViewById(R.id.tv_sp_cat);
        tvSpCategoria.setVisibility(View.GONE);
        tvSpSubcategoria = findViewById(R.id.tv_sp_subcat);
        tvSpSubcategoria.setVisibility(View.GONE);
        tvSpBien = findViewById(R.id.tv_sp_bien);
        tvSpBien.setVisibility(View.GONE);

        btnGuardar = findViewById(R.id.button_guardar_local);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarLocal();
            }
        });

        toolbar = findViewById(R.id.toolbar_edit_local);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle(local.getNumero() + " - " + local.getNombre());
    }

    private void configSpinners() {
        spinnerSubcategoria.setEnabled(false);
        spinnerBien.setEnabled(false);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.categoria, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter1);

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                configSpinnerSubcategoria(parentView.getSelectedItem().toString());
            }

            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spinnerSubcategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                configSpinnerBien(parentView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    private void configSpinnerSubcategoria(String selectedItem) {

        ArrayAdapter<CharSequence> adapter = null;

        switch (selectedItem) {
            case "Moda, Muebles y Enseres":
                adapter = ArrayAdapter.createFromResource(this, R.array.subcategoria1, android.R.layout.simple_spinner_item);
                break;
            case "Bienes y Servicios de Conveniencia":
                adapter = ArrayAdapter.createFromResource(this, R.array.subcategoria2, android.R.layout.simple_spinner_item);
                break;
            case "Otras Categorías":
                adapter = ArrayAdapter.createFromResource(this, R.array.subcategoria3, android.R.layout.simple_spinner_item);
                break;
            case "Entretenimiento":
                adapter = ArrayAdapter.createFromResource(this, R.array.subcategoria5, android.R.layout.simple_spinner_item);
                break;
            case "Vehículos y Serviteca":
                adapter = ArrayAdapter.createFromResource(this, R.array.subcategoria10, android.R.layout.simple_spinner_item);
                break;
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubcategoria.setAdapter(adapter);
        spinnerSubcategoria.setEnabled(true);


        /*spinnerSubcategoria.setEnabled(true);
        if (selectedItem.equalsIgnoreCase("Moda, Muebles y Enseres")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.subcategoria1, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSubcategoria.setAdapter(adapter1);
        } else if (selectedItem.equalsIgnoreCase("Bienes y Servicios de Conveniencia")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.subcategoria2, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSubcategoria.setAdapter(adapter1);
        } else if (selectedItem.equalsIgnoreCase("Otras Categorías")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.subcategoria3, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSubcategoria.setAdapter(adapter1);
        } else if (selectedItem.equalsIgnoreCase("Entretenimiento")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.subcategoria5, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSubcategoria.setAdapter(adapter1);
        } else if (selectedItem.equalsIgnoreCase("Vehículos y Serviteca")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.subcategoria10, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSubcategoria.setAdapter(adapter1);
        } else if (selectedItem.equalsIgnoreCase("Disponible")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.subcategoria11, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSubcategoria.setAdapter(adapter1);
        }*/
    }

    private void configSpinnerBien(String selectedItem) {
        spinnerBien.setEnabled(true);
        if (selectedItem.equalsIgnoreCase("Moda")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipobien1_1, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter1);
        } else if (selectedItem.equalsIgnoreCase("Muebles y Enseres")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipobien1_3, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter1);
        } else if (selectedItem.equalsIgnoreCase("Departamental")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipobien1_4, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter1);
        } else if (selectedItem.equalsIgnoreCase("Supermercado")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipobien2_1, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter1);
        } else if (selectedItem.equalsIgnoreCase("Comidas y Bebidas")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipobien2_2, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter1);
        } else if (selectedItem.equalsIgnoreCase("Cuidado Personal")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipobien2_3, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter1);
        } else if (selectedItem.equalsIgnoreCase("Servicios")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipobien2_4, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter1);
        } else if (selectedItem.equalsIgnoreCase("Otras Categorías")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipobien3_5, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter1);
        } else if (selectedItem.equalsIgnoreCase("Entretenimiento")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipobien5_1, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter1);
        } else if (selectedItem.equalsIgnoreCase("Vehículos y Serviteca")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipobien10_1, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter1);
        }
    }

    private void guardarLocal() {
        String nombre = etNombre.getText().toString();
        String observacion = etObservacion.getText().toString();
        String area = etArea.getText().toString();
        String categoria = catNuevo;
        String subcategoria = subcatNuevo;
        String bien = bienNuevo;
        if (!nombre.isEmpty() && !observacion.isEmpty() && !area.isEmpty() && !categoria.isEmpty() && !subcategoria.isEmpty() && !bien.isEmpty()) {
            displayDialog(nombre, area, observacion, categoria, subcategoria, bien);
        } else {
            Toast.makeText(getApplicationContext(), "Completa todos los campos.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void displayDialog(String nombre, String area, String observacion, String categoria, String subcategoria, String tipobien) {

        String info = "Nombre actual:" + local.getNombre() + "\nLocal: " + local.getNumero() + "\nÁrea actual: "
                + local.getArea() + " m²" + "\nCategoría actual: " + local.getCategoria() + "\nSubcategoría actual: " + local.getSubcategoria() + "\nTipo bien/servicio actual: " + local.getTipoBien() +
                "\n----------------\nNombre nuevo: " + nombre + "\nÁrea nueva: " + area + " m²" + "\nCategoría nueva: " + categoria + "\nSubcategoría nueva: " + subcategoria +
                "\nTipo bien/servicio nuevo: " + tipobien + "\n \nObservación: " + observacion;

        System.out.println("_________________\ninfo: " + info);
        GestionDialog paymentDialog = new GestionDialog();
        Bundle args = new Bundle();
        args.putString("info", info);
        paymentDialog.setArguments(args);
        paymentDialog.show(getSupportFragmentManager(), "example dialog");
    }

    private boolean uploadChanges() {
        String nombreNuevo = etNombre.getText().toString();
        String observacion = etObservacion.getText().toString();
        String areaNuevo = etArea.getText().toString();
        //String categoriaNuevo = spinnerCategoria.getSelectedItem().toString();
        //String subcategoriaNuevo = spinnerSubcategoria.getSelectedItem().toString();
        String cc = local.getCentroComercial();

        ConexionHTTP conexionHTTP = new ConexionHTTP();
        conexionHTTP.gestion(local.getIdLocal(), cc, local.getNombre(), nombreNuevo, local.getArea(), areaNuevo, local.getCodigoCategoria(), catNuevo,
                local.getSubcategoria(), subcatNuevo, local.getTipoBien(), bienNuevo, observacion);
        while (!conexionHTTP.isFinishProcess()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        JSONObject respuesta = conexionHTTP.getRespuesta();
        System.out.println(respuesta);
        if (respuesta != null) {
            try {
                String estado = respuesta.getString("estado");
                if (estado.equals("successful")) {
                    Toast.makeText(getApplicationContext(), "Gestión exitosa",
                            Toast.LENGTH_LONG).show();
                    Gestion gestion = new Gestion(local.getKey_id() + "", nombreNuevo, areaNuevo, catNuevo, subcatNuevo, bienNuevo, 1);
                    db.addGestion(gestion);
                    updateOnDB(nombreNuevo, areaNuevo, catNuevo, subcatNuevo, bienNuevo, observacion);
                    return true;
                } else {
                    Toast.makeText(getApplicationContext(), "Gestión fallida.",
                            Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "No se pudo realizar la gestión correctamente.",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Gestion OFFLINE exitosa.\nRecuerda sincronizar cuando tengas acceso a internet.",
                    Toast.LENGTH_LONG).show();
            Gestion gestion = new Gestion(local.getKey_id() + "", nombreNuevo, areaNuevo, catNuevo, subcatNuevo, bienNuevo, 0);
            db.addGestion(gestion);
            updateOnDB(nombreNuevo, areaNuevo, catNuevo, subcatNuevo, bienNuevo, observacion);
            return true;
        }
        return false;
    }

    //TODO
    private void updateOnDB(String nombreNuevo, String areaNuevo, String categoriaNuevo, String subcategoriaNuevo, String bienNuevo, String observacion) {
        db.updateLocal(local,nombreNuevo,areaNuevo,categoriaNuevo,subcategoriaNuevo,bienNuevo);

        //db.actualizarLocal(local.getKey_id() + "", nombreNuevo, areaNuevo, categoriaNuevo, subcategoriaNuevo, bienNuevo, observacion);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb_igual:
                if (checked) {
                    /*etNombre.setText(local.getNombre());
                    etArea.setText(local.getArea());
                    etNombre.setEnabled(false);
                    etArea.setEnabled(false);
                    setSpinnersIgual(local.getCategoria(), local.getSubcategoria(), local.getTipoBien());
                    spinnerBien.setEnabled(false);
                    spinnerCategoria.setEnabled(false);
                    spinnerSubcategoria.setEnabled(false);*/
                    spinnerCategoria.setVisibility(View.GONE);
                    spinnerSubcategoria.setVisibility(View.GONE);
                    spinnerBien.setVisibility(View.GONE);

                    tvSpCategoria.setText(local.getCategoria());
                    tvSpCategoria.setVisibility(View.VISIBLE);
                    tvSpSubcategoria.setText(local.getSubcategoria());
                    tvSpSubcategoria.setVisibility(View.VISIBLE);
                    tvSpBien.setText(local.getTipoBien());
                    tvSpBien.setVisibility(View.VISIBLE);

                    etArea.setText(local.getArea());
                    etArea.setEnabled(false);
                    etNombre.setText(local.getNombre());
                    etNombre.setEnabled(false);

                    catNuevo = tvSpCategoria.getText().toString();
                    subcatNuevo = tvSpSubcategoria.getText().toString();
                    bienNuevo = tvSpBien.getText().toString();
                }
                break;
            case R.id.rb_cambio:
                if (checked) {
                    /*spinnerBien.setEnabled(true);
                    spinnerSubcategoria.setEnabled(true);
                    spinnerCategoria.setEnabled(true);
                    etNombre.setEnabled(true);
                    etNombre.setText("");
                    etArea.setEnabled(true);
                    etArea.setText("");*/
                    spinnerCategoria.setVisibility(View.VISIBLE);
                    spinnerSubcategoria.setVisibility(View.VISIBLE);
                    spinnerBien.setVisibility(View.VISIBLE);

                    tvSpCategoria.setVisibility(View.GONE);
                    tvSpSubcategoria.setVisibility(View.GONE);
                    tvSpBien.setVisibility(View.GONE);

                    etArea.setEnabled(true);
                    etArea.setText("");
                    etNombre.setEnabled(true);
                    etNombre.setText("");
                }
                break;
            case R.id.rb_vacio:
                if (checked) {
                    spinnerCategoria.setVisibility(View.GONE);
                    spinnerSubcategoria.setVisibility(View.GONE);
                    spinnerBien.setVisibility(View.GONE);

                    tvSpCategoria.setText("Disponible");
                    tvSpCategoria.setVisibility(View.VISIBLE);
                    tvSpSubcategoria.setText("Disponible");
                    tvSpSubcategoria.setVisibility(View.VISIBLE);
                    tvSpBien.setText("Disponible");
                    tvSpBien.setVisibility(View.VISIBLE);

                    etArea.setText(local.getArea());
                    etArea.setEnabled(false);
                    etNombre.setText("Disponible");
                    etNombre.setEnabled(false);

                    catNuevo = tvSpCategoria.getText().toString();
                    subcatNuevo = tvSpSubcategoria.getText().toString();
                    bienNuevo = tvSpBien.getText().toString();

                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void applyDialog() {
        uploadChanges();
    }
}
