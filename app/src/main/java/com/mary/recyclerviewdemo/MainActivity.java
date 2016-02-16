package com.mary.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mary.recyclerviewdemo.activity.EightActivity;
import com.mary.recyclerviewdemo.activity.FiveActivity;
import com.mary.recyclerviewdemo.activity.FourActivity;
import com.mary.recyclerviewdemo.activity.NineActivity;
import com.mary.recyclerviewdemo.activity.OneActivity;
import com.mary.recyclerviewdemo.activity.SevenActivity;
import com.mary.recyclerviewdemo.activity.SixActivity;
import com.mary.recyclerviewdemo.activity.ThreeActivity;
import com.mary.recyclerviewdemo.activity.TwoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void to_1Activity(View view) {
        Intent intent = new Intent(MainActivity.this, OneActivity.class);
        startActivity(intent);
    }
    public void to_2Activity(View view) {
        Intent intent = new Intent(MainActivity.this, TwoActivity.class);
        startActivity(intent);
    }
    public void to_3Activity(View view) {
        Intent intent = new Intent(MainActivity.this, ThreeActivity.class);
        startActivity(intent);
    }
    public void to_4Activity(View view) {
        Intent intent = new Intent(MainActivity.this, FourActivity.class);
        startActivity(intent);
    }
    public void to_5Activity(View view) {
        Intent intent = new Intent(MainActivity.this, FiveActivity.class);
        startActivity(intent);
    }
    public void to_6Activity(View view) {
        Intent intent = new Intent(MainActivity.this, SixActivity.class);
        startActivity(intent);
    }
    public void to_7Activity(View view) {
        Intent intent = new Intent(MainActivity.this, SevenActivity.class);
        startActivity(intent);
    }
    public void to_8Activity(View view) {
        Intent intent = new Intent(MainActivity.this, EightActivity.class);
        startActivity(intent);
    }
    public void to_9Activity(View view) {
        Intent intent = new Intent(MainActivity.this, NineActivity.class);
        startActivity(intent);
    }
}
