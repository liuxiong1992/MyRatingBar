##android自定义评分条（RatingBar）



本自定义控件是因为本人在开发过程中，评分条的适配出现问题而诞生，在网上找了些自定义控件，发现都不是自己想要的，所以只能自己动手。
	原生的RatingBar适配难是因为在要自定义星星图标无法设置星星的大小和星星之间的间距，适配了这个厂商的手机可能那个手机有又问题，当时我就是在vivo手机适配好，到了同样分辨率的乐视手机出现了问题，我这里的思路是：
	直接用一个imageView来表示一颗星星，用一个线性布局包裹所有的imageview，为了以后实现非整数颗星的显示，所以不能用图片来显示。这里的方式是用一个相对布局包裹两个Imageview表示一颗星星，前面ImageView设置图片为空的星星，后面的ImageView用来填充星星的颜色，用填充的部分代表评分，如3.5分就是用三颗全部填充的星星可一颗填充一半的星星表示，用设置后面的ImageView的布局参数来实现填充
	因为公司需求不需要非整数颗星的评分，所以这里只做了整颗星的评分，可以根据需要修改里面的代码。

控件特点：
		1、随意设置星星之间的间隔
		2、只需一张空的星星图片，减少图片带来的内存占用
		3、可以显示0~5任意数值的星级
		4、不用担心不同分辨率、不同厂商带来的屏幕适配问题
