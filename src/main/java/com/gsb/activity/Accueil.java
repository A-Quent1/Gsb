package com.gsb.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gsb.R;

public class Accueil extends AppCompatActivity {

    private Button bAjout;
    private Button bListe;
    private Button bMaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        bAjout = findViewById(R.id.b_accueil_ajout);
        bListe = findViewById(R.id.b_accueil_liste);
        bMaj = findViewById(R.id.b_accueil_maj);

        bAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueil.this, AjoutEchantillon.class);
                startActivity(intent);
            }
        });

        bListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueil.this, ListeEchantillon.class);
                startActivity(intent);
            }
        });

        bMaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accueil.this, MajEchantillon.class);
                startActivity(intent);
            }
        });

    }
}