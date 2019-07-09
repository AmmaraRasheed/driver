package com.example.adminapp.NewBus;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adminapp.R;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CreateNewBus extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private EditText email,pass,busNumber;
    private FirebaseUser firebaseUser;
    private String email1,pass1,busNumber1;
    private  String id;
    private  DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_bus);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("NewBus");
        db=FirebaseDatabase.getInstance().getReference().child("Location");
        firebaseAuth=FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.email_bus);
        pass=(EditText)findViewById(R.id.password_bus);
        busNumber=(EditText)findViewById(R.id.bus_number);
    }
    public void CreateBus(View view){
        email1=email.getText().toString();
        pass1=pass.getText().toString();
        busNumber1=busNumber.getText().toString();
        firebaseUser=firebaseAuth.getCurrentUser();
        firebaseAuth.createUserWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    id=firebaseUser.getUid();
                    GeoFire geoFire=new GeoFire(db);
                    geoFire.setLocation(id,new GeoLocation(33.6274729,73.0909927));
                    HashMap<String,Object> data=new HashMap<>();
                    data.put("email",email1);
                    data.put("Id",id);
                    data.put("BusNumber",busNumber1);
                    databaseReference.child(id).updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"create successfully",Toast.LENGTH_SHORT).show();

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Error "+e,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error "+e,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
