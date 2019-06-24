package com.csti.cetx.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csti.cetx.R;
import com.csti.cetx.activities.LogInActivity;
import com.csti.cetx.activities.MainActivity;
import com.csti.cetx.bmob.User;
import com.csti.cetx.utils.MyToast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LogInFragment extends Fragment implements View.OnClickListener {

    private View view;

    private TextInputEditText mAccount;
    private TextInputEditText mPassword;
    private AppCompatButton mRegister;
    private AppCompatButton mLogIn;

    public LogInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_log_in, container, false);
        iniViews();
        return view;
    }

    private void iniViews(){
        mAccount = view.findViewById(R.id.account);
        mAccount.setOnClickListener(this);

        mPassword = view.findViewById(R.id.password);
        mPassword.setOnClickListener(this);

        mRegister = view.findViewById(R.id.register);
        mRegister.setOnClickListener(this);

        mLogIn = view.findViewById(R.id.login);
        mLogIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.account:

                return;

            case R.id.password:

                return;

            case R.id.register:
                ((LogInActivity) Objects.requireNonNull(getActivity())).setRegisterView();
                return;

            case R.id.login:
                LogIn();
                return;
        }
    }

    private void LogIn(){
        String account = mAccount.getText().toString();
        String password= mPassword.getText().toString();
        //此处替换为你的用户名密码
        BmobUser.loginByAccount(account, password, new LogInListener<User>() {

            @Override
            public void done(User user, BmobException e) {
                if (e == null){
                    MainActivity.actionStart(getActivity());
                    if (getActivity() != null){
                        getActivity().finish();
                    }
                }else {
                    Log.d("123123", e.getErrorCode() + e.getMessage());
                    MyToast.maketext(getActivity(), "失败，请检查网络");
                }
            }
        });
    }

}
