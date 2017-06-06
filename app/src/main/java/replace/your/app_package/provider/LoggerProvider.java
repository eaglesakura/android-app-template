package replace.your.app_package.provider;

import com.eaglesakura.android.garnet.Depend;
import com.eaglesakura.android.garnet.Provide;
import com.eaglesakura.android.garnet.Provider;
import com.eaglesakura.android.util.ContextUtil;
import com.eaglesakura.log.Logger;

import android.content.Context;
import android.util.Log;

public class LoggerProvider implements Provider {
    public static final String NAME_APPLOG = "app.default";

    Context mContext;

    boolean mDebuggable;

    @Depend(require = true)
    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public void onDependsCompleted(Object inject) {
        mDebuggable = ContextUtil.isDebug(mContext);
    }

    @Override
    public void onInjectCompleted(Object inject) {
    }

    @Provide(name = NAME_APPLOG)
    public Logger.Impl provideAppLogger() {
        return new Logger.AndroidLogger(Log.class) {
            @Override
            protected int getStackDepth() {
                return super.getStackDepth() + 1;
            }
        }.setStackInfo(mDebuggable);
    }
}
