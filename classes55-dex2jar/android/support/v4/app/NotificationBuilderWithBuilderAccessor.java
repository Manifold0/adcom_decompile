// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v4.app;

import android.app.Notification$Builder;
import android.app.Notification;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

@RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
public interface NotificationBuilderWithBuilderAccessor
{
    Notification build();
    
    Notification$Builder getBuilder();
}
