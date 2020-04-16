package com.nirali.niralidesaiproficiencytest.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nirali.niralidesaiproficiencytest.Models.CanadaDetailsModel;
import com.nirali.niralidesaiproficiencytest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CanadaDetailsAdapter extends BaseAdapter {

    private final Activity context;
    private ArrayList<CanadaDetailsModel.RowsBean> canadaDetailsModelArrayList;

    public CanadaDetailsAdapter(Activity activity, ArrayList<CanadaDetailsModel.RowsBean> canadaDetailsModelArrayList) {
        this.context = activity;
        this.canadaDetailsModelArrayList = canadaDetailsModelArrayList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return canadaDetailsModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        // initialize all basic variables
        TextView tvTitle, tvDescription;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_canada_details, parent, false);

            holder = new ViewHolder();

            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
            holder.imageView = (ImageView) convertView.findViewById(R.id.ivImage);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        try {

            // If - Else (If all details are null, not to show in list)
            if (canadaDetailsModelArrayList.get(position).getTitle() == null
            && canadaDetailsModelArrayList.get(position).getDescription() == null
            && canadaDetailsModelArrayList.get(position).getImageHref() == null) {

            } else {
                holder.tvTitle.setText(canadaDetailsModelArrayList.get(position).getTitle());
                holder.tvDescription.setText(canadaDetailsModelArrayList.get(position).getDescription());

                // If - Else (If Image Url result is not found, show only default image)
                if (canadaDetailsModelArrayList.get(position).getImageHref() == null) {

                } else {
                    // Used Piccasso loader....here we can use glider too
                    Picasso.get()
                            .load(canadaDetailsModelArrayList.get(position).getImageHref())
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.notfound)
                            .into(holder.imageView);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
