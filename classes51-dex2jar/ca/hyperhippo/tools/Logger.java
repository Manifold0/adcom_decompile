// 
// Decompiled by Procyon v0.5.34
// 

package ca.hyperhippo.tools;

import android.util.Log;

public class Logger
{
    public static final String TAG = "HyperHippo";
    
    public static void Log(final int n, final String s) {
        switch (n + 1) {
            default: {
                Log.v("HyperHippo", s);
            }
            case 6: {
                Log.e("HyperHippo", s);
            }
            case 5: {
                Log.w("HyperHippo", s);
            }
            case 4: {
                Log.i("HyperHippo", s);
            }
            case 3: {
                Log.d("HyperHippo", s);
            }
            case 2: {
                Log.v("HyperHippo", s);
            }
        }
    }
    
    public static void Log(final int n, final String s, final String s2) {
        Log(n, String.format("[%1$s] %2$s", s2, s));
    }
}
