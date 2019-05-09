package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Objects.ToStringHelper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.converter.zaa;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
@KeepForSdk
public abstract class FastJsonResponse {

    @ShowFirstParty
    public interface FieldConverter<I, O> {
        O convert(I i);

        I convertBack(O o);

        int zacj();

        int zack();
    }

    @ShowFirstParty
    @Class(creator = "FieldCreator")
    @VisibleForTesting
    @KeepForSdk
    public static class Field<I, O> extends AbstractSafeParcelable {
        public static final zai CREATOR = new zai();
        @VersionField(getter = "getVersionCode", id = 1)
        private final int zalf;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getTypeIn", id = 2)
        protected final int zapr;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "isTypeInArray", id = 3)
        protected final boolean zaps;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getTypeOut", id = 4)
        protected final int zapt;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "isTypeOutArray", id = 5)
        protected final boolean zapu;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getOutputFieldName", id = 6)
        protected final String zapv;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getSafeParcelableFieldId", id = 7)
        protected final int zapw;
        protected final Class<? extends FastJsonResponse> zapx;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getConcreteTypeName", id = 8)
        private final String zapy;
        private zak zapz;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getWrappedConverter", id = 9, type = "com.google.android.gms.common.server.converter.ConverterWrapper")
        private FieldConverter<I, O> zaqa;

        @Constructor
        Field(@Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) boolean z, @Param(id = 4) int i3, @Param(id = 5) boolean z2, @Param(id = 6) String str, @Param(id = 7) int i4, @Param(id = 8) String str2, @Param(id = 9) zaa zaa) {
            this.zalf = i;
            this.zapr = i2;
            this.zaps = z;
            this.zapt = i3;
            this.zapu = z2;
            this.zapv = str;
            this.zapw = i4;
            if (str2 == null) {
                this.zapx = null;
                this.zapy = null;
            } else {
                this.zapx = SafeParcelResponse.class;
                this.zapy = str2;
            }
            if (zaa == null) {
                this.zaqa = null;
            } else {
                this.zaqa = zaa.zaci();
            }
        }

        private Field(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends FastJsonResponse> cls, FieldConverter<I, O> fieldConverter) {
            this.zalf = 1;
            this.zapr = i;
            this.zaps = z;
            this.zapt = i2;
            this.zapu = z2;
            this.zapv = str;
            this.zapw = i3;
            this.zapx = cls;
            if (cls == null) {
                this.zapy = null;
            } else {
                this.zapy = cls.getCanonicalName();
            }
            this.zaqa = fieldConverter;
        }

        public final Field<I, O> zacl() {
            return new Field(this.zalf, this.zapr, this.zaps, this.zapt, this.zapu, this.zapv, this.zapw, this.zapy, zaco());
        }

        @KeepForSdk
        public int getSafeParcelableFieldId() {
            return this.zapw;
        }

        private final String zacm() {
            if (this.zapy == null) {
                return null;
            }
            return this.zapy;
        }

        public final boolean zacn() {
            return this.zaqa != null;
        }

        public final void zaa(zak zak) {
            this.zapz = zak;
        }

        private final zaa zaco() {
            if (this.zaqa == null) {
                return null;
            }
            return zaa.zaa(this.zaqa);
        }

        public final FastJsonResponse zacp() throws InstantiationException, IllegalAccessException {
            if (this.zapx != SafeParcelResponse.class) {
                return (FastJsonResponse) this.zapx.newInstance();
            }
            Preconditions.checkNotNull(this.zapz, "The field mapping dictionary must be set if the concrete type is a SafeParcelResponse object.");
            return new SafeParcelResponse(this.zapz, this.zapy);
        }

        public final Map<String, Field<?, ?>> zacq() {
            Preconditions.checkNotNull(this.zapy);
            Preconditions.checkNotNull(this.zapz);
            return this.zapz.zai(this.zapy);
        }

        public final O convert(I i) {
            return this.zaqa.convert(i);
        }

        public final I convertBack(O o) {
            return this.zaqa.convertBack(o);
        }

        @KeepForSdk
        @VisibleForTesting
        public static Field<Integer, Integer> forInteger(String str, int i) {
            return new Field(0, false, 0, false, str, i, null, null);
        }

        @KeepForSdk
        public static Field<Long, Long> forLong(String str, int i) {
            return new Field(2, false, 2, false, str, i, null, null);
        }

        @KeepForSdk
        public static Field<Float, Float> forFloat(String str, int i) {
            return new Field(3, false, 3, false, str, i, null, null);
        }

        @KeepForSdk
        public static Field<Double, Double> forDouble(String str, int i) {
            return new Field(4, false, 4, false, str, i, null, null);
        }

        @KeepForSdk
        public static Field<Boolean, Boolean> forBoolean(String str, int i) {
            return new Field(6, false, 6, false, str, i, null, null);
        }

        @KeepForSdk
        public static Field<String, String> forString(String str, int i) {
            return new Field(7, false, 7, false, str, i, null, null);
        }

        @KeepForSdk
        public static Field<ArrayList<String>, ArrayList<String>> forStrings(String str, int i) {
            return new Field(7, true, 7, true, str, i, null, null);
        }

        @KeepForSdk
        @VisibleForTesting
        public static Field<byte[], byte[]> forBase64(String str, int i) {
            return new Field(8, false, 8, false, str, i, null, null);
        }

        @KeepForSdk
        public static <T extends FastJsonResponse> Field<T, T> forConcreteType(String str, int i, Class<T> cls) {
            return new Field(11, false, 11, false, str, i, cls, null);
        }

        @KeepForSdk
        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> forConcreteTypeArray(String str, int i, Class<T> cls) {
            return new Field(11, true, 11, true, str, i, cls, null);
        }

        @KeepForSdk
        public static Field withConverter(String str, int i, FieldConverter<?, ?> fieldConverter, boolean z) {
            return new Field(fieldConverter.zacj(), z, fieldConverter.zack(), false, str, i, null, fieldConverter);
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, this.zalf);
            SafeParcelWriter.writeInt(parcel, 2, this.zapr);
            SafeParcelWriter.writeBoolean(parcel, 3, this.zaps);
            SafeParcelWriter.writeInt(parcel, 4, this.zapt);
            SafeParcelWriter.writeBoolean(parcel, 5, this.zapu);
            SafeParcelWriter.writeString(parcel, 6, this.zapv, false);
            SafeParcelWriter.writeInt(parcel, 7, getSafeParcelableFieldId());
            SafeParcelWriter.writeString(parcel, 8, zacm(), false);
            SafeParcelWriter.writeParcelable(parcel, 9, zaco(), i, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }

        public String toString() {
            ToStringHelper add = Objects.toStringHelper(this).add("versionCode", Integer.valueOf(this.zalf)).add("typeIn", Integer.valueOf(this.zapr)).add("typeInArray", Boolean.valueOf(this.zaps)).add("typeOut", Integer.valueOf(this.zapt)).add("typeOutArray", Boolean.valueOf(this.zapu)).add("outputFieldName", this.zapv).add("safeParcelFieldId", Integer.valueOf(this.zapw)).add("concreteTypeName", zacm());
            Class cls = this.zapx;
            if (cls != null) {
                add.add("concreteType.class", cls.getCanonicalName());
            }
            if (this.zaqa != null) {
                add.add("converterName", this.zaqa.getClass().getCanonicalName());
            }
            return add.toString();
        }
    }

    @KeepForSdk
    public abstract Map<String, Field<?, ?>> getFieldMappings();

    @KeepForSdk
    protected abstract Object getValueObject(String str);

    @KeepForSdk
    protected abstract boolean isPrimitiveFieldSet(String str);

    @KeepForSdk
    protected boolean isFieldSet(Field field) {
        if (field.zapt != 11) {
            return isPrimitiveFieldSet(field.zapv);
        }
        if (field.zapu) {
            String str = field.zapv;
            throw new UnsupportedOperationException("Concrete type arrays not supported");
        }
        str = field.zapv;
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    private final <I, O> void zaa(Field<I, O> field, I i) {
        String str = field.zapv;
        Object convert = field.convert(i);
        switch (field.zapt) {
            case 0:
                if (zaa(str, convert)) {
                    setIntegerInternal(field, str, ((Integer) convert).intValue());
                    return;
                }
                return;
            case 1:
                zaa((Field) field, str, (BigInteger) convert);
                return;
            case 2:
                if (zaa(str, convert)) {
                    setLongInternal(field, str, ((Long) convert).longValue());
                    return;
                }
                return;
            case 4:
                if (zaa(str, convert)) {
                    zaa((Field) field, str, ((Double) convert).doubleValue());
                    return;
                }
                return;
            case 5:
                zaa((Field) field, str, (BigDecimal) convert);
                return;
            case 6:
                if (zaa(str, convert)) {
                    setBooleanInternal(field, str, ((Boolean) convert).booleanValue());
                    return;
                }
                return;
            case 7:
                setStringInternal(field, str, (String) convert);
                return;
            case 8:
            case 9:
                if (zaa(str, convert)) {
                    setDecodedBytesInternal(field, str, (byte[]) convert);
                    return;
                }
                return;
            default:
                throw new IllegalStateException("Unsupported type for conversion: " + field.zapt);
        }
    }

    protected static <O, I> I zab(Field<I, O> field, Object obj) {
        if (field.zaqa != null) {
            return field.convertBack(obj);
        }
        return obj;
    }

    public final <O> void zaa(Field<Integer, O> field, int i) {
        if (field.zaqa != null) {
            zaa((Field) field, Integer.valueOf(i));
        } else {
            setIntegerInternal(field, field.zapv, i);
        }
    }

    public final <O> void zaa(Field<ArrayList<Integer>, O> field, ArrayList<Integer> arrayList) {
        if (field.zaqa != null) {
            zaa((Field) field, (Object) arrayList);
        } else {
            zaa((Field) field, field.zapv, (ArrayList) arrayList);
        }
    }

    public final <O> void zaa(Field<BigInteger, O> field, BigInteger bigInteger) {
        if (field.zaqa != null) {
            zaa((Field) field, (Object) bigInteger);
        } else {
            zaa((Field) field, field.zapv, bigInteger);
        }
    }

    public final <O> void zab(Field<ArrayList<BigInteger>, O> field, ArrayList<BigInteger> arrayList) {
        if (field.zaqa != null) {
            zaa((Field) field, (Object) arrayList);
        } else {
            zab(field, field.zapv, arrayList);
        }
    }

    public final <O> void zaa(Field<Long, O> field, long j) {
        if (field.zaqa != null) {
            zaa((Field) field, Long.valueOf(j));
        } else {
            setLongInternal(field, field.zapv, j);
        }
    }

    public final <O> void zac(Field<ArrayList<Long>, O> field, ArrayList<Long> arrayList) {
        if (field.zaqa != null) {
            zaa((Field) field, (Object) arrayList);
        } else {
            zac(field, field.zapv, arrayList);
        }
    }

    public final <O> void zaa(Field<Float, O> field, float f) {
        if (field.zaqa != null) {
            zaa((Field) field, Float.valueOf(f));
        } else {
            zaa((Field) field, field.zapv, f);
        }
    }

    public final <O> void zad(Field<ArrayList<Float>, O> field, ArrayList<Float> arrayList) {
        if (field.zaqa != null) {
            zaa((Field) field, (Object) arrayList);
        } else {
            zad(field, field.zapv, arrayList);
        }
    }

    public final <O> void zaa(Field<Double, O> field, double d) {
        if (field.zaqa != null) {
            zaa((Field) field, Double.valueOf(d));
        } else {
            zaa((Field) field, field.zapv, d);
        }
    }

    public final <O> void zae(Field<ArrayList<Double>, O> field, ArrayList<Double> arrayList) {
        if (field.zaqa != null) {
            zaa((Field) field, (Object) arrayList);
        } else {
            zae(field, field.zapv, arrayList);
        }
    }

    public final <O> void zaa(Field<BigDecimal, O> field, BigDecimal bigDecimal) {
        if (field.zaqa != null) {
            zaa((Field) field, (Object) bigDecimal);
        } else {
            zaa((Field) field, field.zapv, bigDecimal);
        }
    }

    public final <O> void zaf(Field<ArrayList<BigDecimal>, O> field, ArrayList<BigDecimal> arrayList) {
        if (field.zaqa != null) {
            zaa((Field) field, (Object) arrayList);
        } else {
            zaf(field, field.zapv, arrayList);
        }
    }

    public final <O> void zaa(Field<Boolean, O> field, boolean z) {
        if (field.zaqa != null) {
            zaa((Field) field, Boolean.valueOf(z));
        } else {
            setBooleanInternal(field, field.zapv, z);
        }
    }

    public final <O> void zag(Field<ArrayList<Boolean>, O> field, ArrayList<Boolean> arrayList) {
        if (field.zaqa != null) {
            zaa((Field) field, (Object) arrayList);
        } else {
            zag(field, field.zapv, arrayList);
        }
    }

    public final <O> void zaa(Field<String, O> field, String str) {
        if (field.zaqa != null) {
            zaa((Field) field, (Object) str);
        } else {
            setStringInternal(field, field.zapv, str);
        }
    }

    public final <O> void zah(Field<ArrayList<String>, O> field, ArrayList<String> arrayList) {
        if (field.zaqa != null) {
            zaa((Field) field, (Object) arrayList);
        } else {
            setStringsInternal(field, field.zapv, arrayList);
        }
    }

    public final <O> void zaa(Field<byte[], O> field, byte[] bArr) {
        if (field.zaqa != null) {
            zaa((Field) field, (Object) bArr);
        } else {
            setDecodedBytesInternal(field, field.zapv, bArr);
        }
    }

    public final <O> void zaa(Field<Map<String, String>, O> field, Map<String, String> map) {
        if (field.zaqa != null) {
            zaa((Field) field, (Object) map);
        } else {
            zaa((Field) field, field.zapv, (Map) map);
        }
    }

    @KeepForSdk
    protected void setIntegerInternal(Field<?, ?> field, String str, int i) {
        throw new UnsupportedOperationException("Integer not supported");
    }

    protected void zaa(Field<?, ?> field, String str, ArrayList<Integer> arrayList) {
        throw new UnsupportedOperationException("Integer list not supported");
    }

    protected void zaa(Field<?, ?> field, String str, BigInteger bigInteger) {
        throw new UnsupportedOperationException("BigInteger not supported");
    }

    protected void zab(Field<?, ?> field, String str, ArrayList<BigInteger> arrayList) {
        throw new UnsupportedOperationException("BigInteger list not supported");
    }

    @KeepForSdk
    protected void setLongInternal(Field<?, ?> field, String str, long j) {
        throw new UnsupportedOperationException("Long not supported");
    }

    protected void zac(Field<?, ?> field, String str, ArrayList<Long> arrayList) {
        throw new UnsupportedOperationException("Long list not supported");
    }

    protected void zaa(Field<?, ?> field, String str, float f) {
        throw new UnsupportedOperationException("Float not supported");
    }

    protected void zad(Field<?, ?> field, String str, ArrayList<Float> arrayList) {
        throw new UnsupportedOperationException("Float list not supported");
    }

    protected void zaa(Field<?, ?> field, String str, double d) {
        throw new UnsupportedOperationException("Double not supported");
    }

    protected void zae(Field<?, ?> field, String str, ArrayList<Double> arrayList) {
        throw new UnsupportedOperationException("Double list not supported");
    }

    protected void zaa(Field<?, ?> field, String str, BigDecimal bigDecimal) {
        throw new UnsupportedOperationException("BigDecimal not supported");
    }

    protected void zaf(Field<?, ?> field, String str, ArrayList<BigDecimal> arrayList) {
        throw new UnsupportedOperationException("BigDecimal list not supported");
    }

    @KeepForSdk
    protected void setBooleanInternal(Field<?, ?> field, String str, boolean z) {
        throw new UnsupportedOperationException("Boolean not supported");
    }

    protected void zag(Field<?, ?> field, String str, ArrayList<Boolean> arrayList) {
        throw new UnsupportedOperationException("Boolean list not supported");
    }

    @KeepForSdk
    protected void setStringInternal(Field<?, ?> field, String str, String str2) {
        throw new UnsupportedOperationException("String not supported");
    }

    @KeepForSdk
    protected void setStringsInternal(Field<?, ?> field, String str, ArrayList<String> arrayList) {
        throw new UnsupportedOperationException("String list not supported");
    }

    @KeepForSdk
    protected void setDecodedBytesInternal(Field<?, ?> field, String str, byte[] bArr) {
        throw new UnsupportedOperationException("byte[] not supported");
    }

    protected void zaa(Field<?, ?> field, String str, Map<String, String> map) {
        throw new UnsupportedOperationException("String map not supported");
    }

    private static <O> boolean zaa(String str, O o) {
        if (o != null) {
            return true;
        }
        if (Log.isLoggable("FastJsonResponse", 6)) {
            Log.e("FastJsonResponse", new StringBuilder(String.valueOf(str).length() + 58).append("Output field (").append(str).append(") has a null value, but expected a primitive").toString());
        }
        return false;
    }

    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String str, T t) {
        throw new UnsupportedOperationException("Concrete type not supported");
    }

    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(Field<?, ?> field, String str, ArrayList<T> arrayList) {
        throw new UnsupportedOperationException("Concrete type array not supported");
    }

    @KeepForSdk
    public String toString() {
        Map fieldMappings = getFieldMappings();
        StringBuilder stringBuilder = new StringBuilder(100);
        for (String str : fieldMappings.keySet()) {
            Field field = (Field) fieldMappings.get(str);
            if (isFieldSet(field)) {
                Object zab = zab(field, getFieldValue(field));
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("{");
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append("\"").append(str).append("\":");
                if (zab != null) {
                    switch (field.zapt) {
                        case 8:
                            stringBuilder.append("\"").append(Base64Utils.encode((byte[]) zab)).append("\"");
                            break;
                        case 9:
                            stringBuilder.append("\"").append(Base64Utils.encodeUrlSafe((byte[]) zab)).append("\"");
                            break;
                        case 10:
                            MapUtils.writeStringMapToJson(stringBuilder, (HashMap) zab);
                            break;
                        default:
                            if (!field.zaps) {
                                zaa(stringBuilder, field, zab);
                                break;
                            }
                            ArrayList arrayList = (ArrayList) zab;
                            stringBuilder.append(RequestParameters.LEFT_BRACKETS);
                            int size = arrayList.size();
                            for (int i = 0; i < size; i++) {
                                if (i > 0) {
                                    stringBuilder.append(",");
                                }
                                Object obj = arrayList.get(i);
                                if (obj != null) {
                                    zaa(stringBuilder, field, obj);
                                }
                            }
                            stringBuilder.append(RequestParameters.RIGHT_BRACKETS);
                            break;
                    }
                }
                stringBuilder.append("null");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append("}");
        } else {
            stringBuilder.append("{}");
        }
        return stringBuilder.toString();
    }

    @KeepForSdk
    protected Object getFieldValue(Field field) {
        String str = field.zapv;
        if (field.zapx == null) {
            return getValueObject(field.zapv);
        }
        Preconditions.checkState(getValueObject(field.zapv) == null, "Concrete field shouldn't be value object: %s", new Object[]{field.zapv});
        boolean z = field.zapu;
        try {
            char toUpperCase = Character.toUpperCase(str.charAt(0));
            String substring = str.substring(1);
            return getClass().getMethod(new StringBuilder(String.valueOf(substring).length() + 4).append("get").append(toUpperCase).append(substring).toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static void zaa(StringBuilder stringBuilder, Field field, Object obj) {
        if (field.zapr == 11) {
            stringBuilder.append(((FastJsonResponse) field.zapx.cast(obj)).toString());
        } else if (field.zapr == 7) {
            stringBuilder.append("\"");
            stringBuilder.append(JsonUtils.escapeString((String) obj));
            stringBuilder.append("\"");
        } else {
            stringBuilder.append(obj);
        }
    }
}
