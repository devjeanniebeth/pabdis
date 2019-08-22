package com.example.pabdis.activity.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class VaccinationActivity extends AppCompatActivity {


    EditText otherbreed, othercolormark, txtpetname, txtdistinguish,txtsourceplace;
    TextView dateSurvey,txtAge, txtxDateVacc,strngbreed,txtvaccinatedby2, txtv4;
    Button btndate, chooseImg, btnVacc, dateVacc, btnUpdate;
    FloatingActionButton skip;
    final Calendar myCalendar = Calendar.getInstance();
    TableRow tbl1;
    ImageView imgView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public  static final int RequestPermissionCode  = 1 ;
    Spinner txtbreed, txtGender, txtSpecie, txtColorMark, txtvaccinatedby,txtsource;
    DatabaseHelper myDB;
    String age,ownerid, petid, vacc, update, status, pet;
    Integer pos, year, ctr;
    Character first;
    ArrayAdapter<CharSequence> species, breedsd,breedsc, sex,sources,colormarkings, vaccinatedby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacc);

        myDB = new DatabaseHelper(getApplicationContext());
        dateSurvey = findViewById(R.id.txtdatesurvey);
        btndate = findViewById(R.id.btnDate);
        btnVacc = findViewById(R.id.btnVacc);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setVisibility(View.GONE);
        txtvaccinatedby2= findViewById(R.id.txtvaccinatedby2);
        txtv4 = findViewById(R.id.textView4);
        dateVacc = findViewById(R.id.btnDateVacc);

        tbl1 = findViewById(R.id.tableRow4);


        txtsourceplace = findViewById(R.id.txtsourceplace);
        txtsource = findViewById(R.id.txtsource);
        chooseImg = findViewById(R.id.btnChoose);
        imgView = findViewById(R.id.imgPet);
        txtpetname = findViewById(R.id.txtpetname);
        txtbreed = findViewById(R.id.txtbreed);
        txtGender = findViewById(R.id.txtgender);
        txtSpecie = findViewById(R.id.txtspecies);
        txtColorMark = findViewById(R.id.txtcolormark);
        otherbreed = findViewById(R.id.txtbreedother);
        othercolormark = findViewById(R.id.txtcolorother);
        txtxDateVacc = findViewById(R.id.txtxDateVacc);
        txtvaccinatedby = findViewById(R.id.txtvaccinatedby);
        strngbreed = findViewById(R.id.strngbreed);
        txtAge = findViewById(R.id.txtAge);
        skip = findViewById(R.id.fab);
        txtdistinguish = findViewById(R.id.txtdistinguish);
        otherbreed.setVisibility(View.GONE);
        othercolormark.setVisibility(View.GONE);




        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerid= null;
                petid = null;
                status = null;
                pos = null;
            } else {
                ownerid= extras.getString("ownerid");
                petid= extras.getString("petid");
                update= extras.getString("update");
                status= extras.getString("add");
                pos= extras.getInt("position");

            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
            petid = (String) savedInstanceState.getSerializable("petid");
            update = (String) savedInstanceState.getSerializable("update");
            status = (String) savedInstanceState.getSerializable("add");
            pos = (Integer) savedInstanceState.getSerializable("position");



        }


        Cursor rs = myDB.getVacc(petid);
        rs.moveToFirst();

        if(rs.getCount() > 0 && status == null) {


            txtvaccinatedby.setVisibility(View.GONE);
            dateVacc.setVisibility(View.GONE);
            tbl1.setVisibility(View.GONE);
            txtvaccinatedby2.setVisibility(View.GONE);
            txtv4.setVisibility(View.GONE);




            btnUpdate.setVisibility(View.VISIBLE);
            btnVacc.setVisibility(View.GONE);

//            String id = rs.getString(rs.getColumnIndex(DatabaseHelper.VACCCOL_1));
//            String owner = rs.getString(rs.getColumnIndex(DatabaseHelper.VACCCOL_3));
            String petname =  rs.getString(rs.getColumnIndex(DatabaseHelper.VACCCOL_4));
            String specie = rs.getString(rs.getColumnIndex(DatabaseHelper.VACCCOL_5));
            String breed = rs.getString(rs.getColumnIndex(DatabaseHelper.VACCCOL_6));
            String gender = rs.getString(rs.getColumnIndex(DatabaseHelper.VACCCOL_7));

            String birth = rs.getString(rs.getColumnIndex(DatabaseHelper.VACCCOL_8));
            String color = rs.getString(rs.getColumnIndex(DatabaseHelper.VACCCOL_9));
            String feature = rs.getString(rs.getColumnIndex(DatabaseHelper.VACCCOL_10));
            String srcs = rs.getString(rs.getColumnIndex(DatabaseHelper.VACCCOL_11));
//            final String petid = rs.getString(rs.getColumnIndex(DatabaseHelper.VACCCOL_12));
            String status = rs.getString(rs.getColumnIndex(DatabaseHelper.VACCCOL_13));


            dateSurvey.setText(birth);
            txtpetname.setText(petname);

            chooseImg.setVisibility(View.GONE);



            species = ArrayAdapter.createFromResource(this, R.array.species, R.layout.support_simple_spinner_dropdown_item);
            if (specie != null) {
                int spinnerPosition = species.getPosition(specie);
                txtSpecie.setSelection(spinnerPosition);
            }

            final ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(this, R.array.breed_dod, R.layout.support_simple_spinner_dropdown_item);
            final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.breed_cat, R.layout.support_simple_spinner_dropdown_item);



            if(specie.equals("Cat")) {
                    txtbreed.setVisibility(View.VISIBLE);
                    strngbreed.setVisibility(View.VISIBLE);
                    if (breed != null) {
                        txtbreed.setAdapter(adapter2);
                        int spinnerPosition = adapter2.getPosition(breed);
                        txtbreed.setSelection(spinnerPosition);
                    }
            }else if(specie.equals("Dog"))
            {
                txtbreed.setVisibility(View.VISIBLE);
                strngbreed.setVisibility(View.VISIBLE);
                if (breed != null) {
                    txtbreed.setAdapter(adapter);
                    int spinnerPosition1 = adapter.getPosition(breed);
                    txtbreed.setSelection(spinnerPosition1);
                }
            }else if(specie.equals("Monkey"))
            {
                txtbreed.setVisibility(View.GONE);
                strngbreed.setVisibility(View.GONE);

            }




            sex = ArrayAdapter.createFromResource(this, R.array.gender, R.layout.support_simple_spinner_dropdown_item);
            if (gender != null) {
                int spinnerPosition3 = sex.getPosition(gender);
                txtGender.setSelection(spinnerPosition3);
            }



            sources = ArrayAdapter.createFromResource(this, R.array.source_pet, R.layout.support_simple_spinner_dropdown_item);


            if(srcs.equals("Indigenous"))
            {
                if (srcs != null) {
                    int spinnerPosition4 = sources.getPosition(srcs);
                    txtsource.setSelection(spinnerPosition4);
                }

                txtsourceplace.setVisibility(View.GONE);
            }else{

                String sr = "Introduced";

                int spinnerPosition5 = sources.getPosition(sr);
                txtsource.setSelection(spinnerPosition5);
                txtsourceplace.setVisibility(View.VISIBLE);
                txtsourceplace.setText(srcs);
            }


            colormarkings = ArrayAdapter.createFromResource(this, R.array.color_mark, R.layout.support_simple_spinner_dropdown_item);


            if(color.equals("White"))
            {

                    int spinnerPosition6 = colormarkings.getPosition(color);
                    txtColorMark.setSelection(spinnerPosition6);
                    othercolormark.setVisibility(View.GONE);
            }else if(color.equals("Black"))
            {

                int spinnerPosition6 = colormarkings.getPosition(color);
                txtColorMark.setSelection(spinnerPosition6);
                othercolormark.setVisibility(View.GONE);
            }else if(color.equals("Brown"))
            {

                int spinnerPosition6 = colormarkings.getPosition(color);
                txtColorMark.setSelection(spinnerPosition6);
                othercolormark.setVisibility(View.GONE);
            }else if(color.equals("Orange"))
            {

                int spinnerPosition6 = colormarkings.getPosition(color);
                txtColorMark.setSelection(spinnerPosition6);
                othercolormark.setVisibility(View.GONE);
            }else if(color.equals("Gray"))
            {

                int spinnerPosition6 = colormarkings.getPosition(color);
                txtColorMark.setSelection(spinnerPosition6);
                othercolormark.setVisibility(View.GONE);
            }else if(color.equals("Tricolor"))
            {

                int spinnerPosition6 = colormarkings.getPosition(color);
                txtColorMark.setSelection(spinnerPosition6);
                othercolormark.setVisibility(View.GONE);

            }
            else
            {

                String col = "Others";
                        int spinnerPosition7= colormarkings.getPosition(col);
                        txtColorMark.setSelection(spinnerPosition7);
                        othercolormark.setVisibility(View.VISIBLE);
                        othercolormark.setText(color);



            }


            txtdistinguish.setText(feature);


            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final String petname = txtpetname.getText().toString();
                    final String specie = txtSpecie.getSelectedItem().toString();
                    final String breed;


                    if(specie == "Monkey"){
                        breed = "N/A";
                    }else{
                        breed = txtbreed.getSelectedItem().toString();
                    }
                    final String other_breed;

                    final String gender = txtGender.getSelectedItem().toString();
                    final String vacc_by;




                    final String birthdate = dateSurvey.getText().toString();
                    final String agepet = age;
                    final String colormark = txtColorMark.getSelectedItem().toString();
                     String othercolors;
                    final String feat ;
                    final String dis  = txtdistinguish.getText().toString();
                    final String datevacc = txtxDateVacc.getText().toString();

                    if(datevacc.equals("NOT YET VACCINATED"))
                    {
                        vacc_by = "N/A";
                    }else{
                        vacc_by = txtvaccinatedby.getSelectedItem().toString();
                    }
                    final String stat = "alive";
                    final byte imgv[]  ;
                    final String nulla = "N/A";



                    if(imgView.getDrawable() == null)
                    {
                        imgv = nulla.getBytes();


                    }else{

                        imgv = imageViewToByte(imgView);

                    }


                    if(dis.equals("")){
                        feat = "";

                    }else{
                        feat = dis;
                    }

                    String src = "";
                    String source2 = txtsource.getSelectedItem().toString();
                    final String place =  txtsourceplace.getText().toString();

                    if(colormark.equals("Others"))
                    {
                        othercolors = othercolormark.getText().toString();

                    }else{
                        othercolors = colormark;
                    }

                    final String othercolor = othercolors;



                    if(breed.equals("Others"))
                    {
                        other_breed =  otherbreed.getText().toString();
                    }else{
                        other_breed = breed;
                    }


                    if(source2.equals("Introduced") ) {
                        final String sr = place;
                        src = sr;


                    }else if(source2.equals("Indigenous") ){

                        final String sr = source2;
                        src = sr;
                    }

                    final String souces = src;

                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, 0);
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    final String created_at = format1.format(cal.getTime());


                    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                    StringBuilder salt = new StringBuilder();
                    Random rnd = new Random();
                    while (salt.length() < 7) { // length of the random string.
                        int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                        salt.append(SALTCHARS.charAt(index));
                    }

                    final String end = salt.toString();


                    final byte imgv2[] = imgv;




                    // Build an AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(VaccinationActivity.this);

                    // Set a title for alert dialog
                    builder.setTitle("There's no going back.");

                    // Ask the final question
                    builder.setMessage("Do you want to save the update?");

                    // Set click listener for alert dialog buttons

                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch(which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    // User clicked the Yes button

                                    if (petname.equals("") || specie.equals("") || breed.equals("") || gender.equals("") || birthdate.equals("") ) {
                                        Toast.makeText(VaccinationActivity.this, "Check your input!"   , Toast.LENGTH_SHORT).show();
                                    }else{

                                        try {
                                            myDB.updateVaccination(petname,specie,other_breed,gender,birthdate,othercolor, feat,
                                                    souces,petid);
                                            Toast.makeText(VaccinationActivity.this, "Success!" , Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), PetActivity.class);
                                            intent.putExtra("ownerid", ownerid);
                                            intent.putExtra("petid", petid);

                                            startActivity(intent);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:


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




        EnableRuntimePermissionToAccessCamera();

        final ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(this, R.array.breed_dod, R.layout.support_simple_spinner_dropdown_item);
        final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.breed_cat, R.layout.support_simple_spinner_dropdown_item);
        final ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.breed_monkey, R.layout.support_simple_spinner_dropdown_item);
        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();
