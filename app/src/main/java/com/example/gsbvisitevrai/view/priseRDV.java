package com.example.gsbvisitevrai.view;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import com.desai.vatsal.mydynamictoast.MyDynamicToast;
import com.example.gsbvisitevrai.R;
import com.example.gsbvisitevrai.controller.RendezVousController;
import com.example.gsbvisitevrai.model.Praticien;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class priseRDV extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    TextView textView;
    Button button;
    int day, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;
    LocalDateTime myDateTime;

    /**
     * Appeler à l'ouverture de la page, affiche les praticiens pour la sélection et afficher un sélecteur de date/heure
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prise_r_d_v);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.btnPick);
        //permet la sélection d'une date et d'une heure pour un rendez-vous
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(priseRDV.this, priseRDV.this,year, month,day);
                datePickerDialog.show();
            }
        });
        Intent intent = getIntent();
        ArrayList<Praticien> lesPraticiens =  intent.getParcelableArrayListExtra("lesPraticiens");
        ArrayList<String> numerosPraticiens = new ArrayList<String>();
        lesPraticiens.forEach(item -> {
            numerosPraticiens.add(String.valueOf(item.getNumero()));
        });
        Spinner spinner = (Spinner) findViewById(R.id.spinnerPraticiens);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, numerosPraticiens);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ecouteRetour();
        ecouteSauvegarder();
    }

    /**
     * Appeler lorsqu'une date est sélectionnée
     * @param view
     * @param year
     * @param month
     * @param dayOfMonth
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myYear = year;
        myday = dayOfMonth;
        myMonth = month + 1;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(priseRDV.this, priseRDV.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    /**
     * Appeler lorsqu'une heure est sélectionnée
     * @param view
     * @param hourOfDay
     * @param minute
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        myHour = hourOfDay;
        myMinute = minute;
        myDateTime = LocalDateTime.of(myYear, myMonth, myday, myHour, myMinute);
        DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        String myFormattedDate = myDateTime.format(myDateFormatter);
        TextView txtDateHeure = (TextView)findViewById(R.id.txtDateHeure);
        txtDateHeure.setText(myFormattedDate);
        //  + ":" + myMinute
        // textView / textView
/*        textView.setText("Year: " + myYear + "\n" +
                        "Month: " + myMonth + "\n" +
                        "Day: " + myday + "\n" +
                        "Hour: " + myHour + "\n" +
                        "Minute: " + myMinute);

 */
    }


    /**
     * Gère le retour arrière
     */
    private void ecouteRetour() {
        ((ImageButton) findViewById(R.id.btnRetourdePriseRDV)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * Sauvegarde le rendez-vous, confirme la sauvegarde et retourne à l'accueil
     */
    private void ecouteSauvegarder() {
        ((Button) findViewById(R.id.btnSauvRDV)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RendezVousController rendezVousController = RendezVousController.getInstance(getBaseContext());
                DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String myFormattedDate = myDateTime.format(myDateFormatter);
                DateTimeFormatter myTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                String myFormattedTime = myDateTime.format(myTimeFormatter);
                Spinner spinner = (Spinner) findViewById(R.id.spinnerPraticiens);
                String text = spinner.getSelectedItem().toString();
                rendezVousController.ajouterRendezVous(myFormattedDate, myFormattedTime, text);
                MyDynamicToast.successMessage(priseRDV.this, "Rendez-vous ajouté");
                onBackPressed();
            }
        });
    }
}