// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.server.response;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.server.converter.zaa;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Iterator;
import com.google.android.gms.common.util.MapUtils;
import java.util.HashMap;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;
import java.util.ArrayList;
import android.util.Log;
import com.google.android.gms.common.util.JsonUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@ShowFirstParty
public abstract class FastJsonResponse
{
    private final <I, O> void zaa(final Field<I, O> field, final I n) {
        final String zapv = field.zapv;
        final O convert = field.convert(n);
        switch (field.zapt) {
            default: {
                throw new IllegalStateException(new StringBuilder(44).append("Unsupported type for conversion: ").append(field.zapt).toString());
            }
            case 0: {
                if (zaa(zapv, (Integer)convert)) {
                    this.setIntegerInternal(field, zapv, (int)convert);
                    break;
                }
                break;
            }
            case 1: {
                this.zaa(field, zapv, (BigInteger)convert);
            }
            case 2: {
                if (zaa(zapv, (Integer)convert)) {
                    this.setLongInternal(field, zapv, (long)convert);
                    return;
                }
                break;
            }
            case 4: {
                if (zaa(zapv, (Integer)convert)) {
                    this.zaa(field, zapv, (double)convert);
                    return;
                }
                break;
            }
            case 5: {
                this.zaa(field, zapv, (BigDecimal)convert);
            }
            case 6: {
                if (zaa(zapv, (Integer)convert)) {
                    this.setBooleanInternal(field, zapv, (boolean)convert);
                    return;
                }
                break;
            }
            case 7: {
                this.setStringInternal(field, zapv, (String)convert);
            }
            case 8:
            case 9: {
                if (zaa(zapv, (Integer)convert)) {
                    this.setDecodedBytesInternal(field, zapv, (byte[])(Object)convert);
                    return;
                }
                break;
            }
        }
    }
    
    private static void zaa(final StringBuilder sb, final Field field, final Object o) {
        if (field.zapr == 11) {
            sb.append(((FastJsonResponse)field.zapx.cast(o)).toString());
            return;
        }
        if (field.zapr == 7) {
            sb.append("\"");
            sb.append(JsonUtils.escapeString((String)o));
            sb.append("\"");
            return;
        }
        sb.append(o);
    }
    
    private static <O> boolean zaa(final String s, final O o) {
        if (o == null) {
            if (Log.isLoggable("FastJsonResponse", 6)) {
                Log.e("FastJsonResponse", new StringBuilder(String.valueOf(s).length() + 58).append("Output field (").append(s).append(") has a null value, but expected a primitive").toString());
            }
            return false;
        }
        return true;
    }
    
    protected static <O, I> I zab(final Field<I, O> field, final Object o) {
        Object convertBack = o;
        if (((Field<Object, Object>)field).zaqa != null) {
            convertBack = field.convertBack(o);
        }
        return (I)convertBack;
    }
    
    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(final Field<?, ?> field, final String s, final ArrayList<T> list) {
        throw new UnsupportedOperationException("Concrete type array not supported");
    }
    
    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeInternal(final Field<?, ?> field, final String s, final T t) {
        throw new UnsupportedOperationException("Concrete type not supported");
    }
    
    @KeepForSdk
    public abstract Map<String, Field<?, ?>> getFieldMappings();
    
    @KeepForSdk
    protected Object getFieldValue(final Field field) {
        final String zapv = field.zapv;
        if (field.zapx != null) {
            Label_0121: {
                if (this.getValueObject(field.zapv) != null) {
                    break Label_0121;
                }
                boolean b = true;
                while (true) {
                    Preconditions.checkState(b, "Concrete field shouldn't be value object: %s", new Object[] { field.zapv });
                    final boolean zapu = field.zapu;
                    try {
                        final char upperCase = Character.toUpperCase(zapv.charAt(0));
                        final String substring = zapv.substring(1);
                        return this.getClass().getMethod(new StringBuilder(String.valueOf(substring).length() + 4).append("get").append(upperCase).append(substring).toString(), (Class<?>[])new Class[0]).invoke(this, new Object[0]);
                        b = false;
                        continue;
                    }
                    catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                }
            }
        }
        return this.getValueObject(field.zapv);
    }
    
    @KeepForSdk
    protected abstract Object getValueObject(final String p0);
    
    @KeepForSdk
    protected boolean isFieldSet(final Field field) {
        if (field.zapt != 11) {
            return this.isPrimitiveFieldSet(field.zapv);
        }
        if (field.zapu) {
            final String zapv = field.zapv;
            throw new UnsupportedOperationException("Concrete type arrays not supported");
        }
        final String zapv2 = field.zapv;
        throw new UnsupportedOperationException("Concrete types not supported");
    }
    
