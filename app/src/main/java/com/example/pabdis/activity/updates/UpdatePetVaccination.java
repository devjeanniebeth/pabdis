package com.example.pabdis.activity.updates;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.helper.SessionManager;
import com.example.pabdis.activity.ui.VaccinationActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdatePetVaccination extends AppCompatActivity {

    DatabaseHelper mydb;
    private SessionManager session;
    private ProgressDialog pDialog;
    Button btnDateVacc,btnUpdate;
    TextView txtxDateVacc,txtvaccinatedby2;
    Spinner txtvaccinatedby;
    Integer position,uid;
    String petid;
    final Calendar myCalendar = Calendar.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_vaccination);
        mydb = new DatabaseHelper(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        btnDateVacc = findViewById(R.id.btnDateVacc);
        btnUpdate = findViewById(R.id.btnUpdate);
        txtxDateVacc = findViewById(R.id.txtxDateVacc);
        txtvaccinatedby = findViewById(R.id.txtvaccinatedby);

        btnUpdate.setText("Add");

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                petid= null;
                position = null;
                petid = null;
            } else {
                uid= extras.getInt("id");
                position= extras.getInt("position");
                petid= extras.getString("petid");

            }
        } else {
            uid= (Integer) savedInstanceState.getSerializable("id");
            position= (Integer) savedInstanceState.getSerializable("position");
            petid= (String) savedInstanceState.getSerializable("petid");


        }


        Toast.makeText(UpdatePetVaccination.this, "Check your input!"+  uid, Toast.LENGTH_SHORT).show();


        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                txtxDateVacc.setText(sdf.format(myCalendar.getTime()));
            }

        };

        btnDateVacc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(UpdatePetVaccination.this, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();


            }

        });



        Cursor rs = mydb.getVaccMain(uid);
        rs.moveToFirst();

        if(rs.getCount() > 0)
        {

            btnUpdate.setText("Update");







        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String datevacc = txtxDateVacc.getText().toString();
                final String vaccby = txtvaccinatedby.getSelectedItem().toString();

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 0);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());








                AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePetVaccination.this);
                builder.setTitle("Choose option");
                builder.setMessage("Update or delete user?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Build an AlertDialog
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(UpdatePetVaccination.this);

                        // Set a title for alert dialog
                        builder.setTitle("ADD.");

                        // Ask the final question
                        builder.setMessage("Do you want to add the data?");

                        // Set click listener for alert dialog buttons
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch(which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        // User clicked the Yes button


                                        mydb.addVaccinationDate(petid,datevacc,vaccby,created_at);
                                        

                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        // User clicked the No button


                                }
                            }
                        };

                        // Set the alert dialog yes button click listener
                        builder.setPositiveButton("Yes", dialogClickListener);

                        // Set the alert dialog no button click listener
                        builder.setNegativeButton("No",dialogClickListener);

                        android.app.AlertDialog dialog2 = builder.create();
                        // Display the alert dialog on interface
                        dialog2.show();



                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Build an AlertDialog
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(UpdatePetVaccination.this);

                        // Set a title for alert dialog
                        builder.setTitle("DELETE.");

                        // Ask the final question
                        builder.setMessage("Do you want to delete the data?");

                        // Set click listener for alert dialog buttons
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch(which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        // User clicked the Yes button


                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        // User clicked the No button


                                }
                            }
                        };

                        // Set the alert dialog yes button click listener
                        builder.setPositiveButton("Yes", dialogClickListener);

                        // Set the alert dialog no button click listener
                        builder.setNegativeButton("No",dialogClickListener);

                        android.app.AlertDialog dialog2 = builder.create();
                        // Display the alert dialog on interface
                        dialog2.show();


                    }
                });

                builder.create().show();
            }
        });













    }


}
