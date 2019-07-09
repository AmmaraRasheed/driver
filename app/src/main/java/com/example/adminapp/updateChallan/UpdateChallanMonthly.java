package com.example.adminapp.updateChallan;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class UpdateChallanMonthly extends AppCompatActivity {
    private EditText buss_fee,bus_card_fee,transport_fund,total_of_fund,grand_total;
    private TextView date,duedate;
    private DatabaseReference databaseReference;
    private DatePickerDialog.OnDateSetListener mdatelistener,mDuedatelistener;
    private TextView submit, heading;
    private Toolbar toolbar;
    /* get data from form */
    String admin_date,admin_fee,admin_card_fee,admin_transport,admin_total,admin_grand_total,duedate2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_challan_monthly);
        toolbar=findViewById(R.id.mainPageBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Make Changes");
        buss_fee=(EditText)findViewById(R.id.busFee);
        duedate=(TextView)findViewById(R.id.duedate1);
        bus_card_fee=(EditText)findViewById(R.id.busCardFee);
        transport_fund=(EditText)findViewById(R.id.transportFund);
        total_of_fund=(EditText)findViewById(R.id.totalFund);
        grand_total=(EditText)findViewById(R.id.grandTotal);
        date=(TextView) findViewById(R.id.dated);
        submit=(TextView)findViewById(R.id.submit);
        heading=(TextView)findViewById(R.id.heading);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Admin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String card1=dataSnapshot.child("bus_card_fee").getValue().toString();
                String buss1=dataSnapshot.child("buss_fee").getValue().toString();
                String date1=dataSnapshot.child("date").getValue().toString();
                String duedate3=dataSnapshot.child("duedate").getValue().toString();
                String grand1=dataSnapshot.child("grand_total").getValue().toString();
                String fund1=dataSnapshot.child("total_of_fund").getValue().toString();
                String transport1=dataSnapshot.child("transport_fund").getValue().toString();
                buss_fee.setText(buss1);
                bus_card_fee.setText(card1);
                duedate.setText(duedate3);
                transport_fund.setText(transport1);
                total_of_fund.setText(fund1);
                grand_total.setText(grand1);
                date.setText(date1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//change font of button
        Typeface change_font=Typeface.createFromAsset(getAssets(),"Spiders.ttf");
        submit.setTypeface(change_font);
        Typeface change_font1=Typeface.createFromAsset(getAssets(),"SaucerBB.ttf");
        heading.setTypeface(change_font1);

        /* end of font of button*/
        /*due date dialog*/
        duedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(UpdateChallanMonthly.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDuedatelistener,
                        year,month,day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDuedatelistener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month=month+1;

                String new_date=month+"/"+dayOfMonth+"/"+year;
                duedate.setText(new_date);
            }
        };

        //Date picker

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(UpdateChallanMonthly.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mdatelistener,
                        year,month,day
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mdatelistener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month=month+1;

                String new_date=month+"/"+dayOfMonth+"/"+year;
                date.setText(new_date);
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        /* fetch data from database*/

    }
    /*  check validation  */

    public void Validat_data(View view){
        admin_date=date.getText().toString();
        admin_fee=buss_fee.getText().toString();
        admin_card_fee=bus_card_fee.getText().toString();
        admin_transport=transport_fund.getText().toString();
        admin_total=total_of_fund.getText().toString();
        admin_grand_total=grand_total.getText().toString();
        duedate2=duedate.getText().toString();
        if(duedate2.isEmpty() || admin_date.isEmpty()|| admin_fee.isEmpty()|| admin_card_fee.isEmpty()|| admin_transport.isEmpty()
                || admin_total.isEmpty()|| admin_grand_total.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please Fill All Field",Toast.LENGTH_SHORT).show();
        }
        else{
            ChallanForm();
        }
    }
    /*  validation ends  */
    public void ChallanForm(){
        ChallanUser challanUser= new ChallanUser(admin_fee,admin_card_fee,admin_transport,admin_total,admin_grand_total,admin_date,duedate2);
        databaseReference.child("Admin").setValue(challanUser)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
