// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.overlay;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.content.Intent;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "AdLauncherIntentInfoCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzc extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzc> CREATOR;
    @SafeParcelable$Field(id = 9)
    public final Intent intent;
    @SafeParcelable$Field(id = 4)
    public final String mimeType;
    @SafeParcelable$Field(id = 5)
    public final String packageName;
    @SafeParcelable$Field(id = 3)
    public final String url;
    @SafeParcelable$Field(id = 2)
    private final String zzbxi;
    @SafeParcelable$Field(id = 6)
    public final String zzbxj;
    @SafeParcelable$Field(id = 7)
    public final String zzbxk;
    @SafeParcelable$Field(id = 8)
    private final String zzbxl;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    public zzc(final Intent intent) {
        this(null, null, null, null, null, null, null, intent);
    }
    
    public zzc(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        this(s, s2, s3, s4, s5, s6, s7, null);
    }
    
    @SafeParcelable$Constructor
    public zzc(@SafeParcelable$Param(id = 2) final String zzbxi, @SafeParcelable$Param(id = 3) final String url, @SafeParcelable$Param(id = 4) final String mimeType, @SafeParcelable$Param(id = 5) final String packageName, @SafeParcelable$Param(id = 6) final String zzbxj, @SafeParcelable$Param(id = 7) final String zzbxk, @SafeParcelable$Param(id = 8) final String zzbxl, @SafeParcelable$Param(id = 9) final Intent intent) {
        this.zzbxi = zzbxi;
        this.url = url;
        this.mimeType = mimeType;
        this.packageName = packageName;
        this.zzbxj = zzbxj;
        this.zzbxk = zzbxk;
        this.zzbxl = zzbxl;
        this.intent = intent;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzbxi, false);
        SafeParcelWriter.writeString(parcel, 3, this.url, false);
        SafeParcelWriter.writeString(parcel, 4, this.mimeType, false);
        SafeParcelWriter.writeString(parcel, 5, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzbxj, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzbxk, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzbxl, false);
        SafeParcelWriter.writeParcelable(parcel, 9, (Parcelable)this.intent, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
