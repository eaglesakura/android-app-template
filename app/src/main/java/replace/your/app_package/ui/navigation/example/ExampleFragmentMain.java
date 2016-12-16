package replace.your.app_package.ui.navigation.example;

import com.eaglesakura.android.framework.delegate.fragment.SupportFragmentDelegate;
import com.eaglesakura.android.framework.ui.progress.ProgressToken;
import com.eaglesakura.android.framework.ui.support.annotation.FragmentLayout;
import com.eaglesakura.android.garnet.Inject;
import com.eaglesakura.android.margarine.Bind;
import com.eaglesakura.android.margarine.OnCheckedChanged;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;

import replace.your.app_package.R;
import replace.your.app_package.data.setting.AppSettings;
import replace.your.app_package.provider.AppContextProvider;
import replace.your.app_package.ui.navigation.base.AppNavigationFragment;
import replace.your.app_package.util.AppLog;

/**
 * サンプル画面
 */
@FragmentLayout(R.layout.example_helloworld)
public class ExampleFragmentMain extends AppNavigationFragment {

    @Inject(AppContextProvider.class)
    AppSettings mAppSettings;

    @Bind(R.id.Example_Item_Debuggable)
    SwitchCompat mDebugSwitch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asyncUI(task -> {
            try (ProgressToken token = pushProgress(R.string.EsMaterial_Word_Common_DataLoad)) {
                // 適当な非同期処理
                // ダミーで5秒間スリープ
                task.waitTime(1000 * 5);
                return this;
            }
        }).completed((result, task) -> {
            AppLog.system("Hello World!!");
        }).start();
    }

    @Override
    public void onAfterViews(SupportFragmentDelegate self, int flags) {
        super.onAfterViews(self, flags);
        mDebugSwitch.setChecked(mAppSettings.getDebugSetting().isDebugEnable());
    }

    @OnCheckedChanged(R.id.Example_Item_Debuggable)
    void changedDebuggable(boolean checked) {
        mAppSettings.getDebugSetting().setDebugEnable(checked);
        mAppSettings.commit();
    }
}
