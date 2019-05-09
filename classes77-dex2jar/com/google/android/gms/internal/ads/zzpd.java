// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.ads.internal.zzbo;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.view.ViewGroup;
import android.view.View$OnTouchListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.RemoteException;
import android.view.ViewGroup$LayoutParams;
import android.widget.ImageView$ScaleType;
import android.widget.ImageView;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.widget.FrameLayout;
import android.widget.FrameLayout$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View$OnClickListener;
import android.os.Bundle;
import android.graphics.Point;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import org.json.JSONException;
import android.widget.TextView;
import android.graphics.Rect;
import java.util.Map;
import android.view.View;
import java.lang.ref.WeakReference;
import com.google.android.gms.common.util.VisibleForTesting;
import org.json.JSONObject;
import android.support.annotation.Nullable;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public class zzpd implements zzoz
{
    private final Context mContext;
    private final Object mLock;
    @Nullable
    private final zzacm zzaad;
    @Nullable
    private String zzaae;
    private final zzpa zzbiw;
    private final zzok zzbiz;
    @Nullable
    private final JSONObject zzbja;
    @Nullable
    private final zzpb zzbjb;
    private final zzci zzbjc;
    @VisibleForTesting
    boolean zzbjd;
    @VisibleForTesting
    boolean zzbje;
    private WeakReference<View> zzbjf;
    @Nullable
    private final zzang zzyf;
    @Nullable
    private zzaix zzyv;
    
    public zzpd(final Context mContext, final zzpa zzbiw, @Nullable final zzacm zzaad, final zzci zzbjc, @Nullable final JSONObject zzbja, @Nullable final zzpb zzbjb, @Nullable final zzang zzyf, @Nullable final String zzaae) {
        this.mLock = new Object();
        this.zzbjf = null;
        this.mContext = mContext;
        this.zzbiw = zzbiw;
        this.zzaad = zzaad;
        this.zzbjc = zzbjc;
        this.zzbja = zzbja;
        this.zzbjb = zzbjb;
        this.zzyf = zzyf;
        this.zzaae = zzaae;
        this.zzbiz = new zzok(this.zzaad);
    }
    
    private final JSONObject zza(final Map<String, WeakReference<View>> map, final View view) {
        final JSONObject jsonObject = new JSONObject();
        if (map == null || view == null) {
            return jsonObject;
        }
        while (true) {
            final int[] zzn = zzn(view);
            while (true) {
                int[] zzn2 = null;
                Label_0315: {
                    synchronized (map) {
                        for (final Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
                            final View view2 = entry.getValue().get();
                            if (view2 != null) {
                                zzn2 = zzn(view2);
                                final JSONObject jsonObject2 = new JSONObject();
                                final JSONObject jsonObject3 = new JSONObject();
                                try {
                                    jsonObject3.put("width", this.zzv(view2.getMeasuredWidth()));
                                    jsonObject3.put("height", this.zzv(view2.getMeasuredHeight()));
                                    jsonObject3.put("x", this.zzv(zzn2[0] - zzn[0]));
                                    jsonObject3.put("y", this.zzv(zzn2[1] - zzn[1]));
                                    jsonObject3.put("relative_to", (Object)"ad_view");
                                    jsonObject2.put("frame", (Object)jsonObject3);
                                    final Rect rect = new Rect();
                                    if (!view2.getLocalVisibleRect(rect)) {
                                        break Label_0315;
                                    }
                                    final JSONObject zzb = this.zzb(rect);
                                    jsonObject2.put("visible_bounds", (Object)zzb);
                                    if (view2 instanceof TextView) {
                                        final TextView textView = (TextView)view2;
                                        jsonObject2.put("text_color", textView.getCurrentTextColor());
                                        jsonObject2.put("font_size", (double)textView.getTextSize());
                                        jsonObject2.put("text", (Object)textView.getText());
                                    }
                                    jsonObject.put((String)entry.getKey(), (Object)jsonObject2);
                                }
                                catch (JSONException ex) {
                                    zzakb.zzdk("Unable to get asset views information");
                                }
                            }
                        }
                        break;
                    }
                }
                final JSONObject zzb = new JSONObject();
                zzb.put("width", 0);
                zzb.put("height", 0);
                zzb.put("x", this.zzv(zzn2[0] - zzn[0]));
                zzb.put("y", this.zzv(zzn2[1] - zzn[1]));
                zzb.put("relative_to", (Object)"ad_view");
                continue;
            }
        }
        // monitorexit(map)
        return jsonObject;
    }
    
    private final void zza(final View view, JSONObject optJSONObject, JSONObject jsonObject, JSONObject jsonObject2, final JSONObject jsonObject3, final String s, final JSONObject jsonObject4, final JSONObject jsonObject5) {
    Label_0278_Outer:
        while (true) {
            Preconditions.checkMainThread("Invalid call from a non-UI thread.");
            while (true) {
            Label_0433:
                while (true) {
                    try {
                        final JSONObject jsonObject6 = new JSONObject();
                        jsonObject6.put("ad", (Object)this.zzbja);
                        if (jsonObject != null) {
                            jsonObject6.put("asset_view_signal", (Object)jsonObject);
                        }
                        if (optJSONObject != null) {
                            jsonObject6.put("ad_view_signal", optJSONObject);
                        }
                        if (jsonObject4 != null) {
                            jsonObject6.put("click_signal", (Object)jsonObject4);
                        }
                        if (jsonObject2 != null) {
                            jsonObject6.put("scroll_view_signal", (Object)jsonObject2);
                        }
                        if (jsonObject3 != null) {
                            jsonObject6.put("lock_screen_signal", (Object)jsonObject3);
                        }
                        jsonObject2 = new JSONObject();
                        jsonObject2.put("asset_id", (Object)s);
                        jsonObject2.put("template", (Object)this.zzbjb.zzkb());
                        zzbv.zzem();
                        jsonObject2.put("is_privileged_process", zzakq.zzrp());
                        if ((boolean)zzkb.zzik().zzd(zznk.zzbcf) && this.zzbiz.zzjw() != null && this.zzbja.optBoolean("custom_one_point_five_click_enabled", false)) {
                            jsonObject2.put("custom_one_point_five_click_eligible", true);
                        }
                        jsonObject2.put("timestamp", zzbv.zzer().currentTimeMillis());
                        if (this.zzbiw.zzr(this.zzbjb.getCustomTemplateId()) != null) {
                            final boolean b = true;
                            jsonObject2.put("has_custom_click_handler", b);
                            if (this.zzbiw.zzr(this.zzbjb.getCustomTemplateId()) == null) {
                                break Label_0433;
                            }
                            final boolean b2 = true;
                            jsonObject6.put("has_custom_click_handler", b2);
                            try {
                                jsonObject = (JSONObject)(optJSONObject = this.zzbja.optJSONObject("tracking_urls_and_actions"));
                                if (jsonObject == null) {
                                    optJSONObject = new JSONObject();
                                }
                                jsonObject2.put("click_signals", (Object)this.zzbjc.zzaa().zza(this.mContext, ((JSONObject)optJSONObject).optString("click_string"), view));
                                jsonObject6.put("click", (Object)jsonObject2);
                                if (jsonObject5 != null) {
                                    jsonObject6.put("provided_signals", (Object)jsonObject5);
                                }
                                jsonObject6.put("ads_id", (Object)this.zzaae);
                                zzanm.zza(this.zzaad.zzi(jsonObject6), "NativeAdEngineImpl.performClick");
                                return;
                            }
                            catch (Exception ex) {
                                zzakb.zzb("Exception obtaining click signals", (Throwable)ex);
                            }
                        }
                    }
                    catch (JSONException ex2) {
                        zzakb.zzb("Unable to create click JSON.", (Throwable)ex2);
                        return;
                    }
                    final boolean b = false;
                    continue Label_0278_Outer;
                }
                final boolean b2 = false;
                continue;
            }
        }
    }
    
    private final boolean zza(final JSONObject jsonObject, final JSONObject jsonObject2, final JSONObject jsonObject3, final JSONObject jsonObject4, final JSONObject jsonObject5) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (this.zzbjd) {
            return true;
        }
        this.zzbjd = true;
        try {
            final JSONObject jsonObject6 = new JSONObject();
            jsonObject6.put("ad", (Object)this.zzbja);
            jsonObject6.put("ads_id", (Object)this.zzaae);
            if (jsonObject2 != null) {
                jsonObject6.put("asset_view_signal", (Object)jsonObject2);
            }
            if (jsonObject != null) {
                jsonObject6.put("ad_view_signal", (Object)jsonObject);
            }
            if (jsonObject3 != null) {
                jsonObject6.put("scroll_view_signal", (Object)jsonObject3);
            }
            if (jsonObject4 != null) {
                jsonObject6.put("lock_screen_signal", (Object)jsonObject4);
            }
            if (jsonObject5 != null) {
                jsonObject6.put("provided_signals", (Object)jsonObject5);
            }
            zzanm.zza(this.zzaad.zzj(jsonObject6), "NativeAdEngineImpl.recordImpression");
            this.zzbiw.zza(this);
            this.zzbiw.zzbv();
            this.zzcr();
            return true;
        }
        catch (JSONException ex) {
            zzakb.zzb("Unable to create impression JSON.", (Throwable)ex);
            return false;
        }
    }
    
    private final boolean zzaq(final String s) {
        JSONObject optJSONObject;
        if (this.zzbja == null) {
            optJSONObject = null;
        }
        else {
            optJSONObject = this.zzbja.optJSONObject("allow_pub_event_reporting");
        }
        return optJSONObject != null && optJSONObject.optBoolean(s, false);
    }
    
    private final JSONObject zzb(final Rect rect) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("width", this.zzv(rect.right - rect.left));
        jsonObject.put("height", this.zzv(rect.bottom - rect.top));
        jsonObject.put("x", this.zzv(rect.left));
        jsonObject.put("y", this.zzv(rect.top));
        jsonObject.put("relative_to", (Object)"self");
        return jsonObject;
    }
    
    private static boolean zzm(final View view) {
        return view.isShown() && view.getGlobalVisibleRect(new Rect(), (Point)null);
    }
    
    @VisibleForTesting
    private static int[] zzn(final View view) {
        final int[] array = new int[2];
        view.getLocationOnScreen(array);
        return array;
    }
    
    private final JSONObject zzo(final View view) {
        final JSONObject jsonObject = new JSONObject();
        if (view == null) {
            return jsonObject;
        }
        while (true) {
            while (true) {
                int[] zzn;
                try {
                    zzn = zzn(view);
                    final JSONObject jsonObject2 = new JSONObject();
                    jsonObject2.put("width", this.zzv(view.getMeasuredWidth()));
                    jsonObject2.put("height", this.zzv(view.getMeasuredHeight()));
                    jsonObject2.put("x", this.zzv(zzn[0]));
                    jsonObject2.put("y", this.zzv(zzn[1]));
                    jsonObject2.put("relative_to", (Object)"window");
                    jsonObject.put("frame", (Object)jsonObject2);
                    final Rect rect = new Rect();
                    if (view.getGlobalVisibleRect(rect)) {
                        final JSONObject zzb = this.zzb(rect);
                        jsonObject.put("visible_bounds", (Object)zzb);
                        return jsonObject;
                    }
                }
                catch (Exception ex) {
                    zzakb.zzdk("Unable to get native ad view bounding box");
                    return jsonObject;
                }
                final JSONObject zzb = new JSONObject();
                zzb.put("width", 0);
                zzb.put("height", 0);
                zzb.put("x", this.zzv(zzn[0]));
                zzb.put("y", this.zzv(zzn[1]));
                zzb.put("relative_to", (Object)"window");
                continue;
            }
        }
    }
    
    private static JSONObject zzp(final View view) {
        final JSONObject jsonObject = new JSONObject();
        if (view == null) {
            return jsonObject;
        }
        while (true) {
            while (true) {
                try {
                    zzbv.zzek();
                    if (zzakk.zzx(view) != -1) {
                        final boolean b = true;
                        jsonObject.put("contained_in_scroll_view", b);
                        return jsonObject;
                    }
                }
                catch (Exception ex) {
                    return jsonObject;
                }
                final boolean b = false;
                continue;
            }
        }
    }
    
    private final JSONObject zzq(final View view) {
        final JSONObject jsonObject = new JSONObject();
        if (view == null) {
            return jsonObject;
        }
        try {
            zzbv.zzek();
            jsonObject.put("can_show_on_lock_screen", zzakk.zzw(view));
            zzbv.zzek();
            jsonObject.put("is_keyguard_locked", zzakk.zzau(this.mContext));
            return jsonObject;
        }
        catch (JSONException ex) {
            zzakb.zzdk("Unable to get lock screen information");
            return jsonObject;
        }
    }
    
    @VisibleForTesting
    private final int zzv(final int n) {
        zzkb.zzif();
        return zzamu.zzb(this.mContext, n);
    }
    
    @Override
    public void cancelUnconfirmedClick() {
        if (!(boolean)zzkb.zzik().zzd(zznk.zzbcf)) {
            return;
        }
        if (!this.zzbja.optBoolean("custom_one_point_five_click_enabled", false)) {
            zzakb.zzdk("Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
            return;
        }
        this.zzbiz.cancelUnconfirmedClick();
    }
    
    @Override
    public final Context getContext() {
        return this.mContext;
    }
    
    @Override
    public final void performClick(final Bundle bundle) {
        if (bundle == null) {
            zzakb.zzck("Click data is null. No click is reported.");
            return;
        }
        if (!this.zzaq("click_reporting")) {
            zzakb.e("The ad slot cannot handle external click events. You must be whitelisted to be able to report your click events.");
            return;
        }
        this.zza(null, null, null, null, null, bundle.getBundle("click_signal").getString("asset_id"), null, zzbv.zzek().zza(bundle, null));
    }
    
    @Override
    public final boolean recordImpression(final Bundle bundle) {
        if (!this.zzaq("impression_reporting")) {
            zzakb.e("The ad slot cannot handle external impression events. You must be whitelisted to whitelisted to be able to report your impression events.");
            return false;
        }
        return this.zza(null, null, null, null, zzbv.zzek().zza(bundle, null));
    }
    
    @Override
    public final void reportTouchEvent(final Bundle bundle) {
        if (bundle == null) {
            zzakb.zzck("Touch event data is null. No touch event is reported.");
            return;
        }
        if (!this.zzaq("touch_reporting")) {
            zzakb.e("The ad slot cannot handle external touch events. You must be whitelisted to be able to report your touch events.");
            return;
        }
        this.zzbjc.zzaa().zza((int)bundle.getFloat("x"), (int)bundle.getFloat("y"), bundle.getInt("duration_ms"));
    }
    
    @Override
    public void setClickConfirmingView(final View view) {
        if (zzkb.zzik().zzd(zznk.zzbcf)) {
            if (!this.zzbja.optBoolean("custom_one_point_five_click_enabled", false)) {
                zzakb.zzdk("Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
                return;
            }
            final zzok zzbiz = this.zzbiz;
            if (view != null) {
                view.setOnClickListener((View$OnClickListener)zzbiz);
                view.setClickable(true);
                zzbiz.zzbhq = new WeakReference<View>(view);
            }
        }
    }
    
    @Nullable
    @Override
    public View zza(final View$OnClickListener onClickListener, final boolean b) {
        final zzoj zzkc = this.zzbjb.zzkc();
        if (zzkc == null) {
            return null;
        }
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-2, -2);
        if (!b) {
            switch (zzkc.zzju()) {
                default: {
                    relativeLayout$LayoutParams.addRule(10);
                    relativeLayout$LayoutParams.addRule(11);
                    break;
                }
                case 0: {
                    relativeLayout$LayoutParams.addRule(10);
                    relativeLayout$LayoutParams.addRule(9);
                    break;
                }
                case 3: {
                    relativeLayout$LayoutParams.addRule(12);
                    relativeLayout$LayoutParams.addRule(9);
                    break;
                }
                case 2: {
                    relativeLayout$LayoutParams.addRule(12);
                    relativeLayout$LayoutParams.addRule(11);
                    break;
                }
            }
        }
        final zzom zzom = new zzom(this.mContext, zzkc, relativeLayout$LayoutParams);
        zzom.setOnClickListener(onClickListener);
        zzom.setContentDescription((CharSequence)zzkb.zzik().zzd(zznk.zzbbz));
        return (View)zzom;
    }
    
    @Override
    public final void zza(final View view, final zzox zzox) {
        if (!this.zzb(view, zzox)) {
            final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(-1, -1);
            ((FrameLayout)view).removeAllViews();
            if (this.zzbjb instanceof zzpc) {
                final zzpc zzpc = (zzpc)this.zzbjb;
                if (zzpc.getImages() != null && zzpc.getImages().size() > 0) {
                    final Object value = zzpc.getImages().get(0);
                    while (true) {
                        Label_0165: {
                            if (!(value instanceof IBinder)) {
                                break Label_0165;
                            }
                            final zzpw zzh = zzpx.zzh((IBinder)value);
                            if (zzh == null) {
                                return;
                            }
                            try {
                                final IObjectWrapper zzjy = zzh.zzjy();
                                if (zzjy != null) {
                                    final Drawable imageDrawable = (Drawable)ObjectWrapper.unwrap(zzjy);
                                    final ImageView imageView = new ImageView(this.mContext);
                                    imageView.setImageDrawable(imageDrawable);
                                    imageView.setScaleType(ImageView$ScaleType.CENTER_INSIDE);
                                    ((FrameLayout)view).addView((View)imageView, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
                                }
                                return;
                            }
                            catch (RemoteException ex) {
                                zzakb.zzdk("Could not get drawable from image");
                                return;
                            }
                        }
                        final zzpw zzh = null;
                        continue;
                    }
                }
            }
        }
    }
    
    @Override
    public final void zza(final View p0, final String p1, @Nullable final Bundle p2, final Map<String, WeakReference<View>> p3, final View p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload           4
        //     3: aload           5
        //     5: invokespecial   com/google/android/gms/internal/ads/zzpd.zza:(Ljava/util/Map;Landroid/view/View;)Lorg/json/JSONObject;
        //     8: astore          6
        //    10: aload_0        
        //    11: aload           5
        //    13: invokespecial   com/google/android/gms/internal/ads/zzpd.zzo:(Landroid/view/View;)Lorg/json/JSONObject;
        //    16: astore          7
        //    18: aload           5
        //    20: invokestatic    com/google/android/gms/internal/ads/zzpd.zzp:(Landroid/view/View;)Lorg/json/JSONObject;
        //    23: astore          8
        //    25: aload_0        
        //    26: aload           5
        //    28: invokespecial   com/google/android/gms/internal/ads/zzpd.zzq:(Landroid/view/View;)Lorg/json/JSONObject;
        //    31: astore          5
        //    33: invokestatic    com/google/android/gms/ads/internal/zzbv.zzek:()Lcom/google/android/gms/internal/ads/zzakk;
        //    36: aload_3        
        //    37: aconst_null    
        //    38: invokevirtual   com/google/android/gms/internal/ads/zzakk.zza:(Landroid/os/Bundle;Lorg/json/JSONObject;)Lorg/json/JSONObject;
        //    41: astore          4
        //    43: new             Lorg/json/JSONObject;
        //    46: dup            
        //    47: invokespecial   org/json/JSONObject.<init>:()V
        //    50: astore_3       
        //    51: aload_3        
        //    52: ldc_w           "click_point"
        //    55: aload           4
        //    57: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    60: pop            
        //    61: aload_3        
        //    62: ldc             "asset_id"
        //    64: aload_2        
        //    65: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    68: pop            
        //    69: aload_0        
        //    70: aload_1        
        //    71: aload           7
        //    73: aload           6
        //    75: aload           8
        //    77: aload           5
        //    79: aload_2        
        //    80: aload_3        
        //    81: aconst_null    
        //    82: invokespecial   com/google/android/gms/internal/ads/zzpd.zza:(Landroid/view/View;Lorg/json/JSONObject;Lorg/json/JSONObject;Lorg/json/JSONObject;Lorg/json/JSONObject;Ljava/lang/String;Lorg/json/JSONObject;Lorg/json/JSONObject;)V
        //    85: return         
        //    86: astore          4
        //    88: aconst_null    
        //    89: astore_3       
        //    90: ldc_w           "Error occurred while grabbing click signals."
        //    93: aload           4
        //    95: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    98: goto            69
        //   101: astore          4
        //   103: goto            90
        //    Signature:
        //  (Landroid/view/View;Ljava/lang/String;Landroid/os/Bundle;Ljava/util/Map<Ljava/lang/String;Ljava/lang/ref/WeakReference<Landroid/view/View;>;>;Landroid/view/View;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  33     51     86     90     Ljava/lang/Exception;
        //  51     69     101    106    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
    
    @Override
    public void zza(final View view, final Map<String, WeakReference<View>> map) {
        this.zza(this.zzo(view), this.zza(map, view), zzp(view), this.zzq(view), null);
    }
    
    @Override
    public void zza(final View view, final Map<String, WeakReference<View>> map, final Bundle bundle, final View view2) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        Label_0093: {
            if (map == null) {
                break Label_0093;
            }
            synchronized (map) {
                for (final Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
                    if (view.equals(entry.getValue().get())) {
                        this.zza(view, entry.getKey(), bundle, map, view2);
                        return;
                    }
                }
                // monitorexit(map)
                if ("6".equals(this.zzbjb.zzkb())) {
                    this.zza(view, "3099", bundle, map, view2);
                    return;
                }
            }
        }
        final View view3;
        if ("2".equals(this.zzbjb.zzkb())) {
            this.zza(view3, "2099", bundle, map, view2);
            return;
        }
        if ("1".equals(this.zzbjb.zzkb())) {
            this.zza(view3, "1099", bundle, map, view2);
        }
    }
    
    public void zza(final View view, @Nullable final Map<String, WeakReference<View>> map, @Nullable final Map<String, WeakReference<View>> map2, final View$OnTouchListener onTouchListener, final View$OnClickListener view$OnClickListener) {
        if (zzkb.zzik().zzd(zznk.zzbbw)) {
            view.setOnTouchListener(onTouchListener);
            view.setClickable(true);
            view.setOnClickListener(view$OnClickListener);
            if (map != null) {
                synchronized (map) {
                    final Iterator<Map.Entry<String, WeakReference<View>>> iterator = map.entrySet().iterator();
                    while (iterator.hasNext()) {
                        final View view2 = iterator.next().getValue().get();
                        if (view2 != null) {
                            view2.setOnTouchListener(onTouchListener);
                            view2.setClickable(true);
                            view2.setOnClickListener(view$OnClickListener);
                        }
                    }
                }
            }
            // monitorexit(map)
            if (map2 != null) {
                synchronized (map2) {
                    final Iterator<Map.Entry<String, WeakReference<View>>> iterator2 = map2.entrySet().iterator();
                    while (iterator2.hasNext()) {
                        final View view3 = iterator2.next().getValue().get();
                        if (view3 != null) {
                            view3.setOnTouchListener(onTouchListener);
                        }
                    }
                }
            }
            // monitorexit(map2)
        }
    }
    
    @Override
    public void zza(final zzro zzro) {
        if (!(boolean)zzkb.zzik().zzd(zznk.zzbcf)) {
            return;
        }
        if (!this.zzbja.optBoolean("custom_one_point_five_click_enabled", false)) {
            zzakb.zzdk("Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
            return;
        }
        this.zzbiz.zza(zzro);
    }
    
    @Override
    public void zzb(final View view, final Map<String, WeakReference<View>> map) {
        if (!(boolean)zzkb.zzik().zzd(zznk.zzbbv)) {
            view.setOnTouchListener((View$OnTouchListener)null);
            view.setClickable(false);
            view.setOnClickListener((View$OnClickListener)null);
            if (map != null) {
                synchronized (map) {
                    final Iterator<Map.Entry<String, WeakReference<View>>> iterator = map.entrySet().iterator();
                    while (iterator.hasNext()) {
                        final View view2 = iterator.next().getValue().get();
                        if (view2 != null) {
                            view2.setOnTouchListener((View$OnTouchListener)null);
                            view2.setClickable(false);
                            view2.setOnClickListener((View$OnClickListener)null);
                        }
                    }
                }
            }
            // monitorexit(map)
        }
    }
    
    public final boolean zzb(final View view, final zzox zzox) {
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(-2, -2, 17);
        final View zzkd = this.zzbjb.zzkd();
        if (zzkd != null) {
            final ViewParent parent = zzkd.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup)parent).removeView(zzkd);
            }
            ((FrameLayout)view).removeAllViews();
            ((FrameLayout)view).addView(zzkd, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
            this.zzbiw.zza(zzox);
            return true;
        }
        return false;
    }
    
    @Override
    public final void zzc(final View view, final Map<String, WeakReference<View>> map) {
        synchronized (this.mLock) {
            if (this.zzbjd) {
                return;
            }
            if (zzm(view)) {
                this.zza(view, map);
                return;
            }
        }
        if (!(boolean)zzkb.zzik().zzd(zznk.zzbce) || map == null) {
            return;
        }
        synchronized (map) {
            final Iterator<Map.Entry<String, WeakReference<View>>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                final View view2 = iterator.next().getValue().get();
                if (view2 != null && zzm(view2)) {
                    final View view3;
                    this.zza(view3, map);
                    // monitorexit(map)
                    return;
                }
            }
        }
        // monitorexit(map)
    }
    
    @Override
    public void zzcr() {
        this.zzbiw.zzcr();
    }
    
    @Override
    public void zzcs() {
        this.zzbiw.zzcs();
    }
    
    @Override
    public final void zzd(final MotionEvent motionEvent) {
        this.zzbjc.zza(motionEvent);
    }
    
    public final void zzf(final Map<String, WeakReference<View>> map) {
        if (this.zzbjb.zzkd() != null) {
            if ("2".equals(this.zzbjb.zzkb())) {
                zzbv.zzeo().zzqh().zza(this.zzbiw.getAdUnitId(), this.zzbjb.zzkb(), map.containsKey("2011"));
            }
            else if ("1".equals(this.zzbjb.zzkb())) {
                zzbv.zzeo().zzqh().zza(this.zzbiw.getAdUnitId(), this.zzbjb.zzkb(), map.containsKey("1009"));
            }
        }
    }
    
    public final void zzi(final View view) {
        this.zzbiw.zzi(view);
    }
    
    @Override
    public final void zzj(final View view) {
        if ((boolean)zzkb.zzik().zzd(zznk.zzbat) && this.zzbjc != null) {
            final zzce zzaa = this.zzbjc.zzaa();
            if (zzaa != null) {
                zzaa.zzb(view);
            }
        }
    }
    
    @Override
    public boolean zzkj() {
        final zzoj zzkc = this.zzbjb.zzkc();
        return zzkc != null && zzkc.zzjv();
    }
    
    @Override
    public boolean zzkk() {
        boolean b = false;
        if (this.zzbja != null) {
            b = b;
            if (this.zzbja.optBoolean("allow_pub_owned_ad_view", false)) {
                b = true;
            }
        }
        return b;
    }
    
    @Override
    public void zzkl() {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (this.zzbje) {
            return;
        }
        this.zzbje = true;
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("ad", (Object)this.zzbja);
            jsonObject.put("ads_id", (Object)this.zzaae);
            zzanm.zza(this.zzaad.zzk(jsonObject), "NativeAdEngineImpl.recordDownloadedImpression");
        }
        catch (JSONException ex) {
            zzane.zzb("", (Throwable)ex);
        }
    }
    
    public zzaqw zzko() throws zzarg {
        zzaqw zzaqw = null;
        if (this.zzbja != null) {
            if (this.zzbja.optJSONObject("overlay") == null) {
                zzaqw = zzaqw;
            }
            else {
                zzbv.zzel();
                final Context mContext = this.mContext;
                final zzjn zzf = zzjn.zzf(this.mContext);
                final zzaqw zza = zzarc.zza(mContext, zzasi.zzb(zzf), zzf.zzarb, false, false, this.zzbjc, this.zzyf, null, null, null, zzhs.zzhm());
                if ((zzaqw = zza) != null) {
                    zza.getView().setVisibility(8);
                    new zzpf(zza).zza(this.zzaad);
                    return zza;
                }
            }
        }
        return zzaqw;
    }
    
    @Override
    public void zzkp() {
        this.zzaad.zzmc();
    }
    
    @Override
    public void zzkq() {
        this.zzbiw.zzct();
    }
    
    @Override
    public final View zzkr() {
        if (this.zzbjf != null) {
            return this.zzbjf.get();
        }
        return null;
    }
    
    @Nullable
    public final zzaix zzks() {
        if (zzbv.zzfh().zzu(this.mContext)) {
            if (this.zzyv == null) {
                this.zzyv = new zzaix(this.mContext, this.zzbiw.getAdUnitId());
            }
            return this.zzyv;
        }
        return null;
    }
    
    @Override
    public final void zzl(final View view) {
        this.zzbjf = new WeakReference<View>(view);
    }
}
