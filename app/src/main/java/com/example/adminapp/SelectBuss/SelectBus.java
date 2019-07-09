package com.example.adminapp.SelectBuss;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelectBus extends AppCompatActivity {
    private DatabaseReference db;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private List<SelectBUsModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bus);
        list=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.busRecycle);
        recyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(SelectBus.this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        db=FirebaseDatabase.getInstance().getReference().child("NewBus");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String busNumber=ds.child("BusNumber").getValue().toString();
                    busNumber="Bus"+busNumber;
                    String busId=ds.child("Id").getValue().toString();
                    SelectBUsModel selectBUsModel=new SelectBUsModel(busNumber,busId);
                    list.add(selectBUsModel);
                }
                SelectBusAdapter selectBusAdapter=new SelectBusAdapter(list,SelectBus.this);
                recyclerView.setAdapter(selectBusAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        list=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.busRecycle);
        recyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(SelectBus.this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        db= FirebaseDatabase.getInstance().getReference().child("NewBus");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String busNumber=ds.child("BusNumber").getValue().toString();
                    busNumber="Bus"+busNumber;
                    String busId=ds.child("Id").getValue().toString();
                    SelectBUsModel selectBUsModel=new SelectBUsModel(busNumber,busId);
                    list.add(selectBUsModel);
                }
                SelectBusAdapter selectBusAdapter=new SelectBusAdapter(list,SelectBus.this);
                recyclerView.setAdapter(selectBusAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
