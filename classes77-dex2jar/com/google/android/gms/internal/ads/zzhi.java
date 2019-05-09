// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import java.io.InputStream;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "CacheEntryParcelCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzhi extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzhi> CREATOR;
    @Nullable
    @SafeParcelable$Field(getter = "getContentFileDescriptor", id = 2)
    @GuardedBy("this")
    private ParcelFileDescriptor zzaju;
    
    static {
        CREATOR = (Parcelable$Creator)new zzhj();
    }
    
    public zzhi() {
        this(null);
    }
    
    @SafeParcelable$Constructor
    public zzhi(@Nullable @SafeParcelable$Param(id = 2) final ParcelFileDescriptor zzaju) {
        this.zzaju = zzaju;
    }
    
    private final ParcelFileDescriptor zzhk() {
        synchronized (this) {
            return this.zzaju;
        }
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzhk(), n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final boolean zzhi() {
        synchronized (this) {
            return this.zzaju != null;
        }
    }
    
    @Nullable
    public final InputStream zzhj() {
        Object o = null;
        synchronized (this) {
            if (this.zzaju != null) {
                o = new ParcelFileDescriptor$AutoCloseInputStream(this.zzaju);
                this.zzaju = null;
            }
            return (InputStream)o;
        }
    }
}
