package replace.your.app_package.ui.navigation.base;

import com.eaglesakura.android.util.ViewUtil;
import com.eaglesakura.sloth.app.SlothActivity;
import com.eaglesakura.sloth.app.delegate.ContentHolderActivityDelegate;
import com.eaglesakura.sloth.app.lifecycle.ActivityLifecycle;
import com.eaglesakura.util.Util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import replace.your.app_package.R;

/**
 * アプリ制御用Activity
 */
public abstract class AppNavigationActivity extends SlothActivity implements ContentHolderActivityDelegate.Callback {

    ContentHolderActivityDelegate mContentHolder;

    @Override
    protected void onCreateLifecycle(@NonNull ActivityLifecycle lifecycle) {
        super.onCreateLifecycle(lifecycle);
        mContentHolder = new ContentHolderActivityDelegate(lifecycle, this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.ifPresent(ViewUtil.findViewById(this, Toolbar.class, R.id.EsMaterial_Toolbar), toolbar -> {
            setSupportActionBar(toolbar);
        });
    }

    /**
     * 管理用FragmentMainを取得する
     */
    @Nullable
    public Fragment getContentFragment() {
        return mContentHolder.getContentFragment();
    }
}
