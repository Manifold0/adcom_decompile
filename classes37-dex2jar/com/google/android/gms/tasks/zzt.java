// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzt implements Executor
{
    @Override
    public final void execute(@NonNull final Runnable runnable) {
        runnable.run();
    }
}
