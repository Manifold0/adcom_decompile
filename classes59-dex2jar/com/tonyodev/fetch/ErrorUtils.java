// 
// Decompiled by Procyon v0.5.34
// 

package com.tonyodev.fetch;

final class ErrorUtils
{
    static final int BAD_REQUEST = -116;
    static final int CONNECTION_TIMED_OUT = -104;
    static final int DOWNLOAD_INTERRUPTED = -118;
    static final int ENQUEUE_ERROR = -117;
    static final int FILE_ALREADY_CREATED = -112;
    static final int FILE_NOT_CREATED = -102;
    static final int FILE_NOT_FOUND = -111;
    static final int HTTP_NOT_FOUND = -106;
    static final int ILLEGAL_STATE = -109;
    static final int INVALID_STATUS = -114;
    static final int N0_STORAGE_SPACE = -108;
    static final int NOT_USABLE = -115;
    static final int REQUEST_ALREADY_EXIST = -113;
    static final int SERVER_ERROR = -110;
    static final int THREAD_INTERRUPTED = -103;
    static final int UNKNOWN = -101;
    static final int UNKNOWN_HOST = -105;
    static final int WRITE_PERMISSION_DENIED = -107;
    
    private ErrorUtils() {
    }
    
    static int getCode(final String s) {
        if (s != null) {
            if (s.equalsIgnoreCase("FNC") || s.equalsIgnoreCase("open failed: ENOENT (No such file or directory)")) {
                return -102;
            }
            if (s.equalsIgnoreCase("TI")) {
                return -103;
            }
            if (s.equalsIgnoreCase("DIE")) {
                return -118;
            }
            if (s.equalsIgnoreCase("recvfrom failed: ETIMEDOUT (Connection timed out)") || s.equalsIgnoreCase("timeout")) {
                return -104;
            }
            if (s.equalsIgnoreCase("java.io.IOException: 404") || s.contains("No address associated with hostname")) {
                return -106;
            }
            if (s.contains("Unable to resolve host")) {
                return -105;
            }
            if (s.equalsIgnoreCase("open failed: EACCES (Permission denied)")) {
                return -107;
            }
            if (s.equalsIgnoreCase("write failed: ENOSPC (No space left on device)") || s.equalsIgnoreCase("database or disk is full (code 13)")) {
                return -108;
            }
            if (s.contains("SSRV:")) {
                return -110;
            }
            if (s.contains("column _file_path is not unique")) {
                return -113;
            }
        }
        return -101;
    }
}
