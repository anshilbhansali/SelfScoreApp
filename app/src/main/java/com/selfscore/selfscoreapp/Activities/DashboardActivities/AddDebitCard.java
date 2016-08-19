package com.selfscore.selfscoreapp.Activities.DashboardActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.selfscore.selfscoreapp.Model.DebitCard;
import com.selfscore.selfscoreapp.Model.Model;
import com.selfscore.selfscoreapp.R;
import com.selfscore.selfscoreapp.SelfScoreApplication;

import java.util.ArrayList;

public class AddDebitCard extends AppCompatActivity {

    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debit_card);

        model = ((SelfScoreApplication) this.getApplication()).getModel();

        //TOOL BAR setup
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

        View continue_b = findViewById(R.id.continue_button);
        continue_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryAddAccount();
            }
        });
    }

    private void tryAddAccount()
    {
        EditText cardNum1 = (EditText) findViewById(R.id.card_num1);
        EditText cardNum2 = (EditText) findViewById(R.id.card_num2);
        EditText cardNum3 = (EditText) findViewById(R.id.card_num3);
        EditText cardNum4 = (EditText) findViewById(R.id.card_num4);

        EditText expDateMonth = (EditText) findViewById(R.id.exp_date_m);
        EditText expDateYear = (EditText) findViewById(R.id.exp_date_y);

        EditText secCode = (EditText) findViewById(R.id.sec_code);

        EditText streetAddr = (EditText) findViewById(R.id.street_addr);
        EditText floor = (EditText) findViewById(R.id.floor);
        EditText zipCode = (EditText) findViewById(R.id.zip_code);

        cardNum1.setError(null);
        cardNum2.setError(null);
        cardNum3.setError(null);
        cardNum4.setError(null);
        expDateMonth.setError(null);
        expDateYear.setError(null);
        secCode.setError(null);
        streetAddr.setError(null);
        zipCode.setError(null);

        if(checkEmpty(cardNum1))
            return;
        if(checkEmpty(cardNum2))
            return;
        if(checkEmpty(cardNum3))
            return;
        if(checkEmpty(cardNum4))
            return;

        String cardnum1, cardnum2, cardnum3, cardnum4;
        cardnum1 = (cardNum1.getText().toString());
        cardnum2 = (cardNum2.getText().toString());
        cardnum3 = (cardNum3.getText().toString());
        cardnum4 = (cardNum4.getText().toString());


        if(checkEmpty(expDateMonth))
            return;
        if(checkEmpty(expDateYear))
            return;
        if(checkEmpty(secCode))
            return;

        int expmonth, expyear, sec;
        expmonth = Integer.parseInt(expDateMonth.getText().toString());
        expyear = Integer.parseInt(expDateYear.getText().toString());
        sec = Integer.parseInt(secCode.getText().toString());

        if(checkEmpty(streetAddr))
            return;
        if(checkEmpty(zipCode))
            return;

        String streetaddr, appt, zip;
        streetaddr = streetAddr.getText().toString();
        appt = floor.getText().toString();
        zip = zipCode.getText().toString();

        DebitCard debitCard = new DebitCard(cardnum1, cardnum2, cardnum3, cardnum4, expmonth, expyear, sec, streetaddr, appt, zip);
        model.addDebitCard(debitCard);

        Intent intent = new Intent(getApplicationContext(), PaymentMethod.class);
        startActivity(intent);
    }

    private boolean checkEmpty(EditText view)
    {
        if(view.getText().toString().isEmpty())
        {
            view.setError("Field is required");
            return true;
        }
        return false;
    }
}
