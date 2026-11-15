package com.example.aulaactivamacg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_formulario);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (vista, insets) -> {
            Insets espacio = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime()
            );
            vista.setPadding(espacio.left, espacio.top, espacio.right, espacio.bottom);
            return insets;
        });

        EditText campoNombre = findViewById(R.id.etNombre);
        EditText campoCorreo = findViewById(R.id.etCorreo);
        RadioGroup opcionesTaller = findViewById(R.id.rgTaller);

        findViewById(R.id.btnEnviarInscripcion).setOnClickListener(vista -> {
            String nombre = campoNombre.getText().toString().trim();
            String correo = campoCorreo.getText().toString().trim();
            int tallerSeleccionado = opcionesTaller.getCheckedRadioButtonId();

            if (nombre.isEmpty()) {
                campoNombre.setError("Escribe tu nombre");
                campoNombre.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                campoCorreo.setError("Escribe un correo válido");
                campoCorreo.requestFocus();
                return;
            }

            if (tallerSeleccionado == View.NO_ID) {
                Toast.makeText(this, "Elige un taller", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton opcion = opcionesTaller.findViewById(tallerSeleccionado);
            String taller = opcion.getText().toString();

            Intent intent = new Intent(this, ConfirmacionActivity.class);
            intent.putExtra(ConfirmacionActivity.EXTRA_NOMBRE, nombre);
            intent.putExtra(ConfirmacionActivity.EXTRA_CORREO, correo);
            intent.putExtra(ConfirmacionActivity.EXTRA_TALLER, taller);
            startActivity(intent);
        });
    }
}
