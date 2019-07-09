package com.example.adminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.adminapp.BusStop.UpdateBusStop;
import com.example.adminapp.Events.UpdateEvents;
import com.example.adminapp.NewBus.CreateNewBus;
import com.example.adminapp.Notifiation.Notification1;
import com.example.adminapp.SelectBuss.SelectBus;
import com.example.adminapp.StudentRecord.TabLayoutNew;
import com.example.adminapp.updateChallan.UpdateChallanMonthly;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.mainPageBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select Option");

    }
    public void Stop(View view)
    {
        Intent intent=new Intent(this, UpdateBusStop.class);
        startActivity(intent);
    }
    public void notifications(View view)
    {
        Intent intent=new Intent(this, Notification1.class);
        startActivity(intent);
    }
    public void MoveChallan(View view)
    {
        Intent intent=new Intent(this, UpdateChallanMonthly.class);
        startActivity(intent);
    }
    public void userRecord(View view){
        Intent intent=new Intent(this, TabLayoutNew.class);
        startActivity(intent);
    }
    public void View_Map(View view){
        Intent intent=new Intent(this, SelectBus.class);
        startActivity(intent);
    }
    public void View_Events(View view){
        Intent intent=new Intent(this, UpdateEvents.class);
        startActivity(intent);
    }
    public void createBus(View view)
    {
        Intent intent=new Intent(this, CreateNewBus.class);
        startActivity(intent);
    }
}
