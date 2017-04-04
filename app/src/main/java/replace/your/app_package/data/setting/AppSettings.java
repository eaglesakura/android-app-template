package replace.your.app_package.data.setting;

import com.eaglesakura.sloth.db.property.PropertyStore;
import com.eaglesakura.sloth.db.property.ProviderPropertyStore;
import com.eaglesakura.android.garnet.Singleton;
import com.eaglesakura.json.JSON;
import com.eaglesakura.sloth.db.property.TextDatabasePropertyStore;
import com.eaglesakura.sloth.db.property.model.PropertySource;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;

import replace.your.app_package.R;
import replace.your.app_package.content.CustomDataProvider;
import replace.your.app_package.gen.prop.DebugProps;

/**
 * アプリ設定の保持を行なう
 */
@Singleton
public class AppSettings {
    private final Context mContext;

    private final DebugSetting mDebugSetting;

    private final PropertyStore mPropertyStore;

    private AppSettings(Builder builder) {
        mContext = builder.mContext;
        mDebugSetting = builder.mDebugSetting;
        mPropertyStore = builder.mStore;
    }

    @NonNull
    public Context getContext() {
        return mContext;
    }

    @NonNull
    public DebugSetting getDebugSetting() {
        return mDebugSetting;
    }

    /**
     * データをDBに保存する
     */
    public void commit() {
        mPropertyStore.commit();
    }

    public static class Builder {
        Context mContext;

        DebugSetting mDebugSetting;

        PropertyStore mStore;

        public Builder(Context context) {
            mContext = context.getApplicationContext();
        }

        public Builder store(PropertyStore store) {
            mStore = store;
            return this;
        }

        public Builder debug(DebugSetting debugSettings) {
            mDebugSetting = debugSettings;
            return this;
        }

        public AppSettings build() {
            // Storeの指定がなければContentProviderからStoreを生成する
            if (mStore == null) {
                mStore = newProviderPropertyStore(mContext);
            }

            if (mDebugSetting == null) {
                mDebugSetting = new DebugSetting(mContext, new DebugProps(mStore));
            }
            return new AppSettings(this);
        }

    }

    public static PropertyStore newProviderPropertyStore(Context context) {
        ProviderPropertyStore store = new ProviderPropertyStore(context, CustomDataProvider.PROPERTY_STOREKEY_APPSETTINGS, CustomDataProvider.getPropertyUri(context));
        return store;
    }

    public static TextDatabasePropertyStore newDatabasePropertyStore(Context context) {
        TextDatabasePropertyStore store = new TextDatabasePropertyStore(context, "properties.db");
        try (InputStream is = context.getResources().openRawResource(R.raw.app_properties)) {
            store.loadProperties(JSON.decode(is, PropertySource.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return store;
    }
}
