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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.HashMap;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL45.*;

/**
 * The object to use when drawing with LWJGL, and to load textureObjects into
 * OpenGL. Can use this object in combination with the LWJGL commands.
 * 
 * @author Jonathon
 */
public class LWJGLContext {
    
    private LWJGLGameWindow window;

    private HashMap<String, Integer> vertexShaders = new HashMap<>();

    private HashMap<String, Integer> fragmentShaders = new HashMap<>();

    private HashMap<String, Integer> programs = new HashMap<>();

    private HashMap<String, Integer> buffers = new HashMap<>();

    private HashMap<String, Integer> textureObjects = new HashMap<>();

    private HashMap<String, Integer> vertexObjects = new HashMap<>();

    /**
     * Makes a context to control rendering to a LWJGLGameWindow.
     *
     * @param window the window to render to.
     */
    public LWJGLContext(LWJGLGameWindow window) {
        this.window = window;
    }

    /**
     * Loads and compiles a vertex shader from the given input.
     *
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
            source += (char) in;
        }
        int vert = loadShader(source, GL_VERTEX_SHADER);
        fragmentShaders.put(key, vert);
    }

    /**
     * Loads and compiles a fragment shader from the given input.
     *
     * @param key the key to store the shader as.
     * @param stream the stream that contains the fragment shader source.
     * @throws java.io.IOException if there is an issue with reading the
     * fragment shader.
     */
    public void loadFragmentShader(String key, InputStream stream) throws IOException {
        String source = "";
        InputStreamReader reader = new InputStreamReader(stream);
        int in;
        while ((in = reader.read()) != -1) {
            source += (char) in;
        }
        int frag = loadShader(source, GL_FRAGMENT_SHADER);
        fragmentShaders.put(key, frag);
    }
    
    /**
     * Loads and compiles a vertex shader from the given source.
     *
     * @param key the key to store the shader as.
     * @param source the source of the shader.
     */
    public void loadVertexShader(String key, String source) {
        int vert = loadShader(source, GL_VERTEX_SHADER);
        vertexShaders.put(key, vert);
    }
    
    /**
     * Loads and compiles a fragment shader from the given source.
     *
     * @param key the key to store the shader as.
     * @param source the source of the shader.
     */
    public void loadFragmentShader(String key, String source) {
        int frag = loadShader(source, GL_FRAGMENT_SHADER);
        fragmentShaders.put(key, frag);
    }

    /**
     * Loads and compiles shader.
     *
     * @param source the source for the shader
     * @param type the type of shader
     * @return the compiled shader, or -1 if there was an error in checking.
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
     *
     * @param key the key to store the program as.
     * @param vertexShader the key of the vertex shader to use.
     * @param fragmentShader the key of the fragment shader to use.
     */
    public void createProgram(String key, String vertexShader, String fragmentShader) {
        int program;
        program = glCreateProgram();
        glAttachShader(program, getVertexShader(vertexShader));
        glAttachShader(program, getFragmentShader(fragmentShader));
        glLinkProgram(program);
        int status;
        status = glGetProgrami(program, GL_LINK_STATUS);
        if (status != GL_TRUE) {
            ErrorLogger.println("Error while linking program:\n" + glGetProgramInfoLog(program));
        }
        programs.put(key, program);
    }

    /**
     * Buffers float vertex data and gives it a key to reference it by.
     * This method will bind the newly created buffer to GL_ARRAY_BUFFER.
     * 
     * @param key the key of the buffer.
     * @param data the data to buffer.
     * @param usage the usage enum for the buffer.
     */
    public void bufferVertexDataFloat(String key, final float[] data, int usage) {
        int capacity = data.length*(Float.SIZE/8);
        ByteBuffer fBuffer = BufferUtils.createByteBuffer(capacity);
        for (int i=0; i<data.length; i++) {
            fBuffer.putFloat(data[i]);
        }
        fBuffer.rewind();
        bufferVertexData(key, fBuffer, capacity, usage);
    }

