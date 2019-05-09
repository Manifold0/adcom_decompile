// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata.internal;

import java.util.Collections;
import java.util.HashMap;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import java.util.Map;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Iterator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import java.util.ArrayList;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.Collection;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "AppVisibleCustomPropertiesCreator")
@SafeParcelable$Reserved({ 1 })
public final class AppVisibleCustomProperties extends AbstractSafeParcelable implements ReflectedParcelable, Iterable<zzc>
{
    public static final Parcelable$Creator<AppVisibleCustomProperties> CREATOR;
    public static final AppVisibleCustomProperties zzil;
    @SafeParcelable$Field(id = 2)
    private final List<zzc> zzim;
    
    static {
        CREATOR = (Parcelable$Creator)new com.google.android.gms.drive.metadata.internal.zza();
        zzil = new zza().zzat();
    }
    
    @SafeParcelable$Constructor
    AppVisibleCustomProperties(@SafeParcelable$Param(id = 2) final Collection<zzc> collection) {
        Preconditions.checkNotNull((Object)collection);
        this.zzim = new ArrayList<zzc>(collection);
    }
    
    public final boolean equals(final Object o) {
        return this == o || (o != null && o.getClass() == this.getClass() && this.zzas().equals(((AppVisibleCustomProperties)o).zzas()));
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzim });
    }
    
    public final Iterator<zzc> iterator() {
        return this.zzim.iterator();
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, (List)this.zzim, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final Map<CustomPropertyKey, String> zzas() {
        final HashMap<CustomPropertyKey, String> hashMap = new HashMap<CustomPropertyKey, String>(this.zzim.size());
        for (final zzc zzc : this.zzim) {
            hashMap.put(zzc.zzio, zzc.value);
        }
        return (Map<CustomPropertyKey, String>)Collections.unmodifiableMap((Map<?, ?>)hashMap);
    }
    
    public static final class zza
    {
        private final Map<CustomPropertyKey, zzc> zzin;
        
        public zza() {
            this.zzin = new HashMap<CustomPropertyKey, zzc>();
        }
        
        public final zza zza(final CustomPropertyKey customPropertyKey, final String s) {
            Preconditions.checkNotNull((Object)customPropertyKey, (Object)"key");
            this.zzin.put(customPropertyKey, new zzc(customPropertyKey, s));
            return this;
        }
        
        public final zza zza(final zzc zzc) {
            Preconditions.checkNotNull((Object)zzc, (Object)"property");
            this.zzin.put(zzc.zzio, zzc);
            return this;
        }
        
        public final AppVisibleCustomProperties zzat() {
            return new AppVisibleCustomProperties(this.zzin.values());
        }
    }
}
