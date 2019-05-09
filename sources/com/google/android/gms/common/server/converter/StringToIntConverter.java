package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@KeepForSdk
@Class(creator = "StringToIntConverterCreator")
public final class StringToIntConverter extends AbstractSafeParcelable implements FieldConverter<String, Integer> {
    public static final Creator<StringToIntConverter> CREATOR = new zac();
    @VersionField(id = 1)
    private final int zalf;
    private final HashMap<String, Integer> zapm;
    private final SparseArray<String> zapn;
    @Field(getter = "getSerializedMap", id = 2)
    private final ArrayList<zaa> zapo;

    @Class(creator = "StringToIntConverterEntryCreator")
    public static final class zaa extends AbstractSafeParcelable {
        public static final Creator<zaa> CREATOR = new zad();
        @VersionField(id = 1)
        private final int versionCode;
        @Field(id = 2)
        final String zapp;
        @Field(id = 3)
        final int zapq;

        @Constructor
        zaa(@Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) int i2) {
            this.versionCode = i;
            this.zapp = str;
            this.zapq = i2;
        }

        zaa(String str, int i) {
            this.versionCode = 1;
            this.zapp = str;
            this.zapq = i;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
            SafeParcelWriter.writeString(parcel, 2, this.zapp, false);
            SafeParcelWriter.writeInt(parcel, 3, this.zapq);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Constructor
    StringToIntConverter(@Param(id = 1) int i, @Param(id = 2) ArrayList<zaa> arrayList) {
        this.zalf = i;
        this.zapm = new HashMap();
        this.zapn = new SparseArray();
        this.zapo = null;
        arrayList = arrayList;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            zaa zaa = (zaa) obj;
            add(zaa.zapp, zaa.zapq);
        }
    }

    @KeepForSdk
    public StringToIntConverter() {
        this.zalf = 1;
        this.zapm = new HashMap();
        this.zapn = new SparseArray();
        this.zapo = null;
    }

    @KeepForSdk
    public final StringToIntConverter add(String str, int i) {
        this.zapm.put(str, Integer.valueOf(i));
        this.zapn.put(i, str);
        return this;
    }

    public final int zacj() {
        return 7;
    }

    public final int zack() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        List arrayList = new ArrayList();
        for (String str : this.zapm.keySet()) {
            arrayList.add(new zaa(str, ((Integer) this.zapm.get(str)).intValue()));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* synthetic */ Object convertBack(Object obj) {
        String str = (String) this.zapn.get(((Integer) obj).intValue());
        if (str == null && this.zapm.containsKey("gms_unknown")) {
            return "gms_unknown";
        }
        return str;
    }

    public final /* synthetic */ Object convert(Object obj) {
        Integer num = (Integer) this.zapm.get((String) obj);
        if (num == null) {
            return (Integer) this.zapm.get("gms_unknown");
        }
        return num;
    }
}
