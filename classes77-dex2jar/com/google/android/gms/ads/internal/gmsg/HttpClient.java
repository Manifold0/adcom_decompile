// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.gmsg;

import java.util.List;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaki;
import java.util.Map;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.net.URL;
import java.util.Iterator;
import org.json.JSONException;
import com.google.android.gms.internal.ads.zzakb;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.android.gms.internal.ads.zzang;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepName;
import android.support.annotation.Keep;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzue;

@zzadh
@Keep
@KeepName
public class HttpClient implements zzv<zzue>
{
    private final Context mContext;
    private final zzang zzyf;
    
    public HttpClient(final Context mContext, final zzang zzyf) {
        this.mContext = mContext;
        this.zzyf = zzyf;
    }
    
    private final zzc zza(final zzb p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          6
        //     3: aconst_null    
        //     4: astore          5
        //     6: aload_1        
        //     7: invokevirtual   com/google/android/gms/ads/internal/gmsg/HttpClient$zzb.zzkw:()Ljava/net/URL;
        //    10: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    13: checkcast       Ljava/net/HttpURLConnection;
        //    16: astore          4
        //    18: invokestatic    com/google/android/gms/ads/internal/zzbv.zzek:()Lcom/google/android/gms/internal/ads/zzakk;
        //    21: aload_0        
        //    22: getfield        com/google/android/gms/ads/internal/gmsg/HttpClient.mContext:Landroid/content/Context;
        //    25: aload_0        
        //    26: getfield        com/google/android/gms/ads/internal/gmsg/HttpClient.zzyf:Lcom/google/android/gms/internal/ads/zzang;
        //    29: getfield        com/google/android/gms/internal/ads/zzang.zzcw:Ljava/lang/String;
        //    32: iconst_0       
        //    33: aload           4
        //    35: invokevirtual   com/google/android/gms/internal/ads/zzakk.zza:(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
        //    38: aload_1        
        //    39: invokevirtual   com/google/android/gms/ads/internal/gmsg/HttpClient$zzb.zzkx:()Ljava/util/ArrayList;
        //    42: checkcast       Ljava/util/ArrayList;
        //    45: astore          6
        //    47: aload           6
        //    49: invokevirtual   java/util/ArrayList.size:()I
        //    52: istore_3       
        //    53: iconst_0       
        //    54: istore_2       
        //    55: iload_2        
        //    56: iload_3        
        //    57: if_icmpge       134
        //    60: aload           6
        //    62: iload_2        
        //    63: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //    66: astore          7
        //    68: iload_2        
        //    69: iconst_1       
        //    70: iadd           
        //    71: istore_2       
        //    72: aload           7
        //    74: checkcast       Lcom/google/android/gms/ads/internal/gmsg/HttpClient$zza;
        //    77: astore          7
        //    79: aload           4
        //    81: aload           7
        //    83: invokevirtual   com/google/android/gms/ads/internal/gmsg/HttpClient$zza.getKey:()Ljava/lang/String;
        //    86: aload           7
        //    88: invokevirtual   com/google/android/gms/ads/internal/gmsg/HttpClient$zza.getValue:()Ljava/lang/String;
        //    91: invokevirtual   java/net/HttpURLConnection.addRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    94: goto            55
        //    97: astore          5
        //    99: aload           4
        //   101: astore_1       
        //   102: aload           5
        //   104: astore          4
        //   106: new             Lcom/google/android/gms/ads/internal/gmsg/HttpClient$zzc;
        //   109: dup            
        //   110: aload_0        
        //   111: iconst_0       
        //   112: aconst_null    
        //   113: aload           4
        //   115: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   118: invokespecial   com/google/android/gms/ads/internal/gmsg/HttpClient$zzc.<init>:(Lcom/google/android/gms/ads/internal/gmsg/HttpClient;ZLcom/google/android/gms/ads/internal/gmsg/HttpClient$zzd;Ljava/lang/String;)V
        //   121: astore          4
        //   123: aload_1        
        //   124: ifnull          131
        //   127: aload_1        
        //   128: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   131: aload           4
        //   133: areturn        
        //   134: aload_1        
        //   135: invokevirtual   com/google/android/gms/ads/internal/gmsg/HttpClient$zzb.zzky:()Ljava/lang/String;
        //   138: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   141: ifne            193
        //   144: aload           4
        //   146: iconst_1       
        //   147: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   150: aload_1        
        //   151: invokevirtual   com/google/android/gms/ads/internal/gmsg/HttpClient$zzb.zzky:()Ljava/lang/String;
        //   154: invokevirtual   java/lang/String.getBytes:()[B
        //   157: astore          5
        //   159: aload           4
        //   161: aload           5
        //   163: arraylength    
        //   164: invokevirtual   java/net/HttpURLConnection.setFixedLengthStreamingMode:(I)V
        //   167: new             Ljava/io/BufferedOutputStream;
        //   170: dup            
        //   171: aload           4
        //   173: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   176: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   179: astore          6
        //   181: aload           6
        //   183: aload           5
        //   185: invokevirtual   java/io/BufferedOutputStream.write:([B)V
        //   188: aload           6
        //   190: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   193: new             Lcom/google/android/gms/internal/ads/zzamy;
        //   196: dup            
        //   197: invokespecial   com/google/android/gms/internal/ads/zzamy.<init>:()V
        //   200: astore          6
        //   202: aload           6
        //   204: aload           4
        //   206: aload           5
        //   208: invokevirtual   com/google/android/gms/internal/ads/zzamy.zza:(Ljava/net/HttpURLConnection;[B)V
        //   211: new             Ljava/util/ArrayList;
        //   214: dup            
        //   215: invokespecial   java/util/ArrayList.<init>:()V
        //   218: astore          5
        //   220: aload           4
        //   222: invokevirtual   java/net/HttpURLConnection.getHeaderFields:()Ljava/util/Map;
        //   225: ifnull          347
        //   228: aload           4
        //   230: invokevirtual   java/net/HttpURLConnection.getHeaderFields:()Ljava/util/Map;
        //   233: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //   238: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   243: astore          7
        //   245: aload           7
        //   247: invokeinterface java/util/Iterator.hasNext:()Z
        //   252: ifeq            347
        //   255: aload           7
        //   257: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   262: checkcast       Ljava/util/Map$Entry;
        //   265: astore          8
        //   267: aload           8
        //   269: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   274: checkcast       Ljava/util/List;
        //   277: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   282: astore          9
        //   284: aload           9
        //   286: invokeinterface java/util/Iterator.hasNext:()Z
        //   291: ifeq            245
        //   294: aload           9
        //   296: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   301: checkcast       Ljava/lang/String;
        //   304: astore          10
        //   306: aload           5
        //   308: new             Lcom/google/android/gms/ads/internal/gmsg/HttpClient$zza;
        //   311: dup            
        //   312: aload           8
        //   314: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   319: checkcast       Ljava/lang/String;
        //   322: aload           10
        //   324: invokespecial   com/google/android/gms/ads/internal/gmsg/HttpClient$zza.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //   327: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   330: pop            
        //   331: goto            284
        //   334: astore_1       
        //   335: aload           4
        //   337: ifnull          345
        //   340: aload           4
        //   342: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   345: aload_1        
        //   346: athrow         
        //   347: aload_1        
        //   348: invokevirtual   com/google/android/gms/ads/internal/gmsg/HttpClient$zzb.zzkv:()Ljava/lang/String;
        //   351: astore_1       
        //   352: aload           4
        //   354: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   357: istore_2       
        //   358: invokestatic    com/google/android/gms/ads/internal/zzbv.zzek:()Lcom/google/android/gms/internal/ads/zzakk;
        //   361: pop            
        //   362: new             Lcom/google/android/gms/ads/internal/gmsg/HttpClient$zzd;
        //   365: dup            
        //   366: aload_1        
        //   367: iload_2        
        //   368: aload           5
        //   370: new             Ljava/io/InputStreamReader;
        //   373: dup            
        //   374: aload           4
        //   376: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   379: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //   382: invokestatic    com/google/android/gms/internal/ads/zzakk.zza:(Ljava/io/InputStreamReader;)Ljava/lang/String;
        //   385: invokespecial   com/google/android/gms/ads/internal/gmsg/HttpClient$zzd.<init>:(Ljava/lang/String;ILjava/util/List;Ljava/lang/String;)V
        //   388: astore_1       
        //   389: aload           6
        //   391: aload           4
        //   393: aload_1        
        //   394: invokevirtual   com/google/android/gms/ads/internal/gmsg/HttpClient$zzd.getResponseCode:()I
        //   397: invokevirtual   com/google/android/gms/internal/ads/zzamy.zza:(Ljava/net/HttpURLConnection;I)V
        //   400: aload           6
        //   402: aload_1        
        //   403: invokevirtual   com/google/android/gms/ads/internal/gmsg/HttpClient$zzd.getBody:()Ljava/lang/String;
        //   406: invokevirtual   com/google/android/gms/internal/ads/zzamy.zzdg:(Ljava/lang/String;)V
        //   409: new             Lcom/google/android/gms/ads/internal/gmsg/HttpClient$zzc;
        //   412: dup            
        //   413: aload_0        
        //   414: iconst_1       
        //   415: aload_1        
        //   416: aconst_null    
        //   417: invokespecial   com/google/android/gms/ads/internal/gmsg/HttpClient$zzc.<init>:(Lcom/google/android/gms/ads/internal/gmsg/HttpClient;ZLcom/google/android/gms/ads/internal/gmsg/HttpClient$zzd;Ljava/lang/String;)V
        //   420: astore_1       
        //   421: aload           4
        //   423: ifnull          431
        //   426: aload           4
        //   428: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   431: aload_1        
        //   432: areturn        
        //   433: astore_1       
        //   434: aload           6
        //   436: astore          4
        //   438: goto            335
        //   441: astore          5
        //   443: aload_1        
        //   444: astore          4
        //   446: aload           5
        //   448: astore_1       
        //   449: goto            335
        //   452: astore          4
        //   454: aconst_null    
        //   455: astore_1       
        //   456: goto            106
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      18     452    459    Ljava/lang/Exception;
        //  6      18     433    441    Any
        //  18     53     97     106    Ljava/lang/Exception;
        //  18     53     334    335    Any
        //  60     68     97     106    Ljava/lang/Exception;
        //  60     68     334    335    Any
        //  72     94     97     106    Ljava/lang/Exception;
        //  72     94     334    335    Any
        //  106    123    441    452    Any
        //  134    193    97     106    Ljava/lang/Exception;
        //  134    193    334    335    Any
        //  193    245    97     106    Ljava/lang/Exception;
        //  193    245    334    335    Any
        //  245    284    97     106    Ljava/lang/Exception;
        //  245    284    334    335    Any
        //  284    331    97     106    Ljava/lang/Exception;
        //  284    331    334    335    Any
        //  347    421    97     106    Ljava/lang/Exception;
        //  347    421    334    335    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0106:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static JSONObject zza(final zzd zzd) {
        final JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        try {
            jsonObject.put("http_request_id", (Object)zzd.zzkv());
            if (zzd.getBody() != null) {
                jsonObject.put("body", (Object)zzd.getBody());
            }
            jsonArray = new JSONArray();
            for (final zza zza : zzd.zzla()) {
                jsonArray.put((Object)new JSONObject().put("key", (Object)zza.getKey()).put("value", (Object)zza.getValue()));
            }
        }
        catch (JSONException ex) {
            zzakb.zzb("Error constructing JSON for http response.", (Throwable)ex);
            return jsonObject;
        }
        jsonObject.put("headers", (Object)jsonArray);
        jsonObject.put("response_code", zzd.getResponseCode());
        return jsonObject;
    }
    
