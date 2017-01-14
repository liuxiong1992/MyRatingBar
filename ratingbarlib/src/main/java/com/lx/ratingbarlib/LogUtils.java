package com.lx.ratingbarlib;

import android.text.TextUtils;
import android.util.Log;

/**
*  类说明：
*  created by liuxiong on 2017/1/13
**/
public class LogUtils {
    /**
    * 方法说明:
    * @param  str  要输出的信息
    *@author liuxiong
    *@time 2017/1/13 10:16
    */
    public static void d(String str){
        if(!TextUtils.isEmpty(str)){
            Log.d("------","---"+str);
        }
    }
}
