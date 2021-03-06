package replace.your.app_package.repository.storage;

import com.eaglesakura.android.device.external.Storage;
import com.eaglesakura.android.garnet.Singleton;
import com.eaglesakura.util.IOUtil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;

/**
 * アプリのストレージ領域管理を行う
 */
@Singleton
public class AppStorageManager {
    @NonNull
    final Context mContext;

    public AppStorageManager(@NonNull Context context) {
        mContext = context;
    }

    /**
     * 外部データインストール領域を取得する
     */
    protected File getExternalDataStorage() {
        return Storage.getExternalDataStorage(mContext).getPath();
    }

    /**
     * データベースディレクトリを取得する
     */
    protected File getDatabaseDirectory() {
        File storage = getExternalDataStorage();
        if (storage.getName().equals("files")) {
            storage = storage.getParentFile();
        }
        return IOUtil.mkdirs(new File(storage, "db"));
    }

    /**
     * 外部データベース領域を取得する
     */
    @Nullable
    public File getExternalDatabasePath(@NonNull String name) {
        return new File(getDatabaseDirectory(), name).getAbsoluteFile();
    }
}
