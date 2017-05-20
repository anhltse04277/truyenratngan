package com.techkid.anh82.randomquote.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.techkid.anh82.randomquote.R;
import com.techkid.anh82.randomquote.fragment.QuoteFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayStartScreen();
    }

    private void displayStartScreen() {
        QuoteFragment quoteFragment = new QuoteFragment();
        changeFragment(quoteFragment, false);
    }

    public void changeFragment(Fragment fragment , boolean addToBacStack){
        if(addToBacStack){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main ,fragment).addToBackStack(null).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main ,fragment).commit();
        }

    }
}
