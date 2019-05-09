// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

interface zzq<TResult>
{
    void cancel();
    
    void onComplete(@NonNull final Task<TResult> p0);
}
