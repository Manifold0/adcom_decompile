// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.base;

import android.support.annotation.NonNull;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

final class zao implements zal
{
    private zao() {
    }
    
    @NonNull
    @Override
    public final ExecutorService zaa(final int n, final ThreadFactory threadFactory, final int n2) {
        return Executors.newFixedThreadPool(2, threadFactory);
    }
}
