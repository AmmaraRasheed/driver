package com.example.adminapp.Notifiation;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.adminapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SendAllStudent extends AppCompatActivity  implements AllStudentAdapter.ChangeStatusListener{
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference db;
    private AllStudentstatusModel model;
    private ArrayList<AllStudentstatusModel> list;
    private Toolbar toolbar;
    private AllStudentAdapter allStudentAdapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_all_student);
        toolbar=findViewById(R.id.mainPageBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select Option");
        recyclerView=findViewById(R.id.AllStudentRecycle);
        list=new ArrayList<>();
        db= FirebaseDatabase.getInstance().getReference().child("Student");
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String name=ds.child("name").getValue().toString();
                    String status=ds.child("status").getValue().toString();
                    String image=ds.child("Image").getValue().toString();
                    String id=ds.child("unique_id").getValue().toString();
                    model=new AllStudentstatusModel(name,status,image,id);
                    list.add(model);
                }
                allStudentAdapter=new AllStudentAdapter(list,SendAllStudent.this);
                recyclerView.setAdapter(allStudentAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onItemChangeListener(int postion, AllStudentstatusModel country) {
        try {
            Toast.makeText(this,"2",Toast.LENGTH_SHORT).show();
            list.set(postion, country);
        } catch (Exception e){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.all_student_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.send){
            updateListCountries();
        }
        return true;
    }
    private void updateListCountries(){
        ArrayList<String> tmp = new ArrayList<>();

        try {

            for(int i = 0;i<list.size();i++){
                if(list.get(i).isSelect()){
                    tmp.add(list.get(i).getId());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0;i<tmp.size();i++){
            Toast.makeText(getApplicationContext(), " id is "+tmp.get(i),Toast.LENGTH_SHORT).show();
        }


//        list=tmp;

//        if(list.size() == 0){
//            recyclerView.setVisibility(View.GONE);
//        }
//        allStudentAdapter.setlist(list);
//        allStudentAdapter.notifyDataSetChanged();
    }
}
