// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.CBLogging;
import android.net.Uri;
import android.content.Intent;
import com.chartboost.sdk.i;
import com.chartboost.sdk.Tracking.a;
import android.content.Context;
import java.net.URISyntaxException;
import com.chartboost.sdk.Model.CBError;
import java.net.URI;
import android.app.Activity;
import com.chartboost.sdk.Model.c;
import java.util.concurrent.Executor;
import android.os.Handler;

public class ak
{
    final ai a;
    final Handler b;
    private final Executor c;
    private final ah d;
    
    public ak(final Executor c, final ah d, final ai a, final Handler b) {
        this.c = c;
        this.d = d;
        this.a = a;
        this.b = b;
    }
    
    public void a(final c c, final String s, final Activity activity, final aj aj) {
        String scheme;
        try {
            scheme = new URI(s).getScheme();
            if (scheme == null) {
                this.a(c, false, s, CBError.CBClickError.URI_INVALID, aj);
                return;
            }
        }
        catch (URISyntaxException ex) {
            this.a(c, false, s, CBError.CBClickError.URI_INVALID, aj);
            return;
        }
        if (!scheme.equals("http") && !scheme.equals("https")) {
            this.a(c, s, (Context)activity, aj);
            return;
        }
        this.c.execute(new Runnable() {
            private void a(final String s) {
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ak.this.a(c, s, (Context)activity, aj);
                        }
                        catch (Exception ex) {
                            com.chartboost.sdk.Tracking.a.a(ak.class, "open openOnUiThread Runnable.run", ex);
                        }
                    }
                };
                if (activity != null) {
                    activity.runOnUiThread((Runnable)runnable);
                    return;
                }
                ak.this.b.post((Runnable)runnable);
            }
            
