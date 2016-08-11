package com.selfscore.selfscoreapp.Activities.DashboardActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
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
