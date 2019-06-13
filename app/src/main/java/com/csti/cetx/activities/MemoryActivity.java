package com.csti.cetx.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.csti.cetx.R;
import com.csti.cetx.views.SlideableCardView;

import cn.bmob.v3.exception.BmobException;

public class MemoryActivity extends AppCompatActivity {

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
        //查找Person表里面id为6b6c11c537的数据
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("6b6c11c537", new QueryListener<Person>() {
            @Override
            public void done(Person object, BmobException e) {
                if(e==null){
                    toast("查询成功");
                }else{
                    toast("查询失败：" + e.getMessage());
                }
            }
        });
    }

    public static void actionStart(AppCompatActivity activity){
        Intent intent = new Intent(activity, MemoryActivity.class);
        activity.startActivity(intent);
    }

}
