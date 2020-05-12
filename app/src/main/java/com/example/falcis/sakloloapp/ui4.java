package com.example.falcis.sakloloapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ui4 extends AppCompatActivity {

    private ImageButton button;
    private ImageButton button2;
    private ImageButton button3;
    private ImageButton button4;
    private String add2, name3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui4);

        Intent intent = getIntent();
        String add = intent.getStringExtra("ADDRESS1");
        name3 = intent.getStringExtra("NAME3");
        add2 = add;

        button = (ImageButton) findViewById(R.id.btn_crime);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCrime();
            }
        });

        button2 = (ImageButton) findViewById(R.id.btn_disaster);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisaster();
            }
        });

        button4 = (ImageButton) findViewById(R.id.btn_fire);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFire();
            }
        });

        button3 = (ImageButton) findViewById(R.id.btn_emergency);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmergency();
            }
        });
    }

    public void openCrime() {
        Intent intent = new Intent(this, WitnessCrime.class);
        intent.putExtra("ADDRESS2", add2);
        intent.putExtra("NAME4", name3);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    public void openDisaster() {
        Intent intent = new Intent(this, WitnessDisaster.class);
        intent.putExtra("ADDRESS2", add2);
        intent.putExtra("NAME4", name3);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    public void openEmergency() {
        Intent intent = new Intent(this, WitnessEmergency.class);
        intent.putExtra("ADDRESS2", add2);
        intent.putExtra("NAME4", name3);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    public void openFire() {
        Intent intent = new Intent(this, WitnessFire.class);
        intent.putExtra("ADDRESS2", add2);
        intent.putExtra("NAME4", name3);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
