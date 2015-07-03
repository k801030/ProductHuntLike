package edu.ntu.vison.producthuntlike;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

/**
 * Created by Vison on 2015/7/3.
 */
public class CardOnTouchListener implements View.OnTouchListener {
    private View mView;
    private int mPointerId;
    private float mDownTouchX;
    private float mDownTouchY;
    private float mOriginViewX;
    private float mOriginViewY;
    private float parentWidth;
    private float parentHeight;

    // default setting
    private float rotationDegree = 10;
    private float dragSensitivity = (float)0.8;

    public CardOnTouchListener(View view) {
        mView = view;
        mOriginViewX = mView.getTranslationX();
        mOriginViewY = mView.getTranslationY();
        parentWidth = ((ViewGroup)mView.getParent()).getWidth();
        parentHeight = ((ViewGroup)mView.getParent()).getHeight();

    }

    // Implement onTouchListener
    @Override
    public boolean onTouch(View view, MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                ViewGroup.LayoutParams params = view.getLayoutParams();
                mPointerId = event.getPointerId(0);
                float x = event.getX(mPointerId);
                float y = event.getY(mPointerId);
                mDownTouchX = x;
                mDownTouchY = y;

                view.setPivotX(x);
                view.setPivotY(y);

                Log.i("ACTION_DOWN", x + "," + y);
                break;
            case MotionEvent.ACTION_MOVE:
                // Returns the X coordinate of this event for the given pointer
                float moveX = event.getX(mPointerId);
                float moveY = event.getY(mPointerId);

                // Calculate the distance moved
                float dx = moveX - mDownTouchX;
                float dy = moveY - mDownTouchY;
                view.setTranslationX(view.getTranslationX() + dx);
                view.setTranslationY(view.getTranslationY() + dy);

                // Calculate the rotation degree
                float move = view.getTranslationX() + dx - mOriginViewX;
                float maxMove = parentWidth/2;
                float rotation = (move/maxMove) * rotationDegree;
                view.setRotation(rotation); // 0 to 360



                break;
            default:
                break;
        }
        return true;
    }
}
