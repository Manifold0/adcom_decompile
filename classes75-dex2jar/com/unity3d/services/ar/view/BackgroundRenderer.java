// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ar.view;

import java.nio.Buffer;
import com.google.ar.core.Frame;
import android.annotation.TargetApi;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import android.opengl.GLES20;
import java.nio.FloatBuffer;

public class BackgroundRenderer
{
    private static final int COORDS_PER_VERTEX = 3;
    private static final int FLOAT_SIZE = 4;
    private static final String FRAGMENT_SHADER = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 v_TexCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, v_TexCoord);\n}";
    private static final float[] QUAD_COORDS;
    private static final float[] QUAD_TEXCOORDS;
    private static final int TEXCOORDS_PER_VERTEX = 2;
    private static final String VERTEX_SHADER = "attribute vec4 a_Position;\nattribute vec2 a_TexCoord;\n\nvarying vec2 v_TexCoord;\n\nvoid main() {\n   gl_Position = a_Position;\n   v_TexCoord = a_TexCoord;\n}";
    private int quadPositionParam;
    private int quadProgram;
    private FloatBuffer quadTexCoord;
    private int quadTexCoordParam;
    private FloatBuffer quadTexCoordTransformed;
    private FloatBuffer quadVertices;
    private int textureId;
    
    static {
        QUAD_COORDS = new float[] { -1.0f, -1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f };
        QUAD_TEXCOORDS = new float[] { 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f };
    }
    
    BackgroundRenderer() {
        this.textureId = -1;
    }
    
    @TargetApi(15)
    void createOnGlThread() {
        final int[] array = { 0 };
        GLES20.glGenTextures(1, array, 0);
        GLES20.glBindTexture(36197, this.textureId = array[0]);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        GLES20.glTexParameteri(36197, 10241, 9728);
        GLES20.glTexParameteri(36197, 10240, 9728);
        if (4 != BackgroundRenderer.QUAD_COORDS.length / 3) {
            throw new RuntimeException("Unexpected number of vertices in BackgroundRenderer.");
        }
        final ByteBuffer allocateDirect = ByteBuffer.allocateDirect(BackgroundRenderer.QUAD_COORDS.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        (this.quadVertices = allocateDirect.asFloatBuffer()).put(BackgroundRenderer.QUAD_COORDS);
        this.quadVertices.position(0);
        final ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(32);
        allocateDirect2.order(ByteOrder.nativeOrder());
        (this.quadTexCoord = allocateDirect2.asFloatBuffer()).put(BackgroundRenderer.QUAD_TEXCOORDS);
        this.quadTexCoord.position(0);
        final ByteBuffer allocateDirect3 = ByteBuffer.allocateDirect(32);
        allocateDirect3.order(ByteOrder.nativeOrder());
        this.quadTexCoordTransformed = allocateDirect3.asFloatBuffer();
        final int load = ShaderLoader.load("attribute vec4 a_Position;\nattribute vec2 a_TexCoord;\n\nvarying vec2 v_TexCoord;\n\nvoid main() {\n   gl_Position = a_Position;\n   v_TexCoord = a_TexCoord;\n}", 35633);
        final int load2 = ShaderLoader.load("#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 v_TexCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, v_TexCoord);\n}", 35632);
        GLES20.glAttachShader(this.quadProgram = GLES20.glCreateProgram(), load);
        GLES20.glAttachShader(this.quadProgram, load2);
        GLES20.glLinkProgram(this.quadProgram);
        GLES20.glUseProgram(this.quadProgram);
        ShaderLoader.checkGLError("Program creation");
        this.quadPositionParam = GLES20.glGetAttribLocation(this.quadProgram, "a_Position");
        this.quadTexCoordParam = GLES20.glGetAttribLocation(this.quadProgram, "a_TexCoord");
        ShaderLoader.checkGLError("Program parameters");
    }
    
    @TargetApi(15)
    void draw(final Frame frame) {
        if (frame.hasDisplayGeometryChanged()) {
            frame.transformDisplayUvCoords(this.quadTexCoord, this.quadTexCoordTransformed);
        }
        GLES20.glDisable(2929);
        GLES20.glDepthMask(false);
        GLES20.glBindTexture(36197, this.textureId);
        GLES20.glUseProgram(this.quadProgram);
        GLES20.glVertexAttribPointer(this.quadPositionParam, 3, 5126, false, 0, (Buffer)this.quadVertices);
        GLES20.glVertexAttribPointer(this.quadTexCoordParam, 2, 5126, false, 0, (Buffer)this.quadTexCoordTransformed);
        GLES20.glEnableVertexAttribArray(this.quadPositionParam);
        GLES20.glEnableVertexAttribArray(this.quadTexCoordParam);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(this.quadPositionParam);
        GLES20.glDisableVertexAttribArray(this.quadTexCoordParam);
        GLES20.glDepthMask(true);
        GLES20.glEnable(2929);
    }
    
    int getTextureId() {
        return this.textureId;
    }
}
