package com.anonarmy.prajwalnayak.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class firstActivity extends AppCompatActivity {
    SharedPreferences prefs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        prefs = getSharedPreferences("com.anonarmy.prajwalnayak.myapplication", MODE_PRIVATE);
        Intent i=new Intent(firstActivity.this,LoginActivitynew.class);
        firstActivity.this.startActivity(i);
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            Intent i=new Intent(firstActivity.this,LoginActivity.class);
            firstActivity.this.startActivity(i);
            finish();
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstrun", true).commit();
        }
    }
}
