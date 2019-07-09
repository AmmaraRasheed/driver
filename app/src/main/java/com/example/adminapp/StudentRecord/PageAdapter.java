package com.example.adminapp.StudentRecord;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {
    private int numOfTab;
   PageAdapter(FragmentManager fm,int numOfTab){
       super(fm);
       this.numOfTab=numOfTab;
   }


    @Override
    public Fragment getItem(int i) {
       switch (i){
           case 0:
               return new AcceptedFragment();
           case 1:
               return new StudentRequestFragment();
           case 2:
               return new DenyStudentFragment();
           default:
               return null;
       }
    }

    @Override
    public int getCount() {
        return numOfTab;
    }
}
