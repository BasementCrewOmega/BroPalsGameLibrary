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

import bropals.lib.simplegame.logger.ErrorLogger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import javax.swing.JTextField;

/**
 * Short action listener
 * @author Jonathon
 */
class ShortActionListener implements ActionListener {
    
    private final PropertyPanel panel;
    private Field field;

    public ShortActionListener(PropertyPanel panel, Field field) {
        this.panel = panel;
        this.field = field;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (panel.getEditing() != null) {
            try {
                JTextField f = (JTextField) e.getSource();
                String text = f.getText();
                try {
                    short s = Short.parseShort(text);
                    field.set(panel.getEditing(), s);
                } catch(NumberFormatException nfe) {
                    f.setText("" + field.getShort(panel.getEditing()));
                }
            } catch(Exception except) {
                ErrorLogger.println("Could not set valid for field for some reason: " + except);
            }
        }
    }
}
