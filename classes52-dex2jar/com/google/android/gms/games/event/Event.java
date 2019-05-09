// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.event;

import com.google.android.gms.games.Player;
import com.google.android.gms.common.annotation.KeepName;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

@VisibleForTesting
public interface Event extends Parcelable, Freezable<Event>
{
    String getDescription();
    
    void getDescription(final CharArrayBuffer p0);
    
    String getEventId();
    
    String getFormattedValue();
    
    void getFormattedValue(final CharArrayBuffer p0);
    
    Uri getIconImageUri();
    
    @Deprecated
    @KeepName
    String getIconImageUrl();
    
    String getName();
    
    void getName(final CharArrayBuffer p0);
    
    Player getPlayer();
    
    long getValue();
    
    boolean isVisible();
}
