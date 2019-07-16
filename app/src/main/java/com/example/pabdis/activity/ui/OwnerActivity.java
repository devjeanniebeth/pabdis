package com.example.pabdis.activity.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.helper.ListAdapter;
import com.example.pabdis.activity.helper.Owner;

import java.util.ArrayList;

public class OwnerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper myDB;
    ListView LISTVIEW;
    ListAdapter listAdapter;
    EditText searchView;
    Cursor cursor;
    ArrayList<Owner> OwnerList = new ArrayList<Owner>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);
        Toolbar toolbar = findViewById(R.id.toolbar);
        myDB = new DatabaseHelper(getApplicationContext());
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


        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, int position, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerActivity.this);
                builder.setTitle("Choose option");
                builder.setMessage("Update or delete user?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        //  String code = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_2));
                        Toast.makeText(getApplicationContext(), "Work in progress!", Toast.LENGTH_SHORT).show();



                    }
                });
                builder.setNegativeButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Work in progress!", Toast.LENGTH_SHORT).show();


                    }
                });

                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper dbHelper = new DatabaseHelper(OwnerActivity.this);
                        Toast.makeText(getApplicationContext(), "Work in progress!", Toast.LENGTH_SHORT).show();
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
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM pvet_owner", null);
        Owner owner;
        OwnerList = new ArrayList<Owner>();

        if (cursor.moveToFirst()) {
            do {

                String id =  ("ID: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_1)));
                String lati = ("Latitude: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_12)));
                String longi = ("Longitude: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_13)));
                String ownerid = ("Owner ID: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_3)));
                String r_full = ("Owner Name: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_6)) + cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_5)) );
//                String h_full = ("House Head:" + cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_4)));
//                String owner_info = ("House Head:" + cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_4)));
                String contact = ("Contact: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_8)));
                String muni =  ("Municipality: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_9)));
                String brgy =  ("Barangay: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_10)));
                String createdat =  ("Created At: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_15)));
                String housenum =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.OWNERCOL_10));
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

        } else if (id == R.id.nav_map) {

            Intent intent=new Intent(getApplicationContext(), MapActivity.class);
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
