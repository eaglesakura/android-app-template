package replace.your.app_package.repository;

import android.content.Context;

import replace.your.app_package.gen.prop.DebugProps;

/**
 * 開発設定を保持する
 */
public class DebugSetting {
    final Context mContext;

    final DebugProps mProps;

    public DebugSetting(Context context, DebugProps props) {
        mContext = context;
        mProps = props;
    }

    public void setDebugEnable(boolean set) {
        mProps.setDebugEnable(set);
    }

    public boolean isDebugEnable() {
        return mProps.isDebugEnable();
    }
}
