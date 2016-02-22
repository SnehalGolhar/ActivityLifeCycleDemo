package com.example.vaibhav.helloapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
         count = bundle.getInt("count");
        Log.d("acitivityb", count + "");
        setContentView(R.layout.activity_main2);
        final Context context = this;
        Button buttona = (Button) findViewById(R.id.btnstop);
        buttona.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {

                    Bundle bundle = new Bundle();
                    bundle.putInt("count",count);
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}
