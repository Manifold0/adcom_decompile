// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.objects;

import java.text.ParseException;
import net.hockeyapp.android.utils.HockeyLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.FileReader;
import java.io.File;
import android.text.TextUtils;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CrashDetails
{
    public static final SimpleDateFormat DATE_FORMAT;
    private static final String FIELD_APP_CRASH_DATE = "Date";
    private static final String FIELD_APP_PACKAGE = "Package";
    private static final String FIELD_APP_START_DATE = "Start Date";
    private static final String FIELD_APP_VERSION_CODE = "Version Code";
    private static final String FIELD_APP_VERSION_NAME = "Version Name";
    private static final String FIELD_CRASH_REPORTER_KEY = "CrashReporter Key";
    private static final String FIELD_DEVICE_MANUFACTURER = "Manufacturer";
    private static final String FIELD_DEVICE_MODEL = "Model";
    private static final String FIELD_FORMAT = "Format";
    private static final String FIELD_FORMAT_VALUE = "Xamarin";
    private static final String FIELD_OS_BUILD = "Android Build";
    private static final String FIELD_OS_VERSION = "Android";
    private static final String FIELD_THREAD_NAME = "Thread";
    private static final String FIELD_XAMARIN_CAUSED_BY = "Xamarin caused by: ";
    private Date appCrashDate;
    private String appPackage;
    private Date appStartDate;
    private String appVersionCode;
    private String appVersionName;
    private final String crashIdentifier;
    private String deviceManufacturer;
    private String deviceModel;
    private String format;
    private Boolean isXamarinException;
    private String osBuild;
    private String osVersion;
    private String reporterKey;
    private String threadName;
    private String throwableStackTrace;
    
    static {
        DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
    }
    
    public CrashDetails(final String crashIdentifier) {
        this.crashIdentifier = crashIdentifier;
    }
    
    public CrashDetails(final String s, final Throwable t) {
        this(s);
        this.isXamarinException = false;
        final StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter));
        this.throwableStackTrace = stringWriter.toString();
    }
    
    public CrashDetails(final String s, final Throwable t, final String s2, final Boolean b) {
        this(s);
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        this.isXamarinException = true;
        this.setFormat("Xamarin");
        if (b) {
            printWriter.print("Xamarin caused by: ");
            t.printStackTrace(printWriter);
        }
        else if (!TextUtils.isEmpty((CharSequence)s2)) {
            t.printStackTrace(printWriter);
            printWriter.print("Xamarin caused by: ");
            printWriter.print(s2);
        }
        else {
            t.printStackTrace(printWriter);
        }
        this.throwableStackTrace = stringWriter.toString();
    }
    
    public static CrashDetails fromFile(final File file) throws IOException {
        return fromReader(file.getName().substring(0, file.getName().indexOf(".stacktrace")), new FileReader(file));
    }
    
    public static CrashDetails fromReader(final String s, final Reader reader) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(reader);
        final CrashDetails crashDetails = new CrashDetails(s);
        int n = 0;
        final StringBuilder sb = new StringBuilder();
        while (true) {
            final String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            if (n == 0) {
                if (line.isEmpty()) {
                    n = 1;
                }
                else {
                    final int index = line.indexOf(":");
                    if (index < 0) {
                        HockeyLog.error("Malformed header line when parsing crash details: \"" + line + "\"");
                    }
                    final String trim = line.substring(0, index).trim();
                    final String trim2 = line.substring(index + 1, line.length()).trim();
                    if (trim.equals("CrashReporter Key")) {
                        crashDetails.setReporterKey(trim2);
                    }
                    else {
                        if (trim.equals("Start Date")) {
                            try {
                                crashDetails.setAppStartDate(CrashDetails.DATE_FORMAT.parse(trim2));
                                continue;
                            }
                            catch (ParseException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        if (trim.equals("Date")) {
                            try {
                                crashDetails.setAppCrashDate(CrashDetails.DATE_FORMAT.parse(trim2));
                                continue;
                            }
                            catch (ParseException ex2) {
                                throw new RuntimeException(ex2);
                            }
                        }
                        if (trim.equals("Android")) {
                            crashDetails.setOsVersion(trim2);
                        }
                        else if (trim.equals("Android Build")) {
                            crashDetails.setOsBuild(trim2);
                        }
                        else if (trim.equals("Manufacturer")) {
                            crashDetails.setDeviceManufacturer(trim2);
                        }
                        else if (trim.equals("Model")) {
                            crashDetails.setDeviceModel(trim2);
                        }
                        else if (trim.equals("Package")) {
                            crashDetails.setAppPackage(trim2);
                        }
                        else if (trim.equals("Version Name")) {
                            crashDetails.setAppVersionName(trim2);
                        }
                        else if (trim.equals("Version Code")) {
                            crashDetails.setAppVersionCode(trim2);
                        }
                        else if (trim.equals("Thread")) {
                            crashDetails.setThreadName(trim2);
                        }
                        else {
                            if (!trim.equals("Format")) {
                                continue;
                            }
                            crashDetails.setFormat(trim2);
                        }
                    }
                }
            }
            else {
                sb.append(line).append("\n");
            }
        }
        crashDetails.setThrowableStackTrace(sb.toString());
        return crashDetails;
    }
    
    private void writeHeader(final Writer writer, final String s, final String s2) throws IOException {
        writer.write(s + ": " + s2 + "\n");
    }
    
    public Date getAppCrashDate() {
        return this.appCrashDate;
    }
    
    public String getAppPackage() {
        return this.appPackage;
    }
    
    public Date getAppStartDate() {
        return this.appStartDate;
    }
    
    public String getAppVersionCode() {
        return this.appVersionCode;
    }
    
    public String getAppVersionName() {
        return this.appVersionName;
    }
    
    public String getCrashIdentifier() {
        return this.crashIdentifier;
    }
    
    public String getDeviceManufacturer() {
        return this.deviceManufacturer;
    }
    
    public String getDeviceModel() {
        return this.deviceModel;
    }
    
    public String getFormat() {
        return this.format;
    }
    
    public Boolean getIsXamarinException() {
        return this.isXamarinException;
    }
    
    public String getOsBuild() {
        return this.osBuild;
    }
    
    public String getOsVersion() {
        return this.osVersion;
    }
    
    public String getReporterKey() {
        return this.reporterKey;
    }
    
    public String getThreadName() {
        return this.threadName;
    }
    
    public String getThrowableStackTrace() {
        return this.throwableStackTrace;
    }
    
    public void setAppCrashDate(final Date appCrashDate) {
        this.appCrashDate = appCrashDate;
    }
    
    public void setAppPackage(final String appPackage) {
        this.appPackage = appPackage;
    }
    
    public void setAppStartDate(final Date appStartDate) {
        this.appStartDate = appStartDate;
    }
    
    public void setAppVersionCode(final String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }
    
    public void setAppVersionName(final String appVersionName) {
        this.appVersionName = appVersionName;
    }
    
    public void setDeviceManufacturer(final String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }
    
    public void setDeviceModel(final String deviceModel) {
        this.deviceModel = deviceModel;
    }
    
    public void setFormat(final String format) {
        this.format = format;
    }
    
    public void setIsXamarinException(final Boolean isXamarinException) {
        this.isXamarinException = isXamarinException;
    }
    
    public void setOsBuild(final String osBuild) {
        this.osBuild = osBuild;
    }
    
    public void setOsVersion(final String osVersion) {
        this.osVersion = osVersion;
    }
    
    public void setReporterKey(final String reporterKey) {
        this.reporterKey = reporterKey;
    }
    
    public void setThreadName(final String threadName) {
        this.threadName = threadName;
    }
    
    public void setThrowableStackTrace(final String throwableStackTrace) {
        this.throwableStackTrace = throwableStackTrace;
    }
    
    public void writeCrashReport() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: getstatic       net/hockeyapp/android/Constants.FILES_PATH:Ljava/lang/String;
        //    10: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    13: ldc_w           "/"
        //    16: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    19: aload_0        
        //    20: getfield        net/hockeyapp/android/objects/CrashDetails.crashIdentifier:Ljava/lang/String;
        //    23: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    26: ldc             ".stacktrace"
        //    28: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    31: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    34: astore_2       
        //    35: new             Ljava/lang/StringBuilder;
        //    38: dup            
        //    39: invokespecial   java/lang/StringBuilder.<init>:()V
        //    42: ldc_w           "Writing unhandled exception to: "
        //    45: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    48: aload_2        
        //    49: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    52: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    55: invokestatic    net/hockeyapp/android/utils/HockeyLog.debug:(Ljava/lang/String;)V
        //    58: aconst_null    
        //    59: astore_1       
        //    60: aconst_null    
        //    61: astore_3       
        //    62: new             Ljava/io/BufferedWriter;
        //    65: dup            
        //    66: new             Ljava/io/FileWriter;
        //    69: dup            
        //    70: aload_2        
        //    71: invokespecial   java/io/FileWriter.<init>:(Ljava/lang/String;)V
        //    74: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
        //    77: astore_2       
        //    78: aload_0        
        //    79: aload_2        
        //    80: ldc             "Package"
        //    82: aload_0        
        //    83: getfield        net/hockeyapp/android/objects/CrashDetails.appPackage:Ljava/lang/String;
        //    86: invokespecial   net/hockeyapp/android/objects/CrashDetails.writeHeader:(Ljava/io/Writer;Ljava/lang/String;Ljava/lang/String;)V
        //    89: aload_0        
        //    90: aload_2        
        //    91: ldc             "Version Code"
        //    93: aload_0        
        //    94: getfield        net/hockeyapp/android/objects/CrashDetails.appVersionCode:Ljava/lang/String;
        //    97: invokespecial   net/hockeyapp/android/objects/CrashDetails.writeHeader:(Ljava/io/Writer;Ljava/lang/String;Ljava/lang/String;)V
        //   100: aload_0        
        //   101: aload_2        
        //   102: ldc             "Version Name"
        //   104: aload_0        
        //   105: getfield        net/hockeyapp/android/objects/CrashDetails.appVersionName:Ljava/lang/String;
        //   108: invokespecial   net/hockeyapp/android/objects/CrashDetails.writeHeader:(Ljava/io/Writer;Ljava/lang/String;Ljava/lang/String;)V
        //   111: aload_0        
        //   112: aload_2        
        //   113: ldc             "Android"
        //   115: aload_0        
        //   116: getfield        net/hockeyapp/android/objects/CrashDetails.osVersion:Ljava/lang/String;
        //   119: invokespecial   net/hockeyapp/android/objects/CrashDetails.writeHeader:(Ljava/io/Writer;Ljava/lang/String;Ljava/lang/String;)V
        //   122: aload_0        
        //   123: aload_2        
        //   124: ldc             "Android Build"
        //   126: aload_0        
        //   127: getfield        net/hockeyapp/android/objects/CrashDetails.osBuild:Ljava/lang/String;
        //   130: invokespecial   net/hockeyapp/android/objects/CrashDetails.writeHeader:(Ljava/io/Writer;Ljava/lang/String;Ljava/lang/String;)V
        //   133: aload_0        
        //   134: aload_2        
        //   135: ldc             "Manufacturer"
        //   137: aload_0        
        //   138: getfield        net/hockeyapp/android/objects/CrashDetails.deviceManufacturer:Ljava/lang/String;
        //   141: invokespecial   net/hockeyapp/android/objects/CrashDetails.writeHeader:(Ljava/io/Writer;Ljava/lang/String;Ljava/lang/String;)V
        //   144: aload_0        
        //   145: aload_2        
        //   146: ldc             "Model"
        //   148: aload_0        
        //   149: getfield        net/hockeyapp/android/objects/CrashDetails.deviceModel:Ljava/lang/String;
        //   152: invokespecial   net/hockeyapp/android/objects/CrashDetails.writeHeader:(Ljava/io/Writer;Ljava/lang/String;Ljava/lang/String;)V
        //   155: aload_0        
        //   156: aload_2        
        //   157: ldc             "Thread"
        //   159: aload_0        
        //   160: getfield        net/hockeyapp/android/objects/CrashDetails.threadName:Ljava/lang/String;
        //   163: invokespecial   net/hockeyapp/android/objects/CrashDetails.writeHeader:(Ljava/io/Writer;Ljava/lang/String;Ljava/lang/String;)V
        //   166: aload_0        
        //   167: aload_2        
        //   168: ldc             "CrashReporter Key"
        //   170: aload_0        
        //   171: getfield        net/hockeyapp/android/objects/CrashDetails.reporterKey:Ljava/lang/String;
        //   174: invokespecial   net/hockeyapp/android/objects/CrashDetails.writeHeader:(Ljava/io/Writer;Ljava/lang/String;Ljava/lang/String;)V
        //   177: aload_0        
        //   178: aload_2        
        //   179: ldc             "Start Date"
        //   181: getstatic       net/hockeyapp/android/objects/CrashDetails.DATE_FORMAT:Ljava/text/SimpleDateFormat;
        //   184: aload_0        
        //   185: getfield        net/hockeyapp/android/objects/CrashDetails.appStartDate:Ljava/util/Date;
        //   188: invokevirtual   java/text/SimpleDateFormat.format:(Ljava/util/Date;)Ljava/lang/String;
        //   191: invokespecial   net/hockeyapp/android/objects/CrashDetails.writeHeader:(Ljava/io/Writer;Ljava/lang/String;Ljava/lang/String;)V
        //   194: aload_0        
        //   195: aload_2        
        //   196: ldc             "Date"
        //   198: getstatic       net/hockeyapp/android/objects/CrashDetails.DATE_FORMAT:Ljava/text/SimpleDateFormat;
        //   201: aload_0        
        //   202: getfield        net/hockeyapp/android/objects/CrashDetails.appCrashDate:Ljava/util/Date;
        //   205: invokevirtual   java/text/SimpleDateFormat.format:(Ljava/util/Date;)Ljava/lang/String;
        //   208: invokespecial   net/hockeyapp/android/objects/CrashDetails.writeHeader:(Ljava/io/Writer;Ljava/lang/String;Ljava/lang/String;)V
        //   211: aload_0        
        //   212: getfield        net/hockeyapp/android/objects/CrashDetails.isXamarinException:Ljava/lang/Boolean;
        //   215: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   218: ifeq            230
        //   221: aload_0        
        //   222: aload_2        
        //   223: ldc             "Format"
        //   225: ldc             "Xamarin"
        //   227: invokespecial   net/hockeyapp/android/objects/CrashDetails.writeHeader:(Ljava/io/Writer;Ljava/lang/String;Ljava/lang/String;)V
        //   230: aload_2        
        //   231: ldc_w           "\n"
        //   234: invokevirtual   java/io/BufferedWriter.write:(Ljava/lang/String;)V
        //   237: aload_2        
        //   238: aload_0        
        //   239: getfield        net/hockeyapp/android/objects/CrashDetails.throwableStackTrace:Ljava/lang/String;
        //   242: invokevirtual   java/io/BufferedWriter.write:(Ljava/lang/String;)V
        //   245: aload_2        
        //   246: invokevirtual   java/io/BufferedWriter.flush:()V
        //   249: aload_2        
        //   250: ifnull          257
        //   253: aload_2        
        //   254: invokevirtual   java/io/BufferedWriter.close:()V
        //   257: return         
        //   258: astore_1       
        //   259: ldc_w           "Error saving crash report!"
        //   262: aload_1        
        //   263: invokestatic    net/hockeyapp/android/utils/HockeyLog.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   266: return         
        //   267: astore_1       
        //   268: aload_3        
        //   269: astore_2       
        //   270: aload_1        
        //   271: astore_3       
        //   272: aload_2        
        //   273: astore_1       
        //   274: ldc_w           "Error saving crash report!"
        //   277: aload_3        
        //   278: invokestatic    net/hockeyapp/android/utils/HockeyLog.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   281: aload_2        
        //   282: ifnull          257
        //   285: aload_2        
        //   286: invokevirtual   java/io/BufferedWriter.close:()V
        //   289: return         
        //   290: astore_1       
        //   291: ldc_w           "Error saving crash report!"
        //   294: aload_1        
        //   295: invokestatic    net/hockeyapp/android/utils/HockeyLog.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   298: return         
        //   299: astore_2       
        //   300: aload_1        
        //   301: ifnull          308
        //   304: aload_1        
        //   305: invokevirtual   java/io/BufferedWriter.close:()V
        //   308: aload_2        
        //   309: athrow         
        //   310: astore_1       
        //   311: ldc_w           "Error saving crash report!"
        //   314: aload_1        
        //   315: invokestatic    net/hockeyapp/android/utils/HockeyLog.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   318: goto            308
        //   321: astore_3       
        //   322: aload_2        
        //   323: astore_1       
        //   324: aload_3        
        //   325: astore_2       
        //   326: goto            300
        //   329: astore_3       
        //   330: goto            272
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  62     78     267    272    Ljava/io/IOException;
        //  62     78     299    300    Any
        //  78     230    329    333    Ljava/io/IOException;
        //  78     230    321    329    Any
        //  230    249    329    333    Ljava/io/IOException;
        //  230    249    321    329    Any
        //  253    257    258    267    Ljava/io/IOException;
        //  274    281    299    300    Any
        //  285    289    290    299    Ljava/io/IOException;
        //  304    308    310    321    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0230:
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
}
