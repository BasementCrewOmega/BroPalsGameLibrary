/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.math;

/**
 * A Vector with 2 components to it.
 * 
 * All the components are stored as floats, despite the 
 * getter and setter methods taking doubles.
 * @author Kevin Prehn
 */
public class Vector2D {
    
    private float x, y;
    
    /**
     * Make a new [0, 0] Vector.
     */
    public Vector2D() {
        x = 0;
        y = 0;
    }
    
    /**
     * Make a new Vector with the given initial values.
     * @param x The initial x component.
     * @param y The initial y component.
     */
    public Vector2D(double x, double y) {
        this.x = (float)x;
        this.y = (float)y;
    }

    /**
     * Returns the dot product between this vector and another vector.
     * @param other The other vector
     * @return The dot product between the two vectors.
     */
    public float dot(Vector2D other) {
        return (x * other.getX()) + (y * other.getY());
    }
    
    /**
     * Return a new vector whose magnitude is 1 and direction
     * is the same.
     * @return A new normalized vector
     */
    public Vector2D normalize() {
        float magn = magnitude();
        return new Vector2D(x / magn, y / magn);
    }
    
    /**
     * Normalize this vector, making it's magnitude 1 and the 
     * direction unchanged.
     */
    public void normalizeLocal() {
        float magn = magnitude();
        setX(x / magn);
        setY(y / magn);
    }
    
    /**
     * Make a new vector that is a copy of this vector but scaled.
     * @param factor The factor the new vector will be scaled by.
     * @return A new vector that is a copy of this vector but scaled 
     * by the given factor
     */
    public Vector2D scale(double factor) {
        return new Vector2D(x * factor, y * factor);
    }
    
    /**
     * Scale this vector by the given factor.
     * @param factor The factor this vector will be scaled
     */
    public void scaleLocal(double factor) {
        setX(x * factor);
        setY(y * factor);
    }
    
    /**
     * Make a new vector that is the sum of this and a given vector.
     * @param other The other vector
     * @return A new vector that is the sum of this and the given vector.
     */
    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.getX(), y + other.getY());
    }
    
    /**
     * Add the given vector to this vector.
     * @param other The vector being added.
     */
    public void addLocal(Vector2D other) {
        setX(x + other.getX());
        setY(y + other.getY());
    }
    
    /**
     * Returns the magnitude (length) of the vector.
     * @return The magnitude of the vector.
     */
    public float magnitude() {
        return (float)Math.sqrt((x * x) + (y * y));
    }
    
    /**
     * Returns the magnitude squared of the vector. This
     * is so the computer doesn't have to do a square root.
     * @return The magnitude of the vector squared
     */
    public float magnitudeSquared() {
        return (x * x) + (y * y);
    }
    
    /**
     * Set both values of the Vector.
     * @param x The new x component
     * @param y The new y component
     */
    public void setValues(double x, double y) {
        this.x = (float)x;
        this.y = (float)y;
    }
    
    /**
     * Returns the vector's x component
     * @return the vector's x component
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the vector's x component
     * @param x the vector's x component
     */
    public void setX(double x) {
        this.x = (float)x;
    }

    /**
     * Returns the vector's y component
     * @return the vector's y component
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the vector's y component
     * @param y the vector's y component
     */
    public void setY(double y) {
        this.y = (float)y;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Vector2D) {
            return getX() == ((Vector2D)other).getX() && 
                   getY() == ((Vector2D)other).getY();
        }
        return false;
    }
}
