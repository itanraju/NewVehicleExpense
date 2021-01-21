package com.example.newvehicleexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class VehicalExpenseDetails extends AppCompatActivity {

    Button addItem,showData,delete,editData;

    List<DbModel> dateGroupList;

    List<StringBuffer> childList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehical_expense_details);

        showData=findViewById(R.id.viewData);
        delete=findViewById(R.id.delete);
        addItem=findViewById(R.id.addItem);
        editData=findViewById(R.id.editData);

        dateGroupList=new ArrayList<>();
        childList=new ArrayList<>();
      /*  recyclerView=findViewById(R.id.recycleView);*/

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

                Intent i=new Intent(VehicalExpenseDetails.this,ShowDataActivity.class);
                startActivity(i);

               /* viewData();*/
            }
        });
    }

    private void viewData() {


        DbHelper db=new DbHelper(this);

        Cursor res=db.viewData();
        if(res.getCount()==0)
        {
            Toast.makeText(VehicalExpenseDetails.this, "there is no entry", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer=new StringBuffer();



        while (res.moveToNext())
        {
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Item Name : "+res.getString(1)+"\n");
            buffer.append("Note : "+res.getString(2)+"\n");
            buffer.append("Date : "+res.getString(3)+"\n");
            buffer.append("Ammount : "+res.getString(4)+"\n");
            buffer.append("Type : "+res.getString(5)+"\n\n");

            DbModel model=new DbModel();

            model.setId(res.getString(0));
            model.setItemName(res.getColumnName(1));
            model.setNote(res.getColumnName(2));
            model.setDate(res.getString(3));
            model.setAmmount(res.getColumnName(4));
            model.setType(res.getColumnName(5));

            dateGroupList.add(model);
        }

      /*  StringBuffer stringBuffer=new StringBuffer();

        for(int i=0;i<dateGroupList.size();i++)
        {
            Cursor res2=db.getDatabyDate(dateGroupList.get(i).getDate());

            while (res2.moveToNext())
            {
                stringBuffer.append("Id : "+res2.getString(0)+"\n");
                stringBuffer.append("Item Name : "+res2.getString(1)+"\n");
                stringBuffer.append("Note : "+res2.getString(2)+"\n");
                stringBuffer.append("Date : "+res2.getString(3)+"\n");
                stringBuffer.append("Ammount : "+res2.getString(4)+"\n");
                stringBuffer.append("Type : "+res2.getString(5)+"\n\n");
            }

            childList.add(stringBuffer);
        }

        Log.d("size",childList.get(1).toString());

        AlertDialog.Builder builder=new AlertDialog.Builder(VehicalExpenseDetails.this);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.ic_launcher_foreground);
        builder.setTitle("User Vehicle Mileage");
        builder.setPositiveButton("Okey Bro !",null);
        builder.setMessage(stringBuffer);
        builder.show();
*/
    }

    private void deleteData() {

        DbHelper db = new DbHelper(VehicalExpenseDetails.this);

        db.deleteTable();
        Toast.makeText(this, "Table Deleted", Toast.LENGTH_SHORT).show();

    }
}