// 
// Decompiled by Procyon v0.5.34
// 

package retrofit2;

import okhttp3.OkHttpClient;
import java.util.Collection;
import java.util.ArrayList;
import okhttp3.ResponseBody;
import okhttp3.RequestBody;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Executor;
import okhttp3.Call$Factory;
import okhttp3.HttpUrl;
import java.util.List;

public final class Retrofit
{
    final List<CallAdapter.Factory> adapterFactories;
    final HttpUrl baseUrl;
    final Call$Factory callFactory;
    final Executor callbackExecutor;
    final List<Converter.Factory> converterFactories;
    private final Map<Method, ServiceMethod<?, ?>> serviceMethodCache;
    final boolean validateEagerly;
    
    Retrofit(final Call$Factory callFactory, final HttpUrl baseUrl, final List<Converter.Factory> list, final List<CallAdapter.Factory> list2, final Executor callbackExecutor, final boolean validateEagerly) {
        this.serviceMethodCache = new ConcurrentHashMap<Method, ServiceMethod<?, ?>>();
        this.callFactory = callFactory;
        this.baseUrl = baseUrl;
        this.converterFactories = Collections.unmodifiableList((List<? extends Converter.Factory>)list);
        this.adapterFactories = Collections.unmodifiableList((List<? extends CallAdapter.Factory>)list2);
        this.callbackExecutor = callbackExecutor;
        this.validateEagerly = validateEagerly;
    }
    
    private void eagerlyValidateMethods(final Class<?> clazz) {
        final Platform value = Platform.get();
        final Method[] declaredMethods = clazz.getDeclaredMethods();
        for (int length = declaredMethods.length, i = 0; i < length; ++i) {
            final Method method = declaredMethods[i];
            if (!value.isDefaultMethod(method)) {
                this.loadServiceMethod(method);
            }
        }
    }
    
    public HttpUrl baseUrl() {
        return this.baseUrl;
    }
    
    public CallAdapter<?, ?> callAdapter(final Type type, final Annotation[] array) {
        return this.nextCallAdapter(null, type, array);
    }
    
    public List<CallAdapter.Factory> callAdapterFactories() {
        return this.adapterFactories;
    }
    
    public Call$Factory callFactory() {
        return this.callFactory;
    }
    
    public Executor callbackExecutor() {
        return this.callbackExecutor;
    }
    
    public List<Converter.Factory> converterFactories() {
        return this.converterFactories;
    }
    
