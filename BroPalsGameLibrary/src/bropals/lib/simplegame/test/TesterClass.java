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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 **/
package bropals.lib.simplegame.test;

import bropals.lib.simplegame.logger.InfoLogger;
import bropals.lib.simplegame.util.AssetLoader;
import bropals.lib.simplegame.util.DataTable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class TesterClass {

    public static void main(String[] args) {
        AssetLoader ldr = new AssetLoader();
        DataTable data = null;
        
        try {
            data = ldr.loadDataTable("test_files/data.txt", new Class[] { Integer.class, Boolean.class }, 1);
        } catch (IOException | IllegalStateException ex) {
            InfoLogger.println("Could not read data table: " + ex);
        }
        for (int i=0; i<data.getRowCount(); i++) {
            Object[] row = data.getRow(i);
            InfoLogger.println("" + (int)data.getElement(i, 0) + ", " + (boolean)data.getElement(i, 1));
        }
    }
}
