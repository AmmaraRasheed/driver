package com.example.adminapp.StudentRecord;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcceptedFragment extends Fragment {
    private List<ListItemRecord> list;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private DatabaseReference databaseReference;
    //define position of view
    LinearLayoutManager mLinearLayoutManager;
    private FloatingActionButton btn;


    public AcceptedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_accepted, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("Student");
        btn=v.findViewById(R.id.register3);
        recyclerView = (RecyclerView)v. findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewStudent newStudent=new NewStudent();
                FragmentActivity activity=(FragmentActivity)(getActivity());
                FragmentManager fm=activity.getSupportFragmentManager();
                newStudent.show(fm,"Update");
            }
        });

        // get button id
        View.OnClickListener uu=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int position=(Integer)v.getTag();
                //Toast.makeText(getApplicationContext(),"hi",Toast.LENGTH_SHORT).show();
            }
        };

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        list = new ArrayList<>();
//       Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String status_result=ds.child("status_result").getValue().toString();
                    if(status_result.equals("accepted")){
                        String name = ds.child("name").getValue().toString();
                        String rollnumber = ds.child("rollNumber").getValue().toString();
                        String phone = ds.child("phone").getValue().toString();
                        String email = ds.child("email").getValue().toString();
                        String unique_id=ds.child("unique_id").getValue().toString();
                        String fatherName = ds.child("fatherName").getValue().toString();
                        String userclass = ds.child("classuser").getValue().toString();
                        ListItemRecord listItemRecord = new ListItemRecord(
                                name,
                                rollnumber,
                                phone,
                                email,
                                fatherName,
                                userclass,
                                unique_id
                        );
                        list.add(listItemRecord);
                    }
                }
                RecordAdapter recordAdapter = new RecordAdapter(list, getContext());
                recyclerView.setAdapter(recordAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }

}
