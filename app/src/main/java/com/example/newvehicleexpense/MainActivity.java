package com.example.newvehicleexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button accesories,cleanLines,carFuel,maintenance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accesories=findViewById(R.id.accessories);
        cleanLines=findViewById(R.id.clean);
        carFuel=findViewById(R.id.carFuel);
        maintenance=findViewById(R.id.maintenance);

        accesories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,VehicalExpenseDetails.class);
                i.putExtra("type","accesories");
                startActivity(i);
            }
        });

        cleanLines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,VehicalExpenseDetails.class);
                i.putExtra("type","cleanlines");
                startActivity(i);
            }
        });

        carFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String expenseType="Car Fuel";
                Intent i=new Intent(MainActivity.this,VehicalExpenseDetails.class);
                i.putExtra("type","carfuel");
                startActivity(i);
            }
        });

        maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String expenseType="Maintenance";
                Intent i=new Intent(MainActivity.this,VehicalExpenseDetails.class);
                i.putExtra("type","maintenance");
                startActivity(i);
            }
        });
    }
}