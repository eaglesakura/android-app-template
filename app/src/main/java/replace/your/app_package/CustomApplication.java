package replace.your.app_package;

import com.eaglesakura.android.framework.FrameworkCentral;

import android.app.Application;
import android.support.annotation.NonNull;

import replace.your.app_package.util.AppLog;

/**
 * フレームワーク対応のCustom Applicationを実装する
 *
 * FrameworkCentral.onCreate(Application)を必ず呼び出す必要がある。
 */
public class CustomApplication extends Application implements FrameworkCentral.FrameworkApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        AppLog.inject(this);

        // フレームワーク初期化を行なう
        FrameworkCentral.onApplicationCreate(this);

        // FIXME: 必要に応じてDeploygateの初期化を行なう。
//        FrameworkCentral.requestDeploygateInstall();
    }

    /**
     * アプリアップデート時 / 初回起動時等にマイグレーション等が必要ならば記述する
     */
    @Override
    public void onApplicationUpdated(int oldVersionCode, int newVersionCode, String oldVersionName, String newVersionName) {
        AppLog.system("App Updated old(%d:%s) new(%d:%s)", oldVersionCode, oldVersionName, newVersionCode, newVersionName);
    }

    private Object mCentral;

    @Override
    public void onRequestSaveCentral(@NonNull Object central) {
        mCentral = central;
    }
}
