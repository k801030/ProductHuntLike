package edu.ntu.vison.producthuntlike.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Vison on 2015/7/2.
 */
public class UnswipeableViewPager extends ViewPager {
    public UnswipeableViewPager(Context context) {
        super(context);
    }

    public UnswipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // disallow pagers to be swiped
        return false;
    }
}
