// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.content.Context;

public interface PushRegistrator
{
    void registerForPush(final Context p0, final String p1, final RegisteredHandler p2);
    
    public interface RegisteredHandler
    {
        void complete(final String p0, final int p1);
    }
}
