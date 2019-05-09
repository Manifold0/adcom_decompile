// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.precache;

import com.ironsource.sdk.utils.SDKUtils;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;
import com.ironsource.sdk.utils.Logger;
import android.text.TextUtils;
import java.io.FileNotFoundException;
import java.util.concurrent.Callable;
import android.os.Message;
import java.io.File;
import android.os.Handler;
import com.ironsource.sdk.data.SSAFile;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import com.ironsource.sdk.utils.IronSourceStorageUtils;

public class DownloadManager
{
    public static final String CAMPAIGNS = "campaigns";
    public static final String FILE_ALREADY_EXIST = "file_already_exist";
    protected static final String FILE_NOT_FOUND_EXCEPTION = "file not found exception";
    public static final String GLOBAL_ASSETS = "globalAssets";
    protected static final String HTTP_EMPTY_RESPONSE = "http empty response";
    protected static final String HTTP_ERROR_CODE = "http error code";
    protected static final String HTTP_NOT_FOUND = "http not found";
    protected static final String HTTP_OK = "http ok";
    protected static final String IO_EXCEPTION = "io exception";
    protected static final String MALFORMED_URL_EXCEPTION = "malformed url exception";
    static final int MESSAGE_EMPTY_URL = 1007;
    static final int MESSAGE_FILE_DOWNLOAD_FAIL = 1017;
    static final int MESSAGE_FILE_DOWNLOAD_SUCCESS = 1016;
    static final int MESSAGE_FILE_NOT_FOUND_EXCEPTION = 1018;
    static final int MESSAGE_GENERAL_HTTP_ERROR_CODE = 1011;
    static final int MESSAGE_HTTP_EMPTY_RESPONSE = 1006;
    static final int MESSAGE_HTTP_NOT_FOUND = 1005;
    static final int MESSAGE_INIT_BC_FAIL = 1014;
    static final int MESSAGE_IO_EXCEPTION = 1009;
    static final int MESSAGE_MALFORMED_URL_EXCEPTION = 1004;
    static final int MESSAGE_NUM_OF_BANNERS_TO_CACHE = 1013;
    static final int MESSAGE_NUM_OF_BANNERS_TO_INIT_SUCCESS = 1012;
    static final int MESSAGE_OUT_OF_MEMORY_EXCEPTION = 1019;
    static final int MESSAGE_SOCKET_TIMEOUT_EXCEPTION = 1008;
    static final int MESSAGE_TMP_FILE_RENAME_FAILED = 1020;
    static final int MESSAGE_URI_SYNTAX_EXCEPTION = 1010;
    static final int MESSAGE_ZERO_CAMPAIGNS_TO_INIT_SUCCESS = 1015;
    public static final String NO_DISK_SPACE = "no_disk_space";
    public static final String NO_NETWORK_CONNECTION = "no_network_connection";
    public static final int OPERATION_TIMEOUT = 5000;
    protected static final String OUT_OF_MEMORY_EXCEPTION = "out of memory exception";
    public static final String SETTINGS = "settings";
    protected static final String SOCKET_TIMEOUT_EXCEPTION = "socket timeout exception";
    public static final String STORAGE_UNAVAILABLE = "sotrage_unavailable";
    private static final String TAG = "DownloadManager";
    private static final String TEMP_DIR_FOR_FILES = "temp";
    private static final String TEMP_PREFIX_FOR_FILES = "tmp_";
    private static final String UNABLE_TO_CREATE_FOLDER = "unable_to_create_folder";
    protected static final String URI_SYNTAX_EXCEPTION = "uri syntax exception";
    public static final String UTF8_CHARSET = "UTF-8";
    private static DownloadManager mDownloadManager;
    private String mCacheRootDirectory;
    private DownloadHandler mDownloadHandler;
    private Thread mMobileControllerThread;
    
    private DownloadManager(final String mCacheRootDirectory) {
        this.mCacheRootDirectory = mCacheRootDirectory;
        this.mDownloadHandler = this.getDownloadHandler();
        IronSourceStorageUtils.deleteFolder(this.mCacheRootDirectory, "temp");
        IronSourceStorageUtils.makeDir(this.mCacheRootDirectory, "temp");
    }
    
    static byte[] getBytes(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final byte[] array = new byte[8192];
        while (true) {
            final int read = inputStream.read(array, 0, array.length);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(array, 0, read);
        }
        byteArrayOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }
    
    public static DownloadManager getInstance(final String s) {
        synchronized (DownloadManager.class) {
            if (DownloadManager.mDownloadManager == null) {
                DownloadManager.mDownloadManager = new DownloadManager(s);
            }
            return DownloadManager.mDownloadManager;
        }
    }
    
    public void downloadFile(final SSAFile ssaFile) {
        new Thread(new SingleFileWorkerThread(ssaFile, this.mDownloadHandler, this.mCacheRootDirectory, this.getTempFilesDirectory())).start();
    }
    
