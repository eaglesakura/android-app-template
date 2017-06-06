package replace.your.app_package;

import com.eaglesakura.android.AndroidSupportTestCase;
import com.eaglesakura.android.garnet.Garnet;

import replace.your.app_package.provider.AppContextProvider;
import replace.your.app_package.provider.LoggerProvider;
import replace.your.app_package.provider.TestAppContextProvider;
import replace.your.app_package.provider.TestLogProvider;
import replace.your.app_package.util.AppLog;

/**
 * ASのUnitTestではWorkingDirectory設定を行う。
 */
public abstract class AppUnitTestCase extends AndroidSupportTestCase {

    @Override
    public void onSetup() {
        super.onSetup();

        // UnitTest用モジュールへ切り替える
        Garnet.override(LoggerProvider.class, TestLogProvider.class);
        Garnet.override(AppContextProvider.class, TestAppContextProvider.class);

        // Log Inject
        AppLog.inject(getContext());
        AppLog.test("UnitTest Database[%s]", getApplication().getFilesDir().getAbsolutePath());
    }

}
