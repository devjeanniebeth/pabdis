package com.example.pabdis.activity.updates;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.helper.Pet;
import com.example.pabdis.activity.helper.PetAdapter;
import com.example.pabdis.activity.helper.PetVacc;
import com.example.pabdis.activity.helper.PetVaccAdapter;
import com.example.pabdis.activity.survey.CarabaoActivity;
import com.example.pabdis.activity.ui.PetActivity;
import com.example.pabdis.activity.ui.VaccinationActivity;

import java.util.ArrayList;

public class PetVaccination extends AppCompatActivity {

    DatabaseHelper myDB;
    ListView LISTVIEW;
    EditText searchView;
    Integer pos;
    String ownerid, petid;
    Cursor cursor;
    PetVaccAdapter vaccadapter;
    FloatingActionButton add;
    ArrayList<PetVacc> PetList = new ArrayList<PetVacc>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petvacc);
        Toolbar toolbar = findViewById(R.id.toolbar);
        myDB = new DatabaseHelper(getApplicationContext());
        LISTVIEW = findViewById(R.id.listView1);
        searchView = findViewById(R.id.searchEdt);
        add  = findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                pos = null;
                ownerid= null;
                petid= null;
            } else {
                pos= extras.getInt("pos");
                ownerid= extras.getString("ownerid");
                petid= extras.getString("petid");
            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
            petid= (String) savedInstanceState.getSerializable("petid");
            pos= (Integer) savedInstanceState.getSerializable("pos");
        }


        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                vaccadapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(pos != null) {
            LISTVIEW.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            LISTVIEW.post(new Runnable() {
                @Override
                public void run() {
                    LISTVIEW.requestFocus();
                    LISTVIEW.setSelection(pos);
//                    LISTVIEW.getChildAt(pos).setBackgroundColor(Color.GREEN);

                }
            });

        }


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), UpdatePetVaccination.class);
                intent.putExtra("ownerid",ownerid);
                intent.putExtra("petid", petid);
                intent.putExtra("pos", pos);
                startActivity(intent);
            }
        });




        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, long l) {

                final String code = vaccadapter.getItem(position).getId();
//                final String ownerid = vaccadapter.getItem(position).getOwner_id();


                AlertDialog.Builder builder = new AlertDialog.Builder(PetVaccination.this);
                builder.setTitle("Choose option");
                builder.setMessage("Update or delete user?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        //  String code = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_2));

//
//
                        LISTVIEW.setSelection(position);
                        Toast.makeText(PetVaccination.this, "Check your input!"+ code, Toast.LENGTH_SHORT).show();


////                        view.setBackgroundColor(Color.BLUE);
                        Intent i = new Intent(PetVaccination.this, UpdatePetVaccination.class);
                        i.putExtra("id", code);
                        i.putExtra("position", position);
                        i.putExtra("petid", petid);
                        startActivity(i);




                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Build an AlertDialog
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PetVaccination.this);

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

                                        myDB.deleteVacc(code);
                                        Toast.makeText(getApplicationContext(), "Successfully deleted!", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(PetVaccination.this, PetVaccination.class);
                                        i.putExtra("ownerid", ownerid);
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

                builder.create().show();
            }
        });
    }


    @Override
    protected void onResume() {

        ShowSQLiteDBdata();
        super.onResume();
    }


    private void ShowSQLiteDBdata() {

        SQLiteDatabase sqLiteDatabase = myDB.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT p.id,p.pet_id,p.petname,v.date_vaccination,v.vaccinated_by,v.created_at FROM pvet_pet as p INNER JOIN pvet_pet_vaccination as v on " +
                "p.pet_id = v.pet_id WHERE p.pet_id ='"+petid+"'", null);
        PetVacc pet;
        PetList = new ArrayList<PetVacc>();

        if (cursor.moveToFirst() && cursor.getCount() > 0) {
            do {

                String id =  (cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACC_DATE_1)));
                String petid = (cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACC_DATE_2)));
                String petname = (cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCCOL_4)));
                String datevacc = (cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACC_DATE_3)));
                String vaccby = (cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACC_DATE_4)));
                String created_at =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCCOL_15));
                pet = new PetVacc(id,petid,petname,datevacc,vaccby,created_at);
                PetList.add(pet);
            } while (cursor.moveToNext());


            vaccadapter = new PetVaccAdapter(PetVaccination.this, R.layout.items_petvacc, PetList);
            LISTVIEW.setAdapter(vaccadapter);
            cursor.close();
        }else{
            Toast.makeText(getApplicationContext(), "Successfully deleted!", Toast.LENGTH_SHORT).show();


        }


    }



}
