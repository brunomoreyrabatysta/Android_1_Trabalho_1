package com.brunobatista.trabalhoandroid_1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    private TextView tvtNome, tvtPeso, tvtAltura, tvtIMC, tvtObservacao;
    private LinearLayout lltObservacao;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tvtNome = findViewById(R.id.tvtNome);
        tvtPeso = findViewById(R.id.tvtPeso);
        tvtAltura = findViewById(R.id.tvtAltura);
        tvtIMC = findViewById(R.id.tvtIMC);
        tvtObservacao = findViewById(R.id.tvtObservacao);
        lltObservacao = findViewById(R.id.lltObservacao);

        Intent intent = getIntent();

        tvtNome.setText(intent.getStringExtra(getResources().getString(R.string.PARAM_NOME)));
        Float fPeso = Float.parseFloat(intent.getStringExtra(getResources().getString(R.string.PARAM_PESO)));
        Float fAltura = Float.parseFloat(intent.getStringExtra(getResources().getString(R.string.PARAM_ALTURA)));

        Float fIMC = fPeso / (fAltura * fAltura);
        String observacao = "";
        int corObservacao = Color.parseColor("#FF000000");

        if (fIMC < 18.5) {
            observacao = "Baixo peso";
            corObservacao = Color.parseColor(getResources().getString(R.color.IMC_BaixoPeso));
        } else if (fIMC >= 18.5 && fIMC <= 24.99) {
            observacao = "Normal";
            corObservacao = Color.parseColor(getResources().getString(R.color.IMC_Normal));
        } else if (fIMC >= 25 && fIMC <= 29.99) {
            observacao = "Sobrepeso";
            corObservacao = Color.parseColor(getResources().getString(R.color.IMC_Sobrepeso));
        } else if (fIMC >= 30) {
            observacao = "Obesidade";
            corObservacao = Color.parseColor(getResources().getString(R.color.IMC_Obesidade));
        }

        tvtIMC.setText(String.format("%.2f",fIMC) + " Kg/m²");
        tvtPeso.setText(String.format("%.2f",fPeso) + " Kg");
        tvtAltura.setText(String.format("%.2f",fAltura) + " m");
        tvtObservacao.setText(observacao);

        lltObservacao.setBackgroundColor(corObservacao);
    }
}