    public void downloadMobileControllerFile(final SSAFile ssaFile) {
        (this.mMobileControllerThread = new Thread(new SingleFileWorkerThread(ssaFile, this.mDownloadHandler, this.mCacheRootDirectory, this.getTempFilesDirectory()))).start();
    }
    
    DownloadHandler getDownloadHandler() {
        return new DownloadHandler();
    }
    
    String getTempFilesDirectory() {
        return this.mCacheRootDirectory + File.separator + "temp";
    }
    
    public boolean isMobileControllerThreadLive() {
        return this.mMobileControllerThread != null && this.mMobileControllerThread.isAlive();
    }
    
    public void release() {
        DownloadManager.mDownloadManager = null;
        this.mDownloadHandler.release();
        this.mDownloadHandler = null;
    }
    
    public void setOnPreCacheCompletion(final OnPreCacheCompletion onPreCacheCompletion) {
        this.mDownloadHandler.setOnPreCacheCompletion(onPreCacheCompletion);
    }
    
    static class DownloadHandler extends Handler
    {
        OnPreCacheCompletion mListener;
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                default: {}
                case 1016: {
                    this.mListener.onFileDownloadSuccess((SSAFile)message.obj);
                }
                case 1017: {
                    this.mListener.onFileDownloadFail((SSAFile)message.obj);
                }
            }
        }
        
        public void release() {
            this.mListener = null;
        }
        
        void setOnPreCacheCompletion(final OnPreCacheCompletion mListener) {
            if (mListener == null) {
                throw new IllegalArgumentException();
            }
            this.mListener = mListener;
        }
    }
    
    static class FileWorkerThread implements Callable<Result>
    {
        private long mConnectionRetries;
        private String mDirectory;
        private String mFileName;
        private String mFileUrl;
        private String mTmpFilesDirectory;
        
        public FileWorkerThread(final String mFileUrl, final String mDirectory, final String mFileName, final long mConnectionRetries, final String mTmpFilesDirectory) {
            this.mFileUrl = mFileUrl;
            this.mDirectory = mDirectory;
            this.mFileName = mFileName;
            this.mConnectionRetries = mConnectionRetries;
            this.mTmpFilesDirectory = mTmpFilesDirectory;
        }
        
        @Override
        public Result call() {
            Result downloadContent = null;
            if (this.mConnectionRetries == 0L) {
                this.mConnectionRetries = 1L;
            }
            for (int n = 0; n < this.mConnectionRetries; ++n) {
                downloadContent = this.downloadContent(this.mFileUrl, n);
                final int responseCode = downloadContent.responseCode;
                if (responseCode != 1008 && responseCode != 1009) {
                    break;
                }
            }
            if (downloadContent != null && downloadContent.body != null) {
                final String string = this.mDirectory + File.separator + this.mFileName;
                final String string2 = this.mTmpFilesDirectory + File.separator + "tmp_" + this.mFileName;
                try {
                    if (this.saveFile(downloadContent.body, string2) == 0) {
                        downloadContent.responseCode = 1006;
                        return downloadContent;
                    }
                    if (!this.renameFile(string2, string)) {
                        downloadContent.responseCode = 1020;
                        return downloadContent;
                    }
                }
                catch (FileNotFoundException ex2) {
                    downloadContent.responseCode = 1018;
                    return downloadContent;
                }
                catch (Exception ex) {
                    if (!TextUtils.isEmpty((CharSequence)ex.getMessage())) {
                        Logger.i("DownloadManager", ex.getMessage());
                    }
                    downloadContent.responseCode = 1009;
                    return downloadContent;
                }
                catch (Error error) {
                    if (!TextUtils.isEmpty((CharSequence)error.getMessage())) {
                        Logger.i("DownloadManager", error.getMessage());
                    }
                    downloadContent.responseCode = 1019;
                }
            }
            return downloadContent;
        }
        
        Result downloadContent(final String p0, final int p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     4: invokespecial   com/ironsource/sdk/precache/DownloadManager$Result.<init>:()V
            //     7: astore          36
            //     9: aconst_null    
            //    10: astore          30
            //    12: aconst_null    
            //    13: astore          31
            //    15: aconst_null    
            //    16: astore          32
            //    18: aconst_null    
            //    19: astore          33
            //    21: aconst_null    
            //    22: astore          34
            //    24: aconst_null    
            //    25: astore          35
            //    27: aconst_null    
            //    28: astore          8
            //    30: iconst_0       
            //    31: istore          6
            //    33: iconst_0       
            //    34: istore          5
            //    36: aload_1        
            //    37: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
            //    40: ifeq            60
            //    43: aload           36
            //    45: aload_1        
            //    46: putfield        com/ironsource/sdk/precache/DownloadManager$Result.url:Ljava/lang/String;
            //    49: aload           36
            //    51: sipush          1007
            //    54: putfield        com/ironsource/sdk/precache/DownloadManager$Result.responseCode:I
            //    57: aload           36
            //    59: areturn        
            //    60: aconst_null    
            //    61: astore          23
            //    63: aconst_null    
            //    64: astore          24
            //    66: aconst_null    
            //    67: astore          25
            //    69: aconst_null    
            //    70: astore          26
            //    72: aconst_null    
            //    73: astore          27
            //    75: aconst_null    
            //    76: astore          28
            //    78: aconst_null    
            //    79: astore          29
            //    81: aconst_null    
            //    82: astore          7
            //    84: aload           8
            //    86: astore          19
            //    88: aload           23
            //    90: astore          15
            //    92: aload           30
            //    94: astore          20
            //    96: aload           24
            //    98: astore          16
            //   100: aload           31
            //   102: astore          21
            //   104: aload           25
            //   106: astore          17
            //   108: aload           32
            //   110: astore          22
            //   112: aload           26
            //   114: astore          18
            //   116: aload           33
            //   118: astore          13
            //   120: aload           27
            //   122: astore          11
            //   124: iload           5
            //   126: istore          4
            //   128: aload           34
            //   130: astore          14
            //   132: aload           28
            //   134: astore          12
            //   136: aload           35
            //   138: astore          10
            //   140: aload           29
            //   142: astore          9
            //   144: iload           6
            //   146: istore_3       
            //   147: new             Ljava/net/URL;
            //   150: dup            
            //   151: aload_1        
            //   152: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
            //   155: astore          37
            //   157: aload           8
            //   159: astore          19
            //   161: aload           23
            //   163: astore          15
            //   165: aload           30
            //   167: astore          20
            //   169: aload           24
            //   171: astore          16
            //   173: aload           31
            //   175: astore          21
            //   177: aload           25
            //   179: astore          17
            //   181: aload           32
            //   183: astore          22
            //   185: aload           26
            //   187: astore          18
            //   189: aload           33
            //   191: astore          13
            //   193: aload           27
            //   195: astore          11
            //   197: iload           5
            //   199: istore          4
            //   201: aload           34
            //   203: astore          14
            //   205: aload           28
            //   207: astore          12
            //   209: aload           35
            //   211: astore          10
            //   213: aload           29
            //   215: astore          9
            //   217: iload           6
            //   219: istore_3       
            //   220: aload           37
            //   222: invokevirtual   java/net/URL.toURI:()Ljava/net/URI;
            //   225: pop            
            //   226: aload           8
            //   228: astore          19
            //   230: aload           23
            //   232: astore          15
            //   234: aload           30
            //   236: astore          20
            //   238: aload           24
            //   240: astore          16
            //   242: aload           31
            //   244: astore          21
            //   246: aload           25
            //   248: astore          17
            //   250: aload           32
            //   252: astore          22
            //   254: aload           26
            //   256: astore          18
            //   258: aload           33
            //   260: astore          13
            //   262: aload           27
            //   264: astore          11
            //   266: iload           5
            //   268: istore          4
            //   270: aload           34
            //   272: astore          14
            //   274: aload           28
            //   276: astore          12
            //   278: aload           35
            //   280: astore          10
            //   282: aload           29
            //   284: astore          9
            //   286: iload           6
            //   288: istore_3       
            //   289: aload           37
            //   291: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
            //   294: checkcast       Ljava/net/HttpURLConnection;
            //   297: astore          8
            //   299: aload           8
            //   301: astore          19
            //   303: aload           23
            //   305: astore          15
            //   307: aload           8
            //   309: astore          20
            //   311: aload           24
            //   313: astore          16
            //   315: aload           8
            //   317: astore          21
            //   319: aload           25
            //   321: astore          17
            //   323: aload           8
            //   325: astore          22
            //   327: aload           26
            //   329: astore          18
            //   331: aload           8
            //   333: astore          13
            //   335: aload           27
            //   337: astore          11
            //   339: iload           5
            //   341: istore          4
            //   343: aload           8
            //   345: astore          14
            //   347: aload           28
            //   349: astore          12
            //   351: aload           8
            //   353: astore          10
            //   355: aload           29
            //   357: astore          9
            //   359: iload           6
            //   361: istore_3       
            //   362: aload           8
            //   364: ldc             "GET"
            //   366: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
            //   369: aload           8
            //   371: astore          19
            //   373: aload           23
            //   375: astore          15
            //   377: aload           8
            //   379: astore          20
            //   381: aload           24
            //   383: astore          16
            //   385: aload           8
            //   387: astore          21
            //   389: aload           25
            //   391: astore          17
            //   393: aload           8
            //   395: astore          22
            //   397: aload           26
            //   399: astore          18
            //   401: aload           8
            //   403: astore          13
            //   405: aload           27
            //   407: astore          11
            //   409: iload           5
            //   411: istore          4
            //   413: aload           8
            //   415: astore          14
            //   417: aload           28
            //   419: astore          12
            //   421: aload           8
            //   423: astore          10
            //   425: aload           29
            //   427: astore          9
            //   429: iload           6
            //   431: istore_3       
            //   432: aload           8
            //   434: sipush          5000
            //   437: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
            //   440: aload           8
            //   442: astore          19
            //   444: aload           23
            //   446: astore          15
            //   448: aload           8
            //   450: astore          20
            //   452: aload           24
            //   454: astore          16
            //   456: aload           8
            //   458: astore          21
            //   460: aload           25
            //   462: astore          17
            //   464: aload           8
            //   466: astore          22
            //   468: aload           26
            //   470: astore          18
            //   472: aload           8
            //   474: astore          13
            //   476: aload           27
            //   478: astore          11
            //   480: iload           5
            //   482: istore          4
            //   484: aload           8
            //   486: astore          14
            //   488: aload           28
            //   490: astore          12
            //   492: aload           8
            //   494: astore          10
            //   496: aload           29
            //   498: astore          9
            //   500: iload           6
            //   502: istore_3       
            //   503: aload           8
            //   505: sipush          5000
            //   508: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
            //   511: aload           8
            //   513: astore          19
            //   515: aload           23
            //   517: astore          15
            //   519: aload           8
            //   521: astore          20
            //   523: aload           24
            //   525: astore          16
            //   527: aload           8
            //   529: astore          21
            //   531: aload           25
            //   533: astore          17
            //   535: aload           8
            //   537: astore          22
            //   539: aload           26
            //   541: astore          18
            //   543: aload           8
            //   545: astore          13
            //   547: aload           27
            //   549: astore          11
            //   551: iload           5
            //   553: istore          4
            //   555: aload           8
            //   557: astore          14
            //   559: aload           28
            //   561: astore          12
            //   563: aload           8
            //   565: astore          10
            //   567: aload           29
            //   569: astore          9
            //   571: iload           6
            //   573: istore_3       
            //   574: aload           8
            //   576: invokevirtual   java/net/HttpURLConnection.connect:()V
            //   579: aload           8
            //   581: astore          19
            //   583: aload           23
            //   585: astore          15
            //   587: aload           8
            //   589: astore          20
            //   591: aload           24
            //   593: astore          16
            //   595: aload           8
            //   597: astore          21
            //   599: aload           25
            //   601: astore          17
            //   603: aload           8
            //   605: astore          22
            //   607: aload           26
            //   609: astore          18
            //   611: aload           8
            //   613: astore          13
            //   615: aload           27
            //   617: astore          11
            //   619: iload           5
            //   621: istore          4
            //   623: aload           8
            //   625: astore          14
            //   627: aload           28
            //   629: astore          12
            //   631: aload           8
            //   633: astore          10
            //   635: aload           29
            //   637: astore          9
            //   639: iload           6
            //   641: istore_3       
            //   642: aload           8
            //   644: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
            //   647: istore          5
            //   649: iload           5
            //   651: sipush          200
            //   654: if_icmplt       1404
            //   657: iload           5
            //   659: sipush          400
            //   662: if_icmplt       818
            //   665: goto            1404
            //   668: iload           5
            //   670: sipush          200
            //   673: if_icmpeq       782
            //   676: aload           8
            //   678: astore          19
            //   680: aload           7
            //   682: astore          15
            //   684: aload           8
            //   686: astore          20
            //   688: aload           7
            //   690: astore          16
            //   692: aload           8
            //   694: astore          21
            //   696: aload           7
            //   698: astore          17
            //   700: aload           8
            //   702: astore          22
            //   704: aload           7
            //   706: astore          18
            //   708: aload           8
            //   710: astore          13
            //   712: aload           7
            //   714: astore          11
            //   716: iload           5
            //   718: istore          4
            //   720: aload           8
            //   722: astore          14
            //   724: aload           7
            //   726: astore          12
            //   728: aload           8
            //   730: astore          10
            //   732: aload           7
            //   734: astore          9
            //   736: iload           5
            //   738: istore_3       
            //   739: ldc             "DownloadManager"
            //   741: new             Ljava/lang/StringBuilder;
            //   744: dup            
            //   745: invokespecial   java/lang/StringBuilder.<init>:()V
            //   748: ldc             " RESPONSE CODE: "
            //   750: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   753: iload           5
            //   755: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   758: ldc             " URL: "
            //   760: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   763: aload_1        
            //   764: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   767: ldc             " ATTEMPT: "
            //   769: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   772: iload_2        
            //   773: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   776: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   779: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
            //   782: aload           7
            //   784: ifnull          792
            //   787: aload           7
            //   789: invokevirtual   java/io/InputStream.close:()V
            //   792: aload           8
            //   794: ifnull          802
            //   797: aload           8
            //   799: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   802: aload           36
            //   804: aload_1        
            //   805: putfield        com/ironsource/sdk/precache/DownloadManager$Result.url:Ljava/lang/String;
            //   808: aload           36
            //   810: iload           5
            //   812: putfield        com/ironsource/sdk/precache/DownloadManager$Result.responseCode:I
            //   815: aload           36
            //   817: areturn        
            //   818: aload           8
            //   820: astore          19
            //   822: aload           23
            //   824: astore          15
            //   826: aload           8
            //   828: astore          20
            //   830: aload           24
            //   832: astore          16
            //   834: aload           8
            //   836: astore          21
            //   838: aload           25
            //   840: astore          17
            //   842: aload           8
            //   844: astore          22
            //   846: aload           26
            //   848: astore          18
            //   850: aload           8
            //   852: astore          13
            //   854: aload           27
            //   856: astore          11
            //   858: iload           5
            //   860: istore          4
            //   862: aload           8
            //   864: astore          14
            //   866: aload           28
            //   868: astore          12
            //   870: aload           8
            //   872: astore          10
            //   874: aload           29
            //   876: astore          9
            //   878: iload           5
            //   880: istore_3       
            //   881: aload           8
            //   883: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
            //   886: astore          7
            //   888: aload           8
            //   890: astore          19
            //   892: aload           7
            //   894: astore          15
            //   896: aload           8
            //   898: astore          20
            //   900: aload           7
            //   902: astore          16
            //   904: aload           8
            //   906: astore          21
            //   908: aload           7
            //   910: astore          17
            //   912: aload           8
            //   914: astore          22
            //   916: aload           7
            //   918: astore          18
            //   920: aload           8
            //   922: astore          13
            //   924: aload           7
            //   926: astore          11
            //   928: iload           5
            //   930: istore          4
            //   932: aload           8
            //   934: astore          14
            //   936: aload           7
            //   938: astore          12
            //   940: aload           8
            //   942: astore          10
            //   944: aload           7
            //   946: astore          9
            //   948: iload           5
            //   950: istore_3       
            //   951: aload           36
            //   953: aload_0        
            //   954: aload           7
            //   956: invokevirtual   com/ironsource/sdk/precache/DownloadManager$FileWorkerThread.getBytes:(Ljava/io/InputStream;)[B
            //   959: putfield        com/ironsource/sdk/precache/DownloadManager$Result.body:[B
            //   962: goto            668
            //   965: astore          7
            //   967: aload           15
            //   969: ifnull          977
            //   972: aload           15
            //   974: invokevirtual   java/io/InputStream.close:()V
            //   977: aload           19
            //   979: ifnull          987
            //   982: aload           19
            //   984: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   987: aload           36
            //   989: aload_1        
            //   990: putfield        com/ironsource/sdk/precache/DownloadManager$Result.url:Ljava/lang/String;
            //   993: aload           36
            //   995: sipush          1004
            //   998: putfield        com/ironsource/sdk/precache/DownloadManager$Result.responseCode:I
            //  1001: aload           36
            //  1003: areturn        
            //  1004: astore          7
            //  1006: aload           7
            //  1008: invokevirtual   java/io/IOException.printStackTrace:()V
            //  1011: goto            792
            //  1014: astore          7
            //  1016: aload           7
            //  1018: invokevirtual   java/io/IOException.printStackTrace:()V
            //  1021: goto            977
            //  1024: astore          7
            //  1026: aload           16
            //  1028: ifnull          1036
            //  1031: aload           16
            //  1033: invokevirtual   java/io/InputStream.close:()V
            //  1036: aload           20
            //  1038: ifnull          1046
            //  1041: aload           20
            //  1043: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //  1046: aload           36
            //  1048: aload_1        
            //  1049: putfield        com/ironsource/sdk/precache/DownloadManager$Result.url:Ljava/lang/String;
            //  1052: aload           36
            //  1054: sipush          1010
            //  1057: putfield        com/ironsource/sdk/precache/DownloadManager$Result.responseCode:I
            //  1060: aload           36
            //  1062: areturn        
            //  1063: astore          7
            //  1065: aload           7
            //  1067: invokevirtual   java/io/IOException.printStackTrace:()V
            //  1070: goto            1036
            //  1073: astore          7
            //  1075: aload           17
            //  1077: ifnull          1085
            //  1080: aload           17
            //  1082: invokevirtual   java/io/InputStream.close:()V
            //  1085: aload           21
            //  1087: ifnull          1095
            //  1090: aload           21
            //  1092: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //  1095: aload           36
            //  1097: aload_1        
            //  1098: putfield        com/ironsource/sdk/precache/DownloadManager$Result.url:Ljava/lang/String;
            //  1101: aload           36
            //  1103: sipush          1008
            //  1106: putfield        com/ironsource/sdk/precache/DownloadManager$Result.responseCode:I
            //  1109: aload           36
            //  1111: areturn        
            //  1112: astore          7
            //  1114: aload           7
            //  1116: invokevirtual   java/io/IOException.printStackTrace:()V
            //  1119: goto            1085
            //  1122: astore          7
            //  1124: aload           18
            //  1126: ifnull          1134
            //  1129: aload           18
            //  1131: invokevirtual   java/io/InputStream.close:()V
            //  1134: aload           22
            //  1136: ifnull          1144
            //  1139: aload           22
            //  1141: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //  1144: aload           36
            //  1146: aload_1        
            //  1147: putfield        com/ironsource/sdk/precache/DownloadManager$Result.url:Ljava/lang/String;
            //  1150: aload           36
            //  1152: sipush          1018
            //  1155: putfield        com/ironsource/sdk/precache/DownloadManager$Result.responseCode:I
            //  1158: aload           36
            //  1160: areturn        
            //  1161: astore          7
            //  1163: aload           7
            //  1165: invokevirtual   java/io/IOException.printStackTrace:()V
            //  1168: goto            1134
            //  1171: astore          7
            //  1173: aload           13
            //  1175: astore          10
            //  1177: aload           11
            //  1179: astore          9
            //  1181: iload           4
            //  1183: istore_3       
            //  1184: aload           7
            //  1186: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
            //  1189: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
            //  1192: ifne            1216
            //  1195: aload           13
            //  1197: astore          10
            //  1199: aload           11
            //  1201: astore          9
            //  1203: iload           4
            //  1205: istore_3       
            //  1206: ldc             "DownloadManager"
            //  1208: aload           7
            //  1210: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
            //  1213: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
            //  1216: aload           11
            //  1218: ifnull          1226
            //  1221: aload           11
            //  1223: invokevirtual   java/io/InputStream.close:()V
            //  1226: aload           13
            //  1228: ifnull          1236
            //  1231: aload           13
            //  1233: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //  1236: aload           36
            //  1238: aload_1        
            //  1239: putfield        com/ironsource/sdk/precache/DownloadManager$Result.url:Ljava/lang/String;
            //  1242: aload           36
            //  1244: sipush          1009
            //  1247: putfield        com/ironsource/sdk/precache/DownloadManager$Result.responseCode:I
            //  1250: aload           36
            //  1252: areturn        
            //  1253: astore          7
            //  1255: aload           7
            //  1257: invokevirtual   java/io/IOException.printStackTrace:()V
            //  1260: goto            1226
            //  1263: astore          7
            //  1265: sipush          1019
            //  1268: istore_2       
            //  1269: aload           14
            //  1271: astore          10
            //  1273: aload           12
            //  1275: astore          9
            //  1277: iload_2        
            //  1278: istore_3       
            //  1279: aload           7
            //  1281: invokevirtual   java/lang/Error.getMessage:()Ljava/lang/String;
            //  1284: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
            //  1287: ifne            1310
            //  1290: aload           14
            //  1292: astore          10
            //  1294: aload           12
            //  1296: astore          9
            //  1298: iload_2        
            //  1299: istore_3       
            //  1300: ldc             "DownloadManager"
            //  1302: aload           7
            //  1304: invokevirtual   java/lang/Error.getMessage:()Ljava/lang/String;
            //  1307: invokestatic    com/ironsource/sdk/utils/Logger.i:(Ljava/lang/String;Ljava/lang/String;)V
            //  1310: aload           12
            //  1312: ifnull          1320
            //  1315: aload           12
            //  1317: invokevirtual   java/io/InputStream.close:()V
            //  1320: aload           14
            //  1322: ifnull          1330
            //  1325: aload           14
            //  1327: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //  1330: aload           36
            //  1332: aload_1        
            //  1333: putfield        com/ironsource/sdk/precache/DownloadManager$Result.url:Ljava/lang/String;
            //  1336: aload           36
            //  1338: sipush          1019
            //  1341: putfield        com/ironsource/sdk/precache/DownloadManager$Result.responseCode:I
            //  1344: aload           36
            //  1346: areturn        
            //  1347: astore          7
            //  1349: aload           7
            //  1351: invokevirtual   java/io/IOException.printStackTrace:()V
            //  1354: goto            1320
            //  1357: astore          7
            //  1359: aload           9
            //  1361: ifnull          1369
            //  1364: aload           9
            //  1366: invokevirtual   java/io/InputStream.close:()V
            //  1369: aload           10
            //  1371: ifnull          1379
            //  1374: aload           10
            //  1376: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //  1379: aload           36
            //  1381: aload_1        
            //  1382: putfield        com/ironsource/sdk/precache/DownloadManager$Result.url:Ljava/lang/String;
            //  1385: aload           36
            //  1387: iload_3        
            //  1388: putfield        com/ironsource/sdk/precache/DownloadManager$Result.responseCode:I
            //  1391: aload           7
            //  1393: athrow         
            //  1394: astore          8
            //  1396: aload           8
            //  1398: invokevirtual   java/io/IOException.printStackTrace:()V
            //  1401: goto            1369
            //  1404: sipush          1011
            //  1407: istore          5
            //  1409: goto            668
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                             
            //  -----  -----  -----  -----  ---------------------------------
            //  147    157    965    1004   Ljava/net/MalformedURLException;
            //  147    157    1024   1073   Ljava/net/URISyntaxException;
            //  147    157    1073   1122   Ljava/net/SocketTimeoutException;
            //  147    157    1122   1171   Ljava/io/FileNotFoundException;
            //  147    157    1171   1263   Ljava/lang/Exception;
            //  147    157    1263   1357   Ljava/lang/Error;
            //  147    157    1357   1404   Any
            //  220    226    965    1004   Ljava/net/MalformedURLException;
            //  220    226    1024   1073   Ljava/net/URISyntaxException;
            //  220    226    1073   1122   Ljava/net/SocketTimeoutException;
            //  220    226    1122   1171   Ljava/io/FileNotFoundException;
            //  220    226    1171   1263   Ljava/lang/Exception;
            //  220    226    1263   1357   Ljava/lang/Error;
            //  220    226    1357   1404   Any
            //  289    299    965    1004   Ljava/net/MalformedURLException;
            //  289    299    1024   1073   Ljava/net/URISyntaxException;
            //  289    299    1073   1122   Ljava/net/SocketTimeoutException;
            //  289    299    1122   1171   Ljava/io/FileNotFoundException;
            //  289    299    1171   1263   Ljava/lang/Exception;
            //  289    299    1263   1357   Ljava/lang/Error;
            //  289    299    1357   1404   Any
            //  362    369    965    1004   Ljava/net/MalformedURLException;
            //  362    369    1024   1073   Ljava/net/URISyntaxException;
            //  362    369    1073   1122   Ljava/net/SocketTimeoutException;
            //  362    369    1122   1171   Ljava/io/FileNotFoundException;
            //  362    369    1171   1263   Ljava/lang/Exception;
            //  362    369    1263   1357   Ljava/lang/Error;
            //  362    369    1357   1404   Any
            //  432    440    965    1004   Ljava/net/MalformedURLException;
            //  432    440    1024   1073   Ljava/net/URISyntaxException;
            //  432    440    1073   1122   Ljava/net/SocketTimeoutException;
            //  432    440    1122   1171   Ljava/io/FileNotFoundException;
            //  432    440    1171   1263   Ljava/lang/Exception;
            //  432    440    1263   1357   Ljava/lang/Error;
            //  432    440    1357   1404   Any
            //  503    511    965    1004   Ljava/net/MalformedURLException;
            //  503    511    1024   1073   Ljava/net/URISyntaxException;
            //  503    511    1073   1122   Ljava/net/SocketTimeoutException;
            //  503    511    1122   1171   Ljava/io/FileNotFoundException;
            //  503    511    1171   1263   Ljava/lang/Exception;
            //  503    511    1263   1357   Ljava/lang/Error;
            //  503    511    1357   1404   Any
            //  574    579    965    1004   Ljava/net/MalformedURLException;
            //  574    579    1024   1073   Ljava/net/URISyntaxException;
            //  574    579    1073   1122   Ljava/net/SocketTimeoutException;
            //  574    579    1122   1171   Ljava/io/FileNotFoundException;
            //  574    579    1171   1263   Ljava/lang/Exception;
            //  574    579    1263   1357   Ljava/lang/Error;
            //  574    579    1357   1404   Any
            //  642    649    965    1004   Ljava/net/MalformedURLException;
            //  642    649    1024   1073   Ljava/net/URISyntaxException;
            //  642    649    1073   1122   Ljava/net/SocketTimeoutException;
            //  642    649    1122   1171   Ljava/io/FileNotFoundException;
            //  642    649    1171   1263   Ljava/lang/Exception;
            //  642    649    1263   1357   Ljava/lang/Error;
            //  642    649    1357   1404   Any
            //  739    782    965    1004   Ljava/net/MalformedURLException;
            //  739    782    1024   1073   Ljava/net/URISyntaxException;
            //  739    782    1073   1122   Ljava/net/SocketTimeoutException;
            //  739    782    1122   1171   Ljava/io/FileNotFoundException;
            //  739    782    1171   1263   Ljava/lang/Exception;
            //  739    782    1263   1357   Ljava/lang/Error;
            //  739    782    1357   1404   Any
            //  787    792    1004   1014   Ljava/io/IOException;
            //  881    888    965    1004   Ljava/net/MalformedURLException;
            //  881    888    1024   1073   Ljava/net/URISyntaxException;
            //  881    888    1073   1122   Ljava/net/SocketTimeoutException;
            //  881    888    1122   1171   Ljava/io/FileNotFoundException;
            //  881    888    1171   1263   Ljava/lang/Exception;
            //  881    888    1263   1357   Ljava/lang/Error;
            //  881    888    1357   1404   Any
            //  951    962    965    1004   Ljava/net/MalformedURLException;
            //  951    962    1024   1073   Ljava/net/URISyntaxException;
            //  951    962    1073   1122   Ljava/net/SocketTimeoutException;
            //  951    962    1122   1171   Ljava/io/FileNotFoundException;
            //  951    962    1171   1263   Ljava/lang/Exception;
            //  951    962    1263   1357   Ljava/lang/Error;
            //  951    962    1357   1404   Any
            //  972    977    1014   1024   Ljava/io/IOException;
            //  1031   1036   1063   1073   Ljava/io/IOException;
            //  1080   1085   1112   1122   Ljava/io/IOException;
            //  1129   1134   1161   1171   Ljava/io/IOException;
            //  1184   1195   1357   1404   Any
            //  1206   1216   1357   1404   Any
            //  1221   1226   1253   1263   Ljava/io/IOException;
            //  1279   1290   1357   1404   Any
            //  1300   1310   1357   1404   Any
            //  1315   1320   1347   1357   Ljava/io/IOException;
            //  1364   1369   1394   1404   Ljava/io/IOException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 676, Size: 676
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
        
        byte[] getBytes(final InputStream inputStream) throws IOException {
            return DownloadManager.getBytes(inputStream);
        }
        
        boolean renameFile(final String s, final String s2) throws Exception {
            return IronSourceStorageUtils.renameFile(s, s2);
        }
        
        int saveFile(final byte[] array, final String s) throws Exception {
            return IronSourceStorageUtils.saveFile(array, s);
        }
    }
    
    public interface OnPreCacheCompletion
    {
        void onFileDownloadFail(final SSAFile p0);
        
        void onFileDownloadSuccess(final SSAFile p0);
    }
    
    static class Result
    {
        byte[] body;
        int responseCode;
        public String url;
    }
    
    static class SingleFileWorkerThread implements Runnable
    {
        private String mCacheRootDirectory;
        private long mConnectionRetries;
        Handler mDownloadHandler;
        private String mFile;
        private String mFileName;
        private String mPath;
        private final String mTempFilesDirectory;
        
        SingleFileWorkerThread(final SSAFile ssaFile, final Handler mDownloadHandler, final String mCacheRootDirectory, final String mTempFilesDirectory) {
            this.mFile = ssaFile.getFile();
            this.mPath = ssaFile.getPath();
            this.mFileName = this.guessFileName(this.mFile);
            this.mConnectionRetries = this.getConnectionRetries();
            this.mCacheRootDirectory = mCacheRootDirectory;
            this.mDownloadHandler = mDownloadHandler;
            this.mTempFilesDirectory = mTempFilesDirectory;
        }
        
        String convertErrorCodeToMessage(final int n) {
            final String string = "not defined message for " + n;
            switch (n) {
                default: {
                    return string;
                }
                case 1004: {
                    return "malformed url exception";
                }
                case 404:
                case 1005: {
                    return "http not found";
                }
                case 1006: {
                    return "http empty response";
                }
                case 1010: {
                    return "uri syntax exception";
                }
                case 1011: {
                    return "http error code";
                }
                case 1018: {
                    return "file not found exception";
                }
                case 1008: {
                    return "socket timeout exception";
                }
                case 1009: {
                    return "io exception";
                }
                case 1019: {
                    return "out of memory exception";
                }
            }
        }
        
        public long getConnectionRetries() {
            return Long.parseLong(IronSourceSharedPrefHelper.getSupersonicPrefHelper().getConnectionRetries());
        }
        
        FileWorkerThread getFileWorkerThread(final String s, final String s2, final String s3, final long n, final String s4) {
            return new FileWorkerThread(s, s2, s3, n, s4);
        }
        
        Message getMessage() {
            return new Message();
        }
        
        String guessFileName(final String s) {
            return SDKUtils.getFileName(this.mFile);
        }
        
        String makeDir(final String s, final String s2) {
            return IronSourceStorageUtils.makeDir(s, s2);
        }
        
        @Override
        public void run() {
            final SSAFile obj = new SSAFile(this.mFileName, this.mPath);
            final Message message = this.getMessage();
            message.obj = obj;
            final String dir = this.makeDir(this.mCacheRootDirectory, this.mPath);
            if (dir == null) {
                message.what = 1017;
                obj.setErrMsg("unable_to_create_folder");
                this.mDownloadHandler.sendMessage(message);
                return;
            }
            final int responseCode = this.getFileWorkerThread(this.mFile, dir, obj.getFile(), this.mConnectionRetries, this.mTempFilesDirectory).call().responseCode;
            switch (responseCode) {
                default: {}
                case 200: {
                    message.what = 1016;
                    this.mDownloadHandler.sendMessage(message);
                }
                case 404:
                case 1004:
                case 1005:
                case 1006:
                case 1008:
                case 1009:
                case 1010:
                case 1011:
                case 1018:
                case 1019: {
                    final String convertErrorCodeToMessage = this.convertErrorCodeToMessage(responseCode);
                    message.what = 1017;
                    obj.setErrMsg(convertErrorCodeToMessage);
                    this.mDownloadHandler.sendMessage(message);
                }
            }
        }
    }
}
