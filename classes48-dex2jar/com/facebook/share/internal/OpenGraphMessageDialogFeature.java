// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import com.facebook.internal.DialogFeature;

public enum OpenGraphMessageDialogFeature implements DialogFeature
{
    OG_MESSAGE_DIALOG(20140204);
    
    private int minVersion;
    
    private OpenGraphMessageDialogFeature(final int minVersion) {
        this.minVersion = minVersion;
    }
    
    public String getAction() {
        return "com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG";
    }
    
    public int getMinVersion() {
        return this.minVersion;
    }
}
