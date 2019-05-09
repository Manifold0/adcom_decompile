// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import com.facebook.internal.DialogFeature;

public enum ShareDialogFeature implements DialogFeature
{
    HASHTAG(20160327), 
    LINK_SHARE_QUOTES(20160327), 
    MULTIMEDIA(20160327), 
    PHOTOS(20140204), 
    SHARE_DIALOG(20130618), 
    VIDEO(20141028);
    
    private int minVersion;
    
    private ShareDialogFeature(final int minVersion) {
        this.minVersion = minVersion;
    }
    
    @Override
    public String getAction() {
        return "com.facebook.platform.action.request.FEED_DIALOG";
    }
    
    @Override
    public int getMinVersion() {
        return this.minVersion;
    }
}
