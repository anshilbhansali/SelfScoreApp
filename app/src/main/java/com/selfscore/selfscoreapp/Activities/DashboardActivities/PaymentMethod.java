package com.selfscore.selfscoreapp.Activities.DashboardActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.selfscore.selfscoreapp.Adapters.DebitCardsAdapter;
import com.selfscore.selfscoreapp.Model.Model;
import com.selfscore.selfscoreapp.R;
import com.selfscore.selfscoreapp.SelfScoreApplication;

public class PaymentMethod extends AppCompatActivity {

    private Model model;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        model = ((SelfScoreApplication)this.getApplication()).getModel();

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


        setUpAddMethods();
        setUpPayMethods();

    }

    private void setUpPayMethods()
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        if(intent.hasExtra("REMOVE_CARD"))
        {
            model.removeDebitCard((int) intent.getExtras().get("REMOVE_CARD"));
        }

        DebitCardsAdapter adapter = new DebitCardsAdapter(this, model.getDebitCards());
        mRecyclerView.setAdapter(adapter);

    }

    private void setUpAddMethods()
    {
        final View addView = findViewById(R.id.add_view);
        addView.setVisibility(View.GONE);
        final View addPay = findViewById(R.id.add_pay_method);
        final View dividerLine = findViewById(R.id.divider_line);

        addPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView.setVisibility(View.VISIBLE);
                addPay.setVisibility(View.GONE);
                dividerLine.setVisibility(View.GONE);
            }
        });

        View addBank = findViewById(R.id.add_bank);
        View addDebit = findViewById(R.id.add_debitcard);

        addBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentMethod.this, "Unable to add Bank account right now", Toast.LENGTH_SHORT).show();
            }
        });

        addDebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(PaymentMethod.this, "Unable to add Debit card right now", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AddDebitCard.class);
                startActivity(intent);
            }
        });
    }



}
