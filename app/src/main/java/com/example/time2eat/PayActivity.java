package com.example.time2eat;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PayActivity extends AppCompatActivity {

    private TextView check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        check = (TextView)findViewById(R.id.check);

        ArrayList<String> res = new ArrayList<>();
        res.add("50.00  Whiskey");
        res.add("15.00  Cola");
        res.add("250.00 Queen of the Dance Floor");
        float price = 0;
        Iterator<String> iter = res.iterator();
        StringBuffer buffer = new StringBuffer();
        while (iter.hasNext()) {
            buffer.append(iter.next() + "\n");
        }
        buffer.append("Total: " + Float.valueOf(price));
        check.setText(buffer.toString());

    }
}
