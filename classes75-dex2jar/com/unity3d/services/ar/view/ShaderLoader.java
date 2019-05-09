// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ar.view;

import com.unity3d.services.core.log.DeviceLog;
import android.opengl.GLES20;

public class ShaderLoader
{
    public static boolean checkGLError(final String s) {
        int n = 0;
        while (true) {
            final int glGetError = GLES20.glGetError();
            if (glGetError == 0) {
                break;
            }
            DeviceLog.error(s + ": glError " + glGetError);
            n = glGetError;
        }
        return n != 0;
    }
    
    public static int load(final String s, int n) {
        final int glCreateShader = GLES20.glCreateShader(n);
        GLES20.glShaderSource(glCreateShader, s);
        GLES20.glCompileShader(glCreateShader);
        final int[] array = { 0 };
        GLES20.glGetShaderiv(glCreateShader, 35713, array, 0);
        n = glCreateShader;
        if (array[0] != 1) {
            DeviceLog.error("Error compiling shader: " + GLES20.glGetShaderInfoLog(glCreateShader));
            GLES20.glDeleteShader(glCreateShader);
            n = 0;
        }
        return n;
    }
}
