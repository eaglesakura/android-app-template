package replace.your.app_package.provider.data;

import com.eaglesakura.sloth.provider.ContextProvider;
import com.eaglesakura.android.garnet.Provide;
import com.eaglesakura.android.garnet.Singleton;

import replace.your.app_package.repository.storage.AppStorageManager;

/**
 * アプリ内での各種Controllerを依存解決するProvider
 */
@Singleton
public class AppStorageProvider extends ContextProvider {
    @Provide
    public AppStorageManager provideStorageController() {
        return new AppStorageManager(getApplication());
    }
}
