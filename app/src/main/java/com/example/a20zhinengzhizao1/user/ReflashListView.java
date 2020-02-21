package com.example.a20zhinengzhizao1.user;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.a20zhinengzhizao1.R;


public class ReflashListView extends ListView implements AbsListView.OnScrollListener {
    private View header;
    private int headerHeight;
    private int scrollState;
    private int firstVisibleItem;
    private boolean isRemark;
    private int startY;
    private int state;
    final  int NONE=0;
    final int PULL=1;
    final  int RELESE=2;
    final  int REFLASHIONG=3;
    final  int FINISHING=4;
    private IReflashListerner iReflashListerner;


    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage( Message msg) {
               if (msg.what==0){
                   iReflashListerner.onReflash();
               }else {
                   reflashComplete();
               }

            return false;
        }
    });


    public void setiReflashListerner(IReflashListerner iReflashListerner) {
        this.iReflashListerner = iReflashListerner;
    }

    public ReflashListView(Context context) {
        super(context);
        initView(context);
    }

    public ReflashListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ReflashListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    private void initView(Context context) {
    header= LayoutInflater.from(context).inflate(R.layout.header,null);
    header.measure(0,0);

    headerHeight=header.getMeasuredHeight();
    topPadding(-headerHeight);
        addHeaderView(header);
        setOnScrollListener(this);

    }

    private void topPadding(int topPadding) {
        header.setPadding(header.getPaddingLeft(),topPadding,header.getPaddingRight(),header.getPaddingBottom());
        header.invalidate();

    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        this.scrollState=scrollState;
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int i1, int i2) {
        this.firstVisibleItem=firstVisibleItem;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (firstVisibleItem==0){
                    isRemark=true;
                    startY= (int) ev.getY();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                onMove(ev);
                break;
            case MotionEvent.ACTION_UP:
                if (state==RELESE){
                    state=REFLASHIONG;
                    reflashViewByState();
                    handler.sendEmptyMessageDelayed(0,2000);
                    handler.sendEmptyMessageDelayed(1,3000);

                }else if (state==PULL){
                    state=NONE;
                    isRemark=false;
                    reflashViewByState();
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    private void reflashViewByState() {
        TextView tip=header.findViewById(R.id.tv_state);
        ImageView arrow=header.findViewById(R.id.iv_arrow);
        ProgressBar pb=header.findViewById(R.id.pb);
        RotateAnimation animation=new RotateAnimation(0,180, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        RotateAnimation animation1=new RotateAnimation(180,0, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        animation1.setDuration(500);
        animation1.setFillAfter(true);
        switch (state){
            case NONE:
                arrow.clearAnimation();
                topPadding(-headerHeight);
                break;
            case PULL:
                arrow.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
                tip.setText("下拉可以刷新");
                arrow.clearAnimation();
                arrow.startAnimation(animation1);
                break;
            case RELESE:
                arrow.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
                tip.setText("松开可以刷新");
                arrow.clearAnimation();
                arrow.startAnimation(animation);
                break;
            case REFLASHIONG:
                topPadding(headerHeight);
                arrow.setVisibility(View.GONE);
                pb.setVisibility(View.VISIBLE);
                tip.setText("正在刷新...");
                arrow.clearAnimation();
                break;
            case FINISHING:
                state =FINISHING;
                topPadding(headerHeight);
                arrow.setVisibility(View.GONE);
                pb.setVisibility(View.GONE);
                tip.setText("刷新成功");
                arrow.clearAnimation();

        }
    }

    private void onMove(MotionEvent ev) {
        if (!isRemark){
            return;
        }
        int tempY= (int) ev.getY();
        int space=tempY-startY;
        int topPddding=space-headerHeight;
        switch (state){
            case NONE:
                if (space>0){
                    state=PULL;
                    reflashViewByState();
                }
                break;
            case PULL:
                topPadding(topPddding);
                if (space>headerHeight+150&&(scrollState==SCROLL_STATE_IDLE||scrollState==SCROLL_STATE_TOUCH_SCROLL)){
                    state=RELESE;
                    reflashViewByState();
                }
                break;
            case RELESE:
                topPadding(topPddding);
                if (space<headerHeight+30){
                    state=PULL;
                    reflashViewByState();
                }else if (space<=0){
                    state=NONE;
                    isRemark=false;
                    reflashViewByState();
                }
                    break;
        }
    }
    public  interface IReflashListerner{
        void onReflash();
    }
    public void reflashComplete(){
        state=NONE;
        isRemark=false;
        reflashViewByState();
    }
    public void Isfinish(){
       state=FINISHING;
        reflashViewByState();
    }

}
