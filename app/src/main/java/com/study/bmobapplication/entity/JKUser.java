package com.study.bmobapplication.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2015-12-28.
 */
public class JKUser extends BmobUser{

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
