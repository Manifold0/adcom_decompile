package com.chartboost.sdk.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: com.chartboost.sdk.impl.n */
public class C1449n {
    /* renamed from: a */
    public static String m3608a(File file, Map<String, String> map) throws Exception {
        BufferedReader bufferedReader;
        Throwable e;
        FileReader fileReader;
        BufferedReader bufferedReader2 = null;
        FileReader fileReader2;
        try {
            fileReader2 = new FileReader(file);
            try {
                bufferedReader = new BufferedReader(fileReader2);
                try {
                    String str;
                    String readLine;
                    Map hashMap = new HashMap();
                    for (Entry entry : map.entrySet()) {
                        str = (String) entry.getKey();
                        if (str.startsWith("{{") || str.startsWith("{%")) {
                            hashMap.put(str, entry.getValue());
                        }
                    }
                    Set<Entry> entrySet = hashMap.entrySet();
                    int i = 0;
                    for (Entry entry2 : entrySet) {
                        i = (((String) entry2.getValue()).length() * 3) + i;
                    }
                    StringBuilder stringBuilder = new StringBuilder(((int) file.length()) + i);
                    CharSequence stringBuilder2 = new StringBuilder(2048);
                    while (true) {
                        readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        i = readLine.indexOf("{{");
                        int indexOf = readLine.indexOf("{%");
                        if (i == -1 || indexOf == -1) {
                            indexOf = Math.max(i, indexOf);
                        } else {
                            indexOf = Math.min(i, indexOf);
                        }
                        if (indexOf == -1) {
                            stringBuilder.append(readLine);
                        } else {
                            stringBuilder2.setLength(0);
                            stringBuilder2.append(readLine);
                            for (Entry entry22 : entrySet) {
                                str = (String) entry22.getKey();
                                readLine = (String) entry22.getValue();
                                int length = str.length();
                                while (true) {
                                    indexOf = stringBuilder2.indexOf(str, indexOf);
                                    if (-1 != indexOf) {
                                        stringBuilder2.replace(indexOf, indexOf + length, readLine);
                                        indexOf += readLine.length();
                                    }
                                }
                            }
                            stringBuilder.append(stringBuilder2);
                        }
                        stringBuilder.append("\n");
                    }
                    readLine = stringBuilder.toString();
                    if (readLine.contains("{{")) {
                        throw new IllegalArgumentException("Missing required template parameter");
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                        }
                    }
                    if (fileReader2 != null) {
                        try {
                            fileReader2.close();
                        } catch (IOException e3) {
                        }
                    }
                    return readLine;
                } catch (OutOfMemoryError e4) {
                    e = e4;
                    bufferedReader2 = bufferedReader;
                    fileReader = fileReader2;
                    try {
                        throw new Exception(e);
                    } catch (Throwable th) {
                        e = th;
                        fileReader2 = fileReader;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e5) {
                            }
                        }
                        if (fileReader2 != null) {
                            try {
                                fileReader2.close();
                            } catch (IOException e6) {
                            }
                        }
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (fileReader2 != null) {
                        fileReader2.close();
                    }
                    throw e;
                }
            } catch (OutOfMemoryError e7) {
                e = e7;
                fileReader = fileReader2;
                throw new Exception(e);
            } catch (Throwable th3) {
                e = th3;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                throw e;
            }
        } catch (OutOfMemoryError e8) {
            e = e8;
            fileReader = null;
            throw new Exception(e);
        } catch (Throwable th4) {
            e = th4;
            bufferedReader = null;
            fileReader2 = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileReader2 != null) {
                fileReader2.close();
            }
            throw e;
        }
    }
}
