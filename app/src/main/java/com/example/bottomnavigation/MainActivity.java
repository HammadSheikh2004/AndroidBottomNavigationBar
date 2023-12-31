package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView BottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNav = findViewById(R.id.BottomNav);



        BottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.Home) {
                    LoadFrag(new HomeFragment(), true);
                } else if (id == R.id.Notification) {
                    LoadFrag(new NotificationFragment(), false);
                } else {
                    LoadFrag(new ServiceFragment(), false);
                }


                return true;
            }
        });
        BottomNav.setSelectedItemId(R.id.Home);
    }

    @Override
    public void onBackPressed() {
        // Handle the back press event
        int selectedItemId = BottomNav.getSelectedItemId();

        if (selectedItemId == R.id.Home) {
            // If already on the Home fragment, close the activity
            finish();
        } else if (selectedItemId == R.id.Notification) {
            BottomNav.setSelectedItemId(R.id.Home);
        } else if (selectedItemId == R.id.Service) {
            BottomNav.setSelectedItemId(R.id.Notification);
        } else {
            // Default behavior for unknown fragments
            super.onBackPressed();
        }
    }


    public void LoadFrag(Fragment fragment,boolean flag){
        FragmentManager FragManag = getSupportFragmentManager();
        FragmentTransaction FragTrans = FragManag.beginTransaction();
        if (flag) {
            FragTrans.add(R.id.container, fragment);
        }else{
            FragTrans.replace(R.id.container,fragment);
            FragTrans.addToBackStack(null);
        }

        FragTrans.commit();
    }


}