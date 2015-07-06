package edu.ntu.vison.producthuntlike;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import java.util.AbstractMap;

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
        // override this content
    };

    public void onMovedBeyondRightBorder() {
        // override this content
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

                Log.i("ACTION_DOWN", mOriginViewX + "," + mOriginViewY);
                break;
            case MotionEvent.ACTION_MOVE:
                // Returns the X coordinate of this event for the given pointer
                float moveX = event.getX(mPointerId);
                float moveY = event.getY(mPointerId);
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
                Log.i("ACTION_UP", mOriginViewX + "," + mOriginViewY);
                final int DURATION = 180;
                if (moveStatus() == BEYOND_LEFT_MOVE) {
                    animationFlyOver(mView, -1);
                    onMovedBeyondLeftBorder();

                } else if (moveStatus() == BEYOND_RIGHT_MOVE) {
                    animationFlyOver(mView, 1);
                    onMovedBeyondRightBorder();

                } else if (moveStatus() == LEFT_MOVE) {
                    animationFlyBack(mView);

                } else if (moveStatus() == RIGHT_MOVE) {
                    animationFlyBack(mView);
                }
                break;
            default:
                break;
        }
        return true;
    }

    private int moveStatus() {
        float borderDistance = parentWidth/2 * dragSensitivity;
        float xMove = mView.getTranslationX() - mOriginViewX;

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

    /**
     *
     * @param view the target view to apply animation on
     * @param direction set -1 if direction is left; set 1 if right
     */
    private void animationFlyOver(View view, int direction) {
        final int DURATION = 150;
        float toXDelta = 0, toYDelta = 0;
        float toDegree = 0;
        if (direction < 0) { // left
            toXDelta = -parentWidth - view.getTranslationX();
        } else if (direction > 0){ // right
            toXDelta = parentWidth - view.getTranslationX();
        }
        // formula: y'-y = y*(x'/x) -y
        toYDelta = view.getTranslationY() * (toXDelta/view.getTranslationX()) - view.getTranslationY();
        Animation translateAm = new TranslateAnimation(0, toXDelta, 0, toYDelta);

        // formula: r'-r = r*(x'/x) -r
        toDegree = view.getRotation() * (toXDelta / view.getTranslationX()) - view.getRotation();
        Animation rotationAm = new RotateAnimation(0, toDegree, view.getPivotX(), view.getPivotY());

        AnimationSet set = new AnimationSet(false);
        set.addAnimation(translateAm);
        set.addAnimation(rotationAm);
        set.setDuration(DURATION);
        set.setFillAfter(true);

        view.startAnimation(set);
    }

    private void animationFlyBack(View view) {
        final int DURATION = 180;
        Animation translateAm = new TranslateAnimation(0, mOriginViewX - mView.getTranslationX(), 0, mOriginViewY - mView.getTranslationY());
        Animation rotationAm = new RotateAnimation(0, -mView.getRotation(), mView.getPivotX(), mView.getPivotY());

        AnimationSet amSet = new AnimationSet(false);
        amSet.addAnimation(translateAm);
        amSet.addAnimation(rotationAm);

        amSet.setDuration(DURATION);
        amSet.setFillAfter(true); // true: set the animation to be persisted when finished

        mView.startAnimation(amSet);
    }
}
