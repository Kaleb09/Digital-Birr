package com.kaleb.firebase;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class customadapter extends ViewPager {
    private boolean enabled;
    public customadapter(Context context, AttributeSet attr){
        super(context,attr);
this.enabled=true;
    }
    public customadapter(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(this.enabled){
        return super.onTouchEvent(ev);
    }return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
       if(this.enabled){
        return super.onInterceptTouchEvent(ev);
    }
       return false;
    }
    public void setPagenable(boolean enabled){
        this.enabled=enabled;
    }
}
