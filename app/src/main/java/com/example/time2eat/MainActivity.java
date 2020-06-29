package com.example.time2eat;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {

    Button btnMenu, btnOrder, btnWaiter, btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMenu = (Button)findViewById(R.id.btmenu);
        btnOrder = (Button)findViewById(R.id.btorder);
        btnWaiter = (Button)findViewById(R.id.btwaiter);
        btnPay = (Button)findViewById(R.id.btpay);

        btnMenu.setOnClickListener(new CompoundButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });
        btnOrder.setOnClickListener(new CompoundButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
            }
        });
        btnPay.setOnClickListener(new CompoundButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PayActivity.class));
            }
        });

    }

}
