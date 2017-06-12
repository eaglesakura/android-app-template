package replace.your.app_package.provider;

import com.eaglesakura.android.garnet.Provide;
import com.eaglesakura.android.garnet.Provider;
import com.eaglesakura.sloth.graphics.loader.ImageLoader;
import com.eaglesakura.sloth.provider.ContextProvider;

import replace.your.app_package.repository.AppImageRepository;

/**
 * 各リポジトリを取得するためのProvider
 */
public class AppRepositoryProvider extends ContextProvider {
    @Override
    public void onDependsCompleted(Object inject) {

    }

    @Override
    public void onInjectCompleted(Object inject) {

    }

    @Provide
    public AppImageRepository provideImageRepository() {
        return new AppImageRepository(getApplication());
    }
}
