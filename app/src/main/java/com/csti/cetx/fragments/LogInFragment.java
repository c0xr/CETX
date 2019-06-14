package com.csti.cetx.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csti.cetx.R;
import com.csti.cetx.activities.LogInActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LogInFragment extends Fragment implements View.OnClickListener {

    private View view;

    private TextInputLayout mAccount;
    private TextInputLayout mPassword;
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

                return;
        }
    }

    private void LogIn(){

    }

}
