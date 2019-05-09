// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataBufferRef;

public class StockProfileImageRef extends DataBufferRef implements StockProfileImage
{
    public int describeContents() {
        throw new NoSuchMethodError();
    }
    
    public String getImageUrl() {
        return this.getString("image_url");
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        throw new NoSuchMethodError();
    }
    
    public final Uri zzbz() {
        throw new NoSuchMethodError();
    }
}
