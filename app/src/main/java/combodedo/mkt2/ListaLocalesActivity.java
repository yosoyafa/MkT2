package combodedo.mkt2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import model.Local;
import utils.DataBase;
import utils.LogicDataBase;


public class ListaLocalesActivity extends AppCompatActivity {

    private ArrayList<Local> locales;
    private Toolbar toolbar;
    private LogicDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_locales);
        db = MainActivity.getDb();
        toolbar = findViewById(R.id.toolbar_lista_locales);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        configLocales();
        getSupportActionBar().setTitle("Locales "+getIntent().getStringExtra("cc"));
        db.getTableAsString(DataBase.TABLE_LOCALES);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configLocales();
        // The activity has become visible (it is now "resumed").
    }


    private void configLocales() {
        String cc = getIntent().getStringExtra("cc");
        locales = db.selectLocales(cc);
        System.out.println("----------------------------------------------\nCC: "+cc);
        if(locales != null && !locales.isEmpty()){
            addCards(locales);
        }else{
            if(locales.isEmpty()){
                System.out.println("ESTA VACIO");
            }else{
                System.out.println("ES NULL");
            }
        }
    }

    private void addCards(ArrayList<Local> locales) {
        if (!locales.isEmpty()) {
            ListView listVisitas = findViewById(R.id.lv_lista_locales);
            ListAdapter adapter = new CardsAdapterLocales(this, locales);
            listVisitas.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_ccs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            case R.id.search_locales:
                displayDearchDialog();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void displayDearchDialog() {

    }

}
