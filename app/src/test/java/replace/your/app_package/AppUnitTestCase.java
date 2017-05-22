package replace.your.app_package;

import com.eaglesakura.android.AndroidSupportTestCase;
import com.eaglesakura.android.garnet.Garnet;

import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import replace.your.app_package.provider.AppContextProvider;
import replace.your.app_package.provider.TestAppContextProvider;
import replace.your.app_package.util.AppLog;

/**
 * ASのUnitTestではWorkingDirectory設定を行う。
 */
public abstract class AppUnitTestCase extends AndroidSupportTestCase {

    @Override
    public void onSetup() {
        super.onSetup();

        // UnitTest用モジュールへ切り替える
        Garnet.override(AppContextProvider.class, TestAppContextProvider.class);

        AppLog.test("UnitTest Database[%s]", getApplication().getFilesDir().getAbsolutePath());
    }

}
