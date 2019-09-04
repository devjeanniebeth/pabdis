package com.example.pabdis.activity.updates;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.example.pabdis.activity.ui.PetActivity;
import com.example.pabdis.activity.ui.VaccinationActivity;

public class PetVaccination extends AppCompatActivity {

    DatabaseHelper myDB;
    ListView LISTVIEW;
    EditText searchView;
    Integer pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        myDB = new DatabaseHelper(getApplicationContext());
        LISTVIEW = findViewById(R.id.listView1);
        searchView = findViewById(R.id.searchEdt);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                pos = null;
            } else {
                pos= extras.getInt("pos");
            }
        } else {
            pos= (Integer) savedInstanceState.getSerializable("pos");
        }


        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listAdapter.getFilter().filter(s.toString());
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




        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, long l) {

                final String code = listAdapter.getItem(position).getPetid();
                final String ownerid = listAdapter.getItem(position).getOwner_id();


                AlertDialog.Builder builder = new AlertDialog.Builder(PetActivity.this);
                builder.setTitle("Choose option");
                builder.setMessage("Update or delete user?");
                builder.setPositiveButton("Update Pet Info", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        //  String code = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_2));

//                        Toast.makeText(PetActivity.this, "Check your input!"+ code, Toast.LENGTH_SHORT).show();

                        LISTVIEW.setSelection(position);
                        view.setBackgroundColor(Color.BLUE);
                        Intent i = new Intent(PetActivity.this, VaccinationActivity.class);
                        i.putExtra("petid", code);
                        i.putExtra("position", position);
                        i.putExtra("ownerid", ownerid);
                        i.putExtra("add", update);

                        startActivity(i);



                    }
                });
                builder.setNegativeButton("Update Pet Vaccination Info", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


//                        Intent i = new Intent(PetActivity.this, VaccinationActivity.class);
//                        i.putExtra("petid", code);
//                        startActivity(i);


                    }
                });

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Build an AlertDialog
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PetActivity.this);

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


                                        myDB.deletePet(code);
                                        Toast.makeText(getApplicationContext(), "Successfully deleted!", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(PetActivity.this, PetActivity.class);
                                        i.putExtra("ownerid", code);
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

}
