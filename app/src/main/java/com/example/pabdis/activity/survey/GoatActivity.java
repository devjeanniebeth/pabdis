package com.example.pabdis.activity.survey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.ui.MainActivity;
import com.example.pabdis.activity.ui.VaccinationActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GoatActivity extends AppCompatActivity {


    Button btnNext;
    String ownerid;
    DatabaseHelper myDB;
    EditText edtBuckD,edtBuckM,edtDoeD,edtDoeM,edtKidsD,edtKidsM,edtSF_sw,edtSA_sw,edtSwineTotalArea,edtSwineTotalIncome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_goat);
        btnNext = findViewById(R.id.btnProceedSurvey);
        edtBuckD = findViewById(R.id.edtBuckD);
        edtBuckM = findViewById(R.id.edtBuckM);
        edtDoeD = findViewById(R.id.edtDoeD);
        edtDoeM = findViewById(R.id.edtDoeM);
        edtKidsD = findViewById(R.id.edtKidsD);
        edtKidsM = findViewById(R.id.edtKidsM);
        edtSF_sw = findViewById(R.id.edtSF_sw);
        edtSA_sw = findViewById(R.id.edtSA_sw);
        edtSwineTotalArea = findViewById(R.id.edtSwineTotalArea);
        edtSwineTotalIncome = findViewById(R.id.edtSwineTotalIncome);

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

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String buckd = edtBuckD.getText().toString();
                final String buckm = edtBuckM.getText().toString();
                final String doed = edtDoeD.getText().toString();
                final String doem = edtDoeM.getText().toString();
                final String kidsd = edtKidsD.getText().toString();
                final String kidsm = edtKidsM.getText().toString();
                final String go_sf = edtSF_sw.getText().toString();
                final String go_sa = edtSA_sw.getText().toString();
                final String go_totala = edtSwineTotalArea.getText().toString();
                final String go_totali = edtSwineTotalIncome.getText().toString();


                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(GoatActivity.this);

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

                                if (buckd.equals("") || buckm.equals("") || doed.equals("") ||
                                        doem.equals("") || kidsd.equals("") || kidsm.equals("") ||
                                        go_sf.equals("") || go_sa.equals("") || go_totala.equals("") || go_totali.equals("")  ) {
                                    Toast.makeText(GoatActivity.this, "Check your input!" , Toast.LENGTH_SHORT).show();

                                }else {


                                    try {
                                        myDB.addGoat(ownerid, buckd, buckm, doed, doem, kidsd, kidsm, go_sf, go_sa,
                                                go_totala, go_totali, created_at);
                                        Intent intent = new Intent(getApplicationContext(), OtherActivity.class);
                                        intent.putExtra("owner_id", ownerid);
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