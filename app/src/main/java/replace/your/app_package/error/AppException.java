package replace.your.app_package.error;

import com.eaglesakura.cerberus.error.TaskCanceledException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;

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

    static Exception getInternal(Exception e) {
        // RuntimeExceptionでラップされている場合、内部を確認する
        if (e instanceof RuntimeException) {
            while (e.getCause() != null && !(e.getCause() instanceof RuntimeException)) {
                e = ((Exception) e.getCause());
            }
        }

        return e;
    }

    public static void throwAppException(Exception e) throws AppException {
        // RuntimeExceptionでラップされている場合、内部を確認する
        e = getInternal(e);

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

    public static void throwAppExceptionOrTaskCanceled(Exception e) throws TaskCanceledException, AppException {
        // RuntimeExceptionでラップされている場合、内部を確認する
        e = getInternal(e);

        if (e instanceof TaskCanceledException) {
            throw (TaskCanceledException) e;
        } else if (e instanceof InterruptedIOException) {
            throw new TaskCanceledException(e);
        } else {
            throwAppException(e);
        }
    }
}
