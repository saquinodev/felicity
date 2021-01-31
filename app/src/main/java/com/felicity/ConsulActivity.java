package com.felicity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConsulActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText msjBuscar;
    private Button envConsul;
    private Button envCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consul);

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void envConsul(View view) {
    }

    public void envCancel(View view) {
        mDatabase.child("Reservas").child("Fechas").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ConsulActivity.this, "La reserva a sido cancelada", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ConsulActivity.this, "Reserva no encontrada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void msjBuscar(View view) {
    }
}