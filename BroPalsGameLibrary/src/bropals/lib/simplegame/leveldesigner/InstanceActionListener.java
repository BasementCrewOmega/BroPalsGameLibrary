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
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 *
 * @author Jonathon
 */
public class InstanceActionListener implements ActionListener {
    
    private final PropertyPanel panel;
    private Field field;
    
    public InstanceActionListener(PropertyPanel panel, Field field) {
        this.panel = panel;
        this.field = field;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (panel.getEditing() != null) {
            try {
                JTextField f = (JTextField) e.getSource();
                try {
                    String[] text = f.getText().split(Pattern.quote(" "));
                    String className = text[0];
                    String[] params = new String[text.length-1];
                        for (int i=1; i<text.length; i++) {
                            params[i-1] = text[i];
                        }
                    Class c = Class.forName(className);
                    Object obj = null;
                    
                    Constructor[] constructors = c.getConstructors();
                    //Guess which constructor is being used
                    Constructor using = null;
                    for (Constructor constructor : constructors) {
                        if (constructor.getParameterTypes().length == params.length) {
                            //Use the first one found
                            Class[] typesCheck = constructor.getParameterTypes();
                            for (Class clas : typesCheck) {
                                if ( !(clas.isPrimitive() || clas == String.class) ) {
                                    continue; //Don't use this
                                }
                            }
                            using = constructor;
                            break;
                        }
                    }
                    if (using == null) {
                        throw new IllegalArgumentException("No constructor with " + params.length + " arguments exist!");
                    }
                    Class[] paramTypes = using.getParameterTypes();
                    Object[] paramValues = new Object[paramTypes.length];
                    for (int i=0; i<paramTypes.length; i++) {
                        if (paramTypes[i].isPrimitive()) {
                            paramValues[i] = TypeUtil.parsePrimitive(params[i], paramTypes[i]);
                        } else if (paramTypes[i] == String.class) {
                            paramValues[i] = params[i];
                        }
                    }
                    //Now use the paramValues to create the object
                    obj = using.newInstance(paramValues);
                    field.set(panel.getEditing(), obj);
                } catch(ClassNotFoundException | InstantiationException cnfe) {
                    Object obj = field.get(panel.getEditing());
                    if (obj != null) {
                        f.setText("" + obj.getClass().getName());
                    } else {
                        f.setText("");
                    }
                }
            } catch(Exception except) {
                ErrorLogger.println("Could not set valid for field for some reason: " + except);
            }
        }
    }
}
