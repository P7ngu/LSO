package com.example.myapplication.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.Client;
import com.example.myapplication.CurrentUser;
import com.example.myapplication.HomeActivity;
import com.example.myapplication.MakeABetActivity;
import com.example.myapplication.R;
import com.example.myapplication.WaitingActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    static Context mContext;
    static Button sendBetButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        final Button sendBetButton1 = root.findViewById(R.id.button_goToBet2);
        sendBetButton=sendBetButton1;

        sendBetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int timeLeft = Client.getTimerLeft();

                    if (timeLeft > 30)
                        startActivity(new Intent(mContext, MakeABetActivity.class));
                    else if (timeLeft <= 30)
                        startActivity(new Intent(mContext, WaitingActivity.class));
            }
        });
        mContext=container.getContext();


        return root;
    }
}