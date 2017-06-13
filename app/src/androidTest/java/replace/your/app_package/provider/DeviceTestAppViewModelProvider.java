package replace.your.app_package.provider;

import android.arch.lifecycle.ViewModel;

import static junit.framework.Assert.fail;

/**
 * 必ず新規インスタンスを生成するように変更したViewModelProvider
 */
public class DeviceTestAppViewModelProvider extends AppViewModelProvider {
    @Override
    protected <T extends ViewModel> T getViewModelFromFragment(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Throwable e) {
            fail();
            throw new Error(e);
        }
    }

    @Override
    protected <T extends ViewModel> T getViewModelFromActivity(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Throwable e) {
            fail();
            throw new Error(e);
        }
    }
}
