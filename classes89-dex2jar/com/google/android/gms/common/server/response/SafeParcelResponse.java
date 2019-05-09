// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.server.response;

import java.math.BigInteger;
import java.math.BigDecimal;
import android.os.Parcelable;
import java.util.List;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader$ParseException;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import android.util.SparseArray;
import com.google.android.gms.common.util.MapUtils;
import java.util.HashMap;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import java.util.Iterator;
import java.util.Map;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@SafeParcelable$Class(creator = "SafeParcelResponseCreator")
@VisibleForTesting
public class SafeParcelResponse extends FastSafeParcelableJsonResponse
{
    @KeepForSdk
    public static final Parcelable$Creator<SafeParcelResponse> CREATOR;
    private final String mClassName;
    @SafeParcelable$VersionField(getter = "getVersionCode", id = 1)
    private final int zalf;
    @SafeParcelable$Field(getter = "getFieldMappingDictionary", id = 3)
    private final zak zapz;
    @SafeParcelable$Field(getter = "getParcel", id = 2)
    private final Parcel zarb;
    private final int zarc;
    private int zard;
    private int zare;
    
    static {
        CREATOR = (Parcelable$Creator)new zap();
    }
    
    @SafeParcelable$Constructor
    SafeParcelResponse(@SafeParcelable$Param(id = 1) final int zalf, @SafeParcelable$Param(id = 2) final Parcel parcel, @SafeParcelable$Param(id = 3) final zak zapz) {
        this.zalf = zalf;
        this.zarb = (Parcel)Preconditions.checkNotNull((Object)parcel);
        this.zarc = 2;
        this.zapz = zapz;
        if (this.zapz == null) {
            this.mClassName = null;
        }
        else {
            this.mClassName = this.zapz.zact();
        }
        this.zard = 2;
    }
    
    private SafeParcelResponse(final SafeParcelable safeParcelable, final zak zak, final String s) {
        this.zalf = 1;
        safeParcelable.writeToParcel(this.zarb = Parcel.obtain(), 0);
        this.zarc = 1;
        this.zapz = (zak)Preconditions.checkNotNull((Object)zak);
        this.mClassName = (String)Preconditions.checkNotNull((Object)s);
        this.zard = 2;
    }
    
    public SafeParcelResponse(final zak zak, final String s) {
        this.zalf = 1;
        this.zarb = Parcel.obtain();
        this.zarc = 0;
        this.zapz = (zak)Preconditions.checkNotNull((Object)zak);
        this.mClassName = (String)Preconditions.checkNotNull((Object)s);
        this.zard = 0;
    }
    
    @KeepForSdk
    public static <T extends com.google.android.gms.common.server.response.FastJsonResponse> SafeParcelResponse from(final T t) {
        final String canonicalName = t.getClass().getCanonicalName();
        final zak zak = new zak(t.getClass());
        zaa(zak, (FastJsonResponse)t);
        zak.zacs();
        zak.zacr();
        return new SafeParcelResponse((SafeParcelable)t, zak, canonicalName);
    }
    
    private static void zaa(final zak zak, FastJsonResponse fastJsonResponse) {
        final Class<? extends FastJsonResponse> class1 = fastJsonResponse.getClass();
        if (!zak.zaa(class1)) {
            final Map<String, Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
            zak.zaa(class1, fieldMappings);
            final Iterator<String> iterator = fieldMappings.keySet().iterator();
            while (iterator.hasNext()) {
                fastJsonResponse = (FastJsonResponse)fieldMappings.get(iterator.next());
                final Class<? extends FastJsonResponse> zapx = ((Field)fastJsonResponse).zapx;
                if (zapx != null) {
                    try {
                        zaa(zak, (FastJsonResponse)zapx.newInstance());
                        continue;
                    }
                    catch (InstantiationException ex) {
                        final String value = String.valueOf(((Field)fastJsonResponse).zapx.getCanonicalName());
                        String concat;
                        if (value.length() != 0) {
                            concat = "Could not instantiate an object of type ".concat(value);
                        }
                        else {
                            concat = new String("Could not instantiate an object of type ");
                        }
                        throw new IllegalStateException(concat, ex);
                    }
                    catch (IllegalAccessException ex2) {
                        final String value2 = String.valueOf(((Field)fastJsonResponse).zapx.getCanonicalName());
                        String concat2;
                        if (value2.length() != 0) {
                            concat2 = "Could not access object of type ".concat(value2);
                        }
                        else {
                            concat2 = new String("Could not access object of type ");
                        }
                        throw new IllegalStateException(concat2, ex2);
                    }
                    break;
                }
            }
        }
    }
    
