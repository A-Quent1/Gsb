package com.gsb.activity;

import android.content.Intent;
import com.gsb.adapter.BdAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gsb.R;
import com.gsb.modele.Echantillon;

public class AjoutEchantillon extends AppCompatActivity {

    private EditText et_ajout_code;
    private EditText et_ajout_libelle;
    private EditText et_ajout_stock;

    private Button bAjouter;
    private Button bQuitter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_echantillon);

        et_ajout_code = findViewById(R.id.et_ajout_code);
        et_ajout_libelle = findViewById(R.id.et_ajout_libelle);
        et_ajout_stock = findViewById(R.id.et_ajout_stock);

        bAjouter = findViewById(R.id.b_ajout_ajouter);
        bQuitter = findViewById(R.id.b_ajout_quitter);

        bAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterEchantillon();
            }
        });

        bQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
        public void ajouterEchantillon() {
            BdAdapter db = new BdAdapter(this);
            if (et_ajout_code.getText().toString().matches("") ||
                    et_ajout_libelle.getText().toString().matches("") ||
                    et_ajout_stock.getText().toString().matches("")) {
                Toast.makeText(this, "Remplir l'ensemble des champs", Toast.LENGTH_LONG).show();
                return;
        }
        int stock = Integer.parseInt(et_ajout_stock.getText().toString());
            if (stock <= 0) {
                Toast.makeText(this, "Quantité n'est pas au dessus de 0", Toast.LENGTH_LONG).show();
                return;
            }

            db.open();
            db.insererEchantillon(new Echantillon(et_ajout_code.getText().toString(),
            et_ajout_libelle.getText().toString(),et_ajout_stock.getText().toString()
        ));

            Toast.makeText(this, "Ajout d'un nouvel échantillon", Toast.LENGTH_LONG).show();
            db.close();
    }
}
