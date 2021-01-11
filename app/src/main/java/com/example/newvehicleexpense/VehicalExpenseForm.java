package com.example.newvehicleexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VehicalExpenseForm extends AppCompatActivity {

    Button save;
    EditText item,note,date,ammount;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehical_expense_form);

        save=findViewById(R.id.save);
        item=findViewById(R.id.item);
        note=findViewById(R.id.notes);
        date=findViewById(R.id.date);
        ammount=findViewById(R.id.ammount);

        String currentDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
        date.setText(currentDate);
        Log.d("date",currentDate);

        type=getIntent().getStringExtra("type");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserting();
            }
        });
    }
    public void inserting()
    {
        String sItem=item.getText().toString();
        String sNote=note.getText().toString();
        String sDate=date.getText().toString();
        String sAmmount=ammount.getText().toString();

        if(!sItem.isEmpty() && !sNote.isEmpty() && !sDate.isEmpty() && !sAmmount.isEmpty())
        {
            DbHelper db=new DbHelper(this);

            Boolean checkDataInsert=db.inserUserData(sItem,sNote,sDate,sAmmount,type);

            if(checkDataInsert=true)
            {
                Toast.makeText(VehicalExpenseForm.this, "Data is inserted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(VehicalExpenseForm.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Please enter the data", Toast.LENGTH_SHORT).show();
        }
    }
}