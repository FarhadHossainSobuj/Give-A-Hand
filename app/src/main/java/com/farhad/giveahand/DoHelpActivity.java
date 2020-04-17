package com.farhad.giveahand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DoHelpActivity extends AppCompatActivity {
    private String location;
    private TextView tv_location, tv_date, tv_phone;
    private Button btn_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_help);
        setTitle(R.string.do_help_text);

        location = getIntent().getStringExtra("location");
        /*tv_location = findViewById(R.id.location);
        tv_date = findViewById(R.id.date);
        tv_phone = findViewById(R.id.tv_phone_no);*/
        btn_contact = findViewById(R.id.btn_contact_to);


        /*if(location.equals("")){
            tv_location.setText("Dhaka");
        } else {
            tv_location.setText("" + location);
        }
        tv_date.setText("" + getIntent().getStringExtra("date"));*/

        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                /*Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tv_phone.getText().toString(), null));
                startActivity(intent);*/
            }
        });
    }

}
