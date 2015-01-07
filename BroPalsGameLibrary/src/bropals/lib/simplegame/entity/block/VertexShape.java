/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.entity.block;

import bropals.lib.simplegame.entity.BaseEntity;
import bropals.lib.simplegame.entity.GameWorld;
import bropals.lib.simplegame.math.Vector2D;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A shape that is made up of Vertices
 * @author Kevin Prehn
 */
public class VertexShape extends BaseEntity {
    
    private Vector2D velocity; // per update cycle
    private float angularVelocity; // radians per update cycle
    private float[] center; // [x, y]
    private float[] vertices; // [x1, y2, x2, y2, ... xn, yn] for n vertices.
    
    public VertexShape(GameWorld parent, float[] vertices) {
        super(parent);
        this.vertices = vertices;
        
        float xTotal = 0, yTotal = 0;
        for (int i=0; i<vertices.length; i = i + 2) {
            xTotal += vertices[i];
        }
        for (int i=1; i<vertices.length; i = i + 2) {
            yTotal += vertices[i];
        }
        // find the center point
        center = new float[]{xTotal/(vertices.length/2), yTotal/(vertices.length/2)};
    }

    /**
     * Transform all the vertices with the given 3x3 matrix.
     * The matrix format is:
     * 
     * [0, 1, 2
     *  3, 4, 5
     *  6, 7, 8]
     * 
     * @param m The matrix that will be used to transform the points
     */
    public void transform(float[] m) {
        // idk how to do
    }
    
    @Override
    public void update() {
        float[] transf = {0, 0, 0,
                          0, 0, 0,
                          0, 0, 0};
        // make a transform that moves it with the velocity and rotates
        // it with angular velocity.
        
        transform(transf);
    }

    @Override
    public void render(Object graphicsObj) {
        Graphics2D g2 = (Graphics2D) graphicsObj;
        g2.setColor(Color.BLACK);
        for (int i=2; i<vertices.length; i = i + 2) {
            g2.drawLine((int)vertices[i-2], (int)vertices[i-1], 
                    (int)vertices[i], (int)vertices[i+1]);
        }
        // closing line
        g2.drawLine((int)vertices[0], (int)vertices[1], 
                    (int)vertices[vertices.length-2], 
                    (int)vertices[vertices.length-1]);
    }
}
