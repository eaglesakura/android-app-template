package replace.your.app_package.ui.navigation.example;

import com.eaglesakura.android.framework.ui.support.SupportViewAnimationBuilder;
import com.eaglesakura.android.framework.ui.support.annotation.FragmentLayout;
import com.eaglesakura.android.margarine.Bind;
import com.eaglesakura.android.thread.ui.UIHandler;
import com.eaglesakura.material.widget.SupportWebView;
import com.eaglesakura.material.widget.support.web.SupportWebViewController;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.widget.Button;

import replace.your.app_package.R;
import replace.your.app_package.ui.navigation.base.AppNavigationActivity;
import replace.your.app_package.ui.navigation.base.AppNavigationFragment;
import replace.your.app_package.ui.widget.web.AppWebView;
import replace.your.app_package.ui.widget.web.AppWebViewController;


/**
 * Webコンテンツを表示するための参考Fragment
 */
@FragmentLayout(R.layout.example_web)
public class ExampleWebFragmentMain extends AppNavigationFragment
        implements AppWebViewController.Callback, AppWebViewController.Holder {

    AppWebViewController mAppWebContentController = new AppWebViewController(this, mLifecycleDelegate);

    @Bind(R.id.Button_Example)
    Button mTestButton;

    @Nullable
    @Override
    public SupportWebView getWebView(SupportWebViewController self) {
        return ((AppWebView) getView().findViewById(R.id.Content_Web));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIHandler.postUI(() -> getWebView(null).loadUrl("https://google.com"));
    }

    @NonNull
    @Override
    public AppWebViewController getWebContentController() {
        return mAppWebContentController;
    }

    @Override
    public void onScrollStateChanged(SupportWebViewController self, int scrollY, int oldScrollFlag, int newScrollFlag) {
        AppBarLayout appbar = getActivity(AppNavigationActivity.class).findViewById(AppBarLayout.class, R.id.EsMaterial_Toolbar_Root);

        if ((newScrollFlag & AppWebViewController.SCROLL_FLAG_TOP) == 0) {
            // ActionBarを非表示
            SupportViewAnimationBuilder.from(appbar)
                    .toLayoutUpper()
                    .hide();
        } else {
            SupportViewAnimationBuilder.from(appbar)
                    .toLayoutTop()
                    .show();
        }
        if ((newScrollFlag & AppWebViewController.SCROLL_FLAG_BOTTOM) == 0) {
            // ボタンを非表示
            SupportViewAnimationBuilder.from(mTestButton)
                    .fromLayoutBottom()
                    .toLayoutLower()
                    .hide();
        } else {
            SupportViewAnimationBuilder.from(mTestButton)
                    .fromLayoutLower()
                    .toLayoutBottom()
                    .show();
        }
    }
}
