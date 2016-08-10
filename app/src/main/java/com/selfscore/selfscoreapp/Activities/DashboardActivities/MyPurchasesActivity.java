package com.selfscore.selfscoreapp.Activities.DashboardActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.selfscore.selfscoreapp.Adapters.PurchasesAdapter;
import com.selfscore.selfscoreapp.Model.Model;
import com.selfscore.selfscoreapp.R;
import com.selfscore.selfscoreapp.SelfScoreApplication;

public class MyPurchasesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private Model model;

    private SearchView searchView;
    private View date_button, descr_button, amount_button;

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

        configureSort();


    }

    private void configureSort()
    {
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurchasesAdapter sortedPurchasesAdapter = new PurchasesAdapter(getApplicationContext(), model.getSortedByDate());
                mRecyclerView.setAdapter(sortedPurchasesAdapter);
            }
        });

        descr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurchasesAdapter sortedPurchasesAdapter = new PurchasesAdapter(getApplicationContext(), model.getSortedByName());
                mRecyclerView.setAdapter(sortedPurchasesAdapter);
            }
        });

        amount_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurchasesAdapter sortedPurchasesAdapter = new PurchasesAdapter(getApplicationContext(), model.getSortedByAmount());
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
