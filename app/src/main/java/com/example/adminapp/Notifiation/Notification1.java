package com.example.adminapp.Notifiation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.adminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Notification1 extends AppCompatActivity {
    private EditText notification;
    private RecyclerView recyclerView;
    private DatabaseReference db;
    private List<NotificationModel> list;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification1);
        notification=findViewById(R.id.noification);
        recyclerView=findViewById(R.id.recyclenotification);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        list=new ArrayList<>();
        db= FirebaseDatabase.getInstance().getReference().child("Notification");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String msg=ds.child("message").getValue().toString();
                    String date=ds.child("date").getValue().toString();
                    String time=ds.child("time").getValue().toString();
                    NotificationModel notificationModel=new NotificationModel(msg,date,time);
                    list.add(notificationModel);
                }
                NotificationRecycleAdapter recordAdapter = new NotificationRecycleAdapter(list,Notification1.this);
                recyclerView.setAdapter(recordAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void saveNotifications(View view){
//        Calendar time =Calendar.getInstance();
//        SimpleDateFormat timeFormat=new SimpleDateFormat("hh:mm:ss a");
//        String date=DateFormat.getDateInstance(DateFormat.FULL).format(time.getTime());
//        String message=notification.getText().toString();
//        notification.setText("");
//        String time1=timeFormat.format(time.getTime());
//        NotificationModel notificationModel=new NotificationModel(message,date,time1);
//        db.push().setValue(notificationModel).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(getApplicationContext(),"Notification send",Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(),"Error is "+e,Toast.LENGTH_SHORT).show();
//            }
//        });
        Intent intent=new Intent(this,SendAllStudent.class);
        startActivity(intent);
//        Toast.makeText(getApplicationContext(),date,Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(),time1,Toast.LENGTH_SHORT).show();

    }
}
