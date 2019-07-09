package com.example.adminapp.StudentRecord;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminapp.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static com.example.adminapp.StudentRecord.RecordAdapter.email3;
import static com.example.adminapp.StudentRecord.RecordAdapter.father3;
import static com.example.adminapp.StudentRecord.RecordAdapter.id_user;
import static com.example.adminapp.StudentRecord.RecordAdapter.name3;
import static com.example.adminapp.StudentRecord.RecordAdapter.phone3;
import static com.example.adminapp.StudentRecord.RecordAdapter.roll3;
import static com.example.adminapp.StudentRecord.RecordAdapter.userclass3;


public class Update extends DialogFragment {
    TextView cancel,update;
    DatabaseReference firebaseDatabase;
    static public EditText name,roll_number,father_name,user_class,email,phone_number;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_of_update,container,false);
        firebaseDatabase=FirebaseDatabase.getInstance().getReference().child("Student");
        cancel=view.findViewById(R.id.cancel);
        update=view.findViewById(R.id.update);
        name=view.findViewById(R.id.name1);
        //  Toast.makeText(getContext(),"hoh  ",Toast.LENGTH_SHORT).show();
        email=view.findViewById(R.id.email1);
        roll_number=view.findViewById(R.id.rollnmber1);
        father_name=view.findViewById(R.id.fathername1);
        user_class=view.findViewById(R.id.userclase1);
        phone_number=view.findViewById(R.id.phone1);

        name.setText(name3);
        father_name.setText(father3);
        email.setText(email3);
        roll_number.setText(roll3);
        user_class.setText(userclass3);
        phone_number.setText(phone3);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name2,phone2,email2,roll2,father2,userclass2;
                name2=name.getText().toString();
                phone2=phone_number.getText().toString();
                email2=email.getText().toString();
                roll2=roll_number.getText().toString();
                father2=father_name.getText().toString();
                userclass2=user_class.getText().toString();
                HashMap<String,Object> update_data=new HashMap<>();
                update_data.put("name",name2);
                update_data.put("rollNumber",roll2);
                update_data.put("phone",phone2);
                update_data.put("email",email2);
                update_data.put("fatherName",father2);
                update_data.put("classuser",userclass2);
                firebaseDatabase.child(id_user).updateChildren(update_data)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(getContext(),"value stored",Toast.LENGTH_SHORT).show();
                                    getDialog().dismiss();

                                }
                                else
                                {
                                    Toast.makeText(getContext(),"not store",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


            }
        });
        return view;
    }
}
