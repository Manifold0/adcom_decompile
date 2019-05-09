// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren;

public interface DownloadStrategy
{
    void isApplicationAvailable(final String p0, final VerificationCallback p1);
    
    public interface VerificationCallback
    {
        void onResult(final boolean p0);
    }
}
