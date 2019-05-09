// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;
import java.util.Map;
import com.google.android.gms.common.internal.ShowFirstParty;

@ShowFirstParty
public abstract class zac
{
    private static final Object sLock;
    @GuardedBy("sLock")
    private static final Map<Object, zac> zack;
    
    static {
        zack = new WeakHashMap<Object, zac>();
        sLock = new Object();
    }
    
    public abstract void remove(final int p0);
}
