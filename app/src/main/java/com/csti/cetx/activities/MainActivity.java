package com.csti.cetx.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.csti.cetx.R;
import com.csti.cetx.utils.activity.PushItemsActivity;
import com.csti.cetx.views.SlideableCardView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TextView memory;
    private TextView game;
    private Button   mResetButton;
    private Button   mLogOutButton;

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
        memory.setOnClickListener(this);

        game   = findViewById(R.id.text_games);
        game.setOnClickListener(this);

        mResetButton = findViewById(R.id.reset);
        mResetButton.setOnClickListener(this);

        mLogOutButton = findViewById(R.id.log_out);
        mLogOutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_memory:
                MemoryActivity.actionStart(MainActivity.this);
                return;

            case R.id.text_games:
                GameActivity.actionStart(MainActivity.this);
                return;

            case R.id.reset:
                PushItemsActivity.actionStart(MainActivity.this);
                return;

            case R.id.log_out:
                BmobUser.logOut();
                LogInActivity.actionStart(MainActivity.this);
                finish();
                return;
        }
    }

    public static void actionStart(Object activity){
        Intent intent = new Intent((BaseActivity)activity, MainActivity.class);
        ((BaseActivity)activity).startActivity(intent);
    }
}
