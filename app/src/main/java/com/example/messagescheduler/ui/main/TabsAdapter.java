package com.example.messagescheduler.ui.main;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    public TabsAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ScheduledMessagesFragment();
            case 1:
                return new MessageSchedulerFragment();
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}