// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONException;
import com.google.android.gms.ads.internal.zzbv;
import org.json.JSONArray;
import android.content.Context;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import android.annotation.TargetApi;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.common.util.PlatformVersion;
import android.os.Bundle;
import java.util.Collections;
import org.json.JSONObject;
import java.util.Set;
import android.content.SharedPreferences$Editor;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.Nullable;
import android.content.SharedPreferences;

@zzadh
public final class zzakd
{
    private final Object mLock;
    @Nullable
    @GuardedBy("mLock")
    private SharedPreferences zzatw;
    @GuardedBy("mLock")
    private boolean zzcik;
    @GuardedBy("mLock")
    private boolean zzcil;
    @GuardedBy("mLock")
    private boolean zzcim;
    @GuardedBy("mLock")
    private boolean zzcit;
    @GuardedBy("mLock")
    private String zzcpj;
    @GuardedBy("mLock")
    private int zzcqg;
    private zzanz<?> zzcqu;
    private CopyOnWriteArraySet<zzakh> zzcqv;
    @Nullable
    @GuardedBy("mLock")
    SharedPreferences$Editor zzcqw;
    @GuardedBy("mLock")
    private boolean zzcqx;
    @Nullable
    @GuardedBy("mLock")
    private String zzcqy;
    @Nullable
    @GuardedBy("mLock")
    private String zzcqz;
    @GuardedBy("mLock")
    private long zzcra;
    @GuardedBy("mLock")
    private long zzcrb;
    @GuardedBy("mLock")
    private long zzcrc;
    @GuardedBy("mLock")
    private int zzcrd;
    @GuardedBy("mLock")
    private Set<String> zzcre;
    @GuardedBy("mLock")
    private JSONObject zzcrf;
    
    public zzakd() {
        this.mLock = new Object();
        this.zzcqv = new CopyOnWriteArraySet<zzakh>();
        this.zzcqx = false;
        this.zzcik = true;
        this.zzcit = false;
        this.zzcpj = "";
        this.zzcra = 0L;
        this.zzcrb = 0L;
        this.zzcrc = 0L;
        this.zzcqg = -1;
        this.zzcrd = 0;
        this.zzcre = Collections.emptySet();
        this.zzcrf = new JSONObject();
        this.zzcil = true;
        this.zzcim = true;
    }
    
    private final void zze(final Bundle bundle) {
        new zzakf(this, bundle).zznt();
    }
    
    @TargetApi(23)
    private static boolean zzqq() {
        return PlatformVersion.isAtLeastM() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
    }
    
    private final void zzqr() {
        if (this.zzcqu == null || this.zzcqu.isDone()) {
            return;
        }
        try {
            this.zzcqu.get(1L, TimeUnit.SECONDS);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            zzakb.zzc("Interrupted while waiting for preferences loaded.", (Throwable)ex);
        }
        catch (ExecutionException ex2) {}
        catch (CancellationException ex3) {
            goto Label_0050;
        }
        catch (TimeoutException ex3) {
            goto Label_0050;
        }
    }
    
    private final Bundle zzqs() {
        final Bundle bundle = new Bundle();
        bundle.putBoolean("listener_registration_bundle", true);
        synchronized (this.mLock) {
            bundle.putBoolean("use_https", this.zzcik);
            bundle.putBoolean("content_url_opted_out", this.zzcil);
            bundle.putBoolean("content_vertical_opted_out", this.zzcim);
            bundle.putBoolean("auto_collect_location", this.zzcit);
            bundle.putInt("version_code", this.zzcrd);
            bundle.putStringArray("never_pool_slots", (String[])this.zzcre.toArray(new String[this.zzcre.size()]));
            bundle.putString("app_settings_json", this.zzcpj);
            bundle.putLong("app_settings_last_update_ms", this.zzcra);
            bundle.putLong("app_last_background_time_ms", this.zzcrb);
            bundle.putInt("request_in_session_count", this.zzcqg);
            bundle.putLong("first_ad_req_time_ms", this.zzcrc);
            bundle.putString("native_advanced_settings", this.zzcrf.toString());
            if (this.zzcqy != null) {
                bundle.putString("content_url_hashes", this.zzcqy);
            }
            if (this.zzcqz != null) {
                bundle.putString("content_vertical_hashes", this.zzcqz);
            }
            return bundle;
        }
    }
    
