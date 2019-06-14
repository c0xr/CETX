package com.csti.cetx.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.csti.cetx.R;
import com.csti.cetx.fragments.LogInFragment;
import com.csti.cetx.fragments.RegisterFragment;

import cn.bmob.v3.BmobUser;

public class LogInActivity extends BaseActivity {

    private Fragment mLogIn, mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        if (BmobUser.isLogin()){
            MainActivity.actionStart(this);
            finish();
        }

        iniViews();
    }

    private void iniViews(){
        mLogIn    = new LogInFragment();
        mRegister = new RegisterFragment();

        replaceFragment(mLogIn);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    public void setLogInView(){
        replaceFragment(mLogIn);
    }

    public void setRegisterView(){
        replaceFragment(mRegister);
    }

    public static void actionStart(Object activity){
        Intent intent = new Intent((BaseActivity)activity, LogInActivity.class);
        ((BaseActivity) activity).startActivity(intent);
    }

}
