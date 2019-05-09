package com.google.android.gms.iid;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class InstanceID {
    public static final String ERROR_BACKOFF = "RETRY_LATER";
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_MISSING_INSTANCEID_SERVICE = "MISSING_INSTANCEID_SERVICE";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String ERROR_TIMEOUT = "TIMEOUT";
    private static Map<String, InstanceID> zzick = new ArrayMap();
    private static zzo zzicl;
    private static zzl zzicm;
    private static String zzicq;
    private Context mContext;
    private KeyPair zzicn;
    private String zzico = "";
    private long zzicp;

    private InstanceID(Context context, String str, Bundle bundle) {
        this.mContext = context.getApplicationContext();
        this.zzico = str;
    }

    public static InstanceID getInstance(Context context) {
        return getInstance(context, null);
    }

    @KeepForSdk
    public static synchronized InstanceID getInstance(Context context, Bundle bundle) {
        InstanceID instanceID;
        synchronized (InstanceID.class) {
            String string = bundle == null ? "" : bundle.getString("subtype");
            String str = string == null ? "" : string;
            Context applicationContext = context.getApplicationContext();
            if (zzicl == null) {
                zzicl = new zzo(applicationContext);
                zzicm = new zzl(applicationContext);
            }
            zzicq = Integer.toString(zzdj(applicationContext));
            instanceID = (InstanceID) zzick.get(str);
            if (instanceID == null) {
                instanceID = new InstanceID(applicationContext, str, bundle);
                zzick.put(str, instanceID);
            }
        }
        return instanceID;
    }

    static String zza(KeyPair keyPair) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(keyPair.getPublic().getEncoded());
            digest[0] = (byte) ((digest[0] & 15) + 112);
            return Base64.encodeToString(digest, 0, 8, 11);
        } catch (NoSuchAlgorithmException e) {
            Log.w("InstanceID", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    private final KeyPair zzaus() {
        if (this.zzicn == null) {
            this.zzicn = zzicl.zzhv(this.zzico);
        }
        if (this.zzicn == null) {
            this.zzicp = System.currentTimeMillis();
            this.zzicn = zzicl.zzd(this.zzico, this.zzicp);
        }
        return this.zzicn;
    }

    public static zzo zzauu() {
        return zzicl;
    }

    static int zzdj(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("InstanceID", new StringBuilder(String.valueOf(valueOf).length() + 38).append("Never happens: can't find own package ").append(valueOf).toString());
            return i;
        }
    }

    static String zzdk(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            Log.w("InstanceID", new StringBuilder(String.valueOf(valueOf).length() + 38).append("Never happens: can't find own package ").append(valueOf).toString());
            return null;
        }
    }

    static String zzn(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    public void deleteInstanceID() throws IOException {
        zza("*", "*", null);
        zzaut();
    }

    public void deleteToken(String str, String str2) throws IOException {
        zza(str, str2, null);
    }

    public long getCreationTime() {
        if (this.zzicp == 0) {
            String str = zzicl.get(this.zzico, "cre");
            if (str != null) {
                this.zzicp = Long.parseLong(str);
            }
        }
        return this.zzicp;
    }

    public String getId() {
        return zza(zzaus());
    }

    @KeepForSdk
    public String getSubtype() {
        return this.zzico;
    }

    public String getToken(String str, String str2) throws IOException {
        return getToken(str, str2, null);
    }

    public String getToken(String str, String str2, Bundle bundle) throws IOException {
        Object obj = null;
        Object obj2 = 1;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        Object obj3;
        String str3 = zzicl.get(RequestParameters.APPLICATION_VERSION_NAME);
        if (str3 == null || !str3.equals(zzicq)) {
            obj3 = 1;
        } else {
            str3 = zzicl.get("lastToken");
            int i;
            if (str3 == null) {
                i = 1;
            } else {
                if ((System.currentTimeMillis() / 1000) - Long.valueOf(Long.parseLong(str3)).longValue() > 604800) {
                    i = 1;
                } else {
                    obj3 = null;
                }
            }
        }
        String zze = obj3 != null ? null : zzicl.zze(this.zzico, str, str2);
        if (zze == null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            if (bundle.getString("ttl") != null) {
                obj2 = null;
            }
            if (!"jwt".equals(bundle.getString("type"))) {
                obj = obj2;
            }
            zze = zzb(str, str2, bundle);
            if (!(zze == null || r1 == null)) {
                zzicl.zza(this.zzico, str, str2, zze, zzicq);
            }
        }
        return zze;
    }

    public final void zza(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        zzicl.zzf(this.zzico, str, str2);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("sender", str);
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("subscription", str);
        bundle.putString("delete", "1");
        bundle.putString("X-delete", "1");
        bundle.putString("subtype", "".equals(this.zzico) ? str : this.zzico);
        String str3 = "X-subtype";
        if (!"".equals(this.zzico)) {
            str = this.zzico;
        }
        bundle.putString(str3, str);
        zzl.zzj(zzicm.zza(bundle, zzaus()));
    }

    public final void zzaut() {
        this.zzicp = 0;
        zzicl.zzht(String.valueOf(this.zzico).concat("|"));
        this.zzicn = null;
    }

    public final String zzb(String str, String str2, Bundle bundle) throws IOException {
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("sender", str);
        String str3 = "".equals(this.zzico) ? str : this.zzico;
        if (!bundle.containsKey("legacy.register")) {
            bundle.putString("subscription", str);
            bundle.putString("subtype", str3);
            bundle.putString("X-subscription", str);
            bundle.putString("X-subtype", str3);
        }
        return zzl.zzj(zzicm.zza(bundle, zzaus()));
    }
}
