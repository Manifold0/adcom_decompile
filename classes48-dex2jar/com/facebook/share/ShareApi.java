// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share;

import com.facebook.share.internal.ShareContentValidation;
import java.util.Set;
import android.util.Log;
import android.net.Uri;
import android.graphics.Bitmap;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookGraphResponseException;
import com.facebook.internal.Mutable;
import java.io.FileNotFoundException;
import com.facebook.share.internal.VideoUploader;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.FacebookException;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.AccessToken;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.GraphResponse;
import com.facebook.GraphRequest$Callback;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.FacebookCallback;
import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONObject;
import org.json.JSONArray;
import com.facebook.share.model.SharePhotoContent;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.List;
import android.text.TextUtils;
import java.util.Collection;
import com.facebook.internal.Utility;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.internal.CollectionMapper;
import java.util.ArrayList;
import android.os.Bundle;
import com.facebook.share.model.ShareContent;

public final class ShareApi
{
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_GRAPH_NODE = "me";
    private static final String GRAPH_PATH_FORMAT = "%s/%s";
    private static final String PHOTOS_EDGE = "photos";
    private static final String TAG = "ShareApi";
    private String graphNode;
    private String message;
    private final ShareContent shareContent;
    
    public ShareApi(final ShareContent shareContent) {
        this.shareContent = shareContent;
        this.graphNode = "me";
    }
    
    private void addCommonParameters(final Bundle bundle, final ShareContent shareContent) {
        final List peopleIds = shareContent.getPeopleIds();
        if (!Utility.isNullOrEmpty((Collection)peopleIds)) {
            bundle.putString("tags", TextUtils.join((CharSequence)", ", (Iterable)peopleIds));
        }
        if (!Utility.isNullOrEmpty(shareContent.getPlaceId())) {
            bundle.putString("place", shareContent.getPlaceId());
        }
        if (!Utility.isNullOrEmpty(shareContent.getPageId())) {
            bundle.putString("page", shareContent.getPageId());
        }
        if (!Utility.isNullOrEmpty(shareContent.getRef())) {
            bundle.putString("ref", shareContent.getRef());
        }
    }
    
