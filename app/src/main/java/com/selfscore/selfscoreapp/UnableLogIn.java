package com.selfscore.selfscoreapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.zip.Inflater;

public class UnableLogIn extends AppCompatActivity {

    private Button forgot_username, forgot_password;

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unable_log_in);

        activity = this;

        forgot_username = (Button)findViewById(R.id.forgot_username_button);
        forgot_password = (Button)findViewById(R.id.forgot_password_button);

        final View forgot_u_layout, forgot_passw_layout;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        forgot_u_layout = inflater.inflate(R.layout.forgot_username, null);
        forgot_passw_layout = inflater.inflate(R.layout.forgot_password, null);

        Button getButton = (Button) forgot_u_layout.findViewById(R.id.get_button);
        Button resetButton = (Button) forgot_passw_layout.findViewById(R.id.reset_button);

        forgot_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(forgot_u_layout);
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(forgot_passw_layout);
            }
        });

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, EmailSent.class);
                startActivity(intent);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, EmailSent.class);
                startActivity(intent);
            }
        });

    }
}
