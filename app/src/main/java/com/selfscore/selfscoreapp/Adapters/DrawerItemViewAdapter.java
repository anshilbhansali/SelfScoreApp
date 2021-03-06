package com.selfscore.selfscoreapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.selfscore.selfscoreapp.R;
import com.selfscore.selfscoreapp.Model.RowItem;

import java.util.List;

/**
 * Created by anshilbhansali on 7/7/16.
 */

public class DrawerItemViewAdapter extends ArrayAdapter<RowItem> {


    Context context;

    public DrawerItemViewAdapter(Context context, int resourceId, //resourceId=your layout
                                 List<RowItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItem rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(rowItem.getTitle());
        holder.imageView.setImageDrawable(context.getResources().getDrawable(rowItem.getImageId()));
        //holder.imageView.setBackgroundResource(rowItem.getImageId());

        holder.imageView.setBackgroundColor(context.getResources().getColor(R.color.midnight));
        holder.txtTitle.setTextColor(Color.WHITE);

        return convertView;
    }


}

