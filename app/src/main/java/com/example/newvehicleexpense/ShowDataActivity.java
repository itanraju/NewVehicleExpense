package com.example.newvehicleexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ShowDataActivity extends AppCompatActivity {

    ExpandableListView expandableListView;

    List<String> listGroup;
    List<String> listChild;

    String fullData;

    HashMap<String, List<String>> bindingList;

    ExpandListViewAdapter expandListViewAdapter;

    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        expandableListView=findViewById(R.id.expandListView);
        total=findViewById(R.id.total);

        listGroup=new ArrayList<String>();
        listChild=new ArrayList<String>();

        listGroupData();
        listChildData();
        listCollection();

        expandListViewAdapter=new ExpandListViewAdapter(this,listGroup,bindingList);
        expandableListView.setAdapter(expandListViewAdapter);


        DbHelper db=new DbHelper(this);

        Cursor res3=db.getTotal();

        while (res3.moveToNext())
        {
            total.setText(res3.getString(res3.getColumnIndex("total")));
        }

    }

    private void listCollection() {

        bindingList=new HashMap<String, List<String>>();

        for(int i=0;i<listGroup.size();i++)
        {
            bindingList.put(listGroup.get(i).toString(), Collections.singletonList(listChild.get(i)));
        }
        Log.d("coll",bindingList.toString());
    }

    private void listChildData() {

        DbHelper db=new DbHelper(ShowDataActivity.this);

        StringBuffer buffer = null;



        for (int i=0;i<listGroup.size();i++)
        {
            Cursor res2=db.getDatabyDate(listGroup.get(i).toString());

            while (res2.moveToNext())
            {
                    buffer=new StringBuffer();

                    buffer.append("Name : "+res2.getString(1)+"\n");
                    buffer.append("Note : "+res2.getString(2)+"\n");
                    buffer.append("Ammount : "+res2.getString(4)+"\n");
                    buffer.append("Type : "+res2.getString(5)+"\n\n");

                    DbModel model=new DbModel();

                      model.setItemName(res2.getString(1));
                      model.setNote(res2.getString(2));
                      model.setDate(res2.getString(3));
                      model.setAmmount(res2.getString(4));
                      model.setType(res2.getString(5));

            }
            listChild.add(String.valueOf(buffer));
        }



        System.out.println(listChild);
    }

    private void listGroupData() {

        DbHelper db=new DbHelper(ShowDataActivity.this);

        Cursor res=db.viewData();

        if(res.getCount()==0)
        {
            Toast.makeText(this, "No Record Available", Toast.LENGTH_SHORT).show();
        }



        while (res.moveToNext())
        {
            StringBuffer stringBuffer=new StringBuffer();

            stringBuffer.append(res.getString(3));

            DbModel model=new DbModel();

            model.setDate(res.getString(3));

            listGroup.add(String.valueOf(stringBuffer));
        }


        System.out.println(listGroup);

    }

    private void gettingData() {

      /*  DbHelper db=new DbHelper(this);

        dateList=new ArrayList<>();

        Cursor res=db.viewData();
        if(res.getCount()==0)
        {
            Toast.makeText(ShowDataActivity.this, "there is no entry", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer=new StringBuffer();

        while (res.moveToNext()) {

            DbModel model = new DbModel();

            model.setId(res.getString(0));
            model.setItemName(res.getColumnName(1));
            model.setNote(res.getColumnName(2));
            model.setDate(res.getString(3));
            model.setAmmount(res.getColumnName(4));
            model.setType(res.getColumnName(5));

            dateList.add(model);*/
        }

}