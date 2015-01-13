/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Jonathon Prehn and Kevin Prehn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package bropals.lib.simplegame.util;

import bropals.lib.simplegame.entity.BaseEntity;
import bropals.lib.simplegame.entity.block.BlockEntity;
import java.util.List;

/**
 * Provides convenience functions for making 2D level editors.
 * @author Jonathon
 */
public class LevelEditorUtil {
    
    /**
     * Snaps the given block entity to a nearby entity if it is
     * <code>snapDistance</code> or less away from it.
     * @param snapping the entity to have snap
     * @param snapDistance the minimum distance for a snap to occur.
     */
    public static void snap(BlockEntity snapping, float snapDistance) {
        List<BaseEntity> list = snapping.getParent().getEntities();
        for (BaseEntity e : list) {
            if (e instanceof BlockEntity) {
                BlockEntity be = (BlockEntity)e;
                if (!be.equals(snapping)) {
                    //Right side of snapping
                    if (abs(snapping.getX()+snapping.getWidth()-be.getX()) <= snapDistance) {
                        snapping.setX(be.getX()-snapping.getWidth());
                    }
                    //Left side of snapping
                    else if (abs( snapping.getX()-be.getX()-be.getWidth() ) <= snapDistance) {
                        snapping.setX(be.getX()+be.getWidth());
                    }
                    //Bottom side of snapping
                    if (abs(snapping.getX()+snapping.getHeight()-be.getY()) <= snapDistance) {
                        snapping.setY(be.getY()-snapping.getHeight());
                    }
                    //Top side of snapping
                    else if (abs(snapping.getY()-be.getY()-be.getHeight()) <= snapDistance) {
                        snapping.setY(be.getY()+be.getHeight());
                    }
                }
            }
        }
    }
    
    /**
     * Sets the width of the BlockEntity based on its new right bounds position.
     * Designed to be constantly called while resizing a BlockEntity.
     * @param resizing the block entity to resize
     * @param newX the new X position of its right bounds
     * @param min the minimum allowable width
     */
    public void resizeRight(BlockEntity resizing, float newX, float min) {
        resizing.setWidth(newX-resizing.getX());
        if (resizing.getWidth()<min) {
            resizing.setWidth(min);
        }
    }
    
    /**
     * Sets the width of the BlockEntity based on its new left bounds position.
     * Designed to be constantly called while resizing a BlockEntity.
     * @param resizing the block entity to resize
     * @param newX the new X position of its left bounds
     * @param min the minimum allowable width
     */
    public void resizeLeft(BlockEntity resizing, float newX, float min) {
        float oldRight = resizing.getX()+resizing.getWidth();
        float width = oldRight-newX;
        if (width < min) {
            resizing.setX(oldRight-min);
            resizing.setWidth(min);
        } else {
            resizing.setX(newX);
            resizing.setWidth(width);
        }
    }
    
    /**
     * Sets the height of the BlockEntity based on its new bottom bounds position.
     * Designed to be constantly called while resizing a BlockEntity.
     * @param resizing the block entity to resize
     * @param newY the new Y position of its bottom bounds
     * @param min the minimum allowable height
     */
    public void resizeBottom(BlockEntity resizing, float newY, float min) {
        resizing.setHeight(newY-resizing.getY());
        if (resizing.getHeight()<min) {
            resizing.setHeight(min);
        }
    }
    
    /**
     * Sets the height of the BlockEntity based on its new top bounds position.
     * Designed to be constantly called while resizing a BlockEntity.
     * @param resizing the block entity to resize
     * @param newY the new Y position of its top bounds
     * @param min the minimum allowable height
     */
    public void resizeTop(BlockEntity resizing, float newY, float min) {
        float oldBottom = resizing.getX()+resizing.getWidth();
        float height = oldBottom-newY;
        if (height < min) {
            resizing.setY(oldBottom-min);
            resizing.setHeight(min);
        } else {
            resizing.setY(newY);
            resizing.setHeight(height);
        }
    }
    
    private static float abs(float val) {
        if (val < 0) {
            return -val;
        }
        return val;
    }
}