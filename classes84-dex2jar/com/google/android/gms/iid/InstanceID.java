// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.iid;

import android.os.Looper;
import java.io.IOException;
import android.content.pm.PackageManager$NameNotFoundException;
import java.security.NoSuchAlgorithmException;
import android.util.Log;
import android.util.Base64;
import java.security.MessageDigest;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import java.security.KeyPair;
import android.content.Context;
import java.util.Map;

public class InstanceID
{
    public static final String ERROR_BACKOFF = "RETRY_LATER";
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_MISSING_INSTANCEID_SERVICE = "MISSING_INSTANCEID_SERVICE";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String ERROR_TIMEOUT = "TIMEOUT";
    private static Map<String, InstanceID> zzick;
    private static zzo zzicl;
    private static zzl zzicm;
    private static String zzicq;
    private Context mContext;
    private KeyPair zzicn;
    private String zzico;
    private long zzicp;
    
    static {
        InstanceID.zzick = (Map<String, InstanceID>)new ArrayMap();
    }
    
    private InstanceID(final Context context, final String zzico, final Bundle bundle) {
        this.zzico = "";
        this.mContext = context.getApplicationContext();
        this.zzico = zzico;
    }
    
    public static InstanceID getInstance(final Context context) {
        return getInstance(context, null);
    }
    
    @KeepForSdk
    public static InstanceID getInstance(final Context context, final Bundle bundle) {
        // monitorenter(InstanceID.class)
        while (true) {
            String string = null;
            Label_0126: {
                if (bundle == null) {
                    string = "";
                    break Label_0126;
                }
                while (true) {
                    Label_0107: {
                        break Label_0107;
                        try {
                            final Context applicationContext = context.getApplicationContext();
                            if (InstanceID.zzicl == null) {
                                InstanceID.zzicl = new zzo(applicationContext);
                                InstanceID.zzicm = new zzl(applicationContext);
                            }
                            InstanceID.zzicq = Integer.toString(zzdj(applicationContext));
                            InstanceID instanceID;
                            if ((instanceID = InstanceID.zzick.get(string)) == null) {
                                instanceID = new InstanceID(applicationContext, string, bundle);
                                InstanceID.zzick.put(string, instanceID);
                            }
                            return instanceID;
                            string = bundle.getString("subtype");
                            break Label_0126;
                        }
                        finally {
                        }
                        // monitorexit(InstanceID.class)
                    }
                    continue;
                }
            }
            if (string == null) {
                string = "";
            }
            continue;
        }
    }
    
    static String zza(final KeyPair keyPair) {
        final byte[] encoded = keyPair.getPublic().getEncoded();
        try {
            final byte[] digest = MessageDigest.getInstance("SHA1").digest(encoded);
            digest[0] = (byte)((digest[0] & 0xF) + 112);
            return Base64.encodeToString(digest, 0, 8, 11);
        }
        catch (NoSuchAlgorithmException ex) {
            Log.w("InstanceID", "Unexpected error, device missing required algorithms");
            return null;
        }
    }
    
    private final KeyPair zzaus() {
        if (this.zzicn == null) {
            this.zzicn = InstanceID.zzicl.zzhv(this.zzico);
        }
        if (this.zzicn == null) {
            this.zzicp = System.currentTimeMillis();
            this.zzicn = InstanceID.zzicl.zzd(this.zzico, this.zzicp);
        }
        return this.zzicn;
    }
    
    public static zzo zzauu() {
        return InstanceID.zzicl;
    }
    
