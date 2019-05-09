// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.unity;

import android.os.Bundle;

public class FBUnityGameRequestActivity extends BaseActivity
{
    public static final String GAME_REQUEST_PARAMS = "game_request_params";
    
    @Override
    protected void onCreate(final Bundle p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_1        
        //     2: invokespecial   com/facebook/unity/BaseActivity.onCreate:(Landroid/os/Bundle;)V
        //     5: aload_0        
        //     6: invokevirtual   com/facebook/unity/FBUnityGameRequestActivity.getIntent:()Landroid/content/Intent;
        //     9: ldc             "game_request_params"
        //    11: invokevirtual   android/content/Intent.getBundleExtra:(Ljava/lang/String;)Landroid/os/Bundle;
        //    14: astore_2       
        //    15: new             Lcom/facebook/unity/UnityMessage;
        //    18: dup            
        //    19: ldc             "OnAppRequestsComplete"
        //    21: invokespecial   com/facebook/unity/UnityMessage.<init>:(Ljava/lang/String;)V
        //    24: astore_1       
        //    25: aload_2        
        //    26: ldc             "callback_id"
        //    28: invokevirtual   android/os/Bundle.containsKey:(Ljava/lang/String;)Z
        //    31: ifeq            47
        //    34: aload_1        
        //    35: ldc             "callback_id"
        //    37: aload_2        
        //    38: ldc             "callback_id"
        //    40: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    43: invokevirtual   com/facebook/unity/UnityMessage.put:(Ljava/lang/String;Ljava/io/Serializable;)Lcom/facebook/unity/UnityMessage;
        //    46: pop            
        //    47: new             Lcom/facebook/share/model/GameRequestContent$Builder;
        //    50: dup            
        //    51: invokespecial   com/facebook/share/model/GameRequestContent$Builder.<init>:()V
        //    54: astore_3       
        //    55: aload_2        
        //    56: ldc             "message"
        //    58: invokevirtual   android/os/Bundle.containsKey:(Ljava/lang/String;)Z
        //    61: ifeq            75
        //    64: aload_3        
        //    65: aload_2        
        //    66: ldc             "message"
        //    68: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    71: invokevirtual   com/facebook/share/model/GameRequestContent$Builder.setMessage:(Ljava/lang/String;)Lcom/facebook/share/model/GameRequestContent$Builder;
        //    74: pop            
        //    75: aload_2        
        //    76: ldc             "action_type"
        //    78: invokevirtual   android/os/Bundle.containsKey:(Ljava/lang/String;)Z
        //    81: ifeq            102
        //    84: aload_2        
        //    85: ldc             "action_type"
        //    87: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    90: astore          4
        //    92: aload_3        
        //    93: aload           4
        //    95: invokestatic    com/facebook/share/model/GameRequestContent$ActionType.valueOf:(Ljava/lang/String;)Lcom/facebook/share/model/GameRequestContent$ActionType;
        //    98: invokevirtual   com/facebook/share/model/GameRequestContent$Builder.setActionType:(Lcom/facebook/share/model/GameRequestContent$ActionType;)Lcom/facebook/share/model/GameRequestContent$Builder;
        //   101: pop            
        //   102: aload_2        
        //   103: ldc             "object_id"
        //   105: invokevirtual   android/os/Bundle.containsKey:(Ljava/lang/String;)Z
        //   108: ifeq            122
        //   111: aload_3        
        //   112: aload_2        
        //   113: ldc             "object_id"
        //   115: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   118: invokevirtual   com/facebook/share/model/GameRequestContent$Builder.setObjectId:(Ljava/lang/String;)Lcom/facebook/share/model/GameRequestContent$Builder;
        //   121: pop            
        //   122: aload_2        
        //   123: ldc             "to"
        //   125: invokevirtual   android/os/Bundle.containsKey:(Ljava/lang/String;)Z
        //   128: ifeq            150
        //   131: aload_3        
        //   132: aload_2        
        //   133: ldc             "to"
        //   135: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   138: ldc             ","
        //   140: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //   143: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   146: invokevirtual   com/facebook/share/model/GameRequestContent$Builder.setRecipients:(Ljava/util/List;)Lcom/facebook/share/model/GameRequestContent$Builder;
        //   149: pop            
        //   150: aload_2        
        //   151: ldc             "filters"
        //   153: invokevirtual   android/os/Bundle.containsKey:(Ljava/lang/String;)Z
        //   156: ifeq            183
        //   159: aload_2        
        //   160: ldc             "filters"
        //   162: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   165: getstatic       java/util/Locale.ROOT:Ljava/util/Locale;
        //   168: invokevirtual   java/lang/String.toUpperCase:(Ljava/util/Locale;)Ljava/lang/String;
        //   171: astore          4
        //   173: aload_3        
        //   174: aload           4
        //   176: invokestatic    com/facebook/share/model/GameRequestContent$Filters.valueOf:(Ljava/lang/String;)Lcom/facebook/share/model/GameRequestContent$Filters;
        //   179: invokevirtual   com/facebook/share/model/GameRequestContent$Builder.setFilters:(Lcom/facebook/share/model/GameRequestContent$Filters;)Lcom/facebook/share/model/GameRequestContent$Builder;
        //   182: pop            
        //   183: aload_2        
        //   184: ldc             "data"
        //   186: invokevirtual   android/os/Bundle.containsKey:(Ljava/lang/String;)Z
        //   189: ifeq            203
        //   192: aload_3        
        //   193: aload_2        
        //   194: ldc             "data"
        //   196: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   199: invokevirtual   com/facebook/share/model/GameRequestContent$Builder.setData:(Ljava/lang/String;)Lcom/facebook/share/model/GameRequestContent$Builder;
        //   202: pop            
        //   203: aload_2        
        //   204: ldc             "title"
        //   206: invokevirtual   android/os/Bundle.containsKey:(Ljava/lang/String;)Z
        //   209: ifeq            223
        //   212: aload_3        
        //   213: aload_2        
        //   214: ldc             "title"
        //   216: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   219: invokevirtual   com/facebook/share/model/GameRequestContent$Builder.setTitle:(Ljava/lang/String;)Lcom/facebook/share/model/GameRequestContent$Builder;
        //   222: pop            
        //   223: aload_3        
        //   224: invokevirtual   com/facebook/share/model/GameRequestContent$Builder.build:()Lcom/facebook/share/model/GameRequestContent;
        //   227: astore_2       
        //   228: new             Lcom/facebook/share/widget/GameRequestDialog;
        //   231: dup            
        //   232: aload_0        
        //   233: invokespecial   com/facebook/share/widget/GameRequestDialog.<init>:(Landroid/app/Activity;)V
        //   236: astore_3       
        //   237: aload_3        
        //   238: aload_0        
        //   239: getfield        com/facebook/unity/FBUnityGameRequestActivity.mCallbackManager:Lcom/facebook/CallbackManager;
        //   242: new             Lcom/facebook/unity/FBUnityGameRequestActivity$1;
        //   245: dup            
        //   246: aload_0        
        //   247: aload_1        
        //   248: invokespecial   com/facebook/unity/FBUnityGameRequestActivity$1.<init>:(Lcom/facebook/unity/FBUnityGameRequestActivity;Lcom/facebook/unity/UnityMessage;)V
        //   251: invokevirtual   com/facebook/share/widget/GameRequestDialog.registerCallback:(Lcom/facebook/CallbackManager;Lcom/facebook/FacebookCallback;)V
        //   254: aload_3        
        //   255: aload_2        
        //   256: invokevirtual   com/facebook/share/widget/GameRequestDialog.show:(Ljava/lang/Object;)V
        //   259: return         
        //   260: astore_2       
        //   261: aload_1        
        //   262: new             Ljava/lang/StringBuilder;
        //   265: dup            
        //   266: invokespecial   java/lang/StringBuilder.<init>:()V
        //   269: ldc             "Unknown action type: "
        //   271: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   274: aload           4
        //   276: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   279: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   282: invokevirtual   com/facebook/unity/UnityMessage.sendError:(Ljava/lang/String;)V
        //   285: aload_0        
        //   286: invokevirtual   com/facebook/unity/FBUnityGameRequestActivity.finish:()V
        //   289: return         
        //   290: astore_2       
        //   291: aload_1        
        //   292: new             Ljava/lang/StringBuilder;
        //   295: dup            
        //   296: invokespecial   java/lang/StringBuilder.<init>:()V
        //   299: ldc             "Unsupported filter type: "
        //   301: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   304: aload           4
        //   306: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   309: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   312: invokevirtual   com/facebook/unity/UnityMessage.sendError:(Ljava/lang/String;)V
        //   315: aload_0        
        //   316: invokevirtual   com/facebook/unity/FBUnityGameRequestActivity.finish:()V
        //   319: return         
        //   320: astore_2       
        //   321: aload_1        
        //   322: new             Ljava/lang/StringBuilder;
        //   325: dup            
        //   326: invokespecial   java/lang/StringBuilder.<init>:()V
        //   329: ldc             "Unexpected exception encountered: "
        //   331: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   334: aload_2        
        //   335: invokevirtual   java/lang/IllegalArgumentException.toString:()Ljava/lang/String;
        //   338: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   341: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   344: invokevirtual   com/facebook/unity/UnityMessage.sendError:(Ljava/lang/String;)V
        //   347: aload_0        
        //   348: invokevirtual   com/facebook/unity/FBUnityGameRequestActivity.finish:()V
        //   351: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  92     102    260    290    Ljava/lang/IllegalArgumentException;
        //  173    183    290    320    Ljava/lang/IllegalArgumentException;
        //  254    259    320    352    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 173, Size: 173
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
}
