package replace.your.app_package.provider;

import com.eaglesakura.android.garnet.Provide;
import com.eaglesakura.android.util.FragmentUtil;
import com.eaglesakura.sloth.provider.ContextProvider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import replace.your.app_package.data.res.AppImageLoader;

/**
 * Activity/Fragmentの構造ツリー内部から依存を解決するProvider
 *
 * ライフサイクルに依存するため、Activity/Fragmentが必須となる。
 * Fragment/Activityに紐づくため、シングルトンにはできない。
 */
public class AppLifecycleInterfaceProvider extends ContextProvider {
    Fragment mFragment;

    AppCompatActivity mActivity;

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
    }

    public void setActivity(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        if (context instanceof AppCompatActivity) {
            setActivity(((AppCompatActivity) context));
        }
    }

    @NonNull
    @Override
    protected Context getContext() {
        Context result = null;
        if (mFragment != null) {
            result = mFragment.getContext();
        }
        if (result == null) {
            result = mActivity;
        }
        if (result == null) {
            result = super.getContext();
        }
        return result;
    }

    @Override
    public void onDependsCompleted(Object inject) {
        super.onDependsCompleted(inject);
        if (mFragment == null && mActivity == null) {
            throw new IllegalStateException("Fragment/Activity not binded");
        }

        if (mFragment != null) {
            mActivity = ((AppCompatActivity) mFragment.getActivity());
        }
    }

    <T> T findInterface(Class<? extends T> clazz) {
        return FragmentUtil.listInterfaces(mActivity, clazz).get(0);
    }

    @Provide
    public AppImageLoader provideAppImageLoader() {
        AppImageLoader.Holder holder = findInterface(AppImageLoader.Holder.class);
        return holder.getImageLoader();
    }
}
