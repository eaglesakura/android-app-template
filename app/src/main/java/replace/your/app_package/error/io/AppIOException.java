package replace.your.app_package.error.io;

import replace.your.app_package.error.AppException;

/**
 * アプリの読み書き例外
 */
public class AppIOException extends AppException {
    public AppIOException() {
    }

    public AppIOException(String message) {
        super(message);
    }

    public AppIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppIOException(Throwable cause) {
        super(cause);
    }
}
