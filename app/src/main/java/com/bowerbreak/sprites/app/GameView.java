package com.bowerbreak.sprites.app;

/**
 * @author fede
 */

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private final GameLoop gameLoop;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        gameLoop = new GameLoop(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        System.out.println("GameView.surfaceCreated");
        gameLoop.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        System.out.println("GameView.surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        System.out.println("GameView.surfaceDestroyed");
        gameLoop.stop();
    }
}