// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren;

import android.support.v4.os.ResultReceiver;

public interface PublisherDirectDownload
{
    ResultReceiver getPublisherReceiver();
    
    void setSDKCallbackReceiver(final ResultReceiver p0);
}
