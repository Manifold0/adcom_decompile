package com.google.android.gms.internal.ads;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.ads.impl.C0948R;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzadh
public final class zzaae extends zzaal {
    private final Context mContext;
    private final Map<String, String> zzbgp;

    public zzaae(zzaqw zzaqw, Map<String, String> map) {
        super(zzaqw, "storePicture");
        this.zzbgp = map;
        this.mContext = zzaqw.zzto();
    }

    public final void execute() {
        if (this.mContext == null) {
            zzbw("Activity context is not available");
            return;
        }
        zzbv.zzek();
        if (zzakk.zzao(this.mContext).zziy()) {
            String str = (String) this.zzbgp.get("iurl");
            if (TextUtils.isEmpty(str)) {
                zzbw("Image url cannot be empty.");
                return;
            } else if (URLUtil.isValidUrl(str)) {
                String lastPathSegment = Uri.parse(str).getLastPathSegment();
                zzbv.zzek();
                if (zzakk.zzcw(lastPathSegment)) {
                    Resources resources = zzbv.zzeo().getResources();
                    zzbv.zzek();
                    Builder zzan = zzakk.zzan(this.mContext);
                    zzan.setTitle(resources != null ? resources.getString(C0948R.string.s1) : "Save image");
                    zzan.setMessage(resources != null ? resources.getString(C0948R.string.s2) : "Allow Ad to store image in Picture gallery?");
                    zzan.setPositiveButton(resources != null ? resources.getString(C0948R.string.s3) : "Accept", new zzaaf(this, str, lastPathSegment));
                    zzan.setNegativeButton(resources != null ? resources.getString(C0948R.string.s4) : "Decline", new zzaag(this));
                    zzan.create().show();
                    return;
                }
                r1 = "Image type not recognized: ";
                str = String.valueOf(lastPathSegment);
                zzbw(str.length() != 0 ? r1.concat(str) : new String(r1));
                return;
            } else {
                r1 = "Invalid image url: ";
                str = String.valueOf(str);
                zzbw(str.length() != 0 ? r1.concat(str) : new String(r1));
                return;
            }
        }
        zzbw("Feature is not supported by the device.");
    }
}
