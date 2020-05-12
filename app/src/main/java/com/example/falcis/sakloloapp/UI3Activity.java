package com.example.falcis.sakloloapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class UI3Activity extends AppCompatActivity {

    private ImageButton button;
    private ImageButton button1;
    private String add1;
    private String name2;
    private String fullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui3);

        button1 = (ImageButton) findViewById(R.id.btn_victim);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVictim();
            }
        });

        button = (ImageButton) findViewById(R.id.btn_witness);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWitness();
            }
        });

        Intent intent = getIntent();
        String add = intent.getStringExtra("ADDRESS");
        add1 = add;

        Intent intent1 = getIntent();
        name2 = intent1.getStringExtra("NAME1");


        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            fullName = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();

        }
    }

    private void openVictim() {
        Intent intent = new Intent(this, ui5.class);
        intent.putExtra("ADDRESS1",add1);
        intent.putExtra("NAME3", name2);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    public void openWitness(){
        Intent intent = new Intent(this, ui4.class);
        intent.putExtra("ADDRESS1",add1);
        intent.putExtra("NAME3", name2);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }



    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
