// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import java.util.regex.Matcher;
import android.text.TextUtils;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.regex.Pattern;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@VisibleForTesting
public final class JsonUtils
{
    private static final Pattern zzhd;
    private static final Pattern zzhe;
    
    static {
        zzhd = Pattern.compile("\\\\.");
        zzhe = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
    }
    
    private JsonUtils() {
    }
    
    @KeepForSdk
    public static boolean areJsonValuesEquivalent(final Object o, final Object o2) {
        final boolean b = false;
        boolean b2;
        if (o == null && o2 == null) {
            b2 = true;
        }
        else {
            b2 = b;
            if (o != null) {
                b2 = b;
                if (o2 != null) {
                    Label_0131: {
                        if (!(o instanceof JSONObject) || !(o2 instanceof JSONObject)) {
                            break Label_0131;
                        }
                        final JSONObject jsonObject = (JSONObject)o;
                        final JSONObject jsonObject2 = (JSONObject)o2;
                        b2 = b;
                        if (jsonObject.length() != jsonObject2.length()) {
                            return b2;
                        }
                        final Iterator keys = jsonObject.keys();
                        String s;
                        JSONArray jsonArray;
                        int n;
                        JSONArray jsonArray2;
                        boolean jsonValuesEquivalent;
                        Block_15_Outer:Block_14_Outer:
                        while (true) {
                            if (!keys.hasNext()) {
                                return true;
                            }
                            s = keys.next();
                            b2 = b;
                            if (!jsonObject2.has(s)) {
                                return b2;
                            }
                            try {
                                if (!areJsonValuesEquivalent(jsonObject.get(s), jsonObject2.get(s))) {
                                    return false;
                                }
                                continue Block_15_Outer;
                                // iftrue(Label_0211:, !o instanceof JSONArray || !o2 instanceof JSONArray)
                                // iftrue(Label_0209:, n >= jsonArray.length())
                                while (true) {
                                Block_13:
                                    while (true) {
                                        Label_0171: {
                                            try {
                                                jsonValuesEquivalent = areJsonValuesEquivalent(jsonArray.get(n), jsonArray2.get(n));
                                                b2 = b;
                                                if (jsonValuesEquivalent) {
                                                    ++n;
                                                    break Label_0171;
                                                }
                                                return b2;
                                                Label_0211: {
                                                    return o.equals(o2);
                                                }
                                                Label_0209:
                                                return true;
                                            }
                                            catch (JSONException ex) {
                                                return false;
                                            }
                                            break Block_13;
                                            n = 0;
                                        }
                                        continue Block_14_Outer;
                                    }
                                    jsonArray = (JSONArray)o;
                                    jsonArray2 = (JSONArray)o2;
                                    b2 = b;
                                    continue;
                                }
                            }
                            // iftrue(Label_0013:, jsonArray.length() != jsonArray2.length())
                            catch (JSONException ex2) {
                                return false;
                            }
                            break;
                        }
                    }
                }
            }
        }
        Label_0013: {
            return b2;
        }
    }
    
    @KeepForSdk
    public static String escapeString(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final Matcher matcher = JsonUtils.zzhe.matcher(s);
            StringBuffer sb = null;
            while (matcher.find()) {
                StringBuffer sb2;
                if ((sb2 = sb) == null) {
                    sb2 = new StringBuffer();
                }
                switch (matcher.group().charAt(0)) {
                    default: {
                        sb = sb2;
                        continue;
                    }
                    case '\b': {
                        matcher.appendReplacement(sb2, "\\\\b");
                        sb = sb2;
                        continue;
                    }
                    case '\"': {
                        matcher.appendReplacement(sb2, "\\\\\\\"");
                        sb = sb2;
                        continue;
                    }
                    case '\\': {
                        matcher.appendReplacement(sb2, "\\\\\\\\");
                        sb = sb2;
                        continue;
                    }
                    case '/': {
                        matcher.appendReplacement(sb2, "\\\\/");
                        sb = sb2;
                        continue;
                    }
                    case '\f': {
                        matcher.appendReplacement(sb2, "\\\\f");
                        sb = sb2;
                        continue;
                    }
                    case '\n': {
                        matcher.appendReplacement(sb2, "\\\\n");
                        sb = sb2;
                        continue;
                    }
                    case '\r': {
                        matcher.appendReplacement(sb2, "\\\\r");
                        sb = sb2;
                        continue;
                    }
                    case '\t': {
                        matcher.appendReplacement(sb2, "\\\\t");
                        sb = sb2;
                        continue;
                    }
                }
            }
            if (sb != null) {
                matcher.appendTail(sb);
                return sb.toString();
            }
        }
        return s;
    }
    
    @KeepForSdk
    public static String unescapeString(final String s) {
        String s2 = s;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final String unescape = zzd.unescape(s);
            final Matcher matcher = JsonUtils.zzhd.matcher(unescape);
            StringBuffer sb = null;
            while (matcher.find()) {
                StringBuffer sb2;
                if ((sb2 = sb) == null) {
                    sb2 = new StringBuffer();
                }
                switch (matcher.group().charAt(1)) {
                    default: {
                        throw new IllegalStateException("Found an escaped character that should never be.");
                    }
                    case '\"': {
                        matcher.appendReplacement(sb2, "\"");
                        sb = sb2;
                        continue;
                    }
                    case '\\': {
                        matcher.appendReplacement(sb2, "\\\\");
                        sb = sb2;
                        continue;
                    }
                    case '/': {
                        matcher.appendReplacement(sb2, "/");
                        sb = sb2;
                        continue;
                    }
                    case 'b': {
                        matcher.appendReplacement(sb2, "\b");
                        sb = sb2;
                        continue;
                    }
                    case 'f': {
                        matcher.appendReplacement(sb2, "\f");
                        sb = sb2;
                        continue;
                    }
                    case 'n': {
                        matcher.appendReplacement(sb2, "\n");
                        sb = sb2;
                        continue;
                    }
                    case 'r': {
                        matcher.appendReplacement(sb2, "\r");
                        sb = sb2;
                        continue;
                    }
                    case 't': {
                        matcher.appendReplacement(sb2, "\t");
                        sb = sb2;
                        continue;
                    }
                }
            }
            if (sb != null) {
                matcher.appendTail(sb);
                return sb.toString();
            }
            s2 = unescape;
        }
        return s2;
    }
}
