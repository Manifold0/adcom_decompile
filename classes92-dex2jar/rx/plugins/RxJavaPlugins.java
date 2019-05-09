// 
// Decompiled by Procyon v0.5.34
// 

package rx.plugins;

import java.util.Hashtable;
import rx.annotations.Experimental;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public class RxJavaPlugins
{
    static final RxJavaErrorHandler DEFAULT_ERROR_HANDLER;
    private static final RxJavaPlugins INSTANCE;
    private final AtomicReference<RxJavaCompletableExecutionHook> completableExecutionHook;
    private final AtomicReference<RxJavaErrorHandler> errorHandler;
    private final AtomicReference<RxJavaObservableExecutionHook> observableExecutionHook;
    private final AtomicReference<RxJavaSchedulersHook> schedulersHook;
    private final AtomicReference<RxJavaSingleExecutionHook> singleExecutionHook;
    
    static {
        INSTANCE = new RxJavaPlugins();
        DEFAULT_ERROR_HANDLER = new RxJavaErrorHandler() {};
    }
    
    RxJavaPlugins() {
        this.errorHandler = new AtomicReference<RxJavaErrorHandler>();
        this.observableExecutionHook = new AtomicReference<RxJavaObservableExecutionHook>();
        this.singleExecutionHook = new AtomicReference<RxJavaSingleExecutionHook>();
        this.completableExecutionHook = new AtomicReference<RxJavaCompletableExecutionHook>();
        this.schedulersHook = new AtomicReference<RxJavaSchedulersHook>();
    }
    
    @Deprecated
    public static RxJavaPlugins getInstance() {
        return RxJavaPlugins.INSTANCE;
    }
    
    static Object getPluginImplementationViaProperty(final Class<?> clazz, Properties s) {
        final Properties properties = (Properties)((Hashtable)s).clone();
        final String simpleName = clazz.getSimpleName();
        final String s2 = (String)(s = properties.getProperty("rxjava.plugin." + simpleName + ".implementation"));
        Label_0235: {
            if (s2 == null) {
                final Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator();
                String string;
                Map.Entry<Object, Object> entry;
                do {
                    s = s2;
                    if (!iterator.hasNext()) {
                        break Label_0235;
                    }
                    entry = iterator.next();
                    string = entry.getKey().toString();
                } while (!string.startsWith("rxjava.plugin.") || !string.endsWith(".class") || !simpleName.equals(entry.getValue().toString()));
                final String string2 = "rxjava.plugin." + string.substring(0, string.length() - ".class".length()).substring("rxjava.plugin.".length()) + ".impl";
                if ((s = properties.getProperty(string2)) == null) {
                    throw new IllegalStateException("Implementing class declaration for " + simpleName + " missing: " + string2);
                }
            }
        }
        if (s != null) {
            try {
                return Class.forName((String)s).asSubclass(clazz).newInstance();
            }
            catch (ClassCastException ex) {
                throw new IllegalStateException(simpleName + " implementation is not an instance of " + simpleName + ": " + (String)s, ex);
            }
            catch (ClassNotFoundException ex2) {
                throw new IllegalStateException(simpleName + " implementation class not found: " + (String)s, ex2);
            }
            catch (InstantiationException ex3) {
                throw new IllegalStateException(simpleName + " implementation not able to be instantiated: " + (String)s, ex3);
            }
            catch (IllegalAccessException ex4) {
                throw new IllegalStateException(simpleName + " implementation not able to be accessed: " + (String)s, ex4);
            }
        }
        return null;
    }
    
    @Experimental
    public RxJavaCompletableExecutionHook getCompletableExecutionHook() {
        if (this.completableExecutionHook.get() == null) {
            final Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaCompletableExecutionHook.class, System.getProperties());
            if (pluginImplementationViaProperty == null) {
                this.completableExecutionHook.compareAndSet(null, new RxJavaCompletableExecutionHook() {});
            }
            else {
                this.completableExecutionHook.compareAndSet(null, (RxJavaCompletableExecutionHook)pluginImplementationViaProperty);
            }
        }
        return this.completableExecutionHook.get();
    }
    
    public RxJavaErrorHandler getErrorHandler() {
        if (this.errorHandler.get() == null) {
            final Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaErrorHandler.class, System.getProperties());
            if (pluginImplementationViaProperty == null) {
                this.errorHandler.compareAndSet(null, RxJavaPlugins.DEFAULT_ERROR_HANDLER);
            }
            else {
                this.errorHandler.compareAndSet(null, (RxJavaErrorHandler)pluginImplementationViaProperty);
            }
        }
        return this.errorHandler.get();
    }
    
    public RxJavaObservableExecutionHook getObservableExecutionHook() {
        if (this.observableExecutionHook.get() == null) {
            final Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaObservableExecutionHook.class, System.getProperties());
            if (pluginImplementationViaProperty == null) {
                this.observableExecutionHook.compareAndSet(null, RxJavaObservableExecutionHookDefault.getInstance());
            }
            else {
                this.observableExecutionHook.compareAndSet(null, (RxJavaObservableExecutionHook)pluginImplementationViaProperty);
            }
        }
        return this.observableExecutionHook.get();
    }
    
    public RxJavaSchedulersHook getSchedulersHook() {
        if (this.schedulersHook.get() == null) {
            final Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaSchedulersHook.class, System.getProperties());
            if (pluginImplementationViaProperty == null) {
                this.schedulersHook.compareAndSet(null, RxJavaSchedulersHook.getDefaultInstance());
            }
            else {
                this.schedulersHook.compareAndSet(null, (RxJavaSchedulersHook)pluginImplementationViaProperty);
            }
        }
        return this.schedulersHook.get();
    }
    
    public RxJavaSingleExecutionHook getSingleExecutionHook() {
        if (this.singleExecutionHook.get() == null) {
            final Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaSingleExecutionHook.class, System.getProperties());
            if (pluginImplementationViaProperty == null) {
                this.singleExecutionHook.compareAndSet(null, RxJavaSingleExecutionHookDefault.getInstance());
            }
            else {
                this.singleExecutionHook.compareAndSet(null, (RxJavaSingleExecutionHook)pluginImplementationViaProperty);
            }
        }
        return this.singleExecutionHook.get();
    }
    
    @Experimental
    public void registerCompletableExecutionHook(final RxJavaCompletableExecutionHook rxJavaCompletableExecutionHook) {
        if (!this.completableExecutionHook.compareAndSet(null, rxJavaCompletableExecutionHook)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.singleExecutionHook.get());
        }
    }
    
    public void registerErrorHandler(final RxJavaErrorHandler rxJavaErrorHandler) {
        if (!this.errorHandler.compareAndSet(null, rxJavaErrorHandler)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.errorHandler.get());
        }
    }
    
    public void registerObservableExecutionHook(final RxJavaObservableExecutionHook rxJavaObservableExecutionHook) {
        if (!this.observableExecutionHook.compareAndSet(null, rxJavaObservableExecutionHook)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.observableExecutionHook.get());
        }
    }
    
    public void registerSchedulersHook(final RxJavaSchedulersHook rxJavaSchedulersHook) {
        if (!this.schedulersHook.compareAndSet(null, rxJavaSchedulersHook)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.schedulersHook.get());
        }
    }
    
    public void registerSingleExecutionHook(final RxJavaSingleExecutionHook rxJavaSingleExecutionHook) {
        if (!this.singleExecutionHook.compareAndSet(null, rxJavaSingleExecutionHook)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.singleExecutionHook.get());
        }
    }
    
    @Experimental
    public void reset() {
        RxJavaPlugins.INSTANCE.errorHandler.set(null);
        RxJavaPlugins.INSTANCE.observableExecutionHook.set(null);
        RxJavaPlugins.INSTANCE.singleExecutionHook.set(null);
        RxJavaPlugins.INSTANCE.completableExecutionHook.set(null);
        RxJavaPlugins.INSTANCE.schedulersHook.set(null);
    }
}
