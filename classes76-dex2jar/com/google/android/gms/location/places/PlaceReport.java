// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location.places;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "PlaceReportCreator")
public class PlaceReport extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<PlaceReport> CREATOR;
    @SafeParcelable$Field(getter = "getTag", id = 3)
    private final String tag;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @SafeParcelable$Field(getter = "getPlaceId", id = 2)
    private final String zza;
    @SafeParcelable$Field(getter = "getSource", id = 4)
    private final String zzb;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    @SafeParcelable$Constructor
    PlaceReport(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final String zza, @SafeParcelable$Param(id = 3) final String tag, @SafeParcelable$Param(id = 4) final String zzb) {
        this.versionCode = versionCode;
        this.zza = zza;
        this.tag = tag;
        this.zzb = zzb;
    }
    
    @VisibleForTesting
    public static PlaceReport create(final String s, final String s2) {
        boolean b = false;
        Preconditions.checkNotNull((Object)s);
        Preconditions.checkNotEmpty(s2);
        Preconditions.checkNotEmpty("unknown");
        int n = -1;
        switch ("unknown".hashCode()) {
            case -284840886: {
                if ("unknown".equals("unknown")) {
                    n = 0;
                    break;
                }
                break;
            }
            case -1194968642: {
                if ("unknown".equals("userReported")) {
                    n = 1;
                    break;
                }
                break;
            }
            case -1436706272: {
                if ("unknown".equals("inferredGeofencing")) {
                    n = 2;
                    break;
                }
                break;
            }
            case 1287171955: {
                if ("unknown".equals("inferredRadioSignals")) {
                    n = 3;
                    break;
                }
                break;
            }
            case -262743844: {
                if ("unknown".equals("inferredReverseGeocoding")) {
                    n = 4;
                    break;
                }
                break;
            }
            case 1164924125: {
                if ("unknown".equals("inferredSnappedToRoad")) {
                    n = 5;
                    break;
                }
                break;
            }
        }
        switch (n) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                b = true;
                break;
            }
        }
        Preconditions.checkArgument(b, (Object)"Invalid source");
        return new PlaceReport(1, s, s2, "unknown");
    }
    
    public boolean equals(final Object o) {
        if (o instanceof PlaceReport) {
            final PlaceReport placeReport = (PlaceReport)o;
            if (Objects.equal((Object)this.zza, (Object)placeReport.zza) && Objects.equal((Object)this.tag, (Object)placeReport.tag) && Objects.equal((Object)this.zzb, (Object)placeReport.zzb)) {
                return true;
            }
        }
        return false;
    }
    
    public String getPlaceId() {
        return this.zza;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.zza, this.tag, this.zzb });
    }
    
    public String toString() {
        final Objects$ToStringHelper stringHelper = Objects.toStringHelper((Object)this);
        stringHelper.add("placeId", (Object)this.zza);
        stringHelper.add("tag", (Object)this.tag);
        if (!"unknown".equals(this.zzb)) {
            stringHelper.add("source", (Object)this.zzb);
        }
        return stringHelper.toString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.getPlaceId(), false);
        SafeParcelWriter.writeString(parcel, 3, this.getTag(), false);
        SafeParcelWriter.writeString(parcel, 4, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
