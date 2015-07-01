package edu.ntu.vison.producthuntlike.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;

import java.util.List;

/**
 * Created by Vison on 2015/7/1.
 */
public class CardStackAdapterView extends AdapterView {
    private Adapter mAdapter;
    private AdapterDataSetObserver mDataSetObserver;


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

        if(mAdapter == null) {
            return;
        }

        if(getChildCount() == 0) {
            for(int i=0;i<mAdapter.getCount();i++) {
                View child = mAdapter.getView(i, null, this);
                addAndMeasureChild(child);
            }
        }

        setChildrenLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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

    /**
     * Adds a view as a child view and measure it
     *
     * @param child The view to add
     */
    private void addAndMeasureChild(View child) {
        // ??
        LayoutParams params = child.getLayoutParams();
        if(params == null) {
            params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }
        addViewInLayout(child, -1, params, true);
        child.measure(MeasureSpec.EXACTLY, MeasureSpec.EXACTLY);
    }

    /**
     * Set the children to proper position
     */
    private void setChildrenLayout() {

    }

    private class AdapterDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            requestLayout();
        }
        @Override
        public void onInvalidated() {
            super.onInvalidated();
            requestLayout();
        }
    }
}
