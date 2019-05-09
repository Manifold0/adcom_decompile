// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import com.facebook.internal.Utility;
import java.security.MessageDigest;
import com.facebook.internal.Logger;
import com.facebook.LoggingBehavior;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import android.os.Build$VERSION;
import com.facebook.FacebookException;
import org.json.JSONException;
import android.support.annotation.Nullable;
import java.util.UUID;
import android.os.Bundle;
import org.json.JSONObject;
import java.util.HashSet;
import java.io.Serializable;

class AppEvent implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static final HashSet<String> validatedIdentifiers;
    private final String checksum;
    private final boolean isImplicit;
    private final JSONObject jsonObject;
    private final String name;
    
    static {
        validatedIdentifiers = new HashSet<String>();
    }
    
    public AppEvent(final String s, final String name, final Double n, final Bundle bundle, final boolean isImplicit, @Nullable final UUID uuid) throws JSONException, FacebookException {
        this.jsonObject = getJSONObjectForAppEvent(s, name, n, bundle, isImplicit, uuid);
        this.isImplicit = isImplicit;
        this.name = name;
        this.checksum = this.calculateChecksum();
    }
    
    private AppEvent(final String s, final boolean isImplicit, final String checksum) throws JSONException {
        this.jsonObject = new JSONObject(s);
        this.isImplicit = isImplicit;
        this.name = this.jsonObject.optString("_eventName");
        this.checksum = checksum;
    }
    
    private static String bytesToHex(final byte[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(String.format("%02x", array[i]));
        }
        return sb.toString();
    }
    
    private String calculateChecksum() {
        if (Build$VERSION.SDK_INT > 19) {
            return md5Checksum(this.jsonObject.toString());
        }
        final ArrayList<String> list = (ArrayList<String>)new ArrayList<Comparable>();
        final Iterator keys = this.jsonObject.keys();
        while (keys.hasNext()) {
            list.add(keys.next());
        }
        Collections.sort((List<Comparable>)list);
        final StringBuilder sb = new StringBuilder();
        for (final String s : list) {
            sb.append(s).append(" = ").append(this.jsonObject.optString(s)).append('\n');
        }
        return md5Checksum(sb.toString());
    }
    
    private static JSONObject getJSONObjectForAppEvent(final String s, String s2, final Double n, final Bundle bundle, final boolean b, @Nullable final UUID uuid) throws FacebookException, JSONException {
        validateIdentifier(s2);
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("_eventName", (Object)s2);
        jsonObject.put("_eventName_md5", (Object)md5Checksum(s2));
        jsonObject.put("_logTime", System.currentTimeMillis() / 1000L);
        jsonObject.put("_ui", (Object)s);
        if (uuid != null) {
            jsonObject.put("_session_id", (Object)uuid);
        }
        if (n != null) {
            jsonObject.put("_valueToSum", (double)n);
        }
        if (b) {
            jsonObject.put("_implicitlyLogged", (Object)"1");
        }
        if (bundle != null) {
            final Iterator<String> iterator = bundle.keySet().iterator();
            while (iterator.hasNext()) {
                s2 = iterator.next();
                validateIdentifier(s2);
                final Object value = bundle.get(s2);
                if (!(value instanceof String) && !(value instanceof Number)) {
                    throw new FacebookException(String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", value, s2));
                }
                jsonObject.put(s2, (Object)value.toString());
            }
        }
        if (!b) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Created app event '%s'", jsonObject.toString());
        }
        return jsonObject;
    }
    
    private static String md5Checksum(String bytesToHex) {
        try {
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            final byte[] bytes = bytesToHex.getBytes("UTF-8");
            instance.update(bytes, 0, bytes.length);
            bytesToHex = bytesToHex(instance.digest());
            return bytesToHex;
        }
        catch (NoSuchAlgorithmException ex) {
            Utility.logd("Failed to generate checksum: ", ex);
            return "0";
        }
        catch (UnsupportedEncodingException ex2) {
            Utility.logd("Failed to generate checksum: ", ex2);
            return "1";
        }
    }
    
    private static void validateIdentifier(final String p0) throws FacebookException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnull          20
        //     4: aload_0        
        //     5: invokevirtual   java/lang/String.length:()I
        //     8: ifeq            20
        //    11: aload_0        
        //    12: invokevirtual   java/lang/String.length:()I
        //    15: bipush          40
        //    17: if_icmple       63
        //    20: aload_0        
        //    21: astore_2       
        //    22: aload_0        
        //    23: ifnonnull       30
        //    26: ldc_w           "<None Provided>"
        //    29: astore_2       
        //    30: new             Lcom/facebook/FacebookException;
        //    33: dup            
        //    34: getstatic       java/util/Locale.ROOT:Ljava/util/Locale;
        //    37: ldc_w           "Identifier '%s' must be less than %d characters"
        //    40: iconst_2       
        //    41: anewarray       Ljava/lang/Object;
        //    44: dup            
        //    45: iconst_0       
        //    46: aload_2        
        //    47: aastore        
        //    48: dup            
        //    49: iconst_1       
        //    50: bipush          40
        //    52: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    55: aastore        
        //    56: invokestatic    java/lang/String.format:(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    59: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;)V
        //    62: athrow         
        //    63: getstatic       com/facebook/appevents/AppEvent.validatedIdentifiers:Ljava/util/HashSet;
        //    66: astore_2       
        //    67: aload_2        
        //    68: monitorenter   
        //    69: getstatic       com/facebook/appevents/AppEvent.validatedIdentifiers:Ljava/util/HashSet;
        //    72: aload_0        
        //    73: invokevirtual   java/util/HashSet.contains:(Ljava/lang/Object;)Z
        //    76: istore_1       
        //    77: aload_2        
        //    78: monitorexit    
        //    79: iload_1        
        //    80: ifne            109
        //    83: aload_0        
        //    84: ldc_w           "^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$"
        //    87: invokevirtual   java/lang/String.matches:(Ljava/lang/String;)Z
        //    90: ifeq            120
        //    93: getstatic       com/facebook/appevents/AppEvent.validatedIdentifiers:Ljava/util/HashSet;
        //    96: astore_2       
        //    97: aload_2        
        //    98: monitorenter   
        //    99: getstatic       com/facebook/appevents/AppEvent.validatedIdentifiers:Ljava/util/HashSet;
        //   102: aload_0        
        //   103: invokevirtual   java/util/HashSet.add:(Ljava/lang/Object;)Z
        //   106: pop            
        //   107: aload_2        
        //   108: monitorexit    
        //   109: return         
        //   110: astore_0       
        //   111: aload_2        
        //   112: monitorexit    
        //   113: aload_0        
        //   114: athrow         
        //   115: astore_0       
        //   116: aload_2        
        //   117: monitorexit    
        //   118: aload_0        
        //   119: athrow         
        //   120: new             Lcom/facebook/FacebookException;
        //   123: dup            
        //   124: ldc_w           "Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen."
        //   127: iconst_1       
        //   128: anewarray       Ljava/lang/Object;
        //   131: dup            
        //   132: iconst_0       
        //   133: aload_0        
        //   134: aastore        
        //   135: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   138: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;)V
        //   141: athrow         
        //    Exceptions:
        //  throws com.facebook.FacebookException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  69     79     110    115    Any
        //  99     109    115    120    Any
        //  111    113    110    115    Any
        //  116    118    115    120    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0109:
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
    
    private Object writeReplace() {
        return new SerializationProxyV2(this.jsonObject.toString(), this.isImplicit, this.checksum);
    }
    
    public boolean getIsImplicit() {
        return this.isImplicit;
    }
    
    public JSONObject getJSONObject() {
        return this.jsonObject;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isChecksumValid() {
        return this.checksum == null || this.calculateChecksum().equals(this.checksum);
    }
    
    @Override
    public String toString() {
        return String.format("\"%s\", implicit: %b, json: %s", this.jsonObject.optString("_eventName"), this.isImplicit, this.jsonObject.toString());
    }
    
    static class SerializationProxyV1 implements Serializable
    {
        private static final long serialVersionUID = -2488473066578201069L;
        private final boolean isImplicit;
        private final String jsonString;
        
        private SerializationProxyV1(final String jsonString, final boolean isImplicit) {
            this.jsonString = jsonString;
            this.isImplicit = isImplicit;
        }
        
        private Object readResolve() throws JSONException {
            return new AppEvent(this.jsonString, this.isImplicit, null, null);
        }
    }
    
    static class SerializationProxyV2 implements Serializable
    {
        private static final long serialVersionUID = 20160803001L;
        private final String checksum;
        private final boolean isImplicit;
        private final String jsonString;
        
        private SerializationProxyV2(final String jsonString, final boolean isImplicit, final String checksum) {
            this.jsonString = jsonString;
            this.isImplicit = isImplicit;
            this.checksum = checksum;
        }
        
        private Object readResolve() throws JSONException {
            return new AppEvent(this.jsonString, this.isImplicit, this.checksum, null);
        }
    }
}
