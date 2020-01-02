package com.example.toolsmanagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClubProfileRecyclerViewAdapter extends RecyclerView.Adapter<ClubProfileRecyclerViewAdapter.ViewHolder> {

    private List<ClubProfile> mValues,list;
    private Context context;

    public ClubProfileRecyclerViewAdapter(List<ClubProfile> items, Context context) {
        mValues = items;
        list= items;
        this.context= context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_club_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).name);
        if(mValues.get(position).tools_history.size()!=0){
            SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date strdate= sdf.parse(mValues.get(position).tools_history.get(mValues.get(position).tools_history.size()-1).return_date);
                if(new Date().after(strdate))
                    holder.mContentView.setText(mValues.get(position).tools_history.get(mValues.get(position).tools_history.size()-1).name);
                else holder.mContentView.setText("No Tool");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else holder.mContentView.setText("No Tool");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, ShowProfile.class);
                intent.putExtra("mode",0);
                intent.putExtra("data",holder.mItem);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void add(ClubProfile clubProfile) {
        mValues.add(clubProfile);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;

        public ClubProfile mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public List<ClubProfile> getList() {
        return list;
    }

    public void setData(List<ClubProfile> list) {
        mValues= list;
        notifyDataSetChanged();
    }
}
