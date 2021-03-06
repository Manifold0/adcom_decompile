// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Scope;
import java.util.List;
import com.google.android.gms.common.annotation.KeepForSdk;

public interface GoogleSignInOptionsExtension
{
    @KeepForSdk
    public static final int FITNESS = 3;
    @KeepForSdk
    public static final int GAMES = 1;
    
    @KeepForSdk
    int getExtensionType();
    
    @Nullable
    @KeepForSdk
    List<Scope> getImpliedScopes();
    
    Bundle toBundle();
}
