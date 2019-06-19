package com.csti.cetx.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.csti.cetx.R;
import com.csti.cetx.bmob.Vocabulary;
import com.csti.cetx.utils.MyToast;
import com.csti.cetx.views.SlideableCardView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class MemoryActivity extends BaseActivity {

    private List<Vocabulary> mWordsList = new ArrayList<>();
    private int from = 0;
    private int to   = 10;
    private int num = 0;

    private SlideableCardView[] mSlideableCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }

        iniViews();
    }

    private void iniViews(){
        BmobQuery<Vocabulary> query = new BmobQuery<Vocabulary>();
        query.findObjects(new FindListener<Vocabulary>() {
            @Override
            public void done(List<Vocabulary> list, BmobException e) {
                mWordsList.clear();
                for (Vocabulary vocabulary : list){
                    if (!vocabulary.isMemory()){
                        mWordsList.add(vocabulary);
                    }
                }
                iniClicks();
            }
        });
    }

    private void iniClicks(){
        mSlideableCards = new SlideableCardView[]{
                findViewById(R.id.card_1),
                findViewById(R.id.card_2),
                findViewById(R.id.card_3),
                findViewById(R.id.card_4),
                findViewById(R.id.card_5),
                findViewById(R.id.card_6),
                findViewById(R.id.card_7),
                findViewById(R.id.card_8),
                findViewById(R.id.card_9),
                findViewById(R.id.card_10)
        };

        for (SlideableCardView cardView : mSlideableCards){
            if (mWordsList.size()>num && mWordsList.get(num) != null){
                cardView.setParaphraseText(mWordsList.get(num).getParaphrase());
                cardView.setSpellText(mWordsList.get(num++).getSpell());
            }
            cardView.setOnDragListener(new SlideableCardView.OnDragListener() {
                @Override
                public void onDragOut(SlideableCardView.Direction dir) {
                    if (dir==SlideableCardView.Direction.LEFT){
                        if (num < mWordsList.size()){
                            Memory(mWordsList.get(num));
                        }
                        num ++;
                        MyToast.maketext(MemoryActivity.this, "记得该单词");
                    }else {
                        num ++;
                        Forget();
                    }
                }
            });
        }
        num = 0;
    }

    private void Memory(Vocabulary vocabulary){
        vocabulary.setMemory(true);
        vocabulary.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {

            }
        });
    }

    private void Forget(){
        MyToast.maketext(MemoryActivity.this, "忘记了");
    }

    public static void actionStart(AppCompatActivity activity){
        Intent intent = new Intent(activity, MemoryActivity.class);
        activity.startActivity(intent);
    }

}
