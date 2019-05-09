// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Arrays;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.annotation.Nullable;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ParcelablePayloadCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzfh extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfh> CREATOR;
    @SafeParcelable$Field(getter = "getId", id = 1)
    private long id;
    @SafeParcelable$Field(getter = "getType", id = 2)
    private int type;
    @Nullable
    @SafeParcelable$Field(getter = "getDataPfd", id = 4)
    private ParcelFileDescriptor zzdv;
    @Nullable
    @SafeParcelable$Field(getter = "getJavaFilePath", id = 5)
    private String zzdw;
    @SafeParcelable$Field(defaultValue = "-1", getter = "getJavaFileSize", id = 6)
    private long zzdx;
    @Nullable
    @SafeParcelable$Field(getter = "getStatusPfd", id = 7)
    private ParcelFileDescriptor zzdy;
    @Nullable
    @SafeParcelable$Field(getter = "getBytes", id = 3)
    private byte[] zzy;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfk();
    }
    
    private zzfh() {
        this.zzdx = -1L;
    }
    
    @SafeParcelable$Constructor
    zzfh(@SafeParcelable$Param(id = 1) long id, @SafeParcelable$Param(id = 2) final int type, @Nullable @SafeParcelable$Param(id = 3) final byte[] zzy, @Nullable @SafeParcelable$Param(id = 4) final ParcelFileDescriptor zzdv, @Nullable @SafeParcelable$Param(id = 5) final String zzdw, @SafeParcelable$Param(id = 6) final long zzdx, @Nullable @SafeParcelable$Param(id = 7) final ParcelFileDescriptor zzdy) {
        this.zzdx = -1L;
        this.id = id;
        this.type = type;
        this.zzy = zzy;
        this.zzdv = zzdv;
        this.zzdw = zzdw;
        this.zzdx = zzdx;
        this.zzdy = zzdy;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzfh)) {
                return false;
            }
            final zzfh zzfh = (zzfh)o;
            if (!Objects.equal((Object)this.id, (Object)zzfh.id) || !Objects.equal((Object)this.type, (Object)zzfh.type) || !Arrays.equals(this.zzy, zzfh.zzy) || !Objects.equal((Object)this.zzdv, (Object)zzfh.zzdv) || !Objects.equal((Object)this.zzdw, (Object)zzfh.zzdw) || !Objects.equal((Object)this.zzdx, (Object)zzfh.zzdx) || !Objects.equal((Object)this.zzdy, (Object)zzfh.zzdy)) {
                return false;
            }
        }
        return true;
    }
    
    @Nullable
    public final byte[] getBytes() {
        return this.zzy;
    }
    
    public final long getId() {
        return this.id;
    }
    
    public final int getType() {
        return this.type;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.id, this.type, Arrays.hashCode(this.zzy), this.zzdv, this.zzdw, this.zzdx, this.zzdy });
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.id);
        SafeParcelWriter.writeInt(parcel, 2, this.type);
        SafeParcelWriter.writeByteArray(parcel, 3, this.zzy, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzdv, n, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzdw, false);
        SafeParcelWriter.writeLong(parcel, 6, this.zzdx);
        SafeParcelWriter.writeParcelable(parcel, 7, (Parcelable)this.zzdy, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @Nullable
    public final ParcelFileDescriptor zzo() {
        return this.zzdv;
    }
    
    @Nullable
    public final String zzp() {
        return this.zzdw;
    }
    
    public final long zzq() {
        return this.zzdx;
    }
}
