package replace.your.app_package.provider;

import com.eaglesakura.sloth.provider.ContextProvider;
import com.eaglesakura.android.garnet.Provide;
import com.eaglesakura.thread.LazyObjectHolder1;

import android.content.Context;

import replace.your.app_package.data.db.AppDatabase;

/**
 * DBの依存管理を行う
 */
public class AppDatabaseProvider extends ContextProvider {

    /**
     * 読み取り専用オブジェクト
     */
    static LazyObjectHolder1<AppDatabase, Context> sReadableDatabase = new LazyObjectHolder1<>(context -> new AppDatabase(context));

    /**
     * 書込み可能オブジェクト
     */
    static LazyObjectHolder1<AppDatabase, Context> sWritableDatabase = new LazyObjectHolder1<>(context -> new AppDatabase(context));

    /**
     * 読み込みのみのモードで開く
     */
    public static final String NAME_READ = "NAME_READ";

    /**
     * 書き込み可能モードで取得
     */
    public static final String NAME_WRITE = "NAME_WRITE";

    @Override
    public void onDependsCompleted(Object inject) {
    }

    /**
     * 書き込みモードで開く
     */
    @Provide(name = NAME_WRITE)
    public AppDatabase provideDatabaseWritable() {
        return sWritableDatabase.get(getApplication());
    }

    /**
     * 読み取りモードで開く
     */
    @Provide(name = NAME_READ)
    public AppDatabase provideDatabaseReadable() {
        return sReadableDatabase.get(getApplication());
    }

    @Override
    public void onInjectCompleted(Object inject) {
    }
}
