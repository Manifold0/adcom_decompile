// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v4.graphics;

import android.net.Uri;
import android.support.v4.util.SimpleArrayMap;
import android.support.annotation.NonNull;
import android.support.v4.provider.FontsContractCompat;
import android.support.annotation.Nullable;
import android.os.CancellationSignal;
import android.content.res.Resources;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.content.Context;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Array;
import android.graphics.Typeface;
import java.util.List;
import java.nio.ByteBuffer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.support.annotation.RequiresApi;

@RequiresApi(24)
@RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
class TypefaceCompatApi24Impl extends TypefaceCompatBaseImpl
{
    private static final String ADD_FONT_WEIGHT_STYLE_METHOD = "addFontWeightStyle";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String TAG = "TypefaceCompatApi24Impl";
    private static final Method sAddFontWeightStyle;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;
    
    static {
        try {
            final Class<?> forName = Class.forName("android.graphics.FontFamily");
            final Constructor<?> constructor = forName.getConstructor((Class<?>[])new Class[0]);
            final Method method = forName.getMethod("addFontWeightStyle", ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE);
            final Method method2 = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(forName, 1).getClass());
            sFontFamilyCtor = constructor;
            sFontFamily = forName;
            sAddFontWeightStyle = method;
            sCreateFromFamiliesWithDefault = method2;
        }
        catch (ClassNotFoundException ex) {}
        catch (NoSuchMethodException method) {
            goto Label_0095;
        }
    }
    
    private static boolean addFontWeightStyle(final Object ex, final ByteBuffer byteBuffer, final int n, final int n2, final boolean b) {
        try {
            return (boolean)TypefaceCompatApi24Impl.sAddFontWeightStyle.invoke(ex, byteBuffer, n, null, n2, b);
        }
        catch (IllegalAccessException ex2) {}
        catch (InvocationTargetException ex) {
            goto Label_0053;
        }
    }
    
    private static Typeface createFromFamiliesWithDefault(Object ex) {
        try {
            final Object instance = Array.newInstance(TypefaceCompatApi24Impl.sFontFamily, 1);
            Array.set(instance, 0, ex);
            ex = (InvocationTargetException)TypefaceCompatApi24Impl.sCreateFromFamiliesWithDefault.invoke(null, instance);
            return (Typeface)ex;
        }
        catch (IllegalAccessException ex2) {}
        catch (InvocationTargetException ex) {
            goto Label_0036;
        }
    }
    
    public static boolean isUsable() {
        if (TypefaceCompatApi24Impl.sAddFontWeightStyle == null) {
            Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return TypefaceCompatApi24Impl.sAddFontWeightStyle != null;
    }
    
    private static Object newFamily() {
        try {
            return TypefaceCompatApi24Impl.sFontFamilyCtor.newInstance(new Object[0]);
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
        final Object family = newFamily();
        final FontResourcesParserCompat.FontFileResourceEntry[] entries = fontFamilyFilesResourceEntry.getEntries();
        int length;
        FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry;
        for (length = entries.length, i = 0; i < length; ++i) {
            fontFileResourceEntry = entries[i];
            if (!addFontWeightStyle(family, TypefaceCompatUtil.copyToDirectBuffer(context, resources, fontFileResourceEntry.getResourceId()), 0, fontFileResourceEntry.getWeight(), fontFileResourceEntry.isItalic())) {
                return null;
            }
        }
        return createFromFamiliesWithDefault(family);
    }
    
    @Override
    public Typeface createFromFontInfo(final Context context, @Nullable final CancellationSignal cancellationSignal, @NonNull final FontsContractCompat.FontInfo[] array, int i) {
        final Object family = newFamily();
        final SimpleArrayMap<Uri, ByteBuffer> simpleArrayMap = new SimpleArrayMap<Uri, ByteBuffer>();
        int length;
        FontsContractCompat.FontInfo fontInfo;
        Uri uri;
        ByteBuffer mmap;
        for (length = array.length, i = 0; i < length; ++i) {
            fontInfo = array[i];
            uri = fontInfo.getUri();
            if ((mmap = simpleArrayMap.get(uri)) == null) {
                mmap = TypefaceCompatUtil.mmap(context, cancellationSignal, uri);
                simpleArrayMap.put(uri, mmap);
            }
            if (!addFontWeightStyle(family, mmap, fontInfo.getTtcIndex(), fontInfo.getWeight(), fontInfo.isItalic())) {
                return null;
            }
        }
        return createFromFamiliesWithDefault(family);
    }
}
