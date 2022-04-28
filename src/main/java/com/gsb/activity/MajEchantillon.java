package com.gsb.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gsb.R;
import com.gsb.adapter.BdAdapter;
import com.gsb.modele.Echantillon;

public class MajEchantillon extends AppCompatActivity {

    private EditText et_maj_code;
    private EditText et_maj_quantite;

    private Button bAjouter;
    private Button bSupprimer;
    private Button bQuitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maj_echantillon);

        bAjouter = findViewById(R.id.b_maj_ajouter);
        bSupprimer = findViewById(R.id.b_maj_supprimer);
        bQuitter = findViewById(R.id.b_maj_quitter);

        et_maj_code = findViewById(R.id.et_maj_code);
        et_maj_quantite = findViewById(R.id.et_maj_quantite);

        bAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                majAjouterQuantite();
            }
        });

        bSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                majSupprimerQuantite();
            }
        });

        bQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void majAjouterQuantite() {
        BdAdapter bd = new BdAdapter(this);
        bd.open();

        Echantillon echant = bd.getEchantillonWithLib(et_maj_code.getText().toString());

        if (et_maj_quantite.getText().toString().matches("")) {
            Toast.makeText(this, "Remplir l'ensemble des champs", Toast.LENGTH_LONG).show();
            return;
        }

        int quantite = Integer.parseInt(echant.getQuantiteStock());
        int qteAjouter = Integer.parseInt(et_maj_quantite.getText().toString());
        int nvQte = quantite + qteAjouter;

        if (qteAjouter <= 0) {
            Toast.makeText(this, "Quantité n'est pas au dessus de 0", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "Stock mis à jour", Toast.LENGTH_LONG).show();
        echant.setQuantiteStock(String.valueOf(nvQte));
        bd.updateEchantillon(echant.getCode(),echant);
    }

    public void majSupprimerQuantite() {
        BdAdapter bd = new BdAdapter(this);
        bd.open();

        Echantillon echant = bd.getEchantillonWithLib(et_maj_code.getText().toString());

        if (et_maj_quantite.getText().toString().matches("")) {
            Toast.makeText(this, "Remplir l'ensemble des champs", Toast.LENGTH_LONG).show();
            return;
        }

        int quantite = Integer.parseInt(echant.getQuantiteStock());
        int qteAjouter = Integer.parseInt(et_maj_quantite.getText().toString());
        int nvQte = quantite - qteAjouter;

        if (nvQte <= 0) {
            Toast.makeText(this, "Stock insuffisant", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "Stock mis à jour", Toast.LENGTH_LONG).show();
        echant.setQuantiteStock(String.valueOf(nvQte));
        bd.updateEchantillon(echant.getCode(),echant);
    }
}