    public final void initialize(Context applicationContext) {
        if (applicationContext.getApplicationContext() != null) {
            applicationContext = applicationContext.getApplicationContext();
        }
        this.zzcqu = (zzanz<?>)new zzake(this, applicationContext).zznt();
    }
    
    public final void zza(final zzakh zzakh) {
        synchronized (this.mLock) {
            if (this.zzcqu != null && this.zzcqu.isDone()) {
                zzakh.zzd(this.zzqs());
            }
            this.zzcqv.add(zzakh);
        }
    }
    
    public final void zza(final String s, final String s2, final boolean b) {
        while (true) {
            int n = 0;
            this.zzqr();
            while (true) {
                Label_0135: {
                    JSONArray optJSONArray;
                    int length;
                    while (true) {
                        synchronized (this.mLock) {
                            optJSONArray = this.zzcrf.optJSONArray(s);
                            if (optJSONArray != null) {
                                break Label_0135;
                            }
                            optJSONArray = new JSONArray();
                            length = optJSONArray.length();
                            if (n >= optJSONArray.length()) {
                                break Label_0135;
                            }
                            final JSONObject optJSONObject = optJSONArray.optJSONObject(n);
                            if (optJSONObject == null) {
                                return;
                            }
                            if (s2.equals(optJSONObject.optString("template_id"))) {
                                length = n;
                                if (!b) {
                                    break Label_0135;
                                }
                                length = n;
                                if (optJSONObject.optBoolean("uses_media_view", false) == b) {
                                    return;
                                }
                                break Label_0135;
                            }
                        }
                        ++n;
                        continue;
                    }
                    while (true) {
                        try {
                            final JSONObject jsonObject = new JSONObject();
                            jsonObject.put("template_id", (Object)s2);
                            jsonObject.put("uses_media_view", b);
                            jsonObject.put("timestamp_ms", zzbv.zzer().currentTimeMillis());
                            optJSONArray.put(length, (Object)jsonObject);
                            final String s3;
                            this.zzcrf.put(s3, (Object)optJSONArray);
                            if (this.zzcqw != null) {
                                this.zzcqw.putString("native_advanced_settings", this.zzcrf.toString());
                                this.zzcqw.apply();
                            }
                            final Bundle bundle = new Bundle();
                            bundle.putString("native_advanced_settings", this.zzcrf.toString());
                            this.zze(bundle);
                            // monitorexit(o)
                            return;
                        }
                        catch (JSONException ex) {
                            zzakb.zzc("Could not update native advanced settings", (Throwable)ex);
                            continue;
                        }
                        break;
                    }
                }
                continue;
            }
        }
    }
    
    public final void zzab(final boolean zzcik) {
        this.zzqr();
        synchronized (this.mLock) {
            if (this.zzcik == zzcik) {
                return;
            }
            this.zzcik = zzcik;
            if (this.zzcqw != null) {
                this.zzcqw.putBoolean("use_https", zzcik);
                this.zzcqw.apply();
            }
            if (!this.zzcqx) {
                final Bundle bundle = new Bundle();
                bundle.putBoolean("use_https", zzcik);
                this.zze(bundle);
            }
        }
    }
    
    public final void zzac(final boolean zzcil) {
        this.zzqr();
        synchronized (this.mLock) {
            if (this.zzcil == zzcil) {
                return;
            }
            this.zzcil = zzcil;
            if (this.zzcqw != null) {
                this.zzcqw.putBoolean("content_url_opted_out", zzcil);
                this.zzcqw.apply();
            }
            final Bundle bundle = new Bundle();
            bundle.putBoolean("content_url_opted_out", this.zzcil);
            bundle.putBoolean("content_vertical_opted_out", this.zzcim);
            this.zze(bundle);
        }
    }
    
    public final void zzad(final boolean zzcim) {
        this.zzqr();
        synchronized (this.mLock) {
            if (this.zzcim == zzcim) {
                return;
            }
            this.zzcim = zzcim;
            if (this.zzcqw != null) {
                this.zzcqw.putBoolean("content_vertical_opted_out", zzcim);
                this.zzcqw.apply();
            }
            final Bundle bundle = new Bundle();
            bundle.putBoolean("content_url_opted_out", this.zzcil);
            bundle.putBoolean("content_vertical_opted_out", this.zzcim);
            this.zze(bundle);
        }
    }
    
