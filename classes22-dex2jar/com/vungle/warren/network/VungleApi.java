// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.network;

import retrofit2.http.QueryMap;
import java.util.Map;
import retrofit2.http.GET;
import okhttp3.ResponseBody;
import retrofit2.http.Url;
import retrofit2.http.POST;
import retrofit2.http.Headers;
import retrofit2.Call;
import retrofit2.http.Body;
import com.google.gson.JsonObject;
import retrofit2.http.Path;
import retrofit2.http.Header;

public interface VungleApi
{
    @Headers({ "Content-Type: application/json", "Vungle-Version: 5.2.0" })
    @POST("{ads}")
    Call<JsonObject> ads(@Header("User-Agent") final String p0, @Path(encoded = true, value = "ads") final String p1, @Body final JsonObject p2);
    
    @Headers({ "Content-Type: application/json", "Vungle-Version: 5.2.0" })
    @POST("config")
    Call<JsonObject> config(@Header("User-Agent") final String p0, @Body final JsonObject p1);
    
    @GET
    Call<ResponseBody> pingTPAT(@Header("User-Agent") final String p0, @Url final String p1);
    
    @Headers({ "Content-Type: application/json", "Vungle-Version: 5.2.0" })
    @POST("{report_ad}")
    Call<JsonObject> reportAd(@Header("User-Agent") final String p0, @Path(encoded = true, value = "report_ad") final String p1, @Body final JsonObject p2);
    
    @GET("{new}")
    @Headers({ "Content-Type: application/json", "Vungle-Version: 5.2.0" })
    Call<JsonObject> reportNew(@Header("User-Agent") final String p0, @Path(encoded = true, value = "new") final String p1, @QueryMap final Map<String, String> p2);
    
    @Headers({ "Content-Type: application/json", "Vungle-Version: 5.2.0" })
    @POST("{ri}")
    Call<JsonObject> ri(@Header("User-Agent") final String p0, @Path(encoded = true, value = "ri") final String p1, @Body final JsonObject p2);
    
    @Headers({ "Content-Type: application/json", "Vungle-Version: 5.2.0" })
    @POST("{will_play_ad}")
    Call<JsonObject> willPlayAd(@Header("User-Agent") final String p0, @Path(encoded = true, value = "will_play_ad") final String p1, @Body final JsonObject p2);
}
