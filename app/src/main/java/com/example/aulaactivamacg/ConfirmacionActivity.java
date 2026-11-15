package com.example.aulaactivamacg;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmacionActivity extends AppCompatActivity {

    static final String EXTRA_NOMBRE = "nombre";
    static final String EXTRA_CORREO = "correo";
    static final String EXTRA_TALLER = "taller";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_confirmacion);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (vista, insets) -> {
            Insets barras = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            vista.setPadding(barras.left, barras.top, barras.right, barras.bottom);
            return insets;
        });

        String nombre = getIntent().getStringExtra(EXTRA_NOMBRE);
        String correo = getIntent().getStringExtra(EXTRA_CORREO);
        String taller = getIntent().getStringExtra(EXTRA_TALLER);

        TextView datos = findViewById(R.id.tvDatosInscripcion);
        datos.setText("Nombre: " + nombre
                + "\nCorreo: " + correo
                + "\nTaller: " + taller);

        findViewById(R.id.btnSalir).setOnClickListener(vista -> finishAffinity());
    }
}
