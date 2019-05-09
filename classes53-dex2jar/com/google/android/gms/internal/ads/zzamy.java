// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.net.HttpURLConnection;
import android.provider.Settings$Global;
import android.os.Build$VERSION;
import android.content.Context;
import com.google.android.gms.common.util.Base64Utils;
import java.io.Writer;
import java.io.StringWriter;
import java.util.Iterator;
import java.io.IOException;
import android.util.JsonWriter;
import java.util.Map;
import java.util.UUID;
import java.util.ArrayList;
import android.support.annotation.Nullable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.google.android.gms.common.util.DefaultClock;
import java.util.List;
import java.util.Set;
import com.google.android.gms.common.util.Clock;
import android.support.annotation.GuardedBy;

@zzadh
public final class zzamy
{
    private static Object sLock;
    @GuardedBy("sLock")
    private static boolean zzcuv;
    @GuardedBy("sLock")
    private static boolean zzcuw;
    private static Clock zzcux;
    private static final Set<String> zzcuy;
    private final List<String> zzcuz;
    
    static {
        zzamy.sLock = new Object();
        zzamy.zzcuv = false;
        zzamy.zzcuw = false;
        zzamy.zzcux = DefaultClock.getInstance();
        zzcuy = new HashSet<String>(Arrays.asList(new String[0]));
    }
    
    public zzamy() {
        this(null);
    }
    
    public zzamy(@Nullable String s) {
        List<String> zzcuz;
        if (!isEnabled()) {
            zzcuz = new ArrayList<String>();
        }
        else {
            final String string = UUID.randomUUID().toString();
            if (s == null) {
                s = String.valueOf(string);
                if (s.length() != 0) {
                    s = "network_request_".concat(s);
                }
                else {
                    s = new String("network_request_");
                }
                zzcuz = Arrays.asList(s);
            }
            else {
                s = String.valueOf(s);
                if (s.length() != 0) {
                    s = "ad_request_".concat(s);
                }
                else {
                    s = new String("ad_request_");
                }
                final String value = String.valueOf(string);
                String concat;
                if (value.length() != 0) {
                    concat = "network_request_".concat(value);
                }
                else {
                    concat = new String("network_request_");
                }
                zzcuz = Arrays.asList(s, concat);
            }
        }
        this.zzcuz = zzcuz;
    }
    
    public static boolean isEnabled() {
        while (true) {
            synchronized (zzamy.sLock) {
                if (zzamy.zzcuv && zzamy.zzcuw) {
                    return true;
                }
            }
            return false;
        }
    }
    
    private static void zza(final JsonWriter jsonWriter, @Nullable final Map<String, ?> map) throws IOException {
        if (map == null) {
            return;
        }
        jsonWriter.name("headers").beginArray();
        for (final Map.Entry<String, List<String>> entry : map.entrySet()) {
            final String s = entry.getKey();
            if (!zzamy.zzcuy.contains(s)) {
                if (entry.getValue() instanceof List) {
                    for (final String s2 : entry.getValue()) {
                        jsonWriter.beginObject();
                        jsonWriter.name("name").value(s);
                        jsonWriter.name("value").value(s2);
                        jsonWriter.endObject();
                    }
                }
                else {
                    if (!(entry.getValue() instanceof String)) {
                        zzane.e("Connection headers should be either Map<String, String> or Map<String, List<String>>");
                        break;
                    }
                    jsonWriter.beginObject();
                    jsonWriter.name("name").value(s);
                    jsonWriter.name("value").value((String)entry.getValue());
                    jsonWriter.endObject();
                }
            }
        }
        jsonWriter.endArray();
    }
    
    private final void zza(final String s, final zzand zzand) {
        final StringWriter stringWriter = new StringWriter();
        final JsonWriter jsonWriter = new JsonWriter((Writer)stringWriter);
        while (true) {
            Label_0118: {
                try {
                    jsonWriter.beginObject();
                    jsonWriter.name("timestamp").value(zzamy.zzcux.currentTimeMillis());
                    jsonWriter.name("event").value(s);
                    jsonWriter.name("components").beginArray();
                    final Iterator<String> iterator = this.zzcuz.iterator();
                    while (iterator.hasNext()) {
                        jsonWriter.value((String)iterator.next());
                    }
                    break Label_0118;
                }
                catch (IOException ex) {
                    zzane.zzb("unable to log", ex);
                }
                zzdi(stringWriter.toString());
                return;
            }
            jsonWriter.endArray();
            zzand.zza(jsonWriter);
            jsonWriter.endObject();
            jsonWriter.flush();
            jsonWriter.close();
            continue;
        }
    }
    
