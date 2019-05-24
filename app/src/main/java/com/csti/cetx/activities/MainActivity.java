package com.csti.cetx.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.csti.cetx.R;
import com.csti.cetx.views.SlideableCardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        SlideableCardView slideableCardView=findViewById(R.id.card);
        slideableCardView.setOnDragListener(new SlideableCardView.OnDragListener() {
            @Override
            public void onDragOut(SlideableCardView.Direction dir) {
                Toast.makeText(MainActivity.this,dir==SlideableCardView.Direction.LEFT?
                        "已记忆该单词":"忘了",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
