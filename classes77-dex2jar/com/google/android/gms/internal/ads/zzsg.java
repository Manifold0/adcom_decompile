// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Iterator;
import java.util.Map;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "HttpRequestParcelCreator")
public final class zzsg extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzsg> CREATOR;
    @SafeParcelable$Field(id = 1)
    private final String url;
    @SafeParcelable$Field(id = 2)
    private final String[] zzbnh;
    @SafeParcelable$Field(id = 3)
    private final String[] zzbni;
    
    static {
        CREATOR = (Parcelable$Creator)new zzsh();
    }
    
    @SafeParcelable$Constructor
    zzsg(@SafeParcelable$Param(id = 1) final String url, @SafeParcelable$Param(id = 2) final String[] zzbnh, @SafeParcelable$Param(id = 3) final String[] zzbni) {
        this.url = url;
        this.zzbnh = zzbnh;
        this.zzbni = zzbni;
    }
    
    public static zzsg zzh(final zzr zzr) throws zza {
        final Map headers = zzr.getHeaders();
        final int size = headers.size();
        final String[] array = new String[size];
        final String[] array2 = new String[size];
        final Iterator<Map.Entry<String, V>> iterator = headers.entrySet().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final Map.Entry<String, V> entry = iterator.next();
            array[n] = entry.getKey();
            array2[n] = (String)entry.getValue();
            ++n;
        }
        return new zzsg(zzr.getUrl(), array, array2);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.url, false);
        SafeParcelWriter.writeStringArray(parcel, 2, this.zzbnh, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzbni, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
