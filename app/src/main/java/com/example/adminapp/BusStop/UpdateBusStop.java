package com.example.adminapp.BusStop;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateBusStop extends AppCompatActivity {
    private EditText newstop,stopNum;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private List<RecycleItemStop> list;
    private Toolbar toolbar;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bus_stop);
        toolbar=findViewById(R.id.mainPageBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bus stop Information");
        recyclerView=findViewById(R.id.stopRecycle);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Stops");
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        list = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    list.clear();
                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        String stop=ds.child("stop").getValue().toString();
                        String serial=ds.child("value").getValue().toString();
                        RecycleItemStop item=new RecycleItemStop(stop,serial);
                        list.add(item);

                    }
                    StopRecycleAdapter recordAdapter = new StopRecycleAdapter(list, UpdateBusStop.this);
                    recyclerView.setAdapter(recordAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error is "+databaseError,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
