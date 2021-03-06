package com.selfscore.selfscoreapp.Activities.DashboardActivities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.selfscore.selfscoreapp.Adapters.DrawerItemViewAdapter;
import com.selfscore.selfscoreapp.Activities.LoginActivities.LoginActivity;
import com.selfscore.selfscoreapp.Model.Model;
import com.selfscore.selfscoreapp.R;
import com.selfscore.selfscoreapp.Adapters.DashBoardViewAdapter;
import com.selfscore.selfscoreapp.Model.RowItem;
import com.selfscore.selfscoreapp.SelfScoreApplication;

import java.util.List;

/* created by Anshil Bhansali on 7/7/16 */

public class MainActivity extends AppCompatActivity {

    //DRAWERS
    private ListView mDrawerList_left;
    private ListView mDrawerList_right;
    private List<RowItem> rowItems_left;
    private List<RowItem> rowItems_right;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;


    //RECYCLER VIEW
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] header_names;
    private String[] sub_headers;
    private String[] sub_headers_nums;
    private String[] buttons_text;

    //Conext
    Activity activity;

    //model
    Model model;

    //flag
    private Boolean back_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;

        //get Model data
        model = ((SelfScoreApplication) this.getApplication()).getModel();
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            if(extras.get("ACTIVITY_NAME").equals("Login"))
            {
                Toast.makeText(MainActivity.this, "Welcome "+model.getUsername(), Toast.LENGTH_LONG).show();
                back_flag = true;
            }

        }


        //<-----------------------------TOOLBAR SETUP--------------------------------------->
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.midnight)));

        //<-----------------------------DRAWER SETUP--------------------------------------------------->
        rowItems_left = model.getLeftRowItems();
        rowItems_right = model.getRightRowItems();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerList_left = (ListView) findViewById(R.id.left_drawer);
        mDrawerList_right = (ListView) findViewById(R.id.right_drawer);

        AdaptDrawers();
        setDrawerListeners();
        setupDrawerToggle();

        //<-----------------------------RECYCLER VIEW (MAIN VIEW)--------------------------------------->
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //connect view to adapter
        header_names = model.getCard_headers();
        sub_headers = model.getCard_subheaders();
        sub_headers_nums = model.getCard_subheader_nums();
        buttons_text = model.getCard_buttons();
        mAdapter = new DashBoardViewAdapter(this, header_names, sub_headers, sub_headers_nums, buttons_text);
        mRecyclerView.setAdapter(mAdapter);


    }

    private void AdaptDrawers()
    {
        //Set the ADAPTER for the DRAWER list view
        DrawerItemViewAdapter adapter_left = new DrawerItemViewAdapter(this,
                R.layout.list_item, rowItems_left);

        DrawerItemViewAdapter adapter_right = new DrawerItemViewAdapter(this,
                R.layout.list_item, rowItems_right);

        mDrawerList_left.setAdapter(adapter_left);
        mDrawerList_right.setAdapter(adapter_right);
    }


    private void setDrawerListeners()
    {
        mDrawerList_left.setBackgroundColor(getResources().getColor(R.color.midnight));
        mDrawerList_right.setBackgroundColor(getResources().getColor(R.color.midnight));

        mDrawerList_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0)
                {
                    //activity_paybill
                    Intent intent = new Intent(activity, PayBillActivity.class);
                    startActivity(intent);
                }
                else if(position == 1)
                {
                    //activity_credit_avail
                    Intent intent = new Intent(activity, CreditAvailabilityActivity.class);
                    startActivity(intent);
                }
                else if(position == 2)
                {
                    //earn cash
                    //Toast.makeText(context, "work in progress", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity, EarnCashActivity.class);
                    activity.startActivity(intent);
                }
                else if(position == 3)
                {
                    Intent intent = new Intent(activity, MyPurchasesActivity.class);
                    activity.startActivity(intent);
                }

            }
        });

        mDrawerList_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    //my profile
                    Intent intent = new Intent(activity, MyProfileActivity.class);
                    startActivity(intent);
                }
                else if(position == 1)
                {
                    //account settings
                    Intent intent = new Intent(activity, AccountSettingsActivity.class);
                    startActivity(intent);
                }
                else if(position == 2)
                {
                    //log out
                    Intent intent = new Intent(activity, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
    }


    private void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };



        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buttons, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {

            case R.id.user_account:
                //open right drawer
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

        super.onBackPressed();
    }
}
