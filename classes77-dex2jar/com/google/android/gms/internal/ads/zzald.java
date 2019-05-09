// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.content.Intent;
import android.content.DialogInterface;
import android.view.WindowManager$BadTokenException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import android.net.Uri;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.net.Uri$Builder;
import android.text.TextUtils;
import android.app.Activity;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;
import com.google.android.gms.ads.internal.zzbv;
import android.view.ViewConfiguration;
import android.support.annotation.Nullable;
import android.content.Context;
import android.os.Handler;

@zzadh
public final class zzald
{
    private Handler handler;
    private final Context mContext;
    private int state;
    private String zzaej;
    private final float zzbwx;
    @Nullable
    private String zzchz;
    private String zzcrx;
    private float zzcry;
    private float zzcrz;
    private float zzcsa;
    private int zzcsb;
    private float zzcsc;
    private float zzcsd;
    private float zzcse;
    private float zzcsf;
    private Runnable zzcsg;
    private String zzye;
    
    public zzald(final Context mContext) {
        this.state = 0;
        this.zzcsg = new zzale(this);
        this.mContext = mContext;
        this.zzbwx = mContext.getResources().getDisplayMetrics().density;
        this.zzcsb = ViewConfiguration.get(this.mContext).getScaledTouchSlop();
        zzbv.zzez().zzsa();
        this.handler = zzbv.zzez().getHandler();
    }
    
    public zzald(final Context context, final String zzcrx) {
        this(context);
        this.zzcrx = zzcrx;
    }
    
    private static int zza(final List<String> list, final String s, final boolean b) {
        if (!b) {
            return -1;
        }
        list.add(s);
        return list.size() - 1;
    }
    
    @VisibleForTesting
    private final void zza(final int n, final float zzcry, final float n2) {
        if (n == 0) {
            this.state = 0;
            this.zzcry = zzcry;
            this.zzcrz = n2;
            this.zzcsa = n2;
        }
        else if (this.state != -1) {
            if (n == 2) {
                if (n2 > this.zzcrz) {
                    this.zzcrz = n2;
                }
                else if (n2 < this.zzcsa) {
                    this.zzcsa = n2;
                }
                if (this.zzcrz - this.zzcsa > 30.0f * this.zzbwx) {
                    this.state = -1;
                    return;
                }
                if (this.state == 0 || this.state == 2) {
                    if (zzcry - this.zzcry >= 50.0f * this.zzbwx) {
                        this.zzcry = zzcry;
                        ++this.state;
                    }
                }
                else if ((this.state == 1 || this.state == 3) && zzcry - this.zzcry <= -50.0f * this.zzbwx) {
                    this.zzcry = zzcry;
                    ++this.state;
                }
                if (this.state == 1 || this.state == 3) {
                    if (zzcry > this.zzcry) {
                        this.zzcry = zzcry;
                    }
                }
                else if (this.state == 2 && zzcry < this.zzcry) {
                    this.zzcry = zzcry;
                }
            }
            else if (n == 1 && this.state == 4) {
                this.showDialog();
            }
        }
    }
    
    private final boolean zza(final float n, final float n2, final float n3, final float n4) {
        return Math.abs(this.zzcsc - n) < this.zzcsb && Math.abs(this.zzcsd - n2) < this.zzcsb && Math.abs(this.zzcse - n3) < this.zzcsb && Math.abs(this.zzcsf - n4) < this.zzcsb;
    }
    
