package jp.gcreate.sample.viewdrawingtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Copyright 2016 G-CREATE
 */

public class CustomView extends TextView {
    private Paint paint = new Paint();
    private boolean shouldDrawUp;
    private boolean shouldDrawDown;
    private boolean shouldDrawLeft;
    private boolean shouldDrawRight;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initialize() {
        paint.setColor(Color.RED);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void clearDecorate() {
        shouldDrawUp = shouldDrawDown = shouldDrawLeft = shouldDrawRight = false;
    }

    public void decorateUp() {
        this.shouldDrawUp = true;
    }

    public void decorateBottom() {
        this.shouldDrawDown = true;
    }

    public void decorateLeft() {
        this.shouldDrawLeft = true;
    }

    public void decorateRight() {
        this.shouldDrawRight = true;
    }

    public void decorateAround() {
        shouldDrawUp = shouldDrawDown = shouldDrawLeft = shouldDrawRight = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width  = canvas.getWidth() - 1;
        int height = canvas.getHeight() - 1;

        if (shouldDrawUp) {
            canvas.drawLine(0, 0, width, 0, paint);
        }
        if (shouldDrawDown) {
            canvas.drawLine(0, height, width, height, paint);
        }
        if (shouldDrawLeft) {
            canvas.drawLine(0, 0, 0, height, paint);
        }
        if (shouldDrawRight) {
            canvas.drawLine(width, 0, width, height, paint);
        }
    }
}
