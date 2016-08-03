package com.selfscore.selfscoreapp.Activities.DashboardActivities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.selfscore.selfscoreapp.R;
import com.selfscore.selfscoreapp.Utils.ShareDialog;

public class EarnCashActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earn_cash);

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

        Button email_b = (Button) findViewById(R.id.email_button);
        email_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InviteFriendsActivity.class);
                startActivity(intent);
            }
        });

        View share_link = findViewById(R.id.share_link);
        final ShareDialog shareDialog = new ShareDialog();
        share_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.show(getSupportFragmentManager(), "ShareDialog");
            }
        });






    }
}
