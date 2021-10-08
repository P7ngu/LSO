package com.example.myapplication.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adaptery;
import com.example.myapplication.Client;
import com.example.myapplication.HomeActivity;
import com.example.myapplication.R;
import com.example.myapplication.Utente;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    static Context mContext;
    static RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mContext = container.getContext();
        final RecyclerView recyclerView1=root.findViewById(R.id.utentionline_recycler);
        recyclerView=recyclerView1;


        try {
            String users = Client.getUtentiAttivi();
            String[] utentiArray = users.split(",,");
            ArrayList<Utente> userList;
            userList = new ArrayList<>();
            for (int i = 0; i < utentiArray.length-1; i = i + 2) {
                Utente tempUser = new Utente(utentiArray[i], utentiArray[i + 1]); //username, moneycount
                userList.add(tempUser);
            }

            PutDataIntoRecyclerView(userList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return root;
    }

    public static void PutDataIntoRecyclerView(List<Utente> userList){
        Adaptery adaptery = new Adaptery(mContext, userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adaptery);

    }
}