package replace.your.app_package.ui.navigation.example;

import com.eaglesakura.android.garnet.Inject;
import com.eaglesakura.android.margarine.Bind;
import com.eaglesakura.android.margarine.OnCheckedChanged;
import com.eaglesakura.android.margarine.OnClick;
import com.eaglesakura.android.util.AndroidThreadUtil;
import com.eaglesakura.sloth.annotation.FragmentLayout;
import com.eaglesakura.sloth.ui.license.LicenseViewActivity;
import com.eaglesakura.sloth.util.LiveDataObservers;
import com.eaglesakura.sloth.util.LiveDataUtil;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import replace.your.app_package.R;
import replace.your.app_package.provider.AppViewModelProvider;
import replace.your.app_package.repository.AppSettings;
import replace.your.app_package.provider.AppContextProvider;
import replace.your.app_package.ui.navigation.base.AppNavigationFragment;
import replace.your.app_package.ui.viewmodel.example.ExampleAsyncDataViewModel;
import replace.your.app_package.ui.viewmodel.image.AppImageViewModel;
import replace.your.app_package.util.AppLog;

/**
 * サンプル画面
 */
@FragmentLayout(R.layout.example_helloworld)
public class ExampleFragmentMain extends AppNavigationFragment {

    /**
     * Frameworkにより自動的に依存注入が行われる
     */
    @Inject(AppContextProvider.class)
    AppSettings mAppSettings;

    /**
     * Frameworkにより自動的にViewBindが行われる
     */
    @Bind(R.id.Example_Item_Debuggable)
    SwitchCompat mDebugSwitch;

    @Inject(AppViewModelProvider.class)
    ExampleAsyncDataViewModel mAsyncDataViewModel;

    @Inject(AppViewModelProvider.class)
    AppImageViewModel mImageViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageViewModel.getFromUri(Uri.parse("https://developer.android.com/images/home/nougat_bg_2x.jpg"))
                .builder((data, builder) -> builder.errorImage(android.R.drawable.alert_dark_frame, true))
                .observe(getLifecycle(), LiveDataObservers.single(image -> {
                    AppLog.test("loaded image size[%d x %d]", image.getMinimumWidth(), image.getMinimumHeight());
                }));

        // 非同期テスト
        mAsyncDataViewModel.getAsyncCounter().observe(getLifecycle(), time -> {
            AndroidThreadUtil.assertUIThread();
            AppLog.test("observeAlive[%d]", time);
        });

        mAsyncDataViewModel.getAsyncCounter().observeCurrentForeground(getLifecycle(), time -> {
            AndroidThreadUtil.assertUIThread();
            AppLog.test("observeCurrentForeground[%d]", time);
        });

        mAsyncDataViewModel.getAsyncCounter().observeCurrentForeground(getLifecycle(), LiveDataObservers.single(time -> {
            AndroidThreadUtil.assertUIThread();
            AppLog.test("single/observeCurrentForeground[%d]", time);
        }));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mDebugSwitch.setChecked(mAppSettings.getDebugSetting().isDebugEnable());
        return view;
    }

    /**
     * Frameworkにより自動的にOnCheckedChangeが行われる。
     */
    @OnCheckedChanged(R.id.Example_Item_Debuggable)
    void changedDebuggable(boolean checked) {
        mAppSettings.getDebugSetting().setDebugEnable(checked);
        mAppSettings.commit();
    }

    @OnClick(R.id.Button_Example_ShowLicense)
    void clickShowLicense() {
        LicenseViewActivity.startContent(getContext());
    }

    public interface BindItem {
        String getTitle();
    }

}
