package com.example.adminapp.StudentRecord;

;
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


public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {
    private List<RecycleItem> item;
    private Context context;

    public RequestAdapter(List<RecycleItem> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.request_record_activity,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final RecycleItem item1=item.get(i);
        viewHolder.name.setText(item1.getName());
        viewHolder.fname.setText(item1.getFather_name());
        viewHolder.phone.setText(item1.getPhone());
        viewHolder.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=item1.getName();
                String email=item1.getEmail();
                String phone=item1.getPhone();
                String cnic=item1.getCnic();
                String id=item1.getId();
                String fatherName=item1.getFather_name();
                String status=item1.getStatus();
                String rollnumber=item1.getRollnumber();
                String classuser=item1.getUserclass();
                Intent intent=new Intent(context,StudentDetails.class);
                intent.putExtra("name",name);
                intent.putExtra("fname",fatherName);
                intent.putExtra("email",email);
                intent.putExtra("id",id);
                intent.putExtra("CNIC",cnic);
                intent.putExtra("class",classuser);
                intent.putExtra("rollNumber",rollnumber);
                intent.putExtra("status",status);
                intent.putExtra("phone",phone);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name,fname,phone;
        public LinearLayout linearlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name_request);
            fname=(TextView)itemView.findViewById(R.id.father_request);
            phone=(TextView)itemView.findViewById(R.id.phone_request);
            linearlayout=itemView.findViewById(R.id.linear_layout);
        }
    }
}
