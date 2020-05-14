package com.chaoliu.app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView helloTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void initView(){
        helloTv = findViewById(R.id.helloTv);
        helloTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("hello!");
            }
        });
    }

    void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


}
