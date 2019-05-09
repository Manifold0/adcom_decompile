// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class AccountType
{
    @KeepForSdk
    public static final String GOOGLE = "com.google";
    private static final String[] zzbs;
    
    static {
        zzbs = new String[] { "com.google", "com.google.work", "cn.google" };
    }
    
    private AccountType() {
    }
}
