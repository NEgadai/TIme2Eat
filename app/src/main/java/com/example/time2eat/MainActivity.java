/* Assignment: Final
Campus: Ashdod
Author: Daniel Sukharev, ID: 205583008
Author: Tony Schnider, ID: 205515828
Author: Ben Sopher, ID: 203735170
*/

package com.example.time2eat;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnMenu, btnOrder, btnWaiter, btnPay, btnSelect;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        btnMenu = (Button)findViewById(R.id.btmenu);
        btnOrder = (Button)findViewById(R.id.btorder);
        btnWaiter = (Button)findViewById(R.id.btwaiter);
        btnPay = (Button)findViewById(R.id.btpay);
        btnSelect = (Button)findViewById(R.id.btselect);
        spinner = findViewById(R.id.spinner);

        // Block Buttons
        btnMenu.setEnabled(false);
        btnOrder.setEnabled(false);
        btnWaiter.setEnabled(false);
        btnPay.setEnabled(false);

        List<String> tables = new ArrayList<>();
        tables.add(0, "Choose the table");
        ArrayList<Table> exist_table = dbWrapper.tables;
        for(int i = 0; i < exist_table.size(); i++)
            tables.add(String.valueOf(exist_table.get(i).getID()));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


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
        btnWaiter.setOnClickListener(new CompoundButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"The waiter received the call, please wait.", Toast.LENGTH_SHORT).show();
            }
        });
        btnSelect.setOnClickListener(new CompoundButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner.getSelectedItem().toString().equals("Choose the table")){
                    Toast.makeText(MainActivity.this,"Please Select a Table", Toast.LENGTH_SHORT).show();
                }
                else {
                    String item = spinner.getSelectedItem().toString();
                    Toast.makeText(MainActivity.this,"Selected: " + item + " Table", Toast.LENGTH_SHORT).show();
                    btnMenu.setEnabled(true);
                    btnOrder.setEnabled(true);
                    btnWaiter.setEnabled(true);
                    btnPay.setEnabled(true);
                    btnSelect.setEnabled(false);
                    spinner.setEnabled(false);
                }
            }
        });

    }

}
