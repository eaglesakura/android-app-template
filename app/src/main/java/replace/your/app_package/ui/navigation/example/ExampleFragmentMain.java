package replace.your.app_package.ui.navigation.example;

import com.eaglesakura.android.framework.ui.progress.ProgressToken;
import com.eaglesakura.android.framework.ui.support.annotation.FragmentLayout;

import android.os.Bundle;

import replace.your.app_package.R;
import replace.your.app_package.ui.navigation.base.AppNavigationFragment;
import replace.your.app_package.util.AppLog;

/**
 * サンプル画面
 */
@FragmentLayout(R.layout.example_helloworld)
public class ExampleFragmentMain extends AppNavigationFragment {

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
}
