package edu.ntu.vison.producthuntlike.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import edu.ntu.vison.producthuntlike.framgent.CardStackFragment;
import edu.ntu.vison.producthuntlike.framgent.SampleFragment;
import edu.ntu.vison.producthuntlike.framgent.ProductListFragment;

/**
 * Created by Vison on 2015/6/29.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Return the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CardStackFragment();
            case 1:
                return new ProductListFragment();
            case 2:
                return new SampleFragment();
            default:
                return new SampleFragment();
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page # " + position;
    }
}
