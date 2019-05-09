// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.res.Resources;
import android.app.AlertDialog$Builder;
import android.content.DialogInterface$OnClickListener;
import com.google.android.gms.ads.impl.R$string;
import com.google.android.gms.ads.internal.zzbv;
import android.annotation.TargetApi;
import android.provider.CalendarContract$Events;
import android.content.Intent;
import android.text.TextUtils;
import java.util.Map;
import android.content.Context;

@zzadh
public final class zzzy extends zzaal
{
    private final Context mContext;
    private final Map<String, String> zzbgp;
    private String zzbvs;
    private long zzbvt;
    private long zzbvu;
    private String zzbvv;
    private String zzbvw;
    
    public zzzy(final zzaqw zzaqw, final Map<String, String> zzbgp) {
        super(zzaqw, "createCalendarEvent");
        this.zzbgp = zzbgp;
        this.mContext = (Context)zzaqw.zzto();
        this.zzbvs = this.zzbu("description");
        this.zzbvv = this.zzbu("summary");
        this.zzbvt = this.zzbv("start_ticks");
        this.zzbvu = this.zzbv("end_ticks");
        this.zzbvw = this.zzbu("location");
    }
    
    private final String zzbu(final String s) {
        if (TextUtils.isEmpty((CharSequence)this.zzbgp.get(s))) {
            return "";
        }
        return this.zzbgp.get(s);
    }
    
    private final long zzbv(String s) {
        s = this.zzbgp.get(s);
        if (s == null) {
            return -1L;
        }
        try {
            return Long.parseLong(s);
        }
        catch (NumberFormatException ex) {
            return -1L;
        }
    }
    
    @TargetApi(14)
    final Intent createIntent() {
        final Intent setData = new Intent("android.intent.action.EDIT").setData(CalendarContract$Events.CONTENT_URI);
        setData.putExtra("title", this.zzbvs);
        setData.putExtra("eventLocation", this.zzbvw);
        setData.putExtra("description", this.zzbvv);
        if (this.zzbvt > -1L) {
            setData.putExtra("beginTime", this.zzbvt);
        }
        if (this.zzbvu > -1L) {
            setData.putExtra("endTime", this.zzbvu);
        }
        setData.setFlags(268435456);
        return setData;
    }
    
    public final void execute() {
        if (this.mContext == null) {
            this.zzbw("Activity context is not available.");
            return;
        }
        zzbv.zzek();
        if (!zzakk.zzao(this.mContext).zziz()) {
            this.zzbw("This feature is not available on the device.");
            return;
        }
        zzbv.zzek();
        final AlertDialog$Builder zzan = zzakk.zzan(this.mContext);
        final Resources resources = zzbv.zzeo().getResources();
        String string;
        if (resources != null) {
            string = resources.getString(R$string.s5);
        }
        else {
            string = "Create calendar event";
        }
        zzan.setTitle((CharSequence)string);
        String string2;
        if (resources != null) {
            string2 = resources.getString(R$string.s6);
        }
        else {
            string2 = "Allow Ad to create a calendar event?";
        }
        zzan.setMessage((CharSequence)string2);
        String string3;
        if (resources != null) {
            string3 = resources.getString(R$string.s3);
        }
        else {
            string3 = "Accept";
        }
        zzan.setPositiveButton((CharSequence)string3, (DialogInterface$OnClickListener)new zzzz(this));
        String string4;
        if (resources != null) {
            string4 = resources.getString(R$string.s4);
        }
        else {
            string4 = "Decline";
        }
        zzan.setNegativeButton((CharSequence)string4, (DialogInterface$OnClickListener)new zzaaa(this));
        zzan.create().show();
    }
}
