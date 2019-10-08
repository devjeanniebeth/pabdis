package com.example.pabdis.activity.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.helper.ListAdapter;
import com.example.pabdis.activity.helper.Owner;
import com.example.pabdis.activity.helper.SessionManager;
import com.example.pabdis.activity.login.LoginActivity;
import com.example.pabdis.activity.survey.HouseholdActivity;
import com.example.pabdis.activity.survey.SwineActivity;
import com.example.pabdis.activity.updates.ListUpdateActivity;
import com.example.pabdis.activity.updates.ViewInfoActivity;

import java.util.ArrayList;

public class OwnerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SessionManager session;
    DatabaseHelper myDB;
    ListView LISTVIEW;
    ListAdapter listAdapter;
    EditText searchView;
    Cursor cursor;
    Integer pos;
    ArrayList<Owner> OwnerList = new ArrayList<Owner>();
    LinearLayout btnLay;

    public int TOTAL_LIST_ITEMS;
    public int NUM_ITEMS_PAGE= 20;
    private int noOfBtns;
    private Button[] btns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
        Toolbar toolbar = findViewById(R.id.toolbar);
        myDB = new DatabaseHelper(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        LISTVIEW = findViewById(R.id.listView1);
        searchView = findViewById(R.id.searchEdt);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        TOTAL_LIST_ITEMS = myDB.getCountAll();


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

                Integer ctr = LISTVIEW.getAdapter().getCount();
                if(ctr > 0)
                {
                    listAdapter.getFilter().filter(s.toString());
                }

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
//                    LISTVIEW.setItemChecked(pos, true);
//                    LISTVIEW.getChildAt(pos).setBackgroundColor(Color.GREEN);



                }
            });




        }

        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, long l) {
                final String code = listAdapter.getItem(position).getOwnerid();


                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerActivity.this);
                builder.setTitle("Choose option");
                builder.setMessage("Update or delete user?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        Toast.makeText(OwnerActivity.this, "Check your input!", Toast.LENGTH_SHORT).show();
                        if(pos != null) {
                            LISTVIEW.setSelection(pos);
                            view.setBackgroundColor(Color.BLUE);
                        }
                        Intent i = new Intent(OwnerActivity.this, ListUpdateActivity
                                .class);
                        i.putExtra("ownerid", code);
                        i.putExtra("position", position);
                        startActivity(i);

                    }
                });
                builder.setNegativeButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(OwnerActivity.this, ViewInfoActivity.class);
                        i.putExtra("ownerid", code);
                        i.putExtra("position", position);
                        startActivity(i);

                    }
                });

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Build an AlertDialog
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(OwnerActivity.this);

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


                                        myDB.deleteUser(code);
                                        Toast.makeText(getApplicationContext(), "Successfully deleted!", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(OwnerActivity.this, OwnerActivity.class);
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

    private void Btnfooter()
    {
        int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
        val = val == 0 ? 0 : 1;
        noOfBtns = TOTAL_LIST_ITEMS/NUM_ITEMS_PAGE+val;

        btns = new Button[noOfBtns];

        for(int i = 0; i < noOfBtns;i++)
        {

        }


    }

    @Override
    protected void onResume() {

        ShowSQLiteDBdata();
        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        SQLiteDatabase sqLiteDatabase = myDB.getWritableDatabase();


        cursor = sqLiteDatabase.rawQuery("SELECT * FROM pvet_owner  ", null);
        Owner owner;
        OwnerList = new ArrayList<Owner>();

        if (cursor.moveToFirst()) {
            do {

                String id =  (cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_1)));
                String lati = (cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_12)));
                String longi = (cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_13)));
                String ownerid = (cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_3)));
                String r_full = (cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_6)) + " " +cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_5)) );
                String contact = (cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_7)));
                String muni =  (cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_9)));
                String brgy =  (cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_10)));
                String createdat =  (cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_15)));
                String housenum =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_11)) + ", " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_10)) +"," +cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_9));
                owner = new Owner(id,ownerid,r_full,contact,housenum, muni,brgy,lati,longi,createdat);
                OwnerList.add(owner);
            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(OwnerActivity.this, R.layout.items, OwnerList);
        LISTVIEW.setAdapter(listAdapter);

        cursor.close();
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

    @SuppressWarnings("StatementWithEmptyBody")
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

        }  else if (id == R.id.nav_list_owner) {

            Intent intent=new Intent(getApplicationContext(), OwnerActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_list_pet) {

            Intent intent=new Intent(getApplicationContext(), PetActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {

            session.logoutUser();
            Intent i = new Intent(OwnerActivity.this, LoginActivity.class);
            startActivity(i);
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
