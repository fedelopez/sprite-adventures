package com.bowerbreak.sprites.app;

/**
 * @author fede
 */

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameLoop implements Runnable {

    private static final int MILLIS_PER_SPRITE = 1000 / Actor.NUM_SPRITES;

    private final SurfaceView view;
    private final Lock lock = new ReentrantLock();

    private final Sprite indy;
    private final Sprite target;

    private boolean keepGoing = false;

    public GameLoop(SurfaceView view) {
        this.view = view;
        this.view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Point destination = new Point((int) event.getX(), (int) event.getY());
                target.moveTo(destination);
                indy.moveTo(destination);
                return true;
            }
        });
        this.indy = new Actor(view.getResources());
        this.target = new StaticObject(BitmapFactory.decodeResource(view.getResources(), R.drawable.target));
    }

    public void start() {
        if (lock.tryLock()) {
            keepGoing = true;
            lock.unlock();
        }
        run();
    }

    public void stop() {
        if (lock.tryLock()) {
            keepGoing = false;
            lock.unlock();
        }
    }

    @Override
    public void run() {
        if (lock.tryLock()) {
            try {
                if (keepGoing) {
                    Canvas canvas = view.getHolder().lockCanvas();
                    drawOn(canvas);
                    view.getHolder().unlockCanvasAndPost(canvas);
                    view.getHandler().postDelayed(this, MILLIS_PER_SPRITE);
                    indy.update();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private void drawOn(Canvas canvas) {
        canvas.drawColor(Color.rgb(248, 248, 255));
        indy.drawTo(canvas);
        target.drawTo(canvas);
    }
}