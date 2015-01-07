/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.entity;

import java.util.ArrayList;

/**
 * A generic GameWorld that holds objects of type {@link bropals.lib.simplegame.entity.BaseEntity}.
 * The GameWorld will update and render all the entities.
 * 
 * @author Kevin Prehn
 */
public class GameWorld<T extends BaseEntity> {
    
    private ArrayList<T> entities;
    
    public GameWorld() {
        entities = new ArrayList<>();
    }
    
    /**
     * Updates all the entities in this GameWorld's list of entities. It
     * will also remove any entity whose parent is not this GameWorld or null.
     */
    public void updateEntities() {
        for (int i=0; i<entities.size(); i++) {
            // remove the entity from this game world if it doesn't belong
            // here or if it's been removed
            if (entities.get(i).getParent() != this) {
                entities.remove(i);
                continue;
            }
            entities.get(i).update();
        }
    }
    
    /**
     * Returns a list of the entities in this GameWorld.
     * @return A list of the entities in this GameWorld.
     */
    public ArrayList<T> getEntities() {
        return entities;
    }
    
    /**
     * Adds an entity to the list of entities if it's not already added.
     * @param entity The entity being added.
     */
    public void addEntity(T entity) {
        if (!entities.contains(entity)) {
            entities.add(entity);
            entity.setParent(this);
        }
    }
}
