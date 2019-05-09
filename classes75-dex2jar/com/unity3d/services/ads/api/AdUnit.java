// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.api;

import android.util.SparseIntArray;
import com.unity3d.services.core.misc.Utilities;
import java.util.Collection;
import java.util.Arrays;
import java.util.Map;
import android.view.WindowInsets;
import java.lang.reflect.InvocationTargetException;
import android.os.Build$VERSION;
import org.json.JSONObject;
import com.unity3d.services.core.log.DeviceLog;
import java.util.ArrayList;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.ads.adunit.AdUnitError;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import org.json.JSONException;
import org.json.JSONArray;
import com.unity3d.services.ads.adunit.AdUnitActivity;

public class AdUnit
{
    private static AdUnitActivity _adUnitActivity;
    private static int _currentActivityId;
    
    static {
        AdUnit._currentActivityId = -1;
    }
    
    private AdUnit() {
    }
    
    @WebViewExposed
    public static void clearMotionEventCapture(final WebViewCallback webViewCallback) {
        if (getAdUnitActivity() == null) {
            webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
            return;
        }
        if (getAdUnitActivity().getLayout() != null) {
            getAdUnitActivity().getLayout().clearCapture();
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(AdUnitError.LAYOUT_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void close(final WebViewCallback webViewCallback) {
        if (getAdUnitActivity() != null) {
            getAdUnitActivity().finish();
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void endMotionEventCapture(final WebViewCallback webViewCallback) {
        if (getAdUnitActivity() == null) {
            webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
            return;
        }
        if (getAdUnitActivity().getLayout() != null) {
            getAdUnitActivity().getLayout().endCapture();
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(AdUnitError.LAYOUT_NULL, new Object[0]);
    }
    
    public static AdUnitActivity getAdUnitActivity() {
        return AdUnit._adUnitActivity;
    }
    
    public static int getCurrentAdUnitActivityId() {
        return AdUnit._currentActivityId;
    }
    
    @WebViewExposed
    public static void getCurrentMotionEventCount(final WebViewCallback webViewCallback) {
        if (getAdUnitActivity() == null) {
            webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
            return;
        }
        if (getAdUnitActivity().getLayout() != null) {
            webViewCallback.invoke(getAdUnitActivity().getLayout().getCurrentEventCount());
            return;
        }
        webViewCallback.error(AdUnitError.LAYOUT_NULL, new Object[0]);
    }
    
    private static ArrayList<Integer> getKeyEventList(final JSONArray jsonArray) throws JSONException {
        final ArrayList<Integer> list = new ArrayList<Integer>();
        for (Integer n = 0; n < jsonArray.length(); ++n) {
            list.add(jsonArray.getInt((int)n));
        }
        return list;
    }
    
    @WebViewExposed
    public static void getMotionEventCount(JSONArray eventCount, final WebViewCallback webViewCallback) {
        final ArrayList<Integer> list = new ArrayList<Integer>();
        int i = 0;
    Label_0033_Outer:
        while (i < eventCount.length()) {
            while (true) {
                try {
                    list.add(eventCount.getInt(i));
                    ++i;
                    continue Label_0033_Outer;
                }
                catch (Exception ex) {
                    DeviceLog.exception("Error retrieving int from eventTypes", ex);
                    continue;
                }
                break;
            }
            break;
        }
        if (getAdUnitActivity() == null) {
            webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
            return;
        }
        if (getAdUnitActivity().getLayout() == null) {
            webViewCallback.error(AdUnitError.LAYOUT_NULL, new Object[0]);
            return;
        }
        if (getAdUnitActivity().getLayout().getCurrentEventCount() >= getAdUnitActivity().getLayout().getMaxEventCount()) {
            webViewCallback.error(AdUnitError.MAX_MOTION_EVENT_COUNT_REACHED, new Object[0]);
            return;
        }
        eventCount = (JSONArray)getAdUnitActivity().getLayout().getEventCount(list);
        final JSONObject jsonObject = new JSONObject();
        int j = 0;
    Label_0156_Outer:
        while (j < ((SparseIntArray)eventCount).size()) {
            final int key = ((SparseIntArray)eventCount).keyAt(j);
            final int value = ((SparseIntArray)eventCount).get(key);
            while (true) {
                try {
                    jsonObject.put(Integer.toString(key), value);
                    ++j;
                    continue Label_0156_Outer;
                }
                catch (Exception ex2) {
                    DeviceLog.exception("Error building response JSON", ex2);
                    continue;
                }
                break;
            }
            break;
        }
        webViewCallback.invoke(jsonObject);
    }
    
    @WebViewExposed
    public static void getMotionEventData(final JSONObject p0, final WebViewCallback p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   org/json/JSONObject.keys:()Ljava/util/Iterator;
        //     4: astore          8
        //     6: new             Landroid/util/SparseArray;
        //     9: dup            
        //    10: invokespecial   android/util/SparseArray.<init>:()V
        //    13: astore          9
        //    15: aload           8
        //    17: invokeinterface java/util/Iterator.hasNext:()Z
        //    22: ifeq            149
        //    25: aload           8
        //    27: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    32: checkcast       Ljava/lang/String;
        //    35: astore          7
        //    37: aload           7
        //    39: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //    42: istore_3       
        //    43: aload           9
        //    45: iload_3        
        //    46: invokevirtual   android/util/SparseArray.get:(I)Ljava/lang/Object;
        //    49: ifnonnull       65
        //    52: aload           9
        //    54: iload_3        
        //    55: new             Ljava/util/ArrayList;
        //    58: dup            
        //    59: invokespecial   java/util/ArrayList.<init>:()V
        //    62: invokevirtual   android/util/SparseArray.put:(ILjava/lang/Object;)V
        //    65: aconst_null    
        //    66: astore          6
        //    68: aload_0        
        //    69: aload           7
        //    71: invokevirtual   org/json/JSONObject.getJSONArray:(Ljava/lang/String;)Lorg/json/JSONArray;
        //    74: astore          7
        //    76: aload           7
        //    78: astore          6
        //    80: aload           6
        //    82: ifnull          15
        //    85: iconst_0       
        //    86: istore_2       
        //    87: iload_2        
        //    88: aload           6
        //    90: invokevirtual   org/json/JSONArray.length:()I
        //    93: if_icmpge       15
        //    96: aload           9
        //    98: iload_3        
        //    99: invokevirtual   android/util/SparseArray.get:(I)Ljava/lang/Object;
        //   102: checkcast       Ljava/util/ArrayList;
        //   105: aload           6
        //   107: iload_2        
        //   108: invokevirtual   org/json/JSONArray.getInt:(I)I
        //   111: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   114: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   117: pop            
        //   118: iload_2        
        //   119: iconst_1       
        //   120: iadd           
        //   121: istore_2       
        //   122: goto            87
        //   125: astore          7
        //   127: ldc             "Couldn't fetch keyIndices"
        //   129: aload           7
        //   131: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   134: goto            80
        //   137: astore          7
        //   139: ldc             "Couldn't add value to requested infos"
        //   141: aload           7
        //   143: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   146: goto            118
        //   149: invokestatic    com/unity3d/services/ads/api/AdUnit.getAdUnitActivity:()Lcom/unity3d/services/ads/adunit/AdUnitActivity;
        //   152: ifnull          537
        //   155: invokestatic    com/unity3d/services/ads/api/AdUnit.getAdUnitActivity:()Lcom/unity3d/services/ads/adunit/AdUnitActivity;
        //   158: invokevirtual   com/unity3d/services/ads/adunit/AdUnitActivity.getLayout:()Lcom/unity3d/services/ads/adunit/AdUnitRelativeLayout;
        //   161: ifnull          525
        //   164: invokestatic    com/unity3d/services/ads/api/AdUnit.getAdUnitActivity:()Lcom/unity3d/services/ads/adunit/AdUnitActivity;
        //   167: invokevirtual   com/unity3d/services/ads/adunit/AdUnitActivity.getLayout:()Lcom/unity3d/services/ads/adunit/AdUnitRelativeLayout;
        //   170: invokevirtual   com/unity3d/services/ads/adunit/AdUnitRelativeLayout.getCurrentEventCount:()I
        //   173: invokestatic    com/unity3d/services/ads/api/AdUnit.getAdUnitActivity:()Lcom/unity3d/services/ads/adunit/AdUnitActivity;
        //   176: invokevirtual   com/unity3d/services/ads/adunit/AdUnitActivity.getLayout:()Lcom/unity3d/services/ads/adunit/AdUnitRelativeLayout;
        //   179: invokevirtual   com/unity3d/services/ads/adunit/AdUnitRelativeLayout.getMaxEventCount:()I
        //   182: if_icmplt       197
        //   185: aload_1        
        //   186: getstatic       com/unity3d/services/ads/adunit/AdUnitError.MAX_MOTION_EVENT_COUNT_REACHED:Lcom/unity3d/services/ads/adunit/AdUnitError;
        //   189: iconst_0       
        //   190: anewarray       Ljava/lang/Object;
        //   193: invokevirtual   com/unity3d/services/core/webview/bridge/WebViewCallback.error:(Ljava/lang/Enum;[Ljava/lang/Object;)V
        //   196: return         
        //   197: invokestatic    com/unity3d/services/ads/api/AdUnit.getAdUnitActivity:()Lcom/unity3d/services/ads/adunit/AdUnitActivity;
        //   200: invokevirtual   com/unity3d/services/ads/adunit/AdUnitActivity.getLayout:()Lcom/unity3d/services/ads/adunit/AdUnitRelativeLayout;
        //   203: aload           9
        //   205: invokevirtual   com/unity3d/services/ads/adunit/AdUnitRelativeLayout.getEvents:(Landroid/util/SparseArray;)Landroid/util/SparseArray;
        //   208: astore_0       
        //   209: new             Lorg/json/JSONObject;
        //   212: dup            
        //   213: invokespecial   org/json/JSONObject.<init>:()V
        //   216: astore          6
        //   218: iconst_0       
        //   219: istore_2       
        //   220: iload_2        
        //   221: aload_0        
        //   222: invokevirtual   android/util/SparseArray.size:()I
        //   225: if_icmpge       511
        //   228: aload_0        
        //   229: iload_2        
        //   230: invokevirtual   android/util/SparseArray.keyAt:(I)I
        //   233: istore          4
        //   235: aload_0        
        //   236: iload           4
        //   238: invokevirtual   android/util/SparseArray.get:(I)Ljava/lang/Object;
        //   241: checkcast       Landroid/util/SparseArray;
        //   244: astore          7
        //   246: new             Lorg/json/JSONObject;
        //   249: dup            
        //   250: invokespecial   org/json/JSONObject.<init>:()V
        //   253: astore          8
        //   255: iconst_0       
        //   256: istore_3       
        //   257: iload_3        
        //   258: aload           7
        //   260: invokevirtual   android/util/SparseArray.size:()I
        //   263: if_icmpge       471
        //   266: new             Lorg/json/JSONObject;
        //   269: dup            
        //   270: invokespecial   org/json/JSONObject.<init>:()V
        //   273: astore          9
        //   275: aload           7
        //   277: iload_3        
        //   278: invokevirtual   android/util/SparseArray.keyAt:(I)I
        //   281: istore          5
        //   283: aload           7
        //   285: iload           5
        //   287: invokevirtual   android/util/SparseArray.get:(I)Ljava/lang/Object;
        //   290: checkcast       Lcom/unity3d/services/ads/adunit/AdUnitMotionEvent;
        //   293: astore          10
        //   295: aload           9
        //   297: ldc             "action"
        //   299: aload           10
        //   301: invokevirtual   com/unity3d/services/ads/adunit/AdUnitMotionEvent.getAction:()I
        //   304: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   307: pop            
        //   308: aload           9
        //   310: ldc             "isObscured"
        //   312: aload           10
        //   314: invokevirtual   com/unity3d/services/ads/adunit/AdUnitMotionEvent.isObscured:()Z
        //   317: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   320: pop            
        //   321: aload           9
        //   323: ldc             "toolType"
        //   325: aload           10
        //   327: invokevirtual   com/unity3d/services/ads/adunit/AdUnitMotionEvent.getToolType:()I
        //   330: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   333: pop            
        //   334: aload           9
        //   336: ldc             "source"
        //   338: aload           10
        //   340: invokevirtual   com/unity3d/services/ads/adunit/AdUnitMotionEvent.getSource:()I
        //   343: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   346: pop            
        //   347: aload           9
        //   349: ldc             "deviceId"
        //   351: aload           10
        //   353: invokevirtual   com/unity3d/services/ads/adunit/AdUnitMotionEvent.getDeviceId:()I
        //   356: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
        //   359: pop            
        //   360: aload           9
        //   362: ldc             "x"
        //   364: aload           10
        //   366: invokevirtual   com/unity3d/services/ads/adunit/AdUnitMotionEvent.getX:()F
        //   369: f2d            
        //   370: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;D)Lorg/json/JSONObject;
        //   373: pop            
        //   374: aload           9
        //   376: ldc             "y"
        //   378: aload           10
        //   380: invokevirtual   com/unity3d/services/ads/adunit/AdUnitMotionEvent.getY:()F
        //   383: f2d            
        //   384: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;D)Lorg/json/JSONObject;
        //   387: pop            
        //   388: aload           9
        //   390: ldc             "eventTime"
        //   392: aload           10
        //   394: invokevirtual   com/unity3d/services/ads/adunit/AdUnitMotionEvent.getEventTime:()J
        //   397: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;J)Lorg/json/JSONObject;
        //   400: pop            
        //   401: aload           9
        //   403: ldc_w           "pressure"
        //   406: aload           10
        //   408: invokevirtual   com/unity3d/services/ads/adunit/AdUnitMotionEvent.getPressure:()F
        //   411: f2d            
        //   412: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;D)Lorg/json/JSONObject;
        //   415: pop            
        //   416: aload           9
        //   418: ldc_w           "size"
        //   421: aload           10
        //   423: invokevirtual   com/unity3d/services/ads/adunit/AdUnitMotionEvent.getSize:()F
        //   426: f2d            
        //   427: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;D)Lorg/json/JSONObject;
        //   430: pop            
        //   431: aload           8
        //   433: iload           5
        //   435: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   438: aload           9
        //   440: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   443: pop            
        //   444: iload_3        
        //   445: iconst_1       
        //   446: iadd           
        //   447: istore_3       
        //   448: goto            257
        //   451: astore          9
        //   453: ldc_w           "Couldn't construct event info"
        //   456: iconst_1       
        //   457: anewarray       Ljava/lang/Object;
        //   460: dup            
        //   461: iconst_0       
        //   462: aload           9
        //   464: aastore        
        //   465: invokestatic    com/unity3d/services/core/log/DeviceLog.debug:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   468: goto            444
        //   471: aload           6
        //   473: iload           4
        //   475: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   478: aload           8
        //   480: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   483: pop            
        //   484: iload_2        
        //   485: iconst_1       
        //   486: iadd           
        //   487: istore_2       
        //   488: goto            220
        //   491: astore          7
        //   493: ldc_w           "Couldn't construct info object"
        //   496: iconst_1       
        //   497: anewarray       Ljava/lang/Object;
        //   500: dup            
        //   501: iconst_0       
        //   502: aload           7
        //   504: aastore        
        //   505: invokestatic    com/unity3d/services/core/log/DeviceLog.debug:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   508: goto            484
        //   511: aload_1        
        //   512: iconst_1       
        //   513: anewarray       Ljava/lang/Object;
        //   516: dup            
        //   517: iconst_0       
        //   518: aload           6
        //   520: aastore        
        //   521: invokevirtual   com/unity3d/services/core/webview/bridge/WebViewCallback.invoke:([Ljava/lang/Object;)V
        //   524: return         
        //   525: aload_1        
        //   526: getstatic       com/unity3d/services/ads/adunit/AdUnitError.LAYOUT_NULL:Lcom/unity3d/services/ads/adunit/AdUnitError;
        //   529: iconst_0       
        //   530: anewarray       Ljava/lang/Object;
        //   533: invokevirtual   com/unity3d/services/core/webview/bridge/WebViewCallback.error:(Ljava/lang/Enum;[Ljava/lang/Object;)V
        //   536: return         
        //   537: aload_1        
        //   538: getstatic       com/unity3d/services/ads/adunit/AdUnitError.ADUNIT_NULL:Lcom/unity3d/services/ads/adunit/AdUnitError;
        //   541: iconst_0       
        //   542: anewarray       Ljava/lang/Object;
        //   545: invokevirtual   com/unity3d/services/core/webview/bridge/WebViewCallback.error:(Ljava/lang/Enum;[Ljava/lang/Object;)V
        //   548: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  68     76     125    137    Ljava/lang/Exception;
        //  96     118    137    149    Ljava/lang/Exception;
        //  295    444    451    471    Ljava/lang/Exception;
        //  471    484    491    511    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0118:
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
    
    @WebViewExposed
    public static void getOrientation(final WebViewCallback webViewCallback) {
        if (getAdUnitActivity() != null) {
            webViewCallback.invoke(getAdUnitActivity().getRequestedOrientation());
            return;
        }
        webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void getSafeAreaInsets(final WebViewCallback webViewCallback) {
        if (getAdUnitActivity() == null || getAdUnitActivity().getLayout() == null || Build$VERSION.SDK_INT < 28) {
            goto Label_0344;
        }
        final WindowInsets rootWindowInsets = getAdUnitActivity().getLayout().getRootWindowInsets();
        if (rootWindowInsets == null) {
            goto Label_0320;
        }
        final JSONObject jsonObject = new JSONObject();
        try {
            final Object invoke = rootWindowInsets.getClass().getMethod("getDisplayCutout", (Class<?>[])new Class[0]).invoke(rootWindowInsets, new Object[0]);
            if (invoke != null) {
                final Object invoke2 = invoke.getClass().getMethod("getSafeInsetTop", (Class<?>[])new Class[0]).invoke(invoke, new Object[0]);
                final Object invoke3 = invoke.getClass().getMethod("getSafeInsetRight", (Class<?>[])new Class[0]).invoke(invoke, new Object[0]);
                final Object invoke4 = invoke.getClass().getMethod("getSafeInsetBottom", (Class<?>[])new Class[0]).invoke(invoke, new Object[0]);
                final Object invoke5 = invoke.getClass().getMethod("getSafeInsetLeft", (Class<?>[])new Class[0]).invoke(invoke, new Object[0]);
                jsonObject.put("top", invoke2);
                jsonObject.put("right", invoke3);
                jsonObject.put("bottom", invoke4);
                jsonObject.put("left", invoke5);
                webViewCallback.invoke(jsonObject);
                return;
            }
            webViewCallback.error(AdUnitError.NO_DISPLAY_CUTOUT_AVAILABLE, new Object[0]);
        }
        catch (NoSuchMethodException ex) {
            webViewCallback.error(AdUnitError.DISPLAY_CUTOUT_METHOD_NOT_AVAILABLE, new Object[0]);
            DeviceLog.debug("Method getDisplayCutout not found", ex);
        }
        catch (IllegalAccessException ex3) {}
        catch (JSONException ex2) {
            webViewCallback.error(AdUnitError.DISPLAY_CUTOUT_JSON_ERROR, new Object[0]);
            DeviceLog.debug("JSON error while constructing display cutout object", ex2);
        }
        catch (InvocationTargetException jsonObject) {
            goto Label_0267;
        }
    }
    
    @WebViewExposed
    public static void getViewFrame(final String s, final WebViewCallback webViewCallback) {
        if (getAdUnitActivity() == null) {
            webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
            return;
        }
        if (getAdUnitActivity().getViewFrame(s) != null) {
            final Map<String, Integer> viewFrame = getAdUnitActivity().getViewFrame(s);
            webViewCallback.invoke(viewFrame.get("x"), viewFrame.get("y"), viewFrame.get("width"), viewFrame.get("height"));
            return;
        }
        webViewCallback.error(AdUnitError.UNKNOWN_VIEW, new Object[0]);
    }
    
    private static String[] getViewList(final JSONArray jsonArray) throws JSONException {
        final String[] array = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); ++i) {
            array[i] = jsonArray.getString(i);
        }
        return array;
    }
    
    @WebViewExposed
    public static void getViews(final WebViewCallback webViewCallback) {
        if (getAdUnitActivity() != null) {
            webViewCallback.invoke(new JSONArray((Collection)Arrays.asList(getAdUnitActivity().getViews())));
            return;
        }
        webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void open(final Integer n, final JSONArray jsonArray, final Integer n2, final WebViewCallback webViewCallback) {
        open(n, jsonArray, n2, null, webViewCallback);
    }
    
    @WebViewExposed
    public static void open(final Integer n, final JSONArray jsonArray, final Integer n2, final JSONArray jsonArray2, final WebViewCallback webViewCallback) {
        open(n, jsonArray, n2, jsonArray2, 0, true, webViewCallback);
    }
    
    @WebViewExposed
    public static void open(final Integer n, final JSONArray jsonArray, final Integer n2, final JSONArray jsonArray2, final Integer n3, final Boolean b, final WebViewCallback webViewCallback) {
        open(n, jsonArray, n2, jsonArray2, n3, b, false, webViewCallback);
    }
    
    @WebViewExposed
    public static void open(final Integer n, final JSONArray jsonArray, final Integer n2, final JSONArray jsonArray2, final Integer n3, final Boolean b, final Boolean b2, final WebViewCallback webViewCallback) {
        open(n, jsonArray, n2, jsonArray2, n3, b, b2, 0, webViewCallback);
    }
    
    @WebViewExposed
    public static void open(final Integer p0, final JSONArray p1, final Integer p2, final JSONArray p3, final Integer p4, final Boolean p5, final Boolean p6, final Integer p7, final WebViewCallback p8) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //     5: ifne            176
        //     8: aload           6
        //    10: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    13: ifeq            176
        //    16: ldc_w           "Unity Ads opening new transparent ad unit activity, hardware acceleration disabled"
        //    19: invokestatic    com/unity3d/services/core/log/DeviceLog.debug:(Ljava/lang/String;)V
        //    22: new             Landroid/content/Intent;
        //    25: dup            
        //    26: invokestatic    com/unity3d/services/core/properties/ClientProperties.getActivity:()Landroid/app/Activity;
        //    29: ldc_w           Lcom/unity3d/services/ads/adunit/AdUnitTransparentSoftwareActivity;.class
        //    32: invokespecial   android/content/Intent.<init>:(Landroid/content/Context;Ljava/lang/Class;)V
        //    35: astore          5
        //    37: aload           5
        //    39: ldc_w           268500992
        //    42: invokevirtual   android/content/Intent.addFlags:(I)Landroid/content/Intent;
        //    45: pop            
        //    46: aload_0        
        //    47: ifnull          317
        //    50: aload           5
        //    52: ldc_w           "activityId"
        //    55: aload_0        
        //    56: invokevirtual   java/lang/Integer.intValue:()I
        //    59: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;I)Landroid/content/Intent;
        //    62: pop            
        //    63: aload_0        
        //    64: invokevirtual   java/lang/Integer.intValue:()I
        //    67: invokestatic    com/unity3d/services/ads/api/AdUnit.setCurrentAdUnitActivityId:(I)V
        //    70: aload           5
        //    72: ldc_w           "views"
        //    75: aload_1        
        //    76: invokestatic    com/unity3d/services/ads/api/AdUnit.getViewList:(Lorg/json/JSONArray;)[Ljava/lang/String;
        //    79: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;[Ljava/lang/String;)Landroid/content/Intent;
        //    82: pop            
        //    83: aload_3        
        //    84: ifnull          100
        //    87: aload           5
        //    89: ldc_w           "keyEvents"
        //    92: aload_3        
        //    93: invokestatic    com/unity3d/services/ads/api/AdUnit.getKeyEventList:(Lorg/json/JSONArray;)Ljava/util/ArrayList;
        //    96: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
        //    99: pop            
        //   100: aload           5
        //   102: ldc_w           "systemUiVisibility"
        //   105: aload           4
        //   107: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
        //   110: pop            
        //   111: aload           5
        //   113: ldc_w           "orientation"
        //   116: aload_2        
        //   117: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
        //   120: pop            
        //   121: aload           5
        //   123: ldc_w           "displayCutoutMode"
        //   126: aload           7
        //   128: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
        //   131: pop            
        //   132: invokestatic    com/unity3d/services/core/properties/ClientProperties.getActivity:()Landroid/app/Activity;
        //   135: aload           5
        //   137: invokevirtual   android/app/Activity.startActivity:(Landroid/content/Intent;)V
        //   140: new             Ljava/lang/StringBuilder;
        //   143: dup            
        //   144: invokespecial   java/lang/StringBuilder.<init>:()V
        //   147: ldc_w           "Opened AdUnitActivity with: "
        //   150: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   153: aload_1        
        //   154: invokevirtual   org/json/JSONArray.toString:()Ljava/lang/String;
        //   157: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   160: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   163: invokestatic    com/unity3d/services/core/log/DeviceLog.debug:(Ljava/lang/String;)V
        //   166: aload           8
        //   168: iconst_0       
        //   169: anewarray       Ljava/lang/Object;
        //   172: invokevirtual   com/unity3d/services/core/webview/bridge/WebViewCallback.invoke:([Ljava/lang/Object;)V
        //   175: return         
        //   176: aload           5
        //   178: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   181: ifeq            215
        //   184: aload           6
        //   186: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   189: ifne            215
        //   192: ldc_w           "Unity Ads opening new hardware accelerated ad unit activity"
        //   195: invokestatic    com/unity3d/services/core/log/DeviceLog.debug:(Ljava/lang/String;)V
        //   198: new             Landroid/content/Intent;
        //   201: dup            
        //   202: invokestatic    com/unity3d/services/core/properties/ClientProperties.getActivity:()Landroid/app/Activity;
        //   205: ldc             Lcom/unity3d/services/ads/adunit/AdUnitActivity;.class
        //   207: invokespecial   android/content/Intent.<init>:(Landroid/content/Context;Ljava/lang/Class;)V
        //   210: astore          5
        //   212: goto            37
        //   215: aload           5
        //   217: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   220: ifeq            255
        //   223: aload           6
        //   225: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   228: ifeq            255
        //   231: ldc_w           "Unity Ads opening new hardware accelerated transparent ad unit activity"
        //   234: invokestatic    com/unity3d/services/core/log/DeviceLog.debug:(Ljava/lang/String;)V
        //   237: new             Landroid/content/Intent;
        //   240: dup            
        //   241: invokestatic    com/unity3d/services/core/properties/ClientProperties.getActivity:()Landroid/app/Activity;
        //   244: ldc_w           Lcom/unity3d/services/ads/adunit/AdUnitTransparentActivity;.class
        //   247: invokespecial   android/content/Intent.<init>:(Landroid/content/Context;Ljava/lang/Class;)V
        //   250: astore          5
        //   252: goto            37
        //   255: ldc_w           "Unity Ads opening new ad unit activity, hardware acceleration disabled"
        //   258: invokestatic    com/unity3d/services/core/log/DeviceLog.debug:(Ljava/lang/String;)V
        //   261: new             Landroid/content/Intent;
        //   264: dup            
        //   265: invokestatic    com/unity3d/services/core/properties/ClientProperties.getActivity:()Landroid/app/Activity;
        //   268: ldc_w           Lcom/unity3d/services/ads/adunit/AdUnitSoftwareActivity;.class
        //   271: invokespecial   android/content/Intent.<init>:(Landroid/content/Context;Ljava/lang/Class;)V
        //   274: astore          5
        //   276: goto            37
        //   279: astore_1       
        //   280: ldc_w           "Could not set activityId for intent"
        //   283: aload_1        
        //   284: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   287: aload           8
        //   289: getstatic       com/unity3d/services/ads/adunit/AdUnitError.ACTIVITY_ID:Lcom/unity3d/services/ads/adunit/AdUnitError;
        //   292: iconst_2       
        //   293: anewarray       Ljava/lang/Object;
        //   296: dup            
        //   297: iconst_0       
        //   298: aload_0        
        //   299: invokevirtual   java/lang/Integer.intValue:()I
        //   302: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   305: aastore        
        //   306: dup            
        //   307: iconst_1       
        //   308: aload_1        
        //   309: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   312: aastore        
        //   313: invokevirtual   com/unity3d/services/core/webview/bridge/WebViewCallback.error:(Ljava/lang/Enum;[Ljava/lang/Object;)V
        //   316: return         
        //   317: ldc_w           "Activity ID is NULL"
        //   320: invokestatic    com/unity3d/services/core/log/DeviceLog.error:(Ljava/lang/String;)V
        //   323: aload           8
        //   325: getstatic       com/unity3d/services/ads/adunit/AdUnitError.ACTIVITY_ID:Lcom/unity3d/services/ads/adunit/AdUnitError;
        //   328: iconst_1       
        //   329: anewarray       Ljava/lang/Object;
        //   332: dup            
        //   333: iconst_0       
        //   334: ldc_w           "Activity ID NULL"
        //   337: aastore        
        //   338: invokevirtual   com/unity3d/services/core/webview/bridge/WebViewCallback.error:(Ljava/lang/Enum;[Ljava/lang/Object;)V
        //   341: return         
        //   342: astore_0       
        //   343: ldc_w           "Error parsing views from viewList"
        //   346: aload_0        
        //   347: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   350: aload           8
        //   352: getstatic       com/unity3d/services/ads/adunit/AdUnitError.CORRUPTED_VIEWLIST:Lcom/unity3d/services/ads/adunit/AdUnitError;
        //   355: iconst_2       
        //   356: anewarray       Ljava/lang/Object;
        //   359: dup            
        //   360: iconst_0       
        //   361: aload_1        
        //   362: aastore        
        //   363: dup            
        //   364: iconst_1       
        //   365: aload_0        
        //   366: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   369: aastore        
        //   370: invokevirtual   com/unity3d/services/core/webview/bridge/WebViewCallback.error:(Ljava/lang/Enum;[Ljava/lang/Object;)V
        //   373: return         
        //   374: astore_0       
        //   375: ldc_w           "Error parsing views from viewList"
        //   378: aload_0        
        //   379: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   382: aload           8
        //   384: getstatic       com/unity3d/services/ads/adunit/AdUnitError.CORRUPTED_KEYEVENTLIST:Lcom/unity3d/services/ads/adunit/AdUnitError;
        //   387: iconst_2       
        //   388: anewarray       Ljava/lang/Object;
        //   391: dup            
        //   392: iconst_0       
        //   393: aload_3        
        //   394: aastore        
        //   395: dup            
        //   396: iconst_1       
        //   397: aload_0        
        //   398: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   401: aastore        
        //   402: invokevirtual   com/unity3d/services/core/webview/bridge/WebViewCallback.error:(Ljava/lang/Enum;[Ljava/lang/Object;)V
        //   405: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  50     63     279    317    Ljava/lang/Exception;
        //  70     83     342    374    Ljava/lang/Exception;
        //  87     100    374    406    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 186, Size: 186
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
    
    public static void setAdUnitActivity(final AdUnitActivity adUnitActivity) {
        AdUnit._adUnitActivity = adUnitActivity;
    }
    
    public static void setCurrentAdUnitActivityId(final int currentActivityId) {
        AdUnit._currentActivityId = currentActivityId;
    }
    
    @WebViewExposed
    public static void setKeepScreenOn(final Boolean b, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (AdUnit.getAdUnitActivity() != null) {
                    AdUnit.getAdUnitActivity().setKeepScreenOn(b);
                }
            }
        });
        if (getAdUnitActivity() != null) {
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void setKeyEventList(final JSONArray jsonArray, final WebViewCallback webViewCallback) {
        if (getAdUnitActivity() != null) {
            try {
                getAdUnitActivity().setKeyEventList(getKeyEventList(jsonArray));
                webViewCallback.invoke(jsonArray);
                return;
            }
            catch (Exception ex) {
                DeviceLog.exception("Error parsing views from viewList", ex);
                webViewCallback.error(AdUnitError.CORRUPTED_KEYEVENTLIST, jsonArray, ex.getMessage());
                return;
            }
        }
        webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void setLayoutInDisplayCutoutMode(final Integer n, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (AdUnit.getAdUnitActivity() != null) {
                    AdUnit.getAdUnitActivity().setLayoutInDisplayCutoutMode(n);
                }
            }
        });
        if (getAdUnitActivity() != null) {
            webViewCallback.invoke(n);
            return;
        }
        webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void setOrientation(final Integer n, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (AdUnit.getAdUnitActivity() != null) {
                    AdUnit.getAdUnitActivity().setOrientation(n);
                }
            }
        });
        if (getAdUnitActivity() != null) {
            webViewCallback.invoke(n);
            return;
        }
        webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void setSystemUiVisibility(final Integer n, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (AdUnit.getAdUnitActivity() != null) {
                    AdUnit.getAdUnitActivity().setSystemUiVisibility(n);
                }
            }
        });
        if (getAdUnitActivity() != null) {
            webViewCallback.invoke(n);
            return;
        }
        webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void setViewFrame(final String s, final Integer n, final Integer n2, final Integer n3, final Integer n4, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (AdUnit.getAdUnitActivity() != null) {
                    AdUnit.getAdUnitActivity().setViewFrame(s, n, n2, n3, n4);
                }
            }
        });
        if (getAdUnitActivity() != null) {
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void setViews(final JSONArray jsonArray, final WebViewCallback webViewCallback) {
        int n = 0;
        while (true) {
            try {
                getViewList(jsonArray);
                if (n == 0) {
                    Utilities.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (AdUnit.getAdUnitActivity() == null) {
                                return;
                            }
                            try {
                                AdUnit.getAdUnitActivity().setViews(getViewList(jsonArray));
                            }
                            catch (Exception ex) {
                                DeviceLog.exception("Corrupted viewlist", ex);
                            }
                        }
                    });
                }
                if (getAdUnitActivity() != null) {
                    webViewCallback.invoke(jsonArray);
                    return;
                }
            }
            catch (JSONException ex) {
                webViewCallback.error(AdUnitError.CORRUPTED_VIEWLIST, jsonArray);
                n = 1;
                continue;
            }
            break;
        }
        webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void startMotionEventCapture(final Integer n, final WebViewCallback webViewCallback) {
        if (getAdUnitActivity() == null) {
            webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
            return;
        }
        if (getAdUnitActivity().getLayout() != null) {
            getAdUnitActivity().getLayout().startCapture(n);
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(AdUnitError.LAYOUT_NULL, new Object[0]);
    }
}