    public final void zzae(final int zzcrd) {
        this.zzqr();
        synchronized (this.mLock) {
            if (this.zzcrd == zzcrd) {
                return;
            }
            this.zzcrd = zzcrd;
            if (this.zzcqw != null) {
                this.zzcqw.putInt("version_code", zzcrd);
                this.zzcqw.apply();
            }
            final Bundle bundle = new Bundle();
            bundle.putInt("version_code", zzcrd);
            this.zze(bundle);
        }
    }
    
    public final void zzae(final boolean zzcit) {
        this.zzqr();
        synchronized (this.mLock) {
            if (this.zzcit == zzcit) {
                return;
            }
            this.zzcit = zzcit;
            if (this.zzcqw != null) {
                this.zzcqw.putBoolean("auto_collect_location", zzcit);
                this.zzcqw.apply();
            }
            final Bundle bundle = new Bundle();
            bundle.putBoolean("auto_collect_location", zzcit);
            this.zze(bundle);
        }
    }
    
    public final void zzaf(final int zzcqg) {
        this.zzqr();
        synchronized (this.mLock) {
            if (this.zzcqg == zzcqg) {
                return;
            }
            this.zzcqg = zzcqg;
            if (this.zzcqw != null) {
                this.zzcqw.putInt("request_in_session_count", zzcqg);
                this.zzcqw.apply();
            }
            final Bundle bundle = new Bundle();
            bundle.putInt("request_in_session_count", zzcqg);
            this.zze(bundle);
        }
    }
    
    public final void zzcn(@Nullable final String zzcqy) {
        this.zzqr();
        final Object mLock = this.mLock;
        // monitorenter(mLock)
        if (zzcqy == null) {
            return;
        }
        try {
            if (zzcqy.equals(this.zzcqy)) {
                return;
            }
            this.zzcqy = zzcqy;
            if (this.zzcqw != null) {
                this.zzcqw.putString("content_url_hashes", zzcqy);
                this.zzcqw.apply();
            }
            final Bundle bundle = new Bundle();
            bundle.putString("content_url_hashes", zzcqy);
            this.zze(bundle);
        }
        finally {
        }
        // monitorexit(mLock)
    }
    
    public final void zzco(@Nullable final String zzcqz) {
        this.zzqr();
        final Object mLock = this.mLock;
        // monitorenter(mLock)
        if (zzcqz == null) {
            return;
        }
        try {
            if (zzcqz.equals(this.zzcqz)) {
                return;
            }
            this.zzcqz = zzcqz;
            if (this.zzcqw != null) {
                this.zzcqw.putString("content_vertical_hashes", zzcqz);
                this.zzcqw.apply();
            }
            final Bundle bundle = new Bundle();
            bundle.putString("content_vertical_hashes", zzcqz);
            this.zze(bundle);
        }
        finally {
        }
        // monitorexit(mLock)
    }
    
    public final void zzcp(final String s) {
        this.zzqr();
        synchronized (this.mLock) {
            if (this.zzcre.contains(s)) {
                return;
            }
            this.zzcre.add(s);
            if (this.zzcqw != null) {
                this.zzcqw.putStringSet("never_pool_slots", (Set)this.zzcre);
                this.zzcqw.apply();
            }
            final Bundle bundle = new Bundle();
            bundle.putStringArray("never_pool_slots", (String[])this.zzcre.toArray(new String[this.zzcre.size()]));
            this.zze(bundle);
        }
    }
    
    public final void zzcq(final String s) {
        this.zzqr();
        synchronized (this.mLock) {
            if (!this.zzcre.contains(s)) {
                return;
            }
            this.zzcre.remove(s);
            if (this.zzcqw != null) {
                this.zzcqw.putStringSet("never_pool_slots", (Set)this.zzcre);
                this.zzcqw.apply();
            }
            final Bundle bundle = new Bundle();
            bundle.putStringArray("never_pool_slots", (String[])this.zzcre.toArray(new String[this.zzcre.size()]));
            this.zze(bundle);
        }
    }
    
