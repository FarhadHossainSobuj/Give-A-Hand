package com.farhad.giveahand;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.farhad.giveahand.fragments.DoHelpFragment;
import com.farhad.giveahand.fragments.NeedHelpFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {
    public TabsAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "সাহায্য চাই";
            case 1:
                return "সাহায্য করতে চাই";
            default:
                return null;
        }
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                DoHelpFragment doHelpFragment = new DoHelpFragment();
                return doHelpFragment;
            case 1:
                NeedHelpFragment needHelpFragment = new NeedHelpFragment();
                return needHelpFragment;
            default:
                return null;
        }
    }
}
