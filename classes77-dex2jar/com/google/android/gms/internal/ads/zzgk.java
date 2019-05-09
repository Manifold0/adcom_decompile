// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;
import java.util.List;
import android.content.Context;
import android.os.PowerManager;
import android.os.Process;
import android.app.ActivityManager$RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.app.ActivityManager;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import android.view.ViewGroup;
import com.google.android.gms.common.util.PlatformVersion;
import android.webkit.WebView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import android.annotation.TargetApi;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
@TargetApi(14)
public final class zzgk extends Thread
{
    private final Object mLock;
    private boolean mStarted;
    private final int zzagx;
    private final int zzagz;
    private boolean zzahy;
    private final zzgf zzahz;
    private final zzadf zzaia;
    private final int zzaib;
    private final int zzaic;
    private final int zzaid;
    private final int zzaie;
    private final int zzaif;
    private final int zzaig;
    private final String zzaih;
    private final boolean zzaii;
    private boolean zzbm;
    
    public zzgk(final zzgf zzahz, final zzadf zzaia) {
        this.mStarted = false;
        this.zzahy = false;
        this.zzbm = false;
        this.zzahz = zzahz;
        this.zzaia = zzaia;
        this.mLock = new Object();
        this.zzagx = (int)zzkb.zzik().zzd(zznk.zzawl);
        this.zzaic = (int)zzkb.zzik().zzd(zznk.zzawm);
        this.zzagz = (int)zzkb.zzik().zzd(zznk.zzawn);
        this.zzaid = (int)zzkb.zzik().zzd(zznk.zzawo);
        this.zzaie = (int)zzkb.zzik().zzd(zznk.zzawr);
        this.zzaif = (int)zzkb.zzik().zzd(zznk.zzawt);
        this.zzaig = (int)zzkb.zzik().zzd(zznk.zzawu);
        this.zzaib = (int)zzkb.zzik().zzd(zznk.zzawp);
        this.zzaih = (String)zzkb.zzik().zzd(zznk.zzaww);
        this.zzaii = (boolean)zzkb.zzik().zzd(zznk.zzawy);
        this.setName("ContentFetchTask");
    }
    
    @VisibleForTesting
    private final zzgo zza(@Nullable final View view, final zzge zzge) {
        int n = 0;
        if (view == null) {
            return new zzgo(this, 0, 0);
        }
        final boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if (view instanceof TextView && !(view instanceof EditText)) {
            final CharSequence text = ((TextView)view).getText();
            if (!TextUtils.isEmpty(text)) {
                zzge.zzb(text.toString(), globalVisibleRect, view.getX(), view.getY(), (float)view.getWidth(), (float)view.getHeight());
                return new zzgo(this, 1, 0);
            }
            return new zzgo(this, 0, 0);
        }
        else if (view instanceof WebView && !(view instanceof zzaqw)) {
            zzge.zzgs();
            final WebView webView = (WebView)view;
            int n2;
            if (!PlatformVersion.isAtLeastKitKat()) {
                n2 = 0;
            }
            else {
                zzge.zzgs();
                webView.post((Runnable)new zzgm(this, zzge, webView, globalVisibleRect));
                n2 = 1;
            }
            if (n2 != 0) {
                return new zzgo(this, 0, 1);
            }
            return new zzgo(this, 0, 0);
        }
        else {
            if (view instanceof ViewGroup) {
                final ViewGroup viewGroup = (ViewGroup)view;
                int i = 0;
                int n3 = 0;
                while (i < viewGroup.getChildCount()) {
                    final zzgo zza = this.zza(viewGroup.getChildAt(i), zzge);
                    n3 += zza.zzaiq;
                    n += zza.zzair;
                    ++i;
                }
                return new zzgo(this, n3, n);
            }
            return new zzgo(this, 0, 0);
        }
    }
    
