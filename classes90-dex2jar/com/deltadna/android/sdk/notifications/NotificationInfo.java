// 
// Decompiled by Procyon v0.5.34
// 

package com.deltadna.android.sdk.notifications;

import java.util.Locale;
import java.io.Serializable;

public class NotificationInfo implements Serializable
{
    public final int id;
    public final PushMessage message;
    
    NotificationInfo(final int id, final PushMessage message) {
        this.id = id;
        this.message = message;
    }
    
    @Override
    public String toString() {
        return String.format(Locale.US, "%s{id: %d, message: %s}", this.getClass().getSimpleName(), this.id, this.message);
    }
}
