// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.cache;

import java.util.Iterator;
import android.os.Bundle;
import java.util.Arrays;
import android.os.Message;
import com.unity3d.services.core.api.Request;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.log.DeviceLog;
import android.os.SystemClock;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.unity3d.services.core.request.WebRequest;
import android.os.Handler;

class CacheThreadHandler extends Handler
{
    private boolean _active;
    private boolean _canceled;
    private WebRequest _currentRequest;
    
    CacheThreadHandler() {
        this._currentRequest = null;
        this._canceled = false;
        this._active = false;
    }
    
    private void downloadFile(final String p0, final String p1, final int p2, final int p3, final int p4, final HashMap<String, List<String>> p5, final boolean p6) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/unity3d/services/core/cache/CacheThreadHandler._canceled:Z
        //     4: ifne            15
        //     7: aload_1        
        //     8: ifnull          15
        //    11: aload_2        
        //    12: ifnonnull       16
        //    15: return         
        //    16: new             Ljava/io/File;
        //    19: dup            
        //    20: aload_2        
        //    21: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    24: astore          18
        //    26: iload           7
        //    28: ifeq            119
        //    31: new             Ljava/lang/StringBuilder;
        //    34: dup            
        //    35: invokespecial   java/lang/StringBuilder.<init>:()V
        //    38: ldc             "Unity Ads cache: resuming download "
        //    40: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    43: aload_1        
        //    44: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    47: ldc             " to "
        //    49: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    52: aload_2        
        //    53: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    56: ldc             " at "
        //    58: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    61: aload           18
        //    63: invokevirtual   java/io/File.length:()J
        //    66: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    69: ldc             " bytes"
        //    71: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    74: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    77: invokestatic    com/unity3d/services/core/log/DeviceLog.debug:(Ljava/lang/String;)V
        //    80: invokestatic    com/unity3d/services/core/device/Device.isActiveNetworkConnected:()Z
        //    83: ifne            153
        //    86: ldc             "Unity Ads cache: download cancelled, no internet connection available"
        //    88: invokestatic    com/unity3d/services/core/log/DeviceLog.debug:(Ljava/lang/String;)V
        //    91: invokestatic    com/unity3d/services/core/webview/WebViewApp.getCurrentApp:()Lcom/unity3d/services/core/webview/WebViewApp;
        //    94: getstatic       com/unity3d/services/core/webview/WebViewEventCategory.CACHE:Lcom/unity3d/services/core/webview/WebViewEventCategory;
        //    97: getstatic       com/unity3d/services/core/cache/CacheEvent.DOWNLOAD_ERROR:Lcom/unity3d/services/core/cache/CacheEvent;
        //   100: iconst_2       
        //   101: anewarray       Ljava/lang/Object;
        //   104: dup            
        //   105: iconst_0       
        //   106: getstatic       com/unity3d/services/core/cache/CacheError.NO_INTERNET:Lcom/unity3d/services/core/cache/CacheError;
        //   109: aastore        
        //   110: dup            
        //   111: iconst_1       
        //   112: aload_1        
        //   113: aastore        
        //   114: invokevirtual   com/unity3d/services/core/webview/WebViewApp.sendEvent:(Ljava/lang/Enum;Ljava/lang/Enum;[Ljava/lang/Object;)Z
        //   117: pop            
        //   118: return         
        //   119: new             Ljava/lang/StringBuilder;
        //   122: dup            
        //   123: invokespecial   java/lang/StringBuilder.<init>:()V
        //   126: ldc             "Unity Ads cache: start downloading "
        //   128: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   131: aload_1        
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: ldc             " to "
        //   137: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   140: aload_2        
        //   141: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   144: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   147: invokestatic    com/unity3d/services/core/log/DeviceLog.debug:(Ljava/lang/String;)V
        //   150: goto            80
        //   153: aload_0        
        //   154: iconst_1       
        //   155: putfield        com/unity3d/services/core/cache/CacheThreadHandler._active:Z
        //   158: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   161: lstore          8
        //   163: aconst_null    
        //   164: astore          14
        //   166: aconst_null    
        //   167: astore          15
        //   169: aconst_null    
        //   170: astore          16
        //   172: aconst_null    
        //   173: astore          17
        //   175: aconst_null    
        //   176: astore_2       
        //   177: aconst_null    
        //   178: astore          13
        //   180: new             Ljava/io/FileOutputStream;
        //   183: dup            
        //   184: aload           18
        //   186: iload           7
        //   188: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;Z)V
        //   191: astore          12
        //   193: aload_0        
        //   194: aload_0        
        //   195: aload_1        
        //   196: iload_3        
        //   197: iload           4
        //   199: aload           6
        //   201: invokespecial   com/unity3d/services/core/cache/CacheThreadHandler.getWebRequest:(Ljava/lang/String;IILjava/util/HashMap;)Lcom/unity3d/services/core/request/WebRequest;
        //   204: putfield        com/unity3d/services/core/cache/CacheThreadHandler._currentRequest:Lcom/unity3d/services/core/request/WebRequest;
        //   207: aload_0        
        //   208: getfield        com/unity3d/services/core/cache/CacheThreadHandler._currentRequest:Lcom/unity3d/services/core/request/WebRequest;
        //   211: new             Lcom/unity3d/services/core/cache/CacheThreadHandler$1;
        //   214: dup            
        //   215: aload_0        
        //   216: aload           18
        //   218: iload           5
        //   220: invokespecial   com/unity3d/services/core/cache/CacheThreadHandler$1.<init>:(Lcom/unity3d/services/core/cache/CacheThreadHandler;Ljava/io/File;I)V
        //   223: invokevirtual   com/unity3d/services/core/request/WebRequest.setProgressListener:(Lcom/unity3d/services/core/request/IWebRequestProgressListener;)V
        //   226: aload_0        
        //   227: getfield        com/unity3d/services/core/cache/CacheThreadHandler._currentRequest:Lcom/unity3d/services/core/request/WebRequest;
        //   230: aload           12
        //   232: invokevirtual   com/unity3d/services/core/request/WebRequest.makeStreamRequest:(Ljava/io/OutputStream;)J
        //   235: lstore          10
        //   237: aload_0        
        //   238: iconst_0       
        //   239: putfield        com/unity3d/services/core/cache/CacheThreadHandler._active:Z
        //   242: aload_0        
        //   243: lload           8
        //   245: aload_1        
        //   246: aload           18
        //   248: lload           10
        //   250: aload_0        
        //   251: getfield        com/unity3d/services/core/cache/CacheThreadHandler._currentRequest:Lcom/unity3d/services/core/request/WebRequest;
        //   254: invokevirtual   com/unity3d/services/core/request/WebRequest.getContentLength:()J
        //   257: aload_0        
        //   258: getfield        com/unity3d/services/core/cache/CacheThreadHandler._currentRequest:Lcom/unity3d/services/core/request/WebRequest;
        //   261: invokevirtual   com/unity3d/services/core/request/WebRequest.isCanceled:()Z
        //   264: aload_0        
        //   265: getfield        com/unity3d/services/core/cache/CacheThreadHandler._currentRequest:Lcom/unity3d/services/core/request/WebRequest;
        //   268: invokevirtual   com/unity3d/services/core/request/WebRequest.getResponseCode:()I
        //   271: aload_0        
        //   272: getfield        com/unity3d/services/core/cache/CacheThreadHandler._currentRequest:Lcom/unity3d/services/core/request/WebRequest;
        //   275: invokevirtual   com/unity3d/services/core/request/WebRequest.getResponseHeaders:()Ljava/util/Map;
        //   278: invokespecial   com/unity3d/services/core/cache/CacheThreadHandler.postProcessDownload:(JLjava/lang/String;Ljava/io/File;JJZILjava/util/Map;)V
        //   281: aload_0        
        //   282: aconst_null    
        //   283: putfield        com/unity3d/services/core/cache/CacheThreadHandler._currentRequest:Lcom/unity3d/services/core/request/WebRequest;
        //   286: aload           12
        //   288: ifnull          296
        //   291: aload           12
        //   293: invokevirtual   java/io/FileOutputStream.close:()V
        //   296: return         
        //   297: astore_2       
        //   298: ldc             "Error closing stream"
        //   300: aload_2        
        //   301: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   304: invokestatic    com/unity3d/services/core/webview/WebViewApp.getCurrentApp:()Lcom/unity3d/services/core/webview/WebViewApp;
        //   307: getstatic       com/unity3d/services/core/webview/WebViewEventCategory.CACHE:Lcom/unity3d/services/core/webview/WebViewEventCategory;
        //   310: getstatic       com/unity3d/services/core/cache/CacheEvent.DOWNLOAD_ERROR:Lcom/unity3d/services/core/cache/CacheEvent;
        //   313: iconst_3       
        //   314: anewarray       Ljava/lang/Object;
        //   317: dup            
        //   318: iconst_0       
        //   319: getstatic       com/unity3d/services/core/cache/CacheError.FILE_IO_ERROR:Lcom/unity3d/services/core/cache/CacheError;
        //   322: aastore        
        //   323: dup            
        //   324: iconst_1       
        //   325: aload_1        
        //   326: aastore        
        //   327: dup            
        //   328: iconst_2       
        //   329: aload_2        
        //   330: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   333: aastore        
        //   334: invokevirtual   com/unity3d/services/core/webview/WebViewApp.sendEvent:(Ljava/lang/Enum;Ljava/lang/Enum;[Ljava/lang/Object;)Z
        //   337: pop            
        //   338: return         
        //   339: astore          12
        //   341: aload           13
        //   343: astore          6
        //   345: aload           6
        //   347: astore_2       
        //   348: ldc             "Couldn't create target file"
        //   350: aload           12
        //   352: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   355: aload           6
        //   357: astore_2       
        //   358: aload_0        
        //   359: iconst_0       
        //   360: putfield        com/unity3d/services/core/cache/CacheThreadHandler._active:Z
        //   363: aload           6
        //   365: astore_2       
        //   366: invokestatic    com/unity3d/services/core/webview/WebViewApp.getCurrentApp:()Lcom/unity3d/services/core/webview/WebViewApp;
        //   369: getstatic       com/unity3d/services/core/webview/WebViewEventCategory.CACHE:Lcom/unity3d/services/core/webview/WebViewEventCategory;
        //   372: getstatic       com/unity3d/services/core/cache/CacheEvent.DOWNLOAD_ERROR:Lcom/unity3d/services/core/cache/CacheEvent;
        //   375: iconst_3       
        //   376: anewarray       Ljava/lang/Object;
        //   379: dup            
        //   380: iconst_0       
        //   381: getstatic       com/unity3d/services/core/cache/CacheError.FILE_IO_ERROR:Lcom/unity3d/services/core/cache/CacheError;
        //   384: aastore        
        //   385: dup            
        //   386: iconst_1       
        //   387: aload_1        
        //   388: aastore        
        //   389: dup            
        //   390: iconst_2       
        //   391: aload           12
        //   393: invokevirtual   java/io/FileNotFoundException.getMessage:()Ljava/lang/String;
        //   396: aastore        
        //   397: invokevirtual   com/unity3d/services/core/webview/WebViewApp.sendEvent:(Ljava/lang/Enum;Ljava/lang/Enum;[Ljava/lang/Object;)Z
        //   400: pop            
        //   401: aload_0        
        //   402: aconst_null    
        //   403: putfield        com/unity3d/services/core/cache/CacheThreadHandler._currentRequest:Lcom/unity3d/services/core/request/WebRequest;
        //   406: aload           6
        //   408: ifnull          15
        //   411: aload           6
        //   413: invokevirtual   java/io/FileOutputStream.close:()V
        //   416: return         
        //   417: astore_2       
        //   418: ldc             "Error closing stream"
        //   420: aload_2        
        //   421: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   424: invokestatic    com/unity3d/services/core/webview/WebViewApp.getCurrentApp:()Lcom/unity3d/services/core/webview/WebViewApp;
        //   427: getstatic       com/unity3d/services/core/webview/WebViewEventCategory.CACHE:Lcom/unity3d/services/core/webview/WebViewEventCategory;
        //   430: getstatic       com/unity3d/services/core/cache/CacheEvent.DOWNLOAD_ERROR:Lcom/unity3d/services/core/cache/CacheEvent;
        //   433: iconst_3       
        //   434: anewarray       Ljava/lang/Object;
        //   437: dup            
        //   438: iconst_0       
        //   439: getstatic       com/unity3d/services/core/cache/CacheError.FILE_IO_ERROR:Lcom/unity3d/services/core/cache/CacheError;
        //   442: aastore        
        //   443: dup            
        //   444: iconst_1       
        //   445: aload_1        
        //   446: aastore        
        //   447: dup            
        //   448: iconst_2       
        //   449: aload_2        
        //   450: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   453: aastore        
        //   454: invokevirtual   com/unity3d/services/core/webview/WebViewApp.sendEvent:(Ljava/lang/Enum;Ljava/lang/Enum;[Ljava/lang/Object;)Z
        //   457: pop            
        //   458: return         
        //   459: astore          12
        //   461: aload           14
        //   463: astore          6
        //   465: aload           6
        //   467: astore_2       
        //   468: ldc             "Malformed URL"
        //   470: aload           12
        //   472: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   475: aload           6
        //   477: astore_2       
        //   478: aload_0        
        //   479: iconst_0       
        //   480: putfield        com/unity3d/services/core/cache/CacheThreadHandler._active:Z
        //   483: aload           6
        //   485: astore_2       
        //   486: invokestatic    com/unity3d/services/core/webview/WebViewApp.getCurrentApp:()Lcom/unity3d/services/core/webview/WebViewApp;
        //   489: getstatic       com/unity3d/services/core/webview/WebViewEventCategory.CACHE:Lcom/unity3d/services/core/webview/WebViewEventCategory;
        //   492: getstatic       com/unity3d/services/core/cache/CacheEvent.DOWNLOAD_ERROR:Lcom/unity3d/services/core/cache/CacheEvent;
        //   495: iconst_3       
        //   496: anewarray       Ljava/lang/Object;
        //   499: dup            
        //   500: iconst_0       
        //   501: getstatic       com/unity3d/services/core/cache/CacheError.MALFORMED_URL:Lcom/unity3d/services/core/cache/CacheError;
        //   504: aastore        
        //   505: dup            
        //   506: iconst_1       
        //   507: aload_1        
        //   508: aastore        
        //   509: dup            
        //   510: iconst_2       
        //   511: aload           12
        //   513: invokevirtual   java/net/MalformedURLException.getMessage:()Ljava/lang/String;
        //   516: aastore        
        //   517: invokevirtual   com/unity3d/services/core/webview/WebViewApp.sendEvent:(Ljava/lang/Enum;Ljava/lang/Enum;[Ljava/lang/Object;)Z
        //   520: pop            
        //   521: aload_0        
        //   522: aconst_null    
        //   523: putfield        com/unity3d/services/core/cache/CacheThreadHandler._currentRequest:Lcom/unity3d/services/core/request/WebRequest;
        //   526: aload           6
        //   528: ifnull          15
        //   531: aload           6
        //   533: invokevirtual   java/io/FileOutputStream.close:()V
        //   536: return         
        //   537: astore_2       
        //   538: ldc             "Error closing stream"
        //   540: aload_2        
        //   541: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   544: invokestatic    com/unity3d/services/core/webview/WebViewApp.getCurrentApp:()Lcom/unity3d/services/core/webview/WebViewApp;
        //   547: getstatic       com/unity3d/services/core/webview/WebViewEventCategory.CACHE:Lcom/unity3d/services/core/webview/WebViewEventCategory;
        //   550: getstatic       com/unity3d/services/core/cache/CacheEvent.DOWNLOAD_ERROR:Lcom/unity3d/services/core/cache/CacheEvent;
        //   553: iconst_3       
        //   554: anewarray       Ljava/lang/Object;
        //   557: dup            
        //   558: iconst_0       
        //   559: getstatic       com/unity3d/services/core/cache/CacheError.FILE_IO_ERROR:Lcom/unity3d/services/core/cache/CacheError;
        //   562: aastore        
        //   563: dup            
        //   564: iconst_1       
        //   565: aload_1        
        //   566: aastore        
        //   567: dup            
        //   568: iconst_2       
        //   569: aload_2        
        //   570: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   573: aastore        
        //   574: invokevirtual   com/unity3d/services/core/webview/WebViewApp.sendEvent:(Ljava/lang/Enum;Ljava/lang/Enum;[Ljava/lang/Object;)Z
        //   577: pop            
        //   578: return         
        //   579: astore          12
        //   581: aload           15
        //   583: astore          6
        //   585: aload           6
        //   587: astore_2       
        //   588: ldc             "Couldn't request stream"
        //   590: aload           12
        //   592: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   595: aload           6
        //   597: astore_2       
        //   598: aload_0        
        //   599: iconst_0       
        //   600: putfield        com/unity3d/services/core/cache/CacheThreadHandler._active:Z
        //   603: aload           6
        //   605: astore_2       
        //   606: invokestatic    com/unity3d/services/core/webview/WebViewApp.getCurrentApp:()Lcom/unity3d/services/core/webview/WebViewApp;
        //   609: getstatic       com/unity3d/services/core/webview/WebViewEventCategory.CACHE:Lcom/unity3d/services/core/webview/WebViewEventCategory;
        //   612: getstatic       com/unity3d/services/core/cache/CacheEvent.DOWNLOAD_ERROR:Lcom/unity3d/services/core/cache/CacheEvent;
        //   615: iconst_3       
        //   616: anewarray       Ljava/lang/Object;
        //   619: dup            
        //   620: iconst_0       
        //   621: getstatic       com/unity3d/services/core/cache/CacheError.FILE_IO_ERROR:Lcom/unity3d/services/core/cache/CacheError;
        //   624: aastore        
        //   625: dup            
        //   626: iconst_1       
        //   627: aload_1        
        //   628: aastore        
        //   629: dup            
        //   630: iconst_2       
        //   631: aload           12
        //   633: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   636: aastore        
        //   637: invokevirtual   com/unity3d/services/core/webview/WebViewApp.sendEvent:(Ljava/lang/Enum;Ljava/lang/Enum;[Ljava/lang/Object;)Z
        //   640: pop            
        //   641: aload_0        
        //   642: aconst_null    
        //   643: putfield        com/unity3d/services/core/cache/CacheThreadHandler._currentRequest:Lcom/unity3d/services/core/request/WebRequest;
        //   646: aload           6
        //   648: ifnull          15
        //   651: aload           6
        //   653: invokevirtual   java/io/FileOutputStream.close:()V
        //   656: return         
        //   657: astore_2       
        //   658: ldc             "Error closing stream"
        //   660: aload_2        
        //   661: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   664: invokestatic    com/unity3d/services/core/webview/WebViewApp.getCurrentApp:()Lcom/unity3d/services/core/webview/WebViewApp;
        //   667: getstatic       com/unity3d/services/core/webview/WebViewEventCategory.CACHE:Lcom/unity3d/services/core/webview/WebViewEventCategory;
        //   670: getstatic       com/unity3d/services/core/cache/CacheEvent.DOWNLOAD_ERROR:Lcom/unity3d/services/core/cache/CacheEvent;
        //   673: iconst_3       
        //   674: anewarray       Ljava/lang/Object;
        //   677: dup            
        //   678: iconst_0       
        //   679: getstatic       com/unity3d/services/core/cache/CacheError.FILE_IO_ERROR:Lcom/unity3d/services/core/cache/CacheError;
        //   682: aastore        
        //   683: dup            
        //   684: iconst_1       
        //   685: aload_1        
        //   686: aastore        
        //   687: dup            
        //   688: iconst_2       
        //   689: aload_2        
        //   690: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   693: aastore        
        //   694: invokevirtual   com/unity3d/services/core/webview/WebViewApp.sendEvent:(Ljava/lang/Enum;Ljava/lang/Enum;[Ljava/lang/Object;)Z
        //   697: pop            
        //   698: return         
        //   699: astore          12
        //   701: aload           16
        //   703: astore          6
        //   705: aload           6
        //   707: astore_2       
        //   708: ldc             "Illegal state"
        //   710: aload           12
        //   712: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   715: aload           6
        //   717: astore_2       
        //   718: aload_0        
        //   719: iconst_0       
        //   720: putfield        com/unity3d/services/core/cache/CacheThreadHandler._active:Z
        //   723: aload           6
        //   725: astore_2       
        //   726: invokestatic    com/unity3d/services/core/webview/WebViewApp.getCurrentApp:()Lcom/unity3d/services/core/webview/WebViewApp;
        //   729: getstatic       com/unity3d/services/core/webview/WebViewEventCategory.CACHE:Lcom/unity3d/services/core/webview/WebViewEventCategory;
        //   732: getstatic       com/unity3d/services/core/cache/CacheEvent.DOWNLOAD_ERROR:Lcom/unity3d/services/core/cache/CacheEvent;
        //   735: iconst_3       
        //   736: anewarray       Ljava/lang/Object;
        //   739: dup            
        //   740: iconst_0       
        //   741: getstatic       com/unity3d/services/core/cache/CacheError.ILLEGAL_STATE:Lcom/unity3d/services/core/cache/CacheError;
        //   744: aastore        
        //   745: dup            
        //   746: iconst_1       
        //   747: aload_1        
        //   748: aastore        
        //   749: dup            
        //   750: iconst_2       
        //   751: aload           12
        //   753: invokevirtual   java/lang/IllegalStateException.getMessage:()Ljava/lang/String;
        //   756: aastore        
        //   757: invokevirtual   com/unity3d/services/core/webview/WebViewApp.sendEvent:(Ljava/lang/Enum;Ljava/lang/Enum;[Ljava/lang/Object;)Z
        //   760: pop            
        //   761: aload_0        
        //   762: aconst_null    
        //   763: putfield        com/unity3d/services/core/cache/CacheThreadHandler._currentRequest:Lcom/unity3d/services/core/request/WebRequest;
        //   766: aload           6
        //   768: ifnull          15
        //   771: aload           6
        //   773: invokevirtual   java/io/FileOutputStream.close:()V
        //   776: return         
        //   777: astore_2       
        //   778: ldc             "Error closing stream"
        //   780: aload_2        
        //   781: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   784: invokestatic    com/unity3d/services/core/webview/WebViewApp.getCurrentApp:()Lcom/unity3d/services/core/webview/WebViewApp;
        //   787: getstatic       com/unity3d/services/core/webview/WebViewEventCategory.CACHE:Lcom/unity3d/services/core/webview/WebViewEventCategory;
        //   790: getstatic       com/unity3d/services/core/cache/CacheEvent.DOWNLOAD_ERROR:Lcom/unity3d/services/core/cache/CacheEvent;
        //   793: iconst_3       
        //   794: anewarray       Ljava/lang/Object;
        //   797: dup            
        //   798: iconst_0       
        //   799: getstatic       com/unity3d/services/core/cache/CacheError.FILE_IO_ERROR:Lcom/unity3d/services/core/cache/CacheError;
        //   802: aastore        
        //   803: dup            
        //   804: iconst_1       
        //   805: aload_1        
        //   806: aastore        
        //   807: dup            
        //   808: iconst_2       
        //   809: aload_2        
        //   810: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   813: aastore        
        //   814: invokevirtual   com/unity3d/services/core/webview/WebViewApp.sendEvent:(Ljava/lang/Enum;Ljava/lang/Enum;[Ljava/lang/Object;)Z
        //   817: pop            
        //   818: return         
        //   819: astore          12
        //   821: aload           17
        //   823: astore          6
        //   825: aload           6
        //   827: astore_2       
        //   828: ldc             "Network error"
        //   830: aload           12
        //   832: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   835: aload           6
        //   837: astore_2       
        //   838: aload_0        
        //   839: iconst_0       
        //   840: putfield        com/unity3d/services/core/cache/CacheThreadHandler._active:Z
        //   843: aload           6
        //   845: astore_2       
        //   846: invokestatic    com/unity3d/services/core/webview/WebViewApp.getCurrentApp:()Lcom/unity3d/services/core/webview/WebViewApp;
        //   849: getstatic       com/unity3d/services/core/webview/WebViewEventCategory.CACHE:Lcom/unity3d/services/core/webview/WebViewEventCategory;
        //   852: getstatic       com/unity3d/services/core/cache/CacheEvent.DOWNLOAD_ERROR:Lcom/unity3d/services/core/cache/CacheEvent;
        //   855: iconst_3       
        //   856: anewarray       Ljava/lang/Object;
        //   859: dup            
        //   860: iconst_0       
        //   861: getstatic       com/unity3d/services/core/cache/CacheError.NETWORK_ERROR:Lcom/unity3d/services/core/cache/CacheError;
        //   864: aastore        
        //   865: dup            
        //   866: iconst_1       
        //   867: aload_1        
        //   868: aastore        
        //   869: dup            
        //   870: iconst_2       
        //   871: aload           12
        //   873: invokevirtual   com/unity3d/services/core/request/NetworkIOException.getMessage:()Ljava/lang/String;
        //   876: aastore        
        //   877: invokevirtual   com/unity3d/services/core/webview/WebViewApp.sendEvent:(Ljava/lang/Enum;Ljava/lang/Enum;[Ljava/lang/Object;)Z
        //   880: pop            
        //   881: aload_0        
        //   882: aconst_null    
        //   883: putfield        com/unity3d/services/core/cache/CacheThreadHandler._currentRequest:Lcom/unity3d/services/core/request/WebRequest;
        //   886: aload           6
        //   888: ifnull          15
        //   891: aload           6
        //   893: invokevirtual   java/io/FileOutputStream.close:()V
        //   896: return         
        //   897: astore_2       
        //   898: ldc             "Error closing stream"
        //   900: aload_2        
        //   901: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   904: invokestatic    com/unity3d/services/core/webview/WebViewApp.getCurrentApp:()Lcom/unity3d/services/core/webview/WebViewApp;
        //   907: getstatic       com/unity3d/services/core/webview/WebViewEventCategory.CACHE:Lcom/unity3d/services/core/webview/WebViewEventCategory;
        //   910: getstatic       com/unity3d/services/core/cache/CacheEvent.DOWNLOAD_ERROR:Lcom/unity3d/services/core/cache/CacheEvent;
        //   913: iconst_3       
        //   914: anewarray       Ljava/lang/Object;
        //   917: dup            
        //   918: iconst_0       
        //   919: getstatic       com/unity3d/services/core/cache/CacheError.FILE_IO_ERROR:Lcom/unity3d/services/core/cache/CacheError;
        //   922: aastore        
        //   923: dup            
        //   924: iconst_1       
        //   925: aload_1        
        //   926: aastore        
        //   927: dup            
        //   928: iconst_2       
        //   929: aload_2        
        //   930: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   933: aastore        
        //   934: invokevirtual   com/unity3d/services/core/webview/WebViewApp.sendEvent:(Ljava/lang/Enum;Ljava/lang/Enum;[Ljava/lang/Object;)Z
        //   937: pop            
        //   938: return         
        //   939: astore          12
        //   941: aload_2        
        //   942: astore          6
        //   944: aload           12
        //   946: astore_2       
        //   947: aload_0        
        //   948: aconst_null    
        //   949: putfield        com/unity3d/services/core/cache/CacheThreadHandler._currentRequest:Lcom/unity3d/services/core/request/WebRequest;
        //   952: aload           6
        //   954: ifnull          962
        //   957: aload           6
        //   959: invokevirtual   java/io/FileOutputStream.close:()V
        //   962: aload_2        
        //   963: athrow         
        //   964: astore          6
        //   966: ldc             "Error closing stream"
        //   968: aload           6
        //   970: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   973: invokestatic    com/unity3d/services/core/webview/WebViewApp.getCurrentApp:()Lcom/unity3d/services/core/webview/WebViewApp;
        //   976: getstatic       com/unity3d/services/core/webview/WebViewEventCategory.CACHE:Lcom/unity3d/services/core/webview/WebViewEventCategory;
        //   979: getstatic       com/unity3d/services/core/cache/CacheEvent.DOWNLOAD_ERROR:Lcom/unity3d/services/core/cache/CacheEvent;
        //   982: iconst_3       
        //   983: anewarray       Ljava/lang/Object;
        //   986: dup            
        //   987: iconst_0       
        //   988: getstatic       com/unity3d/services/core/cache/CacheError.FILE_IO_ERROR:Lcom/unity3d/services/core/cache/CacheError;
        //   991: aastore        
        //   992: dup            
        //   993: iconst_1       
        //   994: aload_1        
        //   995: aastore        
        //   996: dup            
        //   997: iconst_2       
        //   998: aload           6
        //  1000: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //  1003: aastore        
        //  1004: invokevirtual   com/unity3d/services/core/webview/WebViewApp.sendEvent:(Ljava/lang/Enum;Ljava/lang/Enum;[Ljava/lang/Object;)Z
        //  1007: pop            
        //  1008: goto            962
        //  1011: astore_2       
        //  1012: aload           12
        //  1014: astore          6
        //  1016: goto            947
        //  1019: astore_2       
        //  1020: aload           12
        //  1022: astore          6
        //  1024: aload_2        
        //  1025: astore          12
        //  1027: goto            825
        //  1030: astore_2       
        //  1031: aload           12
        //  1033: astore          6
        //  1035: aload_2        
        //  1036: astore          12
        //  1038: goto            705
        //  1041: astore_2       
        //  1042: aload           12
        //  1044: astore          6
        //  1046: aload_2        
        //  1047: astore          12
        //  1049: goto            585
        //  1052: astore_2       
        //  1053: aload           12
        //  1055: astore          6
        //  1057: aload_2        
        //  1058: astore          12
        //  1060: goto            465
        //  1063: astore_2       
        //  1064: aload           12
        //  1066: astore          6
        //  1068: aload_2        
        //  1069: astore          12
        //  1071: goto            345
        //    Signature:
        //  (Ljava/lang/String;Ljava/lang/String;IIILjava/util/HashMap<Ljava/lang/String;Ljava/util/List<Ljava/lang/String;>;>;Z)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                  
        //  -----  -----  -----  -----  ------------------------------------------------------
        //  180    193    339    345    Ljava/io/FileNotFoundException;
        //  180    193    459    465    Ljava/net/MalformedURLException;
        //  180    193    579    585    Ljava/io/IOException;
        //  180    193    699    705    Ljava/lang/IllegalStateException;
        //  180    193    819    825    Lcom/unity3d/services/core/request/NetworkIOException;
        //  180    193    939    947    Any
        //  193    281    1063   1074   Ljava/io/FileNotFoundException;
        //  193    281    1052   1063   Ljava/net/MalformedURLException;
        //  193    281    1041   1052   Ljava/io/IOException;
        //  193    281    1030   1041   Ljava/lang/IllegalStateException;
        //  193    281    1019   1030   Lcom/unity3d/services/core/request/NetworkIOException;
        //  193    281    1011   1019   Any
        //  291    296    297    339    Ljava/lang/Exception;
        //  348    355    939    947    Any
        //  358    363    939    947    Any
        //  366    401    939    947    Any
        //  411    416    417    459    Ljava/lang/Exception;
        //  468    475    939    947    Any
        //  478    483    939    947    Any
        //  486    521    939    947    Any
        //  531    536    537    579    Ljava/lang/Exception;
        //  588    595    939    947    Any
        //  598    603    939    947    Any
        //  606    641    939    947    Any
        //  651    656    657    699    Ljava/lang/Exception;
        //  708    715    939    947    Any
        //  718    723    939    947    Any
        //  726    761    939    947    Any
        //  771    776    777    819    Ljava/lang/Exception;
        //  828    835    939    947    Any
        //  838    843    939    947    Any
        //  846    881    939    947    Any
        //  891    896    897    939    Ljava/lang/Exception;
        //  957    962    964    1011   Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 581, Size: 581
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
    
