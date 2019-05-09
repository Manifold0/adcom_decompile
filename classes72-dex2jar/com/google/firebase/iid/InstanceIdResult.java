// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.support.annotation.NonNull;

public interface InstanceIdResult
{
    @NonNull
    String getId();
    
    @NonNull
    String getToken();
}
