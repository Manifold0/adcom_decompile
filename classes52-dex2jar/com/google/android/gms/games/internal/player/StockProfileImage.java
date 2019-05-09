// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal.player;

import android.net.Uri;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface StockProfileImage extends Parcelable, Freezable<StockProfileImage>
{
    String getImageUrl();
    
    Uri zzbz();
}
