// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import android.database.Cursor;
import java.io.File;
import android.util.Pair;
import android.util.Log;
import com.facebook.internal.NativeAppCallAttachmentStore;
import java.io.FileNotFoundException;
import android.os.ParcelFileDescriptor;
import android.content.ContentValues;
import android.net.Uri;
import java.util.UUID;
import android.content.ContentProvider;

public class FacebookContentProvider extends ContentProvider
{
    private static final String ATTACHMENT_URL_BASE = "content://com.facebook.app.FacebookContentProvider";
    private static final String INVALID_FILE_NAME = "..";
    private static final String TAG;
    
    static {
        TAG = FacebookContentProvider.class.getName();
    }
    
    public static String getAttachmentUrl(final String s, final UUID uuid, final String s2) {
        return String.format("%s%s/%s/%s", "content://com.facebook.app.FacebookContentProvider", s, uuid.toString(), s2);
    }
    
    public int delete(final Uri uri, final String s, final String[] array) {
        return 0;
    }
    
    public String getType(final Uri uri) {
        return null;
    }
    
    public Uri insert(final Uri uri, final ContentValues contentValues) {
        return null;
    }
    
    public boolean onCreate() {
        return true;
    }
    
    public ParcelFileDescriptor openFile(final Uri uri, final String s) throws FileNotFoundException {
        final Pair<UUID, String> callIdAndAttachmentName = this.parseCallIdAndAttachmentName(uri);
        if (callIdAndAttachmentName == null) {
            throw new FileNotFoundException();
        }
        File openAttachment;
        try {
            openAttachment = NativeAppCallAttachmentStore.openAttachment((UUID)callIdAndAttachmentName.first, (String)callIdAndAttachmentName.second);
            if (openAttachment == null) {
                throw new FileNotFoundException();
            }
        }
        catch (FileNotFoundException ex) {
            Log.e(FacebookContentProvider.TAG, "Got unexpected exception:" + ex);
            throw ex;
        }
        return ParcelFileDescriptor.open(openAttachment, 268435456);
    }
    
    Pair<UUID, String> parseCallIdAndAttachmentName(final Uri uri) {
        try {
            final String[] split = uri.getPath().substring(1).split("/");
            final String s = split[0];
            final String s2 = split[1];
            if ("..".contentEquals(s) || "..".contentEquals(s2)) {
                throw new Exception();
            }
            return (Pair<UUID, String>)new Pair((Object)UUID.fromString(s), (Object)s2);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public Cursor query(final Uri uri, final String[] array, final String s, final String[] array2, final String s2) {
        return null;
    }
    
    public int update(final Uri uri, final ContentValues contentValues, final String s, final String[] array) {
        return 0;
    }
}
