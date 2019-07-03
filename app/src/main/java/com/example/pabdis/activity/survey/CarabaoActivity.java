package com.example.pabdis.activity.survey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CarabaoActivity extends AppCompatActivity {

    Button btnNext;
    String ownerid,vaccstat, vacctype, deworm;
    DatabaseHelper myDB;
    EditText edtCarabullC,edtCarabullN,edtCaracowC,edtCaracowN,edtCaracalfC,edtCaracalfN,
            edtSF_sw,edtSA_sw,edtSwineTotalArea,edtSwineTotalIncome;
    RadioButton rbyes, rbno;
    Spinner vacc;
    FloatingActionButton skip;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_carabao);
        myDB = new DatabaseHelper(getApplicationContext());
        edtCarabullC = findViewById(R.id.edtCarabullC);
        edtCarabullN = findViewById(R.id.edtCarabullN);
        edtCaracowC = findViewById(R.id.edtCaracowC);
        edtCaracowN = findViewById(R.id.edtCaracowN);
        edtCaracalfC = findViewById(R.id.edtCaracalfC);
        edtCaracalfN = findViewById(R.id.edtCaracalfN);
        edtSF_sw = findViewById(R.id.edtSF_sw);
        edtSA_sw = findViewById(R.id.edtSA_sw);
        edtSwineTotalArea = findViewById(R.id.edtSwineTotalArea);
        edtSwineTotalIncome = findViewById(R.id.edtSwineTotalIncome);
        btnNext = findViewById(R.id.btnProceedSurvey);
        rbno = findViewById(R.id.rb2);
        rbyes = findViewById(R.id.rb1);
        vacc = findViewById(R.id.vaccination);
        textView = findViewById(R.id.textView);


        vacc.setVisibility(View.GONE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerid= null;
            } else {
                ownerid= extras.getString("ownerid");
            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
        }

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String carabullc = edtCarabullC.getText().toString();
                final String carabulln = edtCarabullN.getText().toString();
                final String caracowc = edtCaracowC.getText().toString();
                final String caracown = edtCaracowN.getText().toString();
                final String caracalfc = edtCaracalfC.getText().toString();
                final String caracalfn = edtCaracalfN.getText().toString();
                final String car_sf = edtSF_sw.getText().toString();
                final String car_sa = edtSA_sw.getText().toString();
                final String car_totala = edtSwineTotalArea.getText().toString();
                final String car_totali = edtSwineTotalIncome.getText().toString();

                final String vacc = vaccstat;
                final String vacct = vacctype;
                final String dewormed = deworm;

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());

                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(CarabaoActivity.this);

                // Set a title for alert dialog
                builder.setTitle("There's no going back.");

                // Ask the final question
                builder.setMessage("Are you sure you want to save the data?");

                // Set click listener for alert dialog buttons
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                // User clicked the Yes button
                                try {
                                    myDB.addCarabao(ownerid,carabullc, carabulln,caracowc,caracown,caracalfc,caracalfn,car_sf,car_sa,
                                            car_totala,car_totali ,vacc.trim(), vacct.trim(),dewormed.trim(), created_at );
                                    Toast.makeText(CarabaoActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), GoatActivity.class);
                                    intent.putExtra("owner_id",ownerid);
                                    startActivity(intent);
                                } catch (Exception e) {
                                    e.printStackTrace();

                                }
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                // User clicked the No button
                                break;
                        }
                    }
                };

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Yes", dialogClickListener);

                // Set the alert dialog no button click listener
                builder.setNegativeButton("No",dialogClickListener);

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            }

        });


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb1:
                if (checked)
                    // Pirates are the best
                    vacc.setVisibility(View.VISIBLE);
                vaccstat = "1";
                textView.setVisibility(View.VISIBLE);
                vacctype = vacc.getSelectedItem().toString();
                break;
            case R.id.rb2:
                if (checked)
                    vacc.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                vaccstat = "2";
                vacctype = "";
                // Ninjas rule
                break;
        }
    }

    public void onRadioButtonClicked2(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rby:
                if (checked)
                    // Pirates are the best
                    deworm = "1";
                break;
            case R.id.rbn:
                if (checked)
                    deworm = "2";
                // Ninjas rule
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
    }
}