    private WebRequest getWebRequest(final String s, final int n, final int n2, final HashMap<String, List<String>> hashMap) throws MalformedURLException {
        final HashMap<String, List<String>> hashMap2 = new HashMap<String, List<String>>();
        if (hashMap != null) {
            hashMap2.putAll(hashMap);
        }
        return new WebRequest(s, "GET", hashMap2, n, n2);
    }
    
    private void postProcessDownload(long n, final String s, final File file, final long n2, final long n3, final boolean b, final int n4, final Map<String, List<String>> map) {
        n = SystemClock.elapsedRealtime() - n;
        if (!file.setReadable(true, false)) {
            DeviceLog.debug("Unity Ads cache: could not set file readable!");
        }
        if (!b) {
            DeviceLog.debug("Unity Ads cache: File " + file.getName() + " of " + n2 + " bytes downloaded in " + n + "ms");
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_END, s, n2, n3, n, n4, Request.getResponseHeadersMap(map));
            return;
        }
        DeviceLog.debug("Unity Ads cache: downloading of " + s + " stopped");
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_STOPPED, s, n2, n3, n, n4, Request.getResponseHeadersMap(map));
    }
    
    public void handleMessage(final Message message) {
        final Bundle data = message.getData();
        final String string = data.getString("source");
        data.remove("source");
        final String string2 = data.getString("target");
        data.remove("target");
        final int int1 = data.getInt("connectTimeout");
        data.remove("connectTimeout");
        final int int2 = data.getInt("readTimeout");
        data.remove("readTimeout");
        final int int3 = data.getInt("progressInterval");
        data.remove("progressInterval");
        final boolean boolean1 = data.getBoolean("append", false);
        data.remove("append");
        HashMap<String, List<String>> hashMap = null;
        if (data.size() > 0) {
            DeviceLog.debug("There are headers left in data, reading them");
            final HashMap<String, List<String>> hashMap2 = new HashMap<String, List<String>>();
            final Iterator iterator = data.keySet().iterator();
            while (true) {
                hashMap = hashMap2;
                if (!iterator.hasNext()) {
                    break;
                }
                final String s = iterator.next();
                hashMap2.put(s, Arrays.asList(data.getStringArray(s)));
            }
        }
        final File file = new File(string2);
        if ((boolean1 && !file.exists()) || (!boolean1 && file.exists())) {
            this._active = false;
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_STATE_WRONG, string, string2, boolean1, file.exists());
            return;
        }
        switch (message.what) {
            default: {}
            case 1: {
                this.downloadFile(string, string2, int1, int2, int3, hashMap, boolean1);
            }
        }
    }
    
    public boolean isActive() {
        return this._active;
    }
    
    public void setCancelStatus(final boolean canceled) {
        this._canceled = canceled;
        if (canceled && this._currentRequest != null) {
            this._active = false;
            this._currentRequest.cancel();
        }
    }
}
