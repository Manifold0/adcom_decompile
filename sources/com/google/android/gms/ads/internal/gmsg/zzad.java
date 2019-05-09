package com.google.android.gms.ads.internal.gmsg;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzx;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaab;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajb;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarr;
import com.google.android.gms.internal.ads.zzars;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzarz;
import com.google.android.gms.internal.ads.zzasb;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzcj;
import com.google.android.gms.internal.ads.zzjd;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.ui.VungleWebViewActivity;
import java.util.Map;

@zzadh
public final class zzad<T extends zzarr & zzars & zzarw & zzarz & zzasb> implements zzv<T> {
    private final Context mContext;
    private final zzjd zzapt;
    private final zzb zzbll;
    private final zzd zzblm;
    private final zzx zzbmw;
    private final zzaab zzbmx;
    private final zzci zzbna;
    private final zzt zzbnb;
    private final zzn zzbnc;
    private final zzaqw zzbnd = null;
    private final zzang zzzw;

    public zzad(Context context, zzang zzang, zzci zzci, zzt zzt, zzjd zzjd, zzb zzb, zzd zzd, zzn zzn, zzx zzx, zzaab zzaab) {
        this.mContext = context;
        this.zzzw = zzang;
        this.zzbna = zzci;
        this.zzbnb = zzt;
        this.zzapt = zzjd;
        this.zzbll = zzb;
        this.zzblm = zzd;
        this.zzbmw = zzx;
        this.zzbmx = zzaab;
        this.zzbnc = zzn;
    }

    @VisibleForTesting
    static String zza(Context context, zzci zzci, String str, View view, @Nullable Activity activity) {
        if (zzci != null) {
            try {
                Uri parse = Uri.parse(str);
                if (zzci.zzc(parse)) {
                    parse = zzci.zza(parse, context, view, activity);
                }
                str = parse.toString();
            } catch (zzcj e) {
            } catch (Throwable e2) {
                zzbv.zzeo().zza(e2, "OpenGmsgHandler.maybeAddClickSignalsToUrl");
            }
        }
        return str;
    }

    private static boolean zzg(Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }

    private static int zzh(Map<String, String> map) {
        String str = (String) map.get("o");
        if (str != null) {
            if ("p".equalsIgnoreCase(str)) {
                return zzbv.zzem().zzrm();
            }
            if ("l".equalsIgnoreCase(str)) {
                return zzbv.zzem().zzrl();
            }
            if ("c".equalsIgnoreCase(str)) {
                return zzbv.zzem().zzrn();
            }
        }
        return -1;
    }

