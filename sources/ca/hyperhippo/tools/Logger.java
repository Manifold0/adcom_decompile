package ca.hyperhippo.tools;

import android.util.Log;

public class Logger {
    public static final String TAG = "HyperHippo";

    public static void Log(int level, String message) {
        switch (level + 1) {
            case 2:
                Log.v(TAG, message);
                return;
            case 3:
                Log.d(TAG, message);
                return;
            case 4:
                Log.i(TAG, message);
                return;
            case 5:
                Log.w(TAG, message);
                return;
            case 6:
                Log.e(TAG, message);
                return;
            default:
                Log.v(TAG, message);
                return;
        }
    }

    public static void Log(int level, String message, String name) {
        Log(level, String.format("[%1$s] %2$s", new Object[]{name, message}));
    }
}
