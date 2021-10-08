package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static com.example.myapplication.Connection.myContext;

public class HomeActivity extends AppCompatActivity {
    static Context mContext;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout_menu_button:
                logoutCurrentUser(mContext);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void logoutCurrentUser(Context mContext) {
        Log.d("28 settembre", "user pressed logout button from Home");

        Client.inviaRichiestaLogout(CurrentUser.getInstance().getUsername());
        CurrentUser.getInstance().setUsername(null);
        CurrentUser.setUserLoggedStatus(0);
        CurrentUser.setInstance(null);

        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(mContext, HomeActivity.class);
        startActivity(setIntent);
        return;
    }

    public static void showWinMessage() {
        PopupController.mostraPopup("Complimenti!", "Hai vinto!", mContext);

    }

    public static void showLostMessage() {
        PopupController.mostraPopup("Mi dispiace!", "Hai perso!", mContext);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       mContext=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        //navView.setItemIconTintList(ColorStateList.valueOf(Color.parseColor("#3F51B5")));
        //listen for navigation events
        //avView.setListenerA(this);
// select the correct nav menu item
        //navView.getMenu().findItem(mNavItemId).setChecked(true);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}