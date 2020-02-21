package com.example.a20zhinengzhizao1.user;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/*
* 绘制快速索字母
* 1.绘制26个英文字母
* 2.在onMeasure方法中测量item的宽高
* 3.在onDraw方法中画字母的宽高以及坐标
 */
public class IndexView extends View {

    private int itemWidth,itemHeight;
    private int touchIndex=-1;//字母的下表位置
    private String[] words={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private Paint paint;
    private OnIndexChangeListener listener;

    public void setListener(OnIndexChangeListener listener) {
        this.listener = listener;
    }

    public IndexView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setTypeface(Typeface.DEFAULT_BOLD);//设置字体的样式为粗体
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemWidth=getMeasuredWidth();
        itemHeight=getMeasuredHeight()/words.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i=0;i<words.length;i++){
            if (touchIndex==i){
                paint.setColor(Color.GRAY);
            }else {
                paint.setColor(Color.WHITE);
            }
            String word=words[i];
            Rect rect=new Rect();
            paint.getTextBounds(word,0,1,rect);
            int wordWidth=rect.width();
            int wordHeight=rect.height();

            float wordX=itemWidth/2-wordWidth/2;
            float wordY=itemHeight/2+wordHeight/2+i*itemHeight;

            canvas.drawText(word,wordX,wordY,paint);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float Y=event.getY();
                int index= (int) (Y/itemHeight);//获取字母的索引
                if (index!=touchIndex){
                    touchIndex=index;
                    invalidate();
                    if (listener!=null&&touchIndex<words.length){
                        if (touchIndex<0){
                            listener.onIndexChange(words[0]);
                        }else {
                            listener.onIndexChange(words[touchIndex]);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                touchIndex=-1;
                invalidate();
                break;
        }
        return true;
    }
    public interface OnIndexChangeListener{
        void onIndexChange(String word);
    }
}
