package replace.your.app_package.ui.viewmodel.image;

import com.eaglesakura.cerberus.BackgroundTask;
import com.eaglesakura.cerberus.CallbackTime;
import com.eaglesakura.cerberus.ExecuteTarget;
import com.eaglesakura.lambda.Action2;
import com.eaglesakura.lambda.CancelCallback;
import com.eaglesakura.sloth.app.lifecycle.Lifecycle;
import com.eaglesakura.sloth.app.lifecycle.SlothLiveData;
import com.eaglesakura.sloth.data.SupportCancelCallbackBuilder;
import com.eaglesakura.sloth.graphics.SyncImageLoader;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;

import replace.your.app_package.repository.AppImageRepository;
import replace.your.app_package.util.AppLog;

/**
 * 1Drawableごとに管理されるLiveData
 *
 * キャッシュは {@link AppImageViewModel} で共用される。
 */
public class DrawableLiveData extends SlothLiveData<Drawable> {

    /**
     * キャンセルチェック
     */
    private CancelCallback mCancelCallback;

    /**
     * ロード対象のURI
     */
    @NonNull
    private Uri mUri;

    /**
     * ローダー本体
     */
    private AppImageRepository mImageRepository;

    /**
     * Builderの制御を行う
     */
    private Action2<DrawableLiveData, AppImageRepository.Builder> mBuilderAction;

    Lifecycle mLifecycle;

    DrawableLiveData(Lifecycle lifecycle, AppImageRepository repository, Uri uri) {
        mUri = uri;
        mLifecycle = lifecycle;
        mImageRepository = repository;
    }

    /**
     * ロード時、{@link AppImageRepository.Builder} に対するアクションを決定する。
     */
    public DrawableLiveData builder(Action2<DrawableLiveData, AppImageRepository.Builder> action) {
        mBuilderAction = action;
        return this;
    }


    /**
     * キャンセルコールバックをデフォルト以外で指定する
     */
    public DrawableLiveData cancelSignal(CancelCallback cancelCallback) {
        mCancelCallback = cancelCallback;
        return this;
    }

    protected Drawable loadImage(SupportCancelCallbackBuilder cancelCallbackBuilder) throws Exception {
        if (mCancelCallback != null) {
            cancelCallbackBuilder.or(mCancelCallback);
        }

        SyncImageLoader.Builder builder = mImageRepository.newImage(mUri, true);
        if (mBuilderAction != null) {
            mBuilderAction.action(this, builder);
        }
        return builder.await(cancelCallbackBuilder.build());
    }

    @Override
    protected void onActive() {
        super.onActive();

        if (getValue() != null) {
            return;
        }

        mLifecycle.async(ExecuteTarget.LocalParallel, CallbackTime.Alive, (BackgroundTask<Drawable> task) -> {
            return loadImage(SupportCancelCallbackBuilder.from(task));
        }).completed((result, task) -> {
            setValue(result);
        }).failed((error, task) -> {
            AppLog.printStackTrace(error);
        }).start();
    }
}
