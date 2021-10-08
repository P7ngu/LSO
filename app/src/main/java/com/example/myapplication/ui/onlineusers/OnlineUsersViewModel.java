package com.example.myapplication.ui.onlineusers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OnlineUsersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OnlineUsersViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}