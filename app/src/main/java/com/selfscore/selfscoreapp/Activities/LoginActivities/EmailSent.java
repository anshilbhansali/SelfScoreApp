package com.selfscore.selfscoreapp.Activities.LoginActivities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.selfscore.selfscoreapp.Activities.LoginActivities.LoginActivity;
import com.selfscore.selfscoreapp.Activities.LoginActivities.UnableLogIn;
import com.selfscore.selfscoreapp.R;

public class EmailSent extends AppCompatActivity {

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sent);

        TextView checkMessage = (TextView) findViewById(R.id.check_message);
        Intent intent = getIntent();
        String checkUsernameMsg="", checkPasswordMsg="";
        checkUsernameMsg = intent.getStringExtra("getUsernameMsg");
        checkPasswordMsg = intent.getStringExtra("getPasswordMsg");

        if(checkUsernameMsg == null)
        {
            checkMessage.setText(checkPasswordMsg);
        }
        else
        {
            checkMessage.setText(checkUsernameMsg);
        }

        activity = this;

        Button backToLogIn = (Button) findViewById(R.id.back_login_button);
        Button Resend = (Button) findViewById(R.id.resend_button);

        backToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
            }
        });

        Resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UnableLogIn.class);
                startActivity(intent);
            }
        });
    }
}
