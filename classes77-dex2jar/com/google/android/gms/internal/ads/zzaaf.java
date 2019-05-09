// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import android.os.Environment;
import android.app.DownloadManager$Request;
import android.net.Uri;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

final class zzaaf implements DialogInterface$OnClickListener
{
    private final /* synthetic */ String zzbwo;
    private final /* synthetic */ String zzbwp;
    private final /* synthetic */ zzaae zzbwq;
    
    zzaaf(final zzaae zzbwq, final String zzbwo, final String zzbwp) {
        this.zzbwq = zzbwq;
        this.zzbwo = zzbwo;
        this.zzbwp = zzbwp;
    }
    
    public final void onClick(final DialogInterface dialogInterface, final int n) {
        final DownloadManager downloadManager = (DownloadManager)this.zzbwq.mContext.getSystemService("download");
        try {
            final String zzbwo = this.zzbwo;
            final String zzbwp = this.zzbwp;
            final DownloadManager$Request downloadManager$Request = new DownloadManager$Request(Uri.parse(zzbwo));
            downloadManager$Request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, zzbwp);
            zzbv.zzem().zza(downloadManager$Request);
            downloadManager.enqueue(downloadManager$Request);
        }
        catch (IllegalStateException ex) {
            this.zzbwq.zzbw("Could not store picture.");
        }
    }
}
