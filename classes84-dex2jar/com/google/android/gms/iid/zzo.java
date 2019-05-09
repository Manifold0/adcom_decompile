// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.iid;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
import android.util.Base64;
import java.util.Iterator;
import java.security.KeyPair;
import android.content.SharedPreferences$Editor;
import java.io.IOException;
import android.util.Log;
import java.io.File;
import com.google.android.gms.common.util.zzv;
import android.content.SharedPreferences;
import android.content.Context;

public final class zzo
{
    private Context zzaif;
    private SharedPreferences zzidi;
    
    public zzo(final Context context) {
        this(context, "com.google.android.gms.appid");
    }
    
    private zzo(final Context zzaif, String value) {
        this.zzaif = zzaif;
        this.zzidi = zzaif.getSharedPreferences(value, 0);
        final String value2 = String.valueOf(value);
        value = String.valueOf("-no-backup");
        Label_0097: {
            if (value.length() == 0) {
                break Label_0097;
            }
            String concat = value2.concat(value);
            while (true) {
                final File file = new File(zzv.getNoBackupFilesDir(this.zzaif), concat);
                if (file.exists()) {
                    return;
                }
                try {
                    if (file.createNewFile() && !this.isEmpty()) {
                        Log.i("InstanceID/Store", "App restored, clearing state");
                        InstanceIDListenerService.zza(this.zzaif, this);
                    }
                    return;
                    concat = new String(value2);
                    continue;
                }
                catch (IOException ex) {
                    if (Log.isLoggable("InstanceID/Store", 3)) {
                        final String value3 = String.valueOf(ex.getMessage());
                        String concat2;
                        if (value3.length() != 0) {
                            concat2 = "Error creating file in no backup dir: ".concat(value3);
                        }
                        else {
                            concat2 = new String("Error creating file in no backup dir: ");
                        }
                        Log.d("InstanceID/Store", concat2);
                    }
                }
                break;
            }
        }
    }
    
    private final void zza(final SharedPreferences$Editor sharedPreferences$Editor, final String s, final String s2, final String s3) {
        synchronized (this) {
            sharedPreferences$Editor.putString(new StringBuilder(String.valueOf(s).length() + String.valueOf("|S|").length() + String.valueOf(s2).length()).append(s).append("|S|").append(s2).toString(), s3);
        }
    }
    
    private static String zzd(final String s, final String s2, final String s3) {
        return new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf("|T|").length() + String.valueOf(s2).length() + String.valueOf(s3).length()).append(s).append("|T|").append(s2).append("|").append(s3).toString();
    }
    
    final String get(String string) {
        synchronized (this) {
            string = this.zzidi.getString(string, (String)null);
            return string;
        }
    }
    
    final String get(String string, final String s) {
        synchronized (this) {
            string = this.zzidi.getString(new StringBuilder(String.valueOf(string).length() + String.valueOf("|S|").length() + String.valueOf(s).length()).append(string).append("|S|").append(s).toString(), (String)null);
            return string;
        }
    }
    
    public final boolean isEmpty() {
        return this.zzidi.getAll().isEmpty();
    }
    
    public final void zza(String zzd, final String s, final String s2, final String s3, final String s4) {
        synchronized (this) {
            zzd = zzd(zzd, s, s2);
            final SharedPreferences$Editor edit = this.zzidi.edit();
            edit.putString(zzd, s3);
            edit.putString("appVersion", s4);
            edit.putString("lastToken", Long.toString(System.currentTimeMillis() / 1000L));
            edit.commit();
        }
    }
    
    public final void zzaux() {
        synchronized (this) {
            this.zzidi.edit().clear().commit();
        }
    }
    
    final KeyPair zzd(final String s, final long n) {
        synchronized (this) {
            final KeyPair zzauq = zza.zzauq();
            final SharedPreferences$Editor edit = this.zzidi.edit();
            this.zza(edit, s, "|P|", InstanceID.zzn(zzauq.getPublic().getEncoded()));
            this.zza(edit, s, "|K|", InstanceID.zzn(zzauq.getPrivate().getEncoded()));
            this.zza(edit, s, "cre", Long.toString(n));
            edit.commit();
            return zzauq;
        }
    }
    
    public final String zze(String s, final String s2, final String s3) {
        synchronized (this) {
            s = zzd(s, s2, s3);
            s = this.zzidi.getString(s, (String)null);
            return s;
        }
    }
    
    public final void zzf(String zzd, final String s, final String s2) {
        synchronized (this) {
            zzd = zzd(zzd, s, s2);
            final SharedPreferences$Editor edit = this.zzidi.edit();
            edit.remove(zzd);
            edit.commit();
        }
    }
    
    public final void zzht(final String s) {
        final SharedPreferences$Editor edit;
        synchronized (this) {
            edit = this.zzidi.edit();
            for (final String s2 : this.zzidi.getAll().keySet()) {
                if (s2.startsWith(s)) {
                    edit.remove(s2);
                }
            }
        }
        edit.commit();
    }
    // monitorexit(this)
    
    public final void zzhu(final String s) {
        this.zzht(String.valueOf(s).concat("|T|"));
    }
    
    final KeyPair zzhv(String decode) {
        final String value = this.get((String)decode, "|P|");
        final String value2 = this.get((String)decode, "|K|");
        if (value == null || value2 == null) {
            return null;
        }
        try {
            decode = (NoSuchAlgorithmException)(Object)Base64.decode(value, 8);
            final byte[] decode2 = Base64.decode(value2, 8);
            final KeyFactory instance = KeyFactory.getInstance("RSA");
            decode = (NoSuchAlgorithmException)new KeyPair(instance.generatePublic(new X509EncodedKeySpec((byte[])(Object)decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            return (KeyPair)decode;
        }
        catch (InvalidKeySpecException ex) {}
        catch (NoSuchAlgorithmException decode) {
            goto Label_0082;
        }
    }
}
