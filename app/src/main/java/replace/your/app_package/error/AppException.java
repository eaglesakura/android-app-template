package replace.your.app_package.error;

import com.eaglesakura.android.rx.error.TaskCanceledException;

import java.io.FileNotFoundException;
import java.io.IOException;

import replace.your.app_package.error.io.AppDataNotFoundException;
import replace.your.app_package.error.io.AppIOException;

/**
 * アプリ共通例外
 */
public class AppException extends Exception {
    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public static void throwAppException(Throwable e) throws AppException {
        if (e instanceof AppException) {
            throw (AppException) e;
        } else {
            if (e instanceof FileNotFoundException) {
                throw new AppDataNotFoundException(e);
            } else if (e instanceof IOException) {
                throw new AppIOException(e);
            } else {
                throw new AppException(e);
            }
        }
    }

    public static void throwAppExceptionOrTaskCanceled(Throwable e) throws TaskCanceledException, AppException {
        if (e instanceof TaskCanceledException) {
            throw (TaskCanceledException) e;
        } else {
            throwAppException(e);
        }
    }
}
