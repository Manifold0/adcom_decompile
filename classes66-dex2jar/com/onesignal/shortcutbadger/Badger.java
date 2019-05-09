// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal.shortcutbadger;

import java.util.List;
import android.content.ComponentName;
import android.content.Context;

public interface Badger
{
    void executeBadge(final Context p0, final ComponentName p1, final int p2) throws ShortcutBadgeException;
    
    List<String> getSupportLaunchers();
}
