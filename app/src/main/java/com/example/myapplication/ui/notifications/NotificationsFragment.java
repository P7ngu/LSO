package com.example.myapplication.ui.notifications;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adaptery;
import com.example.myapplication.Client;
import com.example.myapplication.R;
import com.example.myapplication.Utente;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    static Context mContext;
    static RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        mContext = container.getContext();
        final RecyclerView recyclerView1=root.findViewById(R.id.recycler_leaderboard);
        recyclerView=recyclerView1;

        try {
            String users = Client.getListaUtenti();
            String[] utentiArray = users.split(";;");
            ArrayList<Utente> userList;
            userList = new ArrayList<>();
            for (int i = 0; i < utentiArray.length-1; i = i + 2) {
                Utente tempUser = new Utente(utentiArray[i], utentiArray[i + 1]); //username, moneycount
                userList.add(tempUser);
            }
            userList.sort(new Comparator<Utente>() {
                @Override
                //a negative integer, zero, or a positive integer as the first
                // argument is less than, equal to, or greater than the second.
                public int compare(Utente o1, Utente o2) {
                    return Integer.compare( (Integer.parseInt( o2.getMoneyCount() ) ), Integer.parseInt(o1.getMoneyCount()) );
                }
            });
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