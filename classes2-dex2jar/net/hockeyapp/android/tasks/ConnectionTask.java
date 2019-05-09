// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.tasks;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import android.os.AsyncTask;

public abstract class ConnectionTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result>
{
    private static String convertStreamToString(final InputStream ex) {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream)ex), 1024);
        final StringBuilder sb = new StringBuilder();
        try {
            Label_0077: {
                try {
                    while (true) {
                        final String line = bufferedReader.readLine();
                        if (line == null) {
                            break Label_0077;
                        }
                        sb.append(line + "\n");
                    }
                }
                catch (IOException ex2) {
                    ex2.printStackTrace();
                    final IOException ex3 = ex;
                    ((InputStream)ex3).close();
                }
                try {
                    final IOException ex3 = ex;
                    ((InputStream)ex3).close();
                    return sb.toString();
                    try {
                        ((InputStream)ex).close();
                        return sb.toString();
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                        return sb.toString();
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        finally {
            try {
                ((InputStream)ex).close();
            }
            catch (IOException ex4) {
                ex4.printStackTrace();
            }
        }
    }
    
    protected static String getStringFromConnection(final HttpURLConnection httpURLConnection) throws IOException {
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        final String convertStreamToString = convertStreamToString(bufferedInputStream);
        bufferedInputStream.close();
        return convertStreamToString;
    }
}
