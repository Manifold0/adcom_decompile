// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.widget.RelativeLayout;
import android.os.AsyncTask;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import java.lang.reflect.Method;
import android.annotation.TargetApi;
import android.webkit.ValueCallback;
import android.os.Build$VERSION;
import com.tapjoy.internal.ct;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import android.os.Looper;
import com.moat.analytics.mobile.tjy.MoatAdEventType;
import com.moat.analytics.mobile.tjy.ReactiveVideoTrackerPlugin;
import android.app.Activity;
import com.moat.analytics.mobile.tjy.MoatFactory;
import android.content.pm.PackageInfo;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import android.util.Pair;
import org.json.JSONException;
import android.content.Intent;
import android.net.Uri;
import android.view.ViewParent;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import org.json.JSONArray;
import android.view.ViewGroup;
import java.util.Map;
import android.content.pm.ApplicationInfo;
import org.json.JSONObject;
import android.os.Handler;
import java.util.HashMap;
import com.moat.analytics.mobile.tjy.ReactiveVideoTracker;
import android.view.View;
import android.app.ProgressDialog;
import android.webkit.WebView;
import android.content.Context;
import java.util.concurrent.ConcurrentLinkedQueue;
import android.annotation.SuppressLint;

@SuppressLint({ "SetJavaScriptEnabled" })
public class TJAdUnitJSBridge implements TJWebViewJSInterfaceListener
{
    final ConcurrentLinkedQueue a;
    public boolean allowRedirect;
    private TJWebViewJSInterface b;
    private TJAdUnitJSBridge c;
    public boolean closeRequested;
    public boolean customClose;
    private Context d;
    public boolean didLaunchOtherActivity;
    private TJAdUnitActivity e;
    private TJAdUnit f;
    private WebView g;
    private TJSplitWebView h;
    private ProgressDialog i;
    private View j;
    private boolean k;
    private ReactiveVideoTracker l;
    private HashMap m;
    private Handler n;
    public String otherActivityCallbackID;
    public String splitWebViewCallbackID;
    
