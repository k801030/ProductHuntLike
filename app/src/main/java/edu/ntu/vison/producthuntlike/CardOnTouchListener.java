package edu.ntu.vison.producthuntlike;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

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

    // constance
    private final static int RIGHT_MOVE = 1;
    private final static int BEYOND_RIGHT_MOVE = 2;
    private final static int LEFT_MOVE = -1;
    private final static int BEYOND_LEFT_MOVE = -2;

    public CardOnTouchListener(View view) {
        mView = view;
        mOriginViewX = mView.getTranslationX();
        mOriginViewY = mView.getTranslationY();
        parentWidth = ((ViewGroup)mView.getParent()).getWidth();
        parentHeight = ((ViewGroup)mView.getParent()).getHeight();

    }


    public void onMovedBeyondLeftBorder() {

    };

    public void onMovedBeyondRightBorder() {

    };

    // Implement onTouchListener
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                ViewGroup.LayoutParams params = mView.getLayoutParams();
                mPointerId = event.getPointerId(0);
                float x = event.getX(mPointerId);
                float y = event.getY(mPointerId);
                mDownTouchX = x;
                mDownTouchY = y;

                mView.setPivotX(x);
                mView.setPivotY(y);

                Log.i("ACTION_DOWN", x + "," + y);
                break;
            case MotionEvent.ACTION_MOVE:
                // Returns the X coordinate of this event for the given pointer
                float moveX = event.getX(mPointerId);
                float moveY = event.getY(mPointerId);
                Log.i("ALPHA_BUG",mView.toString());
                // Calculate the distance moved
                float dx = moveX - mDownTouchX;
                float dy = moveY - mDownTouchY;
                mView.setTranslationX(view.getTranslationX() + dx);
                mView.setTranslationY(view.getTranslationY() + dy);

                // Calculate the rotation degree
                float move = mView.getTranslationX() + dx - mOriginViewX;
                float maxMove = parentWidth/2;
                float rotation = (move/maxMove) * rotationDegree;
                mView.setRotation(rotation); // 0 to 360

                // Calculate drag event
                if(moveStatus() == BEYOND_RIGHT_MOVE) {
                    mView.getBackground().setAlpha(128);
                }else if(moveStatus() == BEYOND_LEFT_MOVE) {
                    mView.getBackground().setAlpha(128);
                }else {
                    mView.getBackground().setAlpha(255);
                }

                break;
            case MotionEvent.ACTION_UP:
                if(moveStatus() == BEYOND_LEFT_MOVE) {
                    onMovedBeyondLeftBorder();
                }else if(moveStatus() == BEYOND_RIGHT_MOVE) {
                    onMovedBeyondRightBorder();
                }else {

                }
                break;
            default:
                break;
        }
        return true;
    }

    private int moveStatus() {
        float borderDistance = parentWidth/2 * dragSensitivity;
        float xMove = (mOriginViewX - mView.getTranslationX());

        if (xMove >= borderDistance) {
            return BEYOND_RIGHT_MOVE;
        } else if (xMove <= borderDistance && xMove >0) {
            return RIGHT_MOVE;
        } else if (-xMove >= borderDistance) {
            return BEYOND_LEFT_MOVE;
        } else if (-xMove <= borderDistance && xMove <0) {
            return LEFT_MOVE;
        }
        return 0;
    }


}
