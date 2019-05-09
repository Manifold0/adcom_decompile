// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.server.response;

import java.io.InputStream;
import com.google.android.gms.common.util.JsonUtils;
import java.util.Map;
import java.util.HashMap;
import com.google.android.gms.common.util.Base64Utils;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Stack;
import java.math.BigDecimal;
import java.math.BigInteger;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@ShowFirstParty
public class FastParser<T extends FastJsonResponse>
{
    private static final char[] zaqg;
    private static final char[] zaqh;
    private static final char[] zaqi;
    private static final char[] zaqj;
    private static final char[] zaqk;
    private static final char[] zaql;
    private static final zaa<Integer> zaqn;
    private static final zaa<Long> zaqo;
    private static final zaa<Float> zaqp;
    private static final zaa<Double> zaqq;
    private static final zaa<Boolean> zaqr;
    private static final zaa<String> zaqs;
    private static final zaa<BigInteger> zaqt;
    private static final zaa<BigDecimal> zaqu;
    private final char[] zaqb;
    private final char[] zaqc;
    private final char[] zaqd;
    private final StringBuilder zaqe;
    private final StringBuilder zaqf;
    private final Stack<Integer> zaqm;
    
    static {
        zaqg = new char[] { 'u', 'l', 'l' };
        zaqh = new char[] { 'r', 'u', 'e' };
        zaqi = new char[] { 'r', 'u', 'e', '\"' };
        zaqj = new char[] { 'a', 'l', 's', 'e' };
        zaqk = new char[] { 'a', 'l', 's', 'e', '\"' };
        zaql = new char[] { '\n' };
        zaqn = (zaa)new com.google.android.gms.common.server.response.zaa();
        zaqo = (zaa)new zab();
        zaqp = (zaa)new zac();
        zaqq = (zaa)new zad();
        zaqr = (zaa)new zae();
        zaqs = (zaa)new zaf();
        zaqt = (zaa)new zag();
        zaqu = (zaa)new zah();
    }
    
    public FastParser() {
        this.zaqb = new char[1];
        this.zaqc = new char[32];
        this.zaqd = new char[1024];
        this.zaqe = new StringBuilder(32);
        this.zaqf = new StringBuilder(1024);
        this.zaqm = new Stack<Integer>();
    }
    
    private final int zaa(final BufferedReader bufferedReader, final char[] array) throws ParseException, IOException {
        final char zaj = this.zaj(bufferedReader);
        if (zaj == '\0') {
            throw new ParseException("Unexpected EOF");
        }
        if (zaj == ',') {
            throw new ParseException("Missing value");
        }
        if (zaj == 'n') {
            this.zab(bufferedReader, FastParser.zaqg);
            return 0;
        }
        bufferedReader.mark(1024);
        int n2 = 0;
        Label_0175: {
            if (zaj != '\"') {
                array[0] = zaj;
                int n = 1;
                while (true) {
                    n2 = n;
                    if (n >= array.length) {
                        break Label_0175;
                    }
                    n2 = n;
                    if (bufferedReader.read(array, n, 1) == -1) {
                        break Label_0175;
                    }
                    if (array[n] == '}' || array[n] == ',' || Character.isWhitespace(array[n]) || array[n] == ']') {
                        break;
                    }
                    ++n;
                }
                bufferedReader.reset();
                bufferedReader.skip(n - 1);
                array[n] = '\0';
                return n;
            }
            int n3 = 0;
            for (n2 = 0; n2 < array.length && bufferedReader.read(array, n2, 1) != -1; ++n2) {
                final char c = array[n2];
                if (Character.isISOControl(c)) {
                    throw new ParseException("Unexpected control character while reading string");
                }
                if (c == '\"' && n3 == 0) {
                    bufferedReader.reset();
                    bufferedReader.skip(n2 + 1);
                    return n2;
                }
                if (c == '\\') {
                    if (n3 == 0) {
                        n3 = 1;
                    }
                    else {
                        n3 = 0;
                    }
                }
                else {
                    n3 = 0;
                }
            }
        }
        if (n2 == array.length) {
            throw new ParseException("Absurdly long value");
        }
        throw new ParseException("Unexpected EOF");
    }
    
