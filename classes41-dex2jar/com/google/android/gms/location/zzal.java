// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import java.util.Collections;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.annotation.Nullable;
import android.app.PendingIntent;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "RemoveGeofencingRequestCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzal extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzal> CREATOR;
    @SafeParcelable$Field(defaultValue = "", getter = "getTag", id = 3)
    private final String tag;
    @SafeParcelable$Field(getter = "getGeofenceIds", id = 1)
    private final List<String> zzbu;
    @SafeParcelable$Field(getter = "getPendingIntent", id = 2)
    private final PendingIntent zzbv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzam();
    }
    
    @SafeParcelable$Constructor
    zzal(@Nullable @SafeParcelable$Param(id = 1) final List<String> list, @Nullable @SafeParcelable$Param(id = 2) final PendingIntent zzbv, @SafeParcelable$Param(id = 3) final String tag) {
        List<String> zzbu;
        if (list == null) {
            zzbu = Collections.emptyList();
        }
        else {
            zzbu = Collections.unmodifiableList((List<? extends String>)list);
        }
        this.zzbu = zzbu;
        this.zzbv = zzbv;
        this.tag = tag;
    }
    
    public static zzal zza(final PendingIntent pendingIntent) {
        Preconditions.checkNotNull((Object)pendingIntent, (Object)"PendingIntent can not be null.");
        return new zzal(null, pendingIntent, "");
    }
    
    public static zzal zza(final List<String> list) {
        Preconditions.checkNotNull((Object)list, (Object)"geofence can't be null.");
        Preconditions.checkArgument(!list.isEmpty(), (Object)"Geofences must contains at least one id.");
        return new zzal(list, null, "");
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 1, (List)this.zzbu, false);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzbv, n, false);
        SafeParcelWriter.writeString(parcel, 3, this.tag, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
