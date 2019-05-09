// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import com.facebook.internal.CallbackManagerImpl;
import android.content.Intent;

public interface CallbackManager
{
    boolean onActivityResult(final int p0, final int p1, final Intent p2);
    
    public static class Factory
    {
        public static CallbackManager create() {
            return new CallbackManagerImpl();
        }
    }
}
