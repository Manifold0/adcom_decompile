// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ar;

import com.google.ar.core.exceptions.FatalException;
import com.google.ar.core.exceptions.UnavailableException;
import com.google.ar.core.ArCoreApk$Availability;
import com.google.ar.core.ArCoreApk;
import android.content.Context;
import android.annotation.TargetApi;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.ArrayList;
import com.google.ar.core.Config;
import com.google.ar.core.Session;
import org.json.JSONObject;
import com.google.ar.core.Config$UpdateMode;
import com.google.ar.core.Config$PlaneFindingMode;
import com.google.ar.core.Config$LightEstimationMode;

public class ARUtils
{
    public static final int AR_CHECK_NOT_SUPPORTED = 0;
    public static final int AR_CHECK_SUPPORTED = 1;
    public static final int AR_CHECK_TRANSIENT = 2;
    private static Config$LightEstimationMode[] lightEstimationModes;
    private static Config$PlaneFindingMode[] planeFindingModes;
    private static Config$UpdateMode[] updateModes;
    
    public static Config createConfiguration(final JSONObject p0, final Session p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_3       
        //     2: new             Lcom/google/ar/core/Config;
        //     5: dup            
        //     6: aload_1        
        //     7: invokespecial   com/google/ar/core/Config.<init>:(Lcom/google/ar/core/Session;)V
        //    10: astore_1       
        //    11: aload_0        
        //    12: ldc             "lightEstimationMode"
        //    14: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    17: ifeq            71
        //    20: aload_0        
        //    21: ldc             "lightEstimationMode"
        //    23: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    26: astore          5
        //    28: getstatic       com/unity3d/services/ar/ARUtils.lightEstimationModes:[Lcom/google/ar/core/Config$LightEstimationMode;
        //    31: astore          6
        //    33: aload           6
        //    35: arraylength    
        //    36: istore          4
        //    38: iconst_0       
        //    39: istore_2       
        //    40: iload_2        
        //    41: iload           4
        //    43: if_icmpge       71
        //    46: aload           6
        //    48: iload_2        
        //    49: aaload         
        //    50: astore          7
        //    52: aload           5
        //    54: aload           7
        //    56: invokevirtual   com/google/ar/core/Config$LightEstimationMode.name:()Ljava/lang/String;
        //    59: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    62: ifeq            191
        //    65: aload_1        
        //    66: aload           7
        //    68: invokevirtual   com/google/ar/core/Config.setLightEstimationMode:(Lcom/google/ar/core/Config$LightEstimationMode;)V
        //    71: aload_0        
        //    72: ldc             "planeFindingMode"
        //    74: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //    77: ifeq            131
        //    80: aload_0        
        //    81: ldc             "planeFindingMode"
        //    83: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    86: astore          5
        //    88: getstatic       com/unity3d/services/ar/ARUtils.planeFindingModes:[Lcom/google/ar/core/Config$PlaneFindingMode;
        //    91: astore          6
        //    93: aload           6
        //    95: arraylength    
        //    96: istore          4
        //    98: iconst_0       
        //    99: istore_2       
        //   100: iload_2        
        //   101: iload           4
        //   103: if_icmpge       131
        //   106: aload           6
        //   108: iload_2        
        //   109: aaload         
        //   110: astore          7
        //   112: aload           5
        //   114: aload           7
        //   116: invokevirtual   com/google/ar/core/Config$PlaneFindingMode.name:()Ljava/lang/String;
        //   119: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   122: ifeq            208
        //   125: aload_1        
        //   126: aload           7
        //   128: invokevirtual   com/google/ar/core/Config.setPlaneFindingMode:(Lcom/google/ar/core/Config$PlaneFindingMode;)V
        //   131: aload_0        
        //   132: ldc             "updateMode"
        //   134: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   137: ifeq            189
        //   140: aload_0        
        //   141: ldc             "updateMode"
        //   143: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   146: astore_0       
        //   147: getstatic       com/unity3d/services/ar/ARUtils.updateModes:[Lcom/google/ar/core/Config$UpdateMode;
        //   150: astore          5
        //   152: aload           5
        //   154: arraylength    
        //   155: istore          4
        //   157: iload_3        
        //   158: istore_2       
        //   159: iload_2        
        //   160: iload           4
        //   162: if_icmpge       189
        //   165: aload           5
        //   167: iload_2        
        //   168: aaload         
        //   169: astore          6
        //   171: aload_0        
        //   172: aload           6
        //   174: invokevirtual   com/google/ar/core/Config$UpdateMode.name:()Ljava/lang/String;
        //   177: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   180: ifeq            225
        //   183: aload_1        
        //   184: aload           6
        //   186: invokevirtual   com/google/ar/core/Config.setUpdateMode:(Lcom/google/ar/core/Config$UpdateMode;)V
        //   189: aload_1        
        //   190: areturn        
        //   191: iload_2        
        //   192: iconst_1       
        //   193: iadd           
        //   194: istore_2       
        //   195: goto            40
        //   198: astore          5
        //   200: ldc             "lightEstimationEnabled is not a string."
        //   202: invokestatic    com/unity3d/services/core/log/DeviceLog.warning:(Ljava/lang/String;)V
        //   205: goto            71
        //   208: iload_2        
        //   209: iconst_1       
        //   210: iadd           
        //   211: istore_2       
        //   212: goto            100
        //   215: astore          5
        //   217: ldc             "planeFindingMode is not a string."
        //   219: invokestatic    com/unity3d/services/core/log/DeviceLog.warning:(Ljava/lang/String;)V
        //   222: goto            131
        //   225: iload_2        
        //   226: iconst_1       
        //   227: iadd           
        //   228: istore_2       
        //   229: goto            159
        //   232: astore_0       
        //   233: ldc             "updateMode is not a string."
        //   235: invokestatic    com/unity3d/services/core/log/DeviceLog.warning:(Ljava/lang/String;)V
        //   238: aload_1        
        //   239: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  20     38     198    208    Lorg/json/JSONException;
        //  52     71     198    208    Lorg/json/JSONException;
        //  80     98     215    225    Lorg/json/JSONException;
        //  112    131    215    225    Lorg/json/JSONException;
        //  140    157    232    240    Lorg/json/JSONException;
        //  171    189    232    240    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 127, Size: 127
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
    
    @TargetApi(19)
    public static JSONObject getConfigEnums() {
        final int n = 0;
        final JSONObject jsonObject = new JSONObject();
        try {
            final ArrayList<String> list = new ArrayList<String>();
            final Config$LightEstimationMode[] values = Config$LightEstimationMode.values();
            for (int length = values.length, i = 0; i < length; ++i) {
                list.add(values[i].name());
            }
            jsonObject.put("lightEstimationMode", (Object)new JSONArray((Object)list.toArray()));
            list.clear();
            final Config$PlaneFindingMode[] values2 = Config$PlaneFindingMode.values();
            for (int length2 = values2.length, j = 0; j < length2; ++j) {
                list.add(values2[j].name());
            }
            jsonObject.put("planeFindingMode", (Object)new JSONArray((Object)list.toArray()));
            list.clear();
            final Config$UpdateMode[] values3 = Config$UpdateMode.values();
            for (int length3 = values3.length, k = n; k < length3; ++k) {
                list.add(values3[k].name());
            }
            jsonObject.put("updateMode", (Object)new JSONArray((Object)list.toArray()));
            return jsonObject;
        }
        catch (JSONException ex) {
            return jsonObject;
        }
    }
    
    public static int isSupported(final Context context) {
        if (ARCheck.isFrameworkPresent()) {
            if (ARUtils.planeFindingModes == null) {
                ARUtils.planeFindingModes = Config$PlaneFindingMode.values();
                ARUtils.lightEstimationModes = Config$LightEstimationMode.values();
                ARUtils.updateModes = Config$UpdateMode.values();
            }
            final ArCoreApk$Availability checkAvailability = ArCoreApk.getInstance().checkAvailability(context);
            if (checkAvailability.isTransient()) {
                return 2;
            }
            if (checkAvailability == ArCoreApk$Availability.SUPPORTED_INSTALLED) {
                try {
                    new Session(context);
                    return 1;
                }
                catch (UnavailableException ex) {
                    return 0;
                }
                catch (SecurityException ex2) {
                    return 1;
                }
                catch (FatalException ex3) {
                    return 0;
                }
            }
        }
        return 0;
    }
}