    private final String zaa(final BufferedReader bufferedReader) throws ParseException, IOException {
        String zab = null;
        this.zaqm.push(2);
        final char zaj = this.zaj(bufferedReader);
        switch (zaj) {
            default: {
                throw new ParseException(new StringBuilder(19).append("Unexpected token: ").append(zaj).toString());
            }
            case 125: {
                this.zak(2);
                break;
            }
            case 93: {
                this.zak(2);
                this.zak(1);
                this.zak(5);
                return null;
            }
            case 34: {
                this.zaqm.push(3);
                zab = zab(bufferedReader, this.zaqc, this.zaqe, null);
                this.zak(3);
                if (this.zaj(bufferedReader) != ':') {
                    throw new ParseException("Expected key/value separator");
                }
                break;
            }
        }
        return zab;
    }
    
    private final String zaa(final BufferedReader bufferedReader, final char[] array, final StringBuilder sb, final char[] array2) throws ParseException, IOException {
        switch (this.zaj(bufferedReader)) {
            default: {
                throw new ParseException("Expected string");
            }
            case '\"': {
                return zab(bufferedReader, array, sb, array2);
            }
            case 'n': {
                this.zab(bufferedReader, FastParser.zaqg);
                return null;
            }
        }
    }
    
    private final <T extends FastJsonResponse> ArrayList<T> zaa(final BufferedReader bufferedReader, final FastJsonResponse.Field<?, ?> field) throws ParseException, IOException {
        final ArrayList<T> list = new ArrayList<T>();
        final char zaj = this.zaj(bufferedReader);
        Label_0086: {
            switch (zaj) {
                default: {
                    throw new ParseException(new StringBuilder(19).append("Unexpected token: ").append(zaj).toString());
                }
                case 93: {
                    this.zak(5);
                    break;
                }
                case 123: {
                    this.zaqm.push(1);
                }
                case 110: {
                    Label_0263: {
                        Label_0189: {
                            break Label_0189;
                            while (true) {
                                Label_0228: {
                                    try {
                                        final FastJsonResponse zacp = field.zacp();
                                        if (!this.zaa(bufferedReader, zacp)) {
                                            break Label_0086;
                                        }
                                        list.add((T)zacp);
                                        final char zaj2 = this.zaj(bufferedReader);
                                        switch (zaj2) {
                                            default: {
                                                throw new ParseException(new StringBuilder(19).append("Unexpected token: ").append(zaj2).toString());
                                            }
                                            case 44: {
                                                break Label_0228;
                                            }
                                            case 93: {
                                                break Label_0263;
                                            }
                                        }
                                        this.zab(bufferedReader, FastParser.zaqg);
                                        this.zak(5);
                                        return null;
                                    }
                                    catch (InstantiationException ex) {
                                        throw new ParseException("Error instantiating inner object", ex);
                                    }
                                    catch (IllegalAccessException ex2) {
                                        throw new ParseException("Error instantiating inner object", ex2);
                                    }
                                }
                                if (this.zaj(bufferedReader) != '{') {
                                    break;
                                }
                                this.zaqm.push(1);
                            }
                        }
                        throw new ParseException("Expected start of next object in array");
                    }
                    this.zak(5);
                    return list;
                }
            }
        }
        return list;
    }
    
    private final <O> ArrayList<O> zaa(final BufferedReader bufferedReader, final zaa<O> zaa) throws ParseException, IOException {
        final char zaj = this.zaj(bufferedReader);
        if (zaj == 'n') {
            this.zab(bufferedReader, FastParser.zaqg);
            return null;
        }
        if (zaj != '[') {
            throw new ParseException("Expected start of array");
        }
        this.zaqm.push(5);
        final ArrayList<O> list = new ArrayList<O>();
        while (true) {
            bufferedReader.mark(1024);
            switch (this.zaj(bufferedReader)) {
                case ',': {
                    continue;
                }
                default: {
                    bufferedReader.reset();
                    list.add(zaa.zah(this, bufferedReader));
                    continue;
                }
                case ']': {
                    this.zak(5);
                    return list;
                }
                case '\0': {
                    throw new ParseException("Unexpected EOF");
                }
            }
        }
    }
    
