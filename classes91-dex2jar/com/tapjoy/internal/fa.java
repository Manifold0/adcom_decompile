// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Observer;
import com.tapjoy.TJPlacementManager;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import android.content.Context;

public final class fa extends gn
{
    private final fc b;
    
    static {
        gn.a(new fa());
    }
    
    private fa() {
        this.b = new fc() {
            @Override
            protected final boolean a() {
                return true;
            }
            
            @Override
            protected final boolean a(final Observer observer) {
                if (TapjoyConnectCore.isViewOpen()) {
                    TJPlacementManager.dismissContentShowing(true);
                }
                return super.a(observer);
            }
        };
    }
    
    public static void a() {
    }
    
    protected final void a(final a a) {
        this.b.c(a);
    }
    
    protected final boolean b() {
        return this.b.b != null;
    }
}
