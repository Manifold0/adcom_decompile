// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.app.AlertDialog$Builder;
import android.content.res.Resources;
import android.content.DialogInterface$OnClickListener;
import com.google.android.gms.ads.impl.R$string;
import android.net.Uri;
import android.webkit.URLUtil;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;
import android.content.Context;

@zzadh
public final class zzaae extends zzaal
{
    private final Context mContext;
    private final Map<String, String> zzbgp;
    
    public zzaae(final zzaqw zzaqw, final Map<String, String> zzbgp) {
        super(zzaqw, "storePicture");
        this.zzbgp = zzbgp;
        this.mContext = (Context)zzaqw.zzto();
    }
    
    public final void execute() {
        if (this.mContext == null) {
            this.zzbw("Activity context is not available");
            return;
        }
        zzbv.zzek();
        if (!zzakk.zzao(this.mContext).zziy()) {
            this.zzbw("Feature is not supported by the device.");
            return;
        }
        final String s = this.zzbgp.get("iurl");
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.zzbw("Image url cannot be empty.");
            return;
        }
        if (!URLUtil.isValidUrl(s)) {
            final String value = String.valueOf(s);
            String concat;
            if (value.length() != 0) {
                concat = "Invalid image url: ".concat(value);
            }
            else {
                concat = new String("Invalid image url: ");
            }
            this.zzbw(concat);
            return;
        }
        final String lastPathSegment = Uri.parse(s).getLastPathSegment();
        zzbv.zzek();
        if (!zzakk.zzcw(lastPathSegment)) {
            final String value2 = String.valueOf(lastPathSegment);
            String concat2;
            if (value2.length() != 0) {
                concat2 = "Image type not recognized: ".concat(value2);
            }
            else {
                concat2 = new String("Image type not recognized: ");
            }
            this.zzbw(concat2);
            return;
        }
        final Resources resources = zzbv.zzeo().getResources();
        zzbv.zzek();
        final AlertDialog$Builder zzan = zzakk.zzan(this.mContext);
        String string;
        if (resources != null) {
            string = resources.getString(R$string.s1);
        }
        else {
            string = "Save image";
        }
        zzan.setTitle((CharSequence)string);
        String string2;
        if (resources != null) {
            string2 = resources.getString(R$string.s2);
        }
        else {
            string2 = "Allow Ad to store image in Picture gallery?";
        }
        zzan.setMessage((CharSequence)string2);
        String string3;
        if (resources != null) {
            string3 = resources.getString(R$string.s3);
        }
        else {
            string3 = "Accept";
        }
        zzan.setPositiveButton((CharSequence)string3, (DialogInterface$OnClickListener)new zzaaf(this, s, lastPathSegment));
        String string4;
        if (resources != null) {
            string4 = resources.getString(R$string.s4);
        }
        else {
            string4 = "Decline";
        }
        zzan.setNegativeButton((CharSequence)string4, (DialogInterface$OnClickListener)new zzaag(this));
        zzan.create().show();
    }
}
