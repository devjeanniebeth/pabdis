package com.example.pabdis.activity.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.survey.CarabaoActivity;
import com.example.pabdis.activity.survey.GoatActivity;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class VaccinationActivity extends AppCompatActivity {


    EditText otherbreed, othercolormark, txtpetname, txtdistinguish,txtsourceplace;
    TextView dateSurvey,txtAge, txtxDateVacc,strngbreed;
    Button btndate, chooseImg, btnVacc, dateVacc;
    FloatingActionButton skip;
    final Calendar myCalendar = Calendar.getInstance();
    ImageView imgView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public  static final int RequestPermissionCode  = 1 ;
    Spinner txtbreed, txtGender, txtSpecie, txtColorMark, txtvaccinatedby,txtsource;
    DatabaseHelper myDB;
    String age,ownerid, petid, vacc;
    Integer position, year, ctr;
    Character first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacc);

        myDB = new DatabaseHelper(getApplicationContext());
        dateSurvey = findViewById(R.id.txtdatesurvey);
        btndate = findViewById(R.id.btnDate);
        btnVacc = findViewById(R.id.btnVacc);

        dateVacc = findViewById(R.id.btnDateVacc);


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
            } else {
                ownerid= extras.getString("ownerid");
                petid= extras.getString("petid");
            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
            petid = (String) savedInstanceState.getSerializable("petid");
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
                txtAge.setText("Age is: "+ calculateAge(myCalendar.getTimeInMillis()));
                age = Integer.toString(calculateAge(myCalendar.getTimeInMillis()));
            }

        };

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel2();
                txtAge.setText("Age is: "+ calculateAge(myCalendar.getTimeInMillis()));
                age = Integer.toString(calculateAge(myCalendar.getTimeInMillis()));
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
                final String vacc_by = txtvaccinatedby.getSelectedItem().toString();
                final String birthdate = txtxDateVacc.getText().toString();
                final String agepet = age;
                final String colormark = txtColorMark.getSelectedItem().toString();
                final String othercolor;
                final String feat = txtdistinguish.getText().toString();
                final String datevacc = txtxDateVacc.getText().toString();
                final String stat = "alive";

                 String src = "";
                 String source2 = txtsource.getSelectedItem().toString();
                final String place =  txtsourceplace.getText().toString();

                if(colormark == "Others")
                {
                    othercolor = othercolormark.getText().toString();
                }else{
                    othercolor = colormark;
                }

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
                cal.add(Calendar.DATE, 1);
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



                ctr = myDB.getCountPet(ownerid,specie);
                ctr++;

                first = specie.charAt(0);
                final String pet = petid + end;


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

                                if (petname.equals("") || specie.equals("") || breed.equals("") || gender.equals("") || birthdate.equals("") || agepet.equals("") ) {
                                    Toast.makeText(VaccinationActivity.this, "Check your input!"+vacc, Toast.LENGTH_SHORT).show();
                                }else{

                                    try {


                                        if(!ownerid.equals("") && !petname.equals("") && !specie.equals("") && !other_breed.equals("") &&
                                                !gender.equals("") && !birthdate.equals("") && !othercolor.equals("") && !feat.equals("") )
                                        {



                                                myDB.addVaccination(imageViewToByte(imgView),ownerid,petname,specie,other_breed,gender,birthdate,othercolor, feat,
                                                        souces,pet,stat,created_at);

                                            if(!petid.equals("") && !datevacc.equals("") && !vacc_by.equals("") && !created_at.equals("")) {

                                                myDB.addVaccinationDate(pet,datevacc,vacc_by.trim(),created_at);

                                                Intent intent = new Intent(getApplicationContext(), VaccinationActivity.class);
                                                intent.putExtra("ownerid", ownerid);
                                                intent.putExtra("petid", petid);
                                                startActivity(intent);

                                            }

                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                if (petname.equals("") || specie.equals("") || breed.equals("") || gender.equals("") || birthdate.equals("") || agepet.
                                        // User clicked the No button
                                                equals("") ) {
                                    Toast.makeText(VaccinationActivity.this, "Check your input!", Toast.LENGTH_SHORT).show();
                                }else{

                                    try {

                                        if(!ownerid.equals("") && !petname.equals("") && !specie.equals("") && !other_breed.equals("") &&
                                                !gender.equals("") && !birthdate.equals("") && !othercolor.equals("") && !feat.equals("") )
                                        {

                                            myDB.addVaccination(imageViewToByte(imgView),ownerid,petname,specie,other_breed,gender,birthdate,othercolor, feat, souces,pet,stat,created_at);

                                            if(!petid.equals("") && !datevacc.equals("") && !vacc_by.equals("") && !created_at.equals("")) {

                                                myDB.addVaccinationDate(pet,datevacc,vacc_by.trim(),created_at);
                                                Intent intent = new Intent(getApplicationContext(), PetActivity.class);
                                                Toast.makeText(VaccinationActivity.this, "Sucess!" , Toast.LENGTH_SHORT).show();
                                                startActivity(intent);

                                            }

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

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
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

    int calculateAge(long date){
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if(today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)){
            age--;
        }
        return age;
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
                    // Pirates are the best
                    vacc = "1";
                break;
            case R.id.rbn:
                if (checked)
                    vacc = "2";
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