    @VisibleForTesting
    private static boolean zzgx() {
        while (true) {
            boolean b = false;
            Label_0156: {
                try {
                    final Context context = zzbv.zzen().getContext();
                    if (context == null) {
                        return false;
                    }
                    final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
                    final KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService("keyguard");
                    if (activityManager != null) {
                        if (keyguardManager != null) {
                            final List runningAppProcesses = activityManager.getRunningAppProcesses();
                            if (runningAppProcesses == null) {
                                return false;
                            }
                            for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : runningAppProcesses) {
                                if (Process.myPid() == activityManager$RunningAppProcessInfo.pid) {
                                    if (activityManager$RunningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode()) {
                                        final PowerManager powerManager = (PowerManager)context.getSystemService("power");
                                        b = (powerManager != null && powerManager.isScreenOn());
                                        break Label_0156;
                                    }
                                    break;
                                }
                            }
                            return false;
                        }
                    }
                }
                catch (Throwable t) {
                    zzbv.zzeo().zza(t, "ContentFetchTask.isInForeground");
                    return false;
                }
                return false;
            }
            if (b) {
                return true;
            }
            return false;
        }
    }
    
    private final void zzgz() {
        synchronized (this.mLock) {
            this.zzahy = true;
            zzakb.zzck(new StringBuilder(42).append("ContentFetchThread: paused, mPause = ").append(this.zzahy).toString());
        }
    }
    
    @Override
    public final void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ifeq            204
        //     6: invokestatic    com/google/android/gms/ads/internal/zzbv.zzen:()Lcom/google/android/gms/internal/ads/zzgg;
        //     9: invokevirtual   com/google/android/gms/internal/ads/zzgg.getActivity:()Landroid/app/Activity;
        //    12: astore          4
        //    14: aload           4
        //    16: ifnonnull       77
        //    19: ldc_w           "ContentFetchThread: no activity. Sleeping."
        //    22: invokestatic    com/google/android/gms/internal/ads/zzakb.zzck:(Ljava/lang/String;)V
        //    25: aload_0        
        //    26: invokespecial   com/google/android/gms/internal/ads/zzgk.zzgz:()V
        //    29: aload_0        
        //    30: getfield        com/google/android/gms/internal/ads/zzgk.zzaib:I
        //    33: sipush          1000
        //    36: imul           
        //    37: i2l            
        //    38: invokestatic    java/lang/Thread.sleep:(J)V
        //    41: aload_0        
        //    42: getfield        com/google/android/gms/internal/ads/zzgk.mLock:Ljava/lang/Object;
        //    45: astore_2       
        //    46: aload_2        
        //    47: monitorenter   
        //    48: aload_0        
        //    49: getfield        com/google/android/gms/internal/ads/zzgk.zzahy:Z
        //    52: istore_1       
        //    53: iload_1        
        //    54: ifeq            217
        //    57: ldc_w           "ContentFetchTask: waiting"
        //    60: invokestatic    com/google/android/gms/internal/ads/zzakb.zzck:(Ljava/lang/String;)V
        //    63: aload_0        
        //    64: getfield        com/google/android/gms/internal/ads/zzgk.mLock:Ljava/lang/Object;
        //    67: invokevirtual   java/lang/Object.wait:()V
        //    70: goto            48
        //    73: astore_3       
        //    74: goto            48
        //    77: aload           4
        //    79: ifnull          29
        //    82: aconst_null    
        //    83: astore_3       
        //    84: aload_3        
        //    85: astore_2       
        //    86: aload           4
        //    88: invokevirtual   android/app/Activity.getWindow:()Landroid/view/Window;
        //    91: ifnull          122
        //    94: aload_3        
        //    95: astore_2       
        //    96: aload           4
        //    98: invokevirtual   android/app/Activity.getWindow:()Landroid/view/Window;
        //   101: invokevirtual   android/view/Window.getDecorView:()Landroid/view/View;
        //   104: ifnull          122
        //   107: aload           4
        //   109: invokevirtual   android/app/Activity.getWindow:()Landroid/view/Window;
        //   112: invokevirtual   android/view/Window.getDecorView:()Landroid/view/View;
        //   115: ldc_w           16908290
        //   118: invokevirtual   android/view/View.findViewById:(I)Landroid/view/View;
        //   121: astore_2       
        //   122: aload_2        
        //   123: ifnull          29
        //   126: aload_2        
        //   127: ifnull          29
        //   130: aload_2        
        //   131: new             Lcom/google/android/gms/internal/ads/zzgl;
        //   134: dup            
        //   135: aload_0        
        //   136: aload_2        
        //   137: invokespecial   com/google/android/gms/internal/ads/zzgl.<init>:(Lcom/google/android/gms/internal/ads/zzgk;Landroid/view/View;)V
        //   140: invokevirtual   android/view/View.post:(Ljava/lang/Runnable;)Z
        //   143: pop            
        //   144: goto            29
        //   147: astore_2       
        //   148: ldc_w           "Error in ContentFetchTask"
        //   151: aload_2        
        //   152: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   155: goto            41
        //   158: astore_2       
        //   159: invokestatic    com/google/android/gms/ads/internal/zzbv.zzeo:()Lcom/google/android/gms/internal/ads/zzajm;
        //   162: aload_2        
        //   163: ldc_w           "ContentFetchTask.extractContent"
        //   166: invokevirtual   com/google/android/gms/internal/ads/zzajm.zza:(Ljava/lang/Throwable;Ljava/lang/String;)V
        //   169: ldc_w           "Failed getting root view of activity. Content not extracted."
        //   172: invokestatic    com/google/android/gms/internal/ads/zzakb.zzck:(Ljava/lang/String;)V
        //   175: aload_3        
        //   176: astore_2       
        //   177: goto            122
        //   180: astore_2       
        //   181: ldc_w           "Error in ContentFetchTask"
        //   184: aload_2        
        //   185: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   188: aload_0        
        //   189: getfield        com/google/android/gms/internal/ads/zzgk.zzaia:Lcom/google/android/gms/internal/ads/zzadf;
        //   192: aload_2        
        //   193: ldc_w           "ContentFetchTask.run"
        //   196: invokeinterface com/google/android/gms/internal/ads/zzadf.zza:(Ljava/lang/Throwable;Ljava/lang/String;)V
        //   201: goto            41
        //   204: ldc_w           "ContentFetchTask: sleeping"
        //   207: invokestatic    com/google/android/gms/internal/ads/zzakb.zzck:(Ljava/lang/String;)V
        //   210: aload_0        
        //   211: invokespecial   com/google/android/gms/internal/ads/zzgk.zzgz:()V
        //   214: goto            29
        //   217: aload_2        
        //   218: monitorexit    
        //   219: goto            0
        //   222: astore_3       
        //   223: aload_2        
        //   224: monitorexit    
        //   225: aload_3        
        //   226: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      14     147    158    Ljava/lang/InterruptedException;
        //  0      14     180    204    Ljava/lang/Exception;
        //  19     29     147    158    Ljava/lang/InterruptedException;
        //  19     29     180    204    Ljava/lang/Exception;
        //  29     41     147    158    Ljava/lang/InterruptedException;
        //  29     41     180    204    Ljava/lang/Exception;
        //  48     53     222    227    Any
        //  57     70     73     77     Ljava/lang/InterruptedException;
        //  57     70     222    227    Any
        //  86     94     158    180    Ljava/lang/Exception;
        //  86     94     147    158    Ljava/lang/InterruptedException;
        //  96     122    158    180    Ljava/lang/Exception;
        //  96     122    147    158    Ljava/lang/InterruptedException;
        //  130    144    147    158    Ljava/lang/InterruptedException;
        //  130    144    180    204    Ljava/lang/Exception;
        //  159    175    147    158    Ljava/lang/InterruptedException;
        //  159    175    180    204    Ljava/lang/Exception;
        //  204    214    147    158    Ljava/lang/InterruptedException;
        //  204    214    180    204    Ljava/lang/Exception;
        //  217    219    222    227    Any
        //  223    225    222    227    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
    
    public final void wakeup() {
        synchronized (this.mLock) {
            this.zzahy = false;
            this.mLock.notifyAll();
            zzakb.zzck("ContentFetchThread: wakeup");
        }
    }
    
    @VisibleForTesting
    final void zza(final zzge zzge, final WebView webView, String optString, final boolean b) {
        zzge.zzgr();
        try {
            if (!TextUtils.isEmpty((CharSequence)optString)) {
                optString = new JSONObject(optString).optString("text");
                if (!this.zzaii && !TextUtils.isEmpty((CharSequence)webView.getTitle())) {
                    final String title = webView.getTitle();
                    zzge.zza(new StringBuilder(String.valueOf(title).length() + 1 + String.valueOf(optString).length()).append(title).append("\n").append(optString).toString(), b, webView.getX(), webView.getY(), (float)webView.getWidth(), (float)webView.getHeight());
                }
                else {
                    zzge.zza(optString, b, webView.getX(), webView.getY(), (float)webView.getWidth(), (float)webView.getHeight());
                }
            }
            if (zzge.zzgn()) {
                this.zzahz.zzb(zzge);
            }
        }
        catch (JSONException ex) {
            zzakb.zzck("Json string may be malformed.");
        }
        catch (Throwable t) {
            zzakb.zza("Failed to get webview content.", t);
            this.zzaia.zza(t, "ContentFetchTask.processWebViewContent");
        }
    }
    
    public final void zzgw() {
        synchronized (this.mLock) {
            if (this.mStarted) {
                zzakb.zzck("Content hash thread already started, quiting...");
                return;
            }
            this.mStarted = true;
            // monitorexit(this.mLock)
            this.start();
        }
    }
    
    public final zzge zzgy() {
        return this.zzahz.zzgv();
    }
    
    public final boolean zzha() {
        return this.zzahy;
    }
    
    @VisibleForTesting
    final void zzk(final View view) {
        try {
            final zzge zzge = new zzge(this.zzagx, this.zzaic, this.zzagz, this.zzaid, this.zzaie, this.zzaif, this.zzaig);
            final Context context = zzbv.zzen().getContext();
            if (context != null && !TextUtils.isEmpty((CharSequence)this.zzaih)) {
                final String s = (String)view.getTag(context.getResources().getIdentifier((String)zzkb.zzik().zzd(zznk.zzawv), "id", context.getPackageName()));
                if (s != null && s.equals(this.zzaih)) {
                    return;
                }
            }
            final zzgo zza = this.zza(view, zzge);
            zzge.zzgt();
            if ((zza.zzaiq != 0 || zza.zzair != 0) && (zza.zzair != 0 || zzge.zzgu() != 0) && (zza.zzair != 0 || !this.zzahz.zza(zzge))) {
                this.zzahz.zzc(zzge);
            }
        }
        catch (Exception ex) {
            zzakb.zzb("Exception in fetchContentOnUIThread", (Throwable)ex);
            this.zzaia.zza((Throwable)ex, "ContentFetchTask.fetchContent");
        }
    }
}
