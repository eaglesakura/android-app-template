package replace.your.app_package.data.db;

import com.eaglesakura.android.db.DaoDatabase;
import com.eaglesakura.android.garnet.Garnet;
import com.eaglesakura.android.garnet.Initializer;
import com.eaglesakura.android.garnet.Inject;

import org.greenrobot.greendao.database.StandardDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import replace.your.app_package.data.storage.AppStorageManager;
import replace.your.app_package.gen.dao.example.DaoMaster;
import replace.your.app_package.gen.dao.example.DaoSession;
import replace.your.app_package.provider.AppStorageProvider;

/**
 * アプリデータ用DB
 */
public class AppDatabase extends DaoDatabase<DaoSession> {

    static final int SUPPORTED_DATABASE_VERSION = 1;

    @Inject(AppStorageProvider.class)
    AppStorageManager mStorageController;

    public AppDatabase(Context context) {
        super(context, DaoMaster.class);
    }


    @Initializer
    public void initialize() {
        Garnet.inject(this);
    }

    @Override
    protected SQLiteOpenHelper createHelper() {
        return new SQLiteOpenHelper(getContext(), mStorageController.getExternalDatabasePath("app_data.db").getAbsolutePath(), null, SUPPORTED_DATABASE_VERSION) {
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                DaoMaster.createAllTables(new StandardDatabase(db), false);
            }
        };
    }
}
