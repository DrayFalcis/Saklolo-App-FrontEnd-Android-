package com.example.falcis.sakloloapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class ui5 extends AppCompatActivity {

    private ImageButton btncrime;
    private ImageButton btnemer;
    private String currentdate;
    private String currentdate1;
    private FirebaseFirestore mFirestore;
    private String add;
    private String fullName, gmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui5);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            fullName = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
            gmail = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail();
        }

        btncrime = (ImageButton) findViewById(R.id.btn_vcrime);
        btnemer = (ImageButton) findViewById(R.id.btn_vemergency);
        mFirestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        add = intent.getStringExtra("ADDRESS1");

        btncrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ui5.this, "Sending Report", Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance();
                currentdate = DateFormat.getInstance().format(calendar.getTime());


                Map<String, String> userReport1 = new HashMap<>();
                userReport1.put("Location", add);
                userReport1.put("Date", currentdate);
                userReport1.put("Type", "Crime");
                userReport1.put("Name", fullName);
                userReport1.put("Email", gmail);
                userReport1.put("Type", "VictimCrime");


                mFirestore.collection("victimCrimes").add(userReport1).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        findViewById(R.id.received2).setVisibility(View.VISIBLE);
                        findViewById(R.id.btn_vemergency).setVisibility(View.GONE);
                        findViewById(R.id.btn_vcrime).setVisibility(View.GONE);

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                {
                                    try {
                                        sleep(2 * 1000);
                                        Intent i = new Intent(getApplicationContext(), MapsActivity.class);
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

                        Toast.makeText(ui5.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnemer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ui5.this, "Sending Report", Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance();
                currentdate = DateFormat.getInstance().format(calendar.getTime());


                Map<String, String> userReport1 = new HashMap<>();
                userReport1.put("Location", add);
                userReport1.put("Date", currentdate);
                userReport1.put("Type", "Emergency");
                userReport1.put("Name", fullName);
                userReport1.put("Email", gmail);
                userReport1.put("Type", "VictimEmergency");


                mFirestore.collection("victimEmergency").add(userReport1).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        findViewById(R.id.received2).setVisibility(View.VISIBLE);
                        findViewById(R.id.btn_vemergency).setVisibility(View.GONE);
                        findViewById(R.id.btn_vcrime).setVisibility(View.GONE);

                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                {
                                    try {
                                        sleep(2 * 1000);
                                        Intent i = new Intent(getApplicationContext(), MapsActivity.class);
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

                        Toast.makeText(ui5.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