            @Override
            public void run() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     1: getfield        com/chartboost/sdk/impl/ak$1.a:Ljava/lang/String;
                //     4: astore_2       
                //     5: aload_0        
                //     6: getfield        com/chartboost/sdk/impl/ak$1.e:Lcom/chartboost/sdk/impl/ak;
                //     9: getfield        com/chartboost/sdk/impl/ak.a:Lcom/chartboost/sdk/impl/ai;
                //    12: invokevirtual   com/chartboost/sdk/impl/ai.b:()Z
                //    15: istore_1       
                //    16: iload_1        
                //    17: ifeq            161
                //    20: aconst_null    
                //    21: astore_3       
                //    22: aconst_null    
                //    23: astore          5
                //    25: new             Ljava/net/URL;
                //    28: dup            
                //    29: aload_0        
                //    30: getfield        com/chartboost/sdk/impl/ak$1.a:Ljava/lang/String;
                //    33: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
                //    36: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
                //    39: checkcast       Ljava/net/HttpURLConnection;
                //    42: astore          4
                //    44: aload           4
                //    46: iconst_0       
                //    47: invokevirtual   java/net/HttpURLConnection.setInstanceFollowRedirects:(Z)V
                //    50: aload           4
                //    52: sipush          10000
                //    55: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
                //    58: aload           4
                //    60: sipush          10000
                //    63: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
                //    66: aload           4
                //    68: ldc             "Location"
                //    70: invokevirtual   java/net/HttpURLConnection.getHeaderField:(Ljava/lang/String;)Ljava/lang/String;
                //    73: astore_3       
                //    74: aload_3        
                //    75: ifnull          167
                //    78: aload_3        
                //    79: astore_2       
                //    80: aload           4
                //    82: ifnull          164
                //    85: aload           4
                //    87: invokevirtual   java/net/HttpURLConnection.disconnect:()V
                //    90: aload_0        
                //    91: aload_2        
                //    92: invokespecial   com/chartboost/sdk/impl/ak$1.a:(Ljava/lang/String;)V
                //    95: return         
                //    96: astore_3       
                //    97: aload           5
                //    99: astore          4
                //   101: aload_3        
                //   102: astore          5
                //   104: aload           4
                //   106: astore_3       
                //   107: ldc             "CBURLOpener"
                //   109: ldc             "Exception raised while opening a HTTP Conection"
                //   111: aload           5
                //   113: invokestatic    com/chartboost/sdk/Libraries/CBLogging.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
                //   116: aload           4
                //   118: ifnull          161
                //   121: aload           4
                //   123: invokevirtual   java/net/HttpURLConnection.disconnect:()V
                //   126: goto            90
                //   129: aload_3        
                //   130: ifnull          137
                //   133: aload_3        
                //   134: invokevirtual   java/net/HttpURLConnection.disconnect:()V
                //   137: aload_2        
                //   138: athrow         
                //   139: astore_2       
                //   140: ldc             Lcom/chartboost/sdk/impl/ak;.class
                //   142: ldc             "open followTask"
                //   144: aload_2        
                //   145: invokestatic    com/chartboost/sdk/Tracking/a.a:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Exception;)V
                //   148: return         
                //   149: astore_2       
                //   150: aload           4
                //   152: astore_3       
                //   153: goto            129
                //   156: astore          5
                //   158: goto            104
                //   161: goto            90
                //   164: goto            90
                //   167: goto            80
                //   170: astore_2       
                //   171: goto            129
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  0      16     139    149    Ljava/lang/Exception;
                //  25     44     96     104    Ljava/lang/Exception;
                //  25     44     170    174    Any
                //  44     74     156    161    Ljava/lang/Exception;
                //  44     74     149    156    Any
                //  85     90     139    149    Ljava/lang/Exception;
                //  90     95     139    149    Ljava/lang/Exception;
                //  107    116    170    174    Any
                //  121    126    139    149    Ljava/lang/Exception;
                //  133    137    139    149    Ljava/lang/Exception;
                //  137    139    139    149    Ljava/lang/Exception;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0080:
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
    }
    
    void a(final c c, String s, Context string, final aj aj) {
        if (c != null && c.b()) {
            c.l = 5;
        }
        Context m;
        if ((m = string) == null) {
            m = i.m;
        }
        if (m == null) {
            this.a(c, false, s, CBError.CBClickError.NO_HOST_ACTIVITY, aj);
            return;
        }
        while (true) {
            try {
                final Intent intent = new Intent("android.intent.action.VIEW");
                if (!(m instanceof Activity)) {
                    intent.addFlags(268435456);
                }
                intent.setData(Uri.parse(s));
                m.startActivity(intent);
                this.a(c, true, s, null, aj);
            }
            catch (Exception ex2) {
                if (s.startsWith("market://")) {
                    string = (Context)s;
                    try {
                        s = (String)(string = (Context)("http://market.android.com/" + s.substring(9)));
                        final Intent intent2 = new Intent("android.intent.action.VIEW");
                        string = (Context)s;
                        if (!(m instanceof Activity)) {
                            string = (Context)s;
                            intent2.addFlags(268435456);
                        }
                        string = (Context)s;
                        intent2.setData(Uri.parse(s));
                        string = (Context)s;
                        m.startActivity(intent2);
                        continue;
                    }
                    catch (Exception ex) {
                        CBLogging.a("CBURLOpener", "Exception raised openeing an inavld playstore URL", ex);
                        this.a(c, false, (String)string, CBError.CBClickError.URI_UNRECOGNIZED, aj);
                        return;
                    }
                }
                this.a(c, false, s, CBError.CBClickError.URI_UNRECOGNIZED, aj);
                continue;
            }
            break;
        }
    }
    
    public void a(final c c, final String s, final aj aj) {
        Activity a;
        if (c != null) {
            a = c.g.a();
        }
        else {
            a = null;
        }
        this.a(c, s, a, aj);
    }
    
    public void a(final c c, final boolean b, final String s, final CBError.CBClickError cbClickError, final aj aj) {
        if (c != null) {
            c.x = false;
            if (c.b()) {
                c.l = 4;
            }
        }
        if (!b) {
            if (i.c != null) {
                i.c.didFailToRecordClick(s, cbClickError);
            }
        }
        else {
            if (c != null && c.w != null) {
                this.d.a((ad<Object>)c.w);
                return;
            }
            if (aj != null) {
                this.d.a((ad<Object>)aj);
            }
        }
    }
    
    public boolean a(final String s) {
        boolean b = false;
        try {
            final Context m = i.m;
            final Intent intent = new Intent("android.intent.action.VIEW");
            if (!(m instanceof Activity)) {
                intent.addFlags(268435456);
            }
            intent.setData(Uri.parse(s));
            if (m.getPackageManager().queryIntentActivities(intent, 65536).size() > 0) {
                b = true;
            }
            return b;
        }
        catch (Exception ex) {
            CBLogging.a("CBURLOpener", "Cannot open URL", ex);
            com.chartboost.sdk.Tracking.a.a(ak.class, "canOpenURL", ex);
            return false;
        }
    }
}
