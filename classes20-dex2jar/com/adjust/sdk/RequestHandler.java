// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;

public class RequestHandler implements IRequestHandler
{
    private ILogger logger;
    private WeakReference<IPackageHandler> packageHandlerWeakRef;
    private CustomScheduledExecutor scheduledExecutor;
    
    public RequestHandler(final IPackageHandler packageHandler) {
        this.logger = AdjustFactory.getLogger();
        this.scheduledExecutor = new CustomScheduledExecutor("RequestHandler", false);
        this.init(packageHandler);
    }
    
    private void closePackageI(final ActivityPackage activityPackage, final String s, final Throwable t) {
        final String formatString = Util.formatString("%s. (%s) Will retry later", activityPackage.getFailureMessage(), Util.getReasonString(s, t));
        this.logger.error(formatString, new Object[0]);
        final ResponseData buildResponseData = ResponseData.buildResponseData(activityPackage);
        buildResponseData.message = formatString;
        final IPackageHandler packageHandler = this.packageHandlerWeakRef.get();
        if (packageHandler == null) {
            return;
        }
        packageHandler.closeFirstPackage(buildResponseData, activityPackage);
    }
    
    private void sendI(final ActivityPackage activityPackage, final int n) {
        final String string = "https://app.adjust.com" + activityPackage.getPath();
        try {
            final ResponseData postHttpsURLConnection = UtilNetworking.createPOSTHttpsURLConnection(string, activityPackage, n);
            final IPackageHandler packageHandler = this.packageHandlerWeakRef.get();
            if (packageHandler == null) {
                return;
            }
            if (postHttpsURLConnection.jsonResponse == null) {
                packageHandler.closeFirstPackage(postHttpsURLConnection, activityPackage);
                return;
            }
            goto Label_0075;
        }
        catch (UnsupportedEncodingException ex) {
            this.sendNextPackageI(activityPackage, "Failed to encode parameters", ex);
        }
        catch (SocketTimeoutException ex2) {
            this.closePackageI(activityPackage, "Request timed out", ex2);
        }
        catch (IOException ex3) {
            this.closePackageI(activityPackage, "Request failed", ex3);
        }
        catch (Throwable t) {
            this.sendNextPackageI(activityPackage, "Runtime exception", t);
        }
    }
    
    private void sendNextPackageI(final ActivityPackage activityPackage, String formatString, final Throwable t) {
        formatString = Util.formatString("%s. (%s)", activityPackage.getFailureMessage(), Util.getReasonString(formatString, t));
        this.logger.error(formatString, new Object[0]);
        final ResponseData buildResponseData = ResponseData.buildResponseData(activityPackage);
        buildResponseData.message = formatString;
        final IPackageHandler packageHandler = this.packageHandlerWeakRef.get();
        if (packageHandler == null) {
            return;
        }
        packageHandler.sendNextPackage(buildResponseData);
    }
    
    @Override
    public void init(final IPackageHandler packageHandler) {
        this.packageHandlerWeakRef = new WeakReference<IPackageHandler>(packageHandler);
    }
    
    @Override
    public void sendPackage(final ActivityPackage activityPackage, final int n) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                RequestHandler.this.sendI(activityPackage, n);
            }
        });
    }
    
    @Override
    public void teardown() {
        this.logger.verbose("RequestHandler teardown", new Object[0]);
        while (true) {
            if (this.scheduledExecutor == null) {
                break Label_0029;
            }
            try {
                this.scheduledExecutor.shutdownNow();
                if (this.packageHandlerWeakRef != null) {
                    this.packageHandlerWeakRef.clear();
                }
                this.scheduledExecutor = null;
                this.packageHandlerWeakRef = null;
                this.logger = null;
            }
            catch (SecurityException ex) {
                continue;
            }
            break;
        }
    }
}
