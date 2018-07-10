package cn.linhome.switchbutton;

import android.app.Application;

import com.fanwe.library.SDLibrary;

/**
 * des:
 * Created by yangwb
 * on 2018/5/23.
 */

public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        SDLibrary.getInstance().init(this);
    }
}