    private final void zzl(boolean z) {
        if (this.zzbmx != null) {
            this.zzbmx.zzm(z);
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        Object zza;
        zzarr zzarr = (zzarr) obj;
        String zzb = zzajb.zzb((String) map.get("u"), zzarr.getContext());
        String str = (String) map.get("a");
        if (str == null) {
            zzakb.zzdk("Action missing from an open GMSG.");
        } else if (this.zzbmw != null && !this.zzbmw.zzcy()) {
            this.zzbmw.zzs(zzb);
        } else if ("expand".equalsIgnoreCase(str)) {
            if (((zzars) zzarr).zzuj()) {
                zzakb.zzdk("Cannot expand WebView that is already expanded.");
                return;
            }
            zzl(false);
            ((zzarw) zzarr).zza(zzg(map), zzh(map));
        } else if ("webapp".equalsIgnoreCase(str)) {
            zzl(false);
            if (zzb != null) {
                ((zzarw) zzarr).zza(zzg(map), zzh(map), zzb);
            } else {
                ((zzarw) zzarr).zza(zzg(map), zzh(map), (String) map.get(String.HTML), (String) map.get("baseurl"));
            }
        } else if (TapjoyConstants.TJC_APP_PLACEMENT.equalsIgnoreCase(str) && "true".equalsIgnoreCase((String) map.get("system_browser"))) {
            zzl(true);
            zzarr.getContext();
            if (TextUtils.isEmpty(zzb)) {
                zzakb.zzdk("Destination url cannot be empty.");
                return;
            }
            try {
                ((zzarw) zzarr).zza(new zzc(new zzae(zzarr.getContext(), ((zzarz) zzarr).zzui(), ((zzasb) zzarr).getView()).zzi(map)));
            } catch (ActivityNotFoundException e) {
                zzakb.zzdk(e.getMessage());
            }
        } else {
            Intent parseUri;
            Uri data;
            Object uri;
            Uri parse;
            zzl(true);
            str = (String) map.get(VungleWebViewActivity.INTENT_URL);
            if (!TextUtils.isEmpty(str)) {
                try {
                    parseUri = Intent.parseUri(str, 0);
                } catch (Throwable e2) {
                    String str2 = "Error parsing the url: ";
                    str = String.valueOf(str);
                    zzakb.zzb(str.length() != 0 ? str2.concat(str) : new String(str2), e2);
                }
                if (!(parseUri == null || parseUri.getData() == null)) {
                    data = parseUri.getData();
                    uri = data.toString();
                    if (!TextUtils.isEmpty(uri)) {
                        try {
                            zza = zza(zzarr.getContext(), ((zzarz) zzarr).zzui(), uri, ((zzasb) zzarr).getView(), zzarr.zzto());
                        } catch (Throwable e3) {
                            zzakb.zzb("Error occurred while adding signals.", e3);
                            zzbv.zzeo().zza(e3, "OpenGmsgHandler.onGmsg");
                            zza = uri;
                        }
                        try {
                            parse = Uri.parse(zza);
                        } catch (Throwable e22) {
                            String str3 = "Error parsing the uri: ";
                            str = String.valueOf(zza);
                            zzakb.zzb(str.length() != 0 ? str3.concat(str) : new String(str3), e22);
                            zzbv.zzeo().zza(e22, "OpenGmsgHandler.onGmsg");
                        }
                        parseUri.setData(parse);
                    }
                    parse = data;
                    parseUri.setData(parse);
                }
                if (parseUri != null) {
                    ((zzarw) zzarr).zza(new zzc(parseUri));
                    return;
                }
                if (!TextUtils.isEmpty(zzb)) {
                    zzb = zza(zzarr.getContext(), ((zzarz) zzarr).zzui(), zzb, ((zzasb) zzarr).getView(), zzarr.zzto());
                }
                ((zzarw) zzarr).zza(new zzc((String) map.get("i"), zzb, (String) map.get("m"), (String) map.get("p"), (String) map.get("c"), (String) map.get("f"), (String) map.get("e")));
            }
            parseUri = null;
            data = parseUri.getData();
            uri = data.toString();
            if (TextUtils.isEmpty(uri)) {
                zza = zza(zzarr.getContext(), ((zzarz) zzarr).zzui(), uri, ((zzasb) zzarr).getView(), zzarr.zzto());
                parse = Uri.parse(zza);
                parseUri.setData(parse);
                if (parseUri != null) {
                    if (TextUtils.isEmpty(zzb)) {
                        zzb = zza(zzarr.getContext(), ((zzarz) zzarr).zzui(), zzb, ((zzasb) zzarr).getView(), zzarr.zzto());
                    }
                    ((zzarw) zzarr).zza(new zzc((String) map.get("i"), zzb, (String) map.get("m"), (String) map.get("p"), (String) map.get("c"), (String) map.get("f"), (String) map.get("e")));
                }
                ((zzarw) zzarr).zza(new zzc(parseUri));
                return;
            }
            parse = data;
            parseUri.setData(parse);
            if (parseUri != null) {
                ((zzarw) zzarr).zza(new zzc(parseUri));
                return;
            }
            if (TextUtils.isEmpty(zzb)) {
                zzb = zza(zzarr.getContext(), ((zzarz) zzarr).zzui(), zzb, ((zzasb) zzarr).getView(), zzarr.zzto());
            }
            ((zzarw) zzarr).zza(new zzc((String) map.get("i"), zzb, (String) map.get("m"), (String) map.get("p"), (String) map.get("c"), (String) map.get("f"), (String) map.get("e")));
        }
    }
}
