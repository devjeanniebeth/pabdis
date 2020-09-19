package com.example.pabdis.activity.survey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.ui.MainActivity;
import com.example.pabdis.activity.ui.MapActivity;
import com.example.pabdis.activity.ui.OwnerActivity;
import com.example.pabdis.activity.ui.PetActivity;
import com.example.pabdis.activity.ui.ProfileActivity;
import com.example.pabdis.activity.ui.VaccinationActivity;
import com.example.pabdis.activity.updates.ListUpdateActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class CarabaoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button btnNext, compute,btnUpdate;
    String ownerid,vaccstat, vacctype, deworm, petid, update,add;
    DatabaseHelper myDB;
    EditText edtCarabullC,edtCarabullN,edtCaracowC,edtCaracowN,edtCaracalfC,edtCaracalfN, edtTotal,
            edtSF_sw_kg,edtSF_sw_hd,edtSA_sw_kg,edtSA_sw_hd,edtSwineTotalArea,edtSwineTotalIncome;
    RadioButton rbyes, rbno, rbn, rby;
    Spinner vacc;
    CheckBox cbbl;
    ArrayList<String> mylist = new ArrayList<String>();
    ArrayList<String> mylist2 = new ArrayList<String>();
    Integer pos;
    ArrayList<String> mylistup = new ArrayList<String>();
    FloatingActionButton skip, skip_vacc;
    TextView textView, txtincome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_carabao);
        myDB = new DatabaseHelper(getApplicationContext());
        btnUpdate = findViewById(R.id.btnUpdate);
        skip = findViewById(R.id.fab);
        skip_vacc = findViewById(R.id.fab2);
        cbbl = findViewById(R.id.cbbl);
        cbbl.setVisibility(View.GONE);
        edtTotal = findViewById(R.id.edtTotal);
        compute = findViewById(R.id.btnCompute);
        edtCarabullC = findViewById(R.id.edtCarabullC);
        edtCarabullN = findViewById(R.id.edtCarabullN);
        edtCaracowC = findViewById(R.id.edtCaracowC);
        edtCaracowN = findViewById(R.id.edtCaracowN);
        edtCaracalfC = findViewById(R.id.edtCaracalfC);
        edtCaracalfN = findViewById(R.id.edtCaracalfN);
        edtSF_sw_kg = findViewById(R.id.edtSF_sw_kg);
        edtSF_sw_hd = findViewById(R.id.edtSF_sw_hd);
        edtSA_sw_kg = findViewById(R.id.edtSA_sw_kg);
        edtSA_sw_hd = findViewById(R.id.edtSA_sw_hd);
        edtSwineTotalArea = findViewById(R.id.edtSwineTotalArea);
        edtSwineTotalIncome = findViewById(R.id.edtSwineTotalIncome);
        btnNext = findViewById(R.id.btnProceedSurvey);
        rbno = findViewById(R.id.rb2);
        rbyes = findViewById(R.id.rb1);
        rbn = findViewById(R.id.rbn);
        rby = findViewById(R.id.rby);
        textView = findViewById(R.id.textView);
        Calendar now = Calendar.getInstance();
        now = Calendar.getInstance();
        now.add(Calendar.YEAR, 1);

        txtincome = findViewById(R.id.txtincome);
        txtincome.setText("Total Income for " + now.get(Calendar.YEAR));
        edtTotal.setEnabled(false);
        btnUpdate.setVisibility(View.GONE);




        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerid= null;
                petid = null;
                update = null;
                pos = null;
            } else {
                ownerid= extras.getString("ownerid");
                petid= extras.getString("petid");
                update= extras.getString("update");
                pos= extras.getInt("position");
            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
            petid = (String) savedInstanceState.getSerializable("petid");
            update = (String) savedInstanceState.getSerializable("update");
            pos = (Integer) savedInstanceState.getSerializable("position");


        }




        Cursor rs = myDB.getCarabao(ownerid);
        rs.moveToFirst();

        if(rs.getCount() > 0)
        {

            skip.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);


            cbbl.setVisibility(View.VISIBLE);
            cbbl.setChecked(false);



            String carabullc = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_4));
            String carabulln = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_5));
            String caracowc = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_6));
            String caracown = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_7));
            String caracalfc = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_8));
            String caracalfn = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_9));

            String total_inv = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_10));
            String sl_f_kg = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_11));
            String sl_f_hd = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_12));
            String sl_a_kg = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_13));
            String sl_a_hd = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_14));
            String total_area = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_15));
            String total_inc = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_16));

            String vacc = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_17));

            if(vacc.equals("1"))
            {
                rbyes.setChecked(true);
                rbno.setChecked(false);
                vaccstat = "1";

            }else{
                rbno.setChecked(true);
                rbyes.setChecked(false);
                vaccstat = "2";

            }

            String vacctype = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_18));
            vacctype = vacctype.replace("[", "");
            vacctype = vacctype.replace("]", "");
            vacctype = vacctype.replace(", ", ",");



            mylist2 = new ArrayList<String>(Arrays.asList(vacctype.split(",")));




            final String vacca = vacctype;


            if(mylist2.contains(cbbl.getText().toString()))
            {
                mylistup.add(cbbl.getText().toString());
                cbbl.setChecked(true);

            }

            String dewormed = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_19));


            if(dewormed.equals("1"))
            {
                rby.setChecked(true);
                rbn.setChecked(false);
                deworm = "1";


            }else{
                rbn.setChecked(true);
                rby.setChecked(false);
                deworm = "2";


            }

            edtCarabullC.setText(carabullc);
            edtCarabullN.setText(carabulln);
            edtCaracalfC.setText(caracalfc);
            edtCaracalfN.setText(caracalfn);
            edtCaracowC.setText(caracowc);
            edtCaracowN.setText(caracown);
            edtTotal.setText(total_inv);
            edtSF_sw_kg.setText(sl_f_kg);
            edtSF_sw_hd.setText(sl_f_hd);
            edtSA_sw_kg.setText(sl_a_kg);
            edtSA_sw_hd.setText(sl_a_hd);
            edtSwineTotalArea.setText(total_area);
            edtSwineTotalIncome.setText(total_inc);


            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final String carabullc = edtCarabullC.getText().toString();
                    final String carabulln = edtCarabullN.getText().toString();
                    final String caracowc = edtCaracowC.getText().toString();
                    final String caracown = edtCaracowN.getText().toString();
                    final String caracalfc = edtCaracalfC.getText().toString();
                    final String caracalfn = edtCaracalfN.getText().toString();
                    final String total = edtTotal.getText().toString();
                    final String car_sf_kg = edtSF_sw_kg.getText().toString();
                    final String car_sf_hd = edtSF_sw_hd.getText().toString();
                    final String car_sa_kg = edtSA_sw_kg.getText().toString();
                    final String car_sa_hd = edtSA_sw_hd.getText().toString();
                    final String car_totala = edtSwineTotalArea.getText().toString();
                    final String car_totali = edtSwineTotalIncome.getText().toString();

                    final String vacc = vaccstat;
                    final String vacct = mylistup.toString();
                    final String dewormed = deworm;


                    // Build an AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(CarabaoActivity.this);

                    // Set a title for alert dialog
                    builder.setTitle("UPDATE.");

                    // Ask the final question
                    builder.setMessage("Are you sure you want to update the data?");

                    // Set click listener for alert dialog buttons
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch(which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    // User clicked the Yes button




                                    // User clicked the Yes button
                                    if (carabullc.equals("") || carabulln.equals("") || caracowc.equals("") ||
                                            caracown.equals("") || caracalfc.equals("") || caracalfn.equals("") ||
                                            car_sf_kg.equals("") || car_sf_hd.equals("") || car_sa_kg.equals("") ||
                                            car_sa_hd.equals("") || car_totala.equals("") || car_totali.equals("") ||  vacc.equals("") || vacct.equals("") || dewormed.equals("")) {
                                        Toast.makeText(CarabaoActivity.this, "Check your input!" , Toast.LENGTH_SHORT).show();

                                    }else {
                                        try {
                                            myDB.updateCarabao(ownerid,carabullc, carabulln,caracowc,caracown,caracalfc,caracalfn,total,car_sf_kg,car_sf_hd,car_sa_kg,car_sa_hd,
                                                    car_totala,car_totali ,vacc.trim(), vacct.trim(),dewormed.trim() );
                                            Toast.makeText(CarabaoActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), ListUpdateActivity.class);
                                            intent.putExtra("ownerid",ownerid);
                                            intent.putExtra("petid", petid);
                                            intent.putExtra("position", pos);
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

        cbbl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    if(!Arrays.asList(mylist).contains(cbbl.getText().toString()))
                    {
                        mylist.add(cbbl.getText().toString());
                        mylistup.add(cbbl.getText().toString());

                    }
                }else{
                    mylist.remove(cbbl.getText().toString());
                    mylistup.remove(cbbl.getText().toString());

                }
            }
        });



        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Integer boarn = Integer.parseInt(edtCarabullC.getText().toString());
                final Integer boaru = Integer.parseInt(edtCarabullN.getText().toString());
                final Integer grown = Integer.parseInt(edtCaracowC.getText().toString());
                final Integer growu = Integer.parseInt(edtCaracowN.getText().toString());
                final Integer sown = Integer.parseInt(edtCaracalfC.getText().toString());
                final Integer sowu = Integer.parseInt(edtCaracalfN.getText().toString());


                if(edtCarabullC.equals("") || edtCarabullN.equals("") || edtCaracowC.equals("") || edtCaracowN.equals("")
                        || edtCaracalfC.equals("") || edtCaracalfN.equals("") )
                {

                    Toast.makeText(CarabaoActivity.this, "Empty input!" + ownerid , Toast.LENGTH_SHORT).show();



                }else {



                    final Integer total = boarn + boaru + grown + growu +  sown + sowu ;
                    edtTotal.setText(String.valueOf(total));
                }




            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), GoatActivity.class);
                intent.putExtra("ownerid",ownerid);
                intent.putExtra("petid", petid);
                intent.putExtra("petid", petid);
                intent.putExtra("pos", pos);
                startActivity(intent);

            }
        });

        skip_vacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add = "add";
                Intent intent = new Intent(getApplicationContext(), VaccinationActivity.class);
                intent.putExtra("ownerid",ownerid);
                intent.putExtra("petid", petid);
                intent.putExtra("pos", pos);
                intent.putExtra("add", add);
                startActivity(intent);
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
                final String total = edtTotal.getText().toString();
                final String car_sf_kg = edtSF_sw_kg.getText().toString();
                final String car_sf_hd = edtSF_sw_hd.getText().toString();
                final String car_sa_kg = edtSA_sw_kg.getText().toString();
                final String car_sa_hd = edtSA_sw_hd.getText().toString();
                final String car_totala = edtSwineTotalArea.getText().toString();
                final String car_totali = edtSwineTotalIncome.getText().toString();

                final String vacc = vaccstat;
                final String vacct = mylist.toString();
                final String dewormed = deworm;

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 0);
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




                                // User clicked the Yes button
                                if (carabullc.equals("") || carabulln.equals("") || caracowc.equals("") ||
                                        caracown.equals("") || caracalfc.equals("") || caracalfn.equals("") ||
                                        car_sf_kg.equals("") || car_sf_hd.equals("") || car_sa_kg.equals("") ||
                                        car_sa_hd.equals("") || car_totala.equals("") || car_totali.equals("") ||  vacc.equals("") || vacct.equals("") || dewormed.equals("")) {
                                    Toast.makeText(CarabaoActivity.this, "Check your input!" , Toast.LENGTH_SHORT).show();

                                }else {
                                    try {
                                        myDB.addCarabao(ownerid,carabullc, carabulln,caracowc,caracown,caracalfc,caracalfn,total,car_sf_kg,car_sf_hd,car_sa_kg,car_sa_hd,
                                                car_totala,car_totali ,vacc.trim(), vacct.trim(),dewormed.trim(), created_at );
                                        Toast.makeText(CarabaoActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), GoatActivity.class);
                                        intent.putExtra("ownerid",ownerid);
                                        intent.putExtra("petid", petid);
                                        intent.putExtra("pos", pos);
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
                vaccstat = "1";
                cbbl.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                break;
            case R.id.rb2:
                if (checked)
                textView.setVisibility(View.GONE);
                cbbl.setVisibility(View.GONE);
                mylist.add("");
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
        if(pos != null)
        {
            Intent i = new Intent(CarabaoActivity.this, ListUpdateActivity.class);
            i.putExtra("position", pos);
            i.putExtra("ownerid", ownerid);
            startActivity(i);
//        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_survey) {
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            Intent intent=new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_list_owner) {

            Intent intent=new Intent(getApplicationContext(), OwnerActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_list_pet) {

            Intent intent=new Intent(getApplicationContext(), PetActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
