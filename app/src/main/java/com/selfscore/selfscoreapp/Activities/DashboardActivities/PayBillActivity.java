package com.selfscore.selfscoreapp.Activities.DashboardActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.selfscore.selfscoreapp.Activities.DashboardActivities.MainActivity;
import com.selfscore.selfscoreapp.R;

public class PayBillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paybill);

        Toolbar myToolbar = (Toolbar) findViewById(R.id._toolbar);
        setSupportActionBar(myToolbar);

        ImageView back_button = (ImageView) findViewById(R.id.back_button);
        ImageView home_button = (ImageView) findViewById(R.id.home_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        View paynow_b = findViewById(R.id.paynow_button);
        paynow_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PayNowActivity.class);
                intent.putExtra("STATE","PAYNOW");
                startActivity(intent);
            }
        });

        View autopay_b = findViewById(R.id.autopay_button);
        autopay_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PayNowActivity.class);
                intent.putExtra("STATE","AUTOPAY");
                startActivity(intent);
            }
        });




    }





}