    private static void zaa(final StringBuilder sb, final int n, final Object o) {
        switch (n) {
            default: {
                throw new IllegalArgumentException(new StringBuilder(26).append("Unknown type = ").append(n).toString());
            }
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6: {
                sb.append(o);
            }
            case 7: {
                sb.append("\"").append(JsonUtils.escapeString(o.toString())).append("\"");
            }
            case 8: {
                sb.append("\"").append(Base64Utils.encode((byte[])o)).append("\"");
            }
            case 9: {
                sb.append("\"").append(Base64Utils.encodeUrlSafe((byte[])o));
                sb.append("\"");
            }
            case 10: {
                MapUtils.writeStringMapToJson(sb, (HashMap)o);
            }
            case 11: {
                throw new IllegalArgumentException("Method does not accept concrete type.");
            }
        }
    }
    
    private final void zaa(final StringBuilder sb, final Map<String, Field<?, ?>> map, final Parcel parcel) {
        final SparseArray sparseArray = new SparseArray();
        for (final Map.Entry<String, Field<?, ?>> entry : map.entrySet()) {
            sparseArray.put(((Field)entry.getValue()).getSafeParcelableFieldId(), (Object)entry);
        }
        sb.append('{');
        final int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int n = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            final int header = SafeParcelReader.readHeader(parcel);
            final Map.Entry entry2 = (Map.Entry)sparseArray.get(SafeParcelReader.getFieldId(header));
            if (entry2 != null) {
                if (n != 0) {
                    sb.append(",");
                }
                final String s = entry2.getKey();
                final Field<Object, Object> field = entry2.getValue();
                sb.append("\"").append(s).append("\":");
                if (field.zacn()) {
                    switch (field.zapt) {
                        default: {
                            throw new IllegalArgumentException(new StringBuilder(36).append("Unknown field out type = ").append(field.zapt).toString());
                        }
                        case 0: {
                            this.zab(sb, (Field<?, ?>)field, FastJsonResponse.zab(field, SafeParcelReader.readInt(parcel, header)));
                            break;
                        }
                        case 1: {
                            this.zab(sb, (Field<?, ?>)field, FastJsonResponse.zab(field, SafeParcelReader.createBigInteger(parcel, header)));
                            break;
                        }
                        case 2: {
                            this.zab(sb, (Field<?, ?>)field, FastJsonResponse.zab(field, SafeParcelReader.readLong(parcel, header)));
                            break;
                        }
                        case 3: {
                            this.zab(sb, (Field<?, ?>)field, FastJsonResponse.zab(field, SafeParcelReader.readFloat(parcel, header)));
                            break;
                        }
                        case 4: {
                            this.zab(sb, (Field<?, ?>)field, FastJsonResponse.zab(field, SafeParcelReader.readDouble(parcel, header)));
                            break;
                        }
                        case 5: {
                            this.zab(sb, (Field<?, ?>)field, FastJsonResponse.zab(field, SafeParcelReader.createBigDecimal(parcel, header)));
                            break;
                        }
                        case 6: {
                            this.zab(sb, (Field<?, ?>)field, FastJsonResponse.zab(field, SafeParcelReader.readBoolean(parcel, header)));
                            break;
                        }
                        case 7: {
                            this.zab(sb, (Field<?, ?>)field, FastJsonResponse.zab(field, SafeParcelReader.createString(parcel, header)));
                            break;
                        }
                        case 8:
                        case 9: {
                            this.zab(sb, (Field<?, ?>)field, FastJsonResponse.zab(field, SafeParcelReader.createByteArray(parcel, header)));
                            break;
                        }
                        case 10: {
                            final Bundle bundle = SafeParcelReader.createBundle(parcel, header);
                            final HashMap<String, String> hashMap = new HashMap<String, String>();
                            for (final String s2 : bundle.keySet()) {
                                hashMap.put(s2, bundle.getString(s2));
                            }
                            this.zab(sb, (Field<?, ?>)field, FastJsonResponse.zab(field, hashMap));
                            break;
                        }
                        case 11: {
                            throw new IllegalArgumentException("Method does not accept concrete type.");
                        }
                    }
                }
                else if (field.zapu) {
                    sb.append("[");
                    switch (field.zapt) {
                        default: {
                            throw new IllegalStateException("Unknown field type out.");
                        }
                        case 0: {
                            ArrayUtils.writeArray(sb, SafeParcelReader.createIntArray(parcel, header));
                            break;
                        }
                        case 1: {
                            ArrayUtils.writeArray(sb, (Object[])SafeParcelReader.createBigIntegerArray(parcel, header));
                            break;
                        }
                        case 2: {
                            ArrayUtils.writeArray(sb, SafeParcelReader.createLongArray(parcel, header));
                            break;
                        }
                        case 3: {
                            ArrayUtils.writeArray(sb, SafeParcelReader.createFloatArray(parcel, header));
                            break;
                        }
                        case 4: {
                            ArrayUtils.writeArray(sb, SafeParcelReader.createDoubleArray(parcel, header));
                            break;
                        }
                        case 5: {
                            ArrayUtils.writeArray(sb, (Object[])SafeParcelReader.createBigDecimalArray(parcel, header));
                            break;
                        }
                        case 6: {
                            ArrayUtils.writeArray(sb, SafeParcelReader.createBooleanArray(parcel, header));
                            break;
                        }
                        case 7: {
                            ArrayUtils.writeStringArray(sb, SafeParcelReader.createStringArray(parcel, header));
                            break;
                        }
                        case 8:
                        case 9:
                        case 10: {
                            throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                        }
                        case 11: {
                            final Parcel[] parcelArray = SafeParcelReader.createParcelArray(parcel, header);
                            for (int length = parcelArray.length, i = 0; i < length; ++i) {
                                if (i > 0) {
                                    sb.append(",");
                                }
                                parcelArray[i].setDataPosition(0);
                                this.zaa(sb, field.zacq(), parcelArray[i]);
                            }
                            break;
                        }
                    }
                    sb.append("]");
                }
                else {
                    switch (field.zapt) {
                        default: {
                            throw new IllegalStateException("Unknown field type out");
                        }
                        case 0: {
                            sb.append(SafeParcelReader.readInt(parcel, header));
                            break;
                        }
                        case 1: {
                            sb.append(SafeParcelReader.createBigInteger(parcel, header));
                            break;
                        }
                        case 2: {
                            sb.append(SafeParcelReader.readLong(parcel, header));
                            break;
                        }
                        case 3: {
                            sb.append(SafeParcelReader.readFloat(parcel, header));
                            break;
                        }
                        case 4: {
                            sb.append(SafeParcelReader.readDouble(parcel, header));
                            break;
                        }
                        case 5: {
                            sb.append(SafeParcelReader.createBigDecimal(parcel, header));
                            break;
                        }
                        case 6: {
                            sb.append(SafeParcelReader.readBoolean(parcel, header));
                            break;
                        }
                        case 7: {
                            sb.append("\"").append(JsonUtils.escapeString(SafeParcelReader.createString(parcel, header))).append("\"");
                            break;
                        }
                        case 8: {
                            sb.append("\"").append(Base64Utils.encode(SafeParcelReader.createByteArray(parcel, header))).append("\"");
                            break;
                        }
                        case 9: {
                            sb.append("\"").append(Base64Utils.encodeUrlSafe(SafeParcelReader.createByteArray(parcel, header)));
                            sb.append("\"");
                            break;
                        }
                        case 10: {
                            final Bundle bundle2 = SafeParcelReader.createBundle(parcel, header);
                            final Set keySet = bundle2.keySet();
                            keySet.size();
                            sb.append("{");
                            final Iterator<String> iterator3 = keySet.iterator();
                            int n2 = 1;
                            while (iterator3.hasNext()) {
                                final String s3 = iterator3.next();
                                if (n2 == 0) {
                                    sb.append(",");
                                }
                                sb.append("\"").append(s3).append("\"");
                                sb.append(":");
                                sb.append("\"").append(JsonUtils.escapeString(bundle2.getString(s3))).append("\"");
                                n2 = 0;
                            }
                            sb.append("}");
                            break;
                        }
                        case 11: {
                            final Parcel parcel2 = SafeParcelReader.createParcel(parcel, header);
                            parcel2.setDataPosition(0);
                            this.zaa(sb, field.zacq(), parcel2);
                            break;
                        }
                    }
                }
                n = 1;
            }
        }
        if (parcel.dataPosition() != validateObjectHeader) {
            throw new SafeParcelReader$ParseException(new StringBuilder(37).append("Overread allowed size end=").append(validateObjectHeader).toString(), parcel);
        }
        sb.append('}');
    }
    
    private final void zab(final Field<?, ?> field) {
        int n;
        if (field.zapw != -1) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            throw new IllegalStateException("Field does not have a valid safe parcelable field id.");
        }
        if (this.zarb == null) {
            throw new IllegalStateException("Internal Parcel object is null.");
        }
        switch (this.zard) {
            default: {
                throw new IllegalStateException("Unknown parse state in SafeParcelResponse.");
            }
            case 0: {
                this.zare = SafeParcelWriter.beginObjectHeader(this.zarb);
                this.zard = 1;
            }
            case 1: {}
            case 2: {
                throw new IllegalStateException("Attempted to parse JSON with a SafeParcelResponse object that is already filled with data.");
            }
        }
    }
    
    private final void zab(final StringBuilder sb, final Field<?, ?> field, final Object o) {
        if (field.zaps) {
            final ArrayList list = (ArrayList)o;
            sb.append("[");
            for (int size = list.size(), i = 0; i < size; ++i) {
                if (i != 0) {
                    sb.append(",");
                }
                zaa(sb, field.zapr, list.get(i));
            }
            sb.append("]");
            return;
        }
        zaa(sb, field.zapr, o);
    }
    
    private final Parcel zacu() {
        switch (this.zard) {
            case 0: {
                this.zare = SafeParcelWriter.beginObjectHeader(this.zarb);
            }
            case 1: {
                SafeParcelWriter.finishObjectHeader(this.zarb, this.zare);
                this.zard = 2;
                break;
            }
        }
        return this.zarb;
    }
    
    @Override
    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(final Field<?, ?> field, final String s, final ArrayList<T> list) {
        this.zab(field);
        final ArrayList<Parcel> list2 = new ArrayList<Parcel>();
        list.size();
        final ArrayList<Object> list3 = (ArrayList<Object>)list;
        final int size = list3.size();
        int i = 0;
        while (i < size) {
            final FastJsonResponse value = list3.get(i);
            ++i;
            list2.add(((SafeParcelResponse)value).zacu());
        }
        SafeParcelWriter.writeParcelList(this.zarb, field.getSafeParcelableFieldId(), (List)list2, true);
    }
    
    @Override
    public <T extends FastJsonResponse> void addConcreteTypeInternal(final Field<?, ?> field, final String s, final T t) {
        this.zab(field);
        SafeParcelWriter.writeParcel(this.zarb, field.getSafeParcelableFieldId(), ((SafeParcelResponse)t).zacu(), true);
    }
    
    @Override
    public Map<String, Field<?, ?>> getFieldMappings() {
        if (this.zapz == null) {
            return null;
        }
        return this.zapz.zai(this.mClassName);
    }
    
    @Override
    public Object getValueObject(final String s) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
    
    @Override
    public boolean isPrimitiveFieldSet(final String s) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
    
    @Override
    protected void setBooleanInternal(final Field<?, ?> field, final String s, final boolean b) {
        this.zab(field);
        SafeParcelWriter.writeBoolean(this.zarb, field.getSafeParcelableFieldId(), b);
    }
    
    @Override
    protected void setDecodedBytesInternal(final Field<?, ?> field, final String s, final byte[] array) {
        this.zab(field);
        SafeParcelWriter.writeByteArray(this.zarb, field.getSafeParcelableFieldId(), array, true);
    }
    
    @Override
    protected void setIntegerInternal(final Field<?, ?> field, final String s, final int n) {
        this.zab(field);
        SafeParcelWriter.writeInt(this.zarb, field.getSafeParcelableFieldId(), n);
    }
    
    @Override
    protected void setLongInternal(final Field<?, ?> field, final String s, final long n) {
        this.zab(field);
        SafeParcelWriter.writeLong(this.zarb, field.getSafeParcelableFieldId(), n);
    }
    
    @Override
    protected void setStringInternal(final Field<?, ?> field, final String s, final String s2) {
        this.zab(field);
        SafeParcelWriter.writeString(this.zarb, field.getSafeParcelableFieldId(), s2, true);
    }
    
    @Override
    protected void setStringsInternal(final Field<?, ?> field, final String s, final ArrayList<String> list) {
        this.zab(field);
        final int size = list.size();
        final String[] array = new String[size];
        for (int i = 0; i < size; ++i) {
            array[i] = list.get(i);
        }
        SafeParcelWriter.writeStringArray(this.zarb, field.getSafeParcelableFieldId(), array, true);
    }
    
    @Override
    public String toString() {
        Preconditions.checkNotNull((Object)this.zapz, (Object)"Cannot convert to JSON on client side.");
        final Parcel zacu = this.zacu();
        zacu.setDataPosition(0);
        final StringBuilder sb = new StringBuilder(100);
        this.zaa(sb, this.zapz.zai(this.mClassName), zacu);
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, int zarc) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeParcel(parcel, 2, this.zacu(), false);
        Object o = null;
        switch (this.zarc) {
            default: {
                zarc = this.zarc;
                throw new IllegalStateException(new StringBuilder(34).append("Invalid creation type: ").append(zarc).toString());
            }
            case 0: {
                o = null;
                break;
            }
            case 1: {
                o = this.zapz;
                break;
            }
            case 2: {
                o = this.zapz;
                break;
            }
        }
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)o, zarc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    @Override
    protected final void zaa(final Field<?, ?> field, final String s, final double n) {
        this.zab(field);
        SafeParcelWriter.writeDouble(this.zarb, field.getSafeParcelableFieldId(), n);
    }
    
    @Override
    protected final void zaa(final Field<?, ?> field, final String s, final float n) {
        this.zab(field);
        SafeParcelWriter.writeFloat(this.zarb, field.getSafeParcelableFieldId(), n);
    }
    
    @Override
    protected final void zaa(final Field<?, ?> field, final String s, final BigDecimal bigDecimal) {
        this.zab(field);
        SafeParcelWriter.writeBigDecimal(this.zarb, field.getSafeParcelableFieldId(), bigDecimal, true);
    }
    
    @Override
    protected final void zaa(final Field<?, ?> field, final String s, final BigInteger bigInteger) {
        this.zab(field);
        SafeParcelWriter.writeBigInteger(this.zarb, field.getSafeParcelableFieldId(), bigInteger, true);
    }
    
    @Override
    protected final void zaa(final Field<?, ?> field, final String s, final ArrayList<Integer> list) {
        this.zab(field);
        final int size = list.size();
        final int[] array = new int[size];
        for (int i = 0; i < size; ++i) {
            array[i] = list.get(i);
        }
        SafeParcelWriter.writeIntArray(this.zarb, field.getSafeParcelableFieldId(), array, true);
    }
    
    @Override
    protected final void zaa(final Field<?, ?> field, final String s, final Map<String, String> map) {
        this.zab(field);
        final Bundle bundle = new Bundle();
        for (final String s2 : map.keySet()) {
            bundle.putString(s2, (String)map.get(s2));
        }
        SafeParcelWriter.writeBundle(this.zarb, field.getSafeParcelableFieldId(), bundle, true);
    }
    
    @Override
    protected final void zab(final Field<?, ?> field, final String s, final ArrayList<BigInteger> list) {
        this.zab(field);
        final int size = list.size();
        final BigInteger[] array = new BigInteger[size];
        for (int i = 0; i < size; ++i) {
            array[i] = list.get(i);
        }
        SafeParcelWriter.writeBigIntegerArray(this.zarb, field.getSafeParcelableFieldId(), array, true);
    }
    
    @Override
    protected final void zac(final Field<?, ?> field, final String s, final ArrayList<Long> list) {
        this.zab(field);
        final int size = list.size();
        final long[] array = new long[size];
        for (int i = 0; i < size; ++i) {
            array[i] = list.get(i);
        }
        SafeParcelWriter.writeLongArray(this.zarb, field.getSafeParcelableFieldId(), array, true);
    }
    
    @Override
    protected final void zad(final Field<?, ?> field, final String s, final ArrayList<Float> list) {
        this.zab(field);
        final int size = list.size();
        final float[] array = new float[size];
        for (int i = 0; i < size; ++i) {
            array[i] = list.get(i);
        }
        SafeParcelWriter.writeFloatArray(this.zarb, field.getSafeParcelableFieldId(), array, true);
    }
    
    @Override
    protected final void zae(final Field<?, ?> field, final String s, final ArrayList<Double> list) {
        this.zab(field);
        final int size = list.size();
        final double[] array = new double[size];
        for (int i = 0; i < size; ++i) {
            array[i] = list.get(i);
        }
        SafeParcelWriter.writeDoubleArray(this.zarb, field.getSafeParcelableFieldId(), array, true);
    }
    
    @Override
    protected final void zaf(final Field<?, ?> field, final String s, final ArrayList<BigDecimal> list) {
        this.zab(field);
        final int size = list.size();
        final BigDecimal[] array = new BigDecimal[size];
        for (int i = 0; i < size; ++i) {
            array[i] = list.get(i);
        }
        SafeParcelWriter.writeBigDecimalArray(this.zarb, field.getSafeParcelableFieldId(), array, true);
    }
    
    @Override
    protected final void zag(final Field<?, ?> field, final String s, final ArrayList<Boolean> list) {
        this.zab(field);
        final int size = list.size();
        final boolean[] array = new boolean[size];
        for (int i = 0; i < size; ++i) {
            array[i] = list.get(i);
        }
        SafeParcelWriter.writeBooleanArray(this.zarb, field.getSafeParcelableFieldId(), array, true);
    }
}
