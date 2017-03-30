package replace.your.app_package.ui.widget.web;

import com.eaglesakura.sloth.delegate.lifecycle.LifecycleDelegate;
import com.eaglesakura.sloth.ui.support.SupportFragment;
import com.eaglesakura.sloth.util.FragmentUtil;
import com.eaglesakura.material.widget.support.web.SupportWebViewController;

import android.support.v4.app.FragmentManager;

/**
 * Webコンテンツの操作を行う
 */
public class AppWebViewController extends SupportWebViewController {

    public AppWebViewController(Callback callback, LifecycleDelegate lifecycleDelegate) {
        super(callback, lifecycleDelegate);
    }


    public static AppWebViewController from(FragmentManager fragmentManager) {
        return (AppWebViewController) FragmentUtil.listInterfaces(fragmentManager, Holder.class).get(0).getWebContentController();
    }

    public static AppWebViewController from(SupportFragment fragment) {
        return from(fragment.getFragmentManager());
    }

}
