package com.selfscore.selfscoreapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selfscore.selfscoreapp.Model.Purchase;
import com.selfscore.selfscoreapp.R;

import java.util.List;

/**
 * Created by anshilbhansali on 8/4/16.
 */
public class PurchasesAdapter extends RecyclerView.Adapter<PurchasesAdapter.ViewHolder>  {

    private List<Purchase> myPurchases;
    public Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View v;

        public TextView purchase_month, purchase_date, purchase_text, purchase_num;

        public ViewHolder(View v) {
            super(v);
            this.v = v;

            this.purchase_month = (TextView) v.findViewById(R.id.purchase_month);
            this.purchase_date = (TextView) v.findViewById(R.id.purchase_date);
            this.purchase_text = (TextView) v.findViewById(R.id.purchase_text);
            this.purchase_num = (TextView) v.findViewById(R.id.purchase_num);

        }
    }

    public PurchasesAdapter(Context context, List<Purchase> myPurchases)
    {
        this.context = context;
        this.myPurchases = myPurchases;
    }


    @Override
    public PurchasesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.single_purchase_row, parent, false);

        //root view v MUST be passed in constructor of vh
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String month = myPurchases.get(position).getMonth();
        String date = myPurchases.get(position).getDate();
        String name = myPurchases.get(position).getPurchaseName();
        String amt = myPurchases.get(position).getAmount();

        holder.purchase_month.setText(month);
        holder.purchase_date.setText(date);
        holder.purchase_text.setText(name);
        holder.purchase_num.setText(amt);

    }

    @Override
    public int getItemCount() {
        return myPurchases.size();
    }


}
