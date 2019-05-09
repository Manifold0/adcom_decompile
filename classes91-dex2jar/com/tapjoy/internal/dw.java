// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import com.tapjoy.TJConnectListener;
import java.util.Hashtable;
import android.content.Context;

final class dw extends dv
{
    private final fe b;
    
    dw() {
        this.b = new fe() {
            protected final boolean a(final Context context, final String s, final Hashtable hashtable, final TJConnectListener tjConnectListener) {
                return dv.this.a(context, s, hashtable, tjConnectListener);
            }
        };
    }
    
    @Override
    public final boolean a(final Context context, final String s, final Hashtable hashtable, final TJConnectListener tjConnectListener) {
        return this.b.b(context, s, hashtable, tjConnectListener);
    }
}
