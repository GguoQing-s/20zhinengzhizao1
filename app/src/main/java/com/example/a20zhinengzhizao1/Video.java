package com.example.a20zhinengzhizao1;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class Video extends VideoView {
    public Video(Context context) {
        super(context);
    }

    public Video(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Video(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width , height);
    }
}
