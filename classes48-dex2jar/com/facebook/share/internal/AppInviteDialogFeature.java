// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import com.facebook.internal.DialogFeature;

public enum AppInviteDialogFeature implements DialogFeature
{
    APP_INVITES_DIALOG(20140701);
    
    private int minVersion;
    
    private AppInviteDialogFeature(final int minVersion) {
        this.minVersion = minVersion;
    }
    
    public String getAction() {
        return "com.facebook.platform.action.request.APPINVITES_DIALOG";
    }
    
    public int getMinVersion() {
        return this.minVersion;
    }
}