    private static zzb zzc(JSONObject optJSONArray) {
        final String optString = optJSONArray.optString("http_request_id");
        final String optString2 = optJSONArray.optString("url");
        final String optString3 = optJSONArray.optString("post_body", (String)null);
        URL url;
        ArrayList<zza> list;
        while (true) {
            try {
                url = new URL(optString2);
                list = new ArrayList<zza>();
                if ((optJSONArray = (JSONObject)optJSONArray.optJSONArray("headers")) == null) {
                    optJSONArray = (JSONObject)new JSONArray();
                }
                for (int i = 0; i < ((JSONArray)optJSONArray).length(); ++i) {
                    final JSONObject optJSONObject = ((JSONArray)optJSONArray).optJSONObject(i);
                    if (optJSONObject != null) {
                        list.add(new zza(optJSONObject.optString("key"), optJSONObject.optString("value")));
                    }
                }
            }
            catch (MalformedURLException ex) {
                zzakb.zzb("Error constructing http request.", (Throwable)ex);
                url = null;
                continue;
            }
            break;
        }
        return new zzb(optString, url, list, optString3);
    }
    
    @Keep
    @KeepName
    public JSONObject send(final JSONObject jsonObject) {
        final JSONObject jsonObject2 = new JSONObject();
        String optString = "";
        try {
            final String s = optString = jsonObject.optString("http_request_id");
            final zzc zza = this.zza(zzc(jsonObject));
            optString = s;
            if (zza.isSuccess()) {
                optString = s;
                jsonObject2.put("response", (Object)zza(zza.zzkz()));
                optString = s;
                jsonObject2.put("success", true);
                return jsonObject2;
            }
            optString = s;
            jsonObject2.put("response", (Object)new JSONObject().put("http_request_id", (Object)s));
            optString = s;
            jsonObject2.put("success", false);
            optString = s;
            jsonObject2.put("reason", (Object)zza.getReason());
            return jsonObject2;
        }
        catch (Exception ex) {
            zzakb.zzb("Error executing http request.", (Throwable)ex);
            try {
                jsonObject2.put("response", (Object)new JSONObject().put("http_request_id", (Object)optString));
                jsonObject2.put("success", false);
                jsonObject2.put("reason", (Object)ex.toString());
                return jsonObject2;
            }
            catch (JSONException ex2) {
                zzakb.zzb("Error executing http request.", (Throwable)ex2);
                return jsonObject2;
            }
        }
    }
    
