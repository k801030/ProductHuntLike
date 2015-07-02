package edu.ntu.vison.producthuntlike.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;

import java.util.List;

/**
 * Created by Vison on 2015/7/1.
 */
public class CardStackAdapterView extends AdapterView implements View.OnTouchListener {
    private Adapter mAdapter;
    private AdapterDataSetObserver mDataSetObserver;

    private float mDownTouchX;
    private float mDownTouchY;
    private float mOriginViewX;
    private float mOriginViewY;

    private int mPointerId;

    public CardStackAdapterView(Context context) {
        super(context);
    }

    public CardStackAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardStackAdapterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if(getChildCount() == 0) {
            return;
        }

        for(int i=0;i<getChildCount();i++) { // in a decreasing order
            View child = getChildAt(i);
            Integer width = child.getMeasuredWidth();
            Integer height = child.getMeasuredHeight();
            Integer childTop =  (getHeight()-height)/2;
            Integer childLeft = (getWidth()-width)/2;
            child.layout(childLeft, childTop, childLeft + width, childTop + height);
        }

        // set top view touch listener
        View topView = getChildAt(getChildCount()-1);
        topView.setOnTouchListener(this);
        Log.d("SetOnTouchListener", "");
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            LayoutParams params = child.getLayoutParams();
            int childSpecWidth = getChildMeasureSpec(widthMeasureSpec, 0, params.width);
            int childSpecHeight = getChildMeasureSpec(heightMeasureSpec, 0, params.height);
            child.measure(childSpecWidth, childSpecHeight);
        }
    }

    private void addChildrenToView() {

        if(mAdapter == null) {
            return;
        }

        if(getChildCount() == 0) {
            for(int i=0;i<mAdapter.getCount();i++) {
                View child = mAdapter.getView(i, null, this);

                LayoutParams params = child.getLayoutParams();
                if(params == null) {
                    params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                }
                addViewInLayout(child, 0, params, true);
            }
        }

    }

    @Override
    public void setAdapter(Adapter adapter) {
        if(mAdapter != null && mDataSetObserver != null) {
            /**
             *  Calling unregisterDataSetObserver(observer) is not actually mandatory,
             *  but it's a good practice, and avoids possible memory leaking.
             *  (you never know what happens with the old adapter, it might get reused,
             *  so if you skip the unregister part, the custom view will still react to it)
             */
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }
        mAdapter = adapter;
        if(mAdapter != null) {
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }
    }

    @Override
    public Adapter getAdapter() {
        return mAdapter;
    }

    @Override
    public View getSelectedView() {
        return null;
    }

    @Override
    public void setSelection(int i) {

    }

    private class AdapterDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            addChildrenToView();
            requestLayout();
        }
        @Override
        public void onInvalidated() {
            requestLayout();
        }
    }

    // Implement onTouchListener
    @Override
    public boolean onTouch(View view, MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                LayoutParams params = view.getLayoutParams();
                mPointerId = event.getPointerId(0);
                float x = event.getX(mPointerId);
                float y = event.getY(mPointerId);
                mDownTouchX = x;
                mDownTouchY = y;
                mOriginViewX = view.getTranslationX();
                mOriginViewY = view.getTranslationY();
                Log.i("ACTION_DOWN", x + "," + y);
                break;
            case MotionEvent.ACTION_MOVE:
                // Returns the X coordinate of this event for the given pointer
                final float moveX = event.getX(mPointerId);
                final float moveY = event.getY(mPointerId);

                // Calculate the distance moved
                final float dx = moveX - mDownTouchX;
                final float dy = moveY - mDownTouchY;
                view.setTranslationX(view.getTranslationX() + dx);
                view.setTranslationY(view.getTranslationY() + dy);

                break;
            default:
                break;
        }
        return true;
    }

}