    public TJAdUnitJSBridge(final Context d, final WebView g) {
        this.j = null;
        this.didLaunchOtherActivity = false;
        this.allowRedirect = true;
        this.otherActivityCallbackID = null;
        this.customClose = false;
        this.closeRequested = false;
        this.splitWebViewCallbackID = null;
        this.a = new ConcurrentLinkedQueue();
        TapjoyLog.i("TJAdUnitJSBridge", "creating AdUnit/JS Bridge");
        this.d = d;
        this.g = g;
        this.c = this;
        if (this.g == null) {
            TapjoyLog.e("TJAdUnitJSBridge", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Cannot create AdUnitJSBridge -- webview is NULL"));
            return;
        }
        this.b = new TJWebViewJSInterface(this.g, this);
        this.g.addJavascriptInterface((Object)this.b, "AndroidJavascriptInterface");
        this.setEnabled(true);
    }
    
    public TJAdUnitJSBridge(final Context context, final TJAdUnit f) {
        this(context, f.getWebView());
        this.f = f;
    }
    
    public void alert(final JSONObject p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: new             Ljava/lang/StringBuilder;
        //     5: dup            
        //     6: ldc             "alert_method: "
        //     8: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    11: aload_1        
        //    12: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    15: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    18: invokestatic    com/tapjoy/TapjoyLog.d:(Ljava/lang/String;Ljava/lang/String;)V
        //    21: ldc             ""
        //    23: astore          6
        //    25: aload_1        
        //    26: ldc             "title"
        //    28: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    31: astore          5
        //    33: aload           5
        //    35: astore          6
        //    37: aload_1        
        //    38: ldc             "message"
        //    40: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    43: astore          7
        //    45: aload           7
        //    47: astore          6
        //    49: aload_1        
        //    50: ldc             "buttons"
        //    52: invokevirtual   org/json/JSONObject.getJSONArray:(Ljava/lang/String;)Lorg/json/JSONArray;
        //    55: astore_1       
        //    56: aload_0        
        //    57: getfield        com/tapjoy/TJAdUnitJSBridge.e:Lcom/tapjoy/TJAdUnitActivity;
        //    60: astore          7
        //    62: aload           7
        //    64: ifnull          288
        //    67: new             Landroid/app/AlertDialog$Builder;
        //    70: dup            
        //    71: aload           7
        //    73: invokespecial   android/app/AlertDialog$Builder.<init>:(Landroid/content/Context;)V
        //    76: aload           5
        //    78: invokevirtual   android/app/AlertDialog$Builder.setTitle:(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
        //    81: aload           6
        //    83: invokevirtual   android/app/AlertDialog$Builder.setMessage:(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
        //    86: invokevirtual   android/app/AlertDialog$Builder.create:()Landroid/app/AlertDialog;
        //    89: astore          5
        //    91: aload_1        
        //    92: ifnull          102
        //    95: aload_1        
        //    96: invokevirtual   org/json/JSONArray.length:()I
        //    99: ifne            155
        //   102: aload_0        
        //   103: aload_2        
        //   104: iconst_1       
        //   105: anewarray       Ljava/lang/Object;
        //   108: dup            
        //   109: iconst_0       
        //   110: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   113: aastore        
        //   114: invokevirtual   com/tapjoy/TJAdUnitJSBridge.invokeJSCallback:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   117: return         
        //   118: astore_1       
        //   119: ldc             ""
        //   121: astore          7
        //   123: aload           6
        //   125: astore          5
        //   127: aload           7
        //   129: astore          6
        //   131: aload_0        
        //   132: aload_2        
        //   133: iconst_1       
        //   134: anewarray       Ljava/lang/Object;
        //   137: dup            
        //   138: iconst_0       
        //   139: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   142: aastore        
        //   143: invokevirtual   com/tapjoy/TJAdUnitJSBridge.invokeJSCallback:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   146: aload_1        
        //   147: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   150: aconst_null    
        //   151: astore_1       
        //   152: goto            56
        //   155: new             Ljava/util/ArrayList;
        //   158: dup            
        //   159: invokespecial   java/util/ArrayList.<init>:()V
        //   162: astore          6
        //   164: iconst_0       
        //   165: istore          4
        //   167: iload           4
        //   169: aload_1        
        //   170: invokevirtual   org/json/JSONArray.length:()I
        //   173: if_icmpge       270
        //   176: iload           4
        //   178: tableswitch {
        //                0: 248
        //                1: 254
        //          default: 200
        //        }
        //   200: iconst_m1      
        //   201: istore_3       
        //   202: aload           6
        //   204: aload_1        
        //   205: iload           4
        //   207: invokevirtual   org/json/JSONArray.getString:(I)Ljava/lang/String;
        //   210: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   213: pop            
        //   214: aload           5
        //   216: iload_3        
        //   217: aload           6
        //   219: iload           4
        //   221: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   224: checkcast       Ljava/lang/CharSequence;
        //   227: new             Lcom/tapjoy/TJAdUnitJSBridge$1;
        //   230: dup            
        //   231: aload_0        
        //   232: aload_2        
        //   233: invokespecial   com/tapjoy/TJAdUnitJSBridge$1.<init>:(Lcom/tapjoy/TJAdUnitJSBridge;Ljava/lang/String;)V
        //   236: invokevirtual   android/app/AlertDialog.setButton:(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V
        //   239: iload           4
        //   241: iconst_1       
        //   242: iadd           
        //   243: istore          4
        //   245: goto            167
        //   248: bipush          -2
        //   250: istore_3       
        //   251: goto            202
        //   254: bipush          -3
        //   256: istore_3       
        //   257: goto            202
        //   260: astore          7
        //   262: aload           7
        //   264: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   267: goto            214
        //   270: aload           5
        //   272: iconst_0       
        //   273: invokevirtual   android/app/AlertDialog.setCancelable:(Z)V
        //   276: aload           5
        //   278: iconst_0       
        //   279: invokevirtual   android/app/AlertDialog.setCanceledOnTouchOutside:(Z)V
        //   282: aload           5
        //   284: invokevirtual   android/app/AlertDialog.show:()V
        //   287: return         
        //   288: ldc             "TJAdUnitJSBridge"
        //   290: ldc_w           "Cannot alert -- TJAdUnitActivity is null"
        //   293: invokestatic    com/tapjoy/TapjoyLog.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   296: return         
        //   297: astore_1       
        //   298: goto            131
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  25     33     118    131    Ljava/lang/Exception;
        //  37     45     118    131    Ljava/lang/Exception;
        //  49     56     297    301    Ljava/lang/Exception;
        //  202    214    260    270    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0056:
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
    
    public void attachVolumeListener(final JSONObject jsonObject, final String s) {
        try {
            final boolean boolean1 = jsonObject.getBoolean("attach");
            final int optInt = jsonObject.optInt("interval", 500);
            if (optInt > 0) {
                this.f.attachVolumeListener(boolean1, optInt);
                this.invokeJSCallback(s, true);
                return;
            }
            TapjoyLog.d("TJAdUnitJSBridge", "Invalid `interval` value passed to attachVolumeListener(): interval=" + optInt);
            this.invokeJSCallback(s, false);
        }
        catch (Exception ex) {
            TapjoyLog.d("TJAdUnitJSBridge", "attachVolumeListener exception " + ex.toString());
            this.invokeJSCallback(s, false);
            ex.printStackTrace();
        }
    }
    
    public void cacheAsset(final JSONObject p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: astore          5
        //     4: lconst_0       
        //     5: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //     8: astore          6
        //    10: aload_1        
        //    11: ldc_w           "url"
        //    14: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    17: astore          8
        //    19: aload_1        
        //    20: ldc_w           "offerId"
        //    23: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    26: astore          7
        //    28: aload           7
        //    30: astore          5
        //    32: aload_1        
        //    33: ldc_w           "timeToLive"
        //    36: invokevirtual   org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //    39: lstore_3       
        //    40: lload_3        
        //    41: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    44: astore_1       
        //    45: invokestatic    com/tapjoy/TapjoyCache.getInstance:()Lcom/tapjoy/TapjoyCache;
        //    48: ifnull          103
        //    51: aload_0        
        //    52: aload_2        
        //    53: iconst_1       
        //    54: anewarray       Ljava/lang/Object;
        //    57: dup            
        //    58: iconst_0       
        //    59: invokestatic    com/tapjoy/TapjoyCache.getInstance:()Lcom/tapjoy/TapjoyCache;
        //    62: aload           8
        //    64: aload           5
        //    66: aload_1        
        //    67: invokevirtual   java/lang/Long.longValue:()J
        //    70: invokevirtual   com/tapjoy/TapjoyCache.cacheAssetFromURL:(Ljava/lang/String;Ljava/lang/String;J)Ljava/util/concurrent/Future;
        //    73: aastore        
        //    74: invokevirtual   com/tapjoy/TJAdUnitJSBridge.invokeJSCallback:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    77: return         
        //    78: astore_1       
        //    79: ldc             "TJAdUnitJSBridge"
        //    81: ldc_w           "Unable to cache video. Invalid parameters."
        //    84: invokestatic    com/tapjoy/TapjoyLog.w:(Ljava/lang/String;Ljava/lang/String;)V
        //    87: aload_0        
        //    88: aload_2        
        //    89: iconst_1       
        //    90: anewarray       Ljava/lang/Object;
        //    93: dup            
        //    94: iconst_0       
        //    95: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //    98: aastore        
        //    99: invokevirtual   com/tapjoy/TJAdUnitJSBridge.invokeJSCallback:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   102: return         
        //   103: aload_0        
        //   104: aload_2        
        //   105: iconst_1       
        //   106: anewarray       Ljava/lang/Object;
        //   109: dup            
        //   110: iconst_0       
        //   111: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   114: aastore        
        //   115: invokevirtual   com/tapjoy/TJAdUnitJSBridge.invokeJSCallback:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   118: return         
        //   119: astore_1       
        //   120: aload           6
        //   122: astore_1       
        //   123: goto            45
        //   126: astore          7
        //   128: goto            32
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     19     78     103    Ljava/lang/Exception;
        //  19     28     126    131    Ljava/lang/Exception;
        //  32     40     119    126    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0032:
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
    
    public void cachePathForURL(final JSONObject jsonObject, final String s) {
        try {
            final String string = jsonObject.getString("url");
            if (TapjoyCache.getInstance() != null) {
                this.invokeJSCallback(s, TapjoyCache.getInstance().getPathOfCachedURL(string));
                return;
            }
        }
        catch (Exception ex) {
            this.invokeJSCallback(s, "");
            return;
        }
        this.invokeJSCallback(s, "");
    }
    
    public void checkAppInstalled(final JSONObject jsonObject, final String s) {
        Object iterator = "";
    Label_0093:
        while (true) {
            try {
                final Object string = jsonObject.getString("bundle");
                if (string != null && ((String)string).length() > 0) {
                    iterator = this.d.getPackageManager().getInstalledApplications(0).iterator();
                    Block_6: {
                        while (((Iterator)iterator).hasNext()) {
                            if (((Iterator<ApplicationInfo>)iterator).next().packageName.equals(string)) {
                                break Block_6;
                            }
                        }
                        break Label_0093;
                    }
                    this.invokeJSCallback(s, Boolean.TRUE);
                    return;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                final Object string = iterator;
                continue;
            }
            break;
        }
        this.invokeJSCallback(s, Boolean.FALSE);
    }
    
    public void clearCache(final JSONObject jsonObject, final String s) {
        if (TapjoyCache.getInstance() != null) {
            TapjoyCache.getInstance().clearTapjoyCache();
            this.invokeJSCallback(s, Boolean.TRUE);
            return;
        }
        this.invokeJSCallback(s, Boolean.FALSE);
    }
    
    public void clearLoggingLevel(final JSONObject jsonObject, final String s) {
        TapjoyAppSettings.getInstance().clearLoggingLevel();
    }
    
    public void clearVideo(final JSONObject jsonObject, final String s) {
        if (this.f != null) {
            this.f.clearVideo(new AdUnitAsyncTaskListner() {
                @Override
                public final void onComplete(final boolean b) {
                    TJAdUnitJSBridge.this.invokeJSCallback(s, b);
                }
            });
        }
    }
    
    public void closeRequested(final Boolean b) {
        this.closeRequested = true;
        final HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
        hashMap.put("forceClose", b);
        this.invokeJSAdunitMethod("closeRequested", hashMap);
    }
    
    public void contentReady(final JSONObject jsonObject, final String s) {
        if (this.f != null) {
            this.f.fireContentReady();
            this.invokeJSCallback(s, true);
            return;
        }
        this.invokeJSCallback(s, false);
    }
    
    public void destroy() {
    }
    
    public void dismiss(final JSONObject jsonObject, final String s) {
        final TJAdUnitActivity e = this.e;
        if (e != null) {
            this.invokeJSCallback(s, true);
            e.finish();
            return;
        }
        TapjoyLog.d("TJAdUnitJSBridge", "Cannot dismiss -- TJAdUnitActivity is null");
        this.invokeJSCallback(s, false);
    }
    
    public void dismissSplitView(final JSONObject jsonObject, final String s) {
        TapjoyUtil.runOnMainThread(new Runnable() {
            @Override
            public final void run() {
                if (TJAdUnitJSBridge.this.h != null) {
                    if (s != null) {
                        TJAdUnitJSBridge.this.invokeJSCallback(s, Boolean.TRUE);
                    }
                    if (TJAdUnitJSBridge.this.splitWebViewCallbackID != null) {
                        TJAdUnitJSBridge.this.invokeJSCallback(TJAdUnitJSBridge.this.splitWebViewCallbackID, Boolean.TRUE);
                        TJAdUnitJSBridge.this.splitWebViewCallbackID = null;
                    }
                    ((ViewGroup)TJAdUnitJSBridge.this.h.getParent()).removeView((View)TJAdUnitJSBridge.this.h);
                    TJAdUnitJSBridge.this.h = null;
                }
                else if (s != null) {
                    TJAdUnitJSBridge.this.invokeJSCallback(s, Boolean.FALSE);
                }
            }
        });
    }
    
    public void display() {
        this.invokeJSAdunitMethod("display", new Object[0]);
    }
    
    public void displayRichMedia(final JSONObject p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc_w           "inline"
        //     4: invokevirtual   org/json/JSONObject.getBoolean:(Ljava/lang/String;)Z
        //     7: istore_3       
        //     8: aload_1        
        //     9: ldc_w           "html"
        //    12: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    15: astore          4
        //    17: aload           4
        //    19: ifnonnull       58
        //    22: aload_0        
        //    23: aload_2        
        //    24: iconst_1       
        //    25: anewarray       Ljava/lang/Object;
        //    28: dup            
        //    29: iconst_0       
        //    30: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //    33: aastore        
        //    34: invokevirtual   com/tapjoy/TJAdUnitJSBridge.invokeJSCallback:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    37: return         
        //    38: astore          4
        //    40: iconst_0       
        //    41: istore_3       
        //    42: goto            8
        //    45: astore          4
        //    47: aload           4
        //    49: invokevirtual   java/lang/Exception.printStackTrace:()V
        //    52: aconst_null    
        //    53: astore          4
        //    55: goto            17
        //    58: iload_3        
        //    59: ifeq            82
        //    62: aload_0        
        //    63: getfield        com/tapjoy/TJAdUnitJSBridge.d:Landroid/content/Context;
        //    66: checkcast       Landroid/app/Activity;
        //    69: new             Lcom/tapjoy/TJAdUnitJSBridge$8;
        //    72: dup            
        //    73: aload_0        
        //    74: aload_1        
        //    75: invokespecial   com/tapjoy/TJAdUnitJSBridge$8.<init>:(Lcom/tapjoy/TJAdUnitJSBridge;Lorg/json/JSONObject;)V
        //    78: invokevirtual   android/app/Activity.runOnUiThread:(Ljava/lang/Runnable;)V
        //    81: return         
        //    82: new             Lcom/tapjoy/TJPlacementData;
        //    85: dup            
        //    86: invokestatic    com/tapjoy/TapjoyConnectCore.getHostURL:()Ljava/lang/String;
        //    89: aload           4
        //    91: aload_2        
        //    92: invokespecial   com/tapjoy/TJPlacementData.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    95: astore_1       
        //    96: aload_0        
        //    97: getfield        com/tapjoy/TJAdUnitJSBridge.e:Lcom/tapjoy/TJAdUnitActivity;
        //   100: astore_2       
        //   101: aload_2        
        //   102: ifnull          37
        //   105: new             Landroid/content/Intent;
        //   108: dup            
        //   109: aload_2        
        //   110: ldc_w           Lcom/tapjoy/TJAdUnitActivity;.class
        //   113: invokespecial   android/content/Intent.<init>:(Landroid/content/Context;Ljava/lang/Class;)V
        //   116: astore          4
        //   118: aload           4
        //   120: ldc_w           "placement_data"
        //   123: aload_1        
        //   124: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
        //   127: pop            
        //   128: aload_2        
        //   129: aload           4
        //   131: sipush          327
        //   134: invokevirtual   com/tapjoy/TJAdUnitActivity.startActivityForResult:(Landroid/content/Intent;I)V
        //   137: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      8      38     45     Ljava/lang/Exception;
        //  8      17     45     58     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0008:
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
    
    public void displayStoreURL(final JSONObject jsonObject, final String s) {
        this.displayURL(jsonObject, s);
    }
    
    public void displayURL(final JSONObject jsonObject, final String otherActivityCallbackID) {
        try {
            final String optString = jsonObject.optString("style");
            final String string = jsonObject.getString("url");
            final JSONObject optJSONObject = jsonObject.optJSONObject("splitViewLayout");
            final JSONArray optJSONArray = jsonObject.optJSONArray("splitViewExitHosts");
            if ("split".equals(optString)) {
                TapjoyUtil.runOnMainThread(new Runnable() {
                    @Override
                    public final void run() {
                        if (TJAdUnitJSBridge.this.g != null) {
                            if (TJAdUnitJSBridge.this.h == null) {
                                final ViewParent parent = TJAdUnitJSBridge.this.g.getParent();
                                if (parent instanceof ViewGroup) {
                                    final ViewGroup viewGroup = (ViewGroup)parent;
                                    TJAdUnitJSBridge.this.h = new TJSplitWebView(TJAdUnitJSBridge.this.d, optJSONObject, optJSONArray, TJAdUnitJSBridge.this);
                                    viewGroup.addView((View)TJAdUnitJSBridge.this.h, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
                                }
                            }
                            else {
                                TJAdUnitJSBridge.this.h.setExitHosts(optJSONArray);
                                TJAdUnitJSBridge.this.h.applyLayoutOption(optJSONObject);
                            }
                            if (TJAdUnitJSBridge.this.h != null) {
                                TJAdUnitJSBridge.this.splitWebViewCallbackID = otherActivityCallbackID;
                                TJAdUnitJSBridge.this.h.loadUrl(string);
                                return;
                            }
                        }
                        TJAdUnitJSBridge.this.h = null;
                        TJAdUnitJSBridge.this.splitWebViewCallbackID = null;
                        TJAdUnitJSBridge.this.invokeJSCallback(otherActivityCallbackID, Boolean.FALSE);
                    }
                });
                return;
            }
            this.didLaunchOtherActivity = true;
            this.otherActivityCallbackID = otherActivityCallbackID;
            this.d.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(string)));
        }
        catch (Exception ex) {
            this.invokeJSCallback(otherActivityCallbackID, Boolean.TRUE);
            ex.printStackTrace();
        }
    }
    
    public void displayVideo(final JSONObject jsonObject, final String s) {
        try {
            final String string = jsonObject.getString("url");
            if (string.length() > 0 && string != "") {
                this.f.loadVideoUrl(string, new AdUnitAsyncTaskListner() {
                    @Override
                    public final void onComplete(final boolean b) {
                        TJAdUnitJSBridge.this.invokeJSCallback(s, b);
                    }
                });
                return;
            }
            this.invokeJSCallback(s, Boolean.FALSE);
        }
        catch (Exception ex) {
            this.invokeJSCallback(s, Boolean.FALSE);
            ex.printStackTrace();
        }
    }
    
    public void endUsageTrackingEvent(final JSONObject jsonObject, final String s) {
        try {
            final String string = jsonObject.getString("name");
            if (string.isEmpty()) {
                TapjoyLog.d("TJAdUnitJSBridge", "Empty name for endUsageTrackingEvent");
                this.invokeJSCallback(s, false);
                return;
            }
            if (this.f != null) {
                this.f.endAdContentTracking(string, jsonObject);
                this.invokeJSCallback(s, true);
                return;
            }
        }
        catch (JSONException ex) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to endUsageTrackingEvent. Invalid parameters: " + ex);
        }
        this.invokeJSCallback(s, false);
    }
    
    public void flushBacklogMessageQueue() {
        while (true) {
            final Pair pair = this.a.poll();
            if (pair == null) {
                break;
            }
            this.onDispatchMethod((String)pair.first, (JSONObject)pair.second);
        }
    }
    
    public void flushMessageQueue() {
        this.b.flushMessageQueue();
    }
    
    public void getCachedAssets(final JSONObject jsonObject, final String s) {
        if (TapjoyCache.getInstance() != null) {
            this.invokeJSCallback(s, TapjoyCache.getInstance().cachedAssetsToJSON());
            return;
        }
        this.invokeJSCallback(s, "");
    }
    
    public void getInstalledAppData(JSONObject packageManager, final String s) {
        packageManager = (JSONObject)this.d.getPackageManager();
        final List installedApplications = ((PackageManager)packageManager).getInstalledApplications(0);
        final JSONArray jsonArray = new JSONArray();
        final Iterator<ApplicationInfo> iterator = installedApplications.iterator();
        while (true) {
            Label_0210: {
                if (!iterator.hasNext()) {
                    break Label_0210;
                }
                final ApplicationInfo applicationInfo = iterator.next();
                if ((applicationInfo.flags & 0x1) == 0x1) {
                    continue;
                }
                final HashMap<String, Date> hashMap = new HashMap<String, Date>();
                final String packageName = applicationInfo.packageName;
                hashMap.put("packageName", (Date)packageName);
                hashMap.put("targetSdk", (Date)applicationInfo.targetSdkVersion);
                try {
                    final PackageInfo packageInfo = ((PackageManager)packageManager).getPackageInfo(packageName, 4096);
                    hashMap.put("installTime", new Date(packageInfo.firstInstallTime));
                    hashMap.put("updateTime", new Date(packageInfo.lastUpdateTime));
                    hashMap.put("versionName", (Date)packageInfo.versionName);
                    hashMap.put("versionCode", (Date)packageInfo.versionCode);
                    jsonArray.put((Object)new JSONObject((Map)hashMap));
                    continue;
                    this.invokeJSCallback(s, jsonArray);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void getSplitViewURL(final JSONObject jsonObject, final String s) {
        TapjoyUtil.runOnMainThread(new Runnable() {
            @Override
            public final void run() {
                if (TJAdUnitJSBridge.this.h != null) {
                    TJAdUnitJSBridge.this.invokeJSCallback(s, TJAdUnitJSBridge.this.h.getLastUrl());
                    return;
                }
                TJAdUnitJSBridge.this.invokeJSCallback(s, JSONObject.NULL);
            }
        });
    }
    
    public void getVolume(final JSONObject jsonObject, final String s) {
        final HashMap volumeArgs = this.getVolumeArgs();
        if (volumeArgs != null) {
            this.invokeJSCallback(s, volumeArgs);
            return;
        }
        this.invokeJSCallback(s, false);
    }
    
    public HashMap getVolumeArgs() {
        if (this.f == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
            return null;
        }
        final String volume = this.f.getVolume();
        final boolean muted = this.f.isMuted();
        TapjoyLog.d("TJAdUnitJSBridge", "getVolumeArgs: volume=" + volume + "; isMuted=" + muted);
        final HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
        hashMap.put("currentVolume", (Boolean)volume);
        hashMap.put("isMuted", muted);
        return hashMap;
    }
    
    public void hasSplitView(final JSONObject jsonObject, final String s) {
        TapjoyUtil.runOnMainThread(new Runnable() {
            @Override
            public final void run() {
                if (TJAdUnitJSBridge.this.h != null) {
                    TJAdUnitJSBridge.this.invokeJSCallback(s, Boolean.TRUE);
                    return;
                }
                TJAdUnitJSBridge.this.invokeJSCallback(s, Boolean.FALSE);
            }
        });
    }
    
    public void initMoatVideoTracker(final JSONObject jsonObject, final String s) {
        final TJAdUnitActivity e = this.e;
        if (e == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "Error from initMoatVideoTracker -- TJAdUnitActivity is null");
            this.invokeJSCallback(s, false);
            return;
        }
        try {
            this.l = (ReactiveVideoTracker)MoatFactory.create(e).createCustomTracker(new ReactiveVideoTrackerPlugin(jsonObject.getString("partnerCode")));
            if (this.m == null) {
                TapjoyLog.d("TJAdUnitJSBridge", "Initializing MOAT tracking events map");
                (this.m = new HashMap()).put("firstQuartile", MoatAdEventType.AD_EVT_FIRST_QUARTILE);
                this.m.put("midpoint", MoatAdEventType.AD_EVT_MID_POINT);
                this.m.put("thirdQuartile", MoatAdEventType.AD_EVT_THIRD_QUARTILE);
                this.m.put("complete", MoatAdEventType.AD_EVT_COMPLETE);
                this.m.put("paused", MoatAdEventType.AD_EVT_PAUSED);
                this.m.put("playing", MoatAdEventType.AD_EVT_PLAYING);
                this.m.put("start", MoatAdEventType.AD_EVT_START);
                this.m.put("stopped", MoatAdEventType.AD_EVT_STOPPED);
                this.m.put("skipped", MoatAdEventType.AD_EVT_SKIPPED);
                this.m.put("volumeChanged", MoatAdEventType.AD_EVT_VOLUME_CHANGE);
                this.m.put("enterFullScreen", MoatAdEventType.AD_EVT_ENTER_FULLSCREEN);
                this.m.put("exitFullScreen", MoatAdEventType.AD_EVT_EXIT_FULLSCREEN);
            }
            this.n = new Handler(Looper.getMainLooper());
            this.invokeJSCallback(s, true);
        }
        catch (Exception ex) {
            TapjoyLog.d("TJAdUnitJSBridge", "initMoatVideoTracker exception " + ex.toString());
            this.invokeJSCallback(s, false);
        }
    }
    
    public void invokeJSAdunitMethod(final String s, final Map map) {
        this.b.callback(map, s, null);
    }
    
    public void invokeJSAdunitMethod(final String s, final Object... array) {
        this.b.callback(new ArrayList((Collection<? extends E>)Arrays.asList(array)), s, null);
    }
    
    public void invokeJSCallback(final String s, final Map map) {
        this.b.callback(map, "", s);
    }
    
    public void invokeJSCallback(final String s, final Object... array) {
        if (ct.c(s)) {
            TapjoyLog.d("TJAdUnitJSBridge", "invokeJSCallback -- no callbackID provided");
            return;
        }
        this.b.callback(new ArrayList((Collection<? extends E>)Arrays.asList(array)), "", s);
    }
    
    public void log(final JSONObject jsonObject, final String s) {
        try {
            TapjoyLog.d("TJAdUnitJSBridge", "Logging message=" + jsonObject.getString("message"));
            this.invokeJSCallback(s, Boolean.TRUE);
        }
        catch (Exception ex) {
            this.invokeJSCallback(s, Boolean.FALSE);
            ex.printStackTrace();
        }
    }
    
    @TargetApi(19)
    public void nativeEval(final JSONObject jsonObject, final String s) {
        TapjoyUtil.runOnMainThread(new Runnable() {
            @Override
            public final void run() {
                try {
                    if (Build$VERSION.SDK_INT >= 19) {
                        TJAdUnitJSBridge.this.g.evaluateJavascript(jsonObject.getString("command"), (ValueCallback)null);
                    }
                    else {
                        TJAdUnitJSBridge.this.g.loadUrl("javascript:" + jsonObject.getString("command"));
                    }
                    TJAdUnitJSBridge.this.invokeJSCallback(s, Boolean.TRUE);
                }
                catch (Exception ex) {
                    TJAdUnitJSBridge.this.invokeJSCallback(s, Boolean.FALSE);
                }
            }
        });
    }
    
    @Override
    public void onDispatchMethod(final String s, JSONObject jsonObject) {
        String optString = null;
        if (this.k) {
            try {
                final String s2 = optString = jsonObject.optString("callbackId", (String)null);
                jsonObject = jsonObject.getJSONObject("data");
                optString = s2;
                final Method method = TJAdUnitJSBridge.class.getMethod(s, JSONObject.class, String.class);
                optString = s2;
                TapjoyLog.d("TJAdUnitJSBridge", "Dispatching method: " + method + " with data=" + jsonObject + "; callbackID=" + s2);
                optString = s2;
                method.invoke(this.c, jsonObject, s2);
                return;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.invokeJSCallback(optString, Boolean.FALSE);
                return;
            }
        }
        TapjoyLog.d("TJAdUnitJSBridge", "Bridge currently disabled. Adding " + s + " to message queue");
        this.a.add(new Pair((Object)s, (Object)jsonObject));
    }
    
    public void onVideoCompletion() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("videoEventName", "videoComplete");
        this.invokeJSAdunitMethod("videoEvent", hashMap);
    }
    
    public void onVideoError(final String s) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("videoEventName", "videoError");
        hashMap.put("error", s);
        this.invokeJSAdunitMethod("videoEvent", hashMap);
    }
    
    public void onVideoInfo(final String s) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("videoEventName", "videoInfo");
        hashMap.put("info", s);
        this.invokeJSAdunitMethod("videoEvent", hashMap);
    }
    
    public void onVideoPaused(final int n) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("videoEventName", "videoPause");
        hashMap.put("currentTime", (String)n);
        this.invokeJSAdunitMethod("videoEvent", hashMap);
    }
    
    public void onVideoProgress(final int n) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("videoEventName", "videoProgress");
        hashMap.put("currentTime", (String)n);
        this.invokeJSAdunitMethod("videoEvent", hashMap);
    }
    
    public void onVideoReady(final int n, final int n2, final int n3) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("videoEventName", "videoReady");
        hashMap.put("videoDuration", (String)n);
        hashMap.put("videoWidth", (String)n2);
        hashMap.put("videoHeight", (String)n3);
        this.invokeJSAdunitMethod("videoEvent", hashMap);
    }
    
