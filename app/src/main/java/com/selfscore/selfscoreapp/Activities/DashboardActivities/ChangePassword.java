package com.selfscore.selfscoreapp.Activities.DashboardActivities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.selfscore.selfscoreapp.R;

public class ChangePassword extends AppCompatActivity {

    EditText current_p, new_p, confirm_new_p;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Toolbar myToolbar = (Toolbar) findViewById(R.id._toolbar);
        setSupportActionBar(myToolbar);

        ImageView back_button = (ImageView) findViewById(R.id.back_button);
        ImageView home_button = (ImageView) findViewById(R.id.home_button);
        TextView header_view = (TextView) findViewById(R.id.header_card);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)header_view.getLayoutParams();
        params.setMarginStart(100);
        header_view.setLayoutParams(params);
        header_view.setText("Change Password");

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

        Button change_passw = (Button) findViewById(R.id.change_passw_button);
        current_p = (EditText) findViewById(R.id.current_p);
        new_p = (EditText) findViewById(R.id.new_p);
        confirm_new_p = (EditText) findViewById(R.id.confirm_new_p);

        change_passw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //logic to check current password, and confirm new password


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Password changed!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
