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

import model.Gestion;
import model.Local;

public class EditLocalActivity extends AppCompatActivity {

    private Local local;
    private Toolbar toolbar;
    private TextView tvNombre, tvCategoria, tvSubcategoria, tvBien, tvNumLocal, tvArea;
    private RadioButton rbIgual, rbCambio, rbVacio;
    private EditText etNombre, etArea, etObservacion;
    private Spinner spinnerCategoria, spinnerSubcategoria, spinnerBien;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_local);
        setLocal();
        setWidgets();
        configSpinners();
    }

    private void setLocal() {
        String[] loc = getIntent().getStringExtra("local").split(",");
        local = new Local(loc[0], loc[1], loc[2], loc[3], loc[4], loc[5], loc[6], loc[7], loc[8]);
        System.out.println(local.toStringRaw());
    }

    private void setWidgets() {
        tvNombre = findViewById(R.id.tv_edit_nombre);
        tvNombre.setText(local.getNombre());
        tvNombre.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        tvNombre.setTypeface(null, Typeface.BOLD);

        tvCategoria = findViewById(R.id.tv_edit_categoria);
        tvCategoria.setText("Categoría: "+local.getCategoria());

        tvSubcategoria = findViewById(R.id.tv_edit_subcategoria);
        tvSubcategoria.setText("Subcategoría: "+local.getSubcategoria());

        tvBien = findViewById(R.id.tv_edit_tipo_bien_servicio);
        tvBien.setText("Tipo de bien/servicio: "+local.getTipoBien());

        tvNumLocal = findViewById(R.id.tv_edit_numero_local);
        tvNumLocal.setText("Local: "+local.getNumero());

        tvArea = findViewById(R.id.tv_edit_area);
        tvArea.setText("Área: "+local.getArea()+" m²");

        rbIgual = findViewById(R.id.rb_igual);
        rbCambio = findViewById(R.id.rb_cambio);
        rbVacio = findViewById(R.id.rb_vacio);

        etNombre = findViewById(R.id.et_nombre_gestion_local);
        etArea = findViewById(R.id.et_area_gestion);
        etObservacion = findViewById(R.id.et_observacion_gestion_local);

        spinnerBien = findViewById(R.id.spinner_tipobienservicio);
        spinnerCategoria = findViewById(R.id.spinner_categoria);
        spinnerSubcategoria = findViewById(R.id.spinner_subcategoria);

        btnGuardar = findViewById(R.id.button_guardar_local);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarLocal();
            }
        });

        toolbar = findViewById(R.id.toolbar_edit_local);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle(local.getNumero()+" - "+local.getNombre());
    }

    private void configSpinners() {
        spinnerSubcategoria.setEnabled(false);
        spinnerBien.setEnabled(false);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.categoria, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter1);

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                configSpinnerSubcategoria(parentView.getSelectedItem().toString());
            }

            @Override
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
        } else if (selectedItem.equalsIgnoreCase("Disponible")) {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipobien11_1, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter1);
        }
    }

    private void configSpinnerSubcategoria(String selectedItem) {
        spinnerSubcategoria.setEnabled(true);
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
        }
    }

    private void guardarLocal() {
        String nombre = etNombre.getText().toString();
        String observacion = etObservacion.getText().toString();
        String area = etArea.getText().toString();

        if(!nombre.isEmpty() && !observacion.isEmpty() && !area.isEmpty()){
            Gestion gestion = new Gestion("","","","","","", 1);
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_igual:
                if (checked) {
                    etNombre.setText(local.getNombre());
                    etArea.setText(local.getArea());
                    setSpinnersIgual(local.getCategoria(), local.getSubcategoria(), local.getTipoBien());

                    etNombre.setEnabled(false);
                    etArea.setEnabled(false);

                    spinnerBien.setEnabled(false);
                }
                break;
            case R.id.rb_cambio:
                if (checked) {
                    etNombre.setEnabled(true);
                    etNombre.setText("");
                    etArea.setEnabled(true);
                    etArea.setText("");
                }
                break;
            case R.id.rb_vacio:
                if (checked) {
                    etNombre.setText("Disponible");
                    etArea.setText(local.getArea());
                    setSpinnersDisponible();
                    etNombre.setEnabled(false);
                    etArea.setEnabled(false);
                }
                break;
        }
    }

    private void setSpinnersDisponible() {
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.categoria, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter1);
        spinnerCategoria.setSelection(adapter1.getPosition("Disponible"));

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.subcategoria11, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubcategoria.setAdapter(adapter2);
        spinnerSubcategoria.setSelection(adapter2.getPosition("Disponible"));

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.tipobien11_1, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBien.setAdapter(adapter3);
        spinnerBien.setSelection(adapter3.getPosition("Disponible"));
    }

    private void setSpinnersIgual(String categoria, String subcategoria, String tipoBien) {

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.categoria, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapter1);
        spinnerCategoria.setSelection(adapter1.getPosition(categoria));

        if (subcategoria.equalsIgnoreCase("Moda") || subcategoria.equalsIgnoreCase("Muebles y Enseres") || subcategoria.equalsIgnoreCase("Departamental")) {

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.subcategoria1, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSubcategoria.setAdapter(adapter2);
            spinnerSubcategoria.setSelection(adapter2.getPosition(subcategoria));

            if (subcategoria.equalsIgnoreCase("Moda")) {

                ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.tipobien1_1, android.R.layout.simple_spinner_item);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerBien.setAdapter(adapter3);
                spinnerBien.setSelection(adapter3.getPosition(tipoBien));

            } else if (subcategoria.equalsIgnoreCase("Muebles y Enseres")) {

                ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.tipobien1_3, android.R.layout.simple_spinner_item);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerBien.setAdapter(adapter3);
                spinnerBien.setSelection(adapter3.getPosition(tipoBien));

            } else if (subcategoria.equalsIgnoreCase("Departamental")) {

                ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.tipobien1_4, android.R.layout.simple_spinner_item);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerBien.setAdapter(adapter3);
                spinnerBien.setSelection(adapter3.getPosition(tipoBien));

            }

        } else if (subcategoria.equalsIgnoreCase("Supermercado") || subcategoria.equalsIgnoreCase("Comidas y Bebidas") || subcategoria.equalsIgnoreCase("Cuidado Personal") || subcategoria.equalsIgnoreCase("Servicios")) {

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.subcategoria2, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSubcategoria.setAdapter(adapter2);
            spinnerSubcategoria.setSelection(adapter2.getPosition(subcategoria));

            if (subcategoria.equalsIgnoreCase("Supermercado")) {

                ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.tipobien2_1, android.R.layout.simple_spinner_item);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerBien.setAdapter(adapter3);
                spinnerBien.setSelection(adapter3.getPosition(tipoBien));

            } else if (subcategoria.equalsIgnoreCase("Comidas y Bebidas")) {

                ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.tipobien2_2, android.R.layout.simple_spinner_item);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerBien.setAdapter(adapter3);
                spinnerBien.setSelection(adapter3.getPosition(tipoBien));

            } else if (subcategoria.equalsIgnoreCase("Cuidado Personal")) {

                ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.tipobien2_3, android.R.layout.simple_spinner_item);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerBien.setAdapter(adapter3);
                spinnerBien.setSelection(adapter3.getPosition(tipoBien));

            } else if (subcategoria.equalsIgnoreCase("Servicios")) {

                ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.tipobien2_4, android.R.layout.simple_spinner_item);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerBien.setAdapter(adapter3);
                spinnerBien.setSelection(adapter3.getPosition(tipoBien));

            }

        } else if (subcategoria.equalsIgnoreCase("Otras Categorías")) {

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.subcategoria3, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSubcategoria.setAdapter(adapter2);
            spinnerSubcategoria.setSelection(adapter2.getPosition(subcategoria));

            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.tipobien3_5, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter3);
            spinnerBien.setSelection(adapter3.getPosition(tipoBien));

        } else if (subcategoria.equalsIgnoreCase("Entretenimiento")) {

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.subcategoria5, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSubcategoria.setAdapter(adapter2);
            spinnerSubcategoria.setSelection(adapter2.getPosition(subcategoria));

            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.tipobien5_1, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter3);
            spinnerBien.setSelection(adapter3.getPosition(tipoBien));

        } else if (subcategoria.equalsIgnoreCase("Vehículos y Serviteca")) {

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.subcategoria10, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSubcategoria.setAdapter(adapter2);
            spinnerSubcategoria.setSelection(adapter2.getPosition(subcategoria));

            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.tipobien10_1, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter3);
            spinnerBien.setSelection(adapter3.getPosition(tipoBien));

        } else if (subcategoria.equalsIgnoreCase("Disponible")) {

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.subcategoria11, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSubcategoria.setAdapter(adapter2);
            spinnerSubcategoria.setSelection(adapter2.getPosition(subcategoria));

            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.tipobien11_1, android.R.layout.simple_spinner_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBien.setAdapter(adapter3);
            spinnerBien.setSelection(adapter3.getPosition(tipoBien));
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
}
