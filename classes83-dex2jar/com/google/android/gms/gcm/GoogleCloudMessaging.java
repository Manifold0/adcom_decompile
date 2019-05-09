// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import java.util.Iterator;
import android.support.annotation.RequiresPermission;
import android.os.Message;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.iid.InstanceID;
import java.util.concurrent.TimeUnit;
import android.os.Parcelable;
import com.google.android.gms.iid.zzl;
import java.io.IOException;
import android.os.Bundle;
import android.os.Looper;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Collections;
import android.support.v4.util.ArrayMap;
import android.os.Messenger;
import android.content.Intent;
import java.util.concurrent.BlockingQueue;
import android.os.Handler;
import java.util.Map;
import android.app.PendingIntent;
import android.content.Context;
import java.util.concurrent.atomic.AtomicInteger;

public class GoogleCloudMessaging
{
    public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
    public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    public static final String INSTANCE_ID_SCOPE = "GCM";
    @Deprecated
    public static final String MESSAGE_TYPE_DELETED = "deleted_messages";
    @Deprecated
    public static final String MESSAGE_TYPE_MESSAGE = "gcm";
    @Deprecated
    public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";
    @Deprecated
    public static final String MESSAGE_TYPE_SEND_EVENT = "send_event";
    private static GoogleCloudMessaging zzhzq;
    private static final AtomicInteger zzhzt;
    private Context zzaif;
    private PendingIntent zzhzr;
    private final Map<String, Handler> zzhzs;
    private final BlockingQueue<Intent> zzhzu;
    private Messenger zzhzv;
    
    static {
        zzhzt = new AtomicInteger(1);
    }
    
    public GoogleCloudMessaging() {
        this.zzhzs = Collections.synchronizedMap((Map<String, Handler>)new ArrayMap());
        this.zzhzu = new LinkedBlockingQueue<Intent>();
        this.zzhzv = new Messenger((Handler)new zzc(this, Looper.getMainLooper()));
    }
    
    public static GoogleCloudMessaging getInstance(final Context context) {
        synchronized (GoogleCloudMessaging.class) {
            if (GoogleCloudMessaging.zzhzq == null) {
                (GoogleCloudMessaging.zzhzq = new GoogleCloudMessaging()).zzaif = context.getApplicationContext();
            }
            return GoogleCloudMessaging.zzhzq;
        }
    }
    
