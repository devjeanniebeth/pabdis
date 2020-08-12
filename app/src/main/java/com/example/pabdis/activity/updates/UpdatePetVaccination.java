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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.helper.SessionManager;
import com.example.pabdis.activity.ui.ProfileActivity;
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
    ArrayAdapter<CharSequence> vaccby;


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
                uid = null;
            } else {
                uid= extras.getInt("uid");
                position= extras.getInt("position");
                petid= extras.getString("petid");

            }
        } else {
            uid= (Integer) savedInstanceState.getSerializable("uid");
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

            String datevacc =  rs.getString(rs.getColumnIndex(DatabaseHelper.VACC_DATE_3));
            String vacciby = rs.getString(rs.getColumnIndex(DatabaseHelper.VACC_DATE_4));

            vaccby = ArrayAdapter.createFromResource(this, R.array.vaccinated_by, R.layout.support_simple_spinner_dropdown_item);


            if(vacciby.equals("Government"))
            {

                int spinnerPosition6 = vaccby.getPosition(vacciby);
                txtvaccinatedby.setSelection(spinnerPosition6);
            }else if(vacciby.equals("Private"))
            {

                int spinnerPosition6 = vaccby.getPosition(vacciby);
                txtvaccinatedby.setSelection(spinnerPosition6);
            }

            txtxDateVacc.setText(datevacc);

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final String datevacc = txtxDateVacc.getText().toString();
                    final String vaccby = txtvaccinatedby.getSelectedItem().toString();


                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdatePetVaccination.this);
                    builder.setTitle("Update Vaccination");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Build an AlertDialog
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(UpdatePetVaccination.this);

                            // Set a title for alert dialog
                            builder.setTitle("UPDATE.");

                            // Ask the final question
                            builder.setMessage("Do you want to update the data?");

                            // Set click listener for alert dialog buttons
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch(which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            // User clicked the Yes button

                                            String puid = uid.toString();

                                            mydb.updateVaccinationDate(puid,datevacc,vaccby);
                                            Toast.makeText(UpdatePetVaccination.this, "Successfully updated vaccination!", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(UpdatePetVaccination.this, PetVaccination.class);
                                            i.putExtra("pos", position);
                                            i.putExtra("petid", petid);
                                            startActivity(i);


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


                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {



                        }
                    });

                    builder.create().show();
                }
            });


        }


        if( uid == 0) {
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final String datevacc = txtxDateVacc.getText().toString();
                    final String vaccby = txtvaccinatedby.getSelectedItem().toString();

                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, 0);
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    final String created_at = format1.format(cal.getTime());



                    // Build an AlertDialog
                    android.app.AlertDialog.Builder builder2 = new android.app.AlertDialog.Builder(UpdatePetVaccination.this);

                    // Set a title for alert dialog
                    builder2.setTitle("Add.");

                    // Ask the final question
                    builder2.setMessage("Do you want to add the data? ");

                    // Set click listener for alert dialog buttons

                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch(which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    // User clicked the Yes button

                                    try {

                                        if(!datevacc.isEmpty()){
                                            mydb.addVaccinationDate(petid, datevacc, vaccby, created_at);
                                            Toast.makeText(UpdatePetVaccination.this, "Successfully added vaccination!", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(UpdatePetVaccination.this, PetVaccination.class);
                                            i.putExtra("pos", position);
                                            i.putExtra("petid", petid);
                                            startActivity(i);
                                        }else{
                                            Toast.makeText(UpdatePetVaccination.this, "Empty date vaccination!", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:


                                    break;
                            }
                        }
                    };

                    // Set the alert dialog yes button click listener
                    builder2.setPositiveButton("Yes", dialogClickListener);

                    // Set the alert dialog no button click listener
                    builder2.setNegativeButton("No",dialogClickListener);

                    android.app.AlertDialog dialog2 = builder2.create();
                    // Display the alert dialog on interface
                    dialog2.show();







                }
            });
        }
    }


}
