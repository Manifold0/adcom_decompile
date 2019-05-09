// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import java.util.Queue;
import java.util.Iterator;
import android.util.Log;

class au implements ax
{
    final /* synthetic */ as a;
    
    au(final as a) {
        this.a = a;
    }
    
    @Override
    public void a(final ar ar) {
        if (as.c != ar) {
            while (true) {
                while (true) {
                    aq aq = null;
                    Label_0096: {
                        synchronized (as.b) {
                            if (ar == ar.b && as.d) {
                                Log.d("MoatOnOff", "Moat enabled - Version 1.7.10");
                            }
                            as.c = ar;
                            final Iterator iterator = as.b.iterator();
                            while (iterator.hasNext()) {
                                aq = iterator.next();
                                if (ar != ar.b) {
                                    break Label_0096;
                                }
                                aq.a();
                                iterator.remove();
                            }
                            break;
                        }
                    }
                    aq.b();
                    continue;
                }
            }
        }
        // monitorexit(queue)
        this.a.g();
    }
}