    public final boolean zzcr(final String s) {
        this.zzqr();
        synchronized (this.mLock) {
            return this.zzcre.contains(s);
        }
    }
    
    public final void zzcs(final String zzcpj) {
        this.zzqr();
        synchronized (this.mLock) {
            final long currentTimeMillis = zzbv.zzer().currentTimeMillis();
            this.zzcra = currentTimeMillis;
            if (zzcpj == null || zzcpj.equals(this.zzcpj)) {
                return;
            }
            this.zzcpj = zzcpj;
            if (this.zzcqw != null) {
                this.zzcqw.putString("app_settings_json", zzcpj);
                this.zzcqw.putLong("app_settings_last_update_ms", currentTimeMillis);
                this.zzcqw.apply();
            }
            final Bundle bundle = new Bundle();
            bundle.putString("app_settings_json", zzcpj);
            bundle.putLong("app_settings_last_update_ms", currentTimeMillis);
            this.zze(bundle);
        }
    }
    
    public final void zzj(final long zzcrb) {
        this.zzqr();
        synchronized (this.mLock) {
            if (this.zzcrb == zzcrb) {
                return;
            }
            this.zzcrb = zzcrb;
            if (this.zzcqw != null) {
                this.zzcqw.putLong("app_last_background_time_ms", zzcrb);
                this.zzcqw.apply();
            }
            final Bundle bundle = new Bundle();
            bundle.putLong("app_last_background_time_ms", zzcrb);
            this.zze(bundle);
        }
    }
    
    public final void zzk(final long zzcrc) {
        this.zzqr();
        synchronized (this.mLock) {
            if (this.zzcrc == zzcrc) {
                return;
            }
            this.zzcrc = zzcrc;
            if (this.zzcqw != null) {
                this.zzcqw.putLong("first_ad_req_time_ms", zzcrc);
                this.zzcqw.apply();
            }
            final Bundle bundle = new Bundle();
            bundle.putLong("first_ad_req_time_ms", zzcrc);
            this.zze(bundle);
        }
    }
    
    public final boolean zzqt() {
        while (true) {
            this.zzqr();
            synchronized (this.mLock) {
                if (this.zzcik) {
                    return true;
                }
                if (this.zzcqx) {
                    return true;
                }
                return false;
            }
            return true;
            b = false;
            return b;
        }
    }
    
    public final boolean zzqu() {
        this.zzqr();
        synchronized (this.mLock) {
            return this.zzcil;
        }
    }
    
    @Nullable
    public final String zzqv() {
        this.zzqr();
        synchronized (this.mLock) {
            return this.zzcqy;
        }
    }
    
    public final boolean zzqw() {
        this.zzqr();
        synchronized (this.mLock) {
            return this.zzcim;
        }
    }
    
    @Nullable
    public final String zzqx() {
        this.zzqr();
        synchronized (this.mLock) {
            return this.zzcqz;
        }
    }
    
    public final boolean zzqy() {
        this.zzqr();
        synchronized (this.mLock) {
            return this.zzcit;
        }
    }
    
    public final int zzqz() {
        this.zzqr();
        synchronized (this.mLock) {
            return this.zzcrd;
        }
    }
    
    public final zzajl zzra() {
        this.zzqr();
        synchronized (this.mLock) {
            return new zzajl(this.zzcpj, this.zzcra);
        }
    }
    
    public final long zzrb() {
        this.zzqr();
        synchronized (this.mLock) {
            return this.zzcrb;
        }
    }
    
    public final int zzrc() {
        this.zzqr();
        synchronized (this.mLock) {
            return this.zzcqg;
        }
    }
    
    public final long zzrd() {
        this.zzqr();
        synchronized (this.mLock) {
            return this.zzcrc;
        }
    }
    
    public final JSONObject zzre() {
        this.zzqr();
        synchronized (this.mLock) {
            return this.zzcrf;
        }
    }
    
    public final void zzrf() {
        this.zzqr();
        synchronized (this.mLock) {
            this.zzcrf = new JSONObject();
            if (this.zzcqw != null) {
                this.zzcqw.remove("native_advanced_settings");
                this.zzcqw.apply();
            }
            final Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", "{}");
            this.zze(bundle);
        }
    }
}
