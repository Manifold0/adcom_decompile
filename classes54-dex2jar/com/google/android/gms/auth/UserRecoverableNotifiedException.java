// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth;

import com.google.android.gms.common.util.VisibleForTesting;

public class UserRecoverableNotifiedException extends GoogleAuthException
{
    @VisibleForTesting
    public UserRecoverableNotifiedException(final String s) {
        super(s);
    }
}