    private final void zzrs() {
        if (!(this.mContext instanceof Activity)) {
            zzakb.zzdj("Can not create dialog without Activity Context");
            return;
        }
        final String zzcrx = this.zzcrx;
        while (true) {
            Label_0210: {
                if (TextUtils.isEmpty((CharSequence)zzcrx)) {
                    break Label_0210;
                }
                final Uri build = new Uri$Builder().encodedQuery(zzcrx.replaceAll("\\+", "%20")).build();
                final StringBuilder sb = new StringBuilder();
                zzbv.zzek();
                final Map<String, String> zzg = zzakk.zzg(build);
                for (final String s : zzg.keySet()) {
                    sb.append(s).append(" = ").append(zzg.get(s)).append("\n\n");
                }
                final String trim = sb.toString().trim();
                if (TextUtils.isEmpty((CharSequence)trim)) {
                    break Label_0210;
                }
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.mContext);
                alertDialog$Builder.setMessage((CharSequence)trim);
                alertDialog$Builder.setTitle((CharSequence)"Ad Information");
                alertDialog$Builder.setPositiveButton((CharSequence)"Share", (DialogInterface$OnClickListener)new zzalg(this, trim));
                alertDialog$Builder.setNegativeButton((CharSequence)"Close", zzalh.zzcsl);
                alertDialog$Builder.create().show();
                return;
            }
            final String trim = "No debug information";
            continue;
        }
    }
    
    public final void setAdUnitId(final String zzye) {
        this.zzye = zzye;
    }
    
    public final void showDialog() {
    Label_0091_Outer:
        while (true) {
            Label_0257: {
                while (true) {
                Label_0262:
                    while (true) {
                        try {
                            if (!(boolean)zzkb.zzik().zzd(zznk.zzbec) && !(boolean)zzkb.zzik().zzd(zznk.zzbeb)) {
                                break Label_0257;
                            }
                            if (!(this.mContext instanceof Activity)) {
                                zzakb.zzdj("Can not create dialog without Activity Context");
                                return;
                            }
                            if (!TextUtils.isEmpty((CharSequence)zzbv.zzeu().zzrw())) {
                                final String s = "Creative Preview (Enabled)";
                                if (zzbv.zzeu().zzrx()) {
                                    final String s2 = "Troubleshooting (Enabled)";
                                    final ArrayList<String> list = new ArrayList<String>();
                                    final int zza = zza(list, "Ad Information", true);
                                    final int zza2 = zza(list, s, (boolean)zzkb.zzik().zzd(zznk.zzbeb));
                                    final int zza3 = zza(list, s2, (boolean)zzkb.zzik().zzd(zznk.zzbec));
                                    final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(this.mContext, zzbv.zzem().zzrq());
                                    alertDialog$Builder.setTitle((CharSequence)"Select a Debug Mode").setItems((CharSequence[])list.toArray(new String[0]), (DialogInterface$OnClickListener)new zzalf(this, zza, zza2, zza3));
                                    alertDialog$Builder.create().show();
                                    return;
                                }
                                break Label_0262;
                            }
                        }
                        catch (WindowManager$BadTokenException ex) {
                            if (zzakb.zzqp()) {
                                Log.v("Ads", "", (Throwable)ex);
                                return;
                            }
                            break;
                        }
                        final String s = "Creative Preview";
                        continue Label_0091_Outer;
                    }
                    final String s2 = "Troubleshooting";
                    continue;
                }
            }
            this.zzrs();
            break;
        }
    }
    
    public final void zzda(final String zzaej) {
        this.zzaej = zzaej;
    }
    
    public final void zzdb(final String zzcrx) {
        this.zzcrx = zzcrx;
    }
    
    public final void zzdc(final String zzchz) {
        this.zzchz = zzchz;
    }
    
    public final void zze(final MotionEvent motionEvent) {
        final boolean b = true;
        if (zzkb.zzik().zzd(zznk.zzbed)) {
            final int actionMasked = motionEvent.getActionMasked();
            final int historySize = motionEvent.getHistorySize();
            final int pointerCount = motionEvent.getPointerCount();
            if (actionMasked == 0) {
                this.state = 0;
                this.zzcsc = motionEvent.getX();
                this.zzcsd = motionEvent.getY();
            }
            else if (this.state != -1) {
                if (this.state == 0 && actionMasked == 5) {
                    this.state = 5;
                    this.zzcse = motionEvent.getX(1);
                    this.zzcsf = motionEvent.getY(1);
                    this.handler.postDelayed(this.zzcsg, (long)zzkb.zzik().zzd(zznk.zzbee));
                    return;
                }
                if (this.state == 5) {
                    int n;
                    if (pointerCount != 2) {
                        n = (b ? 1 : 0);
                    }
                    else if (actionMasked == 2) {
                        int i = 0;
                        int n2 = 0;
                        while (i < historySize) {
                            if (!this.zza(motionEvent.getHistoricalX(0, i), motionEvent.getHistoricalY(0, i), motionEvent.getHistoricalX(1, i), motionEvent.getHistoricalY(1, i))) {
                                n2 = 1;
                            }
                            ++i;
                        }
                        n = (b ? 1 : 0);
                        if (this.zza(motionEvent.getX(), motionEvent.getY(), motionEvent.getX(1), motionEvent.getY(1))) {
                            n = n2;
                        }
                    }
                    else {
                        n = 0;
                    }
                    if (n != 0) {
                        this.state = -1;
                        this.handler.removeCallbacks(this.zzcsg);
                    }
                }
            }
            return;
        }
        for (int historySize2 = motionEvent.getHistorySize(), j = 0; j < historySize2; ++j) {
            this.zza(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, j), motionEvent.getHistoricalY(0, j));
        }
        this.zza(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }
}