限制：图片的星星以外的颜色必须要和评分条的背景颜色一致，不然的话那效果……
效果：
	![3分](http://img.blog.csdn.net/20170113172108534?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGl1X3hpb25n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
	![4.3分](http://img.blog.csdn.net/20170113172148645?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGl1X3hpb25n/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
	
   自定义RatingBar的布局文件


	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	              android:orientation="horizontal"
	              android:layout_width="wrap_content"
	              android:id="@+id/llt_root"
	              android:layout_height="match_parent">
	    <RelativeLayout
	        android:id="@+id/star_container01"
	        android:layout_width="0px"
	        android:layout_height="0px">
	        <ImageView
	            android:id="@+id/star01"
	            android:background="@color/star_color"
	            android:layout_width="match_parent"
	            android:layout_height="match_parent"/>
	        <ImageView
	            android:id="@+id/star_border01"
	            android:layout_width="match_parent"
	            android:background="@drawable/ic_star_border"
	            android:layout_height="match_parent"/>
	    </RelativeLayout>
	    <RelativeLayout
	        android:id="@+id/star_container02"
	        android:layout_width="0px"
	        android:layout_marginLeft="5dp"
	        android:layout_height="0px">
	        <ImageView
	            android:id="@+id/star02"
	            android:background="@color/star_color"
	            android:layout_width="match_parent"
	            android:layout_height="match_parent"/>
	        <ImageView
	            android:id="@+id/star_border02"
	            android:layout_width="match_parent"
	            android:background="@drawable/ic_star_border"
	            android:layout_height="match_parent"/>
	    </RelativeLayout>
	    <RelativeLayout
	        android:id="@+id/star_container03"
	        android:layout_width="0px"
	        android:layout_marginLeft="5dp"
	        android:layout_height="0px">
	        <ImageView
	            android:id="@+id/star03"
	            android:background="@color/star_color"
	            android:layout_width="match_parent"
	            android:layout_height="match_parent"/>
	        <ImageView
	            android:id="@+id/star_border03"
	            android:layout_width="match_parent"
	            android:background="@drawable/ic_star_border"
	            android:layout_height="match_parent"/>
	    </RelativeLayout>
	    <RelativeLayout
	        android:id="@+id/star_container04"
	        android:layout_width="0px"
	        android:layout_marginLeft="5dp"
	        android:layout_height="0px">
	        <ImageView
	            android:id="@+id/star04"
	            android:background="@color/star_color"
	            android:layout_width="match_parent"
	            android:layout_height="match_parent"/>
	        <ImageView
	            android:id="@+id/star_border04"
	            android:layout_width="match_parent"
	            android:background="@drawable/ic_star_border"
	            android:layout_height="match_parent"/>
	    </RelativeLayout>
	    <RelativeLayout
	        android:id="@+id/star_container05"
	        android:layout_width="0px"
	        android:layout_marginLeft="5dp"
	        android:layout_height="0px">
	        <ImageView
	            android:id="@+id/star05"
	            android:background="@color/star_color"
	            android:layout_width="match_parent"
	            android:layout_height="match_parent"/>
	        <ImageView
	            android:id="@+id/star_border05"
	            android:layout_width="match_parent"
	            android:background="@drawable/ic_star_border"
	            android:layout_height="match_parent"/>
	    </RelativeLayout>
	</LinearLayout>

自定义属性

```
<!-- 自定义RatingBar属性 -->
    <declare-styleable name="MyRatingBar">
        <attr name="progressColor" format="color"></attr>
        <attr name="starWidth" format="dimension"></attr>
        <attr name="starHeight" format="dimension"></attr>
        <attr name="rating" format="float"></attr>
        <attr name="starDrawable" format="reference"></attr>
        <attr name="space" format="dimension"></attr>
        <attr name="isIndicator" format="boolean"></attr>
    </declare-styleable>
```

实现代码

```
package com.example.myratingbar.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.myratingbar.LogUtils;
import com.example.myratingbar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 显示用的
 * 自定义RatingBar
 * */
public class MyRatingBar extends LinearLayout {

    /** 单独一个星星的父布局的id*/
    private int[] containerIds={R.id.star_container01,
                                R.id.star_container02,
                                R.id.star_container03,
                                R.id.star_container04,
                                R.id.star_container05, };
    /** 星星填充颜色所在的控件的id*/
    private int[] starIds={R.id.star01,
                           R.id.star02,
                           R.id.star03,
                           R.id.star04,
                           R.id.star05, };
    /** 星星边框所在的控件的id*/
    private int[] starBorderIds={R.id.star_border01,
                           R.id.star_border02,
                           R.id.star_border03,
                           R.id.star_border04,
                           R.id.star_border05, };
    private LinearLayout mLltRoot;  //本控件的根布局
    /** 单独一个星星的父布局*/
    private List<RelativeLayout> contains=new ArrayList<RelativeLayout>();
    /** 星星填充颜色所在的控件*/
    private List<ImageView> stars=new ArrayList<ImageView>();
    /** 星星边框所在的控件*/
    private List<ImageView> starBorders=new ArrayList<ImageView>();
    private int mWidth;  //单个星星的宽度
    private int mHeight;  //单个星星的高度
    private int mSpace;  //星星之间的间距
    private int mColor;  //被选中的星星的颜色
    private float mRating;   //星星的星级
    private Drawable mDrawable;  //星星的图片
    private boolean mIsIndicator;  //是否为指示器（只显示，不产生交互）

    private int[] starMesureCenterX =new int[5];  //每个星星的中心距离父控件左边的距离
    private int halfStarMesureWidth;  //一个星星的宽度的一半

    public MyRatingBar(Context context) {
        super(context);
        init();
    }
    public MyRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取属性值
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyRatingBar);
        mWidth = (int) ta.getDimension(R.styleable.MyRatingBar_starWidth, 0);
        mHeight = (int) ta.getDimension(R.styleable.MyRatingBar_starHeight, 0);
        mDrawable = ta.getDrawable(R.styleable.MyRatingBar_starDrawable);
        mColor = ta.getColor(R.styleable.MyRatingBar_progressColor, context.getResources()
                .getColor(R.color.star_color));
        mRating = ta.getFloat(R.styleable.MyRatingBar_rating, 0);
        mSpace = (int) ta.getDimension(R.styleable.MyRatingBar_space, 0);
        mIsIndicator = ta.getBoolean(R.styleable.MyRatingBar_isIndicator, false);
        init();
    }

    public MyRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
    * 方法说明: 初始化RatingBar
    * @author liuxiong
    *  create at 2016/12/4 18:18
    */
    private void init() {
        //填充布局
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_my_ratingbar,
                this, false);
        this.addView(view);
        //查找子控件
        mLltRoot = (LinearLayout) findViewById(R.id.llt_root);
        for(int i=0;i<5;i++){
            contains.add((RelativeLayout) findViewById(containerIds[i]));
            stars.add((ImageView) findViewById(starIds[i]));
            starBorders.add((ImageView) findViewById(starBorderIds[i]));
        }
        //设置星星的布局参数
        initLayoutParams();
        //设置星星的图片
        setStarDrawable(mDrawable);
        //设置星星的个数
        setRating(mRating);
        //给星星设置事件监听
        initListener();
    }

    /**
    * 方法说明:  给星星设置事件监听
    *@author liuxiong
    *@time 2016/12/14 16:00
    */
    private void initListener() {
        if(mIsIndicator){
            return;
        }

        //设置View数的监听
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                if(halfStarMesureWidth ==0){
                    //计算星星到父控件左边的距离
                    int left = contains.get(0).getLeft();
                    int right = contains.get(0).getRight();
                    int right1= contains.get(1).getRight();
                    halfStarMesureWidth =((int)(right-left+0.5))/2;  //每颗星星宽度的一半
                    int measuredSpace= right1 - right - (2 * halfStarMesureWidth); //星星之间的间距
                    LogUtils.d("cmeasuredSpace="+measuredSpace);
                    for (int i=0;i<contains.size();i++){
                        starMesureCenterX[i]=(2*i+1)* halfStarMesureWidth +i*measuredSpace;  //计算每颗星星中心到父控件的左边的距离
                        LogUtils.d("centerX[i]="+starMesureCenterX[i]);
                    }
                }
            }
        });
    }

    int downX;
    int downY;
    int moveX;
    int moveY;
    int maxDx;
    int maxDy;
    int range=5;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mIsIndicator){
            return super.onTouchEvent(event);
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX= (int) (event.getX()+0.5);  //记录按下时手指到布局左边的距离
                downY= (int) (event.getY()+0.5);    //记录按下时手指到布局上边的距离
                return true;
            case MotionEvent.ACTION_MOVE:
                moveX= (int) (event.getX()+0.5); //记录移动时手指到布局左边的距离
                moveY= (int) (event.getY()+0.5); //记录移动时手指到布局上边的距离
                LogUtils.d("------ACTION_MOVE");
                if(!isClick()){  //滑动的距离已经超出点击事件的范畴
                    handleEvent(moveX);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.d("maxDx="+maxDx+"::maxDy="+maxDy);
                if(maxDx>range||maxDy>range){  //为滑动事件

                }else{  //为点击事件
                    LogUtils.d("handleClick");
                    handleClick(downX);
                }
                maxDx=0;
                maxDy=0;
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
    * 方法说明:  处理点击事件
    * @param
    *@author liuxiong
    *@time 2017/1/13 11:54
    */
    private void handleClick(int downX) {
        if(isThisStar(downX,0)){
            setSelectRating(1);
        }else if(isThisStar(downX,1)){
            setSelectRating(2);
        }else if(isThisStar(downX,2)){
            setSelectRating(3);
        }else if(isThisStar(downX,3)){
            setSelectRating(4);
        }else if(isThisStar(downX,4)){
            setSelectRating(5);
        }
    }

    /**
    * 方法说明:  判断点击的是否是当前这个星星
    * @param
    *@author liuxiong
    *@time 2017/1/13 13:15
    */
    private boolean isThisStar(int downX,int starIndex){
        if(downX>(starMesureCenterX[starIndex]-halfStarMesureWidth)&&downX<
        (starMesureCenterX[starIndex]+halfStarMesureWidth)){
            return true;
        }else{
            return false;
        }
    }

    /**
    * 方法说明:  处理移动事件,触摸点在星星中心点的右边代表星星被选中
    * @param
    *@author liuxiong
    *@time 2017/1/13 11:30
    */
    private void handleEvent(float x) {
        if(x<starMesureCenterX[0]){
            setRating(0);
        }else if(x<starMesureCenterX[1]){
            setRating(1);
        }else if(x<starMesureCenterX[2]){
            setRating(2);
        }else if(x<starMesureCenterX[3]){
            setRating(3);
        }else if(x<starMesureCenterX[4]){
            setRating(4);
        }else{
            setRating(5);
        }
    }

    /**
    * 方法说明:  判断是否还在点击事件的范围
    * @param
    *@author liuxiong
    *@time 2017/1/13 11:25
    */
    private boolean isClick(){
        int dx=Math.abs(moveX-downX);
        int dy=Math.abs(moveY-downY);
        if(dx>maxDx){
            maxDx=dx;
        }
        if(dy>maxDy){
            maxDy=dy;
        }
        if(dx<=range&&dy<=range){ //未超出设置的移动距离临界值，认定为点击事件
         return true;
        }
        return false;  //认定为拖动事件
    }

    /**
    * 方法说明:点击的星星数等于已选择的星星的数量，取消选择得点击的星星，否则将选择的星星数量设为当前的星星序号
    *@author liuxiong
    *@time 2016/12/14 16:14
    */
    private void setSelectRating(int starId){
        if(mRating==starId){
            setRating(starId-1);
        }else{
            setRating(starId);
        }
    }

    /**
    * 方法说明: 设置星星的图片
    * @author liuxiong
    *  create at 2016/12/4 23:19
    */
    private void setStarDrawable(Drawable drawable) {
        mDrawable=drawable;
        for(int i=0;i<5;i++){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
                starBorders.get(i).setBackground(mDrawable);
            }else{
                starBorders.get(i).setBackgroundDrawable(mDrawable);
            }
        }
    }

    /**
    * 方法说明: 初始化布局参数
    * @author liuxiong
    *  create at 2016/12/4 18:34
    */
    private void initLayoutParams() {
        for(int i=0;i<5;i++){
            LayoutParams layoutParams = (LayoutParams) contains.get(i).getLayoutParams();
            layoutParams.width=mWidth;
            layoutParams.height=mHeight;
            if(i!=0){
                layoutParams.setMargins(mSpace,0,0,0);
            }
            contains.get(i).setLayoutParams(layoutParams);
        }
    }

    /**
    * 方法说明: 设置评分条的进度
    * @author liuxiong
    *  create at 2016/12/4 18:42
    */
    public void setRating(float rating){
        if(rating==mRating){
            return;
        }
        LayoutParams params = (LayoutParams) contains.get(0).getLayoutParams();
        mWidth=params.width;  //星星的宽度
        mHeight=params.height; //星星的高度
        mRating=rating;  //记录当前的星级
        for(int i=0;i<5;i++){
            //第i颗星星的布局参数
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) stars.get(i).getLayoutParams();
            layoutParams.height=mHeight;
            if(mRating==0){
                layoutParams.width=0;
            }else if(mRating<=1){
                if(i==0){
                    layoutParams.width= (int)(mWidth*mRating);//第一颗星星宽度按照小数部分决定
                }else{
                    layoutParams.width=0; //第二、三、四、五颗星星为空
                }
            }else if(mRating<=2){
                if(i==0){
                    layoutParams.width=mWidth;  //第一颗星星填满
                }else if(i==1){
                    layoutParams.width=(int)(mWidth*(mRating-1));//第二颗星星宽度按照小数部分决定
                }else{
                    layoutParams.width=0; //第三、四、五颗星星为空
                }
            }else if(mRating<=3){
                if(i<2){
                    layoutParams.width=mWidth;//第一、二颗星星填满
                }else if(i==2){
                    layoutParams.width=(int)(mWidth*(mRating-2));//第三颗星星宽度按照小数部分决定
                }else{
                    layoutParams.width=0;//第四、五颗星星为空
                }
            }else if(mRating<=4){
                if(i<3){
                    layoutParams.width=mWidth;//第一、二、三颗星星填满
                }else if(i==3){
                    layoutParams.width=(int)(mWidth*(mRating-3));//第四颗星星宽度按照小数部分决定
                }else{
                    layoutParams.width=0;//第五颗星星为空
                }
            }else if(mRating<=5){
                if(i<4){
                    layoutParams.width=mWidth;//第一、二、三、四颗星星填满
                }else if(i==4){
                    layoutParams.width=(int)(mWidth*(mRating-4));//第五颗星星宽度按照小数部分决定
                }else{
                    layoutParams.width=0;  //这句是多余的
                }
            }else{
                layoutParams.width=mWidth;  //所有星星全部填满
            }
            stars.get(i).setLayoutParams(layoutParams);
        }


    }

    /**
    * 方法说明: 获取当前显示的星级
    * @author liuxiong
    *  create at 2016/12/4 23:16
    */
    public float getRating(){
        return mRating;
    }

}


```

使用时例子

```
<com.example.myratingbar.weight.MyRatingBar
        android:layout_width="wrap_content"
        android:layout_marginTop="50dp"
        app:space="5dp"
        app:starHeight="30dp"
        app:starWidth="30dp"
        app:starDrawable="@drawable/ic_star_border"
        app:progressColor="@color/star_color"
        app:rating="4.5"
        app:isIndicator="false"
        android:layout_height="wrap_content">

    </com.example.myratingbar.weight.MyRatingBar>
```

github地址：https://github.com/liuxiong1992/MyRatingBar