package com.example.adminapp.Notifiation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminapp.R;
import java.util.List;

public class NotificationRecycleAdapter extends RecyclerView.Adapter<NotificationRecycleAdapter.ViewHolder> {
    private List<NotificationModel> item;
    private Context context;

    public NotificationRecycleAdapter(List<NotificationModel> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_recycle_layout,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final NotificationModel item1=item.get(i);
        viewHolder.msg.setText(item1.getMessage());
        viewHolder.date.setText(item1.getDate());
        viewHolder.time.setText(item1.getTime());
    }
    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView msg,date,time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            msg=(TextView)itemView.findViewById(R.id.showNoti);
            date=(TextView)itemView.findViewById(R.id.showdate);
            time=(TextView)itemView.findViewById(R.id.showtime);
        }
    }
}
