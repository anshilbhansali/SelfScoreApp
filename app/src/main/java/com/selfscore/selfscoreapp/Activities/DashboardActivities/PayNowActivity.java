package com.selfscore.selfscoreapp.Activities.DashboardActivities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.selfscore.selfscoreapp.R;

public class PayNowActivity extends AppCompatActivity {

    //Main buttons
    View paynow_button;
    View setup_autopay;
    TextView paynow_text;
    TextView autopay_text;

    //Main content
    View paynowcontent;
    View autopaycontent;

    //views
    View min_due_view; TextView min_due_text, min_due_num;
    View prev_bal_view; TextView prev_bal_text, prev_bal_num;
    View curr_bal_view; TextView curr_bal_text, curr_bal_num;
    View other_amt_view; TextView other_amt_text, other_amt_num;
    View min_pay_view; TextView min_pay_text1, min_pay_text2;
    View month_state_view; TextView month_state_text1, month_state_text2;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_now);

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

        //MAIN FUNCTIONALITY
        paynow_button = findViewById(R.id.paynow_button);
        setup_autopay = findViewById(R.id.setupauto_button);
        paynow_text = (TextView) findViewById(R.id.paynow_textview);
        autopay_text = (TextView) findViewById(R.id.autopay_textview);

        paynowcontent =  findViewById(R.id.paynowcontent_view);
        autopaycontent = findViewById(R.id.autopaycontent_view);

        //to change content if pay now OR auto pay is clicked
        setMainButtonListeners();

        //to show clicked state as green
        setClickListeners();

        //expand payment notice when clicked
        setPaymentNotice();

        //init calendar
        setCalendarView();

        //initialize view based on state, currently paynow state
        Intent intent = getIntent();
        if(intent.hasExtra("STATE"))
        {
            if(intent.getExtras().get("STATE").equals("AUTOPAY"))
                setup_autopay.callOnClick();
            else if(intent.getExtras().get("STATE").equals("PAYNOW"))
                paynow_button.callOnClick();
        }
        else
            paynow_button.callOnClick();

        //select payment method
        View select_pay_method = findViewById(R.id.select_pay_method);
        View select_pay_method2 = findViewById(R.id.payment_method2);
        select_pay_method.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Payment methods are not ready yet!", Toast.LENGTH_SHORT).show();
            }
        });
        select_pay_method2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Payment methods are not ready yet!", Toast.LENGTH_SHORT).show();
            }
        });



        //continue button
        View continue_b = findViewById(R.id.continue_button);
        continue_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Payment functionality not ready yet!", Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void setClickListeners()
    {
        //PAY NOW VIEWS
        min_due_view = findViewById(R.id.min_due_view);
        min_due_text = (TextView) findViewById(R.id.min_due_text);
        min_due_num = (TextView) findViewById(R.id.min_due_num);

        prev_bal_view = findViewById(R.id.prev_bal_view);
        prev_bal_text = (TextView) findViewById(R.id.prev_bal_text);
        prev_bal_num = (TextView) findViewById(R.id.prev_bal_num);

        curr_bal_view = findViewById(R.id.curr_bal_view);
        curr_bal_text = (TextView) findViewById(R.id.curr_bal_text);
        curr_bal_num = (TextView) findViewById(R.id.curr_bal_num);

        other_amt_view = findViewById(R.id.other_amt_view);
        other_amt_text = (TextView) findViewById(R.id.other_amt_text);
        other_amt_num = (TextView) findViewById(R.id.other_amt_num);

        //AUTOPAY VIEWS
        min_pay_view = findViewById(R.id.min_pay_view);
        min_pay_text1 = (TextView) findViewById(R.id.min_pay_text1);
        min_pay_text2 = (TextView) findViewById(R.id.min_pay_text2);

        month_state_view = findViewById(R.id.monthly_state_bal_view);
        month_state_text1 = (TextView) findViewById(R.id.monthly_state_bal_text1);
        month_state_text2 = (TextView) findViewById(R.id.monthly_state_bal_text2);

        min_due_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClickedState(min_due_view, min_due_text, min_due_num);
                setUnclickedState(prev_bal_view, prev_bal_text, prev_bal_num);
                setUnclickedState(curr_bal_view, curr_bal_text, curr_bal_num);
                setUnclickedState(other_amt_view, other_amt_text, other_amt_num);
            }
        });
        prev_bal_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUnclickedState(min_due_view, min_due_text, min_due_num);
                setClickedState(prev_bal_view, prev_bal_text, prev_bal_num);
                setUnclickedState(curr_bal_view, curr_bal_text, curr_bal_num);
                setUnclickedState(other_amt_view, other_amt_text, other_amt_num);
            }
        });
        curr_bal_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUnclickedState(min_due_view, min_due_text, min_due_num);
                setUnclickedState(prev_bal_view, prev_bal_text, prev_bal_num);
                setClickedState(curr_bal_view, curr_bal_text, curr_bal_num);
                setUnclickedState(other_amt_view, other_amt_text, other_amt_num);
            }
        });
        other_amt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUnclickedState(min_due_view, min_due_text, min_due_num);
                setUnclickedState(prev_bal_view, prev_bal_text, prev_bal_num);
                setUnclickedState(curr_bal_view, curr_bal_text, curr_bal_num);
                setClickedState(other_amt_view, other_amt_text, other_amt_num);
            }
        });


        min_pay_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClickedState(min_pay_view, min_pay_text1, min_pay_text2);
                setUnclickedState(month_state_view, month_state_text1, month_state_text2);
            }
        });
        month_state_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUnclickedState(min_pay_view, min_pay_text1, min_pay_text2);
                setClickedState(month_state_view, month_state_text1, month_state_text2);
            }
        });


    }

    private void setClickedState(View view, TextView text1, TextView text2)
    {
        view.setBackgroundColor(getResources().getColor(R.color.mint));
        text1.setTextColor(getResources().getColor(R.color.colorWhite));
        text2.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void setUnclickedState(View view, TextView text1, TextView text2)
    {
        view.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        text1.setTextColor(getResources().getColor(R.color.midnight));
        text2.setTextColor(getResources().getColor(R.color.midnight));
    }



    private void setPaymentNotice()
    {
        final ScrollView scrollView = (ScrollView) findViewById(R.id.scroll_view);
        View paymentnotice_b = findViewById(R.id.paymentnotice_button);
        final View paymentnotice_view = findViewById(R.id.paymentnotice);
        paymentnotice_view.setVisibility(View.VISIBLE);

        paymentnotice_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrollView.scrollTo(0, paymentnotice_view.getBottom());
                paymentnotice_view.requestFocus();
            }
        });

    }

    private void setCalendarView()
    {
        final TextView datepicker = (TextView) findViewById(R.id.datepicker);
        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendar_view);
        calendarView.setVisibility(View.GONE);

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.VISIBLE);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                calendarView.setVisibility(View.GONE);

                String y = String.valueOf(year);
                String m = String.valueOf(month+1);
                String d = String.valueOf(dayOfMonth);

                datepicker.setText(m+"/"+d+"/"+y);
            }
        });
    }

    private void setAllUnclicked()
    {
        setUnclickedState(min_due_view, min_due_text, min_due_num);
        setUnclickedState(prev_bal_view, prev_bal_text, prev_bal_num);
        setUnclickedState(curr_bal_view, curr_bal_text, curr_bal_num);
        setUnclickedState(other_amt_view, other_amt_text, other_amt_num);
        setUnclickedState(min_pay_view, min_pay_text1, min_pay_text2);
        setUnclickedState(month_state_view, month_state_text1, month_state_text2);

    }

    private void setMainButtonListeners()
    {
        paynow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clicked
                paynowcontent.setVisibility(View.VISIBLE);
                paynow_text.setTextColor(getResources().getColor(R.color.midnight));
                paynow_button.setBackgroundColor(getResources().getColor(R.color.navy20));


                //not clicked
                autopaycontent.setVisibility(View.GONE);
                autopay_text.setTextColor(getResources().getColor(R.color.navy20));
                setup_autopay.setBackgroundColor(getResources().getColor(R.color.colorWhite));

                //by default all views unclicked
                setAllUnclicked();
            }
        });

        setup_autopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //not clicked
                paynowcontent.setVisibility(View.GONE);
                paynow_text.setTextColor(getResources().getColor(R.color.navy20));
                paynow_button.setBackgroundColor(getResources().getColor(R.color.colorWhite));

                //clicked
                autopaycontent.setVisibility(View.VISIBLE);
                autopay_text.setTextColor(getResources().getColor(R.color.midnight));
                setup_autopay.setBackgroundColor(getResources().getColor(R.color.navy20));

                //by default all unclicked
                setAllUnclicked();
            }
        });
    }

}
