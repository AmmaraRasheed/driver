package com.example.adminapp.BusStop;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class StopRecycleAdapter extends RecyclerView.Adapter<StopRecycleAdapter.MyViewHolder> {
    private List<RecycleItemStop> item;
    public Context context1;
    private DatabaseReference databaseReference;
    private Dialog mdialog;
    private RecycleItemStop item1;
    public StopRecycleAdapter(List<RecycleItemStop> item, Context context) {
        this.item = item;
        this.context1 = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context1).inflate(R.layout.bus_recycle_lyout,viewGroup,false);
        Toast.makeText(context1,"1",Toast.LENGTH_SHORT).show();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, int i) {
         item1=item.get(i);
        viewHolder.stop.setText(item1.getStop());
        Toast.makeText(context1,"2",Toast.LENGTH_SHORT).show();
        viewHolder.stop_serial.setText(item1.getStopTitle());

//        mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
//        viewHolder.delet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context1,"delete   ",Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView stop,stop_serial,delet;
        private LinearLayout linearLayout;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            stop=(TextView)itemView.findViewById(R.id.recycle_stop);
            stop_serial=(TextView)itemView.findViewById(R.id.recycle_serial);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linerlayout1);
            delet=(TextView)itemView.findViewById(R.id.delete_stop);
            delet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer positn=getAdapterPosition();
                    final String serial=item.get(positn).getStopTitle();
                    AlertDialog.Builder builder=new AlertDialog.Builder(context1);
                    builder.setTitle("Do you Want to delete");
                    builder.setCancelable(false)
                            .setMessage("press yes to delete")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    databaseReference=FirebaseDatabase.getInstance().getReference().child("Stops");
                                    databaseReference.child("stop"+serial).removeValue();

                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }
            });
            mdialog=new Dialog(context1);
            mdialog.setContentView(R.layout.update_stop_dialog);
            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer positn=getAdapterPosition();
                    final String serial1 =item.get(positn).getStopTitle();
                    String oldstop=item.get(positn).getStop();

                    final EditText update_stop=mdialog.findViewById(R.id.update_stop);
                    final EditText update_serial=mdialog.findViewById(R.id.update_serial);
                    TextView cncel=mdialog.findViewById(R.id.cancel_stop);
                    TextView update=mdialog.findViewById(R.id.updatestop);
                    databaseReference=FirebaseDatabase.getInstance().getReference().child("Stops");
                    update_stop.setText(oldstop);
                    update_serial.setText(serial1);
                    mdialog.show();
                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String stop1=update_stop.getText().toString();
                            String serial=update_serial.getText().toString();
                            if(stop1.isEmpty() || serial.isEmpty()){
                                Toast.makeText(context1,"Field not empty ",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                HashMap<String,Object> data=new HashMap<>();
                                data.put("stop",stop1);
                                data.put("value",serial);
                                databaseReference.child("stop"+serial1).removeValue();
                                databaseReference.child("stop"+serial).updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(context1,"done",Toast.LENGTH_SHORT).show();
                                            mdialog.dismiss();
                                        }
                                        else{
                                            Toast.makeText(context1,"not done",Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                            }
//                            Toast.makeText(context1,"stop "+stop1,Toast.LENGTH_SHORT).show();

                        }
                    });
                    cncel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mdialog.dismiss();
                        }
                    });
                }
            });
        }
    }
}
