// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import com.facebook.FacebookContentProvider;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.Closeable;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import com.facebook.FacebookSdk;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import android.net.Uri;
import android.graphics.Bitmap;
import java.util.UUID;
import java.util.Iterator;
import com.facebook.FacebookException;
import java.io.IOException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.io.File;

public final class NativeAppCallAttachmentStore
{
    static final String ATTACHMENTS_DIR_NAME = "com.facebook.NativeAppCallAttachmentStore.files";
    private static final String TAG;
    private static File attachmentsDirectory;
    
    static {
        TAG = NativeAppCallAttachmentStore.class.getName();
    }
    
    private NativeAppCallAttachmentStore() {
    }
    
    public static void addAttachments(final Collection<Attachment> ex) {
        if (ex != null && ((Collection)ex).size() != 0) {
            if (NativeAppCallAttachmentStore.attachmentsDirectory == null) {
                cleanupAllAttachments();
            }
            while (true) {
                ensureAttachmentsDirectoryExists();
                final ArrayList<File> list = new ArrayList<File>();
                while (true) {
                    Attachment attachment = null;
                    File attachmentFile = null;
                    Label_0172: {
                        try {
                            final Iterator<Attachment> iterator = ((Collection<Attachment>)ex).iterator();
                            while (iterator.hasNext()) {
                                attachment = iterator.next();
                                if (attachment.shouldCreateFile) {
                                    attachmentFile = getAttachmentFile(attachment.callId, attachment.attachmentName, true);
                                    list.add(attachmentFile);
                                    if (attachment.bitmap == null) {
                                        break Label_0172;
                                    }
                                    processAttachmentBitmap(attachment.bitmap, attachmentFile);
                                }
                            }
                            return;
                        }
                        catch (IOException ex) {
                            Log.e(NativeAppCallAttachmentStore.TAG, "Got unexpected exception:" + ex);
                            for (final File file : list) {
                                try {
                                    file.delete();
                                }
                                catch (Exception ex2) {}
                            }
                            break;
                        }
                    }
                    if (attachment.originalUri != null) {
                        processAttachmentFile(attachment.originalUri, attachment.isContentUri, attachmentFile);
                        continue;
                    }
                    continue;
                }
            }
            throw new FacebookException(ex);
        }
    }
    
    public static void cleanupAllAttachments() {
        Utility.deleteDirectory(getAttachmentsDirectory());
    }
    
    public static void cleanupAttachmentsForCall(final UUID uuid) {
        final File attachmentsDirectoryForCall = getAttachmentsDirectoryForCall(uuid, false);
        if (attachmentsDirectoryForCall != null) {
            Utility.deleteDirectory(attachmentsDirectoryForCall);
        }
    }
    
    public static Attachment createAttachment(final UUID uuid, final Bitmap bitmap) {
        Validate.notNull(uuid, "callId");
        Validate.notNull(bitmap, "attachmentBitmap");
        return new Attachment(uuid, bitmap, (Uri)null);
    }
    
    public static Attachment createAttachment(final UUID uuid, final Uri uri) {
        Validate.notNull(uuid, "callId");
        Validate.notNull(uri, "attachmentUri");
        return new Attachment(uuid, (Bitmap)null, uri);
    }
    
    static File ensureAttachmentsDirectoryExists() {
        final File attachmentsDirectory = getAttachmentsDirectory();
        attachmentsDirectory.mkdirs();
        return attachmentsDirectory;
    }
    