    @KeepForSdk
    protected abstract boolean isPrimitiveFieldSet(final String p0);
    
    @KeepForSdk
    protected void setBooleanInternal(final Field<?, ?> field, final String s, final boolean b) {
        throw new UnsupportedOperationException("Boolean not supported");
    }
    
    @KeepForSdk
    protected void setDecodedBytesInternal(final Field<?, ?> field, final String s, final byte[] array) {
        throw new UnsupportedOperationException("byte[] not supported");
    }
    
    @KeepForSdk
    protected void setIntegerInternal(final Field<?, ?> field, final String s, final int n) {
        throw new UnsupportedOperationException("Integer not supported");
    }
    
    @KeepForSdk
    protected void setLongInternal(final Field<?, ?> field, final String s, final long n) {
        throw new UnsupportedOperationException("Long not supported");
    }
    
    @KeepForSdk
    protected void setStringInternal(final Field<?, ?> field, final String s, final String s2) {
        throw new UnsupportedOperationException("String not supported");
    }
    
    @KeepForSdk
    protected void setStringsInternal(final Field<?, ?> field, final String s, final ArrayList<String> list) {
        throw new UnsupportedOperationException("String list not supported");
    }
    
    @KeepForSdk
    @Override
    public String toString() {
        final Map<String, Field<?, ?>> fieldMappings = this.getFieldMappings();
        final StringBuilder sb = new StringBuilder(100);
        for (final String s : fieldMappings.keySet()) {
            final Field<ArrayList<Object>, Object> field = fieldMappings.get(s);
            if (this.isFieldSet(field)) {
                final ArrayList<Object> zab = zab(field, this.getFieldValue(field));
                if (sb.length() == 0) {
                    sb.append("{");
                }
                else {
                    sb.append(",");
                }
                sb.append("\"").append(s).append("\":");
                if (zab == null) {
                    sb.append("null");
                }
                else {
                    switch (field.zapt) {
                        default: {
                            if (field.zaps) {
                                final ArrayList<Object> list = zab;
                                sb.append("[");
                                for (int i = 0; i < list.size(); ++i) {
                                    if (i > 0) {
                                        sb.append(",");
                                    }
                                    final Object value = list.get(i);
                                    if (value != null) {
                                        zaa(sb, field, value);
                                    }
                                }
                                sb.append("]");
                                continue;
                            }
                            zaa(sb, field, zab);
                            continue;
                        }
                        case 8: {
                            sb.append("\"").append(Base64Utils.encode((byte[])(Object)zab)).append("\"");
                            continue;
                        }
                        case 9: {
                            sb.append("\"").append(Base64Utils.encodeUrlSafe((byte[])(Object)zab)).append("\"");
                            continue;
                        }
                        case 10: {
                            MapUtils.writeStringMapToJson(sb, (HashMap)zab);
                            continue;
                        }
                    }
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        }
        else {
            sb.append("{}");
        }
        return sb.toString();
    }
    
    public final <O> void zaa(final Field<Double, O> field, final double n) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<Double, Object>)field, Double.valueOf(n));
            return;
        }
        this.zaa(field, field.zapv, n);
    }
    
    public final <O> void zaa(final Field<Float, O> field, final float n) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<Float, Object>)field, Float.valueOf(n));
            return;
        }
        this.zaa(field, field.zapv, n);
    }
    
    public final <O> void zaa(final Field<Integer, O> field, final int n) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<Integer, Object>)field, Integer.valueOf(n));
            return;
        }
        this.setIntegerInternal(field, field.zapv, n);
    }
    
    public final <O> void zaa(final Field<Long, O> field, final long n) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<Long, Object>)field, Long.valueOf(n));
            return;
        }
        this.setLongInternal(field, field.zapv, n);
    }
    
    public final <O> void zaa(final Field<String, O> field, final String s) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<String, Object>)field, s);
            return;
        }
        this.setStringInternal(field, field.zapv, s);
    }
    
    protected void zaa(final Field<?, ?> field, final String s, final double n) {
        throw new UnsupportedOperationException("Double not supported");
    }
    
    protected void zaa(final Field<?, ?> field, final String s, final float n) {
        throw new UnsupportedOperationException("Float not supported");
    }
    
    protected void zaa(final Field<?, ?> field, final String s, final BigDecimal bigDecimal) {
        throw new UnsupportedOperationException("BigDecimal not supported");
    }
    
    protected void zaa(final Field<?, ?> field, final String s, final BigInteger bigInteger) {
        throw new UnsupportedOperationException("BigInteger not supported");
    }
    
    protected void zaa(final Field<?, ?> field, final String s, final ArrayList<Integer> list) {
        throw new UnsupportedOperationException("Integer list not supported");
    }
    
    protected void zaa(final Field<?, ?> field, final String s, final Map<String, String> map) {
        throw new UnsupportedOperationException("String map not supported");
    }
    
    public final <O> void zaa(final Field<BigDecimal, O> field, final BigDecimal bigDecimal) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<BigDecimal, Object>)field, bigDecimal);
            return;
        }
        this.zaa(field, field.zapv, bigDecimal);
    }
    
    public final <O> void zaa(final Field<BigInteger, O> field, final BigInteger bigInteger) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<BigInteger, Object>)field, bigInteger);
            return;
        }
        this.zaa(field, field.zapv, bigInteger);
    }
    
    public final <O> void zaa(final Field<ArrayList<Integer>, O> field, final ArrayList<Integer> list) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<ArrayList<Integer>, Object>)field, list);
            return;
        }
        this.zaa(field, field.zapv, list);
    }
    
    public final <O> void zaa(final Field<Map<String, String>, O> field, final Map<String, String> map) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<Map<String, String>, Object>)field, map);
            return;
        }
        this.zaa(field, field.zapv, map);
    }
    
    public final <O> void zaa(final Field<Boolean, O> field, final boolean b) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<Boolean, Object>)field, Boolean.valueOf(b));
            return;
        }
        this.setBooleanInternal(field, field.zapv, b);
    }
    
    public final <O> void zaa(final Field<byte[], O> field, final byte[] array) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<byte[], Object>)field, array);
            return;
        }
        this.setDecodedBytesInternal(field, field.zapv, array);
    }
    
    protected void zab(final Field<?, ?> field, final String s, final ArrayList<BigInteger> list) {
        throw new UnsupportedOperationException("BigInteger list not supported");
    }
    
    public final <O> void zab(final Field<ArrayList<BigInteger>, O> field, final ArrayList<BigInteger> list) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<ArrayList<BigInteger>, Object>)field, list);
            return;
        }
        this.zab(field, field.zapv, list);
    }
    
    protected void zac(final Field<?, ?> field, final String s, final ArrayList<Long> list) {
        throw new UnsupportedOperationException("Long list not supported");
    }
    
    public final <O> void zac(final Field<ArrayList<Long>, O> field, final ArrayList<Long> list) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<ArrayList<Long>, Object>)field, list);
            return;
        }
        this.zac(field, field.zapv, list);
    }
    
    protected void zad(final Field<?, ?> field, final String s, final ArrayList<Float> list) {
        throw new UnsupportedOperationException("Float list not supported");
    }
    
    public final <O> void zad(final Field<ArrayList<Float>, O> field, final ArrayList<Float> list) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<ArrayList<Float>, Object>)field, list);
            return;
        }
        this.zad(field, field.zapv, list);
    }
    
    protected void zae(final Field<?, ?> field, final String s, final ArrayList<Double> list) {
        throw new UnsupportedOperationException("Double list not supported");
    }
    
    public final <O> void zae(final Field<ArrayList<Double>, O> field, final ArrayList<Double> list) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<ArrayList<Double>, Object>)field, list);
            return;
        }
        this.zae(field, field.zapv, list);
    }
    
    protected void zaf(final Field<?, ?> field, final String s, final ArrayList<BigDecimal> list) {
        throw new UnsupportedOperationException("BigDecimal list not supported");
    }
    
    public final <O> void zaf(final Field<ArrayList<BigDecimal>, O> field, final ArrayList<BigDecimal> list) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<ArrayList<BigDecimal>, Object>)field, list);
            return;
        }
        this.zaf(field, field.zapv, list);
    }
    
    protected void zag(final Field<?, ?> field, final String s, final ArrayList<Boolean> list) {
        throw new UnsupportedOperationException("Boolean list not supported");
    }
    
    public final <O> void zag(final Field<ArrayList<Boolean>, O> field, final ArrayList<Boolean> list) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<ArrayList<Boolean>, Object>)field, list);
            return;
        }
        this.zag(field, field.zapv, list);
    }
    
    public final <O> void zah(final Field<ArrayList<String>, O> field, final ArrayList<String> list) {
        if (((Field<Object, Object>)field).zaqa != null) {
            this.zaa((Field<ArrayList<String>, Object>)field, list);
            return;
        }
        this.setStringsInternal(field, field.zapv, list);
    }
    
    @KeepForSdk
    @ShowFirstParty
    @SafeParcelable$Class(creator = "FieldCreator")
    @VisibleForTesting
    public static class Field<I, O> extends AbstractSafeParcelable
    {
        public static final zai CREATOR;
        @SafeParcelable$VersionField(getter = "getVersionCode", id = 1)
        private final int zalf;
        @SafeParcelable$Field(getter = "getTypeIn", id = 2)
        protected final int zapr;
        @SafeParcelable$Field(getter = "isTypeInArray", id = 3)
        protected final boolean zaps;
        @SafeParcelable$Field(getter = "getTypeOut", id = 4)
        protected final int zapt;
        @SafeParcelable$Field(getter = "isTypeOutArray", id = 5)
        protected final boolean zapu;
        @SafeParcelable$Field(getter = "getOutputFieldName", id = 6)
        protected final String zapv;
        @SafeParcelable$Field(getter = "getSafeParcelableFieldId", id = 7)
        protected final int zapw;
        protected final Class<? extends FastJsonResponse> zapx;
        @SafeParcelable$Field(getter = "getConcreteTypeName", id = 8)
        private final String zapy;
        private zak zapz;
        @SafeParcelable$Field(getter = "getWrappedConverter", id = 9, type = "com.google.android.gms.common.server.converter.ConverterWrapper")
        private FieldConverter<I, O> zaqa;
        
        static {
            CREATOR = new zai();
        }
        
        @SafeParcelable$Constructor
        Field(@SafeParcelable$Param(id = 1) final int zalf, @SafeParcelable$Param(id = 2) final int zapr, @SafeParcelable$Param(id = 3) final boolean zaps, @SafeParcelable$Param(id = 4) final int zapt, @SafeParcelable$Param(id = 5) final boolean zapu, @SafeParcelable$Param(id = 6) final String zapv, @SafeParcelable$Param(id = 7) final int zapw, @SafeParcelable$Param(id = 8) final String zapy, @SafeParcelable$Param(id = 9) final zaa zaa) {
            this.zalf = zalf;
            this.zapr = zapr;
            this.zaps = zaps;
            this.zapt = zapt;
            this.zapu = zapu;
            this.zapv = zapv;
            this.zapw = zapw;
            if (zapy == null) {
                this.zapx = null;
                this.zapy = null;
            }
            else {
                this.zapx = SafeParcelResponse.class;
                this.zapy = zapy;
            }
            if (zaa == null) {
                this.zaqa = null;
                return;
            }
            this.zaqa = (FieldConverter<I, O>)zaa.zaci();
        }
        
        private Field(final int zapr, final boolean zaps, final int zapt, final boolean zapu, final String zapv, final int zapw, final Class<? extends FastJsonResponse> zapx, final FieldConverter<I, O> zaqa) {
            this.zalf = 1;
            this.zapr = zapr;
            this.zaps = zaps;
            this.zapt = zapt;
            this.zapu = zapu;
            this.zapv = zapv;
            this.zapw = zapw;
            this.zapx = zapx;
            if (zapx == null) {
                this.zapy = null;
            }
            else {
                this.zapy = zapx.getCanonicalName();
            }
            this.zaqa = zaqa;
        }
        
        @KeepForSdk
        @VisibleForTesting
        public static Field<byte[], byte[]> forBase64(final String s, final int n) {
            return new Field<byte[], byte[]>(8, false, 8, false, s, n, null, null);
        }
        
        @KeepForSdk
        public static Field<Boolean, Boolean> forBoolean(final String s, final int n) {
            return new Field<Boolean, Boolean>(6, false, 6, false, s, n, null, null);
        }
        
        @KeepForSdk
        public static <T extends FastJsonResponse> Field<T, T> forConcreteType(final String s, final int n, final Class<T> clazz) {
            return new Field<T, T>(11, false, 11, false, s, n, clazz, null);
        }
        
        @KeepForSdk
        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> forConcreteTypeArray(final String s, final int n, final Class<T> clazz) {
            return new Field<ArrayList<T>, ArrayList<T>>(11, true, 11, true, s, n, clazz, null);
        }
        
        @KeepForSdk
        public static Field<Double, Double> forDouble(final String s, final int n) {
            return new Field<Double, Double>(4, false, 4, false, s, n, null, null);
        }
        
        @KeepForSdk
        public static Field<Float, Float> forFloat(final String s, final int n) {
            return new Field<Float, Float>(3, false, 3, false, s, n, null, null);
        }
        
        @KeepForSdk
        @VisibleForTesting
        public static Field<Integer, Integer> forInteger(final String s, final int n) {
            return new Field<Integer, Integer>(0, false, 0, false, s, n, null, null);
        }
        
        @KeepForSdk
        public static Field<Long, Long> forLong(final String s, final int n) {
            return new Field<Long, Long>(2, false, 2, false, s, n, null, null);
        }
        
        @KeepForSdk
        public static Field<String, String> forString(final String s, final int n) {
            return new Field<String, String>(7, false, 7, false, s, n, null, null);
        }
        
        @KeepForSdk
        public static Field<ArrayList<String>, ArrayList<String>> forStrings(final String s, final int n) {
            return new Field<ArrayList<String>, ArrayList<String>>(7, true, 7, true, s, n, null, null);
        }
        
        @KeepForSdk
        public static Field withConverter(final String s, final int n, final FieldConverter<?, ?> fieldConverter, final boolean b) {
            return new Field(fieldConverter.zacj(), b, fieldConverter.zack(), false, s, n, null, (FieldConverter<I, O>)fieldConverter);
        }
        
        private final String zacm() {
            if (this.zapy == null) {
                return null;
            }
            return this.zapy;
        }
        
        private final zaa zaco() {
            if (this.zaqa == null) {
                return null;
            }
            return zaa.zaa(this.zaqa);
        }
        
        public final O convert(final I n) {
            return this.zaqa.convert(n);
        }
        
        public final I convertBack(final O o) {
            return this.zaqa.convertBack(o);
        }
        
        @KeepForSdk
        public int getSafeParcelableFieldId() {
            return this.zapw;
        }
        
        public String toString() {
            final Objects$ToStringHelper add = Objects.toStringHelper((Object)this).add("versionCode", (Object)this.zalf).add("typeIn", (Object)this.zapr).add("typeInArray", (Object)this.zaps).add("typeOut", (Object)this.zapt).add("typeOutArray", (Object)this.zapu).add("outputFieldName", (Object)this.zapv).add("safeParcelFieldId", (Object)this.zapw).add("concreteTypeName", (Object)this.zacm());
            final Class<? extends FastJsonResponse> zapx = this.zapx;
            if (zapx != null) {
                add.add("concreteType.class", (Object)zapx.getCanonicalName());
            }
            if (this.zaqa != null) {
                add.add("converterName", (Object)this.zaqa.getClass().getCanonicalName());
            }
            return add.toString();
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, this.zalf);
            SafeParcelWriter.writeInt(parcel, 2, this.zapr);
            SafeParcelWriter.writeBoolean(parcel, 3, this.zaps);
            SafeParcelWriter.writeInt(parcel, 4, this.zapt);
            SafeParcelWriter.writeBoolean(parcel, 5, this.zapu);
            SafeParcelWriter.writeString(parcel, 6, this.zapv, false);
            SafeParcelWriter.writeInt(parcel, 7, this.getSafeParcelableFieldId());
            SafeParcelWriter.writeString(parcel, 8, this.zacm(), false);
            SafeParcelWriter.writeParcelable(parcel, 9, (Parcelable)this.zaco(), n, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
        
        public final void zaa(final zak zapz) {
            this.zapz = zapz;
        }
        
        public final Field<I, O> zacl() {
            return new Field<I, O>(this.zalf, this.zapr, this.zaps, this.zapt, this.zapu, this.zapv, this.zapw, this.zapy, this.zaco());
        }
        
        public final boolean zacn() {
            return this.zaqa != null;
        }
        
        public final FastJsonResponse zacp() throws InstantiationException, IllegalAccessException {
            if (this.zapx == SafeParcelResponse.class) {
                Preconditions.checkNotNull((Object)this.zapz, (Object)"The field mapping dictionary must be set if the concrete type is a SafeParcelResponse object.");
                return new SafeParcelResponse(this.zapz, this.zapy);
            }
            return (FastJsonResponse)this.zapx.newInstance();
        }
        
        public final Map<String, Field<?, ?>> zacq() {
            Preconditions.checkNotNull((Object)this.zapy);
            Preconditions.checkNotNull((Object)this.zapz);
            return this.zapz.zai(this.zapy);
        }
    }
    
    @ShowFirstParty
    public interface FieldConverter<I, O>
    {
        O convert(final I p0);
        
        I convertBack(final O p0);
        
        int zacj();
        
        int zack();
    }
}
