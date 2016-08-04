package com.selfscore.selfscoreapp.Activities.DashboardActivities;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.selfscore.selfscoreapp.Model.Model;
import com.selfscore.selfscoreapp.R;
import com.selfscore.selfscoreapp.SelfScoreApplication;

public class InviteFriendsActivity extends AppCompatActivity {

    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);

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

        model = ((SelfScoreApplication) this.getApplication()).getModel();

        setEmailInvitationListener();

    }

    private void setEmailInvitationListener()
    {
        final EditText friends_name_view = (EditText) findViewById(R.id.friends_name);
        final EditText friends_email_view = (EditText) findViewById(R.id.friends_email);

        Button send_invitation = (Button) findViewById(R.id.send_inv_button);

        send_invitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String friends_name = friends_name_view.getText().toString();
                String friends_email = friends_email_view.getText().toString();

                friends_email_view.setError(null);
                friends_name_view.setError(null);

                boolean cancel = false;

                if(TextUtils.isEmpty(friends_name))
                {
                    friends_name_view.setError("Field is required");
                    cancel = true;
                }

                if(TextUtils.isEmpty(friends_email))
                {
                    friends_email_view.setError("Field is required");
                    cancel = true;
                }

                if(!cancel)
                {
                    sendEmail(friends_name, friends_email);
                }

            }
        });
    }

    private void sendEmail(String name, String email)
    {
        String[] TO = {email};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SelfScore Invitation");

        String user = model.getUser().getName();
        String link = model.getUser().getReferral_link();
        String msg = "Hey "+name+"! "+user+" just invited you to apply for a credit card " +
                "at SelfScore. Use this link to apply: "+link+" \nand you both will get $30 statement credit!";

        emailIntent.putExtra(Intent.EXTRA_TEXT, msg);

        try {
            startActivityForResult(Intent.createChooser(emailIntent, "Send email via..."), 10);

        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 10)
        {
            //email sent, go to dashboard
            //Toast.makeText(getApplicationContext(), "Email sent!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}
