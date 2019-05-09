// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

final class zzaog implements Executor
{
    @Override
    public final void execute(final Runnable runnable) {
        runnable.run();
    }
}
