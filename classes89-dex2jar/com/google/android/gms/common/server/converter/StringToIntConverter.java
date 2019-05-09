// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.server.converter;

import java.util.Iterator;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import java.util.ArrayList;
import android.util.SparseArray;
import java.util.HashMap;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
@SafeParcelable$Class(creator = "StringToIntConverterCreator")
public final class StringToIntConverter extends AbstractSafeParcelable implements FieldConverter<String, Integer>
{
    public static final Parcelable$Creator<StringToIntConverter> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int zalf;
    private final HashMap<String, Integer> zapm;
    private final SparseArray<String> zapn;
    @SafeParcelable$Field(getter = "getSerializedMap", id = 2)
    private final ArrayList<zaa> zapo;
    
    static {
        CREATOR = (Parcelable$Creator)new zac();
    }
    
    @KeepForSdk
    public StringToIntConverter() {
        this.zalf = 1;
        this.zapm = new HashMap<String, Integer>();
        this.zapn = (SparseArray<String>)new SparseArray();
        this.zapo = null;
    }
    
    @SafeParcelable$Constructor
    StringToIntConverter(@SafeParcelable$Param(id = 1) int i, @SafeParcelable$Param(id = 2) final ArrayList<zaa> list) {
        this.zalf = i;
        this.zapm = new HashMap<String, Integer>();
        this.zapn = (SparseArray<String>)new SparseArray();
        this.zapo = null;
        final ArrayList<zaa> list2 = list;
        final int size = list2.size();
        i = 0;
        while (i < size) {
            final zaa value = list2.get(i);
            ++i;
            final zaa zaa = value;
            this.add(zaa.zapp, zaa.zapq);
        }
    }
    
    @KeepForSdk
    public final StringToIntConverter add(final String s, final int n) {
        this.zapm.put(s, n);
        this.zapn.put(n, (Object)s);
        return this;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        final ArrayList<zaa> list = new ArrayList<zaa>();
        for (final String s : this.zapm.keySet()) {
            list.add(new zaa(s, this.zapm.get(s)));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, (List)list, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final int zacj() {
        return 7;
    }
    
    public final int zack() {
        return 0;
    }
    
    @SafeParcelable$Class(creator = "StringToIntConverterEntryCreator")
    public static final class zaa extends AbstractSafeParcelable
    {
        public static final Parcelable$Creator<zaa> CREATOR;
        @SafeParcelable$VersionField(id = 1)
        private final int versionCode;
        @SafeParcelable$Field(id = 2)
        final String zapp;
        @SafeParcelable$Field(id = 3)
        final int zapq;
        
        static {
            CREATOR = (Parcelable$Creator)new zad();
        }
        
        @SafeParcelable$Constructor
        zaa(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final String zapp, @SafeParcelable$Param(id = 3) final int zapq) {
            this.versionCode = versionCode;
            this.zapp = zapp;
            this.zapq = zapq;
        }
        
        zaa(final String zapp, final int zapq) {
            this.versionCode = 1;
            this.zapp = zapp;
            this.zapq = zapq;
        }
        
        public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
            beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
            SafeParcelWriter.writeString(parcel, 2, this.zapp, false);
            SafeParcelWriter.writeInt(parcel, 3, this.zapq);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }
}
