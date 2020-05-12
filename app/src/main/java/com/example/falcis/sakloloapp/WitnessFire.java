package com.example.falcis.sakloloapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WitnessFire extends AppCompatActivity {


    private FirebaseFirestore mFirestore;
    private Button send;
    private ImageButton gps1;
    private EditText location;
    private EditText contact;
    private EditText comments;
    private String currentdate;
    private String fullName, gmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_witness_fire);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            fullName = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
            gmail = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail();
        }


        gps1 = (ImageButton) findViewById(R.id.gpslogo);
        gps1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAddress();
            }
        });


        location = (EditText) findViewById(R.id.txt_location);
        contact = (EditText) findViewById(R.id.txt_contact);
        comments = (EditText) findViewById(R.id.txt_comment);
        mFirestore = FirebaseFirestore.getInstance();

        send = (Button) findViewById(R.id.send_saklolo);
        location.addTextChangedListener(textwatch);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WitnessFire.this, "Sending Report", Toast.LENGTH_SHORT).show();

                Calendar calendar = Calendar.getInstance();
                currentdate = DateFormat.getInstance().format(calendar.getTime());
                String myLocation = location.getText().toString();
                String myComments = comments.getText().toString();
                String myContact =  contact.getText().toString();

                Map<String, String> userReport = new HashMap<>();
                userReport.put("location", myLocation);
                userReport.put("Comments", myComments);
                userReport.put("Date", currentdate);
                userReport.put("Contact_Number", myContact);
                userReport.put("Name", fullName);
                userReport.put("Email", gmail);
                userReport.put("Type", "WitnessFire");

                mFirestore.collection("Fire").add(userReport).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        findViewById(R.id.received).setVisibility(View.VISIBLE);
                        findViewById(R.id.txt_location).setVisibility(View.GONE);
                        findViewById(R.id.txt_comment).setVisibility(View.GONE);
                        findViewById(R.id.send_saklolo).setVisibility(View.GONE);
                        findViewById(R.id.gpslogo).setVisibility(View.GONE);
                        findViewById(R.id.txt_contact).setVisibility(View.GONE);

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                {
                                    try {
                                        sleep(2 * 1000);
                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(i);
                                    } catch (Exception ex) {
                                    }
                                }
                            }
                        };
                        thread.start();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error = e.getMessage();

                        Toast.makeText(WitnessFire.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public TextWatcher textwatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String location1 = location.getText().toString().trim();


            send.setEnabled(!location1.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void callAddress() {
        Intent intent = getIntent();
        String add = intent.getStringExtra("ADDRESS2");
        EditText adress1 = findViewById(R.id.txt_location);
        adress1.setText(add);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
