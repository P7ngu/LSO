package com.example.myapplication;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder>{
    private static Context mContext;
    private static List<Utente> mData;

    public Adaptery(Context mContext, List<Utente> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //v=inflater.inflate(R.layout.movie_item, parent, false);
        v = View.inflate(mContext, R.layout.leaderboard_item, null);

        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.money.setText(mData.get(position).getMoneyCount());
        holder.name.setText(mData.get(position).getUsername());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView money, name;


        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Log.d("Test", "Clicked " + mData.get(pos).toString());

        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            money = itemView.findViewById(R.id.textView_nicknameRecycler);
            name = itemView.findViewById(R.id.textView_points_recycler);

        }


    }
}