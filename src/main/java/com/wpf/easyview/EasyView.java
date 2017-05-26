package com.wpf.easyview;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

import com.example.easyview.R;

public class EasyView extends android.support.v7.widget.AppCompatTextView {
    int solidColor, stroke_Color, touchColor, touchTextColor, normalTextColor, enableColor, enableTextColor;
    int cornesRadius, topLeftRadius, topRightRadius, bottomLeftRadius,
            bottomRightRadius, stroke_Width, strokeDashWidth, strokeDashGap,
            shapeType;
    GradientDrawable gradientDrawable;

    public EasyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public EasyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs);
    }

    public EasyView(Context context) {
        super(context);
    }

    private void initData(Context context, AttributeSet attrs) {
        setClickable(true);
        normalTextColor = getCurrentTextColor();
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.EasyView);
        touchTextColor = a.getColor(R.styleable.EasyView_touchTextColor, getCurrentTextColor());
        solidColor = a.getColor(R.styleable.EasyView_solidColor,
                Color.GRAY);
        stroke_Color = a.getColor(R.styleable.EasyView_stroke_Color,
                Color.TRANSPARENT);
        touchColor = a.getColor(R.styleable.EasyView_touchSolidColor,
                Color.TRANSPARENT);
        enableColor = a.getColor(R.styleable.EasyView_enableColor, solidColor);
        enableTextColor = a.getColor(R.styleable.EasyView_enableTextColor, getCurrentTextColor());
        cornesRadius = (int) a.getDimension(
                R.styleable.EasyView_cornesRadius, 0);
        topLeftRadius = (int) a.getDimension(
                R.styleable.EasyView_topLeftRadius, 0);
        topRightRadius = (int) a.getDimension(
                R.styleable.EasyView_topRightRadius, 0);
        bottomLeftRadius = (int) a.getDimension(
                R.styleable.EasyView_bottomLeftRadius, 0);
        bottomRightRadius = (int) a.getDimension(
                R.styleable.EasyView_bottomRightRadius, 0);
        stroke_Width = (int) a.getDimension(
                R.styleable.EasyView_stroke_Width, 0);
        strokeDashWidth = (int) a.getDimension(
                R.styleable.EasyView_strokeDashWidth, 0);
        strokeDashGap = (int) a.getDimension(
                R.styleable.EasyView_strokeDashGap, 0);
        shapeType = a.getInt(
                R.styleable.EasyView_shapeType, -1);
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(stroke_Width, stroke_Color, strokeDashWidth,
                strokeDashGap);

        if (!isEnabled()) {
            gradientDrawable.setColor(enableColor);
            setTextColor(enableTextColor);
        } else {
            gradientDrawable.setColor(solidColor);
            setTextColor(normalTextColor);
        }
        if (shapeType != -1) {
            gradientDrawable.setShape(shapeType);
        }
        if (shapeType != GradientDrawable.OVAL) {
            if (cornesRadius != 0) {
                gradientDrawable.setCornerRadius(cornesRadius);
            } else {
                //1、2两个参数表示左上角，3、4表示右上角，5、6表示右下角，7、8表示左下角
                gradientDrawable.setCornerRadii(new float[]{topLeftRadius,
                        topLeftRadius, topRightRadius, topRightRadius,
                        bottomRightRadius, bottomRightRadius, bottomLeftRadius,
                        bottomLeftRadius});
            }
        }
        setBackgroundDrawable(gradientDrawable);

    }

    @Override
    public void setEnabled(boolean enabled) {
        if (gradientDrawable != null) {
            if (enabled) {
                gradientDrawable.setColor(solidColor);
                setTextColor(normalTextColor);
            } else {
                gradientDrawable.setColor(enableColor);
                setTextColor(enableTextColor);
            }
            setBackgroundDrawable(gradientDrawable);
        }

        super.setEnabled(enabled);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isEnabled()) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (touchColor != Color.TRANSPARENT) {
                    gradientDrawable.setColor(touchColor);
                    setBackgroundDrawable(gradientDrawable);
                    setTextColor(touchTextColor);
                }
            } else if (event.getAction() == MotionEvent.ACTION_UP
                    || event.getAction() == MotionEvent.ACTION_CANCEL) {
                if (touchColor != Color.TRANSPARENT) {
                    gradientDrawable.setColor(solidColor);
                    setBackgroundDrawable(gradientDrawable);
                    setTextColor(normalTextColor);
                }
            }
        }
        return super.onTouchEvent(event);
    }

}

