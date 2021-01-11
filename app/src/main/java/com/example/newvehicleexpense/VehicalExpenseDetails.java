package com.example.newvehicleexpense;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VehicalExpenseDetails extends AppCompatActivity {

    Button addItem,showData,delete,editData;
    EditText itemName;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehical_expense_details);

        showData=findViewById(R.id.viewData);
        delete=findViewById(R.id.delete);
        addItem=findViewById(R.id.addItem);
        itemName=findViewById(R.id.itemName);
        editData=findViewById(R.id.editData);
        recyclerView=findViewById(R.id.recycleView);

        String type=getIntent().getStringExtra("type");
        Log.d("type",type);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(VehicalExpenseDetails.this,VehicalExpenseForm.class);
                i.putExtra("type",type);
                startActivity(i);

            }
        });

        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteData();
            }
        });

        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewData();
            }
        });
    }

    private void viewData() {

        List<String> dateList = null;

        DbHelper db=new DbHelper(this);

        Cursor res=db.viewData();
        if(res.getCount()==0)
        {
            Toast.makeText(VehicalExpenseDetails.this, "there is no entry", Toast.LENGTH_SHORT).show();
            return;
        }
        dateList=new ArrayList<>();
        while (res.moveToNext())
        {
            dateList.add(res.getString(2));
        }



        ItemAdapter itemAdapter=new ItemAdapter(this,dateList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setHasFixedSize(true);

        // setting list adapter


    }

    private void deleteData() {
        String sitem = itemName.getText().toString();
        DbHelper db = new DbHelper(VehicalExpenseDetails.this);

        if (sitem.isEmpty()) {
            Toast.makeText(this, "Please enter Item Name or Enter valid Item name", Toast.LENGTH_SHORT).show();
        } else {
            Boolean checkInsertData = db.deleteUserData(sitem);
            if (checkInsertData == true) {
                Toast.makeText(VehicalExpenseDetails.this, "Deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(VehicalExpenseDetails.this, "Not Deleted", Toast.LENGTH_SHORT).show();
            }

        }
    }
}