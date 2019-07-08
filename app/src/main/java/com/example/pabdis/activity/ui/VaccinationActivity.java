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
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.survey.HouseholdActivity;
import com.example.pabdis.activity.survey.SwineActivity;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class VaccinationActivity extends AppCompatActivity {


    EditText otherbreed, othercolormark, txtpetname, txtcolor;
    TextView dateSurvey,txtAge, txtxDateVacc,strngbreed;
    Button btndate, chooseImg, btnVacc, dateVacc;
    final Calendar myCalendar = Calendar.getInstance();
    ImageView imgView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public  static final int RequestPermissionCode  = 1 ;
    Spinner txtbreed, txtGender, txtSpecie, txtColorMark, txtvaccinatedby;
    DatabaseHelper myDB;
    String age,ownerid, petid;
    Integer position, year, ctr;
    Character first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacc);


        dateSurvey = findViewById(R.id.txtdatesurvey);
        btndate = findViewById(R.id.btnDate);
        btnVacc = findViewById(R.id.btnVacc);

        dateVacc = findViewById(R.id.btnDateVacc);


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
        txtcolor = findViewById(R.id.txtcolor);
        otherbreed.setVisibility(View.GONE);
        othercolormark.setVisibility(View.GONE);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerid= null;
                petid = null;
            } else {
                ownerid= extras.getString("ownerid");
                petid= extras.getString("ownerid");
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
                      breed = "";
                }else{
                      breed = txtbreed.getSelectedItem().toString();
                }
                final String other_breed;
                final String gender = txtGender.getSelectedItem().toString();
                final String vacc_by = txtvaccinatedby.getSelectedItem().toString();
                final String birthdate = dateSurvey.getText().toString();
                final String agepet = age;
                final String colormark = txtColorMark.getSelectedItem().toString();
                final String othercolor;
                final String datevacc = dateSurvey.getText().toString();

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




                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());

                String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                StringBuilder salt = new StringBuilder();
                Random rnd = new Random();
                while (salt.length() < 5) { // length of the random string.
                    int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                    salt.append(SALTCHARS.charAt(index));
                }

                final String end = salt.toString();
                first = specie.charAt(0);
                final String pet = petid + first + "-" + end;


                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(VaccinationActivity.this);

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

                                if (petname.equals("") || specie.equals("") || breed.equals("") || gender.equals("") || birthdate.equals("") || agepet.equals("") ) {
                                    Toast.makeText(VaccinationActivity.this, "Check your input!"+  datevacc , Toast.LENGTH_SHORT).show();
                                }else{

                                    try {
                                        myDB.addVaccination(ownerid.trim(),petname.trim(),specie.trim(), other_breed.trim(),gender.trim(),birthdate.trim(),othercolor.trim(),petid,created_at);
                                        myDB.addVaccinationDate(petid,datevacc,vacc_by.trim(),created_at);

                                        // Build an AlertDialog
                                        AlertDialog.Builder builder = new AlertDialog.Builder(VaccinationActivity.this);

                                        // Set a title for alert dialog
                                        builder.setTitle("There's no going back.");

                                        // Ask the final question
                                        builder.setMessage("Do you have another pet to be registered?");

                                        // Set click listener for alert dialog buttons
                                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                switch(which){
                                                    case DialogInterface.BUTTON_POSITIVE:
                                                        // User clicked the Yes button

                                                        Toast.makeText(VaccinationActivity.this, "Success!" , Toast.LENGTH_LONG).show();
//                                        showDebugDBAddressLogToast(MainActivity.this);
                                                        Intent intent = new Intent(getApplicationContext(), VaccinationActivity.class);
                                                        intent.putExtra("ownerid", ownerid.trim());
                                                        startActivity(intent);


                                                        break;

                                                    case DialogInterface.BUTTON_NEGATIVE:
                                                        // User clicked the No button

                                                        Toast.makeText(VaccinationActivity.this, "Success!" , Toast.LENGTH_LONG).show();
//                                        showDebugDBAddressLogToast(MainActivity.this);
                                                         intent = new Intent(getApplicationContext(), MainActivity.class);
                                                        intent.putExtra("ownerid", ownerid.trim());
                                                        startActivity(intent);
                                                        break;
                                                }
                                            }
                                        };

                                        // Set the alert dialog yes button click listener
                                        builder.setPositiveButton("Yes", dialogClickListener);

                                        // Set the alert dialog no button click listener
                                        builder.setNegativeButton("No",dialogClickListener);

                                        AlertDialog dialog2 = builder.create();
                                        // Display the alert dialog on interface
                                        dialog2.show();






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
        bitmap.compress(Bitmap.CompressFormat.PNG, 500, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }
}
