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
    private float[] vs; // [x1, y2, x2, y2, ... xn, yn] for n vertices.
    
    /**
     * Create a new VertexShape, a shape that is defined by vertices.
     * @param parent The parent of the shape
     * @param vertices an array of 2D vertices in the format: 
     *                 [x1, y2, x2, y2, ... xn, yn] for n vertices.
     */
    public VertexShape(GameWorld parent, float[] vertices) {
        super(parent);
        vs = vertices;
        findCenter();
    }

    /**
     * Recalculate the center of the shape.
     */
    protected void findCenter() {
        float xTotal = 0, yTotal = 0;
        for (int i=0; i<vs.length; i = i + 2) {
            xTotal += vs[i];
        }
        for (int i=1; i<vs.length; i = i + 2) {
            yTotal += vs[i];
        }
        // find the center point
        center = new float[]{xTotal/(vs.length/2), yTotal/(vs.length/2)};
    }
    
    /**
     * Transform all the vertices with the given 3x3 matrix.
     * The matrix format is:
     * 
     * [0, 1, 2
     *  3, 4, 5]
     * 
     * @param m The matrix that will be used to transform the points
     */
    public void transform(float[] m) {
        findCenter();
        for (int i=0; i<vs.length; i=i+2) {
            vs[i] -= center[0];
            vs[i+1] -= center[1]; // move to local coordinates
            
            float xNew = (m[0]*vs[i]) + (m[1]*vs[i+1]);
            float yNew = (m[3]*vs[i]) + (m[4]*vs[i+1]);
            vs[i+1] = yNew;
            vs[i] = xNew;
            
            vs[i] += center[0] + m[2];
            vs[i+1] += center[1] + m[5]; // move to local coordinates
        }
        center[0] += m[2];
        center[1] += m[5]; // translate the center
    }
    
    @Override
    public void update() {
        float scaleX = 1.000f;
        float scaleY = 1.000f;
        float rotation = 0.4f;
        float transX = 0f;
        float transY = 0f;
        
        float[] transf = {scaleX*(float)Math.cos(rotation), -scaleX*(float)Math.sin(rotation), transX,
                          scaleY*(float)Math.sin(rotation), scaleY*(float)Math.cos(rotation), transY};
        // make a transform that moves it with the velocity and rotates
        // it with angular velocity.
        
        transform(transf);
    }

    @Override
    public void render(Object graphicsObj) {
        Graphics2D g2 = (Graphics2D) graphicsObj;
        g2.setColor(Color.BLACK);
        for (int i=2; i<vs.length; i = i + 2) {
            g2.drawLine((int)vs[i-2], (int)vs[i-1], 
                    (int)vs[i], (int)vs[i+1]);
        }
        // closing line
        g2.drawLine((int)vs[0], (int)vs[1], 
                    (int)vs[vs.length-2], 
                    (int)vs[vs.length-1]);
        g2.fillOval((int)center[0]-1, (int)center[1]-1, 2, 2);
    }
}
