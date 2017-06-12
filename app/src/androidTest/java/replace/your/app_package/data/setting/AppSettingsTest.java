package replace.your.app_package.data.setting;

import com.eaglesakura.android.garnet.Garnet;

import org.junit.Test;

import replace.your.app_package.AppDeviceTestCase;
import replace.your.app_package.provider.AppContextProvider;
import replace.your.app_package.repository.AppSettings;

public class AppSettingsTest extends AppDeviceTestCase {

    @Test
    public void インスタンスが生成できる() throws Throwable {
        AppSettings instance = Garnet.instance(AppContextProvider.class, AppSettings.class);
        assertNotNull(instance);
        assertNotNull(instance.getDebugSetting());
        assertFalse(instance.getDebugSetting().isDebugEnable());
    }
}