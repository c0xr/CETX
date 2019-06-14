package com.csti.cetx.utils.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.csti.cetx.R;
import com.csti.cetx.activities.BaseActivity;
import com.csti.cetx.bmob.Vocabulary;
import com.csti.cetx.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;

public class PushItemsActivity extends BaseActivity implements View.OnClickListener{

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
        Vocabulary vocabulary = new Vocabulary("ability", "n.能力；才能");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("able", "adj.能够；有能力的");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("about", "adv.大约；到处；四处prep.关于；在各处；四处");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("above", "prep.在…上面adj.上面的adv.在…之上");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("abroad", "adv.到(在)国外");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("absent", "adj.缺席，不在");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("accent", "n.口音，音调");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("accept", "vt.接受");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("accident", "n.事故，意外的事");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("according to", "prep.按照，根据");
        vocabularies.add(vocabulary);

        vocabulary = new Vocabulary("account ","n.账目；描述");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("ache"," vi.&n.痛，疼痛");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("achieve"," vt.达到，取得");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("across"," prep.横过，穿过");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("act"," n.法令，条例v.(戏)表演，扮演(角色)，演出(戏)；行动，故事");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("action "," n.行动");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("active"," adj.积极的，主动的");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("activity"," n.活动");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("actor"," n.男演员");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("actress"," n.女演员");
        vocabularies.add(vocabulary);

        vocabulary = new Vocabulary("actual"," adj.实际的；现实的");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("AD"," n.公元");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("add"," vt.添加，增加");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("address"," n.地址");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("admire"," v.钦佩；羡慕");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("admit"," vt.承认，准许(入场，入学，入会)");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("adult"," n.成年人");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("advance"," v.推进，促进；前进");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("advantage"," n.优点；好处");
        vocabularies.add(vocabulary);
        vocabulary = new Vocabulary("adventure"," n.冒险；奇遇");
        vocabularies.add(vocabulary);

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
                    MyToast.maketext(PushItemsActivity.this, "修改成功");
                } else {
                    MyToast.maketext(PushItemsActivity.this, "修改失败");
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
