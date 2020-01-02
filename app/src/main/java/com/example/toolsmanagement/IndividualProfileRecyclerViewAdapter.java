package com.example.toolsmanagement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class IndividualProfileRecyclerViewAdapter extends RecyclerView.Adapter<IndividualProfileRecyclerViewAdapter.ViewHolder> {

    private List<IndividualProfile> mProfile,list;
    private Context context;


    public IndividualProfileRecyclerViewAdapter(List<IndividualProfile> items, Context context) {
        mProfile = items;
        list= items;
        this.context= context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_tools, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mProfile.get(position);
        holder.mIdView.setText(mProfile.get(position).name);
        holder.mContentView.setText(mProfile.get(position).roll_No.toString());
        if(mProfile.get(position).tools_history.size()!=0){
            SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yy");
            try {
                Date strdate= sdf.parse(mProfile.get(position).tools_history.get(mProfile.get(position).tools_history.size()-1).return_date);
                if(new Date().after(strdate))
                    holder.mAvailability.setText(mProfile.get(position).tools_history.get(mProfile.get(position).tools_history.size()-1).name);
                else holder.mAvailability.setText("No Tool");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else holder.mAvailability.setText("No Tool");


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, ShowProfile.class);
                intent.putExtra("mode",1);
                intent.putExtra("data",holder.mItem);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProfile.size();
    }

    public void add(IndividualProfile individualProfile) {
        mProfile.add(individualProfile);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mAvailability;

        public IndividualProfile mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            mAvailability= view.findViewById(R.id.availability);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public List<IndividualProfile> getList() {
        return list;
    }

    public void setData(List<IndividualProfile> list) {
        mProfile= list;
        notifyDataSetChanged();
    }
}
