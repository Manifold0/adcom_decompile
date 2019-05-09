// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.server.response;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Iterator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@ShowFirstParty
@SafeParcelable$Class(creator = "FieldMappingDictionaryCreator")
public final class zak extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zak> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int zalf;
    private final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zaqv;
    @SafeParcelable$Field(getter = "getSerializedDictionary", id = 2)
    private final ArrayList<zal> zaqw;
    @SafeParcelable$Field(getter = "getRootClassName", id = 3)
    private final String zaqx;
    
    static {
        CREATOR = (Parcelable$Creator)new zan();
    }
    
    @SafeParcelable$Constructor
    zak(@SafeParcelable$Param(id = 1) int i, @SafeParcelable$Param(id = 2) final ArrayList<zal> list, @SafeParcelable$Param(id = 3) final String s) {
        this.zalf = i;
        this.zaqw = null;
        final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zaqv = new HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>>();
        int size;
        zal zal;
        String className;
        HashMap<String, FastJsonResponse.Field<?, ?>> hashMap;
        int size2;
        int j;
        zam zam;
        for (size = list.size(), i = 0; i < size; ++i) {
            zal = list.get(i);
            className = zal.className;
            hashMap = new HashMap<String, FastJsonResponse.Field<?, ?>>();
            for (size2 = zal.zaqy.size(), j = 0; j < size2; ++j) {
                zam = zal.zaqy.get(j);
                hashMap.put(zam.zaqz, zam.zara);
            }
            zaqv.put(className, hashMap);
        }
        this.zaqv = zaqv;
        this.zaqx = (String)Preconditions.checkNotNull((Object)s);
        this.zacr();
    }
    
    public zak(final Class<? extends FastJsonResponse> clazz) {
        this.zalf = 1;
        this.zaqw = null;
        this.zaqv = new HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>>();
        this.zaqx = clazz.getCanonicalName();
    }
    
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final String s : this.zaqv.keySet()) {
            sb.append(s).append(":\n");
            final Map<String, FastJsonResponse.Field<?, ?>> map = this.zaqv.get(s);
            for (final String s2 : map.keySet()) {
                sb.append("  ").append(s2).append(": ");
                sb.append(map.get(s2));
            }
        }
        return sb.toString();
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        final ArrayList<zal> list = new ArrayList<zal>();
        for (final String s : this.zaqv.keySet()) {
            list.add(new zal(s, this.zaqv.get(s)));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, (List)list, false);
        SafeParcelWriter.writeString(parcel, 3, this.zaqx, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final void zaa(final Class<? extends FastJsonResponse> clazz, final Map<String, FastJsonResponse.Field<?, ?>> map) {
        this.zaqv.put(clazz.getCanonicalName(), map);
    }
    
    public final boolean zaa(final Class<? extends FastJsonResponse> clazz) {
        return this.zaqv.containsKey(clazz.getCanonicalName());
    }
    
    public final void zacr() {
        final Iterator<String> iterator = this.zaqv.keySet().iterator();
        while (iterator.hasNext()) {
            final Map<String, FastJsonResponse.Field<?, ?>> map = this.zaqv.get(iterator.next());
            final Iterator<String> iterator2 = map.keySet().iterator();
            while (iterator2.hasNext()) {
                ((FastJsonResponse.Field)map.get(iterator2.next())).zaa(this);
            }
        }
    }
    
    public final void zacs() {
        for (final String s : this.zaqv.keySet()) {
            final Map<String, FastJsonResponse.Field<?, ?>> map = this.zaqv.get(s);
            final HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<String, FastJsonResponse.Field<?, ?>>();
            for (final String s2 : map.keySet()) {
                hashMap.put(s2, map.get(s2).zacl());
            }
            this.zaqv.put(s, hashMap);
        }
    }
    
    public final String zact() {
        return this.zaqx;
    }
    
    public final Map<String, FastJsonResponse.Field<?, ?>> zai(final String s) {
        return this.zaqv.get(s);
    }
}
