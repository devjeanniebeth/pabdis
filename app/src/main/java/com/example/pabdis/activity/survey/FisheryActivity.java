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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.ui.MainActivity;
import com.example.pabdis.activity.ui.MapActivity;
import com.example.pabdis.activity.ui.OwnerActivity;
import com.example.pabdis.activity.ui.PetActivity;
import com.example.pabdis.activity.ui.ProfileActivity;
import com.example.pabdis.activity.updates.ApiaryActivity;
import com.example.pabdis.activity.updates.ListUpdateActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FisheryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button btnNext,btnUpdate;
    String ownerid, petid, api, fish, update;
    CheckBox withfishery, withapiary;
    DatabaseHelper myDB, myDB2;
    Integer pos, stat;
    FloatingActionButton skip;
    EditText edtTotalArea,edtProd,edtIncome,edtColonyNum,edtProdH,edtTotalIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = new DatabaseHelper(getApplicationContext());

        setContentView(R.layout.activity_survey_fishery);
        btnNext = findViewById(R.id.btnProceedSurvey);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setVisibility(View.GONE);
        skip = findViewById(R.id.fab);

        edtTotalArea = findViewById(R.id.edtTotalArea);
        edtProd = findViewById(R.id.edtProd);
        edtIncome = findViewById(R.id.edtIncome);




        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerid= null;
                petid = null;
                update = null;
                pos = null;
                stat = null;
            } else {
                ownerid= extras.getString("ownerid");
                petid= extras.getString("petid");
                stat= extras.getInt("stat");
                pos= extras.getInt("position");
            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
            petid = (String) savedInstanceState.getSerializable("petid");
            update = (String) savedInstanceState.getSerializable("update");
            pos = (Integer) savedInstanceState.getSerializable("position");
            stat= (Integer) savedInstanceState.getSerializable("stat");

        }


        Cursor rs = myDB.getFishery(ownerid);
        rs.moveToFirst();

        if(rs.getCount() > 0) {


            withfishery.setChecked(true);
            skip.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);
            fish = "true";
            withapiary.setVisibility(View.GONE);


            String t_area = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY7COL_4));
            String t_prod = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY7COL_5));
            String t_inc = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY7COL_6));


            edtTotalArea.setText(t_area);
            edtProd.setText(t_prod);
            edtIncome.setText(t_inc);


            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final String f_area = edtTotalArea.getText().toString();
                    final String f_prod = edtProd.getText().toString();
                    final String f_inc = edtIncome.getText().toString();


                    // Build an AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(FisheryActivity.this);

                    // Set a title for alert dialog
                    builder.setTitle("UPDATE.");

                    // Ask the final question
                    builder.setMessage("Are you sure you want to update the data?");

                    // Set click listener for alert dialog buttons
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    // User clicked the Yes button

                                    try {

                                        if (f_area.equals("") || f_prod.equals("") || f_inc.equals("")) {
                                            Toast.makeText(FisheryActivity.this, "Check your input!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            myDB.updateFishery(ownerid, f_area, f_prod, f_inc);
                                            Toast.makeText(FisheryActivity.this, "Success!", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), ListUpdateActivity.class);
                                            intent.putExtra("ownerid", ownerid);
                                            intent.putExtra("petid", petid);
                                            intent.putExtra("position", pos);
                                            intent.putExtra("stat", stat);
                                            startActivity(intent);
                                        }

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
                    builder.setNegativeButton("No", dialogClickListener);

                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();

                }
            });

        }




        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ApiaryActivity.class);
                intent.putExtra("ownerid",ownerid);
                intent.putExtra("petid", petid);
                intent.putExtra("pos", pos);
                intent.putExtra("stat", stat);
                startActivity(intent);



            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String f_area = edtTotalArea.getText().toString();
                final String f_prod = edtProd.getText().toString();
                final String f_inc = edtIncome.getText().toString();



                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 0);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(FisheryActivity.this);

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

                                            if ( (f_area.equals("") ||  f_prod.equals("") ||  f_inc.equals(""))) {
                                                Toast.makeText(FisheryActivity.this, "Check your input!" , Toast.LENGTH_SHORT).show();
                                            }else {
                                                myDB.addFishery(ownerid, f_area, f_prod, f_inc, created_at);
                                                Toast.makeText(FisheryActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(getApplicationContext(), ApiaryActivity.class);
                                                intent.putExtra("ownerid", ownerid);
                                                intent.putExtra("petid", petid);
                                                intent.putExtra("pos", pos);
                                                intent.putExtra("stat", stat);
                                                startActivity(intent);
                                            }

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



    @Override
    public void onBackPressed() {
        if(pos != null)
        {
            Intent i = new Intent(FisheryActivity.this, ListUpdateActivity.class);
            i.putExtra("position", pos);
            i.putExtra("ownerid", ownerid);
            i.putExtra("stat", stat);
            startActivity(i);
//        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_survey) {
            finish();
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            finish();
            Intent intent=new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);

        }  else if (id == R.id.nav_list_owner) {
            finish();
            Intent intent=new Intent(getApplicationContext(), OwnerActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_list_pet) {
            finish();
            Intent intent=new Intent(getApplicationContext(), PetActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
