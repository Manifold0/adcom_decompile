// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.net.URL;
import java.util.HashMap;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import javax.net.ssl.SSLSocketFactory;

public final class zzas extends zzai
{
    private final zzat zzci;
    private final SSLSocketFactory zzcj;
    
    public zzas() {
        this(null);
    }
    
    private zzas(final zzat zzat) {
        this(null, null);
    }
    
    private zzas(final zzat zzat, final SSLSocketFactory sslSocketFactory) {
        this.zzci = null;
        this.zzcj = null;
    }
    
    private static InputStream zza(final HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        }
        catch (IOException ex) {
            return httpURLConnection.getErrorStream();
        }
    }
    
    private static List<zzl> zza(final Map<String, List<String>> map) {
        final ArrayList<zzl> list = new ArrayList<zzl>(map.size());
        for (final Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey() != null) {
                final Iterator<String> iterator2 = entry.getValue().iterator();
                while (iterator2.hasNext()) {
                    list.add(new zzl(entry.getKey(), iterator2.next()));
                }
            }
        }
        return list;
    }
    
    private static void zza(final HttpURLConnection httpURLConnection, final zzr<?> zzr) throws IOException, zza {
        final byte[] zzg = zzr.zzg();
        if (zzg != null) {
            httpURLConnection.setDoOutput(true);
            final String value = String.valueOf("UTF-8");
            String concat;
            if (value.length() != 0) {
                concat = "application/x-www-form-urlencoded; charset=".concat(value);
            }
            else {
                concat = new String("application/x-www-form-urlencoded; charset=");
            }
            httpURLConnection.addRequestProperty("Content-Type", concat);
            final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzg);
            dataOutputStream.close();
        }
    }
    
    @Override
    public final zzaq zza(final zzr<?> zzr, final Map<String, String> map) throws IOException, zza {
        final String url = zzr.getUrl();
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.putAll(zzr.getHeaders());
        hashMap.putAll(map);
        String zzg;
        if (this.zzci != null) {
            if ((zzg = this.zzci.zzg(url)) == null) {
                final String value = String.valueOf(url);
                String concat;
                if (value.length() != 0) {
                    concat = "URL blocked by rewriter: ".concat(value);
                }
                else {
                    concat = new String("URL blocked by rewriter: ");
                }
                throw new IOException(concat);
            }
        }
        else {
            zzg = url;
        }
        final URL url2 = new URL(zzg);
        final HttpURLConnection httpURLConnection = (HttpURLConnection)url2.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        final int zzi = zzr.zzi();
        httpURLConnection.setConnectTimeout(zzi);
        httpURLConnection.setReadTimeout(zzi);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        "https".equals(url2.getProtocol());
        for (final String s : hashMap.keySet()) {
            httpURLConnection.addRequestProperty(s, hashMap.get(s));
        }
        Label_0288: {
            switch (zzr.getMethod()) {
                default: {
                    throw new IllegalStateException("Unknown method type.");
                }
                case 0: {
                    httpURLConnection.setRequestMethod("GET");
                    break Label_0288;
                }
                case 7: {
                    httpURLConnection.setRequestMethod("PATCH");
                    zza(httpURLConnection, zzr);
                    break Label_0288;
                }
                case 6: {
                    httpURLConnection.setRequestMethod("TRACE");
                    break Label_0288;
                }
                case 5: {
                    httpURLConnection.setRequestMethod("OPTIONS");
                    break Label_0288;
                }
                case 4: {
                    httpURLConnection.setRequestMethod("HEAD");
                    break Label_0288;
                }
                case 2: {
                    httpURLConnection.setRequestMethod("PUT");
                    zza(httpURLConnection, zzr);
                    break Label_0288;
                }
                case 1: {
                    httpURLConnection.setRequestMethod("POST");
                    zza(httpURLConnection, zzr);
                    break Label_0288;
                }
                case 3: {
                    httpURLConnection.setRequestMethod("DELETE");
                }
                case -1: {
                    final int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == -1) {
                        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
                    }
                    int n;
                    if (zzr.getMethod() != 4 && (100 > responseCode || responseCode >= 200) && responseCode != 204 && responseCode != 304) {
                        n = 1;
                    }
                    else {
                        n = 0;
                    }
                    if (n == 0) {
                        return new zzaq(responseCode, zza(httpURLConnection.getHeaderFields()));
                    }
                    return new zzaq(responseCode, zza(httpURLConnection.getHeaderFields()), httpURLConnection.getContentLength(), zza(httpURLConnection));
                }
            }
        }
    }
}
