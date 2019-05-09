package com.google.android.gms.common.server.response;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

@ShowFirstParty
@KeepForSdk
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zaqg = new char[]{'u', 'l', 'l'};
    private static final char[] zaqh = new char[]{'r', 'u', 'e'};
    private static final char[] zaqi = new char[]{'r', 'u', 'e', '\"'};
    private static final char[] zaqj = new char[]{'a', 'l', 's', 'e'};
    private static final char[] zaqk = new char[]{'a', 'l', 's', 'e', '\"'};
    private static final char[] zaql = new char[]{'\n'};
    private static final zaa<Integer> zaqn = new zaa();
    private static final zaa<Long> zaqo = new zab();
    private static final zaa<Float> zaqp = new zac();
    private static final zaa<Double> zaqq = new zad();
    private static final zaa<Boolean> zaqr = new zae();
    private static final zaa<String> zaqs = new zaf();
    private static final zaa<BigInteger> zaqt = new zag();
    private static final zaa<BigDecimal> zaqu = new zah();
    private final char[] zaqb = new char[1];
    private final char[] zaqc = new char[32];
    private final char[] zaqd = new char[1024];
    private final StringBuilder zaqe = new StringBuilder(32);
    private final StringBuilder zaqf = new StringBuilder(1024);
    private final Stack<Integer> zaqm = new Stack();

    @ShowFirstParty
    @KeepForSdk
    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super(str, th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    private interface zaa<O> {
        O zah(FastParser fastParser, BufferedReader bufferedReader) throws ParseException, IOException;
    }

    @KeepForSdk
    public void parse(InputStream inputStream, T t) throws ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        try {
            this.zaqm.push(Integer.valueOf(0));
            char zaj = zaj(bufferedReader);
            switch (zaj) {
                case '\u0000':
                    throw new ParseException("No data to parse");
                case '[':
                    this.zaqm.push(Integer.valueOf(5));
                    Map fieldMappings = t.getFieldMappings();
                    if (fieldMappings.size() == 1) {
                        Field field = (Field) ((Entry) fieldMappings.entrySet().iterator().next()).getValue();
                        t.addConcreteTypeArrayInternal(field, field.zapv, zaa(bufferedReader, field));
                        break;
                    }
                    throw new ParseException("Object array response class must have a single Field");
                case '{':
                    this.zaqm.push(Integer.valueOf(1));
                    zaa(bufferedReader, (FastJsonResponse) t);
                    break;
                default:
                    throw new ParseException("Unexpected token: " + zaj);
            }
            zak(0);
            try {
                bufferedReader.close();
            } catch (IOException e) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
        } catch (Throwable e2) {
            throw new ParseException(e2);
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException e3) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
        }
    }

    private final boolean zaa(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        Map fieldMappings = fastJsonResponse.getFieldMappings();
        Object zaa = zaa(bufferedReader);
        if (zaa == null) {
            zak(1);
            return false;
        }
        while (zaa != null) {
            Field field = (Field) fieldMappings.get(zaa);
            if (field == null) {
                zaa = zab(bufferedReader);
            } else {
                this.zaqm.push(Integer.valueOf(4));
                char zaj;
                switch (field.zapr) {
                    case 0:
                        if (!field.zaps) {
                            fastJsonResponse.zaa(field, zad(bufferedReader));
                            break;
                        }
                        fastJsonResponse.zaa(field, zaa(bufferedReader, zaqn));
                        break;
                    case 1:
                        if (!field.zaps) {
                            fastJsonResponse.zaa(field, zaf(bufferedReader));
                            break;
                        }
                        fastJsonResponse.zab(field, zaa(bufferedReader, zaqt));
                        break;
                    case 2:
                        if (!field.zaps) {
                            fastJsonResponse.zaa(field, zae(bufferedReader));
                            break;
                        }
                        fastJsonResponse.zac(field, zaa(bufferedReader, zaqo));
                        break;
                    case 3:
                        if (!field.zaps) {
                            fastJsonResponse.zaa(field, zag(bufferedReader));
                            break;
                        }
                        fastJsonResponse.zad(field, zaa(bufferedReader, zaqp));
                        break;
                    case 4:
                        if (!field.zaps) {
                            fastJsonResponse.zaa(field, zah(bufferedReader));
                            break;
                        }
                        fastJsonResponse.zae(field, zaa(bufferedReader, zaqq));
                        break;
                    case 5:
                        if (!field.zaps) {
                            fastJsonResponse.zaa(field, zai(bufferedReader));
                            break;
                        }
                        fastJsonResponse.zaf(field, zaa(bufferedReader, zaqu));
                        break;
                    case 6:
                        if (!field.zaps) {
                            fastJsonResponse.zaa(field, zaa(bufferedReader, false));
                            break;
                        }
                        fastJsonResponse.zag(field, zaa(bufferedReader, zaqr));
                        break;
                    case 7:
                        if (!field.zaps) {
                            fastJsonResponse.zaa(field, zac(bufferedReader));
                            break;
                        }
                        fastJsonResponse.zah(field, zaa(bufferedReader, zaqs));
                        break;
                    case 8:
                        fastJsonResponse.zaa(field, Base64Utils.decode(zaa(bufferedReader, this.zaqd, this.zaqf, zaql)));
                        break;
                    case 9:
                        fastJsonResponse.zaa(field, Base64Utils.decodeUrlSafe(zaa(bufferedReader, this.zaqd, this.zaqf, zaql)));
                        break;
                    case 10:
                        Map hashMap;
                        zaj = zaj(bufferedReader);
                        if (zaj != 'n') {
                            if (zaj == '{') {
                                this.zaqm.push(Integer.valueOf(1));
                                hashMap = new HashMap();
                                while (true) {
                                    switch (zaj(bufferedReader)) {
                                        case '\u0000':
                                            throw new ParseException("Unexpected EOF");
                                        case '\"':
                                            String zab = zab(bufferedReader, this.zaqc, this.zaqe, null);
                                            String str;
                                            String valueOf;
                                            if (zaj(bufferedReader) == ':') {
                                                if (zaj(bufferedReader) == '\"') {
                                                    hashMap.put(zab, zab(bufferedReader, this.zaqc, this.zaqe, null));
                                                    char zaj2 = zaj(bufferedReader);
                                                    if (zaj2 == ',') {
                                                        break;
                                                    } else if (zaj2 == '}') {
                                                        zak(1);
                                                        break;
                                                    } else {
                                                        throw new ParseException("Unexpected character while parsing string map: " + zaj2);
                                                    }
                                                }
                                                str = "Expected String value for key ";
                                                valueOf = String.valueOf(zab);
                                                throw new ParseException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                                            }
                                            str = "No map value found for key ";
                                            valueOf = String.valueOf(zab);
                                            throw new ParseException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                                        case '}':
                                            zak(1);
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                            throw new ParseException("Expected start of a map object");
                        }
                        zab(bufferedReader, zaqg);
                        hashMap = null;
                        fastJsonResponse.zaa(field, hashMap);
                        break;
                    case 11:
                        if (field.zaps) {
                            zaj = zaj(bufferedReader);
                            if (zaj != 'n') {
                                this.zaqm.push(Integer.valueOf(5));
                                if (zaj == '[') {
                                    fastJsonResponse.addConcreteTypeArrayInternal(field, field.zapv, zaa(bufferedReader, field));
                                    break;
                                }
                                throw new ParseException("Expected array start");
                            }
                            zab(bufferedReader, zaqg);
                            fastJsonResponse.addConcreteTypeArrayInternal(field, field.zapv, null);
                            break;
                        }
                        zaj = zaj(bufferedReader);
                        if (zaj == 'n') {
                            zab(bufferedReader, zaqg);
                            fastJsonResponse.addConcreteTypeInternal(field, field.zapv, null);
                            break;
                        }
                        this.zaqm.push(Integer.valueOf(1));
                        if (zaj != '{') {
                            throw new ParseException("Expected start of object");
                        }
                        try {
                            FastJsonResponse zacp = field.zacp();
                            zaa(bufferedReader, zacp);
                            fastJsonResponse.addConcreteTypeInternal(field, field.zapv, zacp);
                            break;
                        } catch (Throwable e) {
                            throw new ParseException("Error instantiating inner object", e);
                        } catch (Throwable e2) {
                            throw new ParseException("Error instantiating inner object", e2);
                        }
                    default:
                        throw new ParseException("Invalid field type " + field.zapr);
                }
                zak(4);
                zak(2);
                char zaj3 = zaj(bufferedReader);
                switch (zaj3) {
                    case ',':
                        zaa = zaa(bufferedReader);
                        break;
                    case '}':
                        zaa = null;
                        break;
                    default:
                        throw new ParseException("Expected end of object or field separator, but found: " + zaj3);
                }
            }
        }
        zak(1);
        return true;
    }

    private final String zaa(BufferedReader bufferedReader) throws ParseException, IOException {
        String str = null;
        this.zaqm.push(Integer.valueOf(2));
        char zaj = zaj(bufferedReader);
        switch (zaj) {
            case '\"':
                this.zaqm.push(Integer.valueOf(3));
                str = zab(bufferedReader, this.zaqc, this.zaqe, null);
                zak(3);
                if (zaj(bufferedReader) != ':') {
                    throw new ParseException("Expected key/value separator");
                }
                break;
            case ']':
                zak(2);
                zak(1);
                zak(5);
                break;
            case '}':
                zak(2);
                break;
            default:
                throw new ParseException("Unexpected token: " + zaj);
        }
        return str;
    }

    private final String zab(BufferedReader bufferedReader) throws ParseException, IOException {
        char c;
        bufferedReader.mark(1024);
        int i;
        int i2;
        switch (zaj(bufferedReader)) {
            case '\"':
                if (bufferedReader.read(this.zaqb) != -1) {
                    c = this.zaqb[0];
                    i = 0;
                    while (true) {
                        if (c == '\"' && i == 0) {
                            break;
                        }
                        i2 = c == '\\' ? i == 0 ? 1 : 0 : 0;
                        if (bufferedReader.read(this.zaqb) == -1) {
                            throw new ParseException("Unexpected EOF while parsing string");
                        }
                        char c2 = this.zaqb[0];
                        if (Character.isISOControl(c2)) {
                            throw new ParseException("Unexpected control character while reading string");
                        }
                        char c3 = c2;
                        i = i2;
                        c = c3;
                    }
                } else {
                    throw new ParseException("Unexpected EOF while parsing string");
                }
                break;
            case ',':
                throw new ParseException("Missing value");
            case '[':
                this.zaqm.push(Integer.valueOf(5));
                bufferedReader.mark(32);
                if (zaj(bufferedReader) != ']') {
                    bufferedReader.reset();
                    i = 1;
                    i2 = 0;
                    int i3 = 0;
                    while (i > 0) {
                        char zaj = zaj(bufferedReader);
                        if (zaj == '\u0000') {
                            throw new ParseException("Unexpected EOF while parsing array");
                        } else if (Character.isISOControl(zaj)) {
                            throw new ParseException("Unexpected control character while reading array");
                        } else {
                            int i4;
                            if (zaj == '\"' && i3 == 0) {
                                i4 = i2 == 0 ? 1 : 0;
                            } else {
                                i4 = i2;
                            }
                            if (zaj == '[' && i4 == 0) {
                                i2 = i + 1;
                            } else {
                                i2 = i;
                            }
                            if (zaj == ']' && i4 == 0) {
                                i = i2 - 1;
                            } else {
                                i = i2;
                            }
                            if (zaj != '\\' || i4 == 0) {
                                i2 = i4;
                                i3 = 0;
                            } else {
                                i3 = i3 == 0 ? 1 : 0;
                                i2 = i4;
                            }
                        }
                    }
                    zak(5);
                    break;
                }
                zak(5);
                break;
                break;
            case '{':
                this.zaqm.push(Integer.valueOf(1));
                bufferedReader.mark(32);
                c = zaj(bufferedReader);
                if (c == '}') {
                    zak(1);
                    break;
                } else if (c == '\"') {
                    bufferedReader.reset();
                    zaa(bufferedReader);
                    do {
                    } while (zab(bufferedReader) != null);
                    zak(1);
                    break;
                } else {
                    throw new ParseException("Unexpected token " + c);
                }
            default:
                bufferedReader.reset();
                zaa(bufferedReader, this.zaqd);
                break;
        }
        c = zaj(bufferedReader);
        switch (c) {
            case ',':
                zak(2);
                return zaa(bufferedReader);
            case '}':
                zak(2);
                return null;
            default:
                throw new ParseException("Unexpected token " + c);
        }
    }

    private final String zac(BufferedReader bufferedReader) throws ParseException, IOException {
        return zaa(bufferedReader, this.zaqc, this.zaqe, null);
    }

    private final <O> ArrayList<O> zaa(BufferedReader bufferedReader, zaa<O> zaa) throws ParseException, IOException {
        char zaj = zaj(bufferedReader);
        if (zaj != 'n') {
            if (zaj == '[') {
                this.zaqm.push(Integer.valueOf(5));
                ArrayList<O> arrayList = new ArrayList();
                while (true) {
                    bufferedReader.mark(1024);
                    switch (zaj(bufferedReader)) {
                        case '\u0000':
                            throw new ParseException("Unexpected EOF");
                        case ',':
                            break;
                        case ']':
                            zak(5);
                            return arrayList;
                        default:
                            bufferedReader.reset();
                            arrayList.add(zaa.zah(this, bufferedReader));
                            break;
                    }
                }
            }
            throw new ParseException("Expected start of array");
        }
        zab(bufferedReader, zaqg);
        return null;
    }

    private final String zaa(BufferedReader bufferedReader, char[] cArr, StringBuilder stringBuilder, char[] cArr2) throws ParseException, IOException {
        switch (zaj(bufferedReader)) {
            case '\"':
                return zab(bufferedReader, cArr, stringBuilder, cArr2);
            case 'n':
                zab(bufferedReader, zaqg);
                return null;
            default:
                throw new ParseException("Expected string");
        }
    }

    private static String zab(BufferedReader bufferedReader, char[] cArr, StringBuilder stringBuilder, char[] cArr2) throws ParseException, IOException {
        int i;
        stringBuilder.setLength(0);
        bufferedReader.mark(cArr.length);
        int i2 = 0;
        int i3 = 0;
        loop0:
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read != -1) {
                i = 0;
                while (i < read) {
                    char c = cArr[i];
                    if (Character.isISOControl(c)) {
                        int i4;
                        if (cArr2 != null) {
                            for (char c2 : cArr2) {
                                if (c2 == c) {
                                    i4 = 1;
                                    break;
                                }
                            }
                        }
                        i4 = 0;
                        if (i4 == 0) {
                            throw new ParseException("Unexpected control character while reading string");
                        }
                    }
                    if (c == '\"' && i3 == 0) {
                        break loop0;
                    }
                    if (c == '\\') {
                        if (i3 == 0) {
                            i2 = 1;
                        } else {
                            i2 = 0;
                        }
                        i3 = i2;
                        i2 = 1;
                    } else {
                        i3 = 0;
                    }
                    i++;
                }
                stringBuilder.append(cArr, 0, read);
                bufferedReader.mark(cArr.length);
            } else {
                throw new ParseException("Unexpected EOF while parsing string");
            }
        }
        stringBuilder.append(cArr, 0, i);
        bufferedReader.reset();
        bufferedReader.skip((long) (i + 1));
        if (i2 != 0) {
            return JsonUtils.unescapeString(stringBuilder.toString());
        }
        return stringBuilder.toString();
    }

    private final int zad(BufferedReader bufferedReader) throws ParseException, IOException {
        int i = 0;
        int zaa = zaa(bufferedReader, this.zaqd);
        if (zaa == 0) {
            return 0;
        }
        char[] cArr = this.zaqd;
        if (zaa > 0) {
            int i2;
            int i3;
            int i4;
            if (cArr[0] == '-') {
                i2 = Integer.MIN_VALUE;
                i3 = 1;
                i4 = 1;
            } else {
                i2 = -2147483647;
                i3 = 0;
                i4 = 0;
            }
            if (i4 < zaa) {
                i = i4 + 1;
                i4 = Character.digit(cArr[i4], 10);
                if (i4 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                int i5 = i;
                i = -i4;
                i4 = i5;
            }
            while (i4 < zaa) {
                int i6 = i4 + 1;
                i4 = Character.digit(cArr[i4], 10);
                if (i4 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                } else if (i < -214748364) {
                    throw new ParseException("Number too large");
                } else {
                    i *= 10;
                    if (i < i2 + i4) {
                        throw new ParseException("Number too large");
                    }
                    i -= i4;
                    i4 = i6;
                }
            }
            if (i3 == 0) {
                return -i;
            }
            if (i4 > 1) {
                return i;
            }
            throw new ParseException("No digits to parse");
        }
        throw new ParseException("No number to parse");
    }

    private final long zae(BufferedReader bufferedReader) throws ParseException, IOException {
        long j = 0;
        int zaa = zaa(bufferedReader, this.zaqd);
        if (zaa == 0) {
            return 0;
        }
        char[] cArr = this.zaqd;
        if (zaa > 0) {
            long j2;
            int i;
            int i2;
            if (cArr[0] == '-') {
                j2 = Long.MIN_VALUE;
                i = 1;
                i2 = 1;
            } else {
                i = 0;
                j2 = -9223372036854775807L;
                i2 = 0;
            }
            if (i2 < zaa) {
                int i3 = i2 + 1;
                int digit = Character.digit(cArr[i2], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                int i4 = i3;
                j = (long) (-digit);
                i2 = i4;
            }
            while (i2 < zaa) {
                int i5 = i2 + 1;
                i2 = Character.digit(cArr[i2], 10);
                if (i2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                } else if (j < -922337203685477580L) {
                    throw new ParseException("Number too large");
                } else {
                    j *= 10;
                    if (j < ((long) i2) + j2) {
                        throw new ParseException("Number too large");
                    }
                    j -= (long) i2;
                    i2 = i5;
                }
            }
            if (i == 0) {
                return -j;
            }
            if (i2 > 1) {
                return j;
            }
            throw new ParseException("No digits to parse");
        }
        throw new ParseException("No number to parse");
    }

    private final BigInteger zaf(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa = zaa(bufferedReader, this.zaqd);
        if (zaa == 0) {
            return null;
        }
        return new BigInteger(new String(this.zaqd, 0, zaa));
    }

    private final boolean zaa(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        while (true) {
            char zaj = zaj(bufferedReader);
            switch (zaj) {
                case '\"':
                    if (z) {
                        throw new ParseException("No boolean value found in string");
                    }
                    z = true;
                case 'f':
                    zab(bufferedReader, z ? zaqk : zaqj);
                    return false;
                case 'n':
                    zab(bufferedReader, zaqg);
                    return false;
                case 't':
                    zab(bufferedReader, z ? zaqi : zaqh);
                    return true;
                default:
                    throw new ParseException("Unexpected token: " + zaj);
            }
        }
    }

    private final float zag(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa = zaa(bufferedReader, this.zaqd);
        if (zaa == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.zaqd, 0, zaa));
    }

    private final double zah(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa = zaa(bufferedReader, this.zaqd);
        if (zaa == 0) {
            return 0.0d;
        }
        return Double.parseDouble(new String(this.zaqd, 0, zaa));
    }

    private final BigDecimal zai(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa = zaa(bufferedReader, this.zaqd);
        if (zaa == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zaqd, 0, zaa));
    }

    private final <T extends FastJsonResponse> ArrayList<T> zaa(BufferedReader bufferedReader, Field<?, ?> field) throws ParseException, IOException {
        ArrayList<T> arrayList = new ArrayList();
        char zaj = zaj(bufferedReader);
        switch (zaj) {
            case ']':
                zak(5);
                return arrayList;
            case 'n':
                zab(bufferedReader, zaqg);
                zak(5);
                return null;
            case '{':
                this.zaqm.push(Integer.valueOf(1));
                while (true) {
                    try {
                        FastJsonResponse zacp = field.zacp();
                        if (!zaa(bufferedReader, zacp)) {
                            return arrayList;
                        }
                        arrayList.add(zacp);
                        zaj = zaj(bufferedReader);
                        switch (zaj) {
                            case ',':
                                if (zaj(bufferedReader) != '{') {
                                    throw new ParseException("Expected start of next object in array");
                                }
                                this.zaqm.push(Integer.valueOf(1));
                            case ']':
                                zak(5);
                                return arrayList;
                            default:
                                throw new ParseException("Unexpected token: " + zaj);
                        }
                    } catch (Throwable e) {
                        throw new ParseException("Error instantiating inner object", e);
                    } catch (Throwable e2) {
                        throw new ParseException("Error instantiating inner object", e2);
                    }
                }
            default:
                throw new ParseException("Unexpected token: " + zaj);
        }
    }

    private final char zaj(BufferedReader bufferedReader) throws ParseException, IOException {
        if (bufferedReader.read(this.zaqb) == -1) {
            return '\u0000';
        }
        while (Character.isWhitespace(this.zaqb[0])) {
            if (bufferedReader.read(this.zaqb) == -1) {
                return '\u0000';
            }
        }
        return this.zaqb[0];
    }

    private final int zaa(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        char zaj = zaj(bufferedReader);
        if (zaj == '\u0000') {
            throw new ParseException("Unexpected EOF");
        } else if (zaj == ',') {
            throw new ParseException("Missing value");
        } else if (zaj == 'n') {
            zab(bufferedReader, zaqg);
            return 0;
        } else {
            int i;
            bufferedReader.mark(1024);
            if (zaj == '\"') {
                zaj = '\u0000';
                int i2 = 0;
                while (i2 < cArr.length && bufferedReader.read(cArr, i2, 1) != -1) {
                    char c = cArr[i2];
                    if (Character.isISOControl(c)) {
                        throw new ParseException("Unexpected control character while reading string");
                    } else if (c == '\"' && zaj == '\u0000') {
                        bufferedReader.reset();
                        bufferedReader.skip((long) (i2 + 1));
                        return i2;
                    } else {
                        zaj = c == '\\' ? zaj == '\u0000' ? '\u0001' : '\u0000' : '\u0000';
                        i2++;
                    }
                }
                i = i2;
            } else {
                cArr[0] = zaj;
                i = 1;
                while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                    if (cArr[i] == '}' || cArr[i] == ',' || Character.isWhitespace(cArr[i]) || cArr[i] == ']') {
                        bufferedReader.reset();
                        bufferedReader.skip((long) (i - 1));
                        cArr[i] = '\u0000';
                        return i;
                    }
                    i++;
                }
            }
            if (i == cArr.length) {
                throw new ParseException("Absurdly long value");
            }
            throw new ParseException("Unexpected EOF");
        }
    }

    private final void zab(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i = 0;
        while (i < cArr.length) {
            int read = bufferedReader.read(this.zaqc, 0, cArr.length - i);
            if (read == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i2 = 0; i2 < read; i2++) {
                if (cArr[i2 + i] != this.zaqc[i2]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i += read;
        }
    }

    private final void zak(int i) throws ParseException {
        if (this.zaqm.isEmpty()) {
            throw new ParseException("Expected state " + i + " but had empty stack");
        }
        int intValue = ((Integer) this.zaqm.pop()).intValue();
        if (intValue != i) {
            throw new ParseException("Expected state " + i + " but had " + intValue);
        }
    }
}
