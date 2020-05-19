package com.example.myapplication;

import android.content.Context;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new OneFragment();
        }
        else if (position == 1)
        {
            fragment = new TwoFragment();
        }
        else if (position == 2)
        {
            fragment = new ThreeFragment();
        }
        else if (position == 3)
        {
            fragment = new FourtFragment();
        }
        else if (position == 4)
        {
            fragment = new FiveFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;

        if (position == 0)
        {
            title = "NEW";
        }
        else if (position == 1)
        {
            title = "POPULAR";
        }
        else if (position == 2)
        {
            title = "CATEGORIES";
        }
        else if (position == 3)
        {
            title = "COLORS";
        }
        else if (position == 4)
        {
            title = "FAVORITE";
        }
        return title;
    }
    }