    public void onVideoStarted(final int n) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("videoEventName", "videoStart");
        hashMap.put("currentTime", (String)n);
        this.invokeJSAdunitMethod("videoEvent", hashMap);
    }
    
    public void onVolumeChanged() {
        this.invokeJSAdunitMethod("volumeChanged", this.getVolumeArgs());
    }
    
    public void openApp(final JSONObject jsonObject, final String s) {
        try {
            this.d.startActivity(this.d.getPackageManager().getLaunchIntentForPackage(jsonObject.getString("bundle")));
            this.invokeJSCallback(s, Boolean.TRUE);
        }
        catch (Exception ex) {
            this.invokeJSCallback(s, Boolean.FALSE);
            ex.printStackTrace();
        }
    }
    
    public void pauseVideo(final JSONObject jsonObject, final String s) {
        if (this.f != null) {
            this.invokeJSCallback(s, this.f.pauseVideo());
        }
    }
    
    public void playVideo(final JSONObject jsonObject, final String s) {
        if (this.f != null) {
            this.invokeJSCallback(s, this.f.playVideo());
        }
    }
    
    public void present(final JSONObject p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //     4: astore_3       
        //     5: aload_1        
        //     6: ldc_w           "visible"
        //     9: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    12: invokestatic    java/lang/Boolean.valueOf:(Ljava/lang/String;)Ljava/lang/Boolean;
        //    15: astore          5
        //    17: aload_1        
        //    18: ldc_w           "transparent"
        //    21: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    24: invokestatic    java/lang/Boolean.valueOf:(Ljava/lang/String;)Ljava/lang/Boolean;
        //    27: astore          4
        //    29: aload           4
        //    31: astore_3       
        //    32: aload_0        
        //    33: aload_1        
        //    34: ldc_w           "customClose"
        //    37: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    40: invokestatic    java/lang/Boolean.valueOf:(Ljava/lang/String;)Ljava/lang/Boolean;
        //    43: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    46: putfield        com/tapjoy/TJAdUnitJSBridge.customClose:Z
        //    49: new             Lcom/tapjoy/TJAdUnitJSBridge$a;
        //    52: dup            
        //    53: aload_0        
        //    54: aload_0        
        //    55: getfield        com/tapjoy/TJAdUnitJSBridge.g:Landroid/webkit/WebView;
        //    58: invokespecial   com/tapjoy/TJAdUnitJSBridge$a.<init>:(Lcom/tapjoy/TJAdUnitJSBridge;Landroid/webkit/WebView;)V
        //    61: iconst_2       
        //    62: anewarray       Ljava/lang/Boolean;
        //    65: dup            
        //    66: iconst_0       
        //    67: aload           5
        //    69: aastore        
        //    70: dup            
        //    71: iconst_1       
        //    72: aload_3        
        //    73: aastore        
        //    74: invokevirtual   com/tapjoy/TJAdUnitJSBridge$a.execute:([Ljava/lang/Object;)Landroid/os/AsyncTask;
        //    77: pop            
        //    78: aload_0        
        //    79: aload_2        
        //    80: iconst_1       
        //    81: anewarray       Ljava/lang/Object;
        //    84: dup            
        //    85: iconst_0       
        //    86: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //    89: aastore        
        //    90: invokevirtual   com/tapjoy/TJAdUnitJSBridge.invokeJSCallback:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    93: return         
        //    94: astore_1       
        //    95: aload_0        
        //    96: aload_2        
        //    97: iconst_1       
        //    98: anewarray       Ljava/lang/Object;
        //   101: dup            
        //   102: iconst_0       
        //   103: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   106: aastore        
        //   107: invokevirtual   com/tapjoy/TJAdUnitJSBridge.invokeJSCallback:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   110: aload_1        
        //   111: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   114: return         
        //   115: astore_1       
        //   116: goto            49
        //   119: astore          4
        //   121: goto            32
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      17     94     115    Ljava/lang/Exception;
        //  17     29     119    124    Ljava/lang/Exception;
        //  32     49     115    119    Ljava/lang/Exception;
        //  49     93     94     115    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0032:
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
    
    public void removeAssetFromCache(final JSONObject jsonObject, final String s) {
        try {
            final String string = jsonObject.getString("url");
            if (TapjoyCache.getInstance() != null) {
                this.invokeJSCallback(s, TapjoyCache.getInstance().removeAssetFromCache(string));
                return;
            }
        }
        catch (Exception ex) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
            this.invokeJSCallback(s, Boolean.FALSE);
            return;
        }
        this.invokeJSCallback(s, Boolean.FALSE);
    }
    
    public void sendUsageTrackingEvent(final JSONObject jsonObject, final String s) {
        try {
            final String string = jsonObject.getString("name");
            if (string.isEmpty()) {
                TapjoyLog.d("TJAdUnitJSBridge", "Empty name for sendUsageTrackingEvent");
                this.invokeJSCallback(s, false);
                return;
            }
            if (this.f != null) {
                this.f.sendAdContentTracking(string, jsonObject);
                this.invokeJSCallback(s, true);
                return;
            }
        }
        catch (JSONException ex) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to sendUsageTrackingEvent. Invalid parameters: " + ex);
        }
        this.invokeJSCallback(s, false);
    }
    
    public void setAdUnitActivity(final TJAdUnitActivity e) {
        this.e = e;
    }
    
    public void setAllowRedirect(final JSONObject jsonObject, final String s) {
        while (true) {
            try {
                final boolean boolean1 = jsonObject.getBoolean("enabled");
                this.allowRedirect = boolean1;
                this.invokeJSCallback(s, Boolean.TRUE);
            }
            catch (Exception ex) {
                final boolean boolean1 = true;
                continue;
            }
            break;
        }
    }
    
    public void setBackgroundColor(final JSONObject jsonObject, final String s) {
        try {
            final String string = jsonObject.getString("backgroundColor");
            if (this.f != null) {
                this.f.setBackgroundColor(string, new AdUnitAsyncTaskListner() {
                    @Override
                    public final void onComplete(final boolean b) {
                        TJAdUnitJSBridge.this.invokeJSCallback(s, b);
                    }
                });
                return;
            }
        }
        catch (Exception ex) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background color. Invalid parameters.");
            this.invokeJSCallback(s, false);
            return;
        }
        this.invokeJSCallback(s, false);
    }
    
    public void setBackgroundWebViewContent(final JSONObject jsonObject, final String s) {
        TapjoyLog.d("TJAdUnitJSBridge", "setBackgroundWebViewContent");
        try {
            final String string = jsonObject.getString("backgroundContent");
            if (this.f != null) {
                this.f.setBackgroundContent(string, new AdUnitAsyncTaskListner() {
                    @Override
                    public final void onComplete(final boolean b) {
                        TJAdUnitJSBridge.this.invokeJSCallback(s, b);
                    }
                });
                return;
            }
        }
        catch (Exception ex) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background content. Invalid parameters.");
            this.invokeJSCallback(s, false);
            return;
        }
        this.invokeJSCallback(s, false);
    }
    
    public void setCloseButtonClickable(final JSONObject jsonObject, final String s) {
        try {
            TapjoyUtil.runOnMainThread(new Runnable() {
                final /* synthetic */ boolean a = jsonObject.optBoolean("clickable");
                
                @Override
                public final void run() {
                    final TJAdUnitActivity e = TJAdUnitJSBridge.this.e;
                    if (e != null) {
                        e.setCloseButtonClickable(this.a);
                        return;
                    }
                    TapjoyLog.d("TJAdUnitJSBridge", "Cannot setCloseButtonClickable -- TJAdUnitActivity is null");
                }
            });
            this.invokeJSCallback(s, true);
        }
        catch (Exception ex) {
            this.invokeJSCallback(s, false);
            ex.printStackTrace();
        }
    }
    
    public void setCloseButtonVisible(final JSONObject jsonObject, final String s) {
        try {
            TapjoyUtil.runOnMainThread(new Runnable() {
                final /* synthetic */ boolean a = jsonObject.getBoolean("visible");
                
                @Override
                public final void run() {
                    final TJAdUnitActivity e = TJAdUnitJSBridge.this.e;
                    if (e != null) {
                        e.setCloseButtonVisibility(this.a);
                        return;
                    }
                    TapjoyLog.d("TJAdUnitJSBridge", "Cannot setCloseButtonVisible -- TJAdUnitActivity is null");
                }
            });
            this.invokeJSCallback(s, true);
        }
        catch (Exception ex) {
            this.invokeJSCallback(s, false);
            ex.printStackTrace();
        }
    }
    
    public void setEnabled(final boolean k) {
        this.k = k;
        if (this.k) {
            this.flushBacklogMessageQueue();
        }
    }
    
    public void setEventPreloadLimit(final JSONObject jsonObject, final String s) {
        if (TapjoyCache.getInstance() != null) {
            try {
                TJPlacementManager.setCachedPlacementLimit(jsonObject.getInt("eventPreloadLimit"));
                this.invokeJSCallback(s, Boolean.TRUE);
                return;
            }
            catch (Exception ex) {
                TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy cache's event preload limit. Invalid parameters.");
                this.invokeJSCallback(s, Boolean.FALSE);
                return;
            }
        }
        this.invokeJSCallback(s, Boolean.FALSE);
    }
    
    public void setLoggingLevel(final JSONObject jsonObject, final String s) {
        try {
            TapjoyAppSettings.getInstance().saveLoggingLevel(String.valueOf(jsonObject.getString("loggingLevel")));
        }
        catch (Exception ex) {
            TapjoyLog.d("TJAdUnitJSBridge", "setLoggingLevel exception " + ex.getLocalizedMessage());
            this.invokeJSCallback(s, false);
            ex.printStackTrace();
        }
    }
    
    public void setOrientation(final JSONObject jsonObject, final String s) {
        if (this.f == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
            this.invokeJSCallback(s, false);
            return;
        }
        while (true) {
            while (true) {
                Label_0131: {
                    String string = null;
                    Label_0106: {
                        try {
                            string = jsonObject.getString("orientation");
                            if (!string.equals("landscape") && !string.equals("landscapeLeft")) {
                                break Label_0106;
                            }
                            break Label_0131;
                            final int orientation;
                            this.f.setOrientation(orientation);
                            this.invokeJSCallback(s, true);
                            return;
                        }
                        catch (Exception ex) {
                            this.invokeJSCallback(s, false);
                            return;
                        }
                    }
                    if (string.equals("landscapeRight")) {
                        final int orientation = 8;
                        continue;
                    }
                    final int orientation = 1;
                    continue;
                }
                final int orientation = 0;
                continue;
            }
        }
    }
    
    public void setPrerenderLimit(final JSONObject jsonObject, final String s) {
        try {
            TJPlacementManager.setPreRenderedPlacementLimit(jsonObject.getInt("prerenderLimit"));
            this.invokeJSCallback(s, Boolean.TRUE);
        }
        catch (Exception ex) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy placement pre-render limit. Invalid parameters.");
            this.invokeJSCallback(s, Boolean.FALSE);
        }
    }
    
    public void setSpinnerVisible(final JSONObject jsonObject, final String s) {
        try {
            final boolean boolean1 = jsonObject.getBoolean("visible");
            final String optString = jsonObject.optString("title");
            final String optString2 = jsonObject.optString("message");
            final TJAdUnitActivity e = this.e;
            if (e != null) {
                if (boolean1) {
                    this.i = ProgressDialog.show((Context)e, (CharSequence)optString, (CharSequence)optString2);
                }
                else if (this.i != null) {
                    this.i.dismiss();
                }
                this.invokeJSCallback(s, Boolean.TRUE);
                return;
            }
        }
        catch (Exception ex) {
            this.invokeJSCallback(s, Boolean.FALSE);
            ex.printStackTrace();
            return;
        }
        TapjoyLog.d("TJAdUnitJSBridge", "Cannot setSpinnerVisible -- TJAdUnitActivity is null");
        this.invokeJSCallback(s, Boolean.FALSE);
    }
    
    public void setVideoMute(final JSONObject jsonObject, final String s) {
        try {
            this.f.a(jsonObject.getBoolean("enabled"));
            this.invokeJSCallback(s, Boolean.TRUE);
        }
        catch (JSONException ex) {
            TapjoyLog.d("TJAdUnitJSBridge", "Failed to parse 'enabled' from json params.");
            this.invokeJSCallback(s, Boolean.FALSE);
        }
    }
    
    public void shouldClose(final JSONObject jsonObject, final String s) {
        final TJAdUnitActivity e = this.e;
        while (true) {
            try {
                if (Boolean.valueOf(jsonObject.getString("close")) && e != null) {
                    e.finish();
                }
                this.invokeJSCallback(s, Boolean.TRUE);
                this.closeRequested = false;
            }
            catch (Exception ex) {
                this.invokeJSCallback(s, Boolean.FALSE);
                if (e != null) {
                    e.finish();
                }
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    public void startMoatVideoTracker(JSONObject jsonObject, final String s) {
        int int1;
        HashMap<String, String> hashMap;
        try {
            int1 = jsonObject.getInt("videoLength");
            hashMap = new HashMap<String, String>();
            jsonObject = jsonObject.getJSONObject("adIds");
            if (jsonObject != null) {
                final Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    final String s2 = keys.next();
                    hashMap.put(s2, jsonObject.getString(s2));
                }
            }
        }
        catch (Exception ex) {
            TapjoyLog.d("TJAdUnitJSBridge", "startMoatVideoTracker exception " + ex.toString());
            this.invokeJSCallback(s, false);
            return;
        }
        this.n.post((Runnable)new Runnable() {
            final /* synthetic */ Integer b = int1;
            
            @Override
            public final void run() {
                TJAdUnitJSBridge.this.invokeJSCallback(s, TJAdUnitJSBridge.this.l != null && TJAdUnitJSBridge.this.l.trackVideoAd(hashMap, this.b, (View)TJAdUnitJSBridge.this.g));
            }
        });
    }
    
    public void startUsageTrackingEvent(final JSONObject jsonObject, final String s) {
        try {
            final String string = jsonObject.getString("name");
            if (string.isEmpty()) {
                TapjoyLog.d("TJAdUnitJSBridge", "Empty name for startUsageTrackingEvent");
                this.invokeJSCallback(s, false);
                return;
            }
            if (this.f != null) {
                this.f.startAdContentTracking(string, jsonObject);
                this.invokeJSCallback(s, true);
                return;
            }
        }
        catch (JSONException ex) {
            TapjoyLog.w("TJAdUnitJSBridge", "Unable to startUsageTrackingEvent. Invalid parameters: " + ex);
        }
        this.invokeJSCallback(s, false);
    }
    
    public void triggerEvent(final JSONObject jsonObject, final String s) {
        if (this.f == null) {
            return;
        }
        while (true) {
            String string;
            try {
                string = jsonObject.getString("eventName");
                if (string.equals("start")) {
                    this.f.fireOnVideoStart();
                    return;
                }
            }
            catch (Exception ex) {
                TapjoyLog.w("TJAdUnitJSBridge", "Unable to triggerEvent. No event name.");
                return;
            }
            if (string.equals("complete")) {
                this.f.fireOnVideoComplete();
                return;
            }
            if (string.equals("error")) {
                this.f.fireOnVideoError("Error while trying to play video.");
            }
        }
    }
    
    public void triggerMoatVideoEvent(final JSONObject jsonObject, final String s) {
        try {
            final int int1 = jsonObject.getInt("currentVideoTime");
            final String string = jsonObject.getString("eventName");
            MoatAdEventType moatAdEventType = null;
            if (this.m != null) {
                moatAdEventType = this.m.get(string);
            }
            if (moatAdEventType == null) {
                TapjoyLog.d("TJAdUnitJSBridge", "eventName:" + string + " has no matching MOAT event");
                this.invokeJSCallback(s, false);
                return;
            }
            TapjoyLog.d("TJAdUnitJSBridge", "Sending MOAT event: " + moatAdEventType);
            this.n.post((Runnable)new Runnable() {
                final /* synthetic */ MoatAdEvent a = new MoatAdEvent(moatAdEventType, int1);
                
                @Override
                public final void run() {
                    if (TJAdUnitJSBridge.this.l != null) {
                        TJAdUnitJSBridge.this.l.dispatchEvent(this.a);
                    }
                }
            });
            this.invokeJSCallback(s, true);
        }
        catch (Exception ex) {
            TapjoyLog.d("TJAdUnitJSBridge", "triggerMoatVideoEvent exception " + ex.toString());
            this.invokeJSCallback(s, false);
        }
    }
    
    public void unsetOrientation(final JSONObject jsonObject, final String s) {
        if (this.f == null) {
            TapjoyLog.d("TJAdUnitJSBridge", "No ad unit provided");
            this.invokeJSCallback(s, false);
            return;
        }
        try {
            this.f.unsetOrientation();
            this.invokeJSCallback(s, true);
        }
        catch (Exception ex) {
            this.invokeJSCallback(s, false);
        }
    }
    
    public interface AdUnitAsyncTaskListner
    {
        void onComplete(final boolean p0);
    }
    
    @TargetApi(11)
    final class a extends AsyncTask
    {
        WebView a;
        
        public a(final WebView a) {
            this.a = a;
        }
    }
}
