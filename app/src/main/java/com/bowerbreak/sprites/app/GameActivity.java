package com.bowerbreak.sprites.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

/**
 * @author fede
 */
public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SceneView(this));
    }

    private class SceneView extends View {
        private final Paint cPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final Paint tPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final Path circle = new Path();

        public SceneView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            cPaint.setColor(Color.LTGRAY);
            cPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            cPaint.setStrokeWidth(3);

            tPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            tPaint.setColor(Color.BLACK);
            float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
            tPaint.setTextSize(20 * scaledDensity);

            float density = getResources().getDisplayMetrics().density;
            circle.addCircle(150 * density, 150 * density, 180 * density, Path.Direction.CW);
            canvas.drawPath(circle, cPaint);

            String title = "This is the most awesome graphic adventure, coming soon!";
            canvas.drawTextOnPath(title, circle, 0, tPaint.getTextSize(), tPaint);
        }
    }
}
