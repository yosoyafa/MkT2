package combodedo.mkt2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import model.CentroComercial;

public class CardsAdapterCCs extends BaseAdapter {

    private ArrayList<String> ccs;
    private Activity context;

    public CardsAdapterCCs(){

    }

    public CardsAdapterCCs(Activity context, ArrayList<String> ccs) {
        this.context = context;
        this.ccs = ccs;
    }

    @Override
    public int getCount() {
        return ccs.size();
    }

    @Override
    public Object getItem(int i) {
        return ccs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ccs.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.card_cc,viewGroup,false);

        final String centroComercial = ccs.get(i);

        CardView card = view.findViewById(R.id.cardview_cc);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread t = new Thread(){
                    public void run(){
                        goToListaLocalesActivity(centroComercial);
                    }
                };
                t.start();
            }
        });

        TextView nombre = view.findViewById(R.id.tv_nombre_cc);
        nombre.setText(centroComercial);
        nombre.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        nombre.setTypeface(null, Typeface.BOLD);

//        TextView direccion = view.findViewById(R.id.tv_direccion_cc);
//        direccion.setText(centroComercial.getDireccion());
//
//        TextView web = view.findViewById(R.id.tv_web_cc);
//        web.setText(centroComercial.getPaginaWeb());

        return view;
    }

    private void goToListaLocalesActivity(String cc) {
        Intent gestionActivity = new Intent(context, ListaLocalesActivity.class);
        gestionActivity.putExtra("cc", cc);
        context.startActivity(gestionActivity);
    }
}