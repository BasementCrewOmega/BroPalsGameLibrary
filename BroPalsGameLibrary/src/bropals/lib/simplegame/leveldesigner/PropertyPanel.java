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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package bropals.lib.simplegame.leveldesigner;

import javax.swing.JPanel;

/**
 * An extended JPanel to handle property editing with JTextFields.
 * @author Jonathon
 * @param <T> the type of object that this PropertyPanel is editing.
 */
public class PropertyPanel<T> extends JPanel {
    
    private T editing = null;

    /**
     * Gets the object that this PropertyPanel is currently editing.
     * @return the object that this PropertyPanel is editing.
     */
    public T getEditing() {
        return editing;
    }

    /**
     * Sets the object that this PropertyPanel will edit.
     * @param editing the object for this PropertyPanel to edit.
     */
    public void setEditing(T editing) {
        this.editing = editing;
    }
    
    
}
