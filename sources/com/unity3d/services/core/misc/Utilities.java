package com.unity3d.services.core.misc;

import android.os.Handler;
import android.os.Looper;
import com.adjust.sdk.Constants;
import com.facebook.appevents.AppEventsConstants;
import com.unity3d.services.core.log.DeviceLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class Utilities {
    public static void runOnUiThread(Runnable runnable) {
        runOnUiThread(runnable, 0);
    }

    public static void runOnUiThread(Runnable runnable, long delay) {
        Handler handler = new Handler(Looper.getMainLooper());
        if (delay > 0) {
            handler.postDelayed(runnable, delay);
        } else {
            handler.post(runnable);
        }
    }

    public static String Sha256(String input) {
        return Sha256(input.getBytes());
    }

    public static String Sha256(byte[] input) {
        if (input == null) {
            return null;
        }
        try {
            MessageDigest m = MessageDigest.getInstance(Constants.SHA256);
            m.update(input, 0, input.length);
            return toHexString(m.digest());
        } catch (NoSuchAlgorithmException e) {
            DeviceLog.exception("SHA-256 algorithm not found", e);
            return null;
        }
    }

    public static String Sha256(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        try {
            MessageDigest m = MessageDigest.getInstance(Constants.SHA256);
            byte[] buffer = new byte[4096];
            while (true) {
                int read = inputStream.read(buffer);
                if (read == -1) {
                    return toHexString(m.digest());
                }
                m.update(buffer, 0, read);
            }
        } catch (NoSuchAlgorithmException e) {
            DeviceLog.exception("SHA-256 algorithm not found", e);
            return null;
        }
    }

    public static String toHexString(byte[] array) {
        String output = "";
        for (byte rawByte : array) {
            int b = rawByte & 255;
            if (b <= 15) {
                output = output + AppEventsConstants.EVENT_PARAM_VALUE_NO;
            }
            output = output + Integer.toHexString(b);
        }
        return output;
    }

    public static boolean writeFile(File fileToWrite, String content) {
        Exception e;
        Throwable th;
        if (fileToWrite == null) {
            return false;
        }
        FileOutputStream fos = null;
        boolean success = true;
        try {
            FileOutputStream fos2 = new FileOutputStream(fileToWrite);
            try {
                fos2.write(content.getBytes());
                fos2.flush();
                if (fos2 != null) {
                    try {
                        fos2.close();
                    } catch (Exception e2) {
                        DeviceLog.exception("Error closing FileOutputStream", e2);
                        fos = fos2;
                    }
                }
                fos = fos2;
            } catch (Exception e3) {
                e2 = e3;
                fos = fos2;
                success = false;
                try {
                    DeviceLog.exception("Could not write file", e2);
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (Exception e22) {
                            DeviceLog.exception("Error closing FileOutputStream", e22);
                        }
                    }
                    if (success) {
                        return success;
                    }
                    DeviceLog.debug("Wrote file: " + fileToWrite.getAbsolutePath());
                    return success;
                } catch (Throwable th2) {
                    th = th2;
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (Exception e222) {
                            DeviceLog.exception("Error closing FileOutputStream", e222);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fos = fos2;
                if (fos != null) {
                    fos.close();
                }
                throw th;
            }
        } catch (Exception e4) {
            e222 = e4;
            success = false;
            DeviceLog.exception("Could not write file", e222);
            if (fos != null) {
                fos.close();
            }
            if (success) {
                return success;
            }
            DeviceLog.debug("Wrote file: " + fileToWrite.getAbsolutePath());
            return success;
        }
        if (success) {
            return success;
        }
        DeviceLog.debug("Wrote file: " + fileToWrite.getAbsolutePath());
        return success;
    }

    public static String readFile(File fileToRead) {
        Exception e;
        if (fileToRead == null) {
            return null;
        }
        String fileContent = "";
        BufferedReader br = null;
        FileReader fr = null;
        if (fileToRead.exists() && fileToRead.canRead()) {
            try {
                FileReader fr2 = new FileReader(fileToRead);
                try {
                    BufferedReader br2 = new BufferedReader(fr2);
                    while (true) {
                        try {
                            String line = br2.readLine();
                            if (line == null) {
                                break;
                            }
                            fileContent = fileContent.concat(line);
                        } catch (Exception e2) {
                            e = e2;
                            fr = fr2;
                            br = br2;
                        }
                    }
                    fr = fr2;
                    br = br2;
                } catch (Exception e3) {
                    e = e3;
                    fr = fr2;
                    DeviceLog.exception("Problem reading file", e);
                    fileContent = null;
                    if (br != null) {
                        try {
                            br.close();
                        } catch (Exception e4) {
                            DeviceLog.exception("Couldn't close BufferedReader", e4);
                        }
                    }
                    if (fr != null) {
                        return fileContent;
                    }
                    try {
                        fr.close();
                        return fileContent;
                    } catch (Exception e42) {
                        DeviceLog.exception("Couldn't close FileReader", e42);
                        return fileContent;
                    }
                }
            } catch (Exception e5) {
                e42 = e5;
                DeviceLog.exception("Problem reading file", e42);
                fileContent = null;
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    return fileContent;
                }
                fr.close();
                return fileContent;
            }
            if (br != null) {
                br.close();
            }
            if (fr != null) {
                return fileContent;
            }
            fr.close();
            return fileContent;
        }
        DeviceLog.error("File did not exist or couldn't be read");
        return null;
    }

    public static byte[] readFileBytes(File file) throws IOException {
        if (file == null) {
            return null;
        }
        int readAmount;
        InputStream inputStream = new FileInputStream(file);
        byte[] buffer = new byte[((int) file.length())];
        int offset = 0;
        if (file.length() < ((long) 4096)) {
            readAmount = (int) file.length();
        } else {
            readAmount = 4096;
        }
        while (true) {
            int read = inputStream.read(buffer, offset, readAmount);
            if (read > 0) {
                offset += read;
                if (file.length() - ((long) offset) < ((long) 4096)) {
                    readAmount = ((int) file.length()) - offset;
                }
            } else {
                inputStream.close();
                return buffer;
            }
        }
    }

    public static JSONObject mergeJsonObjects(JSONObject primary, JSONObject secondary) throws JSONException {
        if (primary == null) {
            return secondary;
        }
        if (secondary == null) {
            return primary;
        }
        JSONObject newJsonObject = new JSONObject();
        Iterator<String> keys = secondary.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            newJsonObject.put(key, secondary.get(key));
        }
        keys = primary.keys();
        while (keys.hasNext()) {
            key = (String) keys.next();
            if (newJsonObject.has(key) && (newJsonObject.get(key) instanceof JSONObject) && (primary.get(key) instanceof JSONObject)) {
                newJsonObject.put(key, mergeJsonObjects(primary.getJSONObject(key), newJsonObject.getJSONObject(key)));
            } else {
                newJsonObject.put(key, primary.get(key));
            }
        }
        return newJsonObject;
    }
}
