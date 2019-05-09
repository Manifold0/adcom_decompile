package okhttp3;

import java.io.IOException;

public interface Authenticator {
    public static final Authenticator NONE = new C10411();

    /* renamed from: okhttp3.Authenticator$1 */
    static class C10411 implements Authenticator {
        C10411() {
        }

        public Request authenticate(Route route, Response response) {
            return null;
        }
    }

    Request authenticate(Route route, Response response) throws IOException;
}
