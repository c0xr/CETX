package com.csti.cetx.utils;

import android.app.Activity;
import android.widget.Toast;

import com.csti.cetx.activities.BaseActivity;

public class MyToast {

    public static void maketext(Activity activity, String ifo){
        Toast.makeText((BaseActivity)activity, ifo, Toast.LENGTH_LONG).show();
    }

}
