/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bropals.lib.simplegame.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * A GUI element that displays some text. When word wrap is enabled, the 
 * GuiText will attempt to draw all text inside of its box. If there is not
 * enough space, then it will continue drawing at the width of the GuiText.
 * 
 * @author Jonathon
 */
public class GuiText extends GuiElement {

    private String text;
    private Font font;
    private Color textColor;
    private boolean wordWrap;
    private String[] lines;
    
    /**
     * Creates a GuiText object to display text. Word wrap mode is on
     * by default; default color is Color.BLACK.
     * @param text the text to display
     * @param x the top-left corner's X position of the GuiText's box
     * @param y the top-left corner's Y position of the GuiText's box
     * @param w the width of the GuiText box
     * @param h the height of the GuiText
     */
    public GuiText(String text, int x, int y, int w, int h) {
        super(x, y, w, h);
        setText(text);
        font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
        wordWrap = true;
        textColor = Color.BLACK;
    }
    
    /**
     * Sets the color of this GuiText's text.
     * @param color the color of this GuiText's text.
     */
    public void setTextColor(Color color) {
        this.textColor=color;
    }
    
    /**
     * Gets the color of this GuiText's text.
     * @return the color of this GuiText's text.
     */
    public Color getTextColor() {
        return textColor;
    }
    
    /**
     * Gets the word wrap preference of this GuiText. Returns true if it
     * is wrapping its text, false if it is not.
     * @return the word wrap preference.
     */
    public boolean isWordWrapping() {
        return wordWrap;
    }
    
    /**
     * Sets the word wrap state of this GuiText.
     * @param wordWrap the word wrap state.
     */
    public void setWordWrap(boolean wordWrap) {
        this.wordWrap = wordWrap;
        lines = null;
    }
        
    private void splitIntoLines(FontMetrics metrics) {
        if (this.wordWrap) {
            ArrayList<String> l = new ArrayList();
            String[] splitText = this.text.split(Pattern.quote(" "));
            /* Split into multiple lines */
            int lineWidth = 0;
            int wordLength;
            int spaceWidth = metrics.charWidth(' ');
            String currentLine = "";
            for (int word = 0; word < splitText.length; word++) {
                wordLength = metrics.stringWidth(splitText[word]);
                if ( wordLength + lineWidth > getWidth() ) {
                    l.add(currentLine);
                    lineWidth = (spaceWidth + wordLength);
                    currentLine = (splitText[word] + ' ');
                } else {
                    lineWidth += (spaceWidth + wordLength);
                    currentLine += (splitText[word] + ' ');
                }
            }
            lines = (String[])l.toArray(new String[0]);
        }
    }
    
    /**
     * Sets the text for this GuiText to display.
     * @param text the text to display.
     */
    public void setText(String text) {
        this.text=text;
        lines = null;
    }
    
    /**
     * Gets the text that this GuiText is displaying.
     * @return the text that this GuiText is displaying.
     */
    public String getText() {
        return text;
    }
    
    /**
     * Gets the Font that this GuiText is using
     * @return the font that this GuiText is using
     */
    public Font getFont() {
        return font;
    }
    
    /**
     * Sets the Font that this GuiText uses.
     * @param font the font that this GuiText uses.
     */
    public void setFont(Font font) {
        this.font=font;
    }

    @Override
    public void render(Object graphicsObject) {
        Graphics g = (Graphics)graphicsObject;
        g.setFont(font);
        g.setColor(textColor);
        FontMetrics fm = g.getFontMetrics();
        int xLoc = getX();
        int yLoc = getY() + fm.getHeight();
        if (wordWrap) {
            if (lines == null) {
                splitIntoLines(fm);
            }
            for (String line : lines) {
                g.drawString(line, xLoc, yLoc);
                yLoc += fm.getHeight();
            }
        } else {
            g.drawString(getText(), xLoc, yLoc);
        }
    }
}
