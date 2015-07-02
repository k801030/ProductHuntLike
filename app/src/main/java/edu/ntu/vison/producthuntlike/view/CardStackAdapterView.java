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

        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            Integer width = child.getMeasuredWidth();
            Integer height = child.getMeasuredHeight();
            child.layout(0, 0, left + width, top + height);
        }

        // set top view touch listener
        View topView = getChildAt(0);
        topView.setOnTouchListener(this);
        Log.d("SetOnTouchListener", "");
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, heightMeasureSpec);
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
        Log.d("ON_TOUCH", Integer.toString(event.getActionMasked()));
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                LayoutParams params = view.getLayoutParams();
                mPointerId = event.getPointerId(0);
                float x = event.getX(mPointerId);
                float y = event.getY(mPointerId);
                mDownTouchX = x;
                mDownTouchY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // Find the index of the active pointer and fetch its position
                final int pointerIndexMove = event.findPointerIndex(mPointerId);
                final float moveX = event.getX(pointerIndexMove);
                final float moveY = event.getY(pointerIndexMove);

                // Calculate the distance moved
                final float dx = moveX - mDownTouchX;
                final float dy = moveY - mDownTouchY;
                view.setTranslationX(moveX);
                view.setTranslationY(moveY);

                Log.d("ON_TOUCH", moveX + "," + moveY);
                break;
            default:
                break;
        }
        return false;
    }
}
