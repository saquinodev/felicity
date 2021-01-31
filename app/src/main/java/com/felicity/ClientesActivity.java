package com.felicity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.felicity.Adaptadores.ListViewPersonasAdapter;
import com.felicity.Models.Persona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ClientesActivity extends AppCompatActivity {

    private ArrayList<Persona> listPersonas = new ArrayList<Persona>();
    ArrayAdapter<Persona>arrayAdapterPersona;
    ListViewPersonasAdapter listViewPersonasAdapter;
    LinearLayout linearLayoutEditar;
    ListView listViewPersonas;

    EditText inputNombre, inputApellido, inputDNI, inputDireccion, inputTelefono;
    Button btnCancelar;

    Persona personaSeleccionada;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        inputNombre = findViewById(R.id.inputNombre);
        inputApellido = findViewById(R.id.inputApellido);
        inputDNI = findViewById(R.id.inputDNI);
        inputDireccion = findViewById(R.id.inputDireccion);
        inputTelefono = findViewById(R.id.inputTelefono);
        btnCancelar = findViewById(R.id.btn_cancelar);
        listViewPersonas = findViewById(R.id.listViewPersonas);
        linearLayoutEditar = findViewById(R.id.linearLayoutEditar);

        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                personaSeleccionada = (Persona) parent.getItemAtPosition(position);
                inputNombre.setText(personaSeleccionada.getNombres());
                inputApellido.setText(personaSeleccionada.getApellido());
                inputDNI.setText(personaSeleccionada.getDNI());
                inputDireccion.setText(personaSeleccionada.getDireccion());
                //Hacer visible el linerLayout
                linearLayoutEditar.setVisibility(View.VISIBLE);
            }
        });
        //Cancelamos y ocultamos la listViewPersonas
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutEditar.setVisibility(View.GONE);
                personaSeleccionada = null;
            }
        });

        inicializarFirebase();
        listarPersonas();
    }
    private void  inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    //Llenamos la lista de personas en el LinearLayout del Activity
    private void listarPersonas(){
        databaseReference.child("Personas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPersonas.clear();
                for(DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Persona p = objSnaptshot.getValue(Persona.class);
                    listPersonas.add(p);
                }
                //iniciar nuestro adaptador
                arrayAdapterPersona = new ArrayAdapter<Persona>(
                        ClientesActivity.this,
                        android.R.layout.simple_list_item_1,
                        listPersonas
                );
                listViewPersonas.setAdapter(arrayAdapterPersona);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.crud_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String nombres = inputNombre.getText().toString();
        String apellido = inputApellido.getText().toString();
        String DNI = inputDNI.getText().toString();
        String direccion = inputDireccion.getText().toString();
        String telefono = inputTelefono.getText().toString();
        switch (item.getItemId()){
            case  R.id.menu_agregar:
                insertar();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void insertar(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(
                ClientesActivity.this
        );
        View mView = getLayoutInflater().inflate(R.layout.insertar, null);
        Button btnInsertar = (Button) mView.findViewById(R.id.btnIsertar);
        final EditText mInputNombres = (EditText) mView.findViewById(R.id.inputNombre);
        final EditText mInputApellido = (EditText) mView.findViewById(R.id.inputApellido);
        final EditText mInputDNI = (EditText) mView.findViewById(R.id.inputDNI);
        final EditText mInputDireccion = (EditText) mView.findViewById(R.id.inputDireccion);
        final EditText mInputTelefono = (EditText) mView.findViewById(R.id.inputTelefono);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        //btnInsertar
    }
}



