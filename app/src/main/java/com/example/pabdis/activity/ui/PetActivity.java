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
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.helper.ListAdapter;
import com.example.pabdis.activity.helper.Owner;
import com.example.pabdis.activity.helper.Pet;
import com.example.pabdis.activity.helper.PetAdapter;

import java.util.ArrayList;

public class PetActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper myDB;
    ListView LISTVIEW;
    PetAdapter listAdapter;
    EditText searchView;
    Cursor cursor;
    ArrayList<Pet> PetList = new ArrayList<Pet>();


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

                AlertDialog.Builder builder = new AlertDialog.Builder(PetActivity.this);
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
                        DatabaseHelper dbHelper = new DatabaseHelper(PetActivity.this);
                        Toast.makeText(getApplicationContext(), "Work in progress!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.create().show();
            }
        });
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
    protected void onResume() {

        ShowSQLiteDBdata();
        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        SQLiteDatabase sqLiteDatabase = myDB.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM pvet_pet", null);
        Pet pet;
        PetList = new ArrayList<Pet>();

        if (cursor.moveToFirst()) {
            do {

                String id =  ("ID: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCCOL_1)));
                String petid = ("Pet ID: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCCOL_12)));
//                String owner_id = ("Owner ID: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCCOL_3)));
                String owner_id = "";
                String petname = ("Pet Name:" + cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCCOL_4)));
                String specie = ("Specie:" + cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCCOL_5)));
                String breed = ("Breed: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCCOL_6)));
                String sex =  ("Sex: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCCOL_7)));
                String birth =  ("Birthdate: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCCOL_8)));
                String color =  ("Color: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCCOL_9)));
                String created_at =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.VACCCOL_14));
                pet = new Pet(id,owner_id,petid,petname,specie,breed,sex, birth,color,created_at);
                PetList.add(pet);
            } while (cursor.moveToNext());
        }

        listAdapter = new PetAdapter(PetActivity.this, R.layout.items_pet, PetList);
        LISTVIEW.setAdapter(listAdapter);
        cursor.close();
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
