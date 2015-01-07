/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.test;

import bropals.lib.simplegame.entity.GameWorld;
import bropals.lib.simplegame.entity.block.BlockEntity;
import bropals.lib.simplegame.logger.InfoLogger;
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

    GameWorld<BlockEntity> world;
    BlockEntity playerBlock;
    int x;
    boolean left, right, up;
    
    @Override
    public void update() {
        if (left && !right) {
            playerBlock.getVelocity().setX(-5);
        } else if (right && !left) {
            playerBlock.getVelocity().setX(5);
        } else if (!right && !left) {
            playerBlock.getVelocity().setX(0);
        }
        
        world.updateEntities();
    }

    @Override
    public void render(Object graphicsObj) {
        Graphics2D g2 = (Graphics2D)graphicsObj;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, (int)getWindow().getWidth(), 
                (int)getWindow().getHeight());
        
        g2.setColor(Color.BLACK);
        for (BlockEntity be : world.getEntities()) {
            g2.fillRect((int)be.getX(),
                    (int)be.getY(),
                    (int)be.getWidth(),
                    (int)be.getHeight());
        }
    }
    
    @Override
    public void onEnter() {
        world = new GameWorld<>();
        playerBlock = new BlockEntity(world, 150, 50, 20, 20, false);
        world.addEntity(new BlockEntity(world, 100, 200, 200, 30, true));
        world.addEntity(playerBlock);
        
        // gravity!
        for (BlockEntity be : world.getEntities()) {
            be.getAcceleration().setValues(0, 1);
        }
    }

    @Override
    public void onExit() {
        InfoLogger.println("Oh no we exitedteteteededed");
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
