// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v4.provider;

import android.database.Cursor;
import android.support.v4.util.Preconditions;
import android.support.annotation.IntRange;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.provider.BaseColumns;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.TypefaceCompatUtil;
import java.util.HashMap;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Collection;
import java.util.Collections;
import android.content.pm.PackageManager;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import android.widget.TextView;
import android.support.annotation.VisibleForTesting;
import android.content.ContentResolver;
import android.net.Uri;
import android.content.ContentUris;
import android.os.Build$VERSION;
import android.net.Uri$Builder;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.content.res.Resources;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.ProviderInfo;
import java.util.Arrays;
import java.util.List;
import android.content.pm.Signature;
import android.support.v4.graphics.TypefaceCompat;
import android.support.annotation.Nullable;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.content.Context;
import android.support.v4.util.LruCache;
import android.support.annotation.GuardedBy;
import android.graphics.Typeface;
import java.util.ArrayList;
import android.support.v4.util.SimpleArrayMap;
import java.util.Comparator;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

public class FontsContractCompat
{
    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
    public static final String PARCEL_FONT_RESULTS = "font_results";
    @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
    public static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
    public static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final String TAG = "FontsContractCompat";
    private static final SelfDestructiveThread sBackgroundThread;
    private static final Comparator<byte[]> sByteArrayComparator;
    private static final Object sLock;
    @GuardedBy("sLock")
    private static final SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<Typeface>>> sPendingReplies;
    private static final LruCache<String, Typeface> sTypefaceCache;
    
    static {
        sTypefaceCache = new LruCache<String, Typeface>(16);
        sBackgroundThread = new SelfDestructiveThread("fonts", 10, 10000);
        sLock = new Object();
        sPendingReplies = new SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<Typeface>>>();
        sByteArrayComparator = new Comparator<byte[]>() {
            @Override
            public int compare(final byte[] array, final byte[] array2) {
                if (array.length != array2.length) {
                    return array.length - array2.length;
                }
                for (int i = 0; i < array.length; ++i) {
                    if (array[i] != array2[i]) {
                        return array[i] - array2[i];
                    }
                }
                return 0;
            }
        };
    }
    
    private FontsContractCompat() {
    }
    
