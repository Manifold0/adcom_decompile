// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import org.json.JSONArray;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Collections;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

@zzadh
public final class zzwy
{
    public final long zzbsl;
    public final List<zzwx> zzbsm;
    public final List<String> zzbsn;
    public final List<String> zzbso;
    public final List<String> zzbsp;
    public final List<String> zzbsq;
    public final List<String> zzbsr;
    public final boolean zzbss;
    public final String zzbst;
    public final long zzbsu;
    public final String zzbsv;
    public final int zzbsw;
    public final int zzbsx;
    public final long zzbsy;
    public final boolean zzbsz;
    public final boolean zzbta;
    public final boolean zzbtb;
    public int zzbtc;
    public int zzbtd;
    public boolean zzbte;
    
    public zzwy(final String s) throws JSONException {
        this(new JSONObject(s));
    }
    
    public zzwy(final List<zzwx> zzbsm, final long zzbsl, final List<String> zzbsn, final List<String> zzbso, final List<String> zzbsp, final List<String> zzbsq, final List<String> zzbsr, final boolean zzbss, final String zzbst, final long n, final int n2, final int n3, final String s, final int n4, final int n5, final long n6, final boolean b) {
        this.zzbsm = zzbsm;
        this.zzbsl = zzbsl;
        this.zzbsn = zzbsn;
        this.zzbso = zzbso;
        this.zzbsp = zzbsp;
        this.zzbsq = zzbsq;
        this.zzbsr = zzbsr;
        this.zzbss = zzbss;
        this.zzbst = zzbst;
        this.zzbsu = -1L;
        this.zzbtc = 0;
        this.zzbtd = 1;
        this.zzbsv = null;
        this.zzbsw = 0;
        this.zzbsx = -1;
        this.zzbsy = -1L;
        this.zzbsz = false;
        this.zzbta = false;
        this.zzbtb = false;
        this.zzbte = false;
    }
    
    public zzwy(JSONObject optJSONObject) throws JSONException {
        if (zzakb.isLoggable(2)) {
            final String value = String.valueOf(optJSONObject.toString(2));
            String concat;
            if (value.length() != 0) {
                concat = "Mediation Response JSON: ".concat(value);
            }
            else {
                concat = new String("Mediation Response JSON: ");
            }
            zzakb.v(concat);
        }
        final JSONArray jsonArray = optJSONObject.getJSONArray("ad_networks");
        final ArrayList list = new ArrayList<zzwx>(jsonArray.length());
        int i = 0;
        int zzbtc = -1;
        while (i < jsonArray.length()) {
            final zzwx zzwx = new zzwx(jsonArray.getJSONObject(i));
            if (zzwx.zzmf()) {
                this.zzbte = true;
            }
            list.add(zzwx);
            int n = 0;
            Label_0177: {
                if ((n = zzbtc) < 0) {
                    final Iterator<String> iterator = zzwx.zzbrt.iterator();
                    while (true) {
                        while (iterator.hasNext()) {
                            if (iterator.next().equals("com.google.ads.mediation.admob.AdMobAdapter")) {
                                final int n2 = 1;
                                n = zzbtc;
                                if (n2 != 0) {
                                    n = i;
                                }
                                break Label_0177;
                            }
                        }
                        final int n2 = 0;
                        continue;
                    }
                }
            }
            ++i;
            zzbtc = n;
        }
        this.zzbtc = zzbtc;
        this.zzbtd = jsonArray.length();
        this.zzbsm = Collections.unmodifiableList((List<? extends zzwx>)list);
        this.zzbst = optJSONObject.optString("qdata");
        this.zzbsx = optJSONObject.optInt("fs_model_type", -1);
        this.zzbsy = optJSONObject.optLong("timeout_ms", -1L);
        optJSONObject = optJSONObject.optJSONObject("settings");
        if (optJSONObject != null) {
            this.zzbsl = optJSONObject.optLong("ad_network_timeout_millis", -1L);
            zzbv.zzfd();
            this.zzbsn = zzxg.zza(optJSONObject, "click_urls");
            zzbv.zzfd();
            this.zzbso = zzxg.zza(optJSONObject, "imp_urls");
            zzbv.zzfd();
            this.zzbsp = zzxg.zza(optJSONObject, "downloaded_imp_urls");
            zzbv.zzfd();
            this.zzbsq = zzxg.zza(optJSONObject, "nofill_urls");
            zzbv.zzfd();
            this.zzbsr = zzxg.zza(optJSONObject, "remote_ping_urls");
            this.zzbss = optJSONObject.optBoolean("render_in_browser", false);
            final long optLong = optJSONObject.optLong("refresh", -1L);
            long zzbsu;
            if (optLong > 0L) {
                zzbsu = optLong * 1000L;
            }
            else {
                zzbsu = -1L;
            }
            this.zzbsu = zzbsu;
            final zzaig zza = zzaig.zza(optJSONObject.optJSONArray("rewards"));
            if (zza == null) {
                this.zzbsv = null;
                this.zzbsw = 0;
            }
            else {
                this.zzbsv = zza.type;
                this.zzbsw = zza.zzcmk;
            }
            this.zzbsz = optJSONObject.optBoolean("use_displayed_impression", false);
            this.zzbta = optJSONObject.optBoolean("allow_pub_rendered_attribution", false);
            this.zzbtb = optJSONObject.optBoolean("allow_pub_owned_ad_view", false);
            return;
        }
        this.zzbsl = -1L;
        this.zzbsn = null;
        this.zzbso = null;
        this.zzbsp = null;
        this.zzbsq = null;
        this.zzbsr = null;
        this.zzbsu = -1L;
        this.zzbsv = null;
        this.zzbsw = 0;
        this.zzbsz = false;
        this.zzbss = false;
        this.zzbta = false;
        this.zzbtb = false;
    }
}
