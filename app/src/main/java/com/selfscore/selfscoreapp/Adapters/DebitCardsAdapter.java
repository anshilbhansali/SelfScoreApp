package com.selfscore.selfscoreapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selfscore.selfscoreapp.Activities.DashboardActivities.PayNowActivity;
import com.selfscore.selfscoreapp.Activities.DashboardActivities.PaymentMethod;
import com.selfscore.selfscoreapp.Model.DebitCard;
import com.selfscore.selfscoreapp.Model.Purchase;
import com.selfscore.selfscoreapp.R;

import java.util.List;

/**
 * Created by anshilbhansali on 8/18/16.
 */
public class DebitCardsAdapter extends RecyclerView.Adapter<DebitCardsAdapter.ViewHolder> {

    private List<DebitCard> myCards;
    public static Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View v;
        public TextView name, remove;

        public ViewHolder(View v, final int viewType) {
            super(v);
            this.v = v;

            name = (TextView) v.findViewById(R.id.pay_method_name);
            remove = (TextView)v.findViewById(R.id.remove_method);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PayNowActivity.class);
                    intent.putExtra("METHOD_NAME", name.getText().toString());
                    context.startActivity(intent);
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PaymentMethod.class);
                    intent.putExtra("REMOVE_CARD", viewType);
                    context.startActivity(intent);
                }
            });
        }
    }

    public DebitCardsAdapter(Context context, List<DebitCard> cards)
    {
        this.context = context;
        this.myCards = cards;
    }


    @Override
    public DebitCardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.single_pay_method, parent, false);

        //root view v MUST be passed in constructor of vh
        ViewHolder vh = new ViewHolder(v, viewType);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String last4 = myCards.get(position).getCardnum4();
        holder.name.setText("xxxx-xxxx-xxxx-"+last4);

    }

    @Override
    public int getItemCount() {
        return myCards.size();
    }

}
