package replace.your.app_package.ui.viewmodel.image;

import com.eaglesakura.android.garnet.Garnet;
import com.eaglesakura.android.thread.UIHandler;
import com.eaglesakura.sloth.app.lifecycle.Lifecycle;
import com.eaglesakura.sloth.app.lifecycle.ServiceLifecycle;

import org.junit.Test;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import replace.your.app_package.AppDeviceTestCase;
import replace.your.app_package.provider.AppViewModelProvider;
import replace.your.app_package.provider.DeviceTestAppViewModelProvider;

import static org.junit.Assert.*;

public class AppImageViewModelTest extends AppDeviceTestCase {
    @Override
    public void onSetup() {
        super.onSetup();

        // Activityと連動しないViewModelに変更する
        Garnet.override(AppViewModelProvider.class, DeviceTestAppViewModelProvider.class);
        Garnet.clearSingletonCache();
    }

    @Test
    public void 異なるViewModelインスタンスが取得できる() throws Throwable {
        assertNotEquals(Garnet.factory(AppViewModelProvider.class).instance(AppImageViewModel.class), Garnet.factory(AppViewModelProvider.class).instance(AppImageViewModel.class));
    }

    @Test
    public void ネットワーク経由で画像をロードできる() throws Throwable {
        AppImageViewModel instance = Garnet.factory(AppViewModelProvider.class).instance(AppImageViewModel.class);
        ServiceLifecycle lifecycle = new ServiceLifecycle();

        try {
            UIHandler.postWithWait(lifecycle::onCreate, () -> false);

            DrawableLiveData liveData = instance.getFromUri(Uri.parse("https://developer.android.com/images/home/nougat_bg_2x.jpg"));
            liveData.observe(lifecycle, image -> {
                validate(image.getMinimumWidth()).from(1);
                validate(image.getMinimumHeight()).from(1);
            });

            Drawable image = liveData.await(() -> false);
            assertNotNull(image);
        } finally {
            UIHandler.postWithWait(lifecycle::onDestroy, () -> false);
        }

    }
}