    /**
     * Buffers some byte data into a buffer, then gives the buffer a name to
     * reference it by.
     * This method will bind the newly created buffer to GL_ARRAY_BUFFER.
     * 
     * @param key the key of the buffer.
     * @param data the data to buffer.
     * @param bytes the number of bytes in the buffer
     * @param usage the usage enum for the buffer.
     */
    public void bufferVertexData(String key, ByteBuffer data, int bytes, int usage) {
        int buffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, buffer);
        glBufferData(GL_ARRAY_BUFFER, bytes, data, usage);
        buffers.put(key, buffer);
    }
    
    /**
     * Binds the requested buffer to the requested target.
     * @param target the target to bind the buffer to
     * @param key the key of the buffer to bind.
     */
    public void bindBufferTo(int target, String key) {
        glBindBuffer(target, buffers.get(key));
    }
    
    /**
     * Unbinds the buffer at the specified target.
     * @param target the target to unbind the buffer from.
     */
    public void unbindBuffer(int target) {
        glBindBuffer(target, 0);
    }
    
    /**
     * Puts the image data into a texture map, then binds the texture map to the
     * active texture unit, and referring to the created texture object with the
     * given key.
     * <p>
     * The image must be square, with its width and height a power of 2. This
     * method does not check for the power of 2 property, but it does
     * check to see if it is square.
     *
     * @param key the key that is the texture.
     * @param image the image data to store in a new texture object.
     * @param bytesPerPixel the number of bytes in each pixel. This is 4 for an
     * RGBA image, and 3 for an RGB image.
     */
    public void loadTextureObject(String key, BufferedImage image, int bytesPerPixel) {
        if (image.getWidth() != image.getHeight() ) {
            ErrorLogger.println("The given texture is not square: not creating a texture object for it.");
            return;
        }
        
        //Thanks StackOverflow for this code: http://stackoverflow.com/questions/10801016/lwjgl-textures-and-strings

        //Create the buffer to hold the pixel data
        ByteBuffer byteBuffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * bytesPerPixel);
        
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixel = image.getRGB(x, y);
                //This 4 byte integer has each of the 4 (or 3) values of
                //RGB(A) in it, so those values need to be extracted with masking.
                //Write RGB
                
                //I must admit that this part is something that I don't understand too much.
                //Why is the 0xFF mask there?
                
                //From this code, my understanding is that an RGBA pixel is stored as:
                // [Alpha byte] [Red byte] [Green byte] [Blue byte]
                byteBuffer.put((byte)( (pixel >> 16) & 0xFF )); //Red component
                byteBuffer.put((byte)( (pixel >> 8) & 0xFF )); //Green component
                byteBuffer.put((byte)( pixel & 0xFF )); //Blue component
                if (bytesPerPixel == 4) {
                byteBuffer.put((byte)( (pixel >> 24) & 0xFF )); //Green component
                } else if (bytesPerPixel != 3)  {
                    ErrorLogger.println("Only 3 bytes per pixel or 4 bytes per pixel is supported for texture loading.");
                }
            }
        }
        //Don't forget this
        byteBuffer.flip();
        
        int texture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texture);
        //It is now bound to the GL_TEXTURE_2D point
        
        //Setup wrap mode for the texture coordinates.
        //In form (s, t) ?
        glTextureParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTextureParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        
        //Setup texture scaling filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        
        //Now fill the texture object with data
        glTexImage2D(
                GL_TEXTURE_2D, //target
                0, //level
                (bytesPerPixel==3 ? GL_RGB8 : GL_RGBA8), //Internal pixel format
                image.getWidth(),
                image.getHeight(),
                0, //border
                (bytesPerPixel==3 ? GL_RGB : GL_RGBA), //External pixel format (less about byte count)
                GL_UNSIGNED_BYTE, //Data type of the color elements
                byteBuffer //Buffer that contains the data to fill the texture with
            );
        //Now give the filled texture object a name to reference it by
        textureObjects.put(key, texture);
    }

    /**
     * Creates a new vertex object that stores a vertex data format.
     *
     * @param key the key of the vertex object that was created.
     */
    public void createVertexObject(String key) {
        vertexObjects.put(key, glGenVertexArrays());
    }

    /**
     * Gets the OpenGL name of the specified vertex shader.
     *
     * @param key the key of the vertex shader to get
     * @return the vertex shader, or <code>-1</code> if it doesn't exist.
     */
    public int getVertexShader(String key) {
        return (vertexShaders.get(key)!=null ? vertexShaders.get(key) : -1);
    }

    /**
     * Gets the OpenGL name of the specified fragment shader.
     *
     * @param key the key of the fragment shader to get
     * @return the fragment shader, or <code>-1</code> if it doesn't exist.
     */
    public int getFragmentShader(String key) {
        return (fragmentShaders.get(key)!=null ? fragmentShaders.get(key) : -1);
    }

    /**
     * Gets the OpenGL name of the specified program.
     *
     * @param key the key of the program to get
     * @return the program, or <code>-1</code> if it doesn't exist.
     */
    public int getProgram(String key) {
        return (programs.get(key)!=null ? programs.get(key) : -1);
    }

    /**
     * Gets the OpenGL name of the specified buffer.
     *
     * @param key the key of the buffer to get.
     * @return the buffer, or <code>-1</code> if it doesn't exist.
     */
    public int getBuffer(String key) {
        return (buffers.get(key)!=null ? buffers.get(key) : -1);
    }

    /**
     * Sets the active texture unit. If it is out of bounds of the available
     * texture units, then nothing happens.
     * <p>
     * Must use the enum GL_TEXTURE<code>i</code> where <code>i</code> is the
     * texture unit number.
     *
     * @param unit the texture unit to set as active.
     */
    public void setActiveTextureUnit(int unit) {
        glActiveTexture(unit);
    }

    /**
     * Gets the active texture unit.
     * <p>
     * Will be the enum GL_TEXTURE<code>i</code> where <code>i</code> is the
     * texture unit number.
     *
     * @return the active texture unit.
     */
    public int getActiveTextureUnit() {
        return glGetInteger(GL_ACTIVE_TEXTURE);
    }

    /**
     * Gets the texture object with the specified key.
     * @param key the key of the texture object to get.
     * @return the texture object, or <code>-1</code> if it doesn't exist.
     */
    public int getTextureObject(String key) {
        return (textureObjects.get(key)!=null ? textureObjects.get(key) : -1);
    }
    
    /**
     * Tell OpenGL to use the specified program, if it exists.
     *
     * @param programKey the program to use.
     */
    public void useProgram(String programKey) {
        glUseProgram(getProgram(programKey));
    }
    
    /**
     * Calls <code>glVertexAttribPointer</code> so that OpenGL knows the
     * format of the vertex data that is being handed to it.
     * @param variableName the name of the variable that is being specified in the shader.
     * @param program the program that contains the variable.
     * @param size the number of components per vertex.
     * @param type the data type of each element in the array.
     * @param normalized indicates tat the vertex data should be normalized.
     * @param stride the byte offset between consecutive elements in the array.
     * @param pointer the offset from the start of the current buffer object.
     */
    public void vertexAttribPointer(String variableName, String program, 
            int size, int type, boolean normalized, int stride, long pointer) {
        glVertexAttribPointer(
            glGetUniformLocation(getProgram(program), variableName),
            size, type, normalized, stride, pointer
        );
    }
    
    /**
     * Enables the vertex attribute associated with the variable name in the
     * specified program.
     * @param variableName the variable name of the attribute.
     * @param program the program that contains the variable.
     */
    public void enableVertexAttribArray(String variableName, String program) {
        glEnableVertexAttribArray(glGetAttribLocation(getProgram(program), variableName));
    }

    /**
     * Disables the vertex attribute associated with the variable name in the
     * specified program.
     * @param variableName the variable name of the attribute.
     * @param program the program that contains the variable.
     */
    public void disableVertexAttribArray(String variableName, String program) {
        glDisableVertexAttribArray(glGetAttribLocation(getProgram(program), variableName));
    }
    
    /**
     * Gets the window associated with this context.
     *
     * @return the window associated with this context.
     */
    public LWJGLGameWindow getWindow() {
        return window;
    }
}