    @Deprecated
    private final Intent zza(final Bundle bundle, final boolean b) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        if (zzdi(this.zzaif) < 0) {
            throw new IOException("Google Play Services missing");
        }
        Label_0175: {
            if (!b) {
                break Label_0175;
            }
            String s = "com.google.iid.TOKEN_REQUEST";
            while (true) {
                final Intent intent = new Intent(s);
                intent.setPackage(zzl.zzdm(this.zzaif));
                this.zzg(intent);
                intent.putExtra("google.message_id", new StringBuilder(String.valueOf("google.rpc").length() + 11).append("google.rpc").append(GoogleCloudMessaging.zzhzt.getAndIncrement()).toString());
                intent.putExtras(bundle);
                intent.putExtra("google.messenger", (Parcelable)this.zzhzv);
                Label_0182: {
                    if (!b) {
                        break Label_0182;
                    }
                    this.zzaif.sendBroadcast(intent);
                    try {
                        return this.zzhzu.poll(30000L, TimeUnit.MILLISECONDS);
                        s = "com.google.android.c2dm.intent.REGISTER";
                        continue;
                        this.zzaif.startService(intent);
                        return this.zzhzu.poll(30000L, TimeUnit.MILLISECONDS);
                    }
                    catch (InterruptedException ex) {
                        throw new IOException(ex.getMessage());
                    }
                }
                break;
            }
        }
    }
    
    @Deprecated
    private final String zza(final boolean b, final String... array) throws IOException {
        final String zzdm;
        synchronized (this) {
            zzdm = zzl.zzdm(this.zzaif);
            if (zzdm == null) {
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
        }
        final String[] array2;
        final String zzd = zzd(array2);
        final Bundle bundle = new Bundle();
        String s;
        if (zzdm.contains(".gsf")) {
            bundle.putString("legacy.sender", zzd);
            s = InstanceID.getInstance(this.zzaif).getToken(zzd, "GCM", bundle);
        }
        else {
            bundle.putString("sender", zzd);
            final Intent zza = this.zza(bundle, b);
            if (zza == null) {
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
            if ((s = zza.getStringExtra("registration_id")) == null) {
                final String stringExtra = zza.getStringExtra("error");
                if (stringExtra != null) {
                    throw new IOException(stringExtra);
                }
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
        }
        // monitorexit(this)
        return s;
    }
    
    private final void zzauk() {
        synchronized (this) {
            if (this.zzhzr != null) {
                this.zzhzr.cancel();
                this.zzhzr = null;
            }
        }
    }
    
    private static String zzd(final String... array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        final StringBuilder sb = new StringBuilder(array[0]);
        for (int i = 1; i < array.length; ++i) {
            sb.append(',').append(array[i]);
        }
        return sb.toString();
    }
    
    public static int zzdi(final Context context) {
        final String zzdm = zzl.zzdm(context);
        if (zzdm != null) {
            try {
                final PackageInfo packageInfo = context.getPackageManager().getPackageInfo(zzdm, 0);
                if (packageInfo != null) {
                    return packageInfo.versionCode;
                }
            }
            catch (PackageManager$NameNotFoundException ex) {}
        }
        return -1;
    }
    
    private final boolean zzf(final Intent obj) {
        String s2;
        final String s = s2 = obj.getStringExtra("In-Reply-To");
        if (s == null) {
            s2 = s;
            if (obj.hasExtra("error")) {
                s2 = obj.getStringExtra("google.message_id");
            }
        }
        if (s2 != null) {
            final Handler handler = this.zzhzs.remove(s2);
            if (handler != null) {
                final Message obtain = Message.obtain();
                obtain.obj = obj;
                return handler.sendMessage(obtain);
            }
        }
        return false;
    }
    
    private final void zzg(final Intent intent) {
        synchronized (this) {
            if (this.zzhzr == null) {
                final Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                this.zzhzr = PendingIntent.getBroadcast(this.zzaif, 0, intent2, 0);
            }
            intent.putExtra("app", (Parcelable)this.zzhzr);
        }
    }
    
    public void close() {
        GoogleCloudMessaging.zzhzq = null;
        zza.zzhzb = null;
        this.zzauk();
    }
    
    public String getMessageType(final Intent intent) {
        String stringExtra;
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            stringExtra = null;
        }
        else if ((stringExtra = intent.getStringExtra("message_type")) == null) {
            return "gcm";
        }
        return stringExtra;
    }
    
    @Deprecated
    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public String register(final String... array) throws IOException {
        synchronized (this) {
            return this.zza(zzl.zzdl(this.zzaif), array);
        }
    }
    
    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public void send(final String s, final String s2, final long n, final Bundle bundle) throws IOException {
        if (s == null) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        final String zzdm = zzl.zzdm(this.zzaif);
        if (zzdm == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        final Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        this.zzg(intent);
        intent.setPackage(zzdm);
        intent.putExtra("google.to", s);
        intent.putExtra("google.message_id", s2);
        intent.putExtra("google.ttl", Long.toString(n));
        final int index = s.indexOf(64);
        String substring;
        if (index > 0) {
            substring = s.substring(0, index);
        }
        else {
            substring = s;
        }
        InstanceID.getInstance(this.zzaif);
        intent.putExtra("google.from", InstanceID.zzauu().zze("", substring, "GCM"));
        if (zzdm.contains(".gsf")) {
            final Bundle bundle2 = new Bundle();
            for (final String s3 : bundle.keySet()) {
                final Object value = bundle.get(s3);
                if (value instanceof String) {
                    final String value2 = String.valueOf(s3);
                    String concat;
                    if (value2.length() != 0) {
                        concat = "gcm.".concat(value2);
                    }
                    else {
                        concat = new String("gcm.");
                    }
                    bundle2.putString(concat, (String)value);
                }
            }
            bundle2.putString("google.to", s);
            bundle2.putString("google.message_id", s2);
            InstanceID.getInstance(this.zzaif).zzb("GCM", "upstream", bundle2);
            return;
        }
        this.zzaif.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }
    
    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public void send(final String s, final String s2, final Bundle bundle) throws IOException {
        this.send(s, s2, -1L, bundle);
    }
    
    @Deprecated
    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public void unregister() throws IOException {
        synchronized (this) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                throw new IOException("MAIN_THREAD");
            }
        }
        InstanceID.getInstance(this.zzaif).deleteInstanceID();
    }
    // monitorexit(this)
}
