package edu.ntu.vison.producthuntlike.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

/**
 * Created by Vison on 2015/7/1.
 */
public class CustomAdapterView extends AdapterView {

    public CustomAdapterView(Context context) {
        super(context);
    }

    public CustomAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomAdapterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdapter(Adapter adapter) {

    }

    @Override
    public Adapter getAdapter() {
        return null;
    }

    @Override
    public View getSelectedView() {
        return null;
    }

    @Override
    public void setSelection(int i) {

    }
}
