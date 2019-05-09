// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import java.util.concurrent.ThreadFactory;
import com.google.android.gms.common.util.concurrent.NumberedThreadFactory;
import com.google.android.gms.internal.base.zam;
import java.util.concurrent.ExecutorService;

public final class zabh
{
    private static final ExecutorService zahw;
    
    static {
        zahw = zam.zacv().zaa(2, (ThreadFactory)new NumberedThreadFactory("GAC_Executor"), 9);
    }
    
    public static ExecutorService zabb() {
        return zabh.zahw;
    }
}
