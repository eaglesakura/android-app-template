package replace.your.app_package.ui.viewmodel.image;

import com.eaglesakura.android.garnet.Garnet;
import com.eaglesakura.android.garnet.Inject;
import com.eaglesakura.sloth.app.lifecycle.GarnetViewModel;

import android.net.Uri;
import android.support.annotation.NonNull;

import replace.your.app_package.provider.AppRepositoryProvider;
import replace.your.app_package.repository.AppImageRepository;

/**
 * 特定ライフサイクルオブジェクトに従属する画像リソースを管理する
 */
public class AppImageViewModel extends GarnetViewModel {

    @Inject(AppRepositoryProvider.class)
    AppImageRepository mImageLoader;

    @Override
    protected void onInitialize() {
        Garnet.inject(this);
    }

    /**
     * Drawableを取得する。
     * データは可能な限りキャッシュされる。
     */
    public DrawableLiveData getFromUri(@NonNull Uri uri) {
        DrawableLiveData result = new DrawableLiveData(mImageLoader, uri);
        return result;
    }
}
