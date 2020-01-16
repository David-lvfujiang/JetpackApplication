package com.example.jetpackapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jetpackapplication.mvp.Presenter;
import com.jeremyliao.liveeventbus.LiveEventBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    MyViewModel myViewModel;
    Button button;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getLifecycle().addObserver(new MyObserver());

        textView = findViewById(R.id.tv_text);
        button = findViewById(R.id.bt_button);
        button.setOnClickListener(this);
        //通过ViewModelProviders创建ViewModel
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });

        LiveEventBus.get().with("event1",String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });

//        //观察liveDate对象
//        myViewModel.liveData.observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                Log.e("Tag", s);
//                textView.setText(s);
//            }
//        });
    }
    @Override
    public void onClick(View view)
    {

       // textView.setText("1");
        myViewModel.getName();
    }

}
