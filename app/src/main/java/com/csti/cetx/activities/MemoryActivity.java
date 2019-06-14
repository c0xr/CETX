package com.csti.cetx.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.csti.cetx.R;
import com.csti.cetx.views.SlideableCardView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;

public class MemoryActivity extends AppCompatActivity {

    private List<String> mWordsList = new ArrayList<>();
    private int from = 0;
    private int to   = 10;

    private SlideableCardView[] mSlideableCards = new SlideableCardView[]{
            findViewById(R.id.card_1),
            findViewById(R.id.card_2),
            findViewById(R.id.card_3),
            findViewById(R.id.card_4),
            findViewById(R.id.card_5),
            findViewById(R.id.card_6),
            findViewById(R.id.card_7),
            findViewById(R.id.card_8),
            findViewById(R.id.card_9),
            findViewById(R.id.card_10),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }

        iniClicks();
    }

    private void iniClicks(){
        for (SlideableCardView cardView : mSlideableCards){
            cardView.setOnDragListener(new SlideableCardView.OnDragListener() {
                @Override
                public void onDragOut(SlideableCardView.Direction dir) {
                    Toast.makeText(MemoryActivity.this,dir==SlideableCardView.Direction.LEFT?
                            "已记忆该单词":"忘了",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void iniCards(){
        BmobQuery<Category> bmobQuery = new BmobQuery<>();
        bmobQuery.findObjects(new FindListener<Category>() {
            @Override
            public void done(List<Category> categories, BmobException e) {
                if (e == null) {
                    Snackbar.make(mBtnQuery, "查询成功：" + categories.size(), Snackbar.LENGTH_LONG).show();
                } else {
                    Log.e("BMOB", e.toString());
                    Snackbar.make(mBtnQuery, e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    public static void actionStart(AppCompatActivity activity){
        Intent intent = new Intent(activity, MemoryActivity.class);
        activity.startActivity(intent);
    }

}
