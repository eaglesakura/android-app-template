package replace.your.app_package;

import com.eaglesakura.sloth.Sloth;

import android.app.Application;

import replace.your.app_package.util.AppLog;

/**
 * フレームワーク対応のCustom Applicationを実装する
 *
 * FrameworkCentral.onCreate(Application)を必ず呼び出す必要がある。
 */
public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AppLog.inject(this);

        // フレームワーク初期化を行なう
        Sloth.init(this);

        // FIXME: 必要に応じてDeploygateの初期化を行なう。
//        DebugUtil.requestDeploygateInstall(this);
    }
}
