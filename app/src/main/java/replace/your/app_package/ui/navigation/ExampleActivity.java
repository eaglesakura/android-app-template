package replace.your.app_package.ui.navigation;

import com.eaglesakura.sloth.ui.progress.SupportProgressFragment;
import com.eaglesakura.sloth.app.delegate.ContentHolderActivityDelegate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import replace.your.app_package.R;
import replace.your.app_package.ui.navigation.base.AppNavigationActivity;
import replace.your.app_package.ui.navigation.example.ExampleFragmentMain;

public class ExampleActivity extends AppNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Progress Dialogを追加する
        if (savedInstanceState == null) {
            SupportProgressFragment.attach(this, R.id.Root);
        }
    }

    @Override
    public int getContentLayout(@NonNull ContentHolderActivityDelegate self) {
        return R.layout.system_activity_with_toolbar;
    }

    @NonNull
    @Override
    public Fragment newContentFragment(@NonNull ContentHolderActivityDelegate self) {
        return new ExampleFragmentMain();
    }
}
