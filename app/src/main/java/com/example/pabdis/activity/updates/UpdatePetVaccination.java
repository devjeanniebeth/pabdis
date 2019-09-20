package com.example.pabdis.activity.updates;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.helper.SessionManager;

public class UpdatePetVaccination extends AppCompatActivity {

    DatabaseHelper mydb;
    private SessionManager session;
    private ProgressDialog pDialog;
    Button btnDateVacc,btnUpdate;
    TextView txtxDateVacc,txtvaccinatedby2;
    Spinner txtvaccinatedby;
    String petid;
    Integer position;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petvacc);
        mydb = new DatabaseHelper(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        btnDateVacc = findViewById(R.id.btnDateVacc);
        btnUpdate = findViewById(R.id.btnUpdate);
        txtxDateVacc = findViewById(R.id.txtxDateVacc);
        txtvaccinatedby2 = findViewById(R.id.txtvaccinatedby2);
        txtvaccinatedby = findViewById(R.id.txtvaccinatedby);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                petid= null;
                position = null;
            } else {
                petid= extras.getString("petid");
                position= extras.getInt("position");

            }
        } else {
            petid= (String) savedInstanceState.getSerializable("petid");
            position= (Integer) savedInstanceState.getSerializable("position");


        }



        Cursor rs = mydb.getVacc(petid);
        rs.moveToFirst();











    }


}
