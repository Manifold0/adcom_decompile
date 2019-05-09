// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import com.google.android.gms.ads.internal.zzbv;
import android.view.View;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.util.PlatformVersion;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executor;
import java.util.Map;
import android.support.annotation.Nullable;
import java.util.Iterator;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.Locale;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashSet;
import android.support.annotation.VisibleForTesting;
import java.util.LinkedHashMap;
import javax.annotation.concurrent.GuardedBy;
import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Future;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzaii implements zzait
{
    private static List<Future<Void>> zzcml;
    private static ScheduledExecutorService zzcmm;
    private final Context mContext;
    private final Object mLock;
    private final zzaiq zzciy;
    @GuardedBy("mLock")
    private final zzbfm zzcmn;
    @GuardedBy("mLock")
    private final LinkedHashMap<String, zzbfu> zzcmo;
    @GuardedBy("mLock")
    private final List<String> zzcmp;
    @GuardedBy("mLock")
    private final List<String> zzcmq;
    private final zzaiv zzcmr;
    @VisibleForTesting
    private boolean zzcms;
    private final zzaiw zzcmt;
    private HashSet<String> zzcmu;
    private boolean zzcmv;
    private boolean zzcmw;
    private boolean zzcmx;
    
    static {
        zzaii.zzcml = Collections.synchronizedList(new ArrayList<Future<Void>>());
        zzaii.zzcmm = Executors.newSingleThreadScheduledExecutor();
    }
    
    public zzaii(final Context context, final zzang zzang, final zzaiq zzciy, final String s, final zzaiv zzcmr) {
        this.zzcmp = new ArrayList<String>();
        this.zzcmq = new ArrayList<String>();
        this.mLock = new Object();
        this.zzcmu = new HashSet<String>();
        this.zzcmv = false;
        this.zzcmw = false;
        this.zzcmx = false;
        Preconditions.checkNotNull((Object)zzciy, (Object)"SafeBrowsing config is not present.");
        Context applicationContext = context;
        if (context.getApplicationContext() != null) {
            applicationContext = context.getApplicationContext();
        }
        this.mContext = applicationContext;
        this.zzcmo = new LinkedHashMap<String, zzbfu>();
        this.zzcmr = zzcmr;
        this.zzciy = zzciy;
        final Iterator<String> iterator = this.zzciy.zzcnh.iterator();
        while (iterator.hasNext()) {
            this.zzcmu.add(iterator.next().toLowerCase(Locale.ENGLISH));
        }
        this.zzcmu.remove("cookie".toLowerCase(Locale.ENGLISH));
        final zzbfm zzcmn = new zzbfm();
        zzcmn.zzamf = 8;
        zzcmn.url = s;
        zzcmn.zzech = s;
        zzcmn.zzecj = new zzbfn();
        zzcmn.zzecj.zzcnd = this.zzciy.zzcnd;
        final zzbfv zzect = new zzbfv();
        zzect.zzedv = zzang.zzcw;
        zzect.zzedx = Wrappers.packageManager(this.mContext).isCallerInstantApp();
        final long n = GoogleApiAvailabilityLight.getInstance().getApkVersion(this.mContext);
        if (n > 0L) {
            zzect.zzedw = n;
        }
        zzcmn.zzect = zzect;
        this.zzcmn = zzcmn;
        this.zzcmt = new zzaiw(this.mContext, this.zzciy.zzcnk, this);
    }
    
    @Nullable
    private final zzbfu zzci(final String s) {
        synchronized (this.mLock) {
            return this.zzcmo.get(s);
        }
    }
    
    @VisibleForTesting
    private final zzanz<Void> zzpk() {
        final boolean b = true;
        int n = 0;
        Label_0059: {
            if (this.zzcms) {
                n = (b ? 1 : 0);
                if (this.zzciy.zzcnj) {
                    break Label_0059;
                }
            }
            if (this.zzcmx) {
                n = (b ? 1 : 0);
                if (this.zzciy.zzcni) {
                    break Label_0059;
                }
            }
            if (!this.zzcms && this.zzciy.zzcng) {
                n = (b ? 1 : 0);
            }
            else {
                n = 0;
            }
        }
        if (n == 0) {
            return (zzanz<Void>)zzano.zzi((Object)null);
        }
        synchronized (this.mLock) {
            this.zzcmn.zzeck = new zzbfu[this.zzcmo.size()];
            this.zzcmo.values().toArray(this.zzcmn.zzeck);
            this.zzcmn.zzecu = this.zzcmp.toArray(new String[0]);
            this.zzcmn.zzecv = this.zzcmq.toArray(new String[0]);
            if (zzais.isEnabled()) {
                final String url = this.zzcmn.url;
                final String zzecl = this.zzcmn.zzecl;
                final StringBuilder sb = new StringBuilder(new StringBuilder(String.valueOf(url).length() + 53 + String.valueOf(zzecl).length()).append("Sending SB report\n  url: ").append(url).append("\n  clickUrl: ").append(zzecl).append("\n  resources: \n").toString());
                final zzbfu[] zzeck = this.zzcmn.zzeck;
                for (int length = zzeck.length, i = 0; i < length; ++i) {
                    final zzbfu zzbfu = zzeck[i];
                    sb.append("    [");
                    sb.append(zzbfu.zzedu.length);
                    sb.append("] ");
                    sb.append(zzbfu.url);
                }
                zzais.zzck(sb.toString());
            }
            final zzanz<String> zza = new zzalt(this.mContext).zza(1, this.zzciy.zzcne, null, zzbfi.zzb((zzbfi)this.zzcmn));
            if (zzais.isEnabled()) {
                zza.zza(new zzain(this), zzaki.zzcrj);
            }
            return zzano.zza((zzanz<Object>)zza, (zzank<Object, Void>)zzaik.zzcmz, zzaoe.zzcvz);
        }
    }
    
    @Override
    public final void zza(final String url, Map<String, String> s, final int n) {
        final Object mLock = this.mLock;
        // monitorenter(mLock)
        while (true) {
            if (n == 3) {
                zzbfu zzbfu = null;
                ArrayList<zzbfo> list = null;
                Object o;
                zzbfo zzbfo;
                zzbfo[] zzeda;
                Label_0187_Outer:Label_0209_Outer:
                while (true) {
                Label_0308:
                    while (true) {
                    Label_0300:
                        while (true) {
                            Label_0293: {
                                try {
                                    this.zzcmx = true;
                                    if (this.zzcmo.containsKey(url)) {
                                        if (n == 3) {
                                            this.zzcmo.get(url).zzedt = n;
                                        }
                                        return;
                                    }
                                    zzbfu = new zzbfu();
                                    zzbfu.zzedt = n;
                                    zzbfu.zzedn = this.zzcmo.size();
                                    zzbfu.url = url;
                                    zzbfu.zzedo = new zzbfp();
                                    if (this.zzcmu.size() > 0 && s != null) {
                                        list = new ArrayList<zzbfo>();
                                        for (Object o : ((Map<K, String>)s).entrySet()) {
                                            try {
                                                if (((Map.Entry<String, String>)o).getKey() == null) {
                                                    break Label_0293;
                                                }
                                                s = ((Map.Entry<String, String>)o).getKey();
                                                if (((Map.Entry<String, String>)o).getValue() == null) {
                                                    break Label_0300;
                                                }
                                                o = ((Map.Entry<String, String>)o).getValue();
                                                if (!this.zzcmu.contains(s.toLowerCase(Locale.ENGLISH))) {
                                                    continue Label_0187_Outer;
                                                }
                                                zzbfo = new zzbfo();
                                                zzbfo.zzecx = s.getBytes("UTF-8");
                                                zzbfo.zzecy = ((String)o).getBytes("UTF-8");
                                                list.add(zzbfo);
                                            }
                                            catch (UnsupportedEncodingException s) {
                                                zzais.zzck("Cannot convert string to bytes, skip header.");
                                            }
                                        }
                                        break Label_0308;
                                    }
                                    break;
                                }
                                finally {
                                }
                                // monitorexit(mLock)
                            }
                            s = "";
                            continue Label_0209_Outer;
                        }
                        o = "";
                        continue;
                    }
                    zzeda = new zzbfo[list.size()];
                    list.toArray(zzeda);
                    zzbfu.zzedo.zzeda = zzeda;
                    break;
                }
                final String s2;
                this.zzcmo.put(s2, zzbfu);
                // monitorexit(mLock)
                return;
            }
            continue;
        }
    }
    
    @Override
    public final String[] zzb(final String[] array) {
        return this.zzcmt.zzc(array).toArray(new String[0]);
    }
    
    @Override
    public final void zzcf(final String zzecl) {
        synchronized (this.mLock) {
            this.zzcmn.zzecl = zzecl;
        }
    }
    
    final void zzcg(final String s) {
        synchronized (this.mLock) {
            this.zzcmp.add(s);
        }
    }
    
    final void zzch(final String s) {
        synchronized (this.mLock) {
            this.zzcmq.add(s);
        }
    }
    
    @Override
    public final zzaiq zzpg() {
        return this.zzciy;
    }
    
    @Override
    public final boolean zzph() {
        return PlatformVersion.isAtLeastKitKat() && this.zzciy.zzcnf && !this.zzcmw;
    }
    
    @Override
    public final void zzpi() {
        this.zzcmv = true;
    }
    
    @Override
    public final void zzpj() {
        synchronized (this.mLock) {
            final zzanz<V> zza = zzano.zza(this.zzcmr.zza(this.mContext, this.zzcmo.keySet()), (zzanj<? super Map<String, String>, ? extends V>)new zzaij(this), zzaoe.zzcvz);
            final zzanz<V> zza2 = zzano.zza((zzanz<V>)zza, 10L, TimeUnit.SECONDS, zzaii.zzcmm);
            zzano.zza(zza, (zzanl<V>)new zzaim(this, zza2), zzaoe.zzcvz);
            zzaii.zzcml.add((zzanz<Void>)zza2);
        }
    }
    
    @Override
    public final void zzr(final View view) {
        if (!this.zzciy.zzcnf || this.zzcmw) {
            return;
        }
        zzbv.zzek();
        final Bitmap zzt = zzakk.zzt(view);
        if (zzt == null) {
            zzais.zzck("Failed to capture the webview bitmap.");
            return;
        }
        this.zzcmw = true;
        zzakk.zzd(new zzail(this, zzt));
    }
}