    static File getAttachmentFile(final UUID uuid, final String s, final boolean b) throws IOException {
        final File attachmentsDirectoryForCall = getAttachmentsDirectoryForCall(uuid, b);
        if (attachmentsDirectoryForCall == null) {
            return null;
        }
        try {
            return new File(attachmentsDirectoryForCall, URLEncoder.encode(s, "UTF-8"));
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    
    static File getAttachmentsDirectory() {
        synchronized (NativeAppCallAttachmentStore.class) {
            if (NativeAppCallAttachmentStore.attachmentsDirectory == null) {
                NativeAppCallAttachmentStore.attachmentsDirectory = new File(FacebookSdk.getApplicationContext().getCacheDir(), "com.facebook.NativeAppCallAttachmentStore.files");
            }
            return NativeAppCallAttachmentStore.attachmentsDirectory;
        }
    }
    
    static File getAttachmentsDirectoryForCall(final UUID uuid, final boolean b) {
        File file;
        if (NativeAppCallAttachmentStore.attachmentsDirectory == null) {
            file = null;
        }
        else {
            final File file2 = file = new File(NativeAppCallAttachmentStore.attachmentsDirectory, uuid.toString());
            if (b) {
                file = file2;
                if (!file2.exists()) {
                    file2.mkdirs();
                    return file2;
                }
            }
        }
        return file;
    }
    
    public static File openAttachment(final UUID uuid, final String s) throws FileNotFoundException {
        if (Utility.isNullOrEmpty(s) || uuid == null) {
            throw new FileNotFoundException();
        }
        try {
            return getAttachmentFile(uuid, s, false);
        }
        catch (IOException ex) {
            throw new FileNotFoundException();
        }
    }
    
    private static void processAttachmentBitmap(final Bitmap bitmap, File file) throws IOException {
        file = (File)new FileOutputStream(file);
        try {
            bitmap.compress(Bitmap$CompressFormat.JPEG, 100, (OutputStream)file);
        }
        finally {
            Utility.closeQuietly((Closeable)file);
        }
    }
    
    private static void processAttachmentFile(final Uri uri, final boolean b, File file) throws IOException {
        file = (File)new FileOutputStream(file);
        Label_0036: {
            if (b) {
                break Label_0036;
            }
            try {
                InputStream openInputStream = new FileInputStream(uri.getPath());
                while (true) {
                    Utility.copyAndCloseInputStream(openInputStream, (OutputStream)file);
                    return;
                    openInputStream = FacebookSdk.getApplicationContext().getContentResolver().openInputStream(uri);
                    continue;
                }
            }
            finally {
                Utility.closeQuietly((Closeable)file);
            }
        }
    }
    
    public static final class Attachment
    {
        private final String attachmentName;
        private final String attachmentUrl;
        private Bitmap bitmap;
        private final UUID callId;
        private boolean isContentUri;
        private Uri originalUri;
        private boolean shouldCreateFile;
        
        private Attachment(final UUID callId, final Bitmap bitmap, final Uri originalUri) {
            boolean shouldCreateFile = true;
            this.callId = callId;
            this.bitmap = bitmap;
            this.originalUri = originalUri;
            if (originalUri != null) {
                final String scheme = originalUri.getScheme();
                if ("content".equalsIgnoreCase(scheme)) {
                    this.isContentUri = true;
                    if (originalUri.getAuthority() == null || originalUri.getAuthority().startsWith("media")) {
                        shouldCreateFile = false;
                    }
                    this.shouldCreateFile = shouldCreateFile;
                }
                else if ("file".equalsIgnoreCase(originalUri.getScheme())) {
                    this.shouldCreateFile = true;
                }
                else if (!Utility.isWebUri(originalUri)) {
                    throw new FacebookException("Unsupported scheme for media Uri : " + scheme);
                }
            }
            else {
                if (bitmap == null) {
                    throw new FacebookException("Cannot share media without a bitmap or Uri set");
                }
                this.shouldCreateFile = true;
            }
            String string;
            if (!this.shouldCreateFile) {
                string = null;
            }
            else {
                string = UUID.randomUUID().toString();
            }
            this.attachmentName = string;
            String attachmentUrl;
            if (!this.shouldCreateFile) {
                attachmentUrl = this.originalUri.toString();
            }
            else {
                attachmentUrl = FacebookContentProvider.getAttachmentUrl(FacebookSdk.getApplicationId(), callId, this.attachmentName);
            }
            this.attachmentUrl = attachmentUrl;
        }
        
        public String getAttachmentUrl() {
            return this.attachmentUrl;
        }
    }
}
