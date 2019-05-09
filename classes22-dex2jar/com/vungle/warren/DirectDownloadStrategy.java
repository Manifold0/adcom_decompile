// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren;

public class DirectDownloadStrategy implements DownloadStrategy
{
    private PublisherDirectDownload directDownload;
    
    public DirectDownloadStrategy(final PublisherDirectDownload directDownload) {
        this.directDownload = directDownload;
    }
    
    @Override
    public void isApplicationAvailable(final String s, final VerificationCallback verificationCallback) {
        final DirectDownloadAdapter directDownloadAdapter = new DirectDownloadAdapter(this.directDownload, s);
        directDownloadAdapter.getSdkDownloadClient().setAppMarketValidation((SDKDownloadClient.ValidationCheck)new SDKDownloadClient.ValidationCheck() {
            @Override
            public void validateAppPresenceInMarket(final boolean b) {
                verificationCallback.onResult(b);
            }
        });
        directDownloadAdapter.getSdkDownloadClient().sendValidation(s);
    }
}
