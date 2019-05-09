// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public abstract class CancellationToken
{
    public abstract boolean isCancellationRequested();
    
    public abstract CancellationToken onCanceledRequested(@NonNull final OnTokenCanceledListener p0);
}
