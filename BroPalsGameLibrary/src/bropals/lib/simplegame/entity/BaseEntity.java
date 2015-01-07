/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
