package com.example.adminapp.StudentRecord;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.adminapp.R;

public class TabLayoutNew extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tb;

    private PageAdapter pageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_new);
        toolbar=findViewById(R.id.mainPageBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select Option");
        tb=findViewById(R.id.tab);
        viewPager=findViewById(R.id.viewpager);
        pageAdapter=new PageAdapter(getSupportFragmentManager(),tb.getTabCount());
        viewPager.setAdapter(pageAdapter);
        tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){
//                    toolbar.setBackgroundColor(ContextCompat.getColor(TabLayoutNew.this,R.color.accept));
//                    tb.setBackgroundColor(ContextCompat.getColor(TabLayoutNew.this,R.color.green));
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLayoutNew.this,R.color.colorPrimaryDark));
                    }
                }
                if(tab.getPosition()==1){
//                    toolbar.setBackgroundColor(ContextCompat.getColor(TabLayoutNew.this,R.color.request));
//                    tb.setBackgroundColor(ContextCompat.getColor(TabLayoutNew.this,R.color.yellow));
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLayoutNew.this,R.color.orange));
                    }
                }
                if(tab.getPosition()==2){
//                    toolbar.setBackgroundColor(ContextCompat.getColor(TabLayoutNew.this,R.color.deny));
//                    tb.setBackgroundColor(ContextCompat.getColor(TabLayoutNew.this,R.color.red));
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        getWindow().setStatusBarColor(ContextCompat.getColor(TabLayoutNew.this,R.color.red));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tb));
    }
}
