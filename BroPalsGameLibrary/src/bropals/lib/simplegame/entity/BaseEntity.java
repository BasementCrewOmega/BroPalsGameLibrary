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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 **/
package bropals.lib.simplegame.entity;

/**
 * The most simple type of Entity.
 * @author Kevin Prehn
 */
public abstract class BaseEntity {
    
    /**
     * The {@link bropals.lib.simplegame.entity.GameWorld} that contains this
     * BaseEntity object.
     */
    private GameWorld parent;
    
    /*
     * Create a new entity with a parent. The parent will add this 
     * entity to it's list of entities.
     * @param par The parent of this new entity.
     */
    public BaseEntity(GameWorld par) {
        parent = par;
        parent.addEntity(this);
    }

    /**
     * Updates this entity. Meant to be overridden and implemented in a subclass.
     */
    public abstract void update();
    
    /**
     * Render this entity. Meant to be overridden and implemented in a subclass.
     * @param graphicsObj The graphics object being used to draw this.
     */
    public abstract void render(Object graphicsObj);
    
    /**
     * Get the parent of the entity.
     * @return The parent of the entity
     */
    public GameWorld getParent() {
        return parent;
    }

    /**
     * Set the parent of the entity
     * @param parent The parent of the entity.
     */
    public void setParent(GameWorld parent) {
        this.parent = parent;
    }
    
    /**
     * Set the parent of this entity to null.
     */
    public void removeParent() {
        this.parent = null;
    }
    
}