    public <T> T create(final Class<T> clazz) {
        Utils.validateServiceInterface(clazz);
        if (this.validateEagerly) {
            this.eagerlyValidateMethods(clazz);
        }
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, new InvocationHandler() {
            private final Platform platform = Platform.get();
            
            @Override
            public Object invoke(final Object o, final Method method, final Object[] array) throws Throwable {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, array);
                }
                if (this.platform.isDefaultMethod(method)) {
                    return this.platform.invokeDefaultMethod(method, clazz, o, array);
                }
                final ServiceMethod<?, ?> loadServiceMethod = Retrofit.this.loadServiceMethod(method);
                return loadServiceMethod.callAdapter.adapt(new OkHttpCall<Object>((ServiceMethod<Object, ?>)loadServiceMethod, array));
            }
        });
    }
    
    ServiceMethod<?, ?> loadServiceMethod(final Method method) {
        final ServiceMethod<?, ?> serviceMethod = this.serviceMethodCache.get(method);
        if (serviceMethod != null) {
            return serviceMethod;
        }
        synchronized (this.serviceMethodCache) {
            ServiceMethod<?, ?> build;
            if ((build = this.serviceMethodCache.get(method)) == null) {
                build = (ServiceMethod<?, ?>)new ServiceMethod.Builder(this, method).build();
                this.serviceMethodCache.put(method, build);
            }
            return build;
        }
    }
    
    public Builder newBuilder() {
        return new Builder(this);
    }
    
    public CallAdapter<?, ?> nextCallAdapter(final CallAdapter.Factory factory, final Type type, final Annotation[] array) {
        Utils.checkNotNull(type, "returnType == null");
        Utils.checkNotNull(array, "annotations == null");
        int j;
        int i;
        for (i = (j = this.adapterFactories.indexOf(factory) + 1); j < this.adapterFactories.size(); ++j) {
            final CallAdapter<?, ?> value = this.adapterFactories.get(j).get(type, array, this);
            if (value != null) {
                return value;
            }
        }
        final StringBuilder append = new StringBuilder("Could not locate call adapter for ").append(type).append(".\n");
        if (factory != null) {
            append.append("  Skipped:");
            for (int k = 0; k < i; ++k) {
                append.append("\n   * ").append(this.adapterFactories.get(k).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        while (i < this.adapterFactories.size()) {
            append.append("\n   * ").append(this.adapterFactories.get(i).getClass().getName());
            ++i;
        }
        throw new IllegalArgumentException(append.toString());
    }
    
    public <T> Converter<T, RequestBody> nextRequestBodyConverter(final Converter.Factory factory, final Type type, final Annotation[] array, final Annotation[] array2) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(array, "parameterAnnotations == null");
        Utils.checkNotNull(array2, "methodAnnotations == null");
        int j;
        int i;
        for (i = (j = this.converterFactories.indexOf(factory) + 1); j < this.converterFactories.size(); ++j) {
            final Converter<?, RequestBody> requestBodyConverter = this.converterFactories.get(j).requestBodyConverter(type, array, array2, this);
            if (requestBodyConverter != null) {
                return (Converter<T, RequestBody>)requestBodyConverter;
            }
        }
        final StringBuilder append = new StringBuilder("Could not locate RequestBody converter for ").append(type).append(".\n");
        if (factory != null) {
            append.append("  Skipped:");
            for (int k = 0; k < i; ++k) {
                append.append("\n   * ").append(this.converterFactories.get(k).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        while (i < this.converterFactories.size()) {
            append.append("\n   * ").append(this.converterFactories.get(i).getClass().getName());
            ++i;
        }
        throw new IllegalArgumentException(append.toString());
    }
    
    public <T> Converter<ResponseBody, T> nextResponseBodyConverter(final Converter.Factory factory, final Type type, final Annotation[] array) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(array, "annotations == null");
        int j;
        int i;
        for (i = (j = this.converterFactories.indexOf(factory) + 1); j < this.converterFactories.size(); ++j) {
            final Converter<ResponseBody, ?> responseBodyConverter = this.converterFactories.get(j).responseBodyConverter(type, array, this);
            if (responseBodyConverter != null) {
                return (Converter<ResponseBody, T>)responseBodyConverter;
            }
        }
        final StringBuilder append = new StringBuilder("Could not locate ResponseBody converter for ").append(type).append(".\n");
        if (factory != null) {
            append.append("  Skipped:");
            for (int k = 0; k < i; ++k) {
                append.append("\n   * ").append(this.converterFactories.get(k).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        while (i < this.converterFactories.size()) {
            append.append("\n   * ").append(this.converterFactories.get(i).getClass().getName());
            ++i;
        }
        throw new IllegalArgumentException(append.toString());
    }
    
    public <T> Converter<T, RequestBody> requestBodyConverter(final Type type, final Annotation[] array, final Annotation[] array2) {
        return this.nextRequestBodyConverter(null, type, array, array2);
    }
    
    public <T> Converter<ResponseBody, T> responseBodyConverter(final Type type, final Annotation[] array) {
        return this.nextResponseBodyConverter(null, type, array);
    }
    
    public <T> Converter<T, String> stringConverter(final Type type, final Annotation[] array) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(array, "annotations == null");
        for (int i = 0; i < this.converterFactories.size(); ++i) {
            final Converter<?, String> stringConverter = this.converterFactories.get(i).stringConverter(type, array, this);
            if (stringConverter != null) {
                return (Converter<T, String>)stringConverter;
            }
        }
        return (Converter<T, String>)BuiltInConverters.ToStringConverter.INSTANCE;
    }
    
    public static final class Builder
    {
        private final List<CallAdapter.Factory> adapterFactories;
        private HttpUrl baseUrl;
        private Call$Factory callFactory;
        private Executor callbackExecutor;
        private final List<Converter.Factory> converterFactories;
        private final Platform platform;
        private boolean validateEagerly;
        
        public Builder() {
            this(Platform.get());
        }
        
        Builder(final Platform platform) {
            this.converterFactories = new ArrayList<Converter.Factory>();
            this.adapterFactories = new ArrayList<CallAdapter.Factory>();
            this.platform = platform;
            this.converterFactories.add(new BuiltInConverters());
        }
        
        Builder(final Retrofit retrofit) {
            this.converterFactories = new ArrayList<Converter.Factory>();
            this.adapterFactories = new ArrayList<CallAdapter.Factory>();
            this.platform = Platform.get();
            this.callFactory = retrofit.callFactory;
            this.baseUrl = retrofit.baseUrl;
            this.converterFactories.addAll(retrofit.converterFactories);
            this.adapterFactories.addAll(retrofit.adapterFactories);
            this.adapterFactories.remove(this.adapterFactories.size() - 1);
            this.callbackExecutor = retrofit.callbackExecutor;
            this.validateEagerly = retrofit.validateEagerly;
        }
        
        public Builder addCallAdapterFactory(final CallAdapter.Factory factory) {
            this.adapterFactories.add(Utils.checkNotNull(factory, "factory == null"));
            return this;
        }
        
        public Builder addConverterFactory(final Converter.Factory factory) {
            this.converterFactories.add(Utils.checkNotNull(factory, "factory == null"));
            return this;
        }
        
        public Builder baseUrl(final String s) {
            Utils.checkNotNull(s, "baseUrl == null");
            final HttpUrl parse = HttpUrl.parse(s);
            if (parse == null) {
                throw new IllegalArgumentException("Illegal URL: " + s);
            }
            return this.baseUrl(parse);
        }
        
        public Builder baseUrl(final HttpUrl baseUrl) {
            Utils.checkNotNull(baseUrl, "baseUrl == null");
            final List pathSegments = baseUrl.pathSegments();
            if (!"".equals(pathSegments.get(pathSegments.size() - 1))) {
                throw new IllegalArgumentException("baseUrl must end in /: " + baseUrl);
            }
            this.baseUrl = baseUrl;
            return this;
        }
        
        public Retrofit build() {
            if (this.baseUrl == null) {
                throw new IllegalStateException("Base URL required.");
            }
            Object callFactory;
            if ((callFactory = this.callFactory) == null) {
                callFactory = new OkHttpClient();
            }
            Executor executor;
            if ((executor = this.callbackExecutor) == null) {
                executor = this.platform.defaultCallbackExecutor();
            }
            final ArrayList<CallAdapter.Factory> list = new ArrayList<CallAdapter.Factory>(this.adapterFactories);
            list.add(this.platform.defaultCallAdapterFactory(executor));
            return new Retrofit((Call$Factory)callFactory, this.baseUrl, new ArrayList<Converter.Factory>(this.converterFactories), list, executor, this.validateEagerly);
        }
        
        public Builder callFactory(final Call$Factory call$Factory) {
            this.callFactory = Utils.checkNotNull(call$Factory, "factory == null");
            return this;
        }
        
        public Builder callbackExecutor(final Executor executor) {
            this.callbackExecutor = Utils.checkNotNull(executor, "executor == null");
            return this;
        }
        
        public Builder client(final OkHttpClient okHttpClient) {
            return this.callFactory(Utils.checkNotNull((Call$Factory)okHttpClient, "client == null"));
        }
        
        public Builder validateEagerly(final boolean validateEagerly) {
            this.validateEagerly = validateEagerly;
            return this;
        }
    }
}