    public static Typeface buildTypeface(@NonNull final Context context, @Nullable final CancellationSignal cancellationSignal, @NonNull final FontInfo[] array) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, array, 0);
    }
    
    private static List<byte[]> convertToByteArrayList(final Signature[] array) {
        final ArrayList<byte[]> list = new ArrayList<byte[]>();
        for (int i = 0; i < array.length; ++i) {
            list.add(array[i].toByteArray());
        }
        return list;
    }
    
    private static boolean equalsByteArrayList(final List<byte[]> list, final List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); ++i) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    @NonNull
    public static FontFamilyResult fetchFonts(@NonNull final Context context, @Nullable final CancellationSignal cancellationSignal, @NonNull final FontRequest fontRequest) throws PackageManager$NameNotFoundException {
        final ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return new FontFamilyResult(1, null);
        }
        return new FontFamilyResult(0, getFontFromProvider(context, fontRequest, provider.authority, cancellationSignal));
    }
    
    private static List<List<byte[]>> getCertificates(final FontRequest fontRequest, final Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }
    
    @NonNull
    @VisibleForTesting
    static FontInfo[] getFontFromProvider(Context context, final FontRequest fontRequest, String s, CancellationSignal cancellationSignal) {
        final ArrayList list = new ArrayList();
        final Uri build = new Uri$Builder().scheme("content").authority(s).build();
        final Uri build2 = new Uri$Builder().scheme("content").authority(s).appendPath("file").build();
        final String s2 = s = null;
        while (true) {
            try {
                if (Build$VERSION.SDK_INT > 16) {
                    s = s2;
                    final ContentResolver contentResolver = context.getContentResolver();
                    s = s2;
                    final String query = fontRequest.getQuery();
                    s = s2;
                    context = (Context)contentResolver.query(build, new String[] { "_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code" }, "query = ?", new String[] { query }, (String)null, cancellationSignal);
                }
                else {
                    s = s2;
                    final ContentResolver contentResolver2 = context.getContentResolver();
                    s = s2;
                    final String query2 = fontRequest.getQuery();
                    s = s2;
                    context = (Context)contentResolver2.query(build, new String[] { "_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code" }, "query = ?", new String[] { query2 }, (String)null);
                }
                Object o = list;
                if (context != null) {
                    o = list;
                    s = (String)context;
                    if (((Cursor)context).getCount() > 0) {
                        int columnIndex;
                        int columnIndex2;
                        int columnIndex3 = 0;
                        int columnIndex4;
                        int columnIndex5;
                        int columnIndex6;
                        int int1;
                        int int2;
                        Object o2 = null;
                        int int3;
                        boolean b;
                        Label_0297_Outer:Label_0317_Outer:Label_0333_Outer:Label_0354_Outer:
                        while (true) {
                            s = (String)context;
                            columnIndex = ((Cursor)context).getColumnIndex("result_code");
                            s = (String)context;
                            cancellationSignal = (CancellationSignal)new ArrayList();
                            Label_0527: {
                                while (true) {
                                Label_0521:
                                    while (true) {
                                    Label_0513:
                                        while (true) {
                                        Label_0496:
                                            while (true) {
                                            Label_0490:
                                                while (true) {
                                                    Label_0484: {
                                                        try {
                                                            columnIndex2 = ((Cursor)context).getColumnIndex("_id");
                                                            columnIndex3 = ((Cursor)context).getColumnIndex("file_id");
                                                            columnIndex4 = ((Cursor)context).getColumnIndex("font_ttc_index");
                                                            columnIndex5 = ((Cursor)context).getColumnIndex("font_weight");
                                                            columnIndex6 = ((Cursor)context).getColumnIndex("font_italic");
                                                            while (((Cursor)context).moveToNext()) {
                                                                if (columnIndex == -1) {
                                                                    break Label_0484;
                                                                }
                                                                int1 = ((Cursor)context).getInt(columnIndex);
                                                                if (columnIndex4 == -1) {
                                                                    break Label_0490;
                                                                }
                                                                int2 = ((Cursor)context).getInt(columnIndex4);
                                                                if (columnIndex3 != -1) {
                                                                    break Label_0496;
                                                                }
                                                                o2 = ContentUris.withAppendedId(build, ((Cursor)context).getLong(columnIndex2));
                                                                if (columnIndex5 == -1) {
                                                                    break Label_0513;
                                                                }
                                                                int3 = ((Cursor)context).getInt(columnIndex5);
                                                                if (columnIndex6 == -1 || ((Cursor)context).getInt(columnIndex6) != 1) {
                                                                    break Label_0521;
                                                                }
                                                                b = true;
                                                                ((ArrayList<FontInfo>)cancellationSignal).add(new FontInfo((Uri)o2, int2, int3, b, int1));
                                                            }
                                                            break Label_0527;
                                                        }
                                                        finally {
                                                            s = (String)context;
                                                            context = (Context)o2;
                                                        }
                                                    }
                                                    int1 = 0;
                                                    continue Label_0297_Outer;
                                                }
                                                int2 = 0;
                                                continue Label_0317_Outer;
                                            }
                                            o2 = ContentUris.withAppendedId(build2, ((Cursor)context).getLong(columnIndex3));
                                            continue Label_0333_Outer;
                                        }
                                        int3 = 400;
                                        continue Label_0354_Outer;
                                    }
                                    b = false;
                                    continue;
                                }
                                if (s != null) {
                                    ((Cursor)s).close();
                                }
                                throw context;
                            }
                            o = cancellationSignal;
                            break;
                        }
                    }
                }
                if (context != null) {
                    ((Cursor)context).close();
                }
                return (FontInfo[])((ArrayList)o).toArray(new FontInfo[0]);
            }
            finally {
                continue;
            }
            break;
        }
    }
    
    private static Typeface getFontInternal(final Context context, final FontRequest fontRequest, final int n) {
        final Typeface typeface = null;
        try {
            final FontFamilyResult fetchFonts = fetchFonts(context, null, fontRequest);
            Typeface fromFontInfo = typeface;
            if (fetchFonts.getStatusCode() == 0) {
                fromFontInfo = TypefaceCompat.createFromFontInfo(context, null, fetchFonts.getFonts(), n);
            }
            return fromFontInfo;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
    public static Typeface getFontSync(final Context context, final FontRequest fontRequest, @Nullable TextView textView, int n, final int n2, final int n3) {
        final String string = fontRequest.getIdentifier() + "-" + n3;
        final Typeface typeface = FontsContractCompat.sTypefaceCache.get(string);
        if (typeface != null) {
            return typeface;
        }
        if (n == 0) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0 && n2 == -1) {
            return getFontInternal(context, fontRequest, n3);
        }
        final Callable<Typeface> callable = new Callable<Typeface>() {
            @Override
            public Typeface call() throws Exception {
                final Typeface access$000 = getFontInternal(context, fontRequest, n3);
                if (access$000 != null) {
                    FontsContractCompat.sTypefaceCache.put(string, access$000);
                }
                return access$000;
            }
        };
        if (n != 0) {
            try {
                return FontsContractCompat.sBackgroundThread.postAndWait((Callable<Typeface>)callable, n2);
            }
            catch (InterruptedException ex) {
                return null;
            }
        }
        textView = (TextView)new SelfDestructiveThread.ReplyCallback<Typeface>() {
            final /* synthetic */ WeakReference val$textViewWeak = new WeakReference((T)textView);
            
            public void onReply(final Typeface typeface) {
                if (this.val$textViewWeak.get() != null) {
                    textView.setTypeface(typeface, n3);
                }
            }
        };
        synchronized (FontsContractCompat.sLock) {
            if (FontsContractCompat.sPendingReplies.containsKey(string)) {
                FontsContractCompat.sPendingReplies.get(string).add((SelfDestructiveThread.ReplyCallback<Typeface>)textView);
                return null;
            }
        }
        final ArrayList<SelfDestructiveThread.ReplyCallback<Typeface>> list = new ArrayList<SelfDestructiveThread.ReplyCallback<Typeface>>();
        list.add((SelfDestructiveThread.ReplyCallback<Typeface>)textView);
        FontsContractCompat.sPendingReplies.put(string, list);
        // monitorexit(fontRequest)
        final Callable<T> callable2;
        FontsContractCompat.sBackgroundThread.postAndReply((Callable<Object>)callable2, (SelfDestructiveThread.ReplyCallback<Object>)new SelfDestructiveThread.ReplyCallback<Typeface>() {
            public void onReply(final Typeface typeface) {
                synchronized (FontsContractCompat.sLock) {
                    final ArrayList<SelfDestructiveThread.ReplyCallback> list = FontsContractCompat.sPendingReplies.get(string);
                    FontsContractCompat.sPendingReplies.remove(string);
                    // monitorexit(FontsContractCompat.access$200())
                    for (int i = 0; i < list.size(); ++i) {
                        list.get(i).onReply(typeface);
                    }
                }
            }
        });
        return null;
    }
    
    @Nullable
    @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
    @VisibleForTesting
    public static ProviderInfo getProvider(@NonNull final PackageManager packageManager, @NonNull final FontRequest fontRequest, @Nullable final Resources resources) throws PackageManager$NameNotFoundException {
        final String providerAuthority = fontRequest.getProviderAuthority();
        final ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager$NameNotFoundException("No package found for authority: " + providerAuthority);
        }
        if (!resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            throw new PackageManager$NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
        }
        final List<byte[]> convertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
        Collections.sort((List<Object>)convertToByteArrayList, (Comparator<? super Object>)FontsContractCompat.sByteArrayComparator);
        final List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
        for (int i = 0; i < certificates.size(); ++i) {
            final ArrayList list = new ArrayList<byte[]>((Collection<? extends T>)certificates.get(i));
            Collections.sort((List<E>)list, (Comparator<? super E>)FontsContractCompat.sByteArrayComparator);
            if (equalsByteArrayList(convertToByteArrayList, (List<byte[]>)list)) {
                return resolveContentProvider;
            }
        }
        return null;
    }
    
    @RequiresApi(19)
    @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
    public static Map<Uri, ByteBuffer> prepareFontData(final Context context, final FontInfo[] array, final CancellationSignal cancellationSignal) {
        final HashMap<Uri, ByteBuffer> hashMap = new HashMap<Uri, ByteBuffer>();
        for (int length = array.length, i = 0; i < length; ++i) {
            final FontInfo fontInfo = array[i];
            if (fontInfo.getResultCode() == 0) {
                final Uri uri = fontInfo.getUri();
                if (!hashMap.containsKey(uri)) {
                    hashMap.put(uri, TypefaceCompatUtil.mmap(context, cancellationSignal, uri));
                }
            }
        }
        return (Map<Uri, ByteBuffer>)Collections.unmodifiableMap((Map<?, ?>)hashMap);
    }
    
    public static void requestFont(@NonNull final Context context, @NonNull final FontRequest fontRequest, @NonNull final FontRequestCallback fontRequestCallback, @NonNull final Handler handler) {
        handler.post((Runnable)new Runnable() {
            final /* synthetic */ Handler val$callerThreadHandler = new Handler();
            
            @Override
            public void run() {
                FontFamilyResult fetchFonts = null;
                Label_0117: {
                    Label_0100: {
                        try {
                            fetchFonts = FontsContractCompat.fetchFonts(context, null, fontRequest);
                            if (fetchFonts.getStatusCode() == 0) {
                                break Label_0117;
                            }
                            switch (fetchFonts.getStatusCode()) {
                                default: {
                                    this.val$callerThreadHandler.post((Runnable)new Runnable() {
                                        @Override
                                        public void run() {
                                            fontRequestCallback.onTypefaceRequestFailed(-3);
                                        }
                                    });
                                    return;
                                }
                                case 1: {
                                    break;
                                }
                                case 2: {
                                    break Label_0100;
                                }
                            }
                        }
                        catch (PackageManager$NameNotFoundException ex) {
                            this.val$callerThreadHandler.post((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-1);
                                }
                            });
                            return;
                        }
                        this.val$callerThreadHandler.post((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                fontRequestCallback.onTypefaceRequestFailed(-2);
                            }
                        });
                        return;
                    }
                    this.val$callerThreadHandler.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            fontRequestCallback.onTypefaceRequestFailed(-3);
                        }
                    });
                    return;
                }
                final FontInfo[] fonts = fetchFonts.getFonts();
                if (fonts == null || fonts.length == 0) {
                    this.val$callerThreadHandler.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            fontRequestCallback.onTypefaceRequestFailed(1);
                        }
                    });
                    return;
                }
                final int length = fonts.length;
                int i = 0;
                while (i < length) {
                    final FontInfo fontInfo = fonts[i];
                    if (fontInfo.getResultCode() != 0) {
                        final int resultCode = fontInfo.getResultCode();
                        if (resultCode < 0) {
                            this.val$callerThreadHandler.post((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            });
                            return;
                        }
                        this.val$callerThreadHandler.post((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                fontRequestCallback.onTypefaceRequestFailed(resultCode);
                            }
                        });
                        return;
                    }
                    else {
                        ++i;
                    }
                }
                final Typeface buildTypeface = FontsContractCompat.buildTypeface(context, null, fonts);
                if (buildTypeface == null) {
                    this.val$callerThreadHandler.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            fontRequestCallback.onTypefaceRequestFailed(-3);
                        }
                    });
                    return;
                }
                this.val$callerThreadHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        fontRequestCallback.onTypefaceRetrieved(buildTypeface);
                    }
                });
            }
        });
    }
    
    public static final class Columns implements BaseColumns
    {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";
    }
    
    public static class FontFamilyResult
    {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;
        
        @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
        public FontFamilyResult(final int mStatusCode, @Nullable final FontInfo[] mFonts) {
            this.mStatusCode = mStatusCode;
            this.mFonts = mFonts;
        }
        
        public FontInfo[] getFonts() {
            return this.mFonts;
        }
        
        public int getStatusCode() {
            return this.mStatusCode;
        }
        
        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
        @interface FontResultStatus {
        }
    }
    
    public static class FontInfo
    {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;
        
        @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
        public FontInfo(@NonNull final Uri uri, @IntRange(from = 0L) final int mTtcIndex, @IntRange(from = 1L, to = 1000L) final int mWeight, final boolean mItalic, final int mResultCode) {
            this.mUri = Preconditions.checkNotNull(uri);
            this.mTtcIndex = mTtcIndex;
            this.mWeight = mWeight;
            this.mItalic = mItalic;
            this.mResultCode = mResultCode;
        }
        
        public int getResultCode() {
            return this.mResultCode;
        }
        
        @IntRange(from = 0L)
        public int getTtcIndex() {
            return this.mTtcIndex;
        }
        
        @NonNull
        public Uri getUri() {
            return this.mUri;
        }
        
        @IntRange(from = 1L, to = 1000L)
        public int getWeight() {
            return this.mWeight;
        }
        
        public boolean isItalic() {
            return this.mItalic;
        }
    }
    
    public static class FontRequestCallback
    {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
        
        public void onTypefaceRequestFailed(final int n) {
        }
        
        public void onTypefaceRetrieved(final Typeface typeface) {
        }
        
        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
        @interface FontRequestFailReason {
        }
    }
}
