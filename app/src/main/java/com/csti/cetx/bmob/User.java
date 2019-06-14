package com.csti.cetx.bmob;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {

    private String mUserName;

    public User(){

    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }
}
