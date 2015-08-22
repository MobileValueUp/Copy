package com.example.kimteaho.copy;

import android.app.Application;

/**
 * Created by user on 8/21/15.
 */
public class UserInfoGlobal extends Application {

    private String usCd;
    private String point;
    private String name;
    private boolean islogin=false;

    public void setAll()
    {
        usCd ="";
        point="";
        name="";

    }

    public String getUsCd()
    {
        return usCd;
    }

    public String getPoint()
    {
        return point;
    }

    public String getName()
    {
        return name;
    }

    public boolean isIslogin()
    {
        return islogin;
    }

    public void setLogin(boolean uname)
    {
        islogin = uname;
    }

    public void setName(String uname)
    {
        name = uname;
    }

    public void setUsCd(String uscd)
    {
        usCd = uscd;
    }

    public void setPoint(String pnt)
    {
        point = pnt;
    }

}
