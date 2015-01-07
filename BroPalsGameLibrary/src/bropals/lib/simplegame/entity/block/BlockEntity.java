/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.entity.block;

import bropals.lib.simplegame.entity.BaseEntity;
import bropals.lib.simplegame.entity.GameWorld;
import bropals.lib.simplegame.math.Vector2D;
import java.util.ArrayList;

/**
 * A simple rectangular entity.
 * @author Kevin Prehn
 */
public class BlockEntity extends BaseEntity {
    
    private float x, y, width, height;
    private Vector2D velocity, acceleration;
    private boolean anchored, collidable;
    
    /**
     * Create a new collidable BlockEntity with the given parameters.
     * @param parent The parent of the BlockEntity
     * @param x The x position of the BlockEntity
     * @param y The y position of the BlockEntity
     * @param width The width of the BlockEntity
     * @param height The height of the BlockEntity
     * @param anchored Whether or not this BlockEntity object can move or not.
     */
    public BlockEntity(GameWorld parent, float x, float y, float width, 
            float height, boolean anchored) {
        super(parent);
        velocity = new Vector2D();
        acceleration = new Vector2D();
        this.anchored = anchored;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        collidable = true;
    }

    
    @Override
    public void update() {
        if (!anchored) {
            velocity.addLocal(acceleration);
            x += velocity.getX();
            y += velocity.getY();
            
            if (collidable)
                checkCollisions();
        }
    }
    
    /**
     * A method that is called when this block collides with another block.
     * This is called after the position is fixed, but before this
     * block stops moving.
     * @param other The other block that this block collided with
     */
    public void collideWith(BlockEntity other) {
        // override in a subclass
    }
    
    /**
     * Check and fix the collision with another BlockEntity.
     * @param other The other BlockEntity
     * @return Whether or not this object collided with the other object.
     */
    public boolean handleCollide(BlockEntity other) {
        if (!other.isCollidable() || !isCollidable()) // return false if any of the two can't collide
            return false;
        
        float smallestMaxX = other.getX() + other.getWidth() <
                getX() + getWidth() ? other.getX() + other.getWidth() : 
                getX() + getWidth();
        float largestMinX = other.getX() > getX() ? other.getX() : getX();
        
        float smallestMaxY = other.getY() + other.getHeight() <
                getY() + getHeight() ? other.getY() + other.getHeight() : 
                getY() + getHeight();
        float largestMinY = other.getY() > getY() ? other.getY() : getY();
        
        float penX = largestMinX - smallestMaxX;
        float penY = largestMinY - smallestMaxY;
        
        if (penX < 0 && penY < 0) {
            if (penY > penX) { // if y is closer to 0 than x is
                if (getY() < other.getY()) {
                    setY(other.getY() - getHeight());
                } else {
                    setY(other.getY() + other.getHeight());
                }
                collideWith(other);
                velocity.setY(0);
            } else {
                if (getX() < other.getX()) {
                    setX(other.getX() - getWidth());
                } else {
                    setX(other.getX() + other.getWidth());
                }
                collideWith(other);
                velocity.setX(0);
            }
            return true;
        }
        // no collision
        return false;
    }
    
    /**
     * Check for and fix collisions with other BlockEntity objects in this 
     * BlockEntity's GameWorld.
     */
    public void checkCollisions() {
        ArrayList<BaseEntity> entities = getParent().getEntities();
        for (BaseEntity entity : entities) {
            if (this != entity && entity instanceof BlockEntity) {
                handleCollide((BlockEntity)entity);
            }
        }
    }

    @Override
    public void render(Object graphicsObj) {
        // override in a subclass to specify how it's rendered
    }
    
    /**
     * Return the block's x position.
     * @return the block's x position
     */
    public float getX() {
        return x;
    }

    /**
     * Set the block's x position
     * @param x the block's new x position
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Return the block's y position.
     * @return the block's y position
     */
    public float getY() {
        return y;
    }

    /**
     * Set the block's y position
     * @param y the block's new y position
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Return the block's width.
     * @return the block's width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Set the block's width
     * @param width the block's new width
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Return the block's height.
     * @return the block's height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Set the block's height
     * @param height the block's new height
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Returns the BlockEntity's velocity vector
     * @return The BlockEntity's velocity vector
     */
    public Vector2D getVelocity() {
        return velocity;
    }

    /**
     * Returns the BlockEntity's acceleration vector
     * @return The BlockEntity's acceleration vector
     */
    public Vector2D getAcceleration() {
        return acceleration;
    }
    
    /**
     * Returns whether this is anchored or not
     * @return Whether or not this is anchored.
     */
    public boolean isAnchored() {
        return anchored;
    }

    /**
     * Set this BlockEntity to anchored or not.
     * @param anchored The BlockEntity's new anchored value.
     */
    public void setAnchored(boolean anchored) {
        this.anchored = anchored;
    }

    /**
     * Returns whether this is collidable or not
     * @return Whether or not this is collidable.
     */
    public boolean isCollidable() {
        return collidable;
    }

    /**
     * Set this BlockEntity to collidable or not.
     * @param anchored The BlockEntity's new collidable value.
     */
    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

}
