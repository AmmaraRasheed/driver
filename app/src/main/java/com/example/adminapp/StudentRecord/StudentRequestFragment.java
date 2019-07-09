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
public class StudentRequestFragment extends Fragment {
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private List<RecycleItem> list;
    private LinearLayoutManager mLinearLayoutManager;


    public StudentRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_student_request, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("Student");
        recyclerView = (RecyclerView)v. findViewById(R.id.request_recycle);
        recyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManager);
//        Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
        list = new ArrayList<>();
//        Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String status_result=ds.child("status_result").getValue().toString();
                    if(status_result.equals("pending")){
                        String phone = ds.child("phone").getValue().toString();
                        String id = ds.child("unique_id").getValue().toString();
                        String name = ds.child("name").getValue().toString();
                        String fatherName = ds.child("fatherName").getValue().toString();
                        /*Get remaining data for next act*/
                        String email=ds.child("email").getValue().toString();
                        String CNIC=ds.child("CNIC").getValue().toString();
                        String classuser=ds.child("classuser").getValue().toString();
                        String rollnumber=ds.child("rollNumber").getValue().toString();
                        String status=ds.child("status").getValue().toString();
                        RecycleItem recycleItem= new RecycleItem(
                                name,
                                fatherName,
                                phone,
                                email,CNIC,classuser,rollnumber,status,id
                        );
                        list.add(recycleItem);
                    }
                }
                RequestAdapter recordAdapter = new RequestAdapter(list,getContext());
                recyclerView.setAdapter(recordAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }

}
