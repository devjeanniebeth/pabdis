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

public class CattleActivity extends AppCompatActivity {

    Button btnNext;
    EditText edtBullD,edtBullM,edtCowD,edtCowM,edtCalfD,edtCalfM,edtSF_sw_kg,edtSF_sw_hd,edtSA_sw_kg,edtSA_sw_hd,edtSwineTotalArea,edtSwineTotalIncome;
    String ownerid, vaccstat, vacctype, deworm;
    DatabaseHelper myDB;
    RadioButton rbyes, rbno;
    Spinner vacc;
    FloatingActionButton skip;
    TextView textView, txtincome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = new DatabaseHelper(getApplicationContext());
        setContentView(R.layout.activity_survey_cattle);
        btnNext = findViewById(R.id.btnProceedSurvey);
        skip = findViewById(R.id.fab);
        edtBullD = findViewById(R.id.edtBullD);
        edtBullM = findViewById(R.id.edtBullM);
        edtCowD = findViewById(R.id.edtCowD);
        edtCowM = findViewById(R.id.edtCowM);
        edtCalfD = findViewById(R.id.edtCalfD);
        edtCalfM = findViewById(R.id.edtCalfM);
        edtSF_sw_kg = findViewById(R.id.edtSF_sw_kg);
        edtSF_sw_hd = findViewById(R.id.edtSF_sw_hd);
        edtSA_sw_kg = findViewById(R.id.edtSA_sw_kg);
        edtSA_sw_hd = findViewById(R.id.edtSA_sw_hd);
        edtSwineTotalArea = findViewById(R.id.edtSwineTotalArea);
        edtSwineTotalIncome = findViewById(R.id.edtSwineTotalIncome);
        rbno = findViewById(R.id.rb2);
        rbyes = findViewById(R.id.rb1);
        vacc = findViewById(R.id.vaccination);
        textView = findViewById(R.id.textView);
        txtincome = findViewById(R.id.txtincome);
        txtincome.setText("Total Income for 2018");
        textView.setVisibility(View.GONE);

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


                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(CattleActivity.this);

                // Set a title for alert dialog
                builder.setTitle("Skipping the process.");

                // Ask the final question
                builder.setMessage("Are you sure you want to skip this survey?");

                // Set click listener for alert dialog buttons
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                // User clicked the Yes button
                                Intent intent = new Intent(getApplicationContext(), CarabaoActivity.class);
                                intent.putExtra("owner_id",ownerid);
                                startActivity(intent);
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

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String bulld = edtBullD.getText().toString();
                final String bullm = edtBullM.getText().toString();
                final String cowd = edtCowD.getText().toString();
                final String cowm = edtCowM.getText().toString();
                final String calfd = edtCalfD.getText().toString();
                final String calfm = edtCalfM.getText().toString();
                final String ca_sf_kg = edtSF_sw_kg.getText().toString();
                final String ca_sf__hd = edtSF_sw_hd.getText().toString();
                final String ca_sa_kg = edtSA_sw_kg.getText().toString();
                final String ca_sa__hd = edtSA_sw_hd.getText().toString();
                final String ca_totala = edtSwineTotalArea.getText().toString();
                final String ca_totali = edtSwineTotalIncome.getText().toString();

                final String vacc = vaccstat;
                final String vacct = vacctype;
                final String dewormed = deworm;

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());


                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(CattleActivity.this);

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


                                // User clicked the Yes button
                                if (bulld.equals("") || bullm.equals("") || cowd.equals("") ||
                                        calfd.equals("") || calfm.equals("") || ca_sf_kg.equals("") ||
                                        ca_sf__hd.equals("") || ca_sa_kg.equals("") || ca_sa__hd.equals("") ||
                                        ca_totala.equals("") || ca_totali.equals("") || vacc.equals("") || vacct.equals("") || dewormed.equals("")) {
                                    Toast.makeText(CattleActivity.this, "Check your input!" + ownerid , Toast.LENGTH_SHORT).show();

                                }else {
                                    try {
                                        myDB.addCattle(ownerid,bulld, bullm,cowd,cowm,calfd,calfm,ca_sf_kg,ca_sf__hd,ca_sa_kg,ca_sa__hd,ca_totala,
                                                ca_totali ,vacc.trim(), vacct.trim(),dewormed.trim(),created_at );
                                        Toast.makeText(CattleActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), CarabaoActivity.class);
                                        intent.putExtra("owner_id",ownerid);
                                        startActivity(intent);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
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

        // Is the button now checked?
        public void onRadioButtonClicked2(View view) {
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