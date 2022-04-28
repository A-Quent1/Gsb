package com.gsb.activi5ty;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.gsb.R;
import com.gsb.adapter.BdAdapter;

public class ListeEchantillon extends AppCompatActivity {

    private ListView listeEchantillon;
    private BdAdapter bd;

    private Button bQuitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_echantillon);

        listeEchantillon = (ListView) findViewById(R.id.lv_liste_liste);
        bd = new BdAdapter(this);
        bd.open();

        Cursor leCurseur = bd.getData();
        Toast.makeText(getApplicationContext(),"Il y a " + String.valueOf(leCurseur.getCount())
                + " echantillons dans la BD", Toast.LENGTH_LONG).show();

        // colonnes à afficher
        String[] colNoms = new String[] {BdAdapter.COL_CODE, BdAdapter.COL_LIB, BdAdapter.COL_STOCK};
        // champs dans lesquelles afficher les colonnes

        int[] colNumeros = new int[] {R.id.tv_echantillon_id,
                R.id.tv_echantllion_libelle, R.id.tv_echantillon_quantite};
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this,
                R.layout.layout_echantillon,leCurseur,colNoms,colNumeros);

        // Assign adapter to ListView

        listeEchantillon.setAdapter(dataAdapter);
        bd.close();


        bQuitter = findViewById(R.id.b_liste_quitter);
        bQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}