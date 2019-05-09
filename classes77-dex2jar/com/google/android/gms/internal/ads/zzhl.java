// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Iterator;
import java.util.List;
import com.google.android.gms.ads.internal.zzbv;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "CacheOfferingCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzhl extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzhl> CREATOR;
    @Nullable
    @SafeParcelable$Field(id = 2)
    public final String url;
    @SafeParcelable$Field(id = 3)
    private final long zzajv;
    @SafeParcelable$Field(id = 4)
    private final String zzajw;
    @SafeParcelable$Field(id = 5)
    private final String zzajx;
    @SafeParcelable$Field(id = 6)
    private final String zzajy;
    @SafeParcelable$Field(id = 7)
    private final Bundle zzajz;
    @SafeParcelable$Field(id = 8)
    private final boolean zzaka;
    @SafeParcelable$Field(id = 9)
    private long zzakb;
    
    static {
        CREATOR = (Parcelable$Creator)new zzhm();
    }
    
    @SafeParcelable$Constructor
    zzhl(@Nullable @SafeParcelable$Param(id = 2) final String url, @SafeParcelable$Param(id = 3) final long zzajv, @SafeParcelable$Param(id = 4) String zzajw, @SafeParcelable$Param(id = 5) String zzajx, @SafeParcelable$Param(id = 6) String zzajy, @SafeParcelable$Param(id = 7) Bundle zzajz, @SafeParcelable$Param(id = 8) final boolean zzaka, @SafeParcelable$Param(id = 9) final long zzakb) {
        this.url = url;
        this.zzajv = zzajv;
        if (zzajw == null) {
            zzajw = "";
        }
        this.zzajw = zzajw;
        if (zzajx == null) {
            zzajx = "";
        }
        this.zzajx = zzajx;
        if (zzajy == null) {
            zzajy = "";
        }
        this.zzajy = zzajy;
        if (zzajz == null) {
            zzajz = new Bundle();
        }
        this.zzajz = zzajz;
        this.zzaka = zzaka;
        this.zzakb = zzakb;
    }
    
    @Nullable
    public static zzhl zzaa(final String s) {
        return zzd(Uri.parse(s));
    }
    
    @Nullable
    public static zzhl zzd(final Uri ex) {
        try {
            if (!"gcache".equals(((Uri)ex).getScheme())) {
                return null;
            }
            final List pathSegments = ((Uri)ex).getPathSegments();
            if (pathSegments.size() != 2) {
                zzakb.zzdk(new StringBuilder(62).append("Expected 2 path parts for namespace and id, found :").append(pathSegments.size()).toString());
                return null;
            }
            final String s = pathSegments.get(0);
            final String s2 = pathSegments.get(1);
            ((Uri)ex).getHost();
            ((Uri)ex).getQueryParameter("url");
            "1".equals(((Uri)ex).getQueryParameter("read_only"));
            if (((Uri)ex).getQueryParameter("expiration") == null) {
                final Bundle bundle = new Bundle();
                for (final String s3 : zzbv.zzem().zzh((Uri)ex)) {
                    if (s3.startsWith("tag.")) {
                        bundle.putString(s3.substring(4), ((Uri)ex).getQueryParameter(s3));
                    }
                }
                goto Label_0226;
            }
            goto Label_0217;
        }
        catch (NullPointerException ex2) {}
        catch (NumberFormatException ex) {
            goto Label_0209;
        }
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.url, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzajv);
        SafeParcelWriter.writeString(parcel, 4, this.zzajw, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzajx, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzajy, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzajz, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzaka);
        SafeParcelWriter.writeLong(parcel, 9, this.zzakb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
