package edu.ntu.vison.producthuntlike.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import edu.ntu.vison.producthuntlike.R;
import edu.ntu.vison.producthuntlike.adapter.PagerAdapter;

public class MainActivity extends FragmentActivity {
    FragmentPagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vp = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        vp.setAdapter(pagerAdapter);
    }

}
