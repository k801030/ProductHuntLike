package edu.ntu.vison.producthuntlike;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {
    FragmentPagerAdapter adapterViewPager;

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // Return the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return ProductsFragment.newInstance(1, "First Page");
                case 1:
                    return SampleFragment.newInstance(2, "Second Page");
                case 2:
                    return SampleFragment.newInstance(3, "Third Page");
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vp = (ViewPager) findViewById(R.id.pager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adapterViewPager);
    }

}
