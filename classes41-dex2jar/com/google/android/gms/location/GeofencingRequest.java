// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import java.util.Iterator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Collection;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.internal.location.zzbh;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "GeofencingRequestCreator")
@SafeParcelable$Reserved({ 1000 })
public class GeofencingRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<GeofencingRequest> CREATOR;
    public static final int INITIAL_TRIGGER_DWELL = 4;
    public static final int INITIAL_TRIGGER_ENTER = 1;
    public static final int INITIAL_TRIGGER_EXIT = 2;
    @SafeParcelable$Field(defaultValue = "", getter = "getTag", id = 3)
    private final String tag;
    @SafeParcelable$Field(getter = "getParcelableGeofences", id = 1)
    private final List<zzbh> zzap;
    @SafeParcelable$Field(getter = "getInitialTrigger", id = 2)
    private final int zzaq;
    
    static {
        CREATOR = (Parcelable$Creator)new zzq();
    }
    
    @SafeParcelable$Constructor
    GeofencingRequest(@SafeParcelable$Param(id = 1) final List<zzbh> zzap, @SafeParcelable$Param(id = 2) final int zzaq, @SafeParcelable$Param(id = 3) final String tag) {
        this.zzap = zzap;
        this.zzaq = zzaq;
        this.tag = tag;
    }
    
    public List<Geofence> getGeofences() {
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<Geofence>();
        list.addAll(this.zzap);
        return (List<Geofence>)list;
    }
    
    public int getInitialTrigger() {
        return this.zzaq;
    }
    
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("GeofencingRequest[");
        sb.append("geofences=");
        sb.append(this.zzap);
        sb.append(new StringBuilder(30).append(", initialTrigger=").append(this.zzaq).append(", ").toString());
        final String value = String.valueOf(this.tag);
        String concat;
        if (value.length() != 0) {
            concat = "tag=".concat(value);
        }
        else {
            concat = new String("tag=");
        }
        sb.append(concat);
        sb.append("]");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, (List)this.zzap, false);
        SafeParcelWriter.writeInt(parcel, 2, this.getInitialTrigger());
        SafeParcelWriter.writeString(parcel, 3, this.tag, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public static final class Builder
    {
        private String tag;
        private final List<zzbh> zzap;
        private int zzaq;
        
        public Builder() {
            this.zzap = new ArrayList<zzbh>();
            this.zzaq = 5;
            this.tag = "";
        }
        
        public final Builder addGeofence(final Geofence geofence) {
            Preconditions.checkNotNull((Object)geofence, (Object)"geofence can't be null.");
            Preconditions.checkArgument(geofence instanceof zzbh, (Object)"Geofence must be created using Geofence.Builder.");
            this.zzap.add((zzbh)geofence);
            return this;
        }
        
        public final Builder addGeofences(final List<Geofence> list) {
            if (list != null && !list.isEmpty()) {
                for (final Geofence geofence : list) {
                    if (geofence != null) {
                        this.addGeofence(geofence);
                    }
                }
            }
            return this;
        }
        
        public final GeofencingRequest build() {
            Preconditions.checkArgument(!this.zzap.isEmpty(), (Object)"No geofence has been added to this request.");
            return new GeofencingRequest(this.zzap, this.zzaq, this.tag);
        }
        
        public final Builder setInitialTrigger(final int n) {
            this.zzaq = (n & 0x7);
            return this;
        }
    }
}
