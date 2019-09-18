package com.example.pabdis.activity.updates;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.survey.FisheryActivity;

public class ApiaryActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText edtColonyNum,edtProdH,edtTotalIncome;
    String ownerid;
    Integer pos;
    CheckBox withapiary;
    Button btnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_apiary);
        myDB = new DatabaseHelper(getApplicationContext());



        edtColonyNum = findViewById(R.id.edtColonyNum);
        edtProdH = findViewById(R.id.edtProdH);
        edtTotalIncome = findViewById(R.id.edtTotalIncome);
        btnUpdate = findViewById(R.id.btnUpdate);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerid= null;
                pos = null;
            } else {
                ownerid= extras.getString("ownerid");
                pos= extras.getInt("position");
            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
            pos = (Integer) savedInstanceState.getSerializable("position");
        }




        Cursor rss = myDB.getApiary(ownerid);
        rss.moveToFirst();

        if(rss.getCount() > 0)
        {

            btnUpdate.setVisibility(View.VISIBLE);

            edtColonyNum.setEnabled(true);
            edtProdH.setEnabled(true);
            edtTotalIncome.setEnabled(true);

            String total_a = rss.getString(rss.getColumnIndex(DatabaseHelper.SURVEY8COL_4));
            String total_p = rss.getString(rss.getColumnIndex(DatabaseHelper.SURVEY8COL_5));
            String total_i = rss.getString(rss.getColumnIndex(DatabaseHelper.SURVEY8COL_6));

            edtColonyNum.setText(total_a);
            edtProdH.setText(total_p);
            edtTotalIncome.setText(total_i);


            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final String a_col = edtColonyNum.getText().toString();
                    final String a_prod = edtProdH.getText().toString();
                    final String a_inc = edtTotalIncome.getText().toString();

                    // Build an AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(ApiaryActivity.this);

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

                                        if (a_col.equals("") || a_prod.equals("") || a_inc.equals("")) {
                                            Toast.makeText(ApiaryActivity.this, "Check your input!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            myDB.updateApiary(ownerid, a_col, a_prod, a_inc);
                                            Toast.makeText(ApiaryActivity.this, "Success!" + ownerid, Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), ListUpdateActivity.class);
                                            intent.putExtra("ownerid", ownerid);
                                            intent.putExtra("position", pos);
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










    }


    @Override
    public void onBackPressed() {
        if(pos != null)
        {
            Intent i = new Intent(ApiaryActivity.this, ListUpdateActivity.class);
            i.putExtra("position", pos);
            startActivity(i);
//        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
        }
    }


}