    private final boolean zaa(final BufferedReader bufferedReader, final FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        final Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
        String s;
        if ((s = this.zaa(bufferedReader)) == null) {
            this.zak(1);
            return false;
        }
        while (s != null) {
            final FastJsonResponse.Field<ArrayList<Integer>, ?> field = fieldMappings.get(s);
            if (field == null) {
                s = this.zab(bufferedReader);
            }
            else {
                this.zaqm.push(4);
                Label_1175: {
                    switch (field.zapr) {
                        default: {
                            throw new ParseException(new StringBuilder(30).append("Invalid field type ").append(field.zapr).toString());
                        }
                        case 0: {
                            if (field.zaps) {
                                fastJsonResponse.zaa((FastJsonResponse.Field<ArrayList<Integer>, Object>)field, this.zaa(bufferedReader, FastParser.zaqn));
                                break;
                            }
                            fastJsonResponse.zaa((FastJsonResponse.Field<Integer, Object>)field, this.zad(bufferedReader));
                            break;
                        }
                        case 1: {
                            if (field.zaps) {
                                fastJsonResponse.zab((FastJsonResponse.Field<ArrayList<BigInteger>, Object>)field, this.zaa(bufferedReader, FastParser.zaqt));
                                break;
                            }
                            fastJsonResponse.zaa((FastJsonResponse.Field<BigInteger, Object>)field, this.zaf(bufferedReader));
                            break;
                        }
                        case 2: {
                            if (field.zaps) {
                                fastJsonResponse.zac((FastJsonResponse.Field<ArrayList<Long>, Object>)field, this.zaa(bufferedReader, FastParser.zaqo));
                                break;
                            }
                            fastJsonResponse.zaa((FastJsonResponse.Field<Long, Object>)field, this.zae(bufferedReader));
                            break;
                        }
                        case 3: {
                            if (field.zaps) {
                                fastJsonResponse.zad((FastJsonResponse.Field<ArrayList<Float>, Object>)field, this.zaa(bufferedReader, FastParser.zaqp));
                                break;
                            }
                            fastJsonResponse.zaa((FastJsonResponse.Field<Float, Object>)field, this.zag(bufferedReader));
                            break;
                        }
                        case 4: {
                            if (field.zaps) {
                                fastJsonResponse.zae((FastJsonResponse.Field<ArrayList<Double>, Object>)field, this.zaa(bufferedReader, FastParser.zaqq));
                                break;
                            }
                            fastJsonResponse.zaa((FastJsonResponse.Field<Double, Object>)field, this.zah(bufferedReader));
                            break;
                        }
                        case 5: {
                            if (field.zaps) {
                                fastJsonResponse.zaf((FastJsonResponse.Field<ArrayList<BigDecimal>, Object>)field, this.zaa(bufferedReader, FastParser.zaqu));
                                break;
                            }
                            fastJsonResponse.zaa((FastJsonResponse.Field<BigDecimal, Object>)field, this.zai(bufferedReader));
                            break;
                        }
                        case 6: {
                            if (field.zaps) {
                                fastJsonResponse.zag((FastJsonResponse.Field<ArrayList<Boolean>, Object>)field, this.zaa(bufferedReader, FastParser.zaqr));
                                break;
                            }
                            fastJsonResponse.zaa((FastJsonResponse.Field<Boolean, Object>)field, this.zaa(bufferedReader, false));
                            break;
                        }
                        case 7: {
                            if (field.zaps) {
                                fastJsonResponse.zah((FastJsonResponse.Field<ArrayList<String>, Object>)field, this.zaa(bufferedReader, FastParser.zaqs));
                                break;
                            }
                            fastJsonResponse.zaa((FastJsonResponse.Field<String, Object>)field, this.zac(bufferedReader));
                            break;
                        }
                        case 8: {
                            fastJsonResponse.zaa((FastJsonResponse.Field<byte[], Object>)field, Base64Utils.decode(this.zaa(bufferedReader, this.zaqd, this.zaqf, FastParser.zaql)));
                            break;
                        }
                        case 9: {
                            fastJsonResponse.zaa((FastJsonResponse.Field<byte[], Object>)field, Base64Utils.decodeUrlSafe(this.zaa(bufferedReader, this.zaqd, this.zaqf, FastParser.zaql)));
                            break;
                        }
                        case 10: {
                            final char zaj = this.zaj(bufferedReader);
                            HashMap<String, String> hashMap = null;
                            Label_0647: {
                                if (zaj == 'n') {
                                    this.zab(bufferedReader, FastParser.zaqg);
                                    hashMap = null;
                                }
                                else {
                                    if (zaj != '{') {
                                        throw new ParseException("Expected start of a map object");
                                    }
                                    this.zaqm.push(1);
                                    hashMap = new HashMap<String, String>();
                                    while (true) {
                                        switch (this.zaj(bufferedReader)) {
                                            default: {
                                                continue;
                                            }
                                            case '\0': {
                                                throw new ParseException("Unexpected EOF");
                                            }
                                            case '\"': {
                                                final String zab = zab(bufferedReader, this.zaqc, this.zaqe, null);
                                                if (this.zaj(bufferedReader) != ':') {
                                                    final String value = String.valueOf(zab);
                                                    String concat;
                                                    if (value.length() != 0) {
                                                        concat = "No map value found for key ".concat(value);
                                                    }
                                                    else {
                                                        concat = new String("No map value found for key ");
                                                    }
                                                    throw new ParseException(concat);
                                                }
                                                if (this.zaj(bufferedReader) != '\"') {
                                                    final String value2 = String.valueOf(zab);
                                                    String concat2;
                                                    if (value2.length() != 0) {
                                                        concat2 = "Expected String value for key ".concat(value2);
                                                    }
                                                    else {
                                                        concat2 = new String("Expected String value for key ");
                                                    }
                                                    throw new ParseException(concat2);
                                                }
                                                hashMap.put(zab, zab(bufferedReader, this.zaqc, this.zaqe, null));
                                                final char zaj2 = this.zaj(bufferedReader);
                                                if (zaj2 == ',') {
                                                    continue;
                                                }
                                                if (zaj2 == '}') {
                                                    this.zak(1);
                                                    break Label_0647;
                                                }
                                                throw new ParseException(new StringBuilder(48).append("Unexpected character while parsing string map: ").append(zaj2).toString());
                                            }
                                            case '}': {
                                                this.zak(1);
                                                break Label_0647;
                                            }
                                        }
                                    }
                                }
                            }
                            fastJsonResponse.zaa((FastJsonResponse.Field<Map<String, String>, Object>)field, (Map<String, String>)hashMap);
                            break;
                        }
                        case 11: {
                            if (field.zaps) {
                                final char zaj3 = this.zaj(bufferedReader);
                                if (zaj3 == 'n') {
                                    this.zab(bufferedReader, FastParser.zaqg);
                                    fastJsonResponse.addConcreteTypeArrayInternal(field, field.zapv, (ArrayList<FastJsonResponse>)null);
                                    break;
                                }
                                this.zaqm.push(5);
                                if (zaj3 != '[') {
                                    throw new ParseException("Expected array start");
                                }
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.zapv, this.zaa(bufferedReader, (FastJsonResponse.Field<?, ?>)field));
                                break;
                            }
                            else {
                                final char zaj4 = this.zaj(bufferedReader);
                                if (zaj4 == 'n') {
                                    this.zab(bufferedReader, FastParser.zaqg);
                                    fastJsonResponse.addConcreteTypeInternal(field, field.zapv, (FastJsonResponse)null);
                                    break;
                                }
                                this.zaqm.push(1);
                                if (zaj4 != '{') {
                                    throw new ParseException("Expected start of object");
                                }
                                try {
                                    final FastJsonResponse zacp = field.zacp();
                                    this.zaa(bufferedReader, zacp);
                                    fastJsonResponse.addConcreteTypeInternal(field, field.zapv, zacp);
                                    break;
                                }
                                catch (InstantiationException ex) {
                                    throw new ParseException("Error instantiating inner object", ex);
                                }
                                catch (IllegalAccessException ex2) {
                                    throw new ParseException("Error instantiating inner object", ex2);
                                }
                                break Label_1175;
                            }
                            break;
                        }
                    }
                    this.zak(4);
                    this.zak(2);
                    final char zaj5 = this.zaj(bufferedReader);
                    switch (zaj5) {
                        case 125: {
                            s = null;
                            continue;
                        }
                        default: {
                            throw new ParseException(new StringBuilder(55).append("Expected end of object or field separator, but found: ").append(zaj5).toString());
                        }
                        case 44: {
                            s = this.zaa(bufferedReader);
                            continue;
                        }
                    }
                }
            }
        }
        this.zak(1);
        return true;
    }
    
    private final boolean zaa(final BufferedReader bufferedReader, boolean b) throws ParseException, IOException {
        while (true) {
            final char zaj = this.zaj(bufferedReader);
            switch (zaj) {
                default: {
                    throw new ParseException(new StringBuilder(19).append("Unexpected token: ").append(zaj).toString());
                }
                case 110: {
                    this.zab(bufferedReader, FastParser.zaqg);
                    return false;
                }
                case 116: {
                    char[] array;
                    if (b) {
                        array = FastParser.zaqi;
                    }
                    else {
                        array = FastParser.zaqh;
                    }
                    this.zab(bufferedReader, array);
                    return true;
                }
                case 102: {
                    char[] array2;
                    if (b) {
                        array2 = FastParser.zaqk;
                    }
                    else {
                        array2 = FastParser.zaqj;
                    }
                    this.zab(bufferedReader, array2);
                    return false;
                }
                case 34: {
                    if (b) {
                        throw new ParseException("No boolean value found in string");
                    }
                    b = true;
                    continue;
                }
            }
        }
    }
    
    private final String zab(final BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(1024);
        switch (this.zaj(bufferedReader)) {
            default: {
                bufferedReader.reset();
                this.zaa(bufferedReader, this.zaqd);
                break;
            }
            case '\"': {
                if (bufferedReader.read(this.zaqb) == -1) {
                    throw new ParseException("Unexpected EOF while parsing string");
                }
                char c = this.zaqb[0];
                int n2;
                char c2;
                for (int n = 0; c != '\"' || n != 0; n = n2, c = c2) {
                    if (c == '\\') {
                        if (n == 0) {
                            n2 = 1;
                        }
                        else {
                            n2 = 0;
                        }
                    }
                    else {
                        n2 = 0;
                    }
                    if (bufferedReader.read(this.zaqb) == -1) {
                        throw new ParseException("Unexpected EOF while parsing string");
                    }
                    c2 = this.zaqb[0];
                    if (Character.isISOControl(c2)) {
                        throw new ParseException("Unexpected control character while reading string");
                    }
                }
                break;
            }
            case '{': {
                this.zaqm.push(1);
                bufferedReader.mark(32);
                final char zaj = this.zaj(bufferedReader);
                if (zaj == '}') {
                    this.zak(1);
                    break;
                }
                if (zaj == '\"') {
                    bufferedReader.reset();
                    this.zaa(bufferedReader);
                    while (this.zab(bufferedReader) != null) {}
                    this.zak(1);
                    break;
                }
                throw new ParseException(new StringBuilder(18).append("Unexpected token ").append(zaj).toString());
            }
            case '[': {
                this.zaqm.push(5);
                bufferedReader.mark(32);
                if (this.zaj(bufferedReader) == ']') {
                    this.zak(5);
                    break;
                }
                bufferedReader.reset();
                int i = 1;
                int n3 = 0;
                int n4 = 0;
                while (i > 0) {
                    final char zaj2 = this.zaj(bufferedReader);
                    if (zaj2 == '\0') {
                        throw new ParseException("Unexpected EOF while parsing array");
                    }
                    if (Character.isISOControl(zaj2)) {
                        throw new ParseException("Unexpected control character while reading array");
                    }
                    if (zaj2 == '\"' && n4 == 0) {
                        if (n3 == 0) {
                            n3 = 1;
                        }
                        else {
                            n3 = 0;
                        }
                    }
                    if (zaj2 == '[' && n3 == 0) {
                        ++i;
                    }
                    if (zaj2 == ']' && n3 == 0) {
                        --i;
                    }
                    if (zaj2 == '\\' && n3 != 0) {
                        if (n4 == 0) {
                            n4 = 1;
                        }
                        else {
                            n4 = 0;
                        }
                    }
                    else {
                        n4 = 0;
                    }
                }
                this.zak(5);
                break;
            }
            case ',': {
                throw new ParseException("Missing value");
            }
        }
        final char zaj3 = this.zaj(bufferedReader);
        switch (zaj3) {
            default: {
                throw new ParseException(new StringBuilder(18).append("Unexpected token ").append(zaj3).toString());
            }
            case 44: {
                this.zak(2);
                return this.zaa(bufferedReader);
            }
            case 125: {
                this.zak(2);
                return null;
            }
        }
    }
    
    private static String zab(final BufferedReader bufferedReader, final char[] array, final StringBuilder sb, final char[] array2) throws ParseException, IOException {
        sb.setLength(0);
        bufferedReader.mark(array.length);
        int n = 0;
        int n2 = 0;
        while (true) {
            final int read = bufferedReader.read(array);
            if (read == -1) {
                throw new ParseException("Unexpected EOF while parsing string");
            }
            int i = 0;
            while (i < read) {
                final char c = array[i];
                Label_0110: {
                    if (Character.isISOControl(c)) {
                    Label_0080:
                        while (true) {
                            if (array2 != null) {
                                for (int j = 0; j < array2.length; ++j) {
                                    if (array2[j] == c) {
                                        final int n3 = 1;
                                        break Label_0080;
                                    }
                                }
                            }
                            Label_0104: {
                                break Label_0104;
                                final int n3;
                                if (n3 == 0) {
                                    throw new ParseException("Unexpected control character while reading string");
                                }
                                break Label_0110;
                            }
                            final int n3 = 0;
                            continue Label_0080;
                        }
                    }
                }
                if (c == '\"' && n2 == 0) {
                    sb.append(array, 0, i);
                    bufferedReader.reset();
                    bufferedReader.skip(i + 1);
                    if (n != 0) {
                        return JsonUtils.unescapeString(sb.toString());
                    }
                    return sb.toString();
                }
                else {
                    if (c == '\\') {
                        if (n2 == 0) {
                            n2 = 1;
                        }
                        else {
                            n2 = 0;
                        }
                        n = 1;
                    }
                    else {
                        n2 = 0;
                    }
                    ++i;
                }
            }
            sb.append(array, 0, read);
            bufferedReader.mark(array.length);
        }
    }
    
    private final void zab(final BufferedReader bufferedReader, final char[] array) throws ParseException, IOException {
        int read;
        for (int i = 0; i < array.length; i += read) {
            read = bufferedReader.read(this.zaqc, 0, array.length - i);
            if (read == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int j = 0; j < read; ++j) {
                if (array[j + i] != this.zaqc[j]) {
                    throw new ParseException("Unexpected character");
                }
            }
        }
    }
    
    private final String zac(final BufferedReader bufferedReader) throws ParseException, IOException {
        return this.zaa(bufferedReader, this.zaqc, this.zaqe, null);
    }
    
    private final int zad(final BufferedReader bufferedReader) throws ParseException, IOException {
        int n = 0;
        final int n2 = 0;
        final int zaa = this.zaa(bufferedReader, this.zaqd);
        if (zaa == 0) {
            n = n2;
        }
        else {
            final char[] zaqd = this.zaqd;
            if (zaa <= 0) {
                throw new ParseException("No number to parse");
            }
            int n3;
            boolean b;
            int n4;
            if (zaqd[0] == '-') {
                n3 = Integer.MIN_VALUE;
                b = true;
                n4 = 1;
            }
            else {
                n3 = -2147483647;
                b = false;
                n4 = 0;
            }
            int i = n4;
            if (n4 < zaa) {
                final int digit = Character.digit(zaqd[n4], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                n = -digit;
                i = n4 + 1;
            }
            while (i < zaa) {
                final int digit2 = Character.digit(zaqd[i], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if (n < -214748364) {
                    throw new ParseException("Number too large");
                }
                final int n5 = n * 10;
                if (n5 < n3 + digit2) {
                    throw new ParseException("Number too large");
                }
                n = n5 - digit2;
                ++i;
            }
            if (!b) {
                return -n;
            }
            if (i <= 1) {
                throw new ParseException("No digits to parse");
            }
        }
        return n;
    }
    
    private final long zae(final BufferedReader bufferedReader) throws ParseException, IOException {
        long n = 0L;
        final int zaa = this.zaa(bufferedReader, this.zaqd);
        if (zaa != 0) {
            final char[] zaqd = this.zaqd;
            if (zaa <= 0) {
                throw new ParseException("No number to parse");
            }
            long n2;
            boolean b;
            int n3;
            if (zaqd[0] == '-') {
                n2 = Long.MIN_VALUE;
                b = true;
                n3 = 1;
            }
            else {
                b = false;
                n2 = -9223372036854775807L;
                n3 = 0;
            }
            int i = n3;
            if (n3 < zaa) {
                final int digit = Character.digit(zaqd[n3], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                n = -digit;
                i = n3 + 1;
            }
            while (i < zaa) {
                final int digit2 = Character.digit(zaqd[i], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if (n < -922337203685477580L) {
                    throw new ParseException("Number too large");
                }
                final long n4 = n * 10L;
                if (n4 < digit2 + n2) {
                    throw new ParseException("Number too large");
                }
                n = n4 - digit2;
                ++i;
            }
            if (!b) {
                return -n;
            }
            if (i <= 1) {
                throw new ParseException("No digits to parse");
            }
        }
        return n;
    }
    
    private final BigInteger zaf(final BufferedReader bufferedReader) throws ParseException, IOException {
        final int zaa = this.zaa(bufferedReader, this.zaqd);
        if (zaa == 0) {
            return null;
        }
        return new BigInteger(new String(this.zaqd, 0, zaa));
    }
    
    private final float zag(final BufferedReader bufferedReader) throws ParseException, IOException {
        final int zaa = this.zaa(bufferedReader, this.zaqd);
        if (zaa == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.zaqd, 0, zaa));
    }
    
    private final double zah(final BufferedReader bufferedReader) throws ParseException, IOException {
        final int zaa = this.zaa(bufferedReader, this.zaqd);
        if (zaa == 0) {
            return 0.0;
        }
        return Double.parseDouble(new String(this.zaqd, 0, zaa));
    }
    
    private final BigDecimal zai(final BufferedReader bufferedReader) throws ParseException, IOException {
        final int zaa = this.zaa(bufferedReader, this.zaqd);
        if (zaa == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zaqd, 0, zaa));
    }
    
    private final char zaj(final BufferedReader bufferedReader) throws ParseException, IOException {
        if (bufferedReader.read(this.zaqb) == -1) {
            return '\0';
        }
        while (Character.isWhitespace(this.zaqb[0])) {
            if (bufferedReader.read(this.zaqb) == -1) {
                return '\0';
            }
        }
        return this.zaqb[0];
    }
    
    private final void zak(final int n) throws ParseException {
        if (this.zaqm.isEmpty()) {
            throw new ParseException(new StringBuilder(46).append("Expected state ").append(n).append(" but had empty stack").toString());
        }
        final int intValue = this.zaqm.pop();
        if (intValue != n) {
            throw new ParseException(new StringBuilder(46).append("Expected state ").append(n).append(" but had ").append(intValue).toString());
        }
    }
    
    @KeepForSdk
    public void parse(final InputStream p0, final T p1) throws ParseException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: new             Ljava/io/InputStreamReader;
        //     7: dup            
        //     8: aload_1        
        //     9: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    12: sipush          1024
        //    15: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;I)V
        //    18: astore_1       
        //    19: aload_0        
        //    20: getfield        com/google/android/gms/common/server/response/FastParser.zaqm:Ljava/util/Stack;
        //    23: iconst_0       
        //    24: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    27: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //    30: pop            
        //    31: aload_0        
        //    32: aload_1        
        //    33: invokespecial   com/google/android/gms/common/server/response/FastParser.zaj:(Ljava/io/BufferedReader;)C
        //    36: istore_3       
        //    37: iload_3        
        //    38: lookupswitch {
        //                0: 242
        //               91: 147
        //              123: 118
        //          default: 279
        //        }
        //    72: new             Lcom/google/android/gms/common/server/response/FastParser$ParseException;
        //    75: dup            
        //    76: new             Ljava/lang/StringBuilder;
        //    79: dup            
        //    80: bipush          19
        //    82: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //    85: ldc             "Unexpected token: "
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    90: iload_3        
        //    91: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    94: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    97: invokespecial   com/google/android/gms/common/server/response/FastParser$ParseException.<init>:(Ljava/lang/String;)V
        //   100: athrow         
        //   101: astore_2       
        //   102: new             Lcom/google/android/gms/common/server/response/FastParser$ParseException;
        //   105: dup            
        //   106: aload_2        
        //   107: invokespecial   com/google/android/gms/common/server/response/FastParser$ParseException.<init>:(Ljava/lang/Throwable;)V
        //   110: athrow         
        //   111: astore_2       
        //   112: aload_1        
        //   113: invokevirtual   java/io/BufferedReader.close:()V
        //   116: aload_2        
        //   117: athrow         
        //   118: aload_0        
        //   119: getfield        com/google/android/gms/common/server/response/FastParser.zaqm:Ljava/util/Stack;
        //   122: iconst_1       
        //   123: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   126: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //   129: pop            
        //   130: aload_0        
        //   131: aload_1        
        //   132: aload_2        
        //   133: invokespecial   com/google/android/gms/common/server/response/FastParser.zaa:(Ljava/io/BufferedReader;Lcom/google/android/gms/common/server/response/FastJsonResponse;)Z
        //   136: pop            
        //   137: aload_0        
        //   138: iconst_0       
        //   139: invokespecial   com/google/android/gms/common/server/response/FastParser.zak:(I)V
        //   142: aload_1        
        //   143: invokevirtual   java/io/BufferedReader.close:()V
        //   146: return         
        //   147: aload_0        
        //   148: getfield        com/google/android/gms/common/server/response/FastParser.zaqm:Ljava/util/Stack;
        //   151: iconst_5       
        //   152: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   155: invokevirtual   java/util/Stack.push:(Ljava/lang/Object;)Ljava/lang/Object;
        //   158: pop            
        //   159: aload_2        
        //   160: invokevirtual   com/google/android/gms/common/server/response/FastJsonResponse.getFieldMappings:()Ljava/util/Map;
        //   163: astore          4
        //   165: aload           4
        //   167: invokeinterface java/util/Map.size:()I
        //   172: iconst_1       
        //   173: if_icmpeq       187
        //   176: new             Lcom/google/android/gms/common/server/response/FastParser$ParseException;
        //   179: dup            
        //   180: ldc_w           "Object array response class must have a single Field"
        //   183: invokespecial   com/google/android/gms/common/server/response/FastParser$ParseException.<init>:(Ljava/lang/String;)V
        //   186: athrow         
        //   187: aload           4
        //   189: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //   194: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   199: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   204: checkcast       Ljava/util/Map$Entry;
        //   207: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   212: checkcast       Lcom/google/android/gms/common/server/response/FastJsonResponse$Field;
        //   215: astore          4
        //   217: aload_0        
        //   218: aload_1        
        //   219: aload           4
        //   221: invokespecial   com/google/android/gms/common/server/response/FastParser.zaa:(Ljava/io/BufferedReader;Lcom/google/android/gms/common/server/response/FastJsonResponse$Field;)Ljava/util/ArrayList;
        //   224: astore          5
        //   226: aload_2        
        //   227: aload           4
        //   229: aload           4
        //   231: getfield        com/google/android/gms/common/server/response/FastJsonResponse$Field.zapv:Ljava/lang/String;
        //   234: aload           5
        //   236: invokevirtual   com/google/android/gms/common/server/response/FastJsonResponse.addConcreteTypeArrayInternal:(Lcom/google/android/gms/common/server/response/FastJsonResponse$Field;Ljava/lang/String;Ljava/util/ArrayList;)V
        //   239: goto            137
        //   242: new             Lcom/google/android/gms/common/server/response/FastParser$ParseException;
        //   245: dup            
        //   246: ldc_w           "No data to parse"
        //   249: invokespecial   com/google/android/gms/common/server/response/FastParser$ParseException.<init>:(Ljava/lang/String;)V
        //   252: athrow         
        //   253: astore_1       
        //   254: ldc_w           "FastParser"
        //   257: ldc_w           "Failed to close reader while parsing."
        //   260: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   263: pop            
        //   264: return         
        //   265: astore_1       
        //   266: ldc_w           "FastParser"
        //   269: ldc_w           "Failed to close reader while parsing."
        //   272: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   275: pop            
        //   276: goto            116
        //   279: goto            72
        //    Exceptions:
        //  throws com.google.android.gms.common.server.response.FastParser.ParseException
        //    Signature:
        //  (Ljava/io/InputStream;TT;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  19     37     101    111    Ljava/io/IOException;
        //  19     37     111    118    Any
        //  72     101    101    111    Ljava/io/IOException;
        //  72     101    111    118    Any
        //  102    111    111    118    Any
        //  112    116    265    279    Ljava/io/IOException;
        //  118    137    101    111    Ljava/io/IOException;
        //  118    137    111    118    Any
        //  137    142    101    111    Ljava/io/IOException;
        //  137    142    111    118    Any
        //  142    146    253    265    Ljava/io/IOException;
        //  147    187    101    111    Ljava/io/IOException;
        //  147    187    111    118    Any
        //  187    239    101    111    Ljava/io/IOException;
        //  187    239    111    118    Any
        //  242    253    101    111    Ljava/io/IOException;
        //  242    253    111    118    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0116:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @KeepForSdk
    @ShowFirstParty
    public static class ParseException extends Exception
    {
        public ParseException(final String s) {
            super(s);
        }
        
        public ParseException(final String s, final Throwable t) {
            super(s, t);
        }
        
        public ParseException(final Throwable t) {
            super(t);
        }
    }
    
    private interface zaa<O>
    {
        O zah(final FastParser p0, final BufferedReader p1) throws ParseException, IOException;
    }
}
