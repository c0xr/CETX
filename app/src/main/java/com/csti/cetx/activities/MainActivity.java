package com.csti.cetx.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.csti.cetx.R;
import com.csti.cetx.views.SlideableCardView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView memory;
    private TextView game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }

        iniViews();
    }

    private void iniViews(){
        memory = findViewById(R.id.text_memory);
        game   = findViewById(R.id.text_games);

        memory.setOnClickListener(this);
        game.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_memory:
                MemoryActivity.actionStart(MainActivity.this);
                return;

            case R.id.text_games:
                Toast.makeText(MainActivity.this, "尚未推出，敬请期待", Toast.LENGTH_LONG).show();
                return;
        }
    }

    public static void actionStart(AppCompatActivity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}
