package com.selfscore.selfscoreapp.Activities.DashboardActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.selfscore.selfscoreapp.Adapters.PurchasesAdapter;
import com.selfscore.selfscoreapp.Model.Model;
import com.selfscore.selfscoreapp.Model.Purchase;
import com.selfscore.selfscoreapp.R;
import com.selfscore.selfscoreapp.SelfScoreApplication;

import java.util.Collections;
import java.util.List;

public class MyPurchasesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private Model model;

    private SearchView searchView;
    private View date_button, descr_button, amount_button;
    private View arrow1, arrow2, arrow3;
    private boolean date_flag, descr_flag, amount_flag;

    private EditText date1, date2;
    private View dates_show, dates_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_purchases);

        model = ((SelfScoreApplication) this.getApplication()).getModel();

        setUpToolbar();

        setPurchases();

        View pdf_b = findViewById(R.id.pdf_statements);
        pdf_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "PDFs not ready yet", Toast.LENGTH_SHORT).show();
            }
        });

        searchView = (SearchView) findViewById(R.id.search_view);
        configureSearch();

        date_button = findViewById(R.id.date_button);
        descr_button = findViewById(R.id.description_button);
        amount_button = findViewById(R.id.amount_button);

        arrow1 = date_button.findViewById(R.id.arrow_down1);
        arrow2 = descr_button.findViewById(R.id.arrow_down2);
        arrow3 = amount_button.findViewById(R.id.arrow_down3);

        configureSort();


        date1 = (EditText) findViewById(R.id.date1);
        date2 = (EditText) findViewById(R.id.date2);

        dates_show = findViewById(R.id.dates_show);
        dates_close = findViewById(R.id.dates_close);
        setUpDatesFilter();

    }

    private void setUpDatesFilter()
    {
        date1.setVisibility(View.INVISIBLE);
        date2.setVisibility(View.INVISIBLE);
        dates_close.setVisibility(View.GONE);

        dates_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //show date range
                date1.setVisibility(View.VISIBLE);
                date2.setVisibility(View.VISIBLE);
                dates_close.setVisibility(View.VISIBLE);
                date1.setText("");
                date2.setText("");

                //hide
                dates_show.setVisibility(View.GONE);
            }
        });

        dates_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide date range
                date1.setVisibility(View.INVISIBLE);
                date2.setVisibility(View.INVISIBLE);
                dates_close.setVisibility(View.GONE);

                //show
                dates_show.setVisibility(View.VISIBLE);

                //show normal all purchases
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        setDateListeners();

    }

    private void setDateListeners()
    {
        date1.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.v("ACTION ID ", String.valueOf(actionId));

                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
                    Log.v("DONE "," DATE1 IS PRESSED");

                    String d = date1.getText().toString();
                    if(!isValidDateFormat(d))
                    {
                        date1.setError("Please enter valid date");
                    }
                }

                return false;
            }
        });

        date2.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE)

                {
                    Log.v("DONE ","DATE2 IS PRESSED");

                    String d1 = date1.getText().toString();
                    String d2 = date2.getText().toString();
                    if(!isValidDateFormat(d2))
                    {
                        date2.setError("Please enter valid date");
                    }

                    if(isValidDateFormat(d2) && isValidDateFormat(d1))
                        extractDates(d1, d2);

                }

                return false;
            }
        });
    }

    private void extractDates(String d1, String d2)
    {
        Log.v("EXTRACTED 1 ", d1.split("/")[0] + "/ "+ d1.split("/")[1]);

        int day1 = Integer.parseInt(d1.split("/")[1]);
        int month1 = Integer.parseInt(d1.split("/")[0]);
        int year1 = Integer.parseInt(d1.split("/")[2]);

        int day2 = Integer.parseInt(d2.split("/")[1]);
        int month2 = Integer.parseInt(d2.split("/")[0]);
        int year2 = Integer.parseInt(d2.split("/")[2]);

        extractPurchases(day1, day2, month1, month2, year1, year2);

    }

    private void extractPurchases(int d1, int d2, int m1, int m2, int y1, int y2)
    {
        List<Purchase> filteredPurchases = model.getFilteredByDates(d1, d2, m1, m2, y1, y2);

        PurchasesAdapter filteredPurchasesAdapter = new PurchasesAdapter(getApplicationContext(), filteredPurchases);
        mRecyclerView.setAdapter(filteredPurchasesAdapter);

    }

    private boolean isValidDateFormat(String date)
    {
        if(date.isEmpty())
            return false;

        if(date.split("/").length != 3)
            return false;


        return true;
    }




    private void configureSort()
    {
        arrow1.setVisibility(View.VISIBLE);
        arrow2.setVisibility(View.INVISIBLE);
        arrow3.setVisibility(View.INVISIBLE);

        date_flag = false;
        descr_flag = false;
        amount_flag = false;

        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //display arrow
                arrow1.setVisibility(View.VISIBLE);
                arrow2.setVisibility(View.INVISIBLE);
                arrow3.setVisibility(View.INVISIBLE);

                //set to default state
                descr_flag = false;
                amount_flag = false;

                //for toggle and reverse the list
                List<Purchase> sortedbyDate = model.getSortedByDate();
                if(date_flag)
                {
                    Log.v("DATE FLAG: ", "TRUE");
                    Collections.reverse(sortedbyDate);
                    date_flag = false;
                }
                else
                {
                    Log.v("DATE FLAG: ", "FALSE");
                    date_flag = true;
                }

                PurchasesAdapter sortedPurchasesAdapter = new PurchasesAdapter(getApplicationContext(), sortedbyDate);
                mRecyclerView.setAdapter(sortedPurchasesAdapter);
            }
        });

        descr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrow2.setVisibility(View.VISIBLE);
                arrow1.setVisibility(View.INVISIBLE);
                arrow3.setVisibility(View.INVISIBLE);

                //set to default state
                date_flag = false;
                amount_flag = false;

                List<Purchase> sortedbyName = model.getSortedByName();
                if(descr_flag)
                {
                    Log.v("DATE FLAG: ", "TRUE");
                    Collections.reverse(sortedbyName);
                    descr_flag = false;
                }
                else
                {
                    Log.v("DATE FLAG: ", "FALSE");
                    descr_flag = true;
                }

                PurchasesAdapter sortedPurchasesAdapter = new PurchasesAdapter(getApplicationContext(), sortedbyName);
                mRecyclerView.setAdapter(sortedPurchasesAdapter);
            }
        });

        amount_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrow3.setVisibility(View.VISIBLE);
                arrow1.setVisibility(View.INVISIBLE);
                arrow2.setVisibility(View.INVISIBLE);

                //set to default state
                date_flag = false;
                descr_flag = false;

                List<Purchase> sortedbyAmount = model.getSortedByAmount();
                if(amount_flag)
                {
                    Log.v("DATE FLAG: ", "TRUE");
                    Collections.reverse(sortedbyAmount);
                    amount_flag = false;
                }
                else
                {
                    Log.v("DATE FLAG: ", "FALSE");
                    amount_flag = true;
                }

                PurchasesAdapter sortedPurchasesAdapter = new PurchasesAdapter(getApplicationContext(), sortedbyAmount);
                mRecyclerView.setAdapter(sortedPurchasesAdapter);
            }
        });
    }

    private void configureSearch()
    {
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                PurchasesAdapter filteredPurchasesAdapter = new PurchasesAdapter(getApplicationContext(), model.getFilteredPurchases(query));
                mRecyclerView.setAdapter(filteredPurchasesAdapter);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                PurchasesAdapter filteredPurchasesAdapter = new PurchasesAdapter(getApplicationContext(), model.getFilteredPurchases(newText));
                mRecyclerView.setAdapter(filteredPurchasesAdapter);

                return true;
            }
        });
    }

    private void setPurchases()
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PurchasesAdapter(this, model.getMyPurchases());
        mRecyclerView.setAdapter(mAdapter);
    }


    private void setUpToolbar()
    {
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
    }
}
