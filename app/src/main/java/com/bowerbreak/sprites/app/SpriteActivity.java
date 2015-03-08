package com.bowerbreak.sprites.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * @author fede
 */
public class SpriteActivity extends Activity {
    private static final String TAG = "SpriteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "About to set game view...");
        setContentView(new GameView(this));
    }
}
