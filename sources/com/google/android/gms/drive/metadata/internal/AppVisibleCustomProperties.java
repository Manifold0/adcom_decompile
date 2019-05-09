package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Class(creator = "AppVisibleCustomPropertiesCreator")
@Reserved({1})
public final class AppVisibleCustomProperties extends AbstractSafeParcelable implements ReflectedParcelable, Iterable<zzc> {
    public static final Creator<AppVisibleCustomProperties> CREATOR = new zza();
    public static final AppVisibleCustomProperties zzil = new zza().zzat();
    @Field(id = 2)
    private final List<zzc> zzim;

    public static class zza {
        private final Map<CustomPropertyKey, zzc> zzin = new HashMap();

        public final zza zza(CustomPropertyKey customPropertyKey, String str) {
            Preconditions.checkNotNull(customPropertyKey, ParametersKeys.KEY);
            this.zzin.put(customPropertyKey, new zzc(customPropertyKey, str));
            return this;
        }

        public final zza zza(zzc zzc) {
            Preconditions.checkNotNull(zzc, "property");
            this.zzin.put(zzc.zzio, zzc);
            return this;
        }

        public final AppVisibleCustomProperties zzat() {
            return new AppVisibleCustomProperties(this.zzin.values());
        }
    }

    @Constructor
    AppVisibleCustomProperties(@Param(id = 2) Collection<zzc> collection) {
        Preconditions.checkNotNull(collection);
        this.zzim = new ArrayList(collection);
    }

    public final boolean equals(Object obj) {
        return this == obj ? true : (obj == null || obj.getClass() != getClass()) ? false : zzas().equals(((AppVisibleCustomProperties) obj).zzas());
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzim});
    }

    public final Iterator<zzc> iterator() {
        return this.zzim.iterator();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzim, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final Map<CustomPropertyKey, String> zzas() {
        Map hashMap = new HashMap(this.zzim.size());
        for (zzc zzc : this.zzim) {
            hashMap.put(zzc.zzio, zzc.value);
        }
        return Collections.unmodifiableMap(hashMap);
    }
}
