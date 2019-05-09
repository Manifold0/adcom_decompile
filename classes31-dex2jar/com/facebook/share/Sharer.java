// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share;

public interface Sharer
{
    boolean getShouldFailOnDataError();
    
    void setShouldFailOnDataError(final boolean p0);
    
    public static class Result
    {
        final String postId;
        
        public Result(final String postId) {
            this.postId = postId;
        }
        
        public String getPostId() {
            return this.postId;
        }
    }
}
