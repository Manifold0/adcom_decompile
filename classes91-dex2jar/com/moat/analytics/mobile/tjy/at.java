// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import java.util.concurrent.ThreadFactory;

class at implements ThreadFactory
{
    final /* synthetic */ as a;
    
    at(final as a) {
        this.a = a;
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        final Thread thread = new Thread(runnable, "MoatStatus");
        thread.setDaemon(true);
        return thread;
    }
}
