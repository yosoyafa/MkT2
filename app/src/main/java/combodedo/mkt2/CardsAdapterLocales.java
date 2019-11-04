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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import model.Local;

public class CardsAdapterLocales extends BaseAdapter {

    private ArrayList<Local> locales;
    private Activity context;

    public CardsAdapterLocales(){

    }

    public CardsAdapterLocales(Activity context, ArrayList<Local> locales) {
        this.context = context;
        this.locales = locales;
    }

    @Override
    public int getCount() {
        return locales.size();
    }

    @Override
    public Object getItem(int i) {
        return locales.get(i);
    }

    @Override
    public long getItemId(int i) {
        return locales.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.card_local,viewGroup,false);

        final Local local = locales.get(i);

        CardView card = view.findViewById(R.id.cardview_local);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread t = new Thread(){
                    public void run(){
                        goToEditLocalActivity(local);
                    }
                };
                t.start();
            }
        });

        TextView num = view.findViewById(R.id.tv_num_local);
        num.setText(local.getNumero());
        num.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        num.setTypeface(null, Typeface.BOLD);

        TextView nombre = view.findViewById(R.id.tv_nombre_local);
        nombre.setText(local.getNombre());
        nombre.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        nombre.setTypeface(null, Typeface.BOLD);

        ImageView check = view.findViewById(R.id.check);

        System.out.println("_______________________________\nGestionado:\n"+local.getGestionado());

        if(local.getGestionado().equals("0")){
            check.setVisibility(View.GONE);
        }else if(local.getGestionado().equals("1")){
            check.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private void goToEditLocalActivity(Local local) {
        Intent gestionActivity = new Intent(context, EditLocalActivity.class);
        System.out.println("---------------------------\nLocal que pasa a editar:\n"+local.toStringRaw());
        gestionActivity.putExtra("local", local.toStringRaw());
        context.startActivity(gestionActivity);

    }
}