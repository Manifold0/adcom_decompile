// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.internal.IAccountAccessor;
import android.support.annotation.WorkerThread;

@WorkerThread
public interface zach
{
    void zaa(final IAccountAccessor p0, final Set<Scope> p1);
    
    void zag(final ConnectionResult p0);
}
