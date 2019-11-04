package combodedo.mkt2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;



public class ListaCCsActivity extends AppCompatActivity {

    private ArrayList<String> centrosComerciales;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ccs);
        toolbar = findViewById(R.id.toolbar_lista_ccs);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        configVisitas();
        getSupportActionBar().setTitle("Centros Comerciales");
    }

    private void configVisitas() {
        int numVisitas = getIntent().getIntExtra("num_ccs", 0);
        centrosComerciales = new ArrayList<String>();
        for (int a = 0; a < numVisitas; a++) {
            String ccStr = getIntent().getStringExtra("cc" + a);
            centrosComerciales.add(ccStr);
        }
        addCards(centrosComerciales);
    }

    private void addCards(ArrayList<String> ccs) {
        if (!ccs.isEmpty()) {
            ListView listVisitas = findViewById(R.id.lv_lista_ccs);
            ListAdapter adapter = new CardsAdapterCCs(this, ccs);
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
        }
        return super.onOptionsItemSelected(item);
    }

}
