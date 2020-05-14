package com.chaoliu.app.aop.method;

import android.util.Log;
import android.view.View;

import com.chaoliu.app.R;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Calendar;

/**
 *
 * 拦截onClick方法 解决解决双击问题
 * @author chentong
 * @date 2020-05-11
 *
 */
@Aspect
public class OnClickAspect {
    static int TIME_TAG = R.id.click_time;
    public static final int MIN_CLICK_DELAY_TIME = 600;

    @Around("execution(* android.view.View.OnClickListener.onClick(..))")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e("tag","onclick");
        View view = null;
        for (Object arg : joinPoint.getArgs())
            if (arg instanceof View) view = (View) arg;
        if (view != null) {
            Object tag = view.getTag(TIME_TAG);
            long lastClickTime = ((tag != null) ? (long) tag : 0);
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {//过滤掉600毫秒内的连续点击
                view.setTag(TIME_TAG, currentTime);
                Log.e("tag","proceed");
                joinPoint.proceed();//执行原方法
            }
        }
    }
}
