package com.example.newvehicleexpense;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    Context context;
    List<String> dateList=new ArrayList<>();

    public ItemAdapter(Context context, List<String> dateList) {
        this.context = context;
        this.dateList = dateList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.recycleview_items_layout,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemsName.setText(dateList.get(position));
        String sDate=dateList.get(position);
        holder.itemsName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbHelper db=new DbHelper(context);

                Cursor res2=db.getDatabyDate(sDate);
                if(res2.getCount()==0)
                {
                    Toast.makeText(context, "there is no entry", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<StringBuffer> dataByDate=new ArrayList<>();
                StringBuffer buffer=new StringBuffer();
                while (res2.moveToNext())
                {
                    buffer.append("Item Name : "+res2.getString(0)+"\n");
                    buffer.append("Note : "+res2.getString(1)+"\n");
                    buffer.append("Date : "+res2.getString(2)+"\n");
                    buffer.append("Ammount : "+res2.getString(3)+"\n");
                    buffer.append("Type : "+res2.getString(4)+"\n\n");

                    dataByDate.add(buffer);
                }

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setIcon(R.drawable.ic_launcher_foreground);
                builder.setTitle("User Expense Detail");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView itemsName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemsName=itemView.findViewById(R.id.items);
        }
    }
}
