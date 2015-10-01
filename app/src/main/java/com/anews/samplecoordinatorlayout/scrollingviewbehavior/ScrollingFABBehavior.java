package com.anews.samplecoordinatorlayout.scrollingviewbehavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.anews.samplecoordinatorlayout.R;
import com.anews.samplecoordinatorlayout.logger.L;


public class ScrollingFABBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    private int toolbarHeight;

    public ScrollingFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.toolbarHeight = getToolbarHeight(context);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {

        L.i("layoutDependsOn: dependency=" + String.valueOf(dependency));
        L.i("layoutDependsOn: dependency instanceof RelativeLayout ?" + String.valueOf(dependency instanceof RelativeLayout));
        L.i("layoutDependsOn: dependency instanceof LinearLayout ?" + String.valueOf(dependency instanceof LinearLayout));
        L.i("layoutDependsOn: dependency instanceof NestedScrollView ?" + String.valueOf(dependency instanceof NestedScrollView));
        L.i("layoutDependsOn: dependency instanceof ListView ?" + String.valueOf(dependency instanceof ListView));
        L.i("layoutDependsOn: dependency instanceof AppBarLayout ?" + String.valueOf(dependency instanceof AppBarLayout));
        return dependency instanceof AppBarLayout;
//        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
        L.i("FAB: toolbarHeight   =" + toolbarHeight);
        L.i("FAB: fab.getHeight() =" + fab.getHeight());
        L.i("FAB: dependency.getY =" + dependency.getY());
        L.i("FAB: ---             =");
        if (dependency instanceof AppBarLayout) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            int fabBottomMargin = lp.bottomMargin;
            int distanceToScroll = fab.getHeight() + fabBottomMargin;
            float ratio = (float) dependency.getY() / (float) toolbarHeight;
            L.i("FAB: toolbarHeight   =" + toolbarHeight);
            L.i("FAB: fab.getHeight() =" + fab.getHeight());
            L.i("FAB: fabBottomMargin =" + fabBottomMargin);
            L.i("FAB: distanceToScroll=" + distanceToScroll);
            L.i("FAB: dependency.getY =" + dependency.getY());
            L.i("FAB: ratio           =" + String.valueOf(ratio));
            L.i("FAB: result          =" + String.valueOf(-distanceToScroll * ratio));
            L.i("FAB: up?             =" + String.valueOf((-distanceToScroll * ratio) > 0));
            L.i("FAB: ---     " );


            fab.setTranslationY(-distanceToScroll * ratio);
        }
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        L.i("onNestedScroll: dyConsumed         =" + dyConsumed);
        L.i("onNestedScroll: coordinatorLayout  =" + String.valueOf(coordinatorLayout));
        L.i("onNestedScroll: child              =" + String.valueOf(child));
        L.i("onNestedScroll: target             =" + String.valueOf(target));
        L.i("onNestedScroll: dxConsumed         =" + String.valueOf(dxConsumed));
        L.i("onNestedScroll: dyConsumed         =" + String.valueOf(dyConsumed));
        L.i("onNestedScroll: dxUnconsumed       =" + String.valueOf(dxUnconsumed));
        L.i("onNestedScroll: dyUnconsumed       =" + String.valueOf(dyUnconsumed));
        L.i("onNestedScroll:---  =" );
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, FloatingActionButton child, MotionEvent ev) {
        L.i("FAB: onInterceptTouchEvent   =" + String.valueOf(parent));
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, FloatingActionButton child, int layoutDirection) {
        L.i("FAB: layoutDirection   =" + layoutDirection);
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context
                .getTheme().obtainStyledAttributes(new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        L.i("onStartNestedScroll: coordinatorLayout  =" + String.valueOf(coordinatorLayout));
        L.i("onStartNestedScroll: child              =" + String.valueOf(child));
        L.i("onStartNestedScroll: target             =" + String.valueOf(target));
        L.i("onStartNestedScroll: nestedScrollAxes   =" + String.valueOf(nestedScrollAxes));
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }
}