package replace.your.app_package.ui.navigation;

import com.eaglesakura.android.framework.delegate.activity.ContentHolderActivityDelegate;
import com.eaglesakura.material.widget.support.SupportProgressFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import replace.your.app_package.R;
import replace.your.app_package.ui.navigation.base.AppNavigationActivity;
import replace.your.app_package.ui.navigation.example.ExampleWebFragmentMain;
import replace.your.app_package.ui.widget.web.AppWebViewController;

public class ExampleWebActivity extends AppNavigationActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Progress Dialogを追加する
        if (savedInstanceState == null) {
            SupportProgressFragment.attach(this, R.id.Root);
        }
    }

    @Override
    public int getDefaultLayoutId(@NonNull ContentHolderActivityDelegate self) {
        return R.layout.example_web_activity;
    }

    @NonNull
    @Override
    public Fragment newDefaultContentFragment(@NonNull ContentHolderActivityDelegate self) {
        return new ExampleWebFragmentMain();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        AppWebViewController webContentController = AppWebViewController.from(getSupportFragmentManager());
        if (webContentController != null && webContentController.dispatchKeyEvent(event)) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
