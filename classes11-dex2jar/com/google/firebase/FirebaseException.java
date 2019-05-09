// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase;

import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.NonNull;

public class FirebaseException extends Exception
{
    @Deprecated
    protected FirebaseException() {
    }
    
    public FirebaseException(@NonNull final String s) {
        super(Preconditions.checkNotEmpty(s, "Detail message must not be empty"));
    }
    
    public FirebaseException(@NonNull final String s, final Throwable t) {
        super(Preconditions.checkNotEmpty(s, "Detail message must not be empty"), t);
    }
}
