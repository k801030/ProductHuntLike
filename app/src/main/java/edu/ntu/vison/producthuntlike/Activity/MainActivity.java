package edu.ntu.vison.producthuntlike.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;

import edu.ntu.vison.producthuntlike.R;
import edu.ntu.vison.producthuntlike.adapter.PagerAdapter;
import edu.ntu.vison.producthuntlike.view.UnswipeableViewPager;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UnswipeableViewPager pager = (UnswipeableViewPager) findViewById(R.id.pager);


        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setUnderlineColor((getResources().getColor(R.color.main_color)));
        tabs.setViewPager(pager);

    }

}
