package com.csti.cetx.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.csti.cetx.R;
import com.csti.cetx.bmob.Vocabulary;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;

public class PushItemsActivity extends AppCompatActivity implements View.OnClickListener{

    private List<BmobObject> vocabularies = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_items);

        iniViews();
        iniWordsList();
    }

    private void iniViews(){
        TextView textView = findViewById(R.id.text);
        textView.setOnClickListener(this);
    }

    private void iniWordsList(){
        for (int i = 0; i < 3; i++) {
            Vocabulary vocabulary = new Vocabulary();
//            vocabulary.setName("category"category + i);
//            vocabulary.setDesc("类别" + i);
//            vocabulary.setSequence(i);
            vocabularies.add(vocabulary);
        }

    }

    private void pushToBmob(){
        new BmobBatch().insertBatch(vocabularies).doBatch(new QueryListListener<BatchResult>() {

            @Override
            public void done(List<BatchResult> results, BmobException e) {
                if (e == null) {
                    for (int i = 0; i < results.size(); i++) {
                        BatchResult result = results.get(i);
                        BmobException ex = result.getError();
                        if (ex == null) {

                        } else {

                        }
                    }
                } else {

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        pushToBmob();
    }

    public static void actionStart(AppCompatActivity activity){
        Intent intent = new Intent(activity, PushItemsActivity.class);
        activity.startActivity(intent);
    }
}
