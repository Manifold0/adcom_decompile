package com.tapjoy.mraid.controller;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Environment;
import android.os.StatFs;
import android.webkit.JavascriptInterface;
import com.adjust.sdk.Constants;
import com.tapjoy.TapjoyLog;
import com.tapjoy.internal.da;
import com.tapjoy.internal.em;
import com.tapjoy.mraid.view.MraidView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.jar.JarFile;

public class Assets extends Abstract {
    /* renamed from: d */
    private static final char[] f8257d = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /* renamed from: c */
    private int f8258c = 0;

    public Assets(MraidView adView, Context c) {
        super(adView, c);
    }

    @JavascriptInterface
    public void storePictureInit(final String URI) {
        Builder builder = new Builder(this.b);
        builder.setMessage("Are you sure you want to save file from " + URI + " to your SD card?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ Assets f8256b;

            public final void onClick(DialogInterface dialog, int id) {
                this.f8256b.storePicture(URI);
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    public void storePicture(String URI) {
        TapjoyLog.m7126d("MRAID Assets", "Storing media from " + URI + " to device photo album.  Output directory: " + Environment.getExternalStorageDirectory() + " state: " + Environment.getExternalStorageState());
        this.f8258c++;
        try {
            URL url = new URL(URI);
            String str = "MraidMedia" + this.f8258c + ".jpg";
            File file = new File(Environment.getExternalStorageDirectory().toString() + "/" + str);
            long currentTimeMillis = System.currentTimeMillis();
            TapjoyLog.m7126d("MRAID Assets", "download beginning");
            TapjoyLog.m7126d("MRAID Assets", "download url:" + url);
            TapjoyLog.m7126d("MRAID Assets", "downloaded file name:" + str);
            InputStream inputStream = em.m7652a(url).getInputStream();
            OutputStream fileOutputStream = new FileOutputStream(file);
            da.m7354a(inputStream, fileOutputStream);
            fileOutputStream.close();
            TapjoyLog.m7126d("MRAID Assets", "download ready in" + ((System.currentTimeMillis() - currentTimeMillis) / 1000) + " sec");
        } catch (IOException e) {
            TapjoyLog.m7126d("MRAID Assets", "Error: " + e);
        }
    }

    public String copyTextFromJarIntoAssetDir(String alias, String source) {
        InputStream open;
        Exception e;
        Throwable th;
        String str = null;
        try {
            URL resource = Assets.class.getClassLoader().getResource(source);
            if (resource == null) {
                open = this.b.getAssets().open(source);
            } else {
                String file = resource.getFile();
                if (file.startsWith("jar:")) {
                    file = file.substring(4);
                }
                if (file.startsWith("file:")) {
                    file = file.substring(5);
                }
                int indexOf = file.indexOf("!");
                if (indexOf > 0) {
                    file = file.substring(0, indexOf);
                }
                JarFile jarFile = new JarFile(file);
                open = jarFile.getInputStream(jarFile.getJarEntry(source));
            }
            try {
                str = writeToDisk(open, alias, false);
                if (open != null) {
                    try {
                        open.close();
                    } catch (Exception e2) {
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    TapjoyLog.m7128e("MRAID Assets", "copyTextFromJarIntoAssetDir: " + e.toString());
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Exception e4) {
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Exception e5) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e6) {
            e = e6;
            open = str;
            TapjoyLog.m7128e("MRAID Assets", "copyTextFromJarIntoAssetDir: " + e.toString());
            if (open != null) {
                open.close();
            }
            return str;
        } catch (Throwable th3) {
            open = str;
            th = th3;
            if (open != null) {
                open.close();
            }
            throw th;
        }
        return str;
    }

    public void addAsset(String alias, String url) {
        InputStream inputStream = null;
        try {
            inputStream = em.m7651a(url);
            writeToDisk(inputStream, alias, false);
            this.a.injectMraidJavaScript("MraidAdController.addedAsset('" + alias + "' )");
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e3) {
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e4) {
                }
            }
        }
    }

    public int cacheRemaining() {
        StatFs statFs = new StatFs(this.b.getFilesDir().getPath());
        return statFs.getFreeBlocks() * statFs.getBlockSize();
    }

    public String writeToDisk(InputStream in, String file, boolean storeInHashedDirectory) {
        MessageDigest instance;
        int read;
        String a;
        String str;
        FileOutputStream fileOutputStream = null;
        int i = 0;
        byte[] bArr = new byte[1024];
        if (storeInHashedDirectory) {
            try {
                instance = MessageDigest.getInstance(Constants.MD5);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            fileOutputStream = getAssetOutputString(file);
            while (true) {
                read = in.read(bArr);
                if (read > 0) {
                    break;
                }
                if (storeInHashedDirectory && instance != null) {
                    instance.update(bArr);
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.flush();
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e2) {
                }
            }
            a = m8237a();
            if (storeInHashedDirectory || instance == null) {
                str = a;
            } else {
                bArr = instance.digest();
                char[] cArr = new char[(bArr.length * 2)];
                int i2 = 0;
                while (i < bArr.length) {
                    int i3 = i2 + 1;
                    cArr[i2] = f8257d[(bArr[i] >>> 4) & 15];
                    i2 = i3 + 1;
                    cArr[i3] = f8257d[bArr[i] & 15];
                    i++;
                }
                str = new String(cArr);
                File file2 = new File(a + File.separator + file);
                new File(a + File.separator + "ad").mkdir();
                File file3 = new File(a + File.separator + "ad" + File.separator + str);
                file3.mkdir();
                file2.renameTo(new File(file3, file2.getName()));
                str = file3.getPath() + File.separator;
            }
            return str + file;
        }
        instance = null;
        try {
            fileOutputStream = getAssetOutputString(file);
            while (true) {
                read = in.read(bArr);
                if (read > 0) {
                    break;
                }
                instance.update(bArr);
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.flush();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            a = m8237a();
            if (storeInHashedDirectory) {
            }
            str = a;
            return str + file;
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e3) {
                }
            }
        }
    }

    /* renamed from: a */
    private String m8237a() {
        return this.b.getFilesDir().getPath();
    }

    public FileOutputStream getAssetOutputString(String asset) {
        File a = m8236a(m8238b(asset));
        a.mkdirs();
        return new FileOutputStream(new File(a, m8239c(asset)));
    }

    public void removeAsset(String asset) {
        File a = m8236a(m8238b(asset));
        a.mkdirs();
        new File(a, m8239c(asset)).delete();
        this.a.injectMraidJavaScript("MraidAdController.assetRemoved('" + asset + "' )");
    }

    /* renamed from: a */
    private File m8236a(String str) {
        return new File(this.b.getFilesDir().getPath() + File.separator + str);
    }

    /* renamed from: b */
    private static String m8238b(String str) {
        String str2 = "/";
        if (str.lastIndexOf(File.separatorChar) >= 0) {
            return str.substring(0, str.lastIndexOf(File.separatorChar));
        }
        return str2;
    }

    /* renamed from: c */
    private static String m8239c(String str) {
        if (str.lastIndexOf(File.separatorChar) >= 0) {
            return str.substring(str.lastIndexOf(File.separatorChar) + 1);
        }
        return str;
    }

    public String getAssetPath() {
        return "file://" + this.b.getFilesDir() + "/";
    }

    public static boolean deleteDirectory(String path) {
        if (path != null) {
            return deleteDirectory(new File(path));
        }
        return false;
    }

    public static boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] listFiles = path.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    deleteDirectory(listFiles[i]);
                } else {
                    listFiles[i].delete();
                }
            }
        }
        return path.delete();
    }

    public void deleteOldAds() {
        deleteDirectory(new File(m8237a() + File.separator + "ad"));
    }

    public void stopAllListeners() {
    }
}
