// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

class UserStateEmail extends UserState
{
    UserStateEmail(final String s, final boolean b) {
        super("email" + s, b);
    }
    
    @Override
    protected void addDependFields() {
    }
    
    @Override
    boolean isSubscribed() {
        return true;
    }
    
    @Override
    UserState newInstance(final String s) {
        return new UserStateEmail(s, false);
    }
}
