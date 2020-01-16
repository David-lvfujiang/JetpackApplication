package com.example.jetpackapplication.mvp;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

/**
 * @Author: david.lvfujiang
 * @Date: 2019/12/7
 * @Describe:
 */
public class Presenter {
    private Thread thread;
    public void getName() {
        thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Log.e("Tag", "" + i);
                        if (thread.isInterrupted()) {
                            throw new InterruptedException();
                        }
                        EventBus.getDefault().post(String.valueOf(i));
                        Thread.sleep(1000);

                    } catch (Exception e) {
                        Log.e("Tag", e.toString());
                        break;
                    }

                }

            }
        };
        thread.start();

    }
}
