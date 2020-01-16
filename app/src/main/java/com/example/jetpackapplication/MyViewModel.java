package com.example.jetpackapplication;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jeremyliao.liveeventbus.LiveEventBus;

/**
 * @Author: david.lvfujiang
 * @Date: 2019/12/6
 * @Describe:
 */
public class MyViewModel extends AndroidViewModel {
    MutableLiveData<String> liveData = new MutableLiveData();
    Thread thread;
    Context context;

    public MyViewModel(@NonNull Application application) {
        super(application);
        context =application;
    }

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
                        liveData.postValue(String.valueOf(i));
                        LiveEventBus.get().with("event1").post(String.valueOf(i));
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

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e("Tag", "清除数据");
        //给线程设置一个中断标志，不一定保证立马中断，在线程中判断线程是否中断，然后结束循环
        thread.interrupt();
        Toast.makeText(context,"活动关闭",Toast.LENGTH_SHORT).show();

    }
}
