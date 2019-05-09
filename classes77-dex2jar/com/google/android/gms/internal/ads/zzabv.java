// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.Future;
import org.json.JSONArray;
import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Iterator;
import java.util.ArrayList;
import android.os.RemoteException;
import android.graphics.Color;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import org.json.JSONObject;
import java.util.List;
import com.google.android.gms.ads.internal.zzbc;
import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.Callable;

@zzadh
public final class zzabv implements Callable<zzajh>
{
    @VisibleForTesting
    private static long zzbzx;
    private final Context mContext;
    private int mErrorCode;
    private final Object mLock;
    private final zzacm zzaad;
    private final zzci zzbjc;
    private final zzaji zzbze;
    private final zzalt zzbzy;
    private final zzbc zzbzz;
    private boolean zzcaa;
    private List<String> zzcab;
    private JSONObject zzcac;
    private String zzcad;
    @Nullable
    private String zzcae;
    private final zznx zzvr;
    
    static {
        zzabv.zzbzx = 10L;
    }
    
    public zzabv(final Context mContext, final zzbc zzbzz, final zzalt zzbzy, final zzci zzbjc, final zzaji zzbze, final zznx zzvr) {
        this.mLock = new Object();
        this.mContext = mContext;
        this.zzbzz = zzbzz;
        this.zzbzy = zzbzy;
        this.zzbze = zzbze;
        this.zzbjc = zzbjc;
        this.zzvr = zzvr;
        this.zzaad = zzbzz.zzdr();
        this.zzcaa = false;
        this.mErrorCode = -2;
        this.zzcab = null;
        this.zzcad = null;
        this.zzcae = null;
    }
    
    private final zzajh zza(final zzpb zzpb, final boolean b) {
        while (true) {
            while (true) {
                Label_0267: {
                    synchronized (this.mLock) {
                        int mErrorCode;
                        final int n = mErrorCode = this.mErrorCode;
                        if (zzpb == null) {
                            mErrorCode = n;
                            if (this.mErrorCode == -2) {
                                mErrorCode = 0;
                            }
                        }
                        // monitorexit(this.mLock)
                        if (mErrorCode != -2) {
                            final zzpb zzpb2 = null;
                            return new zzajh(this.zzbze.zzcgs.zzccv, null, this.zzbze.zzcos.zzbsn, mErrorCode, this.zzbze.zzcos.zzbso, this.zzcab, this.zzbze.zzcos.orientation, this.zzbze.zzcos.zzbsu, this.zzbze.zzcgs.zzccy, false, null, null, null, null, null, 0L, this.zzbze.zzacv, this.zzbze.zzcos.zzcep, this.zzbze.zzcoh, this.zzbze.zzcoi, this.zzbze.zzcos.zzcev, this.zzcac, zzpb2, null, null, null, this.zzbze.zzcos.zzcfh, this.zzbze.zzcos.zzcfi, null, this.zzbze.zzcos.zzbsr, this.zzcad, this.zzbze.zzcoq, this.zzbze.zzcos.zzzl, this.zzbze.zzcor, b, this.zzbze.zzcos.zzbsp, this.zzbze.zzcos.zzzm, this.zzcae);
                        }
                        break Label_0267;
                    }
                }
                continue;
            }
        }
    }
    
