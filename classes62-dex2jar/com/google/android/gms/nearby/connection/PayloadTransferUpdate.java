// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.connection;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "PayloadTransferUpdateCreator")
@SafeParcelable$Reserved({ 1000 })
public final class PayloadTransferUpdate extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<PayloadTransferUpdate> CREATOR;
    @SafeParcelable$Field(getter = "getStatus", id = 2)
    private int status;
    @SafeParcelable$Field(getter = "getPayloadId", id = 1)
    private long zzaf;
    @SafeParcelable$Field(getter = "getTotalBytes", id = 3)
    private long zzag;
    @SafeParcelable$Field(getter = "getBytesTransferred", id = 4)
    private long zzah;
    
    static {
        CREATOR = (Parcelable$Creator)new zzi();
    }
    
    private PayloadTransferUpdate() {
    }
    
    @SafeParcelable$Constructor
    PayloadTransferUpdate(@SafeParcelable$Param(id = 1) final long zzaf, @SafeParcelable$Param(id = 2) final int status, @SafeParcelable$Param(id = 3) final long zzag, @SafeParcelable$Param(id = 4) final long zzah) {
        this.zzaf = zzaf;
        this.status = status;
        this.zzag = zzag;
        this.zzah = zzah;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof PayloadTransferUpdate)) {
                return false;
            }
            final PayloadTransferUpdate payloadTransferUpdate = (PayloadTransferUpdate)o;
            if (!Objects.equal((Object)this.zzaf, (Object)payloadTransferUpdate.zzaf) || !Objects.equal((Object)this.status, (Object)payloadTransferUpdate.status) || !Objects.equal((Object)this.zzag, (Object)payloadTransferUpdate.zzag) || !Objects.equal((Object)this.zzah, (Object)payloadTransferUpdate.zzah)) {
                return false;
            }
        }
        return true;
    }
    
    public final long getBytesTransferred() {
        return this.zzah;
    }
    
    public final long getPayloadId() {
        return this.zzaf;
    }
    
    public final int getStatus() {
        return this.status;
    }
    
    public final long getTotalBytes() {
        return this.zzag;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzaf, this.status, this.zzag, this.zzah });
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.getPayloadId());
        SafeParcelWriter.writeInt(parcel, 2, this.getStatus());
        SafeParcelWriter.writeLong(parcel, 3, this.getTotalBytes());
        SafeParcelWriter.writeLong(parcel, 4, this.getBytesTransferred());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @Deprecated
    public static final class Builder
    {
        private final PayloadTransferUpdate zzai;
        
        public Builder() {
            this.zzai = new PayloadTransferUpdate(null);
        }
        
        public Builder(final PayloadTransferUpdate payloadTransferUpdate) {
            (this.zzai = new PayloadTransferUpdate(null)).zzaf = payloadTransferUpdate.zzaf;
            this.zzai.status = payloadTransferUpdate.status;
            this.zzai.zzag = payloadTransferUpdate.zzag;
            this.zzai.zzah = payloadTransferUpdate.zzah;
        }
        
        public final PayloadTransferUpdate build() {
            return this.zzai;
        }
        
        public final Builder setBytesTransferred(final long n) {
            this.zzai.zzah = n;
            return this;
        }
        
        public final Builder setPayloadId(final long n) {
            this.zzai.zzaf = n;
            return this;
        }
        
        public final Builder setStatus(final int n) {
            this.zzai.status = n;
            return this;
        }
        
        public final Builder setTotalBytes(final long n) {
            this.zzai.zzag = n;
            return this;
        }
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
        public static final int CANCELED = 4;
        public static final int FAILURE = 2;
        public static final int IN_PROGRESS = 3;
        public static final int SUCCESS = 1;
    }
}
