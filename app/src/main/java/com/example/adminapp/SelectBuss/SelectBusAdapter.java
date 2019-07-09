package com.example.adminapp.SelectBuss;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.adminapp.R;


import java.util.List;

public class SelectBusAdapter extends RecyclerView.Adapter<SelectBusAdapter.ViewHolder> {
    private List<SelectBUsModel> item;
    private Context context;

    public SelectBusAdapter(List<SelectBUsModel> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.select_bus_layout, null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        SelectBUsModel item1=item.get(i);
        viewHolder.tv.setText(item1.getBussNumber());
        final String id=item1.getBusId();
        viewHolder.ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,StudentMapNew.class);
                intent.putExtra("Id",id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;
        private LinearLayout ln;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ln=(LinearLayout)itemView.findViewById(R.id.linearBus);
            tv=itemView.findViewById(R.id.busNumber);
        }
    }
}
