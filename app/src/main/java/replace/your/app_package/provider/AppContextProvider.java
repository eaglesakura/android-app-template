package replace.your.app_package.provider;

import com.eaglesakura.android.framework.provider.ContextProvider;
import com.eaglesakura.android.garnet.Provide;
import com.eaglesakura.android.garnet.Singleton;

import replace.your.app_package.CustomApplication;
import replace.your.app_package.data.setting.AppSettings;


/**
 * アプリ設定等のグローバルリソースにアクセスする
 */
@Singleton
public class AppContextProvider extends ContextProvider {

    @Provide
    public CustomApplication provideCustomApplication() {
        return (CustomApplication) getApplication();
    }

    @Provide
    public AppSettings provideAppSettings() {
        return new AppSettings.Builder(getContext()).build();
    }
}
