package com.unity3d.services.ar.view;

import android.opengl.GLES20;
import com.unity3d.services.core.log.DeviceLog;

public class ShaderLoader {
    public static int load(String code, int type) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, code);
        GLES20.glCompileShader(shader);
        int[] status = new int[1];
        GLES20.glGetShaderiv(shader, 35713, status, 0);
        if (status[0] == 1) {
            return shader;
        }
        DeviceLog.error("Error compiling shader: " + GLES20.glGetShaderInfoLog(shader));
        GLES20.glDeleteShader(shader);
        return 0;
    }

    public static boolean checkGLError(String label) {
        int lastError = 0;
        while (true) {
            int error = GLES20.glGetError();
            if (error == 0) {
                break;
            }
            DeviceLog.error(label + ": glError " + error);
            lastError = error;
        }
        return lastError != 0;
    }
}
