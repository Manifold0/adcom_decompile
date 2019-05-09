// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal.experience;

import com.google.android.gms.common.annotation.KeepName;
import android.net.Uri;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

@VisibleForTesting
public interface ExperienceEvent extends Parcelable, Freezable<ExperienceEvent>
{
    Game getGame();
    
    Uri getIconImageUri();
    
    @Deprecated
    @KeepName
    String getIconImageUrl();
    
    int getType();
    
    String zzbm();
    
    String zzbn();
    
    String zzbo();
    
    long zzbp();
    
    long zzbq();
    
    long zzbr();
    
    int zzbs();
}
