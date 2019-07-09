package com.example.adminapp.StudentRecord;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    private List<ListItemRecord> listitem;
    private Context context;
    public Button update,delete;
    static String id_user;
    DatabaseReference firebaseDatabase;
    static public String name3,phone3,email3,roll3,father3,userclass3;
    public RecordAdapter(List<ListItemRecord> listitem, Context context) {
        this.listitem = listitem;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.record_list, viewGroup, false);
        //Toast.makeText(context,"4"+viewGroup,Toast.LENGTH_SHORT).show();
        //Toast.makeText(context,"5"+i,Toast.LENGTH_SHORT).show();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ListItemRecord listItems = listitem.get(i);
        viewHolder.name4.setText(listItems.getName());
        viewHolder.fatherName.setText(listItems.getFatherName());
        viewHolder.email.setText(listItems.getEmail());
        viewHolder.phone.setText(listItems.getPhone());
        viewHolder.rollNumber.setText(listItems.getRollNumber());
        viewHolder.userClass.setText(listItems.getClassuser());
//        viewHolder.id.setText((listItems.getUnique_id()));
        //Toast.makeText(context,"6"+viewHolder,Toast.LENGTH_SHORT).show();
        //Toast.makeText(context,"7"+i,Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return listitem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name4,fatherName,userClass,rollNumber,phone,email,id;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            name4=itemView.findViewById(R.id.name);
            fatherName=itemView.findViewById(R.id.fatherName);
            userClass=itemView.findViewById(R.id.userClass);
            rollNumber=itemView.findViewById(R.id.rollNum);
            phone=itemView.findViewById(R.id.phone);
            email=itemView.findViewById(R.id.email);
//            id=itemView.findViewById(R.id.id);
            update=itemView.findViewById(R.id.updateRecord);
            delete=itemView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer positn=getAdapterPosition();
                    id_user=listitem.get(positn).getUnique_id();
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("Do you Want to delete");
                    builder.setCancelable(false)
                            .setMessage("press yes to delete")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    firebaseDatabase=FirebaseDatabase.getInstance().getReference().child("Student").child(id_user);
                                    firebaseDatabase.removeValue();

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

            /* Toast.makeText(context,"8" +itemView,Toast.LENGTH_SHORT).show(); */
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer positn=getAdapterPosition();
                    id_user=listitem.get(positn).getUnique_id();
                    firebaseDatabase=FirebaseDatabase.getInstance().getReference().child("Student");
                    firebaseDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            name3=dataSnapshot.child(id_user).child("name").getValue().toString();
                            phone3=dataSnapshot.child(id_user).child("phone").getValue().toString();
                            roll3=dataSnapshot.child(id_user).child("rollNumber").getValue().toString();
                            userclass3=dataSnapshot.child(id_user).child("classuser").getValue().toString();
                            email3=dataSnapshot.child(id_user).child("email").getValue().toString();
                            father3=dataSnapshot.child(id_user).child("fatherName").getValue().toString();
                            FragmentActivity activity=(FragmentActivity)(context);
                            FragmentManager fm=activity.getSupportFragmentManager();
                            Update update=new Update();
                            update.show(fm,"Update");

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                   /* Integer positn=getAdapterPosition();
                    id_user=listitem.get(positn).getUnique_id();
                   // Toast.makeText(context,"88"+ listitem.get(positn).getId(),Toast.LENGTH_SHORT).show();
                    FragmentActivity activity=(FragmentActivity)(context);
                    FragmentManager fm=activity.getSupportFragmentManager();
                    Update update=new Update();
                        Toast.makeText(context,"hi",Toast.LENGTH_SHORT).show();
                    update.show(fm,"Update");*/



                }
            });

        }
    }


}