    private String getGraphPath(String format) {
        try {
            format = String.format(Locale.ROOT, "%s/%s", URLEncoder.encode(this.getGraphNode(), "UTF-8"), format);
            return format;
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    
    private Bundle getSharePhotoCommonParameters(final SharePhoto sharePhoto, final SharePhotoContent sharePhotoContent) throws JSONException {
        final Bundle parameters = sharePhoto.getParameters();
        if (!parameters.containsKey("place") && !Utility.isNullOrEmpty(sharePhotoContent.getPlaceId())) {
            parameters.putString("place", sharePhotoContent.getPlaceId());
        }
        if (!parameters.containsKey("tags") && !Utility.isNullOrEmpty((Collection)sharePhotoContent.getPeopleIds())) {
            final List peopleIds = sharePhotoContent.getPeopleIds();
            if (!Utility.isNullOrEmpty((Collection)peopleIds)) {
                final JSONArray jsonArray = new JSONArray();
                for (final String s : peopleIds) {
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("tag_uid", (Object)s);
                    jsonArray.put((Object)jsonObject);
                }
                parameters.putString("tags", jsonArray.toString());
            }
        }
        if (!parameters.containsKey("ref") && !Utility.isNullOrEmpty(sharePhotoContent.getRef())) {
            parameters.putString("ref", sharePhotoContent.getRef());
        }
        return parameters;
    }
    
    private static void handleImagesOnAction(final Bundle bundle) {
        final String string = bundle.getString("image");
        if (string == null) {
            return;
        }
        while (true) {
            while (true) {
                int n = 0;
                Label_0108: {
                    try {
                        final JSONArray jsonArray = new JSONArray(string);
                        n = 0;
                        if (n >= jsonArray.length()) {
                            break Label_0108;
                        }
                        final JSONObject optJSONObject = jsonArray.optJSONObject(n);
                        if (optJSONObject != null) {
                            putImageInBundleWithArrayFormat(bundle, n, optJSONObject);
                            break Label_0108;
                        }
                        bundle.putString(String.format(Locale.ROOT, "image[%d][url]", n), jsonArray.getString(n));
                        break Label_0108;
                    }
                    catch (JSONException ex) {
                        final Bundle bundle2 = bundle;
                        final int n2 = 0;
                        final String s = string;
                        final JSONObject jsonObject = new JSONObject(s);
                        putImageInBundleWithArrayFormat(bundle2, n2, jsonObject);
                        final Bundle bundle3 = bundle;
                        final String s2 = "image";
                        bundle3.remove(s2);
                    }
                    try {
                        final Bundle bundle2 = bundle;
                        final int n2 = 0;
                        final String s = string;
                        final JSONObject jsonObject = new JSONObject(s);
                        putImageInBundleWithArrayFormat(bundle2, n2, jsonObject);
                        final Bundle bundle3 = bundle;
                        final String s2 = "image";
                        bundle3.remove(s2);
                        return;
                        bundle.remove("image");
                        return;
                    }
                    catch (JSONException ex2) {
                        return;
                    }
                }
                ++n;
                continue;
            }
        }
    }
    
    private static void putImageInBundleWithArrayFormat(final Bundle bundle, final int n, final JSONObject jsonObject) throws JSONException {
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            bundle.putString(String.format(Locale.ROOT, "image[%d][%s]", n, s), jsonObject.get(s).toString());
        }
    }
    
    public static void share(final ShareContent shareContent, final FacebookCallback<Sharer$Result> facebookCallback) {
        new ShareApi(shareContent).share(facebookCallback);
    }
    
    private void shareLinkContent(final ShareLinkContent shareLinkContent, final FacebookCallback<Sharer$Result> facebookCallback) {
        final GraphRequest$Callback graphRequest$Callback = (GraphRequest$Callback)new GraphRequest$Callback() {
            public void onCompleted(final GraphResponse graphResponse) {
                final JSONObject jsonObject = graphResponse.getJSONObject();
                String optString;
                if (jsonObject == null) {
                    optString = null;
                }
                else {
                    optString = jsonObject.optString("id");
                }
                ShareInternalUtility.invokeCallbackWithResults(facebookCallback, optString, graphResponse);
            }
        };
        final Bundle bundle = new Bundle();
        this.addCommonParameters(bundle, (ShareContent)shareLinkContent);
        bundle.putString("message", this.getMessage());
        bundle.putString("link", Utility.getUriString(shareLinkContent.getContentUrl()));
        bundle.putString("picture", Utility.getUriString(shareLinkContent.getImageUrl()));
        bundle.putString("name", shareLinkContent.getContentTitle());
        bundle.putString("description", shareLinkContent.getContentDescription());
        bundle.putString("ref", shareLinkContent.getRef());
        new GraphRequest(AccessToken.getCurrentAccessToken(), this.getGraphPath("feed"), bundle, HttpMethod.POST, (GraphRequest$Callback)graphRequest$Callback).executeAsync();
    }
    
    private void shareOpenGraphContent(final ShareOpenGraphContent shareOpenGraphContent, final FacebookCallback<Sharer$Result> facebookCallback) {
        final GraphRequest$Callback graphRequest$Callback = (GraphRequest$Callback)new GraphRequest$Callback() {
            public void onCompleted(final GraphResponse graphResponse) {
                final JSONObject jsonObject = graphResponse.getJSONObject();
                String optString;
                if (jsonObject == null) {
                    optString = null;
                }
                else {
                    optString = jsonObject.optString("id");
                }
                ShareInternalUtility.invokeCallbackWithResults(facebookCallback, optString, graphResponse);
            }
        };
        final ShareOpenGraphAction action = shareOpenGraphContent.getAction();
        final Bundle bundle = action.getBundle();
        this.addCommonParameters(bundle, (ShareContent)shareOpenGraphContent);
        if (!Utility.isNullOrEmpty(this.getMessage())) {
            bundle.putString("message", this.getMessage());
        }
        this.stageOpenGraphAction(bundle, new CollectionMapper.OnMapperCompleteListener() {
            @Override
            public void onComplete() {
                try {
                    handleImagesOnAction(bundle);
                    new GraphRequest(AccessToken.getCurrentAccessToken(), ShareApi.this.getGraphPath(URLEncoder.encode(action.getActionType(), "UTF-8")), bundle, HttpMethod.POST, graphRequest$Callback).executeAsync();
                }
                catch (UnsupportedEncodingException ex) {
                    ShareInternalUtility.invokeCallbackWithException(facebookCallback, (Exception)ex);
                }
            }
            
            @Override
            public void onError(final FacebookException ex) {
                ShareInternalUtility.invokeCallbackWithException(facebookCallback, (Exception)ex);
            }
        });
    }
    
    private void sharePhotoContent(final SharePhotoContent p0, final FacebookCallback<Sharer$Result> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: iconst_0       
        //     5: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //     8: invokespecial   com/facebook/internal/Mutable.<init>:(Ljava/lang/Object;)V
        //    11: astore          5
        //    13: invokestatic    com/facebook/AccessToken.getCurrentAccessToken:()Lcom/facebook/AccessToken;
        //    16: astore          6
        //    18: new             Ljava/util/ArrayList;
        //    21: dup            
        //    22: invokespecial   java/util/ArrayList.<init>:()V
        //    25: astore          7
        //    27: new             Lcom/facebook/share/ShareApi$3;
        //    30: dup            
        //    31: aload_0        
        //    32: new             Ljava/util/ArrayList;
        //    35: dup            
        //    36: invokespecial   java/util/ArrayList.<init>:()V
        //    39: new             Ljava/util/ArrayList;
        //    42: dup            
        //    43: invokespecial   java/util/ArrayList.<init>:()V
        //    46: aload           5
        //    48: aload_2        
        //    49: invokespecial   com/facebook/share/ShareApi$3.<init>:(Lcom/facebook/share/ShareApi;Ljava/util/ArrayList;Ljava/util/ArrayList;Lcom/facebook/internal/Mutable;Lcom/facebook/FacebookCallback;)V
        //    52: astore          8
        //    54: aload_1        
        //    55: invokevirtual   com/facebook/share/model/SharePhotoContent.getPhotos:()Ljava/util/List;
        //    58: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    63: astore          9
        //    65: aload           9
        //    67: invokeinterface java/util/Iterator.hasNext:()Z
        //    72: ifeq            203
        //    75: aload           9
        //    77: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    82: checkcast       Lcom/facebook/share/model/SharePhoto;
        //    85: astore_3       
        //    86: aload_0        
        //    87: aload_3        
        //    88: aload_1        
        //    89: invokespecial   com/facebook/share/ShareApi.getSharePhotoCommonParameters:(Lcom/facebook/share/model/SharePhoto;Lcom/facebook/share/model/SharePhotoContent;)Landroid/os/Bundle;
        //    92: astore          10
        //    94: aload_3        
        //    95: invokevirtual   com/facebook/share/model/SharePhoto.getBitmap:()Landroid/graphics/Bitmap;
        //    98: astore          11
        //   100: aload_3        
        //   101: invokevirtual   com/facebook/share/model/SharePhoto.getImageUrl:()Landroid/net/Uri;
        //   104: astore          12
        //   106: aload_3        
        //   107: invokevirtual   com/facebook/share/model/SharePhoto.getCaption:()Ljava/lang/String;
        //   110: astore          4
        //   112: aload           4
        //   114: astore_3       
        //   115: aload           4
        //   117: ifnonnull       125
        //   120: aload_0        
        //   121: invokevirtual   com/facebook/share/ShareApi.getMessage:()Ljava/lang/String;
        //   124: astore_3       
        //   125: aload           11
        //   127: ifnull          171
        //   130: aload           7
        //   132: aload           6
        //   134: aload_0        
        //   135: ldc             "photos"
        //   137: invokespecial   com/facebook/share/ShareApi.getGraphPath:(Ljava/lang/String;)Ljava/lang/String;
        //   140: aload           11
        //   142: aload_3        
        //   143: aload           10
        //   145: aload           8
        //   147: invokestatic    com/facebook/GraphRequest.newUploadPhotoRequest:(Lcom/facebook/AccessToken;Ljava/lang/String;Landroid/graphics/Bitmap;Ljava/lang/String;Landroid/os/Bundle;Lcom/facebook/GraphRequest$Callback;)Lcom/facebook/GraphRequest;
        //   150: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   153: pop            
        //   154: goto            65
        //   157: astore_1       
        //   158: aload_2        
        //   159: aload_1        
        //   160: invokestatic    com/facebook/share/internal/ShareInternalUtility.invokeCallbackWithException:(Lcom/facebook/FacebookCallback;Ljava/lang/Exception;)V
        //   163: return         
        //   164: astore_1       
        //   165: aload_2        
        //   166: aload_1        
        //   167: invokestatic    com/facebook/share/internal/ShareInternalUtility.invokeCallbackWithException:(Lcom/facebook/FacebookCallback;Ljava/lang/Exception;)V
        //   170: return         
        //   171: aload           12
        //   173: ifnull          65
        //   176: aload           7
        //   178: aload           6
        //   180: aload_0        
        //   181: ldc             "photos"
        //   183: invokespecial   com/facebook/share/ShareApi.getGraphPath:(Ljava/lang/String;)Ljava/lang/String;
        //   186: aload           12
        //   188: aload_3        
        //   189: aload           10
        //   191: aload           8
        //   193: invokestatic    com/facebook/GraphRequest.newUploadPhotoRequest:(Lcom/facebook/AccessToken;Ljava/lang/String;Landroid/net/Uri;Ljava/lang/String;Landroid/os/Bundle;Lcom/facebook/GraphRequest$Callback;)Lcom/facebook/GraphRequest;
        //   196: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   199: pop            
        //   200: goto            65
        //   203: aload           5
        //   205: aload           5
        //   207: getfield        com/facebook/internal/Mutable.value:Ljava/lang/Object;
        //   210: checkcast       Ljava/lang/Integer;
        //   213: invokevirtual   java/lang/Integer.intValue:()I
        //   216: aload           7
        //   218: invokevirtual   java/util/ArrayList.size:()I
        //   221: iadd           
        //   222: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   225: putfield        com/facebook/internal/Mutable.value:Ljava/lang/Object;
        //   228: aload           7
        //   230: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   233: astore_1       
        //   234: aload_1        
        //   235: invokeinterface java/util/Iterator.hasNext:()Z
        //   240: ifeq            163
        //   243: aload_1        
        //   244: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   249: checkcast       Lcom/facebook/GraphRequest;
        //   252: invokevirtual   com/facebook/GraphRequest.executeAsync:()Lcom/facebook/GraphRequestAsyncTask;
        //   255: pop            
        //   256: goto            234
        //    Signature:
        //  (Lcom/facebook/share/model/SharePhotoContent;Lcom/facebook/FacebookCallback<Lcom/facebook/share/Sharer$Result;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  54     65     157    163    Ljava/io/FileNotFoundException;
        //  65     86     157    163    Ljava/io/FileNotFoundException;
        //  86     94     164    171    Lorg/json/JSONException;
        //  86     94     157    163    Ljava/io/FileNotFoundException;
        //  94     112    157    163    Ljava/io/FileNotFoundException;
        //  120    125    157    163    Ljava/io/FileNotFoundException;
        //  130    154    157    163    Ljava/io/FileNotFoundException;
        //  165    170    157    163    Ljava/io/FileNotFoundException;
        //  176    200    157    163    Ljava/io/FileNotFoundException;
        //  203    234    157    163    Ljava/io/FileNotFoundException;
        //  234    256    157    163    Ljava/io/FileNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0125:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void shareVideoContent(final ShareVideoContent shareVideoContent, final FacebookCallback<Sharer$Result> facebookCallback) {
        try {
            VideoUploader.uploadAsync(shareVideoContent, this.getGraphNode(), facebookCallback);
        }
        catch (FileNotFoundException ex) {
            ShareInternalUtility.invokeCallbackWithException((FacebookCallback)facebookCallback, (Exception)ex);
        }
    }
    
    private void stageArrayList(final ArrayList list, final CollectionMapper.OnMapValueCompleteListener onMapValueCompleteListener) {
        final JSONArray jsonArray = new JSONArray();
        this.stageCollectionValues((CollectionMapper.Collection<Object>)new CollectionMapper.Collection<Integer>() {
            public Object get(final Integer n) {
                return list.get(n);
            }
            
            @Override
            public Iterator<Integer> keyIterator() {
                return new Iterator<Integer>() {
                    final /* synthetic */ Mutable val$current = new Mutable((T)0);
                    final /* synthetic */ int val$size = list.size();
                    
                    @Override
                    public boolean hasNext() {
                        return (int)this.val$current.value < this.val$size;
                    }
                    
                    @Override
                    public Integer next() {
                        final Integer n = (Integer)this.val$current.value;
                        final Mutable val$current = this.val$current;
                        val$current.value = (T)Integer.valueOf((int)val$current.value + 1);
                        return n;
                    }
                    
                    @Override
                    public void remove() {
                    }
                };
            }
            
            public void set(final Integer n, final Object o, final OnErrorListener onErrorListener) {
                try {
                    jsonArray.put((int)n, o);
                }
                catch (JSONException ex) {
                    String localizedMessage;
                    if ((localizedMessage = ex.getLocalizedMessage()) == null) {
                        localizedMessage = "Error staging object.";
                    }
                    onErrorListener.onError(new FacebookException(localizedMessage));
                }
            }
        }, new CollectionMapper.OnMapperCompleteListener() {
            @Override
            public void onComplete() {
                onMapValueCompleteListener.onComplete(jsonArray);
            }
            
            @Override
            public void onError(final FacebookException ex) {
                ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(ex);
            }
        });
    }
    
    private <T> void stageCollectionValues(final CollectionMapper.Collection<T> collection, final CollectionMapper.OnMapperCompleteListener onMapperCompleteListener) {
        CollectionMapper.iterate((CollectionMapper.Collection<Object>)collection, (CollectionMapper.ValueMapper)new CollectionMapper.ValueMapper() {
            @Override
            public void mapValue(final Object o, final OnMapValueCompleteListener onMapValueCompleteListener) {
                if (o instanceof ArrayList) {
                    ShareApi.this.stageArrayList((ArrayList)o, onMapValueCompleteListener);
                    return;
                }
                if (o instanceof ShareOpenGraphObject) {
                    ShareApi.this.stageOpenGraphObject((ShareOpenGraphObject)o, onMapValueCompleteListener);
                    return;
                }
                if (o instanceof SharePhoto) {
                    ShareApi.this.stagePhoto((SharePhoto)o, onMapValueCompleteListener);
                    return;
                }
                onMapValueCompleteListener.onComplete(o);
            }
        }, onMapperCompleteListener);
    }
    
    private void stageOpenGraphAction(final Bundle bundle, final CollectionMapper.OnMapperCompleteListener onMapperCompleteListener) {
        this.stageCollectionValues((CollectionMapper.Collection<Object>)new CollectionMapper.Collection<String>() {
            public Object get(final String s) {
                return bundle.get(s);
            }
            
            @Override
            public Iterator<String> keyIterator() {
                return bundle.keySet().iterator();
            }
            
            public void set(final String s, final Object o, final OnErrorListener onErrorListener) {
                if (!Utility.putJSONValueInBundle(bundle, s, o)) {
                    onErrorListener.onError(new FacebookException("Unexpected value: " + o.toString()));
                }
            }
        }, onMapperCompleteListener);
    }
    
    private void stageOpenGraphObject(final ShareOpenGraphObject shareOpenGraphObject, final CollectionMapper.OnMapValueCompleteListener onMapValueCompleteListener) {
        String s;
        if ((s = shareOpenGraphObject.getString("type")) == null) {
            s = shareOpenGraphObject.getString("og:type");
        }
        if (s == null) {
            ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookException("Open Graph objects must contain a type value."));
            return;
        }
        final JSONObject jsonObject = new JSONObject();
        this.stageCollectionValues((CollectionMapper.Collection<Object>)new CollectionMapper.Collection<String>() {
            public Object get(final String s) {
                return shareOpenGraphObject.get(s);
            }
            
            @Override
            public Iterator<String> keyIterator() {
                return shareOpenGraphObject.keySet().iterator();
            }
            
            public void set(String localizedMessage, final Object o, final OnErrorListener onErrorListener) {
                try {
                    jsonObject.put(localizedMessage, o);
                }
                catch (JSONException ex) {
                    if ((localizedMessage = ex.getLocalizedMessage()) == null) {
                        localizedMessage = "Error staging object.";
                    }
                    onErrorListener.onError(new FacebookException(localizedMessage));
                }
            }
        }, new CollectionMapper.OnMapperCompleteListener() {
            final /* synthetic */ GraphRequest$Callback val$requestCallback = new GraphRequest$Callback(this, onMapValueCompleteListener) {
                final /* synthetic */ OnMapValueCompleteListener val$onOpenGraphObjectStagedListener;
                
                public void onCompleted(final GraphResponse graphResponse) {
                    final FacebookRequestError error = graphResponse.getError();
                    if (error != null) {
                        String errorMessage;
                        if ((errorMessage = error.getErrorMessage()) == null) {
                            errorMessage = "Error staging Open Graph object.";
                        }
                        ((CollectionMapper.OnErrorListener)this.val$onOpenGraphObjectStagedListener).onError((FacebookException)new FacebookGraphResponseException(graphResponse, errorMessage));
                        return;
                    }
                    final JSONObject jsonObject = graphResponse.getJSONObject();
                    if (jsonObject == null) {
                        ((CollectionMapper.OnErrorListener)this.val$onOpenGraphObjectStagedListener).onError((FacebookException)new FacebookGraphResponseException(graphResponse, "Error staging Open Graph object."));
                        return;
                    }
                    final String optString = jsonObject.optString("id");
                    if (optString == null) {
                        ((CollectionMapper.OnErrorListener)this.val$onOpenGraphObjectStagedListener).onError((FacebookException)new FacebookGraphResponseException(graphResponse, "Error staging Open Graph object."));
                        return;
                    }
                    this.val$onOpenGraphObjectStagedListener.onComplete(optString);
                }
            };
            
            @Override
            public void onComplete() {
                final String string = jsonObject.toString();
                final Bundle bundle = new Bundle();
                bundle.putString("object", string);
                try {
                    new GraphRequest(AccessToken.getCurrentAccessToken(), ShareApi.this.getGraphPath("objects/" + URLEncoder.encode(s, "UTF-8")), bundle, HttpMethod.POST, this.val$requestCallback).executeAsync();
                }
                catch (UnsupportedEncodingException ex) {
                    String localizedMessage;
                    if ((localizedMessage = ex.getLocalizedMessage()) == null) {
                        localizedMessage = "Error staging Open Graph object.";
                    }
                    ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookException(localizedMessage));
                }
            }
            
            @Override
            public void onError(final FacebookException ex) {
                ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(ex);
            }
        });
    }
    
    private void stagePhoto(final SharePhoto sharePhoto, final CollectionMapper.OnMapValueCompleteListener onMapValueCompleteListener) {
        final Bitmap bitmap = sharePhoto.getBitmap();
        final Uri imageUrl = sharePhoto.getImageUrl();
        if (bitmap != null || imageUrl != null) {
            final GraphRequest$Callback graphRequest$Callback = (GraphRequest$Callback)new GraphRequest$Callback() {
                public void onCompleted(final GraphResponse graphResponse) {
                    final FacebookRequestError error = graphResponse.getError();
                    if (error != null) {
                        String errorMessage;
                        if ((errorMessage = error.getErrorMessage()) == null) {
                            errorMessage = "Error staging photo.";
                        }
                        ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError((FacebookException)new FacebookGraphResponseException(graphResponse, errorMessage));
                        return;
                    }
                    final JSONObject jsonObject = graphResponse.getJSONObject();
                    if (jsonObject == null) {
                        ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookException("Error staging photo."));
                        return;
                    }
                    final String optString = jsonObject.optString("uri");
                    if (optString == null) {
                        ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookException("Error staging photo."));
                        return;
                    }
                    final JSONObject jsonObject2 = new JSONObject();
                    try {
                        jsonObject2.put("url", (Object)optString);
                        jsonObject2.put("user_generated", sharePhoto.getUserGenerated());
                        onMapValueCompleteListener.onComplete(jsonObject2);
                    }
                    catch (JSONException ex) {
                        String localizedMessage;
                        if ((localizedMessage = ex.getLocalizedMessage()) == null) {
                            localizedMessage = "Error staging photo.";
                        }
                        ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookException(localizedMessage));
                    }
                }
            };
            if (bitmap != null) {
                ShareInternalUtility.newUploadStagingResourceWithImageRequest(AccessToken.getCurrentAccessToken(), bitmap, (GraphRequest$Callback)graphRequest$Callback).executeAsync();
                return;
            }
            try {
                ShareInternalUtility.newUploadStagingResourceWithImageRequest(AccessToken.getCurrentAccessToken(), imageUrl, (GraphRequest$Callback)graphRequest$Callback).executeAsync();
                return;
            }
            catch (FileNotFoundException ex) {
                String localizedMessage;
                if ((localizedMessage = ex.getLocalizedMessage()) == null) {
                    localizedMessage = "Error staging photo.";
                }
                ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookException(localizedMessage));
                return;
            }
        }
        ((CollectionMapper.OnErrorListener)onMapValueCompleteListener).onError(new FacebookException("Photos must have an imageURL or bitmap."));
    }
    
    public boolean canShare() {
        if (this.getShareContent() != null) {
            final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            if (AccessToken.isCurrentAccessTokenActive()) {
                final Set permissions = currentAccessToken.getPermissions();
                if (permissions == null || !permissions.contains("publish_actions")) {
                    Log.w("ShareApi", "The publish_actions permissions are missing, the share will fail unless this app was authorized to publish in another installation.");
                }
                return true;
            }
        }
        return false;
    }
    
    public String getGraphNode() {
        return this.graphNode;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public ShareContent getShareContent() {
        return this.shareContent;
    }
    
    public void setGraphNode(final String graphNode) {
        this.graphNode = graphNode;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public void share(final FacebookCallback<Sharer$Result> facebookCallback) {
        if (!this.canShare()) {
            ShareInternalUtility.invokeCallbackWithError((FacebookCallback)facebookCallback, "Insufficient permissions for sharing content via Api.");
        }
        else {
            final ShareContent shareContent = this.getShareContent();
            try {
                ShareContentValidation.validateForApiShare(shareContent);
                if (shareContent instanceof ShareLinkContent) {
                    this.shareLinkContent((ShareLinkContent)shareContent, facebookCallback);
                    return;
                }
            }
            catch (FacebookException ex) {
                ShareInternalUtility.invokeCallbackWithException((FacebookCallback)facebookCallback, (Exception)ex);
                return;
            }
            if (shareContent instanceof SharePhotoContent) {
                this.sharePhotoContent((SharePhotoContent)shareContent, facebookCallback);
                return;
            }
            if (shareContent instanceof ShareVideoContent) {
                this.shareVideoContent((ShareVideoContent)shareContent, facebookCallback);
                return;
            }
            if (shareContent instanceof ShareOpenGraphContent) {
                this.shareOpenGraphContent((ShareOpenGraphContent)shareContent, facebookCallback);
            }
        }
    }
}
