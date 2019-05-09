// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.utils;

import net.hockeyapp.android.objects.FeedbackResponse;

public class FeedbackParser
{
    private FeedbackParser() {
    }
    
    public static FeedbackParser getInstance() {
        return FeedbackParserHolder.INSTANCE;
    }
    
    public FeedbackResponse parseFeedbackResponse(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          9
        //     3: aconst_null    
        //     4: astore          8
        //     6: aload_1        
        //     7: ifnull          700
        //    10: new             Lorg/json/JSONObject;
        //    13: dup            
        //    14: aload_1        
        //    15: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    18: astore          11
        //    20: aload           11
        //    22: ldc             "feedback"
        //    24: invokevirtual   org/json/JSONObject.getJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //    27: astore          13
        //    29: new             Lnet/hockeyapp/android/objects/Feedback;
        //    32: dup            
        //    33: invokespecial   net/hockeyapp/android/objects/Feedback.<init>:()V
        //    36: astore          12
        //    38: aload           13
        //    40: ldc             "messages"
        //    42: invokevirtual   org/json/JSONObject.getJSONArray:(Ljava/lang/String;)Lorg/json/JSONArray;
        //    45: astore          14
        //    47: aconst_null    
        //    48: astore_1       
        //    49: aload           14
        //    51: invokevirtual   org/json/JSONArray.length:()I
        //    54: ifle            592
        //    57: new             Ljava/util/ArrayList;
        //    60: dup            
        //    61: invokespecial   java/util/ArrayList.<init>:()V
        //    64: astore          8
        //    66: iconst_0       
        //    67: istore_2       
        //    68: aload           8
        //    70: astore_1       
        //    71: iload_2        
        //    72: aload           14
        //    74: invokevirtual   org/json/JSONArray.length:()I
        //    77: if_icmpge       592
        //    80: aload           14
        //    82: iload_2        
        //    83: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //    86: ldc             "subject"
        //    88: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    91: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //    94: astore          15
        //    96: aload           14
        //    98: iload_2        
        //    99: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   102: ldc             "text"
        //   104: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   107: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   110: astore          16
        //   112: aload           14
        //   114: iload_2        
        //   115: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   118: ldc             "oem"
        //   120: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   123: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   126: astore          17
        //   128: aload           14
        //   130: iload_2        
        //   131: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   134: ldc             "model"
        //   136: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   139: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   142: astore          18
        //   144: aload           14
        //   146: iload_2        
        //   147: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   150: ldc             "os_version"
        //   152: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   155: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   158: astore          19
        //   160: aload           14
        //   162: iload_2        
        //   163: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   166: ldc             "created_at"
        //   168: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   171: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   174: astore          20
        //   176: aload           14
        //   178: iload_2        
        //   179: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   182: ldc             "id"
        //   184: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //   187: istore          4
        //   189: aload           14
        //   191: iload_2        
        //   192: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   195: ldc             "token"
        //   197: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   200: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   203: astore          21
        //   205: aload           14
        //   207: iload_2        
        //   208: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   211: ldc             "via"
        //   213: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //   216: istore          5
        //   218: aload           14
        //   220: iload_2        
        //   221: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   224: ldc             "user_string"
        //   226: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   229: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   232: astore          22
        //   234: aload           14
        //   236: iload_2        
        //   237: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   240: ldc             "clean_text"
        //   242: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   245: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   248: astore          23
        //   250: aload           14
        //   252: iload_2        
        //   253: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   256: ldc             "name"
        //   258: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   261: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   264: astore          24
        //   266: aload           14
        //   268: iload_2        
        //   269: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   272: ldc             "app_id"
        //   274: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   277: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   280: astore          25
        //   282: aload           14
        //   284: iload_2        
        //   285: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   288: ldc             "attachments"
        //   290: invokevirtual   org/json/JSONObject.optJSONArray:(Ljava/lang/String;)Lorg/json/JSONArray;
        //   293: astore          26
        //   295: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   298: astore_1       
        //   299: aload           26
        //   301: ifnull          471
        //   304: new             Ljava/util/ArrayList;
        //   307: dup            
        //   308: invokespecial   java/util/ArrayList.<init>:()V
        //   311: astore          10
        //   313: iconst_0       
        //   314: istore_3       
        //   315: aload           10
        //   317: astore_1       
        //   318: iload_3        
        //   319: aload           26
        //   321: invokevirtual   org/json/JSONArray.length:()I
        //   324: if_icmpge       471
        //   327: aload           26
        //   329: iload_3        
        //   330: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   333: ldc             "id"
        //   335: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //   338: istore          6
        //   340: aload           26
        //   342: iload_3        
        //   343: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   346: ldc             "feedback_message_id"
        //   348: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //   351: istore          7
        //   353: aload           26
        //   355: iload_3        
        //   356: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   359: ldc             "file_name"
        //   361: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   364: astore_1       
        //   365: aload           26
        //   367: iload_3        
        //   368: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   371: ldc             "url"
        //   373: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   376: astore          27
        //   378: aload           26
        //   380: iload_3        
        //   381: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   384: ldc             "created_at"
        //   386: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   389: astore          28
        //   391: aload           26
        //   393: iload_3        
        //   394: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   397: ldc             "updated_at"
        //   399: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   402: astore          29
        //   404: new             Lnet/hockeyapp/android/objects/FeedbackAttachment;
        //   407: dup            
        //   408: invokespecial   net/hockeyapp/android/objects/FeedbackAttachment.<init>:()V
        //   411: astore          30
        //   413: aload           30
        //   415: iload           6
        //   417: invokevirtual   net/hockeyapp/android/objects/FeedbackAttachment.setId:(I)V
        //   420: aload           30
        //   422: iload           7
        //   424: invokevirtual   net/hockeyapp/android/objects/FeedbackAttachment.setMessageId:(I)V
        //   427: aload           30
        //   429: aload_1        
        //   430: invokevirtual   net/hockeyapp/android/objects/FeedbackAttachment.setFilename:(Ljava/lang/String;)V
        //   433: aload           30
        //   435: aload           27
        //   437: invokevirtual   net/hockeyapp/android/objects/FeedbackAttachment.setUrl:(Ljava/lang/String;)V
        //   440: aload           30
        //   442: aload           28
        //   444: invokevirtual   net/hockeyapp/android/objects/FeedbackAttachment.setCreatedAt:(Ljava/lang/String;)V
        //   447: aload           30
        //   449: aload           29
        //   451: invokevirtual   net/hockeyapp/android/objects/FeedbackAttachment.setUpdatedAt:(Ljava/lang/String;)V
        //   454: aload           10
        //   456: aload           30
        //   458: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   463: pop            
        //   464: iload_3        
        //   465: iconst_1       
        //   466: iadd           
        //   467: istore_3       
        //   468: goto            315
        //   471: new             Lnet/hockeyapp/android/objects/FeedbackMessage;
        //   474: dup            
        //   475: invokespecial   net/hockeyapp/android/objects/FeedbackMessage.<init>:()V
        //   478: astore          10
        //   480: aload           10
        //   482: aload           25
        //   484: invokevirtual   net/hockeyapp/android/objects/FeedbackMessage.setAppId:(Ljava/lang/String;)V
        //   487: aload           10
        //   489: aload           23
        //   491: invokevirtual   net/hockeyapp/android/objects/FeedbackMessage.setCleanText:(Ljava/lang/String;)V
        //   494: aload           10
        //   496: aload           20
        //   498: invokevirtual   net/hockeyapp/android/objects/FeedbackMessage.setCreatedAt:(Ljava/lang/String;)V
        //   501: aload           10
        //   503: iload           4
        //   505: invokevirtual   net/hockeyapp/android/objects/FeedbackMessage.setId:(I)V
        //   508: aload           10
        //   510: aload           18
        //   512: invokevirtual   net/hockeyapp/android/objects/FeedbackMessage.setModel:(Ljava/lang/String;)V
        //   515: aload           10
        //   517: aload           24
        //   519: invokevirtual   net/hockeyapp/android/objects/FeedbackMessage.setName:(Ljava/lang/String;)V
        //   522: aload           10
        //   524: aload           17
        //   526: invokevirtual   net/hockeyapp/android/objects/FeedbackMessage.setOem:(Ljava/lang/String;)V
        //   529: aload           10
        //   531: aload           19
        //   533: invokevirtual   net/hockeyapp/android/objects/FeedbackMessage.setOsVersion:(Ljava/lang/String;)V
        //   536: aload           10
        //   538: aload           15
        //   540: invokevirtual   net/hockeyapp/android/objects/FeedbackMessage.setSubjec:(Ljava/lang/String;)V
        //   543: aload           10
        //   545: aload           16
        //   547: invokevirtual   net/hockeyapp/android/objects/FeedbackMessage.setText:(Ljava/lang/String;)V
        //   550: aload           10
        //   552: aload           21
        //   554: invokevirtual   net/hockeyapp/android/objects/FeedbackMessage.setToken:(Ljava/lang/String;)V
        //   557: aload           10
        //   559: aload           22
        //   561: invokevirtual   net/hockeyapp/android/objects/FeedbackMessage.setUserString:(Ljava/lang/String;)V
        //   564: aload           10
        //   566: iload           5
        //   568: invokevirtual   net/hockeyapp/android/objects/FeedbackMessage.setVia:(I)V
        //   571: aload           10
        //   573: aload_1        
        //   574: invokevirtual   net/hockeyapp/android/objects/FeedbackMessage.setFeedbackAttachments:(Ljava/util/List;)V
        //   577: aload           8
        //   579: aload           10
        //   581: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   584: pop            
        //   585: iload_2        
        //   586: iconst_1       
        //   587: iadd           
        //   588: istore_2       
        //   589: goto            68
        //   592: aload           12
        //   594: aload_1        
        //   595: invokevirtual   net/hockeyapp/android/objects/Feedback.setMessages:(Ljava/util/ArrayList;)V
        //   598: aload           12
        //   600: aload           13
        //   602: ldc             "name"
        //   604: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   607: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   610: invokevirtual   net/hockeyapp/android/objects/Feedback.setName:(Ljava/lang/String;)V
        //   613: aload           12
        //   615: aload           13
        //   617: ldc             "email"
        //   619: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   622: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   625: invokevirtual   net/hockeyapp/android/objects/Feedback.setEmail:(Ljava/lang/String;)V
        //   628: aload           12
        //   630: aload           13
        //   632: ldc             "id"
        //   634: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //   637: invokevirtual   net/hockeyapp/android/objects/Feedback.setId:(I)V
        //   640: aload           12
        //   642: aload           13
        //   644: ldc             "created_at"
        //   646: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   649: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   652: invokevirtual   net/hockeyapp/android/objects/Feedback.setCreatedAt:(Ljava/lang/String;)V
        //   655: new             Lnet/hockeyapp/android/objects/FeedbackResponse;
        //   658: dup            
        //   659: invokespecial   net/hockeyapp/android/objects/FeedbackResponse.<init>:()V
        //   662: astore_1       
        //   663: aload_1        
        //   664: aload           12
        //   666: invokevirtual   net/hockeyapp/android/objects/FeedbackResponse.setFeedback:(Lnet/hockeyapp/android/objects/Feedback;)V
        //   669: aload_1        
        //   670: aload           11
        //   672: ldc             "status"
        //   674: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   677: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   680: invokevirtual   net/hockeyapp/android/objects/FeedbackResponse.setStatus:(Ljava/lang/String;)V
        //   683: aload_1        
        //   684: aload           11
        //   686: ldc             "token"
        //   688: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   691: invokevirtual   java/lang/String.toString:()Ljava/lang/String;
        //   694: invokevirtual   net/hockeyapp/android/objects/FeedbackResponse.setToken:(Ljava/lang/String;)V
        //   697: aload_1        
        //   698: astore          8
        //   700: aload           8
        //   702: areturn        
        //   703: astore_1       
        //   704: aload_1        
        //   705: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   708: goto            613
        //   711: astore_1       
        //   712: aload           9
        //   714: astore          8
        //   716: aload_1        
        //   717: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   720: aload           8
        //   722: areturn        
        //   723: astore_1       
        //   724: aload_1        
        //   725: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   728: goto            628
        //   731: astore_1       
        //   732: aload_1        
        //   733: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   736: goto            640
        //   739: astore_1       
        //   740: aload_1        
        //   741: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   744: goto            655
        //   747: astore          8
        //   749: aload           8
        //   751: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   754: goto            683
        //   757: astore          8
        //   759: aload           8
        //   761: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   764: goto            697
        //   767: astore_1       
        //   768: aload           9
        //   770: astore          8
        //   772: goto            716
        //   775: astore          9
        //   777: aload_1        
        //   778: astore          8
        //   780: aload           9
        //   782: astore_1       
        //   783: goto            716
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  10     38     767    775    Lorg/json/JSONException;
        //  38     47     711    716    Lorg/json/JSONException;
        //  49     66     711    716    Lorg/json/JSONException;
        //  71     299    711    716    Lorg/json/JSONException;
        //  304    313    711    716    Lorg/json/JSONException;
        //  318    464    711    716    Lorg/json/JSONException;
        //  471    585    711    716    Lorg/json/JSONException;
        //  592    598    711    716    Lorg/json/JSONException;
        //  598    613    703    711    Lorg/json/JSONException;
        //  613    628    723    731    Lorg/json/JSONException;
        //  628    640    731    739    Lorg/json/JSONException;
        //  640    655    739    747    Lorg/json/JSONException;
        //  655    663    711    716    Lorg/json/JSONException;
        //  663    669    775    786    Lorg/json/JSONException;
        //  669    683    747    757    Lorg/json/JSONException;
        //  683    697    757    767    Lorg/json/JSONException;
        //  704    708    711    716    Lorg/json/JSONException;
        //  724    728    711    716    Lorg/json/JSONException;
        //  732    736    711    716    Lorg/json/JSONException;
        //  740    744    711    716    Lorg/json/JSONException;
        //  749    754    775    786    Lorg/json/JSONException;
        //  759    764    775    786    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 363, Size: 363
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
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
    
    private static class FeedbackParserHolder
    {
        public static final FeedbackParser INSTANCE;
        
        static {
            INSTANCE = new FeedbackParser(null);
        }
    }
}
