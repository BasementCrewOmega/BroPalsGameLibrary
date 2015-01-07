/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.test;

import bropals.lib.simplegame.entity.GameWorld;
import bropals.lib.simplegame.entity.block.BlockEntity;
import bropals.lib.simplegame.entity.block.VertexShape;
import bropals.lib.simplegame.state.GameState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Owner
 */
public class TestState extends GameState {

    GameWorld<VertexShape> world;
    BlockEntity playerBlock;
    int x;
    boolean left, right, up;
    
    @Override
    public void update() {
        /*
        if (left && !right) {
            playerBlock.getVelocity().setX(-5);
        } else if (right && !left) {
            playerBlock.getVelocity().setX(5);
        } else if (!right && !left) {
            playerBlock.getVelocity().setX(0);
        }
        */
        
        world.updateEntities();
    }

    @Override
    public void render(Object graphicsObj) {
        Graphics2D g2 = (Graphics2D)graphicsObj;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, (int)getWindow().getWidth(), 
                (int)getWindow().getHeight());
        
        g2.setColor(Color.BLACK);
        for (VertexShape vs : world.getEntities()) {
            vs.render(graphicsObj);
        }
    }
    
    @Override
    public void onEnter() {
        world = new GameWorld<>();
       
        world.addEntity(new VertexShape(world, 
            new float[]{40, 50, 
                        120, 50, 
                        85, 110}));
    }

    @Override
    public void onExit() {
        System.out.println("Oh no we exitedteteteededed");
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void setKey(int keyCode, boolean pressed) {
        switch(keyCode) {
            case KeyEvent.VK_LEFT: left = pressed; break;
            case KeyEvent.VK_RIGHT: right = pressed; break;
            case KeyEvent.VK_UP: up = pressed; break;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_U) {
            getGameStateRunner().setState(new TestState());
        }
        
        setKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setKey(e.getKeyCode(), false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
