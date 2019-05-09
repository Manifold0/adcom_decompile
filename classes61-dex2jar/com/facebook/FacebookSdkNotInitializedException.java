// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

public class FacebookSdkNotInitializedException extends FacebookException
{
    static final long serialVersionUID = 1L;
    
    public FacebookSdkNotInitializedException() {
    }
    
    public FacebookSdkNotInitializedException(final String s) {
        super(s);
    }
    
    public FacebookSdkNotInitializedException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public FacebookSdkNotInitializedException(final Throwable t) {
        super(t);
    }
}
