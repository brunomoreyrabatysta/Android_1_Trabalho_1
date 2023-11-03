package com.brunobatista.trabalhoandroid_1_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;

public class MainActivity extends AppCompatActivity {

    private Button btnCalcular;
    private EditText edtPeso, edtAltura, edtNome;
    private Switch sshModoNoturno;
    private ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalcular = findViewById(R.id.btnCalcular);
        edtNome = findViewById(R.id.edtNome);
        edtPeso = findViewById(R.id.edtPeso);
        edtAltura = findViewById(R.id.edtAltura);
        sshModoNoturno = findViewById(R.id.sshModoNoturno);
        imgLogo = findViewById(R.id.imgLogo);

        edtPeso.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edtAltura.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Validar()) {
                    Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);

                    intent.putExtra(getResources().getString(R.string.PARAM_NOME), edtNome.getText().toString().trim());
                    intent.putExtra(getResources().getString(R.string.PARAM_PESO), edtPeso.getText().toString().trim());
                    intent.putExtra(getResources().getString(R.string.PARAM_ALTURA) , edtAltura.getText().toString().trim());

                    startActivity(intent);
                }
            }
        });

        sshModoNoturno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sshModoNoturno.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }

    private boolean Validar() {
        String sNome = edtNome.getText().toString().trim();
        String sPeso = edtPeso.getText().toString().trim();
        String sAltura = edtAltura.getText().toString().trim();

        if (sNome == null || sNome.length() == 0) {
            Toast.makeText(MainActivity.this, "O nome deve ser preenchido!", Toast.LENGTH_LONG).show();
            return false;
        } else if (sNome.length() <  5) {
            Toast.makeText(MainActivity.this, "O nome deve conter no mínimo 5 (cinco) caracteres!", Toast.LENGTH_LONG).show();
            return false;
        }

        try {
            if (Float.parseFloat(sPeso) <= 0 ) {
                Toast.makeText(MainActivity.this, "O peso deve ser maior do que 0 (zero)!", Toast.LENGTH_LONG).show();
                return false;
            }
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "O peso deve número válido!", Toast.LENGTH_LONG).show();
            return false;
        }

        try {
            if (Float.parseFloat(sAltura) <= 0 ) {
                Toast.makeText(MainActivity.this, "A altura deve ser maior do que 0 (zero)!", Toast.LENGTH_LONG).show();
                return false;
            }
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "A altura deve número válido!", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}