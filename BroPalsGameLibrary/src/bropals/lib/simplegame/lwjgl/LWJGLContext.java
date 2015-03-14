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
package bropals.lib.simplegame.lwjgl;

import bropals.lib.simplegame.LWJGLGameWindow;
import bropals.lib.simplegame.logger.ErrorLogger;
import bropals.lib.simplegame.math.Matrix3D;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUseProgram;
import org.lwjgl.opengl.GL30;

/**
 * The object to use when drawing with LWJGL, and to load textures into OpenGL.
 * Can use this object in combination with the LWJGL commands.
 * <p>
 * This context creates programs and buffers when it is created for its
 * shortcut functions, so some buffer creation by you is unnecessary.
 * <p>
 * LWJGL draws with textures, and not BufferedImages.
 * @author Jonathon
 */
public class LWJGLContext {
    /**
     * A square shape for the vertex buffer.
     */
    private String SQUARE_VERTEX_BUFFER;
        
    private LWJGLGameWindow window;
    
    private HashMap<String, Integer> vertexShaders = new HashMap<>();
    
    private HashMap<String, Integer> fragmentShaders = new HashMap<>();
    
    private HashMap<String, Integer> programs = new HashMap<>();

    private HashMap<String, Integer> vertexBuffers = new HashMap<>();
    
    private HashMap<String, Integer> textures = new HashMap<>();
    
    private HashMap<String, Integer> vertexObjects = new HashMap<>();
    
    /**
     * Makes a context to control rendering to a LWJGLGameWindow.
     * @param window the window to render to.
     */
    public LWJGLContext(LWJGLGameWindow window) {
        this.window = window;
    }
    
    /**
     * Loads and compiles a vertex shader from the given input.
     * @param key the key to store the shader as.
     * @param stream the stream that contains the vertex shader source.
     * @throws java.io.IOException if there is an issue with reading the vertex 
     * shader.
     */
    public void loadVertexShader(String key, InputStream stream) throws IOException {
        String source = "";
        InputStreamReader reader = new InputStreamReader(stream);
        int in;
        while ((in = reader.read()) != -1) {
            source += (char)in;
        }
        int vert = loadShader(source, GL_VERTEX_SHADER);
        fragmentShaders.put(key, vert);
    }
    
    /**
     * Loads and compiles a fragment shader from the given input.
     * @param key the key to store the shader as.
     * @param stream the stream that contains the fragment shader source.
     * @throws java.io.IOException if there is an issue with reading the fragment 
     * shader.
     */
    public void loadFragmentShader(String key, InputStream stream) throws IOException {
        String source = "";
        InputStreamReader reader = new InputStreamReader(stream);
        int in;
        while ((in = reader.read()) != -1) {
            source += (char)in;
        }
        int frag = loadShader(source, GL_FRAGMENT_SHADER);
        fragmentShaders.put(key, frag);
    }
    
    /**
     * Loads and compiles shader.
     * @param source the source for the shader
     * @param type the type of shader
     * @return the compiled shader, or -1 if there was an error in
     * checking.
     */
    private int loadShader(String source, int type) {
        int shader = glCreateShader(type);
        glShaderSource(shader, source);
        glCompileShader(shader);
        int status;
        status = glGetShaderi(shader, GL_COMPILE_STATUS);
        if (status != GL_TRUE) {
            ErrorLogger.println("Could not compile shader: \n" + glGetShaderInfoLog(shader));
            return -1;
        } else {
            return shader;
        }
    }
    
    /**
     * Creates a program from two stored shaders.
     * @param key the key to store the program as.
     * @param vertexShader the key of the vertex shader to use.
     * @param fragmentShader the key of the fragment shader to use.
     */
    public void createProgram(String key, String vertexShader, String fragmentShader) {
        int program;
        program = glCreateProgram();
        glAttachShader(program, getVertexShader(key));
        glAttachShader(program, getFragmentShader(fragmentShader));
    }
    
    /**
     * Buffers float data and gives it a key to reference it by.
     * @param key the key of the buffer.
     * @param data the data to buffer.
     */
    public void bufferVertexDataFloat(String key, final float[] data) {
        
    }
    
    /**
     * Buffers int data and gives it a key to reference it by.
     * @param key the key of the buffer.
     * @param data the data to buffer.
     */
    public void bufferVertexDataInt(String key, final int[] data) {
        
    }
    
    /**
     * Loads a texture from an input stream that points to where it is located,
     * then gives the loaded texture a key to reference it by.
     * @param key the key that is the texture.
     * @param stream the input stream that points to the texture.
     */
    public void loadTexture(String key, InputStream stream) {
        
    }
    
    /**
     * Creates a new vertex object that stores a vertex data format.
     * @param key the key of the vertex object that was created.
     */
    public void createVertexObject(String key) {
        vertexObjects.put(key, GL30.glGenVertexArrays());
    }
    
    /**
     * Gets the OpenGL name of the specified vertex shader.
     * @param key the key of the vertex shader to get
     * @return the vertex shader, or <code>null</code> if it doesn't exist.
     */
    public int getVertexShader(String key) {
        return vertexShaders.get(key);
    }
    
    /**
     * Gets the OpenGL name of the specified fragment shader.
     * @param key the key of the fragment shader to get
     * @return the fragment shader, or <code>null</code> if it doesn't exist.
     */
    public int getFragmentShader(String key) {
        return fragmentShaders.get(key);
    }
    
    /**
     * Gets the OpenGL name of the specified program.
     * @param key the key of the program to get
     * @return the program, or <code>null</code> if it doesn't exist.
     */
    public int getProgram(String key) {
        return programs.get(key);
    }
    
    /**
     * Gets the OpenGL name of the specified vertex buffer.
     * @param key the key of the vertex buffer to get.
     * @return the vertex buffer, or <code>null</code> if it doesn't exist. 
     */
    public int getVertexBuffer(String key) {
        return vertexBuffers.get(key);
    }
    
    /**
     * Tell OpenGL to use the specified program, if it exists.
     * @param programKey the program to use.
     */
    public void useProgram(String programKey) {
        glUseProgram(getProgram(programKey));
    }
    
    /**
     * Gets the window associated with this context.
     * @return the window associated with this context.
     */
    public LWJGLGameWindow getWindow() {
        return window;
    }
}
