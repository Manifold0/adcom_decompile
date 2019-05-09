// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v4.graphics;

import android.support.annotation.NonNull;
import android.support.v4.provider.FontsContractCompat;
import android.support.annotation.Nullable;
import android.os.CancellationSignal;
import android.content.res.Resources;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.util.Log;
import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Array;
import android.graphics.Typeface;
import java.nio.ByteBuffer;
import android.graphics.fonts.FontVariationAxis;
import android.content.res.AssetManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.RequiresApi;

@RequiresApi(26)
@RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl
{
    private static final String ABORT_CREATION_METHOD = "abortCreation";
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
    private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String FREEZE_METHOD = "freeze";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi26Impl";
    private static final Method sAbortCreation;
    private static final Method sAddFontFromAssetManager;
    private static final Method sAddFontFromBuffer;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;
    private static final Method sFreeze;
    
    static {
        try {
            final Class<?> forName = Class.forName("android.graphics.FontFamily");
            final Constructor<?> constructor = forName.getConstructor((Class<?>[])new Class[0]);
            final Method method = forName.getMethod("addFontFromAssetManager", AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class);
            final Method method2 = forName.getMethod("addFontFromBuffer", ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE);
            final Method method3 = forName.getMethod("freeze", (Class<?>[])new Class[0]);
            final Method method4 = forName.getMethod("abortCreation", (Class<?>[])new Class[0]);
            final Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(forName, 1).getClass(), Integer.TYPE, Integer.TYPE);
            declaredMethod.setAccessible(true);
            sFontFamilyCtor = constructor;
            sFontFamily = forName;
            sAddFontFromAssetManager = method;
            sAddFontFromBuffer = method2;
            sFreeze = method3;
            sAbortCreation = method4;
            sCreateFromFamiliesWithDefault = declaredMethod;
        }
        catch (ClassNotFoundException ex) {}
        catch (NoSuchMethodException method4) {
            goto Label_0217;
        }
    }
    
    private static boolean abortCreation(final Object ex) {
        try {
            return (boolean)TypefaceCompatApi26Impl.sAbortCreation.invoke(ex, new Object[0]);
        }
        catch (IllegalAccessException ex2) {}
        catch (InvocationTargetException ex) {
            goto Label_0021;
        }
    }
    
    private static boolean addFontFromAssetManager(final Context ex, final Object o, final String s, final int n, final int n2, final int n3) {
        try {
            return (boolean)TypefaceCompatApi26Impl.sAddFontFromAssetManager.invoke(o, ((Context)ex).getAssets(), s, 0, false, n, n2, n3, null);
        }
        catch (IllegalAccessException ex2) {}
        catch (InvocationTargetException ex) {
            goto Label_0078;
        }
    }
    
    private static boolean addFontFromBuffer(final Object ex, final ByteBuffer byteBuffer, final int n, final int n2, final int n3) {
        try {
            return (boolean)TypefaceCompatApi26Impl.sAddFontFromBuffer.invoke(ex, byteBuffer, n, null, n2, n3);
        }
        catch (IllegalAccessException ex2) {}
        catch (InvocationTargetException ex) {
            goto Label_0053;
        }
    }
    
    private static Typeface createFromFamiliesWithDefault(Object ex) {
        try {
            final Object instance = Array.newInstance(TypefaceCompatApi26Impl.sFontFamily, 1);
            Array.set(instance, 0, ex);
            ex = (InvocationTargetException)TypefaceCompatApi26Impl.sCreateFromFamiliesWithDefault.invoke(null, instance, -1, -1);
            return (Typeface)ex;
        }
        catch (IllegalAccessException ex2) {}
        catch (InvocationTargetException ex) {
            goto Label_0050;
        }
    }
    
    private static boolean freeze(final Object ex) {
        try {
            return (boolean)TypefaceCompatApi26Impl.sFreeze.invoke(ex, new Object[0]);
        }
        catch (IllegalAccessException ex2) {}
        catch (InvocationTargetException ex) {
            goto Label_0021;
        }
    }
    
    private static boolean isFontFamilyPrivateAPIAvailable() {
        if (TypefaceCompatApi26Impl.sAddFontFromAssetManager == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return TypefaceCompatApi26Impl.sAddFontFromAssetManager != null;
    }
    
    private static Object newFamily() {
        try {
            return TypefaceCompatApi26Impl.sFontFamilyCtor.newInstance(new Object[0]);
        }
        catch (InstantiationException ex) {}
        catch (IllegalAccessException instance) {
            goto Label_0014;
        }
        catch (InvocationTargetException instance) {
            goto Label_0014;
        }
    }
    
    @Override
    public Typeface createFromFontFamilyFilesResourceEntry(final Context context, final FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, final Resources resources, int i) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context, fontFamilyFilesResourceEntry, resources, i);
        }
        final Object family = newFamily();
        final FontResourcesParserCompat.FontFileResourceEntry[] entries = fontFamilyFilesResourceEntry.getEntries();
        int length;
        FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry;
        String fileName;
        int weight;
        int n;
        for (length = entries.length, i = 0; i < length; ++i) {
            fontFileResourceEntry = entries[i];
            fileName = fontFileResourceEntry.getFileName();
            weight = fontFileResourceEntry.getWeight();
            if (fontFileResourceEntry.isItalic()) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (!addFontFromAssetManager(context, family, fileName, 0, weight, n)) {
                abortCreation(family);
                return null;
            }
        }
        if (!freeze(family)) {
            return null;
        }
        return createFromFamiliesWithDefault(family);
    }
    
    @Override
    public Typeface createFromFontInfo(final Context p0, @Nullable final CancellationSignal p1, @NonNull final FontsContractCompat.FontInfo[] p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: arraylength    
        //     2: iconst_1       
        //     3: if_icmpge       10
        //     6: aconst_null    
        //     7: astore_1       
        //     8: aload_1        
        //     9: areturn        
        //    10: invokestatic    android/support/v4/graphics/TypefaceCompatApi26Impl.isFontFamilyPrivateAPIAvailable:()Z
        //    13: ifne            144
        //    16: aload_0        
        //    17: aload_3        
        //    18: iload           4
        //    20: invokevirtual   android/support/v4/graphics/TypefaceCompatApi26Impl.findBestInfo:([Landroid/support/v4/provider/FontsContractCompat$FontInfo;I)Landroid/support/v4/provider/FontsContractCompat$FontInfo;
        //    23: astore          9
        //    25: aload_1        
        //    26: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //    29: astore_1       
        //    30: aload_1        
        //    31: aload           9
        //    33: invokevirtual   android/support/v4/provider/FontsContractCompat$FontInfo.getUri:()Landroid/net/Uri;
        //    36: ldc_w           "r"
        //    39: aload_2        
        //    40: invokevirtual   android/content/ContentResolver.openFileDescriptor:(Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
        //    43: astore_3       
        //    44: aconst_null    
        //    45: astore_2       
        //    46: new             Landroid/graphics/Typeface$Builder;
        //    49: dup            
        //    50: aload_3        
        //    51: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
        //    54: invokespecial   android/graphics/Typeface$Builder.<init>:(Ljava/io/FileDescriptor;)V
        //    57: aload           9
        //    59: invokevirtual   android/support/v4/provider/FontsContractCompat$FontInfo.getWeight:()I
        //    62: invokevirtual   android/graphics/Typeface$Builder.setWeight:(I)Landroid/graphics/Typeface$Builder;
        //    65: aload           9
        //    67: invokevirtual   android/support/v4/provider/FontsContractCompat$FontInfo.isItalic:()Z
        //    70: invokevirtual   android/graphics/Typeface$Builder.setItalic:(Z)Landroid/graphics/Typeface$Builder;
        //    73: invokevirtual   android/graphics/Typeface$Builder.build:()Landroid/graphics/Typeface;
        //    76: astore_1       
        //    77: aload_1        
        //    78: astore_2       
        //    79: aload_2        
        //    80: astore_1       
        //    81: aload_3        
        //    82: ifnull          8
        //    85: iconst_0       
        //    86: ifeq            104
        //    89: aload_3        
        //    90: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //    93: aload_2        
        //    94: areturn        
        //    95: astore_1       
        //    96: new             Ljava/lang/NullPointerException;
        //    99: dup            
        //   100: invokespecial   java/lang/NullPointerException.<init>:()V
        //   103: athrow         
        //   104: aload_3        
        //   105: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   108: aload_2        
        //   109: areturn        
        //   110: astore_2       
        //   111: aload_2        
        //   112: athrow         
        //   113: astore_1       
        //   114: aload_3        
        //   115: ifnull          126
        //   118: aload_2        
        //   119: ifnull          137
        //   122: aload_3        
        //   123: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   126: aload_1        
        //   127: athrow         
        //   128: astore_3       
        //   129: aload_2        
        //   130: aload_3        
        //   131: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   134: goto            126
        //   137: aload_3        
        //   138: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //   141: goto            126
        //   144: aload_1        
        //   145: aload_3        
        //   146: aload_2        
        //   147: invokestatic    android/support/v4/provider/FontsContractCompat.prepareFontData:(Landroid/content/Context;[Landroid/support/v4/provider/FontsContractCompat$FontInfo;Landroid/os/CancellationSignal;)Ljava/util/Map;
        //   150: astore_2       
        //   151: invokestatic    android/support/v4/graphics/TypefaceCompatApi26Impl.newFamily:()Ljava/lang/Object;
        //   154: astore          10
        //   156: iconst_0       
        //   157: istore          5
        //   159: aload_3        
        //   160: arraylength    
        //   161: istore          6
        //   163: iconst_0       
        //   164: istore          4
        //   166: iload           4
        //   168: iload           6
        //   170: if_icmpge       265
        //   173: aload_3        
        //   174: iload           4
        //   176: aaload         
        //   177: astore_1       
        //   178: aload_2        
        //   179: aload_1        
        //   180: invokevirtual   android/support/v4/provider/FontsContractCompat$FontInfo.getUri:()Landroid/net/Uri;
        //   183: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   188: checkcast       Ljava/nio/ByteBuffer;
        //   191: astore          9
        //   193: aload           9
        //   195: ifnonnull       207
        //   198: iload           4
        //   200: iconst_1       
        //   201: iadd           
        //   202: istore          4
        //   204: goto            166
        //   207: aload_1        
        //   208: invokevirtual   android/support/v4/provider/FontsContractCompat$FontInfo.getTtcIndex:()I
        //   211: istore          8
        //   213: aload_1        
        //   214: invokevirtual   android/support/v4/provider/FontsContractCompat$FontInfo.getWeight:()I
        //   217: istore          7
        //   219: aload_1        
        //   220: invokevirtual   android/support/v4/provider/FontsContractCompat$FontInfo.isItalic:()Z
        //   223: ifeq            253
        //   226: iconst_1       
        //   227: istore          5
        //   229: aload           10
        //   231: aload           9
        //   233: iload           8
        //   235: iload           7
        //   237: iload           5
        //   239: invokestatic    android/support/v4/graphics/TypefaceCompatApi26Impl.addFontFromBuffer:(Ljava/lang/Object;Ljava/nio/ByteBuffer;III)Z
        //   242: ifne            259
        //   245: aload           10
        //   247: invokestatic    android/support/v4/graphics/TypefaceCompatApi26Impl.abortCreation:(Ljava/lang/Object;)Z
        //   250: pop            
        //   251: aconst_null    
        //   252: areturn        
        //   253: iconst_0       
        //   254: istore          5
        //   256: goto            229
        //   259: iconst_1       
        //   260: istore          5
        //   262: goto            198
        //   265: iload           5
        //   267: ifne            278
        //   270: aload           10
        //   272: invokestatic    android/support/v4/graphics/TypefaceCompatApi26Impl.abortCreation:(Ljava/lang/Object;)Z
        //   275: pop            
        //   276: aconst_null    
        //   277: areturn        
        //   278: aload           10
        //   280: invokestatic    android/support/v4/graphics/TypefaceCompatApi26Impl.freeze:(Ljava/lang/Object;)Z
        //   283: ifne            288
        //   286: aconst_null    
        //   287: areturn        
        //   288: aload           10
        //   290: invokestatic    android/support/v4/graphics/TypefaceCompatApi26Impl.createFromFamiliesWithDefault:(Ljava/lang/Object;)Landroid/graphics/Typeface;
        //   293: areturn        
        //   294: astore_1       
        //   295: goto            114
        //   298: astore_1       
        //   299: aconst_null    
        //   300: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  30     44     298    301    Ljava/io/IOException;
        //  46     77     110    114    Ljava/lang/Throwable;
        //  46     77     294    298    Any
        //  89     93     95     104    Ljava/lang/Throwable;
        //  89     93     298    301    Ljava/io/IOException;
        //  96     104    298    301    Ljava/io/IOException;
        //  104    108    298    301    Ljava/io/IOException;
        //  111    113    113    114    Any
        //  122    126    128    137    Ljava/lang/Throwable;
        //  122    126    298    301    Ljava/io/IOException;
        //  126    128    298    301    Ljava/io/IOException;
        //  129    134    298    301    Ljava/io/IOException;
        //  137    141    298    301    Ljava/io/IOException;
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
    
    @Nullable
    @Override
    public Typeface createFromResourcesFontFile(final Context context, final Resources resources, final int n, final String s, final int n2) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context, resources, n, s, n2);
        }
        final Object family = newFamily();
        if (!addFontFromAssetManager(context, family, s, 0, -1, -1)) {
            abortCreation(family);
            return null;
        }
        if (!freeze(family)) {
            return null;
        }
        return createFromFamiliesWithDefault(family);
    }
}
