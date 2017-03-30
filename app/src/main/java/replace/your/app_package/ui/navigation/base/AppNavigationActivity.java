package replace.your.app_package.ui.navigation.base;

import com.eaglesakura.sloth.delegate.activity.ContentHolderActivityDelegate;
import com.eaglesakura.sloth.ui.support.ContentHolderActivity;
import com.eaglesakura.util.Util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import replace.your.app_package.R;

/**
 * アプリ制御用Activity
 */
public abstract class AppNavigationActivity extends ContentHolderActivity {

    @Override
    public int getDefaultLayoutId(@NonNull ContentHolderActivityDelegate self) {
        return R.layout.system_activity_with_toolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.ifPresent(findViewById(Toolbar.class, R.id.EsMaterial_Toolbar), toolbar -> {
            setSupportActionBar(toolbar);
        });
    }
}
