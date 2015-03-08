package com.bowerbreak.sprites.app;

/**
 * @author fede
 */

import android.graphics.*;

public class StaticObject implements Sprite {

    private final Bitmap bitmap;
    private final Paint paint = new Paint();
    private Point destination;

    public StaticObject(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public void update() {
    }

    @Override
    public void drawTo(Canvas canvas) {
        if (destination == null) {
            return;
        }
        Rect rect = rect();
        canvas.drawBitmap(bitmap, rect.left, rect.top, paint);
        paint.setStrokeWidth(1);
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);
    }

    @Override
    public void moveTo(Point destination) {
        this.destination = destination;
    }

    @Override
    public Rect rect() {
        int left = destination.x - bitmap.getWidth() / 2;
        int top = destination.y - bitmap.getHeight() / 2;
        return new Rect(left, top, left + bitmap.getWidth(), top + bitmap.getHeight());
    }

}