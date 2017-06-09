package replace.your.app_package.ui.navigation.example;

import com.eaglesakura.android.garnet.Inject;
import com.eaglesakura.android.margarine.Bind;
import com.eaglesakura.android.margarine.OnCheckedChanged;
import com.eaglesakura.android.margarine.OnClick;
import com.eaglesakura.cerberus.CallbackTime;
import com.eaglesakura.cerberus.ExecuteTarget;
import com.eaglesakura.sloth.annotation.FragmentLayout;
import com.eaglesakura.sloth.ui.license.LicenseViewActivity;
import com.eaglesakura.sloth.ui.progress.ProgressToken;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 非同期処理を行なう
        getLifecycle().async(ExecuteTarget.LocalQueue, CallbackTime.FireAndForget, task -> {
            try (ProgressToken token = pushProgress(R.string.EsMaterial_Word_Common_DataLoad)) {
                // 適当な非同期処理
                // ダミーで5秒間スリープ
                task.waitTime(1000 * 5);
                return this;
            }
        }).completed((result, task) -> {
            AppLog.system("Hello World!!");
        }).cancelSignal(this)
                .canceled(task -> {
                    AppLog.system("Task Canceled...");
                }).start();
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
