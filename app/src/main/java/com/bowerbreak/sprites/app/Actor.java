package com.bowerbreak.sprites.app;

/**
 * @author fede
 */

import android.content.res.Resources;
import android.graphics.*;

import java.util.*;

import static android.graphics.BitmapFactory.decodeResource;

public class Actor implements Sprite {

    public static final int NUM_SPRITES = 7;

    private final Map<Boolean, Bitmap> idleSprites = new HashMap<>();
    private final Map<Boolean, List<Bitmap>> walkingSprites = new HashMap<>();

    private final Paint paint = new Paint();

    private final int spriteWidth;
    private final int spriteHeight;

    private final Resources resources;
    private int walkingSpriteIndex = 0;

    private boolean westToEast = false;

    private Point location = new Point(0, 0);
    private Point destination;

    public Actor(Resources resources) {
        this.resources = resources;

        Bitmap spriteSheetRight = decodeResource(resources, R.drawable.indy_right);
        Bitmap spriteSheetLeft = decodeResource(resources, R.drawable.indy_left);

        spriteWidth = spriteSheetRight.getWidth() / NUM_SPRITES;
        spriteHeight = spriteSheetRight.getHeight();

        initIdleSprites();
        initWalkingSprites(spriteSheetRight, spriteSheetLeft);
    }

    @Override
    public void update() {
        if (isIdle()) {
            return;
        }
        walkingSpriteIndex++;
        if (!westToEast) {
            location.x += spriteWidth / NUM_SPRITES;
        } else {
            location.x -= spriteWidth / NUM_SPRITES;
        }
        if (walkingSpriteIndex > NUM_SPRITES - 1) {
            walkingSpriteIndex = 0;
        }
        if (location.x > resources.getDisplayMetrics().widthPixels) {
            location.y += spriteHeight;
            westToEast = true;
        }
        if (location.x < -spriteWidth) {
            location.y += spriteHeight;
            westToEast = false;
        }
        if (isAtDestination()) {
            destination = null;
        }
    }

    @Override
    public void drawTo(Canvas canvas) {
        if (isIdle()) {
            canvas.drawBitmap(idleSprites.get(westToEast), location.x, location.y, paint);
        } else {
            canvas.drawBitmap(walkingSprites.get(westToEast).get(walkingSpriteIndex), location.x, location.y, paint);
        }
        paint.setStrokeWidth(1);
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect(), paint);
    }

    @Override
    public void moveTo(Point destination) {
        this.destination = destination;
    }

    @Override
    public Rect rect() {
        return new Rect(location.x, location.y, location.x + spriteWidth, location.y + spriteHeight);
    }

    private boolean isAtDestination() {
        return destination != null && rect().contains(destination.x, destination.y);
    }

    private boolean isIdle() {
        return destination == null;
    }

    private void initIdleSprites() {
        idleSprites.put(!westToEast, decodeResource(resources, R.drawable.indy_left_standup));
        idleSprites.put(westToEast, decodeResource(resources, R.drawable.indy_right_standup));
    }

    private void initWalkingSprites(Bitmap spriteSheetRight, Bitmap spriteSheetLeft) {
        List<Bitmap> spritesRight = new ArrayList<>();
        List<Bitmap> spritesLeft = new ArrayList<>();
        int offset = 0;
        for (int i = 0; i < NUM_SPRITES; i++) {
            spritesRight.add(Bitmap.createBitmap(spriteSheetRight, offset, 0, spriteWidth, spriteHeight));
            spritesLeft.add(Bitmap.createBitmap(spriteSheetLeft, offset, 0, spriteWidth, spriteHeight));
            offset += spriteWidth;
        }
        Collections.reverse(spritesLeft);
        walkingSprites.put(!westToEast, spritesLeft);
        walkingSprites.put(westToEast, spritesRight);
    }
}