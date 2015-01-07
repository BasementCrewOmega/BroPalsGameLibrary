/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.test;

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

    int x;
    
    @Override
    public void update() {
        x++;
    }

    @Override
    public void render(Object graphicsObj) {
        Graphics2D g2 = (Graphics2D)graphicsObj;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, (int)getWindow().getWidth(), 
                (int)getWindow().getHeight());
        
        g2.setColor(Color.BLUE);
        g2.fillRect(x, 50, 10, 10);
    }
    
    @Override
    public void onEnter() {
        x = 0;
    }

    @Override
    public void onExit() {
        System.out.println("Oh no we exitedteteteededed");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
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
