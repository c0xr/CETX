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
    private AppCompatButton   mAppCompatButton;   // 注册

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

        mAppCompatButton = view.findViewById(R.id.register);
        mAppCompatButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.account:

                return;

            case R.id.password:

                return;

            case R.id.again_password:

                return;

            case R.id.register:
                pushResistQuest();
                return;
        }
    }

    private void pushResistQuest(){
        final String account = mAccountText.getText().toString();
        final String password= mPasswordText.getText().toString();
        if (password != mAgainPasswordText.getText().toString()){
            Toast.makeText(getActivity(), "两次输入密码不一致，重新输入", Toast.LENGTH_LONG).show();
            mAgainPasswordText.setText("");
            return;
        }

        User p2 = new User();
        p2.setmUserName(account);
        p2.setPassword(password);
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    MyToast.maketext(getActivity(), "注册成功");
                }else{
                    MyToast.maketext(getActivity(), "注册失败，请检查网络");
                }
            }
        });
    }

}
