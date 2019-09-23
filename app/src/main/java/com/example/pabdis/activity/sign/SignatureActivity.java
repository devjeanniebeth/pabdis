package com.example.pabdis.activity.sign;

import android.Manifest;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.helper.SessionManager;
import com.github.gcacace.signaturepad.views.SignaturePad;

public class SignatureActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private SignaturePad mSignaturePad;
    private Button mClearButton;
    private Button mSaveButton;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);







    }




}
