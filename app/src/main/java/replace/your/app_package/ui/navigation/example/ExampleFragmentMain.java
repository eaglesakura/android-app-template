package replace.your.app_package.ui.navigation.example;

import com.eaglesakura.android.garnet.Inject;
import com.eaglesakura.android.margarine.Bind;
import com.eaglesakura.android.margarine.OnCheckedChanged;
import com.eaglesakura.android.margarine.OnClick;
import com.eaglesakura.sloth.annotation.FragmentLayout;
import com.eaglesakura.sloth.app.lifecycle.Lifecycle;
import com.eaglesakura.sloth.app.lifecycle.SlothLiveData;
import com.eaglesakura.sloth.ui.license.LicenseViewActivity;
import com.eaglesakura.util.Timer;

import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
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

    @NonNull
    AsyncTimeData mAsyncTimeData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 非同期テスト
        mAsyncTimeData = new AsyncTimeData(getLifecycle());

        mAsyncTimeData.observeAlive(getLifecycle(), time -> {
            AppLog.test("observeAlive[%d]", time);
        });

        mAsyncTimeData.observeForeground(getLifecycle(), time -> {
            AppLog.test("observeForeground[%d]", time);
        });
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

    public static class AsyncTimeData extends SlothLiveData<Integer> {
        Lifecycle mLifecycle;

        CancellationSignal mTaskSignal;

        public AsyncTimeData(Lifecycle lifecycle) {
            mLifecycle = lifecycle;
        }

        @Override
        protected void onActive() {
            super.onActive();
            CancellationSignal signal = new CancellationSignal();
            mTaskSignal = signal;
            mLifecycle.asyncQueue(task -> {
                Timer timer = new Timer();
                for (int i = 0; i < 10; ++i) {
                    task.waitTime(1000);
                    postValue((int) timer.end());
                }
                return this;
            }).cancelSignal(task -> {
                return signal.isCanceled();
            }).start();
        }

        @Override
        protected void onInactive() {
            super.onInactive();
            mTaskSignal.cancel();
            mTaskSignal = null;
        }
    }
}
