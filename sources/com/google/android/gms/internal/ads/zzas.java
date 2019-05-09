package com.google.android.gms.internal.ads;

import com.adjust.sdk.Constants;
import com.applovin.sdk.AppLovinErrorCodes;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLSocketFactory;

public final class zzas extends zzai {
    private final zzat zzci;
    private final SSLSocketFactory zzcj;

    public zzas() {
        this(null);
    }

    private zzas(zzat zzat) {
        this(null, null);
    }

    private zzas(zzat zzat, SSLSocketFactory sSLSocketFactory) {
        this.zzci = null;
        this.zzcj = null;
    }

    private static InputStream zza(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException e) {
            return httpURLConnection.getErrorStream();
        }
    }

    private static List<zzl> zza(Map<String, List<String>> map) {
        List<zzl> arrayList = new ArrayList(map.size());
        for (Entry entry : map.entrySet()) {
            if (entry.getKey() != null) {
                for (String zzl : (List) entry.getValue()) {
                    arrayList.add(new zzl((String) entry.getKey(), zzl));
                }
            }
        }
        return arrayList;
    }

    private static void zza(HttpURLConnection httpURLConnection, zzr<?> zzr) throws IOException, zza {
        byte[] zzg = zzr.zzg();
        if (zzg != null) {
            httpURLConnection.setDoOutput(true);
            String str = "Content-Type";
            String str2 = "application/x-www-form-urlencoded; charset=";
            String valueOf = String.valueOf("UTF-8");
            httpURLConnection.addRequestProperty(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(zzg);
            dataOutputStream.close();
        }
    }

    public final zzaq zza(zzr<?> zzr, Map<String, String> map) throws IOException, zza {
        String zzg;
        String url = zzr.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(zzr.getHeaders());
        hashMap.putAll(map);
        if (this.zzci != null) {
            zzg = this.zzci.zzg(url);
            if (zzg == null) {
                String str = "URL blocked by rewriter: ";
                zzg = String.valueOf(url);
                throw new IOException(zzg.length() != 0 ? str.concat(zzg) : new String(str));
            }
        }
        zzg = url;
        URL url2 = new URL(zzg);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        int zzi = zzr.zzi();
        httpURLConnection.setConnectTimeout(zzi);
        httpURLConnection.setReadTimeout(zzi);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        Constants.SCHEME.equals(url2.getProtocol());
        for (String url3 : hashMap.keySet()) {
            httpURLConnection.addRequestProperty(url3, (String) hashMap.get(url3));
        }
        switch (zzr.getMethod()) {
            case -1:
                break;
            case 0:
                httpURLConnection.setRequestMethod("GET");
                break;
            case 1:
                httpURLConnection.setRequestMethod("POST");
                zza(httpURLConnection, (zzr) zzr);
                break;
            case 2:
                httpURLConnection.setRequestMethod("PUT");
                zza(httpURLConnection, (zzr) zzr);
                break;
            case 3:
                httpURLConnection.setRequestMethod("DELETE");
                break;
            case 4:
                httpURLConnection.setRequestMethod("HEAD");
                break;
            case 5:
                httpURLConnection.setRequestMethod("OPTIONS");
                break;
            case 6:
                httpURLConnection.setRequestMethod("TRACE");
                break;
            case 7:
                httpURLConnection.setRequestMethod("PATCH");
                zza(httpURLConnection, (zzr) zzr);
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
        zzi = httpURLConnection.getResponseCode();
        if (zzi == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        boolean z = (zzr.getMethod() == 4 || ((100 <= zzi && zzi < 200) || zzi == AppLovinErrorCodes.NO_FILL || zzi == 304)) ? false : true;
        return !z ? new zzaq(zzi, zza(httpURLConnection.getHeaderFields())) : new zzaq(zzi, zza(httpURLConnection.getHeaderFields()), httpURLConnection.getContentLength(), zza(httpURLConnection));
    }
}
