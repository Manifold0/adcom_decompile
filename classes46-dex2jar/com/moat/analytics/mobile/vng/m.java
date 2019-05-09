// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.util.Log;

class m extends Exception
{
    static void a(final Exception ex) {
        if (w.a().b) {
            Log.e("MoatException", Log.getStackTraceString((Throwable)ex));
        }
    }
}
