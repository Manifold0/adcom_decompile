package com.onevcat.UniPasteBoard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;
import com.unity3d.player.UnityPlayer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

class PasteBoard {
    private static CharSequence result = "";

    PasteBoard() {
    }

    public static void setClipBoardString(final String text) {
        Log.d("UniPasteBoard", "Copy Begin");
        final Activity currentActivity = UnityPlayer.currentActivity;
        currentActivity.runOnUiThread(new Runnable() {
            public void run() {
                if (VERSION.SDK_INT >= 11) {
                    Log.d("UniPasteBoard", "Miao>11");
                    ClipboardManager clipboard = (ClipboardManager) currentActivity.getSystemService("clipboard");
                    if (clipboard != null) {
                        clipboard.setPrimaryClip(ClipData.newPlainText("", text));
                        Log.d("UniPasteBoard", "Copy OK");
                        return;
                    }
                    return;
                }
                android.text.ClipboardManager clipboard2 = (android.text.ClipboardManager) currentActivity.getSystemService("clipboard");
                if (clipboard2 != null) {
                    clipboard2.setText(text);
                }
            }
        });
    }

    @SuppressLint({"NewApi"})
    public static String getClipBoardString() throws InterruptedException {
        result = "";
        Log.d("UniPasteBoard", "Begin");
        final Activity currentActivity = UnityPlayer.currentActivity;
        Runnable run = new Runnable() {
            public void run() {
                if (VERSION.SDK_INT < 11) {
                    PasteBoard.result = ((android.text.ClipboardManager) currentActivity.getSystemService("clipboard")).getText().toString();
                } else {
                    ClipboardManager clipboard = (ClipboardManager) currentActivity.getSystemService("clipboard");
                    ContentResolver cr = currentActivity.getContentResolver();
                    ClipData clip = clipboard.getPrimaryClip();
                    if (clip != null) {
                        String text = null;
                        Item item = clip.getItemAt(0);
                        if (null == null) {
                            text = PasteBoard.coerceToText(currentActivity, item).toString();
                        }
                        PasteBoard.result = text;
                    }
                }
                synchronized (this) {
                    notify();
                }
            }
        };
        synchronized (run) {
            currentActivity.runOnUiThread(run);
            run.wait();
        }
        return result.toString();
    }

    @SuppressLint({"NewApi"})
    public static CharSequence coerceToText(Context context, Item item) {
        CharSequence text = item.getText();
        if (text != null) {
            return text;
        }
        Uri uri = item.getUri();
        if (uri != null) {
            FileInputStream stream = null;
            try {
                stream = context.getContentResolver().openTypedAssetFileDescriptor(uri, "text/*", null).createInputStream();
                InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
                StringBuilder builder = new StringBuilder(128);
                char[] buffer = new char[8192];
                while (true) {
                    int len = reader.read(buffer);
                    if (len <= 0) {
                        break;
                    }
                    builder.append(buffer, 0, len);
                }
                text = builder.toString();
                if (stream == null) {
                    return text;
                }
                try {
                    stream.close();
                    return text;
                } catch (IOException e) {
                    return text;
                }
            } catch (FileNotFoundException e2) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e3) {
                    }
                }
                return uri.toString();
            } catch (IOException e4) {
                Log.w("ClippedData", "Failure loading text", e4);
                text = e4.toString();
                if (stream == null) {
                    return text;
                }
                try {
                    stream.close();
                    return text;
                } catch (IOException e5) {
                    return text;
                }
            } catch (Throwable th) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e6) {
                    }
                }
            }
        } else {
            Intent intent = item.getIntent();
            if (intent != null) {
                return intent.toUri(1);
            }
            return "";
        }
    }
}