    static int zzdj(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            final String value = String.valueOf(ex);
            Log.w("InstanceID", new StringBuilder(String.valueOf(value).length() + 38).append("Never happens: can't find own package ").append(value).toString());
            return 0;
        }
    }
    
    static String zzdk(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }
        catch (PackageManager$NameNotFoundException ex) {
            final String value = String.valueOf(ex);
            Log.w("InstanceID", new StringBuilder(String.valueOf(value).length() + 38).append("Never happens: can't find own package ").append(value).toString());
            return null;
        }
    }
    
    static String zzn(final byte[] array) {
        return Base64.encodeToString(array, 11);
    }
    
    public void deleteInstanceID() throws IOException {
        this.zza("*", "*", null);
        this.zzaut();
    }
    
    public void deleteToken(final String s, final String s2) throws IOException {
        this.zza(s, s2, null);
    }
    
    public long getCreationTime() {
        if (this.zzicp == 0L) {
            final String value = InstanceID.zzicl.get(this.zzico, "cre");
            if (value != null) {
                this.zzicp = Long.parseLong(value);
            }
        }
        return this.zzicp;
    }
    
    public String getId() {
        return zza(this.zzaus());
    }
    
    @KeepForSdk
    public String getSubtype() {
        return this.zzico;
    }
    
    public String getToken(final String s, final String s2) throws IOException {
        return this.getToken(s, s2, null);
    }
    
    public String getToken(final String s, final String s2, final Bundle bundle) throws IOException {
        final int n = 0;
        final int n2 = 1;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        final String value = InstanceID.zzicl.get("appVersion");
        int n3;
        if (value == null || !value.equals(InstanceID.zzicq)) {
            n3 = 1;
        }
        else {
            final String value2 = InstanceID.zzicl.get("lastToken");
            if (value2 == null) {
                n3 = 1;
            }
            else if (System.currentTimeMillis() / 1000L - Long.parseLong(value2) > 604800L) {
                n3 = 1;
            }
            else {
                n3 = 0;
            }
        }
        String s3;
        if (n3 != 0) {
            s3 = null;
        }
        else {
            s3 = InstanceID.zzicl.zze(this.zzico, s, s2);
        }
        if (s3 == null) {
            Bundle bundle2;
            if ((bundle2 = bundle) == null) {
                bundle2 = new Bundle();
            }
            int n4 = n2;
            if (bundle2.getString("ttl") != null) {
                n4 = 0;
            }
            if ("jwt".equals(bundle2.getString("type"))) {
                n4 = n;
            }
            final String s4 = s3 = this.zzb(s, s2, bundle2);
            if (s4 != null) {
                s3 = s4;
                if (n4 != 0) {
                    InstanceID.zzicl.zza(this.zzico, s, s2, s4, InstanceID.zzicq);
                    return s4;
                }
            }
        }
        return s3;
    }
    
    public final void zza(String zzico, String zzico2, final Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        InstanceID.zzicl.zzf(this.zzico, zzico, zzico2);
        Bundle bundle2;
        if ((bundle2 = bundle) == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString("sender", zzico);
        if (zzico2 != null) {
            bundle2.putString("scope", zzico2);
        }
        bundle2.putString("subscription", zzico);
        bundle2.putString("delete", "1");
        bundle2.putString("X-delete", "1");
        if ("".equals(this.zzico)) {
            zzico2 = zzico;
        }
        else {
            zzico2 = this.zzico;
        }
        bundle2.putString("subtype", zzico2);
        if (!"".equals(this.zzico)) {
            zzico = this.zzico;
        }
        bundle2.putString("X-subtype", zzico);
        zzl.zzj(InstanceID.zzicm.zza(bundle2, this.zzaus()));
    }
    
    public final void zzaut() {
        this.zzicp = 0L;
        InstanceID.zzicl.zzht(String.valueOf(this.zzico).concat("|"));
        this.zzicn = null;
    }
    
    public final String zzb(final String s, String zzico, final Bundle bundle) throws IOException {
        if (zzico != null) {
            bundle.putString("scope", zzico);
        }
        bundle.putString("sender", s);
        if ("".equals(this.zzico)) {
            zzico = s;
        }
        else {
            zzico = this.zzico;
        }
        if (!bundle.containsKey("legacy.register")) {
            bundle.putString("subscription", s);
            bundle.putString("subtype", zzico);
            bundle.putString("X-subscription", s);
            bundle.putString("X-subtype", zzico);
        }
        return zzl.zzj(InstanceID.zzicm.zza(bundle, this.zzaus()));
    }
}
