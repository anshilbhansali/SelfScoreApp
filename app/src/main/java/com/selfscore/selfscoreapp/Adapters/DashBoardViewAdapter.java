package com.selfscore.selfscoreapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.selfscore.selfscoreapp.Activities.DashboardActivities.CreditAvailabilityActivity;
import com.selfscore.selfscoreapp.Activities.DashboardActivities.EarnCashActivity;
import com.selfscore.selfscoreapp.Activities.DashboardActivities.MyPurchasesActivity;
import com.selfscore.selfscoreapp.Activities.DashboardActivities.PayBillActivity;
import com.selfscore.selfscoreapp.Activities.DashboardActivities.PayNowActivity;
import com.selfscore.selfscoreapp.R;

/**
 * Created by anshilbhansali on 7/8/16.
 */


public class DashBoardViewAdapter extends RecyclerView.Adapter<DashBoardViewAdapter.ViewHolder> {
    private String[] header_names;
    private String[] sub_headers;
    private String[] sub_headers_nums;
    private String[] buttons_text;
    public Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View v;
        public TextView header;
        public TextView subheader;
        public TextView subheader_num;
        public LinearLayout subheader_layout;
        public Button button;
        public View headerClick;
        public LinearLayout content;
        View content_layout;

        public ViewHolder(View v, int viewType) {
            super(v);

            this.v = v;
            this.header = (TextView) v.findViewById(R.id.header_text);
            this.subheader = (TextView) v.findViewById(R.id.subheader_text);
            this.subheader_num = (TextView) v.findViewById(R.id.subheader_number);
            this.subheader_layout = (LinearLayout) v.findViewById(R.id.subheader_layout);
            this.button = (Button) v.findViewById(R.id.button_text);

            this.headerClick = v.findViewById(R.id.header_click);

            this.content = (LinearLayout) v.findViewById(R.id.content_cardview);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            //to show appropriate content in each cardview
            if(viewType == 0)
            {
                //pay bill
                content_layout = inflater.inflate(R.layout.paybill_content, null);
                content.addView(content_layout);
            }
            else if(viewType == 1)
            {
                //credit availability
                subheader_num.setTextColor(context.getResources().getColor(R.color.teal));
                content_layout = inflater.inflate(R.layout.credit_avail_content, null);
                content.addView(content_layout);

            }
            else if(viewType == 2)
            {
                //earn cash
                LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) content.getLayoutParams();
                p.setMargins(0,0,5,5); //decrease top and bottom margins, to adjust button

                content_layout = inflater.inflate(R.layout.earn_cash_content, null);
                content.addView(content_layout);

            }
            else if (viewType == 3)
            {
                //My Purchases
                subheader.setVisibility(View.GONE);
                subheader_num.setVisibility(View.GONE);
                subheader_layout.setVisibility(View.GONE);
                button.setVisibility(View.GONE);

                content_layout = inflater.inflate(R.layout.my_purchases_content, null);

                //change right margin
                LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) content.getLayoutParams();
                p.setMargins(0,0,0,0);
                content.setLayoutParams(p);
                content.setGravity(Gravity.NO_GRAVITY);


                content.addView(content_layout);
            }

            setListeners();

        }

        //to set cardview click listeners
        public void setListeners()
        {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView h = (TextView) v.findViewById(R.id.header_text);
                    String header = h.getText().toString();
                    Log.v("TAGTAGTAG", header);
                    if(header.equals("Pay Bill"))
                    {
                        Intent intent = new Intent(context, PayBillActivity.class);
                        context.startActivity(intent);
                    }
                    else if (header.equals("Credit Availability"))
                    {
                        Intent intent = new Intent(context, CreditAvailabilityActivity.class);
                        context.startActivity(intent);
                    }
                    else if (header.equals("Earn Cash"))
                    {
                        //Toast.makeText(context, "work in progress", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, EarnCashActivity.class);
                        context.startActivity(intent);
                    }
                    else if (header.equals("My Purchases"))
                    {
                        Intent intent = new Intent(context, MyPurchasesActivity.class);
                        context.startActivity(intent);
                        //Toast.makeText(context, "work in progress", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String button_text = button.getText().toString();
                    if(button_text.equals("PAY NOW"))
                    {
                        Intent intent = new Intent(context, PayNowActivity.class);
                        context.startActivity(intent);
                    }
                    else if(button_text.equals("INCREASE CREDIT"))
                    {
                        Toast.makeText(context, "work in progress", Toast.LENGTH_SHORT).show();
                    }
                    else if(button_text.equals("INVITE FRIENDS"))
                    {
                        //Toast.makeText(context, "work in progress", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, EarnCashActivity.class);
                        context.startActivity(intent);
                    }

                }
            });


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DashBoardViewAdapter(Context context, String[] header_names, String[] sub_headers, String[] sub_headers_nums, String[] button_text) {
        this.header_names = header_names;
        this.sub_headers = sub_headers;
        this.sub_headers_nums = sub_headers_nums;
        this.buttons_text = button_text;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DashBoardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cardview, parent, false);

        //root view v MUST be passed in constructor of vh
        ViewHolder vh = new ViewHolder(v, viewType);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.header.setText(header_names[position]);
        holder.subheader.setText(sub_headers[position]);
        holder.subheader_num.setText(sub_headers_nums[position]);
        holder.button.setText(buttons_text[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return header_names.length;
    }
}




