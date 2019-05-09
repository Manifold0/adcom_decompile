// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata.internal;

import java.util.HashSet;
import com.google.android.gms.internal.drive.zzhp;
import com.google.android.gms.common.data.BitmapTeleporter;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Set;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Bundle;
import com.google.android.gms.common.internal.GmsLogger;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "MetadataBundleCreator")
@SafeParcelable$Reserved({ 1 })
public final class MetadataBundle extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<MetadataBundle> CREATOR;
    private static final GmsLogger zzbx;
    @SafeParcelable$Field(id = 2)
    private final Bundle zzir;
    
    static {
        zzbx = new GmsLogger("MetadataBundle", "");
        CREATOR = (Parcelable$Creator)new zzj();
    }
    
    @SafeParcelable$Constructor
    MetadataBundle(@SafeParcelable$Param(id = 2) final Bundle bundle) {
        int i = 0;
        (this.zzir = (Bundle)Preconditions.checkNotNull((Object)bundle)).setClassLoader(this.getClass().getClassLoader());
        final ArrayList<String> list = new ArrayList<String>();
        for (final String s : this.zzir.keySet()) {
            if (zzf.zzd(s) == null) {
                list.add(s);
                MetadataBundle.zzbx.wfmt("MetadataBundle", "Ignored unknown metadata field in bundle: %s", new Object[] { s });
            }
        }
        final ArrayList<String> list2 = list;
        while (i < list2.size()) {
            final String value = list2.get(i);
            ++i;
            this.zzir.remove((String)value);
        }
    }
    
    public static <T> MetadataBundle zza(final MetadataField<T> metadataField, final T t) {
        final MetadataBundle zzaw = zzaw();
        zzaw.zzb(metadataField, t);
        return zzaw;
    }
    
    public static MetadataBundle zzaw() {
        return new MetadataBundle(new Bundle());
    }
    
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        final MetadataBundle metadataBundle = (MetadataBundle)o;
        final Set keySet = this.zzir.keySet();
        if (!keySet.equals(metadataBundle.zzir.keySet())) {
            return false;
        }
        for (final String s : keySet) {
            if (!Objects.equal(this.zzir.get(s), metadataBundle.zzir.get(s))) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        final Iterator<String> iterator = this.zzir.keySet().iterator();
        int n = 1;
        while (iterator.hasNext()) {
            n = this.zzir.get((String)iterator.next()).hashCode() + n * 31;
        }
        return n;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzir, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @Nullable
    public final <T> T zza(final MetadataField<T> metadataField) {
        return metadataField.zza(this.zzir);
    }
    
    public final void zza(final Context context) {
        final BitmapTeleporter bitmapTeleporter = this.zza(zzhp.zzka);
        if (bitmapTeleporter != null) {
            bitmapTeleporter.setTempDir(context.getCacheDir());
        }
    }
    
    public final MetadataBundle zzax() {
        return new MetadataBundle(new Bundle(this.zzir));
    }
    
    public final Set<MetadataField<?>> zzay() {
        final HashSet<MetadataField<?>> set = new HashSet<MetadataField<?>>();
        final Iterator<String> iterator = this.zzir.keySet().iterator();
        while (iterator.hasNext()) {
            set.add(zzf.zzd(iterator.next()));
        }
        return set;
    }
    
    public final <T> void zzb(final MetadataField<T> metadataField, final T t) {
        if (zzf.zzd(metadataField.getName()) == null) {
            final String value = String.valueOf(metadataField.getName());
            String concat;
            if (value.length() != 0) {
                concat = "Unregistered field: ".concat(value);
            }
            else {
                concat = new String("Unregistered field: ");
            }
            throw new IllegalArgumentException(concat);
        }
        metadataField.zza(t, this.zzir);
    }
    
    @Nullable
    public final <T> T zzc(final MetadataField<T> metadataField) {
        final Object zza = this.zza((MetadataField<Object>)metadataField);
        this.zzir.remove(metadataField.getName());
        return (T)zza;
    }
    
    public final boolean zzd(final MetadataField<?> metadataField) {
        return this.zzir.containsKey(metadataField.getName());
    }
}
