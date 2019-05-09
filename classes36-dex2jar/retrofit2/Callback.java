// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2;

public interface Callback<T>
{
    void onFailure(final Call<T> p0, final Throwable p1);
    
    void onResponse(final Call<T> p0, final Response<T> p1);
}
