package com.example.jetpackapplication;

import android.app.Application;

import com.jeremyliao.liveeventbus.LiveEventBus;

/**
 * @Author: david.lvfujiang
 * @Date: 2019/12/7
 * @Describe:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LiveEventBus.get()
                .config()
                .supportBroadcast(this)
                .lifecycleObserverAlwaysActive(true);
    }
}
