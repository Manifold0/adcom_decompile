// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.s;

import org.json.JSONException;
import com.facebook.ads.internal.r.a;
import com.facebook.ads.internal.k.e;
import android.database.Cursor;
import org.json.JSONObject;
import org.json.JSONArray;
import android.support.annotation.VisibleForTesting;
import com.facebook.ads.internal.j.d;
import android.content.Context;

public class i implements a
{
    private static final String a;
    private Context b;
    private d c;
    
    static {
        a = i.class.getSimpleName();
    }
    
    @VisibleForTesting
    public i(final Context b, final d c) {
        this.b = b;
        this.c = c;
    }
    
    private static JSONArray a(final JSONArray p0, final JSONArray p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       6
        //     4: aload_1        
        //     5: areturn        
        //     6: aload_1        
        //     7: ifnonnull       12
        //    10: aload_0        
        //    11: areturn        
        //    12: aload_0        
        //    13: invokevirtual   org/json/JSONArray.length:()I
        //    16: istore          12
        //    18: aload_1        
        //    19: invokevirtual   org/json/JSONArray.length:()I
        //    22: istore          13
        //    24: new             Lorg/json/JSONArray;
        //    27: dup            
        //    28: invokespecial   org/json/JSONArray.<init>:()V
        //    31: astore          17
        //    33: aconst_null    
        //    34: astore          15
        //    36: ldc2_w          1.7976931348623157E308
        //    39: dstore          5
        //    41: ldc2_w          1.7976931348623157E308
        //    44: dstore_3       
        //    45: aconst_null    
        //    46: astore          14
        //    48: iconst_0       
        //    49: istore          9
        //    51: iconst_0       
        //    52: istore          11
        //    54: iload_2        
        //    55: istore          10
        //    57: iload           11
        //    59: istore_2       
        //    60: iload_2        
        //    61: iload           12
        //    63: if_icmplt       73
        //    66: iload           9
        //    68: iload           13
        //    70: if_icmpge       285
        //    73: iload           10
        //    75: ifle            285
        //    78: iload_2        
        //    79: iload           12
        //    81: if_icmpge       325
        //    84: aload           15
        //    86: ifnonnull       325
        //    89: aload_0        
        //    90: iload_2        
        //    91: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //    94: astore          15
        //    96: aload           15
        //    98: ldc             "time"
        //   100: invokevirtual   org/json/JSONObject.getDouble:(Ljava/lang/String;)D
        //   103: dstore          5
        //   105: iload_2        
        //   106: iconst_1       
        //   107: iadd           
        //   108: istore_2       
        //   109: iload           9
        //   111: iload           13
        //   113: if_icmpge       322
        //   116: aload           14
        //   118: ifnonnull       322
        //   121: aload_1        
        //   122: iload           9
        //   124: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   127: astore          14
        //   129: aload           14
        //   131: ldc             "time"
        //   133: invokevirtual   org/json/JSONObject.getDouble:(Ljava/lang/String;)D
        //   136: dstore_3       
        //   137: iload           9
        //   139: iconst_1       
        //   140: iadd           
        //   141: istore          9
        //   143: aload           15
        //   145: ifnonnull       153
        //   148: aload           14
        //   150: ifnull          328
        //   153: aload           15
        //   155: ifnull          165
        //   158: dload_3        
        //   159: dload           5
        //   161: dcmpg          
        //   162: ifge            252
        //   165: aload           17
        //   167: aload           14
        //   169: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   172: pop            
        //   173: ldc2_w          1.7976931348623157E308
        //   176: dstore          7
        //   178: aconst_null    
        //   179: astore          16
        //   181: aload           15
        //   183: astore          14
        //   185: dload           5
        //   187: dstore_3       
        //   188: aload           16
        //   190: astore          15
        //   192: dload           7
        //   194: dstore          5
        //   196: iload           10
        //   198: iconst_1       
        //   199: isub           
        //   200: istore          10
        //   202: dload           5
        //   204: dstore          7
        //   206: aload           15
        //   208: astore          16
        //   210: aload           14
        //   212: astore          15
        //   214: dload_3        
        //   215: dstore          5
        //   217: aload           16
        //   219: astore          14
        //   221: dload           7
        //   223: dstore_3       
        //   224: goto            60
        //   227: astore          15
        //   229: aconst_null    
        //   230: astore          15
        //   232: ldc2_w          1.7976931348623157E308
        //   235: dstore          5
        //   237: goto            105
        //   240: astore          14
        //   242: aconst_null    
        //   243: astore          14
        //   245: ldc2_w          1.7976931348623157E308
        //   248: dstore_3       
        //   249: goto            137
        //   252: aload           17
        //   254: aload           15
        //   256: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   259: pop            
        //   260: ldc2_w          1.7976931348623157E308
        //   263: dstore          7
        //   265: aconst_null    
        //   266: astore          16
        //   268: dload_3        
        //   269: dstore          5
        //   271: dload           7
        //   273: dstore_3       
        //   274: aload           14
        //   276: astore          15
        //   278: aload           16
        //   280: astore          14
        //   282: goto            196
        //   285: iload           10
        //   287: ifle            303
        //   290: aload           15
        //   292: ifnull          306
        //   295: aload           17
        //   297: aload           15
        //   299: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   302: pop            
        //   303: aload           17
        //   305: areturn        
        //   306: aload           14
        //   308: ifnull          303
        //   311: aload           17
        //   313: aload           14
        //   315: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   318: pop            
        //   319: goto            303
        //   322: goto            143
        //   325: goto            109
        //   328: goto            60
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  89     105    227    240    Lorg/json/JSONException;
        //  121    137    240    252    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0137:
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
    
    private JSONObject a(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/facebook/ads/internal/s/i.c:Lcom/facebook/ads/internal/j/d;
        //     4: invokevirtual   com/facebook/ads/internal/j/d.d:()Landroid/database/Cursor;
        //     7: astore_2       
        //     8: aload_0        
        //     9: getfield        com/facebook/ads/internal/s/i.c:Lcom/facebook/ads/internal/j/d;
        //    12: iload_1        
        //    13: invokevirtual   com/facebook/ads/internal/j/d.a:(I)Landroid/database/Cursor;
        //    16: astore_3       
        //    17: aload_3        
        //    18: invokeinterface android/database/Cursor.getCount:()I
        //    23: ifle            468
        //    26: aload_0        
        //    27: aload_3        
        //    28: invokespecial   com/facebook/ads/internal/s/i.a:(Landroid/database/Cursor;)Lorg/json/JSONObject;
        //    31: astore          5
        //    33: new             Lorg/json/JSONArray;
        //    36: dup            
        //    37: invokespecial   org/json/JSONArray.<init>:()V
        //    40: astore          6
        //    42: aload_3        
        //    43: iconst_m1      
        //    44: invokeinterface android/database/Cursor.moveToPosition:(I)Z
        //    49: pop            
        //    50: aload_3        
        //    51: invokeinterface android/database/Cursor.moveToNext:()Z
        //    56: ifeq            307
        //    59: new             Lorg/json/JSONObject;
        //    62: dup            
        //    63: invokespecial   org/json/JSONObject.<init>:()V
        //    66: astore          7
        //    68: aload           7
        //    70: ldc             "id"
        //    72: aload_3        
        //    73: iconst_2       
        //    74: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    79: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    82: pop            
        //    83: aload           7
        //    85: ldc             "token_id"
        //    87: aload_3        
        //    88: iconst_0       
        //    89: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    94: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    97: pop            
        //    98: aload           7
        //   100: ldc             "type"
        //   102: aload_3        
        //   103: iconst_4       
        //   104: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   109: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   112: pop            
        //   113: aload           7
        //   115: ldc             "time"
        //   117: aload_3        
        //   118: iconst_5       
        //   119: invokeinterface android/database/Cursor.getDouble:(I)D
        //   124: invokestatic    com/facebook/ads/internal/w/b/v.a:(D)Ljava/lang/String;
        //   127: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   130: pop            
        //   131: aload           7
        //   133: ldc             "session_time"
        //   135: aload_3        
        //   136: bipush          6
        //   138: invokeinterface android/database/Cursor.getDouble:(I)D
        //   143: invokestatic    com/facebook/ads/internal/w/b/v.a:(D)Ljava/lang/String;
        //   146: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   149: pop            
        //   150: aload           7
        //   152: ldc             "session_id"
        //   154: aload_3        
        //   155: bipush          7
        //   157: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   162: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   165: pop            
        //   166: aload_3        
        //   167: bipush          8
        //   169: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   174: astore          4
        //   176: aload           4
        //   178: ifnull          263
        //   181: new             Lorg/json/JSONObject;
        //   184: dup            
        //   185: aload           4
        //   187: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   190: astore          4
        //   192: aload           7
        //   194: ldc             "data"
        //   196: aload           4
        //   198: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   201: pop            
        //   202: aload           7
        //   204: ldc             "attempt"
        //   206: aload_3        
        //   207: bipush          9
        //   209: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   214: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   217: pop            
        //   218: aload           6
        //   220: aload           7
        //   222: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   225: pop            
        //   226: goto            50
        //   229: astore          4
        //   231: aload_3        
        //   232: astore          4
        //   234: aload_2        
        //   235: astore_3       
        //   236: aload           4
        //   238: astore_2       
        //   239: aload_3        
        //   240: ifnull          249
        //   243: aload_3        
        //   244: invokeinterface android/database/Cursor.close:()V
        //   249: aload_2        
        //   250: ifnull          259
        //   253: aload_2        
        //   254: invokeinterface android/database/Cursor.close:()V
        //   259: aconst_null    
        //   260: astore_2       
        //   261: aload_2        
        //   262: areturn        
        //   263: new             Lorg/json/JSONObject;
        //   266: dup            
        //   267: invokespecial   org/json/JSONObject.<init>:()V
        //   270: astore          4
        //   272: goto            192
        //   275: astore          5
        //   277: aload_3        
        //   278: astore          4
        //   280: aload           5
        //   282: astore_3       
        //   283: aload_2        
        //   284: ifnull          293
        //   287: aload_2        
        //   288: invokeinterface android/database/Cursor.close:()V
        //   293: aload           4
        //   295: ifnull          305
        //   298: aload           4
        //   300: invokeinterface android/database/Cursor.close:()V
        //   305: aload_3        
        //   306: athrow         
        //   307: aload           6
        //   309: astore          4
        //   311: aload_0        
        //   312: getfield        com/facebook/ads/internal/s/i.b:Landroid/content/Context;
        //   315: invokestatic    com/facebook/ads/internal/r/a.p:(Landroid/content/Context;)Z
        //   318: ifeq            465
        //   321: aload_0        
        //   322: getfield        com/facebook/ads/internal/s/i.b:Landroid/content/Context;
        //   325: iload_1        
        //   326: invokestatic    com/facebook/ads/internal/k/e.a:(Landroid/content/Context;I)Lorg/json/JSONArray;
        //   329: astore          6
        //   331: aload           6
        //   333: ifnull          465
        //   336: aload           6
        //   338: invokevirtual   org/json/JSONArray.length:()I
        //   341: ifle            465
        //   344: aload           6
        //   346: aload           4
        //   348: iload_1        
        //   349: invokestatic    com/facebook/ads/internal/s/i.a:(Lorg/json/JSONArray;Lorg/json/JSONArray;I)Lorg/json/JSONArray;
        //   352: astore          4
        //   354: aload           4
        //   356: ifnull          459
        //   359: new             Lorg/json/JSONObject;
        //   362: dup            
        //   363: invokespecial   org/json/JSONObject.<init>:()V
        //   366: astore          6
        //   368: aload           5
        //   370: ifnull          383
        //   373: aload           6
        //   375: ldc             "tokens"
        //   377: aload           5
        //   379: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   382: pop            
        //   383: aload           6
        //   385: ldc             "events"
        //   387: aload           4
        //   389: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   392: pop            
        //   393: aload           6
        //   395: astore          4
        //   397: aload_2        
        //   398: ifnull          407
        //   401: aload_2        
        //   402: invokeinterface android/database/Cursor.close:()V
        //   407: aload           4
        //   409: astore_2       
        //   410: aload_3        
        //   411: ifnull          261
        //   414: aload_3        
        //   415: invokeinterface android/database/Cursor.close:()V
        //   420: aload           4
        //   422: areturn        
        //   423: astore_3       
        //   424: aconst_null    
        //   425: astore          4
        //   427: aconst_null    
        //   428: astore_2       
        //   429: goto            283
        //   432: astore_3       
        //   433: aconst_null    
        //   434: astore          4
        //   436: goto            283
        //   439: astore_2       
        //   440: aconst_null    
        //   441: astore_2       
        //   442: aconst_null    
        //   443: astore_3       
        //   444: goto            239
        //   447: astore_3       
        //   448: aconst_null    
        //   449: astore          4
        //   451: aload_2        
        //   452: astore_3       
        //   453: aload           4
        //   455: astore_2       
        //   456: goto            239
        //   459: aconst_null    
        //   460: astore          4
        //   462: goto            397
        //   465: goto            354
        //   468: aconst_null    
        //   469: astore          4
        //   471: aconst_null    
        //   472: astore          5
        //   474: goto            311
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  0      8      439    447    Lorg/json/JSONException;
        //  0      8      423    432    Any
        //  8      17     447    459    Lorg/json/JSONException;
        //  8      17     432    439    Any
        //  17     50     229    239    Lorg/json/JSONException;
        //  17     50     275    283    Any
        //  50     176    229    239    Lorg/json/JSONException;
        //  50     176    275    283    Any
        //  181    192    229    239    Lorg/json/JSONException;
        //  181    192    275    283    Any
        //  192    226    229    239    Lorg/json/JSONException;
        //  192    226    275    283    Any
        //  263    272    229    239    Lorg/json/JSONException;
        //  263    272    275    283    Any
        //  311    331    229    239    Lorg/json/JSONException;
        //  311    331    275    283    Any
        //  336    354    229    239    Lorg/json/JSONException;
        //  336    354    275    283    Any
        //  359    368    229    239    Lorg/json/JSONException;
        //  359    368    275    283    Any
        //  373    383    229    239    Lorg/json/JSONException;
        //  373    383    275    283    Any
        //  383    393    229    239    Lorg/json/JSONException;
        //  383    393    275    283    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
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
    
    private JSONObject a(final Cursor cursor) {
        final JSONObject jsonObject = new JSONObject();
        while (cursor.moveToNext()) {
            jsonObject.put(cursor.getString(0), (Object)cursor.getString(1));
        }
        return jsonObject;
    }
    
    private void a(final String s) {
        if (com.facebook.ads.internal.k.e.c(s)) {
            com.facebook.ads.internal.k.e.a(s);
            return;
        }
        this.c.a(s);
    }
    
    private JSONObject e() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/facebook/ads/internal/s/i.c:Lcom/facebook/ads/internal/j/d;
        //     4: invokevirtual   com/facebook/ads/internal/j/d.f:()Landroid/database/Cursor;
        //     7: astore_3       
        //     8: aload_0        
        //     9: getfield        com/facebook/ads/internal/s/i.c:Lcom/facebook/ads/internal/j/d;
        //    12: invokevirtual   com/facebook/ads/internal/j/d.e:()Landroid/database/Cursor;
        //    15: astore          4
        //    17: aload_3        
        //    18: invokeinterface android/database/Cursor.getCount:()I
        //    23: ifle            567
        //    26: aload           4
        //    28: invokeinterface android/database/Cursor.getCount:()I
        //    33: ifle            567
        //    36: aload_0        
        //    37: aload_3        
        //    38: invokespecial   com/facebook/ads/internal/s/i.a:(Landroid/database/Cursor;)Lorg/json/JSONObject;
        //    41: astore          6
        //    43: new             Lorg/json/JSONArray;
        //    46: dup            
        //    47: invokespecial   org/json/JSONArray.<init>:()V
        //    50: astore          7
        //    52: aload           4
        //    54: iconst_m1      
        //    55: invokeinterface android/database/Cursor.moveToPosition:(I)Z
        //    60: pop            
        //    61: aload           4
        //    63: invokeinterface android/database/Cursor.moveToNext:()Z
        //    68: ifeq            370
        //    71: new             Lorg/json/JSONObject;
        //    74: dup            
        //    75: invokespecial   org/json/JSONObject.<init>:()V
        //    78: astore          8
        //    80: aload           8
        //    82: ldc             "id"
        //    84: aload           4
        //    86: getstatic       com/facebook/ads/internal/j/c.a:Lcom/facebook/ads/internal/j/b;
        //    89: getfield        com/facebook/ads/internal/j/b.a:I
        //    92: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    97: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   100: pop            
        //   101: aload           8
        //   103: ldc             "token_id"
        //   105: aload           4
        //   107: getstatic       com/facebook/ads/internal/j/c.b:Lcom/facebook/ads/internal/j/b;
        //   110: getfield        com/facebook/ads/internal/j/b.a:I
        //   113: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   118: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   121: pop            
        //   122: aload           8
        //   124: ldc             "type"
        //   126: aload           4
        //   128: getstatic       com/facebook/ads/internal/j/c.d:Lcom/facebook/ads/internal/j/b;
        //   131: getfield        com/facebook/ads/internal/j/b.a:I
        //   134: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   139: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   142: pop            
        //   143: aload           8
        //   145: ldc             "time"
        //   147: aload           4
        //   149: getstatic       com/facebook/ads/internal/j/c.e:Lcom/facebook/ads/internal/j/b;
        //   152: getfield        com/facebook/ads/internal/j/b.a:I
        //   155: invokeinterface android/database/Cursor.getDouble:(I)D
        //   160: invokestatic    com/facebook/ads/internal/w/b/v.a:(D)Ljava/lang/String;
        //   163: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   166: pop            
        //   167: aload           8
        //   169: ldc             "session_time"
        //   171: aload           4
        //   173: getstatic       com/facebook/ads/internal/j/c.f:Lcom/facebook/ads/internal/j/b;
        //   176: getfield        com/facebook/ads/internal/j/b.a:I
        //   179: invokeinterface android/database/Cursor.getDouble:(I)D
        //   184: invokestatic    com/facebook/ads/internal/w/b/v.a:(D)Ljava/lang/String;
        //   187: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   190: pop            
        //   191: aload           8
        //   193: ldc             "session_id"
        //   195: aload           4
        //   197: getstatic       com/facebook/ads/internal/j/c.g:Lcom/facebook/ads/internal/j/b;
        //   200: getfield        com/facebook/ads/internal/j/b.a:I
        //   203: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   208: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   211: pop            
        //   212: aload           4
        //   214: getstatic       com/facebook/ads/internal/j/c.h:Lcom/facebook/ads/internal/j/b;
        //   217: getfield        com/facebook/ads/internal/j/b.a:I
        //   220: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   225: astore          5
        //   227: aload           5
        //   229: ifnull          323
        //   232: new             Lorg/json/JSONObject;
        //   235: dup            
        //   236: aload           5
        //   238: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   241: astore          5
        //   243: aload           8
        //   245: ldc             "data"
        //   247: aload           5
        //   249: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   252: pop            
        //   253: aload           8
        //   255: ldc             "attempt"
        //   257: aload           4
        //   259: getstatic       com/facebook/ads/internal/j/c.i:Lcom/facebook/ads/internal/j/b;
        //   262: getfield        com/facebook/ads/internal/j/b.a:I
        //   265: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   270: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   273: pop            
        //   274: aload           7
        //   276: aload           8
        //   278: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   281: pop            
        //   282: goto            61
        //   285: astore          5
        //   287: aload           4
        //   289: astore          5
        //   291: aload_3        
        //   292: astore          4
        //   294: aload           5
        //   296: astore_3       
        //   297: aload           4
        //   299: ifnull          309
        //   302: aload           4
        //   304: invokeinterface android/database/Cursor.close:()V
        //   309: aload_3        
        //   310: ifnull          319
        //   313: aload_3        
        //   314: invokeinterface android/database/Cursor.close:()V
        //   319: aconst_null    
        //   320: astore_3       
        //   321: aload_3        
        //   322: areturn        
        //   323: new             Lorg/json/JSONObject;
        //   326: dup            
        //   327: invokespecial   org/json/JSONObject.<init>:()V
        //   330: astore          5
        //   332: goto            243
        //   335: astore          6
        //   337: aload           4
        //   339: astore          5
        //   341: aload           6
        //   343: astore          4
        //   345: aload_3        
        //   346: ifnull          355
        //   349: aload_3        
        //   350: invokeinterface android/database/Cursor.close:()V
        //   355: aload           5
        //   357: ifnull          367
        //   360: aload           5
        //   362: invokeinterface android/database/Cursor.close:()V
        //   367: aload           4
        //   369: athrow         
        //   370: aload           7
        //   372: astore          5
        //   374: aload_0        
        //   375: getfield        com/facebook/ads/internal/s/i.b:Landroid/content/Context;
        //   378: invokestatic    com/facebook/ads/internal/r/a.p:(Landroid/content/Context;)Z
        //   381: ifeq            564
        //   384: aload_0        
        //   385: getfield        com/facebook/ads/internal/s/i.b:Landroid/content/Context;
        //   388: invokestatic    com/facebook/ads/internal/k/e.a:(Landroid/content/Context;)Lorg/json/JSONArray;
        //   391: astore          7
        //   393: aload           7
        //   395: ifnull          564
        //   398: aload           7
        //   400: invokevirtual   org/json/JSONArray.length:()I
        //   403: ifle            564
        //   406: iconst_0       
        //   407: istore_1       
        //   408: aload           7
        //   410: ifnull          421
        //   413: iconst_0       
        //   414: aload           7
        //   416: invokevirtual   org/json/JSONArray.length:()I
        //   419: iadd           
        //   420: istore_1       
        //   421: iload_1        
        //   422: istore_2       
        //   423: aload           5
        //   425: ifnull          436
        //   428: iload_1        
        //   429: aload           5
        //   431: invokevirtual   org/json/JSONArray.length:()I
        //   434: iadd           
        //   435: istore_2       
        //   436: aload           7
        //   438: aload           5
        //   440: iload_2        
        //   441: invokestatic    com/facebook/ads/internal/s/i.a:(Lorg/json/JSONArray;Lorg/json/JSONArray;I)Lorg/json/JSONArray;
        //   444: astore          5
        //   446: aload           5
        //   448: ifnull          558
        //   451: new             Lorg/json/JSONObject;
        //   454: dup            
        //   455: invokespecial   org/json/JSONObject.<init>:()V
        //   458: astore          7
        //   460: aload           6
        //   462: ifnull          475
        //   465: aload           7
        //   467: ldc             "tokens"
        //   469: aload           6
        //   471: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   474: pop            
        //   475: aload           7
        //   477: ldc             "events"
        //   479: aload           5
        //   481: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   484: pop            
        //   485: aload           7
        //   487: astore          5
        //   489: aload_3        
        //   490: ifnull          499
        //   493: aload_3        
        //   494: invokeinterface android/database/Cursor.close:()V
        //   499: aload           5
        //   501: astore_3       
        //   502: aload           4
        //   504: ifnull          321
        //   507: aload           4
        //   509: invokeinterface android/database/Cursor.close:()V
        //   514: aload           5
        //   516: areturn        
        //   517: astore          4
        //   519: aconst_null    
        //   520: astore          5
        //   522: aconst_null    
        //   523: astore_3       
        //   524: goto            345
        //   527: astore          4
        //   529: aconst_null    
        //   530: astore          5
        //   532: goto            345
        //   535: astore_3       
        //   536: aconst_null    
        //   537: astore_3       
        //   538: aconst_null    
        //   539: astore          4
        //   541: goto            297
        //   544: astore          4
        //   546: aconst_null    
        //   547: astore          5
        //   549: aload_3        
        //   550: astore          4
        //   552: aload           5
        //   554: astore_3       
        //   555: goto            297
        //   558: aconst_null    
        //   559: astore          5
        //   561: goto            489
        //   564: goto            446
        //   567: aconst_null    
        //   568: astore          5
        //   570: aconst_null    
        //   571: astore          6
        //   573: goto            374
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  0      8      535    544    Lorg/json/JSONException;
        //  0      8      517    527    Any
        //  8      17     544    558    Lorg/json/JSONException;
        //  8      17     527    535    Any
        //  17     61     285    297    Lorg/json/JSONException;
        //  17     61     335    345    Any
        //  61     227    285    297    Lorg/json/JSONException;
        //  61     227    335    345    Any
        //  232    243    285    297    Lorg/json/JSONException;
        //  232    243    335    345    Any
        //  243    282    285    297    Lorg/json/JSONException;
        //  243    282    335    345    Any
        //  323    332    285    297    Lorg/json/JSONException;
        //  323    332    335    345    Any
        //  374    393    285    297    Lorg/json/JSONException;
        //  374    393    335    345    Any
        //  398    406    285    297    Lorg/json/JSONException;
        //  398    406    335    345    Any
        //  413    421    285    297    Lorg/json/JSONException;
        //  413    421    335    345    Any
        //  428    436    285    297    Lorg/json/JSONException;
        //  428    436    335    345    Any
        //  436    446    285    297    Lorg/json/JSONException;
        //  436    446    335    345    Any
        //  451    460    285    297    Lorg/json/JSONException;
        //  451    460    335    345    Any
        //  465    475    285    297    Lorg/json/JSONException;
        //  465    475    335    345    Any
        //  475    485    285    297    Lorg/json/JSONException;
        //  475    485    335    345    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0061:
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
    public JSONObject a() {
        final int x = com.facebook.ads.internal.r.a.x(this.b);
        if (x > 0) {
            return this.a(x);
        }
        return this.e();
    }
    
    @Override
    public boolean a(final JSONArray jsonArray) {
        final boolean p = com.facebook.ads.internal.r.a.p(this.b);
        int i = 0;
        boolean b = true;
        while (i < jsonArray.length()) {
            boolean b2;
            try {
                final JSONObject jsonObject = jsonArray.getJSONObject(i);
                final String string = jsonObject.getString("id");
                final int int1 = jsonObject.getInt("code");
                if (int1 == 1) {
                    b2 = b;
                    if (!this.c.b(string)) {
                        b2 = b;
                        if (p) {
                            com.facebook.ads.internal.k.e.b(string);
                            b2 = b;
                        }
                    }
                }
                else if (int1 >= 1000 && int1 < 2000) {
                    this.a(string);
                    b2 = false;
                }
                else {
                    b2 = b;
                    if (int1 >= 2000) {
                        b2 = b;
                        if (int1 < 3000) {
                            b2 = b;
                            if (!this.c.b(string)) {
                                b2 = b;
                                if (p) {
                                    com.facebook.ads.internal.k.e.b(string);
                                    b2 = b;
                                }
                            }
                        }
                    }
                }
            }
            catch (JSONException ex) {
                b2 = false;
            }
            ++i;
            b = b2;
        }
        return b;
    }
    
    @Override
    public void b() {
        this.c.g();
        this.c.b();
        com.facebook.ads.internal.k.e.d(this.b);
    }
    
    @Override
    public void b(final JSONArray jsonArray) {
        final int length = jsonArray.length();
        int n = 0;
    Label_0026_Outer:
        while (true) {
            if (n >= length) {
                return;
            }
            while (true) {
                try {
                    this.a(jsonArray.getJSONObject(n).getString("id"));
                    ++n;
                    continue Label_0026_Outer;
                }
                catch (JSONException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    @Override
    public void c() {
        this.c.h();
        com.facebook.ads.internal.k.e.b(this.b);
    }
    
    @Override
    public boolean d() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore          4
        //     3: aload_0        
        //     4: getfield        com/facebook/ads/internal/s/i.b:Landroid/content/Context;
        //     7: invokestatic    com/facebook/ads/internal/r/a.x:(Landroid/content/Context;)I
        //    10: istore_2       
        //    11: iload_2        
        //    12: iconst_1       
        //    13: if_icmpge       18
        //    16: iconst_0       
        //    17: ireturn        
        //    18: aload_0        
        //    19: getfield        com/facebook/ads/internal/s/i.c:Lcom/facebook/ads/internal/j/d;
        //    22: invokevirtual   com/facebook/ads/internal/j/d.d:()Landroid/database/Cursor;
        //    25: astore          6
        //    27: aload           6
        //    29: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    34: ifeq            76
        //    37: aload           6
        //    39: iconst_0       
        //    40: invokeinterface android/database/Cursor.getInt:(I)I
        //    45: istore_1       
        //    46: aload_0        
        //    47: getfield        com/facebook/ads/internal/s/i.b:Landroid/content/Context;
        //    50: invokestatic    com/facebook/ads/internal/k/e.c:(Landroid/content/Context;)I
        //    53: istore_3       
        //    54: iload_1        
        //    55: iload_3        
        //    56: iadd           
        //    57: iload_2        
        //    58: if_icmple       81
        //    61: aload           6
        //    63: ifnull          73
        //    66: aload           6
        //    68: invokeinterface android/database/Cursor.close:()V
        //    73: iload           4
        //    75: ireturn        
        //    76: iconst_0       
        //    77: istore_1       
        //    78: goto            46
        //    81: iconst_0       
        //    82: istore          4
        //    84: goto            61
        //    87: astore          5
        //    89: aconst_null    
        //    90: astore          6
        //    92: aload           6
        //    94: ifnull          104
        //    97: aload           6
        //    99: invokeinterface android/database/Cursor.close:()V
        //   104: aload           5
        //   106: athrow         
        //   107: astore          5
        //   109: goto            92
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  18     27     87     92     Any
        //  27     46     107    112    Any
        //  46     54     107    112    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0046:
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
}