    private final zzanz<zzon> zza(final JSONObject jsonObject, final boolean b, final boolean b2) throws JSONException {
        String s;
        if (b) {
            s = jsonObject.getString("url");
        }
        else {
            s = jsonObject.optString("url");
        }
        final double optDouble = jsonObject.optDouble("scale", 1.0);
        final boolean optBoolean = jsonObject.optBoolean("is_transparent", true);
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.zzd(0, b);
            return (zzanz<zzon>)zzano.zzi((Object)null);
        }
        if (b2) {
            return zzano.zzi(new zzon((Drawable)null, Uri.parse(s), optDouble));
        }
        return this.zzbzy.zza(s, (zzalz<zzon>)new zzacb(this, b, optDouble, optBoolean, s));
    }
    
    private final void zzab(final int mErrorCode) {
        synchronized (this.mLock) {
            this.zzcaa = true;
            this.mErrorCode = mErrorCode;
        }
    }
    
    private static zzaqw zzb(zzanz<zzaqw> zzaqw) {
        try {
            zzaqw = ((Future<zzaqw>)zzaqw).get((long)zzkb.zzik().zzd(zznk.zzbby), TimeUnit.MILLISECONDS);
            return zzaqw;
        }
        catch (InterruptedException ex) {
            zzane.zzc("", (Throwable)ex);
            Thread.currentThread().interrupt();
        }
        catch (ExecutionException ex2) {}
        catch (CancellationException zzaqw) {
            goto Label_0050;
        }
        catch (TimeoutException zzaqw) {
            goto Label_0050;
        }
    }
    
    private static Integer zzb(JSONObject jsonObject, final String s) {
        try {
            jsonObject = jsonObject.getJSONObject(s);
            return Color.rgb(jsonObject.getInt("r"), jsonObject.getInt("g"), jsonObject.getInt("b"));
        }
        catch (JSONException ex) {
            return null;
        }
    }
    
    static zzaqw zzc(zzanz<zzaqw> zzaqw) {
        try {
            zzaqw = ((Future<zzaqw>)zzaqw).get((long)zzkb.zzik().zzd(zznk.zzbbx), TimeUnit.SECONDS);
            return zzaqw;
        }
        catch (InterruptedException ex) {
            zzakb.zzc("InterruptedException occurred while waiting for video to load", (Throwable)ex);
            Thread.currentThread().interrupt();
        }
        catch (ExecutionException ex2) {}
        catch (CancellationException zzaqw) {
            goto Label_0050;
        }
        catch (TimeoutException zzaqw) {
            goto Label_0050;
        }
    }
    
    private final void zzc(final zzqs zzqs, final String s) {
        try {
            final zzrc zzr = this.zzbzz.zzr(zzqs.getCustomTemplateId());
            if (zzr != null) {
                zzr.zzb(zzqs, s);
            }
        }
        catch (RemoteException ex) {
            zzakb.zzc(new StringBuilder(String.valueOf(s).length() + 40).append("Failed to call onCustomClick for asset ").append(s).append(".").toString(), (Throwable)ex);
        }
    }
    
    private static <V> List<V> zzk(final List<zzanz<V>> list) throws ExecutionException, InterruptedException {
        final ArrayList<Object> list2 = (ArrayList<Object>)new ArrayList<V>();
        final Iterator<zzanz<V>> iterator = list.iterator();
        while (iterator.hasNext()) {
            final Object value = iterator.next().get();
            if (value != null) {
                list2.add(value);
            }
        }
        return (List<V>)list2;
    }
    
    private final zzajh zznw() {
        try {
            this.zzbzz.getUuid();
            if (this.zznx()) {
                goto Label_0898;
            }
            final JSONObject jsonObject = new JSONObject(this.zzbze.zzcos.zzceo);
            Object o = new JSONObject(this.zzbze.zzcos.zzceo);
            Label_0103: {
                if (((JSONObject)o).length() != 0) {
                    o = ((JSONObject)o).optJSONArray("ads");
                    if (o == null) {
                        goto Label_0892;
                    }
                    o = ((JSONArray)o).optJSONObject(0);
                    if (o != null && ((JSONObject)o).length() != 0) {
                        break Label_0103;
                    }
                }
                this.zzab(3);
            }
            o = this.zzaad.zzh(jsonObject).get(zzabv.zzbzx, TimeUnit.SECONDS);
            if (!((JSONObject)o).optBoolean("success", false)) {
                goto Label_0898;
            }
            final JSONObject jsonObject2 = ((JSONObject)o).getJSONObject("json").optJSONArray("ads").getJSONObject(0);
            final boolean optBoolean = jsonObject2.optBoolean("enable_omid");
            if (!optBoolean) {
                final Object o2 = zzano.zzi((Object)null);
            }
            else {
                o = jsonObject2.optJSONObject("omid_settings");
                if (o == null) {
                    final Object o2 = zzano.zzi((Object)null);
                }
                else {
                    o = ((JSONObject)o).optString("omid_html");
                    if (TextUtils.isEmpty((CharSequence)o)) {
                        final Object o2 = zzano.zzi((Object)null);
                    }
                    else {
                        final Object o2 = new zzaoj<zzaqw>();
                        zzaoe.zzcvy.execute(new zzabw(this, (zzaoj)o2, (String)o));
                    }
                }
            }
            if (this.zznx() || jsonObject2 == null) {
                goto Label_0880;
            }
            o = jsonObject2.getString("template_id");
            if (this.zzbze.zzcgs.zzadj == null) {
                goto Label_0904;
            }
            final boolean zzbjn = this.zzbze.zzcgs.zzadj.zzbjn;
            if (this.zzbze.zzcgs.zzadj == null) {
                goto Label_0909;
            }
            final boolean zzbjp = this.zzbze.zzcgs.zzadj.zzbjp;
            if ("2".equals(o)) {
                o = new zzacn(zzbjn, zzbjp, this.zzbze.zzcor);
            }
            else if ("1".equals(o)) {
                o = new zzaco(zzbjn, zzbjp, this.zzbze.zzcor);
            }
            else {
                if (!"3".equals(o)) {
                    goto Label_0650;
                }
                o = jsonObject2.getString("custom_template_id");
                final zzaoj zzaoj = new zzaoj();
                zzakk.zzcrm.post((Runnable)new zzaby(this, zzaoj, (String)o));
                if (zzaoj.get(zzabv.zzbzx, TimeUnit.SECONDS) == null) {
                    o = String.valueOf(jsonObject2.getString("custom_template_id"));
                    if (((String)o).length() != 0) {
                        o = "No handler for custom template: ".concat((String)o);
                    }
                    else {
                        o = new String("No handler for custom template: ");
                    }
                    zzakb.e((String)o);
                    goto Label_0914;
                }
                o = new zzacp(zzbjn);
            }
            if (!this.zznx() && o != null && jsonObject2 == null) {
                goto Label_0886;
            }
            goto Label_0886;
            this.zzaad.zza("/nativeAdCustomClick", new zzabz(this, (zzos)o));
            o = this.zza((zzpb)o, optBoolean);
            Object o2 = null;
            this.zzbzz.zzg(zzb((zzanz<zzaqw>)o2));
            return (zzajh)o;
        }
        catch (CancellationException ex3) {}
        catch (TimeoutException ex) {
            zzakb.zzc("Timeout when loading native ad.", (Throwable)ex);
            goto Label_0631;
        }
        catch (Exception ex2) {
            zzakb.zzc("Error occured while doing native ads initialization.", (Throwable)ex2);
            goto Label_0631;
        }
        catch (InterruptedException o) {
            goto Label_0623;
        }
        catch (JSONException o) {
            goto Label_0623;
        }
        catch (ExecutionException o) {
            goto Label_0623;
        }
        final Object o3;
        if (o3 == null) {
            goto Label_0689;
        }
        goto Label_0834;
    }
    
    private final boolean zznx() {
        synchronized (this.mLock) {
            return this.zzcaa;
        }
    }
    
    public final zzanz<zzon> zza(JSONObject jsonObject, final String s, final boolean b, final boolean b2) throws JSONException {
        if (b) {
            jsonObject = jsonObject.getJSONObject(s);
        }
        else {
            jsonObject = jsonObject.optJSONObject(s);
        }
        JSONObject jsonObject2 = jsonObject;
        if (jsonObject == null) {
            jsonObject2 = new JSONObject();
        }
        return this.zza(jsonObject2, b, b2);
    }
    
    public final List<zzanz<zzon>> zza(JSONObject jsonObject, final String s, final boolean b, final boolean b2, final boolean b3) throws JSONException {
        final JSONArray optJSONArray = ((JSONObject)jsonObject).optJSONArray(s);
        final ArrayList<zzanz<zzon>> list = new ArrayList<zzanz<zzon>>();
        if (optJSONArray == null || optJSONArray.length() == 0) {
            this.zzd(0, false);
            return list;
        }
        int length;
        if (b3) {
            length = optJSONArray.length();
        }
        else {
            length = 1;
        }
        for (int i = 0; i < length; ++i) {
            if ((jsonObject = optJSONArray.getJSONObject(i)) == null) {
                jsonObject = new JSONObject();
            }
            list.add(this.zza((JSONObject)jsonObject, false, b2));
        }
        return list;
    }
    
    public final Future<zzon> zza(JSONObject jsonObject, final String s, final boolean b) throws JSONException {
        final JSONObject jsonObject2 = jsonObject.getJSONObject(s);
        final boolean optBoolean = jsonObject2.optBoolean("require", true);
        jsonObject = jsonObject2;
        if (jsonObject2 == null) {
            jsonObject = new JSONObject();
        }
        return this.zza(jsonObject, optBoolean, b);
    }
    
    public final zzanz<zzaqw> zzc(JSONObject optJSONObject, final String s) throws JSONException {
        optJSONObject = optJSONObject.optJSONObject(s);
        if (optJSONObject == null) {
            return (zzanz<zzaqw>)zzano.zzi((Object)null);
        }
        if (TextUtils.isEmpty((CharSequence)optJSONObject.optString("vast_xml"))) {
            zzakb.zzdk("Required field 'vast_xml' is missing");
            return (zzanz<zzaqw>)zzano.zzi((Object)null);
        }
        final zzace zzace = new zzace(this.mContext, this.zzbjc, this.zzbze, this.zzvr, this.zzbzz);
        final zzaoj<zzaqw> zzaoj = new zzaoj<zzaqw>();
        zzaoe.zzcvy.execute(new zzacf(zzace, optJSONObject, zzaoj));
        return zzaoj;
    }
    
    public final void zzd(final int n, final boolean b) {
        if (b) {
            this.zzab(n);
        }
    }
    
    public final zzanz<zzoj> zzg(final JSONObject jsonObject) throws JSONException {
        final JSONObject optJSONObject = jsonObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return (zzanz<zzoj>)zzano.zzi((Object)null);
        }
        final String optString = optJSONObject.optString("text");
        final int optInt = optJSONObject.optInt("text_size", -1);
        final Integer zzb = zzb(optJSONObject, "text_color");
        final Integer zzb2 = zzb(optJSONObject, "bg_color");
        final int optInt2 = optJSONObject.optInt("animation_ms", 1000);
        final int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
        int zzbjq;
        if (this.zzbze.zzcgs.zzadj != null && this.zzbze.zzcgs.zzadj.versionCode >= 2) {
            zzbjq = this.zzbze.zzcgs.zzadj.zzbjq;
        }
        else {
            zzbjq = 1;
        }
        final boolean optBoolean = optJSONObject.optBoolean("allow_pub_rendering");
        List<zzanz<zzon>> zza = new ArrayList<zzanz<zzon>>();
        if (optJSONObject.optJSONArray("images") != null) {
            zza = this.zza(optJSONObject, "images", false, false, true);
        }
        else {
            zza.add(this.zza(optJSONObject, "image", false, false));
        }
        final zzaoj<Object> zzaoj = new zzaoj<Object>();
        final int size = zza.size();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final Iterator<zzanz<zzon>> iterator = zza.iterator();
        while (iterator.hasNext()) {
            iterator.next().zza(new zzacc(atomicInteger, size, zzaoj, zza), zzaki.zzcrj);
        }
        return zzano.zza(zzaoj, (zzank<Object, zzoj>)new zzaca(this, optString, zzb2, zzb, optInt, optInt3, optInt2, zzbjq, optBoolean), zzaki.zzcrj);
    }
}