    @zzadh
    @VisibleForTesting
    static final class zza
    {
        private final String mKey;
        private final String mValue;
        
        public zza(final String mKey, final String mValue) {
            this.mKey = mKey;
            this.mValue = mValue;
        }
        
        public final String getKey() {
            return this.mKey;
        }
        
        public final String getValue() {
            return this.mValue;
        }
    }
    
    @zzadh
    @VisibleForTesting
    static final class zzb
    {
        private final String zzbmm;
        private final URL zzbmn;
        private final ArrayList<zza> zzbmo;
        private final String zzbmp;
        
        zzb(final String zzbmm, final URL zzbmn, final ArrayList<zza> zzbmo, final String zzbmp) {
            this.zzbmm = zzbmm;
            this.zzbmn = zzbmn;
            this.zzbmo = zzbmo;
            this.zzbmp = zzbmp;
        }
        
        public final String zzkv() {
            return this.zzbmm;
        }
        
        public final URL zzkw() {
            return this.zzbmn;
        }
        
        public final ArrayList<zza> zzkx() {
            return this.zzbmo;
        }
        
        public final String zzky() {
            return this.zzbmp;
        }
    }
    
    @zzadh
    @VisibleForTesting
    final class zzc
    {
        private final zzd zzbmq;
        private final boolean zzbmr;
        private final String zzbms;
        
        public zzc(final HttpClient httpClient, final boolean zzbmr, final zzd zzbmq, final String zzbms) {
            this.zzbmr = zzbmr;
            this.zzbmq = zzbmq;
            this.zzbms = zzbms;
        }
        
        public final String getReason() {
            return this.zzbms;
        }
        
        public final boolean isSuccess() {
            return this.zzbmr;
        }
        
        public final zzd zzkz() {
            return this.zzbmq;
        }
    }
    
    @zzadh
    @VisibleForTesting
    static final class zzd
    {
        private final String zzbhy;
        private final String zzbmm;
        private final int zzbmt;
        private final List<zza> zzcf;
        
        zzd(final String zzbmm, final int zzbmt, final List<zza> zzcf, final String zzbhy) {
            this.zzbmm = zzbmm;
            this.zzbmt = zzbmt;
            this.zzcf = zzcf;
            this.zzbhy = zzbhy;
        }
        
        public final String getBody() {
            return this.zzbhy;
        }
        
        public final int getResponseCode() {
            return this.zzbmt;
        }
        
        public final String zzkv() {
            return this.zzbmm;
        }
        
        public final Iterable<zza> zzla() {
            return this.zzcf;
        }
    }
}
