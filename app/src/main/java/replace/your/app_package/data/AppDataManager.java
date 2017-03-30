package replace.your.app_package.data;

import com.eaglesakura.android.db.DaoDatabase;
import com.eaglesakura.android.garnet.Garnet;
import com.eaglesakura.android.garnet.Initializer;
import com.eaglesakura.android.garnet.Inject;

import android.content.Context;
import android.support.annotation.NonNull;

import replace.your.app_package.data.db.AppDatabase;
import replace.your.app_package.provider.AppDatabaseProvider;

/**
 * アプリデータ管理用Managerの基底クラス
 *
 * Read/Write等の処理は内部で隠蔽することで、利用側とDBを切り離す。
 */
public abstract class AppDataManager {

    private Context mContext;

    /**
     * sync管理
     * <p>
     * データは複数スレッドから呼び出されるため、ロックを行ってオブジェクトがコンフリクトしないようにする。
     */
    @NonNull
    private final Object lock = new Object();

    @Inject(value = AppDatabaseProvider.class, name = AppDatabaseProvider.NAME_READ)
    private AppDatabase mReadDatabase;

    @Inject(value = AppDatabaseProvider.class, name = AppDatabaseProvider.NAME_WRITE)
    private AppDatabase mWriteDatabase;

    public AppDataManager(Context context) {
        mContext = context;
    }

    @Initializer
    public void initialize() {
        Garnet.create(this)
                .depend(Context.class, mContext)
                .inject();
    }

    /**
     * 読み込み専用モードで開く
     */
    protected AppDatabase openRead() {
        return mReadDatabase.open(DaoDatabase.FLAG_READ_ONLY);
    }

    /**
     * 書込み可能モードで開く
     */
    protected AppDatabase openWrite() {
        return mReadDatabase.open(0x00);
    }
}
