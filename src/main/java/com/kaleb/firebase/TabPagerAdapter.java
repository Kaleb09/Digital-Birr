package com.kaleb.firebase;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
public class TabPagerAdapter extends FragmentPagerAdapter {
    int tabCount=2;
    public TabPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCount = numberOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new sent();
            case 1:
                return new recieved();
            default:
                return null;
        }
    }

   

    @Override
    public int getCount() {
        return tabCount;
    }
}