    public static void zzaf(final boolean zzcuw) {
        synchronized (zzamy.sLock) {
            zzamy.zzcuv = true;
            zzamy.zzcuw = zzcuw;
        }
    }
    
    private final void zzb(final String s, final String s2, @Nullable final Map<String, ?> map, @Nullable final byte[] array) {
        this.zza("onNetworkRequest", new zzamz(s, s2, map, array));
    }
    
    private final void zzb(@Nullable final Map<String, ?> map, final int n) {
        this.zza("onNetworkResponse", new zzana(n, map));
    }
    
    public static boolean zzbl(final Context context) {
        if (Build$VERSION.SDK_INT < 17) {
            return false;
        }
        if (!zzkb.zzik().zzd(zznk.zzazm)) {
            return false;
        }
        try {
            return Settings$Global.getInt(context.getContentResolver(), "development_settings_enabled", 0) != 0;
        }
        catch (Exception ex) {
            zzane.zzc("Fail to determine debug setting.", ex);
            return false;
        }
    }
    
    private final void zzdh(@Nullable final String s) {
        this.zza("onNetworkRequestError", new zzanc(s));
    }
    
    private static void zzdi(final String s) {
        synchronized (zzamy.class) {
            zzane.zzdj("GMA Debug BEGIN");
            for (int i = 0; i < s.length(); i += 4000) {
                final String value = String.valueOf(s.substring(i, Math.min(i + 4000, s.length())));
                String concat;
                if (value.length() != 0) {
                    concat = "GMA Debug CONTENT ".concat(value);
                }
                else {
                    concat = new String("GMA Debug CONTENT ");
                }
                zzane.zzdj(concat);
            }
        }
        zzane.zzdj("GMA Debug FINISH");
    }
    // monitorexit(zzamy.class)
    
    public static void zzsj() {
        synchronized (zzamy.sLock) {
            zzamy.zzcuv = false;
            zzamy.zzcuw = false;
            zzane.zzdk("Ad debug logging enablement is out of date.");
        }
    }
    
    public static boolean zzsk() {
        synchronized (zzamy.sLock) {
            return zzamy.zzcuv;
        }
    }
    
    public final void zza(final String s, final String s2, @Nullable final Map<String, ?> map, @Nullable final byte[] array) {
        if (!isEnabled()) {
            return;
        }
        this.zzb(s, s2, map, array);
    }
    
    public final void zza(final HttpURLConnection httpURLConnection, final int n) {
        final String s = null;
        if (isEnabled()) {
            Label_0050: {
                if (httpURLConnection.getHeaderFields() != null) {
                    break Label_0050;
                }
                Map<String, ?> map = null;
            Label_0044_Outer:
                while (true) {
                    this.zzb(map, n);
                    if (n >= 200) {
                        if (n < 300) {
                            return;
                        }
                    }
                    while (true) {
                        try {
                            final String responseMessage = httpURLConnection.getResponseMessage();
                            this.zzdh(responseMessage);
                            return;
                            map = new HashMap<String, Object>(httpURLConnection.getHeaderFields());
                            continue Label_0044_Outer;
                        }
                        catch (IOException ex) {
                            final String value = String.valueOf(ex.getMessage());
                            String concat;
                            if (value.length() != 0) {
                                concat = "Can not get error message from error HttpURLConnection\n".concat(value);
                            }
                            else {
                                concat = new String("Can not get error message from error HttpURLConnection\n");
                            }
                            zzane.zzdk(concat);
                            final String responseMessage = s;
                            continue;
                        }
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    public final void zza(final HttpURLConnection httpURLConnection, @Nullable final byte[] array) {
        if (!isEnabled()) {
            return;
        }
        Map<String, ?> map;
        if (httpURLConnection.getRequestProperties() == null) {
            map = null;
        }
        else {
            map = new HashMap<String, Object>(httpURLConnection.getRequestProperties());
        }
        this.zzb(new String(httpURLConnection.getURL().toString()), new String(httpURLConnection.getRequestMethod()), map, array);
    }
    
    public final void zza(@Nullable final Map<String, ?> map, final int n) {
        if (isEnabled()) {
            this.zzb(map, n);
            if (n < 200 || n >= 300) {
                this.zzdh(null);
            }
        }
    }
    
    public final void zzdg(@Nullable final String s) {
        if (isEnabled() && s != null) {
            this.zzf(s.getBytes());
        }
    }
    
    public final void zzf(final byte[] array) {
        this.zza("onNetworkResponseBody", new zzanb(array));
    }
}
