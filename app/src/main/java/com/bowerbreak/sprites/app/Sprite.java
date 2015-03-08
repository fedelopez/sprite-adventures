package com.bowerbreak.sprites.app;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * @author fede
 */
public interface Sprite {

    void update();

    void drawTo(Canvas canvas);

    void moveTo(Point point);

    Rect rect();

}
