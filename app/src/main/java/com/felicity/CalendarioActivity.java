package com.felicity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CalendarioActivity extends AppCompatActivity {

    TextView tv;
    TextView tv2;
    TextView tv3;
    TextView cli;

    private Button envDatos;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        tv = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        cli = findViewById(R.id.textView4);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        String mFecha = tv.getText().toString();

    }
    //Enviar datos a la BD
    public void envDatos(View view) {
        String mFecha = tv.getText().toString();
        String mHrInicio = tv2.getText().toString();
        String mHrFin = tv3.getText().toString();

        Map <String, Object> mReserva = new HashMap<>();
        mReserva.put("Fecha", mFecha);
        mReserva.put("hr_inicio", mHrInicio);
        mReserva.put("hr_Fin", mHrFin);

        mDatabase.child("Reserva").child("Fechas").push().setValue(mReserva);
    }

    //Calendario
    public void abrirCalendar(View view) {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(CalendarioActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + (month+1) + "/" + year;
                tv.setText(fecha);
            }
        },anio, mes, dia);
        dpd.show();
    }

    //Abrir la seleccion de horarios
    public void abrirHraInicio(View view) {
        Calendar cal = Calendar.getInstance();
        int hora = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int hora2 = cal.get(Calendar.HOUR_OF_DAY);
        int min2 = cal.get(Calendar.MINUTE);


        //Hora fin
        TimePickerDialog tmd2 = new TimePickerDialog(CalendarioActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int i, int i1) {
                tv3.setText(i + ":" + i1);
            }
        },hora2,min2,false);
        tmd2.show();

        //Hora Inicio
        TimePickerDialog tmd = new TimePickerDialog(CalendarioActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int i, int i1) {
                tv2.setText(i + ":" + i1);
            }
        },hora,min,false);
        tmd.show();

    }
}