package com.csti.cetx.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.csti.cetx.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.listener.SaveListener;

public class PushItemsActivity extends AppCompatActivity implements View.OnClickListener{

    private List<String> mWordsList = new ArrayList();

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

    }

    private void pushToBmob(){
        Person p2 = new Person();
        p2.setName("lucky");
        p2.setAddress("北京海淀");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    toast("添加数据成功，返回objectId为："+objectId);
                }else{
                    toast("创建数据失败：" + e.getMessage());
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
