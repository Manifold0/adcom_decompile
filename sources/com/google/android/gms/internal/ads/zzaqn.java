package com.google.android.gms.internal.ads;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzadh
public final class zzaqn extends zzaqh {
    private static final Set<String> zzdbg = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzdbh = new DecimalFormat("#,###");
    private File zzdbi;
    private boolean zzdbj;

    public zzaqn(zzapw zzapw) {
        super(zzapw);
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzakb.zzdk("Context.getCacheDir() returned null");
            return;
        }
        this.zzdbi = new File(cacheDir, "admobVideoStreams");
        String str;
        String valueOf;
        if (!this.zzdbi.isDirectory() && !this.zzdbi.mkdirs()) {
            str = "Could not create preload cache directory at ";
            valueOf = String.valueOf(this.zzdbi.getAbsolutePath());
            zzakb.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzdbi = null;
        } else if (!this.zzdbi.setReadable(true, false) || !this.zzdbi.setExecutable(true, false)) {
            str = "Could not set cache file permissions at ";
            valueOf = String.valueOf(this.zzdbi.getAbsolutePath());
            zzakb.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            this.zzdbi = null;
        }
    }

    private final File zzc(File file) {
        return new File(this.zzdbi, String.valueOf(file.getName()).concat(".done"));
    }

    public final void abort() {
        this.zzdbj = true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzdp(java.lang.String r28) {
        /*
        r27 = this;
        r0 = r27;
        r2 = r0.zzdbi;
        if (r2 != 0) goto L_0x0013;
    L_0x0006:
        r2 = 0;
        r3 = "noCacheDir";
        r4 = 0;
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r2, r3, r4);
        r2 = 0;
    L_0x0012:
        return r2;
    L_0x0013:
        r0 = r27;
        r2 = r0.zzdbi;
        if (r2 != 0) goto L_0x0048;
    L_0x0019:
        r2 = 0;
        r3 = r2;
    L_0x001b:
        r2 = com.google.android.gms.internal.ads.zznk.zzaux;
        r4 = com.google.android.gms.internal.ads.zzkb.zzik();
        r2 = r4.zzd(r2);
        r2 = (java.lang.Integer) r2;
        r2 = r2.intValue();
        if (r3 <= r2) goto L_0x00b9;
    L_0x002d:
        r0 = r27;
        r2 = r0.zzdbi;
        if (r2 != 0) goto L_0x006f;
    L_0x0033:
        r2 = 0;
    L_0x0034:
        if (r2 != 0) goto L_0x0013;
    L_0x0036:
        r2 = "Unable to expire stream cache";
        com.google.android.gms.internal.ads.zzakb.zzdk(r2);
        r2 = 0;
        r3 = "expireFailed";
        r4 = 0;
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r2, r3, r4);
        r2 = 0;
        goto L_0x0012;
    L_0x0048:
        r3 = 0;
        r0 = r27;
        r2 = r0.zzdbi;
        r4 = r2.listFiles();
        r5 = r4.length;
        r2 = 0;
        r26 = r2;
        r2 = r3;
        r3 = r26;
    L_0x0058:
        if (r3 >= r5) goto L_0x006d;
    L_0x005a:
        r6 = r4[r3];
        r6 = r6.getName();
        r7 = ".done";
        r6 = r6.endsWith(r7);
        if (r6 != 0) goto L_0x006a;
    L_0x0068:
        r2 = r2 + 1;
    L_0x006a:
        r3 = r3 + 1;
        goto L_0x0058;
    L_0x006d:
        r3 = r2;
        goto L_0x001b;
    L_0x006f:
        r7 = 0;
        r4 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r0 = r27;
        r2 = r0.zzdbi;
        r9 = r2.listFiles();
        r10 = r9.length;
        r2 = 0;
        r8 = r2;
    L_0x0080:
        if (r8 >= r10) goto L_0x009f;
    L_0x0082:
        r6 = r9[r8];
        r2 = r6.getName();
        r3 = ".done";
        r2 = r2.endsWith(r3);
        if (r2 != 0) goto L_0x05b7;
    L_0x0090:
        r2 = r6.lastModified();
        r11 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r11 >= 0) goto L_0x05b7;
    L_0x0098:
        r4 = r6;
    L_0x0099:
        r5 = r8 + 1;
        r8 = r5;
        r7 = r4;
        r4 = r2;
        goto L_0x0080;
    L_0x009f:
        r2 = 0;
        if (r7 == 0) goto L_0x0034;
    L_0x00a2:
        r2 = r7.delete();
        r0 = r27;
        r3 = r0.zzc(r7);
        r4 = r3.isFile();
        if (r4 == 0) goto L_0x0034;
    L_0x00b2:
        r3 = r3.delete();
        r2 = r2 & r3;
        goto L_0x0034;
    L_0x00b9:
        com.google.android.gms.internal.ads.zzkb.zzif();
        r2 = com.google.android.gms.internal.ads.zzamu.zzde(r28);
        r13 = new java.io.File;
        r0 = r27;
        r3 = r0.zzdbi;
        r13.<init>(r3, r2);
        r0 = r27;
        r14 = r0.zzc(r13);
        r2 = r13.isFile();
        if (r2 == 0) goto L_0x0107;
    L_0x00d5:
        r2 = r14.isFile();
        if (r2 == 0) goto L_0x0107;
    L_0x00db:
        r2 = r13.length();
        r3 = (int) r2;
        r4 = "Stream cache hit at ";
        r2 = java.lang.String.valueOf(r28);
        r5 = r2.length();
        if (r5 == 0) goto L_0x0101;
    L_0x00ec:
        r2 = r4.concat(r2);
    L_0x00f0:
        com.google.android.gms.internal.ads.zzakb.zzck(r2);
        r2 = r13.getAbsolutePath();
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r2, r3);
        r2 = 1;
        goto L_0x0012;
    L_0x0101:
        r2 = new java.lang.String;
        r2.<init>(r4);
        goto L_0x00f0;
    L_0x0107:
        r0 = r27;
        r2 = r0.zzdbi;
        r2 = r2.getAbsolutePath();
        r3 = java.lang.String.valueOf(r2);
        r2 = java.lang.String.valueOf(r28);
        r4 = r2.length();
        if (r4 == 0) goto L_0x0155;
    L_0x011d:
        r2 = r3.concat(r2);
        r9 = r2;
    L_0x0122:
        r3 = zzdbg;
        monitor-enter(r3);
        r2 = zzdbg;	 Catch:{ all -> 0x0152 }
        r2 = r2.contains(r9);	 Catch:{ all -> 0x0152 }
        if (r2 == 0) goto L_0x0162;
    L_0x012d:
        r4 = "Stream cache already in progress at ";
        r2 = java.lang.String.valueOf(r28);	 Catch:{ all -> 0x0152 }
        r5 = r2.length();	 Catch:{ all -> 0x0152 }
        if (r5 == 0) goto L_0x015c;
    L_0x0139:
        r2 = r4.concat(r2);	 Catch:{ all -> 0x0152 }
    L_0x013d:
        com.google.android.gms.internal.ads.zzakb.zzdk(r2);	 Catch:{ all -> 0x0152 }
        r2 = r13.getAbsolutePath();	 Catch:{ all -> 0x0152 }
        r4 = "inProgress";
        r5 = 0;
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r2, r4, r5);	 Catch:{ all -> 0x0152 }
        r2 = 0;
        monitor-exit(r3);	 Catch:{ all -> 0x0152 }
        goto L_0x0012;
    L_0x0152:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0152 }
        throw r2;
    L_0x0155:
        r2 = new java.lang.String;
        r2.<init>(r3);
        r9 = r2;
        goto L_0x0122;
    L_0x015c:
        r2 = new java.lang.String;	 Catch:{ all -> 0x0152 }
        r2.<init>(r4);	 Catch:{ all -> 0x0152 }
        goto L_0x013d;
    L_0x0162:
        r2 = zzdbg;	 Catch:{ all -> 0x0152 }
        r2.add(r9);	 Catch:{ all -> 0x0152 }
        monitor-exit(r3);	 Catch:{ all -> 0x0152 }
        r5 = 0;
        r11 = "error";
        r10 = 0;
        com.google.android.gms.ads.internal.zzbv.zzew();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = com.google.android.gms.internal.ads.zznk.zzavc;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = r3.zzd(r2);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = (java.lang.Integer) r2;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r7 = r2.intValue();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = new java.net.URL;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r0 = r28;
        r3.<init>(r0);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = 0;
    L_0x0187:
        r4 = r2 + 1;
        r2 = 20;
        if (r4 > r2) goto L_0x02a9;
    L_0x018d:
        r2 = r3.openConnection();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2.setConnectTimeout(r7);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2.setReadTimeout(r7);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r6 = r2 instanceof java.net.HttpURLConnection;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        if (r6 != 0) goto L_0x021a;
    L_0x019b:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = "Invalid protocol.";
        r2.<init>(r3);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        throw r2;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
    L_0x01a3:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
    L_0x01a6:
        r6 = r2 instanceof java.lang.RuntimeException;
        if (r6 == 0) goto L_0x01b3;
    L_0x01aa:
        r6 = com.google.android.gms.ads.internal.zzbv.zzeo();
        r7 = "VideoStreamFullFileCache.preload";
        r6.zza(r2, r7);
    L_0x01b3:
        r5.close();	 Catch:{ IOException -> 0x05a4, NullPointerException -> 0x05a7 }
    L_0x01b6:
        r0 = r27;
        r5 = r0.zzdbj;
        if (r5 == 0) goto L_0x0573;
    L_0x01bc:
        r2 = java.lang.String.valueOf(r28);
        r2 = r2.length();
        r2 = r2 + 26;
        r5 = new java.lang.StringBuilder;
        r5.<init>(r2);
        r2 = "Preload aborted for URL \"";
        r2 = r5.append(r2);
        r0 = r28;
        r2 = r2.append(r0);
        r5 = "\"";
        r2 = r2.append(r5);
        r2 = r2.toString();
        com.google.android.gms.internal.ads.zzakb.zzdj(r2);
    L_0x01e4:
        r2 = r13.exists();
        if (r2 == 0) goto L_0x0207;
    L_0x01ea:
        r2 = r13.delete();
        if (r2 != 0) goto L_0x0207;
    L_0x01f0:
        r5 = "Could not delete partial cache file at ";
        r2 = r13.getAbsolutePath();
        r2 = java.lang.String.valueOf(r2);
        r6 = r2.length();
        if (r6 == 0) goto L_0x059d;
    L_0x0200:
        r2 = r5.concat(r2);
    L_0x0204:
        com.google.android.gms.internal.ads.zzakb.zzdk(r2);
    L_0x0207:
        r2 = r13.getAbsolutePath();
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r2, r4, r3);
        r2 = zzdbg;
        r2.remove(r9);
        r2 = 0;
        goto L_0x0012;
    L_0x021a:
        r2 = (java.net.HttpURLConnection) r2;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r6 = new com.google.android.gms.internal.ads.zzamy;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r6.<init>();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r8 = 0;
        r6.zza(r2, r8);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r8 = 0;
        r2.setInstanceFollowRedirects(r8);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r8 = r2.getResponseCode();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r6.zza(r2, r8);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r6 = r8 / 100;
        r8 = 3;
        if (r6 != r8) goto L_0x02b1;
    L_0x0235:
        r6 = "Location";
        r8 = r2.getHeaderField(r6);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        if (r8 != 0) goto L_0x024a;
    L_0x023d:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = "Missing Location header in redirect";
        r2.<init>(r3);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        throw r2;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
    L_0x0245:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
        goto L_0x01a6;
    L_0x024a:
        r6 = new java.net.URL;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r6.<init>(r3, r8);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = r6.getProtocol();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        if (r3 != 0) goto L_0x025d;
    L_0x0255:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = "Protocol is null";
        r2.<init>(r3);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        throw r2;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
    L_0x025d:
        r12 = "http";
        r12 = r3.equals(r12);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        if (r12 != 0) goto L_0x0289;
    L_0x0265:
        r12 = "https";
        r12 = r3.equals(r12);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        if (r12 != 0) goto L_0x0289;
    L_0x026d:
        r4 = new java.io.IOException;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r6 = "Unsupported scheme: ";
        r2 = java.lang.String.valueOf(r3);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = r2.length();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        if (r3 == 0) goto L_0x0283;
    L_0x027b:
        r2 = r6.concat(r2);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
    L_0x027f:
        r4.<init>(r2);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        throw r4;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
    L_0x0283:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2.<init>(r6);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        goto L_0x027f;
    L_0x0289:
        r12 = "Redirecting to ";
        r3 = java.lang.String.valueOf(r8);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r8 = r3.length();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        if (r8 == 0) goto L_0x02a3;
    L_0x0295:
        r3 = r12.concat(r3);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
    L_0x0299:
        com.google.android.gms.internal.ads.zzakb.zzck(r3);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2.disconnect();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = r4;
        r3 = r6;
        goto L_0x0187;
    L_0x02a3:
        r3 = new java.lang.String;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3.<init>(r12);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        goto L_0x0299;
    L_0x02a9:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = "Too many redirects (20)";
        r2.<init>(r3);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        throw r2;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
    L_0x02b1:
        r3 = r2 instanceof java.net.HttpURLConnection;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        if (r3 == 0) goto L_0x0313;
    L_0x02b5:
        r0 = r2;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = r0;
        r6 = r3.getResponseCode();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r6 < r3) goto L_0x0313;
    L_0x02c1:
        r4 = "badUrl";
        r2 = "HTTP request failed. Code: ";
        r3 = java.lang.Integer.toString(r6);	 Catch:{ IOException -> 0x030f, RuntimeException -> 0x05aa }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ IOException -> 0x030f, RuntimeException -> 0x05aa }
        r7 = r3.length();	 Catch:{ IOException -> 0x030f, RuntimeException -> 0x05aa }
        if (r7 == 0) goto L_0x0309;
    L_0x02d3:
        r3 = r2.concat(r3);	 Catch:{ IOException -> 0x030f, RuntimeException -> 0x05aa }
    L_0x02d7:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x0306, RuntimeException -> 0x05ae }
        r7 = java.lang.String.valueOf(r28);	 Catch:{ IOException -> 0x0306, RuntimeException -> 0x05ae }
        r7 = r7.length();	 Catch:{ IOException -> 0x0306, RuntimeException -> 0x05ae }
        r7 = r7 + 32;
        r8 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0306, RuntimeException -> 0x05ae }
        r8.<init>(r7);	 Catch:{ IOException -> 0x0306, RuntimeException -> 0x05ae }
        r7 = "HTTP status code ";
        r7 = r8.append(r7);	 Catch:{ IOException -> 0x0306, RuntimeException -> 0x05ae }
        r6 = r7.append(r6);	 Catch:{ IOException -> 0x0306, RuntimeException -> 0x05ae }
        r7 = " at ";
        r6 = r6.append(r7);	 Catch:{ IOException -> 0x0306, RuntimeException -> 0x05ae }
        r0 = r28;
        r6 = r6.append(r0);	 Catch:{ IOException -> 0x0306, RuntimeException -> 0x05ae }
        r6 = r6.toString();	 Catch:{ IOException -> 0x0306, RuntimeException -> 0x05ae }
        r2.<init>(r6);	 Catch:{ IOException -> 0x0306, RuntimeException -> 0x05ae }
        throw r2;	 Catch:{ IOException -> 0x0306, RuntimeException -> 0x05ae }
    L_0x0306:
        r2 = move-exception;
        goto L_0x01a6;
    L_0x0309:
        r3 = new java.lang.String;	 Catch:{ IOException -> 0x030f, RuntimeException -> 0x05aa }
        r3.<init>(r2);	 Catch:{ IOException -> 0x030f, RuntimeException -> 0x05aa }
        goto L_0x02d7;
    L_0x030f:
        r2 = move-exception;
        r3 = r10;
        goto L_0x01a6;
    L_0x0313:
        r7 = r2.getContentLength();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        if (r7 >= 0) goto L_0x0348;
    L_0x0319:
        r3 = "Stream cache aborted, missing content-length header at ";
        r2 = java.lang.String.valueOf(r28);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r4 = r2.length();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        if (r4 == 0) goto L_0x0342;
    L_0x0325:
        r2 = r3.concat(r2);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
    L_0x0329:
        com.google.android.gms.internal.ads.zzakb.zzdk(r2);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = r13.getAbsolutePath();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = "contentLengthMissing";
        r4 = 0;
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r2, r3, r4);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = zzdbg;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2.remove(r9);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = 0;
        goto L_0x0012;
    L_0x0342:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        goto L_0x0329;
    L_0x0348:
        r3 = zzdbh;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r0 = (long) r7;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r16 = r0;
        r0 = r16;
        r4 = r3.format(r0);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = com.google.android.gms.internal.ads.zznk.zzauy;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r6 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = r6.zzd(r3);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = (java.lang.Integer) r3;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r15 = r3.intValue();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        if (r7 <= r15) goto L_0x03c5;
    L_0x0365:
        r2 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = r2.length();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = r2 + 33;
        r3 = java.lang.String.valueOf(r28);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = r3.length();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = r2 + r3;
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3.<init>(r2);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = "Content length ";
        r2 = r3.append(r2);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = r2.append(r4);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = " exceeds limit at ";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r0 = r28;
        r2 = r2.append(r0);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        com.google.android.gms.internal.ads.zzakb.zzdk(r2);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = "File too big for full file cache. Size: ";
        r2 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r4 = r2.length();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        if (r4 == 0) goto L_0x03bf;
    L_0x03a6:
        r2 = r3.concat(r2);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
    L_0x03aa:
        r3 = r13.getAbsolutePath();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r4 = "sizeExceeded";
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r3, r4, r2);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = zzdbg;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2.remove(r9);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = 0;
        goto L_0x0012;
    L_0x03bf:
        r2 = new java.lang.String;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2.<init>(r3);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        goto L_0x03aa;
    L_0x03c5:
        r3 = java.lang.String.valueOf(r4);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = r3.length();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = r3 + 20;
        r6 = java.lang.String.valueOf(r28);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r6 = r6.length();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = r3 + r6;
        r6 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r6.<init>(r3);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = "Caching ";
        r3 = r6.append(r3);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r4 = " bytes from ";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r0 = r28;
        r3 = r3.append(r0);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r3 = r3.toString();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        com.google.android.gms.internal.ads.zzakb.zzck(r3);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r2 = r2.getInputStream();	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r16 = java.nio.channels.Channels.newChannel(r2);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r12 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r12.<init>(r13);	 Catch:{ IOException -> 0x01a3, RuntimeException -> 0x0245 }
        r17 = r12.getChannel();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        r18 = java.nio.ByteBuffer.allocate(r2);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r19 = com.google.android.gms.ads.internal.zzbv.zzer();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r6 = 0;
        r20 = r19.currentTimeMillis();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = com.google.android.gms.internal.ads.zznk.zzavb;	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r3 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = r3.zzd(r2);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = (java.lang.Long) r2;	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = r2.longValue();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r22 = new com.google.android.gms.internal.ads.zzamj;	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r0 = r22;
        r0.<init>(r2);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = com.google.android.gms.internal.ads.zznk.zzava;	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r3 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = r3.zzd(r2);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = (java.lang.Long) r2;	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r24 = r2.longValue();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
    L_0x0441:
        r0 = r16;
        r1 = r18;
        r2 = r0.read(r1);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        if (r2 < 0) goto L_0x0502;
    L_0x044b:
        r6 = r6 + r2;
        if (r6 <= r15) goto L_0x047b;
    L_0x044e:
        r4 = "sizeExceeded";
        r2 = "File too big for full file cache. Size: ";
        r3 = java.lang.Integer.toString(r6);	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        r5 = r3.length();	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        if (r5 == 0) goto L_0x0470;
    L_0x0460:
        r3 = r2.concat(r3);	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
    L_0x0464:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x046c, RuntimeException -> 0x04c6 }
        r5 = "stream cache file size limit exceeded";
        r2.<init>(r5);	 Catch:{ IOException -> 0x046c, RuntimeException -> 0x04c6 }
        throw r2;	 Catch:{ IOException -> 0x046c, RuntimeException -> 0x04c6 }
    L_0x046c:
        r2 = move-exception;
        r5 = r12;
        goto L_0x01a6;
    L_0x0470:
        r3 = new java.lang.String;	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        r3.<init>(r2);	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        goto L_0x0464;
    L_0x0476:
        r2 = move-exception;
        r3 = r10;
        r5 = r12;
        goto L_0x01a6;
    L_0x047b:
        r18.flip();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
    L_0x047e:
        r2 = r17.write(r18);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        if (r2 > 0) goto L_0x047e;
    L_0x0484:
        r18.clear();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = r19.currentTimeMillis();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = r2 - r20;
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4 = r4 * r24;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x04ca;
    L_0x0495:
        r4 = "downloadTimeout";
        r2 = java.lang.Long.toString(r24);	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        r3 = java.lang.String.valueOf(r2);	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        r3 = r3.length();	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        r3 = r3 + 29;
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        r5.<init>(r3);	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        r3 = "Timeout exceeded. Limit: ";
        r3 = r5.append(r3);	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        r2 = r3.append(r2);	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        r3 = " sec";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        r3 = r2.toString();	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x046c, RuntimeException -> 0x04c6 }
        r5 = "stream cache time limit exceeded";
        r2.<init>(r5);	 Catch:{ IOException -> 0x046c, RuntimeException -> 0x04c6 }
        throw r2;	 Catch:{ IOException -> 0x046c, RuntimeException -> 0x04c6 }
    L_0x04c6:
        r2 = move-exception;
        r5 = r12;
        goto L_0x01a6;
    L_0x04ca:
        r0 = r27;
        r2 = r0.zzdbj;	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        if (r2 == 0) goto L_0x04df;
    L_0x04d0:
        r4 = "externalAbort";
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        r3 = "abort requested";
        r2.<init>(r3);	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
        throw r2;	 Catch:{ IOException -> 0x0476, RuntimeException -> 0x04da }
    L_0x04da:
        r2 = move-exception;
        r3 = r10;
        r5 = r12;
        goto L_0x01a6;
    L_0x04df:
        r2 = r22.tryAcquire();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        if (r2 == 0) goto L_0x0441;
    L_0x04e5:
        r5 = r13.getAbsolutePath();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r23 = com.google.android.gms.internal.ads.zzamu.zzsy;	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = new com.google.android.gms.internal.ads.zzaqi;	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r8 = 0;
        r3 = r27;
        r4 = r28;
        r2.<init>(r3, r4, r5, r6, r7, r8);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r0 = r23;
        r0.post(r2);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        goto L_0x0441;
    L_0x04fc:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
        r5 = r12;
        goto L_0x01a6;
    L_0x0502:
        r12.close();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = 3;
        r2 = com.google.android.gms.internal.ads.zzakb.isLoggable(r2);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        if (r2 == 0) goto L_0x0548;
    L_0x050c:
        r2 = zzdbh;	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r4 = (long) r6;	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = r2.format(r4);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r3 = java.lang.String.valueOf(r2);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r3 = r3.length();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r3 = r3 + 22;
        r4 = java.lang.String.valueOf(r28);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r4 = r4.length();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r3 = r3 + r4;
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r4.<init>(r3);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r3 = "Preloaded ";
        r3 = r4.append(r3);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = r3.append(r2);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r3 = " bytes from ";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r0 = r28;
        r2 = r2.append(r0);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        com.google.android.gms.internal.ads.zzakb.zzck(r2);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
    L_0x0548:
        r2 = 1;
        r3 = 0;
        r13.setReadable(r2, r3);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = r14.isFile();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        if (r2 == 0) goto L_0x056d;
    L_0x0553:
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r14.setLastModified(r2);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
    L_0x055a:
        r2 = r13.getAbsolutePath();	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r0 = r27;
        r1 = r28;
        r0.zza(r1, r2, r6);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = zzdbg;	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2.remove(r9);	 Catch:{ IOException -> 0x04fc, RuntimeException -> 0x05b1 }
        r2 = 1;
        goto L_0x0012;
    L_0x056d:
        r14.createNewFile();	 Catch:{ IOException -> 0x0571, RuntimeException -> 0x05b1 }
        goto L_0x055a;
    L_0x0571:
        r2 = move-exception;
        goto L_0x055a;
    L_0x0573:
        r5 = java.lang.String.valueOf(r28);
        r5 = r5.length();
        r5 = r5 + 25;
        r6 = new java.lang.StringBuilder;
        r6.<init>(r5);
        r5 = "Preload failed for URL \"";
        r5 = r6.append(r5);
        r0 = r28;
        r5 = r5.append(r0);
        r6 = "\"";
        r5 = r5.append(r6);
        r5 = r5.toString();
        com.google.android.gms.internal.ads.zzakb.zzc(r5, r2);
        goto L_0x01e4;
    L_0x059d:
        r2 = new java.lang.String;
        r2.<init>(r5);
        goto L_0x0204;
    L_0x05a4:
        r5 = move-exception;
        goto L_0x01b6;
    L_0x05a7:
        r5 = move-exception;
        goto L_0x01b6;
    L_0x05aa:
        r2 = move-exception;
        r3 = r10;
        goto L_0x01a6;
    L_0x05ae:
        r2 = move-exception;
        goto L_0x01a6;
    L_0x05b1:
        r2 = move-exception;
        r3 = r10;
        r4 = r11;
        r5 = r12;
        goto L_0x01a6;
    L_0x05b7:
        r2 = r4;
        r4 = r7;
        goto L_0x0099;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaqn.zzdp(java.lang.String):boolean");
    }
}
