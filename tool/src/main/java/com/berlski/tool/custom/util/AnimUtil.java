package com.berlski.tool.custom.util;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 动画工具类（Util）
 * Created by Berlski on 2019/3/8.
 */
public class AnimUtil {

    /**
     * 横向移动动画
     *
     * @param view 需要移动的view
     * @param one  起点坐标
     * @param two  终点坐标
     * @param time 动画持续时长
     */
    public static void translationX(View view, int one, int two, int time) {
        ObjectAnimator.ofFloat(view, "translationX", one, two).setDuration(time).start();
    }

    /**
     * 透明度渐变动画,从隐藏到显现
     *
     * @param view
     */
    public static void setAlphaGone(final View view) {
        view.setAlpha(1);
        setAlphaAnim(view, 1.0F, 0.0F, 2000, 1000);
    }

    /**
     * 透明度渐变动画,从显现到隐藏
     *
     * @param view
     */
    public static void setAlphaVisible(final View view) {
        setAlphaVisible(view, 1000);
    }

    /**
     * 透明度渐变动画,从显现到隐藏
     *
     * @param view
     * @param time 动画时长
     */
    public static void setAlphaVisible(final View view, int time) {
        view.setAlpha(0);
        setAlphaAnim(view, 0.0F, 1.0F, time, 0);
    }

    /**
     * 透明度渐变动画,从显现到隐藏
     *
     * @param view
     * @param time      动画时长
     * @param delayTime 延时时长
     */
    public static void setAlphaVisible(final View view, int time, int delayTime) {
        view.setAlpha(0);
        setAlphaAnim(view, 0.0F, 1.0F, time, delayTime);
    }

    /**
     * 透明度渐变动画
     *
     * @param view      渐变的view
     * @param valueOne  渐变初始透明度
     * @param valueTwo  渐变结束透明度
     * @param time      动画持续时间
     * @param delayTime 动画延迟时间
     */
    public static void setAlphaAnim(final View view, final float valueOne, final float valueTwo, final int time, int delayTime) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator.ofFloat(view, "alpha", valueOne, valueTwo).setDuration(time).start();
            }
        }, delayTime);
    }

    /**
     * 果冻弹力动画
     *
     * @param view 设定
     */
    public static void setJellyElastanAnim(final View view) {
        jellyElastanAnim(view, 500);
    }

    /**
     * 果冻弹力动画
     *
     * @param view
     */
    public static void setJellyElastanAnim(final View view, int time) {
        jellyElastanAnim(view, time);
    }

    /**
     * 果冻弹力动画
     *
     * @param view
     */
    public static void jellyElastanAnim(final View view, int time) {
        ObjectAnimator scaleAnimX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.3f, 0.8f, 1f);
        scaleAnimX.setDuration(time);

        scaleAnimX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                view.setScaleY(cVal);
            }
        });
        scaleAnimX.start();
    }

    /**
     * 设置晃动动画
     *
     * @param view 晃动的view，默认晃动5次
     */
    public static void setShakeAnim(View view) {
        view.setAnimation(shakeAnimation(4, 600));
    }

    /**
     * 设置晃动动画
     *
     * @param view      晃动的view
     * @param frequency 晃动的次数
     */
    public static void setShakeAnim(View view, int frequency) {
        view.setAnimation(shakeAnimation(frequency, 1000));
    }

    /**
     * * 设置晃动动画
     *
     * @param view      晃动的view
     * @param frequency 晃动的次数
     * @param time      晃动的时间
     */
    public static void setShakeAnim(View view, int frequency, int time) {
        view.setAnimation(shakeAnimation(frequency, time));
    }

    /**
     * 晃动动画
     * 在指定的时间内，晃动指定的次数
     *
     * @param frequency 晃动的次数
     * @param time      晃动的时间
     * @return
     */
    public static Animation shakeAnimation(int frequency, int time) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(frequency));
        translateAnimation.setDuration(time);
        return translateAnimation;
    }

    /**
     * 震动动画
     *
     * @param view
     */
    public static void shockAnim(View view) {
        int shakeDegrees = 20;
        //先往左再往右
        PropertyValuesHolder rotateValuesHolder = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.1f, -shakeDegrees),
                Keyframe.ofFloat(0.2f, 0f),
                Keyframe.ofFloat(0.3f, shakeDegrees),
                Keyframe.ofFloat(0.4f, 0f),
                Keyframe.ofFloat(0.5f, -shakeDegrees),
                Keyframe.ofFloat(0.6f, 0f),
                Keyframe.ofFloat(0.7f, shakeDegrees),
                Keyframe.ofFloat(0.8f, 0f),
                Keyframe.ofFloat(0.9f, -shakeDegrees),
                Keyframe.ofFloat(1.0f, 0f));

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, rotateValuesHolder);
        objectAnimator.setDuration(500);
        objectAnimator.start();

        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY,
                HapticFeedbackConstants.FLAG_IGNORE_VIEW_SETTING
                        | HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
    }
}
