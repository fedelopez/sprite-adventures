package com.bowerbreak.sprites.app;

import android.graphics.Point;
import android.graphics.Rect;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * @author fede
 */
public class ActorMotionManagerTest {

    @Test
    public void shouldCalculateTheNextPoint() {
        //given
        Rect rect = new Rect(0, 0, 10, 20);
        Point destination = new Point(100, rect.height() - 4);
        int offset = rect.width() / 2;
        //when
        Point actual = ActorMotionManager.nextPoint(rect, destination, offset);
        //then
        Assert.assertThat(actual, equalTo(new Point(rect.width() + offset, 0)));
    }

}
