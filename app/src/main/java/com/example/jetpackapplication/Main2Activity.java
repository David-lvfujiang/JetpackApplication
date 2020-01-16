package com.example.jetpackapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jeremyliao.liveeventbus.LiveEventBus;

public class Main2Activity extends AppCompatActivity  implements View.OnClickListener {

    TextView textView;
    MyViewModel myViewModel;
    Button button;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.tv_text2);
        button =  findViewById(R.id.bt_button2);
        button.setOnClickListener(this);
        //通过ViewModelProviders创建ViewModel
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
//        LiveEventBus.get().with("event1",String.class).observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                textView.setText(s);
//            }
//        });


    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
    }
}
