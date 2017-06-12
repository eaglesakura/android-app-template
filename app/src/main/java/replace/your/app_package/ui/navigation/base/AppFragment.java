package replace.your.app_package.ui.navigation.base;

import com.eaglesakura.android.garnet.Garnet;
import com.eaglesakura.sloth.ui.progress.SupportProgressFragment;
import com.eaglesakura.sloth.app.SlothFragment;
import com.eaglesakura.sloth.app.support.GarnetSupport;
import com.eaglesakura.sloth.app.support.InstanceStateSupport;
import com.eaglesakura.sloth.app.support.ViewBindingSupport;
import com.eaglesakura.sloth.app.lifecycle.FragmentLifecycle;
import com.eaglesakura.sloth.ui.progress.ProgressToken;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

/**
 * アプリの制御Fragment
 */
public abstract class AppFragment extends SlothFragment {

    /**
     * Fragment用View
     */
    private View mRootView;

    @Override
    protected void onCreateLifecycle(FragmentLifecycle lifecycle) {
        // Dependency Injectionの処理を行う
        GarnetSupport.bind(lifecycle, new GarnetSupport.Callback() {
            @Override
            public void onAfterInjection() {

            }

            @NonNull
            @Override
            public Garnet.Builder newInjectionBuilder(Context context) {
                return Garnet.create(AppFragment.this)
                        .depend(Fragment.class, AppFragment.this)
                        .depend(Activity.class, getActivity())
                        .depend(Context.class, context);
            }
        });

        // View Injectionを行う
        ViewBindingSupport.bind(lifecycle, this, new ViewBindingSupport.Callback() {
            @Override
            public void onAfterViews(View rootView) {
                mRootView = rootView;
            }

            @Override
            public void onAfterBindMenu(Menu menu) {

            }
        });

        // Save/Restoreを行う
        InstanceStateSupport.bind(lifecycle, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return mRootView;
    }

    @NonNull
    public ProgressToken pushProgress(@StringRes int stringRes) {
        return pushProgress(getString(stringRes));
    }

    @NonNull
    public ProgressToken pushProgress(String message) {
        return SupportProgressFragment.pushProgress(this, message);
    }
}
