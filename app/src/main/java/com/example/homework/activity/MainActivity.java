package com.example.homework.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.homework.fragment.ListFragment;


import com.example.homework.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            initState();
        }
    }

    private void initState() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragmentList = getSupportFragmentManager().findFragmentById(R.id.container_fragment);

        if (fragmentList == null || !fragmentList.isVisible()) {
            transaction.add(R.id.container_fragment, new ListFragment());
        }

        transaction.addToBackStack(null);
        transaction.commit();
    }
}
