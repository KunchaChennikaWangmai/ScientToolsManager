package com.example.toolsmanagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;


public class ToolsRecyclerViewAdapter extends RecyclerView.Adapter<ToolsRecyclerViewAdapter.ViewHolder> {

    private List<ToolsListN> mValues,list;
    private int mode;
    private Context context;
    private DatabaseReference databaseReference;

    public ToolsRecyclerViewAdapter(List<ToolsListN> items, Context context) {
        mValues = items;
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).name);
        holder.mContentView.setText(mValues.get(position).getUid().toString());
        holder.mAvailability.setText(mValues.get(position).availability.toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, ShowTools.class);
                intent.putExtra("uid",mValues.get(position).getUid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void add(ToolsListN toolsListN) {
        mValues.add(toolsListN);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mAvailability;

        public ToolsListN mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
            mAvailability= view.findViewById(R.id.availability);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public List<ToolsListN> getList() {
        return list;
    }

    public void setData(List<ToolsListN> list) {
        mValues= list;
        notifyDataSetChanged();
    }
}
