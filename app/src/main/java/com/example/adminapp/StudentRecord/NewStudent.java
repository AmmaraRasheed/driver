package com.example.adminapp.StudentRecord;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adminapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class NewStudent extends DialogFragment {
    private EditText name,fatherName,pass,userClss,rollNumber,email,phoneNumber,CNIC,status1;
    private DatabaseReference rootref;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private Button register,cancel;
    private String email1,pass1,name1,fatherName1,CNIC1,phoneNUmber1,rollNumber1,class1,status;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.student,null);
        name=(EditText)view.findViewById(R.id.name);
        fatherName=(EditText)view.findViewById(R.id.father_name);
        CNIC=(EditText)view.findViewById(R.id.CNIC);
        status1=(EditText)view.findViewById(R.id.status1);
        pass=(EditText)view.findViewById(R.id.password);
        register=view.findViewById(R.id.register);
        cancel=view.findViewById(R.id.cancel);
        phoneNumber=(EditText)view.findViewById(R.id.phone_number);
        rollNumber=(EditText)view.findViewById(R.id.roll_no);
        userClss=(EditText)view.findViewById(R.id.user_class);
        email=(EditText)view.findViewById(R.id.email);
        auth=FirebaseAuth.getInstance();
        rootref= FirebaseDatabase.getInstance().getReference().child("Student");
//        Toast.makeText(getActivity(),"email good "+email1,Toast.LENGTH_SHORT).show();
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                email1=email.getText().toString();
                pass1=pass.getText().toString();
                name1=name.getText().toString();
                fatherName1=fatherName.getText().toString();
                rollNumber1=rollNumber.getText().toString();
                class1=userClss.getText().toString();
                email1=email.getText().toString();
                status=status1.getText().toString();
                phoneNUmber1=phoneNumber.getText().toString();
                CNIC1=CNIC.getText().toString();
                if(status.isEmpty() || email1.isEmpty()|| pass1.isEmpty()|| name1.isEmpty()|| fatherName1.isEmpty()|| rollNumber1.isEmpty()|| class1.isEmpty()|| phoneNUmber1.isEmpty()|| CNIC1.isEmpty()){
                    Toast.makeText(getContext(),"Enter fill all fields",Toast.LENGTH_SHORT).show();
                }
                else if(!pass1.isEmpty()){
                    validate_pass(pass1);
                }
                auth.createUserWithEmailAndPassword(email1,pass1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
//                                        Toast.makeText(getContext(),"stor1221e",Toast.LENGTH_SHORT).show();
                                    firebaseUser=auth.getCurrentUser();
                                    String id=firebaseUser.getUid();
                                    String status_result="accepted";
                                    User user=new User(name1,fatherName1,rollNumber1,class1,email1,phoneNUmber1,id,CNIC1,status,status_result);

                                    rootref.child(firebaseUser.getUid()).setValue(user)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful())
                                                    {
                                                        Toast.makeText(getContext(),"store",Toast.LENGTH_SHORT).show();
                                                        dismiss();
                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(getContext(),"not store",Toast.LENGTH_SHORT).show();
                                                    }

                                                }
                                            });
                                }
                                else
                                {
                                    Toast.makeText(getContext(),"Not done",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });
        return view;
    }

    private void validate_pass(String pass1) {
        Pattern uppercase=Pattern.compile("[A-Z]");
        Pattern lowercase=Pattern.compile("[a-z]");
        Pattern digit=Pattern.compile("[0-9]");
        if(lowercase.matcher(pass1).find()){
            if(uppercase.matcher(pass1).find()) {
                if (digit.matcher(pass1).find()) {
                    if (pass1.length() < 10) {
                        Toast.makeText(getContext(), "Password should > 10", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Password contain 0-9", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getContext(),"Password should A-Z",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(),"Password should a-z",Toast.LENGTH_SHORT).show();
        }
    }
}

