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

import edu.ntu.vison.producthuntlike.CardOnTouchListener;
import edu.ntu.vison.producthuntlike.adapter.CardStackAdapter;

/**
 * Created by Vison on 2015/7/1.
 */
public class CardStackAdapterView extends AdapterView {
    private CardStackAdapter mAdapter;
    private AdapterDataSetObserver mDataSetObserver;
    private CardOnTouchListener mOnTouchListener;

    private View mTopView;
    private int mTopViewAt;

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
            child.getBackground().setAlpha(255);
        }

        // set top view touch listener
        mTopViewAt = getChildCount()-1;
        mTopView = getChildAt(mTopViewAt);
        mOnTouchListener = new CardOnTouchListener(mTopView) {
            @Override
            public void onMovedBeyondLeftBorder() {

                mAdapter.removeCardAtTop();
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onMovedBeyondRightBorder() {
                mAdapter.removeCardAtTop();
                mAdapter.notifyDataSetChanged();
            }
        };
        mTopView.setOnTouchListener(mOnTouchListener);
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
        removeAllViewsInLayout();

        for(int i=0;i<mAdapter.getCount();i++) {
            View child = mAdapter.getView(i, null, this);

            LayoutParams params = child.getLayoutParams();
            if(params == null) {
                params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            }
            addViewInLayout(child, 0, params, true);
        }


    }

    public void setAdapter(CardStackAdapter adapter) {
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
    public void setAdapter(Adapter adapter) {

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



}