//                txtAge.setText("Age is: "+ calculateAge(myCalendar.getTimeInMillis()));
//                age = Integer.toString(calculateAge(myCalendar.getTimeInMillis()));
            }



        };

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                txtxDateVacc.setText(sdf.format(myCalendar.getTime()));
                updateLabel2();
//                txtAge.setText("Age is: "+ calculateAge(myCalendar.getTimeInMillis()));
//                age = Integer.toString(calculateAge(myCalendar.getTimeInMillis()));
            }

        };


        dateVacc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(VaccinationActivity.this, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();


            }

        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(VaccinationActivity.this);

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
                                Intent intent = new Intent(getApplicationContext(), PetActivity.class);
                                intent.putExtra("pos", pos);
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



        btndate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(VaccinationActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        btnVacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String petname = txtpetname.getText().toString();
                final String specie = txtSpecie.getSelectedItem().toString();
                final String breed;


                if(specie == "Monkey"){
                      breed = "N/A";
                }else{
                      breed = txtbreed.getSelectedItem().toString();
                }
                final String other_breed;

                final String gender = txtGender.getSelectedItem().toString();
                final String vacc_by;




                final String birthdate = dateSurvey.getText().toString();
                final String agepet = age;
                final String colormark = txtColorMark.getSelectedItem().toString();
                String othercolors;
                final String feat ;
                final String dis  = txtdistinguish.getText().toString();
                final String datevacc = txtxDateVacc.getText().toString();

                if(datevacc.equals("NOT YET VACCINATED"))
                {
                    vacc_by = "N/A";
                }else{
                    vacc_by = txtvaccinatedby.getSelectedItem().toString();
                }
                final String stat = "alive";
                 final byte imgv[]  ;
                 final String nulla = "N/A";



                if(imgView.getDrawable() == null)
                {
                    imgv = nulla.getBytes();


                }else{

                    imgv = imageViewToByte(imgView);

                }


                if(dis.equals("")){
                    feat = "";

                }else{
                    feat = dis;
                }

                 String src = "";
                 String source2 = txtsource.getSelectedItem().toString();
                final String place =  txtsourceplace.getText().toString();

                if(colormark.equals("Others"))
                {
                    othercolors = othercolormark.getText().toString();
                }else{
                    othercolors = colormark;
                }



                final String othercolor = othercolors;


                if(breed == "Others")
                {
                    other_breed =  otherbreed.getText().toString();
                }else{
                    other_breed = breed;
                }


                if(source2.equals("Introduced") ) {
                    final String sr = place;
                    src = sr;


                }else if(source2.equals("Indigenous") ){

                    final String sr = source2;
                    src = sr;
                }

                final String souces = src;

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 0);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());


                String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                StringBuilder salt = new StringBuilder();
                Random rnd = new Random();
                while (salt.length() < 7) { // length of the random string.
                    int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                    salt.append(SALTCHARS.charAt(index));
                }

                final String end = salt.toString();


                final byte imgv2[] = imgv;



                ctr = myDB.getCountPet(ownerid,specie);
                ctr++;

                first = specie.charAt(0);

                if (petid == null) {

                    String muni = myDB.getMuni(ownerid);
                    String brgy = myDB.getBrgy(ownerid);

                    if(muni.equals("La Trinidad"))
                    petid = String.valueOf((muni.charAt(0))) + muni.charAt(3);
                    else{
                        petid = String.valueOf((muni.charAt(0)));
                    }

                }

                   pet = petid + end;


                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(VaccinationActivity.this);

                // Set a title for alert dialog
                builder.setTitle("There's no going back.");

                // Ask the final question
                builder.setMessage("Do you have any pet/s to be vaccinated?");

                // Set click listener for alert dialog buttons

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                // User clicked the Yes button

                                if (petname.equals("") || specie.equals("") || breed.equals("") || gender.equals("") || birthdate.equals("") ) {
                                    Toast.makeText(VaccinationActivity.this, "Check your input!", Toast.LENGTH_SHORT).show();
                                }else{

                                    try {





                                                myDB.addVaccination(imgv,ownerid,petname,specie,other_breed,gender,birthdate,othercolor, feat,
                                                        souces,pet,stat,created_at);

                                            if(!petid.equals("") && !datevacc.equals("") && !vacc_by.equals("") && !created_at.equals("")) {

                                                myDB.addVaccinationDate(pet,datevacc,vacc_by.trim(),created_at);

                                                Intent intent = new Intent(getApplicationContext(), VaccinationActivity.class);
                                                intent.putExtra("ownerid", ownerid);
                                                intent.putExtra("petid", petid);
                                                startActivity(intent);

                                            }



                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                if (petname.equals("") || specie.equals("") || breed.equals("") || gender.equals("") || birthdate.equals("") ) {
                                    Toast.makeText(VaccinationActivity.this, "Check your input!" + petid , Toast.LENGTH_SHORT).show();
                                }else{

                                    try {



                                            myDB.addVaccination(imgv,ownerid,petname,specie,other_breed,gender,birthdate,othercolor, feat, souces,pet,stat,created_at);

                                            if(!petid.equals("") && !datevacc.equals("") && !vacc_by.equals("") && !created_at.equals("")) {

                                                myDB.addVaccinationDate(pet,datevacc,vacc_by.trim(),created_at);
                                                Intent intent = new Intent(getApplicationContext(), PetActivity.class);
                                                Toast.makeText(VaccinationActivity.this, "Sucess!" , Toast.LENGTH_SHORT).show();
                                                startActivity(intent);

                                            }



                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

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


        if(petid == null || ownerid != null)
        {

        txtsource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                String src = txtsourceplace.getText().toString();


                switch (selectedItem)
                {
                    case "Indigenous":
                        txtsourceplace.setVisibility(View.GONE);
                        break;
                    case "Introduced":
                        txtsourceplace.setVisibility(View.VISIBLE);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

            txtSpecie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedItem = adapterView.getItemAtPosition(i).toString();

                    switch (selectedItem)
                    {
                        case "Cat":
                            txtbreed.setVisibility(View.VISIBLE);
                            strngbreed.setVisibility(View.VISIBLE);
                            txtbreed.setAdapter(adapter2);
                            break;
                        case "Dog":
                            txtbreed.setVisibility(View.VISIBLE);
                            strngbreed.setVisibility(View.VISIBLE);
                            txtbreed.setAdapter(adapter);
                            break;
                        case "Monkey":
                            txtbreed.setVisibility(View.GONE);
                            strngbreed.setVisibility(View.GONE);
                            break;

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });





        txtColorMark.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                switch (selectedItem)
                {
                    case "Others":
                        othercolormark.setVisibility(View.VISIBLE);
                        break;
                    case "White":
                        othercolormark.setVisibility(View.GONE);
                        break;
                    case "Black":
                        othercolormark.setVisibility(View.GONE);
                        break;
                    case "Orange":
                        othercolormark.setVisibility(View.GONE);
                        break;
                    case "Gray":
                        othercolormark.setVisibility(View.GONE);
                        break;
                    case "Tricolor":
                        othercolormark.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        txtbreed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                switch (selectedItem)
                {
                    case "Others":
                        otherbreed.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        }




    }

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        String age = calculateAge(myCalendar.getTime());
        Toast.makeText(VaccinationActivity.this, "Check your input!"+age, Toast.LENGTH_SHORT).show();
        txtAge.setText(age);

        dateSurvey.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabel2() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txtxDateVacc.setText(sdf.format(myCalendar.getTime()));
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public String calculateAge(Date date){

        int year =0;
        int months = 0;
        int days = 0;

        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date.getTime());
        long current = System.currentTimeMillis();
        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(current);


        year = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        int currMonth = today.get(Calendar.MONTH);
        int birthMonth = dob.get(Calendar.MONTH);

        months = currMonth - birthMonth;

        if( months < 0)
        {
            year--;
            months = 12 - birthMonth + currMonth;
            if(today.get(Calendar.DATE) < dob.get(Calendar.DATE))
            {
                months--;
            }else if(months == 0 && today.get(Calendar.DATE) < dob.get(Calendar.DATE))
            {
                year--;
                months = 11;
            }
        }


        if(today.get(Calendar.DATE) > dob.get(Calendar.DATE))
        {
            days = today.get(Calendar.DATE) - dob.get(Calendar.DATE);

        }else if(today.get(Calendar.DATE) < dob.get(Calendar.DATE)){

            int now = today.get(Calendar.DAY_OF_MONTH);
            today.add(Calendar.MONTH, -1);
            days = today.getActualMaximum(Calendar.DAY_OF_MONTH) - dob.get(Calendar.DAY_OF_MONTH) + now;
        }
        else
        {
            days = 0;
            if (months == 12)
            {
                year++;
                months = 0;
            }
        }

        final String aged1 ;

        if(year > 0 && months > 0 && days > 0)
        {
            aged1 = year + " year/s, " + months + " month/s, " + days + " day/s";
        }else if(year == 0 && months > 0 && days > 0)
        {
            aged1 = months + " month/s, " + days + " day/s";
        }else if(year == 0 && months == 0 && days > 0){
            aged1 =  days + " day/s";
        }
        else if(year > 0 && months == 0 && days == 0){
            aged1 =  year + " year/s";
        }

        else if(year > 0 && months == 0 && days > 0){
            aged1 =  year + " year/s " + days + " day/s";
        }

        else if(year > 0 && months > 0 && days == 0){
            aged1 =  year + " year/s " + months + " month/s";
        }

        else if(year == 0 && months > 0 && days == 0){
            aged1 =  months + " month/s";
        }else{
            aged1 = "New Born";
        }




        return aged1;
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(VaccinationActivity.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(VaccinationActivity.this,"Permission Cancelled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
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

                    dateVacc.setEnabled(true);
                txtxDateVacc.setText("");
                txtvaccinatedby.setVisibility(View.VISIBLE);

                // Pirates are the best

                break;
            case R.id.rbn:
                if (checked)
                    txtxDateVacc.setText("NOT YET VACCINATED");
                txtvaccinatedby.setVisibility(View.GONE);
                txtvaccinatedby2.setVisibility(View.GONE);
                dateVacc.setEnabled(false);
                // Ninjas rule
                break;
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imageBitmap);
        }
    }

    public void EnableRuntimePermissionToAccessCamera() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(VaccinationActivity.this, Manifest.permission.CAMERA)) {

            Toast.makeText(VaccinationActivity.this, "PABDIS wants to access your Camera", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(VaccinationActivity.this, new String[]{Manifest.permission.CAMERA}, RequestPermissionCode);
        }

    }



    public static byte[] imageViewToByte(ImageView image) {

        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }
}
