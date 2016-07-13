package com.selfscore.selfscoreapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by anshilbhansali on 7/8/16.
 */


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private String[] header_names;
    private String[] sub_headers;
    private String[] buttons_text;
    public Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView header;
        public TextView subheader;
        public Button button;
        public View headerClick;

        public ViewHolder(View v) {
            super(v);

            this.header = (TextView) v.findViewById(R.id.header_text);
            this.subheader = (TextView) v.findViewById(R.id.subheader_text);
            this.button = (Button) v.findViewById(R.id.button_text);

            this.headerClick = v.findViewById(R.id.header_click);

            setListeners();

        }

        public void setListeners()
        {
            headerClick.setOnClickListener(new View.OnClickListener() {
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
                    else
                        return;
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(Context context, String[] header_names, String[] sub_headers, String[] button_text) {
        this.header_names = header_names;
        this.sub_headers = sub_headers;
        this.buttons_text = button_text;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cardview, parent, false);

        //root view v MUST be passed in constructor of vh
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.header.setText(header_names[position]);
        holder.subheader.setText(sub_headers[position]);
        holder.button.setText(buttons_text[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return header_names.length;
    }
}




