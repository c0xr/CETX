package com.csti.cetx.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.csti.cetx.R;
import com.csti.cetx.activities.BaseActivity;
import com.csti.cetx.activities.LogInActivity;
import com.csti.cetx.bmob.User;
import com.csti.cetx.utils.MyToast;
import com.google.android.material.textfield.TextInputEditText;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    private View view;

    private TextInputEditText mAccountText;      // 账户
    private TextInputEditText mPasswordText;     // 密码
    private TextInputEditText mAgainPasswordText;// 再次输入密码
    private AppCompatButton   mRegistButton;  // 注册
    private AppCompatButton   mCancelButton;  // 注册

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);
        iniViews();
        return view;
    }

    private void iniViews(){
        mAccountText = view.findViewById(R.id.account);
        mAccountText.setOnClickListener(this);

        mPasswordText = view.findViewById(R.id.password);
        mPasswordText.setOnClickListener(this);

        mAgainPasswordText = view.findViewById(R.id.again_password);
        mAgainPasswordText.setOnClickListener(this);

        mRegistButton = view.findViewById(R.id.register);
        mRegistButton.setOnClickListener(this);

        mCancelButton = view.findViewById(R.id.cancel);
        mCancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                if (getActivity() != null)
                    ((LogInActivity)getActivity()).setLogInView();
                return;

            case R.id.register:
                pushResistQuest();
                return;
        }
    }

    private void pushResistQuest(){
        final String account = mAccountText.getText().toString();
        final String password= mPasswordText.getText().toString();
        if (!password.equals(mAgainPasswordText.getText().toString())){
            Toast.makeText(getActivity(), "两次输入密码不一致，重新输入", Toast.LENGTH_LONG).show();
            mAgainPasswordText.setText("");
            return;
        }

        // 使用BmobSDK提供的注册功能
        User myUser=new User();
        myUser.setUsername(account);
        myUser.setPassword(password);
        myUser.signUp(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if(e==null){
                    Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                    ((LogInActivity)getActivity()).setLogInView();
                }else{
                    //loge(e);
                    Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
