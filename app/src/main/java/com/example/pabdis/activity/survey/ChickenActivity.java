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
import com.example.pabdis.activity.ui.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChickenActivity extends AppCompatActivity {

    Button btnNext;
    EditText edtBroiler,edtLayers,edtNative,edtTotal,edtProd,edtSF_sw,edtSA_sw,edtTotalArea,edtTotalIncome;
    String ownerid;
    DatabaseHelper myDB;
    RadioButton rbyes, rbno;
    FloatingActionButton skip;
    Spinner vacc;
    String vaccstat, vacctype, deworm;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_chicken);
        myDB = new DatabaseHelper(getApplicationContext());
        btnNext = findViewById(R.id.btnProceedSurvey);
        edtBroiler = findViewById(R.id.edtBroiler);
        edtLayers = findViewById(R.id.edtLayers);
        edtNative = findViewById(R.id.edtNative);
        edtTotal = findViewById(R.id.edtTotal);
        edtProd = findViewById(R.id.edtProd);
        edtSF_sw = findViewById(R.id.edtSF_sw);
        edtSA_sw = findViewById(R.id.edtSA_sw);
        edtTotalArea = findViewById(R.id.edtTotalArea);
        edtTotalIncome = findViewById(R.id.edtTotalIncome);
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
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ChickenActivity.this);

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
                                Intent intent = new Intent(getApplicationContext(), CattleActivity.class);
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


                final String broiler = edtBroiler.getText().toString();
                final String layer = edtLayers.getText().toString();
                final String snative = edtNative.getText().toString();
                final String total = edtTotal.getText().toString();
                final String prod = edtProd.getText().toString();
                final String ch_sf = edtSF_sw.getText().toString();
                final String ch_sa = edtSA_sw.getText().toString();
                final String ch_totala = edtTotalArea.getText().toString();
                final String ch_totali = edtTotalIncome.getText().toString();
                final String vacc = vaccstat;
                final String vacct = vacctype;
                final String dewormed = deworm;


                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());

                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ChickenActivity.this);

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


                                if (broiler.equals("") || layer.equals("") || snative.equals("") ||
                                        total.equals("") || prod.equals("") || ch_sf.equals("")) {
                                    Toast.makeText(ChickenActivity.this, "Check your input!" , Toast.LENGTH_SHORT).show();
                                }else {

                                    try {
                                        myDB.addChicken(ownerid,broiler, layer,snative,total,prod,ch_sf,ch_sa,
                                                ch_totala,ch_totali,vacc.trim(), vacct.trim(), dewormed.trim(),created_at );
                                        Toast.makeText(ChickenActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), CattleActivity.class);
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